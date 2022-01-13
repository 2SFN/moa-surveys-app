package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.MultiSelectQuestion;


public class MultiQuestionListItem implements ListItem{
    public static final int TYPE = 2;

    private MultiSelectQuestion question;

    public MultiQuestionListItem(MultiSelectQuestion question) {
        this.question = question;
    }

    public MultiSelectQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
