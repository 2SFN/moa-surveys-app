package de.fhswf.moa.surveys.list.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.fhswf.moa.surveys.list.item.ListItem;

public abstract class BaseViewHolder<T extends ListItem> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T item);

}
