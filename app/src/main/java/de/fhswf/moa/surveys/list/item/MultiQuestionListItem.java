package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.MultiSelectQuestion;

/**
 * Wrapper Class für MultiSelectQuestion
 * @author Joey F.M. Esteves
 */
public class MultiQuestionListItem implements ListItem{
    public static final int TYPE = 5;

    private MultiSelectQuestion question;

    public MultiQuestionListItem(MultiSelectQuestion question) {
        this.question = question;
    }

    /**
     * Getter MultiSelectQuestion
     * @return question
     */
    public MultiSelectQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
