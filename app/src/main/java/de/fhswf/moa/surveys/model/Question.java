package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * The base-model for any question object.
 */
public abstract class Question {

    private String id;

    private String title;

    @Nullable
    private String description;

    /**
     * The total number of users who responded to this individual question.
     */
    private int respondents;

    /**
     * The question's type, as defined in {@link QuestionType}.
     *
     * @return The question's type; must not be null.
     */
    @NonNull
    public abstract QuestionType getType();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public int getRespondents() {
        return respondents;
    }

    public void setRespondents(int respondents) {
        this.respondents = respondents;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Question: '%s' [%s]", title, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
