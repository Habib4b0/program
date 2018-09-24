/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.pagedtreegrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkPagedTreeGridComponent
		implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedTreeGridComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		VerticalLayout resultLayout = new VerticalLayout();
		loadStyles(resultLayout, componentConfig.getComponentStyle());
		GtnUIFrameworkPagedTreeTableConfig tableConfig = componentConfig.getGtnPagedTreeTableConfig();
		
		configureTableHeaders(tableConfig, componentConfig.getSourceViewId());
		PagedTreeGrid pagedTreeGrid = new PagedTreeGrid(tableConfig, componentConfig);
		
		resultLayout.setSizeFull();
		resultLayout.addComponent(pagedTreeGrid.getGrid());
		pagedTreeGrid.getGrid().setWidth(componentConfig.getComponentWidth());
		pagedTreeGrid.getGrid().setHeight(componentConfig.getComponentHight());

		resultLayout.setComponentAlignment(pagedTreeGrid.getGrid(), Alignment.MIDDLE_CENTER);
		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		componentData.setCustomData(pagedTreeGrid);

		pagedTreeGrid.getGrid().getEditor().setEnabled(true);
		pagedTreeGrid.getGrid().setData(componentData);
		componentData.setCurrentGtnComponent(this);
		try {

			initializeResultTable(pagedTreeGrid);
			resultLayout.setSizeFull();
			VerticalLayout controls = new VerticalLayout();
			controls.addComponents(pagedTreeGrid.getControlLayout());
			controls.setWidth("100%");
			controls.setHeightUndefined();
			resultLayout.addComponent(pagedTreeGrid.getGrid());
			resultLayout.addComponent(controls);
			resultLayout.setData(componentData);
		} catch (Exception exception) {
			throw new GtnFrameworkGeneralException("Exception while loading the table logic", exception);
		}
		return resultLayout;
	}
	
	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				component.addStyleName(style);

			}
		}

	}

	/**
	 * Initialize Result Table.
	 * 
	 * @param resultsTable
	 * @param tableConfig
	 */
	protected void initializeResultTable(PagedTreeGrid resultsTable) {
		resultsTable.getGrid().markAsDirty();
		resultsTable.getGrid().setSelectionMode(Grid.SelectionMode.NONE);
		resultsTable.getGrid().removeAllColumns();
	}

	/**
	 * Configuration method to fire required tables headers
	 *
	 * @param tableConfig
	 * @param componentId
	 * @param string
	 * @throws GtnFrameworkGeneralException
	 */
	@SuppressWarnings("rawtypes")
	protected void configureTableHeaders(GtnUIFrameworkPagedTreeTableConfig tableConfig, String sourceViewId)
			throws GtnFrameworkGeneralException {

		if (tableConfig.getLeftHeader() != null) {
			this.configureLeftTablHeader(tableConfig, sourceViewId);
		}
		if (tableConfig.getRightHeader() != null) {
			this.configureRightTableHeader(tableConfig, sourceViewId);
		}

		List<Object> leftVisibleColumnList = new ArrayList<>(Arrays.asList(tableConfig.getLeftTableColumnMappingId()));
		List<Object> rightVisibleColumnList = new ArrayList<>(
				Arrays.asList(tableConfig.getRightTableColumnMappingId()));

		List<String> leftHeaderList = new ArrayList<>(Arrays.asList(tableConfig.getLeftTableVisibleHeader()));
		List<String> rightHeaderList = new ArrayList<>(Arrays.asList(tableConfig.getRightTableVisibleHeader()));
		leftVisibleColumnList.addAll(rightVisibleColumnList);
		leftHeaderList.addAll(rightHeaderList);

		tableConfig.setVisibleColumns(leftVisibleColumnList);
		tableConfig.setColumnHeaders(leftHeaderList);

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, dependentComponentId).getComponentData();
		GtnUIFrameworkPagedTreeTableConfig config = componentData.getCurrentComponentConfig()
				.getGtnPagedTreeTableConfig();
		PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();
		grid.getGrid().removeAllColumns();
		try {
			configureTableHeaders(config, componentData.getViewId());
		} catch (GtnFrameworkGeneralException e) {
			gtnlogger.error("Error in loading headers ", e);
		}
		grid.setTableConfig(config);
        grid.initializeGrid(componentData.getViewId() + "_" + componentId);
		grid.setColumnPageNumber(0);
		return;

	}


	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
            // yet to implement
	
	}

	/**
	 * @param tableConfigLeftTable
	 * @throws GtnFrameworkGeneralException
	 *             GtnUIFrameworkWebserviceRequest dummy request for initial load
	 */
	private void configureLeftTablHeader(GtnUIFrameworkPagedTreeTableConfig tableConfigLeftTable, String sourceViewId)
			throws GtnFrameworkGeneralException {

		String classPath = tableConfigLeftTable.getLeftHeaderCustomClassLoadUrl();
		classLoader(tableConfigLeftTable.getGtnUIFrameWorkActionConfig(), classPath, sourceViewId);
		GtnUIFrameworkWebserviceRequest leftHeaderRequest = getCustomPagedTreeTableRequest(tableConfigLeftTable, sourceViewId);
		GtnUIFrameworkWebServiceClient clientLeftTable = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse responseLeftTable = clientLeftTable.callGtnWebServiceUrl(tableConfigLeftTable.getLeftHeader(),
				tableConfigLeftTable.getModuleName(), leftHeaderRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPagedTreeTableResponse leftTableHeaders = responseLeftTable.getGtnWSPagedTreeTableResponse();

		tableConfigLeftTable.setLeftTableColumnMappingId(leftTableHeaders.getSingleColumns().toArray());
		tableConfigLeftTable.setLeftTableVisibleHeader(Arrays.copyOf(leftTableHeaders.getSingleHeaders().toArray(),
				leftTableHeaders.getSingleHeaders().toArray().length, String[].class));
		tableConfigLeftTable.setLeftTableDoubleHeaderVisibleColumns(leftTableHeaders.getDoubleColumns());
		tableConfigLeftTable.setLeftTableDoubleHeaderVisibleHeaders(leftTableHeaders.getDoubleHeaders());
		tableConfigLeftTable.setLeftTableDoubleHeaderMap(leftTableHeaders.getDoubleHeaderMaps());

	}

	private void configureRightTableHeader(GtnUIFrameworkPagedTreeTableConfig tableConfigGrid, String sourceViewId)
			throws GtnFrameworkGeneralException {

		String classPath = tableConfigGrid.getRightHeaderCustomClassLoadUrl();
		classLoader(tableConfigGrid.getGtnUIFrameWorkActionConfig(), classPath, sourceViewId);
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = getCustomPagedTreeTableRequest(tableConfigGrid,
				sourceViewId);
		GtnUIFrameworkWebServiceClient clientGrid = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse responseForRightHeader = clientGrid.callGtnWebServiceUrl(
				tableConfigGrid.getRightHeader(), tableConfigGrid.getModuleName(), gtnUIFrameworkWebserviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsPagedTreeTableResponse rightTableHeadersGrid = responseForRightHeader.getGtnWSPagedTreeTableResponse();
		tableConfigGrid.setRightTableColumnMappingId(rightTableHeadersGrid.getSingleColumns().toArray());
		tableConfigGrid.setRightTableVisibleHeader(Arrays.copyOf(rightTableHeadersGrid.getSingleHeaders().toArray(),
				rightTableHeadersGrid.getSingleHeaders().toArray().length, String[].class));

		String[] columnDateType = new String[rightTableHeadersGrid.getProperties().size()];
		for (int i = 0; i < rightTableHeadersGrid.getProperties().size(); i++) {
			columnDateType[i] = "java.lang.String";
		}
		tableConfigGrid.setTableColumnDataType(columnDateType);
		tableConfigGrid.setRightTableDoubleHeaderVisibleColumns(rightTableHeadersGrid.getDoubleColumns());
		tableConfigGrid.setRightTableDoubleVisibleHeaders(rightTableHeadersGrid.getDoubleHeaders());
		tableConfigGrid.setRightTableDoubleHeaderMap(rightTableHeadersGrid.getDoubleHeaderMaps());
                tableConfigGrid.setRightTableTripleHeaderVisibleColumns(rightTableHeadersGrid.getTripleColumn());
		tableConfigGrid.setRightTableTripleVisibleHeaders(rightTableHeadersGrid.getTripleHeader());
		tableConfigGrid.setRightTableTripleHeaderMap(rightTableHeadersGrid.getTripleHeaderMap());

		List<GtnUIFrameworkComponentConfig> rightfieldFactoryComponent = new ArrayList<>();
		if (rightTableHeadersGrid.getEditableFields() != null) {
			for (String tableFieldFactoryColumnsGrid : rightTableHeadersGrid.getEditableFields()) {
				GtnUIFrameworkComponentConfig fieldFactoryInputComponentConfigGrid = new GtnUIFrameworkComponentConfig();

				fieldFactoryInputComponentConfigGrid.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
				fieldFactoryInputComponentConfigGrid.setComponentId(tableFieldFactoryColumnsGrid);
				fieldFactoryInputComponentConfigGrid.setComponentName(tableFieldFactoryColumnsGrid.toUpperCase(Locale.ENGLISH));
				fieldFactoryInputComponentConfigGrid
						.setGtnUIFrameWorkValueChangeActionConfigList(tableConfigGrid.getComponentconfigActionlist());
				rightfieldFactoryComponent.add(fieldFactoryInputComponentConfigGrid);

			}
			tableConfigGrid.setRightTableEditableComponentConfig(rightfieldFactoryComponent);
			tableConfigGrid.setRightTableEditableColumnList(rightTableHeadersGrid.getEditableFields());
		}

	}

	private void classLoader(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfigGrid, String classPath,
			String sourceViewId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkClassLoader classLoaderGrid = new GtnUIFrameworkClassLoader();
		GtnUIFrameWorkAction loader = (GtnUIFrameWorkAction) classLoaderGrid.loadDynamicClass(classPath);
		loader.configureParams(gtnUIFrameWorkActionConfigGrid);
		loader.doAction(sourceViewId, gtnUIFrameWorkActionConfigGrid);
	}

	private GtnUIFrameworkWebserviceRequest getCustomPagedTreeTableRequest(
			GtnUIFrameworkPagedTreeTableConfig tableConfig, String componentId) {
		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				(String) tableConfig.getGtnUIFrameWorkActionConfig().getActionParameterList().get(0), componentId)
				.getComponentData();
		return resultTableComponentData.getCustomPagedTreeTableRequest();
	}

	public void configureDynamicTreeTableHeaders(PagedTreeGrid pagedTreeGrid) {
		initializeResultTable(pagedTreeGrid);
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

}