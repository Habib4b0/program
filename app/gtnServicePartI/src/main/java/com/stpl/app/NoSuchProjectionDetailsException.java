package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchProjectionDetailsException extends NoSuchModelException {

    public NoSuchProjectionDetailsException() {
        super();
    }

    public NoSuchProjectionDetailsException(String msg) {
        super(msg);
    }

    public NoSuchProjectionDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchProjectionDetailsException(Throwable cause) {
        super(cause);
    }

}
