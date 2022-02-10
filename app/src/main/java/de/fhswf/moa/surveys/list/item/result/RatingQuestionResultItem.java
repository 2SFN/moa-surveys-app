package de.fhswf.moa.surveys.list.item.result;

import de.fhswf.moa.surveys.list.item.question.RatingQuestionListItem;
import de.fhswf.moa.surveys.model.RatingQuestion;

/**
 * Result-Item für {@link RatingQuestion}.
 */
public class RatingQuestionResultItem extends RatingQuestionListItem {
    public static final int ITEM_TYPE = 12;

    public RatingQuestionResultItem(RatingQuestion question) {
        super(question);
    }

    @Override
    public int getType() {
        return ITEM_TYPE;
    }
}
