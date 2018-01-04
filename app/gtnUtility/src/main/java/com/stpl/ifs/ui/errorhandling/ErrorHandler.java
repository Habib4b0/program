package com.stpl.ifs.ui.errorhandling;


import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.v7.ui.Label;
import org.jboss.logging.Logger;

/**
 * Binder Error Handler
 * @author shrihariharan
 */
public class ErrorHandler extends DefaultErrorHandler{

	
	private static final long serialVersionUID = 7750147698117722961L;
	private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());
	AbstractLayout layout;
	
	public ErrorHandler(AbstractLayout layout) {
		this.layout=layout;
	}
	
        /**
         * Handling the error
         * @param event 
         */
	@Override
    public void error(com.vaadin.server.ErrorEvent event) {
        // Find the final cause
        String cause = "<b>Ooops!!  There is a problem. Try again. If it repeats Contact your System admin.</b><br/>";
        for (Throwable t = event.getThrowable(); t != null;
             t = t.getCause())
            if (t.getCause() == null) // We're at final cause
            	LOGGER.error(t.getClass().getName()+t.getMessage());
       
        // Display the error message in a custom fashion
        layout.addComponent(new Label(cause, ContentMode.HTML));
           
        // Do the default error handling (optional)
        doDefault(event);
    } 
}
