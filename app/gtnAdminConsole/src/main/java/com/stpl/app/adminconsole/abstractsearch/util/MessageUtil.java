/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class Message Util
 *
 * @author Harlin
 */
public final class MessageUtil {

    /**
     * The resouce bundle.
     */
    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.message");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

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
            LOGGER.error(e.getMessage());
            return "";
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
