package com.stpl.ifs.ui.errorhandling;


import com.vaadin.server.UserError;
import com.vaadin.ui.Label;

public class ErrorLabel extends Label implements ErrorDisplay {
    private static final long serialVersionUID = 3064066324612002015L;

    public ErrorLabel() {
        setVisible(false);
        setStyleName("myerror");
    }
   
    public void setError(String error) {
        setValue(error);
        setComponentError(new UserError(error));
        setVisible(true);
    }

    public void clearError() {
        setValue(null);
        setComponentError(null);
        setVisible(false);
    }
}