package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchWorkflowMasterException extends NoSuchModelException {

    public NoSuchWorkflowMasterException() {
        super();
    }

    public NoSuchWorkflowMasterException(String msg) {
        super(msg);
    }

    public NoSuchWorkflowMasterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchWorkflowMasterException(Throwable cause) {
        super(cause);
    }

}
