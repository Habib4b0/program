package com.stpl.app.cff.ui.errorhandling;

import com.vaadin.server.UserError;
import com.vaadin.v7.ui.Label;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorLabel.
 */
public class ErrorLabel extends Label implements ErrorDisplay {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(ErrorLabel.class);

	/**
	 * The Constructor.
	 */
	public ErrorLabel() {
		super();
		setVisible(false);
		setStyleName("myerror");
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param error
	 *            the new error
	 * @see com.stpl.app.ui.errorhandling.ErrorDisplay#setError(java.lang.String)
	 */
	public void setError(final String error) {
		try {
			LOGGER.debug("Entering setError ");
			setValue(error);
			setComponentError(new UserError(error));
			setVisible(true);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Ends setError ");
	}

	/**
	 * (non-Javadoc).
	 *
	 * @see com.stpl.app.ui.errorhandling.ErrorDisplay#clearError()
	 */
	public void clearError() {
		try {
			LOGGER.debug("Entering clearError ");
			setValue(null);
			setComponentError(null);
			setVisible(false);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Ends clearError ");
	}
}