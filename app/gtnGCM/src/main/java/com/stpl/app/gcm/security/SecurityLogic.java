/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.security;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.ui.Component;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class SecurityLogic {

      private static final Logger LOGGER = LoggerFactory.getLogger(SecurityLogic.class);
    
    private SecurityLogic()
    {
        LOGGER.debug("SecurityLogic");
    }
    
    public static Boolean isPermitted(final Map<String, AppPermission> functionHM, final String propertyName) {
        if (functionHM.get(propertyName) != null && ((AppPermission) functionHM.get(propertyName)).isFunctionFlag()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void isPermitted(final Map<String, AppPermission> functionHM, final String propertyName, final Component component) {
        if (functionHM.get(propertyName) == null || !((AppPermission) functionHM.get(propertyName)).isFunctionFlag()) {
            component.setVisible(BooleanConstant.getFalseFlag());
        }
    }

    public static void setButtonVisible(final Component[] componentArray) {
        for (int i = 0; i < componentArray.length; i++) {
            componentArray[i].setVisible(false);
        }
    }
}
