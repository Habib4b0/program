package com.stpl.app.ui.errorhandling;

import org.jboss.logging.Logger;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Label;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorHandler.
 */
public class ErrorHandler extends DefaultErrorHandler {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);
	/**
	 * The Constant CAUSE.
	 */
	private final static String CAUSE = "<b>Ooops!!  There is a problem. Try again. If it repeats Contact your System admin.</b><br/>";
	/**
	 * The layout.
	 */
	private final AbstractLayout layout;

	/**
	 * The Constructor.
	 *
	 * @param layout
	 *            the layout
	 */
	public ErrorHandler(final AbstractLayout layout) {
		super();
		this.layout = layout;
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param event
	 *            the event
	 * @see com.vaadin.server.DefaultErrorHandler#error(com.vaadin.server.ErrorEvent)
	 */
	@Override
	public void error(final com.vaadin.server.ErrorEvent event) {
		try {
			LOGGER.info("Entering error ");
			for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
				if (t.getCause() == null) {
					LOGGER.error(t.getClass().getName() + t.getMessage());
				}
			}

			layout.addComponent(new Label(CAUSE, ContentMode.HTML));

			doDefault(event);
			LOGGER.info("Ends error ");
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * @return the layout
	 */
	public AbstractLayout getLayout() {
		return layout;
	}

}
