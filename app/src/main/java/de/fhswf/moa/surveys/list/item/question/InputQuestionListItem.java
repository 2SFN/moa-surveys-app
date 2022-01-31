package de.fhswf.moa.surveys.list.item.question;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.moa.surveys.list.item.ListItem;
import de.fhswf.moa.surveys.model.InputQuestion;

/**
 * Wrapper Class für InputQuestion
 * @author Joey F.M. Esteves
 */
public class InputQuestionListItem implements ListItem, QuestionResultItem {
    public static final int TYPE = 3;

    private InputQuestion question;
    private String userInput;

    public InputQuestionListItem(InputQuestion question) {
        this.question = question;
    }

    /**
     * Getter für InputQuestion
     * @return question
     */
    public InputQuestion getQuestion() {
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
			"type": "INPUT",
		  "id": "Q-56ac01fc-e43e-4755-ab50-68de6f6f8564",
			"response": "consetetur sadipscing elitr nonomy"
		}
         */

        if(userInput != null) {
            return new JSONObject()
                    .put("type", question.getType().getValue())
                    .put("id", question.getId())
                    .put("response", userInput);
        }

        return null;
    }
}
