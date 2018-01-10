package com.stpl.app.adminconsole.util;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.liferay.portal.kernel.dao.orm.ORMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ErrorCodeUtil.
 *
 * @author manikanta
 */
public final class ErrorCodeUtil {

    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("errorcodes.errorcode");

    public static final String HYPHEN = " - ";

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodeUtil.class);

    private ErrorCodeUtil() {

    }

    public static String getEC(final String key) {
        try {
            return resouceBundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }

    public static String getErrorMessage(final Exception eexception) {
        final StringBuffer message = new StringBuffer();
        if (eexception.getCause() instanceof IOException) {
            message.append(getEC(ErrorCodes.IO_ERROR_CODE) + HYPHEN);
        } else if (eexception.getCause() instanceof ORMException) {
            message.append(getEC(ErrorCodes.SQL_ERROR_CODE) + HYPHEN);
        } else if (eexception.getCause() instanceof RuntimeException) {
            message.append(getEC(ErrorCodes.RT_ERROR_CODE) + HYPHEN);
        }
        message.append(getEC(ErrorCodes.ERROR_CODE_1000));
        return message.toString();
    }
}
