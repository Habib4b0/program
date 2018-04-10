/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.liferay.portal.kernel.dao.orm.ORMException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author santanukumar
 */
public class ErrorCodeUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodeUtil.class);

    /**
     * The resouce bundle.
     */
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("errorcodes.errorcode");

    /**
     * HYPEN constant
     */
    public static final String HYPHEN = " - ";

    /**
     * The Constructor.
     */
    private ErrorCodeUtil() {
        // empty constructor
    }

    /**
     * Gets the Error code of the key.
     *
     * @param key the key
     * @return the errorcode
     */
    public static String getEC(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
             LOGGER.error("",e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * Gets the Error Message along with the Error Code
     *
     * @param eexception
     * @return message
     */
    public static String getErrorMessage(final Exception eexception) {
        final StringBuffer message = new StringBuffer();
        if (eexception.getCause() instanceof IOException) {
            message.append(getEC(ErrorCodes.IO_ERROR_CODE) ).append( HYPHEN);
        } else if (eexception.getCause() instanceof ORMException) {
            message.append(getEC(ErrorCodes.SQL_ERROR_CODE) ).append( HYPHEN);
        } else if (eexception.getCause() instanceof RuntimeException) {
            message.append(getEC(ErrorCodes.RT_ERROR_CODE) ).append( HYPHEN);
        }
        message.append(getEC(ErrorCodes.ERROR_CODE_1000));
        return message.toString();
    }

}
