package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchUdcsException extends NoSuchModelException {

    public NoSuchUdcsException() {
        super();
    }

    public NoSuchUdcsException(String msg) {
        super(msg);
    }

    public NoSuchUdcsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchUdcsException(Throwable cause) {
        super(cause);
    }

}
