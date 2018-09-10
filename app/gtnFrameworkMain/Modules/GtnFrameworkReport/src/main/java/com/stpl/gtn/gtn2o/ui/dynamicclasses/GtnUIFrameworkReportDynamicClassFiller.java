package com.stpl.gtn.gtn2o.ui.dynamicclasses;

import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableFillCountDataAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetBulkDataAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetCountAction;
import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkReturnCheckAllAction;
import com.stpl.gtn.gtn2o.ui.action.ComparisonProjectionResultFilterAction;
import com.stpl.gtn.gtn2o.ui.action.ForecastEligibilityDateValueChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkCVSaveValidationAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkComparisonLookupTextFieldEnableAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkConfirmSaveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkCustomTreeConfirmedSaveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkDataAssumptionFilterAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkLoadFromInDataSelectionAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkLoadToInDataSelectionAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkPublicViewDeleteValidation;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportConfirmedCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportCustomViewConfirmDeleteAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportCustomViewDeleteAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportCustomViewEditAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportDashBoardRightHeaderRequestAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportDataSelectionRegenerateConfirmationAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportOptionsViewOptionsAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportResetAndCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportSearchNotification;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportingDashboardConfirmUpdateProfileAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportingDashboardSaveProfileAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkSelectButtonEnableActionInHierarchyLookup;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIBuildCustomTreeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeSaveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariableGridLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariablePositionChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomViewEditAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomViewHierarchyLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportCustomViewReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportDasboardTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportGenerateRequestAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIUOMLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnProductLevelAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportCCPTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionBeforeCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionResultsLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionSubmitAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDashboardFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDashboardValuesResetAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataAssumptionsTabLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionDeleteViewAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionLoadViewAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionReGenerateAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionResetAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionViewAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionViewUpdateAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportFilterReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportForecastEligibleDateReloadInReportingDashboardAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportLevelFilterReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportReportProfileDeleteAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportVariableReloadInReportingDashboardAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportingDashboardReportProfileLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportingDashboardSaveProfileAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportingDashboardUpdateProfileAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkReportConfirmedDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkReportFilterGenerateLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkReportLevelDdlbLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkSaveViewAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIReportExpandCollapseAction;
import com.stpl.gtn.gtn2o.ui.action.chart.GtnFrameworkGridToBarChartAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnFrameworkReportTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnRelationshipVersionLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportComparisonEnableAddBtnAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportDashboardComparisonResultsSearchAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownGridLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownGridResetAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownHeaderLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownMassUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownSubmitAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownGridLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownGridLoadActionBasedOnHistory;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownGridResetAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownHeaderLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownHistoryLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownMassUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownSubmitAction;

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
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCVSaveValidationAction.class.getName(),
				new GtnFrameworkCVSaveValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfirmSaveAction.class.getName(),
				new GtnFrameworkConfirmSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportCustomViewDeleteAction.class.getName(),
				new GtnFrameworkReportCustomViewDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportCustomViewEditAction.class.getName(),
				new GtnFrameworkReportCustomViewEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataAssumptionsTabLoadAction.class.getName(),
				new GtnReportDataAssumptionsTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionViewAddAction.class.getName(),
				new GtnReportDataSelectionViewAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionLoadViewAction.class.getName(),
				new GtnReportDataSelectionLoadViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionDeleteViewAction.class.getName(),
				new GtnReportDataSelectionDeleteViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportComparisonProjectionAddAction.class.getName(),
				new GtnReportComparisonProjectionAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportComparisonProjectionSubmitAction.class.getName(),
				new GtnReportComparisonProjectionSubmitAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportComparisonProjectionRemoveAction.class.getName(),
				new GtnReportComparisonProjectionRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportComparisonProjectionResultsLoadAction.class.getName(),
				new GtnReportComparisonProjectionResultsLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportForecastLevelLoadAction.class.getName(),
				new GtnReportForecastLevelLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownGridLoadAction.class.getName(),
				new GtnReportingVariableBreakdownGridLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingComparisonBreakdownGridLoadAction.class.getName(),
				new GtnReportingComparisonBreakdownGridLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction.class.getName(),
				new GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingComparisonBreakdownHeaderLoadAction.class.getName(),
				new GtnReportingComparisonBreakdownHeaderLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingComparisonBreakdownGridResetAction.class.getName(),
				new GtnReportingComparisonBreakdownGridResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingComparisonBreakdownMassUpdateAction.class.getName(),
				new GtnReportingComparisonBreakdownMassUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingComparisonBreakdownSubmitAction.class.getName(),
				new GtnReportingComparisonBreakdownSubmitAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownHeaderLoadAction.class.getName(),
				new GtnReportingVariableBreakdownHeaderLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReportDashBoardRightHeaderRequestAction.class.getName(),
				new GtnFrameworkReportDashBoardRightHeaderRequestAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportFilterReloadAction.class.getName(),
				new GtnReportFilterReloadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDashboardFrequencyLoadAction.class.getName(),
				new GtnReportDashboardFrequencyLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportLevelFilterReloadAction.class.getName(),
				new GtnReportLevelFilterReloadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownMassUpdateAction.class.getName(),
				new GtnReportingVariableBreakdownMassUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownSubmitAction.class.getName(),
				new GtnReportingVariableBreakdownSubmitAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownGridResetAction.class.getName(),
				new GtnReportingVariableBreakdownGridResetAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUIReportGenerateRequestAction.class.getName(),
				new GtnFrameworkUIReportGenerateRequestAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkReportFilterGenerateLoadAction.class.getName(),
				new GtnUIFrameworkReportFilterGenerateLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownFrequencyLoadAction.class.getName(),
				new GtnReportingVariableBreakdownFrequencyLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkReportLevelDdlbLoadAction.class.getName(),
				new GtnUIFrameworkReportLevelDdlbLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIReportExpandCollapseAction.class.getName(),
				new GtnUIReportExpandCollapseAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUIReportCustomViewReloadAction.class.getName(),
				new GtnFrameworkUIReportCustomViewReloadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingVariableBreakdownHistoryLoadAction.class.getName(),
				new GtnReportingVariableBreakdownHistoryLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUIUOMLoadAction.class.getName(),
				new GtnFrameworkUIUOMLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportTabChangeAction.class.getName(),
				new GtnFrameworkReportTabChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportVariableReloadInReportingDashboardAction.class.getName(),
				new GtnReportVariableReloadInReportingDashboardAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnReportForecastEligibleDateReloadInReportingDashboardAction.class.getName(),
				new GtnReportForecastEligibleDateReloadInReportingDashboardAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportingDashboardSaveProfileAction.class.getName(),
				new GtnFrameworkReportingDashboardSaveProfileAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingDashboardSaveProfileAddAction.class.getName(),
				new GtnReportingDashboardSaveProfileAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingDashboardReportProfileLoadAction.class.getName(),
				new GtnReportingDashboardReportProfileLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportReportProfileDeleteAction.class.getName(),
				new GtnReportReportProfileDeleteAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingDashboardUpdateProfileAddAction.class.getName(),
				new GtnReportingDashboardUpdateProfileAddAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkLoadFromInDataSelectionAction.class.getName(),
				new GtnFrameworkLoadFromInDataSelectionAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkLoadToInDataSelectionAction.class.getName(),
				new GtnFrameworkLoadToInDataSelectionAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDashboardComparisonResultsSearchAction.class.getName(),
				new GtnReportDashboardComparisonResultsSearchAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionReGenerateAction.class.getName(),
				new GtnReportDataSelectionReGenerateAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportingDashboardSaveProfileAction.class.getName(),
				new GtnFrameworkReportingDashboardSaveProfileAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingDashboardSaveProfileAddAction.class.getName(),
				new GtnReportingDashboardSaveProfileAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportingDashboardReportProfileLoadAction.class.getName(),
				new GtnReportingDashboardReportProfileLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportReportProfileDeleteAction.class.getName(),
				new GtnReportReportProfileDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDataSelectionViewUpdateAction.class.getName(),
				new GtnReportDataSelectionViewUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkReportConfirmedDeleteButtonAction.class.getName(),
				new GtnUIFrameworkReportConfirmedDeleteButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName(),
				new GtnFrameworkSelectButtonEnableActionInHierarchyLookup());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportDashboardValuesResetAction.class.getName(),
				new GtnReportDashboardValuesResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportComparisonEnableAddBtnAction.class.getName(),
				new GtnReportComparisonEnableAddBtnAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReportingDashboardConfirmUpdateProfileAction.class.getName(),
				new GtnFrameworkReportingDashboardConfirmUpdateProfileAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportResetAndCloseAction.class.getName(),
				new GtnFrameworkReportResetAndCloseAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReportDataSelectionRegenerateConfirmationAction.class.getName(),
				new GtnFrameworkReportDataSelectionRegenerateConfirmationAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportConfirmedCloseAction.class.getName(),
				new GtnFrameworkReportConfirmedCloseAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkComparisonLookupTextFieldEnableAction.class.getName(),
				new GtnFrameworkComparisonLookupTextFieldEnableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportCustomViewConfirmDeleteAction.class.getName(),
				new GtnFrameworkReportCustomViewConfirmDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnReportingVariableBreakdownGridLoadActionBasedOnHistory.class.getName(),
				new GtnReportingVariableBreakdownGridLoadActionBasedOnHistory());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkGridToBarChartAction.class.getName(),
				new GtnFrameworkGridToBarChartAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPublicViewDeleteValidation.class.getName(),
				new GtnFrameworkPublicViewDeleteValidation());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportOptionsViewOptionsAction.class.getName(),
				new GtnFrameworkReportOptionsViewOptionsAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReportSearchNotification.class.getName(),
				new GtnFrameworkReportSearchNotification());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(ForecastEligibilityDateValueChangeAction.class.getName(),
				new ForecastEligibilityDateValueChangeAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(ComparisonProjectionResultFilterAction.class.getName(),
				new ComparisonProjectionResultFilterAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnReportComparisonProjectionBeforeCloseAction.class.getName(),
				new GtnReportComparisonProjectionBeforeCloseAction());
                GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDataAssumptionFilterAction.class.getName(),
				new GtnFrameworkDataAssumptionFilterAction());

	}

}