package de.fhswf.moa.surveys.list.item;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.model.EndQuestion;

/**
 * Wrapper Class für EndQuestion
 * @author Joey F.M. Esteves
 */
public class EndQuestionListItem implements ListItem {
    public static final int TYPE = 7;

    private EndQuestion question;
    private OnEndListener onEndListener;

    //Konstruktor
    public EndQuestionListItem(EndQuestion question) {
        this.question = question;
    }

    /**
     * Getter EndQuestion
     * @return question
     */
    public EndQuestion getQuestion() {
        return question;
    }

    /**
     * Getter Listener
     * @return onEndListener
     */
    public OnEndListener getOnEndListener() {
        return onEndListener;
    }

    /**
     * Setter Listener
     * @param onEndListener
     */
    //TODO ist dieser Setter Falsch ? vgl. Survey setter
    public EndQuestionListItem setOnEndListener(OnEndListener onEndListener) {
        this.onEndListener = onEndListener;
        return this;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    /**
     * Interface Listener für Button auf EndQuestion Item.
     */
    public interface OnEndListener{
        void onEndButtonClick(@NonNull EndQuestionListItem item );


    }
}
