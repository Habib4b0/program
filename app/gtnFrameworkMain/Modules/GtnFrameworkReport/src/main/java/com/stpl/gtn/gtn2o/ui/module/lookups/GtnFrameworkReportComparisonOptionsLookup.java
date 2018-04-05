package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkReportComparisonOptionsLookup {
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getComparisonOptionsLookUpView(String namespace) {

		GtnUIFrameworkViewConfig comparisonOptionsLookupView = new GtnUIFrameworkViewConfig();
		comparisonOptionsLookupView.setViewName("Comparison Options");
		comparisonOptionsLookupView.setViewId("comparisonOptions");
		comparisonOptionsLookupView.setDefaultView(false);
		addComponentList(comparisonOptionsLookupView, namespace);
		return comparisonOptionsLookupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> comparisonOptionsLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(comparisonOptionsLookupComponentList);
		addRootLayout(comparisonOptionsLookupComponentList, namespace);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig comparisonOptionsLookupVLayout = new GtnUIFrameworkComponentConfig();
		comparisonOptionsLookupVLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonOptionsLookupVLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "rootLayout");
		comparisonOptionsLookupVLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsLookupVLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsLookupVLayout.setAddToParent(false);
		comparisonOptionsLookupVLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig comparisonOptionsLookupConf = new GtnUIFrameworkLayoutConfig();
		comparisonOptionsLookupConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		comparisonOptionsLookupVLayout.setGtnLayoutConfig(comparisonOptionsLookupConf);
		componentList.add(comparisonOptionsLookupVLayout);
		getRootComponentForPopUp(componentList, namespace);
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		gtnLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "rootLayout");
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		componentList.add(gtnLayout);

		addComparisonOptionsPanel(componentList, namespace);
		addMassUpdateRootPanel(componentList, namespace);
		addResultsPanel(componentList, gtnLayout.getComponentId());
		addControlButtonComponent(componentList, gtnLayout.getComponentId(),namespace);
	}

	private void addComparisonOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig comparisonOptionsPanelConfig = new GtnUIFrameworkComponentConfig();
		comparisonOptionsPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		comparisonOptionsPanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsPanel");
		comparisonOptionsPanelConfig.setComponentName("Comparison Options");
		comparisonOptionsPanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		comparisonOptionsPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsPanelConfig.setAddToParent(true);
		componentList.add(comparisonOptionsPanelConfig);
		addComparisonOptionsLayout(componentList, namespace);
	}

	private void addComparisonOptionsLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonOptionsLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonOptionsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonOptionsLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsLayout");
		comparisonOptionsLayoutConfig.setComponentName("Comparison Options Layout");
		comparisonOptionsLayoutConfig.setAddToParent(true);
		comparisonOptionsLayoutConfig.setComponentWidth("102%");
		comparisonOptionsLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsPanel");
		comparisonOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		comparisonOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		comparisonOptionsLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(comparisonOptionsLayoutConfig);

		addComparisonBasisComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
		addFrequencyComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
		addPeriodRangeFromComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
		addPeriodRangeToComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
	}

	private void addComparisonBasisComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig comparisonBasisConfig = layoutsConfig
				.getHorizontalLayoutConfig("comparisonBasisConfig", parentId);
		componentList.add(comparisonBasisConfig);

		GtnUIFrameworkComponentConfig comparisonBasis = new GtnUIFrameworkComponentConfig();
		comparisonBasis.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		comparisonBasis.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"comparisonBasis");
		comparisonBasis.setComponentName("Comparison Basis: ");
		comparisonBasis.setAddToParent(true);
		comparisonBasis.setParentComponentId(comparisonBasisConfig.getComponentId());
		comparisonBasis.setEnable(false);

		componentList.add(comparisonBasis);
	}

	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig frequencyConfig = layoutsConfig.getHorizontalLayoutConfig("frequencyConfig",
				parentId);
		componentList.add(frequencyConfig);

		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		frequency.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"frequencyConfig");
		frequency.setComponentName("Frequency: ");
		frequency.setAddToParent(true);
		frequency.setParentComponentId(frequencyConfig.getComponentId());
		frequency.setEnable(false);

		componentList.add(frequency);
	}

	private void addPeriodRangeFromComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig periodRangeFromConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeFromConfig", parentId);
		componentList.add(periodRangeFromConfig);

		GtnUIFrameworkComponentConfig periodRangeFrom = new GtnUIFrameworkComponentConfig();
		periodRangeFrom.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeFrom.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"periodRangeFrom");
		periodRangeFrom.setComponentName("Period Range From: ");
		periodRangeFrom.setAddToParent(true);
		periodRangeFrom.setParentComponentId(periodRangeFromConfig.getComponentId());
		periodRangeFrom.setEnable(false);

		componentList.add(periodRangeFrom);
	}

	private void addPeriodRangeToComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig periodRangeToConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeToConfig", parentId);
		componentList.add(periodRangeToConfig);

		GtnUIFrameworkComponentConfig periodRangeTo = new GtnUIFrameworkComponentConfig();
		periodRangeTo.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeTo.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"periodRangeTo");
		periodRangeTo.setComponentName("Period Range To: ");
		periodRangeTo.setAddToParent(true);
		periodRangeTo.setParentComponentId(periodRangeToConfig.getComponentId());
		periodRangeTo.setEnable(false);

		componentList.add(periodRangeTo);
	}

	private void addMassUpdateRootPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig massUpdatePanelConfig = new GtnUIFrameworkComponentConfig();
		massUpdatePanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		massUpdatePanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdatePanel");
		massUpdatePanelConfig.setComponentName("Mass Update");
		massUpdatePanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		massUpdatePanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		massUpdatePanelConfig.setAddToParent(true);
		componentList.add(massUpdatePanelConfig);
		massUpdateLayout(componentList, namespace);
	}

	private void massUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig massUpdateLayoutConfig = new GtnUIFrameworkComponentConfig();
		massUpdateLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		massUpdateLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdateLayout");
		massUpdateLayoutConfig.setComponentName("MassUpdateLayout");
		massUpdateLayoutConfig.setAddToParent(true);
		massUpdateLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		massUpdateLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdatePanel");
		massUpdateLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		massUpdateLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(massUpdateLayoutConfig);

		addValueComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addComparisonComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addStartPeriodComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addEndPeriodComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addPopulateButtonComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
	}

	private void addValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig valueConfig = layoutsConfig.getHorizontalLayoutConfig("valueConfig", parentId);
		componentList.add(valueConfig);

		GtnUIFrameworkComponentConfig value = new GtnUIFrameworkComponentConfig();
		value.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		value.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"value");
		value.setComponentName("Value: ");
		value.setAddToParent(true);
		value.setParentComponentId(valueConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig valueLoadConfig = new GtnUIFrameworkComboBoxConfig();
		valueLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		valueLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		value.setGtnComboboxConfig(valueLoadConfig);
		componentList.add(value);
	}

	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig comparisonConfig = layoutsConfig.getHorizontalLayoutConfig("comparisonConfig",
				parentId);
		componentList.add(comparisonConfig);

		GtnUIFrameworkComponentConfig comparison = new GtnUIFrameworkComponentConfig();
		comparison.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparison.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"comparison");
		comparison.setComponentName("Comparison: ");
		comparison.setAddToParent(true);
		comparison.setParentComponentId(comparisonConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comparisonLoadConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comparisonLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		comparison.setGtnComboboxConfig(comparisonLoadConfig);
		componentList.add(comparison);
	}

	private void addStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig startPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("startPeriodConfig",
				parentId);
		componentList.add(startPeriodConfig);

		GtnUIFrameworkComponentConfig startPeriod = new GtnUIFrameworkComponentConfig();
		startPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		startPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"startPeriod");
		startPeriod.setComponentName("Start Period: ");
		startPeriod.setAddToParent(true);
		startPeriod.setParentComponentId(startPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig startPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		startPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		startPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		startPeriod.setGtnComboboxConfig(startPeriodLoadConfig);
		componentList.add(startPeriod);
	}

	private void addEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig endPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("endPeriodConfig",
				parentId);
		componentList.add(endPeriodConfig);

		GtnUIFrameworkComponentConfig endPeriod = new GtnUIFrameworkComponentConfig();
		endPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		endPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"endPeriod");
		endPeriod.setComponentName("End Period: ");
		endPeriod.setAddToParent(true);
		endPeriod.setParentComponentId(endPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig endPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		endPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		endPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		endPeriod.setGtnComboboxConfig(endPeriodLoadConfig);
		componentList.add(endPeriod);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig populateButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("populateButtonConfig", parentId);
		componentList.add(populateButtonConfig);

		GtnUIFrameworkComponentConfig populateButton = new GtnUIFrameworkComponentConfig();
		populateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		populateButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"populateButton");
		populateButton.setComponentName("POPULATE");
		populateButton.setAddToParent(true);
		populateButton.setParentComponentId(populateButtonConfig.getComponentId());

		componentList.add(populateButton);
	}

	private void addControlButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig controlButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("controlButtonConfig", parentId);
		componentList.add(controlButtonConfig);

		GtnUIFrameworkComponentConfig submitButton = new GtnUIFrameworkComponentConfig();
		submitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"submitButton");
		submitButton.setComponentName("SUBMIT");
		submitButton.setAddToParent(true);
		submitButton.setParentComponentId(controlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"resetButton");
		resetButton.setComponentName("RESET");
		resetButton.setAddToParent(true);
		resetButton.setParentComponentId(controlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"closeButton");
		closeButton.setComponentName("CLOSE");
		closeButton.setAddToParent(true);
		closeButton.setParentComponentId(controlButtonConfig.getComponentId());

		componentList.add(submitButton);
		componentList.add(resetButton);
		componentList.add(closeButton);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resultsPanel = layoutsConfig.getPanelConfig("resultsLayout", parentId);
		resultsPanel.setComponentName("Time Period");
		resultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(resultsPanel);

		addResultTable(componentList, resultsPanel.getComponentId());

	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		resultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		resultTableComponentConfig.setAddToParent(true);
		resultTableComponentConfig.setParentComponentId(parentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("resultsLayoutPanel");
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(actionConfigList);
		gtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(gtnUIFrameWorkActionConfig);

		gtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		gtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		gtnPagedTreeTableConfig.setCountUrl("");
		gtnPagedTreeTableConfig.setItemPerPage(10);

		gtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		gtnPagedTreeTableConfig.setMinSplitPosition(300);
		gtnPagedTreeTableConfig.setPageLength(15);
		gtnPagedTreeTableConfig.setResultSetUrl("");

		gtnPagedTreeTableConfig.setSplitPosition(493);

		gtnPagedTreeTableConfig.setTableHeight("650px");
		gtnPagedTreeTableConfig.setDoubleHeaderVisible(true);

		gtnPagedTreeTableConfig.setLeftTableEditable(true);
		gtnPagedTreeTableConfig.setRightTableEditable(true);

		List<String> fieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> fieldFactoryComponent = new ArrayList<>();
		gtnPagedTreeTableConfig.setLeftTableEditableColumnList(fieldFactoryColum);

		GtnUIFrameworkComponentConfig checkBox = new GtnUIFrameworkComponentConfig();
		checkBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		checkBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig checkBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		checkBoxConfig.setImmediate(true);

		checkBox.setGtnCheckBoxConfig(checkBoxConfig);
		List<GtnUIFrameWorkActionConfig> checkBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkGenerateActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_LEFT_FIELD_FACTORY_ACTION);
		checkBoxClickActionList.add(gtnUIFrameWorkGenerateActionConfig);
		gtnUIFrameWorkGenerateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		checkBox.setGtnUIFrameWorkItemClickActionConfigList(checkBoxClickActionList);

		fieldFactoryComponent.add(checkBox);
		gtnPagedTreeTableConfig.setLeftTableEditableComponentConfig(fieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> textFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig fieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		fieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		fieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fieldFactoryCustomAction
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_RIGHT_FIELD_FACTORY_ACTION);
		textFieldConfig.add(fieldFactoryCustomAction);
		gtnPagedTreeTableConfig.setComponentconfigActionlist(textFieldConfig);

		gtnPagedTreeTableConfig.setCheckBoxVisibleColoumn(Arrays.asList(GtnFrameworkCommonConstants.CHECK));
		List<GtnUIFrameWorkActionConfig> checkAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllActionConfig = new GtnUIFrameWorkActionConfig();
		checkAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllActionConfig.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		checkAllConflist.add(checkAllActionConfig);
		gtnPagedTreeTableConfig.setCheckBoxActionConfigList(checkAllConflist);
		gtnPagedTreeTableConfig
				.setCountUrl(GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		gtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		gtnPagedTreeTableConfig.setBulkDataUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		gtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		gtnPagedTreeTableConfig.setFillCountUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		gtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		gtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		gtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		gtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		resultTableComponentConfig.setGtnPagedTreeTableConfig(gtnPagedTreeTableConfig);
		componentList.add(resultTableComponentConfig);
	}
}
