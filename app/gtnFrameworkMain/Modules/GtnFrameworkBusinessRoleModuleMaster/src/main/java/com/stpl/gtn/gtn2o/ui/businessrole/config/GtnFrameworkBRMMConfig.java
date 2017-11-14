package com.stpl.gtn.gtn2o.ui.businessrole.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.businessrole.action.GtnFrameworkBusinessRoleMasterSave;
import com.stpl.gtn.gtn2o.ui.businessrole.action.GtnUIFrameworkModuleDetailsSaveAction;
import com.stpl.gtn.gtn2o.ui.businessrole.constants.GtnFrameworkBrmConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.authorization.constants.GtnWsModuleAuthorizationConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkBRMMConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkRootConfig getBRMMRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

	private GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("BusniessRoleModuleMaster",
				"BusniessRoleModuleMaster", true);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addBRMMSearchCriteriaPanel(componentList);
		addBRMMSearchButtonLayout(componentList);
		addBRMMResultPanel(componentList);
		addBRMMPropertyResultPanel(componentList);
		addBRMMSaveButtonLayout(componentList);
		addBRMMModuleResultPanel(componentList);
		addBRMMModuleDetailsSaveButtonLayout(componentList);
	}

	private void addBRMMSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("brmmSearchCriteriaPanel", false, null);
		panel.setComponentName("Search Criteria");
		panel.setComponentWidth("100%");

		componentList.add(panel);
		addBRMMFieldLayout(componentList);
	}

	private void addBRMMResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("brmmResultPanel", false, null);
		panel.setComponentName("Component List");
		panel.setComponentWidth("100%");

		componentList.add(panel);
		addBRMMResultLayout(componentList);
	}

	private void addBRMMPropertyResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("brmmPropertyResultPanel", false, null);
		panel.setComponentName("Table Properties");
		panel.setComponentWidth("100%");

		componentList.add(panel);
		addBRMMPropertyResultLayout(componentList);
	}

	private void addBRMMModuleResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig moduleDeatilsPanel = configProvider.getPanelConfig("brmmModuleResultPanel", false,
				null);
		moduleDeatilsPanel.setComponentName("Module Details");
		moduleDeatilsPanel.setComponentWidth("100%");

		componentList.add(moduleDeatilsPanel);
		addBRMMModuleResultLayout(componentList);
	}

	private void addBRMMModuleResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig moduleDeatilsResultLayout = configProvider
				.getHorizontalLayoutConfig("brmmModuleResultlayout", true, "brmmModuleResultPanel");
		componentList.add(moduleDeatilsResultLayout);
		moduleDeatilsResultLayout.setComponentWidth("100%");
		addBRMMModuleTableComponent(componentList);
	}

	private void addBRMMResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultLayout = configProvider.getHorizontalLayoutConfig("brmmResultlayout", true,
				"brmmResultPanel");
		componentList.add(resultLayout);
		resultLayout.setComponentWidth("100%");
		addBRMMTableComponent(componentList);
	}

	private void addBRMMPropertyResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig propertyResultLayout = configProvider
				.getHorizontalLayoutConfig("brmmPropertyResultlayout", true, "brmmPropertyResultPanel");
		propertyResultLayout.setComponentWidth("100%");
		componentList.add(propertyResultLayout);
		addPropertyTableComponent(componentList);
	}

	private void addBRMMFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				"brmmSearchCriteriaLayout", true, "brmmSearchCriteriaPanel", GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.addComponentStyle("gtnGrid-single-ln-layout-3");
		componentList.add(gtnLayout);
		addBRMMFieldComponent(componentList);
	}

	private void addBRMMSearchButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnFrameworkBrmConstants.BRM_SEARCH_BUTTON_CRITERIA_LAYOUT, false, null,
				GtnUIFrameworkLayoutType.CSS_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWO);
		componentList.add(gtnLayout);
		addCMSearchButtonComponent(componentList);
		addCMResetButtonComponent(componentList);
	}

	private void addBRMMSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				"brmmSaveButtonCriteriaLayout", false, null, GtnUIFrameworkLayoutType.CSS_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth("20%");
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWO);
		componentList.add(gtnLayout);
		addBRMMSaveButtonComponent(componentList);
	}

	private void addBRMMModuleDetailsSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				"brmmModuleDetailsSaveButtonCriteriaLayout", false, null, GtnUIFrameworkLayoutType.CSS_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth("20%");
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWO);
		componentList.add(gtnLayout);
		addBRMMModuleDetailsSaveButtonComponent(componentList);
	}

	private void addBRMMTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				"brmmResultTable", true, "brmmResultlayout", GtnUIFrameworkComponentType.DATATABLE);
		searchResultConfig.setComponentName(GtnFrameworkBrmConstants.RESULT_TABLE);

		searchResultConfig.setComponentWidth("100%");

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig componentDeatilsResults = new GtnUIFrameworkPagedTableConfig();
		componentDeatilsResults.setEditable(true);
		componentDeatilsResults.setFilterBar(true);
		componentDeatilsResults.setPageLength(10);
		componentDeatilsResults.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, Boolean.class, Boolean.class });
		componentDeatilsResults.setTableVisibleHeader(new String[] { "Component Name", "Component Type", "Screen Name",
				"Visible", GtnFrameworkBrmConstants.EDITABLE });
		componentDeatilsResults.setTableColumnMappingId(new Object[] { "componentName", "componentType", "screenName",
				GtnFrameworkBrmConstants.VISIBLE, GtnFrameworkBrmConstants.EDITABLE });
		componentDeatilsResults.setModuleName(GtnFrameworkBrmConstants.BUSINESS_ROLE_MODULE_MASTER);
		componentDeatilsResults.setQueryName("getGtnModuleComponentRoleDetailsQuery");
		componentDeatilsResults.setEditableColumnList(
				Arrays.asList(GtnFrameworkBrmConstants.VISIBLE, GtnFrameworkBrmConstants.EDITABLE));
		componentDeatilsResults.setCheckAllColumnList(
				Arrays.asList(GtnFrameworkBrmConstants.VISIBLE, GtnFrameworkBrmConstants.EDITABLE));
		componentDeatilsResults.addColumnCheckActionConfig(
				configProvider.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DATA_TABLE_CHECKALL_ACTION));
		componentDeatilsResults.setEditableField(getFieldFactory(2));
		componentDeatilsResults.setResultSetUrl("/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
				+ GtnWsModuleAuthorizationConstants.GTN_GET_COMPONENT_SECURITY_DETAILS_URI);
		searchResultConfig.setGtnPagedTableConfig(componentDeatilsResults);
	}

	private void addBRMMFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addBusinessRoleName(componentList);
		addModuleName(componentList);
		addSubModuleName(componentList);
	}

	private void addBusinessRoleName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig businessRoleName = addBRMMSearchGeneralSearcField(componentList,
				GtnFrameworkBrmConstants.BUSINESS_ROLE_NAME, "Business Role Name", GtnUIFrameworkComponentType.COMBOBOX,
				GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig businessRoleNameConfig = configProvider.getComboBoxConfig("BusinessRole",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessRoleName.setGtnComboboxConfig(businessRoleNameConfig);
	}

	private void addModuleName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig moduleName = addBRMMSearchGeneralSearcField(componentList,
				GtnFrameworkBrmConstants.MODULE_NAME, "Module Name", GtnUIFrameworkComponentType.COMBOBOX,
				GtnUIFrameworkConditionalValidationType.NOT_NULL);

		moduleName.addDependentComponent(GtnFrameworkBrmConstants.SUBMODULENAME);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig valueChangeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION);
		actionConfigList.add(valueChangeAction);

		moduleName.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComboBoxConfig moduleNameConfig = configProvider.getComboBoxConfig(
				GtnFrameworkBrmConstants.MODULE_NAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		moduleName.setGtnComboboxConfig(moduleNameConfig);
		moduleNameConfig.setIntegerItemCode(false);
	}

	private void addSubModuleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig subModuleName = addBRMMSearchGeneralSearcField(componentList,
				GtnFrameworkBrmConstants.SUBMODULENAME, "Sub Module Name", GtnUIFrameworkComponentType.COMBOBOX,
				GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig subModuleNameConfig = configProvider.getComboBoxConfig(
				GtnFrameworkBrmConstants.SUBMODULENAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		subModuleName.setGtnComboboxConfig(subModuleNameConfig);

	}

	private GtnUIFrameworkComponentConfig addBRMMSearchGeneralSearcField(
			List<GtnUIFrameworkComponentConfig> componentList, String componentId, String componentName,
			GtnUIFrameworkComponentType componentType, GtnUIFrameworkConditionalValidationType validationType) {
		GtnUIFrameworkLayoutConfig brmmGeneralLayout = new GtnUIFrameworkLayoutConfig();
		brmmGeneralLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(componentId + "layout", true,
				"brmmSearchCriteriaLayout");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig brmmGeneralConfig = configProvider.getUIFrameworkComponentConfig(componentId,
				true, componentId + "layout", componentType);
		brmmGeneralConfig.setComponentName(componentName);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(validationType));
		brmmGeneralConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(brmmGeneralConfig);

		return brmmGeneralConfig;
	}

	private void addCMSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnBRMMSearch01", true, GtnFrameworkBrmConstants.BRM_SEARCH_BUTTON_CRITERIA_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cmSearchButtonConfig.setComponentName("SEARCH");

		componentList.add(cmSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> cmSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("brmmResultTable");

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkBrmConstants.SUBMODULENAME, GtnFrameworkBrmConstants.BUSINESS_ROLE_NAME));

		cmSearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig loadTablePropertiesActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_TABLE_ACTION);

		List<Object> tablePropertiesActionParams = new ArrayList<>();
		tablePropertiesActionParams.add("brmmPropertyResultTable");

		loadTablePropertiesActionConfig.setActionParameterList(tablePropertiesActionParams);
		loadTablePropertiesActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkBrmConstants.SUBMODULENAME, GtnFrameworkBrmConstants.BUSINESS_ROLE_NAME));

		cmSearchActionConfigList.add(loadTablePropertiesActionConfig);

		cmSearchButtonConfig.setGtnUIFrameWorkActionConfigList(cmSearchActionConfigList);

	}

	private void addCMResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnBRMMReset01", true, GtnFrameworkBrmConstants.BRM_SEARCH_BUTTON_CRITERIA_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("RESET");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		params.add(Arrays.asList(GtnFrameworkBrmConstants.BUSINESS_ROLE_NAME, GtnFrameworkBrmConstants.MODULE_NAME,
				GtnFrameworkBrmConstants.SUBMODULENAME));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, null, null, GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPropertyTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig propertyResultConfig = configProvider.getUIFrameworkComponentConfig(
				"brmmPropertyResultTable", true, "brmmPropertyResultlayout", GtnUIFrameworkComponentType.DATATABLE);
		propertyResultConfig.setComponentName(GtnFrameworkBrmConstants.RESULT_TABLE);
		propertyResultConfig.setComponentWidth("100%");

		componentList.add(propertyResultConfig);

		GtnUIFrameworkPagedTableConfig propertyResults = new GtnUIFrameworkPagedTableConfig();
		propertyResults.setEditable(true);
		propertyResults.setFilterBar(true);
		propertyResults.setPageLength(10);
		propertyResults
				.setTableColumnDataType(new Class<?>[] { String.class, String.class, Boolean.class, Boolean.class });
		propertyResults.setTableVisibleHeader(
				new String[] { "Property Name", "Table Name", "Visible", GtnFrameworkBrmConstants.EDITABLE });
		propertyResults.setTableColumnMappingId(new Object[] { "propertyName", "tableName",
				GtnFrameworkBrmConstants.VISIBLE, GtnFrameworkBrmConstants.EDITABLE });
		propertyResults.setModuleName(GtnFrameworkBrmConstants.BUSINESS_ROLE_MODULE_MASTER);
		propertyResults.setQueryName("getGtnModuleTablePropertyDetailsQuery");
		propertyResults.setEditableColumnList(
				Arrays.asList(GtnFrameworkBrmConstants.VISIBLE, GtnFrameworkBrmConstants.EDITABLE));
		propertyResults.setCheckAllColumnList(
				Arrays.asList(GtnFrameworkBrmConstants.VISIBLE, GtnFrameworkBrmConstants.EDITABLE));
		propertyResults.addColumnCheckActionConfig(
				configProvider.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DATA_TABLE_CHECKALL_ACTION));
		propertyResults.setEditableField(getFieldFactory(2));
		propertyResults.setResultSetUrl("/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
				+ GtnWsModuleAuthorizationConstants.GTN_GET_COMPONENT_SECURITY_DETAILS_URI);

		propertyResultConfig.setGtnPagedTableConfig(propertyResults);
	}

	private void addBRMMModuleTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				"brmmModuleResultTable", true, "brmmModuleResultlayout", GtnUIFrameworkComponentType.DATATABLE);
		searchResultConfig.setComponentName(GtnFrameworkBrmConstants.RESULT_TABLE);

		searchResultConfig.setComponentWidth("100%");

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig moduleDeatilsResults = new GtnUIFrameworkPagedTableConfig();
		moduleDeatilsResults.setEditable(true);
		moduleDeatilsResults.setFilterBar(true);
		moduleDeatilsResults.setPageLength(10);
		moduleDeatilsResults.setTableColumnDataType(new Class<?>[] { String.class, String.class, Boolean.class });
		moduleDeatilsResults
				.setTableVisibleHeader(new String[] { "Module Name", "Sub Module Name", "Component Update" });
		moduleDeatilsResults.setTableColumnMappingId(new Object[] { GtnFrameworkBrmConstants.MODULE_NAME,
				GtnFrameworkBrmConstants.SUBMODULENAME, GtnFrameworkBrmConstants.COMPONENT_UPDATE });
		moduleDeatilsResults.setModuleName(GtnFrameworkBrmConstants.BUSINESS_ROLE_MODULE_MASTER);
		moduleDeatilsResults.setQueryName("getComponentUpdateSelectQuery");
		moduleDeatilsResults.setEditableColumnList(Arrays.asList(GtnFrameworkBrmConstants.COMPONENT_UPDATE));
		moduleDeatilsResults.setCheckAllColumnList(Arrays.asList(GtnFrameworkBrmConstants.COMPONENT_UPDATE));
		moduleDeatilsResults.addColumnCheckActionConfig(
				configProvider.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DATA_TABLE_CHECKALL_ACTION));
		moduleDeatilsResults.setEditableField(getFieldFactory(1));
		moduleDeatilsResults.setResultSetUrl("/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
				+ GtnWsModuleAuthorizationConstants.GTN_GET_COMPONENT_SECURITY_DETAILS_URI);
		searchResultConfig.setGtnPagedTableConfig(moduleDeatilsResults);
		searchResultConfig.setResetToDefaultAllowed(false);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_TABLE_ACTION);
		List<Object> actionParams = new ArrayList<>();
		actionParams.add("brmmModuleResultTable");
		loadDataTableActionConfig.setActionParameterList(actionParams);
		moduleDeatilsResults.addPostCreationActionConfig(loadDataTableActionConfig);
	}

	private void addBRMMSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnBRMMSave01", true, "brmmSaveButtonCriteriaLayout", GtnUIFrameworkComponentType.BUTTON);
		cmSearchButtonConfig.setComponentName("Save");
		GtnUIFrameWorkActionConfig searchActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchActionConfig.addActionParameter(GtnFrameworkBusinessRoleMasterSave.class.getName());
		cmSearchButtonConfig.addGtnUIFrameWorkActionConfig(searchActionConfig);
		componentList.add(cmSearchButtonConfig);
	}

	private void addBRMMModuleDetailsSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnBRMMModuleDetailsSave01", true, "brmmModuleDetailsSaveButtonCriteriaLayout",
				GtnUIFrameworkComponentType.BUTTON);
		cmSearchButtonConfig.setComponentName("Save Module Deatils");
		GtnUIFrameWorkActionConfig searchActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchActionConfig.addActionParameter(GtnUIFrameworkModuleDetailsSaveAction.class.getName());
		cmSearchButtonConfig.addGtnUIFrameWorkActionConfig(searchActionConfig);
		componentList.add(cmSearchButtonConfig);
	}

	private List<GtnUIFrameworkComponentConfig> getFieldFactory(int count) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
			fieldConfig.setEnable(true);
			editableFields.add(fieldConfig);
		}
		return editableFields;
	}
}
