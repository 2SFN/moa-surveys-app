package de.fhswf.moa.surveys.api;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/**
 * A simple interface that is used for negative service callbacks.
 *
 * Note that the caller of the interface method should always make sure to post the execution
 * back on the main thread.
 */
public interface OnFailureListener {

    /**
     * Called when an error occurred while processing the service request.
     *
     * @param e Error cause with additional data.
     */
    @MainThread
    void onFailure(@NonNull Throwable e);
}
