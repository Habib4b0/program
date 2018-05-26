package com.stpl.gtn.gtn2o.ui.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkCustomTreeConfirmedSaveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIBuildCustomTreeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeSaveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariableGridLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariablePositionChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomViewEditAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomViewHierarchyLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportDasboardTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnProductLevelAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportCCPTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataAssumptionsTabLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionResetAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkSaveViewAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPagedTreeTableFillCountDataAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetBulkDataAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetCountAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkReturnCheckAllAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnRelationshipVersionLoadAction;

public class GtnUIFrameworkReportDynamicClassFiller implements GtnUIDynamicObjectFiller {

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
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnCustomerAvailableTableLoadAction.class.getName(),
				new GtnCustomerAvailableTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnProductLevelAvailableTableLoadAction.class.getName(),
				new GtnProductLevelAvailableTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomViewHierarchyLoadAction.class.getName(),
				new GtnFrameworkUICustomViewHierarchyLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportCCPTableLoadAction.class.getName(),
				new GtnReportCCPTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomTreeAddAction.class.getName(),
				new GtnFrameworkUICustomTreeAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomTreeSaveAction.class.getName(),
				new GtnFrameworkUICustomTreeSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomVariablePositionChangeAction.class.getName(),
				new GtnFrameworkUICustomVariablePositionChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomVariableGridLoadAction.class.getName(),
				new GtnFrameworkUICustomVariableGridLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionTabLoadAction.class.getName(),
				new GtnReportDataSelectionTabLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomTreeRemoveAction.class.getName(),
				new GtnFrameworkUICustomTreeRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUIBuildCustomTreeAction.class.getName(),
				new GtnFrameworkUIBuildCustomTreeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUIReportDasboardTableLoadAction.class.getName(),
				new GtnFrameworkUIReportDasboardTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkSaveViewAction.class.getName(),
				new GtnUIFrameworkSaveViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionResetAction.class.getName(),
				new GtnReportDataSelectionResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnForecastEligibleDateLoadAction.class.getName(),
				new GtnForecastEligibleDateLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCustomTreeConfirmedSaveAction.class.getName(),
				new GtnFrameworkCustomTreeConfirmedSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUICustomViewEditAction.class.getName(),
				new GtnFrameworkUICustomViewEditAction());
                

                GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataAssumptionsTabLoadAction.class.getName(),
				new GtnReportDataAssumptionsTabLoadAction());
	}

}
