package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchPsContractException extends NoSuchModelException {

    public NoSuchPsContractException() {
        super();
    }

    public NoSuchPsContractException(String msg) {
        super(msg);
    }

    public NoSuchPsContractException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchPsContractException(Throwable cause) {
        super(cause);
    }

}
