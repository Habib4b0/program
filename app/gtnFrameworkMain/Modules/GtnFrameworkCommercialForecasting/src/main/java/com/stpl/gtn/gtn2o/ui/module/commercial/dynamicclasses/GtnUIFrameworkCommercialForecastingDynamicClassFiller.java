package com.stpl.gtn.gtn2o.ui.module.commercial.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.commercial.action.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.module.commercial.action.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.module.commercial.action.GtnFrameworkResetRelationValuesAction;
import com.stpl.gtn.gtn2o.ui.module.commercial.action.duallistbox.GtnUIFrameWorkLoadDualListBoxAction;
import com.stpl.gtn.gtn2o.ui.module.commercial.action.duallistbox.GtnUIFrameworkCommercialDualListBoxConfigAction;

public class GtnUIFrameworkCommercialForecastingDynamicClassFiller implements GtnUIDynamicObjectFiller{

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCommercialDualListBoxConfigAction.class.getName(),new GtnUIFrameworkCommercialDualListBoxConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkLoadDualListBoxAction.class.getName(),new GtnUIFrameWorkLoadDualListBoxAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction.class.getName(),new GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class.getName(),new GtnFrameworkConfigureRightTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkResetRelationValuesAction.class.getName(),new GtnFrameworkResetRelationValuesAction());
		
	}

}
