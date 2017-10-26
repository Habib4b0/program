/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;



/**
 *
 * @author Santanukumar
 */
public class CommonUIUtils {
    public static final String SELECT_ONE="-Select One-";
    public static final String EMAIL_VALIDATION="^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
    public static final String DELETE = "Delete";
    public static final String THREE_FIFTY_PIXELS = "350px";
    public static final String ENDDATE = "EndDate";
    public static final String STARTDATE = "StartDate";
    public static final String ATTACHED_STATUS = "Attached Status";

        public static final Object[] NOTIFICATION_COLUMNS = new Object[]{
            "businessProcess","fromMailId","toMailId","ccMailId","subject","body","creationDate","modifiedDate"
            
        };
        public static final String[] NOTIFICATION_HEADERS= new String[]{
            "business Process","from Mail-Id","to Mail-Id","cc Mail-Id","subject","body","creation Date","modified Date"
            };
    
    
}
