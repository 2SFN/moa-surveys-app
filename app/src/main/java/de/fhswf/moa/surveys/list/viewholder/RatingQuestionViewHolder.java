package de.fhswf.moa.surveys.list.viewholder;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.RatingQuestionListItem;

public class RatingQuestionViewHolder extends BaseViewHolder<RatingQuestionListItem> {
    private TextView title;
    private TextView description;
    private LinearLayout container;

    public RatingQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
        this.container = itemView.findViewById(R.id.container);
    }

    @Override
    public void bind(RatingQuestionListItem item) {
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Sicherheitsmassnahme
        container.removeAllViews();

        // TODO Rating hinzufügen
        RatingBar ratingBar = new RatingBar(container.getContext());
        ratingBar.setStepSize(1F);
        ratingBar.setNumStars(5);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                item.setUserInput(rating);
            }
        });
        container.addView(ratingBar);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ratingBar.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;

        ratingBar.setLayoutParams(layoutParams);
    }
}
