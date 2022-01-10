package de.fhswf.moa.surveys.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.fhswf.moa.surveys.model.InfoQuestion;
import de.fhswf.moa.surveys.model.InputQuestion;
import de.fhswf.moa.surveys.model.MultiSelectQuestion;
import de.fhswf.moa.surveys.model.Question;
import de.fhswf.moa.surveys.model.QuestionType;
import de.fhswf.moa.surveys.model.RatingQuestion;
import de.fhswf.moa.surveys.model.SingleSelectQuestion;

/**
 * Parser for single questions.
 */
public class QuestionParser implements ResponseParser<Question> {

    @NonNull
    @Override
    public Question parse(@NonNull JSONObject data) throws ParsingException {
        try {
            Question question;

            // Read question-type and parse type-specific fields
            QuestionType questionType = QuestionType.parse(data.getString("type"));

            switch (questionType) {
                case INFO:
                    question = new InfoQuestion();
                    // Info questions don't have any additional fields
                    break;
                case INPUT:
                    question =  fillInputQuestion(data);
                    break;
                case SINGLE_SELECT:
                    question = fillSingleSelectQuestion(data);
                    break;
                case MULTI_SELECT:
                    question = fillMultiSelectQuestion(data);
                    break;
                case RATING:
                    question = fillRatingQuestion(data);
                    break;
                default:
                    throw new ParsingException("Question type not supported: %s",
                            questionType.getValue());
            }

            // Parse generic question details
            // JSON.get(...) >> Mandatory field; throws exception when missing
            // JSON.opt(...) >> Optional field with default/fallback value
            question.setId(data.getString("id"));
            question.setTitle(data.optString("title", ""));
            question.setDescription(data.optString("description", ""));
            question.setRespondents(data.optInt("responses", 0));

            return question;
        } catch (Exception e) {
            throw new ParsingException("Parsing Question failed.", e);
        }
    }

    @NonNull
    private InputQuestion fillInputQuestion(@NonNull JSONObject data) throws JSONException {
        InputQuestion question = new InputQuestion();

        // Maximum input length field
        question.setMaxLength(data.optInt("maxLength", InputQuestion.DEFAULT_MAX_LENGTH));

        // Optional list of results
        if (data.has("results")) {
            JSONArray array = data.getJSONArray("results");
            ArrayList<String> results = new ArrayList<>(array.length());

            for (int i = 0; i < array.length(); i++) {
                results.add(array.getString(i));
            }

            question.setResults(results);
        }

        return question;
    }

    @NonNull
    private SingleSelectQuestion fillSingleSelectQuestion(@NonNull JSONObject data)
            throws JSONException {
        SingleSelectQuestion question = new SingleSelectQuestion();

        // Read survey options
        JSONArray array = data.getJSONArray("options");
        ArrayList<String> options = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            if(!options.contains(array.getString(i))) {
                options.add(array.getString(i));
            }
        }

        question.setOptions(options);

        // Optional Map of results
        if(data.has("results")) {
            array = data.getJSONArray( "results");
            Map<String, Integer> results = new HashMap<>(array.length());

            for (int i = 0; i < array.length(); i++) {
                JSONObject c = array.getJSONObject(i);
                results.put(c.getString("key"), c.getInt("value"));
            }

            question.setResults(results);
        }

        return question;
    }

    @NonNull
    private MultiSelectQuestion fillMultiSelectQuestion(@NonNull JSONObject data)
            throws JSONException {
        MultiSelectQuestion question = new MultiSelectQuestion();

        // Read survey options
        JSONArray array = data.getJSONArray("options");
        ArrayList<String> options = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            if(!options.contains(array.getString(i))) {
                options.add(array.getString(i));
            }
        }

        question.setOptions(options);

        // Optional Map of results
        if(data.has("results")) {
            array = data.getJSONArray( "results");
            Map<String, Integer> results = new HashMap<>(array.length());

            for (int i = 0; i < array.length(); i++) {
                JSONObject c = array.getJSONObject(i);
                results.put(c.getString("key"), c.getInt("value"));
            }

            question.setResults(results);
        }

        return question;
    }

    @NonNull
    private RatingQuestion fillRatingQuestion(@NonNull JSONObject data)
            throws JSONException {
        RatingQuestion question = new RatingQuestion();

        // Optional Map of results
        if(data.has("results")) {
            JSONArray array = data.getJSONArray( "results");
            Map<Integer, Integer> results = new HashMap<>(array.length());

            for (int i = 0; i < array.length(); i++) {
                JSONObject c = array.getJSONObject(i);
                results.put(c.getInt("key"), c.getInt("value"));
            }

            question.setResults(results);
        }

        return question;
    }

}
