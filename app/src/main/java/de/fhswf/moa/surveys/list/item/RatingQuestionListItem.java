package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.RatingQuestion;

/**
 * Wrapper Class für RatingQuestion
 * @author Joey F.M. Esteves
 */
public class RatingQuestionListItem implements ListItem{
    public static final int TYPE = 6;

    private RatingQuestion question;

    public RatingQuestionListItem(RatingQuestion question) {
        this.question = question;
    }

    public RatingQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
