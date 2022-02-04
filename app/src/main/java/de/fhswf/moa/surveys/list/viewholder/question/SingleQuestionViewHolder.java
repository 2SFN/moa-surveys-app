package de.fhswf.moa.surveys.list.viewholder.question;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.SingleQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;

public class SingleQuestionViewHolder extends BaseViewHolder<SingleQuestionListItem> {

    private TextView title;
    private TextView description;
    private LinearLayout container;
    private RadioButton resultbtn;

    public SingleQuestionViewHolder(@NonNull View itemView) {
        super(itemView);

        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
        this.container = itemView.findViewById(R.id.container);
    }

    @Override
    public void bind(SingleQuestionListItem item) {
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Sicherheitsmassnahme
        container.removeAllViews();

        // RadioGroup für Single Selection
        RadioGroup radioGroup = new RadioGroup(itemView.getContext());
        ViewGroup.MarginLayoutParams marginparams = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.MATCH_PARENT,
                ViewGroup.MarginLayoutParams.MATCH_PARENT);
        //marginparams.topMargin = (int) DimensionsUtil.dpToPx(container.getResources(), 16.0f);
        radioGroup.setLayoutParams(marginparams);
//TODO Test this method

        CompoundButton.OnCheckedChangeListener checkedChangeListener = (button, checked) -> {
            item.setUserInput(button.getText().toString());
        };

        for(String c : item.getQuestion().getOptions()) {
            RadioButton radioButton = new RadioButton(container.getContext());
            radioButton.setText(c);
            radioButton.setOnCheckedChangeListener(checkedChangeListener);

            radioGroup.addView(radioButton);
        }

        container.addView(radioGroup);
    }
}
