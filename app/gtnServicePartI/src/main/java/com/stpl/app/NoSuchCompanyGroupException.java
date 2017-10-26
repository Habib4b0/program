

package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchCompanyGroupException extends NoSuchModelException {

	public NoSuchCompanyGroupException() {
		super();
	}

	public NoSuchCompanyGroupException(String msg) {
		super(msg);
	}

	public NoSuchCompanyGroupException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCompanyGroupException(Throwable cause) {
		super(cause);
	}

}