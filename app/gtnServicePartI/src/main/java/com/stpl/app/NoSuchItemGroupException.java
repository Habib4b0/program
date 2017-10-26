package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchItemGroupException extends NoSuchModelException {

    public NoSuchItemGroupException() {
        super();
    }

    public NoSuchItemGroupException(String msg) {
        super(msg);
    }

    public NoSuchItemGroupException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchItemGroupException(Throwable cause) {
        super(cause);
    }

}
