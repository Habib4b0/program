package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.V8_LABEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
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
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
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

	private List<Object[]> comparisonBreakdownSaveActionList = new ArrayList<>();

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

			GtnUIFrameworkComponentData idComponentDataFromDisplaySelectionTab = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
					.getComponentData();

			finalArrayListforGrid = getFinalArrayListForGrid(componentId, idComponentDataFromDisplaySelectionTab);

			List<String> projectionNameListFromCustomData = new ArrayList<>(finalArrayListforGrid.size() + 1);
			projectionNameListFromCustomData.clear();
			String reportDataSourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId)
					.getViewId();
			if (!GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", reportDataSourceComponentId)
					.getCaptionFromV8ComboBox().equals("3")) {
				projectionNameListFromCustomData.add("Latest Approved");
			}
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
			combobox.reloadComponentFromView("reportOptionsTabComparisonOptions_comparison", componentId,
					Arrays.asList(""));

			int comparisonLookupBeanSize = projectionNameListFromCustomData.size();

			AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView(comparisonBreakdownTableId, componentId).getComponent();
			GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
			PagedGrid pagedGrid = gridComponent.getPagedGrid();
			Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
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
					GtnReportComparisonBreakdownLookupBean comparisonLookUpBean = new GtnReportComparisonBreakdownLookupBean();
					comparisonLookUpBean.setProperty(String.valueOf(filterColumnIdList[col]));
					comparisonLookUpBean.setComponentId(componentId);
					comparisonLookUpBean.setRowId(i);
					comparisonLookUpBean.setCol(col);
					comparisonLookUpBean.setProjectionName(projectionNameListFromCustomData.get(i));
					comparisonLookUpBean.setComparisonBreakdownSaveActionList(cmoparisonBreakdownSaveActionList);
					comparisonLookUpBean.setRowCount(rowCount);
					comparisonLookUpBean.setComparisonLookupBeanList(finalArrayListforGrid);
					vaadinComponent = getCustomFilterComponent(comparisonLookUpBean, grid, tableConfig, gridComponent);
					filterRow.getCell(String.valueOf(filterColumnIdList[col])).setComponent(vaadinComponent);
				}
				i++;
				rowCount++;
			}
			setReportProfileComparisonBreakdown(gridComponent, grid, componentId);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	private List<GtnReportComparisonProjectionBean> getFinalArrayListForGrid(String componentId,
			GtnUIFrameworkComponentData idComponentDataFromDisplaySelectionTab) {

		List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromDisplaySelectionTab = new ArrayList<>();
		List<GtnReportComparisonProjectionBean> finalArrayListforGrid;
		if (idComponentDataFromDisplaySelectionTab.getCustomData() != null) {
			comparisonLookupBeanListFromDisplaySelectionTab = (List<GtnReportComparisonProjectionBean>) idComponentDataFromDisplaySelectionTab
					.getCustomData();
		}

		List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromReportLandingScreen = new ArrayList<>();

		GtnUIFrameworkComponentData idComponentDataFromReportingLandingScreen = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent("reportLandingScreen_reportingDashboardComparisonConfig", componentId)
				.getComponentData();

		if (idComponentDataFromReportingLandingScreen.getCustomData() != null) {
			comparisonLookupBeanListFromReportLandingScreen = (List<GtnReportComparisonProjectionBean>) idComponentDataFromReportingLandingScreen
					.getCustomData();
		}

		if ((!comparisonLookupBeanListFromDisplaySelectionTab.isEmpty())
				&& (!comparisonLookupBeanListFromReportLandingScreen.isEmpty())) {
			finalArrayListforGrid = new ArrayList<>(comparisonLookupBeanListFromDisplaySelectionTab);
			finalArrayListforGrid.addAll(comparisonLookupBeanListFromReportLandingScreen);
			Set<GtnReportComparisonProjectionBean> finalSet = new LinkedHashSet<>(finalArrayListforGrid);
			finalArrayListforGrid = new ArrayList<>(finalSet);

		}

		else if (comparisonLookupBeanListFromDisplaySelectionTab.isEmpty()
				&& comparisonLookupBeanListFromReportLandingScreen.isEmpty()) {
			finalArrayListforGrid = new ArrayList<>();
		} else if (comparisonLookupBeanListFromDisplaySelectionTab.isEmpty()) {
			finalArrayListforGrid = comparisonLookupBeanListFromReportLandingScreen;
		} else {
			finalArrayListforGrid = comparisonLookupBeanListFromDisplaySelectionTab;
		}
		return finalArrayListforGrid;
	}

	private void setReportProfileComparisonBreakdown(GtnUIFrameworkComponentData gridComponent,
			Grid<GtnWsRecordBean> grid, String componentId) {
		List<Object[]> reportProfileSubmitBeanList = new ArrayList<>();
		GtnUIFrameworkBaseComponent comparisonBreakdownLookupCustomData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_reportOptionsTabComparisonOptions", componentId);
		if (Optional.ofNullable(comparisonBreakdownLookupCustomData.getComponentData().getCustomData()).isPresent()) {
			List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownReportProfileBean = (List<GtnReportComparisonBreakdownLookupBean>) comparisonBreakdownLookupCustomData
					.getComponentData().getCustomData();
			for (int start = 0; start < comparisonBreakdownReportProfileBean.size(); start++) {
				ComboBox comparisonBreakdownGridCombo = (ComboBox) grid
						.getHeaderRow(comparisonBreakdownReportProfileBean.get(start).getRowCount())
						.getCell(comparisonBreakdownReportProfileBean.get(start).getProperty()).getComponent();
				comparisonBreakdownGridCombo.setSelectedItem(
						String.valueOf(comparisonBreakdownReportProfileBean.get(start).getSelectedVariable()));
				Object[] obj = new Object[7];
				obj[0] = comparisonBreakdownReportProfileBean.get(start).getSelectedVariable();
				obj[1] = comparisonBreakdownReportProfileBean.get(start).getColumnId();
				obj[2] = comparisonBreakdownReportProfileBean.get(start).getMasterSid();
				obj[3] = comparisonBreakdownReportProfileBean.get(start).getProperty();
				obj[4] = comparisonBreakdownReportProfileBean.get(start).getProperty();
				obj[5] = comparisonBreakdownReportProfileBean.get(start).getRowCount();
				obj[6] = comparisonBreakdownReportProfileBean.get(start).getComponentId();
				reportProfileSubmitBeanList.add(obj);
			}

			gridComponent.setCustomData(reportProfileSubmitBeanList);
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
		startPeriodCombobox.reloadComponentFromView("reportOptionsTabComparisonOptions_startPeriod", componentId,
				Arrays.asList(""));

		GtnUIFrameworkComboBoxConfig endPeriodComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_endPeriod", componentId)
				.getComponentConfig().getGtnComboboxConfig();
		endPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
		endPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);

		GtnUIFrameworkComboBoxComponent endPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
		endPeriodCombobox.reloadComponentFromView("reportOptionsTabComparisonOptions_endPeriod", componentId,
				Arrays.asList(""));

	}

	private GtnUIFrameworkPagedTableConfig setHeaderFromWs(PagedGrid comparisonBreakdownPagedGrid, String componentId,
			Grid<GtnWsRecordBean> comparisonBreakdownGrid) throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableConfig comparisonBreakdownTableConfig = comparisonBreakdownPagedGrid.getTableConfig();
		String classPath = comparisonBreakdownTableConfig.getGridHeaderCustomClassLoadURL();
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
		tableSingleHeaders.add(0, "");
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
		grid.setFrozenColumnCount(1);
		if (tableConfig.isEnableCheckBoxInGridHeader()) {
			HeaderRow mainHeader = grid.getHeaderRow(0);
			for (int i = 0; i < tableColumnMappingId.length; i++) {
				CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
				vaadinCheckBoxGroup.setItems(columnHeaders.get(i));
				if (!tableColumnMappingId[i].equals(GtnFrameworkReportStringConstants.PROJECTION_NAMES)) {
					mainHeader.getCell(String.valueOf(tableColumnMappingId[i])).setComponent(vaadinCheckBoxGroup);
				}
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

	private Component getCustomFilterComponent(GtnReportComparisonBreakdownLookupBean comparisonLookUpBean,
			Grid<GtnWsRecordBean> comparisonProjectiongrid,
			GtnUIFrameworkPagedTableConfig comparisonBreakdownTableConfig,
			GtnUIFrameworkComponentData comparisonBreakdownGridComponent) {
		try {

			if (comparisonLookUpBean.getProperty().equals(GtnFrameworkReportStringConstants.PROJECTION_NAMES)) {
				GtnUIFrameworkComponentConfig comparisonBreakdownComponentConfig = new GtnUIFrameworkComponentConfig();
				comparisonBreakdownComponentConfig.setComponentName(comparisonLookUpBean.getProjectionName());
				comparisonBreakdownComponentConfig.setComponentId(comparisonLookUpBean.getProperty()
						+ comparisonLookUpBean.getProjectionName() + comparisonLookUpBean.getRowId());
				GtnUIFrameworkComponent componentLabelForGrid = V8_LABEL.getGtnComponent();
				Component vaadinComponentLabel = null;
				vaadinComponentLabel = componentLabelForGrid.buildVaadinComponent(comparisonBreakdownComponentConfig);
				Label vaadinLabel = (Label) vaadinComponentLabel;
				vaadinLabel.setValue(comparisonLookUpBean.getProjectionName());
				comparisonProjectiongrid.getColumn(comparisonLookUpBean.getProperty()).setMinimumWidthFromContent(true);
				comparisonProjectiongrid.getColumn(comparisonLookUpBean.getProperty()).setResizable(false);
				return vaadinLabel;
			}
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(
					"reportOptionsTabComparisonOptions_value", comparisonLookUpBean.getComponentId());

			GtnUIFrameworkComponent vaadinGtnComboBoxComponent = COMBOBOX_VAADIN8.getGtnComponent();
			AbstractComponent abstractVaadinComponent = null;
			abstractVaadinComponent = vaadinGtnComboBoxComponent
					.buildVaadinComponent(baseComponent.getComponentConfig());
			GtnUIFrameworkComboBoxComponent gtnUIFrameworkComboBoxComponent = new GtnUIFrameworkComboBoxComponent();
			gtnUIFrameworkComboBoxComponent.postCreateComponent(abstractVaadinComponent,
					baseComponent.getComponentConfig());
			ComboBox vaadinCombobox = (ComboBox) abstractVaadinComponent;
			vaadinCombobox.addStyleName("stpl-comboBox-Inside-Grid-CustomStyle");
			vaadinCombobox.setId(comparisonLookUpBean.getProperty() + String.valueOf(comparisonLookUpBean.getRowId()));
			vaadinCombobox.setSelectedItem("0");

			vaadinCombobox.addValueChangeListener(new HasValue.ValueChangeListener() {
				@Override
				public void valueChange(HasValue.ValueChangeEvent event) {
					Object[] obj = new Object[7];
					obj[0] = event.getValue().toString();
					obj[1] = comparisonBreakdownTableConfig.getColumnHeaders().get(comparisonLookUpBean.getCol());
					Label projectionNameForWebService = (Label) comparisonProjectiongrid
							.getHeaderRow(comparisonLookUpBean.getRowCount())
							.getCell(GtnFrameworkReportStringConstants.PROJECTION_NAMES).getComponent();
					obj[2] = getMasterSidForComparisonBreakdown(projectionNameForWebService,
							comparisonLookUpBean.getComparisonLookupBeanList());
					obj[3] = comparisonLookUpBean.getProperty();
					obj[4] = projectionNameForWebService.getValue();
					obj[5] = comparisonLookUpBean.getRowCount();
					obj[6] = vaadinCombobox.getId();
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
