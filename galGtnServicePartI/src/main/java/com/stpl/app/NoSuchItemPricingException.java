package com.stpl.app;

import com.stpl.portal.NoSuchModelException;

/**
 * @author
 */
public class NoSuchItemPricingException extends NoSuchModelException {

    public NoSuchItemPricingException() {
        super();
    }

    public NoSuchItemPricingException(String msg) {
        super(msg);
    }

    public NoSuchItemPricingException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchItemPricingException(Throwable cause) {
        super(cause);
    }

}
