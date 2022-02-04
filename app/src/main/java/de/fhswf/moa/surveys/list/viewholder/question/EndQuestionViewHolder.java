package de.fhswf.moa.surveys.list.viewholder.question;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.EndQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;

public class EndQuestionViewHolder extends BaseViewHolder<EndQuestionListItem> {

    private final ImageButton results;
    private final ImageButton submit;

    public EndQuestionViewHolder(@NonNull View itemView) {
        super(itemView);

        this.results = itemView.findViewById(R.id.results);
        this.submit = itemView.findViewById(R.id.submit);
    }

    @Override
    public void bind(EndQuestionListItem item) {
        results.setOnClickListener(v -> {
            if(item.getOnResultsClickListener() != null)
                item.getOnResultsClickListener().onResultsClick();
        });

        submit.setOnClickListener(v -> {
            if(item.getOnEndListener() != null)
                item.getOnEndListener().onEndButtonClick(item);
        });
    }
}