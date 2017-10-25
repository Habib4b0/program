/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.util;

import com.stpl.ifs.ui.util.NumericConstants;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

/**
 *
 * @author Karthik.Raja
 */
public abstract class CommonMethods {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonMethods.class);
      private static Calendar cal;

    public static void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {

        controlBar.setStyleName("responsivePagedTable");
        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);

    }

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
            public void buttonClicked(final ButtonId buttonId) {
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
            public void buttonClicked(final ButtonId buttonId) {
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
            public void buttonClicked(final ButtonId buttonId) {
            }
        }, ButtonId.OK);
    }
    /* Gets the info notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the info notification
     */

    public static MessageBox getInfoNotification(final String confirmationHeader, final String confirmationMessage) {
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
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return msg;

        } catch (Exception ex) {
            LOGGER.debug(ex);
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
    
     public static Date getYesterdayDate() {
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
      public static Date changeTime(Date date,int time) {
        cal = Calendar.getInstance();
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, time);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
      public static Date getCurrentTime() {
           cal = Calendar.getInstance();
           return cal.getTime();
      }
    public static Button configureExcelButton(Button excelExport) {
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        return excelExport;
    }
      public static void commonTableConfig(ExtPagedTable table){
        table.setFilterBarVisible(true);
        table.setImmediate(true);
        table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        table.setHeight("250px");
        table.setSelectable(true);
        table.markAsDirty();
        table.setValidationVisible(false);
        table.addStyleName("filterbar");
    }
    public static Object customizeUsingReflection(Class className, Object object,Object[] resultSet) throws Exception {
        Field[] declaredField = null;
        int index = NumericConstants.ZERO;
        declaredField = className.getDeclaredFields();
        for (Field field : declaredField) {
            field.setAccessible(true);
            field.set(object, resultSet[index++]);
        }
        return object;
    }
}
