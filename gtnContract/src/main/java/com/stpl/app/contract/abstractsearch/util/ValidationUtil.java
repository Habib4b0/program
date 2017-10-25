/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.abstractsearch.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.jboss.logging.Logger;

/**
 * The Class ValidationUtil.
 *
 * @author pvinoth
 */
public class ValidationUtil {
    
    /** The resouce bundle. */
    public static final ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.validation");
    
    /** The label. */
    public static final ResourceBundle label = ResourceBundle.getBundle("properties.labelname");
    private static final Logger LOGGER = Logger.getLogger(ValidationUtil.class);

    /** The Constant HYPHEN. */
    public static final String HYPHEN = " - ";

    /**
     * The Constructor.
     *
     * @param key the key
     * @return the mc
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
            LOGGER.error(e);
            return "";
        }
    }

    /**
     * Gets the Error Message along with the Error Code.
     *
     * @param key the key
     * @return message
     */
    public static String getMessage(final String key) {
        final StringBuffer sbValue = new StringBuffer();
        sbValue.append(getMC(key));
        return sbValue.toString();
    }
    
    /**
     * Gets the label.
     *
     * @param key the key
     * @return the label
     */
    public static String getLabel(final String key) {
        final StringBuffer sbValue = new StringBuffer();
        sbValue.append(getLC(key));
        return sbValue.toString();
    }

    /**
     * Gets the lc.
     *
     * @param key the key
     * @return the lc
     */
    public static String getLC(final String key) {
        try {
            return label.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e);
            return "";
        }
    }
}
