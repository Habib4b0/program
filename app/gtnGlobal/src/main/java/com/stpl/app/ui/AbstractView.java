package com.stpl.app.ui;

import org.jboss.logging.Logger;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;

/**
 * Customized VerticalLayout Component.
 *
 * @author 
 */
public abstract class AbstractView extends StplVerticalLayout {

     /** The Constant LOGGER. */
     private static final Logger LOGGER = Logger.getLogger(AbstractView.class);
    
    /**
     * Constructor.
     */
    public AbstractView() {
    	super();
        try{
        setSpacing(true);
        setComponentError(new UserError(""));
        } catch(Exception e){
        LOGGER.error(e);
        }
    }

    /**
     * method over ridded while implementing View.
     *
     * @param event ViewChangeEvent
     */
     @Override
    public void enter(final ViewChangeEvent event) {
		LOGGER.debug("Entering into abstractview class");
    }

}
