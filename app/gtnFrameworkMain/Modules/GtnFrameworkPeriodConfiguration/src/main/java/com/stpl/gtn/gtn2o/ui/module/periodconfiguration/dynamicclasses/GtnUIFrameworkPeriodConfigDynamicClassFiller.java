package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkModeFromToAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodConfigSaveAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodConfigurationValidation;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodViewAction;

public class GtnUIFrameworkPeriodConfigDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkModeFromToAction.class.getName(),
				new GtnFrameworkModeFromToAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPeriodConfigSaveAction.class.getName(),
				new GtnFrameworkPeriodConfigSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPeriodConfigurationValidation.class.getName(),
				new GtnFrameworkPeriodConfigurationValidation());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPeriodViewAction.class.getName(),
				new GtnFrameworkPeriodViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPeriodFieldValueChangeAction.class.getName(),
				new GtnFrameworkPeriodFieldValueChangeAction());

	}

}
