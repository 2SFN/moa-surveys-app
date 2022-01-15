package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.InputQuestion;

public class InputQuestionListItem implements ListItem{
    public static final int TYPE = 3;

    private InputQuestion question;

    public InputQuestionListItem(InputQuestion question) {
        this.question = question;
    }

    public InputQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
