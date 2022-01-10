package de.fhswf.moa.surveys.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import de.fhswf.moa.surveys.model.Survey;

/**
 * Parser for surveys.
 *
 * Note that the set of questions is optional, so this can also be used to parse only the
 * details of surveys.
 */
public class SurveyParser implements ResponseParser<Survey> {

    @NonNull
    @Override
    public Survey parse(@NonNull JSONObject data) throws ParsingException {
        return null;
    }
}
