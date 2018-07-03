package com.stpl.app.utils.errorhandling;

/**
 * Contains abstract methods to configure error message.
 *
 * @author
 */
public interface ErrorDisplay {

    /**
     * Configures the error message.
     *
     * @param error
     */
    void setError(String error);

    /**
     * Clears the component's error message and makes it invisible.
     */
    void clearError();
}
