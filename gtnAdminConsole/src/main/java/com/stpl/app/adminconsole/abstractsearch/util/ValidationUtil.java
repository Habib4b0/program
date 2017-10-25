/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class ValidationUtil {

    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.validation");

    public static ResourceBundle label = ResourceBundle.getBundle("properties.labelname");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = Logger.getLogger(ValidationUtil.class);

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
            LOGGER.error(e);
            return "";
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

    /**
     *
     * @param key
     * @return
     */
    public static String getLabel(final String key) {
        final StringBuffer sbValue = new StringBuffer();
        sbValue.append(getLC(key));
        return sbValue.toString();
    }

    public static String getLC(final String key) {
        try {
            return label.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e);
            return "";
        }
    }

    private ValidationUtil() {

    }
}
