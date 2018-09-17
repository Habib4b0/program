/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram.LeelaRam
 */
public abstract class CommonUI extends UI {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonUI.class);
    private boolean excelFlag = true;

    public boolean isExcelFlag() {
        return excelFlag;
    }

    public void setExcelFlag(boolean excelFlag) {
        this.excelFlag = excelFlag;
    }

    public void uiErrorHandler() {

        //added Default Error Handler 
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                StringBuilder cause = new StringBuilder("The Exception occured because of: ");
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) {
                        cause.append(t.getClass().getName());
                    }

                }
                LOGGER.error(cause.toString());
            }
        });

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
