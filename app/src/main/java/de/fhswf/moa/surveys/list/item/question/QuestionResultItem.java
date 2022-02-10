package de.fhswf.moa.surveys.list.item.question;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Interface, über welches die Eingaben des Benutzers (Survey Results) eines
 * {@link de.fhswf.moa.surveys.list.item.ListItem} abgefragt werden können.
 *
 * @see de.fhswf.moa.surveys.QuestionActivity#onEndButtonClick(EndQuestionListItem)
 */
public interface QuestionResultItem {

    /**
     * Generiert ein Antwort-Objekt nach API-Spezifikation.
     *
     * @return JSON-Objekt mit allen Antwort-Daten, oder <pre>null</pre>, wenn für die entsprechende
     * Frage keine Eingaben vorliegen.
     * @throws JSONException Geworfen, wenn das Zusammenbauen des JSON-Objekts fehlschlägt.
     */
    @Nullable
    JSONObject getResult() throws JSONException;

}
