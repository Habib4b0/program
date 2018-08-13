package com.stpl.gtn.gtn2o.registry.dynamicclasses;

import com.stpl.gtn.gtn2o.registry.action.GtnLandingScreenFromAndToPeriodLoadAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableFillCountDataAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetBulkDataAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetCountAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkReturnCheckAllAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnRelationshipVersionLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction.class.getName(),
				new GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class.getName(),
				new GtnFrameworkConfigureRightTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableFillCountDataAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableFillCountDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableGetBulkDataAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableGetBulkDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableGetCountAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableGetCountAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction.class.getName(),
				new GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction.class.getName(),
				new GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnCheckAllAction.class.getName(),
				new GtnFrameworkReturnCheckAllAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnRelationshipVersionLoadAction.class.getName(),
				new GtnRelationshipVersionLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastLevelLoadAction.class.getName(),
				new GtnForecastLevelLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastEligibleDateLoadAction.class.getName(),
				new GtnForecastEligibleDateLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnLandingScreenFromAndToPeriodLoadAction.class.getName(),
				new GtnLandingScreenFromAndToPeriodLoadAction());
		
	}

}
