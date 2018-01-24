/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.liferay.portal.kernel.dao.orm.ORMException;
import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorCodeUtil.
 *
 * @author lokeshwari
 */
public class ErrorCodeUtil {

    /**
     * The resouce bundle.
     */
    public static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("errorcodes.errorcode");
    /**
     * The HYPHEN.
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
            LOGGER.error(e.getMessage());
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
