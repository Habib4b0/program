package com.stpl.gtn.gtn2o.ui.module.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.action.GtnUiFrameworkCustomerADDAllButtonAction;
import com.stpl.gtn.gtn2o.ui.module.action.GtnUiFrameworkItemADDAddAllButtonAction;

public class GtnUIFrameworkDCDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkCustomerADDAllButtonAction.class.getName(),new GtnUiFrameworkCustomerADDAllButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkItemADDAddAllButtonAction.class.getName(),new GtnUiFrameworkCustomerADDAllButtonAction());
	}

}
