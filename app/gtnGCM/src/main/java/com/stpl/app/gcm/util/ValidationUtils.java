/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alok.v
 */
public final class ValidationUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    /**
     * The special characters.
     */
    public final static String SPECIAL_CHARACTERS = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";
    /**
     * The special characters error msg.
     */
    public final static String SPCL_CHARS_ERROR_MSG = "allowed Special characters are @,*,#,.,$,&,_,-";
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("properties.validation");
    
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
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
             LOGGER.error(e);
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
