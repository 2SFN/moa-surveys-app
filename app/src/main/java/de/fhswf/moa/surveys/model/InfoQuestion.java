package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

/**
 * This question type semantically isn't an actual question, but rather provides a simple
 * way to display additional information.
 *
 * This can be, for instance, used to introduce a survey and provide some context, or as a tool
 * to separate more complex surveys by adding an info-card before each section.
 *
 * Info-cards currently do not require any additional fields.
 */
public class InfoQuestion extends Question {
    public static final QuestionType QUESTION_TYPE = QuestionType.INFO;

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }

    public InfoQuestion() {
    }

    public InfoQuestion(String id, String title, String description) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
    }
}
