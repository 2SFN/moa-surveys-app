package de.fhswf.moa.surveys.list.viewholder.question;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;

public class MultiQuestionViewHolder extends ContainerCardBaseViewHolder<MultiQuestionListItem> {

    public MultiQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(MultiQuestionListItem item) {
        super.bind(item);

        // Checked-Change Listener für die Checkboxes
        CompoundButton.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> {
            if (isChecked) {
                item.getUserInput().add(buttonView.getText().toString());
            } else {
                item.getUserInput().remove(buttonView.getText().toString());
            }
        };

        /*
         Erstellt RadioButtons ohne RadioGroup, damit mehrere ausgewählt werden
         können
        */
        for (String c : item.getQuestion().getOptions()) {
            // Neues CheckBox-View
            CheckBox checkBox = new CheckBox(new ContextThemeWrapper(
                    getContext(), R.style.WhiteCheckedButtons));
            checkBox.setText(c);
            checkBox.setTextColor(Color.WHITE);

            // Prüfen, ob der Nutzer diese CheckBox vorher schon ausgewählt hatte
            if (item.getUserInput().contains(c)) {
                checkBox.setChecked(true);
            }

            checkBox.setOnCheckedChangeListener(checkedChangeListener);

            addContentView(checkBox);
        }

    }
}
