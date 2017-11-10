package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkParentIfpPopupConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Parent Item Family Plan",
				GtnFrameworkCommonConstants.PARENT_IFP_VIEW, false);
		addParentIfpComponentList(view);
		return view;
	}

	private void addParentIfpComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addParentIfpFieldLayout(componentList);
		addParentIfpButtonLayout(componentList);
		addParentIfpResultPanel(componentList);
		addParentIfpActionButtonLayout(componentList);

	}

	private void addParentIfpFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(false);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addParentIfpFieldComponent(componentList);
	}

	private void addParentIfpResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentIfpResultPanelConfig = configProvider.getPanelConfig("parentIfpResultPanel",
				false, null);
		parentIfpResultPanelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(parentIfpResultPanelConfig);
		addParentIfpResultLayout(componentList);
	}

	private void addParentIfpResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentIfpResultlayoutConfig = configProvider
				.getHorizontalLayoutConfig("parentIfpResultlayout", true, "parentIfpResultPanel");
		parentIfpResultlayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(parentIfpResultlayoutConfig);
		addPagedTableComponent(componentList);
	}

	private void addParentIfpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addParentIfpSearchButtonComponent(componentList);
		addParentIfpResetButtonComponent(componentList);
	}

	private void addParentIfpFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addParentIFPId(componentList);
		addParentIFPNo(componentList);
		addParentIFPName(componentList);
		addParentIFPStatus(componentList);
		addParentIFPType(componentList);
	}

	private void addParentIFPId(List<GtnUIFrameworkComponentConfig> componentList) {
		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentifpIdlayout", GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_CRITERIALAYOUT, true, componentList);
		GtnUIFrameworkComponentConfig parentIfpIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENTIFP_ID, true, "parentifpIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentIfpIdConfig.setAuthorizationIncluded(true);
		parentIfpIdConfig.setComponentName("IFP ID");
		setDefaultTextBoxConfig(50, new GtnUIFrameworkTextBoxConfig(), new GtnUIFrameworkValidationConfig(),
				parentIfpIdConfig, componentList);
	}

	private void setDefaultTextBoxConfig(int maxLength, GtnUIFrameworkTextBoxConfig gtnMaxLengthTextBoxConfig,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig,
			GtnUIFrameworkComponentConfig parentIfpNoConfig, List<GtnUIFrameworkComponentConfig> componentList) {
		gtnMaxLengthTextBoxConfig.setMaximumLength(maxLength);
		parentIfpNoConfig.setGtnTextBoxConfig(gtnMaxLengthTextBoxConfig);
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		parentIfpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(parentIfpNoConfig);

	}

	private void addParentIFPNo(List<GtnUIFrameworkComponentConfig> componentList) {

		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpNolayout", GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_CRITERIALAYOUT, true, componentList);

		GtnUIFrameworkComponentConfig ifpNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_IFP_NO, true, "parentIfpNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpNoConfig.setAuthorizationIncluded(true);
		ifpNoConfig.setComponentName("IFP NO");

		GtnUIFrameworkTextBoxConfig gtnMaxLengthTextBoxConfig = new GtnUIFrameworkTextBoxConfig();
		gtnMaxLengthTextBoxConfig.setMaximumLength(50);
		ifpNoConfig.setGtnTextBoxConfig(gtnMaxLengthTextBoxConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(ifpNoConfig);
	}

	private void addParentIFPName(List<GtnUIFrameworkComponentConfig> componentList) {
		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpNamelayout", GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_CRITERIALAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_IFP_NAME, true, "parentIfpNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpNameConfig.setAuthorizationIncluded(true);
		ifpNameConfig.setComponentName("IFP Name");

		GtnUIFrameworkTextBoxConfig gtnMaxLengthTextBoxConfig = new GtnUIFrameworkTextBoxConfig();
		gtnMaxLengthTextBoxConfig.setMaximumLength(100);
		ifpNameConfig.setGtnTextBoxConfig(gtnMaxLengthTextBoxConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(ifpNameConfig);
	}

	private void addParentIFPStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpStatuslayout", GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_CRITERIALAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpStatusConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_IFP_STATUS, true, "parentIfpStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		ifpStatusConfig.setAuthorizationIncluded(true);
		ifpStatusConfig.setComponentName("IFP Status");

		componentList.add(ifpStatusConfig);

		GtnUIFrameworkComboBoxConfig parentIfpStatusConfig = new GtnUIFrameworkComboBoxConfig();
		parentIfpStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		parentIfpStatusConfig.setComboBoxType("STATUS");
		ifpStatusConfig.setGtnComboboxConfig(parentIfpStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ifpStatusConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addParentIFPType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentIfpTypelayout", true,
				GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentIfpType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_IFP_TYPE, true, "parentIfpTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		parentIfpType.setAuthorizationIncluded(true);
		parentIfpType.setComponentName("IFP Type");
		componentList.add(parentIfpType);

		GtnUIFrameworkComboBoxConfig parentIfpTypeConfig = new GtnUIFrameworkComboBoxConfig();
		parentIfpTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		parentIfpTypeConfig.setComboBoxType("IFP_TYPE");
		parentIfpType.setGtnComboboxConfig(parentIfpTypeConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		parentIfpType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addParentIfpSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpSearchButton01layout", GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_BUTTONLAYOUT, true,
				componentList);
		GtnUIFrameworkComponentConfig searchButtonConfig = creatingButtonConfig("parentIfpgtnSearch01", "Search",
				"parentIfpSearchButton01layout", componentList);
		searchButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PARENTIFP_ID,
				GtnFrameworkCommonConstants.PARENT_IFP_NO, GtnFrameworkCommonConstants.PARENT_IFP_NAME,
				GtnFrameworkCommonConstants.PARENT_IFP_STATUS, GtnFrameworkCommonConstants.PARENT_IFP_TYPE));

		GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> searchAlertParamsList = new ArrayList<>();
		searchAlertParamsList.add("Search Criteria ");
		searchAlertParamsList.add("Please enter Search Criteria");

		searchAlertActionConfig.setActionParameterList(searchAlertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(searchAlertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.PARENT_IFPSEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PARENTIFP_ID,
				GtnFrameworkCommonConstants.PARENT_IFP_NO, GtnFrameworkCommonConstants.PARENT_IFP_NAME,
				GtnFrameworkCommonConstants.PARENT_IFP_STATUS, GtnFrameworkCommonConstants.PARENT_IFP_TYPE));

		actionConfigList.add(loadDataTableActionConfig);
		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PARENT_IFPSEARCH_RESULT_TABLE);
		notificationActionConfig.addActionParameter(0);
		actionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addParentIfpResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpResetButton01layout", GtnFrameworkCommonConstants.PARENT_IFP_SEARCH_BUTTONLAYOUT, true,
				componentList);
		GtnUIFrameworkComponentConfig searchButtonConfig = creatingButtonConfig("parentIfpgtnReset01", "Reset",
				"parentIfpResetButton01layout", componentList);
		searchButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig.addActionParameter(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET);
		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET_HEADER);
		params.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET);

		params.add(Arrays.asList(GtnFrameworkCommonConstants.PARENTIFP_ID, GtnFrameworkCommonConstants.PARENT_IFP_NO,
				GtnFrameworkCommonConstants.PARENT_IFP_NAME, GtnFrameworkCommonConstants.PARENT_IFP_STATUS,
				GtnFrameworkCommonConstants.PARENT_IFP_TYPE));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig parentIfpSearchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_IFPSEARCH_RESULT_TABLE, true, "parentIfpResultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		parentIfpSearchResultConfig.setAuthorizationIncluded(true);
		parentIfpSearchResultConfig.setComponentName("Results");
		parentIfpSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		parentIfpSearchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_500);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		parentIfpSearchResultConfig.setComponentStyle(tableStyleList);

		componentList.add(parentIfpSearchResultConfig);

		GtnUIFrameworkPagedTableConfig parentIfpSearchResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"itemFamilyPlan", "parentIfpSearchQuery");
		parentIfpSearchResults.setEditable(false);
		parentIfpSearchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE });

		parentIfpSearchResults.setTableVisibleHeader(new String[] { "System ID", "IFP ID", " IFP No", " IFP Name",
				"IFP Type", "IFP Status", "IFP Category", "IFP Start Date", "IFP End Date", "IFP Designation",
				"Parent IFP ID", "Parent IFP Name", "Created By", "Created Date" });
		parentIfpSearchResults.setTableColumnMappingId(
				new Object[] { "itemFamilyplanSystemId", "ifpId", "ifpNo", "ifpName", "ifpType", "ifpStatus",
						"ifpCategory", "itemFamilyplanStartDate", "itemFamilyplanEndDate", "ifpDesignation",
						"parentItemFamilyplanId", "parentIetmFamilyplanName", "ifpcreatedBy", "ifpcreatedDate" });
		parentIfpSearchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_FAMILY_PLAN);
		parentIfpSearchResultConfig.setGtnPagedTableConfig(parentIfpSearchResults);
	}

	private void addParentIfpActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_IFPACTION_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);

		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpSelectButton01layout", GtnFrameworkCommonConstants.PARENT_IFPACTION_BUTTONLAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig searchButtonConfig = creatingButtonConfig("parentIfpSelectButton", "Select",
				"parentIfpSelectButton01layout", componentList);
		searchButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		selectAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_IFPSEARCH_RESULT_TABLE);
		selectAction.addActionParameter("ifpInformationTabParentIFPId");
		selectAction.addActionParameter(Arrays.asList("ifpId", "ifpName"));
		selectAction
				.addActionParameter(Arrays.asList("ifpInformationTabParentIFPId", "ifpInformationTabParentIFPName"));
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_IFP_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		getCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"parentIfpClosetButton01layout", GtnFrameworkCommonConstants.PARENT_IFPACTION_BUTTONLAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig searchButtonConfig = creatingButtonConfig("parentIfpCloseButton", "Close",
				"parentIfpClosetButton01layout", componentList);
		searchButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_IFP_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private GtnUIFrameworkComponentConfig creatingButtonConfig(String componentId, String componentName,
			String parentComponentName, List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(componentId,
				true, parentComponentName, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName(componentName);
		componentList.add(searchButtonConfig);
		return searchButtonConfig;

	}

	private void getCommonLayout(GtnUIFrameworkLayoutType horizontalLayout, GtnUIFrameworkComponentType layoutType,
			String componentId, String parentComponentId, boolean addToParent,
			List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(horizontalLayout);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkComponentConfig(componentId, addToParent,
				parentComponentId, layoutType);
		gtnLayout.setGtnLayoutConfig(layout);

		componentList.add(gtnLayout);

	}
}
