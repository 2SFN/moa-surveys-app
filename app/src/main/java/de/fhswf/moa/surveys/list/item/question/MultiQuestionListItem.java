package de.fhswf.moa.surveys.list.item.question;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.model.MultiSelectQuestion;

/**
 * Wrapper Class für MultiSelectQuestion
 * @author Joey F.M. Esteves
 */
public class MultiQuestionListItem implements ListItem, QuestionResultItem{
    public static final int TYPE = 5;

    private MultiSelectQuestion question;

    private ArrayList<String> userInput;

    public MultiQuestionListItem(MultiSelectQuestion question) {
        this.question = question;
    }

    /**
     * Getter MultiSelectQuestion
     * @return question
     */
    public MultiSelectQuestion getQuestion() {
        return question;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public ArrayList<String> getUserInput() {
        return userInput;
    }

    public void setUserInput(ArrayList<String> userInput) {
        this.userInput = userInput;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        if(userInput != null){
            JSONArray responses = new JSONArray();
            for(String c : userInput) {
                responses.put(c);
            }

            return new JSONObject()
                    .put("type",question.getType().getValue())
                    .put("id",question.getId())
                    .put("response", responses);
        }
        return null;
    }
}
