package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.EndQuestion;

public class EndQuestionListItem implements ListItem {
    public static final int TYPE = 7;

    private EndQuestion question;

    public EndQuestionListItem(EndQuestion question) {
        this.question = question;
    }

    public EndQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
