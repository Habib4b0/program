/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import org.jboss.logging.Logger;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author KarthikeyanS
 */

/**
 * The Class AbstractNotificationUtils.
 */
public abstract class AbstractNotificationUtils {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(AbstractNotificationUtils.class);

	/**
	 * Gets the confirmation message.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 * @return the confirmation message
	 */
	public MessageBox getConfirmationMessage(final String confirmationHeader, final String confirmationMessage) {
		LOGGER.debug("Entering getConfirmationMessage method ");
		try {
			final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage, new MessageBoxListener() {

				/**
				 * The method is triggered when a button of the message box is
				 * pressed .
				 *
				 * @param buttonId
				 *            The buttonId of the pressed button.
				 */
				@SuppressWarnings("PMD")
				public void buttonClicked(final ButtonId buttonId) {
					if (buttonId.name().equals("YES")) {
						yesMethod();
					} else {
						noMethod();
					}
				}
			}, ButtonId.YES, ButtonId.NO);
			msg.getButton(ButtonId.YES).focus();
			LOGGER.debug("End of getConfirmationMessage method");
			return msg;

		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

	/**
	 * Gets the error notification.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 * @return the error notification
	 */
	public static MessageBox getErrorNotification(final String confirmationHeader, final String confirmationMessage) {
		LOGGER.debug("Entering getErrorNotification method ");
		try {
			final MessageBox msg = MessageBox.showPlain(Icon.ERROR, confirmationHeader, confirmationMessage, new MessageBoxListener() {
				/**
				 * The method is triggered when a button of the message box is
				 * pressed .
				 *
				 * @param buttonId
				 *            The buttonId of the pressed button.
				 */
				@SuppressWarnings("PMD")
				public void buttonClicked(final ButtonId buttonId) {
				}
			}, ButtonId.OK);
			msg.getButton(ButtonId.OK).focus();
			LOGGER.debug("End of getErrorNotifications method");
			return msg;

		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}

	}

	/**
	 * Gets the warning notification.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 * @return the warning notification
	 */
	public static MessageBox getWarningNotification(final String confirmationHeader, final String confirmationMessage) {
		LOGGER.debug("Entering getWarningNotification method ");
		try {
			final MessageBox msg = MessageBox.showPlain(Icon.WARN, confirmationHeader, confirmationMessage, new MessageBoxListener() {

				/**
				 * The method is triggered when a button of the message box is
				 * pressed .
				 *
				 * @param buttonId
				 *            The buttonId of the pressed button.
				 */
				@SuppressWarnings("PMD")
				public void buttonClicked(final ButtonId buttonId) {
				}
			}, ButtonId.OK);
			msg.getButton(ButtonId.OK).focus();
			LOGGER.debug("End of getWarningNotification method");
			return msg;

		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

	/**
	 * Gets the info notification.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 * @return the info notification
	 */
	public static MessageBox getInfoNotification(final String confirmationHeader, final String confirmationMessage) {
		LOGGER.debug("Entering getInfoNotification method ");
		try {
			final MessageBox msg = MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage, new MessageBoxListener() {
				/**
				 * The method is triggered when a button of the message box is
				 * pressed .
				 *
				 * @param buttonId
				 *            The buttonId of the pressed button.
				 */
				@SuppressWarnings("PMD")
				public void buttonClicked(final ButtonId buttonId) {
				}
			}, ButtonId.OK);
			msg.getButton(ButtonId.OK).focus();
			LOGGER.debug("End of getInfoNotification method");
			return msg;

		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

	/**
	 * Gets the ok cancel message.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 * @return the ok cancel message
	 */
	public MessageBox getOkCancelMessage(final String confirmationHeader, final String confirmationMessage) {
		LOGGER.debug("Entering getOkCancelMessage method ");
		try {
			final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage, new MessageBoxListener() {
				/**
				 * The method is triggered when a button of the message box is
				 * pressed .
				 *
				 * @param buttonId
				 *            The buttonId of the pressed button.
				 */
				@SuppressWarnings("PMD")
				public void buttonClicked(final ButtonId buttonId) {
					if (buttonId.name().equals("OK")) {
						yesMethod();
					} else {
						noMethod();
					}
				}
			}, ButtonId.OK, ButtonId.CANCEL);
			msg.getButton(ButtonId.OK).focus();
			LOGGER.debug("End of getOkCancelMessage method");
			return msg;

		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

	/**
	 * No method.
	 */
	public abstract void noMethod();

	/**
	 * Yes method.
	 */
	public abstract void yesMethod();
}
