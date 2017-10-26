package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchDocDetailsException extends NoSuchModelException {

    public NoSuchDocDetailsException() {
        super();
    }

    public NoSuchDocDetailsException(String msg) {
        super(msg);
    }

    public NoSuchDocDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchDocDetailsException(Throwable cause) {
        super(cause);
    }

}
