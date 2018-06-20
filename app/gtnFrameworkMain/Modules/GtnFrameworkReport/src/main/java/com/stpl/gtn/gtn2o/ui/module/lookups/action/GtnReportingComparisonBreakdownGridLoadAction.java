package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.V8_LABEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.grid.HeaderRow;

public class GtnReportingComparisonBreakdownGridLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownGridLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			logger.info("------------GtnReportingComparisionBreakdownGridLoadAction----------------");
			int i = 0;
			List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
			List cmoparisonBreakdownSaveActionList = new ArrayList<>();
			String comparisonBreakdownTableId = actionParameterList.get(1).toString();

			List<GtnReportComparisonProjectionBean> finalArrayListforGrid;

			List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromDisplaySelectionTab = new ArrayList<>();
			GtnUIFrameworkComponentData idComponentDataFromDisplaySelectionTab = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
					.getComponentData();

			if(idComponentDataFromDisplaySelectionTab
					.getCustomData()!=null)
			comparisonLookupBeanListFromDisplaySelectionTab = (List<GtnReportComparisonProjectionBean>) idComponentDataFromDisplaySelectionTab
					.getCustomData();

			List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromReportLandingScreen = new ArrayList<>() ;

			GtnUIFrameworkComponentData idComponentDataFromReportingLandingScreen = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent("reportLandingScreen_reportingDashboardComparisonConfig",
							componentId)
					.getComponentData();

			if(idComponentDataFromReportingLandingScreen
					.getCustomData()!=null)
			comparisonLookupBeanListFromReportLandingScreen = (List<GtnReportComparisonProjectionBean>) idComponentDataFromReportingLandingScreen
					.getCustomData();

			if ((comparisonLookupBeanListFromDisplaySelectionTab != null)
					&& (comparisonLookupBeanListFromReportLandingScreen != null)) {
				finalArrayListforGrid = new ArrayList<>(
						comparisonLookupBeanListFromDisplaySelectionTab);
				finalArrayListforGrid.addAll(comparisonLookupBeanListFromReportLandingScreen);
				Set<GtnReportComparisonProjectionBean> finalSet = new LinkedHashSet<>(
						finalArrayListforGrid);
				finalArrayListforGrid = new ArrayList<>(finalSet);

			}

			else if (comparisonLookupBeanListFromDisplaySelectionTab == null) {
				finalArrayListforGrid = comparisonLookupBeanListFromReportLandingScreen;
			} else if (comparisonLookupBeanListFromReportLandingScreen == null) {
				finalArrayListforGrid = comparisonLookupBeanListFromDisplaySelectionTab;
			} else {
				finalArrayListforGrid = new ArrayList<>();
			}

			List<String> projectionNameListFromCustomData = new ArrayList<>();
			projectionNameListFromCustomData.clear();
			projectionNameListFromCustomData.add("Latest Approved");
			for (int count = 0; count < finalArrayListforGrid.size(); count++) {
				projectionNameListFromCustomData.add(finalArrayListforGrid.get(count).getProjectionName());
			}

			String comparisonBasisInDisplaySelectionTab = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId)
					.getStringCaptionFromV8ComboBox();

			List projectionList = new ArrayList<>();
			for (int start = 0; start < projectionNameListFromCustomData.size(); start++) {
				if (projectionNameListFromCustomData.get(start).equals(comparisonBasisInDisplaySelectionTab))
					continue;
				projectionList.add(projectionNameListFromCustomData.get(start));
			}

			GtnUIFrameworkComboBoxConfig comparisonBasisComboBoxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_comparison", componentId)
					.getComponentConfig().getGtnComboboxConfig();
			comparisonBasisComboBoxConfig.setItemValues(projectionList);
			comparisonBasisComboBoxConfig.setItemCaptionValues(projectionList);

			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponentFromView(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportOptionsTabComparisonOptions_comparison", componentId, Arrays.asList(""));

			int comparisonLookupBeanSize = projectionNameListFromCustomData.size();

			AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView(comparisonBreakdownTableId, componentId).getComponent();
			GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
			PagedGrid pagedGrid = gridComponent.getPagedGrid();
			Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();
			clearGrid(grid);
			GtnUIFrameworkPagedTableConfig tableConfig = setHeaderFromWs(pagedGrid, componentId, grid);
			configureCheckboxHeaderComponents(tableConfig.getTableColumnMappingId(), tableConfig.getColumnHeaders(),
					grid, tableConfig);
			setStartAndEndPeriodForComparisonBreakdownLookup(tableConfig, componentId);
			Component vaadinComponent = null;

			Object[] filterColumnIdList = pagedGrid.getTableConfig().getTableColumnMappingId();

			int rowCount = 1;
			while (rowCount <= comparisonLookupBeanSize) {
				if (projectionNameListFromCustomData.get(i).equals(comparisonBasisInDisplaySelectionTab)) {
					i++;
					continue;
				}

				HeaderRow filterRow = grid.appendHeaderRow();
				for (int col = 0; col < filterColumnIdList.length; col++) {
					
					vaadinComponent = getCustomFilterComponent(String.valueOf(filterColumnIdList[col]), componentId, i,
							col, grid, projectionNameListFromCustomData.get(i), tableConfig,
							cmoparisonBreakdownSaveActionList, rowCount, finalArrayListforGrid, gridComponent);
					filterRow.getCell(String.valueOf(filterColumnIdList[col])).setComponent(vaadinComponent);
				}
				i++;
				rowCount++;
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	private void setStartAndEndPeriodForComparisonBreakdownLookup(GtnUIFrameworkPagedTableConfig tableConfig,
			String componentId) {
		List<String> startAndEndPeriodCaptionList = new ArrayList<>(tableConfig.getColumnHeaders());
		ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(
				Arrays.asList(tableConfig.getTableColumnMappingId()));

		startAndEndPeriodCaptionList.remove(0);
		startAndEndPeriodItemIdList.remove(0);

		GtnUIFrameworkComboBoxConfig startPeriodComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_startPeriod", componentId)
				.getComponentConfig().getGtnComboboxConfig();
		startPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
		startPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);

		GtnUIFrameworkComboBoxComponent startPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
		startPeriodCombobox.reloadComponentFromView(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				"reportOptionsTabComparisonOptions_startPeriod", componentId, Arrays.asList(""));

		GtnUIFrameworkComboBoxConfig endPeriodComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_endPeriod", componentId)
				.getComponentConfig().getGtnComboboxConfig();
		endPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
		endPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);

		GtnUIFrameworkComboBoxComponent endPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
		endPeriodCombobox.reloadComponentFromView(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				"reportOptionsTabComparisonOptions_endPeriod", componentId, Arrays.asList(""));

	}

	private GtnUIFrameworkPagedTableConfig setHeaderFromWs(PagedGrid comparisonBreakdownPagedGrid, String componentId,
			Grid<GtnWsRecordBean> comparisonBreakdownGrid) throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableConfig comparisonBreakdownTableConfig = comparisonBreakdownPagedGrid.getTableConfig();
		String classPath = comparisonBreakdownTableConfig.getGridHeaderCustomClassLoadUrl();
		classLoaderForComparisonBreakdown(comparisonBreakdownTableConfig.getGtnUIFrameWorkActionConfig(), classPath,
				componentId);
		GtnUIFrameworkWebserviceRequest comparisonBreakdownHeaderRequest = getCustomPagedTableRequestForComparisonBreakdown(
				comparisonBreakdownTableConfig.getGtnUIFrameWorkActionConfig(), componentId);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				comparisonBreakdownTableConfig.getGridColumnHeader(), comparisonBreakdownTableConfig.getModuleName(),
				comparisonBreakdownHeaderRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPagedTableResponse tableHeadersResponse = response.getGtnWsPagedTableResponse();
		List<Object> tableHeaderMappingIdList = tableHeadersResponse.getSingleColumns();
		tableHeaderMappingIdList.add(0, GtnFrameworkReportStringConstants.PROJECTION_NAMES);
		List<String> tableSingleHeaders = tableHeadersResponse.getSingleHeaders();
		tableSingleHeaders.add(0, "Projections");
		comparisonBreakdownTableConfig.setTableColumnMappingId(tableHeaderMappingIdList.toArray());
		comparisonBreakdownTableConfig.setColumnHeaders(tableSingleHeaders);
		int j = 0;
		for (Object column : comparisonBreakdownTableConfig.getTableColumnMappingId()) {
			String property = column.toString();
			comparisonBreakdownGrid.addColumn(row -> row.getPropertyValue(property))
					.setCaption(comparisonBreakdownTableConfig.getColumnHeaders().get(j)).setId(property);

			j++;
		}
		return comparisonBreakdownTableConfig;
	}

	private void configureCheckboxHeaderComponents(Object[] tableColumnMappingId, List<String> columnHeaders,
			Grid<GtnWsRecordBean> grid, GtnUIFrameworkPagedTableConfig tableConfig) {
		if (tableConfig.isEnableCheckBoxInGridHeader()) {
			HeaderRow mainHeader = grid.getHeaderRow(0);
			for (int i = 0; i < tableColumnMappingId.length; i++) {
				CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
				vaadinCheckBoxGroup.setItems(columnHeaders.get(i));
				mainHeader.getCell(String.valueOf(tableColumnMappingId[i])).setComponent(vaadinCheckBoxGroup);
			}
		}
	}

	private void clearGrid(Grid<GtnWsRecordBean> grid) {
		grid.removeAllColumns();
		int headerCount = grid.getHeaderRowCount();
		if (headerCount > 1) {
			for (int start = headerCount; start > 1; start--) {
				grid.removeHeaderRow(start - 1);
			}
		}
	}

	private Component getCustomFilterComponent(String comparisonBreakdownProperty, String componentId, int i, int col,
			Grid<GtnWsRecordBean> comparisonProjectiongrid, String projectionNameForComparisonBreakdown,
			GtnUIFrameworkPagedTableConfig comparisonBreakdownTableConfig, List comparisonBreakdownSaveActionList,
			int rowCount, List<GtnReportComparisonProjectionBean> comparisonLookupBeanList,
			GtnUIFrameworkComponentData comparisonBreakdownGridComponent) {
		try {

			if (comparisonBreakdownProperty.equals(GtnFrameworkReportStringConstants.PROJECTION_NAMES)) {
				GtnUIFrameworkComponentConfig comparisonBreakdownComponentConfig = new GtnUIFrameworkComponentConfig();
				comparisonBreakdownComponentConfig.setComponentName(projectionNameForComparisonBreakdown);
				comparisonBreakdownComponentConfig
						.setComponentId(comparisonBreakdownProperty + projectionNameForComparisonBreakdown + i);

				GtnUIFrameworkComponent componentLabelForGrid = V8_LABEL.getGtnComponent();
				Component vaadinComponentLabel = null;
				vaadinComponentLabel = componentLabelForGrid.buildVaadinComponent(comparisonBreakdownComponentConfig);
				Label vaadinLabel = (Label) vaadinComponentLabel;
				vaadinLabel.setValue(projectionNameForComparisonBreakdown);
				comparisonProjectiongrid.getColumn(comparisonBreakdownProperty).setWidth(400);
				return vaadinLabel;
			}
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_value", componentId);

			GtnUIFrameworkComponent vaadinGtnComboBoxComponent = COMBOBOX_VAADIN8.getGtnComponent();
			AbstractComponent abstractVaadinComponent = null;
			abstractVaadinComponent = vaadinGtnComboBoxComponent
					.buildVaadinComponent(baseComponent.getComponentConfig());
			GtnUIFrameworkComboBoxComponent gtnUIFrameworkComboBoxComponent = new GtnUIFrameworkComboBoxComponent();
			gtnUIFrameworkComboBoxComponent.postCreateComponent(abstractVaadinComponent,
					baseComponent.getComponentConfig());
			ComboBox vaadinCombobox = (ComboBox) abstractVaadinComponent;
			vaadinCombobox.setId(comparisonBreakdownProperty + String.valueOf(i));

			vaadinCombobox.addValueChangeListener(new HasValue.ValueChangeListener() {
				@Override
				public void valueChange(HasValue.ValueChangeEvent event) {
					Object[] obj = new Object[5];
					obj[0] = event.getValue().toString();
					obj[1] = comparisonBreakdownTableConfig.getColumnHeaders().get(col);
					Label projectionNameForWebService = (Label) comparisonProjectiongrid.getHeaderRow(rowCount)
							.getCell(GtnFrameworkReportStringConstants.PROJECTION_NAMES).getComponent();
					obj[2] = getMasterSidForComparisonBreakdown(projectionNameForWebService, comparisonLookupBeanList);
					obj[3] = comparisonBreakdownProperty;
					obj[4] = projectionNameForWebService.getValue();
					comparisonBreakdownSaveActionList.add(obj);
					comparisonBreakdownGridComponent.setCustomData(comparisonBreakdownSaveActionList);
				}
			});

			return vaadinCombobox;
		} catch (Exception e) {
			logger.error("Error message" + e);
		}
		return null;
	}

	private int getMasterSidForComparisonBreakdown(Label projectionNamesInComparisonBreakdown,
			List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		int masterSid = 0;

		if (projectionNamesInComparisonBreakdown.getValue().equalsIgnoreCase("Latest Approved")) {
			masterSid = 0;
		}
		for (int start = 0; start < comparisonLookupBeanList.size(); start++) {
			if (projectionNamesInComparisonBreakdown.getValue()
					.equalsIgnoreCase(comparisonLookupBeanList.get(start).getProjectionName())) {
				masterSid = comparisonLookupBeanList.get(start).getProjectionMasterSid();
			}
		}
		return masterSid;
	}

	private void classLoaderForComparisonBreakdown(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			String classPath, String sourceViewId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkClassLoader gtnClassLoader = new GtnUIFrameworkClassLoader();
		GtnUIFrameWorkAction loaderAction = (GtnUIFrameWorkAction) gtnClassLoader.loadDynamicClass(classPath);
		loaderAction.configureParams(gtnUIFrameWorkActionConfig);
		loaderAction.doAction(sourceViewId, gtnUIFrameWorkActionConfig);
	}

	private GtnUIFrameworkWebserviceRequest getCustomPagedTableRequestForComparisonBreakdown(
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String sourceViewId) {
		GtnUIFrameworkBaseComponent vaadinGtnBaseComponentFromParent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(),
						sourceViewId);
		GtnUIFrameworkComponentData resultTableComponentData = vaadinGtnBaseComponentFromParent.getComponentData();
		return resultTableComponentData.getCustomPagedTreeTableRequest();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
