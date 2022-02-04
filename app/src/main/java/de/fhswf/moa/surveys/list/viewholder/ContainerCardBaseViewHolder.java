package de.fhswf.moa.surveys.list.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.QuestionListItem;
import de.fhswf.moa.surveys.util.ColorGenerator;
import de.fhswf.moa.surveys.util.DimensionsUtil;

public abstract class ContainerCardBaseViewHolder<T extends QuestionListItem>
        extends BaseViewHolder<T> {

    private final CardView card;
    private final LinearLayout container;
    private final TextView title;
    private final TextView description;

    /**
     * Erstelle eine neue ViewHolder-Instanz.
     *
     * Es wird das standard Question-Layout {@link R.layout#question_item} vorausgesetzt.
     *
     * @param itemView Layout.
     */
    public ContainerCardBaseViewHolder(@NonNull View itemView) {
        super(itemView);

        this.card = itemView.findViewById(R.id.cardview);
        this.container = itemView.findViewById(R.id.container);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
    }

    @CallSuper
    @Override
    public void bind(T item) {
        // Hintergrundfarbe der Karte setzten
        card.setBackgroundColor(ColorGenerator.MATERIAL.getColor(item.getQuestion()));

        // TODO: Dimens resource
        card.setRadius(DimensionsUtil.dpToPx(getContext().getResources(), 16.0f));

        // Standard-Views und Container leeren
        container.removeAllViews();
        setTitle(item.getQuestion().getTitle());
        setDescription(item.getQuestion().getDescription());
    }

    /**
     * Setze den Titel der Card.
     *
     * @param title Titel als String.
     */
    protected void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * Setze die Kurzbeschreibung der Card.
     *
     * @param description Beschreibung als String.
     */
    protected void setDescription(String description) {
        this.description.setText(description);
    }

    /**
     * Füge dem Container ein View hinzu.
     *
     * @param view Target View.
     */
    protected void addContentView(@NonNull View view) {
        this.container.addView(view);
    }

    @NonNull
    protected Context getContext() {
        return container.getContext();
    }

    protected LinearLayout getContainer() {
        return container;
    }
}
