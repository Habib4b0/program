package com.stpl.app.contract.util;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.stpl.portal.kernel.dao.orm.ORMException;
import org.jboss.logging.Logger;

/**
 * The Class ErrorCodeUtil.
 *
 * @author manikanta
 */
public final class ErrorCodeUtil {

    /**
     * The resouce bundle.
     */
    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("errorcodes.errorcode");
    
    private static final Logger LOGGER = Logger.getLogger(ErrorCodeUtil.class);
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
            return resouceBundle.getString(key);
        } catch (MissingResourceException e) {
             LOGGER.debug(e);
            return "";
        }
    }

    /**
     * Gets the Error Message along with the Error Code
     *
     * @param Exception
     * @return message
     */
    public static String getErrorMessage(final Exception exception) {
        final StringBuffer message = new StringBuffer("");
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
