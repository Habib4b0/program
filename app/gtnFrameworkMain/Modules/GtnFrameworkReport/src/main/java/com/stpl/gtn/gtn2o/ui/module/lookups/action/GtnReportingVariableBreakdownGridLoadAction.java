package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.V8_LABEL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.grid.HeaderRow;

public class GtnReportingVariableBreakdownGridLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final String EX_FACTORY_SALES = "Ex-Factory Sales";
	private static final String REPORTING_DASHBOARD_SCREEN = "reportingDashboardScreen";
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownGridLoadAction.class);
	private int check = 0;

	public GtnReportingVariableBreakdownGridLoadAction() {
		super();
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	private boolean isDisableColumns;

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			loadGridAction(componentId, gtnUIFrameWorkActionConfig);

		} catch (Exception ex) {
			logger.error("Error message", ex);
		}
	}

	private void loadGridAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int i = 0;
		String viewIdCheck = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("variableBreakdown", componentId)
				.getComponentData().getSharedPopupData().toString();
		String parentComponentId;
		List variableBreakdownSaveActionList = new ArrayList<>();
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String variableBreakdownTableId = actionParameterList.get(1).toString();

		List<GtnReportComparisonProjectionBean> comparisonLookupBeanList = new ArrayList<>();
		List<GtnReportComparisonProjectionBean> finalArrayListforGrid;
		GtnUIFrameworkBaseComponent idComponentDataFromDisplaySelectionTab = null;
		GtnUIFrameworkBaseComponent idComponentData = null;
		List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromDisplaySelectionTab = new ArrayList<>();
		String reportdata = null;
		if (REPORTING_DASHBOARD_SCREEN.equals(viewIdCheck)) {
			String parentComponentIdForFrequency = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId)
					.getParentViewId();
			parentComponentId = GtnUIFrameworkGlobalUI.getVaadinComponentData(parentComponentIdForFrequency)
					.getParentViewId();
			reportdata = actionParameterList.get(9).toString();
			idComponentDataFromDisplaySelectionTab = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
					"reportingDashboardTab_reportingDashboardComparisonConfig", componentId);
		} else {
			parentComponentId = componentId;
			idComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(actionParameterList.get(2).toString(), parentComponentId);
			reportdata = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(actionParameterList.get(9).toString(), parentComponentId)
					.getCaptionFromV8ComboBox();
		}

		if (baseComponentValidationDisplaySelectionTab(idComponentDataFromDisplaySelectionTab)) {
			if (idComponentDataFromDisplaySelectionTab.getComponentData().getCustomData() != null) {
				comparisonLookupBeanListFromDisplaySelectionTab = (List<GtnReportComparisonProjectionBean>) idComponentDataFromDisplaySelectionTab
						.getComponentData().getCustomData();
			} else {
				idComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
						"reportLandingScreen_reportingDashboardComparisonConfig", parentComponentId);
			}
		}

		if (baseComponentValidation(idComponentData)) {
			comparisonLookupBeanList = (List<GtnReportComparisonProjectionBean>) idComponentData.getComponentData()
					.getCustomData();
		}

		if ((!comparisonLookupBeanListFromDisplaySelectionTab.isEmpty()) && (!comparisonLookupBeanList.isEmpty())) {
			finalArrayListforGrid = new ArrayList<>(comparisonLookupBeanListFromDisplaySelectionTab);
			finalArrayListforGrid.addAll(comparisonLookupBeanList);
			Set<GtnReportComparisonProjectionBean> finalSet = new LinkedHashSet<>(finalArrayListforGrid);
			finalArrayListforGrid = new ArrayList<>(finalSet);
		} else if (comparisonLookupBeanListEmptyValidation(comparisonLookupBeanListFromDisplaySelectionTab,
				comparisonLookupBeanList)) {
			finalArrayListforGrid = new ArrayList<>();
		} else if (comparisonLookupBeanListFromDisplaySelectionTab.isEmpty()) {
			finalArrayListforGrid = comparisonLookupBeanList;
		} else {
			finalArrayListforGrid = comparisonLookupBeanListFromDisplaySelectionTab;
		}

		comparisonLookupBeanList = Optional.ofNullable(comparisonLookupBeanList).orElseGet(ArrayList::new);

		List<String> projectionNameListFromCustomData = new ArrayList<>(comparisonLookupBeanList.size() + 2);

		loadProjectionNameInDdlb(projectionNameListFromCustomData, reportdata, finalArrayListforGrid);

		int comparisonLookupBeanSize = projectionNameListFromCustomData.size();

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView(variableBreakdownTableId, componentId).getComponent();
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();

		List projectionList = new ArrayList<>(projectionNameListFromCustomData.size());
		loadProjectionList(projectionNameListFromCustomData, projectionList);

		GtnUIFrameworkComboBoxConfig fileOrProjectionComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString(), componentId).getComponentConfig()
				.getGtnComboboxConfig();
		fileOrProjectionComboboxConfig.setItemValues(projectionList);
		fileOrProjectionComboboxConfig.setItemCaptionValues(projectionList);

		GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
		combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION, actionParameterList.get(3).toString(),
				componentId, Arrays.asList(""));

		String frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(4).toString())
				.getStringCaptionFromV8ComboBox();

		reloadGridBasedOnFrequency(viewIdCheck, pagedGrid);

		clearGrid(grid);

		GtnUIFrameworkPagedTableConfig tableConfig = setHeaderFromWs(pagedGrid, componentId, grid, parentComponentId,
				viewIdCheck);
		configureCheckboxHeaderComponents(tableConfig.getTableColumnMappingId(), tableConfig.getColumnHeaders(), grid,
				tableConfig);

		setStartAndEndPeriodForVariableBreakdwonLookup(tableConfig, componentId, actionParameterList);

		String localDate = String.valueOf(LocalDate.now());

		String[] localDateSplit = localDate.split("-");
		String currentYear = localDateSplit[0];
		String currentmonth = localDateSplit[1];
		String currentDateToDisableField = getCurrentDateToDisableField(frequency, currentYear, currentmonth);

		Component vaadinComponent = null;

		Object[] filterColumnIdList = pagedGrid.getTableConfig().getTableColumnMappingId();

		int rowCount = 1;
		while (rowCount <= comparisonLookupBeanSize) {
			HeaderRow filterRow = grid.appendHeaderRow();
			isDisableColumns = true;
			for (int col = 0; col < filterColumnIdList.length; col++) {
				GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean = new GtnReportVariableBreakdownLookupBean();
				variableBreakdownLookupBean.setProperty(String.valueOf(filterColumnIdList[col]));
				variableBreakdownLookupBean.setComponentId(componentId);
				variableBreakdownLookupBean.setRowId(i);
				variableBreakdownLookupBean.setCol(col);
				variableBreakdownLookupBean.setCurrentDateField(currentDateToDisableField);
				variableBreakdownLookupBean.setProjectionName(projectionNameListFromCustomData.get(i));
				variableBreakdownLookupBean.setVariableBreakdownSaveActionList(variableBreakdownSaveActionList);
				variableBreakdownLookupBean.setRowCount(rowCount);
				variableBreakdownLookupBean.setComparisonLookupBeanList(finalArrayListforGrid);
				vaadinComponent = getCustomFilterComponent(variableBreakdownLookupBean, grid, tableConfig,
						gridComponent);
				filterRow.getCell(String.valueOf(filterColumnIdList[col])).setComponent(vaadinComponent);
			}
			i++;
			rowCount++;
		}

		setReportProfileVariableBreakdown(gridComponent, grid, componentId);
	}

	private boolean comparisonLookupBeanListEmptyValidation(
			List<GtnReportComparisonProjectionBean> comparisonLookupBeanListFromDisplaySelectionTab,
			List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		return comparisonLookupBeanListFromDisplaySelectionTab.isEmpty() && comparisonLookupBeanList.isEmpty();
	}

	private boolean baseComponentValidationDisplaySelectionTab(
			GtnUIFrameworkBaseComponent idComponentDataFromDisplaySelectionTab) {
		return idComponentDataFromDisplaySelectionTab != null
				&& idComponentDataFromDisplaySelectionTab.getComponentData() != null;
	}

	private boolean baseComponentValidation(GtnUIFrameworkBaseComponent idComponentData) {
		return idComponentData != null && idComponentData.getComponentData().getCustomData() != null;
	}

	private void reloadGridBasedOnFrequency(String viewIdCheck, PagedGrid pagedGrid) {
		if (REPORTING_DASHBOARD_SCREEN.equals(viewIdCheck)) {
			GtnUIFrameWorkActionConfig variableBreakDownHeaderLoadActionFrequency = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakDownHeaderLoadActionFrequency.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT);
			variableBreakDownHeaderLoadActionFrequency.addActionParameter("reportLandingScreen_fromPeriod");
			variableBreakDownHeaderLoadActionFrequency.addActionParameter("reportLandingScreen_STATUS");
			variableBreakDownHeaderLoadActionFrequency.addActionParameter(viewIdCheck);
			pagedGrid.getTableConfig().setGtnUIFrameWorkActionConfig(variableBreakDownHeaderLoadActionFrequency);
		} else {
			GtnUIFrameWorkActionConfig variableBreakDownHeaderLoadActionFrequency = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakDownHeaderLoadActionFrequency
					.addActionParameter("reportOptionsTab_variableBreakdownFrequencyConfig");
			variableBreakDownHeaderLoadActionFrequency.addActionParameter("reportLandingScreen_fromPeriod");
			variableBreakDownHeaderLoadActionFrequency.addActionParameter("reportLandingScreen_STATUS");
			variableBreakDownHeaderLoadActionFrequency.addActionParameter(viewIdCheck);
			pagedGrid.getTableConfig().setGtnUIFrameWorkActionConfig(variableBreakDownHeaderLoadActionFrequency);
		}
	}

	private void loadProjectionList(List<String> projectionNameListFromCustomData, List projectionList) {
		for (int start = 0; start < projectionNameListFromCustomData.size(); start++) {
			projectionList.add(projectionNameListFromCustomData.get(start));
		}
	}

	private void loadProjectionNameInDdlb(List<String> projectionNameListFromCustomData, String reportdata,
			List<GtnReportComparisonProjectionBean> finalArrayListforGrid) {
		projectionNameListFromCustomData.clear();
		projectionNameListFromCustomData.add(EX_FACTORY_SALES);

		if (!"3".equals(reportdata)) {
			projectionNameListFromCustomData.add("Latest Approved");
		}

		for (int count = 0; count < finalArrayListforGrid.size(); count++) {
			projectionNameListFromCustomData.add(finalArrayListforGrid.get(count).getProjectionName());
		}
	}

	private void setReportProfileVariableBreakdown(GtnUIFrameworkComponentData gridComponent,
			Grid<GtnWsRecordBean> grid, String componentId) {
		List<Object[]> reportProfileSubmitBeanList = new ArrayList<>();

		GtnUIFrameworkBaseComponent landingScreenVariableBreakdownLookupCustomData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent("dataSelectionTab_reportOptionsTabVariableBreakdown", componentId);

		GtnUIFrameworkBaseComponent variableBreakdownLookupCustomData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent("reportingDashboardTab_reportOptionsTabVariableBreakdown",
						componentId);
		if (landingScreenVariableBreakdownLookupCustomData.getComponent() != null
				&& landingScreenVariableBreakdownLookupCustomData.getComponentData() != null
				&& landingScreenVariableBreakdownLookupCustomData.getComponentData().getCustomData() != null) {
			List<GtnReportVariableBreakdownLookupBean> variableBreakdownReportProfileBean = (List<GtnReportVariableBreakdownLookupBean>) landingScreenVariableBreakdownLookupCustomData
					.getComponentData().getCustomData();
			setVariableBreakdownFromView(gridComponent, grid, reportProfileSubmitBeanList,
					variableBreakdownReportProfileBean);
		}
		if (variableBreakdownLookupCustomData.getComponent() != null
				&& variableBreakdownLookupCustomData.getComponentData() != null
				&& variableBreakdownLookupCustomData.getComponentData().getCustomData() != null) {
			List<GtnReportVariableBreakdownLookupBean> variableBreakdownReportProfileBean = (List<GtnReportVariableBreakdownLookupBean>) variableBreakdownLookupCustomData
					.getComponentData().getCustomData();
			setVariableBreakdownFromView(gridComponent, grid, reportProfileSubmitBeanList,
					variableBreakdownReportProfileBean);
		}

	}

	private void setVariableBreakdownFromView(GtnUIFrameworkComponentData gridComponent, Grid<GtnWsRecordBean> grid,
			List<Object[]> reportProfileSubmitBeanList,
			List<GtnReportVariableBreakdownLookupBean> variableBreakdownReportProfileBean) {
		for (int start = 0; start < variableBreakdownReportProfileBean.size(); start++) {
			ComboBox variableBreakdownGridCombo = (ComboBox) grid
					.getHeaderRow(variableBreakdownReportProfileBean.get(start).getRowCount())
					.getCell(variableBreakdownReportProfileBean.get(start).getProperty()).getComponent();
			variableBreakdownGridCombo
					.setSelectedItem(variableBreakdownReportProfileBean.get(start).getSelectedVariable());
			Object[] obj = new Object[7];
			obj[0] = variableBreakdownReportProfileBean.get(start).getSelectedVariable();
			obj[1] = variableBreakdownReportProfileBean.get(start).getColumnId();
			obj[2] = variableBreakdownReportProfileBean.get(start).getMasterSid();
			obj[3] = variableBreakdownReportProfileBean.get(start).getProperty();
			obj[4] = variableBreakdownReportProfileBean.get(start).getProperty();
			obj[5] = variableBreakdownReportProfileBean.get(start).getRowCount();
			obj[6] = variableBreakdownReportProfileBean.get(start).getComponentId();
			reportProfileSubmitBeanList.add(obj);
		}

		gridComponent.setCustomData(reportProfileSubmitBeanList);
	}

	private void setStartAndEndPeriodForVariableBreakdwonLookup(GtnUIFrameworkPagedTableConfig tableConfig,
			String componentId, List<Object> actionParameterList) {
		List<String> startAndEndPeriodCaptionList = new ArrayList<>(tableConfig.getColumnHeaders());
		ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(
				Arrays.asList(tableConfig.getTableColumnMappingId()));

		startAndEndPeriodCaptionList.remove(0);
		startAndEndPeriodItemIdList.remove(0);

		GtnUIFrameworkComboBoxConfig startPeriodComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(6).toString(), componentId).getComponentConfig()
				.getGtnComboboxConfig();
		startPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
		startPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);

		GtnUIFrameworkComboBoxComponent startPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
		startPeriodCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				actionParameterList.get(6).toString(), componentId, Arrays.asList(""));

		GtnUIFrameworkComboBoxConfig endPeriodComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(7).toString(), componentId).getComponentConfig()
				.getGtnComboboxConfig();
		endPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
		endPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);

		GtnUIFrameworkComboBoxComponent endPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
		endPeriodCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				actionParameterList.get(7).toString(), componentId, Arrays.asList(""));

	}

	public String getCurrentDateToDisableField(String frequency, String currentYear, String currentmonth) {
		String currentDate = "";
		if (frequency.startsWith("Ann")) {

			currentDate = currentYear + "year";
		} else if (frequency.startsWith("Qua") || frequency.startsWith("-Sel")) {

			Map<String, String> monthToQuarter = new HashMap<>();
			monthToQuarter.put("01", "1");
			monthToQuarter.put("02", "1");
			monthToQuarter.put("03", "1");
			monthToQuarter.put("04", "2");
			monthToQuarter.put("05", "2");
			monthToQuarter.put("06", "2");
			monthToQuarter.put("07", "3");
			monthToQuarter.put("08", "3");
			monthToQuarter.put("09", "3");
			monthToQuarter.put("10", "4");
			monthToQuarter.put("11", "4");
			monthToQuarter.put("12", "4");
			currentDate = "q" + monthToQuarter.get(currentmonth) + currentYear + "quarter";
		} else if (frequency.startsWith("Sem")) {
			Map<String, String> monthToSemiAnnual = new HashMap<>();
			monthToSemiAnnual.put("01", "1");
			monthToSemiAnnual.put("02", "1");
			monthToSemiAnnual.put("03", "1");
			monthToSemiAnnual.put("04", "1");
			monthToSemiAnnual.put("05", "1");
			monthToSemiAnnual.put("06", "1");
			monthToSemiAnnual.put("07", "2");
			monthToSemiAnnual.put("08", "2");
			monthToSemiAnnual.put("09", "2");
			monthToSemiAnnual.put("10", "2");
			monthToSemiAnnual.put("11", "2");
			monthToSemiAnnual.put("12", "2");
			currentDate = "s" + monthToSemiAnnual.get(currentmonth) + currentYear + "semiAnnual";
		} else {
			Map<String, String> monthToSemiAnnual = new HashMap<>();
			monthToSemiAnnual.put("01", "Jan");
			monthToSemiAnnual.put("02", "Feb");
			monthToSemiAnnual.put("03", "Mar");
			monthToSemiAnnual.put("04", "Apr");
			monthToSemiAnnual.put("05", "May");
			monthToSemiAnnual.put("06", "Jun");
			monthToSemiAnnual.put("07", "Jul");
			monthToSemiAnnual.put("08", "Aug");
			monthToSemiAnnual.put("09", "Sep");
			monthToSemiAnnual.put("10", "Oct");
			monthToSemiAnnual.put("11", "Nov");
			monthToSemiAnnual.put("12", "Dec");
			currentDate = monthToSemiAnnual.get(currentmonth) + "month" + currentYear;
		}
		return currentDate;
	}

	private GtnUIFrameworkPagedTableConfig setHeaderFromWs(PagedGrid pagedGrid, String componentId,
			Grid<GtnWsRecordBean> grid, String parentComponentId, String viewIdCheck)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableConfig tableConfig = pagedGrid.getTableConfig();
		String classPath = tableConfig.getGridHeaderCustomClassLoadURL();
		classLoader(tableConfig.getGtnUIFrameWorkActionConfig(), classPath, componentId);
		GtnUIFrameworkWebserviceRequest headerRequest = getCustomPagedTableRequest(
				tableConfig.getGtnUIFrameWorkActionConfig(), componentId, viewIdCheck, parentComponentId);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(tableConfig.getGridColumnHeader(),
				tableConfig.getModuleName(), headerRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPagedTableResponse tableHeadersResponse = response.getGtnWsPagedTableResponse();
		List<Object> tableHeaderMappingIdList = tableHeadersResponse.getSingleColumns();
		tableHeaderMappingIdList.add(0, GtnFrameworkReportStringConstants.VB_GRID_LOAD_HEADER_PROJECTION_NAMES);
		List<String> tableSingleHeaders = tableHeadersResponse.getSingleHeaders();
		tableSingleHeaders.add(0, "");
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
				if (!tableColumnMappingId[i]
						.equals(GtnFrameworkReportStringConstants.VB_GRID_LOAD_HEADER_PROJECTION_NAMES)) {
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

	private Component getCustomFilterComponent(GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean,
			Grid<GtnWsRecordBean> grid, GtnUIFrameworkPagedTableConfig tableConfig,
			GtnUIFrameworkComponentData gridComponent) {
		try {

			if (variableBreakdownLookupBean.getProperty()
					.equals(GtnFrameworkReportStringConstants.VB_GRID_LOAD_HEADER_PROJECTION_NAMES)) {
				GtnUIFrameworkComponentConfig componentConfig = new GtnUIFrameworkComponentConfig();
				componentConfig.setComponentName(variableBreakdownLookupBean.getProjectionName());
				componentConfig.setComponentId(variableBreakdownLookupBean.getProperty()
						+ variableBreakdownLookupBean.getProjectionName() + variableBreakdownLookupBean.getRowId());

				GtnUIFrameworkComponent componentLabel = V8_LABEL.getGtnComponent();
				Component vaadinComponentLabel = null;
				vaadinComponentLabel = componentLabel.buildVaadinComponent(componentConfig);
				Label vaadinLabel = (Label) vaadinComponentLabel;
				vaadinLabel.setValue(variableBreakdownLookupBean.getProjectionName());
				grid.getColumn(variableBreakdownLookupBean.getProperty()).setMinimumWidthFromContent(true);
				grid.getColumn(variableBreakdownLookupBean.getProperty()).setResizable(false);
				return vaadinLabel;
			}
			GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(
					"reportOptionsTab_variableBreakdownValue", variableBreakdownLookupBean.getComponentId());

			GtnUIFrameworkComponent component = COMBOBOX_VAADIN8.getGtnComponent();
			AbstractComponent vaadinComponent = null;
			GtnUIFrameworkComboBoxConfig variableBreakdownValueLoadConfig = new GtnUIFrameworkComboBoxConfig();
			getComboBoxConfig(variableBreakdownLookupBean, variableBreakdownValueLoadConfig);
			base.getComponentConfig().setGtnComboboxConfig(variableBreakdownValueLoadConfig);
			vaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
			GtnUIFrameworkComboBoxComponent gtnUIFrameworkComboBoxComponent = new GtnUIFrameworkComboBoxComponent();
			gtnUIFrameworkComboBoxComponent.postCreateComponent(vaadinComponent, base.getComponentConfig());

			ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
			vaadinCombobox.setId(
					variableBreakdownLookupBean.getProperty() + String.valueOf(variableBreakdownLookupBean.getRowId()));
			vaadinCombobox.addStyleName("stpl-comboBox-Inside-Grid-CustomStyle");
			vaadinCombobox.setSelectedItem(1);
			if (variableBreakdownLookupBean.getProperty()
					.equalsIgnoreCase(variableBreakdownLookupBean.getCurrentDateField())) {

				check = variableBreakdownLookupBean.getCol() + 1;

			}
			if (variableBreakdownLookupBean.getCol() == check) {
				isDisableColumns = false;
			}
			if (!isDisableColumns) {
				grid.getHeaderRow(0).getCell(variableBreakdownLookupBean.getProperty()).getComponent()
						.setEnabled(false);
				vaadinCombobox.setSelectedItem(2);
				vaadinCombobox.setReadOnly(true);
			}

			vaadinCombobox.addValueChangeListener(event -> {
				int selectedValue = (int) event.getValue();
				String columnId = tableConfig.getColumnHeaders().get(variableBreakdownLookupBean.getCol());
				Label projectionNameForWs = (Label) grid.getHeaderRow(variableBreakdownLookupBean.getRowCount())
						.getCell(GtnFrameworkReportStringConstants.VB_GRID_LOAD_HEADER_PROJECTION_NAMES).getComponent();
				int masterSid = getMasterSid(projectionNameForWs,
						variableBreakdownLookupBean.getComparisonLookupBeanList());

				Object[] obj = new Object[7];
				obj[0] = selectedValue;
				obj[1] = columnId;
				obj[2] = masterSid;
				obj[3] = variableBreakdownLookupBean.getProperty();
				obj[4] = projectionNameForWs.getValue();
				obj[5] = variableBreakdownLookupBean.getRowCount();
				obj[6] = vaadinCombobox.getId();
				variableBreakdownLookupBean.getVariableBreakdownSaveActionList().add(obj);
				gridComponent.setCustomData(variableBreakdownLookupBean.getVariableBreakdownSaveActionList());
			});

			return vaadinCombobox;
		} catch (Exception e) {
			logger.error("Error message" + e);
		}
		return null;
	}

	private void getComboBoxConfig(GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean,
			GtnUIFrameworkComboBoxConfig variableBreakdownValueLoadConfig) {
		if (EX_FACTORY_SALES.equals(variableBreakdownLookupBean.getProjectionName())) {
			variableBreakdownValueLoadConfig.setItemValues(Arrays.asList(1, 2));
			variableBreakdownValueLoadConfig.setItemCaptionValues(Arrays.asList("Actuals", "Projections"));
		} else {
			variableBreakdownValueLoadConfig.setItemValues(Arrays.asList(1, 2, 3));
			variableBreakdownValueLoadConfig
					.setItemCaptionValues(Arrays.asList("Actuals", "Projections", "P & L (Accruals)"));
		}
	}

	private int getMasterSid(Label projectionNames, List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		int masterSid = 0;
		if (projectionNames.getValue().equalsIgnoreCase(EX_FACTORY_SALES)) {
			masterSid = -1;
		}
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
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String sourceViewId, String viewIdCheck,
			String parentComponentId) {
		if (REPORTING_DASHBOARD_SCREEN.equals(viewIdCheck)) {
			GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(), parentComponentId)
					.getComponentData();
			return resultTableComponentData.getCustomPagedTreeTableRequest();
		}
		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(), sourceViewId).getComponentData();
		return resultTableComponentData.getCustomPagedTreeTableRequest();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
