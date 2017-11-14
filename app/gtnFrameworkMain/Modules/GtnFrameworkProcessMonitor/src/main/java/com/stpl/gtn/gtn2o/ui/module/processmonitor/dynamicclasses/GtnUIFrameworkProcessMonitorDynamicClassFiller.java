package com.stpl.gtn.gtn2o.ui.module.processmonitor.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.action.GtnFrameworkProcessMonitorValidationAction;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.action.GtnFrameworkSaveButtonAction;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.action.GtnFrameworkTableClickAction;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.action.GtnUIFrameworkProcessTypeDblbChangeAction;

public class GtnUIFrameworkProcessMonitorDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkProcessMonitorValidationAction.class.getName(),
				new GtnFrameworkProcessMonitorValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSaveButtonAction.class.getName(),
				new GtnFrameworkSaveButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTableClickAction.class.getName(),
				new GtnFrameworkTableClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkProcessTypeDblbChangeAction.class.getName(),
				new GtnUIFrameworkProcessTypeDblbChangeAction());

	}

}
