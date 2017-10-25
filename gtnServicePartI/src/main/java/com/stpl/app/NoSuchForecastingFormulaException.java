package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchForecastingFormulaException extends NoSuchModelException {

    public NoSuchForecastingFormulaException() {
        super();
    }

    public NoSuchForecastingFormulaException(String msg) {
        super(msg);
    }

    public NoSuchForecastingFormulaException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchForecastingFormulaException(Throwable cause) {
        super(cause);
    }

}
