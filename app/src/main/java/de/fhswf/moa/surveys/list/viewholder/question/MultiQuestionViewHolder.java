package de.fhswf.moa.surveys.list.viewholder.question;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;

import java.util.concurrent.atomic.AtomicInteger;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;
import de.fhswf.moa.surveys.model.MultiSelectQuestion;

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

            updateCheckBoxStyle(item.getQuestion().getMaxSelectedOptions());
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

        updateCheckBoxStyle(item.getQuestion().getMaxSelectedOptions());

    }

    /**
     * Überprüft die Anzahl der ausgewählten CheckBoxes und passt die Views in dem
     * Container entsprechend an.
     *
     * Ist die maximal zulässige Anzahl an gleichzeitig ausgewählten Optionen erreicht
     * (s. {@link MultiSelectQuestion#getMaxSelectedOptions()}), dann werden die restlichen
     * Optionen deaktiviert; es können also nur noch bereits ausgewählte wieder abgewählt werden.
     *
     * @param maxSelected Anzahl der gleichzeitig auswählbaren Optionen.
     */
    private void updateCheckBoxStyle(int maxSelected) {
        // Zähle ausgewählte CheckBoxes
        AtomicInteger selected = new AtomicInteger();
        checkBoxForEach(e -> {
            if(e.isChecked())
                selected.getAndIncrement();
        });

        // Vergleiche mit maximal erlaubter Anzahl
        if ((maxSelected == MultiSelectQuestion.NO_SELECTION_LIMIT) || (selected.get() < maxSelected)) {
            // Die maximale Anzahl von ausgewählten CheckBoxes ist nicht erreicht
            // >> Alle CheckBoxes aktivieren
            checkBoxForEach(e -> e.setEnabled(true));
        } else {
            // Maximale Anzahl erreicht >> Deaktiviere _nicht_ ausgewählte CheckBoxes
            checkBoxForEach(e -> {
                if(!e.isChecked())
                    e.setEnabled(false);
            });
        }
    }

    private void checkBoxForEach(CompoundButtonAction action) {
        for (int i = 0; i < getContainer().getChildCount(); i++) {
            View child = getContainer().getChildAt(i);

            if (child instanceof CompoundButton)
                action.process((CompoundButton) child);
        }
    }

    private interface CompoundButtonAction {
        void process(@NonNull CompoundButton e);
    }
}
