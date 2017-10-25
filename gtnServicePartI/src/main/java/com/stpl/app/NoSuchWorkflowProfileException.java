package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchWorkflowProfileException extends NoSuchModelException {

    public NoSuchWorkflowProfileException() {
        super();
    }

    public NoSuchWorkflowProfileException(String msg) {
        super(msg);
    }

    public NoSuchWorkflowProfileException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchWorkflowProfileException(Throwable cause) {
        super(cause);
    }

}
