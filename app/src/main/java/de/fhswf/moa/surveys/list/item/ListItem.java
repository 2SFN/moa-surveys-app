package de.fhswf.moa.surveys.list.item;

/**
 * Interface für Listitem
 * @description getType() wird benutzt um die verschiedenen Fragekartentypen zu unterscheiden.
 *            1 Survey
 *            2 InfoQuestion
 *            3 InputQuestion
 *            4 SingleSelectQuestion
 *            5 MultiSelectQuestion
 *            6 RatingQuestion
 *            7 EndQuestion
 */
public interface ListItem {
    /**
     *
     * @return Integer welcher einen Typen darstellt.
     */
    int getType();

}
