package com.stpl.app.utils.errorhandling;

import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.UserError;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.Label;

/**
 * Class contains the over ridded methods to display the error message.
 *
 * @author
 */
public class ErrorLabel extends Label implements ErrorDisplay {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorLabel.class);

    /**
     * Configures the visibility and style of error message.
     */
    public ErrorLabel() {
        super("", ContentMode.HTML);
        try {
            setVisible(false);
            setStyleName("myerror");
        } catch (Exception e) {
            LOGGER.error("Error in ErrorLabel :" + e);
        }
    }

    /**
     * Configures the error message.
     *
     * @param error the error
     */
    @Override
    public void setError(final String error) {
        try {
            setValue(error);
            setComponentError(new UserError(error, AbstractErrorMessage.ContentMode.HTML, ErrorMessage.ErrorLevel.ERROR));
            setVisible(true);
        } catch (Exception e) {
            LOGGER.error("Error " + e);
        }
    }

    /**
     * Clears the component's error message and makes it invisible.
     */
    @Override
    public void clearError() {
        try {
            setValue(null);
            setComponentError(null);
            setVisible(false);
        } catch (Exception e) {
            LOGGER.error("Error :" + e);
        }
    }
}
