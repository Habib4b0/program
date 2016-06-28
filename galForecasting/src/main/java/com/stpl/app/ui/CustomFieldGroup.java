package com.stpl.app.ui;

import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Binding Component and Variables in DTO class
 * @author shrihariharan
 */
public class CustomFieldGroup extends FieldGroup {
    private static final long serialVersionUID = -6293510593661094366L;
     static Log log = LogFactory.getLog(CustomFieldGroup.class);
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
     * Checks whether the component return type and the variable binded type are same or not.  
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException 
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