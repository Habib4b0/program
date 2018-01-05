package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.ExtFilteringTableConstant;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkCustomFieldFactory;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CssLayout;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnUIFrameworkPagedTreeTableComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedTreeTableComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkPagedTreeTableComponent");

		VerticalLayout resultLayout = new VerticalLayout();
		try {

			GtnUIFrameworkPagedTreeTableConfig tableConfig = componentConfig.getGtnPagedTreeTableConfig();

			if (tableConfig.getLeftHeader() != null) {
				this.configureLeftTablHeader(tableConfig, componentConfig.getSourceViewId());
			}
			if (tableConfig.getRightHeader() != null) {
				this.configureRightTableHeader(tableConfig, componentConfig.getSourceViewId());
			}

			GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();

			GtnUIFrameworkPagedTreeTableLogic tableLogic = getPagedTableLogicClass(componentConfig, componentData);

			FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
			for (String style : componentConfig.getComponentStyle()) {
				resultsTable.addStyleName(style);
			}

			// ConfigureTables
			initializeResultTable(resultsTable, tableConfig);
			configureTableHeaders(resultsTable, tableConfig);

			addFieldFactory(resultsTable, tableConfig, componentData);

			componentData.setCurrentComponentConfig(componentConfig);
			componentData.getCurrentComponentConfig().setGtnPagedTreeTableConfig(tableConfig);
			componentData.setCustomData(this);
			componentData.addCustomDataToList(resultsTable);
			componentData.addCustomDataToList(new HashSet<String>());
			componentData.addCustomDataToList(new HashSet<String>());

			resultsTable.getLeftFreezeAsTable().addStyleName("filterbar");
			resultsTable.getLeftFreezeAsTable().addStyleName("v-has-width");
			resultsTable.getLeftFreezeAsTable().addStyleName("v-table-filterbar");
			resultsTable.getLeftFreezeAsTable().addStyleName("table-header-normal");

			resultsTable.getRightFreezeAsTable().addStyleName("filterbar");
			resultsTable.getRightFreezeAsTable().addStyleName("v-has-width");
			resultsTable.getRightFreezeAsTable().addStyleName("v-table-filterbar");
			resultsTable.getRightFreezeAsTable().addStyleName("table-header-normal");

			resultLayout.setSizeFull();
			HorizontalLayout tableHorizontalLayout = new HorizontalLayout();
			tableHorizontalLayout.addComponent(resultsTable);
			tableHorizontalLayout.setSizeFull();
			resultLayout.addComponent(tableHorizontalLayout);
			resultLayout.addComponent(getResponsiveControls(tableLogic.createControls()));
			resultLayout.setData(componentData);
			gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkPagedTreeTableComponent");
		} catch (Exception exception) {
			gtnLogger.error("Exception while loading the table logic", exception);
			throw new GtnFrameworkGeneralException("Exception while loading the table logic", exception);
		}
		return resultLayout;
	}

	/**
	 * Initialize Result Table.
	 */
	protected void initializeResultTable(FreezePagedTreeTable resultsTable,
			GtnUIFrameworkPagedTreeTableConfig tableConfig) {
		resultsTable.markAsDirty();
		resultsTable.setSelectable(false);
		resultsTable.setSplitPosition(tableConfig.getSplitPosition(), Sizeable.Unit.PIXELS);
		resultsTable.setMinSplitPosition(tableConfig.getMinSplitPosition(), Sizeable.Unit.PIXELS);
		resultsTable.setMaxSplitPosition(tableConfig.getMaxSplitPosition(), Sizeable.Unit.PIXELS);
		resultsTable.addStyleName(ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE);
	}

	/**
	 * Configuration method to fire required tables headers
	 *
	 * @param resultsTable
	 * @param tableConfig
	 */
	@SuppressWarnings("rawtypes")
	protected void configureTableHeaders(FreezePagedTreeTable resultsTable,
			GtnUIFrameworkPagedTreeTableConfig tableConfig) {

		final ExtTreeContainer<GtnWsRecordBean> container = new ExtTreeContainer<>(GtnWsRecordBean.class,
				ExtContainer.DataStructureMode.LIST);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		List<Object> leftVisibleColumnList = Arrays.asList(tableConfig.getLeftTableColumnMappingId());
		List<Object> rightVisibleColumnList = Arrays.asList(tableConfig.getRightTableColumnMappingId());
		List<Object> recordHeader = new ArrayList<>();
		recordHeader.addAll(leftVisibleColumnList);
		recordHeader.addAll(rightVisibleColumnList);
		container.setRecordHeader(recordHeader);
		Map<Object, Class> dataType = new HashMap<>();
		for (int i = 0; i < recordHeader.size(); i++) {
			dataType.put(recordHeader.get(i).toString(), String.class);
		}
		container.setColumnProperties(dataType);
		tableLogic.setRecordHeader(recordHeader);
		tableLogic.setContainerDataSource(container);
		tableLogic.setItemsPerPage(tableConfig.getItemPerPage());
		tableLogic.setPageLength(tableConfig.getPageLength());

		List<String> leftHeaderList = Arrays.asList(tableConfig.getLeftTableVisibleHeader());
		List<String> rightHeaderList = Arrays.asList(tableConfig.getRightTableVisibleHeader());
		final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
		final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();

		leftTable.setImmediate(true);
		leftTable.reConstruct(true);
		resultsTable.setHeight(tableConfig.getTableHeight());
		leftTable.setHeight(tableConfig.getTableHeight());
		leftTable.setVisibleColumns(leftVisibleColumnList.toArray());
		leftTable.setColumnHeaders(leftHeaderList.toArray(new String[leftHeaderList.size()]));

		rightTable.setImmediate(true);
		rightTable.reConstruct(true);
		rightTable.setHeight(tableConfig.getTableHeight());
		rightTable.setVisibleColumns(rightVisibleColumnList.toArray());
		rightTable.setColumnHeaders(rightHeaderList.toArray(new String[rightHeaderList.size()]));
		if (tableConfig.isDoubleHeaderVisible()) {
			leftTable.setDoubleHeaderVisible(true);
			leftTable.setDoubleHeaderVisibleColumns(tableConfig.getLeftTableDoubleHeaderVisibleColumns().toArray());
			leftTable.setDoubleHeaderColumnHeaders(tableConfig.getLeftTableDoubleHeaderVisibleHeaders()
					.toArray(new String[tableConfig.getLeftTableDoubleHeaderVisibleHeaders().size()]));
			leftTable.setDoubleHeaderMap(tableConfig.getLeftTableDoubleHeaderMap());

			rightTable.setDoubleHeaderVisible(true);
			rightTable.setDoubleHeaderVisibleColumns(tableConfig.getRightTableDoubleHeaderVisibleColumns().toArray());
			rightTable.setDoubleHeaderColumnHeaders(tableConfig.getRightTableDoubleVisibleHeaders()
					.toArray(new String[tableConfig.getRightTableDoubleVisibleHeaders().size()]));
			rightTable.setDoubleHeaderMap(tableConfig.getRightTableDoubleHeaderMap());

			List<Object> doubleHeaderColumns = tableConfig.getRightTableDoubleHeaderVisibleColumns();
			for (Object doubleHeaderColumn : doubleHeaderColumns) {
				rightTable.setDoubleHeaderColumnCheckBox(doubleHeaderColumn, true);
				// rightTable.setDoubleHeaderColumnCheckBoxDisable(); need to
				// implement
			}
		}

	}

	private HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
		HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setStyleName("responsivePagedTable");

		HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
		HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

		CssLayout cssLayout = new CssLayout();
		cssLayout.setSizeFull();
		cssLayout.addComponent(pageSize.getComponent(0));
		cssLayout.addComponent(pageSize.getComponent(0));
		for (int index = 0; index < 8; index++) {
			cssLayout.addComponent(pageManagement.getComponent(0));
		}
		controlBar.addComponent(cssLayout);
		return controlBar;
	}

	public void reloadComponent(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId,
			String sourceComponentId) throws GtnFrameworkGeneralException {
		AbstractComponent resultLayoutInMap = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId, sourceComponentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayoutInMap.getData();
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableConfig tableConfig = componentData.getCurrentComponentConfig()
				.getGtnPagedTreeTableConfig();
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		formHeadersAndConfig(gtnUIFrameWorkActionConfig, tableConfig, resultsTable, tableLogic, componentData);
	}

	/**
	 * This is the method to form Dynamic Header based on the inputs. The input is
	 * List<List<String>>
	 *
	 * 1. Single Header - Visible column list 2. Single Header - Column Values 3.
	 * Double Header - Visible column list 4. Double Header - Column Values
	 *
	 * 4 List should be added in a single List and to be sent as an input to this
	 * method to form dynamic Headers
	 *
	 * @param configureInputList
	 * @throws GtnFrameworkGeneralException
	 */
	private void formHeadersAndConfig(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnUIFrameworkPagedTreeTableConfig tableConfig, FreezePagedTreeTable resultsTable,
			GtnUIFrameworkPagedTreeTableLogic tableLogic, GtnUIFrameworkComponentData componentData)
			throws GtnFrameworkGeneralException {
		try {

			GtnWsPagedTreeTableResponse leftTableHeaders = loadLeftHeader(gtnUIFrameWorkActionConfig, tableConfig,
					componentData.getComponentIdInMap());

			GtnWsPagedTreeTableResponse rightTableHeaders = loadRightHeader(gtnUIFrameWorkActionConfig, tableConfig,
					componentData.getComponentIdInMap());

			setHeaderConfig(leftTableHeaders, rightTableHeaders, resultsTable, tableLogic, componentData);
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

	private void addFieldFactory(FreezePagedTreeTable resultsTable, GtnUIFrameworkPagedTreeTableConfig tableConfig,
			GtnUIFrameworkComponentData componentData) {
		if (tableConfig.isLeftTableEditable()) {

			addTableFieldFactory(resultsTable.getLeftFreezeAsTable(),
					tableConfig.getLeftTableCustomFieldFactoryClassname(), tableConfig.getLeftTableEditableColumnList(),
					tableConfig.getLeftTableEditableComponentConfig(), componentData);
		}
		if (tableConfig.isRightTableEditable()) {

			addTableFieldFactory(resultsTable.getRightFreezeAsTable(),
					tableConfig.getRightTableCustomFieldFactoryClassname(),
					tableConfig.getRightTableEditableColumnList(), tableConfig.getRightTableEditableComponentConfig(),
					componentData);
		}
	}

	private void addTableFieldFactory(ExtFilterTreeTable resultTable, String customFieldFactoryClassname,
			List<String> editableColumnList, List<GtnUIFrameworkComponentConfig> editableField,
			GtnUIFrameworkComponentData componentData) {
		resultTable.setEditable(true);
		if (customFieldFactoryClassname == null) {
			resultTable.setTableFieldFactory(
					new GtnUIFrameworkCustomFieldFactory(editableColumnList, editableField, componentData));
		} else {
			try {
				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
				DefaultFieldFactory fieldFactory = (DefaultFieldFactory) classLoader
						.loadDynamicClass(customFieldFactoryClassname);
				if (fieldFactory instanceof GtnUIFrameworkCustomFieldFactory) {
					GtnUIFrameworkCustomFieldFactory tempFieldFactory = ((GtnUIFrameworkCustomFieldFactory) fieldFactory);
					tempFieldFactory.setEditableColumnList(editableColumnList);
					tempFieldFactory.setEditableComponentConfig(editableField);

				}
				resultTable.setTableFieldFactory(fieldFactory);
			} catch (GtnFrameworkGeneralException fieldFactoryException) {
				gtnLogger.error("Custom Field Factory : ", fieldFactoryException);
			}
		}
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		VerticalLayout resultLayout = (VerticalLayout) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayout.getData();
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		tableLogic.clearAll();
	}

	/**
	 * @param tableConfig
	 * @throws GtnFrameworkGeneralException
	 *             GtnUIFrameworkWebserviceRequest dummy request for initial load
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
		List<GtnUIFrameworkComponentConfig> rightfieldFactoryComponent = new ArrayList<>();
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
			GtnWsPagedTreeTableResponse rightTableHeaders, FreezePagedTreeTable resultsTable,
			GtnUIFrameworkPagedTreeTableLogic tableLogic, GtnUIFrameworkComponentData componentData) {

		configureDynamicTreeTableHeaders(resultsTable,
				componentData.getCurrentComponentConfig().getGtnPagedTreeTableConfig(), tableLogic, componentData,
				leftTableHeaders, rightTableHeaders);
	}

	public void configureDynamicTreeTableHeaders(FreezePagedTreeTable resultsTable,
			GtnUIFrameworkPagedTreeTableConfig tableConfig, GtnUIFrameworkPagedTreeTableLogic tableLogic,
			GtnUIFrameworkComponentData componentData, GtnWsPagedTreeTableResponse leftTableHeaders,
			GtnWsPagedTreeTableResponse rightTableHeaders) {
		initializeResultTable(resultsTable, tableConfig);
		loadHeaders(resultsTable, tableConfig, tableLogic, leftTableHeaders, rightTableHeaders, componentData);
		addFieldFactory(resultsTable, tableConfig, componentData);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadHeaders(FreezePagedTreeTable resultsTable, GtnUIFrameworkPagedTreeTableConfig tableConfig,
			GtnUIFrameworkPagedTreeTableLogic tableLogic, GtnWsPagedTreeTableResponse leftTableHeaders,
			GtnWsPagedTreeTableResponse rightTableHeaders, GtnUIFrameworkComponentData componentData) {

		ExtPagedTreeTable rightTable = resultsTable.getRightFreezeAsTable();

		final ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();

		ExtTreeContainer resultBeanContainer = new ExtTreeContainer<>(GtnWsRecordBean.class,
				ExtContainer.DataStructureMode.LIST);

		List<Object> recordHeader = new ArrayList<>();
		recordHeader.addAll(leftTableHeaders.getSingleColumns());
		recordHeader.addAll(rightTableHeaders.getSingleColumns());
		resultBeanContainer.setRecordHeader(recordHeader);
		Map<String, Class<?>> dataType = new HashMap<>();
		for (int i = 0; i < recordHeader.size(); i++) {
			dataType.put(recordHeader.get(i).toString(), String.class);
		}
		resultBeanContainer.setColumnProperties(dataType);
		tableLogic.setRecordHeader(recordHeader);
		tableLogic.setContainerDataSource(resultBeanContainer);
		tableLogic.sinkItemPerPageWithPageLength(false);
		tableLogic.setTreeNodeMultiClick(false);
		tableLogic.setItemsPerPage(tableConfig.getItemPerPage());
		tableLogic.setPageLength(tableConfig.getPageLength());

		leftTable.setImmediate(true);
		leftTable.reConstruct(true);
		leftTable.setVisibleColumns(leftTableHeaders.getSingleColumns().toArray());
		leftTable.setColumnHeaders(
				leftTableHeaders.getSingleHeaders().toArray(new String[leftTableHeaders.getSingleHeaders().size()]));

		for (String column : tableConfig.getCheckBoxVisibleColoumn()) {
			leftTable.setColumnCheckBox(column, true, false);
		}
		if (tableConfig.getCheckBoxVisibleColoumn() != null && !tableConfig.getCheckBoxVisibleColoumn().isEmpty()) {
			leftTable.setData(new GtnUIFrameworkActionParameter());
			addCheckAllListener(leftTable, tableConfig, componentData);
		}
		leftTable.setDoubleHeaderVisible(true);
		leftTable.setDoubleHeaderVisibleColumns(leftTableHeaders.getDoubleColumns().toArray());
		leftTable.setDoubleHeaderColumnHeaders(
				leftTableHeaders.getDoubleHeaders().toArray(new String[leftTableHeaders.getDoubleHeaders().size()]));
		leftTable.setDoubleHeaderMap(leftTableHeaders.getDoubleHeaderMaps());

		rightTable.setImmediate(true);
		rightTable.reConstruct(true);
		rightTable.setVisibleColumns(rightTableHeaders.getSingleColumns().toArray());
		rightTable.setColumnHeaders(
				rightTableHeaders.getSingleHeaders().toArray(new String[rightTableHeaders.getSingleHeaders().size()]));
		rightTable.setDoubleHeaderVisible(true);
		rightTable.setDoubleHeaderVisibleColumns(rightTableHeaders.getDoubleColumns().toArray());
		rightTable.setDoubleHeaderColumnHeaders(
				rightTableHeaders.getDoubleHeaders().toArray(new String[rightTableHeaders.getDoubleHeaders().size()]));
		rightTable.setDoubleHeaderMap(rightTableHeaders.getDoubleHeaderMaps());

		List<Object> doubleHeaderColumns = rightTableHeaders.getDoubleColumns();
		for (Object doubleHeaderColumn : doubleHeaderColumns) {
			rightTable.setDoubleHeaderColumnCheckBox(doubleHeaderColumn, true);
		}
		List<GtnUIFrameworkComponentConfig> rightfieldFactoryComponent = new ArrayList<>();
		for (String tableFieldFactoryColumns : rightTableHeaders.getEditableFields()) {
			GtnUIFrameworkComponentConfig fieldFactoryInputComponentConfig = new GtnUIFrameworkComponentConfig();
			fieldFactoryInputComponentConfig.setComponentId(tableFieldFactoryColumns);
			fieldFactoryInputComponentConfig.setComponentName(tableFieldFactoryColumns.toUpperCase(Locale.ENGLISH));
			fieldFactoryInputComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
			fieldFactoryInputComponentConfig
					.setGtnUIFrameWorkValueChangeActionConfigList(tableConfig.getComponentconfigActionlist());
			rightfieldFactoryComponent.add(fieldFactoryInputComponentConfig);
		}
		tableConfig.setRightTableEditableColumnList(rightTableHeaders.getEditableFields());
		tableConfig.setRightTableEditableComponentConfig(rightfieldFactoryComponent);

	}

	private void addCheckAllListener(final ExtPagedTreeTable<?> leftTable,
			GtnUIFrameworkPagedTreeTableConfig tableConfig, GtnUIFrameworkComponentData pagedTreeTableComponentData) {
		leftTable.addColumnCheckListener(
				new GtnUIFrameworkPagedTreeTableColumnListener(tableConfig, leftTable, pagedTreeTableComponentData));
	}

}
