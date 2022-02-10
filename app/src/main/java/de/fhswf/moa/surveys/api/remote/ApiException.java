package de.fhswf.moa.surveys.api.remote;

/**
 * Exception-Klasse für API-Fehler.
 */
public class ApiException extends Exception {

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
