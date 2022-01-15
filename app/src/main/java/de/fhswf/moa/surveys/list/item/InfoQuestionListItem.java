package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.InfoQuestion;

public class InfoQuestionListItem implements ListItem {
    public static final int TYPE = 2;
    private InfoQuestion question;

    public InfoQuestionListItem(InfoQuestion question) {
        this.question = question;
    }

    public InfoQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
