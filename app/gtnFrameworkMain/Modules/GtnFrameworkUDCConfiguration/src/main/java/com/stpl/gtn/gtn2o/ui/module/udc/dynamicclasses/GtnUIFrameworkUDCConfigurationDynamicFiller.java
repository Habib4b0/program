package com.stpl.gtn.gtn2o.ui.module.udc.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.udc.action.GtnFrameworkUdcAddAction;
import com.stpl.gtn.gtn2o.ui.module.udc.action.GtnFrameworkUdcDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.udc.action.GtnFrameworkUdcSearchAction;

public class GtnUIFrameworkUDCConfigurationDynamicFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUdcSearchAction.class.getName(),
				new GtnFrameworkUdcSearchAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUdcAddAction.class.getName(),
				new GtnFrameworkUdcAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUdcDeleteAction.class.getName(),
				new GtnFrameworkUdcDeleteAction());
	}

}
