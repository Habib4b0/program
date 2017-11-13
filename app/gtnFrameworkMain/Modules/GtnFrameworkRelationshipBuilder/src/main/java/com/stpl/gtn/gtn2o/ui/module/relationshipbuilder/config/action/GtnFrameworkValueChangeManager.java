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

	}

	public static Boolean isValueChangeAllowed() {
		return (boolean) GtnUIFrameworkGlobalUI.getSessionProperty("hierarchyNameValueChange");
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty("hierarchyNameValueChange", value);
	}

}
