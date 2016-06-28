package com.stpl.app.util;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public abstract class NotificationUtils {

//	public static void getErrorMessage(String message) {
//		Notification notif = new Notification(message,
//				Notification.Type.ERROR_MESSAGE);
//		notif.setPosition(Position.MIDDLE_CENTER);
//		notif.setStyleName("mystyle");
//		notif.show(Page.getCurrent());
//	}

//	public static void getWarningMessage(String message) {
//		Notification notif = new Notification(message,
//				Notification.Type.WARNING_MESSAGE);
//		notif.setPosition(Position.MIDDLE_CENTER);
//		notif.setStyleName("mystyle");
//		notif.show(Page.getCurrent());
//	}

//	public static void getAlertMessage(String message) {
//		Notification notif = new Notification(message,
//				Notification.Type.HUMANIZED_MESSAGE);
//		notif.setPosition(Position.MIDDLE_CENTER);
//		notif.setStyleName("mystyle");
//		notif.show(Page.getCurrent());
//	}

	public void getConfirmationMessage(String confirmationHeader,
			String confirmationMessage) {
		MessageBox.showPlain(Icon.QUESTION, confirmationHeader,
				confirmationMessage, new MessageBoxListener() {
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals("YES")) {
							yesMethod();
						} else {
							noMethod();
						}
					}
				}, ButtonId.YES, ButtonId.NO);
	}

	public static void getErrorNotification(String confirmationHeader,
			String confirmationMessage) {
		MessageBox.showPlain(Icon.ERROR, confirmationHeader,
				confirmationMessage, new MessageBoxListener() {
					public void buttonClicked(ButtonId buttonId) {
						// Do Nothing
					}
				}, ButtonId.OK);
	}
	
	public static void getWarningNotification(String confirmationHeader,
			String confirmationMessage) {
		MessageBox.showPlain(Icon.WARN, confirmationHeader,
				confirmationMessage, new MessageBoxListener() {
					public void buttonClicked(ButtonId buttonId) {
						// Do Nothing
					}
				}, ButtonId.OK);
	}
	
	public static void getAlertNotification(String confirmationHeader,
			String confirmationMessage) {
		MessageBox.showPlain(Icon.INFO, confirmationHeader,
				confirmationMessage, new MessageBoxListener() {
					public void buttonClicked(ButtonId buttonId) {
						// Do Nothing
					}
				}, ButtonId.OK);
	}
public void getOkCancelMessage(String confirmationHeader,
			String confirmationMessage) {
		MessageBox.showPlain(Icon.QUESTION, confirmationHeader,
				confirmationMessage, new MessageBoxListener() {
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals("OK")) {
							yesMethod();
						} else {
							noMethod();
						}
					}
				}, ButtonId.OK, ButtonId.CANCEL);
	}

	
        
        public abstract void noMethod();

	public abstract void yesMethod();
}
