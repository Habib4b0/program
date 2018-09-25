package com.stpl.gtn.gtn2o.registry.dynamicclasses;

import com.stpl.gtn.gtn2o.registry.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionRelationshipLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnForecastingDataSelectionLoadViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnForecastingProductAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkDeleteViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastDataSelectionViewAddAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastDataSelectionViewUpdateAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastEditAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastInnerLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkNewToOldArchitectureDeleteAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkNewToOldArchitectureGenerateAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkSaveViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkScreenRegistryResetAction;
import com.stpl.gtn.gtn2o.registry.action.GtnLandingScreenFromAndToPeriodLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnModeOptionValueChangeAction;
import com.stpl.gtn.gtn2o.registry.action.GtnSelectButtonEnableAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableFillCountDataAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetBulkDataAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetCountAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkReturnCheckAllAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnFrameworkForecastCustomViewLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class.getName(),
				new GtnFrameworkConfigureRightTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableGetCountAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableGetCountAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction.class.getName(),
				new GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction.class.getName(),
				new GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableGetBulkDataAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableGetBulkDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnCheckAllAction.class.getName(),
				new GtnFrameworkReturnCheckAllAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableFillCountDataAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableFillCountDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction.class.getName(),
				new GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnRelationshipVersionLoadAction.class.getName(),
				new GtnRelationshipVersionLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastLevelLoadAction.class.getName(),
				new GtnForecastLevelLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastEligibleDateLoadAction.class.getName(),
				new GtnForecastEligibleDateLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnLandingScreenFromAndToPeriodLoadAction.class.getName(),
				new GtnLandingScreenFromAndToPeriodLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnCustomerSelectionRelationshipLoadAction.class.getName(),
				new GtnCustomerSelectionRelationshipLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnCustomerSelectionForecastLevelLoadAction.class.getName(),
				new GtnCustomerSelectionForecastLevelLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastInnerLevelLoadAction.class.getName(),
				new GtnFrameworkForecastInnerLevelLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnCustomerAvailableTableLoadAction.class.getName(),
				new GtnCustomerAvailableTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastingProductAvailableTableLoadAction.class.getName(),
				new GtnForecastingProductAvailableTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkNewToOldArchitectureGenerateAction.class.getName(),
				new GtnFrameworkNewToOldArchitectureGenerateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnSelectButtonEnableAction.class.getName(),
				new GtnSelectButtonEnableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnModeOptionValueChangeAction.class.getName(),
				new GtnModeOptionValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastingDataSelectionLoadViewAction.class.getName(),
				new GtnForecastingDataSelectionLoadViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSaveViewAction.class.getName(),
				new GtnFrameworkSaveViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastDataSelectionViewAddAction.class.getName(),
				new GtnFrameworkForecastDataSelectionViewAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastDataSelectionViewUpdateAction.class.getName(),
				new GtnFrameworkForecastDataSelectionViewUpdateAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkScreenRegistryResetAction.class.getName(),
				new GtnFrameworkScreenRegistryResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnModeOptionValueChangeAction.class.getName(),
				new GtnModeOptionValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeleteViewAction.class.getName(),
				new GtnFrameworkDeleteViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastEditAction.class.getName(),
				new GtnFrameworkForecastEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastCustomViewLoadAction.class.getName(),
				new GtnFrameworkForecastCustomViewLoadAction());
                GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkNewToOldArchitectureDeleteAction.class.getName(),
                                new GtnFrameworkNewToOldArchitectureDeleteAction());
	}

}
