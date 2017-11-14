package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

public class GtnFrameworkIfpValueChangeManager {

	private GtnFrameworkIfpValueChangeManager() {
	}

	public static Boolean isValueChangeAllowed() {
		return (boolean) GtnUIFrameworkGlobalUI.getSessionProperty("ValueChangeAllowed");
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", value);
	}

}
