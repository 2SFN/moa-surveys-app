package de.fhswf.moa.surveys.list.viewholder.result;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import de.fhswf.moa.surveys.R;
import de.fhswf.moa.surveys.list.item.result.InputQuestionResultItem;
import de.fhswf.moa.surveys.list.viewholder.BaseViewHolder;

import java.util.Locale;

public class InputQuestionResultViewHolder extends BaseViewHolder<InputQuestionResultItem> {

    private final LinearLayout container;
    private final TextView title;
    private final TextView description;

    private TextView resultText;
    private TextView resultInfo;
    private ImageButton next;
    private ImageButton prev;

    public InputQuestionResultViewHolder(@NonNull View itemView) {
        super(itemView);

        this.container = itemView.findViewById(R.id.container);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
    }

    @Override
    public void bind(InputQuestionResultItem item) {
        // Container leeren
        container.removeAllViews();

        // Allgemeine Felder
        title.setText(item.getQuestion().getTitle());
        description.setText(item.getQuestion().getDescription());

        // Ergebnis-Anzeige vorbereiten
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View resultsView = inflater.inflate(R.layout.input_result, container, false);
        container.addView(resultsView);

        // Views (Content)
        resultText = resultsView.findViewById(R.id.text);
        resultInfo = resultsView.findViewById(R.id.info);
        next = resultsView.findViewById(R.id.next);
        prev = resultsView.findViewById(R.id.previous);

        // Navigation
        next.setOnClickListener(v -> {
            item.increaseResultPosition();
            updateContent(item);
        });

        prev.setOnClickListener(v -> {
            item.decreaseResultPosition();
            updateContent(item);
        });

        updateContent(item);
    }

    /**
     * Ändert die Sichtbarkeit der Navigations-Buttons in Abhängigkeit von
     * der aktuellen Position und der Anzahl der Ergebnisse und setzt den
     * Inhalt entsprechend.
     *
     * @param item Item.
     */
    private void updateContent(InputQuestionResultItem item) {
        if(item.getQuestion().getResults() == null || item.getQuestion().getResults().size() == 0) {
            resultText.setText(R.string.input_results_no_results);
            next.setVisibility(View.INVISIBLE);
            prev.setVisibility(View.INVISIBLE);
        } else {
            // Inhalt setzen
            resultText.setText(item.getQuestion().getResults().get(item.getResultPosition()));
            resultInfo.setText(String.format(Locale.getDefault(),
                    container.getContext().getString(R.string.input_results_info_label),
                    (item.getResultPosition()+1), item.getQuestion().getResults().size()));

            // Button-Sichtbarkeit äbhängig von Position
            if(item.getResultPosition() == 0) {
                next.setVisibility(View.VISIBLE);
                prev.setVisibility(View.INVISIBLE);
            } else if(item.getResultPosition() == (item.getQuestion().getResults().size() - 1)) {
                next.setVisibility(View.INVISIBLE);
                prev.setVisibility(View.VISIBLE);
            } else {
                next.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);
            }
        }
    }
}
