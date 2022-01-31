package de.fhswf.moa.surveys.list.viewholder.result;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Locale;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.result.SelectQuestionResultItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;

/**
 * View-Holder für die Ergebnisse von Auswahl-Fragen.
 */
public class SelectQuestionResultViewHolder extends BaseViewHolder<SelectQuestionResultItem> {
    public static final String TAG = SelectQuestionResultViewHolder.class.getSimpleName();

    private final LinearLayout container;
    private final TextView title;
    private final TextView description;

    public SelectQuestionResultViewHolder(@NonNull View itemView) {
        super(itemView);

        this.container = itemView.findViewById(R.id.container);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
    }

    @Override
    public void bind(SelectQuestionResultItem item) {
        // Container leeren
        container.removeAllViews();

        // Allgemeine Felder
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Container mit Ergebnissen befüllen
        item.getQuestion().getResults().forEach((k, v) -> {
            container.addView(generateResultView(
                    k, v, item.getQuestion().getRespondents()));
        });

        // Gesamte Anzahl von Antworten
        TextView respondents = (TextView) LayoutInflater.from(container.getContext()).inflate(
                R.layout.respondents_item, container, false);
        respondents.setText(String.format(Locale.getDefault(), container.getContext().getString(
                R.string.num_respondents_caption), item.getQuestion().getRespondents()));
        container.addView(respondents);
    }

    private View generateResultView(String caption, int value, int max) {
        // View aus Layout
        View item = LayoutInflater.from(container.getContext()).inflate(
                R.layout.result_item, container, false);

        // Prozent berechnen
        Log.d(TAG, "generateResultView: Value " + value + ", Max: " + max);
        int percent = (int) ((value / ((float) max))*100);

        // Werte setzen
        ((TextView) item.findViewById(R.id.caption)).setText(caption);
        ((TextView) item.findViewById(R.id.value)).setText(
                String.format(Locale.getDefault(), "%d %%", percent));
        ((ProgressBar) item.findViewById(R.id.progress)).setProgress(percent);

        return item;
    }
}
