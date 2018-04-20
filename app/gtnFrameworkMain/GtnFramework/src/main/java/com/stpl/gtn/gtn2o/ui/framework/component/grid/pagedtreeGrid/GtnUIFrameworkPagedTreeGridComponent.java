/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.pagedtreeGrid;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkPagedTreeGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

    @Override
    public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) throws GtnFrameworkGeneralException {
        VerticalLayout resultLayout = new VerticalLayout();
        GtnUIFrameworkPagedTreeTableConfig tableConfig = componentConfig.getGtnPagedTreeTableConfig();

//        String columns[] = Arrays.stream(tableConfig.getLeftTableColumnMappingId()).map(Object::toString).toArray(String[]::new);
//        pagedTreeTableConfig.setVisibleColumns(Arrays.asList(columns));
//        
//        
//        pagedTreeTableConfig.setColumnHeaders(Arrays.asList(tableConfig.getLeftTableVisibleHeader()));
//        QueryBean queryBean = GtnUIFrameworkGlobalUI.setQueryBean(tableConfig);
//        pagedTreeTableConfig.setQueryBean(queryBean);
        if (tableConfig.getLeftHeader() != null) {
            this.configureLeftTablHeader(tableConfig, componentConfig.getSourceViewId());
        }
        if (tableConfig.getRightHeader() != null) {
            this.configureRightTableHeader(tableConfig, componentConfig.getSourceViewId());
        }
        configureTableHeaders(tableConfig);
        PagedTreeGrid pagedTreeGrid = new PagedTreeGrid(tableConfig);
        pagedTreeGrid.getGrid().setId(componentConfig.getComponentId());
        resultLayout.setSizeFull();
        resultLayout.addComponent(pagedTreeGrid.getGrid());
        pagedTreeGrid.getGrid().setWidth(componentConfig.getComponentWidth());
        pagedTreeGrid.getGrid().setHeight(componentConfig.getComponentHight());
        
        resultLayout.setComponentAlignment(pagedTreeGrid.getGrid(), Alignment.MIDDLE_CENTER);
        GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
        componentData.setCustomData(pagedTreeGrid);

        

        pagedTreeGrid.getGrid().getEditor().setEnabled(true);
        resultLayout.setData(componentData);

        try {

            GtnUIFrameworkPagedTreeTableLogic tableLogic = getPagedTableLogicClass(componentConfig, componentData);

//            pagedTreeGrid.addStyleNames(componentConfig.getComponentStyle().toArray(new String[componentConfig.getComponentStyle().size()]));

            // ConfigureTables
            initializeResultTable(pagedTreeGrid, tableConfig);
            pagedTreeGrid.setPageLength(tableConfig.getPageLength());

//			addFieldFactory(pagedTreeGrid, tableConfig, componentData);
            componentData.setCurrentComponentConfig(componentConfig);
            componentData.getCurrentComponentConfig().setGtnPagedTreeTableConfig(tableConfig);
            componentData.setCustomData(this);
            componentData.addCustomDataToList(pagedTreeGrid);
            componentData.addCustomDataToList(new HashSet<String>());
            componentData.addCustomDataToList(new HashSet<String>());

//            pagedTreeGrid.addStyleNames("filterbar", "v-has-width", "v-table-filterbar", "table-header-normal");
//            pagedTreeGrid.addStyleNames("filterbar", "v-has-width", "v-table-filterbar", "table-header-normal");

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

    /**
     * Initialize Result Table.
     */
    protected void initializeResultTable(PagedTreeGrid resultsTable,
            GtnUIFrameworkPagedTreeTableConfig tableConfig) {
        resultsTable.getGrid().markAsDirty();
        resultsTable.getGrid().setSelectionMode(Grid.SelectionMode.NONE);
//		resultsTable.getGrid().setSplitPosition(tableConfig.getSplitPosition(), Sizeable.Unit.PIXELS);
//		resultsTable.setMinSplitPosition(tableConfig.getMinSplitPosition(), Sizeable.Unit.PIXELS);
//		resultsTable.setMaxSplitPosition(tableConfig.getMaxSplitPosition(), Sizeable.Unit.PIXELS);
//		resultsTable.addStyleNames(ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE);
    }

    /**
     * Configuration method to fire required tables headers
     *
     * @param resultsTable
     * @param tableConfig
     */
    @SuppressWarnings("rawtypes")
    protected void configureTableHeaders(
            GtnUIFrameworkPagedTreeTableConfig tableConfig) {

//		final ExtTreeContainer<GtnWsRecordBean> container = new ExtTreeContainer<>(GtnWsRecordBean.class,
//				ExtContainer.DataStructureMode.LIST);
//		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) pagedTreeGrid
//				.getLeftFreezeAsTable().getContainerLogic();
        List<Object> leftVisibleColumnList = new ArrayList<>(Arrays.asList(tableConfig.getLeftTableColumnMappingId()));
        List<Object> rightVisibleColumnList = new ArrayList<>(Arrays.asList(tableConfig.getRightTableColumnMappingId()));
        List<Object> recordHeader = new ArrayList<>();
        recordHeader.addAll(leftVisibleColumnList);
        recordHeader.addAll(rightVisibleColumnList);
//		container.setRecordHeader(recordHeader);
//		Map<Object, Class> dataType = new HashMap<>();
//		for (int i = 0; i < recordHeader.size(); i++) {
//			dataType.put(recordHeader.get(i).toString(), String.class);
//		}
//		container.setColumnProperties(dataType);
//		pagedTreeGrid.setRecordHeader(recordHeader);
//		pagedTreeGrid.setContainerDataSource(container);

        List<String> leftHeaderList = new ArrayList<>(Arrays.asList(tableConfig.getLeftTableVisibleHeader()));
        List<String> rightHeaderList = new ArrayList<>(Arrays.asList(tableConfig.getRightTableVisibleHeader()));
//		final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
//		final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();

//		leftTable.setImmediate(true);
//		leftTable.reConstruct(true);
//		resultsTable.setHeight(tableConfig.getTableHeight());
//		leftTable.setHeight(tableConfig.getTableHeight());
        
        leftVisibleColumnList.addAll(rightVisibleColumnList);
        leftHeaderList.addAll(rightHeaderList);

        tableConfig.setVisibleColumns(leftVisibleColumnList);
        tableConfig.setColumnHeaders(leftHeaderList);
       
//		leftTable.setColumnHeaders(leftHeaderList.toArray(new String[leftHeaderList.size()]));
//
//		rightTable.setImmediate(true);
//		rightTable.reConstruct(true);
//		rightTable.setHeight(tableConfig.getTableHeight());
//		rightTable.setVisibleColumns(.toArray());
//		rightTable.setColumnHeaders(rightHeaderList.toArray(new String[rightHeaderList.size()]));
//        if (tableConfig.isDoubleHeaderVisible()) {
//              List<Object> doubleHeaderVisibleColumns = new ArrayList<>(Arrays.asList(tableConfig.getLeftTableColumnMappingId()));
//           List<Object> doubleHeaderVisibleHeaders = new ArrayList<>(Arrays.asList(tableConfig.getRightTableColumnMappingId()));
//            tableConfig.getLeftTableDoubleHeaderVisibleHeaders().addAll(doubleHeaderVisibleHeaders);
//            tableConfig.getLeftTableDoubleHeaderVisibleColumns().addAll(doubleHeaderVisibleColumns);
//			leftTable.setDoubleHeaderVisible(true);
//			leftTable.setDoubleHeaderVisibleColumns(.toArray());
//			leftTable.setDoubleHeaderColumnHeaders(tableConfig.getLeftTableDoubleHeaderVisibleHeaders()
//					.toArray(new String[tableConfig.getLeftTableDoubleHeaderVisibleHeaders().size()]));
//			leftTable.setDoubleHeaderMap(tableConfig.getLeftTableDoubleHeaderMap());
//
//			rightTable.setDoubleHeaderVisible(true);
//			rightTable.setDoubleHeaderVisibleColumns(.toArray());
//			rightTable.setDoubleHeaderColumnHeaders(
//					.toArray(new String[tableConfig.getRightTableDoubleVisibleHeaders().size()]));
//			rightTable.setDoubleHeaderMap(tableConfig.getRightTableDoubleHeaderMap());
//
//			List<Object> doubleHeaderColumns = tableConfig.getRightTableDoubleHeaderVisibleColumns();
//			for (Object doubleHeaderColumn : doubleHeaderColumns) {
//				rightTable.setDoubleHeaderColumnCheckBox(doubleHeaderColumn, true);
//				// rightTable.setDoubleHeaderColumnCheckBoxDisable(); need to
//				// implement
//			}
//        }

    }

    public void reloadComponent(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId,
            String sourceComponentId) throws GtnFrameworkGeneralException {
//		AbstractComponent resultLayoutInMap = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId, sourceComponentId);
//		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayoutInMap.getData();
//		PagedTreeGrid  resultsTable = (PagedTreeGrid ) componentData.getCustomDataList().get(0);
//		GtnUIFrameworkPagedTreeTableConfig tableConfig = componentData.getCurrentComponentConfig()
//				.getGtnPagedTreeTableConfig();
//		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
//				.getLeftFreezeAsTable().getContainerLogic();
//		formHeadersAndConfig(gtnUIFrameWorkActionConfig, tableConfig, resultsTable, tableLogic, componentData);
    }

    /**
     * This is the method to form Dynamic Header based on the inputs. The input
     * is List<List<String>>
     *
     * 1. Single Header - Visible column list 2. Single Header - Column Values
     * 3. Double Header - Visible column list 4. Double Header - Column Values
     *
     * 4 List should be added in a single List and to be sent as an input to
     * this method to form dynamic Headers
     *
     * @param configureInputList
     * @throws GtnFrameworkGeneralException
     */
    private void formHeadersAndConfig(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
            GtnUIFrameworkPagedTreeTableConfig tableConfig, PagedTreeGrid pagedTreeGrid,
            GtnUIFrameworkPagedTreeTableLogic tableLogic, GtnUIFrameworkComponentData componentData)
            throws GtnFrameworkGeneralException {
        try {

            GtnWsPagedTreeTableResponse leftTableHeaders = loadLeftHeader(gtnUIFrameWorkActionConfig, tableConfig,
                    componentData.getComponentIdInMap());

            GtnWsPagedTreeTableResponse rightTableHeaders = loadRightHeader(gtnUIFrameWorkActionConfig, tableConfig,
                    componentData.getComponentIdInMap());

            setHeaderConfig(leftTableHeaders, rightTableHeaders, pagedTreeGrid, tableLogic, componentData);
        } catch (GtnFrameworkValidationFailedException e) {
            throw new GtnFrameworkValidationFailedException("Error in formHeadersAndConfig ", e);
        }
    }

    @Override
    public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
            Object reloadInput) {
        return;

    }

    private GtnUIFrameworkPagedTreeTableLogic getPagedTableLogicClass(GtnUIFrameworkComponentConfig componentConfig,
            GtnUIFrameworkComponentData componentData) throws GtnFrameworkGeneralException {
        GtnUIFrameworkPagedTreeTableLogic tableLogic;
        if (componentConfig.getPagedTableLogicClassName() == null) {
            tableLogic = new GtnUIFrameworkPagedTreeTableLogic(componentData);
        } else {
            GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
            tableLogic = (GtnUIFrameworkPagedTreeTableLogic) classLoader
                    .loadDynamicClass(componentConfig.getPagedTableLogicClassName());
        }
        return tableLogic;
    }

//	private void addFieldFactory(PagedTreeGrid  resultsTable, GtnUIFrameworkPagedTreeTableConfig tableConfig,
//			GtnUIFrameworkComponentData componentData) {
//		if (tableConfig.isLeftTableEditable()) {
//
//			addTableFieldFactory(resultsTable.getLeftFreezeAsTable(),
//					tableConfig.getLeftTableCustomFieldFactoryClassname(), tableConfig.getLeftTableEditableColumnList(),
//					tableConfig.getLeftTableEditableComponentConfig(), componentData);
//		}
//		if (tableConfig.isRightTableEditable()) {
//
//			addTableFieldFactory(resultsTable.getRightFreezeAsTable(),
//					tableConfig.getRightTableCustomFieldFactoryClassname(),
//					tableConfig.getRightTableEditableColumnList(), tableConfig.getRightTableEditableComponentConfig(),
//					componentData);
//		}
//	}
//	private void addTableFieldFactory(ExtFilterTreeTable resultTable, String customFieldFactoryClassname,
//			List<String> editableColumnList, List<GtnUIFrameworkComponentConfig> editableField,
//			GtnUIFrameworkComponentData componentData) {
//		resultTable.setEditable(true);
//		if (customFieldFactoryClassname == null) {
//			resultTable.setTableFieldFactory(
//					new GtnUIFrameworkCustomFieldFactory(editableColumnList, editableField, componentData));
//		} else {
//			try {
//				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
//				DefaultFieldFactory fieldFactory = (DefaultFieldFactory) classLoader
//						.loadDynamicClass(customFieldFactoryClassname);
//				if (fieldFactory instanceof GtnUIFrameworkCustomFieldFactory) {
//					GtnUIFrameworkCustomFieldFactory tempFieldFactory = ((GtnUIFrameworkCustomFieldFactory) fieldFactory);
//					tempFieldFactory.setEditableColumnList(editableColumnList);
//					tempFieldFactory.setEditableComponentConfig(editableField);
//
//				}
//				resultTable.setTableFieldFactory(fieldFactory);
//			} catch (GtnFrameworkGeneralException fieldFactoryException) {
//				gtnLogger.error("Custom Field Factory : ", fieldFactoryException);
//			}
//		}
//	}
    @Override
    public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
        VerticalLayout resultLayout = (VerticalLayout) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
        GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayout.getData();
        PagedTreeGrid resultsTable = (PagedTreeGrid) componentData.getCustomDataList().get(0);
//		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
//				.getLeftFreezeAsTable().getContainerLogic();
//		tableLogic.clearAll();
    }

    /**
     * @param tableConfig
     * @throws GtnFrameworkGeneralException GtnUIFrameworkWebserviceRequest
     * dummy request for initial load
     */
    private void configureLeftTablHeader(GtnUIFrameworkPagedTreeTableConfig tableConfig, String sourceViewId)
            throws GtnFrameworkGeneralException {

        String classPath = tableConfig.getLeftHeaderCustomClassLoadUrl();
        classLoader(tableConfig.getGtnUIFrameWorkActionConfig(), classPath, sourceViewId);
        GtnUIFrameworkWebserviceRequest leftHeaderRequest = getCustomPagedTreeTableRequest(
                tableConfig.getGtnUIFrameWorkActionConfig(), sourceViewId);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(tableConfig.getLeftHeader(),
                tableConfig.getModuleName(), leftHeaderRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnWsPagedTreeTableResponse leftTableHeaders = response.getGtnWSPagedTreeTableResponse();

        tableConfig.setLeftTableColumnMappingId(leftTableHeaders.getSingleColumns().toArray());
        tableConfig.setLeftTableVisibleHeader(Arrays.copyOf(leftTableHeaders.getSingleHeaders().toArray(),
                leftTableHeaders.getSingleHeaders().toArray().length, String[].class));

        tableConfig.setLeftTableDoubleHeaderVisibleColumns(leftTableHeaders.getDoubleColumns());
        tableConfig.setLeftTableDoubleHeaderVisibleHeaders(leftTableHeaders.getDoubleHeaders());
        tableConfig.setLeftTableDoubleHeaderMap(leftTableHeaders.getDoubleHeaderMaps());
        

    }

    private void configureRightTableHeader(GtnUIFrameworkPagedTreeTableConfig tableConfig, String sourceViewId)
            throws GtnFrameworkGeneralException {

        String classPath = tableConfig.getRightHeaderCustomClassLoadUrl();
        classLoader(tableConfig.getGtnUIFrameWorkActionConfig(), classPath, sourceViewId);
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = getCustomPagedTreeTableRequest(
                tableConfig.getGtnUIFrameWorkActionConfig(), sourceViewId);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse responseForRightHeader = client.callGtnWebServiceUrl(
                tableConfig.getRightHeader(), tableConfig.getModuleName(), gtnUIFrameworkWebserviceRequest,
                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnWsPagedTreeTableResponse rightTableHeaders = responseForRightHeader.getGtnWSPagedTreeTableResponse();
        tableConfig.setRightTableColumnMappingId(rightTableHeaders.getSingleColumns().toArray());
        tableConfig.setRightTableVisibleHeader(Arrays.copyOf(rightTableHeaders.getSingleHeaders().toArray(),
                rightTableHeaders.getSingleHeaders().toArray().length, String[].class));

        String[] columnDateType = new String[rightTableHeaders.getProperties().size()];
        for (int i = 0; i < rightTableHeaders.getProperties().size(); i++) {
            columnDateType[i] = "java.lang.String";
        }
        tableConfig.setTableColumnDataType(columnDateType);
        tableConfig.setRightTableDoubleHeaderVisibleColumns(rightTableHeaders.getDoubleColumns());
        tableConfig.setRightTableDoubleVisibleHeaders(rightTableHeaders.getDoubleHeaders());
        tableConfig.setRightTableDoubleHeaderMap(rightTableHeaders.getDoubleHeaderMaps());
        
        tableConfig.setRightTableTripleVisibleHeaders(rightTableHeaders.getTripleHeader());
        tableConfig.setRightTableTripleHeaderMap(rightTableHeaders.getTripleHeaderMap());
        
        List<GtnUIFrameworkComponentConfig> rightfieldFactoryComponent = new ArrayList<>();
        if(rightTableHeaders.getEditableFields()!=null){
        for (String tableFieldFactoryColumns : rightTableHeaders.getEditableFields()) {
            GtnUIFrameworkComponentConfig fieldFactoryInputComponentConfig = new GtnUIFrameworkComponentConfig();

            fieldFactoryInputComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
            fieldFactoryInputComponentConfig.setComponentId(tableFieldFactoryColumns);
            fieldFactoryInputComponentConfig.setComponentName(tableFieldFactoryColumns.toUpperCase(Locale.ENGLISH));
            fieldFactoryInputComponentConfig
                    .setGtnUIFrameWorkValueChangeActionConfigList(tableConfig.getComponentconfigActionlist());
            rightfieldFactoryComponent.add(fieldFactoryInputComponentConfig);

        }
        tableConfig.setRightTableEditableComponentConfig(rightfieldFactoryComponent);
        tableConfig.setRightTableEditableColumnList(rightTableHeaders.getEditableFields());
        }

    }

    private void classLoader(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String classPath,
            String sourceViewId) throws GtnFrameworkGeneralException {
        GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
        GtnUIFrameWorkAction loader = (GtnUIFrameWorkAction) classLoader.loadDynamicClass(classPath);
        loader.configureParams(gtnUIFrameWorkActionConfig);
        loader.doAction(sourceViewId, gtnUIFrameWorkActionConfig);
    }

    private GtnUIFrameworkWebserviceRequest getCustomPagedTreeTableRequest(
            GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String sourceViewId) {
        GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
                gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(), sourceViewId).getComponentData();
        return resultTableComponentData.getCustomPagedTreeTableRequest();
    }

    private GtnWsPagedTreeTableResponse loadLeftHeader(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
            GtnUIFrameworkPagedTreeTableConfig tableConfig, String sourceViewId) throws GtnFrameworkGeneralException {
        String classPath = tableConfig.getLeftHeaderUrl();
        classLoader(gtnUIFrameWorkActionConfig, classPath, sourceViewId);
        GtnUIFrameworkWebserviceRequest leftHeaderRequest = getCustomPagedTreeTableRequest(gtnUIFrameWorkActionConfig,
                sourceViewId);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(tableConfig.getLeftWsHeaderUrl(),
                tableConfig.getModuleName(), leftHeaderRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        return response.getGtnWSPagedTreeTableResponse();
    }

    private GtnWsPagedTreeTableResponse loadRightHeader(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
            GtnUIFrameworkPagedTreeTableConfig tableConfig, String sourceViewId) throws GtnFrameworkGeneralException {
        String classPath = tableConfig.getRighttHeaderUrl();
        classLoader(gtnUIFrameWorkActionConfig, classPath, sourceViewId);
        GtnUIFrameworkWebserviceRequest rightHeaderRequest = getCustomPagedTreeTableRequest(gtnUIFrameWorkActionConfig,
                sourceViewId);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse responseForRightHeader = client.callGtnWebServiceUrl(
                tableConfig.getRightWsHeaderUrl(), tableConfig.getModuleName(), rightHeaderRequest,
                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        return responseForRightHeader.getGtnWSPagedTreeTableResponse();
    }

    private void setHeaderConfig(GtnWsPagedTreeTableResponse leftTableHeaders,
            GtnWsPagedTreeTableResponse rightTableHeaders, PagedTreeGrid pagedTreeGrid,
            GtnUIFrameworkPagedTreeTableLogic tableLogic, GtnUIFrameworkComponentData componentData) {

        configureDynamicTreeTableHeaders(pagedTreeGrid,
                componentData.getCurrentComponentConfig().getGtnPagedTreeTableConfig(), tableLogic, componentData,
                leftTableHeaders, rightTableHeaders);
    }

    public void configureDynamicTreeTableHeaders(PagedTreeGrid pagedTreeGrid,
            GtnUIFrameworkPagedTreeTableConfig tableConfig, GtnUIFrameworkPagedTreeTableLogic tableLogic,
            GtnUIFrameworkComponentData componentData, GtnWsPagedTreeTableResponse leftTableHeaders,
            GtnWsPagedTreeTableResponse rightTableHeaders) {
        initializeResultTable(pagedTreeGrid, tableConfig);
//		loadHeaders(pagedTreeGrid, tableConfig, tableLogic, leftTableHeaders, rightTableHeaders, componentData);
//		addFieldFactory(pagedTreeGrid, tableConfig, componentData);

    }

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void loadHeaders(PagedTreeGrid  resultsTable, GtnUIFrameworkPagedTreeTableConfig tableConfig,
//			GtnUIFrameworkPagedTreeTableLogic tableLogic, GtnWsPagedTreeTableResponse leftTableHeaders,
//			GtnWsPagedTreeTableResponse rightTableHeaders, GtnUIFrameworkComponentData componentData) {
//
////		List<Object> recordHeader = new ArrayList<>();
////		recordHeader.addAll(leftTableHeaders.getSingleColumns());
////		recordHeader.addAll(rightTableHeaders.getSingleColumns());
////		Map<String, Class<?>> dataType = new HashMap<>();
////		for (int i = 0; i < recordHeader.size(); i++) {
////			dataType.put(recordHeader.get(i).toString(), String.class);
////		}
////		resultBeanContainer.setColumnProperties(dataType);
////		tableLogic.setRecordHeader(recordHeader);
////		tableLogic.setContainerDataSource(resultBeanContainer);
//		tableLogic.sinkItemPerPageWithPageLength(false);
//		tableLogic.setTreeNodeMultiClick(false);
//		tableLogic.setItemsPerPage(tableConfig.getItemPerPage());
//		tableLogic.setPageLength(tableConfig.getPageLength());
//
//		leftTable.setImmediate(true);
//		leftTable.reConstruct(true);
//		leftTable.setVisibleColumns(leftTableHeaders.getSingleColumns().toArray());
//		leftTable.setColumnHeaders(
//				leftTableHeaders.getSingleHeaders().toArray(new String[leftTableHeaders.getSingleHeaders().size()]));
//
//		for (String column : tableConfig.getCheckBoxVisibleColoumn()) {
//			leftTable.setColumnCheckBox(column, true, false);
//		}
//		if (tableConfig.getCheckBoxVisibleColoumn() != null && !tableConfig.getCheckBoxVisibleColoumn().isEmpty()) {
//			leftTable.setData(new GtnUIFrameworkActionParameter());
//			addCheckAllListener(leftTable, tableConfig, componentData);
//		}
//		leftTable.setDoubleHeaderVisible(true);
//		leftTable.setDoubleHeaderVisibleColumns(leftTableHeaders.getDoubleColumns().toArray());
//		leftTable.setDoubleHeaderColumnHeaders(
//				leftTableHeaders.getDoubleHeaders().toArray(new String[leftTableHeaders.getDoubleHeaders().size()]));
//		leftTable.setDoubleHeaderMap(leftTableHeaders.getDoubleHeaderMaps());
//
//		rightTable.setImmediate(true);
//		rightTable.reConstruct(true);
//		rightTable.setVisibleColumns(rightTableHeaders.getSingleColumns().toArray());
//		rightTable.setColumnHeaders(
//				rightTableHeaders.getSingleHeaders().toArray(new String[rightTableHeaders.getSingleHeaders().size()]));
//		rightTable.setDoubleHeaderVisible(true);
//		rightTable.setDoubleHeaderVisibleColumns(rightTableHeaders.getDoubleColumns().toArray());
//		rightTable.setDoubleHeaderColumnHeaders(
//				rightTableHeaders.getDoubleHeaders().toArray(new String[rightTableHeaders.getDoubleHeaders().size()]));
//		rightTable.setDoubleHeaderMap(rightTableHeaders.getDoubleHeaderMaps());
//
//		List<Object> doubleHeaderColumns = rightTableHeaders.getDoubleColumns();
//		for (Object doubleHeaderColumn : doubleHeaderColumns) {
//			rightTable.setDoubleHeaderColumnCheckBox(doubleHeaderColumn, true);
//		}
//		List<GtnUIFrameworkComponentConfig> rightfieldFactoryComponent = new ArrayList<>();
//		for (String tableFieldFactoryColumns : rightTableHeaders.getEditableFields()) {
//			GtnUIFrameworkComponentConfig fieldFactoryInputComponentConfig = new GtnUIFrameworkComponentConfig();
//			fieldFactoryInputComponentConfig.setComponentId(tableFieldFactoryColumns);
//			fieldFactoryInputComponentConfig.setComponentName(tableFieldFactoryColumns.toUpperCase(Locale.ENGLISH));
//			fieldFactoryInputComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
//			fieldFactoryInputComponentConfig
//					.setGtnUIFrameWorkValueChangeActionConfigList(tableConfig.getComponentconfigActionlist());
//			rightfieldFactoryComponent.add(fieldFactoryInputComponentConfig);
//		}
//		tableConfig.setRightTableEditableColumnList(rightTableHeaders.getEditableFields());
//		tableConfig.setRightTableEditableComponentConfig(rightfieldFactoryComponent);
//
//	}
//	private void addCheckAllListener(final ExtPagedTreeTable<?> leftTable,
//			GtnUIFrameworkPagedTreeTableConfig tableConfig, GtnUIFrameworkComponentData pagedTreeTableComponentData) {
//		leftTable.addColumnCheckListener(
//				new GtnUIFrameworkPagedTreeTableColumnListener(tableConfig, leftTable, pagedTreeTableComponentData));
//	}
    @Override
    public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
