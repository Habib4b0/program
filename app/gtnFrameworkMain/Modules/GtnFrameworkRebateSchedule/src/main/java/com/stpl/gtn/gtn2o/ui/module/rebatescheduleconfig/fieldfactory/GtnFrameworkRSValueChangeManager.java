
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;

public class GtnFrameworkRSValueChangeManager {
    private GtnFrameworkRSValueChangeManager(){
        /**
         * empty constructor
         */
    }

	public static Boolean isValueChangeAllowed() {
		if (GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkRSConstants.VALUE_CHANGE_ALLOWED) == null) {
			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkRSConstants.VALUE_CHANGE_ALLOWED, Boolean.TRUE);
		}
		return (Boolean) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkRSConstants.VALUE_CHANGE_ALLOWED);
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkRSConstants.VALUE_CHANGE_ALLOWED, value);
	}

}
