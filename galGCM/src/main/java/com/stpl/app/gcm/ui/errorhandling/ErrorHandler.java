package com.stpl.app.gcm.ui.errorhandling;

import org.jboss.logging.Logger;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Label;

// TODO: Auto-generated Javadoc
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
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());

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
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method over ridded to display the error message.
     *
     * @param event the event
     */
    public void error(final ErrorEvent event) {
        try {

            for (Throwable t = event.getThrowable(); t != null;
                    t = t.getCause()) {
                if (t.getCause() == null) {
                    LOGGER.error(t.getClass().getName() + t.getMessage());
                }
            }

            layout.addComponent(new Label(CAUSE, ContentMode.HTML));
            doDefault(event);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
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
