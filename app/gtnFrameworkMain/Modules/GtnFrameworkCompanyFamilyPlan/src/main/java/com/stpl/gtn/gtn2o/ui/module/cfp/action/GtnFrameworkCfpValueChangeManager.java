package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

public class GtnFrameworkCfpValueChangeManager {

	private GtnFrameworkCfpValueChangeManager() {
	}

	public static Boolean isValueChangeAllowed() {
		return (Boolean) GtnUIFrameworkGlobalUI.getSessionProperty("ValueChangeAllowed");
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", value);
	}

}
