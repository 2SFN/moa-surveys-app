package de.fhswf.moa.surveys.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import de.fhswf.moa.surveys.model.Question;
import de.fhswf.moa.surveys.model.Survey;

/**
 * Parser für Umfragen.
 * <p>
 * (Die Liste von {@link Question} Objekten ist nicht zwingend erforderlich und darf fehlen;
 * es werden dann nur die Details, die unmittelbar zur Umfrage gehören interpretiert.)
 */
public class SurveyParser implements ResponseParser<Survey> {

    @NonNull
    @Override
    public Survey parse(@NonNull JSONObject data) throws ParsingException {
        return null; // TODO
    }
}
