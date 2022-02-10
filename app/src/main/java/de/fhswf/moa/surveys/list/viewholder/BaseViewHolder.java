package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.fhswf.moa.surveys.list.item.ListItem;

/**
 * Grundlegende {@link androidx.recyclerview.widget.RecyclerView.ViewHolder}-Implementierung
 * für den {@link de.fhswf.moa.surveys.list.ListAdapter}.
 * <p>
 * Der ViewHolder ist typisiert für alle {@link ListItem}.
 * <p>
 * Hier gibt es keine Anforderungen an das Layout in <pre>itemView</pre>.
 *
 * @param <T> Item-Typ, welcher mit diesem ViewHolder präsentiert werden kann.
 */
public abstract class BaseViewHolder<T extends ListItem> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Wird aufgerufen, wenn die ViewHolder-Instanz ein neues Item darstellen soll.
     *
     * @param item Darzustellendes Item.
     */
    public abstract void bind(T item);

}
