package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchSalesMasterException extends NoSuchModelException {

    public NoSuchSalesMasterException() {
        super();
    }

    public NoSuchSalesMasterException(String msg) {
        super(msg);
    }

    public NoSuchSalesMasterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchSalesMasterException(Throwable cause) {
        super(cause);
    }

}
