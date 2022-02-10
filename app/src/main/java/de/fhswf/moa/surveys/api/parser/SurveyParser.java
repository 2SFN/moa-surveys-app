package de.fhswf.moa.surveys.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fhswf.moa.surveys.model.Question;
import de.fhswf.moa.surveys.model.Survey;

/**
 * Parser für eine einzelne Survey.
 * <p>
 * (Die Liste von {@link Question} Objekten ist nicht zwingend erforderlich und darf fehlen;
 * es werden dann nur die Details, die unmittelbar zur Umfrage gehören interpretiert.)
 *
 * @see QuestionParser Questions werden mit einem anderen Parser verarbeitet.
 */
public class SurveyParser implements ResponseParser<Survey> {

    @NonNull
    @Override
    public Survey parse(@NonNull JSONObject data) throws ParsingException {
        Survey survey = new Survey();

        // Allgemeine Daten
        survey.setId(data.optString("id", ""));
        survey.setTitle(data.optString("title", ""));
        survey.setDescription(data.optString("description", ""));

        // Fragen sind optional
        if (data.has("questions")) {
            try {
                JSONArray array = data.getJSONArray("questions");

                ArrayList<Question> questions = new ArrayList<>();
                QuestionParser parser = new QuestionParser();

                for (int i = 0; i < array.length(); i++) {
                    questions.add(parser.parse(array.getJSONObject(i)));
                }

                survey.setQuestions(questions);
            } catch (JSONException e) {
                throw new ParsingException("Unable to read questions.", e);
            }
        }

        return survey;
    }
}
