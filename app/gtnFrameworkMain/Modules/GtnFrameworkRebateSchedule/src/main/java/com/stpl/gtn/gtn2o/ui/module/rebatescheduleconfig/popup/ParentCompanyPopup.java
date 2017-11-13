package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class ParentCompanyPopup {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Parent Company",
				GtnFrameworkCommonConstants.PARENT_COMPANY_VIEW, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addParentCompanyPopupFieldLayout(componentList);
		addParentCompanyPopupButtonLayout(componentList);
		addParentCompanyPopupResultPanel(componentList);
		addParentCompanyPopupActionButtonLayout(componentList);

	}

	private void addParentCompanyPopupFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIA_LAYOUT, false, null);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addParentCompanyPopupResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("parentCompanyresultPanel", false,
				null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentCompanyresultlayout",
				true, "parentCompanyresultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addParentCompanyPopupButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_BUTTON_LAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addParentCompanyPopupSearchButton(componentList);
		addParentCompanyPopupResetButton(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addParentCompanyPopupCompanyId(componentList);
		addParentCompanyPopupCompanyNo(componentList);
		addParentCompanyPopupCompanyName(componentList);
		addParentCompanyPopupCompanyStatus(componentList);
		addParentCompanyPopupCompanyType(componentList);
	}

	private void addParentCompanyPopupCompanyId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyIdlayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_ID, true,
				"parentCompanySearchcompanyIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Company ID");

		componentList.add(companyIdConfig);
	}

	private void addParentCompanyPopupCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyNolayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanySearchcompanyNo", true, "parentCompanySearchcompanyNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Company NO");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addParentCompanyPopupCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyNamelayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanySearchcompanyName", true, "parentCompanySearchcompanyNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Company Name");

		componentList.add(companyNameConfig);
	}

	private void addParentCompanyPopupCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyStatuslayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				"parentCompanySearchcompanyStatus", true, "parentCompanySearchcompanyStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Company Status");

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addParentCompanyPopupCompanyType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyTypelayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_TYPE, true,
				"parentCompanySearchcompanyTypelayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Company Type");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addParentCompanyPopupSearchButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanygtnSearch01", true, GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_ID, "parentCompanySearchcompanyNo",
				"parentCompanySearchcompanyName", "parentCompanySearchcompanyStatus",
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_TYPE }));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		List<String> validationFields = new ArrayList<>();
		validationFields.add(GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_ID);
		validationActionConfig.setFieldValues(validationFields);

		actionConfigList.add(validationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addParentCompanyPopupResetButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanygtnReset01", true, GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Reset");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.RESET_ACTION, "Reset Confirmation",
				"Are you sure you want to reset the values in the Search Criteria group box?",
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_ID,
						GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_COMPANY_TYPE }),
				Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY, null }));

		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig parentCompanyPopupResultsTableResultConfig = configProvider
				.getUIFrameworkComponentConfig(GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_RESULT_TABLE, true,
						"parentCompanyresultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		parentCompanyPopupResultsTableResultConfig.setComponentName("Results");
		parentCompanyPopupResultsTableResultConfig.setAuthorizationIncluded(true);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		parentCompanyPopupResultsTableResultConfig.setComponentStyle(tableStyle);

		componentList.add(parentCompanyPopupResultsTableResultConfig);

		GtnUIFrameworkPagedTableConfig parentCompanyPopupResultsTable = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyMaster", "SearchQuery");
		parentCompanyPopupResultsTable.setEditable(false);
		parentCompanyPopupResultsTable.setTableColumnDataType(
				new Class[] { String.class, String.class, String.class, String.class, String.class });
		parentCompanyPopupResultsTable.setTableVisibleHeader(
				new String[] { "Company ID", "Company No", "Company Name", "Company Status", "Company Type" });
		parentCompanyPopupResultsTable.setExtraColumn(new Object[] { "companyMasterSid" });
		parentCompanyPopupResultsTable.setExtraColumnDataType(new Class[] { Integer.class });
		parentCompanyPopupResultsTable.setTableColumnMappingId(
				new Object[] { "companyId", "companyNo", "companyName", "companyStatus", "companyType" });
		parentCompanyPopupResultsTableResultConfig.setGtnPagedTableConfig(parentCompanyPopupResultsTable);
	}

	private void addParentCompanyPopupActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_COMPANYACTION_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanySelectButton", true, GtnFrameworkCommonConstants.PARENT_COMPANYACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Select");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_RESULT_TABLE);
		actionParameter.add("parentCompanyNo");
		actionParameter.add(Arrays.asList(new String[] { "companyNo", "companyName" }));
		actionParameter.add(Arrays.asList(new String[] { "parentCompanyNo", "parentCompanyName" }));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.PARENT_COMPANY_VIEW }));
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanyCloseButton", true, GtnFrameworkCommonConstants.PARENT_COMPANYACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Close");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.PARENT_COMPANY_VIEW }));
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}
}
