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

/**
 * Spezifische Implementierung von {@link BaseViewHolder} für Umfrage-Karten mit Container.
 * <p>
 * Die Klasse verwaltet Typen, welche {@link QuestionListItem} implementieren, um auf die
 * generelle {@link de.fhswf.moa.surveys.model.Question} zugreifen zu können.
 * Hierdurch können allgemeine Felder wie Titel und Beschreibung einheitlich gesetzt werden.
 * <p>
 * Stellt außerdem Helfer-Methoden zum Füllen des Containers (s. {@link this#addContentView(View)})
 * und Beschaffen des Contexts ({@link this#getContext()}) zur Verfügung.
 *
 * @param <T> Typ, der dargestellt werden kann.
 * @see R.layout#question_item Zugrundeliegendes Layout.
 */
public abstract class ContainerCardBaseViewHolder<T extends QuestionListItem>
        extends BaseViewHolder<T> {

    private final CardView card;
    private final LinearLayout container;
    private final TextView title;
    private final TextView description;

    /**
     * Erstelle eine neue ViewHolder-Instanz.
     * <p>
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

    /**
     * Die Standard-Implementierung der bind-Methode generiert eine dynamische Hintergrund-Farbe
     * für die Karte auf Basis der angezeigten {@link de.fhswf.moa.surveys.model.Question} und
     * leert den Container.
     * <p>
     * Sie sollte deshalb im Regelfall immer als super aufgerufen werden.
     *
     * @param item Darzustellendes Item.
     */
    @CallSuper
    @Override
    public void bind(T item) {
        // Hintergrundfarbe der Karte setzten
        card.setCardBackgroundColor(ColorGenerator.MATERIAL.getColor(item.getQuestion()));

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
