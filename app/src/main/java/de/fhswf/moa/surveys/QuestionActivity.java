package de.fhswf.moa.surveys;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import de.fhswf.moa.surveys.list.ListAdapter;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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

        ListAdapter adapter = new ListAdapter(this);
        container.setAdapter(adapter);

    }

}
