package de.fhswf.moa.surveys.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.moa.surveys.model.Survey;

/**
 * Parser für eine <b>Liste</b> von Surveys.
 * <p>
 * Diese Klasse 'entpackt' lediglich die einzelnen Objekte aus einer Liste und verarbeitet diese
 * dann mit einem {@link SurveyParser}.
 *
 * @see SurveyParser Weitere Details zum Umgang mit den Surveys.
 */
public class SurveyListParser implements ResponseParser<List<Survey>> {

    @NonNull
    @Override
    public List<Survey> parse(@NonNull JSONObject data) throws ParsingException {
        try {
            JSONArray array = data.getJSONArray("surveys");

            ArrayList<Survey> surveys = new ArrayList<>();
            SurveyParser parser = new SurveyParser();

            for (int i = 0; i < array.length(); i++) {
                surveys.add(parser.parse(array.getJSONObject(i)));
            }

            return surveys;
        } catch (JSONException e) {
            // Dieser Fall sollte nur auftreten, wenn das Feld für die Liste von Surveys nicht
            // existiert oder falsche Inhalte hat. Die ursprüngliche Exception wird in einer
            // ParsingException gekapselt.
            throw new ParsingException("Reading list of surveys failed.", e);
        }
    }

}
