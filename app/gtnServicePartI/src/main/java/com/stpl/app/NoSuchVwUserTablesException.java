package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchVwUserTablesException extends NoSuchModelException {

    public NoSuchVwUserTablesException() {
        super();
    }

    public NoSuchVwUserTablesException(String msg) {
        super(msg);
    }

    public NoSuchVwUserTablesException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchVwUserTablesException(Throwable cause) {
        super(cause);
    }

}
