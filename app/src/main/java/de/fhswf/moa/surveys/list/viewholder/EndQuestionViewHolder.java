package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.EndQuestionListItem;

public class EndQuestionViewHolder extends BaseViewHolder<EndQuestionListItem> implements View.OnClickListener {

    private TextView title;

    private Button results;
    private EndQuestionListItem currentEndQuestion;

    public EndQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.results = itemView.findViewById(R.id.resultbtn);
        results.setOnClickListener(this);
    }

    @Override
    public void bind(EndQuestionListItem item) {
        this.currentEndQuestion = item;
        title.setText(item.getQuestion().getTitle());

        // TODO Möglichkeit widgets  hinzufügen
    }

    @Override
    public void onClick(View v) {
        if(currentEndQuestion.getOnEndListener() != null)
            currentEndQuestion.getOnEndListener().onEndButtonClick(currentEndQuestion);
    }
}