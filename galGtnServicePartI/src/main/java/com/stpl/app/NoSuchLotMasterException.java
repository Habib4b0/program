package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchLotMasterException extends NoSuchModelException {

    public NoSuchLotMasterException() {
        super();
    }

    public NoSuchLotMasterException(String msg) {
        super(msg);
    }

    public NoSuchLotMasterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchLotMasterException(Throwable cause) {
        super(cause);
    }

}
