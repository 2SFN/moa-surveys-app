package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class ResultActivity extends AppCompatActivity {
    private String SurveyID;
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);

        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent MainActivityIntent = getIntent();
        SurveyID = MainActivityIntent.getStringExtra("ID");

        //Setting up View
        setContentView(R.layout.question_view);
        RecyclerView container = findViewById(R.id.question_container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);
    }
}
