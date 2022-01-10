package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

import java.util.Map;

/**
 * A question that allows the user to enter a rating between one and five stars, points, etc.
 *
 * The results are stored in a map that associates the rating (Integer, key) to the number of
 * users who chose the rating (Integer, value).
 */
public class RatingQuestion extends Question {
    public static final QuestionType QUESTION_TYPE = QuestionType.RATING;

    private Map<Integer, Integer> results;

    // TODO: Add useful constructor(s)

    public Map<Integer, Integer> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Integer> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
