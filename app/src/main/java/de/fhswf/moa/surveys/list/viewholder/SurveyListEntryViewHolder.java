package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.SurveyListItem;
import de.fhswf.moa.surveys.util.ColorGenerator;

/**
 * ViewHolder-Implementierung für {@link SurveyListItem}.
 *
 * Eine einfache, anklickbare Karte mit Titel und Beschreibung der Umfrage.
 */
public class SurveyListEntryViewHolder extends BaseViewHolder<SurveyListItem>
        implements View.OnClickListener {

    private SurveyListItem currentSurvey;

    private final CardView card;
    private final TextView title;
    private final TextView description;

    public SurveyListEntryViewHolder(@NonNull View itemView) {
        super(itemView);

        this.card = itemView.findViewById(R.id.cardview);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);

        itemView.setOnClickListener(this);
    }

    @Override
    public void bind(SurveyListItem item) {
        this.currentSurvey = item;

        // Card Hintergrund
        card.setCardBackgroundColor(ColorGenerator.MATERIAL.getColor(item.getSurvey()));

        title.setText(item.getSurvey().getTitle());
        description.setText(item.getSurvey().getDescription());
    }

    @Override
    public void onClick(View v) {
        if (currentSurvey.getOnSurveyListener() != null)
            currentSurvey.getOnSurveyListener().onSurveyClick(currentSurvey);
    }
}
