package com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnFrameworkDCCustomerConfig {

	public void addCustomerSearchTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("customerSelectionTab");
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig priceScheduleMainCssConfig = new GtnUIFrameworkComponentConfig();
		priceScheduleMainCssConfig.setComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		priceScheduleMainCssConfig.setAddToParent(true);
		priceScheduleMainCssConfig.setParentComponentId("customerSelectionTab");
		priceScheduleMainCssConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> priceScheduleMainCssCStyleList = new ArrayList<>();
		priceScheduleMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		priceScheduleMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		priceScheduleMainCssConfig.setComponentStyle(priceScheduleMainCssCStyleList);

		GtnUIFrameworkLayoutConfig priceScheduleMainCssLayout = new GtnUIFrameworkLayoutConfig();
		priceScheduleMainCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		priceScheduleMainCssConfig.setGtnLayoutConfig(priceScheduleMainCssLayout);

		componentList.add(priceScheduleMainCssConfig);

		addCustomerSearchPanel(componentList);
		addButtonLayout(componentList);
		addAvailableResultPanel(componentList);
		addAvailableTableActionButtonLayout(componentList);
		addSelectedResultPanel(componentList);
		addSelectedTableActionButtonLayout(componentList);

	}

	private void addCustomerSearchPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Customer Search");
		panel.setComponentId("dcCustomerSearchPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(true);
		panel.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		componentList.add(panel);
		addCustomerSearchFieldLayout(componentList);
	}

	private void addCustomerSearchFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		layoutConfig.setAddToParent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId("dcCustomerSearchPanel");

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		layoutConfig.setComponentStyle(styleList);
		componentList.add(layoutConfig);
		addCustomerSearchFieldComponent(componentList);
	}

	private void addCustomerSearchFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addCustomerNo(componentList);
		addTradeClass(componentList);
		addCustomerStatus(componentList);
		addState(componentList);
		addCustomerName(componentList);
		addCustomerType(componentList);
		addCity(componentList);
		addZipCode(componentList);

	}

	private void addCustomerNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabCustomerNolayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_NO);
		companyNoConfig.setComponentName("Customer NO");
		companyNoConfig.setParentComponentId("dcCustomerTabCustomerNolayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(companyNoConfig);
	}

	private void addTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabTradeClasslayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_TRADE_CLASS);
		companyStatus.setComponentName("Trade Class");
		companyStatus.setParentComponentId("dcCustomerTabTradeClasslayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("COMPANY_TRADE_CLASS");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
	}
    

	private void addCustomerStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabCustomerStatuslayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_STATUS);
		companyStatus.setComponentName(GtnFrameworkCommonConstants.CUSTOMER_STATUS);
		companyStatus.setParentComponentId("dcCustomerTabCustomerStatuslayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("STATUS");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addState(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabStatelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_STATE);
		companyStatus.setComponentName(GtnFrameworkCommonConstants.STATE);
		companyStatus.setParentComponentId("dcCustomerTabStatelayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("STATE");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
	}
   

	private void addCustomerName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabCustomerNamelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = new GtnUIFrameworkComponentConfig();
		companyNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_NAME);
		companyNameConfig.setComponentName("Customer Name");
		companyNameConfig.setParentComponentId("dcCustomerTabCustomerNamelayout");
		companyNameConfig.setAddToParent(true);

		componentList.add(companyNameConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCustomerType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabCustomerTypelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = new GtnUIFrameworkComponentConfig();
		companyType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_TYPE);
		companyType.setComponentName(GtnFrameworkCommonConstants.CUSTOMER_TYPE);
		companyType.setParentComponentId("dcCustomerTabCustomerTypelayout");
		gtnLayout.setGtnLayoutConfig(layout);
		companyType.setAddToParent(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType("COMPANY_TYPE");
		companyType.setGtnComboboxConfig(companyTypeConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addCity(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabCitylayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CITY);
		companyNoConfig.setComponentName("City");
		companyNoConfig.setParentComponentId("dcCustomerTabCitylayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addZipCode(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerTabZipcodelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_ZIPCODE);
		companyNoConfig.setComponentName(GtnFrameworkCommonConstants.ZIP_CODE);
		companyNoConfig.setParentComponentId("dcCustomerTabZipcodelayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
   

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMERSEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);

		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("gtnSearch01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMERSEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		;

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("gtnSearch01");
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("gtnSearch01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_NO, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_TRADE_CLASS, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_STATUS, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_STATE, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_NAME, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_TYPE, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CITY, GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_ZIPCODE));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add("Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);
		validationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig) }));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.DC_CUSTOMER_AVAILABLESEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(new ArrayList<String>());
		actionConfigList.add(loadDataTableActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("cGrpgtnReset01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMERSEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("cGrpgtnReset01");
		searchButtonConfig.setComponentName("RESET");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("cGrpgtnReset01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig.addActionParameter("Reset Confirmation");
		resetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_TRADE_CLASS, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_STATUS, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_STATE, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CUSTOMER_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_CITY, "");
		resetMap.put(GtnFrameworkCommonConstants.DC_CUSTOMER_TAB_ZIPCODE, "");

		resetActionConfig.addActionParameter(resetMap);

		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addAvailableResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Results");
		panelConfig.setComponentId("dcCustomerAvailableResultPanel");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		componentList.add(panelConfig);
		addAvailableResultLayout(componentList);
	}

	private void addAvailableResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerAvailableResultlayout");
		gtnLayout.setParentComponentId("dcCustomerAvailableResultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addAvailableResultTableComponent(componentList);
	}

	private void addAvailableResultTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_AVAILABLESEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("");
		searchResultConfig.setParentComponentId("dcCustomerAvailableResultlayout");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentHight("400px");
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setMultiSelect(false);

		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE });

		searchResults.setTableVisibleHeader(new String[] { "Organization Key ", "Customer ID", "Customer No ",
				"Customer Name ", "Trade Class ", "Trade Class Start Date ", "Trade Class End Date", GtnFrameworkCommonConstants.CUSTOMER_TYPE, GtnFrameworkCommonConstants.CUSTOMER_STATUS, "Lives", "Customer End Date", "UDC1", "UDC2", "Customer Group", "Financial System",
				"Address 1", "Address 2", "City", GtnFrameworkCommonConstants.STATE, GtnFrameworkCommonConstants.ZIP_CODE, "Country", "Region Code", "Parent Customer No",
				"Parent Start Date", "Parent End Date", "Customer Start Date", "Prior Parent Start Date" });
		searchResults.setTableColumnMappingId(new Object[] { "organizationKey", "customerId", "customerNo",
				"customerName", "tradeClass", "tradeClassStartDate", "tradeClassEndDate", "customerType",
				"customerStatus", "lives", "customerEndDate", "udc1", "udc2", "customerGroup", "financialSystem",
				"address1", "address2", "city", "state", "zipCode", "country", "regionCode", "parentCustomerNo",
				"parentStartDate", "parentEndDate", "customerStartDate", "priorParentStartDate" });
		// 27 columns
		searchResults
				.setExtraColumn(new Object[] { "companyMasterSid", "companyTradeClass", "parentCompanyMasterSid" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setQueryName("dcAvailableCustomer");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addAvailableTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addButtonComponent(componentList);
		addAllButtonComponent(componentList);
	}

	private void addButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerAvailableTablegtnAdd01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcCustomerAvailableTablegtnAdd01");
		searchButtonConfig.setComponentName("ADD");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcCustomerAvailableTablegtnAdd01layout");
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCommonConstants.DC_CUSTOMER_AVAILABLESEARCH_RESULT_TABLE);
		alertParams.add("Edit Error");
		alertParams.add("Please select a record to edit");

		alertActionConfig.setActionParameterList(alertParams);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig
				.addActionParameter("com.stpl.gtn.gtn2o.ui.module.action.GtnUiFrameworkCustomerADDAllButtonAction");
		actionConfigList.add(addActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerAvailableTablegtnAddAll01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcCustomerAvailableTablegtnAddAll01");
		searchButtonConfig.setComponentName("ADD ALL");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcCustomerAvailableTablegtnAddAll01layout");
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig
				.addActionParameter("com.stpl.gtn.gtn2o.ui.module.action.GtnUiFrameworkCustomerADDAllButtonAction");

		actionConfigList.add(addActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSelectedResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Selected Customers");
		panelConfig.setComponentId("dcCustomerSelectedResultPanel");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		componentList.add(panelConfig);
		addSelectedResultLayout(componentList);
	}

	private void addSelectedResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerSelectedResultlayout");
		gtnLayout.setParentComponentId("dcCustomerSelectedResultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSelectedPagedTableComponent(componentList);
	}

	private void addSelectedPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("dcCustomerSelectedResultTable");
		searchResultConfig.setComponentName("");
		searchResultConfig.setParentComponentId("dcCustomerSelectedResultlayout");
		searchResultConfig.setComponentHight("400px");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setSpacing(true);
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setMultiSelect(true);

		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING });

		searchResults.setTableVisibleHeader(new String[] { "Organization Key ", "Customer ID", "Customer No ",
				"Customer Name ", "Trade Class ", "Trade Class Start Date ", "Trade Class End Date", GtnFrameworkCommonConstants.CUSTOMER_TYPE, GtnFrameworkCommonConstants.CUSTOMER_STATUS, "Lives", "Customer End Date", "UDC1", "UDC2", "Customer Group", "Financial System",
				"Address 1", "Address 2", "City", GtnFrameworkCommonConstants.STATE, GtnFrameworkCommonConstants.ZIP_CODE, "Country", "Region Code", "Parent Customer No",
				"Parent Start Date", "Parent End Date", "Customer Start Date", "Prior Parent Start Date",
				"Prior Parent Customer No" });
		searchResults.setTableColumnMappingId(
				new Object[] { "organizationKey", "customerId", "customerNo", "customerName", "tradeClass",
						"tradeClassStartDate", "tradeClassEndDate", "customerType", "customerStatus", "lives",
						"customerEndDate", "udc1", "udc2", "customerGroup", "financialSystem", "address1", "address2",
						"city", "state", "zipCode", "country", "regionCode", "parentCustomerNo", "parentStartDate",
						"parentEndDate", "customerStartDate", "priorParentStartDate", "priorParentCustomerNo" });
		searchResults.setExtraColumn(new Object[] { "companyMasterSid" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("dcSelectedCustomer");
		searchResults.setQueryName("dcSelectedCustomer");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addSelectedTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SELECTED_TABLE_ACTION_BUTTONLAYOUT);

		gtnLayout.setAddToParent(true);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB_MAIN_CSS_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addRemoveButtonComponent(componentList);
		addRemoveAllButtonComponent(componentList);
	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerSelectedTablegtnRemove01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcCustomerSelectedTablegtnRemove01");
		searchButtonConfig.setComponentName("REMOVE");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcCustomerSelectedTablegtnRemove01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add("dcCustomerSelectedResultTable");
		alertParams.add("Edit Error");
		alertParams.add("Please select a record to edit");

		alertActionConfig.setActionParameterList(alertParams);
		actionConfigList.add(alertActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRemoveAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcCustomerSelectedTablegtnRemoveAll01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_CUSTOMER_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcCustomerSelectedTablegtnRemoveAll01");
		searchButtonConfig.setComponentName("REMOVE ALL");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcCustomerSelectedTablegtnRemoveAll01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
