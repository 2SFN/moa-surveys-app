package de.fhswf.moa.surveys.list.item.question;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.fhswf.moa.surveys.list.item.ListItem;

/**
 * Wrapper Class für EndQuestion
 *
 * @author Joey F.M. Esteves
 */
public class EndQuestionListItem implements ListItem {
    public static final int TYPE = 7;

    private @Nullable OnEndClickListener onEndListener;
    private @Nullable OnResultsClickListener onResultsClickListener;

    //Konstruktor
    public EndQuestionListItem() {
    }

    /**
     * Getter Listener
     * @return onEndListener
     */
    @Nullable
    public OnEndClickListener getOnEndListener() {
        return onEndListener;
    }

    /**
     * Setter Listener
     * @param onEndListener
     */
    public EndQuestionListItem setOnEndListener(
            @Nullable OnEndClickListener onEndListener) {
        this.onEndListener = onEndListener;
        return this;
    }

    @Nullable
    public OnResultsClickListener getOnResultsClickListener() {
        return onResultsClickListener;
    }

    public EndQuestionListItem setOnResultsClickListener(
            @Nullable OnResultsClickListener onResultsClickListener) {
        this.onResultsClickListener = onResultsClickListener;
        return this;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    /**
     * Interface Listener für Umfrage-Beenden-Button auf EndQuestion Item.
     */
    public interface OnEndClickListener {
        void onEndButtonClick(@NonNull EndQuestionListItem item );
    }

    /**
     * Callback für den Ergebnisse-Anzeigen-Button.
     */
    public interface OnResultsClickListener {
        void onResultsClick();
    }
}
