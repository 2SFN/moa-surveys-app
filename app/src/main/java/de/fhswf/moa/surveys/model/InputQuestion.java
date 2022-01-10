package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Nullable
    public List<String> getResults() {
        return results;
    }

    public void setResults(@Nullable List<String> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
