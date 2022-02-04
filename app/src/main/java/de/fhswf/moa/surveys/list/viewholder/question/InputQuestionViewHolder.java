package de.fhswf.moa.surveys.list.viewholder.question;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.InputQuestionListItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;
import de.fhswf.moa.surveys.util.DimensionsUtil;

public class InputQuestionViewHolder extends ContainerCardBaseViewHolder<InputQuestionListItem> {

    public InputQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    @Override
    public void bind(InputQuestionListItem item) {
        super.bind(item);

        // Eingabe-Text-Feld
        TextInputEditText textInputEditText = new TextInputEditText(getContext());

        // Styles
        textInputEditText.setBackgroundResource(R.drawable.white_bg);
        int editTextPadding = (int) DimensionsUtil.dpToPx(textInputEditText.getResources(), 12);
        textInputEditText.setPadding(editTextPadding, editTextPadding, editTextPadding, editTextPadding);
        textInputEditText.setHint("Antwort eingeben");

        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setUserInput(s.toString());
            }
        });

        addContentView(textInputEditText);
    }
}
