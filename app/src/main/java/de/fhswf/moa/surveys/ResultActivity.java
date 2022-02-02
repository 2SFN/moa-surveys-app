package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import de.fhswf.moa.surveys.api.service.RemoteSurveyService;
import de.fhswf.moa.surveys.api.service.SurveyService;
import de.fhswf.moa.surveys.list.ListAdapter;
import de.fhswf.moa.surveys.list.item.question.InfoQuestionListItem;
import de.fhswf.moa.surveys.list.item.result.InputQuestionResultItem;
import de.fhswf.moa.surveys.list.item.result.RatingQuestionResultItem;
import de.fhswf.moa.surveys.list.item.result.SelectQuestionResultItem;
import de.fhswf.moa.surveys.model.*;

public class ResultActivity extends AppCompatActivity {

    private String surveyID;
    private SurveyService surveyService;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);

        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent MainActivityIntent = getIntent();
        surveyID = MainActivityIntent.getStringExtra("ID");

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

        surveyService.fetchSurveyDetails(
                surveyID,
                this::handleSurveyResult,
                this::handleError
        );
    }

    private void handleSurveyResult(Survey survey) {
        // Titel
        setTitle(String.format(getString(R.string.result_activity_title), survey.getTitle()));

        if(survey.getQuestions() == null) {
            handleError(new RuntimeException("No questions received!"));
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

    private void handleError(Throwable e) {
        // TODO
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
