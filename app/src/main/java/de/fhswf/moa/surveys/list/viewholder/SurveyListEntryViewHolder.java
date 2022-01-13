package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.SurveyListItem;

public class SurveyListEntryViewHolder extends BaseViewHolder<SurveyListItem>
        implements View.OnClickListener {

    private SurveyListItem currentSurvey;
    private TextView title;
    private TextView description;

    public SurveyListEntryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
        itemView.setOnClickListener(this);

    }

    @Override
    public void bind(SurveyListItem item) {
        this.currentSurvey = item;

        title.setText(item.getSurvey().getTitle());
        description.setText(item.getSurvey().getDescription());
    }

    @Override
    public void onClick(View v) {
        if (currentSurvey.getOnSurveyListener() != null)
            currentSurvey.getOnSurveyListener().onSurveyClick(currentSurvey);
    }
}
