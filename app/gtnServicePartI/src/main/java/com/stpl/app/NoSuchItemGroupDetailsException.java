package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchItemGroupDetailsException extends NoSuchModelException {

    public NoSuchItemGroupDetailsException() {
        super();
    }

    public NoSuchItemGroupDetailsException(String msg) {
        super(msg);
    }

    public NoSuchItemGroupDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchItemGroupDetailsException(Throwable cause) {
        super(cause);
    }

}
