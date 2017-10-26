
package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchMasterDataFilesException extends NoSuchModelException {

	public NoSuchMasterDataFilesException() {
		super();
	}

	public NoSuchMasterDataFilesException(String msg) {
		super(msg);
	}

	public NoSuchMasterDataFilesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMasterDataFilesException(Throwable cause) {
		super(cause);
	}

}