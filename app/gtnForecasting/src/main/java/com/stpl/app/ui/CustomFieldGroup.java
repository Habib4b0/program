package com.stpl.app.ui;

import com.stpl.app.gtnforecasting.logic.PPAProjectionLogic;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import org.jboss.logging.Logger;



/**
 * Binding Component and Variables in DTO class
 * @author shrihariharan
 */
public class CustomFieldGroup extends FieldGroup {
    private static final long serialVersionUID = -6293510593661094366L;
    ErrorLabel errorDisplay;
   
    /**
     * binds the item
     * @param item 
     */
    public CustomFieldGroup(Item item) {
        super(item);
    }

    public void setErrorDisplay(ErrorLabel errorDisplay) {
        this.errorDisplay = errorDisplay; 
    }
   
    public ErrorLabel getErrorDisplay() {
		return errorDisplay;
	}
    
    /**
     * Checks whether the component return type and the variable binded type are same or not
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