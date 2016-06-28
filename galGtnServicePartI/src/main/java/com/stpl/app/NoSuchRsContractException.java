package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchRsContractException extends NoSuchModelException {

    public NoSuchRsContractException() {
        super();
    }

    public NoSuchRsContractException(String msg) {
        super(msg);
    }

    public NoSuchRsContractException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchRsContractException(Throwable cause) {
        super(cause);
    }

}
