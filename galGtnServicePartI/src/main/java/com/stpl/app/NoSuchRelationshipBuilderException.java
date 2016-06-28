package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchRelationshipBuilderException extends NoSuchModelException {

    public NoSuchRelationshipBuilderException() {
        super();
    }

    public NoSuchRelationshipBuilderException(String msg) {
        super(msg);
    }

    public NoSuchRelationshipBuilderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchRelationshipBuilderException(Throwable cause) {
        super(cause);
    }

}
