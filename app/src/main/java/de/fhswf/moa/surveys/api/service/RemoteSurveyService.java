package de.fhswf.moa.surveys.api.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.fhswf.moa.surveys.api.OnFailureListener;
import de.fhswf.moa.surveys.api.OnSuccessListener;
import de.fhswf.moa.surveys.api.parser.SurveyListParser;
import de.fhswf.moa.surveys.api.parser.SurveyParser;
import de.fhswf.moa.surveys.api.remote.RemoteRequest;
import de.fhswf.moa.surveys.model.Survey;

/**
 * Service-Implementierung für die Live-API über HTTP.
 *
 * @see RemoteRequest Für HTTP-Anfragen verwendet.
 */
public class RemoteSurveyService implements SurveyService {

    /**
     * Basis-URL des Remote-Backends (ohne abschließendes '/').
     */
    public static final String BASE_URL = "http://167.86.108.121:9084/SurveyBackend/api";

    private final @NonNull
    Context context;

    /**
     * Erzeuge eine neue Service-Instanz.
     *
     * @param context Context, erforderlich für Requests.
     */
    public RemoteSurveyService(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void fetchSurveyList(@Nullable OnSuccessListener<List<Survey>> onSuccessListener,
                                @Nullable OnFailureListener onFailureListener) {
        new RemoteRequest<>(
                context,
                BASE_URL + "/survey/list",
                new JSONObject(),
                new SurveyListParser(),
                onSuccessListener,
                onFailureListener
        ).execute();
    }

    @Override
    public void fetchSurveyDetails(String id,
                                   @Nullable OnSuccessListener<Survey> onSuccessListener,
                                   @Nullable OnFailureListener onFailureListener) {
        try {
            new RemoteRequest<>(
                    context,
                    BASE_URL + "/survey/details",
                    new JSONObject().put("id", id),
                    new SurveyParser(),
                    onSuccessListener,
                    onFailureListener
            ).execute();
        } catch (JSONException e) {
            if (onFailureListener != null)
                onFailureListener.onFailure(e);
        }
    }

    @Override
    public void submitResponses(@NonNull String id, @NonNull JSONArray responses,
                                @Nullable OnSuccessListener<Void> onSuccessListener,
                                @Nullable OnFailureListener onFailureListener) {
        try {
            new RemoteRequest<>(
                    context,
                    BASE_URL + "/survey/respond",
                    new JSONObject()
                            .put("id", id)
                            .put("responses", responses),
                    null, // Dieser API-Aufruf erwartet keine Ergebnis-Daten
                    onSuccessListener,
                    onFailureListener
            ).execute();
        } catch (JSONException e) {
            if (onFailureListener != null)
                onFailureListener.onFailure(e);
        }
    }
}
