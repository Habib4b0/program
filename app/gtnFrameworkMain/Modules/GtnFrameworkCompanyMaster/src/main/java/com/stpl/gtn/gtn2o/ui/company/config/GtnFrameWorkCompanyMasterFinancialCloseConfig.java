/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnFrameWorkCompanyMasterFinancialCloseConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addFinancialCloseTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_TAB, false, null);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(layoutConfig);
		financialCloseInformationPanel(componentList);
		financialCloseOpenButtonLayout(componentList);
		financialCloseResultPanel(componentList);

	}

	private void financialCloseInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("financialCloseInformationPanel", true,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_TAB);
		panel.setMargin(true);
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				"financialCloseInformationPanelLayout", true, "financialCloseInformationPanel");
		layoutConfig.setComponentWidth("100%");
		componentList.add(layoutConfig);

		financialCloseInformationLayout(componentList);
	}

	private void financialCloseInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT, true,
				"financialCloseInformationPanelLayout", GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);

		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		financialCloseInformationFields(componentList);
	}

	private void financialCloseInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addFinancialCloseMode(componentList);
		addFinancialCloseMonth(componentList);
		addFinancialCloseBusinessDay(componentList);
		addFinancialCloseBusinessHour(componentList);
		addFinancialCloseMinute(componentList);
		addFinancialCloseCalendar(componentList);
		addFinancialCloseYear(componentList);
	}

	private void addFinancialCloseMode(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("FinancialCloseModelayout",
				true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseMode = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_MODE, true, "FinancialCloseModelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		financialCloseMode.setComponentName("Mode");
		financialCloseMode.setAuthorizationIncluded(true);

		componentList.add(financialCloseMode);

		GtnUIFrameworkComboBoxConfig financialCloseModeConfig = configProvider.getComboBoxConfig("ARM_MODE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		financialCloseMode.setGtnComboboxConfig(financialCloseModeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.FINANCIAL_MODE_CUSTOM_ACTION);
		actionConfigList.add(customAction);
		financialCloseMode.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addFinancialCloseMonth(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("financialCloseMonthlayout",
				true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseMonth = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_MONTH, true, "financialCloseMonthlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		financialCloseMonth.setComponentName("Month");
		financialCloseMonth.setAuthorizationIncluded(true);

		financialCloseMonth.setEnable(false);
		componentList.add(financialCloseMonth);

		GtnUIFrameworkComboBoxConfig monthConfig = configProvider.getComboBoxConfig("Business_Month_Load",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		Calendar mCalendar = Calendar.getInstance();
		String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		monthConfig.setDefaultDesc(month);
		financialCloseMonth.setGtnComboboxConfig(monthConfig);

	}

	private void addFinancialCloseBusinessDay(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"financialCloseBusinessDaylayout", true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessDayConfig = configProvider.getUIFrameworkComponentConfig(
				"financialCloseBusinessDay", true, "financialCloseBusinessDaylayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		businessDayConfig.setAuthorizationIncluded(true);
		businessDayConfig.setComponentName("Business Day");

		GtnUIFrameworkComboBoxConfig financialCloseDayConfig = new GtnUIFrameworkComboBoxConfig();
		financialCloseDayConfig.setItemValues(GtnFrameworkCompanyStringContants.getGtnBusinessDay());
		businessDayConfig.setGtnComboboxConfig(financialCloseDayConfig);
		businessDayConfig.setEnable(false);
		componentList.add(businessDayConfig);

	}

	private void addFinancialCloseBusinessHour(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"financialCloseBusinesslayoutHour", true,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessHourConfig = configProvider.getUIFrameworkComponentConfig(
				"financialCloseBusinessHour", true, "financialCloseBusinesslayoutHour",
				GtnUIFrameworkComponentType.COMBOBOX);
		businessHourConfig.setAuthorizationIncluded(true);
		businessHourConfig.setComponentName("Hour");
		businessHourConfig.setEnable(false);
		componentList.add(businessHourConfig);

		GtnUIFrameworkComboBoxConfig financialCloseHourConfig = new GtnUIFrameworkComboBoxConfig();
		financialCloseHourConfig.setItemValues(GtnFrameworkCompanyStringContants.getGtnBusinessHour());
		businessHourConfig.setGtnComboboxConfig(financialCloseHourConfig);

	}

	private void addFinancialCloseMinute(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("financialCloseMinutelayout",
				true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseMinute = configProvider.getUIFrameworkComponentConfig(
				"financialCloseMinute", true, "financialCloseMinutelayout", GtnUIFrameworkComponentType.COMBOBOX);
		financialCloseMinute.setComponentName("Minute");
		financialCloseMinute.setAuthorizationIncluded(true);
		financialCloseMinute.setEnable(false);
		componentList.add(financialCloseMinute);

		GtnUIFrameworkComboBoxConfig minuteConfig = configProvider.getComboBoxConfig("ARM_MINUTE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		financialCloseMinute.setGtnComboboxConfig(minuteConfig);
	}

	private void financialCloseOpenButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_ATTACH_BUTTON_LAYOUT, true,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_TAB);
		componentList.add(layoutConfig);
		financialCloseResetButton(componentList);
		financialCloseCloseButton(componentList);
		financialCloseOpenButton(componentList);
	}

	private void financialCloseCloseButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig closeButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"financialCloseButton", true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_ATTACH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentName("Close");
		closeButtonConfig.setAuthorizationIncluded(true);
		closeButtonConfig.setComponentWidth("50%");
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.FINANCIAL_MODE_CLOSE_ACTION_VALIDATION);
		customAction.addActionParameter("Closed");
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_CLOSE);
		alertParamsList.add(GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_ALERT_MSG);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(confirmationActionConfig);

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCompanyClassContants.FINANCIAL_MODE_CLOSE_CUSTOM_ACTION);
		actionParameter.add(GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_TABLE);
		actionParameter.add("Closed");
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.MODE_DESC,
				GtnFrameworkCompanyStringContants.CALENDAR_DESC, GtnFrameworkCompanyStringContants.MONTH, "year"));
		actionParameter
				.add(Arrays.asList("financialCloseMode", GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_CALENDAR,
						GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_MONTH,
						GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_YEAR));
		actionParameter.add(Arrays.asList(String.class, String.class, String.class, String.class));
		selectAction.setFieldValues(
				Arrays.asList(GtnFrameworkCompanyStringContants.USER_ID, GtnFrameworkCompanyStringContants.SESSION_ID));
		selectAction.setActionParameterList(actionParameter);
		onSucessActionConfigList.add(selectAction);

		closeButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void financialCloseOpenButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"financialCloseOpenButton", true,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_ATTACH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Open");
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setAddToParent(true);
		attachButtonConfig.setComponentWidth("50%");
		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.FINANCIAL_MODE_CLOSE_ACTION_VALIDATION);
		customAction.addActionParameter("Open");
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_OPEN);
		alertParamsList.add(GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_OPEN_ALERT_MSG);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(confirmationActionConfig);

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCompanyClassContants.FINANCIAL_MODE_CLOSE_CUSTOM_ACTION);
		actionParameter.add("financialCloseResultTable");
		actionParameter.add("Open");
		actionParameter.add(Arrays.asList("modeDescription", "calendarDesc", "month", "year"));
		actionParameter
				.add(Arrays.asList("financialCloseMode", GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_CALENDAR,
						GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_MONTH,
						GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_YEAR));
		actionParameter.add(Arrays.asList(String.class, String.class, String.class, String.class));
		selectAction.setActionParameterList(actionParameter);
		onSucessActionConfigList.add(selectAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void financialCloseResetButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"financialCloseResetButton", true,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_ATTACH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("Reset");
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentWidth("50%");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_RESET_ALERT_MSG);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.FINANCIAL_RESET_ACTION_CUSTOM);
		onSucessActionConfigList.add(customAction);
		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void financialCloseResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("financialCloseResultPanel", true,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_TAB);
		panel.setComponentName("History");
		panel.setAuthorizationIncluded(true);
		panel.setMargin(true);
		componentList.add(panel);
		financialCloseResultLayout(componentList);
	}

	private void financialCloseResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig("financialCloseResultLayout", true, "financialCloseResultPanel");
		layoutConfig.setComponentWidth("100%");

		componentList.add(layoutConfig);

		financialCloseResultPagedTable(componentList);
	}

	private void financialCloseResultPagedTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				"financialCloseResultTable", true, "financialCloseResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentName("RESULTS");

		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				String.class, Date.class, Date.class, String.class });
		searchResults.setTableVisibleHeader(new String[] { "Mode", "Status", "Calendar", "Month", "Year", "Date/Time",
				"Created Date", "Created By" });
		searchResults.setTableColumnMappingId(new Object[] { "modeDescription", "statusDesc", "calendarDesc", "month",
				"year", "statusPeriodDate", "createdDate", "createdBy" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);

		searchResults.setQueryName("FinancialCloseTableLoadData");
		searchResults.setTableDateColumnObject(new String[] { "statusPeriodDate" });
		searchResults.setTableDateColumnFormat(new String[] { "MM/dd/yyyy HH:mm:ss" });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_FINANCIAL_HEADER);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addFinancialCloseCalendar(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"financialCloseCalendarlayout", true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseCalendar = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_CALENDAR, true, "financialCloseCalendarlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		financialCloseCalendar.setAuthorizationIncluded(true);
		financialCloseCalendar.setComponentName("Calendar");

		financialCloseCalendar.setEnable(false);
		componentList.add(financialCloseCalendar);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(
				GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE + GtnWebServiceUrlConstants.GTN_CM_GET_CALENDAR);
		financialCloseCalendar.setGtnComboboxConfig(companyStatusConfig);

	}

	private void addFinancialCloseYear(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("financialCloseYearlayout",
				true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseYear = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_YEAR, true, "financialCloseYearlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		financialCloseYear.setAuthorizationIncluded(true);
		financialCloseYear.setComponentName("Year");

		componentList.add(financialCloseYear);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameworkComboBoxConfig yearConfig = new GtnUIFrameworkComboBoxConfig();
		yearConfig.setLoadingUrl(
				GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE + GtnWebServiceUrlConstants.GTN_CM_BUSINESS_YEAR);
		financialCloseYear.setGtnComboboxConfig(yearConfig);
		financialCloseYear.setGtnUIFrameWorkActionConfigList(actionConfigList);
		addFinancialCloseUserId(componentList);
		addFinancialCloseSessionId(componentList);

	}

	private void addFinancialCloseUserId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("financialCloseUserlayout",
				true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseUserId = configProvider.getUIFrameworkComponentConfig("userId",
				true, "financialCloseUserlayout", GtnUIFrameworkComponentType.TEXTBOX);
		financialCloseUserId.setComponentName("userId");
		financialCloseUserId.setAuthorizationIncluded(true);
		financialCloseUserId.setVisible(false);
		componentList.add(financialCloseUserId);

	}

	private void addFinancialCloseSessionId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"financialCloseSessionIdlayout", true, GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_INFO_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig financialCloseSession = configProvider.getUIFrameworkComponentConfig("SessionId",
				true, "financialCloseSessionIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		financialCloseSession.setComponentName("SessionId");
		financialCloseSession.setAuthorizationIncluded(true);
		financialCloseSession.setVisible(false);

		componentList.add(financialCloseSession);

	}

}
