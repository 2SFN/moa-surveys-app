package de.fhswf.moa.surveys.list.viewholder.question;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;

public class MultiQuestionViewHolder extends BaseViewHolder<MultiQuestionListItem> {
    private TextView title;
    private TextView description;
    private LinearLayout container;
    private ArrayList<String> userinput;

    public MultiQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
        this.container = itemView.findViewById(R.id.container);
    }

    @Override
    public void bind(MultiQuestionListItem item){
        userinput = new ArrayList<>();
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Sicherheitsmassnahme
        container.removeAllViews();

        /*
         Erstellt RadioButtons ohne RadioGroup, damit mehrere ausgewählt werden
         können
        */
        for(String c : item.getQuestion().getOptions()) {
            CheckBox checkBox = new CheckBox(container.getContext());
            checkBox.setText(c);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkBox.isChecked()) {
                        userinput.add(checkBox.getText().toString());
                    } else if (!checkBox.isChecked()) {
                        userinput.remove(checkBox.getText().toString());
                    }

                    item.setUserInput(userinput);
                }
            });

            container.addView(checkBox);
        }

    }
}
