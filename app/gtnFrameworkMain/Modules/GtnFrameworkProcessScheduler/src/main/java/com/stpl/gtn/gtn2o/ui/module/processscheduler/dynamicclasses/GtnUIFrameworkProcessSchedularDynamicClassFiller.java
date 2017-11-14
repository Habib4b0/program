package com.stpl.gtn.gtn2o.ui.module.processscheduler.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkRunButtonAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkUpdateButtonAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnUIFrameworkFrequencyValueChangeAction;

public class GtnUIFrameworkProcessSchedularDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRunButtonAction.class.getName(),
				new GtnFrameworkRunButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUpdateButtonAction.class.getName(),
				new GtnFrameworkUpdateButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkFrequencyValueChangeAction.class.getName(),
				new GtnUIFrameworkFrequencyValueChangeAction());

	}

}
