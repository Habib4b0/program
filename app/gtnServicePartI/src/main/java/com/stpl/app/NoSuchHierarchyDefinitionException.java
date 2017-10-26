package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchHierarchyDefinitionException extends NoSuchModelException {

    public NoSuchHierarchyDefinitionException() {
        super();
    }

    public NoSuchHierarchyDefinitionException(String msg) {
        super(msg);
    }

    public NoSuchHierarchyDefinitionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchHierarchyDefinitionException(Throwable cause) {
        super(cause);
    }

}
