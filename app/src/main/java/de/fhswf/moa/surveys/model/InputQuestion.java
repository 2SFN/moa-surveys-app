package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * A question that allows the user to freely input any text as a response.
 *
 * This question type's responses are represented as a list of strings.
 *
 * The maximum length of the user's response can be configured.
 */
public class InputQuestion extends Question {
    public static final QuestionType QUESTION_TYPE = QuestionType.INPUT;
    public static final int DEFAULT_MAX_LENGTH = 255;

    private int maxLength = DEFAULT_MAX_LENGTH;

    @Nullable
    private List<String> results;

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Nullable
    public List<String> getResults() {
        return results;
    }

    public void setResults(@Nullable List<String> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
