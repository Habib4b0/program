package com.stpl.app.adminconsole.util;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationUtils.
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
	 */
	public void getConfirmationMessage(final String confirmationHeader, final String confirmationMessage) {
		MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage, new MessageBoxListener() {
			/**
			 * The method is triggered when a button of the message box is
			 * pressed.
			 *
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
	public static void getErrorNotification(final String confirmationHeader, final String confirmationMessage) {
		MessageBox.showPlain(Icon.ERROR, confirmationHeader, confirmationMessage, new MessageBoxListener() {
			/**
			 * The method is triggered when a button of the message box is
			 * pressed.
			 */
			@SuppressWarnings("PMD")
			public void buttonClicked(final ButtonId buttonId) {
                            return;
			}
		}, ButtonId.OK);
	}

	/**
	 * Gets the warning notification.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 */
	public static void getWarningNotification(final String confirmationHeader, final String confirmationMessage) {
		MessageBox.showPlain(Icon.WARN, confirmationHeader, confirmationMessage, new MessageBoxListener() {
			/**
			 * The method is triggered when a button of the message box is
			 * pressed.
			 */
			@SuppressWarnings("PMD")
			public void buttonClicked(final ButtonId buttonId) {
                                return;
			}
		}, ButtonId.OK);
	}

	/**
	 * Gets the alert notification.
	 *
	 * @param confirmationHeader
	 *            the confirmation header
	 * @param confirmationMessage
	 *            the confirmation message
	 */
	public static void getAlertNotification(final String confirmationHeader, final String confirmationMessage) {
		MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage, new MessageBoxListener() {
			/**
			 * The method is triggered when a button of the message box is
			 * pressed.
			 */
			@SuppressWarnings("PMD")
			public void buttonClicked(final ButtonId buttonId) {
                            return;
			}
		}, ButtonId.OK);
	}
       /* Gets the info notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the info notification
     */
    public static MessageBox getInfoNotification(final String confirmationHeader,final  String confirmationMessage) {
        try {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    return;
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
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
