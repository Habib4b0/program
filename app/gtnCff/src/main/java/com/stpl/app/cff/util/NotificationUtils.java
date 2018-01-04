/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author mohamed.hameed
 */
public abstract class NotificationUtils {
    /**
     * Used to show Confirmation Message Notification in all market type classes.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the confirmation message
     */
    public void getConfirmationMessage(final String confirmationHeader,
            final String confirmationMessage) {
        MessageBox.showPlain(Icon.QUESTION, confirmationHeader,
                confirmationMessage, new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            yesMethod();
                        } else {
                            noMethod();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Used to show Error Notification in all market type classes.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the error notification
     */
    public static void getErrorNotification(final String confirmationHeader,
            final String confirmationMessage) {
        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, confirmationHeader,
                confirmationMessage, new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
        msgBox.getButton(ButtonId.OK).setCaption("OK");
    }

    /**
     * Used to show Warning Notification in all market type classes.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the warning notification
     */
    public static void getWarningNotification(final String confirmationHeader,
            final String confirmationMessage) {
        MessageBox.showPlain(Icon.WARN, confirmationHeader,
                confirmationMessage, new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
    }

    /**
     * Used to show Alert Notification in all market type classes.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the alert notification
     */
    public static void getAlertNotification(final String confirmationHeader,
            final String confirmationMessage) {
        MessageBox.showPlain(Icon.INFO, confirmationHeader,
                confirmationMessage, new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
    }

    /**
     * Used to show get Confirmation Alert in all market type classes.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the confirmation alert
     */
    public void getConfirmationAlert(final String confirmationHeader,
            final String confirmationMessage) {
        MessageBox.showPlain(Icon.QUESTION, confirmationHeader,
                confirmationMessage, new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equalsIgnoreCase("OK")) {
                            yesMethod();
                        } else {
                            noMethod();
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
    }

    /**
     * Used for no option in confirmation alert.
     */
    public abstract void noMethod();

    /**
     * Used for yes option in confirmation alert.
     */
    public abstract void yesMethod();
}
