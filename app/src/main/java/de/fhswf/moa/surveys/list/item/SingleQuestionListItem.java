package de.fhswf.moa.surveys.list.item;


import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.moa.surveys.model.SingleSelectQuestion;

/**
 * Wrapper Class für SingleSelectQuestion
 * @author  Joey F.M. Esteves , Konzept SFN
 */
public class SingleQuestionListItem implements ListItem, QuestionResultItem {
    public static final int TYPE = 4;

    private SingleSelectQuestion question;
    //TODO Datentyp besprechen, könnte RadiobtnID sein(int), könnte Radiobutton Name sein.
    private String userInput;

    public SingleQuestionListItem(SingleSelectQuestion question) {
        this.question = question;
    }

    /**
     * Getter für SingleSelectQuestion
     * @return question
     */
    public SingleSelectQuestion getQuestion() {
        return question;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        /*
        Ziel-Output:
        {
            "type": "SINGLE_SELECTION"
            "id": "Q-56ac01fc-e43e-4755-ab50-68de6f6f8564",
            respone:"Answer"
         */
        if(userInput != null){
            return new JSONObject()
                    .put("type",question.getType().getValue())
                    .put("id",question.getId())
                    .put("response",userInput);
        }
        return null;
    }
}
