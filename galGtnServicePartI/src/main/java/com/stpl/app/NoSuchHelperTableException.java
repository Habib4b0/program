package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchHelperTableException extends NoSuchModelException {

    public NoSuchHelperTableException() {
        super();
    }

    public NoSuchHelperTableException(String msg) {
        super(msg);
    }

    public NoSuchHelperTableException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchHelperTableException(Throwable cause) {
        super(cause);
    }

}
