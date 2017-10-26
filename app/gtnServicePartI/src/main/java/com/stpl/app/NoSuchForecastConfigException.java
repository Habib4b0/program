package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchForecastConfigException extends NoSuchModelException {

    public NoSuchForecastConfigException() {
        super();
    }

    public NoSuchForecastConfigException(String msg) {
        super(msg);
    }

    public NoSuchForecastConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchForecastConfigException(Throwable cause) {
        super(cause);
    }

}
