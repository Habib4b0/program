package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchItemMasterException extends NoSuchModelException {

	public NoSuchItemMasterException() {
		super();
	}

	public NoSuchItemMasterException(String msg) {
		super(msg);
	}

	public NoSuchItemMasterException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchItemMasterException(Throwable cause) {
		super(cause);
	}

}