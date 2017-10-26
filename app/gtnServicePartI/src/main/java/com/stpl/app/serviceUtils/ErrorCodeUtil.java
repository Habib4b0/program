package com.stpl.app.serviceUtils;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.stpl.portal.kernel.dao.orm.ORMException;

/**
 * The Class ErrorCodeUtil.
 * 
 * @author manikanta
 */
public final class ErrorCodeUtil {

	/** The resouce bundle. */
	public static ResourceBundle resouceBundle = ResourceBundle.getBundle("errorcodes.errorcode");

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
			return resouceBundle.getString(key);
		} catch (MissingResourceException e) {
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
		final StringBuffer message=new StringBuffer();
		if (eexception.getCause() instanceof IOException) {
			message.append(getEC(ErrorCodes.IO_ERROR_CODE) + HYPHEN);
		} else if (eexception.getCause() instanceof ORMException) {
			message .append( getEC(ErrorCodes.SQL_ERROR_CODE) + HYPHEN);
		} else if (eexception.getCause() instanceof RuntimeException) {
			message.append(getEC(ErrorCodes.RT_ERROR_CODE) + HYPHEN);
		}
		message .append(getEC(ErrorCodes.ERROR_CODE_1000));
		return message.toString();
	}
}
