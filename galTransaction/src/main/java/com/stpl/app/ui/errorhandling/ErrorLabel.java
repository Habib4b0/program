package com.stpl.app.ui.errorhandling;

import org.jboss.logging.Logger;

import com.vaadin.server.UserError;
import com.vaadin.ui.Label;

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
			LOGGER.info("Entering setError ");
			setValue(error);
			setComponentError(new UserError(error));
			setVisible(true);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("Ends setError ");
	}

	/**
	 * (non-Javadoc).
	 *
	 * @see com.stpl.app.ui.errorhandling.ErrorDisplay#clearError()
	 */
	public void clearError() {
		try {
			LOGGER.info("Entering clearError ");
			setValue(null);
			setComponentError(null);
			setVisible(false);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("Ends clearError ");
	}
}