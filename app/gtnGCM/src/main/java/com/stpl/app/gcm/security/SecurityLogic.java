/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.security;

import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.ui.Component;
import java.util.Map;

/**
 *
 * @author mohamed.hameed
 */
public class SecurityLogic {

    public static Boolean isPermitted(final Map<String, AppPermission> functionHM, final String propertyName) {
        if (functionHM.get(propertyName) != null && ((AppPermission) functionHM.get(propertyName)).isFunctionFlag()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void isPermitted(final Map<String, AppPermission> functionHM, final String propertyName, final Component component) {
        if (functionHM.get(propertyName) == null || !((AppPermission) functionHM.get(propertyName)).isFunctionFlag()) {
            component.setVisible(Boolean.FALSE);
        }
    }

    public static void setButtonVisible(final Component[] componentArray) {
        for (int i = 0; i < componentArray.length; i++) {
            componentArray[i].setVisible(false);
        }
    }
}
