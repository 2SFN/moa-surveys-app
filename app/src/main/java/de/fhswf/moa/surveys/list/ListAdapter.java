package de.fhswf.moa.surveys.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.list.item.SurveyListItem;
import de.fhswf.moa.surveys.list.item.question.EndQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.InfoQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.InputQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.RatingQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.SingleQuestionListItem;
import de.fhswf.moa.surveys.list.item.result.InputQuestionResultItem;
import de.fhswf.moa.surveys.list.item.result.RatingQuestionResultItem;
import de.fhswf.moa.surveys.list.item.result.SelectQuestionResultItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;
import de.fhswf.moa.surveys.list.viewholder.SurveyListEntryViewHolder;
import de.fhswf.moa.surveys.list.viewholder.question.EndQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.question.InfoQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.question.InputQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.question.MultiQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.question.RatingQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.question.SingleQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.result.InputQuestionResultViewHolder;
import de.fhswf.moa.surveys.list.viewholder.result.RatingQuestionResultViewHolder;
import de.fhswf.moa.surveys.list.viewholder.result.SelectQuestionResultViewHolder;

public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final ArrayList<ListItem> items;

    public ListAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            //1
            case SurveyListItem.TYPE:
                return new SurveyListEntryViewHolder(
                        inflater.inflate(R.layout.survey_item, parent, false));

            //2
            case InfoQuestionListItem.TYPE:
                return new InfoQuestionViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            //3
            case InputQuestionListItem.TYPE:
                return new InputQuestionViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            //4
            case SingleQuestionListItem.TYPE:
                return new SingleQuestionViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            //5
            case MultiQuestionListItem.TYPE:
                return new MultiQuestionViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            //6
            case RatingQuestionListItem.TYPE:
                return new RatingQuestionViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            //7
            case EndQuestionListItem.TYPE:
                return new EndQuestionViewHolder(
                        inflater.inflate(R.layout.survey_end, parent, false));

            case SelectQuestionResultItem.ITEM_TYPE:
                return new SelectQuestionResultViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            case RatingQuestionResultItem.ITEM_TYPE:
                return new RatingQuestionResultViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            case InputQuestionResultItem.ITEM_TYPE:
                return new InputQuestionResultViewHolder(
                        inflater.inflate(R.layout.question_item, parent, false));

            default:
                throw new RuntimeException("Unsupported item-type: " + viewType);
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

    /**
     * Entfernt alle items aus der Liste und benachrichtigt den Adapter.
     */
    public synchronized void clear() {
        int count = items.size();
        items.clear();
        notifyItemRangeRemoved(0, count);
    }

    public ArrayList<ListItem> getItems() {
        return items;
    }
}
