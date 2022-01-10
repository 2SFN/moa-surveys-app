package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

/**
 * Question-Typ, der sehr ähnlich zu der {@link SingleSelectQuestion} ist. Allerdings kann der
 * Nutzer hier mehrere Optionen gleichzeitig auswählen.
 * <p>
 * Es ist möglich, eine maximale Anzahl von gleichzeitig auswählbaren Optionen festzulegen.
 * Standardmäßig gibt es keine Beschränkung.
 */
public class MultiSelectQuestion extends SingleSelectQuestion {
    public static final QuestionType QUESTION_TYPE = QuestionType.MULTI_SELECT;

    /**
     * Eine Konstante für {@link this#maxSelectedOptions}, welche eingesetzt wird, wenn es keine
     * Auswahl-Beschränkung für diese Frage geben soll.
     */
    public static final int NO_SELECTION_LIMIT = 0;

    public static final int DEFAULT_MAX_SELECTION = NO_SELECTION_LIMIT;

    // TODO: Add minimum selected options?

    private int maxSelectedOptions = DEFAULT_MAX_SELECTION;

    // TODO: Add useful constructor(s)

    public int getMaxSelectedOptions() {
        return maxSelectedOptions;
    }

    public void setMaxSelectedOptions(int maxSelectedOptions) {
        this.maxSelectedOptions = maxSelectedOptions;
    }

    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
}
