package de.fhswf.moa.surveys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

/**
 * Launcher-Activity der Anwendung.
 * <p>
 * Ruft eine Liste von verfügbaren Umfragen ab und zeigt diese an.
 *
 * @see SurveyService
 * @see ListAdapter
 * @see Survey
 */
public class MainActivity extends AppCompatActivity implements SurveyListItem.OnSurveyListener {

    private ArrayList<ListItem> items;
    private ListAdapter adapter;

    private SurveyService surveyService;
    private boolean busy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set First View
        setContentView(R.layout.activity_main);
        RecyclerView container = findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        // Init survey-service
        this.surveyService = new RemoteSurveyService(this);
//        this.surveyService = new MockSurveyService(true);   //Mock-Version

        // Daten von Service laden
        this.busy = false;
        refreshContent();
    }

    /**
     * Initiiert das Abrufen von Umfragen.
     */
    private void refreshContent() {
        if (!busy) {
            this.busy = true;

            surveyService.fetchSurveyList(
                    this::addSurveysToList,
                    this::showErrorDialog
            );
        }
    }

    /**
     * Kapselt die erhaltenen Umfragen in {@link SurveyListItem} und fügt sie dem Adapter
     * hinzu.
     *
     * @param result Ergebnis des Services.
     */
    private void addSurveysToList(List<Survey> result) {
        this.busy = false;
        adapter.clear();

        for (Survey c : result) {
            adapter.add(new SurveyListItem(c).setOnSurveyListener(this));
        }
    }

    /**
     * Zeigt einen Fehler-Dialog an.
     * <p>
     * Da die Anwendung bei einem Service-Fehler nutzlos ist, gibt es entweder die Option, es
     * direkt erneut zu versuchen, oder die App wird beendet.
     *
     * @param e Fehler-Details.
     */
    private void showErrorDialog(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(this, R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> refreshContent())
                .setNegativeButton(R.string.exit, (dialog, which) -> finish())
                .setCancelable(true)
                .setOnCancelListener(dialog -> finish())
                .show();
    }

    /**
     * Aufgerufen, wenn der Nutzer eine Umfragen-Karte anklickt.
     * <p>
     * Erzeugt einen Intent mit der Survey-ID als Parameter, welcher die {@link QuestionActivity}
     * startet.
     *
     * @param item Angeklicktes Survey-Item.
     */
    @Override
    public void onSurveyClick(@NonNull SurveyListItem item) {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(QuestionActivity.EXTRA_SURVEY_ID, item.getSurvey().getId());
        startActivity(intent);
    }
}