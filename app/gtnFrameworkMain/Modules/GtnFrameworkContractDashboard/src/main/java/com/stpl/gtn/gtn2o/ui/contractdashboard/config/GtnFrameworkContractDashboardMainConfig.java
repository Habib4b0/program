/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAddToTreeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkChildDetectAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkDropToTreeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkProcessTreeExcelExportAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRemoveFromTreeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkTreeSaveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkValidateContractToProcessAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkValidateContractToRebuildAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentResetAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentSearchAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkComponentValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkContractRebuildAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkProcessAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.splitpanel.GtnUIFrameworkSplitPanelConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkTableDragMode;
import com.stpl.gtn.gtn2o.ui.framework.component.tree.GtnUIFrameworkTreeConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractDashboardMainConfig {

	private static final String DUPLICATE_CONTRACT_ID = "Duplicate Contract ID";
	private static final String COMPONENT_NAME = "ComponentName";
	private static final String SELECTED_CONTRACT_ID_IS_ALREADY_EXIST = "Selected Contract ID is already exist";
	private static final String COMPONENT_RESULT_TABLE = "ComponentResultTable";
	private static final String RESULT_TABLE = "resultTable";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getMainView() {
		GtnUIFrameworkViewConfig view = commonConfig.getViewConfig(GtnFrameworkContractDashboardContants.CD_MAIN_VIEW,
				GtnFrameworkContractDashboardContants.CD_MAIN_VIEW, true);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String cdMainTabPrefix) {
		List<GtnUIFrameworkComponentConfig> cdMainComponentList = new ArrayList<>();
		view.setGtnComponentList(cdMainComponentList);
		addContractDashboardMainPanel(cdMainComponentList, cdMainTabPrefix);

	}

	private void addContractDashboardMainPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig
				.getPanelConfig(cdMainTabPrefix + "ContractDashboardMainPanel", false, null);
		cdMainComponentList.add(panel);
		addContractDashboardMainLayout(cdMainComponentList, cdMainTabPrefix, panel.getComponentId());
	}

	private void addContractDashboardMainLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {

		GtnUIFrameworkComponentConfig mainSplitConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "mainSplitPanel", true, parent, GtnUIFrameworkComponentType.HORIZONTAL_SPLIT_PANEL);
		mainSplitConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		mainSplitConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_600);
		mainSplitConfig.addComponentStyle("gtnFramework-horizontal-splitpanel");
		cdMainComponentList.add(mainSplitConfig);

		addComponentSearchPanel(cdMainComponentList, cdMainTabPrefix, mainSplitConfig.getComponentId());
		addTreeVerticalLayout(cdMainComponentList, cdMainTabPrefix, mainSplitConfig.getComponentId());
	}

	private void addComponentSearchPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig
				.getPanelConfig(cdMainTabPrefix + "componentSearchPanel", true, parent);
		panelConfig.setComponentName("Component Search");
		panelConfig.setAuthorizationIncluded(true);
		cdMainComponentList.add(panelConfig);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig.getVerticalLayoutConfig(
				cdMainTabPrefix + "componentSearchLayout", true, cdMainTabPrefix + "componentSearchPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		componentIdList.add(GtnWsContractDashboardContants.LEFT);
		componentIdList.add(cdMainTabPrefix);
		addSearchCriteriaPanel(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addComponentResultPanel(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addComponentDetailsPanel(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addActionButtonLayout(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		componentIdList.add(GtnFrameworkCommonStringConstants.LAYOUT);
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(cdMainTabPrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		cdMainComponentList.add(panelConfig);
		addFieldLayout(cdMainComponentList, cdMainTabPrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdMainTabPrefix + "addToTreeButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(layoutConfig);
		addAddToTreeButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
		addExcelButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId(), "Contract");
	}

	private void addAddToTreeButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig addToTreeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "addToTreeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		addToTreeBtnConfig.setAuthorizationIncluded(true);
		addToTreeBtnConfig.setComponentName("Add To Tree");
		cdMainComponentList.add(addToTreeBtnConfig);
		GtnUIFrameWorkActionConfig addToTreeAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addToTreeAction.addActionParameter(GtnFrameworkAddToTreeAction.class.getName());
		addToTreeAction.addActionParameter(cdMainTabPrefix + COMPONENT_RESULT_TABLE);
		addToTreeAction.addActionParameter(GtnWsContractDashboardContants.CONTRACT_DASHBOARD_TREE);
		addToTreeAction.addActionParameter("Please select Search Result");
		addToTreeAction.addActionParameter("Criteria Mismatch");
		addToTreeAction.addActionParameter("Cannot make a ? as contracts header");
		addToTreeAction.addActionParameter(DUPLICATE_CONTRACT_ID);
		addToTreeAction.addActionParameter(SELECTED_CONTRACT_ID_IS_ALREADY_EXIST);
		addToTreeAction.addActionParameter("Cannot make a ? as child node");
		addToTreeAction.addActionParameter("? cannot be added to ?");
		addToTreeBtnConfig.addGtnUIFrameWorkActionConfig(addToTreeAction);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent, String exportFileName) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "excelBtn", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdMainComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName(exportFileName);
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(cdMainTabPrefix + COMPONENT_RESULT_TABLE);
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdMainTabPrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(layoutConfig);
		addSearchButtonComponent(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		cdMainComponentList.add(searchButtonConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkComponentSearchAction.class.getName());
		customAction.addActionParameter(componentIdList);
		searchButtonConfig.addGtnUIFrameWorkActionConfig(customAction);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		cdMainComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Are you sure you want to reset the page to default/previous values ?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetToDefaultAction = new GtnUIFrameWorkActionConfig();
		resetToDefaultAction.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetToDefaultAction
				.addActionParameter(Arrays.asList(cdMainTabPrefix + COMPONENT_NAME, cdMainTabPrefix + "SearchType"));
		resetToDefaultAction.addActionParameter(
				Arrays.asList(GtnWsContractDashboardContants.CONTRACT, GtnWsContractDashboardContants.SUMMARY));
		successActionConfigList.add(resetToDefaultAction);
		GtnUIFrameWorkActionConfig customResetAction = new GtnUIFrameWorkActionConfig();
		customResetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customResetAction.addActionParameter(GtnUIFrameworkComponentResetAction.class.getName());
		customResetAction.addActionParameter(componentIdList);
		customResetAction.addActionParameter(GtnWsContractDashboardContants.SUMMARY);
		customResetAction.addActionParameter(GtnWsContractDashboardContants.DETAIL);

		successActionConfigList.add(customResetAction);
		confirmActionConfig.addActionParameter(successActionConfigList);
		resetButtonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(cdMainTabPrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cdMainComponentList.add(gtnLayout);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_725);
		addFieldComponent(cdMainComponentList, cdMainTabPrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {

		addComponentName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addSearchType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addContractId(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractStartDate(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractEndDate(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractHolderNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addContractHolderName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addCfpId(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCfpNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCfpName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCfpType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCompanyID(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCompanyNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCompanyName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCompanyType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addCompanyCategory(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addIfpId(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addIfpNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addIfpName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addIfpType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addItemID(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addPSId(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addPSNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addPSName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addPSType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addRSId(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addRSNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addRSName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addRSType(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addItemNo(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addItemName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addTherapeuticClass(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
		addBrandName(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);

		addRSProgramCategory(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
	}

	private void addComponentName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + COMPONENT_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentNameConfig.setAuthorizationIncluded(true);
		componentNameConfig.setComponentName("Component");
		cdMainComponentList.add(componentNameConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setDefaultDesc(GtnWsContractDashboardContants.CONTRACT);
		comboBoxConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.CONTRACT,
				GtnWsContractDashboardContants.COMPANY_FAMILY_PLAN, GtnWsContractDashboardContants.ITEM_FAMILY_PLAN,
				GtnWsContractDashboardContants.PRICE_SCHEDULE, GtnWsContractDashboardContants.REBATE_SCHEDULE));
		comboBoxConfig.setHasDefaultValue(true);
		componentNameConfig.setGtnComboboxConfig(comboBoxConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkComponentValueChangeAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(componentId);
		customAction.addActionParameter(GtnWsContractDashboardContants.SUMMARY);
		customAction.addActionParameter(GtnWsContractDashboardContants.DETAIL);
		componentNameConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(componentNameConfig.getComponentId());
	}

	private void addSearchType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig searchTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		searchTypeConfig.setAuthorizationIncluded(true);
		searchTypeConfig.setComponentName("Search Type");
		searchTypeConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdMainComponentList.add(searchTypeConfig);

		GtnUIFrameworkOptionGroupConfig searchTypeOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		searchTypeOptionConfig.setValuesFromService(false);
		searchTypeOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.SUMMARY, GtnWsContractDashboardContants.DETAIL));
		searchTypeOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.SUMMARY);

		searchTypeConfig.setGtnUIFrameworkOptionGroupConfig(searchTypeOptionConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkComponentValueChangeAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(cdMainTabPrefix + COMPONENT_NAME);
		actionConfigList.add(customAction);
		searchTypeConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentIdList.add(searchTypeConfig.getComponentId());
	}

	private void addContractId(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1S_L1D_R1S_R1D_Text_ContractID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainContractIdConfig.setAuthorizationIncluded(true);
		cdMainContractIdConfig.setComponentName("Contract ID");
		cdMainComponentList.add(cdMainContractIdConfig);
		componentIdList.add(cdMainContractIdConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cdMainContractIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1S_L1D_R1S_R1D_Text_ContractNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainContractNoConfig.setAuthorizationIncluded(true);
		cdMainContractNoConfig.setComponentName("Contract No");
		cdMainComponentList.add(cdMainContractNoConfig);
		componentIdList.add(cdMainContractNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cdMainContractNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1S_L1D_R1S_R1D_Text_ContractName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainContractNameConfig.setAuthorizationIncluded(true);
		cdMainContractNameConfig.setComponentName("Contract Name");
		cdMainComponentList.add(cdMainContractNameConfig);
		componentIdList.add(cdMainContractNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainContractNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1S_L1D_R1S_R1D_Combo_ContractType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainContractTypeConfig.setAuthorizationIncluded(true);
		cdMainContractTypeConfig.setComponentName("Contract Type");
		cdMainComponentList.add(cdMainContractTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CONTRACT_TYPE);
		cdMainContractTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainContractTypeConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainContractTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractStartDate(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1D_R1D_ContractStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		cdMainContractTypeConfig.setAuthorizationIncluded(true);
		cdMainContractTypeConfig.setComponentName("Contract Start Date");
		cdMainComponentList.add(cdMainContractTypeConfig);
		componentIdList.add(cdMainContractTypeConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainContractTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractEndDate(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1D_R1D_ContractEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractEndDateConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		cdMainContractEndDateConfig.setAuthorizationIncluded(true);
		cdMainContractEndDateConfig.setComponentName("Contract End Date");
		cdMainComponentList.add(cdMainContractEndDateConfig);
		componentIdList.add(cdMainContractEndDateConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainContractEndDateConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractHolderNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1D_Text_ContractHolderNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractHolderNoConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainContractHolderNoConfig.setAuthorizationIncluded(true);
		cdMainContractHolderNoConfig.setComponentName("Contract Holder No");
		cdMainComponentList.add(cdMainContractHolderNoConfig);
		componentIdList.add(cdMainContractHolderNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainContractHolderNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractHolderName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L1D_Text_ContractHolderName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainContractNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainContractNameConfig.setAuthorizationIncluded(true);
		cdMainContractNameConfig.setComponentName("Contract Holder Name");
		cdMainComponentList.add(cdMainContractNameConfig);
		componentIdList.add(cdMainContractNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainContractNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCfpId(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2S_L2D_R2S_R2D_R1D_Text_cfpID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCfpIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCfpIdConfig.setAuthorizationIncluded(true);
		cdMainCfpIdConfig.setComponentName("CFP ID");
		cdMainComponentList.add(cdMainCfpIdConfig);
		componentIdList.add(cdMainCfpIdConfig.getComponentId());
		GtnUIFrameworkValidationConfig cdMainCfpIdValConfig = new GtnUIFrameworkValidationConfig();
		cdMainCfpIdValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainCfpIdConfig.setGtnUIFrameworkValidationConfig(cdMainCfpIdValConfig);
	}

	private void addCfpNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2S_L2D_R2S_R2D_R1D_Text_cfpNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCfpNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCfpNoConfig.setAuthorizationIncluded(true);
		cdMainCfpNoConfig.setComponentName("CFP No");
		cdMainComponentList.add(cdMainCfpNoConfig);
		componentIdList.add(cdMainCfpNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig cdMainCfpValConfig = new GtnUIFrameworkValidationConfig();
		cdMainCfpValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainCfpNoConfig.setGtnUIFrameworkValidationConfig(cdMainCfpValConfig);
	}

	private void addCfpName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2S_L2D_R2S_R2D_R1D_Text_cfpName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCfpNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCfpNameConfig.setAuthorizationIncluded(true);
		cdMainCfpNameConfig.setComponentName("CFP Name");
		cdMainComponentList.add(cdMainCfpNameConfig);
		componentIdList.add(cdMainCfpNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainCfpNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCfpType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2S_L2D_R2S_R2D_Combo_cfpType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCfpTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainCfpTypeConfig.setAuthorizationIncluded(true);
		cdMainCfpTypeConfig.setComponentName("CFP Type");
		cdMainComponentList.add(cdMainCfpTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CFP_TYPE);
		cdMainCfpTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainCfpTypeConfig.getComponentId());
		String componentIds = cdMainTabPrefix + "R1D_cfpSupID";
		GtnUIFrameworkComponentConfig gtnsLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentIds + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnsLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnsLayoutConfig);
		componentIdList.add(componentIds);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainCfpTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCompanyID(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "R1D_Text_cfpCompanyID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCompanyIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCompanyIdConfig.setAuthorizationIncluded(true);
		cdMainCompanyIdConfig.setComponentName("Company ID");
		cdMainComponentList.add(cdMainCompanyIdConfig);
		componentIdList.add(cdMainCompanyIdConfig.getComponentId());
		String componentIds = cdMainTabPrefix + "R1D_cfpCompanySupID";
		GtnUIFrameworkComponentConfig gtnsLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentIds + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnsLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnsLayoutConfig);
		componentIdList.add(componentIds);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainCompanyIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2D_R2D_R1D_Text_cfpCompanyNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCompanyNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCompanyNoConfig.setAuthorizationIncluded(true);
		cdMainCompanyNoConfig.setComponentName("Company No");
		cdMainComponentList.add(cdMainCompanyNoConfig);
		componentIdList.add(cdMainCompanyNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainCompanyNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2D_R2D_R1D_Text_cfpCompanyName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCompanyNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCompanyNameConfig.setAuthorizationIncluded(true);
		cdMainCompanyNameConfig.setComponentName("Company Name");
		cdMainComponentList.add(cdMainCompanyNameConfig);
		componentIdList.add(cdMainCompanyNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainCompanyNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2D_R2D_Combo_cfpCompanyType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCompanyTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainCompanyTypeConfig.setAuthorizationIncluded(true);
		cdMainCompanyTypeConfig.setComponentName("Company Type");
		cdMainComponentList.add(cdMainCompanyTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.COMP_TYPE);
		cdMainCompanyTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainCompanyTypeConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainCompanyTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCompanyCategory(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2D_R2D_Text_cfpCompanyCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainCompanyCategoryConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainCompanyCategoryConfig.setAuthorizationIncluded(true);
		cdMainCompanyCategoryConfig.setComponentName("Company Category");
		cdMainComponentList.add(cdMainCompanyCategoryConfig);
		componentIdList.add(cdMainCompanyCategoryConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainCompanyCategoryConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addIfpId(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3S_L3D_R3S_R3D_R1D_Text_ifpID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainIfpIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainIfpIdConfig.setAuthorizationIncluded(true);
		cdMainIfpIdConfig.setComponentName("IFP ID");
		cdMainComponentList.add(cdMainIfpIdConfig);
		componentIdList.add(cdMainIfpIdConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainIfpIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addIfpNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3S_L3D_R3S_R3D_R1D_Text_ifpNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainIfpNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainIfpNoConfig.setAuthorizationIncluded(true);
		cdMainIfpNoConfig.setComponentName("IFP No");
		cdMainComponentList.add(cdMainIfpNoConfig);
		componentIdList.add(cdMainIfpNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainIfpNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addIfpName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3S_L3D_R3S_R3D_R1D_Text_ifpName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainIfpNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainIfpNameConfig.setAuthorizationIncluded(true);
		cdMainIfpNameConfig.setComponentName("IFP Name");
		cdMainComponentList.add(cdMainIfpNameConfig);
		componentIdList.add(cdMainIfpNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainIfpNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addIfpType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3S_L3D_R3S_R3D_Combo_ifpType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainIfpTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainIfpTypeConfig.setAuthorizationIncluded(true);
		cdMainIfpTypeConfig.setComponentName("IFP Type");
		cdMainComponentList.add(cdMainIfpTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.IFP_TYPE);
		cdMainIfpTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainIfpTypeConfig.getComponentId());
		String componentIds = cdMainTabPrefix + "R1D_ifpSupID";
		GtnUIFrameworkComponentConfig gtnsLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentIds + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnsLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnsLayoutConfig);
		componentIdList.add(componentIds);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainIfpTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addItemID(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "R1D_Text_ifpItemID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig addItemIDConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addItemIDConfig.setAuthorizationIncluded(true);
		addItemIDConfig.setComponentName("Item ID");
		cdMainComponentList.add(addItemIDConfig);
		componentIdList.add(addItemIDConfig.getComponentId());
		String componentIds = cdMainTabPrefix + "R1D_ifpItemSupID";
		GtnUIFrameworkComponentConfig gtnsLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentIds + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnsLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnsLayoutConfig);
		componentIdList.add(componentIds);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addItemIDConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig addItemNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addItemNoConfig.setAuthorizationIncluded(true);
		addItemNoConfig.setComponentName("Item No");
		cdMainComponentList.add(addItemNoConfig);
		componentIdList.add(addItemNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addItemNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig addItemNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addItemNameConfig.setAuthorizationIncluded(true);
		addItemNameConfig.setComponentName("Item Name");
		cdMainComponentList.add(addItemNameConfig);
		componentIdList.add(addItemNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addItemNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addTherapeuticClass(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3D_L4D_R3D_R4D_Combo_ifpTherapeuticClass";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainTherapeuticClassConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainTherapeuticClassConfig.setAuthorizationIncluded(true);
		cdMainTherapeuticClassConfig.setComponentName("Therapeutic Class");
		cdMainComponentList.add(cdMainTherapeuticClassConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.THERAPEUTIC_CLASS);
		cdMainTherapeuticClassConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainTherapeuticClassConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainTherapeuticClassConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addBrandName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L3D_L4D_L5D_R3D_R4D_R5D_Combo_ifpBrandName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainBrandNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainBrandNameConfig.setAuthorizationIncluded(true);
		cdMainBrandNameConfig.setComponentName("Brand Name");
		cdMainComponentList.add(cdMainBrandNameConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.BRAND_NAME);
		cdMainBrandNameConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainBrandNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainBrandNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPSId(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L4S_L4D_R4S_R4D_R1D_Text_psID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainPSIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainPSIdConfig.setAuthorizationIncluded(true);
		cdMainPSIdConfig.setComponentName("PS ID");
		cdMainComponentList.add(cdMainPSIdConfig);
		componentIdList.add(cdMainPSIdConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainPSIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPSNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L4S_L4D_R4S_R4D_R1D_Text_psNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainPSNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainPSNoConfig.setAuthorizationIncluded(true);
		cdMainPSNoConfig.setComponentName("PS No");
		cdMainComponentList.add(cdMainPSNoConfig);
		componentIdList.add(cdMainPSNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainPSNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPSName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L4S_L4D_R4S_R4D_R1D_Text_psName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainPSNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainPSNameConfig.setAuthorizationIncluded(true);
		cdMainPSNameConfig.setComponentName("PS Name");
		cdMainComponentList.add(cdMainPSNameConfig);
		componentIdList.add(cdMainPSNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainPSNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPSType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L4S_L4D_R4S_R4D_Combo_psType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainPSTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainPSTypeConfig.setAuthorizationIncluded(true);
		cdMainPSTypeConfig.setComponentName("PS Type");
		cdMainComponentList.add(cdMainPSTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PS_TYPE);
		cdMainPSTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainPSTypeConfig.getComponentId());
		String componentIds = cdMainTabPrefix + "R1D_psSupID";
		GtnUIFrameworkComponentConfig gtnsLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentIds + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnsLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnsLayoutConfig);
		componentIdList.add(componentIds);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainPSTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRSId(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L5S_L5D_R5S_R5D_R1D_Text_rsID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainRSIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainRSIdConfig.setAuthorizationIncluded(true);
		cdMainRSIdConfig.setComponentName("RS ID");
		cdMainComponentList.add(cdMainRSIdConfig);
		componentIdList.add(cdMainRSIdConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainRSIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRSNo(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L5S_L5D_R5S_R5D_R1D_Text_rsNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainRSNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainRSNoConfig.setAuthorizationIncluded(true);
		cdMainRSNoConfig.setComponentName("RS No");
		cdMainComponentList.add(cdMainRSNoConfig);
		componentIdList.add(cdMainRSNoConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainRSNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRSName(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L5S_L5D_R5S_R5D_R1D_Text_rsName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainRSNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdMainRSNameConfig.setAuthorizationIncluded(true);
		cdMainRSNameConfig.setComponentName("RS Name");
		cdMainComponentList.add(cdMainRSNameConfig);
		componentIdList.add(cdMainRSNameConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdMainRSNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRSType(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L5S_L5D_R5S_R5D_Combo_rsType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainRSTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainRSTypeConfig.setAuthorizationIncluded(true);
		cdMainRSTypeConfig.setComponentName("RS Type");
		cdMainComponentList.add(cdMainRSTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_TYPE);
		cdMainRSTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainRSTypeConfig.getComponentId());
		String componentIds = cdMainTabPrefix + "R1D_rsSupID";
		GtnUIFrameworkComponentConfig gtnsLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentIds + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnsLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnsLayoutConfig);
		componentIdList.add(componentIds);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainRSTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRSProgramCategory(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L5D_R5D_Combo_rsProgramCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setVisible(false);
		cdMainComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdMainRSProgramCategoryConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdMainRSProgramCategoryConfig.setAuthorizationIncluded(true);
		cdMainRSProgramCategoryConfig.setComponentName("Program Category");
		cdMainComponentList.add(cdMainRSProgramCategoryConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC_2);
		cdMainRSProgramCategoryConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(cdMainRSProgramCategoryConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdMainRSProgramCategoryConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addComponentResultPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig
				.getPanelConfig(cdMainTabPrefix + "ComponentResultPanel", true, parent);
		panelConfig.setComponentName("Component Results");
		cdMainComponentList.add(panelConfig);
		addComponentResultTable(cdMainComponentList, cdMainTabPrefix, componentIdList, panelConfig.getComponentId());
	}

	private void addComponentResultTable(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + COMPONENT_RESULT_TABLE, true, parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig cdMainComponentTableConfig = new GtnUIFrameworkPagedTableConfig();
		cdMainComponentTableConfig.setEditable(false);
		cdMainComponentTableConfig.setFilterBar(true);
		cdMainComponentTableConfig.setSelectable(true);
		cdMainComponentTableConfig.setMultiSelect(false);
		cdMainComponentTableConfig.setPageLength(5);
		cdMainComponentTableConfig.setItemPerPage(5);
		cdMainComponentTableConfig.setSinkItemPerPageWithPageLength(false);
		cdMainComponentTableConfig
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getComponentTableColumnType());
		cdMainComponentTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants
				.getComponentHeaderMappedValue(GtnWsContractDashboardContants.CONTRACT));
		cdMainComponentTableConfig
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getComponentTableColumns());
		cdMainComponentTableConfig.setDragMode(GtnUIFrameworkTableDragMode.ROW);
		GtnUIFrameWorkActionConfig itemClickAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemClickAction.addActionParameter(GtnUIFrameworkComponentTableItemClickAction.class.getName());
		itemClickAction.addActionParameter(componentIdList);
		itemClickAction.addActionParameter(cdMainTabPrefix + "ComponentDetailsTable");
		cdMainComponentTableConfig.setItemClickListener(true);
		componentConfig.addGtnUIFrameWorkActionConfig(itemClickAction);
		componentConfig.setGtnPagedTableConfig(cdMainComponentTableConfig);
		cdMainComponentTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_TABLE_DATA);
		cdMainComponentTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_TABLE_DATA);
		cdMainComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addComponentDetailsPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		String componentId = cdMainTabPrefix + "L2S_L3S_L4S_L5S_L2D_L3D_L4D_L5D_Panel";
		GtnUIFrameworkComponentConfig panelConfig = commonConfig
				.getPanelConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		panelConfig.setComponentName("Component Details");
		panelConfig.setVisible(false);
		cdMainComponentList.add(panelConfig);
		componentIdList.add(componentId);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getVerticalLayoutConfig(cdMainTabPrefix + "resultlayout", true, panelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(gtnLayoutConfig);
		addComponentDetailsTable(cdMainComponentList, cdMainTabPrefix, componentIdList,
				gtnLayoutConfig.getComponentId());
	}

	private void addComponentDetailsTable(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "ComponentDetailsTable", true, parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig cdMainComponentDeatailsTableConfig = new GtnUIFrameworkPagedTableConfig();
		cdMainComponentDeatailsTableConfig.setEditable(false);
		cdMainComponentDeatailsTableConfig.setFilterBar(true);
		cdMainComponentDeatailsTableConfig.setSelectable(false);
		cdMainComponentDeatailsTableConfig.setPageLength(5);
		cdMainComponentDeatailsTableConfig.setItemPerPage(5);
		cdMainComponentDeatailsTableConfig.setSinkItemPerPageWithPageLength(false);
		cdMainComponentDeatailsTableConfig
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getComponentDetailsTableColumnType());
		cdMainComponentDeatailsTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants
				.getComponentDetailsHeaderMappedValue(GtnWsContractDashboardContants.CONTRACT));
		cdMainComponentDeatailsTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants
				.getComponentDetailsColumnMappedValue(GtnWsContractDashboardContants.CONTRACT));
		componentConfig.setGtnPagedTableConfig(cdMainComponentDeatailsTableConfig);
		cdMainComponentDeatailsTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_DETAILS_TABLE_DATA);
		cdMainComponentDeatailsTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_DETAILS_TABLE_DATA);
		cdMainComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		addHiddenId(cdMainComponentList, cdMainTabPrefix, componentIdList, parent);
	}

	private void addHiddenId(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "hiddenID", true, parent, GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setVisible(false);
		cdMainComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addTreeVerticalLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig treeVerticalConfig = commonConfig
				.getVerticalLayoutConfig(cdMainTabPrefix + "treeVerticalLayout", true, parent);
		treeVerticalConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		treeVerticalConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(treeVerticalConfig);

		GtnUIFrameworkComponentConfig treeSplitConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "treeVerticalSplitPanel", true, treeVerticalConfig.getComponentId(),
				GtnUIFrameworkComponentType.VERTICAL_SPLIT_PANEL);
		treeSplitConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		treeSplitConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(treeSplitConfig);
		GtnUIFrameworkSplitPanelConfig splitPanelConfig = new GtnUIFrameworkSplitPanelConfig();
		splitPanelConfig.setSplitPosition(String.valueOf(GtnWsNumericConstants.ONE_THREE_ZERO));
		treeSplitConfig.setGtnSplitPanelConfig(splitPanelConfig);
		addTreeConfigurationPanel(cdMainComponentList, cdMainTabPrefix, treeSplitConfig.getComponentId());
		List<String> componentIdList = new ArrayList<>();
		componentIdList.add(GtnWsContractDashboardContants.RIGHT);
		componentIdList.add(cdMainTabPrefix);
		addContractManagementPanel(cdMainComponentList, GtnWsContractDashboardContants.RIGHT + cdMainTabPrefix,
				componentIdList, treeSplitConfig.getComponentId());
	}

	private void addTreeConfigurationPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig treePanelConfig = commonConfig.getPanelConfig(cdMainTabPrefix + "treePanel", true,
				parent);
		treePanelConfig.setComponentName("Working Board");
		cdMainComponentList.add(treePanelConfig);

		GtnUIFrameworkComponentConfig treeLayoutConfig = commonConfig
				.getVerticalLayoutConfig(cdMainTabPrefix + "treeResultLayout", true, treePanelConfig.getComponentId());
		treeLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(treeLayoutConfig);
		addTree(cdMainComponentList, cdMainTabPrefix, treeLayoutConfig.getComponentId());
		addSaveButtonLayout(cdMainComponentList, cdMainTabPrefix, treeLayoutConfig.getComponentId());
	}

	private void addContractManagementPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(cdMainTabPrefix + "contractPanel", true,
				parent);
		panelConfig.setComponentName("Contract Management");
		cdMainComponentList.add(panelConfig);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(cdMainTabPrefix + "contractLayout", true, panelConfig.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(layoutConfig);
		addSearchCriteriaPanel(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addTreeResultPanel(cdMainComponentList, cdMainTabPrefix, componentIdList, layoutConfig.getComponentId());
		addProcessButtonLayout(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
	}

	private void addTree(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig treeComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_DASHBOARD_TREE, true, parent, GtnUIFrameworkComponentType.TREE);
		treeComponentConfig.setAuthorizationIncluded(true);
		treeComponentConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MIN_HEIGHT_30);
		GtnUIFrameworkTreeConfig treeConfig = new GtnUIFrameworkTreeConfig();
		treeConfig.setSelectable(true);
		treeConfig.setDragMode(true);
		treeComponentConfig.setGtnUIFrameworkTreeConfig(treeConfig);
		GtnUIFrameWorkActionConfig dropToTreeAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dropToTreeAction.addActionParameter(GtnFrameworkDropToTreeAction.class.getName());
		dropToTreeAction.addActionParameter(cdMainTabPrefix + COMPONENT_RESULT_TABLE);
		dropToTreeAction.addActionParameter(GtnWsContractDashboardContants.CONTRACT_DASHBOARD_TREE);
		dropToTreeAction.addActionParameter("Please select Search Result");
		dropToTreeAction.addActionParameter("Criteria Mismatch");
		dropToTreeAction.addActionParameter("Cannot make a ? as contracts header");
		dropToTreeAction.addActionParameter(DUPLICATE_CONTRACT_ID);
		dropToTreeAction.addActionParameter(SELECTED_CONTRACT_ID_IS_ALREADY_EXIST);
		dropToTreeAction.addActionParameter("Cannot make a ? as child node");
		dropToTreeAction.addActionParameter("? cannot be added to ?");
		treeConfig.setDropHandlerAction(dropToTreeAction);

		cdMainComponentList.add(treeComponentConfig);
	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdMainTabPrefix + "saveButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(layoutConfig);
		addSaveButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
		addRemoveFromTreeButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig saveButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "gtnSave", true, parent, GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("Save");
		cdMainComponentList.add(saveButtonConfig);
		GtnUIFrameWorkActionConfig cdMainSaveActionConfig = new GtnUIFrameWorkActionConfig();
		cdMainSaveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdMainSaveActionConfig.addActionParameter(GtnFrameworkTreeSaveAction.class.getName());
		cdMainSaveActionConfig.addActionParameter(GtnWsContractDashboardContants.CONTRACT_DASHBOARD_TREE);
		cdMainSaveActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		cdMainSaveActionConfig.addActionParameter("No new contract created. Create new contract and save it.");
		cdMainSaveActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		cdMainSaveActionConfig.addActionParameter("Save record ");

		saveButtonConfig.addGtnUIFrameWorkActionConfig(cdMainSaveActionConfig);

	}

	private void addRemoveFromTreeButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String namespaceprefix, String parent) {
		GtnUIFrameworkComponentConfig removeFromTreeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namespaceprefix + "removeFromTreeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		removeFromTreeBtnConfig.setAuthorizationIncluded(true);
		removeFromTreeBtnConfig.setComponentName("Remove");
		cdMainComponentList.add(removeFromTreeBtnConfig);
		GtnUIFrameWorkActionConfig removeFromTreeAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeFromTreeAction.addActionParameter(GtnFrameworkRemoveFromTreeAction.class.getName());
		removeFromTreeAction.addActionParameter(GtnWsContractDashboardContants.CONTRACT_DASHBOARD_TREE);
		removeFromTreeAction.addActionParameter("Remove Criteria");
		removeFromTreeAction.addActionParameter("No data to remove");
		removeFromTreeAction.addActionParameter("No Node Selected");
		removeFromTreeAction.addActionParameter("Please select a node to remove ");
		removeFromTreeAction.addActionParameter("Please remove the child nodes of  ");
		removeFromTreeAction.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		removeFromTreeAction.addActionParameter("Are you sure you want to remove the component?");

		removeFromTreeBtnConfig.addGtnUIFrameWorkActionConfig(removeFromTreeAction);

	}

	private void addProcessButtonLayout(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdMainTabPrefix + "processButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdMainComponentList.add(layoutConfig);
		addProcessButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
		addRebuildButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
		addProcessExcelButtonComponent(cdMainComponentList, cdMainTabPrefix, layoutConfig.getComponentId());
	}

	private void addProcessExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "excelBtn", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdMainComponentList.add(excelBtnComponentConfig);
		GtnUIFrameWorkActionConfig excelExportAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		excelExportAction.addActionParameter(GtnFrameworkProcessTreeExcelExportAction.class.getName());
		excelExportAction.addActionParameter(GtnFrameworkContractDashboardContants.PROCESS_EXCELTABLE);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelExportAction));
	}

	private void addProcessButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig cdMainProcessBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "gtnProcess", true, parent, GtnUIFrameworkComponentType.BUTTON);
		cdMainProcessBtnConfig.setAuthorizationIncluded(true);
		cdMainProcessBtnConfig.setComponentName("Process");
		cdMainComponentList.add(cdMainProcessBtnConfig);

		GtnUIFrameWorkActionConfig cdMainProcessBtnValidationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cdMainProcessBtnValidationActionConfig.setFieldValues(Arrays.asList(cdMainTabPrefix + RESULT_TABLE));
		cdMainProcessBtnValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig cdMainProcessBtnFailureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		cdMainProcessBtnFailureActionConfig.addActionParameter("Process Error");
		cdMainProcessBtnFailureActionConfig.addActionParameter("Please select a Contract");

		GtnUIFrameWorkActionConfig validateContractToProcessActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		validateContractToProcessActionConfig
				.addActionParameter(GtnFrameworkValidateContractToProcessAction.class.getName());
		validateContractToProcessActionConfig.addActionParameter(cdMainTabPrefix + RESULT_TABLE);

		GtnUIFrameWorkActionConfig cdMainProcessBtnNavigationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		cdMainProcessBtnNavigationActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.CD_PROCESS_VIEW);

		GtnUIFrameWorkActionConfig cdMainProcessBtnProcessActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdMainProcessBtnProcessActionConfig.addActionParameter(GtnFrameworkProcessAction.class.getName());
		cdMainProcessBtnProcessActionConfig.addActionParameter(cdMainTabPrefix + RESULT_TABLE);
		cdMainProcessBtnProcessActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_PROCESS_VIEW);
		cdMainProcessBtnProcessActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.TAB_SHEET_ID);

		validateContractToProcessActionConfig.addActionParameter(
				Arrays.asList(cdMainProcessBtnNavigationActionConfig, cdMainProcessBtnProcessActionConfig));

		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter(
				"The Contract selected to be ‘Processed’ already has Actuals, Forecasted Sales, or Forecasted Discounts associated with it.  Proceed?");
		confirmActionConfig.addActionParameter(
				Arrays.asList(cdMainProcessBtnNavigationActionConfig, cdMainProcessBtnProcessActionConfig));
		validateContractToProcessActionConfig.addActionParameter(Arrays.asList(confirmActionConfig));

		cdMainProcessBtnValidationActionConfig.addActionParameter(Arrays.asList(cdMainProcessBtnFailureActionConfig));
		cdMainProcessBtnValidationActionConfig.addActionParameter(Arrays.asList(validateContractToProcessActionConfig));

		cdMainProcessBtnConfig.addGtnUIFrameWorkActionConfig(cdMainProcessBtnValidationActionConfig);
	}

	private void addRebuildButtonComponent(List<GtnUIFrameworkComponentConfig> cdMainComponentList,
			String cdMainTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig cdMainRebuildBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + "rebuildBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		cdMainRebuildBtnConfig.setAuthorizationIncluded(true);
		cdMainRebuildBtnConfig.setComponentName("Rebuild");
		cdMainComponentList.add(cdMainRebuildBtnConfig);

		GtnUIFrameWorkActionConfig failureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("Rebuild Criteria");
		failureActionConfig.addActionParameter("Select a contract to rebuild");

		GtnUIFrameWorkActionConfig cdMainRebuildBtnActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdMainRebuildBtnActionConfig.addActionParameter(GtnFrameworkContractRebuildAction.class.getName());
		cdMainRebuildBtnActionConfig.addActionParameter(cdMainTabPrefix + RESULT_TABLE);
		cdMainRebuildBtnActionConfig.addActionParameter("Cannot Rebuild");
		cdMainRebuildBtnActionConfig.addActionParameter("The selected node_name - node_id can not be rebuilt");
		cdMainRebuildBtnActionConfig.addActionParameter("node_name");
		cdMainRebuildBtnActionConfig.addActionParameter("node_id");
		cdMainRebuildBtnActionConfig.addActionParameter(GtnWsContractDashboardContants.CONTRACT_DASHBOARD_TREE);
		cdMainRebuildBtnActionConfig.addActionParameter(DUPLICATE_CONTRACT_ID);
		cdMainRebuildBtnActionConfig.addActionParameter(SELECTED_CONTRACT_ID_IS_ALREADY_EXIST);

		GtnUIFrameWorkActionConfig confirmActionContractNotToProcessConfig = new GtnUIFrameWorkActionConfig();
		confirmActionContractNotToProcessConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionContractNotToProcessConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionContractNotToProcessConfig.addActionParameter("Are you sure you want to Rebuild the contract?");
		confirmActionContractNotToProcessConfig.addActionParameter(Arrays.asList(cdMainRebuildBtnActionConfig));

		GtnUIFrameWorkActionConfig confirmActionContractToProcessConfig = new GtnUIFrameWorkActionConfig();
		confirmActionContractToProcessConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionContractToProcessConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionContractToProcessConfig.addActionParameter(
				"The Contract selected to be ‘Processed’ already has Actuals, Forecasted Sales, or Forecasted Discounts associated with it.  Proceed?");
		confirmActionContractToProcessConfig.addActionParameter(Arrays.asList(cdMainRebuildBtnActionConfig));

		GtnUIFrameWorkActionConfig validateContractToProcessActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		validateContractToProcessActionConfig
				.addActionParameter(GtnFrameworkValidateContractToProcessAction.class.getName());
		validateContractToProcessActionConfig.addActionParameter(cdMainTabPrefix + RESULT_TABLE);
		validateContractToProcessActionConfig
				.addActionParameter(Arrays.asList(confirmActionContractNotToProcessConfig));
		validateContractToProcessActionConfig.addActionParameter(Arrays.asList(confirmActionContractToProcessConfig));

		GtnUIFrameWorkActionConfig validateContractToRebuildActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		validateContractToRebuildActionConfig
				.addActionParameter(GtnFrameworkValidateContractToRebuildAction.class.getName());
		validateContractToRebuildActionConfig.addActionParameter(cdMainTabPrefix + RESULT_TABLE);
		validateContractToRebuildActionConfig.addActionParameter("Rebuild Criteria");
		validateContractToRebuildActionConfig
				.addActionParameter("The selected node is node_name. Please select a contract to Rebuild");
		validateContractToRebuildActionConfig.addActionParameter("node_name");
		validateContractToRebuildActionConfig.addActionParameter(Arrays.asList(validateContractToProcessActionConfig));

		GtnUIFrameWorkActionConfig cdMainRebuildBtnValidationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cdMainRebuildBtnValidationActionConfig.setFieldValues(Arrays.asList(cdMainTabPrefix + RESULT_TABLE));
		cdMainRebuildBtnValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);
		cdMainRebuildBtnValidationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		cdMainRebuildBtnValidationActionConfig.addActionParameter(Arrays.asList(validateContractToRebuildActionConfig));

		cdMainRebuildBtnConfig.addGtnUIFrameWorkActionConfig(cdMainRebuildBtnValidationActionConfig);
	}

	private void addTreeResultPanel(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(cdMainTabPrefix + "resultPanel", true,
				parent);
		panelConfig.setComponentName("Results");
		cdMainComponentList.add(panelConfig);

		addTreeTable(cdMainComponentList, cdMainTabPrefix, componentIdList, panelConfig.getComponentId());
	}

	private void addTreeTable(List<GtnUIFrameworkComponentConfig> cdMainComponentList, String cdMainTabPrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdMainTabPrefix + RESULT_TABLE, true, parent, GtnUIFrameworkComponentType.PAGED_TREE_TABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkNewTableConfig cdMainNewTableConfig = new GtnUIFrameworkNewTableConfig();
		cdMainNewTableConfig.setEditable(false);
		cdMainNewTableConfig.setFilterBar(false);
		cdMainNewTableConfig.setSelectable(true);
		cdMainNewTableConfig.setMultiSelect(false);
		cdMainNewTableConfig.setPageLength(10);
		cdMainNewTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getMainTreeTableColumnType());
		cdMainNewTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getMainTreeTableHeader());
		cdMainNewTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getMainTreeTableColumns());
		cdMainNewTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_PROCESS_TABLE_DATA);
		componentConfig.setGtnNewTableConfig(cdMainNewTableConfig);

		GtnUIFrameworkNewPagedTableConfig cdMainPagedTableConfig = new GtnUIFrameworkNewPagedTableConfig();
		cdMainPagedTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_PROCESS_TABLE_DATA);
		cdMainPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		cdMainPagedTableConfig.setItemPerPage(10);
		componentConfig.setGtnNewPagedTableConfig(cdMainPagedTableConfig);

		GtnUIFrameworkNewTreeTableConfig cdMainTreeTableConfig = new GtnUIFrameworkNewTreeTableConfig();
		GtnUIFrameWorkActionConfig childDetectActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		childDetectActionConfig.addActionParameter(GtnFrameworkChildDetectAction.class.getName());
		cdMainTreeTableConfig.setChildDetectActionConfig(childDetectActionConfig);
		componentConfig.setGtnNewTreeTableConfig(cdMainTreeTableConfig);

		GtnUIFrameworkValidationConfig cdMainTreePagedTableValidationConfig = new GtnUIFrameworkValidationConfig();
		cdMainTreePagedTableValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(cdMainTreePagedTableValidationConfig);

		cdMainComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}
}
