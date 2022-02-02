package de.fhswf.moa.surveys.list.item.result;

import de.fhswf.moa.surveys.list.item.question.InputQuestionListItem;
import de.fhswf.moa.surveys.model.InputQuestion;

public class InputQuestionResultItem extends InputQuestionListItem {
    public static final int ITEM_TYPE = 13;

    /**
     * Index des Ergebnisses, das gerade angezeigt wird (durch Ergebnisse blättern).
     */
    private int resultPosition = 0;

    public InputQuestionResultItem(InputQuestion question) {
        super(question);
    }

    public int getResultPosition() {
        return resultPosition;
    }

    public void setResultPosition(int resultPosition) {
        this.resultPosition = resultPosition;
    }

    public void increaseResultPosition() {
        this.resultPosition++;
    }

    public void decreaseResultPosition() {
        this.resultPosition--;
    }

    @Override
    public int getType() {
        return ITEM_TYPE;
    }
}
