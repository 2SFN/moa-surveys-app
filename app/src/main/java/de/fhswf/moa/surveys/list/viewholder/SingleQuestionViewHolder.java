package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.SingleQuestionListItem;

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
        ViewGroup.MarginLayoutParams marginparams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT);
        marginparams.topMargin = 10;
        radioGroup.setLayoutParams(marginparams);
//TODO Test this method
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resultbtn = itemView.findViewById(checkedId);
                item.setUserInput(resultbtn.getText().toString());
            }
        });

        int i = 1;
        for(String c : item.getQuestion().getOptions()) {
            RadioButton radioButton = new RadioButton(container.getContext());
            radioButton.setText(c);
            radioButton.setId(i);
            i++;

            radioGroup.addView(radioButton);
        }
    }
}
