/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author porchelvi.g
 */
public class ValidationUtil {

    public static final ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.validation");
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    public static final String HYPHEN = " - ";

    private final static Logger LOGGER = Logger.getLogger(ValidationUtil.class);

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

    private ValidationUtil() {

    }
}
