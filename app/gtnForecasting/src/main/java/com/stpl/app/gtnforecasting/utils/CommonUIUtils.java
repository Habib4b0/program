/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;


/**
 * The Class CommonUIUtils.
 *
 * @author lokeshwari
 */
public class CommonUIUtils {

    public static void getMessageNotification(final String message) {

        final Notification notif = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName(Constant.MY_STYLE);
        notif.show(Page.getCurrent());

    }
      /**
     * Method is used to display Success Notification after save.
     *
     * @param message - message that is to be displayed.
     */
    public static void successNotification(final String message) {
       
        final Notification notif = new Notification(message,
                Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName(Constant.MY_STYLE);
        notif.setDelayMsec(NumericConstants.THOUSAND);
        notif.show(Page.getCurrent());
       
    }
}
