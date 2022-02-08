package de.fhswf.moa.surveys.list.viewholder.question;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.RatingQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;

public class RatingQuestionViewHolder extends ContainerCardBaseViewHolder<RatingQuestionListItem> {

    public RatingQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RatingQuestionListItem item) {
        super.bind(item);

        // Neues RatingBar-View
        RatingBar ratingBar = new RatingBar(new ContextThemeWrapper(
                getContext(), R.style.WhiteRatingBar));
        ratingBar.setStepSize(1F);
        ratingBar.setNumStars(5);

        // Hat der Nutzer bereits eine Bewertung ausgewählt?
        if(item.getUserInput() != null) {
            ratingBar.setRating(item.getUserInput());
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                item.setUserInput(rating);
            }
        });

        addContentView(ratingBar);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ratingBar.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;

        ratingBar.setLayoutParams(layoutParams);
    }
}
