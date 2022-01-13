package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.Survey;

public class SurveyListItem implements ListItem {
    public static final int TYPE = 1;

    private Survey survey;

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
}
