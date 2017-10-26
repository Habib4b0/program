/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.portal.kernel.dao.orm.ORMException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author kasiammal.m
 */
public class ErrorCodeUtil {

    public static final ResourceBundle resouceBundle = ResourceBundle.getBundle("errorcodes.errorcode");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = Logger.getLogger(ErrorCodeUtil.class);

    private ErrorCodeUtil() {

    }

    /**
     * Gets the Error code of the key.
     *
     * @param key the key
     * @return the errorcode
     */
    public static String getEC(final String key) {
        try {
            return resouceBundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * Gets the Error Message along with the Error Code.
     *
     * @param exception the exception
     * @return message
     */
    public static String getErrorMessage(final Exception exception) {
        final StringBuffer message = new StringBuffer(StringUtils.EMPTY);
        if (exception.getCause() instanceof IOException) {
            message.append(getEC(ErrorCodes.IO_ERROR_CODE) + HYPHEN);
        } else if (exception.getCause() instanceof ORMException) {
            message.append(getEC(ErrorCodes.SQL_ERROR_CODE) + HYPHEN);
        } else if (exception.getCause() instanceof RuntimeException) {
            message.append(getEC(ErrorCodes.RT_ERROR_CODE) + HYPHEN);
        }
        message.append(getEC(ErrorCodes.ERROR_CODE_1000));
        return message.toString();
    }
}
