package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.InputQuestion;

/**
 * Wrapper Class für InputQuestion
 * @author Joey F.M. Esteves
 */
public class InputQuestionListItem implements ListItem{
    public static final int TYPE = 3;

    private InputQuestion question;

    public InputQuestionListItem(InputQuestion question) {
        this.question = question;
    }

    /**
     * Getter für InputQuestion
     * @return question
     */
    public InputQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
