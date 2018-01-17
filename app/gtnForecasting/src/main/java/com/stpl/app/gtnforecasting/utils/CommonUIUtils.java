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
    public final Object[] attachmentColumns = new Object[]{Constant.DOCUMENT_NAME, Constant.DATE_ADDED, Constant.USER_NAME};
    /**
     * The Constant ATTACHMENT_HEADER.
     */
    public final String[] attachmentHeader = new String[]{"Document Name", "Date Added", "User Name"};
    
      /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] formulaLookup = new Object[]{
        Constant.FORMULA_TYPE,"formulaID", "formulaNo", "formulaName","version"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final String[] formulaLookupHeader = new String[]{
        "Formula Type","Formula ID", "Formula No", "Formula Name","Version"}; 
    
    public final  Object[] ruleDetailsColumns = new Object[]{
        Constant.DEDUCTION_TYPE, Constant.DEDUCTION_SUB_TYPE, Constant.DEDUCTION_CATEGORY, Constant.INDICATOR};
    public final  String[] ruleDetailsHeaders = new String[]{"Deduction Type", "Deduction Sub Type", "Deduction Category", "+/- Indicator"};

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
        notif.setDelayMsec(NumericConstants.THOUSAND);
        notif.show(Page.getCurrent());
       
    }
}
