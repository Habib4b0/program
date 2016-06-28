package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchCompanyParentDetailsException extends NoSuchModelException {

    public NoSuchCompanyParentDetailsException() {
        super();
    }

    public NoSuchCompanyParentDetailsException(String msg) {
        super(msg);
    }

    public NoSuchCompanyParentDetailsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchCompanyParentDetailsException(Throwable cause) {
        super(cause);
    }

}
