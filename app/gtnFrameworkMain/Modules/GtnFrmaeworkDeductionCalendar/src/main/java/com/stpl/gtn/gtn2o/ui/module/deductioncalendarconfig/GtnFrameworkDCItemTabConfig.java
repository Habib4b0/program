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

public class GtnFrameworkDCItemTabConfig {

	public void addDCItemrTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("itemSelectionTab");
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
		priceScheduleMainCssConfig.setComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
		priceScheduleMainCssConfig.setAddToParent(true);
		priceScheduleMainCssConfig.setParentComponentId("itemSelectionTab");
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
		panel.setComponentName("Item Search");
		panel.setComponentId("dcItemSearchPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(true);
		panel.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
		componentList.add(panel);
		addCustomerSearchFieldLayout(componentList);
	}

	private void addCustomerSearchFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		layoutConfig.setAddToParent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId("dcItemSearchPanel");

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
		addItemType(componentList);
		addItemDesc(componentList);
		addBrand(componentList);
		addStrength(componentList);
		addItemNo(componentList);
		addTherapeuticClass(componentList);
		addForm(componentList);

	}

	private void addItemType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemTabItemTypelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = new GtnUIFrameworkComponentConfig();
		companyType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_TYPE);
		companyType.setComponentName("Item Type");
		companyType.setParentComponentId("dcItemTabItemTypelayout");
		gtnLayout.setGtnLayoutConfig(layout);
		companyType.setAddToParent(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType("ITEM_TYPE");
		companyType.setGtnComboboxConfig(companyTypeConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemDesc(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemDesclayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		companyIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_I_GRP_DESC);
		companyIdConfig.setComponentName(GtnFrameworkCommonConstants.ITEM_DESC_HEADER);
		companyIdConfig.setParentComponentId("dcItemDesclayout");
		companyIdConfig.setAddToParent(true);

		componentList.add(companyIdConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemTabItemNolayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_NO);
		companyNoConfig.setComponentName("Item NO");
		companyNoConfig.setParentComponentId("dcItemTabItemNolayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addBrand(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemInformationTabBrandlayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_BRAND);
		companyStatus.setComponentName(GtnFrameworkCommonConstants.BRAND_IFP);
		companyStatus.setParentComponentId("dcItemInformationTabBrandlayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("COMPANY_TRADE_CLASS");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addStrength(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemInformationTabStrengthlayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_STRENGTH);
		companyNoConfig.setComponentName(GtnFrameworkCommonConstants.STRENGTH_IFP);
		companyNoConfig.setParentComponentId("dcItemInformationTabStrengthlayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addTherapeuticClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemInformationTabTherapeuticClasslayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_THERAPEUTIC_CLASS);
		companyStatus.setComponentName(GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL);
		companyStatus.setParentComponentId("dcItemInformationTabTherapeuticClasslayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("THERAPEUTIC_CLASS");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addForm(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemInformationTabFormlayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_FORM);
		companyStatus.setComponentName("Form");
		companyStatus.setParentComponentId("dcItemInformationTabFormlayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("FORM");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.DC_ITEMSEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
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
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEMSEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

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

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_TYPE, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_I_GRP_DESC, GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_NO, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_BRAND, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_STRENGTH, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_THERAPEUTIC_CLASS, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_FORM));

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

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.DC_ITEM_AVAILABLESEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(new ArrayList<String>());

		actionConfigList.add(loadDataTableActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemgtnReset01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEMSEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcItemgtnReset01");
		searchButtonConfig.setComponentName("RESET");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcItemgtnReset01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig.addActionParameter("Reset Confirmation");
		resetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_I_GRP_DESC, "");
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_BRAND, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_STRENGTH, "");
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_THERAPEUTIC_CLASS, null);
		resetMap.put(GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_FORM, null);

		resetActionConfig.addActionParameter(resetMap);

		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addAvailableResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Results");
		panelConfig.setComponentId("dcItemAvailableResultPanel");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
		componentList.add(panelConfig);
		addAvailableResultLayout(componentList);
	}

	private void addAvailableResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemAvailableResultlayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addAvailableResultTableComponent(componentList);
	}

	private void addAvailableResultTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_AVAILABLESEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("");
		searchResultConfig.setParentComponentId("dcItemAvailableResultlayout");
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
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE });
		searchResults.setTableVisibleHeader(new String[] { "Item ID", "Item No", "Item Code", "Item Name", GtnFrameworkCommonConstants.ITEM_DESC_HEADER,
				"Item Start Date", "Item End Date", "Item Status", GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP, "Form", GtnFrameworkCommonConstants.STRENGTH_IFP,
				"Package Size Code", "Package Size Intro Date", "UPPS", "Manufacturer ID", "Manufacturer No",
				"Manufacturer Name", "Labeler Code", "Organization Key", "Acquisition Date", "Authorized Generic",
				"Authorized Generic Start Date", "Authorized Generic End Date", "First Sale Date",
				"Item Type Indicator", "Item Class ", "Item Type ", "Market Termination Date",
				"New Formulation Indicator", "New Formulation", "New Formulation Start Date",
				"New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
				"Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
				"Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
				"Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6",
				"Acquired AMP", "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date",
				"Last Lot Expiration Date", "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP",
				"Base Year CPI" });
		searchResults.setTableColumnMappingId(new Object[] { "itemId", "itemNo", "itemCode", "itemName", "itemDesc",
				"itemStartDate", "itemEndDate", "itemStatus", "therapeuticClass", "brand", "form", "strength",
				"packageSizeCode", "packageSizeIntroDate", "upps", "manufacturerId", "manufacturerNo",
				"manufacturerName", "labelerCode", "organizationKey", "acquisitionDate", "authorizedGeneric",
				"authorizedGenericStartDate", "authorizedGenericEndDate", "firstSaleDate", "itemTypeIndicator",
				"itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator", "newFormulation",
				"newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
				"pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator",
				"clottingFactorStartDate", "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife",
				"shelfLifeType", "dualPricingIndicator", "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6",
				"acquiredAmp", "acquiredBamp", "obraBamp", "dra", "dosesPerUnit", "discontinuationDate",
				"lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode", "baselineAmp",
				"baseYearCpi" });
		// 27 columns
		searchResults.setExtraColumn(new Object[] { "itemMasterSid" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setQueryName("dcAvailableItem");
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
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
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
		gtnLayout.setComponentId("dcItemAvailableTablegtnAdd01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcItemAvailableTablegtnAdd01");
		searchButtonConfig.setComponentName("ADD");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcItemAvailableTablegtnAdd01layout");
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCommonConstants.DC_ITEM_AVAILABLESEARCH_RESULT_TABLE);
		alertParams.add("Edit Error");
		alertParams.add("Please select a record to edit");

		alertActionConfig.setActionParameterList(alertParams);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig
				.addActionParameter("com.stpl.gtn.gtn2o.ui.module.action.GtnUiFrameworkItemADDAddAllButtonAction");
		actionConfigList.add(addActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemAvailableTablegtnAddAll01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcItemAvailableTablegtnAddAll01");
		searchButtonConfig.setComponentName("ADD ALL");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcItemAvailableTablegtnAddAll01layout");
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig
				.addActionParameter("com.stpl.gtn.gtn2o.ui.module.action.GtnUiFrameworkItemADDAddAllButtonAction");

		addActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_TYPE, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_I_GRP_DESC, GtnFrameworkCommonConstants.DC_ITEM_TAB_ITEM_NO, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_BRAND, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_STRENGTH, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_THERAPEUTIC_CLASS, GtnFrameworkCommonConstants.DC_ITEM_INFORMATION_TAB_FORM}));
		actionConfigList.add(addActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSelectedResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Selected Customers");
		panelConfig.setComponentId("dcItemSelectedResultPanel");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
		componentList.add(panelConfig);
		addSelectedResultLayout(componentList);
	}

	private void addSelectedResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("dcItemSelectedResultlayout");
		gtnLayout.setParentComponentId("dcItemSelectedResultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSelectedPagedTableComponent(componentList);
	}

	private void addSelectedPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("dcItemSelectedResultTable");
		searchResultConfig.setComponentName("");
		searchResultConfig.setParentComponentId("dcItemSelectedResultlayout");
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
		searchResults.setTableColumnDataType(new Class<?>[] {

				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE });
		searchResults.setTableVisibleHeader(new String[] { "Item ID", "Item No", "Item Code", "Item Name", GtnFrameworkCommonConstants.ITEM_DESC_HEADER,
				"Item Start Date", "Item End Date", "Item Status", GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP, "Form", GtnFrameworkCommonConstants.STRENGTH_IFP,
				"Package Size Code", "Package Size Intro Date", "UPPS", "Manufacturer ID", "Manufacturer No",
				"Manufacturer Name", "Labeler Code", "Organization Key", "Acquisition Date", "Authorized Generic",
				"Authorized Generic Start Date", "Authorized Generic End Date", "First Sale Date",
				"Item Type Indicator", "Item Class ", "Item Type ", "Market Termination Date",
				"New Formulation Indicator", "New Formulation", "New Formulation Start Date",
				"New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
				"Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
				"Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
				"Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6",
				"Acquired AMP", "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date",
				"Last Lot Expiration Date", "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP",
				"Base Year CPI" });
		searchResults.setTableColumnMappingId(new Object[] { "itemId", "itemNo", "itemCode", "itemName", "itemDesc",
				"itemStartDate", "itemEndDate", "itemStatus", "therapeuticClass", "brand", "form", "strength",
				"packageSizeCode", "packageSizeIntroDate", "upps", "manufacturerId", "manufacturerNo",
				"manufacturerName", "labelerCode", "organizationKey", "acquisitionDate", "authorizedGeneric",
				"authorizedGenericStartDate", "authorizedGenericEndDate", "firstSaleDate", "itemTypeIndicator",
				"itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator", "newFormulation",
				"newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
				"pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator",
				"clottingFactorStartDate", "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife",
				"shelfLifeType", "dualPricingIndicator", "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6",
				"acquiredAmp", "acquiredBamp", "obraBamp", "dra", "dosesPerUnit", "discontinuationDate",
				"lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode", "baselineAmp",
				"baseYearCpi" });
		searchResults.setExtraColumn(new Object[] { "itemMasterSid" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("dcSelectedItem");
		searchResults.setQueryName("dcSelectedItem");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addSelectedTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.DC_ITEM_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SELECTION_TAB_MAIN_CSS_LAYOUT);
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
		gtnLayout.setComponentId("dcItemSelectedTablegtnRemove01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcItemSelectedTablegtnRemove01");
		searchButtonConfig.setComponentName("REMOVE");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcItemSelectedTablegtnRemove01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add("dcItemSelectedResultTable");
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
		gtnLayout.setComponentId("dcItemSelectedTablegtnRemoveAll01layout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.DC_ITEM_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcItemSelectedTablegtnRemoveAll01");
		searchButtonConfig.setComponentName("REMOVE ALL");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("dcItemSelectedTablegtnRemoveAll01layout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
