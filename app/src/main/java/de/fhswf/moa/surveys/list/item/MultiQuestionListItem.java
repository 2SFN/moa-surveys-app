package de.fhswf.moa.surveys.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fhswf.moa.surveys.model.MultiSelectQuestion;

/**
 * Wrapper Class für MultiSelectQuestion
 * @author Joey F.M. Esteves
 */
public class MultiQuestionListItem implements ListItem, QuestionResultItem{
    public static final int TYPE = 5;

    private MultiSelectQuestion question;
    //TODO Typ besprechen
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
            return new JSONObject()
                    .put("type",question.getType().getValue())
                    .put("id",question.getId())
                    .put("responses",userInput);
        }
        return null;
    }
}
