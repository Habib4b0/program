package com.stpl.app.util;

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

	/** The resouce bundle. */
	private static ResourceBundle resouceBundle = ResourceBundle.getBundle("errorcodes.errorcode");

	public static final String HYPHEN = " - ";
        
        
        private static final Logger LOGGER = Logger.getLogger(ErrorCodeUtil.class);


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
		    if (key != null && resouceBundle.containsKey(key)) {
                        return resouceBundle.getString(key);
                    }
                    return "";
		} catch(NullPointerException | MissingResourceException e){
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
	public static String getErrorMessage(final Exception exception ) {
		final StringBuffer sbValue =new StringBuffer();
		if (exception.getCause() instanceof IOException) {
			sbValue.append(getEC(ErrorCodes.IO_ERROR_CODE) + HYPHEN);
		} else if (exception.getCause() instanceof ORMException) {
			sbValue.append(getEC(ErrorCodes.SQL_ERROR_CODE) + HYPHEN);
		} else if (exception.getCause() instanceof RuntimeException) {
			sbValue.append(getEC(ErrorCodes.RT_ERROR_CODE) + HYPHEN);
		}
		sbValue.append(getEC(ErrorCodes.ERROR_CODE_1000));
		return sbValue.toString();
	}
	
    
}
