package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkCompanyItemAdditionSearchAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkSearchFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.duallistboxaction.GtnFrameworkCompanyAdditionTableRecordMoveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkCompanyAdditionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardCompanyAdditionTabConfig {

	private static final String VALUE = "Value";
	private static final String LEFT_RESULT_TABLE = "leftResultTable";
	private static final String RIGHT_RESULT_TABLE = "rightResultTable";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdCompanyAdditionTabPrefix = mainNamspacePrefix + "CADT";
		GtnUIFrameworkTabConfig cdCompanyAdditionTabConfig = new GtnUIFrameworkTabConfig();
		cdCompanyAdditionTabConfig.setComponentId(cdCompanyAdditionTabPrefix + "companyAdditionTab");
		cdCompanyAdditionTabConfig.setTabCaption("Company Addition");
		List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList = new ArrayList<>();
		cdCompanyAdditionTabConfig.setTabLayoutComponentConfigList(cdCompanyAdditionComponentList);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdCompanyAdditionTabConfig.getComponentId(), true, cdCompanyAdditionTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);
		addEditTableLayout(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId());
		viewResultDataTable(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId());
		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkCompanyAdditionTabLoadAction.class.getName());
		tabLoadAction.addActionParameter(cdCompanyAdditionTabPrefix + "editCssLayout");
		tabLoadAction.addActionParameter(
				cdCompanyAdditionTabPrefix + "rightResultTableView" + GtnFrameworkCommonStringConstants.LAYOUT);
		tabLoadAction.addActionParameter(cdCompanyAdditionTabPrefix + RIGHT_RESULT_TABLE);
		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(tabLoadAction);
		return cdCompanyAdditionTabConfig;
	}

	private void addEditTableLayout(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdCompanyAdditionTabPrefix + "editCssLayout", true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);
		addSearchFieldLayout(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId());
		addDualListBoxLayout(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId());
	}

	private void addSearchFieldLayout(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdCompanyAdditionTabPrefix + "searchCssLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1250);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchFields(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addSearchValue(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addSearchButtonComponent(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId(), componentIdList);
	}

	private void addSearchFields(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompanyAdditionTabPrefix + "Combo_SearchField";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Search Field");
		cdCompanyAdditionComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setItemValues(GtnWsContractDashboardContants.getCompanyAdditionSearchFieldValues());
		componentConfig.setGtnComboboxConfig(comboBoxConfig);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkSearchFieldValueChangeAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(GtnWsContractDashboardContants.getCompanyAdditionSearchFieldValues().get(0));
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addSearchValue(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				cdCompanyAdditionTabPrefix + VALUE + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig companyIDConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompanyAdditionTabPrefix + "Text_CompanyID", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIDConfig.setAuthorizationIncluded(true);
		companyIDConfig.setComponentName(VALUE);
		cdCompanyAdditionComponentList.add(companyIDConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIDConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(companyIDConfig.getComponentId());

		GtnUIFrameworkComponentConfig companyNameConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompanyAdditionTabPrefix + "Text_CompanyName", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName(VALUE);
		companyNameConfig.setVisible(false);
		cdCompanyAdditionComponentList.add(companyNameConfig);
		companyNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(companyNameConfig.getComponentId());

		GtnUIFrameworkComponentConfig companyNoConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompanyAdditionTabPrefix + "Text_CompanyNo", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName(VALUE);
		companyNoConfig.setVisible(false);
		cdCompanyAdditionComponentList.add(companyNoConfig);
		companyNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(companyNoConfig.getComponentId());

		GtnUIFrameworkComponentConfig companyStatusConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompanyAdditionTabPrefix + "Combo_CompanyStatus", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatusConfig.setAuthorizationIncluded(true);
		companyStatusConfig.setComponentName(VALUE);
		companyStatusConfig.setVisible(false);
		cdCompanyAdditionComponentList.add(companyStatusConfig);
		GtnUIFrameworkValidationConfig val1Config = new GtnUIFrameworkValidationConfig();
		val1Config.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatusConfig.setGtnUIFrameworkValidationConfig(val1Config);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		companyStatusConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(companyStatusConfig.getComponentId());

		GtnUIFrameworkComponentConfig companyTypeConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompanyAdditionTabPrefix + "Combo_CompanyType", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		companyTypeConfig.setAuthorizationIncluded(true);
		companyTypeConfig.setComponentName(VALUE);
		companyTypeConfig.setVisible(false);
		cdCompanyAdditionComponentList.add(companyTypeConfig);
		companyTypeConfig.setGtnUIFrameworkValidationConfig(val1Config);
		GtnUIFrameworkComboBoxConfig companyTypeComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.COMP_TYPE);
		companyTypeConfig.setGtnComboboxConfig(companyTypeComboBoxConfig);
		componentIdList.add(companyTypeConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompanyAdditionTabPrefix + "SearchBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_100);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setComponentName("Search");
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		cdCompanyAdditionComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig searchAction = new GtnUIFrameWorkActionConfig();
		searchAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchAction.addActionParameter(GtnUIFrameworkCompanyItemAdditionSearchAction.class.getName());
		searchAction.addActionParameter(componentIdList);
		searchAction.addActionParameter(GtnFrameworkContractDashboardContants.COMPANY_ADDITION);
		searchAction.addActionParameter(cdCompanyAdditionTabPrefix + "Combo_SearchField");
		searchAction.addActionParameter("Company ID");
		searchAction.addActionParameter(GtnFrameworkContractDashboardContants.ERROR_DISPLAY_LABEL);
		searchAction.addActionParameter(Arrays.asList(GtnFrameworkContractDashboardContants.MSG_SEARCH_FIELD,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_COMBO_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_COMBO_VALUE));
		searchAction.addActionParameter(cdCompanyAdditionTabPrefix + LEFT_RESULT_TABLE);
		componentConfig.addGtnUIFrameWorkActionConfig(searchAction);
	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + "dualListBox";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		leftResultDataTable(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId());
		moveButtonsLayout(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		rightResultDataTable(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix,
				gtnLayoutConfig.getComponentId());
	}

	private void moveButtonsLayout(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdCompanyAdditionTabPrefix + "dualListBoxButton" + GtnFrameworkCommonStringConstants.LAYOUT, true,
				parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);
		gtnLayoutConfig.setMargin(true);
		gtnLayoutConfig.setSpacing(true);
		gtnLayoutConfig.addComponentStyle("gtnGrid-DualList-Buttons");
		moveRight(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveLeft(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveAllRight(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveAllLeft(cdCompanyAdditionComponentList, cdCompanyAdditionTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + "moveRightBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" > ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdCompanyAdditionComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(cdCompanyAdditionTabPrefix + LEFT_RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig failureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("Error");
		failureActionConfig.addActionParameter("Please select a company to add");

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkCompanyAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdCompanyAdditionTabPrefix + LEFT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_RIGHT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkCompanyAdditionTabLoadAction.class.getName());

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(moveAction, tabLoadAction));

		componentConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + "moveLeftBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" < ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdCompanyAdditionComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(cdCompanyAdditionTabPrefix + RIGHT_RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig failureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("Error");
		failureActionConfig.addActionParameter("Please select a company to remove");

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkCompanyAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdCompanyAdditionTabPrefix + RIGHT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_LEFT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkCompanyAdditionTabLoadAction.class.getName());

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(moveAction, tabLoadAction));

		componentConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void moveAllRight(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + "moveRightBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig contractDashboardMoveRightConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		contractDashboardMoveRightConfig.setAuthorizationIncluded(true);
		contractDashboardMoveRightConfig.setComponentName(" >> ");
		contractDashboardMoveRightConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdCompanyAdditionComponentList.add(contractDashboardMoveRightConfig);

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkCompanyAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdCompanyAdditionTabPrefix + LEFT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_ALL_RIGHT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkCompanyAdditionTabLoadAction.class.getName());

		contractDashboardMoveRightConfig.addGtnUIFrameWorkActionConfig(moveAction);
		contractDashboardMoveRightConfig.addGtnUIFrameWorkActionConfig(tabLoadAction);
	}

	private void moveAllLeft(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + "moveLeftBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig coontractDashboardMoveLeftConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		coontractDashboardMoveLeftConfig.setAuthorizationIncluded(true);
		coontractDashboardMoveLeftConfig.setComponentName(" << ");
		coontractDashboardMoveLeftConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdCompanyAdditionComponentList.add(coontractDashboardMoveLeftConfig);

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkCompanyAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdCompanyAdditionTabPrefix + RIGHT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_ALL_LEFT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkCompanyAdditionTabLoadAction.class.getName());

		coontractDashboardMoveLeftConfig.addGtnUIFrameWorkActionConfig(moveAction);
		coontractDashboardMoveLeftConfig.addGtnUIFrameWorkActionConfig(tabLoadAction);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + LEFT_RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompanyAdditionLeftResultDataTableConfig = commonConfig
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.PAGEDTABLE);
		cdCompanyAdditionLeftResultDataTableConfig.setAuthorizationIncluded(true);
		cdCompanyAdditionLeftResultDataTableConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdCompanyAdditionLeftResultDataTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		cdCompanyAdditionComponentList.add(cdCompanyAdditionLeftResultDataTableConfig);

		GtnUIFrameworkValidationConfig cdCompanyAdditionLeftResultDataTableValConfig = new GtnUIFrameworkValidationConfig();
		cdCompanyAdditionLeftResultDataTableValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdCompanyAdditionLeftResultDataTableConfig
				.setGtnUIFrameworkValidationConfig(cdCompanyAdditionLeftResultDataTableValConfig);

		GtnUIFrameworkPagedTableConfig cdCompanyAdditionLeftResultDataTable = new GtnUIFrameworkPagedTableConfig();
		cdCompanyAdditionLeftResultDataTableConfig.setGtnPagedTableConfig(cdCompanyAdditionLeftResultDataTable);
		cdCompanyAdditionLeftResultDataTable.setFilterBar(true);
		cdCompanyAdditionLeftResultDataTable.setSelectable(true);
		cdCompanyAdditionLeftResultDataTable.setSinkItemPerPageWithPageLength(false);
		cdCompanyAdditionLeftResultDataTable
				.setTableColumnDataType(GtnWsContractDashboardContants.getCompanyAdditionColumnType());
		cdCompanyAdditionLeftResultDataTable
				.setTableVisibleHeader(GtnWsContractDashboardContants.getCompanyAdditionHeader());
		cdCompanyAdditionLeftResultDataTable
				.setTableColumnMappingId(GtnWsContractDashboardContants.getCompanyAdditionColumn());
		cdCompanyAdditionLeftResultDataTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_LEFT_TABLE_DATA);
		cdCompanyAdditionLeftResultDataTable
				.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_LEFT_TABLE_DATA);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + RIGHT_RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompanyAdditionRightResultDataTableConfig = commonConfig
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.PAGEDTABLE);
		cdCompanyAdditionRightResultDataTableConfig.setAuthorizationIncluded(true);
		cdCompanyAdditionRightResultDataTableConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdCompanyAdditionRightResultDataTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		cdCompanyAdditionComponentList.add(cdCompanyAdditionRightResultDataTableConfig);

		GtnUIFrameworkValidationConfig cdCompanyAdditionRightResultDataTableValConfig = new GtnUIFrameworkValidationConfig();
		cdCompanyAdditionRightResultDataTableValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdCompanyAdditionRightResultDataTableConfig
				.setGtnUIFrameworkValidationConfig(cdCompanyAdditionRightResultDataTableValConfig);

		GtnUIFrameworkPagedTableConfig cdCompanyAdditionRightResultDataTable = new GtnUIFrameworkPagedTableConfig();
		cdCompanyAdditionRightResultDataTableConfig.setGtnPagedTableConfig(cdCompanyAdditionRightResultDataTable);
		cdCompanyAdditionRightResultDataTable.setFilterBar(true);
		cdCompanyAdditionRightResultDataTable.setSelectable(true);
		cdCompanyAdditionRightResultDataTable.setSinkItemPerPageWithPageLength(false);
		cdCompanyAdditionRightResultDataTable
				.setTableColumnDataType(GtnWsContractDashboardContants.getCompanyAdditionColumnType());
		cdCompanyAdditionRightResultDataTable
				.setTableVisibleHeader(GtnWsContractDashboardContants.getCompanyAdditionHeader());
		cdCompanyAdditionRightResultDataTable
				.setTableColumnMappingId(GtnWsContractDashboardContants.getCompanyAdditionColumn());
		cdCompanyAdditionRightResultDataTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA);
		cdCompanyAdditionRightResultDataTable
				.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA);

	}

	private void viewResultDataTable(List<GtnUIFrameworkComponentConfig> cdCompanyAdditionComponentList,
			String cdCompanyAdditionTabPrefix, String parent) {
		String componentId = cdCompanyAdditionTabPrefix + "rightResultTableView";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdCompanyAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdCompanyAdditionComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdCompanyAdditionResultDataTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdCompanyAdditionResultDataTable);
		cdCompanyAdditionResultDataTable.setPageLength(10);
		cdCompanyAdditionResultDataTable.setItemPerPage(10);
		cdCompanyAdditionResultDataTable.setSinkItemPerPageWithPageLength(false);
		cdCompanyAdditionResultDataTable
				.setTableColumnDataType(GtnWsContractDashboardContants.getCompanyAdditionColumnType());
		cdCompanyAdditionResultDataTable
				.setTableVisibleHeader(GtnWsContractDashboardContants.getCompanyAdditionHeader());
		cdCompanyAdditionResultDataTable
				.setTableColumnMappingId(GtnWsContractDashboardContants.getCompanyAdditionColumn());
		cdCompanyAdditionResultDataTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA);
		cdCompanyAdditionResultDataTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA);

	}
}
