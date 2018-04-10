/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkPriceTabValueChangeManager {

	GtnFrameworkPriceTabValueChangeManager() {
		return;
	}

	public static Boolean isValueChangeAllowed() {
		return (Boolean) GtnUIFrameworkGlobalUI.getSessionProperty("PriceTabValueChangeAllowed");
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty("PriceTabValueChangeAllowed", value);
	}

}