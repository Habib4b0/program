package com.stpl.gtn.gtn2o.ui.framework.engine.base;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkClassLoader {

	public Object loadDynamicClass(String className) throws GtnFrameworkGeneralException {

		GtnUIFrameworkConfigMap frameworkConfigMap = GtnUIFrameworkGlobalUI.getGlobalComponentData()
				.getFrameworkConfigMap();
		if (frameworkConfigMap.getDynamicClass(className) != null) {
			return frameworkConfigMap.getDynamicClass(className);

		} else {
			throw new GtnFrameworkGeneralException(
					"Error in loading " + className + "class please add the action to filler.");
		}
	}
}
