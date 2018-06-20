package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.V8_LABEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownGridLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	private boolean isDisableColumns;
	protected GtnUIFrameworkPagedTableConfig tableConfig;

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
			
			comparisonLookupBeanListFromDisplaySelectionTab = (List<GtnReportComparisonProjectionBean>) idComponentDataFromDisplaySelectionTab.getCustomData();
			
			List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromReportLandingScreen = new ArrayList<>();
			
			GtnUIFrameworkComponentData idComponentDataFromReportingLandingScreen = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent("reportLandingScreen_reportingDashboardComparisonConfig", componentId)
					.getComponentData();
			
			comparisonLookupBeanListFromReportLandingScreen = (List<GtnReportComparisonProjectionBean>) idComponentDataFromReportingLandingScreen.getCustomData();
			
			if((comparisonLookupBeanListFromDisplaySelectionTab!=null)&&(comparisonLookupBeanListFromReportLandingScreen!=null)) {							
			finalArrayListforGrid = new ArrayList<GtnReportComparisonProjectionBean>(comparisonLookupBeanListFromDisplaySelectionTab);			
			finalArrayListforGrid.addAll(comparisonLookupBeanListFromReportLandingScreen);
			Set<GtnReportComparisonProjectionBean> finalSet = new LinkedHashSet<GtnReportComparisonProjectionBean>(finalArrayListforGrid);
			finalArrayListforGrid = new ArrayList<GtnReportComparisonProjectionBean>(finalSet);
			
			}
			
			else if(comparisonLookupBeanListFromDisplaySelectionTab==null) {
				finalArrayListforGrid = comparisonLookupBeanListFromReportLandingScreen;
			}
			else if(comparisonLookupBeanListFromReportLandingScreen==null) {
				finalArrayListforGrid = comparisonLookupBeanListFromDisplaySelectionTab;
			}
			else {
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
				if(projectionNameListFromCustomData.get(start).equals(comparisonBasisInDisplaySelectionTab))
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

	private GtnUIFrameworkPagedTableConfig setHeaderFromWs(PagedGrid pagedGrid, String componentId,
			Grid<GtnWsRecordBean> grid) throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableConfig tableConfig = pagedGrid.getTableConfig();
		String classPath = tableConfig.getGridHeaderCustomClassLoadUrl();
		classLoader(tableConfig.getGtnUIFrameWorkActionConfig(), classPath, componentId);
		GtnUIFrameworkWebserviceRequest headerRequest = getCustomPagedTableRequest(
				tableConfig.getGtnUIFrameWorkActionConfig(), componentId);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(tableConfig.getGridColumnHeader(),
				tableConfig.getModuleName(), headerRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPagedTableResponse tableHeadersResponse = response.getGtnWsPagedTableResponse();
		List<Object> tableHeaderMappingIdList = tableHeadersResponse.getSingleColumns();
		tableHeaderMappingIdList.add(0, "projectionNames");
		List<String> tableSingleHeaders = tableHeadersResponse.getSingleHeaders();
		tableSingleHeaders.add(0, "Projections");
		tableConfig.setTableColumnMappingId(tableHeaderMappingIdList.toArray());
		tableConfig.setColumnHeaders(tableSingleHeaders);
		int j = 0;
		for (Object column : tableConfig.getTableColumnMappingId()) {
			String property = column.toString();
			grid.addColumn(row -> row.getPropertyValue(property)).setCaption(tableConfig.getColumnHeaders().get(j))
					.setId(property);

			j++;
		}
		return tableConfig;
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

	private Component getCustomFilterComponent(String property, String componentId, int i, int col,
			Grid<GtnWsRecordBean> grid, String projectionName, GtnUIFrameworkPagedTableConfig tableConfig,
			List comparisonBreakdownSaveActionList, int rowCount,
			List<GtnReportComparisonProjectionBean> comparisonLookupBeanList,
			GtnUIFrameworkComponentData gridComponent) {
		try {

			if (property.equals("projectionNames")) {
				GtnUIFrameworkComponentConfig componentConfig = new GtnUIFrameworkComponentConfig();
				componentConfig.setComponentName(projectionName);
				componentConfig.setComponentId(property + projectionName + i);

				GtnUIFrameworkComponent componentLabel = V8_LABEL.getGtnComponent();
				Component vaadinComponentLabel = null;
				vaadinComponentLabel = componentLabel.buildVaadinComponent(componentConfig);
				Label vaadinLabel = (Label) vaadinComponentLabel;
				vaadinLabel.setValue(projectionName);
				grid.getColumn(property).setWidth(400);
				return vaadinLabel;
			}
			GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_value", componentId);

			GtnUIFrameworkComponent component = COMBOBOX_VAADIN8.getGtnComponent();
			AbstractComponent vaadinComponent = null;
			vaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
			GtnUIFrameworkComboBoxComponent gtnUIFrameworkComboBoxComponent = new GtnUIFrameworkComboBoxComponent();
			gtnUIFrameworkComboBoxComponent.postCreateComponent(vaadinComponent, base.getComponentConfig());
			ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
			vaadinCombobox.setId(property + String.valueOf(i));

			vaadinCombobox.addValueChangeListener(new HasValue.ValueChangeListener() {
				@Override
				public void valueChange(HasValue.ValueChangeEvent event) {
					Object[] obj = new Object[5];
					obj[0] = event.getValue().toString();
					obj[1] = tableConfig.getColumnHeaders().get(col);
					Label projectionNameForWs = (Label) grid.getHeaderRow(rowCount).getCell("projectionNames")
							.getComponent();
					obj[2] = getMasterSid(projectionNameForWs, comparisonLookupBeanList);
					obj[3]=property;
					obj[4]=projectionNameForWs.getValue();
					comparisonBreakdownSaveActionList.add(obj);
					gridComponent.setCustomData(comparisonBreakdownSaveActionList);
				}
			});

			return vaadinCombobox;
		} catch (Exception e) {
			logger.error("Error message" + e);
		}
		return null;
	}

	private int getMasterSid(Label projectionNames, List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		int masterSid = 0;

		if (projectionNames.getValue().equalsIgnoreCase("Latest Approved")) {
			masterSid = 0;
		}
		for (int start = 0; start < comparisonLookupBeanList.size(); start++) {
			if (projectionNames.getValue().equalsIgnoreCase(comparisonLookupBeanList.get(start).getProjectionName())) {
				masterSid = comparisonLookupBeanList.get(start).getProjectionMasterSid();
			}
		}
		return masterSid;
	}

	private void classLoader(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String classPath,
			String sourceViewId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
		GtnUIFrameWorkAction loader = (GtnUIFrameWorkAction) classLoader.loadDynamicClass(classPath);
		loader.configureParams(gtnUIFrameWorkActionConfig);
		loader.doAction(sourceViewId, gtnUIFrameWorkActionConfig);
	}

	private GtnUIFrameworkWebserviceRequest getCustomPagedTableRequest(
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String sourceViewId) {
		GtnUIFrameworkBaseComponent vaadinBaseComponentFromParent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(),
						sourceViewId);
		GtnUIFrameworkComponentData resultTableComponentData = vaadinBaseComponentFromParent.getComponentData();
		return resultTableComponentData.getCustomPagedTreeTableRequest();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
