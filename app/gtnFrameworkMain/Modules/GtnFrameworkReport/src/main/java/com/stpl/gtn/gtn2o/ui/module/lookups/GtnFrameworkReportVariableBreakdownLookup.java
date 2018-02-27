package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkReportVariableBreakdownLookup {
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getVariableBreakdownLookUpView(String namespace) {

		GtnUIFrameworkViewConfig variableBreakdownView = new GtnUIFrameworkViewConfig();
		variableBreakdownView.setViewName("Variable Breakdown");
		variableBreakdownView.setViewId("variableBreakdown");
		variableBreakdownView.setDefaultView(Boolean.FALSE);
		addComponentList(variableBreakdownView, namespace);
		return variableBreakdownView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addVariableBreakdownMainLayout(componentList, namespace);
	}

	private void addVariableBreakdownMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig variableBreakdownLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownLayout");
		variableBreakdownLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownLayout.setAddToParent(Boolean.FALSE);
		variableBreakdownLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig variableBreakdownConfig = new GtnUIFrameworkLayoutConfig();
		variableBreakdownConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		variableBreakdownLayout.setGtnLayoutConfig(variableBreakdownConfig);
		componentList.add(variableBreakdownLayout);
		getVariableBreakdownPopupComponents(componentList, namespace);
	}

	public void getVariableBreakdownPopupComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownPopupConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig variableBreakdownPopupLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownPopupLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		variableBreakdownPopupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownPopupConfig.setGtnLayoutConfig(variableBreakdownPopupLayout);
		variableBreakdownPopupConfig.setAddToParent(Boolean.TRUE);
		variableBreakdownPopupConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownPopupConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "variableBreakdownPopupConfig");
		variableBreakdownPopupConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownLayout");
		variableBreakdownPopupConfig.setSpacing(Boolean.TRUE);
		variableBreakdownPopupConfig.setMargin(Boolean.TRUE);
		componentList.add(variableBreakdownPopupConfig);
		
		addVariableBreakdownMassUpdateRootPanel(componentList, namespace);
		addVariableBreakdownFrequencyAndHistoryComponent(componentList, variableBreakdownPopupConfig.getComponentId(),namespace);
		addVariableBreakdownResultsPanel(componentList, variableBreakdownPopupConfig.getComponentId());
		addVariableBreakdownControlButtonComponent(componentList, variableBreakdownPopupConfig.getComponentId(),namespace);
	}

	private void addVariableBreakdownFrequencyAndHistoryComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownFrequencyAndHistoryConfig = layoutsConfig.getHorizontalLayoutConfig("resultLayoutConfig",
				parentId);
		componentList.add(variableBreakdownFrequencyAndHistoryConfig);

		GtnUIFrameworkLayoutConfig variableBreakdownFrequencyAndHistoryLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownFrequencyAndHistoryLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownFrequencyAndHistoryResultLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentId("variableBreakdownFrequencyAndHistoryResultLayout");
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentName("variableBreakdownFrequencyAndHistoryResultLayout");
		variableBreakdownFrequencyAndHistoryResultLayout.setAddToParent(Boolean.TRUE);
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownFrequencyAndHistoryResultLayout.setParentComponentId(variableBreakdownFrequencyAndHistoryConfig.getComponentId());
		variableBreakdownFrequencyAndHistoryResultLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		variableBreakdownFrequencyAndHistoryResultLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		variableBreakdownFrequencyAndHistoryResultLayout.setGtnLayoutConfig(variableBreakdownFrequencyAndHistoryLayout);
		componentList.add(variableBreakdownFrequencyAndHistoryResultLayout);

		GtnUIFrameworkComponentConfig variableBreakdownFrequencyLayoutConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownFrequencyLayoutConfig", variableBreakdownFrequencyAndHistoryResultLayout.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownFrequencyConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownFrequencyConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		variableBreakdownFrequencyConfig.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownFrequencyConfig");
		variableBreakdownFrequencyConfig.setComponentName("Frequency: ");
		variableBreakdownFrequencyConfig.setAddToParent(true);
		variableBreakdownFrequencyConfig.setParentComponentId(variableBreakdownFrequencyLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownFrequencyLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownFrequencyLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableBreakdownFrequencyLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownFrequencyConfig.setGtnComboboxConfig(variableBreakdownFrequencyLoadConfig);

		GtnUIFrameworkComponentConfig variableBreakdownHistoryLayoutConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownHistoryLayoutConfig", variableBreakdownFrequencyAndHistoryResultLayout.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownHistoryConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownHistoryConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		variableBreakdownHistoryConfig.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownHistoryConfig");
		variableBreakdownHistoryConfig.setComponentName("History: ");
		variableBreakdownHistoryConfig.setAddToParent(true);
		variableBreakdownHistoryConfig.setParentComponentId(variableBreakdownHistoryLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownHistoryLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownHistoryLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableBreakdownHistoryLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownHistoryConfig.setGtnComboboxConfig(variableBreakdownHistoryLoadConfig);

		componentList.add(variableBreakdownFrequencyLayoutConfig);
		componentList.add(variableBreakdownFrequencyConfig);
		componentList.add(variableBreakdownHistoryLayoutConfig);
		componentList.add(variableBreakdownHistoryConfig);

	}

	private void addVariableBreakdownMassUpdateRootPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownMassUpdateRootPanelConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownMassUpdateRootPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		variableBreakdownMassUpdateRootPanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateRootPanelConfig");
		variableBreakdownMassUpdateRootPanelConfig.setComponentName("Mass Update");
		variableBreakdownMassUpdateRootPanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "variableBreakdownPopupConfig");
		variableBreakdownMassUpdateRootPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownMassUpdateRootPanelConfig.setAddToParent(Boolean.TRUE);
		componentList.add(variableBreakdownMassUpdateRootPanelConfig);
		addVariableBreakdownMassUpdateLayout(componentList, namespace);
	}

	private void addVariableBreakdownMassUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig variableBreakdownMassUpdateLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownMassUpdateLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownMassUpdateConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownMassUpdateConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownMassUpdateConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateConfig");
		variableBreakdownMassUpdateConfig.setComponentName("MassUpdateLayout");
		variableBreakdownMassUpdateConfig.setAddToParent(Boolean.TRUE);
		variableBreakdownMassUpdateConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownMassUpdateConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateRootPanelConfig");
		variableBreakdownMassUpdateConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		variableBreakdownMassUpdateConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		variableBreakdownMassUpdateConfig.setGtnLayoutConfig(variableBreakdownMassUpdateLayout);
		componentList.add(variableBreakdownMassUpdateConfig);

		addVariableBreakdownValueComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownFileorProjectionComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownStartPeriodComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownEndPeriodComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownPopulateButtonComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
	}

	private void addVariableBreakdownValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownValueConfig = layoutsConfig.getHorizontalLayoutConfig("variableBreakdownValueConfig", parentId);
		componentList.add(variableBreakdownValueConfig);

		GtnUIFrameworkComponentConfig variableBreakdownValue = new GtnUIFrameworkComponentConfig();
		variableBreakdownValue.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		variableBreakdownValue.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownValue");
		variableBreakdownValue.setComponentName("Value: ");
		variableBreakdownValue.setAddToParent(Boolean.TRUE);
		variableBreakdownValue.setParentComponentId(variableBreakdownValueConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownValueLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownValueLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownValueLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownValue.setGtnComboboxConfig(variableBreakdownValueLoadConfig);
		componentList.add(variableBreakdownValue);
	}

	private void addVariableBreakdownFileorProjectionComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownValueFileorProjectionConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownValueFileorProjectionConfig", parentId);
		componentList.add(variableBreakdownValueFileorProjectionConfig);

		GtnUIFrameworkComponentConfig variableBreakdownValueFileorProjection = new GtnUIFrameworkComponentConfig();
		variableBreakdownValueFileorProjection.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		variableBreakdownValueFileorProjection.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownValueFileorProjection");
		variableBreakdownValueFileorProjection.setComponentName("File/Projection: ");
		variableBreakdownValueFileorProjection.setAddToParent(Boolean.TRUE);
		variableBreakdownValueFileorProjection.setParentComponentId(variableBreakdownValueFileorProjectionConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownValueFileorProjectionLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownValueFileorProjectionLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownValueFileorProjectionLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownValueFileorProjection.setGtnComboboxConfig(variableBreakdownValueFileorProjectionLoadConfig);
		componentList.add(variableBreakdownValueFileorProjection);
	}

	private void addVariableBreakdownStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownStartPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("variableBreakdownStartPeriodConfig",
				parentId);
		componentList.add(variableBreakdownStartPeriodConfig);

		GtnUIFrameworkComponentConfig variableBreakdownStartPeriod = new GtnUIFrameworkComponentConfig();
		variableBreakdownStartPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		variableBreakdownStartPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownStartPeriod");
		variableBreakdownStartPeriod.setComponentName("Start Period: ");
		variableBreakdownStartPeriod.setAddToParent(Boolean.TRUE);
		variableBreakdownStartPeriod.setParentComponentId(variableBreakdownStartPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownStartPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownStartPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownStartPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownStartPeriod.setGtnComboboxConfig(variableBreakdownStartPeriodLoadConfig);
		componentList.add(variableBreakdownStartPeriod);
	}

	private void addVariableBreakdownEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownEndPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("variableBreakdownEndPeriodConfig",
				parentId);
		componentList.add(variableBreakdownEndPeriodConfig);

		GtnUIFrameworkComponentConfig variableBreakdownEndPeriod = new GtnUIFrameworkComponentConfig();
		variableBreakdownEndPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		variableBreakdownEndPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownEndPeriod");
		variableBreakdownEndPeriod.setComponentName("End Period: ");
		variableBreakdownEndPeriod.setAddToParent(Boolean.TRUE);
		variableBreakdownEndPeriod.setParentComponentId(variableBreakdownEndPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownEndPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownEndPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownEndPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownEndPeriod.setGtnComboboxConfig(variableBreakdownEndPeriodLoadConfig);
		componentList.add(variableBreakdownEndPeriod);
	}

	private void addVariableBreakdownPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownPopulateButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownPopulateButtonConfig", parentId);
		componentList.add(variableBreakdownPopulateButtonConfig);

		GtnUIFrameworkComponentConfig variableBreakdownPopulateButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownPopulateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownPopulateButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownPopulateButton");
		variableBreakdownPopulateButton.setComponentName("POPULATE");
		variableBreakdownPopulateButton.setAddToParent(Boolean.TRUE);
		variableBreakdownPopulateButton.setParentComponentId(variableBreakdownPopulateButtonConfig.getComponentId());

		componentList.add(variableBreakdownPopulateButton);
	}

	private void addVariableBreakdownControlButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownControlButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownControlButtonConfig", parentId);
		componentList.add(variableBreakdownControlButtonConfig);

		GtnUIFrameworkComponentConfig variableBreakdownSubmitButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownSubmitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownSubmitButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownSubmitButton");
		variableBreakdownSubmitButton.setComponentName("SUBMIT");
		variableBreakdownSubmitButton.setAddToParent(Boolean.TRUE);
		variableBreakdownSubmitButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownResetButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownResetButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownResetButton");
		variableBreakdownResetButton.setComponentName("RESET");
		variableBreakdownResetButton.setAddToParent(Boolean.TRUE);
		variableBreakdownResetButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownCloseButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownCloseButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownCloseButton");
		variableBreakdownCloseButton.setComponentName("CLOSE");
		variableBreakdownCloseButton.setAddToParent(Boolean.TRUE);
		variableBreakdownCloseButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		componentList.add(variableBreakdownSubmitButton);
		componentList.add(variableBreakdownResetButton);
		componentList.add(variableBreakdownCloseButton);
	}

	private void addVariableBreakdownResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig variableBreakdownResultsPanel = layoutsConfig.getPanelConfig("variableBreakdownResultsPanel", parentId);
		variableBreakdownResultsPanel.setComponentName("Results");
		variableBreakdownResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(variableBreakdownResultsPanel);

		addResultTable(componentList, variableBreakdownResultsPanel.getComponentId());

	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		/*GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		resultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

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
		gtnPagedTreeTableConfig.setDoubleHeaderVisible(Boolean.TRUE);

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
		componentList.add(resultTableComponentConfig);*/
	}
}
