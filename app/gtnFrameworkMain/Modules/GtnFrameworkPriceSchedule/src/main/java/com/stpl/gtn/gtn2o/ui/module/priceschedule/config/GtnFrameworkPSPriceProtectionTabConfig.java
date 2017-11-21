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
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceProtectionTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkTableRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSPriceProtectionTabPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnFrameworkPSPriceProtectionTabAddLineAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnUIFrameworkPSPopulateCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPPFieldFactoryDynamicComponentAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPsFieldFactoryPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFramworkPsPriceProtectionResultsFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkPSPriceProtectionTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addPriceProtectionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPriceProtectionMainLayoutConfig = configProvider
				.getVerticalLayoutConfig(GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB, false, null);
		psPriceProtectionMainLayoutConfig.setTabComponent(true);
		psPriceProtectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psPriceProtectionMainLayoutConfig);
		massUpdatePanel(componentList);
		recordLayout(componentList);
		companiesResultPanel(componentList);
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdatePanelLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB);
		massUpdatePanelLayout.setAuthorizationIncluded(true);
		massUpdatePanelLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(massUpdatePanelLayout);
		psPriceProtectionTabInformationFields(componentList);
	}

	private void recordLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig recordLayout = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionRecordTypelayout", true, GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB);
		recordLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(recordLayout);

		GtnUIFrameworkComponentConfig recordType = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabRecordType", true, "psPriceProtectionRecordTypelayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		recordType.setComponentName("Record:");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reloadPSPpTableActionConfig = new GtnUIFrameWorkActionConfig();
		reloadPSPpTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		reloadPSPpTableActionConfig
				.addActionParameter(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE);
		actionConfigList.add(reloadPSPpTableActionConfig);
		recordType.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(recordType);

		GtnUIFrameworkOptionGroupConfig reordTypeConfig = configProvider
				.getOptionGroupConfig(Arrays.asList("Current", "History", "Future"), "", false);
		reordTypeConfig.setIsMultiSelect(true);
		recordType.setGtnUIFrameworkOptionGroupConfig(reordTypeConfig);
		recordType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
	}

	private void psPriceProtectionTabInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addPsPriceProtectionMassCheck(componentList);
		addPsPriceProtectionMassFields(componentList);
		addPsBasePriceDdlb(componentList);
		addPsPriceProtectionMassDateField(componentList);
		addPsPriceProtectionMassDropDown(componentList);
		addPsPriceProtectionMassTextField(componentList);
		addPsPriceProtectionMassCustomTextField(componentList);
		addPsPriceProtectionPopulateButtons(componentList);

	}

	private void addPsPriceProtectionMassCheck(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig companiesMassCheckLayout = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionmassupdatelayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		companiesMassCheckLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(companiesMassCheckLayout);

		GtnUIFrameworkComponentConfig massUpdate = configProvider.getUIFrameworkComponentConfig(
				"cfpPriceProtectionMassCheck", true, "psPriceProtectionmassupdatelayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		massUpdate.setComponentName("Mass Update");
		componentList.add(massUpdate);

		GtnUIFrameworkOptionGroupConfig massUpdateConfig = configProvider.getOptionGroupConfig(
				Arrays.asList("Enable", GtnFrameworkCommonStringConstants.DISABLE),
				GtnFrameworkCommonStringConstants.DISABLE, false);
		massUpdate.setGtnUIFrameworkOptionGroupConfig(massUpdateConfig);
		massUpdate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));

		List<GtnUIFrameWorkActionConfig> massUpdateActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig massUpdateCustomAction = new GtnUIFrameWorkActionConfig();
		massUpdateCustomAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		massUpdateCustomAction.setFieldValues(Arrays.asList("psPriceProtectionTabMassField",
				"psPriceProtectionTabPopulateButton", "psPriceProtectionTabPopulateAllButton",
				"psPriceProtectionTabMassDateFeild", "psPriceProtectionTabMassDropDown",
				"psPriceProtectionTabTabmassCustomTextField", "psPriceProtectionTabTabmassTextField"));
		massUpdateActionConfigList.add(massUpdateCustomAction);
		massUpdate.setGtnUIFrameWorkActionConfigList(massUpdateActionConfigList);
	}

	private void addPsPriceProtectionMassFields(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psPriceProtectionTabMassFieldLayout = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionTabMassFeildlayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		psPriceProtectionTabMassFieldLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(psPriceProtectionTabMassFieldLayout);

		GtnUIFrameworkComponentConfig psPriceProtectionTabMassField = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabMassField", true, "psPriceProtectionTabMassFeildlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		psPriceProtectionTabMassField.setComponentName(" ");
		psPriceProtectionTabMassField.setEnable(false);
		componentList.add(psPriceProtectionTabMassField);

	           GtnUIFrameworkComboBoxConfig psPriceProtectionTabMassFieldConfig = new GtnUIFrameworkComboBoxConfig();

            psPriceProtectionTabMassFieldConfig.setItemValues(Arrays.asList("Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
                    "Measurement Price", "NEP", "NEP Formula", "Base Price Type", "Baseline Net WAC", "Net Baseline WAC Formula", "Subsequent Period Price Type",
                    "Net Subsequent Period Price", "Net Subsequent Period Price Formula", "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type",
                    "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type",
                    "Net Reset Price Formula", "Net Price Type", "Net Price Type Formula"));

            psPriceProtectionTabMassField.setGtnComboboxConfig(psPriceProtectionTabMassFieldConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction.class.getName());
		customAction.addActionParameter(false);
		actionConfigList.add(customAction);
		psPriceProtectionTabMassField.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPsPriceProtectionMassDateField(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psPriceProtectionTabMassDateFieldLayout = configProvider
				.getHorizontalLayoutConfig("psPriceProtectionTabMassDateFeildlayout", true,
						GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		psPriceProtectionTabMassDateFieldLayout.setComponentStyle(Arrays.asList(
				GtnFrameworkCssConstants.STPL_MARGIN_TOP_34, GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		psPriceProtectionTabMassDateFieldLayout.setVisible(false);
		componentList.add(psPriceProtectionTabMassDateFieldLayout);

		GtnUIFrameworkComponentConfig psPriceProtectionTabMassDateField = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabMassDateFeild", true, "psPriceProtectionTabMassDateFeildlayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		psPriceProtectionTabMassDateField.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(psPriceProtectionTabMassDateField);

	}

	private void addPsPriceProtectionMassDropDown(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psPriceProtectionTabMassDropDownLayout = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionTabMassDropDownlayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		psPriceProtectionTabMassDropDownLayout.setComponentStyle(Arrays.asList(
				GtnFrameworkCssConstants.STPL_MARGIN_TOP_34, GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		psPriceProtectionTabMassDropDownLayout.setVisible(false);
		componentList.add(psPriceProtectionTabMassDropDownLayout);

		GtnUIFrameworkComponentConfig psPriceProtectionTabMassDropDown = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabMassDropDown", true, "psPriceProtectionTabMassDropDownlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		psPriceProtectionTabMassDropDown.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(psPriceProtectionTabMassDropDown);

		GtnUIFrameworkComboBoxConfig psPriceProtectionTabMassDropDownConfig = configProvider
				.getComboBoxConfig("CFP_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		psPriceProtectionTabMassDropDown.setGtnComboboxConfig(psPriceProtectionTabMassDropDownConfig);

	}

	private void addPsPriceProtectionMassCustomTextField(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig massCustomTextFieldLayout = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionTabMassCustomTextFieldlayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		massCustomTextFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_34,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		massCustomTextFieldLayout.setVisible(false);
		componentList.add(massCustomTextFieldLayout);

		GtnUIFrameworkComponentConfig massCustomTextField = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabTabmassCustomTextField", true, "psPriceProtectionTabMassCustomTextFieldlayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		massCustomTextField.setComponentName(GtnFrameworkCommonConstants.VALUE);
		massCustomTextField.setComponentStyle(Arrays.asList("searchicon"));
		componentList.add(massCustomTextField);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("rebatePlanPopUpSearchView");
		popupActionParam.add("Parent Price Schedule ID");
		popupActionParam.add("70%");
		popupActionParam.add("70%");

		popupActionConfig.setActionParameterList(popupActionParam);

		massCustomTextField.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPsPriceProtectionMassTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massTextFieldLayout = configProvider.getHorizontalLayoutConfig(
				"PSPriceProtectionTabMassTextFieldlayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		massTextFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_34,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		massTextFieldLayout.setVisible(false);
		componentList.add(massTextFieldLayout);

		GtnUIFrameworkComponentConfig massTextFieldConfig = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabTabmassTextField", true, "PSPriceProtectionTabMassTextFieldlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		massTextFieldConfig.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(massTextFieldConfig);

	}

	private void addPsPriceProtectionPopulateButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		massUpdatePopulateButton(componentList);
		massUpdatePopulateAllButton(componentList);
	}

	private void massUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdatePopulateButtonLayout = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionTabPopulateButtonlayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		massUpdatePopulateButtonLayout.addComponentStyle(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1);
		componentList.add(massUpdatePopulateButtonLayout);

		GtnUIFrameworkComponentConfig massUpdatePopulateButton = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabPopulateButton", true, "psPriceProtectionTabPopulateButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		massUpdatePopulateButton.setComponentName("Populate");
		componentList.add(massUpdatePopulateButton);

		List<GtnUIFrameWorkActionConfig> populateButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig populateCheck = new GtnUIFrameWorkActionConfig();
		populateCheck.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		populateCheck.addActionParameter(GtnUIFrameworkPSPopulateCheckAction.class.getName());
		populateButtonActionConfigList.add(populateCheck);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPSPriceProtectionTabPopulateAction.class.getName());
		populateButtonActionConfigList.add(customAction);
		massUpdatePopulateButton.setGtnUIFrameWorkActionConfigList(populateButtonActionConfigList);
	}

	private void massUpdatePopulateAllButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdatePopulateAllButton = configProvider.getHorizontalLayoutConfig(
				"psPriceProtectionTabPopulateAllButtonlayout", true,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		massUpdatePopulateAllButton.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_25));
		componentList.add(massUpdatePopulateAllButton);

		GtnUIFrameworkComponentConfig populateAllButton = configProvider.getUIFrameworkComponentConfig(
				"psPriceProtectionTabPopulateAllButton", true, "psPriceProtectionTabPopulateAllButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		populateAllButton.setComponentName("Populate All");
		componentList.add(populateAllButton);

		List<GtnUIFrameWorkActionConfig> populateAllButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig populateAllButtonCustomAction = new GtnUIFrameWorkActionConfig();
		populateAllButtonCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		populateAllButtonCustomAction
				.addActionParameter(GtnUIFrameworkPSPriceProtectionTabPopulateAction.class.getName());
		populateAllButtonActionConfigList.add(populateAllButtonCustomAction);
		populateAllButton.setGtnUIFrameWorkActionConfigList(populateAllButtonActionConfigList);
	}

	private void companiesResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultTablePanel = configProvider.getPanelConfig(
				"psPriceProtectionTabResultPanel", true, GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB);
		resultTablePanel.setComponentName("Results");
		resultTablePanel.setAuthorizationIncluded(true);
		componentList.add(resultTablePanel);
		getMainTableLayout(componentList);

	}

	private void getMainTableLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig mainTableLayout = configProvider
				.getVerticalLayoutConfig("psPriceProtectionMainResultLayout", true, "psPriceProtectionTabResultPanel");
		componentList.add(mainTableLayout);
		companiesResultLayout(componentList, mainTableLayout.getComponentId());
		addExcelButtonComponent(componentList, mainTableLayout.getComponentId());
		addAddLineButtonComponent(componentList, mainTableLayout.getComponentId());
		addRemoveLineButtonComponent(componentList);
		addCopyLineButtonComponent(componentList);

	}

	private void companiesResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig tableLayout = configProvider
				.getVerticalLayoutConfig("psPriceProtectionTabResultLayout", true, parentId);
		componentList.add(tableLayout);
		psPriceProtectionTabResultDataTable(componentList);

	}

	private void psPriceProtectionTabResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceProtectionResultTableConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE, true,
				"psPriceProtectionTabResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		priceProtectionResultTableConfig.setAuthorizationIncluded(true);
		priceProtectionResultTableConfig.setComponentName("Results");
		priceProtectionResultTableConfig.setComponentHight("400px");
		priceProtectionResultTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(priceProtectionResultTableConfig);

		GtnUIFrameworkPagedTableConfig priceProtectionResultTable = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"priceSchedulePriceProtection", "priceSchedulePriceProtection");
		priceProtectionResultTableConfig.setGtnPagedTableConfig(priceProtectionResultTable);
		priceProtectionResultTable.setEditable(true);
		priceProtectionResultTable.setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class,
				String.class, String.class, Integer.class, Date.class, Date.class, Integer.class, String.class,
				String.class, Integer.class, Object.class, Integer.class, String.class, Integer.class, Integer.class,
				String.class, Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class,
				Integer.class, Date.class, Integer.class, Integer.class, Integer.class, Integer.class, String.class,
				Integer.class, String.class, Date.class, Date.class, Integer.class, String.class });
		priceProtectionResultTable.setTableVisibleHeader(GtnFrameworkPSConstants.getPriceProtectionHeader());
		priceProtectionResultTable
				.setTableColumnMappingId(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray());

		priceProtectionResultTable.setExtraColumn(new Object[] { "systemId" });
		priceProtectionResultTable.setExtraColumnDataType(new Class<?>[] { String.class });
		priceProtectionResultTable.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		priceProtectionResultTable.setInvisibleFilterPropertyIds(
				GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[0],
				GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12]);
		priceProtectionResultTable.setEditableColumnList(GtnFrameworkPSConstants.getPriceProtectionEditableList());
		priceProtectionResultTable.setEditableField(
				createTableFieldFactoryComponents(priceProtectionResultTable.getEditableColumnList()));
		priceProtectionResultTable.setCustomFilterConfigMap(getCustomFilterConfig());
		priceProtectionResultTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);

		priceProtectionResultTable.setRecordTypeComponentId("psPriceProtectionTabRecordType");
		priceProtectionResultTable.setRecordTypeStartDate("psPPStartDate");
		priceProtectionResultTable.setRecordTypeEndDate("psPPEndDate");

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameWorkTableRecordTypeAction.class.getName());
		customAction.addActionParameter(priceProtectionResultTableConfig.getComponentId());
		priceProtectionResultTable.setRecordTypeManageActionConfig(customAction);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig tableCheckCustomAction = new GtnUIFrameWorkActionConfig();
		tableCheckCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableCheckCustomAction.addActionParameter(GtnFrameworkPSPriceProtectionTabTableCheckAction.class.getName());
		actionConfigList.add(tableCheckCustomAction);
		priceProtectionResultTable.setColumnCheckActionConfigList(actionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 * @param parentId
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig excelButtonLayout = configProvider
				.getHorizontalLayoutConfig("gtnExcelButtonlayout", false, parentId);
		componentList.add(excelButtonLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(null, true,
				"gtnExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);

		GtnUIFrameworkExcelButtonConfig priceProtectionTabExcelButtonConfig = configProvider.getExcelBtnconfig(
				"PRICE_PROTECTION", true, GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE, false);
		priceProtectionTabExcelButtonConfig.setTitleNeeded(true);
		priceProtectionTabExcelButtonConfig
				.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		priceProtectionTabExcelButtonConfig.setHelperTableMapedPropertyIdList(Arrays.asList("psPPStatus",
				"psPPPriceType", "psBasePriceType", "psNetBasePrice", "psSubseqPeriodPriceType", "psToleranceInterval",
				"psToleranceFreq", "psToleranceType", "psResetEligible", "psResetType", "psResetInterval",
				"psResetFrequency", "psResetPriceType", "psNetResetPriceType", "psNetPriceType"));
		priceProtectionTabExcelButtonConfig
				.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(priceProtectionTabExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(priceProtectionTabExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> priceProtectionTabEditableFields = new ArrayList<>();
		Map<String, String> comboBoxMap = getComboBoxMap();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentConfig priceProtectionTabConfig = new GtnUIFrameworkComponentConfig();

			GtnUIFrameworkComponentType gtnUIFrameworkComponentType;
			if (GtnFrameworkPSConstants.getPriceProtectionTextFieldProperties().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
			} else if (GtnFrameworkPSConstants.getPricePrtectionDateFieldProperties().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
				priceProtectionTabConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
			} else if (GtnFrameworkPSConstants.getPriceProtectionCustomTextFieldProperties().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.POPUPTEXTFIELD;
			} else if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.CHECKBOX;
			} else {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			}

			priceProtectionTabConfig.setComponentType(gtnUIFrameworkComponentType);
			priceProtectionTabConfig.setEnable(
					!GtnFrameworkPSConstants.getPriceProtectionNonEditableFieldProperties().contains(propertyId));
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig companyFamilyPlanStatusConfig = configProvider.getComboBoxConfig(
						comboBoxMap.get(propertyId), GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				priceProtectionTabConfig.setGtnComboboxConfig(companyFamilyPlanStatusConfig);
				priceProtectionTabConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
			}
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.POPUPTEXTFIELD)) {

				GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
				popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
				List<Object> popupActionParam = new ArrayList<>();
				popupActionParam.add("netSalesFormulaPopUpView");
				popupActionParam.add("Net Sales Formula Lookup");
				popupActionParam.add("70%");
				popupActionParam.add("70%");
				popupActionConfig.setActionParameterList(popupActionParam);
				
				priceProtectionTabConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
				GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
				selectAction.addActionParameter(GtnFrameworkPsFieldFactoryPopupSelectAction.class.getName());
				priceProtectionTabConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
				priceProtectionTabConfig.addGtnUIFrameWorkActionConfig(selectAction);
			 }
			if (GtnFrameworkCommonConstants.PS_BASE_PRICE_ENTRY.equals(propertyId)) {
				GtnUIFrameWorkActionConfig pricingTableFieldFactoryFieldFactoryComponentCreateAction = new GtnUIFrameWorkActionConfig();
				pricingTableFieldFactoryFieldFactoryComponentCreateAction
						.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				pricingTableFieldFactoryFieldFactoryComponentCreateAction
						.addActionParameter(GtnFrameworkPPFieldFactoryDynamicComponentAction.class.getName());
				pricingTableFieldFactoryFieldFactoryComponentCreateAction
						.addActionParameter(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[11]);
				pricingTableFieldFactoryFieldFactoryComponentCreateAction
						.addActionParameter(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12]);
				priceProtectionTabConfig.setRebuild(true);
				pricingTableFieldFactoryFieldFactoryComponentCreateAction.setComponentConfig(priceProtectionTabConfig);
				priceProtectionTabConfig.setFieldFactoryComponentCreateActionConfig(
						pricingTableFieldFactoryFieldFactoryComponentCreateAction);
			}
			if (priceProtectionTabConfig.isEnable()) {
				List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
				GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
				customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				customAction.addActionParameter(GtnFramworkPsPriceProtectionResultsFieldFactoryAction.class.getName());
				actionConfigList.add(customAction);
				priceProtectionTabConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);
				priceProtectionTabConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
			}
			priceProtectionTabEditableFields.add(priceProtectionTabConfig);
		}

		return priceProtectionTabEditableFields;
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> ppCustomFilterConfigMap = new HashMap<>();
		Map<String, String> comboBoxFieldMaps = getComboBoxMap();
		for (Entry<String, String> entry : comboBoxFieldMaps.entrySet()) {
			if (!GtnFrameworkCommonConstants.PS_BASE_PRICE_ENTRY.equals(entry.getKey())) {
				GtnUIFrameworkPagedTableCustomFilterConfig ppCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
				ppCustomFilterConfig.setPropertId(entry.getKey());
				ppCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
				GtnUIFrameworkComponentConfig ppCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
				ppCustomFilterComponentConfig.setComponentId("customFilterComboBox");
				ppCustomFilterComponentConfig.setComponentName("customFilterComboBox");
				ppCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				ppCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(entry.getValue());
				ppCustomFilterComponentConfig.getGtnComboboxConfig()
						.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
				ppCustomFilterComponentConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				ppCustomFilterConfig.setGtnComponentConfig(ppCustomFilterComponentConfig);
				ppCustomFilterConfigMap.put(ppCustomFilterConfig.getPropertId(), ppCustomFilterConfig);
			}
		}
		return ppCustomFilterConfigMap;
	}

	private Map<String, String> getComboBoxMap() {
		Map<String, String> priceProtectionTabComboBoxMap = new HashMap<>();
		priceProtectionTabComboBoxMap.put("psPPStatus", "STATUS");
		priceProtectionTabComboBoxMap.put("psPPPriceType", GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		priceProtectionTabComboBoxMap.put("psBasePriceType", "BASE_PRICE_TYPE");
		priceProtectionTabComboBoxMap.put("psSubseqPeriodPriceType", GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		priceProtectionTabComboBoxMap.put("psNetBasePrice", GtnFrameworkCommonConstants.LOCKED_STATUS);
		priceProtectionTabComboBoxMap.put("psNetBSubseqPeriodPrice", GtnFrameworkCommonConstants.LOCKED_STATUS);
		priceProtectionTabComboBoxMap.put("psToleranceInterval", "PRICE_TOLERANCE_INTERVAL");
		priceProtectionTabComboBoxMap.put("psToleranceFreq", "PRICE_TOLERANCE_FREQUENCY");
		priceProtectionTabComboBoxMap.put("psToleranceType", "PRICE_TOLERANCE_TYPE");
		priceProtectionTabComboBoxMap.put("psResetEligible", GtnFrameworkCommonConstants.LOCKED_STATUS);
		priceProtectionTabComboBoxMap.put("psResetType", "RESET_TYPE");
		priceProtectionTabComboBoxMap.put("psResetInterval", "PRICE_TOLERANCE_INTERVAL");
		priceProtectionTabComboBoxMap.put("psResetFrequency", "PRICE_TOLERANCE_FREQUENCY");
		priceProtectionTabComboBoxMap.put("psResetPriceType", GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		priceProtectionTabComboBoxMap.put("psNetPriceType", GtnFrameworkCommonConstants.LOCKED_STATUS);
		priceProtectionTabComboBoxMap.put("psNetResetPriceType", GtnFrameworkCommonConstants.LOCKED_STATUS);
		priceProtectionTabComboBoxMap.put(GtnFrameworkCommonConstants.PS_BASE_PRICE_ENTRY, "pricingCodeQualifierName");
		return priceProtectionTabComboBoxMap;
	}

	private void addPsBasePriceDdlb(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psPriceProtectionTabPsBasePriceDdlbLayout = configProvider
				.getHorizontalLayoutConfig("psPriceProtectionTabPsBasePriceDdlbLayout", true,
						GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_MASS_UPDATE_PANEL_LAYOUT);
		psPriceProtectionTabPsBasePriceDdlbLayout.setComponentStyle(Arrays.asList(
				GtnFrameworkCssConstants.STPL_MARGIN_TOP_34, GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		psPriceProtectionTabPsBasePriceDdlbLayout.setVisible(false);
		componentList.add(psPriceProtectionTabPsBasePriceDdlbLayout);

		GtnUIFrameworkComponentConfig psPriceProtectionTabPsBasePriceDdlb = configProvider
				.getUIFrameworkComponentConfig("psPriceProtectionTabPsBasePriceDdlb", true,
						"psPriceProtectionTabPsBasePriceDdlbLayout", GtnUIFrameworkComponentType.COMBOBOX);
		psPriceProtectionTabPsBasePriceDdlb.setComponentName("Base Price Type");
		componentList.add(psPriceProtectionTabPsBasePriceDdlb);

		GtnUIFrameworkComboBoxConfig psPriceProtectionTabMassDropDownConfig = configProvider
				.getComboBoxConfig("BASE_PRICE_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		psPriceProtectionTabPsBasePriceDdlb.setGtnComboboxConfig(psPriceProtectionTabMassDropDownConfig);

		List<GtnUIFrameWorkActionConfig> psBaseTypeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction.class.getName());
		customAction.addActionParameter(true);
		psBaseTypeActionConfigList.add(customAction);
		psPriceProtectionTabPsBasePriceDdlb.setGtnUIFrameWorkActionConfigList(psBaseTypeActionConfigList);

	}

	private void addAddLineButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig addLineButtonLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.ADD_LINE, false, parentId);
		componentList.add(addLineButtonLayout);

		GtnUIFrameworkComponentConfig addLineButtonConfig = configProvider.getUIFrameworkComponentConfig(null, true,
				GtnFrameworkCommonConstants.ADD_LINE, GtnUIFrameworkComponentType.BUTTON);
		addLineButtonConfig.setComponentName("Add Line");
		componentList.add(addLineButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addLineCustomAction = new GtnUIFrameWorkActionConfig();
		addLineCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addLineCustomAction.addActionParameter(GtnFrameworkPSPriceProtectionTabAddLineAction.class.getName());
		addLineCustomAction.addActionParameter("getImtdPsDetailsInsertQueryAddLine");
		addLineCustomAction.addActionParameter("A");
		actionConfigList.add(addLineCustomAction);

		addLineButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRemoveLineButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig removeLineButtonLayout = configProvider
				.getVerticalLayoutConfig("gtnRemoveLineButtonlayout", true, GtnFrameworkCommonConstants.ADD_LINE);
		componentList.add(removeLineButtonLayout);

		GtnUIFrameworkComponentConfig removeLineButtonConfig = configProvider.getUIFrameworkComponentConfig(null, true,
				"gtnRemoveLineButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		removeLineButtonConfig.setComponentName("Remove Line");
		componentList.add(removeLineButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeLineCustomAction = new GtnUIFrameWorkActionConfig();
		removeLineCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeLineCustomAction.addActionParameter(GtnFrameworkPSPriceProtectionTabAddLineAction.class.getName());
		removeLineCustomAction.addActionParameter("getImtdPsDetailsDeleteLineQuery");
		removeLineCustomAction.addActionParameter("D");
		actionConfigList.add(removeLineCustomAction);

		removeLineButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCopyLineButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig copyLineButtonLayout = configProvider
				.getVerticalLayoutConfig("gtnCopyLineButtonlayout", true, GtnFrameworkCommonConstants.ADD_LINE);
		componentList.add(copyLineButtonLayout);

		GtnUIFrameworkComponentConfig copyLineButtonConfig = configProvider.getUIFrameworkComponentConfig(null, true,
				"gtnCopyLineButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		copyLineButtonConfig.setComponentName("Copy Line");
		componentList.add(copyLineButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig copyLineCustomAction = new GtnUIFrameWorkActionConfig();
		copyLineCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyLineCustomAction.addActionParameter(GtnFrameworkPSPriceProtectionTabAddLineAction.class.getName());
		copyLineCustomAction.addActionParameter("getPsEditInsertMainToTempCopyQuery");
		copyLineCustomAction.addActionParameter("A");
		actionConfigList.add(copyLineCustomAction);

		copyLineButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}
}
