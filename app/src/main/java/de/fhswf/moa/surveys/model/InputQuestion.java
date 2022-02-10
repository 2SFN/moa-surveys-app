package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Question-Typ, welcher es dem Nutzer erlaubt, einen Freitext als Antwort einzugeben.
 * <p>
 * Die Ergebnisse dieser Fragen werden als Liste von Strings repräsentiert.
 * <p>
 * Dieser Typ erlaubt es, die maximale Anzahl von Zeichen, die der Nutzer eingeben kann,
 * zu beschränken.
 */
public class InputQuestion extends Question {
    public static final QuestionType QUESTION_TYPE = QuestionType.INPUT;
    public static final int DEFAULT_MAX_LENGTH = 255;

    private int maxLength = DEFAULT_MAX_LENGTH;

    @Nullable
    private List<String> results;

    public InputQuestion() {
    }

    public InputQuestion(String id, String title, @Nullable String description, int maxLength) {
        super(id, title, description);
        setMaxLength(maxLength);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        if (maxLength < 1)
            throw new IllegalArgumentException("Maximum length must be positive and non-zero.");

        this.maxLength = maxLength;
    }

    @Nullable
    public List<String> getResults() {
        return results;
    }

    public void setResults(@Nullable List<String> results) {
        this.results = results;
    }

    /**
     * Einfache Methode, mit der mehrere Ergebnisse hinzugefügt werden können.
     * <p>
     * Verwendet beim Bauen der Strukturen in der
     * {@link de.fhswf.moa.surveys.api.service.MockSurveyService} Implementierung.
     *
     * @param results Neue Ergebnisse.
     * @return Instance.
     */
    public InputQuestion addResults(@NonNull String... results) {
        if (this.results == null)
            this.results = new ArrayList<>();

        Collections.addAll(this.results, results);
        setRespondents(getRespondents() + results.length);

        return this;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
