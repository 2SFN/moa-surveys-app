package de.fhswf.moa.surveys.list.viewholder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.InputQuestionListItem;
import de.fhswf.moa.surveys.util.DimensionsUtil;

public class InputQuestionViewHolder extends BaseViewHolder<InputQuestionListItem> {

    private TextView title;
    private TextView description;
    private LinearLayout container;

    public InputQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
        this.container = itemView.findViewById(R.id.container);
    }
    @Override
    public void bind(InputQuestionListItem item) {
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Sicherheitsmassnahme
        container.removeAllViews();

        // Eingabe-Text-Feld
        TextInputEditText textInputEditText = new TextInputEditText(container.getContext());

        // Styles
        textInputEditText.setBackgroundResource(R.drawable.white_bg);
        int editTextPadding = (int) DimensionsUtil.dpToPx(textInputEditText.getResources(), 12);
        textInputEditText.setPadding(editTextPadding, editTextPadding, editTextPadding, editTextPadding);

        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                item.setUserInput(s.toString());
            }
        });

        container.addView(textInputEditText);
    }
}
