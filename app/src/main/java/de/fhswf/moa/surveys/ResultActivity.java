package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.Locale;

import de.fhswf.moa.surveys.api.service.RemoteSurveyService;
import de.fhswf.moa.surveys.api.service.SurveyService;
import de.fhswf.moa.surveys.list.ListAdapter;
import de.fhswf.moa.surveys.list.item.question.InfoQuestionListItem;
import de.fhswf.moa.surveys.list.item.result.InputQuestionResultItem;
import de.fhswf.moa.surveys.list.item.result.RatingQuestionResultItem;
import de.fhswf.moa.surveys.list.item.result.SelectQuestionResultItem;
import de.fhswf.moa.surveys.model.InfoQuestion;
import de.fhswf.moa.surveys.model.InputQuestion;
import de.fhswf.moa.surveys.model.Question;
import de.fhswf.moa.surveys.model.RatingQuestion;
import de.fhswf.moa.surveys.model.SingleSelectQuestion;
import de.fhswf.moa.surveys.model.Survey;

public class ResultActivity extends AppCompatActivity {
    public static final String EXTRA_SURVEY_ID = "id";

    private String surveyID;
    private SurveyService surveyService;
    private ListAdapter adapter;
    private boolean busy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent MainActivityIntent = getIntent();
        surveyID = MainActivityIntent.getStringExtra(EXTRA_SURVEY_ID);

        //Setting up View
        setContentView(R.layout.question_view);
        RecyclerView container = findViewById(R.id.question_container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);

        adapter = new ListAdapter();
        container.setAdapter(adapter);

        // Daten abrufen
        surveyService = new RemoteSurveyService(this);
        fetchSurveyDetails();
    }

    private void fetchSurveyDetails() {
        if(!busy) {
            this.busy = true;

            surveyService.fetchSurveyDetails(
                    surveyID,
                    this::handleSurveyResult,
                    this::handleSurveyDetailsError
            );
        }
    }

    private void handleSurveyResult(Survey survey) {
        this.busy = false;
        adapter.clear();

        // Titel
        setTitle(String.format(getString(R.string.result_activity_title), survey.getTitle()));

        if(survey.getQuestions() == null) {
            handleSurveyDetailsError(new RuntimeException("No questions received!"));
            return;
        }

        // Ergebnis-Items erzeugen
        for(Question q : survey.getQuestions()) {
            switch (q.getType()) {
                case INFO:
                    adapter.add(new InfoQuestionListItem((InfoQuestion) q));
                    break;
                case SINGLE_SELECT:
                case MULTI_SELECT:
                    adapter.add(new SelectQuestionResultItem((SingleSelectQuestion) q));
                    break;
                case RATING:
                    adapter.add(new RatingQuestionResultItem((RatingQuestion) q));
                    break;
                case INPUT:
                    adapter.add(new InputQuestionResultItem((InputQuestion) q));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Fehler-Behandlung für das Abrufen der Survey-Details.
     *
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
}
