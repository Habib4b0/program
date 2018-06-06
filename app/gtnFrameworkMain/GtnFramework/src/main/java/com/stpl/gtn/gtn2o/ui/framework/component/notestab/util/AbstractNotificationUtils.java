package com.stpl.gtn.gtn2o.ui.framework.component.notestab.util;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public abstract class AbstractNotificationUtils {

	public MessageBox getConfirmationMessage(final String confirmationHeader, final String confirmationMessage) {

		final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage,
				new MessageBoxListener() {
					@Override
					/**
					 * The method is triggered when a button of the message box
					 * is pressed .
					 *
					 * @param buttonId
					 *            The buttonId of the pressed button.
					 */
					public void buttonClicked(final ButtonId buttonId) {
						if (buttonId.name().equals("YES")) {
							yesMethod();
						} else {
							noMethod();
						}
					}
				}, ButtonId.YES, ButtonId.NO);
		msg.getButton(ButtonId.YES).focus();
		return msg;

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

		final MessageBox msg = MessageBox.showPlain(Icon.ERROR, confirmationHeader, confirmationMessage,
				new MessageBoxListener() {
					/**
					 * The method is triggered when a button of the message box
					 * is pressed .
					 */
					@Override
					public void buttonClicked(final ButtonId buttonId) {
						return;

					}
				}, ButtonId.OK);
		msg.getButton(ButtonId.OK).focus();
		return msg;

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

		final MessageBox msg = MessageBox.showPlain(Icon.WARN, confirmationHeader, confirmationMessage,
				new MessageBoxListener() {
					@Override
					/**
					 * The method is triggered when a button of the message box
					 * is pressed .
					 *
					 * @param buttonId
					 *            The buttonId of the pressed button.
					 */
					public void buttonClicked(final ButtonId buttonId) {
						return;
					}
				}, ButtonId.OK);
		msg.getButton(ButtonId.OK).focus();
		return msg;

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
		final MessageBox msg = MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage,
				new MessageBoxListener() {
					/**
					 * The method is triggered when a button of the message box
					 * is pressed .
					 *
					 * @param buttonId
					 *            The buttonId of the pressed button.
					 */
					public void buttonClicked(final ButtonId buttonId) {
						return;
					}
				}, ButtonId.OK);
		msg.getButton(ButtonId.OK).focus();
		return msg;

	}

	public static class Parameter {
		private boolean ok = false;

		public boolean isOk() {
			return ok;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

	}

	/**
	 * Gets the info notification.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 * @param isOk
	 * @return the info notification
	 */
	public static MessageBox getInfoNotification(final String confirmationHeader, final String confirmationMessage,
			final Parameter isOk) {
		try {
			final MessageBox msg = MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage,
					new MessageBoxListener() {
						/**
						 * The method is triggered when a button of the message
						 * box is pressed .
						 *
						 * @param buttonId
						 *            The buttonId of the pressed button.
						 */
						public void buttonClicked(final ButtonId buttonId) {
							isOk.setOk(false);
						}
					}, ButtonId.OK);
			msg.getButton(ButtonId.OK).focus();
			return msg;

		} catch (Exception ex) {
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

		final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage,
				new MessageBoxListener() {
					/**
					 * The method is triggered when a button of the message box
					 * is pressed .
					 *
					 * @param buttonId
					 *            The buttonId of the pressed button.
					 */
					public void buttonClicked(final ButtonId buttonId) {
						if (buttonId.name().equals("OK")) {
							yesMethod();
						} else {
							noMethod();
						}
					}
				}, ButtonId.OK, ButtonId.CANCEL);
		msg.getButton(ButtonId.OK).focus();
		return msg;

	}

	public abstract void noMethod();

	public abstract void yesMethod();
}
