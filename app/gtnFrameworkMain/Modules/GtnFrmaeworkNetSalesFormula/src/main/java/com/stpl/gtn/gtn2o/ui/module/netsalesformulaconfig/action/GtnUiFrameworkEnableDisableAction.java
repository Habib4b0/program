/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import static com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants.NET_SALES_RULE_NAME;
import static com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants.NET_SALES_RULE_NO;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.Arrays;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkEnableDisableAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private String formulaType;

	public GtnUiFrameworkEnableDisableAction() {
		super();
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		boolean isEnable = (Boolean) actionParemeterList.get(2);
		boolean isAdd = (Boolean) actionParemeterList.get(3);
		List<String> componentIds = new ArrayList<>(50);
		componentIds.add(viewId + "formulaInformationPanel");
		componentIds.add(viewId + "saveButtonLayout");
		componentIds.add(viewId + "salesBasisSearchButton");
		componentIds.add(viewId + "displayButtonlayout");
		componentIds.add(viewId + "addButtonlayout");
		componentIds.add(viewId + "salesBasisOptionMainLayout");
		componentIds.add(viewId + "searchCriteriaPanel");
		componentIds.add(viewId + "searchButtonlayout");
		componentIds.add(viewId + "massUpdatePanel");
		componentIds.add(viewId + "selectedCustomerTableResetButton");
		componentIds.add(viewId + "selectedCustomerTableRemoveButton");
		componentIds.add(viewId + "deductionsTabSearchCriteriaPanel");
		componentIds.add(viewId + "selectedDeductionsmassUpdatePanel");
		componentIds.add(viewId + "deductionTabsearchButtonlayout");
		componentIds.add(viewId + "availableDeductionButtonlayout");
		componentIds.add(viewId + "selectedDeductionsTableResetButton");
		componentIds.add(viewId + "selectedDeductionsTableRemoveButton");
		componentIds.add(viewId + "formulaType");
		enableDisableComponents(componentIds, isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "availableContractTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "availableCustomersTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedCustomersResultTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + GtnFrameworkNSFConstants.SELECTED_DEDUCTION_RESULT_TABLE)
				.getExtFilterTable().setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "availableDeductionsTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		if (!isAdd) {
			formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaType")
					.getCaptionFromComboBox();
			String[] selectedDeductionHeader = getSelectedDeductionHeaderArray(isEnable, viewId);
			Object[] selectedDeductionColumn = getSelectedDeductionColumnArray(isEnable, viewId);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(viewId + GtnFrameworkNSFConstants.SELECTED_DEDUCTION_RESULT_TABLE)
					.setTableColumns(selectedDeductionColumn);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(viewId + GtnFrameworkNSFConstants.SELECTED_DEDUCTION_RESULT_TABLE)
					.setTableColumnHeaders(selectedDeductionHeader);
		}
		if (isAdd) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "saveButton").setCaption("SAVE");
		}
	}

	private String[] getSelectedDeductionHeaderArray(boolean isEnable, String viewId) {

		if (isEnable) {
			return getSelectedFormulaTypeHeaderArray();

		} else {
			String[] editHeaders = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(viewId + "selectedDeductionsResultTable").getExtCustomTable()
					.getColumnHeaders();
			return Arrays.copyOfRange(editHeaders, 1, editHeaders.length);
		}

	}

	private Object[] getSelectedDeductionColumnArray(boolean isEnable, String viewId) {
		if (isEnable) {
			return getSelectedFormulaTypeColumnArray();
		} else {
			Object[] editColumns = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(viewId + "selectedDeductionsResultTable").getExtCustomTable()
					.getVisibleColumns();
			return Arrays.copyOfRange(editColumns, 1, editColumns.length);
		}

	}

	private String[] getSelectedFormulaTypeHeaderArray() {

		if (formulaType.equals(GtnFrameworkNSFConstants.getFormulaTypeContract())) {

			return new String[] { "", GtnFrameworkCommonConstants.CONTRACT_NO_HEADER,
					GtnFrameworkCommonConstants.CONTRACT_NAME_HEADER, GtnFrameworkCommonConstants.DEDUCTION_NO,
					GtnFrameworkCommonConstants.DEDUCTION_NAME, GtnFrameworkCommonConstants.DEDUCTION_TYPE,
					GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE,
					GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_HEADER,
					GtnFrameworkCommonConstants.MARKET_TYPE_HEADER, "Start Date", "End Date",
					GtnFrameworkCommonConstants.CONTRACT_HOLDER_HEADER,
					GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO_HEADER,
					GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME_HEADER,
					GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_NO,
					GtnFrameworkCommonConstants.HEADER_ITEM_FAMILY_PLAN_NAME,
					GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_HEADER,
					GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_HEADER, GtnFrameworkNSFConstants.INDICATOR_HEADER,
					NET_SALES_RULE_NO, NET_SALES_RULE_NAME };

		} else {
			return new String[] { "", GtnFrameworkCommonConstants.DEDUCTION_TYPE,
					GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE,
					GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_HEADER, GtnFrameworkNSFConstants.INDICATOR_HEADER,
					GtnFrameworkNSFConstants.NET_SALES_RULE_NO, GtnFrameworkNSFConstants.NET_SALES_RULE_NAME };
		}
	}

	private Object[] getSelectedFormulaTypeColumnArray() {

		if (formulaType.equals(GtnFrameworkNSFConstants.getFormulaTypeContract())) {
			return new String[] { GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.CONTRACT_NO,
					GtnFrameworkCommonConstants.CONTRACT_NAME, "deductionNo", "deductionName",
					GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_TYPE,
					GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE_HEADER,
					GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_PROPERTY, GtnFrameworkCommonConstants.MARKET_TYPE,
					"startDate", "endDate", GtnFrameworkCommonConstants.CONTRACT_HOLDER,
					GtnFrameworkCommonConstants.CFP_NO, GtnFrameworkCommonConstants.CFP_NAME,
					GtnFrameworkCommonConstants.IFP_NUMBER, GtnFrameworkCommonConstants.IFP_NAME, "psNo",
					GtnFrameworkCommonConstants.PS_NAME, "indicator", GtnFrameworkCommonConstants.RULE_NO,
					GtnFrameworkCommonConstants.RULE_NAME };
		} else {
			return new String[] { GtnFrameworkCommonConstants.CHECK_RECORD_ID,
					GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_TYPE,
					GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE_HEADER,
					GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_PROPERTY, "indicator", "netSalesRuleNo",
					"netSalesRuleName" };
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void enableDisableComponents(List<String> componentIds, boolean isEnable) {
		for (String componentId : componentIds) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).setEnable(isEnable);
		}
	}
}
