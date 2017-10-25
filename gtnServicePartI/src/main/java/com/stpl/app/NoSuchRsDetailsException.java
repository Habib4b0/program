package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchRsDetailsException extends NoSuchModelException {

    public NoSuchRsDetailsException() {
        super();
    }

    public NoSuchRsDetailsException(String msg) {
        super(msg);
    }

    public NoSuchRsDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchRsDetailsException(Throwable cause) {
        super(cause);
    }

}
