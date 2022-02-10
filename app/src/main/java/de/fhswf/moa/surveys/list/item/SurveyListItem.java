package de.fhswf.moa.surveys.list.item;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.model.Survey;

/**
 * Wrapper Class für einzelne Survey (Übersicht)
 */
public class SurveyListItem implements ListItem {
    public static final int TYPE = 1;

    private final Survey survey;
    private OnSurveyListener onSurveyListener;

    public SurveyListItem(Survey survey) {
        this.survey = survey;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public OnSurveyListener getOnSurveyListener() {
        return onSurveyListener;
    }

    public SurveyListItem setOnSurveyListener(OnSurveyListener onSurveyListener) {
        this.onSurveyListener = onSurveyListener;
        return this;
    }

    /**
     * Callback-Interface für Nutzer-Interaktion.
     */
    public interface OnSurveyListener {

        /**
         * Wird aufgerufen, wenn der Nutzer eine Survey auswählt.
         *
         * @param item Angeklicktes Survey-Item.
         */
        void onSurveyClick(@NonNull SurveyListItem item);
    }
}
