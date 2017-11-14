package com.stpl.gtn.gtn2o.ui.businessrole.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.businessrole.action.GtnFrameworkBusinessRoleMasterSave;
import com.stpl.gtn.gtn2o.ui.businessrole.action.GtnUIFrameworkModuleDetailsSaveAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnFrameworkBRMMDynamicClassObject implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkBusinessRoleMasterSave.class.getName(),
				new GtnFrameworkBusinessRoleMasterSave());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkModuleDetailsSaveAction.class.getName(),
				new GtnUIFrameworkModuleDetailsSaveAction());
	}
}
