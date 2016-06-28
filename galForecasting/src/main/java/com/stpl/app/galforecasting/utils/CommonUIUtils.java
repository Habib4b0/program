/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUIUtils.
 *
 * @author lokeshwari
 */
public class CommonUIUtils {

    /**
     * The Constant ATTACHMENT_COLUMNS.
     */
    public static final Object[] ATTACHMENT_COLUMNS = new Object[]{Constant.DOCUMENT_NAME, Constant.DATE_ADDED, Constant.USER_NAME};
    /**
     * The Constant ATTACHMENT_HEADER.
     */
    public static final String[] ATTACHMENT_HEADER = new String[]{"Document Name", "Date Added", "User Name"};
    
      /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] FORMULA_LOOKUP = new Object[]{
        Constant.FORMULA_TYPE,"formulaID", "formulaNo", "formulaName","version"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final String[] FORMULA_LOOKUP_HEADER = new String[]{
        "Formula Type","Formula ID", "Formula No", "Formula Name","Version"}; 
    
    public static final  Object RULE_DETAILS_COLUMNS[] = new Object[]{
        Constant.DEDUCTION_TYPE, Constant.DEDUCTION_SUB_TYPE, Constant.DEDUCTION_CATEGORY, Constant.INDICATOR};
    public static final  String RULE_DETAILS_HEADERS[] = new String[]{"Deduction Type", "Deduction Sub Type", "Deduction Category", "+/- Indicator"};

    /**
     * Gets the message notification.
     *
     * @param message the message
     * @return the message notification
     */
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
        notif.setDelayMsec(1000);
        notif.show(Page.getCurrent());
       
    }
}
