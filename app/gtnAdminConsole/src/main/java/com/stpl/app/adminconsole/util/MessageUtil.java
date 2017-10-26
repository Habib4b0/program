/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class MessageUtil {

    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.message");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = Logger.getLogger(MessageUtil.class);

    public static String getErrorCode(final String key) {
        try {
            return resouceBundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e);
            return "";
        }
    }

    public static String getMessage(final String key) {
        final StringBuffer sbValue = new StringBuffer();

        sbValue.append(getErrorCode(key));
        return sbValue.toString();
    }

    private MessageUtil() {

    }

}
