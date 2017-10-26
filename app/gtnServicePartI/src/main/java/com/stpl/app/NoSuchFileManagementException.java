package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchFileManagementException extends NoSuchModelException {

    public NoSuchFileManagementException() {
        super();
    }

    public NoSuchFileManagementException(String msg) {
        super(msg);
    }

    public NoSuchFileManagementException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchFileManagementException(Throwable cause) {
        super(cause);
    }

}
