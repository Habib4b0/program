package com.stpl.gtn.gtn2o.ui.module.processscheduler.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkAdditionalSearchCriteriaAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkCffResultTableResetAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkDateFromToValidationAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkGenerateCffOutBoundAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkMandatoryFieldSettingAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkRunButtonAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkScheduledProcessTableDoubleClickAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkStartEndDateValidationCustomAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkUpdateProcessAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnUIFrameworkFrequencyValueChangeAction;

public class GtnUIFrameworkProcessSchedularDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRunButtonAction.class.getName(),
				new GtnFrameworkRunButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkStartEndDateValidationCustomAction.class.getName(),
				new GtnFrameworkStartEndDateValidationCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkFrequencyValueChangeAction.class.getName(),
				new GtnUIFrameworkFrequencyValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkScheduledProcessTableDoubleClickAction.class.getName(),
				new GtnFrameworkScheduledProcessTableDoubleClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkMandatoryFieldSettingAction.class.getName(),
				new GtnFrameworkMandatoryFieldSettingAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUpdateProcessAction.class.getName(),
				new GtnFrameworkUpdateProcessAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCffResultTableResetAction.class.getName(),
				new GtnFrameworkCffResultTableResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAdditionalSearchCriteriaAction.class.getName(),
				new GtnFrameworkAdditionalSearchCriteriaAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDateFromToValidationAction.class.getName(),
				new GtnFrameworkDateFromToValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkGenerateCffOutBoundAction.class.getName(),
				new GtnFrameworkGenerateCffOutBoundAction());

	}

}
