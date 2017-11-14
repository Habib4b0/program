/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkPriceTabValueChangeManager {

	public static Boolean isValueChangeAllowed() {
		if (GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.PRICE_TAB_VALUE_CHANGE_ALLOWED) == null) {
			setValueChangeAllowed(false);

		}
		return (boolean) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.PRICE_TAB_VALUE_CHANGE_ALLOWED);
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.PRICE_TAB_VALUE_CHANGE_ALLOWED, value);
	}

}
