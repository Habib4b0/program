/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceTabMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSPriceTabPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnUIFrameworkPSPopulateCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPricingTabFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkPSPricingTabConfig {

	private static final String PS_PRICING_TAB_TAB_MASS_FEILDLAYOUT = "psPricingTabTabMassFeildlayout";
	private static final String PS_PRICING_TAB_MASS_TEXT_FIELDLAYOUT = "PSPricingTabMassTextFieldlayout";
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addPriceSchedulePricingTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingConfig = configProvider
				.getVerticalLayoutConfig(GtnFrameworkCommonConstants.PRICING_TAB, false, null);
		psPricingConfig.setTabComponent(true);
		psPricingConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psPricingConfig);
		massUpdatePanel(componentList);
		psPricingRecordLayout(componentList);
		psPricingResultPanel(componentList);
		addPsPricingExcelButtonComponent(componentList);
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdatePanel = configProvider.getPanelConfig("pricingMassUpdatePanel",true,GtnFrameworkCommonConstants.PRICING_TAB);
		massUpdatePanel.setAuthorizationIncluded(true);
		massUpdatePanel.setComponentName("Mass Update");
		componentList.add(massUpdatePanel);
		addFieldLayout(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psFieldLayoutLayout = configProvider.getCssLayoutConfig(GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT, true,
						"pricingMassUpdatePanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		psFieldLayoutLayout.setComponentStyle(styleList);
		psFieldLayoutLayout.setComponentWidth("75%");
		psFieldLayoutLayout.setComponentHight("55%");
		componentList.add(psFieldLayoutLayout);
		psPricingTabFields(componentList);
	}

	private void psPricingRecordLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingRecordLayout = configProvider.getHorizontalLayoutConfig(
				"PSPricingTabRecordTypelayout", true, GtnFrameworkCommonConstants.PRICING_TAB);
		psPricingRecordLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(psPricingRecordLayout);

		GtnUIFrameworkComponentConfig psPricingRecordType = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabRecordType", true, "PSPricingTabRecordTypelayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		psPricingRecordType.setComponentName("Record:");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reloadPSPricingTableActionConfig = new GtnUIFrameWorkActionConfig();
		reloadPSPricingTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		reloadPSPricingTableActionConfig
				.addActionParameter(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE);
		actionConfigList.add(reloadPSPricingTableActionConfig);
		psPricingRecordType.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(psPricingRecordType);

		GtnUIFrameworkOptionGroupConfig psPricingRecordTypeConfig = configProvider
				.getOptionGroupConfig(Arrays.asList("Current", "History", "Future"), "", false);
		psPricingRecordTypeConfig.setIsMultiSelect(true);
		psPricingRecordType.setGtnUIFrameworkOptionGroupConfig(psPricingRecordTypeConfig);
		psPricingRecordType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
	}

	private void psPricingTabFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addPsPricingMassCheck(componentList);
		addPsPricingMassFields(componentList);
		addPsPricingMassDateField(componentList);
		addPsPricingMassDropDown(componentList);
		addPsPricingMassTextField(componentList);
		addPsPricingMassCustomTextField(componentList);
		addPsPricingPopulateButtons(componentList);

	}

	private void addPsPricingMassCheck(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingMassUpdateLayout = configProvider.getHorizontalLayoutConfig(
				"PSPricingTabmassupdatelayout", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		psPricingMassUpdateLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(psPricingMassUpdateLayout);

		GtnUIFrameworkComponentConfig psPricingMassUpdate = configProvider.getUIFrameworkComponentConfig(
				"cfpPricingTabMassCheck", true, "PSPricingTabmassupdatelayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		psPricingMassUpdate.setAuthorizationIncluded(true);
		psPricingMassUpdate.setComponentName("Mass Update");
		componentList.add(psPricingMassUpdate);

		GtnUIFrameworkOptionGroupConfig psPricingMassUpdateConfig = configProvider.getOptionGroupConfig(
				Arrays.asList("Enable", GtnFrameworkCommonStringConstants.DISABLE),
				GtnFrameworkCommonStringConstants.DISABLE, false);
		psPricingMassUpdate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		psPricingMassUpdate.setGtnUIFrameworkOptionGroupConfig(psPricingMassUpdateConfig);

		List<GtnUIFrameWorkActionConfig> psPricingMassUpdateActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig psPricingMassUpdateCustomAction = new GtnUIFrameWorkActionConfig();
		psPricingMassUpdateCustomAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		psPricingMassUpdateCustomAction.setFieldValues(Arrays.asList("psPricingTabTabMassField",
				"psPricingTabTabPopulateButton", "psPricingTabTabPopulateAllButton", "psPricingTabTabMassDateFeild",
				"psPricingTabTabMassDropDown", "psPricingTabTabmassTextField", "psPricingTabTabmassCustomTextField"));
		psPricingMassUpdateActionConfigList.add(psPricingMassUpdateCustomAction);
		psPricingMassUpdate.setGtnUIFrameWorkActionConfigList(psPricingMassUpdateActionConfigList);

	}

	private void addPsPricingMassFields(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingMassFieldLayout = configProvider.getHorizontalLayoutConfig(
				PS_PRICING_TAB_TAB_MASS_FEILDLAYOUT, true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		psPricingMassFieldLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(psPricingMassFieldLayout);

		GtnUIFrameworkComponentConfig psPricingMassFields = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabMassField", true, PS_PRICING_TAB_TAB_MASS_FEILDLAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig psPricingMassTextFieldLoading = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabmassTextFieldLoading", true, PS_PRICING_TAB_TAB_MASS_FEILDLAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		psPricingMassFields.setComponentName("Field  ");
		psPricingMassTextFieldLoading.setComponentName(GtnFrameworkCommonConstants.VALUE);
		psPricingMassFields.setEnable(false);
		psPricingMassTextFieldLoading.setEnable(false);
		componentList.add(psPricingMassFields);
		componentList.add(psPricingMassTextFieldLoading);
		
		GtnUIFrameworkComboBoxConfig psPricingMassFieldsConfig = new GtnUIFrameworkComboBoxConfig();
		psPricingMassFieldsConfig.setItemValues(
				Arrays.asList("Status", "Price", "CP Start Date", "CP End Date", "Price Type", "Suggested Price"));
		psPricingMassFields.setGtnComboboxConfig(psPricingMassFieldsConfig);

		List<GtnUIFrameWorkActionConfig> psPricingMassFieldsActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig psPricingMassFieldsCustomAction = new GtnUIFrameWorkActionConfig();
		psPricingMassFieldsCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		psPricingMassFieldsCustomAction
				.addActionParameter(GtnFrameworkPSPriceTabMassFieldValueChangeAction.class.getName());
		psPricingMassFieldsActionConfigList.add(psPricingMassFieldsCustomAction);
		psPricingMassFields.setGtnUIFrameWorkActionConfigList(psPricingMassFieldsActionConfigList);

	}

	private void addPsPricingMassDateField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingMassDateFieldLayout = configProvider.getHorizontalLayoutConfig(
				"psPricingTabTabMassDateFeildlayout", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		psPricingMassDateFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_34,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psPricingMassDateFieldLayout);

		GtnUIFrameworkComponentConfig psPricingMassDateField = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabMassDateFeild", true, "psPricingTabTabMassDateFeildlayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		psPricingMassDateField.setComponentName(GtnFrameworkCommonConstants.VALUE);
		psPricingMassDateField.setVisible(false);
		componentList.add(psPricingMassDateField);

	}

	private void addPsPricingMassDropDown(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingMassDropDownLayout = configProvider.getHorizontalLayoutConfig(
				"psPricingTabTabMassDropDownlayout", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		psPricingMassDropDownLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_34,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psPricingMassDropDownLayout);

		GtnUIFrameworkComponentConfig psPricingMassDropDown = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabMassDropDown", true, "psPricingTabTabMassDropDownlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		psPricingMassDropDown.setComponentName(GtnFrameworkCommonConstants.VALUE);
		psPricingMassDropDown.setVisible(false);
		componentList.add(psPricingMassDropDown);

		GtnUIFrameworkComboBoxConfig psPricingMassDropDownConfig = new GtnUIFrameworkComboBoxConfig();
		psPricingMassDropDownConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		psPricingMassDropDownConfig.setComboBoxType("CFP_TYPE");
		psPricingMassDropDown.setGtnComboboxConfig(psPricingMassDropDownConfig);

	}

	private void addPsPricingMassCustomTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingMassCustomTextFieldLayout = configProvider.getHorizontalLayoutConfig(
				"psPricingTabMassCustomTextFieldlayout", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		psPricingMassCustomTextFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_34,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psPricingMassCustomTextFieldLayout);

		GtnUIFrameworkComponentConfig psPricingMassCustomTextField = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabmassCustomTextField", true, "psPricingTabMassCustomTextFieldlayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		psPricingMassCustomTextField.setComponentName(GtnFrameworkCommonConstants.VALUE);
		psPricingMassCustomTextField.setVisible(false);
		psPricingMassCustomTextField.setComponentStyle(Arrays.asList("searchicon"));
		componentList.add(psPricingMassCustomTextField);

		List<GtnUIFrameWorkActionConfig> psPricingMassCustomTextFieldActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		psPricingMassCustomTextFieldActionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("rebatePlanPopUpSearchView");
		popupActionParam.add("Parent Price Schedule ID");
		popupActionParam.add("70%");
		popupActionParam.add("70%");

		popupActionConfig.setActionParameterList(popupActionParam);

		psPricingMassCustomTextField.setGtnUIFrameWorkActionConfigList(psPricingMassCustomTextFieldActionConfigList);

	}

	private void addPsPricingMassTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingMassTextFieldLayout = configProvider.getHorizontalLayoutConfig(
				PS_PRICING_TAB_MASS_TEXT_FIELDLAYOUT, true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		psPricingMassTextFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_34,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psPricingMassTextFieldLayout);

		GtnUIFrameworkComponentConfig psPricingMassTextField = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabmassTextField", true, PS_PRICING_TAB_MASS_TEXT_FIELDLAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		psPricingMassTextField.setComponentName(GtnFrameworkCommonConstants.VALUE);
		psPricingMassTextField.setVisible(false);
		componentList.add(psPricingMassTextField);

	}

	private void addPsPricingPopulateButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		massUpdatePopulateButton(componentList);
		massUpdatePopulateAllButton(componentList);
	}

	private void massUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdatePopulateButtonLayout = configProvider.getHorizontalLayoutConfig(
				"psPricingTabTabPopulateButtonlayout", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		massUpdatePopulateButtonLayout.addComponentStyle(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1);
		massUpdatePopulateButtonLayout.setComponentWidth("15%");
		massUpdatePopulateButtonLayout.setMargin(true);
		componentList.add(massUpdatePopulateButtonLayout);

		GtnUIFrameworkComponentConfig massUpdatePopulateButton = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabPopulateButton", true, "psPricingTabTabPopulateButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		massUpdatePopulateButton.setComponentName("Populate");
		componentList.add(massUpdatePopulateButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig populateCheck = new GtnUIFrameWorkActionConfig();
		populateCheck.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		populateCheck.addActionParameter(GtnUIFrameworkPSPopulateCheckAction.class.getName());
		actionConfigList.add(populateCheck);
		GtnUIFrameWorkActionConfig populateAction = new GtnUIFrameWorkActionConfig();
		populateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		populateAction.addActionParameter(GtnUIFrameworkPSPriceTabPopulateAction.class.getName());
		actionConfigList.add(populateAction);
		massUpdatePopulateButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void massUpdatePopulateAllButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdatePopulateAllButtonLayout = configProvider.getHorizontalLayoutConfig(
				"psPricingTabTabPopulateAllButtonlayout", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_MASS_UPDATE_PANEL_LAYOUT);
		massUpdatePopulateAllButtonLayout.addComponentStyle(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1);
		massUpdatePopulateAllButtonLayout.setComponentWidth("15%");
		componentList.add(massUpdatePopulateAllButtonLayout);

		GtnUIFrameworkComponentConfig massUpdatePopulateAllButton = configProvider.getUIFrameworkComponentConfig(
				"psPricingTabTabPopulateAllButton", true, "psPricingTabTabPopulateAllButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		massUpdatePopulateAllButton.setComponentName("Populate All");
		componentList.add(massUpdatePopulateAllButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPSPriceTabPopulateAction.class.getName());
		actionConfigList.add(customAction);
		massUpdatePopulateAllButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void psPricingResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingResultPanel = configProvider.getPanelConfig("psPricingTabTabResultPanel",
				true, GtnFrameworkCommonConstants.PRICING_TAB);
		psPricingResultPanel.setComponentName("Results");
		psPricingResultPanel.setAuthorizationIncluded(true);
		componentList.add(psPricingResultPanel);
		addPsPricingResultTableLayout(componentList);

	}

	private void addPsPricingResultTableLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingResultTableLayout = configProvider
				.getVerticalLayoutConfig("psPricingTabMainResultLayout", true, GtnFrameworkPSConstants.PS_PRICINGTAB_RESULTPANEL);
		componentList.add(psPricingResultTableLayout);
		psPricingResultLayout(componentList);
	}

	private void psPricingResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingResultLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkPSConstants.PS_PRICINGTAB_RESULT_LAYOUT, true, GtnFrameworkPSConstants.PS_PRICINGTAB_RESULTPANEL);
		psPricingResultLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(psPricingResultLayout);
		psPricingTabTabResultDataTable(componentList);

	}

	private void psPricingTabTabResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingTabTabResultDataTable = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE, true, "psPricingTabTabResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		psPricingTabTabResultDataTable.setAuthorizationIncluded(true);
		psPricingTabTabResultDataTable.setComponentName("Results");
		psPricingTabTabResultDataTable.setComponentHight("400px");
		psPricingTabTabResultDataTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psPricingTabTabResultDataTable);

		GtnUIFrameworkPagedTableConfig psPricingTabTabResultDataTableConfig = configProvider.getPagedTableConfig(true,
				true, GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"priceSchedulePrice", "priceSchedulePrice");
		psPricingTabTabResultDataTable.setGtnPagedTableConfig(psPricingTabTabResultDataTableConfig);
		psPricingTabTabResultDataTableConfig.setEditable(true);
		psPricingTabTabResultDataTableConfig.setTableColumnDataType(new Class<?>[] { Boolean.class, String.class,
				String.class, String.class, String.class, GtnFrameworkCommonConstants.JAVALANG_INTEGER, Date.class,
				Date.class, GtnFrameworkCommonConstants.JAVALANG_INTEGER, String.class, String.class, String.class,String.class,
				Date.class, Date.class });
		psPricingTabTabResultDataTableConfig.setTableVisibleHeader(new String[] { "", "Item ID", "Item No", "Item Name",
				"Brand Name", "Status", "CP Start Date", "CP End Date", "Price Type", "Price", "Suggested Price","Source",
				"Created By", "Created Date", "Attached Date" });
		psPricingTabTabResultDataTableConfig
				.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.CHECK_RECORD_ID, "itemId", "itemNo",
						"itemName", "brandName", GtnFrameworkCommonConstants.PS_STATUS, "cpStartDate", "cpEndDate",
						GtnFrameworkCommonConstants.PRICE_TYPE, "psPrice", "suggestedPrice","source", "createdBy",
						"psCreatedDate", "psAttachedDate" });
		psPricingTabTabResultDataTableConfig.setExtraColumn(new Object[] { "systemId" });
		psPricingTabTabResultDataTableConfig.setExtraColumnDataType(new Class<?>[] { String.class });
		psPricingTabTabResultDataTableConfig.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		psPricingTabTabResultDataTableConfig
				.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);
		psPricingTabTabResultDataTableConfig.setEditable(true);
		psPricingTabTabResultDataTableConfig.setInvisibleFilterPropertyIds(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		psPricingTabTabResultDataTableConfig.setRecordTypeComponentId("psPricingTabTabRecordType");
		psPricingTabTabResultDataTableConfig.setRecordTypeStartDate("cpStartDate");
		psPricingTabTabResultDataTableConfig.setRecordTypeEndDate("cpEndDate");

		psPricingTabTabResultDataTableConfig
				.setEditableColumnList(GtnFrameworkPSConstants.getPricingEditableFieldPropertiesArray());
		psPricingTabTabResultDataTableConfig.setEditableField(
				createTableFieldFactoryComponents(psPricingTabTabResultDataTableConfig.getEditableColumnList()));
		psPricingTabTabResultDataTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPSPriceTabTableCheckAction.class.getName());
		actionConfigList.add(customAction);
		psPricingTabTabResultDataTableConfig.setColumnCheckActionConfigList(actionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 * @param parentId
	 */
	private void addPsPricingExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPricingExcelButtonLayout = configProvider
				.getHorizontalLayoutConfig("gtnExcelButtonlayout", true, GtnFrameworkCommonConstants.PRICING_TAB);
		componentList.add(psPricingExcelButtonLayout);

		GtnUIFrameworkComponentConfig psPricingExcelButton = configProvider.getUIFrameworkComponentConfig(null, true,
				"gtnExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		psPricingExcelButton.setAuthorizationIncluded(true);
		psPricingExcelButton.setAddToParent(true);
		componentList.add(psPricingExcelButton);

		GtnUIFrameworkExcelButtonConfig psPricingExcelButtonConfig = configProvider.getExcelBtnconfig("PRICING", true,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE, false);
		psPricingExcelButtonConfig.setTitleNeeded(true);
		psPricingExcelButtonConfig.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		psPricingExcelButtonConfig.setHelperTableMapedPropertyIdList(
				Arrays.asList(GtnFrameworkCommonConstants.PS_STATUS, GtnFrameworkCommonConstants.PRICE_TYPE));
		psPricingExcelButtonConfig.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		psPricingExcelButton.setGtnUIFrameworkExcelButtonConfig(psPricingExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(psPricingExcelButtonConfig);
		psPricingExcelButton.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> psPricingEditableFields = new ArrayList<>();
		Map<String, String> psPricingComboBoxMap = getPsPricingComboBoxMap();
		for (String psPricingPropertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType;

			if (GtnFrameworkPSConstants.getPricingTextFieldPropertiesArray().contains(psPricingPropertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
			} else if (GtnFrameworkPSConstants.getPricingDateFieldPropertiesArray().contains(psPricingPropertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
			} else if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(psPricingPropertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.CHECKBOX;
			} else {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			}

			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			fieldConfig.setEnable(
					GtnFrameworkPSConstants.getPricingNonEditableFieldPropertiesArray().contains(psPricingPropertyId));
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig psPricingStatusConfig = configProvider.getComboBoxConfig(
						(psPricingComboBoxMap.get(psPricingPropertyId)),
						GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				fieldConfig.setGtnComboboxConfig(psPricingStatusConfig);
			}

			if (fieldConfig.isEnable()) {
				List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
				GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
				customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				customAction.addActionParameter(GtnFrameworkPricingTabFieldFactoryAction.class.getName());
				actionConfigList.add(customAction);
				fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);
			}
			if (GtnFrameworkCommonConstants.PS_PRICE.equals(psPricingPropertyId)) {
				fieldConfig.setEnable(false);
			}
			psPricingEditableFields.add(fieldConfig);
		}

		return psPricingEditableFields;
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		Map<String, String> comboBoxFieldMaps = getPsPricingComboBoxMap();
		for (Entry<String, String> entry : comboBoxFieldMaps.entrySet()) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(entry.getKey());
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			customFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(entry.getValue());
			customFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}

	private Map<String, String> getPsPricingComboBoxMap() {
		Map<String, String> psPricingComboBoxFieldMaps = new HashMap<>();
		psPricingComboBoxFieldMaps.put(GtnFrameworkCommonConstants.PS_STATUS, "STATUS");
		psPricingComboBoxFieldMaps.put(GtnFrameworkCommonConstants.PRICE_TYPE, "ItemPricingQualifier");
		return psPricingComboBoxFieldMaps;
	}
}
