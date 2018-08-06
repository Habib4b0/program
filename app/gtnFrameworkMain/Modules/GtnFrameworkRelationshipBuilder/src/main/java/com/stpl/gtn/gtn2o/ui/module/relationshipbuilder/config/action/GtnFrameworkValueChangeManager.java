/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkValueChangeManager {

    private GtnFrameworkValueChangeManager() {
        throw new AccessDeniedException("Can't create Object for this class");
    }

    public static Boolean isValueChangeAllowed() {
        return (Boolean) GtnUIFrameworkGlobalUI.getSessionProperty("hierarchyNameValueChange");
    }

    public static void setValueChangeAllowed(boolean value) {
        GtnUIFrameworkGlobalUI.addSessionProperty("hierarchyNameValueChange", value);
    }

}

class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    AccessDeniedException(String cause) {
        super(cause);
    }
}
