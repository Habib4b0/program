package com.stpl.gtn.gtn2o.ui.module.emailconfig.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigDefaultDataLoadAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigSaveAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigSaveValueChange;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigViewAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigurationValidation;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailNotificationSaveAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailNotificationValidation;

public class GtnUIFrameworkEmailConfigDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailConfigDefaultDataLoadAction.class.getName(),
				new GtnFrameworkEmailConfigDefaultDataLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailConfigSaveAction.class.getName(),
				new GtnFrameworkEmailConfigSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailConfigSaveValueChange.class.getName(),
				new GtnFrameworkEmailConfigSaveValueChange());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailConfigurationValidation.class.getName(),
				new GtnFrameworkEmailConfigurationValidation());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailConfigViewAction.class.getName(),
				new GtnFrameworkEmailConfigViewAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailNotificationSaveAction.class.getName(),
				new GtnFrameworkEmailNotificationSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEmailNotificationValidation.class.getName(),
				new GtnFrameworkEmailNotificationValidation());
	}

}
