package com.stpl.app.contract.ui;

import org.jboss.logging.Logger;

import com.stpl.ifs.ui.errorhandling.ErrorDisplay;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;

/**
 * The Class ErrorfulFieldGroup.
 */
public class ErrorfulFieldGroup extends FieldGroup {

    /**
     * The error display.
     */
    private ErrorDisplay errorDisplay;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ErrorfulFieldGroup.class);

    /**
     * The Constructor for ErrorfulFieldGroup.
     *
     * @param item the item
     */
    public ErrorfulFieldGroup(final Item item) {
        super(item);
    }

    /**
     * Sets the error display.
     *
     * @param errorDisplay the error display
     */
    public void setErrorDisplay(final ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay;
    }

    /**
     * Commits all changes done to the bound fields.
     *
     * @throws CommitException the commit exception
     */
    @Override
    public void commit() throws CommitException {
        try {
            super.commit();
            if (errorDisplay != null) {
                errorDisplay.clearError();
            }

        } catch (CommitException e) {
            LOGGER.error(e);
            if (errorDisplay != null) {
                if(e.getCause().getMessage()!=null)
                {
                errorDisplay.setError(e.getCause().getMessage());
                }
            }
            throw e;
        }
    }

    /**
     * Gets the error display.
     *
     * @return the error display
     */
    public ErrorDisplay getErrorDisplay() {
        return errorDisplay;
    }
}