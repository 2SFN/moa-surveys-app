package de.fhswf.moa.surveys.list.viewholder.question;

import android.graphics.Color;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.SingleQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;
import de.fhswf.moa.surveys.model.SingleSelectQuestion;

/**
 * ViewHolder-Implementierung für {@link SingleQuestionListItem}.
 * <p>
 * Erzeugt eine {@link RadioGroup} mit einem {@link RadioButton} für jede Option
 * (s. {@link SingleSelectQuestion#getOptions()}).
 *
 * @see SingleQuestionListItem#setUserInput(String) Festhalten der Nutzer-Eingaben.
 */
public class SingleQuestionViewHolder extends ContainerCardBaseViewHolder<SingleQuestionListItem> {

    public SingleQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(SingleQuestionListItem item) {
        super.bind(item);

        // RadioGroup für Single Selection
        RadioGroup radioGroup = new RadioGroup(itemView.getContext());

        CompoundButton.OnCheckedChangeListener checkedChangeListener = (button, checked) -> {
            item.setUserInput(button.getText().toString());
        };

        // Radio-Buttons für jede Option erzeugen
        for (String c : item.getQuestion().getOptions()) {
            RadioButton radioButton = new RadioButton(new ContextThemeWrapper(
                    getContext(), R.style.WhiteCheckedButtons));
            radioButton.setText(c);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setOnCheckedChangeListener(checkedChangeListener);

            // Auswahl setzen, falls schon vorhanden
            if (c.equals(item.getUserInput())) {
                radioButton.setChecked(true);
            }

            radioGroup.addView(radioButton);
        }

        addContentView(radioGroup);
    }
}
