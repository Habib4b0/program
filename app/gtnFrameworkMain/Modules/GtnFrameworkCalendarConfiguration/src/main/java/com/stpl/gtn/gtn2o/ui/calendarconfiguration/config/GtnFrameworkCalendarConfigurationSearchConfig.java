/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCopyAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdResetAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkDeleteAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkProcessAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.constants.GtnFrameworkCalendarConfigurationContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.calendarconfiguration.constants.GtnWsCalendarConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCalendarConfigurationSearchConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getMainView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkCalendarConfigurationContants.CC_MAIN_VIEW,
				GtnFrameworkCalendarConfigurationContants.CC_MAIN_VIEW, true);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addMainPanel(componentList, namspacePrefix);
	}

	private void addMainPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig(namspacePrefix + "MainPanel", false, null);
		componentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(namspacePrefix + "MainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addCCSearchCriteriaPanel(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addCCButtonLayout(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addCCResultPanel(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCActionButtonLayout(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());

	}

	private void addCCSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addCCFieldLayout(componentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addCCActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getCssLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addCCAddButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCEditButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCViewButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCDeleteButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addCCCopyButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCCExcelButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addCCAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnCCAdd01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig addBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "addBtn", true, namspacePrefix + "gtnCCAdd01Layout", GtnUIFrameworkComponentType.BUTTON);
		addBtnConfig.setComponentName("ADD");
		addBtnConfig.setAuthorizationIncluded(true);
		componentList.add(addBtnConfig);

		GtnUIFrameWorkActionConfig navigationAction = new GtnUIFrameWorkActionConfig();
		navigationAction.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationAction.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		addBtnConfig.addGtnUIFrameWorkActionConfig(navigationAction);

		GtnUIFrameWorkActionConfig processActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		processActionConfig.addActionParameter(GtnFrameworkProcessAction.class.getName());
		processActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		processActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		addBtnConfig.addGtnUIFrameWorkActionConfig(processActionConfig);
	}

	private void addCCEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnCCEdit01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig editBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "editBtn", true, namspacePrefix + "gtnCCEdit01Layout", GtnUIFrameworkComponentType.BUTTON);
		editBtnConfig.setComponentName("EDIT");
		editBtnConfig.setAuthorizationIncluded(true);
		componentList.add(editBtnConfig);

		GtnUIFrameWorkActionConfig editNavigationAction = new GtnUIFrameWorkActionConfig();
		editNavigationAction.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		editNavigationAction.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		GtnUIFrameWorkActionConfig editValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		editValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE));
		editValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig editFailureActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		editFailureActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		editFailureActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CALENDAR_SELECT_ALERT);

		GtnUIFrameWorkActionConfig editProcessActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editProcessActionConfig.addActionParameter(GtnFrameworkProcessAction.class.getName());
		editProcessActionConfig
				.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		editProcessActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		GtnUIFrameWorkActionConfig editResetActionConfig = new GtnUIFrameWorkActionConfig();
		editResetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editResetActionConfig.addActionParameter(GtnFrameworkCurdResetAction.class.getName());
		editResetActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		editResetActionConfig.setFieldValues(GtnFrameworkCalendarConfigurationContants.getFieldList());

		editValidationActionConfig.addActionParameter(Arrays.asList(editFailureActionConfig));
		editValidationActionConfig.addActionParameter(
				Arrays.asList(editNavigationAction, editProcessActionConfig, editResetActionConfig));

		editBtnConfig.addGtnUIFrameWorkActionConfig(editValidationActionConfig);
	}

	private void addCCViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnCCView01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig viewBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "viewBtn", true, namspacePrefix + "gtnCCView01Layout", GtnUIFrameworkComponentType.BUTTON);
		viewBtnConfig.setComponentName("VIEW");
		viewBtnConfig.setAuthorizationIncluded(true);
		componentList.add(viewBtnConfig);

		GtnUIFrameWorkActionConfig viewNavigationAction = new GtnUIFrameWorkActionConfig();
		viewNavigationAction.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		viewNavigationAction.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		GtnUIFrameWorkActionConfig viewValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		viewValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE));
		viewValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig viewFailureActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		viewFailureActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		viewFailureActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CALENDAR_SELECT_ALERT);

		GtnUIFrameWorkActionConfig viewProcessActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewProcessActionConfig.addActionParameter(GtnFrameworkProcessAction.class.getName());
		viewProcessActionConfig
				.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		viewProcessActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		GtnUIFrameWorkActionConfig viewResetActionConfig = new GtnUIFrameWorkActionConfig();
		viewResetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewResetActionConfig.addActionParameter(GtnFrameworkCurdResetAction.class.getName());
		viewResetActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		viewResetActionConfig.setFieldValues(GtnFrameworkCalendarConfigurationContants.getFieldList());

		GtnUIFrameWorkActionConfig viewDisableActionConfig = new GtnUIFrameWorkActionConfig();
		viewDisableActionConfig.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		viewDisableActionConfig.setActionParameterList(
				Arrays.asList(GtnFrameworkCalendarConfigurationContants.getTotalFieldList().toArray()));

		viewValidationActionConfig.addActionParameter(Arrays.asList(viewFailureActionConfig));
		viewValidationActionConfig.addActionParameter(Arrays.asList(viewNavigationAction, viewProcessActionConfig,
				viewResetActionConfig, viewDisableActionConfig));

		viewBtnConfig.addGtnUIFrameWorkActionConfig(viewValidationActionConfig);
	}

	private void addCCDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnCCDelete01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig deleteBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "deleteBtn", true, namspacePrefix + "gtnCCDelete01Layout", GtnUIFrameworkComponentType.BUTTON);
		deleteBtnConfig.setComponentName("DELETE");
		deleteBtnConfig.setAuthorizationIncluded(true);
		componentList.add(deleteBtnConfig);

		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkDeleteAction.class.getName());
		deleteActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		deleteActionConfig.setFieldValues(componentIdList);
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Are you sure you want to delete the selected Calendar?");
		confirmActionConfig.addActionParameter(Arrays.asList(deleteActionConfig));

		GtnUIFrameWorkActionConfig deleteValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		deleteValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE));
		deleteValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig deleteFailureActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		deleteFailureActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		deleteFailureActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CALENDAR_SELECT_ALERT);

		deleteValidationActionConfig.addActionParameter(Arrays.asList(deleteFailureActionConfig));
		deleteValidationActionConfig.addActionParameter(Arrays.asList(confirmActionConfig));

		deleteBtnConfig.addGtnUIFrameWorkActionConfig(deleteValidationActionConfig);
	}

	private void addCCCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnCCCopy01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig copyBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "copyBtn", true, namspacePrefix + "gtnCCCopy01Layout", GtnUIFrameworkComponentType.BUTTON);
		copyBtnConfig.setComponentName("COPY");
		copyBtnConfig.setAuthorizationIncluded(true);
		componentList.add(copyBtnConfig);

		GtnUIFrameWorkActionConfig copyNavigationAction = new GtnUIFrameWorkActionConfig();
		copyNavigationAction.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		copyNavigationAction.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		GtnUIFrameWorkActionConfig copyNalidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		copyNalidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE));
		copyNalidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig copyFailureActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		copyFailureActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		copyFailureActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CALENDAR_SELECT_ALERT);

		GtnUIFrameWorkActionConfig copyProcessActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyProcessActionConfig.addActionParameter(GtnFrameworkProcessAction.class.getName());
		copyProcessActionConfig
				.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		copyProcessActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		GtnUIFrameWorkActionConfig coptResetActionConfig = new GtnUIFrameWorkActionConfig();
		coptResetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		coptResetActionConfig.addActionParameter(GtnFrameworkCurdResetAction.class.getName());
		coptResetActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);
		coptResetActionConfig.setFieldValues(GtnFrameworkCalendarConfigurationContants.getFieldList());

		GtnUIFrameWorkActionConfig copyActionConfig = new GtnUIFrameWorkActionConfig();
		copyActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyActionConfig.addActionParameter(GtnFrameworkCopyAction.class.getName());
		copyActionConfig.addActionParameter(GtnFrameworkCalendarConfigurationContants.CC_CRUD_VIEW);

		copyNalidationActionConfig.addActionParameter(Arrays.asList(copyFailureActionConfig));
		copyNalidationActionConfig.addActionParameter(
				Arrays.asList(copyNavigationAction, copyProcessActionConfig, copyActionConfig, coptResetActionConfig));

		copyBtnConfig.addGtnUIFrameWorkActionConfig(copyNalidationActionConfig);
	}

	private void addCCExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnCCExcel01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "excelBtn", true, namspacePrefix + "gtnCCExcel01Layout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		componentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("CALENDAR_CONFIGURATION");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addCCButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getCssLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addSearchButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnSearch01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig ccSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, namspacePrefix + "gtnSearch01Layout", GtnUIFrameworkComponentType.BUTTON);
		ccSearchButtonConfig.setComponentName("SEARCH");
		ccSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(ccSearchButtonConfig);

		GtnUIFrameWorkActionConfig ccValidationActionConfig = new GtnUIFrameWorkActionConfig();
		ccValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		ccValidationActionConfig.setFieldValues(componentIdList);
		ccValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig ccFailureActionConfig = new GtnUIFrameWorkActionConfig();
		ccFailureActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		ccFailureActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		ccFailureActionConfig.addActionParameter("Please enter/select search criteria");

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig
				.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		tableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig
				.addActionParameter(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);

		ccValidationActionConfig.addActionParameter(Arrays.asList(ccFailureActionConfig));
		ccValidationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig, notificationActionConfig));

		ccSearchButtonConfig.addGtnUIFrameWorkActionConfig(ccValidationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
                GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnReset01Layout", true,
				parent);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, namspacePrefix + "gtnReset01Layout", GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		resetActionConfig.addActionParameter("Are you sure you want to Reset the screen?");
		List<String> newComponentIdList = new ArrayList<>(componentIdList);
		newComponentIdList.add(namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE);
		resetActionConfig.addActionParameter(newComponentIdList);
		resetActionConfig.addActionParameter(Arrays.asList("", "", null, null, null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addCCFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "searchFieldLayout", true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addCCResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		String resultTableId = namspacePrefix + GtnFrameworkCalendarConfigurationContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig resultPanelConfig = configProvider.getPanelConfig(resultTableId + "Panel", true,
				parent);
		resultPanelConfig.setComponentName("Results");
		resultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(resultPanelConfig);

		GtnUIFrameworkComponentConfig resultTableConfig = configProvider.getUIFrameworkComponentConfig(resultTableId,
				true, resultPanelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		resultTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultTableConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkPagedTableConfig pagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		pagedTableConfig.setEditable(false);
		pagedTableConfig.setFilterBar(true);
		pagedTableConfig.setSelectable(true);
		pagedTableConfig.setPageLength(10);
		pagedTableConfig.setItemPerPage(10);
		pagedTableConfig.setSinkItemPerPageWithPageLength(false);

		pagedTableConfig.setTableColumnDataType(GtnFrameworkCalendarConfigurationContants.getCalendarConfColumnType());
		pagedTableConfig.setTableVisibleHeader(GtnFrameworkCalendarConfigurationContants.getCalendarConfHeader());
		pagedTableConfig.setTableColumnMappingId(GtnFrameworkCalendarConfigurationContants.getCalendarConfColumn());
		pagedTableConfig.setCountUrl(GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE
				+ GtnWsCalendarConfigurationConstants.GET_CALENDAR_CONFIGURATION_TABLE_DATA);
		pagedTableConfig.setResultSetUrl(GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE
				+ GtnWsCalendarConfigurationConstants.GET_CALENDAR_CONFIGURATION_TABLE_DATA);
		pagedTableConfig.setIntegerFormatPropertyList(Arrays.asList("calendarYear"));
		resultTableConfig.setGtnPagedTableConfig(pagedTableConfig);
		componentList.add(resultTableConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		resultTableConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addCalendarName(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCalendarDescription(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCalendarYear(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addCountry(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);

	}

	private void addCalendarName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String calendarName = namspacePrefix + "CalendarName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(calendarName + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(calendarName, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Calendar Name");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCalendarDescription(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CalendarDescription";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Calendar Description");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCalendarYear(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CalendarYear";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Calendar Year");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setItemValues(GtnFrameworkCalendarConfigurationContants.getYearDdlbValue());
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCountry(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "Country";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Country");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsCalendarConfigurationConstants.COUNTRY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
