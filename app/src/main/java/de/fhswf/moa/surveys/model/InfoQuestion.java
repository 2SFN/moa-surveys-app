package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

/**
 * Dieser Question-Typ steht nicht für eine tatsächliche Frage, sondern dient viel eher als
 * einfache Möglichkeit, dem Nutzer zusätzliche Informationen zur Verfügung zu stellen.
 * <p>
 * Dies könnte z.B. genutzt werden, um eine Umfrage einzuleiten und einen Kontext zu etablieren,
 * oder aber um komplexere Umfragen durch Info-Karten in Abschnitte zu unterteilen.
 * <p>
 * Info-Karten erfordern aktuell keine zusätzlichen Felder.
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
