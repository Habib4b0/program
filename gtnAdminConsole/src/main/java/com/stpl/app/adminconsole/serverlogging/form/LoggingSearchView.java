/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.serverlogging.form;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 *
 * @author Karthik.Raja
 */
public class LoggingSearchView extends VerticalLayout implements View {

    /**
     * String name.
     */
    public static final String NAME = ConstantsUtils.EMPTY;

    /**
     * Constructor reference for LoggingSearchIndex.
     */
    private LoggingSearchIndex loggingSearchIndex;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(LoggingSearchIndex.class); 

    /**
     * constructor LoggingSearchView.
     */
    public LoggingSearchView()  {
      
        super();
        LOGGER.debug("Inside LoggingSearchView");
        loggingSearchIndex = new LoggingSearchIndex();
        setSpacing(true);
        addComponent(loggingSearchIndex);
        setStyleName("bootstrap");

    }

    /**
     * enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            loggingSearchIndex = new LoggingSearchIndex();
            addComponent(loggingSearchIndex);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
