/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSTableFieldFactoryFieldUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRebateSetupCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRebateScheduleSetUpTabConfig {

	private static final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public void addPriceSchedulePriocingTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SETUP_TAB, false, GtnFrameworkRSConstants.REBATE_SETUP_TAB);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);
		massUpdatePanel(componentList);
		reordLayout(componentList);
		companiesResultPanel(componentList);
		addExcelButtonComponent(componentList);

	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SETUP_TAB);
		layoutConfig.setAuthorizationIncluded(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		layoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12));
		componentList.add(layoutConfig);
		companiesTabInformationFields(componentList);

	}

	private void reordLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupRecordTypelayout", true, GtnFrameworkRSConstants.REBATE_SETUP_TAB);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig recordType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_RECORD_TYPE, true, "rebateSetupRecordTypelayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		recordType.setAuthorizationIncluded(true);
		recordType.setComponentName("Record:");

		recordType.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reloadRsSetupTableActionConfig = new GtnUIFrameWorkActionConfig();
		reloadRsSetupTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		reloadRsSetupTableActionConfig
				.addActionParameter(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE);
		actionConfigList.add(reloadRsSetupTableActionConfig);
		recordType.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkOptionGroupConfig reordTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		reordTypeConfig.setValuesFromService(false);
		reordTypeConfig.setDefaultSelection(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		reordTypeConfig.setItemValues(Arrays.asList("Current", "History", "Future"));
		reordTypeConfig.setIsMultiSelect(true);
		recordType.setGtnUIFrameworkOptionGroupConfig(reordTypeConfig);

		recordType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(recordType);

	}

	private void companiesTabInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addCompaniesMassCheck(componentList);
		addCompaniesMassFields(componentList);
		addCompaniesMassDateField(componentList);
		addCompaniesMassDropDown(componentList);
		addCompaniesMassCustomTextField(componentList);
		addCompaniesMassTextField(componentList);
		addCompaniesPopulateButtons(componentList);

	}

	private void addCompaniesMassCheck(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupmassupdatelayout", true, GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				"RSRebateSetupMassCheck", true, "rebateSetupmassupdatelayout", GtnUIFrameworkComponentType.OPTIONGROUP);
		companyQualifierName.setComponentName("Mass Update");

		componentList.add(companyQualifierName);

		GtnUIFrameworkOptionGroupConfig companyQualifierNameConfig = new GtnUIFrameworkOptionGroupConfig();
		companyQualifierNameConfig.setValuesFromService(false);
		companyQualifierNameConfig.setItemValues(Arrays.asList("Enable", "Disable"));

		companyQualifierName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		companyQualifierName.setGtnUIFrameworkOptionGroupConfig(companyQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		customAction.setFieldValues(
				Arrays.asList("rebateSetupTabMassField", GtnFrameworkRSConstants.REBATE_SETUP_TAB_POPULATE_BUTTON,
						GtnFrameworkRSConstants.REBATE_SETUP_TAB_POPULATE_ALL_BUTTON,
						GtnFrameworkRSConstants.REBATE_SETUP_TAB_MASS_DATE_FEILD,
						GtnFrameworkRSConstants.REBATE_SETUP_TAB_MASS_DROP_DOWN, "massCustomTextField"));

		actionConfigList.add(customAction);
		companyQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCompaniesMassFields(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabMassFeildlayout", true, GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"rebateSetupTabMassField", true, "rebateSetupTabMassFeildlayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Field :");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();

		companyTypeConfig.setItemValues(Arrays.asList("RS Status", "Start Date", "End Date"));
		companyType.setGtnComboboxConfig(companyTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.MASS_FIELD_VALUE_CHANGE);

		customAction.setFieldValues(Arrays.asList(GtnFrameworkRSConstants.REBATE_SETUP_TAB_MASS_DATE_FEILD,
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_MASS_DROP_DOWN));
		actionConfigList.add(customAction);
		companyType.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCompaniesMassDateField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabMassDateFeildlayout", true,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_MASS_DATE_FEILD, true, "rebateSetupTabMassDateFeildlayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyType.setComponentName(GtnFrameworkCommonConstants.VALUE);
		companyType.setVisible(false);
		componentList.add(companyType);

	}

	private void addCompaniesMassDropDown(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabMassDropDownlayout", true, GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_MASS_DROP_DOWN, true, "rebateSetupTabMassDropDownlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(companyType);
		companyType.setVisible(false);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		companyType.setGtnComboboxConfig(companyTypeConfig);

	}

	private void addCompaniesMassCustomTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabMassCustomTextFieldlayout", true,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentCompanyNo = configProvider.getUIFrameworkComponentConfig(
				"massCustomTextField", true, "rebateSetupTabMassCustomTextFieldlayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentCompanyNo.setComponentName(GtnFrameworkCommonConstants.VALUE);
		parentCompanyNo.setVisible(false);

		parentCompanyNo.setComponentStyle(Arrays.asList("searchicon"));
		componentList.add(parentCompanyNo);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<String> popupActionParam = new ArrayList<>();
		popupActionParam.add("parenPriceScheduleIDView");
		popupActionParam.add("Parent Rebate Schedule ID");
		popupActionParam.add("70%");
		popupActionParam.add("70%");

		popupActionConfig.addActionParameter(popupActionParam);

		parentCompanyNo.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCompaniesMassTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabMassTextFieldlayout", true,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig("massTextField",
				true, "rebateSetupTabMassTextFieldlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName(GtnFrameworkCommonConstants.VALUE);
		companyIdConfig.setVisible(false);

		componentList.add(companyIdConfig);

	}

	private void addCompaniesPopulateButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		massUpdatePopulateButton(componentList);
		massUpdatePopulateAllButton(componentList);
	}

	private void massUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabPopulateButtonlayout", true,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_POPULATE_BUTTON, true, "rebateSetupTabPopulateButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Populate");
		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validateAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION,
				GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert.class.getName(), "populate");

		actionConfigList.add(validateAction);
		GtnUIFrameWorkActionConfig customAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.RS_POPULATE_ACTION, "populate");

		customAction.setFieldValues(Arrays.asList("CFPCompanyAdditionSearchField", "CFPCompanyAdditionSearchValue",
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_RECORD_TYPE));
		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void massUpdatePopulateAllButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateSetupTabPopulateAllButtonlayout", true,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_POPULATE_ALL_BUTTON, true,
				"rebateSetupTabPopulateAllButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Populate All");
		componentList.add(attachButtonConfig);
		// GtnFrameworkrebateSetupTabPopulateAction
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.RS_POPULATE_ACTION, "populateAll");

		customAction.setFieldValues(Arrays.asList("CFPCompanyAdditionSearchField", "CFPCompanyAdditionSearchValue",
				GtnFrameworkRSConstants.REBATE_SETUP_TAB_RECORD_TYPE));
		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void companiesResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getUIFrameworkComponentConfig("rebateSetupTabResultPanel",
				true, GtnFrameworkRSConstants.REBATE_SETUP_TAB, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentName("Results");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panel);
		companiesResultLayout(componentList);
	}

	private void companiesResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig("rebateSetupTabResultLayout", true, "rebateSetupTabResultPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);

		rebateSetupTabResultDataTable(componentList);
	}

	private void rebateSetupTabResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE, true, "rebateSetupTabResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		attachResultConfig.setAuthorizationIncluded(true);
		attachResultConfig.setComponentName("Results");
		attachResultConfig.setComponentHight("400px");
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		attachResultConfig.setComponentStyle(tableStyle);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"rebateScheduleRebateSetup", "rebateScheduleRebateSetup");
		attachResultConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(true);
		attachResults.setTableColumnDataType(
				new Class[] { Boolean.class, String.class, String.class, Integer.class, Date.class, Date.class,String.class });
		attachResults.setTableVisibleHeader(GtnFrameworkRSConstants.getRsSetupTabTableHeaders());
		attachResults.setTableColumnMappingId(GtnFrameworkRSConstants.getRsSetupTabVisibleColumns());
		attachResults.setExtraColumn(new Object[] { "systemId"  });
		attachResults.setExtraColumnDataType(new Class[] { String.class});
		attachResults.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		attachResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_SCHEDULE);
		attachResults.setEditable(true);
		attachResults.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID, "itemNo",
				"itemName",GtnFrameworkRSConstants.RS_STATUS, "rsStartDate", "rsEndDate"));
		attachResults.setRecordTypeComponentId(GtnFrameworkRSConstants.REBATE_SETUP_TAB_RECORD_TYPE);
		attachResults.setRecordTypeStartDate("rsStartDate");
		attachResults.setRecordTypeEndDate("rsEndDate");

		attachResults.setEditableField(createTableFieldFactoryComponents(attachResults.getEditableColumnList()));

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkRebateSetupCheckAllAction.class.getName());
		actionConfigList.add(checkAllAction);
		attachResults.setColumnCheckActionConfigList(actionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig("rsAddExcel",
				true, GtnFrameworkRSConstants.REBATE_SETUP_TAB, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		excelButtonConfig.setComponentName("true;");
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"Rebate Setup", true, GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE, false);
		gtnUIFrameworkExcelButtonConfig
				.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		gtnUIFrameworkExcelButtonConfig
				.setHelperTableMapedPropertyIdList(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS));
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	public static List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType;
			if (GtnFrameworkRSConstants.getTextFieldProperties().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
			} else if (GtnFrameworkRSConstants.getDateFieldProperties().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
			} else if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.CHECKBOX;
			} else if (GtnFrameworkRSConstants.getPopUpTextFieldProperties().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.POPUPTEXTFIELD;
			} else {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			}
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			fieldConfig.setEnable(!GtnFrameworkRSConstants.getNonEditableList().contains(propertyId));
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig customAction = configProvider
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customAction.addActionParameter(GtnFrameworkRSTableFieldFactoryFieldUpdateAction.class.getName());
			actionConfigList.add(customAction);
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.POPUPTEXTFIELD)) {
				fieldConfig.addComponentStyle("searchicon");
				fieldConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
			} else {
				fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);
			}

			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig comboBoxConfig = configProvider.getComboBoxConfig(
						getComboBoxType(propertyId), GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				fieldConfig.setGtnComboboxConfig(comboBoxConfig);
			}

			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	static String getComboBoxType(String propertyId) {
		if (propertyId.equals(GtnFrameworkRSConstants.RS_STATUS)) {
			return "STATUS";
		} else if (propertyId.equals("psPPPriceType")) {
			return "ItemPricingQualifier";
		} else if (propertyId.equals("psBasePriceType")) {
			return "BASE_PRICE_TYPE";
		} else if (propertyId.equals("psSubseqPeriodPriceType")) {
			return "ItemPricingQualifier";
		} else if (propertyId.equals("psToleranceInterval") || propertyId.equals("psResetInterval")) {
			return "PRICE_TOLERANCE_INTERVAL";
		} else if (propertyId.equals("psToleranceFreq") || propertyId.equals("psResetFrequency")) {
			return "PRICE_TOLERANCE_FREQUENCY";
		} else if (propertyId.equals("psToleranceType")) {
			return "PRICE_TOLERANCE_TYPE";
		} else if (propertyId.equals("psResetType")) {
			return "RESET_TYPE";
		} else if (propertyId.equals("psResetEligible") || propertyId.equals("psResetPriceType")
				|| propertyId.equals("psNetPriceType") || propertyId.equals("psNetResetPriceType")) {
			return "LOCKED_STATUS";
		} else {
			return null;
		}
	}
}