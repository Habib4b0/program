
package com.stpl.gtn.gtn2o.ui.module.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkValueChangeManager {

	public static Boolean isValueChangeAllowed() {
		if (GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB_VALUE_CHANGE_ALLOWED) == null) {
			setValueChangeAllowed(false);

		}
		return (boolean) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB_VALUE_CHANGE_ALLOWED);
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB_VALUE_CHANGE_ALLOWED, value);
	}

}
