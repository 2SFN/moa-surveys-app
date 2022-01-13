package de.fhswf.moa.surveys.api.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;

import java.util.List;

import de.fhswf.moa.surveys.api.OnFailureListener;
import de.fhswf.moa.surveys.api.OnSuccessListener;
import de.fhswf.moa.surveys.model.Survey;

/**
 * Service-Definition, welche {@link Survey}-bezogene Funktionen bereitstellt.
 */
public interface SurveyService {

    /**
     * Erhalte eine Liste von verfügbaren Surveys.
     *
     * @param onSuccessListener Erfolg-Callback, enthält eine Liste von Surveys.
     * @param onFailureListener Fehler-Callback.
     */
    void fetchSurveyList(@Nullable OnSuccessListener<List<Survey>> onSuccessListener,
                         @Nullable OnFailureListener onFailureListener);

    /**
     * Frage die Details (alle Fragen und Ergebnisse) zu einer bestimmten Umfrage ab.
     *
     * @param id                ID der abzufragenden Survey.
     * @param onSuccessListener Erfolg-Callback, enthält ein einzelnes Survey-Objekt.
     * @param onFailureListener Fehler-Callback.
     */
    void fetchSurveyDetails(String id,
                            @Nullable OnSuccessListener<Survey> onSuccessListener,
                            @Nullable OnFailureListener onFailureListener);

    /**
     * Sende die Nutzer-Antworten zu einer Survey.
     *
     * @param id                ID der beantworteten Survey.
     * @param responses         Antwort-Daten als JSON-Objekt(e).
     * @param onSuccessListener Erfolg-Callback, leerer Ergebnis-Typ.
     * @param onFailureListener Fehler-Callback.
     */
    void submitResponses(@NonNull String id, @NonNull JSONArray responses,
                         @Nullable OnSuccessListener<Void> onSuccessListener,
                         @Nullable OnFailureListener onFailureListener);

}
