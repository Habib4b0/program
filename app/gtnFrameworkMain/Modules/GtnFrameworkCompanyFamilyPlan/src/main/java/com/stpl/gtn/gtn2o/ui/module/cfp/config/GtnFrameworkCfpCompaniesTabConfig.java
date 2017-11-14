package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCfpCompaniesTabConfig {

	public void addCompaniesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkComponentConfig companiesTabLayoutConfig = componentConfig.getRootLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB, GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, true);
		companiesTabLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(companiesTabLayoutConfig);
		massUpdatePanel(componentList, componentConfig);
		addRecordPanel(componentList, componentConfig);
		companiesResultPanel(componentList, componentConfig);
		addExcelButtonComponent(componentList, componentConfig);
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig massUpdateLayoutConfig = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB);
		massUpdateLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		massUpdateLayoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12));
		componentList.add(massUpdateLayoutConfig);
		companiesTabInformationFields(componentList, componentConfig);

	}

	private void addRecordPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig recordPanelLayoutConfig = componentConfig.getGtnCssLayoutConfig(
				"cfpCompaniesRecordPanel", true, GtnFrameworkCommonConstants.CFP_COMPANIES_TAB,
				GtnUIFrameworkLayoutType.COL1_LAYOUT);
		recordPanelLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_40);
		recordPanelLayoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(recordPanelLayoutConfig);
		reordLayout(componentList, componentConfig);

	}

	private void reordLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig recordTypeLayout = componentConfig
				.getHorizontalLayoutConfig("CFPCompaniesRecordTypelayout", true, "cfpCompaniesRecordPanel");
		componentList.add(recordTypeLayout);
		recordTypeLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10,
				GtnFrameworkCssConstants.PADDING_LEFT10, GtnFrameworkCssConstants.IYGDSAYGKHYA));

		GtnUIFrameworkComponentConfig recordType = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompaniesTabRecordType", true, recordTypeLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		recordType.setAuthorizationIncluded(true);
		recordType.setComponentName("Record:");
		recordType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(recordType);

		GtnUIFrameworkOptionGroupConfig reordTypeConfig = componentConfig.getOptionGroupConfig(
				Arrays.asList(GtnFrameworkCommonStringConstants.CURRENT, GtnFrameworkCommonStringConstants.HISTORY,
						GtnFrameworkCommonStringConstants.FUTURE),
				GtnFrameworkCommonStringConstants.STRING_EMPTY, false);
		reordTypeConfig.setIsMultiSelect(true);
		recordType.setGtnUIFrameworkOptionGroupConfig(reordTypeConfig);

		List<GtnUIFrameWorkActionConfig> recordTypeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMPANIES_RECORD_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList("cfpCompaniesTabRecordType"));
		recordTypeActionConfigList.add(customAction);
		recordType.setGtnUIFrameWorkActionConfigList(recordTypeActionConfigList);
	}

	private void companiesTabInformationFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCompaniesMassCheck(componentList, componentConfig);
		addCompaniesMassFields(componentList, componentConfig);
		addCompaniesMassDateField(componentList, componentConfig);
		addCompaniesMassDropDown(componentList, componentConfig);
		addCompaniesPopulateButtons(componentList, componentConfig);
	}

	private void addCompaniesMassCheck(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig massUpdateLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPCompaniesmassupdatelayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT);
		massUpdateLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		componentList.add(massUpdateLayout);

		GtnUIFrameworkComponentConfig massCheck = componentConfig.getUIFrameworkComponentConfig("cfpMassCheck", true,
				massUpdateLayout.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		massCheck.setAuthorizationIncluded(true);
		massCheck.setComponentName("Mass Update");
		massCheck.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		massCheck.setParentComponentId("CFPCompaniesmassupdatelayout");
		componentList.add(massCheck);

		GtnUIFrameworkOptionGroupConfig massCheckConfig = componentConfig.getOptionGroupConfig(
				Arrays.asList(GtnFrameworkCommonStringConstants.ENABLE, GtnFrameworkCommonStringConstants.DISABLE),
				GtnFrameworkCommonStringConstants.DISABLE, false);
		massCheck.setGtnUIFrameworkOptionGroupConfig(massCheckConfig);

		List<GtnUIFrameWorkActionConfig> massCheckActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		customAction.setFieldValues(Arrays.asList("cfpCompaniesTabMassField", "cfpCompaniesTabPopulateButton",
				"cfpCompaniesTabPopulateAllButton", GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_DATE_FEILD,
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_DROP_DOWN));
		massCheckActionConfigList.add(customAction);
		massCheck.setGtnUIFrameWorkActionConfigList(massCheckActionConfigList);
	}

	private void addCompaniesMassFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig massFieldLayout = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_FEILD_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT);
		massFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		massFieldLayout.setSpacing(true);
		componentList.add(massFieldLayout);

		GtnUIFrameworkComponentConfig massFieldType = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompaniesTabMassField", true, massFieldLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		massFieldType.setAuthorizationIncluded(true);
		massFieldType.setComponentName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		massFieldType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		massFieldType.setEnable(false);
		componentList.add(massFieldType);

		GtnUIFrameworkComboBoxConfig massFieldTypeConfig = new GtnUIFrameworkComboBoxConfig();
		massFieldTypeConfig.setItemValues(Arrays.asList("CFP Start Date", "CFP End Date", "CFP Status"));
		massFieldType.setGtnComboboxConfig(massFieldTypeConfig);

		List<GtnUIFrameWorkActionConfig> massFieldTypeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMPANIES_MASSFIELD_VALUE_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_DATE_FEILD,
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_DROP_DOWN));
		massFieldTypeActionConfigList.add(customAction);
		massFieldType.setGtnUIFrameWorkActionConfigList(massFieldTypeActionConfigList);

	}

	private void addCompaniesMassDateField(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig massDateField = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_DATE_FEILD, true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_FEILD_LAYOUT, GtnUIFrameworkComponentType.DATEFIELD);
		massDateField.setComponentName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		massDateField.setVisible(false);
		componentList.add(massDateField);
		massDateField.setEnable(false);

	}

	private void addCompaniesMassDropDown(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig massDropDown = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_DROP_DOWN, true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_MASS_FEILD_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		massDropDown.setComponentName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		componentList.add(massDropDown);
		massDropDown.setVisible(false);
		massDropDown.setEnable(false);
		massDropDown.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));

		GtnUIFrameworkComboBoxConfig massDropDownConfig = componentConfig.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		massDropDown.setGtnComboboxConfig(massDropDownConfig);

	}

	private void addCompaniesPopulateButtons(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		massUpdatePopulateButton(componentList, componentConfig);
		massUpdatePopulateAllButton(componentList, componentConfig);
	}

	private void massUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig populateBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpCompaniesTabPopulateButtonlayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT);
		populateBtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(populateBtnLayout);

		GtnUIFrameworkComponentConfig populateButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompaniesTabPopulateButton", true, populateBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		populateButtonConfig.setAuthorizationIncluded(true);
		populateButtonConfig.setComponentName("Populate");
		populateButtonConfig.setEnable(false);
		componentList.add(populateButtonConfig);

		List<GtnUIFrameWorkActionConfig> populateActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMPANIES_TAB_POPULATE_ACTION);
		customAction.addActionParameter("populate");
		populateActionConfigList.add(customAction);
		populateButtonConfig.setGtnUIFrameWorkActionConfigList(populateActionConfigList);
	}

	private void massUpdatePopulateAllButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig populateAllBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpCompaniesTabPopulateAllButtonlayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT);
		populateAllBtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(populateAllBtnLayout);

		GtnUIFrameworkComponentConfig populateAllButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompaniesTabPopulateAllButton", true, "cfpCompaniesTabPopulateAllButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		populateAllButtonConfig.setAuthorizationIncluded(true);
		populateAllButtonConfig.setComponentName("Populate All");
		componentList.add(populateAllButtonConfig);
		populateAllButtonConfig.setEnable(false);
		List<GtnUIFrameWorkActionConfig> populateAllActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMPANIES_TAB_POPULATE_ACTION);
		populateAllActionConfigList.add(customAction);
		customAction.addActionParameter("populateAll");
		populateAllButtonConfig.setGtnUIFrameWorkActionConfigList(populateAllActionConfigList);
	}

	private void companiesResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultPanel = componentConfig.getPanelConfig("cfpCompaniesTabResultPanel", true,
				GtnFrameworkCommonConstants.CFP_COMPANIES_TAB);
		resultPanel.setComponentName("Results");
		resultPanel.setAuthorizationIncluded(true);
		componentList.add(resultPanel);
		companiesResultLayout(componentList, componentConfig);
	}

	private void companiesResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultLayoutConfig = componentConfig
				.getHorizontalLayoutConfig("cfpCompaniesTabResultLayout", true, "cfpCompaniesTabResultPanel");
		resultLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultLayoutConfig);

		cfpCompaniesTabResultDataTable(componentList, componentConfig);

	}

	private void cfpCompaniesTabResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultTable = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompaniesTabResultDataTable", true, "cfpCompaniesTabResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		resultTable.setAuthorizationIncluded(true);
		resultTable.setComponentName("Results");
		resultTable.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		resultTable.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTable);

		GtnUIFrameworkPagedTableConfig resultTableConfig = componentConfig.getPagedTableConfig(true, true,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE,
				"companyFamilyPlan", "");

		resultTableConfig.setTableColumnDataType(new Class[] { Boolean.class, String.class, String.class, String.class,
				Integer.class, Date.class, Date.class, String.class, String.class, String.class, String.class,
				String.class, Date.class, Date.class, String.class, Date.class, String.class });
		resultTableConfig.setTableVisibleHeader(GtnFrameworkCfpStringContants.CFP_COMPANIES_HEADERS_LIST
				.toArray(new String[GtnFrameworkCfpStringContants.CFP_COMPANIES_HEADERS_LIST.size()]));
		resultTableConfig.setTableColumnMappingId(GtnFrameworkCfpStringContants.CFP_COMPANIES_COLUMN_LIST.toArray());
		resultTableConfig.setExtraColumn(new Object[] { "imtdCfpDetailsSid", "companyFamilyPlanStatusHelperValue" });
		resultTableConfig.setExtraColumnDataType(new Class[] { Integer.class, String.class });
		resultTableConfig.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		resultTableConfig.setEditable(true);
		resultTableConfig.setInvisibleFilterPropertyIds(GtnFrameworkCommonConstants.CHECK_RECORD_ID);

		resultTableConfig.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID, "companyId",
				"companyNo", "companyName", GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS_VALUE,
				"companyFamilyPlanStartDate", "companyFamilyPlanEndDate", "companyStatusValue", "companyTypeValue",
				"tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate",
				"modifiedBy", "createdDate", "createdBy"));

		resultTableConfig.setEditableField(
				createTableFieldFactoryComponents(resultTableConfig.getEditableColumnList(), componentConfig));
		resultTableConfig.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));

		List<GtnUIFrameWorkActionConfig> checkAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllActionConfig = new GtnUIFrameWorkActionConfig();
		checkAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllActionConfig
				.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMPANIES_TAB_TABLE_CHECK_ACTION);
		checkAllConflist.add(checkAllActionConfig);

		resultTableConfig.setColumnCheckActionConfigList(checkAllConflist);
		resultTable.setGtnPagedTableConfig(resultTableConfig);
	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds,
			GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = getComponentType(propertyId);
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			fieldConfig.setEnable(GtnFrameworkCfpStringContants.CFP_EDITABLEFIELD_LIST.contains(propertyId));
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig companyFamilyPlanStatusConfig = componentConfig.getComboBoxConfig(
						GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				fieldConfig.setGtnComboboxConfig(companyFamilyPlanStatusConfig);
			}
			if (fieldConfig.isEnable()) {
				List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
				GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
				customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_FIELD_FACTORY);
				actionConfigList.add(customAction);
				fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);
			}
			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	private GtnUIFrameworkComponentType getComponentType(String propertyId) {

		if (GtnFrameworkCfpStringContants.CFP_TEXTFIELD_PROPERTIES_LIST.contains(propertyId)) {
			return GtnUIFrameworkComponentType.TEXTBOX;
		} else if (GtnFrameworkCfpStringContants.CFP_DATEFIELD_PROPERTIES_LIST.contains(propertyId)) {
			return GtnUIFrameworkComponentType.DATEFIELD;
		} else if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(propertyId)) {
			return GtnUIFrameworkComponentType.CHECKBOX;
		}
		return GtnUIFrameworkComponentType.COMBOBOX;
	}

	/**
	 * 
	 * @param componentList
	 * @param componentConfig
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig excelBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnExcelButtonlayout",
				true, GtnFrameworkCommonConstants.CFP_COMPANIES_TAB);
		componentList.add(excelBtnLayout);

		GtnUIFrameworkComponentConfig excelButton = new GtnUIFrameworkComponentConfig();
		excelButton.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButton.setAuthorizationIncluded(true);
		excelButton.setAddToParent(true);
		excelButton.setParentComponentId("gtnExcelButtonlayout");
		componentList.add(excelButton);

		GtnUIFrameworkExcelButtonConfig cfpExcelButtonConfig = componentConfig.getExcelBtnconfig("CFP Companies", true,
				"cfpCompaniesTabResultDataTable", false);
		cfpExcelButtonConfig.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		cfpExcelButtonConfig.setHelperTableMapedPropertyIdList(
				Arrays.asList(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS_VALUE));
		excelButton.setGtnUIFrameworkExcelButtonConfig(cfpExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(cfpExcelButtonConfig);
		excelButton.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
			GtnFrameworkComponentConfigProvider componentConfig) {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS_VALUE, "companyStatusValue",
				"companyTypeValue", "tradeClass", "companyCategory", "modifiedBy", "createdBy" };
		String[] listNameList = { GtnFrameworkCommonConstants.STATUS, GtnFrameworkCommonConstants.STATUS,
				"COMPANY_TYPE", "COMPANY_TRADE_CLASS", "COMPANY_CATEGORY", "USERS", "USERS" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			GtnUIFrameworkComboBoxConfig customCompoBoxFilter = componentConfig.getComboBoxConfig(listNameList[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			customCompoBoxFilter.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.setGtnComboboxConfig(customCompoBoxFilter);
			customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}

}
