package com.stpl.gtn.gtn2o.registry.config.projectionvariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.common.UpdatePreviousNextCloseSubmitButton;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;


public class CommercialForecastingProjectionVarianceMain {


	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addProjectionVarianceTabComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
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
			nameSpace + "_" + "projectionVarianceMainLayout", true, nameSpace + "_" + "projectionVarianceMainPanel");
		projectionVarianceMainLayout.setComponentWidth("100%");
		projectionVarianceMainLayout.setMargin(true);
		componentList.add(projectionVarianceMainLayout);
		new CommercialForecastingProjectionVarianceDisplaySelectionFilterOptionTab().addTabSheet(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
		new CommercialForecastingProjectionVarianceGenerateResetButton().addGenerateResetButtonComponetsLayout(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
		
		addProjectionPivoteViewTable(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
		
		
		addUpdatePreviousNextCloseSubmitButtonLayout(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
		
	}

	private void addProjectionPivoteViewTable(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace)
	{
		
		GtnUIFrameworkComponentConfig projectionpivoteViewPanel = new GtnUIFrameworkComponentConfig();
		projectionpivoteViewPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionpivoteViewPanel.setComponentId(nameSpace + "_" + "projectionpivoteViewPanel");
		projectionpivoteViewPanel.setComponentName("Projecion Pivot View");
		projectionpivoteViewPanel.setAddToParent(true);
		projectionpivoteViewPanel.setParentComponentId(parentComponentId);
		projectionpivoteViewPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(projectionpivoteViewPanel);
		
		
		GtnUIFrameworkComponentConfig projectionpivoteViewResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		projectionpivoteViewResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		projectionpivoteViewResultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		projectionpivoteViewResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		projectionpivoteViewResultTableComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionpivoteViewResultTableComponentConfig.setAddToParent(true);
		projectionpivoteViewResultTableComponentConfig.setParentComponentId(nameSpace + "_" + "projectionpivoteViewPanel");

		GtnUIFrameworkPagedTreeTableConfig reportingDashboardGtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> reportingDashboardActionConfigList = new ArrayList<>();
		reportingDashboardActionConfigList.add(parentComponentId);
		
		
		
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkActionConfig.setActionParameterList(reportingDashboardActionConfigList);
		reportingDashboardGtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(reportingDashboardGtnUIFrameWorkActionConfig);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);
//            reportingDashboardGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
//                    GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
//             reportingDashboardGtnPagedTreeTableConfig.setRightWsHeaderUrl(
//                    GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig.setCountUrl("");
		reportingDashboardGtnPagedTreeTableConfig.setItemPerPage(10);

		reportingDashboardGtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		reportingDashboardGtnPagedTreeTableConfig.setMinSplitPosition(300);
		reportingDashboardGtnPagedTreeTableConfig.setPageLength(15);
		reportingDashboardGtnPagedTreeTableConfig.setResultSetUrl("");

		reportingDashboardGtnPagedTreeTableConfig.setSplitPosition(493);
		reportingDashboardGtnPagedTreeTableConfig.setTableHeight("650px");
		reportingDashboardGtnPagedTreeTableConfig.setDoubleHeaderVisible(true);

		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditable(true);
		reportingDashboardGtnPagedTreeTableConfig.setRightTableEditable(true);

		List<String> reportingDashboardFieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> reportingDashboardFieldFactoryComponent = new ArrayList<>();
		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditableColumnList(reportingDashboardFieldFactoryColum);

		GtnUIFrameworkComponentConfig reportingDashboardCheckBox = new GtnUIFrameworkComponentConfig();
		reportingDashboardCheckBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		reportingDashboardCheckBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig reportingDashboardCheckBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		reportingDashboardCheckBoxConfig.setImmediate(true);

		reportingDashboardCheckBox.setGtnCheckBoxConfig(reportingDashboardCheckBoxConfig);
		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardGtnUIFrameWorkGenerateActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_LEFT_FIELD_FACTORY_ACTION);
		reportingDashboardCheckBoxClickActionList.add(reportingDashboardGtnUIFrameWorkGenerateActionConfig);
		reportingDashboardGtnUIFrameWorkGenerateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		reportingDashboardCheckBox.setGtnUIFrameWorkItemClickActionConfigList(reportingDashboardCheckBoxClickActionList);

		reportingDashboardFieldFactoryComponent.add(reportingDashboardCheckBox);
		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditableComponentConfig(reportingDashboardFieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> reportingDashboardTextFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardFieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		reportingDashboardFieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		reportingDashboardFieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardFieldFactoryCustomAction
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_RIGHT_FIELD_FACTORY_ACTION);
		reportingDashboardTextFieldConfig.add(reportingDashboardFieldFactoryCustomAction);
		reportingDashboardGtnPagedTreeTableConfig.setComponentconfigActionlist(reportingDashboardTextFieldConfig);

		reportingDashboardGtnPagedTreeTableConfig.setCheckBoxVisibleColoumn(Arrays.asList(GtnFrameworkCommonConstants.CHECK));
		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardCheckAllActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardCheckAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardCheckAllActionConfig.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		reportingDashboardCheckAllConflist.add(reportingDashboardCheckAllActionConfig);
		reportingDashboardGtnPagedTreeTableConfig.setCheckBoxActionConfigList(reportingDashboardCheckAllConflist);
		reportingDashboardGtnPagedTreeTableConfig
				.setCountUrl(GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		reportingDashboardGtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setBulkDataUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		reportingDashboardGtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setFillCountUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		reportingDashboardGtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		projectionpivoteViewResultTableComponentConfig.setGtnPagedTreeTableConfig(reportingDashboardGtnPagedTreeTableConfig);
		componentList.add(projectionpivoteViewResultTableComponentConfig);
		
		//addProjectionVarianceExcelGraphButton(componentList, nameSpace + "_" + "projectionpivoteViewPanel", nameSpace);

	}
	
	private void addProjectionVarianceExcelGraphButton(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig pvExcelBtnLayout = configProvider.getHorizontalLayoutConfig(nameSpace + "_" + "pvExcelButtonlayout", true, parentComponentId);
		componentList.add(pvExcelBtnLayout);

		GtnUIFrameworkComponentConfig pvExcelButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "excelButton", true, pvExcelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		pvExcelButton.setAuthorizationIncluded(true);
		componentList.add(pvExcelButton);
		
//		GtnUIFrameworkComponentConfig pvGraphButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "pvGraphButton", true, pvExcelBtnLayout.getComponentId(),
//				GtnUIFrameworkComponentType.);
//		pvGraphButton.setComponentName("RESET");
//		componentList.add(pvGraphButton);
//		
//		GtnUIFrameworkComponentConfig refreshButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "refreshButton", true, pvExcelBtnLayout.getComponentId(),
//				GtnUIFrameworkComponentType.BUTTON);
//		refreshButton.setComponentName("REFRESH");
//		componentList.add(refreshButton);

	}
	
	private void addUpdatePreviousNextCloseSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		UpdatePreviousNextCloseSubmitButton discountProjectionButtonLayout=new UpdatePreviousNextCloseSubmitButton();
		discountProjectionButtonLayout.addCommonButtonLayout(componentList,  parentComponentId, nameSpace);
		discountProjectionButtonLayout.addUpdateButton(componentList, nameSpace);
		discountProjectionButtonLayout.addPreviousButton(componentList, nameSpace);
		discountProjectionButtonLayout.addNextButton(componentList, nameSpace);
		discountProjectionButtonLayout.addCloseButton(componentList, nameSpace);
		discountProjectionButtonLayout.addSubmitButton(componentList, nameSpace);
		
	}
}