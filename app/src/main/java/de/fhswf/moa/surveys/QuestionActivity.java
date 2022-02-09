package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import de.fhswf.moa.surveys.api.service.RemoteSurveyService;
import de.fhswf.moa.surveys.api.service.SurveyService;
import de.fhswf.moa.surveys.list.ListAdapter;
import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.list.item.question.EndQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.InfoQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.InputQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.QuestionResultItem;
import de.fhswf.moa.surveys.list.item.question.RatingQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.SingleQuestionListItem;
import de.fhswf.moa.surveys.model.InfoQuestion;
import de.fhswf.moa.surveys.model.InputQuestion;
import de.fhswf.moa.surveys.model.MultiSelectQuestion;
import de.fhswf.moa.surveys.model.Question;
import de.fhswf.moa.surveys.model.RatingQuestion;
import de.fhswf.moa.surveys.model.SingleSelectQuestion;
import de.fhswf.moa.surveys.model.Survey;
import de.fhswf.moa.surveys.util.CirclePagerIndicatorDecoration;

public class QuestionActivity extends AppCompatActivity implements
        EndQuestionListItem.OnEndClickListener,
        EndQuestionListItem.OnResultsClickListener {
    public static final String EXTRA_SURVEY_ID = "id";

    private String surveyID;
    private Survey survey;

    private SurveyService surveyService;
    private ListAdapter adapter;
    private TextView progressText;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent mainActivityIntent = getIntent();
        surveyID = mainActivityIntent.getStringExtra(EXTRA_SURVEY_ID);

        //Setting up View
        setContentView(R.layout.question_view);
        RecyclerView container = findViewById(R.id.question_container);

        layoutManager = new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);

        //Recyclerview Adapter anfügen
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        // Page Indicator
        container.addItemDecoration(new CirclePagerIndicatorDecoration());
        this.progressText = findViewById(R.id.progress);
        container.addOnScrollListener(new IndicatorScrollListener());
        updateProgressText();

        this.surveyService = new RemoteSurveyService(this);
        surveyService.fetchSurveyDetails(
                surveyID,
                this::handleSurveyResult,
                this::handleError
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Actionbar-Menü-Eintrag, mit dem man direkt zu den Ergebnissen springen kann
        // (Icon analog zur End-Card)
        menu.add(R.string.questions_menu_results)
                .setOnMenuItemClickListener(m -> {
                    openResults();
                    return true;
                }).setIcon(R.drawable.ic_bar_chart)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Aufgerufen, wenn der SurveyService die Survey erfolgreich abgerufen hat.
     *
     * @param result Survey mit Details.
     */
    private void handleSurveyResult(Survey result) {
        this.survey = result;

        if (survey.getQuestions() == null) {
            // TODO: Fehler behandeln
            return;
        }

        // Titel der Activity
        setTitle(survey.getTitle());

        for (Question c : survey.getQuestions()) {
            ListItem item;
            switch (c.getType()) {
                case INFO:
                    item = new InfoQuestionListItem((InfoQuestion) c);
                    break;
                case INPUT:
                    item = new InputQuestionListItem((InputQuestion) c);
                    break;
                case SINGLE_SELECT:
                    item = new SingleQuestionListItem((SingleSelectQuestion) c);
                    break;
                case MULTI_SELECT:
                    item = new MultiQuestionListItem((MultiSelectQuestion) c);
                    break;
                case RATING:
                    item = new RatingQuestionListItem((RatingQuestion) c);
                    break;
                default:
                    throw new RuntimeException("Unsupported question type!");
            }

            adapter.add(item);
        }

        // End-Card einfügen
        adapter.add(new EndQuestionListItem()
                .setOnEndListener(this)
                .setOnResultsClickListener(this));

        // Fortschritts-Text aktualisieren
        updateProgressText();
    }

    private void handleError(Throwable e) {
        // TODO
    }

    /**
     * Aufgerufen, wenn der "Ergebnisse anzeigen" Button geklickt wird.
     *
     * @param item End-Card-Item.
     */
    @Override
    public void onEndButtonClick(@NonNull EndQuestionListItem item) {
        try {
            // I. Ergebnisse übermitteln
            JSONArray results = new JSONArray();

            for (ListItem c : adapter.getItems()) {
                if (c instanceof QuestionResultItem) {
                    JSONObject res = ((QuestionResultItem) c).getResult();
                    if (res != null)
                        results.put(res);
                }
            }

            // Ergebnis-Array fertig
            // Ergebnisse senden
            surveyService.submitResponses(
                    survey.getId(),
                    results,
                    r -> {
                        // II. Zur result-activity
                        openResults();
                    },
                    e -> {
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        // TODO: Fehler-Behandlung
                    }
            );

        } catch (JSONException e) {
            e.printStackTrace();
            // TODO: Fehler-Behandlung
        }
    }

    @Override
    public void onResultsClick() {
        openResults();
    }

    private void openResults() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_SURVEY_ID, surveyID);
        startActivity(intent);
        finish();
    }

    /**
     * Aktualisiert den Fortschritts-Text am unteren Bildschirmrand.
     */
    private void updateProgressText() {
        // Position ermitteln
        int position = layoutManager.findFirstVisibleItemPosition() + 1;
        if(position == 0) position = 1;

        // Text aktualisieren
        String label = String.format(Locale.getDefault(),
                getString(R.string.question_activity_progress_label),
                position, adapter.getItemCount());
        progressText.setText(label);
    }

    private class IndicatorScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            updateProgressText();
        }
    }
}
