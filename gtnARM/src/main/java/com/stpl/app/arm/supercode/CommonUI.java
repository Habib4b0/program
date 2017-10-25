/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram.LeelaRam
 */
public abstract class CommonUI extends UI {

    public static final Logger LOGGER = Logger.getLogger(CommonUI.class);
    public boolean excelFlag = Boolean.TRUE;

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
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) {

                        cause += t.getClass().getName();
                    }
                    t.printStackTrace();
                }
                LOGGER.error(cause);
            }
        });

    }

}
