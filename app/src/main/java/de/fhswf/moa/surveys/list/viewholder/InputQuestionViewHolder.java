package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.InputQuestionListItem;

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

        // TODO Sinnvolles Textfeld hinzufügen
    }
}
