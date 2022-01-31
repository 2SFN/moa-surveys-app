package de.fhswf.moa.surveys.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.question.EndQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.InfoQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.InputQuestionListItem;
import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.list.item.question.MultiQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.RatingQuestionListItem;
import de.fhswf.moa.surveys.list.item.question.SingleQuestionListItem;
import de.fhswf.moa.surveys.list.item.SurveyListItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;
import de.fhswf.moa.surveys.list.viewholder.EndQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.InfoQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.InputQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.MultiQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.RatingQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.SingleQuestionViewHolder;
import de.fhswf.moa.surveys.list.viewholder.SurveyListEntryViewHolder;

public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<ListItem> items;


    public ListAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {

            //1
            case SurveyListItem.TYPE:
                return new SurveyListEntryViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.survey_item, parent, false));

            //2
            case InfoQuestionListItem.TYPE:
                return new InfoQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.question_item, parent, false));

            //3
            case InputQuestionListItem.TYPE:
                return new InputQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.question_item, parent, false));

            //4
            case SingleQuestionListItem.TYPE:
                return new SingleQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.question_item, parent, false));

            //5
            case MultiQuestionListItem.TYPE:
                return new MultiQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.question_item, parent, false));

            //6
            case RatingQuestionListItem.TYPE:
                return new RatingQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.question_item, parent, false));

            //7
            case EndQuestionListItem.TYPE:
                return new EndQuestionViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.survey_end, parent, false));

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

    public ArrayList<ListItem> getItems() {
        return items;
    }
}
