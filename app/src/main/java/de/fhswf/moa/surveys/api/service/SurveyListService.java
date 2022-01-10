package de.fhswf.moa.surveys.api.service;

import androidx.annotation.Nullable;

import java.util.List;

import de.fhswf.moa.surveys.api.OnFailureListener;
import de.fhswf.moa.surveys.api.OnSuccessListener;
import de.fhswf.moa.surveys.model.Survey;

/**
 * Service definition for fetching the list of available surveys (overview).
 */
public interface SurveyListService {

    /**
     * Fetch the list of available surveys.
     *
     * @param onSuccessListener Success callback.
     * @param onFailureListener Failure callback.
     */
    void fetchSurveys(@Nullable OnSuccessListener<List<Survey>> onSuccessListener,
                      @Nullable OnFailureListener onFailureListener);

}
