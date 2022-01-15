package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.InfoQuestionListItem;

public class InfoQuestionViewHolder  extends BaseViewHolder<InfoQuestionListItem> {

    private TextView title;
    private TextView description;

    public InfoQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.description= itemView.findViewById(R.id.description);

    }

    @Override
    public void bind(InfoQuestionListItem item) {
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());
    }
}