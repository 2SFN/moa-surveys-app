package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * Basis-Model für alle Frage-Klassen.
 * <p>
 * Enthält einige rudimentäre Felder, über welche alle Fragen verfügen.
 */
public abstract class Question {

    private String id;

    private String title;

    @Nullable
    private String description;

    /**
     * Die Anzahl von Nutzern, welche insgesamt auf diese Frage geantwortet haben.
     * <p>
     * (Verwendet in der Ergebnis-Präsentation.)
     */
    private int respondents;

    public Question() {
    }

    public Question(String id, String title, @Nullable String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * Frage-Typ (definiert in {@link QuestionType}).
     *
     * @return Frage-Typ; darf nicht null sein.
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
