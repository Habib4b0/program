package com.stpl.app.ui.errorhandling;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorfulFieldGroup.
 */
public class ErrorfulFieldGroup extends FieldGroup {

    private ErrorDisplay errorDisplay;

    public ErrorfulFieldGroup(final Item item) {
        super(item);
    }

    public void setErrorDisplay(final ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay;
    }

    /**
     * (non-Javadoc).
     *
     * @throws CommitException the commit exception
     * @see com.vaadin.data.fieldgroup.FieldGroup#commit()
     */
    @Override
    public void commit() throws CommitException {
        try {
            super.commit();
            if (errorDisplay != null) {
                errorDisplay.clearError();
            }
        } catch (CommitException e) {
            if (errorDisplay != null) {
                errorDisplay.setError(e.getCause().getMessage());
            }
            throw e;
        }
    }

    public ErrorDisplay getErrorDisplay() {
        return errorDisplay;
    }
}
