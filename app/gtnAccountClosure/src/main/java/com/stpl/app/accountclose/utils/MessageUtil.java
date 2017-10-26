/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class MessageUtil {

    public static final ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.message");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = Logger.getLogger(MessageUtil.class);

    /**
     * Gets the Error code of the key.
     *
     * @param key the key
     * @return the errorcode
     */
    public static String getErrorCode(final String key) {
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
     * @param Exception
     * @return message
     */
    public static String getMessage(final String key) {
        final StringBuffer sbValue = new StringBuffer();

        sbValue.append(getErrorCode(key));
        return sbValue.toString();
    }

    /**
     * Constructor.
     */
    private MessageUtil() {
    }

}
