package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import de.fhswf.moa.surveys.list.ListAdapter;
import de.fhswf.moa.surveys.list.item.EndQuestionListItem;

public class QuestionActivity extends AppCompatActivity implements EndQuestionListItem.OnEndListener {

    private String SurveyID;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent MainActivityIntent = getIntent();
        SurveyID = MainActivityIntent.getStringExtra("ID");

        //Setting up View
        setContentView(R.layout.question_view);
        RecyclerView container = findViewById(R.id.question_container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        container.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);


        container.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int current = layoutManager.findLastVisibleItemPosition();
                Log.i("Test", "Current position: " + current);
            }
        });

        //Recyclerview Adapter anfügen
        ListAdapter adapter = new ListAdapter();
        container.setAdapter(adapter);

    }

    /**
     *
     * @param item
     */
    @Override
    public void onEndButtonClick(@NonNull EndQuestionListItem item) {
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("ID",SurveyID);
        startActivity(intent);
    }
}
