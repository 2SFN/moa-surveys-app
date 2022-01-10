package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

/**
 * A question that's very similar to {@link SingleSelectQuestion}, but allows the user to select
 * multiple options.
 *
 * There's an option to set a maximum number of options that the user can select simultaneously.
 * By default, there's no limit in place.
 */
public class MultiSelectQuestion extends SingleSelectQuestion {
    public static final QuestionType QUESTION_TYPE = QuestionType.MULTI_SELECT;

    /**
     * A constant for {@link this#maxSelectedOptions} that is used as a value when there's
     * no selection limit in place (ie. the user can select as many options as they like).
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
