package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkParentCfpPopupConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig view = componentConfig.getViewConfig("Parent Company Family Plan",
				GtnFrameworkCommonConstants.PARENT_CFP_VIEW, false);
		addComponentList(view, componentConfig);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addFieldLayout(componentList, componentConfig);
		addButtonLayout(componentList, componentConfig);
		addResultPanel(componentList, componentConfig);
		addActionButtonLayout(componentList, componentConfig);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT, false, null,
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultPanelConfig = componentConfig.getPanelConfig("parentCfpResultPanel", false,
				null);
		resultPanelConfig.setComponentName("Results");
		resultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(resultPanelConfig);
		addResultLayout(componentList, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig parentCfpResultPanelLayout = componentConfig
				.getHorizontalLayoutConfig("parentCfpResultlayout", true, "parentCfpResultPanel");
		parentCfpResultPanelLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(parentCfpResultPanelLayout);
		addPagedTableComponent(componentList, componentConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_BUTTON_LAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCFPId(componentList, componentConfig);
		addCFPNo(componentList, componentConfig);
		addCFPName(componentList, componentConfig);
		addCFPStatus(componentList, componentConfig);
		addCFPType(componentList, componentConfig);
		addCompanyId(componentList, componentConfig);
		addCompanyNo(componentList, componentConfig);
		addCompanyName(componentList, componentConfig);
	}

	private void addCFPId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpPopupCFPIdLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpPopupCFPIdlayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpPopupCFPIdLayout);

		GtnUIFrameworkComponentConfig parentCfpPopupCFPId = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID, true, "parentCfpPopupCFPIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCfpPopupCFPId.setAuthorizationIncluded(true);
		parentCfpPopupCFPId.setComponentName("Company Family Plan ID");

		GtnUIFrameworkTextBoxConfig parentCfpPopupCFPIdConfig = new GtnUIFrameworkTextBoxConfig();
		parentCfpPopupCFPIdConfig.setRequired(true);

		GtnUIFrameworkValidationConfig parentCfpPopupCFPIdValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpPopupCFPIdValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		parentCfpPopupCFPId.setGtnUIFrameworkValidationConfig(parentCfpPopupCFPIdValConfig);

		parentCfpPopupCFPId.setGtnTextBoxConfig(parentCfpPopupCFPIdConfig);
		componentList.add(parentCfpPopupCFPId);
	}

	private void addCFPNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpPopupCFPNoLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpPopupCFPNolayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpPopupCFPNoLayout);

		GtnUIFrameworkComponentConfig parentCfpPopupCFPNo = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NO, true, "parentCfpPopupCFPNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCfpPopupCFPNo.setAuthorizationIncluded(true);
		parentCfpPopupCFPNo.setComponentName("Company Family Plan No");

		GtnUIFrameworkTextBoxConfig parentCfpPopupCFPNoConfig = new GtnUIFrameworkTextBoxConfig();
		parentCfpPopupCFPNoConfig.setRequired(true);

		GtnUIFrameworkValidationConfig parentCfpPopupCFPNoValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpPopupCFPNoValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		parentCfpPopupCFPNo.setGtnUIFrameworkValidationConfig(parentCfpPopupCFPNoValConfig);

		parentCfpPopupCFPNo.setGtnTextBoxConfig(parentCfpPopupCFPNoConfig);
		componentList.add(parentCfpPopupCFPNo);

	}

	private void addCFPName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig parentCfpPopupCFPNameLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpPopupCFPNamelayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpPopupCFPNameLayout);

		GtnUIFrameworkComponentConfig parentCfpPopupCFPName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NAME, true, "parentCfpPopupCFPNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCfpPopupCFPName.setAuthorizationIncluded(true);
		parentCfpPopupCFPName.setComponentName("Company Family Plan Name");

		GtnUIFrameworkTextBoxConfig parentCfpPopupCFPNameConfig = new GtnUIFrameworkTextBoxConfig();
		parentCfpPopupCFPNameConfig.setRequired(true);

		GtnUIFrameworkValidationConfig parentCfpPopupCFPNameValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpPopupCFPNameValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		parentCfpPopupCFPName.setGtnUIFrameworkValidationConfig(parentCfpPopupCFPNameValConfig);

		parentCfpPopupCFPName.setGtnTextBoxConfig(parentCfpPopupCFPNameConfig);
		componentList.add(parentCfpPopupCFPName);
	}

	private void addCFPStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpPopupCFPStatusLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpPopupCFPStatuslayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpPopupCFPStatusLayout);

		GtnUIFrameworkComponentConfig parentCfpPopupCFPStatus = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_STATUS, true, "parentCfpPopupCFPStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		parentCfpPopupCFPStatus.setAuthorizationIncluded(true);
		parentCfpPopupCFPStatus.setComponentName("Company Family Plan Status");

		GtnUIFrameworkComboBoxConfig parentCfpPopupCFPStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		parentCfpPopupCFPStatus.setGtnComboboxConfig(parentCfpPopupCFPStatusConfig);

		GtnUIFrameworkValidationConfig parentCfpPopupCFPStatusValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpPopupCFPStatusValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		parentCfpPopupCFPStatus.setGtnUIFrameworkValidationConfig(parentCfpPopupCFPStatusValConfig);
		componentList.add(parentCfpPopupCFPStatus);

	}

	private void addCFPType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpPopupCFPTypeLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpPopupCFPTypelayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpPopupCFPTypeLayout);

		GtnUIFrameworkComponentConfig parentCfpPopupCFPType = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_TYPE, true, "parentCfpPopupCFPTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		parentCfpPopupCFPType.setAuthorizationIncluded(true);
		parentCfpPopupCFPType.setComponentName("Company Family Plan Type");

		GtnUIFrameworkComboBoxConfig parentCfpPopupCFPTypeConfig = componentConfig.getComboBoxConfig("CFP_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig parentCfpPopupCFPTypeValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpPopupCFPTypeValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		parentCfpPopupCFPType.setGtnUIFrameworkValidationConfig(parentCfpPopupCFPTypeValConfig);

		parentCfpPopupCFPType.setGtnComboboxConfig(parentCfpPopupCFPTypeConfig);
		componentList.add(parentCfpPopupCFPType);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpSearchCompanyIdLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpSearchcompanyIdlayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpSearchCompanyIdLayout);

		GtnUIFrameworkComponentConfig parentCfpSearchCompanyId = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_ID, true, "parentCfpSearchcompanyIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCfpSearchCompanyId.setAuthorizationIncluded(true);
		parentCfpSearchCompanyId.setComponentName("Company ID");

		GtnUIFrameworkValidationConfig parentCfpSearchCompanyIdValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpSearchCompanyIdValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		parentCfpSearchCompanyId.setGtnUIFrameworkValidationConfig(parentCfpSearchCompanyIdValConfig);

		componentList.add(parentCfpSearchCompanyId);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpSearchCompanyNolayoutLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpSearchcompanyNolayout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpSearchCompanyNolayoutLayout);

		GtnUIFrameworkComponentConfig parentCfpSearchCompanyNo = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NO, true, "parentCfpSearchcompanyNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCfpSearchCompanyNo.setAuthorizationIncluded(true);
		parentCfpSearchCompanyNo.setComponentName("Company NO");

		GtnUIFrameworkValidationConfig parentCfpSearchCompanyNoValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpSearchCompanyNoValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		parentCfpSearchCompanyNo.setGtnUIFrameworkValidationConfig(parentCfpSearchCompanyNoValConfig);

		componentList.add(parentCfpSearchCompanyNo);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig parentCfpSearchCompanyNameLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpSearchcompanyNamelayout", true,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentCfpSearchCompanyNameLayout);

		GtnUIFrameworkComponentConfig parentCfpSearchCompanyName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NAME, true, "parentCfpSearchcompanyNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCfpSearchCompanyName.setAuthorizationIncluded(true);
		parentCfpSearchCompanyName.setComponentName("Company Name");

		GtnUIFrameworkValidationConfig parentCfpSearchCompanyNameValConfig = new GtnUIFrameworkValidationConfig();
		parentCfpSearchCompanyNameValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		parentCfpSearchCompanyName.setGtnUIFrameworkValidationConfig(parentCfpSearchCompanyNameValConfig);
		componentList.add(parentCfpSearchCompanyName);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpSearchButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpSearchButton01layout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_BUTTON_LAYOUT);
		componentList.add(parentCfpSearchButtonLayout);

		GtnUIFrameworkComponentConfig parentCfpSearchButton = componentConfig.getUIFrameworkComponentConfig(
				"parentCfpgtnSearch01", true, "parentCfpSearchButton01layout", GtnUIFrameworkComponentType.BUTTON);
		parentCfpSearchButton.setComponentName("Search");
		parentCfpSearchButton.setAuthorizationIncluded(true);
		componentList.add(parentCfpSearchButton);

		List<GtnUIFrameWorkActionConfig> parentCfpSearchBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID,
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NO,
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NAME,
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_STATUS,
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_TYPE,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_ID,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NO,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NAME));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
		parentCfpSearchBtnActionConfigList.add(validationActionConfig);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID,
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NO,
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NAME,
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_STATUS,
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_TYPE,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_ID,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NO,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NAME));

		parentCfpSearchBtnActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_RESULT_TABLE);
		notificationActionConfig.addActionParameter(0);
		parentCfpSearchBtnActionConfigList.add(notificationActionConfig);
		parentCfpSearchButton.setGtnUIFrameWorkActionConfigList(parentCfpSearchBtnActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpResetBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpResetButton01layout", true, GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_BUTTON_LAYOUT);
		componentList.add(parentCfpResetBtnLayout);

		GtnUIFrameworkComponentConfig parentCfpResetBtn = componentConfig.getUIFrameworkComponentConfig(
				"parentCfpgtnReset01", true, "parentCfpResetButton01layout", GtnUIFrameworkComponentType.BUTTON);
		parentCfpResetBtn.setComponentName("Reset");
		parentCfpResetBtn.setAuthorizationIncluded(true);
		componentList.add(parentCfpResetBtn);

		List<GtnUIFrameWorkActionConfig> parentCfpResetBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_RESET_HEADER);
		params.add(GtnFrameworkCfpStringContants.GTN_CFP_PARENT_CONFIRMATION_MSG_RESET);

		params.add(Arrays.asList(GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID,
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NO,
				GtnFrameworkCommonConstants.PARENT_CFP_POP_UP_CFP_NAME,
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_STATUS,
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_TYPE,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_ID,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NO,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_COMPANY_NAME,
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_RESULT_TABLE));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null));

		resetActionConfig.setActionParameterList(params);

		parentCfpResetBtnActionConfigList.add(resetActionConfig);
		parentCfpResetBtn.setGtnUIFrameWorkActionConfigList(parentCfpResetBtnActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig cffSearchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_RESULT_TABLE, true, "parentCfpResultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		cffSearchResultConfig.setComponentName("Results");
		cffSearchResultConfig.setAuthorizationIncluded(true);
		cffSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cffSearchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_500);

		GtnUIFrameWorkActionConfig itemClickActionConfig = new GtnUIFrameWorkActionConfig();
		itemClickActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemClickActionConfig
				.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_PARENT_CFP_POPUP_TABLE_ITEM_CLICK_ACTION);
		cffSearchResultConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(itemClickActionConfig));
		componentList.add(cffSearchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyFamilyPlan", "parentCfpSearchQuery");
		searchResults.setEditable(false);
		searchResults.setTableColumnDataType(new Class[] { Integer.class, String.class, String.class, String.class,
				String.class, String.class, String.class, Date.class, Date.class, String.class, String.class,
				String.class, Date.class, String.class, String.class, Date.class });

		searchResults.setTableVisibleHeader(new String[] { "System ID", " CFP ID", " CFP No", "CFP Name", "CFP Type",
				"CFP Status", "CFP Category", "Start Date", " End Date", "CFP Designation", "Parent ID", "Parent No",
				"Modified Date", "Modified By", "Created By", "Created Date" });
		searchResults.setTableColumnMappingId(new Object[] { "companyFamilyPlanSystemId", "companyFamilyPlanId",
				"companyFamilyPlanNo", "companyFamilyPlanName", "companyFamilyPlanType", "companyFamilyPlanStatus",
				"companyFamilyPlanCategory", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate",
				"companyFamilyPlanDesignation", "parentCompanyFamilyPlanId", "parentCompanyFamilyPlanName",
				"cfpmodifiedDate", "cfpmodifiedBy", "cfpcreatedBy", "cfpcreatedDate" });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_FAMILY_PLAN_SEARCH);
		searchResults.setItemClickListener(true);
		cffSearchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig actionBtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_CFP_ACTION_BUTTON_LAYOUT, false, null);
		actionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		actionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(actionBtnLayout);
		addSelectButtonComponent(componentList, componentConfig);
		addCloseButtonComponent(componentList, componentConfig);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpSelectBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpSelectButton01layout", true, GtnFrameworkCommonConstants.PARENT_CFP_ACTION_BUTTON_LAYOUT);
		componentList.add(parentCfpSelectBtnLayout);

		GtnUIFrameworkComponentConfig parentCfpSelectBtn = componentConfig.getUIFrameworkComponentConfig(
				"parentCfpSelectButton", true, "parentCfpSelectButton01layout", GtnUIFrameworkComponentType.BUTTON);
		parentCfpSelectBtn.setComponentName("Select");
		parentCfpSelectBtn.setAuthorizationIncluded(true);
		parentCfpSelectBtn.setEnable(false);
		componentList.add(parentCfpSelectBtn);

		List<GtnUIFrameWorkActionConfig> parentCfpSelectBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		selectAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_CFP_SEARCH_RESULT_TABLE);
		selectAction.addActionParameter("cfpInformationTabParentCFPId");
		selectAction.addActionParameter(
				Arrays.asList("companyFamilyPlanNo", "companyFamilyPlanName", "companyFamilyPlanSystemId"));
		selectAction.addActionParameter(Arrays.asList("cfpInformationTabParentCFPId", "cfpInformationTabParentCFPName",
				"cfpInformationTabParentCFSPId"));
		parentCfpSelectBtnActionConfigList.add(selectAction);
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_CFP_VIEW);
		parentCfpSelectBtnActionConfigList.add(closeAction);
		parentCfpSelectBtn.setGtnUIFrameWorkActionConfigList(parentCfpSelectBtnActionConfigList);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCfpClosetBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"parentCfpClosetButton01layout", true, GtnFrameworkCommonConstants.PARENT_CFP_ACTION_BUTTON_LAYOUT);
		componentList.add(parentCfpClosetBtnLayout);

		GtnUIFrameworkComponentConfig parentCfpClosetBtn = componentConfig.getUIFrameworkComponentConfig(
				"parentCfpCloseButton", true, "parentCfpClosetButton01layout", GtnUIFrameworkComponentType.BUTTON);
		parentCfpClosetBtn.setComponentName("Close");
		parentCfpClosetBtn.setAuthorizationIncluded(true);
		componentList.add(parentCfpClosetBtn);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_CFP_VIEW);
		closeActionConfigList.add(closeAction);
		parentCfpClosetBtn.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}
}
