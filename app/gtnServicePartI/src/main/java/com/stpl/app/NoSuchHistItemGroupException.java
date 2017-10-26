package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchHistItemGroupException extends NoSuchModelException {

    public NoSuchHistItemGroupException() {
        super();
    }

    public NoSuchHistItemGroupException(String msg) {
        super(msg);
    }

    public NoSuchHistItemGroupException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchHistItemGroupException(Throwable cause) {
        super(cause);
    }

}
