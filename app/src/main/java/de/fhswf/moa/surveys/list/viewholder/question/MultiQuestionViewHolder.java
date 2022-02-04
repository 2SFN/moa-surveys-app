package de.fhswf.moa.surveys.list.viewholder.question;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;

public class MultiQuestionViewHolder extends ContainerCardBaseViewHolder<MultiQuestionListItem> {

    private ArrayList<String> userinput;

    public MultiQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(MultiQuestionListItem item){
        super.bind(item);

        userinput = new ArrayList<>();

        /*
         Erstellt RadioButtons ohne RadioGroup, damit mehrere ausgewählt werden
         können
        */
        for(String c : item.getQuestion().getOptions()) {
            CheckBox checkBox = new CheckBox(getContext());
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

            addContentView(checkBox);
        }

    }
}
