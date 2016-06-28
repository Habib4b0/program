package com.stpl.app.util;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class MessageUtils {
	public static void getMessageNotification(String message) {
        Notification notif = new Notification(message,
                Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName("mystyle");
        notif.show(Page.getCurrent());

    }

}
