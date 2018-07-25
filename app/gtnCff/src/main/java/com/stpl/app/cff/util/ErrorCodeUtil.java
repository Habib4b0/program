package com.stpl.app.cff.util;

import com.liferay.portal.kernel.dao.orm.ORMException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ErrorCodeUtil.
 * 
 * @author manikanta
 */
public final class ErrorCodeUtil {

	/** The resouce bundle. */
	public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("errorcodes.errorcode");
        
        private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodeUtil.class);

	/** HYPEN constant */
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
	 * @param key
	 *            the key
	 * @return the errorcode
	 */
	public static String getEC(final String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
                    LOGGER.error(e.getMessage());
			return "";
		}
	}

	/**
	 * Gets the Error Message along with the Error Code
	 * 
     * @param eexception
	 * @return message
	 */
	public static String getErrorMessage(final Exception eexception) {
		final StringBuilder message=new StringBuilder();
		if (eexception.getCause() instanceof IOException) {
			message.append(getEC(ErrorCodes.IO_ERROR_CODE) ).append( HYPHEN);
		} else if (eexception.getCause() instanceof ORMException) {
			message .append( getEC(ErrorCodes.SQL_ERROR_CODE) ).append( HYPHEN);
		} else if (eexception.getCause() instanceof RuntimeException) {
			message.append(getEC(ErrorCodes.RT_ERROR_CODE) ).append( HYPHEN);
		}
		message .append(getEC(ErrorCodes.ERROR_CODE_1000));
		return message.toString();
	}
}
