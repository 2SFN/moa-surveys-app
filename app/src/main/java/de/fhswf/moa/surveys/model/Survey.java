package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Daten-Modell für eine Survey.
 */
public class Survey {

    private String id;

    private String title;

    private String description;

    @Nullable
    private List<Question> questions;

    public Survey() {
    }

    public Survey(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Survey(String id, String title, String description,
                  @Nullable List<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(@Nullable List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Einfache Helfer-Methode, um die Fragen der Survey zu füllen.
     *
     * @param question Frage zum Hinzufügen.
     */
    public Survey addQuestion(@NonNull Question question) {
        if (this.questions == null)
            this.questions = new ArrayList<>();

        if (!questions.contains(question))
            questions.add(question);

        return this;
    }
}
