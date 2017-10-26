package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchItemQualifierException extends NoSuchModelException {

    public NoSuchItemQualifierException() {
        super();
    }

    public NoSuchItemQualifierException(String msg) {
        super(msg);
    }

    public NoSuchItemQualifierException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchItemQualifierException(Throwable cause) {
        super(cause);
    }

}
