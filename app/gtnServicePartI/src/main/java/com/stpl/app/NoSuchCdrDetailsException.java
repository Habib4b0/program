

package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchCdrDetailsException extends NoSuchModelException {

	public NoSuchCdrDetailsException() {
		super();
	}

	public NoSuchCdrDetailsException(String msg) {
		super(msg);
	}

	public NoSuchCdrDetailsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCdrDetailsException(Throwable cause) {
		super(cause);
	}

}