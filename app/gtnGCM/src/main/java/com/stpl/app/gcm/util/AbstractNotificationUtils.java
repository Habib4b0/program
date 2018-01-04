/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author santanukumar
 */
public abstract class AbstractNotificationUtils {

    /**
     * Gets the confirmation message.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     */
    public void getConfirmationMessage(final String confirmationHeader, final String confirmationMessage) {
        MessageBox.showPlain(Icon.QUESTION, confirmationHeader, confirmationMessage, new MessageBoxListener() {
            /**
             * The method is triggered when a button of the message box is
             * pressed.
             *
             */
            @SuppressWarnings("PMD")
            @Override
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
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the error notification
     */
    public static void getErrorNotification(final String confirmationHeader, final String confirmationMessage) {
        MessageBox.showPlain(Icon.ERROR, confirmationHeader, confirmationMessage, new MessageBoxListener() {
            /**
             * The method is triggered when a button of the message box is
             * pressed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                // Do Nothing
            }
        }, ButtonId.OK);
    }

    /**
     * Gets the warning notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     */
    public static void getWarningNotification(final String confirmationHeader, final String confirmationMessage) {
        MessageBox.showPlain(Icon.WARN, confirmationHeader, confirmationMessage, new MessageBoxListener() {
            /**
             * The method is triggered when a button of the message box is
             * pressed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                // Do Nothing
            }
        }, ButtonId.OK);
    }

    /**
     * Gets the alert notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     */
    public static void getAlertNotification(final String confirmationHeader, final String confirmationMessage) {
        MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage, new MessageBoxListener() {
            /**
             * The method is triggered when a button of the message box is
             * pressed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                // Do Nothing
            }
        }, ButtonId.OK);
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
