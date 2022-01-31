package de.fhswf.moa.surveys.list.item.question;

import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.model.InfoQuestion;

/**
 * Wrapper Class für InfoQuestion
 * @author  Joey F.M. Esteves
 */
public class InfoQuestionListItem implements ListItem {
    public static final int TYPE = 2;
    private InfoQuestion question;

    //Konstruktor
    public InfoQuestionListItem(InfoQuestion question) {
        this.question = question;
    }

    /**
     * Getter InfoQuestion
     * @return question
     */
    public InfoQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
