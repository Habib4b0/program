package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchItemIdentifierException extends NoSuchModelException {

    public NoSuchItemIdentifierException() {
        super();
    }

    public NoSuchItemIdentifierException(String msg) {
        super(msg);
    }

    public NoSuchItemIdentifierException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchItemIdentifierException(Throwable cause) {
        super(cause);
    }

}
