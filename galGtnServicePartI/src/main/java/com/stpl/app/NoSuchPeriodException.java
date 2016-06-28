package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchPeriodException extends NoSuchModelException {

    public NoSuchPeriodException() {
        super();
    }

    public NoSuchPeriodException(String msg) {
        super(msg);
    }

    public NoSuchPeriodException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchPeriodException(Throwable cause) {
        super(cause);
    }

}
