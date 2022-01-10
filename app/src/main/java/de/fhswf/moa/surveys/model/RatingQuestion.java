package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

import java.util.Map;

/**
 * Question-Typ für Fragen, die es dem Nutzer erlauben, eine Bewertung zwischen 1 und 6 Sternen
 * abzugeben.
 * <p>
 * Die Ergebnisse dieser Fragen werden in einer {@link Map} abgelegt, die jeder Bewertung
 * (Integer, key) die Anzahl der Nutzer, die diese Option gewählt haben (Integer, value) zuordnet.
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
