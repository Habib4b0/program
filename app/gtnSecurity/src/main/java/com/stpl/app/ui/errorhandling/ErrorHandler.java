package com.stpl.app.ui.errorhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Label;

public class ErrorHandler extends DefaultErrorHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7750147698117722961L;
	private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class.getName());
	private AbstractLayout layout;
	
	public ErrorHandler(AbstractLayout layout) {
		this.layout=layout;
	}
	
	@Override
    public void error(com.vaadin.server.ErrorEvent event) {
        String cause = "<b>Ooops!!  There is a problem. Try again. If it repeats Contact your System admin.</b><br/>";
        for (Throwable t = event.getThrowable(); t != null;
             t = t.getCause())
            if (t.getCause() == null) // We're at final cause
            	LOGGER.error(t.getClass().getName()+t.getMessage());
        layout.addComponent(new Label(cause, ContentMode.HTML));
        doDefault(event);
    } 
}
