package com.stpl.app.ui.errorhandling;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;

/**
 * The Class ErrorfulFieldGroup.
 */
public class ErrorfulFieldGroup extends FieldGroup {

	/** The error display. */
	private ErrorDisplay errorDisplay;

	/**
	 * The Constructor.
	 *
	 * @param item
	 *            the item
	 */
	public ErrorfulFieldGroup(final Item item) {
		super(item);
	}

	/**
	 * Sets the error display.
	 *
	 * @param errorDisplay
	 *            the error display
	 */
	public void setErrorDisplay(final ErrorDisplay errorDisplay) {
		this.errorDisplay = errorDisplay;
	}

	/**
	 * (non-Javadoc).
	 *
	 * @throws CommitException
	 *             the commit exception
	 * @see com.vaadin.data.fieldgroup.FieldGroup#commit()
	 */
	@Override
	public void commit() throws CommitException {
		try {
			super.commit();
			if (errorDisplay != null) {
				errorDisplay.clearError();
			}
		} catch (CommitException e) {
			if (errorDisplay != null) {
				errorDisplay.setError(e.getCause().getMessage());
			}
			throw e;
		}
	}

	/**
	 * Gets the error display.
	 *
	 * @return the error display
	 */
	public ErrorDisplay getErrorDisplay() {
		return errorDisplay;
	}
}