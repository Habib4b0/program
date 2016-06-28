package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchCustomViewDetailsException extends NoSuchModelException {

    public NoSuchCustomViewDetailsException() {
        super();
    }

    public NoSuchCustomViewDetailsException(String msg) {
        super(msg);
    }

    public NoSuchCustomViewDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchCustomViewDetailsException(Throwable cause) {
        super(cause);
    }

}
