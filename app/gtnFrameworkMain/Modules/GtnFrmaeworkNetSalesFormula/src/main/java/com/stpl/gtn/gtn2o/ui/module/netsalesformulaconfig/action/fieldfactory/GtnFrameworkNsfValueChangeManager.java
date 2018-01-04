/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;

/**
 *
 * @author Deepika.KrishnaKumar
 */
public class GtnFrameworkNsfValueChangeManager {

	private GtnFrameworkNsfValueChangeManager() {
		/**
		 * empty constructor
		 */
	}

	public static Boolean isValueChangeAllowed() {
		if (GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkNSFConstants.VALUE_CHANGE_ALLOWED) == null) {
			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkNSFConstants.VALUE_CHANGE_ALLOWED, true);
		}
		return (boolean) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkNSFConstants.VALUE_CHANGE_ALLOWED);
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkNSFConstants.VALUE_CHANGE_ALLOWED, value);
	}

}
