package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchDemandForecastException extends NoSuchModelException {

    public NoSuchDemandForecastException() {
        super();
    }

    public NoSuchDemandForecastException(String msg) {
        super(msg);
    }

    public NoSuchDemandForecastException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchDemandForecastException(Throwable cause) {
        super(cause);
    }

}
