package com.stpl.app.contract.ui;

import org.jboss.logging.Logger;

import com.vaadin.server.UserError;
import com.vaadin.ui.Label;

/**
 * The Class ErrorLabel.
 */
public class ErrorLabel extends Label implements ErrorDisplay {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ErrorLabel.class);

    /**
     * The Constructor.
     */
    public ErrorLabel() {
        super();
        LOGGER.debug("entering ErrorLabel");
        setVisible(false);
        setStyleName("myerror");
        LOGGER.debug("End of ErrorLabel");
    }

    /**
     * Method used to Sets the error.
     *
     * @param error the new error
     */
    public void setError(final String error) {
        LOGGER.debug("entering setError method");
        setValue(error);
        setComponentError(new UserError(error));
        setVisible(true);
        LOGGER.debug("End of setError method");
    }

    /**
     * toclear Error
     */
    public void clearError() {
        LOGGER.debug("entering clearError method");
        setValue(null);
        setComponentError(null);
        setVisible(false);
        LOGGER.debug("End of clearError method");
    }
}