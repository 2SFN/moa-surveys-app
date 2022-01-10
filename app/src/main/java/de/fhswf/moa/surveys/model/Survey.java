package de.fhswf.moa.surveys.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Data-model for a survey.
 */
public class Survey {

    private String id;

    private String title;

    private String description;

    @Nullable
    private ArrayList<Question> questions;

    public Survey() {
    }

    public Survey(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(@Nullable ArrayList<Question> questions) {
        this.questions = questions;
    }
}
