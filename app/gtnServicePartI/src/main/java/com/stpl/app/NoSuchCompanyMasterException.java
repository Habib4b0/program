

package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchCompanyMasterException extends NoSuchModelException {

	public NoSuchCompanyMasterException() {
		super();
	}

	public NoSuchCompanyMasterException(String msg) {
		super(msg);
	}

	public NoSuchCompanyMasterException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCompanyMasterException(Throwable cause) {
		super(cause);
	}

}