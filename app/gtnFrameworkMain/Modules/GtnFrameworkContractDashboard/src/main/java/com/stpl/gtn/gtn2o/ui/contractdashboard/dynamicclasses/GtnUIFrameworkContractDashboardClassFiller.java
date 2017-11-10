package com.stpl.gtn.gtn2o.ui.contractdashboard.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAddToTreeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAddToTreeCommonAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAddToTreeMainAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAliasAddAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAliasConfirmAddAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAliasRemoveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkChildDetectAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyChangeIndicatorAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyConfirmPopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyPopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractPriceProtectionTabAddLineAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkDropToTreeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkErrorLabelAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkItemChangeIndicatorAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkNSRPopupLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkPricingTableExcelExportAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkProcessTreeExcelExportAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRebatePopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRebateTableLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRemoveFromTreeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRemoveFromTreeConfirmAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkTreeConfirmSaveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkTreeSaveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkUpdateSelectedRecordInTempTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkValidateContractToProcessAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkValidateContractToRebuildAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkWorkflowInboxRefreshAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameWorkContractTableRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkBasePriceValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkCompaniesTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkCompanyItemAdditionSearchAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentResetAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentSearchAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkFieldEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkInfoTabLevelValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkInfoTabViewValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkItemsTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkLoadTableFromAnTableValueAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPopulateFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPriceProtectionTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPricingTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkRebateTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkSearchFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkWrapLoadTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.duallistboxaction.GtnFrameworkCompanyAdditionTableRecordMoveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.duallistboxaction.GtnFrameworkItemAdditionTableRecordMoveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryComponentCreationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryValueUpdateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnUIFrameworkFocusAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkContractDashBoardConfirmSubmitAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkContractDashBoardSubmitAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkContractRebuildAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkContractWorkflowButtonAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkProcessAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkBackButtonAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkCloseButtonAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkContractWorkflowUpdateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkPermissionCheckAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkAliasTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkCompaniesTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkCompanyAdditionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkContractInformationTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkItemAdditionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkItemsTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkPricingTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkRebateTabLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkContractDashboardClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemAdditionTableRecordMoveAction.class.getName(),
				new GtnFrameworkItemAdditionTableRecordMoveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyAdditionTableRecordMoveAction.class.getName(),
				new GtnFrameworkCompanyAdditionTableRecordMoveAction());

		loadFieldFactoryActions();
		loadProcessActions();
		loadTabLoadActions();
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddToTreeAction.class.getName(),
				new GtnFrameworkAddToTreeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddToTreeCommonAction.class.getName(),
				new GtnFrameworkAddToTreeCommonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddToTreeMainAction.class.getName(),
				new GtnFrameworkAddToTreeMainAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAliasAddAction.class.getName(),
				new GtnFrameworkAliasAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAliasConfirmAddAction.class.getName(),
				new GtnFrameworkAliasConfirmAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAliasRemoveAction.class.getName(),
				new GtnFrameworkAliasRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUpdateSelectedRecordInTempTableAction.class.getName(),
				new GtnFrameworkUpdateSelectedRecordInTempTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkChildDetectAction.class.getName(),
				new GtnFrameworkChildDetectAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyChangeIndicatorAction.class.getName(),
				new GtnFrameworkCompanyChangeIndicatorAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyConfirmPopulateAction.class.getName(),
				new GtnFrameworkCompanyConfirmPopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyPopulateAction.class.getName(),
				new GtnFrameworkCompanyPopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkContractDateValidationAction.class.getName(),
				new GtnFrameworkContractDateValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDropToTreeAction.class.getName(),
				new GtnFrameworkDropToTreeAction());

		loadCommonActions();

	}

	private void loadFieldFactoryActions() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFieldFactoryAction.class.getName(),
				new GtnFrameworkFieldFactoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFieldFactoryComponentCreationAction.class.getName(),
				new GtnFrameworkFieldFactoryComponentCreationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFieldFactoryValueUpdateAction.class.getName(),
				new GtnFrameworkFieldFactoryValueUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTableCheckAllAction.class.getName(),
				new GtnFrameworkTableCheckAllAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkFocusAction.class.getName(),
				new GtnUIFrameworkFocusAction());
	}

	private void loadProcessActions() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkContractDashBoardConfirmSubmitAction.class.getName(),
				new GtnFrameworkContractDashBoardConfirmSubmitAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkContractDashBoardSubmitAction.class.getName(),
				new GtnFrameworkContractDashBoardSubmitAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkContractRebuildAction.class.getName(),
				new GtnFrameworkContractRebuildAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkContractWorkflowButtonAction.class.getName(),
				new GtnFrameworkContractWorkflowButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkProcessAction.class.getName(),
				new GtnFrameworkProcessAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkBackButtonAction.class.getName(),
				new GtnUIFrameworkBackButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCloseButtonAction.class.getName(),
				new GtnUIFrameworkCloseButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractWorkflowUpdateAction.class.getName(),
				new GtnUIFrameworkContractWorkflowUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPermissionCheckAction.class.getName(),
				new GtnUIFrameworkPermissionCheckAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkWorkflowInboxRefreshAction.class.getName(),
				new GtnFrameworkWorkflowInboxRefreshAction());

	}

	private void loadTabLoadActions() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAliasTabLoadAction.class.getName(),
				new GtnFrameworkAliasTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompaniesTabLoadAction.class.getName(),
				new GtnFrameworkCompaniesTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyAdditionTabLoadAction.class.getName(),
				new GtnFrameworkCompanyAdditionTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkContractInformationTabLoadAction.class.getName(),
				new GtnFrameworkContractInformationTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemAdditionTabLoadAction.class.getName(),
				new GtnFrameworkItemAdditionTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemsTabLoadAction.class.getName(),
				new GtnFrameworkItemsTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPricingTabLoadAction.class.getName(),
				new GtnFrameworkPricingTabLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebateTabLoadAction.class.getName(),
				new GtnFrameworkRebateTabLoadAction());

	}

	private void loadCommonActions() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkErrorLabelAction.class.getName(),
				new GtnFrameworkErrorLabelAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemChangeIndicatorAction.class.getName(),
				new GtnFrameworkItemChangeIndicatorAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkNSRPopupLoadAction.class.getName(),
				new GtnFrameworkNSRPopupLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPopupSelectAction.class.getName(),
				new GtnFrameworkPopupSelectAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPricingTableExcelExportAction.class.getName(),
				new GtnFrameworkPricingTableExcelExportAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkProcessTreeExcelExportAction.class.getName(),
				new GtnFrameworkProcessTreeExcelExportAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebatePopulateAction.class.getName(),
				new GtnFrameworkRebatePopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebateTableLoadAction.class.getName(),
				new GtnFrameworkRebateTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRemoveFromTreeAction.class.getName(),
				new GtnFrameworkRemoveFromTreeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRemoveFromTreeConfirmAction.class.getName(),
				new GtnFrameworkRemoveFromTreeConfirmAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTreeConfirmSaveAction.class.getName(),
				new GtnFrameworkTreeConfirmSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTreeSaveAction.class.getName(),
				new GtnFrameworkTreeSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkValidateContractToProcessAction.class.getName(),
				new GtnFrameworkValidateContractToProcessAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkValidateContractToRebuildAction.class.getName(),
				new GtnFrameworkValidateContractToRebuildAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkBasePriceValueChangeAction.class.getName(),
				new GtnUIFrameworkBasePriceValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkComponentResetAction.class.getName(),
				new GtnUIFrameworkComponentResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkComponentSearchAction.class.getName(),
				new GtnUIFrameworkComponentSearchAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkComponentTableItemClickAction.class.getName(),
				new GtnUIFrameworkComponentTableItemClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkComponentValueChangeAction.class.getName(),
				new GtnUIFrameworkComponentValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkContractTableRecordTypeAction.class.getName(),
				new GtnUIFrameWorkContractTableRecordTypeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkFieldEnableDisableAction.class.getName(),
				new GtnUIFrameworkFieldEnableDisableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkInfoTabLevelValueChangeAction.class.getName(),
				new GtnUIFrameworkInfoTabLevelValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkInfoTabViewValueChangeAction.class.getName(),
				new GtnUIFrameworkInfoTabViewValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompanyItemAdditionSearchAction.class.getName(),
				new GtnUIFrameworkCompanyItemAdditionSearchAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkLoadTableFromAnTableValueAction.class.getName(),
				new GtnUIFrameworkLoadTableFromAnTableValueAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName(),
				new GtnUIFrameworkPopulateFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkSearchFieldValueChangeAction.class.getName(),
				new GtnUIFrameworkSearchFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkWrapLoadTableAction.class.getName(),
				new GtnUIFrameworkWrapLoadTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompaniesTabTableAction.class.getName(),
				new GtnUIFrameworkCompaniesTabTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkItemsTabTableAction.class.getName(),
				new GtnUIFrameworkItemsTabTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRebateTabTableAction.class.getName(),
				new GtnUIFrameworkRebateTabTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPricingTabTableAction.class.getName(),
				new GtnUIFrameworkPricingTabTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPriceProtectionTabTableAction.class.getName(),
				new GtnUIFrameworkPriceProtectionTabTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkContractPriceProtectionTabAddLineAction.class.getName(),
				new GtnFrameworkContractPriceProtectionTabAddLineAction());

	}

}
