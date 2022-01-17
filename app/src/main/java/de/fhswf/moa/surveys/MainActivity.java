package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.moa.surveys.api.service.RemoteSurveyService;
import de.fhswf.moa.surveys.api.service.SurveyService;
import de.fhswf.moa.surveys.list.ListAdapter;
import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.list.item.SurveyListItem;
import de.fhswf.moa.surveys.model.Survey;

public class MainActivity extends AppCompatActivity  implements SurveyListItem.OnSurveyListener {

    private ArrayList<ListItem> items;
    private ListAdapter adapter;

    private SurveyService surveyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set First View
        setContentView(R.layout.activity_main);
        RecyclerView container = findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        container.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        // Init survey-service
        this.surveyService = new RemoteSurveyService(this);
//        this.surveyService = new RemoteSurveyService(this);   Live-Version

        // Daten von Service laden
        surveyService.fetchSurveyList(
                this::addSurveysToList,
                this::showErrorDialog
        );
    }

    private void addSurveysToList(List<Survey> result) {
        for(Survey c : result) {
            adapter.add(new SurveyListItem(c).setOnSurveyListener(this));
        }
    }

    private void showErrorDialog(Throwable e) {
        // TODO: Ordentlichen Fehler-Dialog anzeigen, neu versuchen Option
        e.printStackTrace();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSurveyClick(@NonNull SurveyListItem item) {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("ID",item.getSurvey().getId());
        startActivity(intent);
    }
}