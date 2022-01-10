package de.fhswf.moa.surveys.api;

import androidx.annotation.MainThread;

/**
 * A simple interface that is used for positive service callbacks with a result-object.
 *
 * Note that the caller of the interface method should always make sure to post the result back
 * to the main thread.
 *
 * @param <T> Type of the result-object.
 */
public interface OnSuccessListener <T> {

    /**
     * Called when the request was successfully executed.
     *
     * @param result The result-object of the request.
     */
    @MainThread
    void onSuccess(T result);
}
