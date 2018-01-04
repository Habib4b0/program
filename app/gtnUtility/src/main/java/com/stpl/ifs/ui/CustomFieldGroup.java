package com.stpl.ifs.ui;

import com.stpl.app.ui.errorhandling.ErrorDisplay;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.fieldgroup.FieldGroup;

/**
 * Binding Component and Variables in DTO class
 * @author shrihariharan
 */
public class CustomFieldGroup extends FieldGroup {
    private static final long serialVersionUID = -6293510593661094366L;
    ErrorDisplay errorDisplay;
   
    /**
     * binds the item
     * @param item 
     */
    public CustomFieldGroup(Item item) {
        super(item);
    }

    public void setErrorDisplay(ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay; 
    }
   
    public ErrorDisplay getErrorDisplay() {
		return errorDisplay;
	}
    
    /**
     * Checks whether the component return type and the variable binded type are same or not.  
     * @throws com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException 
     */
    @Override
    public void commit() throws CommitException {
        try {
            super.commit();
            if (errorDisplay != null)
                errorDisplay.clearError();
        } catch (CommitException e) {
            if (errorDisplay != null)
                errorDisplay.setError(e.getCause().getMessage());
            throw e;
        }
    }

}