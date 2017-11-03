/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.abstractsearch.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.jboss.logging.Logger;

/**
 * The class Message Util
 * @author Harlin
 */
public final class MessageUtil {
    /** The resouce bundle. */
	public static ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.message");

	public static final String HYPHEN = " - ";
        
        private static final Logger LOGGER = Logger.getLogger(MessageUtil.class);

	/**
	 * The Constructor.
	 */

        /**
	 * Gets the Error code of the key.
	 *
	 * @param key
	 *            the key
	 * @return the errorcode
	 */
	public static String getErrorCode(final String key) {
		try {
	            if (key != null && key.contains(key)) {
                        return resouceBundle.getString(key);
                    }
                    return "";
		} catch (MissingResourceException e) {
                        LOGGER.error(e);
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
		final StringBuffer sbValue =new StringBuffer();
		
		sbValue.append(getErrorCode(key));
		return sbValue.toString();
	}
         /**
            * Constructor.
            */
           private MessageUtil(){
               //Empty
           }
}
