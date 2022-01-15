package de.fhswf.moa.surveys.list.item;


import de.fhswf.moa.surveys.model.SingleSelectQuestion;

public class SingleQuestionListItem implements ListItem {
    public static final int TYPE = 4;

    private SingleSelectQuestion question;

    public SingleQuestionListItem(SingleSelectQuestion question) {
        this.question = question;
    }

    public SingleSelectQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
