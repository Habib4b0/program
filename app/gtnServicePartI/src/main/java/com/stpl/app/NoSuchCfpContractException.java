

package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author 
 */
public class NoSuchCfpContractException extends NoSuchModelException {

	public NoSuchCfpContractException() {
		super();
	}

	public NoSuchCfpContractException(String msg) {
		super(msg);
	}

	public NoSuchCfpContractException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCfpContractException(Throwable cause) {
		super(cause);
	}

}