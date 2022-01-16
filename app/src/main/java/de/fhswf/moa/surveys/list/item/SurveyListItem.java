package de.fhswf.moa.surveys.list.item;

import androidx.annotation.NonNull;

import de.fhswf.moa.surveys.model.Survey;

/**
 * Wrapper Class für Survey
 * @author Joey F.M. Esteves , Konzept SFN
 */
public class SurveyListItem implements ListItem {
    public static final int TYPE = 1;

    private Survey survey;
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

    /**
     * Getter Listener
     * @return onSurveyListener
     */
    public OnSurveyListener getOnSurveyListener() {
        return onSurveyListener;
    }

    /**
     * Setter Listener
     * @param onSurveyListener
     * @return onSurveyListener
     */
    public SurveyListItem setOnSurveyListener(OnSurveyListener onSurveyListener) {
        this.onSurveyListener = onSurveyListener;
        return this;
    }

    /**
     * Interface für Listener
     * @author Joey F.M. Esteves
     */
    public interface OnSurveyListener{
        void onSurveyClick(@NonNull SurveyListItem item);
    }
}
