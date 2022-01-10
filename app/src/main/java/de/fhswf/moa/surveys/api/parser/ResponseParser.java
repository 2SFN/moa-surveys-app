package de.fhswf.moa.surveys.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONObject;

/**
 * An interface that describes a simple response-parser that deserializes the given JSON-formatted
 * data to the corresponding object type.
 *
 * @param <T> Resulting object type.
 */
public interface ResponseParser <T> {

    /**
     * Parse the JSON-data into an instance of the target class.
     *
     * @param data Source data.
     * @return Target object instance.
     * @throws ParsingException In case there's an error while parsing the data.
     */
    @NonNull
    T parse(@NonNull JSONObject data) throws ParsingException;

}
