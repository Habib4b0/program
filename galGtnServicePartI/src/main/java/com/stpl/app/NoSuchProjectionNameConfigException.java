package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchProjectionNameConfigException extends NoSuchModelException {

    public NoSuchProjectionNameConfigException() {
        super();
    }

    public NoSuchProjectionNameConfigException(String msg) {
        super(msg);
    }

    public NoSuchProjectionNameConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchProjectionNameConfigException(Throwable cause) {
        super(cause);
    }

}
