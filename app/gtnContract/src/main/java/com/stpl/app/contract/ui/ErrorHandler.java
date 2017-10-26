package com.stpl.app.contract.ui;

import org.jboss.logging.Logger;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Label;


/**
 * The Class ErrorHandler.
 */
public class ErrorHandler extends DefaultErrorHandler {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);
    /**
     * The layout.
     */
    private final AbstractLayout layout;

    /**
     * The get Layout.
     *
     * @return the layout
     */
    public AbstractLayout getLayout() {
        return layout;
    }

    /**
     * The Constructor.
     *
     * @param layout the layout
     */
    public ErrorHandler(final AbstractLayout layout) {
        super();
        this.layout = layout;
    }

    /**
     * Method used to Invoked when an error occurs.
     *
     * @param event the event
     */
    @Override
    public void error(final com.vaadin.server.ErrorEvent event) {

        for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
            if (t.getCause() == null) { // We're at final cause
                LOGGER.error(t.getClass().getName() + t.getMessage());
            }
        }
        layout.addComponent(new Label("<b>Ooops!!  There is a problem. Try again. If it repeats Contact your System admin.</b><br/>", ContentMode.HTML));

        doDefault(event);
    }
}