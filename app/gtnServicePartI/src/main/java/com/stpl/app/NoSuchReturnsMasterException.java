package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchReturnsMasterException extends NoSuchModelException {

    public NoSuchReturnsMasterException() {
        super();
    }

    public NoSuchReturnsMasterException(String msg) {
        super(msg);
    }

    public NoSuchReturnsMasterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchReturnsMasterException(Throwable cause) {
        super(cause);
    }

}
