package de.fhswf.moa.surveys.list.item;

import android.view.ViewGroup;

/**
 * Interface für List-Items, welche durch den {@link de.fhswf.moa.surveys.list.ListAdapter}
 * verwaltet werden.
 *
 * @see de.fhswf.moa.surveys.list.ListAdapter#onCreateViewHolder(ViewGroup, int) verwendet.
 */
public interface ListItem {

    /**
     * @return Integer welcher einen Typen darstellt.
     */
    int getType();

}
