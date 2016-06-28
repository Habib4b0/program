package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchDeductionDetailsException extends NoSuchModelException {

    public NoSuchDeductionDetailsException() {
        super();
    }

    public NoSuchDeductionDetailsException(String msg) {
        super(msg);
    }

    public NoSuchDeductionDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchDeductionDetailsException(Throwable cause) {
        super(cause);
    }

}
