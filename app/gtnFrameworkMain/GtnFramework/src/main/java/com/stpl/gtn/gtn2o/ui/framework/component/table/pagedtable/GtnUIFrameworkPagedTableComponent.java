package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable;

import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkCustomFieldFactory;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkGeneratedColumn;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIFrameworkTableColumnCheckListener;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableFilterGenerator;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.formater.IntegerFormatConverter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CssLayout;
import org.asi.ui.extfilteringtable.ExtCustomTable.Align;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;

public class GtnUIFrameworkPagedTableComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedTableComponent.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkPagedTableComponent");
		VerticalLayout resultLayout = new VerticalLayout();
		try {
			GtnUIFrameworkPagedTableConfig tableConfig = componentConfig.getGtnPagedTableConfig();
			GtnUIFrameworkPagedTableLogic tableLogic = getPagedTableLogicClass(componentConfig);

			if (tableConfig.getTableColumnDataTypeURL() != null) {
				loadColumnDataTypeFromWS(tableConfig);
			}

			tableLogic.setComponentConfig(componentConfig);
			final ExtContainer container = new ExtContainer(GtnWsRecordBean.class, ExtContainer.DataStructureMode.LIST);
			final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

			if (tableConfig.isCaptionVisible()) {
				resultTable.setCaption(componentConfig.getComponentName());
			}
			for (String style : componentConfig.getComponentStyle()) {
				resultTable.addStyleName(style);
			}
			GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
			componentData.setCurrentPageTableLogic(tableLogic);
			componentData.setCustomData(resultTable);

			resultTable.setWidth(componentConfig.getComponentWidth());
			if (componentConfig.getComponentHight() != null) {
				resultTable.setHeight(componentConfig.getComponentHight());
			}
			resultTable.setSortEnabled(tableConfig.isSortingEnable());
			resultTable.setDragMode(tableConfig.getDragMode().getDragMode());
			resultTable.setPageLength(tableConfig.getPageLength());
			resultTable.setItemsPerPage(tableConfig.getItemPerPage());
			tableLogic.sinkItemPerPageWithPageLength(tableConfig.isSinkItemPerPageWithPageLength());
			tableLogic.setTempPageLength(tableConfig.getPageLength());
			resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
			resultTable.setFilterGenerator(new GtnUIFrameworkPagedTableFilterGenerator(tableConfig));
			resultTable.setEditable(tableConfig.isEditable());
			resultTable.setMultiSelect(tableConfig.isMultiSelect());
			setFieldFactoryProperties(tableConfig, resultTable, componentData);
			resultTable.setImmediate(true);
			resultTable.addStyleName(GtnFrameworkCssConstants.FILTERBAR);
			resultTable.addStyleName(GtnFrameworkCssConstants.V_HAS_WIDTH);
			resultTable.addStyleName(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
			resultTable.addStyleName(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
			resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

			List<Object> visibleColumnList = Arrays.asList(tableConfig.getTableColumnMappingId());
			if (tableConfig.getRecordTypeManageActionConfig() == null) {
				GtnUIFrameWorkActionConfig recordTypeManageActionConfig = new GtnUIFrameWorkActionConfig();
				recordTypeManageActionConfig.setActionType(GtnUIFrameworkActionType.MANAGE_TABLE_RECORD_TYPE_ACTION);
				recordTypeManageActionConfig.addActionParameter(componentConfig.getComponentId());
				tableConfig.setRecordTypeManageActionConfig(recordTypeManageActionConfig);
			}
			List<Integer> dateColumn = new ArrayList<>();
			Map<String, Class<?>> dataType = getColumnProperties(tableConfig, visibleColumnList, dateColumn);
			container.setColumnProperties(dataType);
			container.setRecordHeader(getRecordHeader(tableConfig, visibleColumnList));
			tableLogic.setRecordHeader((List<Object>) getRecordHeader(tableConfig, visibleColumnList));
			tableLogic.setContainerDataSource(container);
			tableLogic.setDateColumn(dateColumn);
			resultTable.setVisibleColumns(visibleColumnList.toArray());
			resultTable.setColumnHeaders(tableConfig.getTableVisibleHeader());
			resultTable.setFilterBarVisible(tableConfig.isFilterBar());
			resultTable.setSelectable(tableConfig.isSelectable());
			for (Object propertyId : resultTable.getVisibleColumns()) {
				resultTable.setColumnWidth(propertyId, -1);
			}
			setInvisibleFilterPropertyIds(tableConfig, resultTable);
			setTableColumnAlignment(tableConfig, resultTable);
			setDoubleHeaders(tableConfig, resultTable);
			resultLayout.setSizeFull();
			CssLayout tableCssLayout = new CssLayout();
			tableCssLayout.addComponent(resultTable);
			tableCssLayout.setSizeFull();
			tableLogic.setPageLength(tableConfig.getItemPerPage());
			tableLogic.setItemsPerPage(tableConfig.getPageLength());
			tableLogic.sinkItemPerPageWithPageLength(tableConfig.isSinkItemPerPageWithPageLength());
			resultLayout.addComponent(tableCssLayout);
			HorizontalLayout pageHorizontalLayout = tableLogic.createControls();
			pageHorizontalLayout.addStyleName("responsivePagedTable");
			resultLayout.addComponent(pageHorizontalLayout);
			tableLogic.setCountUrl(tableConfig.getCountUrl());
			tableLogic.setResultSetUrl(tableConfig.getResultSetUrl());
			if (!tableConfig.getColumnCheckBoxId().isEmpty()) {
				resultTable.setColumnCheckBox(tableConfig.getColumnCheckBoxId(), true, false);
			}
			if (tableConfig.getColumnCheckActionConfigList() != null
					&& !tableConfig.getColumnCheckActionConfigList().isEmpty()) {
				resultTable.addColumnCheckListener(
						new GtnUIFrameworkTableColumnCheckListener(componentConfig.getComponentId()));
			}
			addTableClickListener(tableConfig, resultTable, componentConfig);
			resultTable.addValueChangeListener(new TableValueChangeListener(componentConfig));
			setDefaultDateFormat(tableConfig, resultTable, visibleColumnList);
			setCustomDateFormat(tableConfig, resultTable, visibleColumnList);
			if (tableConfig.getIntegerFormatPropertyList() != null
					&& !tableConfig.getIntegerFormatPropertyList().isEmpty()) {
				for (String propertyId : tableConfig.getIntegerFormatPropertyList()) {
					resultTable.setConverter(propertyId, IntegerFormatConverter.getConverter());
				}
			}

			resultLayout.setData(componentData);
			gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkPagedTableComponent");
		} catch (Exception exception) {
			gtnLogger.error("Exception while loading the table logic", exception);
			throw new GtnFrameworkGeneralException("Exception while loading the table logic", exception);
		}
		return resultLayout;
	}

	private Map<String, Class<?>> getColumnProperties(GtnUIFrameworkPagedTableConfig pagedTableConfig,
			List<Object> visibleColumnList, List<Integer> dateColumn) {
		Map<String, Class<?>> dataType = new HashMap<>();
		addColumnProperties(visibleColumnList, dateColumn, dataType, pagedTableConfig.getTableColumnDataType());
		generateForExtraColumn(pagedTableConfig, dataType, dateColumn);
		return dataType;
	}

	private void generateForExtraColumn(GtnUIFrameworkPagedTableConfig pagedTableConfig, Map<String, Class<?>> dataType,
			List<Integer> dateColumn) {
		if (pagedTableConfig.getExtraColumn() != null && pagedTableConfig.getExtraColumnDataType() != null) {
			if (pagedTableConfig.getExtraColumn().length != pagedTableConfig.getExtraColumnDataType().length) {
				throw new IllegalArgumentException("Extracolumn and ExtraColumn data types are not equal");
			}
			addColumnProperties(Arrays.asList(pagedTableConfig.getExtraColumn()), dateColumn, dataType,
					pagedTableConfig.getExtraColumnDataType());
		}

	}

	private void addColumnProperties(List<Object> pagedTableVisibleColumnList, List<Integer> dateColumn,
			Map<String, Class<?>> dataType, Class<?>[] dataTypeArray) {
		for (int i = 0; i < dataTypeArray.length; i++) {
			if (Date.class.equals(dataTypeArray[i])) {
				dateColumn.add(i);
			}
			dataType.put(pagedTableVisibleColumnList.get(i).toString(), dataTypeArray[i]);
		}
	}

	private List<?> getRecordHeader(GtnUIFrameworkPagedTableConfig tableConfig, List<Object> visibleColumnList) {
		if (validateExtraColumnProperties(tableConfig)) {
			List<Object> recordHeader = new ArrayList<>(visibleColumnList);
			recordHeader.addAll(Arrays.asList(tableConfig.getExtraColumn()));
			return recordHeader;
		}
		return visibleColumnList;
	}

	private boolean validateExtraColumnProperties(GtnUIFrameworkPagedTableConfig tableConfig) {
		return tableConfig.getExtraColumn() != null && tableConfig.getExtraColumn().length > 0
				&& tableConfig.getExtraColumnDataType() != null
				&& tableConfig.getExtraColumnDataType().length == tableConfig.getExtraColumn().length;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	private GtnUIFrameworkPagedTableLogic getPagedTableLogicClass(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableLogic tableLogic;
		if (componentConfig.getPagedTableLogicClassName() == null) {
			tableLogic = new GtnUIFrameworkPagedTableLogic();
		} else {
			GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
			tableLogic = (GtnUIFrameworkPagedTableLogic) classLoader
					.loadDynamicClass(componentConfig.getPagedTableLogicClassName());
		}
		return tableLogic;
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkGlobalUI.tablePostCreateAction(componentConfig);
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(e.getMessage());
		}
	}

	class ItemClickListener implements ItemClickEvent.ItemClickListener {

		private static final long serialVersionUID = 1L;
		private GtnUIFrameworkComponentConfig componentConfig;

		public ItemClickListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void itemClick(ItemClickEvent event) {
			try {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnUIFrameWorkActionConfigList()) {
					final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
					GtnUIFrameworkActionParameter actionParameter = new GtnUIFrameworkActionParameter();
					List<Object> actionParameterList = new ArrayList<>(actionConfig.getActionParameterList());
					actionParameter.setItemId((GtnWsRecordBean) event.getItemId());
					actionParameterList.add(event.getItemId());
					actionConfig.setActionParameter(actionParameter);
					actionConfig.setActionParameterList(actionParameterList);
					action.configureParams(actionConfig);
					action.doAction(componentConfig.getComponentId(), actionConfig);
				}
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	class ItemDoubleClickListener implements ItemClickEvent.ItemClickListener {

		private static final long serialVersionUID = 1L;
		private GtnUIFrameworkComponentConfig componentConfig;

		public ItemDoubleClickListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void itemClick(ItemClickEvent event) {
			try {
				if (componentConfig.getGtnPagedTableConfig().isDoubleClickEnable() && (event.isDoubleClick())) {
					for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnPagedTableConfig()
							.getGtnUIFrameWorkActionConfigList()) {
						final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
						actionConfig.addActionParameter(event.getItemId());
						action.configureParams(actionConfig);
						action.doAction(componentConfig.getComponentId(), actionConfig);
					}
				}

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	class TableValueChangeListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = 1L;
		private GtnUIFrameworkComponentConfig componentConfig;

		public TableValueChangeListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event) {
			try {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig
						.getGtnUIFrameWorkValueChangeActionConfigList()) {
					GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
					actionConfig.addActionParameter(event.getProperty().getValue());
					action.configureParams(actionConfig);
					action.doAction(componentConfig.getComponentId(), actionConfig);
					// preventing addition of value in ActionParameterList again
					// and again
					actionConfig.removeActionParameter(event.getProperty().getValue());
				}

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			GtnUIFrameworkPagedTableLogic tableLogic = baseComponent.getComponentData().getCurrentPageTableLogic();
			tableLogic.setExtraParameter(null);
			tableLogic.resetValues();
			tableLogic.startSearchProcess(null, null, Boolean.FALSE);
			if (componentConfig.getGtnPagedTableConfig().getInvisibleFilterPropertyIds() != null) {
				for (Object propertyId : componentConfig.getGtnPagedTableConfig().getInvisibleFilterPropertyIds()) {
					baseComponent.setFilterFieldVisible(propertyId, false);
				}
			}
			tableLogic.setCurrentSearchCriteria(null);
			tableLogic.setAdditioanlSearchCriteriaList(null);
		} catch (GtnFrameworkValidationFailedException ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}

	}

	private void loadColumnDataTypeFromWS(GtnUIFrameworkPagedTableConfig tableConfig) {
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setModuleName(String.valueOf(GtnUIFrameworkGlobalUI.getCurrentSessionBean()
				.getSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME)));
		gtnUIFrameworkWebserviceRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnWsSecurityToken gtnWsSecurityToken = new GtnWsSecurityToken();
		gtnWsSecurityToken.setUserId(GtnUIFrameworkGlobalUI.getCurrentSessionBean().getUserId());
		gtnWsSecurityToken.setSessionId(GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId());
		gtnLogger.info("--url-->" + GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
				+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETDATATYPE_SERVICE);
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
						+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETDATATYPE_SERVICE,
				gtnUIFrameworkWebserviceRequest, gtnWsSecurityToken);

		Map<String, Class<?>> columnDataTypeMap = response.getGtnWsTransactionResponse().getColumnDataTypeMap();
		List<Class<?>> columnDataType = new ArrayList<>();
		for (Object object : tableConfig.getTableColumnMappingId()) {
			columnDataType.add(object != null && columnDataTypeMap.get(String.valueOf(object)) != null
					? columnDataTypeMap.get(String.valueOf(object)) : String.class);
		}
		tableConfig.setTableColumnDataType(columnDataType.toArray(new Class<?>[columnDataType.size()]));
	}

	public List<GtnWebServiceSearchCriteria> getPagedTableSearchCriteriaList(
			GtnUIFrameworkComponentData componentData) {
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		return tableLogic.getSearchCriteriaList(tableLogic.getFilters());
	}

	public void setFieldFactoryProperties(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable,
			GtnUIFrameworkComponentData componentData) {
		if (tableConfig.isEditable()) {
			if (tableConfig.getCustomFieldFactoryClassname() == null) {
				resultTable.setTableFieldFactory(new GtnUIFrameworkCustomFieldFactory(
						tableConfig.getEditableColumnList(), tableConfig.getEditableField(), componentData));
			}

			if (tableConfig.getGeneratedColumnList() != null && !tableConfig.getGeneratedColumnList().isEmpty()) {
				for (int i = 0; i < tableConfig.getGeneratedColumnList().size(); i++) {
					resultTable.addGeneratedColumn(tableConfig.getGeneratedColumnList().get(i),
							new GtnUIFrameworkGeneratedColumn(tableConfig.getGeneratedColumn().get(i), componentData));
				}

			}
		}
	}

	private void setTableColumnAlignment(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable) {
		if (tableConfig.getColumnAlignment() != null) {
			for (int i = 0; i < tableConfig.getColumnToAlign().length; i++) {
				resultTable.setColumnAlignment(tableConfig.getColumnToAlign()[i],
						Align.valueOf(tableConfig.getColumnAlignment()[i]));
			}

		}
	}

	private void setDoubleHeaders(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable) {
		if (tableConfig.isDoubleHeaderVisible()) {
			resultTable.setDoubleHeaderVisible(true);
			resultTable.setDoubleHeaderVisibleColumns(tableConfig.getTableDoubleHeaderVisibleColumns());
			resultTable.setDoubleHeaderColumnHeaders(tableConfig.getTableDoubleHeaderVisibleHeaders());
			resultTable.setDoubleHeaderMap(tableConfig.getTableDoubleHeaderMap());

			for (Object Header : tableConfig.getDoubleHeaderObject()) {
				for (String alignment : tableConfig.getDoubleHeaderColumnAlignment()) {
					resultTable.setDoubleHeaderColumnAlignment(Header, Align.valueOf(alignment));
				}
			}
		}
	}

	private void setInvisibleFilterPropertyIds(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable) {
		if (tableConfig.getInvisibleFilterPropertyIds() != null) {
			for (Object propertyId : tableConfig.getInvisibleFilterPropertyIds()) {
				resultTable.setFilterFieldVisible(propertyId, false);
			}
		}
	}

	private void addTableClickListener(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable,
			GtnUIFrameworkComponentConfig componentConfig) {
		if (tableConfig.isItemClickListener()) {
			resultTable.addItemClickListener(new ItemClickListener(componentConfig));
		}
		if (tableConfig.isDoubleClickEnable()) {
			resultTable.addItemClickListener(new ItemDoubleClickListener(componentConfig));
		}
	}

	private void setDefaultDateFormat(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable,
			List<Object> visibleColumnList) {
		if (tableConfig.getDefaultDateFormat() != null) {
			GtnUIFrameworkDateFormatConverter dataFormatConverter = new GtnUIFrameworkDateFormatConverter(
					tableConfig.getDefaultDateFormat());
			for (int i = 0; i < tableConfig.getTableColumnDataType().length; i++) {
				if (Date.class.equals(tableConfig.getTableColumnDataType()[i])) {

					resultTable.setConverter(visibleColumnList.get(i), dataFormatConverter);
				}
			}
		}

	}

	private void setCustomDateFormat(GtnUIFrameworkPagedTableConfig tableConfig, ExtPagedTable resultTable,
			List<Object> visibleColumnList) {
		if (tableConfig.getTableDateColumnFormat() != null && tableConfig.getTableDateColumnObject() != null) {
			String[] dateFormat = tableConfig.getTableDateColumnFormat();
			String[] dateObject = tableConfig.getTableDateColumnObject();
			if (dateFormat.length == dateObject.length) {
				for (int i = 0; i < dateFormat.length; i++) {
					GtnUIFrameworkDateFormatConverter dataFormatConverter = new GtnUIFrameworkDateFormatConverter(
							dateFormat[i]);
					if (Date.class
							.equals(tableConfig.getTableColumnDataType()[visibleColumnList.indexOf(dateObject[i])])) {

						resultTable.setConverter(dateObject[i], dataFormatConverter);
					}
				}
			}
		}
	}

}
