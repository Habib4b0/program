package com.stpl.gtn.gtn2o.registry.config.projectionvariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.common.UpdatePreviousNextCloseSubmitButton;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class CommercialForecastingProjectionVarianceMain {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addProjectionVarianceTabComponents(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		addProjectionVariancePanel(componentList, nameSpace);
	}

	private void addProjectionVariancePanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig projectionVarianceMainPanel = new GtnUIFrameworkComponentConfig();
		projectionVarianceMainPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionVarianceMainPanel.setComponentId(nameSpace + "_" + "projectionVarianceMainPanel");
		projectionVarianceMainPanel.setAddToParent(false);
		projectionVarianceMainPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(projectionVarianceMainPanel);
		addprojectionVarianceMainLayout(componentList, nameSpace);

	}

	private void addprojectionVarianceMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig projectionVarianceMainLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "projectionVarianceMainLayout", true,
				nameSpace + "_" + "projectionVarianceMainPanel");
		projectionVarianceMainLayout.setComponentWidth("100%");
		projectionVarianceMainLayout.setMargin(true);
		componentList.add(projectionVarianceMainLayout);
		new CommercialForecastingProjectionVarianceDisplaySelectionFilterOptionTab().addTabSheet(componentList,
				projectionVarianceMainLayout.getComponentId(), nameSpace);
		new CommercialForecastingProjectionVarianceGenerateResetButton().addGenerateResetButtonComponetsLayout(
				componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);

		addProjectionPivoteViewTablePanel(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);

		addUpdatePreviousNextCloseSubmitButtonLayout(componentList, projectionVarianceMainLayout.getComponentId(),
				nameSpace);

	}

	private void addProjectionPivoteViewTablePanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig projectionpivoteViewPanel = new GtnUIFrameworkComponentConfig();
		projectionpivoteViewPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionpivoteViewPanel.setComponentId(nameSpace + "_" + "projectionpivoteViewPanel");
		projectionpivoteViewPanel.setComponentName("Projecion Pivot View");
		projectionpivoteViewPanel.setAddToParent(true);
		projectionpivoteViewPanel.setParentComponentId(parentComponentId);
		projectionpivoteViewPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(projectionpivoteViewPanel);

		addProjectionPivoteViewTablePanelComponents(componentList, nameSpace + "_" + "projectionpivoteViewPanel",
				nameSpace);

	}

	private void addProjectionPivoteViewTablePanelComponents(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig ppvComponetsLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_PPV_COMPONETS_LAYOUT, true,
				parentComponentId);
		ppvComponetsLayout.setComponentWidth("100%");
		componentList.add(ppvComponetsLayout);

		addProjectionPivoteViewTable(componentList,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_PPV_COMPONETS_LAYOUT, nameSpace);
		addProjectionVarianceExcelGraphButton(componentList,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_PPV_COMPONETS_LAYOUT, nameSpace);

	}

	private void addProjectionPivoteViewTable(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig ppvComponetsLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "ppvTableLayout", true, parentComponentId);
		ppvComponetsLayout.setComponentWidth("100%");
		ppvComponetsLayout.setSpacing(false);
		componentList.add(ppvComponetsLayout);

		GtnUIFrameworkComponentConfig projectionpivoteViewResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		projectionpivoteViewResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		projectionpivoteViewResultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		projectionpivoteViewResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		projectionpivoteViewResultTableComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionpivoteViewResultTableComponentConfig.setAddToParent(true);
		projectionpivoteViewResultTableComponentConfig.setParentComponentId(nameSpace + "_" + "ppvTableLayout");

		GtnUIFrameworkPagedTreeTableConfig projectionVarianceProjectPivotViewGtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> projectionVarianceProjectPivotViewActionConfigList = new ArrayList<>();
		projectionVarianceProjectPivotViewActionConfigList.add(parentComponentId);

		GtnUIFrameWorkActionConfig projectionVarianceProjectPivotViewGtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		projectionVarianceProjectPivotViewGtnUIFrameWorkActionConfig
				.setActionParameterList(projectionVarianceProjectPivotViewActionConfigList);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setGtnUIFrameWorkActionConfig(projectionVarianceProjectPivotViewGtnUIFrameWorkActionConfig);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_COMMERCIAL_FORECAST_PROJECTION_VARIANCE_TAB_LEFT_HEADERS_SERVICE);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_COMMERCIAL_FORECAST_PROJECTION_VARIANCE_TAB_RIGHT_HEADERS_SERVICE);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setCountUrl("");
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setItemPerPage(10);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setMinSplitPosition(300);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setPageLength(15);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setResultSetUrl("");

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setSplitPosition(493);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setTableHeight("650px");
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setDoubleHeaderVisible(true);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setLeftTableEditable(true);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setRightTableEditable(true);

		List<String> projectionVarianceProjectPivotViewFieldFactoryColumn = Arrays
				.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> projectionVarianceProjectPivotViewFieldFactoryComponent = new ArrayList<>();
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setLeftTableEditableColumnList(projectionVarianceProjectPivotViewFieldFactoryColumn);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setLeftTableEditableComponentConfig(projectionVarianceProjectPivotViewFieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> projectionVarianceProjectPivotViewTextFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig projectionVarianceProjectPivotViewFieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		projectionVarianceProjectPivotViewFieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		projectionVarianceProjectPivotViewFieldFactoryCustomAction
				.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		projectionVarianceProjectPivotViewTextFieldConfig
				.add(projectionVarianceProjectPivotViewFieldFactoryCustomAction);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setComponentconfigActionlist(projectionVarianceProjectPivotViewTextFieldConfig);
		List<GtnUIFrameWorkActionConfig> projectionVarianceProjectPivotViewCheckAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig projectionVarianceProjectPivotViewCheckAllActionConfig = new GtnUIFrameWorkActionConfig();
		projectionVarianceProjectPivotViewCheckAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		projectionVarianceProjectPivotViewCheckAllActionConfig.addActionParameter(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		projectionVarianceProjectPivotViewCheckAllConflist.add(projectionVarianceProjectPivotViewCheckAllActionConfig);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setCheckBoxActionConfigList(projectionVarianceProjectPivotViewCheckAllConflist);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setBulkDataUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setFillCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig
				.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		projectionVarianceProjectPivotViewGtnPagedTreeTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		projectionpivoteViewResultTableComponentConfig
				.setGtnPagedTreeTableConfig(projectionVarianceProjectPivotViewGtnPagedTreeTableConfig);


	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		String[] propertyIds = { "leftSingleHeader" };
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>(
				propertyIds.length);
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfig.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfig.setGtnComponentType(componentType[i]);
			customFilterConfigMap.put(pagedTableCustomFilterConfig.getPropertId(), pagedTableCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

	private void addProjectionVarianceExcelGraphButton(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig pvExcelBtnLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "pvExcelButtonlayout", true, parentComponentId);
		pvExcelBtnLayout.setSpacing(false);
		componentList.add(pvExcelBtnLayout);

		GtnUIFrameworkComponentConfig pvExcelButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "excelButton", true, pvExcelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		pvExcelButton.setAuthorizationIncluded(true);
		componentList.add(pvExcelButton);

	}

	private void addUpdatePreviousNextCloseSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		UpdatePreviousNextCloseSubmitButton discountProjectionButtonLayout = new UpdatePreviousNextCloseSubmitButton();
		discountProjectionButtonLayout.addCommonButtonLayout(componentList, parentComponentId, nameSpace);
		discountProjectionButtonLayout.addUpdateButton(componentList, nameSpace);
		discountProjectionButtonLayout.addPreviousButton(componentList, nameSpace);
		discountProjectionButtonLayout.addNextButton(componentList, nameSpace);
		discountProjectionButtonLayout.addCloseButton(componentList, nameSpace);
		discountProjectionButtonLayout.addSubmitButton(componentList, nameSpace);

	}
}