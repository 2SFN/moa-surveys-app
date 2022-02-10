package de.fhswf.moa.surveys.list.viewholder.result;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Locale;

import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.result.RatingQuestionResultItem;
import de.fhswf.moa.surveys.list.viewholder.ContainerCardBaseViewHolder;

/**
 * ViewHolder-Implementierung für {@link RatingQuestionResultItem}.
 * <p>
 * Die Ergebnisse werden als einfache Liste erzeugt und mit einer {@link ProgressBar}
 * anteilsmäßig präsentiert.
 */
public class RatingQuestionResultViewHolder
        extends ContainerCardBaseViewHolder<RatingQuestionResultItem> {

    public RatingQuestionResultViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RatingQuestionResultItem item) {
        super.bind(item);

        // Container mit Ergebnissen befüllen
        item.getQuestion().getResults().forEach((k, v) -> {
            addContentView(generateResultView(
                    k, v, item.getQuestion().getRespondents()));
        });

        // Gesamte Anzahl von Antworten
        TextView respondents = (TextView) LayoutInflater.from(getContext()).inflate(
                R.layout.respondents_item, getContainer(), false);
        respondents.setText(String.format(Locale.getDefault(), getContext().getString(
                R.string.num_respondents_caption), item.getQuestion().getRespondents()));
        addContentView(respondents);
    }

    private View generateResultView(Integer caption, Integer value, int max) {
        // View aus Layout
        View item = LayoutInflater.from(getContext()).inflate(
                R.layout.result_item, getContainer(), false);

        // Prozent berechnen
        int percent = (int) ((value / ((float) max)) * 100);

        // Werte setzen
        String text = String.format(Locale.getDefault(), getContext()
                .getString(R.string.rating_stars_label), caption);
        ((TextView) item.findViewById(R.id.caption)).setText(text);
        ((TextView) item.findViewById(R.id.value)).setText(
                String.format(Locale.getDefault(), "%d %%", percent));
        ((ProgressBar) item.findViewById(R.id.progress)).setProgress(percent);

        return item;
    }
}
