package com.stpl.app.utils.errorhandling;

import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.ErrorMessage;
import org.jboss.logging.Logger;

import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

// TODO: Auto-generated Javadoc
/**
 * Class contains the over ridded methods to display the error message.
 * @author 
 */
public class ErrorLabel extends Label implements ErrorDisplay {
  
 /** The Constant LOGGER. */
 private static final Logger LOGGER = Logger.getLogger(ErrorLabel.class);
    /**
     * Configures the visibility and style of error message. 
     */
    public ErrorLabel() {
    	super("",ContentMode.HTML);
        try{
        setVisible(false);
        setStyleName("myerror");
        } catch (Exception e) {
	LOGGER.error(e);
	}
    }
   
    /**
     * Configures the error message.
     *
     * @param error the error
     */
    public void setError(final String error) {
        try{
        setValue(error);
        setComponentError(new UserError(error, AbstractErrorMessage.ContentMode.HTML, ErrorMessage.ErrorLevel.ERROR));
        setVisible(true);
        } catch(Exception e){
        LOGGER.error(e);
        }
    }

    /**
     * Clears the component's error message and makes it invisible.
     */
    public void clearError() {
        try{
        setValue(null);
        setComponentError(null);
        setVisible(false);
         } catch(Exception e){
        LOGGER.error(e);
        }
    }
}