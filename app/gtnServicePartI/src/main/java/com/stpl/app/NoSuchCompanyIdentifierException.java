package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchCompanyIdentifierException extends NoSuchModelException {

	public NoSuchCompanyIdentifierException() {
		super();
	}

	public NoSuchCompanyIdentifierException(String msg) {
		super(msg);
	}

	public NoSuchCompanyIdentifierException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCompanyIdentifierException(Throwable cause) {
		super(cause);
	}

}