package de.fhswf.moa.surveys.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.list.item.SingleQuestionListItem;
import de.fhswf.moa.surveys.list.item.SurveyListItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;
import de.fhswf.moa.surveys.list.viewholder.SingleQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.SurveyListEntryViewHolder;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<ListItem> items;

    public ListAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            //
            case SurveyListItem.TYPE:
                return new SurveyListEntryViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.survey_item, parent, false));
            case SingleQuestionListItem.TYPE:
                return new SingleQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.question_item, parent, false));
            default:
                // Ist dat kein Problem ??
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public synchronized void add(@NonNull ListItem item) {
        int index = items.size();
        items.add(item);
        notifyItemInserted(index);
    }
}
