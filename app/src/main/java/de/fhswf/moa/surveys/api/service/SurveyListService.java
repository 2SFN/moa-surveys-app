package de.fhswf.moa.surveys.api.service;

import androidx.annotation.Nullable;

import java.util.List;

import de.fhswf.stnau001.surveysandbox.api.OnFailureListener;
import de.fhswf.stnau001.surveysandbox.api.OnSuccessListener;
import de.fhswf.stnau001.surveysandbox.model.Survey;

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
