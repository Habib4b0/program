package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdHolidaySelectionAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdResetAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdSaveAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdYearChangeAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.constants.GtnFrameworkCalendarConfigurationContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.calendarfield.GtnUIFrameworkCalendarConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.calendarconfiguration.constants.GtnWsCalendarConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCalendarConfigurationCrudConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getCalendarConfigView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW,
				GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW, false);
		addCCCrudViewComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addCCCrudViewComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCCCrudViewMainPanel(componentList, namspacePrefix);
	}

	private void addCCCrudViewMainPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig(namspacePrefix + "MainPanel", false, null);
		componentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(namspacePrefix + "MainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addCCCrudViewSearchCriteriaPanel(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addCCrudCalendarPanel(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCCrudViewButtonLayout(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());

	}

	private void addCCCrudViewSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addCCCrudViewFieldLayout(componentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addCCCrudViewButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig(namspacePrefix + "saveButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addCCCrudViewSaveButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addCCCrudViewResetButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCCrudViewCloseButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addCCCrudViewSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchCCCrudButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSave", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchCCCrudButtonConfig.setComponentName("SAVE");
		searchCCCrudButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchCCCrudButtonConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(componentIdList);
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);

		GtnUIFrameWorkActionConfig failureActionConfig = new GtnUIFrameWorkActionConfig();
		failureActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		failureActionConfig.addActionParameter("Please complete all mandatory fields");

		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkCurdSaveAction.class.getName());
		saveActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		saveActionConfig.setFieldValues(GtnFrameworkCalendarConfigurationContants.getFieldList());

		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter(" Are you sure you want to save the Calendar?");
		confirmActionConfig.addActionParameter(Arrays.asList(saveActionConfig));

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(confirmActionConfig));

		searchCCCrudButtonConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void addCCCrudViewResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkCurdResetAction.class.getName());
		resetActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		resetActionConfig.setFieldValues(GtnFrameworkCalendarConfigurationContants.getFieldList());

		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Are you sure you want to Reset the screen?");
		confirmActionConfig.addActionParameter(Arrays.asList(resetActionConfig));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);
	}

	private void addCCCrudViewCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnClose", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAuthorizationIncluded(true);
		componentList.add(closeButtonConfig);

		GtnUIFrameWorkActionConfig confirmActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter(
				"Are you sure you want to close the Calendar Configuration screen?  Nothing will be saved.");

		GtnUIFrameWorkActionConfig navigateActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigateActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		confirmActionConfig.addActionParameter(Arrays.asList(navigateActionConfig));

		closeButtonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);

	}

	private void addCCCrudViewFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "searchFieldLayout", true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		componentList.add(gtnLayout);
		addCCrudFieldComponent(componentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addCCrudCalendarPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkCalendarConfigurationContants.CALENDAR_FIELD;
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(componentId + "Panel", true, parent);
		componentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.CALENDAR_FIELD);
		panelConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkCalendarConfig calendarConfig = new GtnUIFrameworkCalendarConfig();
		calendarConfig.setImmediate(true);
		componentConfig.setCalendarConfig(calendarConfig);
		componentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addCCrudFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addCCrudCalendarName(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCCrudCalendarDescription(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCCrudCalendarYear(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addCCrudDefaultHolidays(componentList, namspacePrefix, parent, nullValidationConfig);
		addCCrudCountry(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);

	}

	private void addCCrudCalendarName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String calendarName = namspacePrefix + "CalendarName";
		GtnUIFrameworkComponentConfig calendarNameLayoutConfig = configProvider
				.getHorizontalLayoutConfig(calendarName + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(calendarNameLayoutConfig);

		GtnUIFrameworkComponentConfig calendarNameConfig = configProvider.getUIFrameworkComponentConfig(calendarName,
				true, calendarNameLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		calendarNameConfig.setComponentName("Calendar Name");
		calendarNameConfig.setAuthorizationIncluded(true);
		componentList.add(calendarNameConfig);
		componentIdList.add(calendarNameConfig.getComponentId());
		calendarNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCCrudCalendarDescription(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String calendarDesc = namspacePrefix + "CalendarDescription";
		GtnUIFrameworkComponentConfig calendarDescLayoutConfig = configProvider
				.getHorizontalLayoutConfig(calendarDesc + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(calendarDescLayoutConfig);

		GtnUIFrameworkComponentConfig calendarDescConfig = configProvider.getUIFrameworkComponentConfig(calendarDesc,
				true, calendarDescLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		calendarDescConfig.setComponentName("Calendar Description");
		calendarDescConfig.setAuthorizationIncluded(true);
		componentList.add(calendarDescConfig);
		componentIdList.add(calendarDescConfig.getComponentId());
		calendarDescConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCCrudCalendarYear(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String calendarYear = namspacePrefix + "CalendarYear";
		GtnUIFrameworkComponentConfig calendarYearLayoutConfig = configProvider
				.getHorizontalLayoutConfig(calendarYear + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(calendarYearLayoutConfig);

		GtnUIFrameworkComponentConfig calendarYearConfig = configProvider.getUIFrameworkComponentConfig(calendarYear,
				true, calendarYearLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		calendarYearConfig.setComponentName("Calendar Year");
		calendarYearConfig.setAuthorizationIncluded(true);
		componentList.add(calendarYearConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setItemValues(GtnFrameworkCalendarConfigurationContants.getYearDdlbValue());
		calendarYearConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(calendarYearConfig.getComponentId());
		calendarYearConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		GtnUIFrameWorkActionConfig yearChangeActionConfig = new GtnUIFrameWorkActionConfig();
		yearChangeActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		yearChangeActionConfig.addActionParameter(GtnFrameworkCurdYearChangeAction.class.getName());
		yearChangeActionConfig.setFieldValues(Arrays.asList(namspacePrefix + "DefaultHolidays",
				namspacePrefix + GtnFrameworkCalendarConfigurationContants.CALENDAR_FIELD));
		calendarYearConfig.addGtnUIFrameWorkActionConfig(yearChangeActionConfig);
	}

	private void addCCrudDefaultHolidays(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String defaultHolidays = namspacePrefix + "DefaultHolidays";
		GtnUIFrameworkComponentConfig defaultHolidaysLayoutConfig = configProvider
				.getHorizontalLayoutConfig(defaultHolidays + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(defaultHolidaysLayoutConfig);

		GtnUIFrameworkComponentConfig defaultHolidaysConfig = configProvider.getUIFrameworkComponentConfig(
				defaultHolidays, true, defaultHolidaysLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.CHECKBOX);
		defaultHolidaysConfig.setComponentName("Default Holidays");
		defaultHolidaysConfig.setAuthorizationIncluded(true);
		componentList.add(defaultHolidaysConfig);
		defaultHolidaysConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		GtnUIFrameWorkActionConfig yearChangeActionConfig = new GtnUIFrameWorkActionConfig();
		yearChangeActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		yearChangeActionConfig.addActionParameter(GtnFrameworkCurdHolidaySelectionAction.class.getName());
		yearChangeActionConfig.setFieldValues(
				Arrays.asList(namspacePrefix + GtnFrameworkCalendarConfigurationContants.CALENDAR_FIELD));
		defaultHolidaysConfig.addGtnUIFrameWorkActionConfig(yearChangeActionConfig);
	}

	private void addCCrudCountry(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String country = namspacePrefix + "Country";
		GtnUIFrameworkComponentConfig countryLayoutConfig = configProvider
				.getHorizontalLayoutConfig(country + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(countryLayoutConfig);

		GtnUIFrameworkComponentConfig countryConfig = configProvider.getUIFrameworkComponentConfig(country, true,
				countryLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		countryConfig.setComponentName("Country");
		countryConfig.setAuthorizationIncluded(true);
		componentList.add(countryConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsCalendarConfigurationConstants.COUNTRY);
		countryConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(countryConfig.getComponentId());
		countryConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

}
