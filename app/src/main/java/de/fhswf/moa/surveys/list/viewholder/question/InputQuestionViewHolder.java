package de.fhswf.moa.surveys.list.viewholder.question;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

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

        // Counter Text-View
        TextView counter = new TextView(getContext());
        counter.setTextColor(Color.WHITE);
        counter.setGravity(Gravity.END);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginTop = (int) DimensionsUtil.dpToPx(getContext().getResources(), 4);
        params.setMargins(0, marginTop, 0, 0);
        counter.setLayoutParams(params);

        // Eingabe-Text-Feld
        TextInputEditText textInputEditText = new TextInputEditText(getContext());

        // Sollte der Nutzer bereits Text eingegeben haben, wird dieser hier
        // "wiederhergestellt"
        if (item.getUserInput() != null) {
            textInputEditText.setText(item.getUserInput());
        }

        // Style für EditText
        textInputEditText.setBackgroundResource(R.drawable.white_bg);

        int editTextPadding = (int) DimensionsUtil.dpToPx(textInputEditText.getResources(), 12);
        textInputEditText.setPadding(
                editTextPadding, editTextPadding, editTextPadding, editTextPadding);
        textInputEditText.setHint("Antwort eingeben");

        // ChangedListener
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCounter(counter, s.length(), item.getQuestion().getMaxLength());
            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setUserInput(s.toString());
            }
        });

        addContentView(textInputEditText);
        addContentView(counter);
    }

    private void updateCounter(TextView counter, int current, int max) {
        // Counter aktualisieren
        String label = String.format(Locale.getDefault(), "%d / %d", current, max);
        counter.setText(label);

        // Überschritten? Style anpassen
        if (current > max) {
            counter.setTypeface(null, Typeface.BOLD);

            if(counter.getTag() == null) {
                // Warn-Icon
                counter.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0, 0, R.drawable.ic_warning_tiny, 0);
                int padding = (int) DimensionsUtil.dpToPx(getContext().getResources(), 6);
                counter.setCompoundDrawablePadding(padding);
                counter.setTag(this);
            }
        } else {
            counter.setTypeface(null, Typeface.NORMAL);

            if(counter.getTag() != null) {
                counter.setCompoundDrawables(null, null, null, null);
                counter.setTag(null);
            }
        }
    }
}
