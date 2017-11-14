/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfFormulaTypeChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaType")
				.getCaptionFromComboBox();
		List<String> componentIds = new ArrayList<>();
		componentIds.add(viewId + "deductionsTabcontractNo");
		componentIds.add(viewId + "deductionsTabcontractName");
		componentIds.add(viewId + "deductionsTabcontractHolder");
		componentIds.add(viewId + "deductionsTabmarketType");
		componentIds.add(viewId + "deductionsTabcfpNo");
		componentIds.add(viewId + "deductionsTabifpNo");
		componentIds.add(viewId + "deductionsTabPsNumber");
		componentIds.add(viewId + "deductionsTabDeductionNumber");
		componentIds.add(viewId + "deductionsTabcfpName");
		componentIds.add(viewId + "deductionsTabifpName");
		componentIds.add(viewId + "deductionsTabPsName");
		componentIds.add(viewId + "deductionsTabDeductionName");
		componentIds.add(viewId + "deductionsTabDeductionAlias");
		Class<?>[] tableColumnDataTypes = null;
		String[] availableDeductionVisibleHeaders = null;
		Object[] availableDeductionVisibleColumns = null;
		Object[] availableDeductionExtraColumns = null;
		Class<?>[] availableDeductionExtraColumnsDataTypes = null;
		String availableDeductionQueryName;

		Class<?>[] selectedDeductionTableColumnDataTypes = null;
		String[] selectedDeductionVisibleHeaders = null;
		Object[] selectedDeductionVisibleColumns = null;
		Object[] selectedDeductionExtraColumns = null;
		Class<?>[] selectedDeductionExtraColumnsDataTypes = null;
		String selectedDeductionQueryName;
		if (formulaType != null && formulaType.equals(GtnFrameworkNSFConstants.getFormulaTypeContract())) {
			enableDisableComponents(componentIds, true);
			tableColumnDataTypes = new Class<?>[] { String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class, Date.class, Date.class, String.class,
					String.class, String.class, String.class, String.class, String.class, String.class };
			availableDeductionVisibleHeaders = GtnFrameworkNSFConstants
					.getAvailableDeductionsHeadersFormulaTypeContract();
			availableDeductionVisibleColumns = GtnFrameworkNSFConstants
					.getAvailableDeductionsColumnsFormulaTypeContract();
			availableDeductionExtraColumns = new Object[] { GtnFrameworkNSFConstants.getDeductionType(),
					GtnFrameworkNSFConstants.getDeductionSubType(), GtnFrameworkNSFConstants.getDeductionCategory(),
					"contractSystemId", "rsContractSystemId" };
			availableDeductionExtraColumnsDataTypes = new Class<?>[] { String.class, String.class, String.class,
					Integer.class, Integer.class };
			availableDeductionQueryName = GtnWsNsfCommonConstants.GTN_NSF_FORMULA_TYPE_CONTRACT_SEARCH_QUERY;

			selectedDeductionTableColumnDataTypes = new Class<?>[] { Boolean.class, String.class, String.class,
					String.class, String.class, String.class, String.class, String.class, String.class, Date.class,
					Date.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class };
			selectedDeductionVisibleHeaders = GtnFrameworkNSFConstants
					.getSelectedDeductionsHeadersFormulaTypeContract();
			selectedDeductionVisibleColumns = GtnFrameworkNSFConstants
					.getSelectedDeductionsColumnsFormulaTypeContract();
			selectedDeductionExtraColumns = new Object[] { GtnFrameworkNSFConstants.getSystemid() };
			selectedDeductionExtraColumnsDataTypes = new Class<?>[] { Integer.class };
			selectedDeductionQueryName = GtnWsNsfCommonConstants.GTN_NSF_FORMULA_TYPE_CONTRACT_SELECTED_DEDUCTION_QUERY;

		} else {
			enableDisableComponents(componentIds, false);
			tableColumnDataTypes = new Class<?>[] { String.class, String.class, String.class };
			availableDeductionVisibleHeaders = GtnFrameworkNSFConstants.getAvailableDeductionsVisibleHeaders();
			availableDeductionVisibleColumns = GtnFrameworkNSFConstants.getAvailableDeductionsVisibleColumns();
			availableDeductionExtraColumns = new Object[] { GtnFrameworkNSFConstants.getDeductionType(),
					GtnFrameworkNSFConstants.getDeductionSubType(), GtnFrameworkNSFConstants.getDeductionCategory(),
					 };
			availableDeductionExtraColumnsDataTypes = new Class<?>[] { Integer.class, Integer.class, Integer.class,
					 };
			availableDeductionQueryName = GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_DEDUCTIONS_SEARCH_QUERY_NAME;

			selectedDeductionTableColumnDataTypes = new Class<?>[] { Boolean.class, String.class, String.class,
					String.class, String.class, String.class, String.class };
			selectedDeductionVisibleHeaders = GtnFrameworkNSFConstants.getSelectedDeductionsVisibleHeaders();
			selectedDeductionVisibleColumns = GtnFrameworkNSFConstants.getSelectedDeductionsVisibleColumns();
			selectedDeductionExtraColumns = new Object[] { GtnFrameworkNSFConstants.getSystemid() };
			selectedDeductionExtraColumnsDataTypes = new Class<?>[] { Integer.class };
			selectedDeductionQueryName = GtnWsNsfCommonConstants.GTN_NSF_SELECTED_DEDUCTIONS_SEARCH_QUERY_NAME;

		}
		replaceAvailableDeductionTableForContract(viewId, tableColumnDataTypes, availableDeductionVisibleHeaders,
				availableDeductionVisibleColumns, availableDeductionExtraColumns,
				availableDeductionExtraColumnsDataTypes, availableDeductionQueryName);
		replaceSelectedDeductionTableForContract(viewId, selectedDeductionTableColumnDataTypes,
				selectedDeductionVisibleHeaders, selectedDeductionVisibleColumns, selectedDeductionExtraColumns,
				selectedDeductionExtraColumnsDataTypes, selectedDeductionQueryName);

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

	private void replaceAvailableDeductionTableForContract(String viewId, Class<?>[] tableColumnDataTypes,
			String[] visibleHeaders, Object[] visibleColumns, Object[] extraColumns, Class<?>[] extraColumnsDataTypes,
			String queryName) throws GtnFrameworkGeneralException {
		String tableComponentId = viewId + "availableDeductionsTable";
		GtnUIFrameworkComponentConfig searchResultConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId).getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig searchResults = searchResultConfig.getGtnPagedTableConfig();
		searchResults.setTableColumnDataType(tableColumnDataTypes);
		searchResults.setTableVisibleHeader(visibleHeaders);
		searchResults.setTableColumnMappingId(visibleColumns);
		searchResults.setExtraColumn(extraColumns);
		searchResults.setExtraColumnDataType(extraColumnsDataTypes);
		searchResults.setQueryName(queryName);
		GtnUIFrameworkGlobalUI.addChildComponent(tableComponentId + "Layout", Arrays.asList(searchResultConfig));

	}

	private void replaceSelectedDeductionTableForContract(String viewId, Class<?>[] tableColumnDataTypes,
			String[] visibleHeaders, Object[] visibleColumns, Object[] extraColumns, Class<?>[] extraColumnsDataTypes,
			String queryName) throws GtnFrameworkGeneralException {
		String tableComponentId = viewId + "selectedDeductionsResultTable";
		GtnUIFrameworkComponentConfig selectedDeductionTableConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId).getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig selectedDeductionsTable = selectedDeductionTableConfig.getGtnPagedTableConfig();
		selectedDeductionsTable.setTableColumnDataType(tableColumnDataTypes);
		selectedDeductionsTable.setTableVisibleHeader(visibleHeaders);
		selectedDeductionsTable.setTableColumnMappingId(visibleColumns);
		selectedDeductionsTable.setExtraColumn(extraColumns);
		selectedDeductionsTable.setExtraColumnDataType(extraColumnsDataTypes);
		selectedDeductionsTable.setQueryName(queryName);
		GtnUIFrameworkGlobalUI.addChildComponent(tableComponentId + "Layout", Arrays.asList(selectedDeductionTableConfig));

	}

}
