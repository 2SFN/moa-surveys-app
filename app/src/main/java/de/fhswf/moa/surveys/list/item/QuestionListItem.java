package de.fhswf.moa.surveys.list.item;

import de.fhswf.moa.surveys.model.Question;

/**
 * Spezifikation des {@link ListItem} Interfaces, das zusätzlich noch eine Methode zur Verfügung
 * stellt, mit welcher auf die zugehörige Question zugegriffen werden kann.
 * <p>
 * Dies wird z.B. genutzt, um mit dem {@link de.fhswf.moa.surveys.util.ColorGenerator} eine Farbe
 * auf Basis der tatsächlichen Frage (und nicht des Wrapper List-Items) ermitteln zu können.
 *
 * @see de.fhswf.moa.surveys.list.viewholder.BaseViewHolder verwendet.
 */
public interface QuestionListItem extends ListItem {

    /**
     * Gebe die Frage der jeweiligen Karte zurück.
     *
     * @return {@link Question} der Karte.
     */
    Question getQuestion();

}
