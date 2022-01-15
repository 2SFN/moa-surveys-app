package de.fhswf.moa.surveys.list.item;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.model.EndQuestion;

public class EndQuestionListItem implements ListItem {
    public static final int TYPE = 7;

    private EndQuestion question;
    private OnEndListener onEndListener;

    public EndQuestionListItem(EndQuestion question) {
        this.question = question;
    }

    public EndQuestion getQuestion() {
        return question;
    }

    public OnEndListener getOnEndListener() {
        return onEndListener;
    }

    public void setOnEndListener(OnEndListener onEndListener) {
        this.onEndListener = onEndListener;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public interface OnEndListener{
        void onEndButtonClick(@NonNull EndQuestionListItem item );


    }
}
