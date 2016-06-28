package com.stpl.app.ui.errorhandling;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;

// TODO: Auto-generated Javadoc
/**
 * Class contains methods to Display/Clear error message.
 * @author
 */
public class ErrorfulFieldGroup extends FieldGroup {

    /** The error display. */
    private ErrorDisplay errorDisplay;

    /**
     *  Parameterized constructor to create a field binder that uses the data source.
     *
     * @param item the item
     */
    public ErrorfulFieldGroup(final Item item) {
        super(item);
    }

    /**
     * Sets the ErrorLabel that is to be displayed in errordisplay field.
     *
     * @param errorDisplay the error display
     */
    public void setErrorDisplay(final ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay;
    }

    /**
     * Logic for display/clear error message.
     *
     * @throws CommitException the commit exception
     */    
    public void commit() throws CommitException {
        try {
            super.commit();
            if (errorDisplay != null) {
                errorDisplay.clearError();
            }
        } catch (CommitException e) {
            if (errorDisplay != null) {
                if(e.getCause().getMessage()!=null)
                {
                errorDisplay.setError(e.getCause().getMessage());
                }
                else{
                errorDisplay.setError("Please enter valid value.");
                }
            }
            throw e;
        }
    }

    /**
     * Returns the error label that is to displayed.
     * @return ErrorDisplay.
     */
    public ErrorDisplay getErrorDisplay() {
        return errorDisplay;
    }

}
