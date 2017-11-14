package com.stpl.gtn.gtn2o.ui.module.customergroup.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpBackBtnAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpClassContants;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCGrpAddComapanyConfig {

	public GtnUIFrameworkViewConfig getCGrpAddView() {
		GtnFrameworkComponentConfigProvider addViewComponentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig cGroupAddView = addViewComponentConfig.getViewConfig("Add View", "V002", false);
		addComponentList(cGroupAddView, addViewComponentConfig);
		return cGroupAddView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		List<GtnUIFrameworkComponentConfig> cGrpComponentList = new ArrayList<>();
		view.setGtnComponentList(cGrpComponentList);
		addCGrpInfoPanel(cGrpComponentList, addViewComponentConfig);
		addSaveButtonLayout(cGrpComponentList, addViewComponentConfig);
	}

	private void addCGrpInfoPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGroupInfoPanel = addViewComponentConfig
				.getPanelConfig(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_PANEL, false, null);
		cGroupInfoPanel.setComponentName("Customer Group Information");
		cGroupInfoPanel.setAuthorizationIncluded(true);
		componentList.add(cGroupInfoPanel);

		addCGrpInfoFieldLayout(componentList, addViewComponentConfig);
		addCustomerSearchPanel(componentList, addViewComponentConfig);
		addButtonLayout(componentList, addViewComponentConfig);
		addAvailableResultPanel(componentList, addViewComponentConfig);
		addAvailableTableActionButtonLayout(componentList, addViewComponentConfig);
		addSelectedResultPanel(componentList, addViewComponentConfig);
		addSelectedTableActionButtonLayout(componentList, addViewComponentConfig);
	}

	private void addCGrpInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGroupInfoLayout = addViewComponentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_LAYOUT, true,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_PANEL, GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cGroupInfoLayout.setComponentStyle(styleList);
		componentList.add(cGroupInfoLayout);

		addCGrpInfoFieldComponent(componentList, addViewComponentConfig);
	}

	private void addCGrpInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		addCGrpName(componentList, addViewComponentConfig);
		addCGrpNo(componentList, addViewComponentConfig);
		addCGrpDesc(componentList, addViewComponentConfig);
	}

	private void addCGrpName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewCGrpNamelayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpNamelayout", true, GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewCGrpNamelayout);

		GtnUIFrameworkComponentConfig adddViewCGroupNameConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME, true,
				addViewCGrpNamelayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		adddViewCGroupNameConfig.setAuthorizationIncluded(true);
		adddViewCGroupNameConfig.setComponentName("Customer Group Name");
		adddViewCGroupNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(adddViewCGroupNameConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		adddViewCGroupNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCGrpNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewCGrpNolayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpNolayout", true, GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewCGrpNolayout);

		GtnUIFrameworkComponentConfig addViewCGroupNoConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpInformationCGrpNo", true, addViewCGrpNolayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		addViewCGroupNoConfig.setAuthorizationIncluded(true);
		addViewCGroupNoConfig.setComponentName("Customer Group No");
		addViewCGroupNoConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(addViewCGroupNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewCGroupNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addCGrpDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig addViewCGrpDesclayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpDesclayout", true, GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewCGrpDesclayout);

		GtnUIFrameworkComponentConfig addViewCGroupDescConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpInformationCGrpDesc", true, addViewCGrpDesclayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		addViewCGroupDescConfig.setAuthorizationIncluded(true);
		addViewCGroupDescConfig.setComponentName("Customer Group Desc");
		addViewCGroupDescConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(addViewCGroupDescConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewCGroupDescConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addCustomerSearchPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchPanelConfig = addViewComponentConfig
				.getPanelConfig("cGrpCustomerSearchPanel", false, null);
		addViewSearchPanelConfig.setComponentName("Customer Search");
		componentList.add(addViewSearchPanelConfig);

		addCustomerSearchFieldLayout(componentList, addViewComponentConfig);
	}

	private void addCustomerSearchFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGroupInfoLayout = addViewComponentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT, true, "cGrpCustomerSearchPanel",
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		itemGroupInfoLayout.setComponentStyle(styleList);
		componentList.add(itemGroupInfoLayout);

		addCustomerSearchFieldComponent(componentList, addViewComponentConfig);
	}

	private void addCustomerSearchFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		addCustomerNo(componentList, addViewComponentConfig);
		addTradeClass(componentList, addViewComponentConfig);
		addCustomerStatus(componentList, addViewComponentConfig);
		addState(componentList, addViewComponentConfig);
		addCustomerName(componentList, addViewComponentConfig);
		addCustomerType(componentList, addViewComponentConfig);
		addCity(componentList, addViewComponentConfig);
		addZipCode(componentList, addViewComponentConfig);

	}

	private void addCustomerNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchCGrpNolayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabCustomerNolayout", true,
				GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchCGrpNolayout);

		GtnUIFrameworkComponentConfig addViewSearchCGroupNoConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NO, true,
						addViewSearchCGrpNolayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addViewSearchCGroupNoConfig.setComponentName("Customer No");
		addViewSearchCGroupNoConfig.setAuthorizationIncluded(true);
		componentList.add(addViewSearchCGroupNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewSearchCGroupNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addTradeClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchTradeClasslayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabTradeClasslayout", true,
				GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchTradeClasslayout);

		GtnUIFrameworkComponentConfig addViewTradeClassConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_TRADE_CLASS, true,
				addViewSearchTradeClasslayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		addViewTradeClassConfig.setComponentName("Trade Class");
		addViewTradeClassConfig.setAuthorizationIncluded(true);
		componentList.add(addViewTradeClassConfig);

		GtnUIFrameworkComboBoxConfig addViewTradeClassComboConfig = addViewComponentConfig
				.getComboBoxConfig("COMPANY_TRADE_CLASS", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewTradeClassConfig.setGtnComboboxConfig(addViewTradeClassComboConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewTradeClassConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCustomerStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewCustomerStatuslayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabCustomerStatuslayout", true,
				GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewCustomerStatuslayout);

		GtnUIFrameworkComponentConfig addViewCustomerStatusConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_STATUS,
						true, addViewCustomerStatuslayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		addViewCustomerStatusConfig.setAuthorizationIncluded(true);
		addViewCustomerStatusConfig.setComponentName(GtnFrameworkCGrpStringContants.CUSTOMER_STATUS);
		componentList.add(addViewCustomerStatusConfig);

		GtnUIFrameworkComboBoxConfig addViewItmTypeComboConfig = addViewComponentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewCustomerStatusConfig.setGtnComboboxConfig(addViewItmTypeComboConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewCustomerStatusConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addState(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchStatelayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabStatelayout", true, GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchStatelayout);

		GtnUIFrameworkComponentConfig addViewSearchStateConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_STATE, true,
				addViewSearchStatelayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		addViewSearchStateConfig.setAuthorizationIncluded(true);
		addViewSearchStateConfig.setComponentName(GtnFrameworkCGrpStringContants.STATE);
		componentList.add(addViewSearchStateConfig);

		GtnUIFrameworkComboBoxConfig addViewStateComboConfig = addViewComponentConfig.getComboBoxConfig("STATE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewSearchStateConfig.setGtnComboboxConfig(addViewStateComboConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewSearchStateConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCustomerName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchCGrpNamelayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabCustomerNamelayout", true,
				GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchCGrpNamelayout);

		GtnUIFrameworkComponentConfig addViewSearchCGroupNameConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NAME, true,
						addViewSearchCGrpNamelayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addViewSearchCGroupNameConfig.setAuthorizationIncluded(true);
		addViewSearchCGroupNameConfig.setComponentName("Customer Name");
		componentList.add(addViewSearchCGroupNameConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewSearchCGroupNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCustomerType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchCustomerTypelayout = addViewComponentConfig
				.getHorizontalLayoutConfig("cGrpInformationTabCustomerTypelayout", true,
						GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchCustomerTypelayout);

		GtnUIFrameworkComponentConfig addViewSearchCustTypeConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_TYPE, true,
						addViewSearchCustomerTypelayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		addViewSearchCustTypeConfig.setAuthorizationIncluded(true);
		addViewSearchCustTypeConfig.setComponentName(GtnFrameworkCGrpStringContants.CUSTOMER_TYPE);
		componentList.add(addViewSearchCustTypeConfig);

		GtnUIFrameworkComboBoxConfig addViewCustTypeComboConfig = addViewComponentConfig
				.getComboBoxConfig("COMPANY_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewSearchCustTypeConfig.setGtnComboboxConfig(addViewCustTypeComboConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewSearchCustTypeConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addCity(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchCitylayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabCitylayout", true, GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchCitylayout);

		GtnUIFrameworkComponentConfig addViewSearchCityConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CITY, true,
				addViewSearchCitylayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addViewSearchCityConfig.setAuthorizationIncluded(true);
		addViewSearchCityConfig.setComponentName("City");
		componentList.add(addViewSearchCityConfig);

		GtnUIFrameworkValidationConfig cityValidationConfig = new GtnUIFrameworkValidationConfig();
		cityValidationConfig.setMaxLength(5);
		addViewSearchCityConfig.setGtnUIFrameworkValidationConfig(cityValidationConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewSearchCityConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addZipCode(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchZipCodelayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpInformationTabZipcodelayout", true, GtnFrameworkCGrpStringContants.C_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewSearchZipCodelayout);

		GtnUIFrameworkComponentConfig addViewSearchZipcodeConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_ZIPCODE, true,
				addViewSearchZipCodelayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addViewSearchZipcodeConfig.setAuthorizationIncluded(true);
		addViewSearchZipcodeConfig.setComponentName(GtnFrameworkCGrpStringContants.ZIP_CODE);
		componentList.add(addViewSearchZipcodeConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		addViewSearchZipcodeConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		GtnUIFrameworkValidationConfig zipCodeValidationConfig = new GtnUIFrameworkValidationConfig();
		zipCodeValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewSearchZipcodeConfig.setGtnUIFrameworkValidationConfig(zipCodeValidationConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGroupAddvwSearchCriteriaButtonLayout = addViewComponentConfig
				.getCssLayoutConfig(GtnFrameworkCGrpStringContants.C_GRPSEARCH_BUTTONLAYOUT, false, null);
		cGroupAddvwSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cGroupAddvwSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cGroupAddvwSearchCriteriaButtonLayout.setSpacing(true);
		cGroupAddvwSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(cGroupAddvwSearchCriteriaButtonLayout);

		addSearchButtonComponent(componentList, addViewComponentConfig);
		addResetButtonComponent(componentList, addViewComponentConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"gtnSearch01layout", true, GtnFrameworkCGrpStringContants.C_GRPSEARCH_BUTTONLAYOUT);
		componentList.add(addViewSearchBtnLayout);

		GtnUIFrameworkComponentConfig addViewSearchButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"gtnSearch01", true, addViewSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addViewSearchButtonConfig.setAuthorizationIncluded(true);
		addViewSearchButtonConfig.setComponentName("SEARCH");
		componentList.add(addViewSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		searchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NO,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_TRADE_CLASS,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_STATUS,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_STATE,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NAME,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CITY,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_ZIPCODE));

		GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_SEARCH_ERROR);
		alertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_NO_SEARCH_FOUND);

		searchAlertActionConfig.setActionParameterList(alertParamsList);
		Object validationType = GtnUIFrameworkValidationType.OR;
		searchValidationActionConfig
				.setActionParameterList(Arrays.asList(validationType, Arrays.asList(searchAlertActionConfig)));
		searchActionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRP_AVAILABLESEARCH_RESULT_TABLE);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NO,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_TRADE_CLASS,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_STATUS,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_STATE,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NAME,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CITY,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_ZIPCODE));

		searchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		notificationActionConfig
				.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TABLE_EMPTY_VALIDATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRP_AVAILABLESEARCH_RESULT_TABLE);
		notificationActionConfig
				.addActionParameter(GtnFrameworkCGrpStringContants.GTN_CUSTOMER_GRP_NO_RESULTS_FOUND_MSG);
		searchActionConfigList.add(notificationActionConfig);

		addViewSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewResetBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpgtnReset01layout", true, GtnFrameworkCGrpStringContants.C_GRPSEARCH_BUTTONLAYOUT);
		componentList.add(addViewResetBtnLayout);

		GtnUIFrameworkComponentConfig addViewResetButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpgtnReset01", true, addViewResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addViewResetButtonConfig.setAuthorizationIncluded(true);
		addViewResetButtonConfig.setComponentName("RESET");
		componentList.add(addViewResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_CONFIRMATION_MSG_RESET);

		List<String> resetComponentIdList = new ArrayList<>();
		String[] componentIdArray = { GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NO,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_TRADE_CLASS,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_STATUS,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_STATE,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NAME,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CITY,
				GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_ZIPCODE };
		resetComponentIdList.addAll(Arrays.asList(componentIdArray));

		List<Object> resetComponentValueList = new ArrayList<>();

		Object[] resetComponentValueArray = { GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY };
		resetComponentValueList.addAll(Arrays.asList(resetComponentValueArray));

		resetActionConfig.addActionParameter(resetComponentIdList);
		resetActionConfig.addActionParameter(resetComponentValueList);

		resetActionConfigList.add(resetActionConfig);
		addViewResetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addAvailableResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig cGroupSearchResultConfig = addViewComponentConfig
				.getPanelConfig("cGrpAvailableResultPanel", false, null);
		cGroupSearchResultConfig.setComponentName("Results");
		cGroupSearchResultConfig.setAuthorizationIncluded(true);
		cGroupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(cGroupSearchResultConfig);

		addAvailableResultLayout(componentList, addViewComponentConfig);
	}

	private void addAvailableResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewResultTableLayout = addViewComponentConfig
				.getHorizontalLayoutConfig("cGrpAvailableResultlayout", true, "cGrpAvailableResultPanel");
		addViewResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(addViewResultTableLayout);

		addAvailableResultTableComponent(componentList, addViewComponentConfig);
	}

	private void addAvailableResultTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchResultConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_AVAILABLESEARCH_RESULT_TABLE, true, "cGrpAvailableResultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		addViewSearchResultConfig.setAuthorizationIncluded(true);
		addViewSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(addViewSearchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		searchResultsTableConfig.setEditable(false);
		searchResultsTableConfig.setFilterBar(true);
		searchResultsTableConfig.setSelectable(true);
		searchResultsTableConfig.setMultiSelect(true);
		searchResultsTableConfig.setSinkItemPerPageWithPageLength(false);

		searchResultsTableConfig.setTableColumnDataType(new Class[] { String.class, String.class, String.class,
				String.class, String.class, Date.class, Date.class, String.class, String.class, String.class,
				Date.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, Integer.class, Date.class,
				Date.class, Date.class, Date.class });

		searchResultsTableConfig.setTableVisibleHeader(new String[] { "Organization Key ", "Customer ID",
				"Customer No ", "Customer Name ", "Trade Class ", "Trade Class Start Date ", "Trade Class End Date",
				GtnFrameworkCGrpStringContants.CUSTOMER_TYPE, GtnFrameworkCGrpStringContants.CUSTOMER_STATUS, "Lives",
				"Customer End Date", "UDC1", "UDC2", "Customer Group", "Financial System", "Address 1", "Address 2",
				"City", GtnFrameworkCGrpStringContants.STATE, GtnFrameworkCGrpStringContants.ZIP_CODE, "Country",
				"Region Code", "Parent Customer No", "Parent Start Date", "Parent End Date", "Customer Start Date",
				"Prior Parent Start Date" });
		searchResultsTableConfig.setTableColumnMappingId(new Object[] { "organizationKey", "customerId", "customerNo",
				"customerName", "tradeClass", "tradeClassStartDate", "tradeClassEndDate", "customerType",
				"customerStatus", "lives", "customerEndDate", "udc1", "udc2", "customerGroup", "financialSystem",
				"address1", "address2", "city", "state", "zipCode", "country", "regionCode", "parentCustomerNo",
				"parentStartDate", "parentEndDate", "customerStartDate", "priorParentStartDate" });
		// 27 columns
		searchResultsTableConfig
				.setExtraColumn(new Object[] { "companyMasterSid", "companyTradeClass", "parentCompanyMasterSid" });
		searchResultsTableConfig.setExtraColumnDataType(new Class[] { Integer.class, Integer.class, Integer.class });
		searchResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResultsTableConfig.setQueryName("cGrpAddTabSearchQuery");
		searchResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_GROUPS);
		addViewSearchResultConfig.setGtnPagedTableConfig(searchResultsTableConfig);

		searchResultsTableConfig.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> doubleClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig doubleClickActionConfig = new GtnUIFrameWorkActionConfig();
		doubleClickActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		doubleClickActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_ADD_BUTTON_ACTION);
		doubleClickActionConfigList.add(doubleClickActionConfig);
		searchResultsTableConfig.setGtnUIFrameWorkActionConfigList(doubleClickActionConfigList);
	}

	private void addAvailableTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig cGrpAddViewAddActionBtnLayout = addViewComponentConfig
				.getCssLayoutConfig(GtnFrameworkCGrpStringContants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT, false, null);
		cGrpAddViewAddActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cGrpAddViewAddActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cGrpAddViewAddActionBtnLayout.setSpacing(true);
		cGrpAddViewAddActionBtnLayout.setMargin(true);
		componentList.add(cGrpAddViewAddActionBtnLayout);

		addButtonComponent(componentList, addViewComponentConfig);
		addAllButtonComponent(componentList, addViewComponentConfig);
		addAvailableExcelButtonComponent(componentList, addViewComponentConfig);
	}

	private void addButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig cGrpAddViewAddBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpAvailableTablegtnAdd01layout", true,
				GtnFrameworkCGrpStringContants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(cGrpAddViewAddBtnLayout);

		GtnUIFrameworkComponentConfig cGrpaddViewAddButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpAvailableTablegtnAdd01", true, cGrpAddViewAddBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		cGrpaddViewAddButtonConfig.setAuthorizationIncluded(true);
		cGrpaddViewAddButtonConfig.setComponentName("ADD");
		componentList.add(cGrpaddViewAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addAlertActionConfig = new GtnUIFrameWorkActionConfig();
		addAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> addAlertParamsList = new ArrayList<>();
		addAlertParamsList.add(GtnFrameworkCGrpStringContants.C_GRP_AVAILABLESEARCH_RESULT_TABLE);
		addAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_ADD_MSG);
		addAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_ADD_MSG_BODY);

		addAlertActionConfig.setActionParameterList(addAlertParamsList);
		addActionConfigList.add(addAlertActionConfig);

		GtnUIFrameWorkActionConfig addCustomActionConfig = new GtnUIFrameWorkActionConfig();
		addCustomActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addCustomActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_ADD_BUTTON_ACTION);
		addActionConfigList.add(addCustomActionConfig);
		cGrpaddViewAddButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);
	}

	private void addAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpAddViewAddAllBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpAvailableTablegtnAddAll01layout", true,
				GtnFrameworkCGrpStringContants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(cGrpAddViewAddAllBtnLayout);

		GtnUIFrameworkComponentConfig cGrpAddViewAddAllButtonConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("cGrpAvailableTablegtnAddAll01", true,
						cGrpAddViewAddAllBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		cGrpAddViewAddAllButtonConfig.setAuthorizationIncluded(true);
		cGrpAddViewAddAllButtonConfig.setComponentName("ADD ALL");
		componentList.add(cGrpAddViewAddAllButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addAllActionConfig = new GtnUIFrameWorkActionConfig();
		addAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addAllActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_ADD_ALL_ACTION);

		addAllActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NO,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_TRADE_CLASS,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_STATUS,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_STATE,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_NAME,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_CITY,
						GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_TAB_ZIPCODE));
		actionConfigList.add(addAllActionConfig);
		cGrpAddViewAddAllButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addAvailableExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewExcelBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpAvailableTablegtnExcellayout", true,
				GtnFrameworkCGrpStringContants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		addViewExcelBtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(addViewExcelBtnLayout);

		GtnUIFrameworkComponentConfig addViewExcelButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpAvailableTablegtnExcelBtn", true, addViewExcelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		addViewExcelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig availableExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		availableExcelButtonConfig.setExportFileName("Available Customer Results");
		availableExcelButtonConfig.setTitleNeeded(true);
		availableExcelButtonConfig.setExportFromTable(true);
		availableExcelButtonConfig.setExportTableId(GtnFrameworkCGrpStringContants.C_GRP_AVAILABLESEARCH_RESULT_TABLE);

		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		resultTableExcelAction.addActionParameter(availableExcelButtonConfig);

		addViewExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(availableExcelButtonConfig);
		addViewExcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(addViewExcelButtonConfig);

	}

	private void addSelectedResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGroupSelectedTablePanelConfig = addViewComponentConfig
				.getPanelConfig("cGrpSelectedResultPanel", false, null);
		cGroupSelectedTablePanelConfig.setComponentName("Selected Customers");
		cGroupSelectedTablePanelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cGroupSelectedTablePanelConfig);

		addSelectedResultLayout(componentList, addViewComponentConfig);
	}

	private void addSelectedResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpSelectedResultTableLayout = addViewComponentConfig
				.getHorizontalLayoutConfig("cGrpSelectedResultlayout", true, "cGrpSelectedResultPanel");
		cGrpSelectedResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cGrpSelectedResultTableLayout);

		addSelectedPagedTableComponent(componentList, addViewComponentConfig);
	}

	private void addSelectedPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSelectedResultConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("cGrpSelectedResultTable", true, "cGrpSelectedResultlayout",
						GtnUIFrameworkComponentType.PAGEDTABLE);
		addViewSelectedResultConfig.setAuthorizationIncluded(true);
		addViewSelectedResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(addViewSelectedResultConfig);

		GtnUIFrameworkPagedTableConfig selectedRecordTableConfig = new GtnUIFrameworkPagedTableConfig();
		selectedRecordTableConfig.setEditable(false);
		selectedRecordTableConfig.setFilterBar(true);
		selectedRecordTableConfig.setSelectable(true);
		selectedRecordTableConfig.setMultiSelect(true);
		selectedRecordTableConfig.setSinkItemPerPageWithPageLength(false);

		selectedRecordTableConfig.setTableColumnDataType(new Class[] { Integer.class, String.class, String.class,
				String.class, String.class, Date.class, Date.class, String.class, String.class, Integer.class,
				Date.class, String.class, String.class, Integer.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, Date.class,
				Date.class, Date.class, Date.class, String.class });

		selectedRecordTableConfig.setTableVisibleHeader(new String[] { "Organization Key ", "Customer ID",
				"Customer No ", "Customer Name ", "Trade Class ", "Trade Class Start Date ", "Trade Class End Date",
				GtnFrameworkCGrpStringContants.CUSTOMER_TYPE, GtnFrameworkCGrpStringContants.CUSTOMER_STATUS, "Lives",
				"Customer End Date", "UDC1", "UDC2", "Customer Group", "Financial System", "Address 1", "Address 2",
				"City", GtnFrameworkCGrpStringContants.STATE, GtnFrameworkCGrpStringContants.ZIP_CODE, "Country",
				"Region Code", "Parent Customer No", "Parent Start Date", "Parent End Date", "Customer Start Date",
				"Prior Parent Start Date", "Prior Parent Customer No" });
		selectedRecordTableConfig.setTableColumnMappingId(
				new Object[] { "organizationKey", "customerId", "customerNo", "customerName", "tradeClass",
						"tradeClassStartDate", "tradeClassEndDate", "customerType", "customerStatus", "lives",
						"customerEndDate", "udc1", "udc2", "customerGroup", "financialSystem", "address1", "address2",
						"city", "state", "zipCode", "country", "regionCode", "parentCustomerNo", "parentStartDate",
						"parentEndDate", "customerStartDate", "priorParentStartDate", "priorParentCustomerNo" });
		selectedRecordTableConfig.setExtraColumn(new Object[] { "companyMasterSid" });
		selectedRecordTableConfig.setExtraColumnDataType(new Class[] { Integer.class });
		selectedRecordTableConfig.setCountUrl(GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
				+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SELECTED_TABLE_SERVICE);
		selectedRecordTableConfig.setResultSetUrl(GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
				+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SELECTED_TABLE_SERVICE);
		selectedRecordTableConfig.setModuleName("companyGroupsMaster");
		selectedRecordTableConfig.setQueryName("cGrpAddTabSelectedSearchQuery");
		selectedRecordTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_GROUPS);
		addViewSelectedResultConfig.setGtnPagedTableConfig(selectedRecordTableConfig);
	}

	private void addSelectedTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpAddViewRemoveActionBtnLayout = addViewComponentConfig.getCssLayoutConfig(
				GtnFrameworkCGrpStringContants.C_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT, false, null);
		cGrpAddViewRemoveActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cGrpAddViewRemoveActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cGrpAddViewRemoveActionBtnLayout.setSpacing(true);
		cGrpAddViewRemoveActionBtnLayout.setMargin(true);
		componentList.add(cGrpAddViewRemoveActionBtnLayout);

		addRemoveButtonComponent(componentList, addViewComponentConfig);
		addRemoveAllButtonComponent(componentList, addViewComponentConfig);
		addSelectedExcelButtonComponent(componentList, addViewComponentConfig);
	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpAddViewRemoveBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpSelectedTablegtnRemove01layout", true,
				GtnFrameworkCGrpStringContants.C_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(cGrpAddViewRemoveBtnLayout);

		GtnUIFrameworkComponentConfig addViewRemoveButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpSelectedTablegtnRemove01", true, cGrpAddViewRemoveBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		addViewRemoveButtonConfig.setAuthorizationIncluded(true);
		addViewRemoveButtonConfig.setComponentName("REMOVE");
		componentList.add(addViewRemoveButtonConfig);

		List<GtnUIFrameWorkActionConfig> removeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeActionConfig = new GtnUIFrameWorkActionConfig();
		removeActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_REMOVE_ACTION);
		removeActionConfigList.add(removeActionConfig);
		addViewRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(removeActionConfigList);
	}

	private void addRemoveAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpAddViewRemoveAllBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpSelectedTablegtnRemoveAll01layout", true,
				GtnFrameworkCGrpStringContants.C_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(cGrpAddViewRemoveAllBtnLayout);

		GtnUIFrameworkComponentConfig addViewRemoveAllButtonConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("cGrpSelectedTablegtnRemoveAll01", true,
						cGrpAddViewRemoveAllBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addViewRemoveAllButtonConfig.setComponentName("REMOVE ALL");
		addViewRemoveAllButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addViewRemoveAllButtonConfig);

		List<GtnUIFrameWorkActionConfig> removeAllactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_REMOVE_ALL_ACTION);
		removeAllactionConfigList.add(addActionConfig);
		addViewRemoveAllButtonConfig.setGtnUIFrameWorkActionConfigList(removeAllactionConfigList);

	}

	private void addSelectedExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpAddViewSelectedExcelBtnLayout = addViewComponentConfig
				.getHorizontalLayoutConfig("cGrpSelectedTablegtnExcellayout", true,
						GtnFrameworkCGrpStringContants.C_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		cGrpAddViewSelectedExcelBtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(cGrpAddViewSelectedExcelBtnLayout);

		GtnUIFrameworkComponentConfig addViewSelectedExcelButtonConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("cGrpSelectedgtnExcelBtn", true,
						cGrpAddViewSelectedExcelBtnLayout.getComponentId(), GtnUIFrameworkComponentType.EXCEL_BUTTON);
		addViewSelectedExcelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig selectedRecordExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		selectedRecordExcelButtonConfig.setIsTreeTable(false);
		selectedRecordExcelButtonConfig.setExportFileName("Selected Customer Results");
		selectedRecordExcelButtonConfig.setTitleNeeded(true);
		selectedRecordExcelButtonConfig.setExportFromTable(true);
		selectedRecordExcelButtonConfig.setExportTableId("cGrpSelectedResultTable");

		GtnUIFrameWorkActionConfig selectedTableExcelAction = new GtnUIFrameWorkActionConfig();
		selectedTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		selectedTableExcelAction.addActionParameter(selectedRecordExcelButtonConfig);

		addViewSelectedExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(selectedRecordExcelButtonConfig);
		addViewSelectedExcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(selectedTableExcelAction));
		componentList.add(addViewSelectedExcelButtonConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkLayoutConfig cGrpSaveBtnlayout = new GtnUIFrameworkLayoutConfig();
		cGrpSaveBtnlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cGrpSaveBtnlayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig cGrpSaveBtnLayoutConfig = new GtnUIFrameworkComponentConfig();
		cGrpSaveBtnLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cGrpSaveBtnLayoutConfig.setComponentId(GtnFrameworkCGrpStringContants.C_GRPSAVE_BUTTONLAYOUT);
		cGrpSaveBtnLayoutConfig.setSpacing(true);
		cGrpSaveBtnLayoutConfig.setMargin(true);
		cGrpSaveBtnLayoutConfig.setAddToParent(false);
		cGrpSaveBtnLayoutConfig.setGtnLayoutConfig(cGrpSaveBtnlayout);
		componentList.add(cGrpSaveBtnLayoutConfig);

		addBackButtonComponent(componentList, addViewComponentConfig);
		addSaveButtonComponent(componentList, addViewComponentConfig);
		addActionResetButtonComponent(componentList, addViewComponentConfig);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpBackBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpAddBackButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRPSAVE_BUTTONLAYOUT);
		componentList.add(cGrpBackBtnLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpAddBackButton", true, cGrpBackBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setComponentName("BACK");
		backButtonConfig.setAuthorizationIncluded(true);
		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> backBtnActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig backBtnConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		backBtnConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		backBtnConfirmationActionConfig.addActionParameter(GtnFrameworkCGrpBackBtnAction.class.getName());
		backBtnConfirmationActionConfig
				.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_CONFIRMATION);
		backBtnConfirmationActionConfig
				.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_BACK_CONFIRMATION);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);
		backBtnConfirmationActionConfig.addActionParameter(onSucessActionConfigList);
		backBtnActionConfigList.add(backBtnConfirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(backBtnActionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpSaveBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"cGrpAddSaveButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRPSAVE_BUTTONLAYOUT);
		componentList.add(cGrpSaveBtnLayout);

		GtnUIFrameworkComponentConfig cGrpSaveButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"cGrpAddSaveButton", true, cGrpSaveBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		cGrpSaveButtonConfig.setComponentName("SAVE");
		cGrpSaveButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cGrpSaveButtonConfig);

		List<GtnUIFrameWorkActionConfig> cGrpActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig cGrpValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cGrpValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		cGrpValidationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME,
						"cGrpInformationCGrpNo", "cGrpInformationCGrpDesc"));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> saveAlertParamsList = new ArrayList<>();
		saveAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_SAVE_HEADER);
		saveAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_SAVE_ERROR);

		alertActionConfig.setActionParameterList(saveAlertParamsList);
		Object validationType = GtnUIFrameworkValidationType.AND;
		cGrpValidationActionConfig
				.setActionParameterList(Arrays.asList(validationType, Arrays.asList(alertActionConfig)));
		cGrpActionConfigList.add(cGrpValidationActionConfig);

		GtnUIFrameWorkActionConfig saveCustomValidationAction = new GtnUIFrameWorkActionConfig();
		saveCustomValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveCustomValidationAction
				.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_COMMON_VALIDATION_ACTION);
		cGrpActionConfigList.add(saveCustomValidationAction);

		GtnUIFrameWorkActionConfig saveConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		saveConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> saveConfirmationParamsList = new ArrayList<>();
		saveConfirmationParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_CONFIRMATION);
		saveConfirmationParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_SAVE_CONFIRMATION);
		saveConfirmationParamsList.add(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_SAVE_ACTION);
		onSucessActionConfigList.add(customAction);
		saveConfirmationParamsList.add(onSucessActionConfigList);
		saveConfirmationActionConfig.setActionParameterList(saveConfirmationParamsList);
		cGrpActionConfigList.add(saveConfirmationActionConfig);
		cGrpSaveButtonConfig.setGtnUIFrameWorkActionConfigList(cGrpActionConfigList);

	}

	private void addActionResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig cGrpResetBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"CGrpADDResetButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRPSAVE_BUTTONLAYOUT);
		componentList.add(cGrpResetBtnLayout);

		GtnUIFrameworkComponentConfig cGrpResetButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"CGrpADDResetButton", true, cGrpResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		cGrpResetButtonConfig.setComponentName("RESET");
		cGrpResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cGrpResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig resetConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		resetConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> resetAlertParamsList = new ArrayList<>();
		resetAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_RESET_HEADER);
		resetAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_RESET_ERROR);

		List<GtnUIFrameWorkActionConfig> resetOnSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig resetNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		resetNavigationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetNavigationActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_RESET_ACTION);
		resetOnSucessActionConfigList.add(resetNavigationActionConfig);
		/**
		 * To clear Temp Table
		 */
		GtnUIFrameWorkActionConfig tempclearActionConfig = new GtnUIFrameWorkActionConfig();
		tempclearActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tempclearActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TEMP_TABLE_CLEAR_ACTION);
		resetOnSucessActionConfigList.add(tempclearActionConfig);
		resetAlertParamsList.add(resetOnSucessActionConfigList);

		resetConfirmationActionConfig.setActionParameterList(resetAlertParamsList);

		resetActionConfigList.add(resetConfirmationActionConfig);

		cGrpResetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

}
