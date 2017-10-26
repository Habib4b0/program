package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchMasterDataAttributeException extends NoSuchModelException {

    public NoSuchMasterDataAttributeException() {
        super();
    }

    public NoSuchMasterDataAttributeException(String msg) {
        super(msg);
    }

    public NoSuchMasterDataAttributeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchMasterDataAttributeException(Throwable cause) {
        super(cause);
    }

}
