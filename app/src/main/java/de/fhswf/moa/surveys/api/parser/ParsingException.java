package de.fhswf.moa.surveys.api.parser;

/**
 * Exception that can be used when parsing an object's details failed.
 *
 * For instance, it can be used when a required field is not provided.
 */
public class ParsingException extends Exception {

    public ParsingException() {
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException(String formattedMessage, Object... args) {
        super(String.format(formattedMessage, args));
    }

    public ParsingException(String formattedMessage, Throwable cause, Object... args) {
        super(String.format(formattedMessage, args), cause);
    }
}
