package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.list.item.MultiQuestionListItem;

public class MultiQuestionViewHolder extends BaseViewHolder<MultiQuestionListItem> {
    private TextView title;
    private TextView description;
    private LinearLayout container;

    public MultiQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = title;
        this.description = description;
        this.container = container;
    }

    @Override
    public void bind(MultiQuestionListItem item){
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Sicherheitsmassnahme
        container.removeAllViews();

        /*
         Erstellt RadioButtons ohne RadioGroup, damit mehrere ausgewählt werden
         können
        */
        for(String c : item.getQuestion().getOptions()) {
            RadioButton radioButton = new RadioButton(container.getContext());
            radioButton.setText(c);

            container.addView(radioButton);
        }
    }
}
