package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.moa.surveys.list.ListAdapter;
import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.list.item.SurveyListItem;
import de.fhswf.moa.surveys.model.Survey;

public class MainActivity extends AppCompatActivity  implements SurveyListItem.OnSurveyListener {

    private ArrayList<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set First View
        setContentView(R.layout.activity_main);

        RecyclerView container = findViewById(R.id.container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        container.setLayoutManager(layoutManager);



        ListAdapter adapter = new ListAdapter();
        container.setAdapter(adapter);
        // ----- Testing -----
        /*
        ArrayList<Question> Testlist = new ArrayList<>();

        Question q = new InputQuestion();
        q.setTitle("TestTitle q1");
        q.setDescription("Test description");
        q.setId("123");
        Testlist.add(q);
        Survey testsurvey = new Survey("Test01","Probesurvey","This is a test");
        testsurvey.setQuestions(Testlist);

         */
        //---- Testing -----
        adapter.add(new SurveyListItem(new Survey("T001","Titel Survey","Beschreibung bro")).setOnSurveyListener(this));
        adapter.add(new SurveyListItem(new Survey("T002","Titel Survey","Beschreibung vom zweiten Survey \n ist nice hier.")).setOnSurveyListener(this));
        adapter.add(new SurveyListItem(new Survey("T003","Titel Survey","Na \n wie \n verhält \n sich \n dieser \n Text \n so \n ? \n :D \n")).setOnSurveyListener(this));
        adapter.add(new SurveyListItem(new Survey("T004","Titel Survey","Diese Beschreibung soll einfach nur nervig lang sein, also beachte garnicht was hier so steht, wäre übelster time waste das hier zu lesen.")).setOnSurveyListener(this));
    }

    @Override
    public void onSurveyClick(@NonNull SurveyListItem item) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
/*
    @Override
    public void onNoteClick(int position) {
        //TODO navigate to new activity
        // Reference to the item
        //items.get(position);
        Log.d(TAG, "onNoteClick: clicked." + position);
        Intent intent = new Intent(this, QuestionActivity.class);

        //Intent.putExtra("Survey", testsurvey);

        startActivity(intent);
    }

 */
}