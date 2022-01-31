package de.fhswf.moa.surveys.list.item.result;

import de.fhswf.moa.surveys.list.item.question.SingleQuestionListItem;
import de.fhswf.moa.surveys.model.SingleSelectQuestion;

/**
 * List-Item Klasse für {@link SingleSelectQuestion} (und auch
 * {@link de.fhswf.moa.surveys.model.MultiSelectQuestion}) items.
 *
 * Eine Unterscheidung ist nicht erforderlich, weil die Präsentation der Ergebnisse für
 * {@link SingleSelectQuestion} und die Spezialisierung gleich ist.
 */
public class SelectQuestionResultItem extends SingleQuestionListItem {
    public static final int ITEM_TYPE = 11;

    public SelectQuestionResultItem(SingleSelectQuestion question) {
        super(question);
    }

    @Override
    public int getType() {
        return ITEM_TYPE;
    }
}
