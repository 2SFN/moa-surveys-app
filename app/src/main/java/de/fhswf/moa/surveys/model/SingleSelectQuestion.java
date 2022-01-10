package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

/**
 * Question-Typ für Fragen, die dem Nutzer eine Liste von Optionen präsentiert und eine einzelne
 * Auswahl erlaubt.
 * <p>
 * Die Ergebnisse für diesen Frage-Typen werden in einer {@link Map} abgelegt, welche den
 * jeweiligen Optionen (String, key) der Anzahl der Nutzer zuordnet, welche dies Option
 * ausgewählt haben (Integer, value).
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
