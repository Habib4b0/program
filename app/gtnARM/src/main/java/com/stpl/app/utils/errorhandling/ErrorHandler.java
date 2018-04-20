package com.stpl.app.utils.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.v7.ui.Label;

/**
 * Class contains method to handle the error from UOI component.
 *
 * @author
 */
public class ErrorHandler extends DefaultErrorHandler {

    /**
     * The layout.
     */
    private AbstractLayout layout;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class.getName());

    /**
     * The cause.
     */
    private static final String CAUSE = "<b>Ooops!!  There is a problem. Try again. If it repeats Contact your System admin.</b><br/>";

    /**
     * Parameterized Constructor to initialize the layout field.
     *
     * @param layout - AbstractLayout
     */
    public ErrorHandler(final AbstractLayout layout) {
        super();
        try {
            this.layout = layout;
        } catch (Exception e) {
            LOGGER.error("Error in ErrorHandler :" + e);
        }
    }

    /**
     * Method over ridded to display the error message.
     *
     * @param event the event
     */
    @Override
    public void error(final ErrorEvent event) {
        try {

            for (Throwable t = event.getThrowable(); t != null;
                    t = t.getCause()) {
                if (t.getCause() == null) {
                    LOGGER.error(t.getClass().getName() + t);
                }
            }

            layout.addComponent(new Label(CAUSE, ContentMode.HTML));
            doDefault(event);
        } catch (Exception e) {
            LOGGER.error("Error :" + e);
        }
    }

    /**
     * Gets the cause.
     *
     * @return the cause
     */
    public String getCause() {
        return CAUSE;
    }

    /**
     * Gets the layout.
     *
     * @return the layout
     */
    public AbstractLayout getLayout() {
        return layout;
    }

    /**
     * Sets the layout.
     *
     * @param layout the new layout
     */
    public void setLayout(final AbstractLayout layout) {
        this.layout = layout;
    }

}
