package com.stpl.app.ui.errorhandling;

/**
 * Contains abstract methods to configure error message.
 * @author 
 */
public interface ErrorDisplay {

    /**
     * Configures the error message.
     * @param error 
     */
    void setError(String error);

    /**
     * Clears the component's  error message and makes it invisible.
     */
    void clearError();
}
