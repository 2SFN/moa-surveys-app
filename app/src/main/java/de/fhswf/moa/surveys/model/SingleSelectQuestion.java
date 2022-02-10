package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
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

    public SingleSelectQuestion() {
    }

    public SingleSelectQuestion(String id, String title, @Nullable String description) {
        super(id, title, description);
    }

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

    /**
     * Einfacher Weg, um einer Frage schnell einige Antwort-Optionen hinzuzufügen.
     *
     * @param option Text für die neue Option.
     */
    public SingleSelectQuestion addOption(@NonNull String option) {
        if (this.options == null)
            this.options = new ArrayList<>();

        if (!options.contains(option))
            options.add(option);

        return this;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
