package de.fhswf.moa.surveys.api.service;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import de.fhswf.moa.surveys.api.OnFailureListener;
import de.fhswf.moa.surveys.api.OnSuccessListener;
import de.fhswf.moa.surveys.api.parser.ParsingException;
import de.fhswf.moa.surveys.model.InfoQuestion;
import de.fhswf.moa.surveys.model.InputQuestion;
import de.fhswf.moa.surveys.model.MultiSelectQuestion;
import de.fhswf.moa.surveys.model.RatingQuestion;
import de.fhswf.moa.surveys.model.SingleSelectQuestion;
import de.fhswf.moa.surveys.model.Survey;

/**
 * Mock-Service, welcher immer einige statische Test-Daten zurückgibt.
 * <p>
 * Alternativ kann konfiguriert werden, dass die Methoden immer eine negative (Fehler) Meldung
 * als Antwort erzeugen (s. {@link this#setGenerateNegativeResponses(boolean)}).
 */
public class MockSurveyService implements SurveyService {
    public static final int DELAY = 2000;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private boolean generateNegativeResponses;

    private final ArrayList<Survey> surveys;

    public MockSurveyService() {
        this.surveys = new ArrayList<>();

        // Fill in some example data
        fillExampleSurveys();
    }

    public MockSurveyService(boolean generateNegativeResponses) {
        this();

        this.generateNegativeResponses = generateNegativeResponses;
    }

    @Override
    public void fetchSurveyList(@Nullable OnSuccessListener<List<Survey>> onSuccessListener,
                                @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("FetchSurveyList", onFailureListener);
        } else if (onSuccessListener != null) {
            handler.postDelayed(() -> onSuccessListener.onSuccess(
                    Collections.unmodifiableList(surveys)), DELAY);
        }
    }

    @Override
    public void fetchSurveyDetails(String id,
                                   @Nullable OnSuccessListener<Survey> onSuccessListener,
                                   @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("FetchSurveyDetails", onFailureListener);
        } else if (onSuccessListener != null) {
            handler.postDelayed(() -> {
                for (Survey c : surveys) {
                    if (c.getId().equals(id)) {
                        onSuccessListener.onSuccess(c);
                        return;
                    }
                }

                if (onFailureListener != null)
                    onFailureListener.onFailure(new RuntimeException("Survey not found! (mock)"));
            }, DELAY);
        }
    }

    @Override
    public void submitResponses(@NonNull String id, @NonNull JSONArray responses,
                                @Nullable OnSuccessListener<Void> onSuccessListener,
                                @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("SubmitResponse", onFailureListener);
        } else if (onSuccessListener != null) {
            handler.postDelayed(() -> onSuccessListener.onSuccess(null), DELAY);
        }
    }

    public boolean isGenerateNegativeResponses() {
        return generateNegativeResponses;
    }

    public void setGenerateNegativeResponses(boolean generateNegativeResponses) {
        this.generateNegativeResponses = generateNegativeResponses;
    }

    private void errorCall(String source, @Nullable OnFailureListener onFailureListener) {
        if (onFailureListener != null) {
            handler.postDelayed(() -> onFailureListener.onFailure(
                    new ParsingException("Mock-Error for %s!", source)), DELAY);
        }
    }

    /**
     * Generiert einige Beispiel-Umfragen zum Testen.
     */
    private void fillExampleSurveys() {
        surveys.add(new Survey(
                        "S1", "Lieblingsfarbe",
                        "Kurze Fragen zu deinen Gedanken über verschiedene Farben."
                ).addQuestion(new InfoQuestion(
                        "Q1-1", "Umfrage zu Lieblingsfarben",
                        "Im Folgenden findest du einige Fragen zum Thema Farben!"
                )).addQuestion(new InputQuestion(
                        "Q1-2", "Gedanken",
                        "Beschreibe, wie du dich fühlst, wenn du über das Thema " +
                                "'Farben' nachdenkst.", 500
                ).addResults(
                "Gut", "Schlecht", "Medium", "Glücklich", "Nervös"
                )).addQuestion(randomizeSelectionResults(new SingleSelectQuestion(
                        "Q1-3", "Lieblingsfarbe",
                        "Wähle hier deine absolute Lieblingsfarbe aus. Sollte deine " +
                                "Farbe nicht aufgeführt sein, wähle bitte eine Farbe, die " +
                                "deiner am ähnlichsten sieht."
                ).addOption("Rot").addOption("Blau").addOption("Weiß").addOption("Schwarz")
                        .addOption("Grün").addOption("Magenta").addOption("Aubergine")
                        .addOption("Kristall").addOption("Türkis").addOption("Lachs")
                        .addOption("Salbei").addOption("Fuchsia").addOption("Beige"))
                ).addQuestion(randomizeSelectionResults(new MultiSelectQuestion(
                        "Q1-4", "Hässliche Farben",
                        "Wähle bis zu fünf Farben, die absolut unschön aussehen.",
                        5
                ).addOption("Rot").addOption("Blau").addOption("Weiß").addOption("Schwarz")
                        .addOption("Grün").addOption("Magenta").addOption("Aubergine")
                        .addOption("Kristall").addOption("Türkis").addOption("Lachs")
                        .addOption("Salbei").addOption("Fuchsia").addOption("Beige"))
                ).addQuestion(randomizeRatingResults(new RatingQuestion(
                        "Q1-5", "Feedback zur Umfrage",
                        "Was hältst du von dieser Umfrage?"
                )))
        );

        surveys.add(new Survey(
                "S2", "Beispiel 1",
                "Diese Umfrage enthält keine tatsächlichen Fragen und ist nur hier, " +
                        "damit alles ein wenig gefüllter ausschaut."
        ).addQuestion(new InfoQuestion("Q2-1", "Leer", "Hier ist nichts.")));

        surveys.add(new Survey(
                "S3", "Beispiel 2",
                "Diese Umfrage enthält keine tatsächlichen Fragen und ist nur hier, " +
                        "damit alles ein wenig gefüllter ausschaut."
        ).addQuestion(new InfoQuestion("Q3-1", "Leer", "Hier ist nichts.")));

        surveys.add(new Survey(
                "S4", "Beispiel 3",
                "Diese Umfrage enthält keine tatsächlichen Fragen und ist nur hier, " +
                        "damit alles ein wenig gefüllter ausschaut."
        ).addQuestion(new InfoQuestion("Q4-1", "Leer", "Hier ist nichts.")));
    }

    /**
     * Einfache Helfer-Methode, welche die Ergebnisse der angegebenen Question mit zufälligen
     * Werten füllt.
     *
     * @param question Ziel-Frage.
     * @return Ziel-Frage.
     */
    private SingleSelectQuestion randomizeSelectionResults(@NonNull SingleSelectQuestion question) {
        HashMap<String, Integer> map = new HashMap<>(question.getOptions().size());

        Random random = new Random();
        int sum = 0;

        for (String c : question.getOptions()) {
            int rnd = random.nextInt(123);
            map.put(c, rnd);
            sum += rnd;
        }

        question.setResults(map);
        question.setRespondents(sum);

        return question;
    }

    private RatingQuestion randomizeRatingResults(@NonNull RatingQuestion question) {
        HashMap<Integer, Integer> map = new HashMap<>(5);

        Random random = new Random();
        int sum = 0;

        for (int i = 1; i <= 5; i++) {
            int rnd = random.nextInt(123);
            map.put(i, rnd);
            sum += rnd;
        }

        question.setResults(map);
        question.setRespondents(sum);

        return question;
    }

}
