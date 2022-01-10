package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

/**
 * A question that offers a list of options of which the user can pick exactly one.
 *
 * This question type's responses are stored in a map that stores the number of times an option was
 * chosen for each response.
 */
public class SingleSelectQuestion extends Question {
    public static final QuestionType QUESTION_TYPE = QuestionType.SINGLE_SELECT;

    private List<String> options;

    private Map<String, Integer> results;

    // TODO: Add useful constructors
    // TODO: Add simple helper-methods for lists/maps (utility class?)

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Map<String, Integer> getResults() {
        return results;
    }

    public void setResults(Map<String, Integer> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
