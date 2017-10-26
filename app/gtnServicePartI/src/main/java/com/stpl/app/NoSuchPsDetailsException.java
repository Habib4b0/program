package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchPsDetailsException extends NoSuchModelException {

    public NoSuchPsDetailsException() {
        super();
    }

    public NoSuchPsDetailsException(String msg) {
        super(msg);
    }

    public NoSuchPsDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchPsDetailsException(Throwable cause) {
        super(cause);
    }

}
