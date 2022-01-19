package de.fhswf.moa.surveys.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.moa.surveys.model.RatingQuestion;

/**
 * Wrapper Class für RatingQuestion
 * @author Joey F.M. Esteves
 */
public class RatingQuestionListItem implements ListItem, QuestionResultItem{
    public static final int TYPE = 6;

    private RatingQuestion question;
    private Float userInput;

    public RatingQuestionListItem(RatingQuestion question) {
        this.question = question;
    }

    public RatingQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public Float getUserInput() {
        return userInput;
    }

    public void setUserInput(Float userInput) {
        this.userInput = userInput;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        if (userInput != null){
            return new JSONObject()
                    .put("type",question.getType().getValue())
                    .put("id",question.getId())
                    .put("response", userInput);
        }
        return null;
    }
}
