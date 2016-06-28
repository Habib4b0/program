/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public final class ValidationUtils {

    /**
     * The special characters.
     */
    public final static String SPECIAL_CHARACTERS = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";
    /**
     * The special characters error msg.
     */
    public final static String SPCL_CHARS_ERROR_MSG = "allowed Special characters are @,*,#,.,$,&,_,-";
    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.validation");
    
     /**
     * The Constructor.
     */
    /**
     * Gets the Message Code of the key.
     *
     * @param key the key
     * @return the message
     */
    public static String getMC(final String key) {
        try {
            return resouceBundle.getString(key);
        } catch (MissingResourceException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * Gets the Error Message along with the Error Code
     *
     * @param key
     * @return message
     */
    public static String getMessage(final String key) {
        final StringBuffer sbValue = new StringBuffer();
        sbValue.append(getMC(key));
        return sbValue.toString();
    }
}
