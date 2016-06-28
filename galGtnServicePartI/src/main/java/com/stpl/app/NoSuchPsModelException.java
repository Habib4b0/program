package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchPsModelException extends NoSuchModelException {

    public NoSuchPsModelException() {
        super();
    }

    public NoSuchPsModelException(String msg) {
        super(msg);
    }

    public NoSuchPsModelException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchPsModelException(Throwable cause) {
        super(cause);
    }

}
