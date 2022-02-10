package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

/**
 * Activity, welche die Details einer Umfrage abruft und die Fragen als horizontal navigierbare
 * Karten darstellt.
 * <p>
 * Außerdem wird hier auch das (optionale) Übermitteln der Ergebnisse übernommen
 * (s. {@link this#onEndButtonClick(EndQuestionListItem)}).
 */
public class QuestionActivity extends AppCompatActivity implements
        EndQuestionListItem.OnEndClickListener,
        EndQuestionListItem.OnResultsClickListener {
    public static final String EXTRA_SURVEY_ID = "id";

    private String surveyID;
    private Survey survey;
    private boolean busy;

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

        // Setting up View
        setContentView(R.layout.question_view);
        RecyclerView container = findViewById(R.id.question_container);

        layoutManager = new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);

        // Recyclerview Adapter anfügen
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        // Page Indicator
        container.addItemDecoration(new CirclePagerIndicatorDecoration());
        this.progressText = findViewById(R.id.progress);
        container.addOnScrollListener(new IndicatorScrollListener());
        updateProgressText();

        // Content laden
        this.surveyService = new RemoteSurveyService(this);
//        this.surveyService = new MockSurveyService(true); // Mock-Service

        this.busy = false;
        fetchSurveyDetails();
    }

    /**
     * Stößt das Abrufen der Survey-Details (mit Fragen) an.
     */
    private void fetchSurveyDetails() {
        if (!busy) {
            this.busy = true;

            surveyService.fetchSurveyDetails(
                    surveyID,
                    this::handleSurveyResult,
                    this::handleSurveyDetailsError
            );
        }
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
        this.busy = false;
        this.survey = result;

        if (survey.getQuestions() == null) {
            handleSurveyDetailsError(new RuntimeException("Survey details are incomplete!"));
            return;
        }

        // Titel der Activity
        setTitle(survey.getTitle());
        adapter.clear();

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
                    r -> openResults(),
                    this::handleCommonError
            );

        } catch (JSONException e) {
            handleCommonError(new RuntimeException("Couldn't assemble request body!"));
        }
    }

    @Override
    public void onResultsClick() {
        openResults();
    }

    /**
     * Erzeugt einen Intent, welcher die {@link ResultActivity} startet.
     * <p>
     * Übergibt die Survey-ID als Extra.
     * <p>
     * Beendet außerdem diese Activity, um dem Interaktionsdesign treu zu bleiben
     * (zurück navigieren führt dann wieder zu der Startseite/{@link MainActivity}).
     */
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
        if (position == 0) position = 1;

        // Text aktualisieren
        String label = String.format(Locale.getDefault(),
                getString(R.string.question_activity_progress_label),
                position, adapter.getItemCount());
        progressText.setText(label);
    }

    /**
     * Fehler-Behandlung für das Abrufen der Survey-Details.
     * <p>
     * Da ein Versagen hier bedeutet, dass die Activity nicht weiter genutzt werden kann, gibt
     * es hier lediglich die Möglichkeit, es erneut zu versuchen, oder die Activity zu beenden.
     *
     * @param e Fehler-Details.
     */
    private void handleSurveyDetailsError(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(this, R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(Locale.getDefault(),
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> fetchSurveyDetails())
                .setNegativeButton(R.string.exit, (dialog, which) -> finish())
                .setCancelable(true).setOnCancelListener(di -> finish())
                .show();
    }

    /**
     * Zeige einen Fehler-Dialog für allgemeine Fehler (insbesondere bei der Übermittlung
     * der Ergebnisse).
     *
     * @param e Details.
     */
    private void handleCommonError(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(this, R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(Locale.getDefault(),
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    /**
     * Scroll-Listener Implementierung, welche den Text der Fortschritts-Anzeige aktualisiert,
     * wenn der Nutzer mit dem RecyclerView interagiert.
     */
    private class IndicatorScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            updateProgressText();
        }
    }
}
