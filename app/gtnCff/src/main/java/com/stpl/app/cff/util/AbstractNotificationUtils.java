/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

/**
 *
 * @author maheshj
 */

import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class NotificationUtils.
 */
public abstract class AbstractNotificationUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNotificationUtils.class);

    /**
     * Gets the confirmation message.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the confirmation message
     */
    public MessageBox getConfirmationMessage(final String confirmationHeader,final  String confirmationMessage) {
        try {
            final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage, new MessageBoxListener() {
                
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                    @Override
                public void buttonClicked(final ButtonId buttonId) {
                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                        yesMethod();
                    } else {
                        noMethod();
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
            msg.getButton(ButtonId.YES).focus();
            return msg;

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Gets the error notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the error notification
     */
    public static MessageBox getErrorNotification(final String confirmationHeader,final  String confirmationMessage) {
        try {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, confirmationHeader, confirmationMessage, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                    @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return msg;

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }

    }

    /**
     * Gets the warning notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the warning notification
     */
    public static MessageBox getWarningNotification(final String confirmationHeader,final  String confirmationMessage) {
        try {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, confirmationHeader, confirmationMessage, new MessageBoxListener() {
              
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                    @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return msg;

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Gets the info notification.
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
                    @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return msg;

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Gets the ok cancel message.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the ok cancel message
     */
    public MessageBox getOkCancelMessage(final String confirmationHeader,final  String confirmationMessage) {
        try {
            final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                    @Override
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

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
