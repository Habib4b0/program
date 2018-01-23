/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha
 */
public class MessageUtil {

    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("properties.message");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

    public static String getErrorCode(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e.getMessage());
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
