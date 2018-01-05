package com.stpl.gtn.gtn2o.ui.framework.component.table.datatable;

import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkCustomFieldFactory;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIFrameworkDataTableColumnCheckListener;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkDateFormatConverter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableFilterGenerator;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.VerticalLayout;

public class GtnUIFrameworkTableCompnent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTabSheetComponent.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkTableCompnent");
		final ExtContainer container = new ExtContainer(GtnWsRecordBean.class, ExtContainer.DataStructureMode.LIST);
		final ExtFilterTable resultTable = new ExtFilterTable();
		VerticalLayout resultLayout = new VerticalLayout();
		final GtnUIFrameworkPagedTableConfig tableConfig = componentConfig.getGtnPagedTableConfig();
		for (String style : componentConfig.getComponentStyle()) {
			resultTable.addStyleName(style);
		}
		resultTable.setPageLength(tableConfig.getPageLength());
		resultTable.setFilterBarVisible(tableConfig.isFilterBar());
		resultTable.setSelectable(tableConfig.isSelectable());
		configureTableStyle(componentConfig, resultTable);
		setTableCaption(tableConfig, resultTable, componentConfig);
		resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
		addCustomFilterToTable(tableConfig, resultTable);
		List<Object> visibleColumnList = Arrays.asList(tableConfig.getTableColumnMappingId());

		List<Integer> dateColumn = new ArrayList<>();
		Map<String, Class<?>> dataType = getColumnProperties(tableConfig, visibleColumnList, dateColumn);
		GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
		resultTable.setEditable(tableConfig.isEditable());
		resultTable.setVisible(componentConfig.isVisible());
		resultTable.setMultiSelect(tableConfig.isMultiSelect());
		setFieldFactory(resultTable, tableConfig, componentData);
		if (tableConfig.getCheckAllColumnList() != null && !tableConfig.getCheckAllColumnList().isEmpty()) {
			for (String columnId : tableConfig.getCheckAllColumnList()) {
				resultTable.setColumnCheckBox(columnId, true, false);
			}
			resultTable.addColumnCheckListener(new GtnUIFrameworkDataTableColumnCheckListener(componentData));
		}

		if (componentConfig.getGtnPagedTableConfig().isItemClickListener()) {
			resultTable.addItemClickListener(new ItemClickListener(componentConfig));
		}

		componentData.setCustomData(resultTable);
		container.setColumnProperties(dataType);
		container.setRecordHeader(getRecordHeader(tableConfig, visibleColumnList));
		resultTable.setContainerDataSource(container);
		resultTable.setVisibleColumns(visibleColumnList.toArray());
		resultTable.setColumnHeaders(tableConfig.getTableVisibleHeader());
		resultLayout.setSizeFull();
		resultLayout.addComponent(resultTable);
		resultLayout.setData(componentData);
		if (tableConfig.getDefaultDateFormat() != null) {
			GtnUIFrameworkDateFormatConverter dataFormatConverter = new GtnUIFrameworkDateFormatConverter(
					tableConfig.getDefaultDateFormat());
			for (Object propertyId : resultTable.getContainerPropertyIds()) {
				if (String.valueOf(propertyId).contains("Date")) {
					resultTable.setConverter(propertyId, dataFormatConverter);
				}
			}
		}
		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkTableCompnent");

		return resultLayout;
	}

	private void setFieldFactory(ExtFilterTable resultTable, GtnUIFrameworkPagedTableConfig tableConfig,
			GtnUIFrameworkComponentData componentData) {
		if (tableConfig.isEditable()) {
			if (tableConfig.getCustomFieldFactoryClassname() == null) {
				resultTable.setTableFieldFactory(new GtnUIFrameworkCustomFieldFactory(
						tableConfig.getEditableColumnList(), tableConfig.getEditableField(), componentData));
			} else {
				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
				try {
					resultTable.setTableFieldFactory((DefaultFieldFactory) classLoader
							.loadDynamicClass(tableConfig.getCustomFieldFactoryClassname()));
				} catch (GtnFrameworkGeneralException fieldFactoryException) {
					gtnLogger.error("Custom Field Factory : ", fieldFactoryException);
				}
			}
		}
	}

	private void configureTableStyle(GtnUIFrameworkComponentConfig componentConfig, final ExtFilterTable resultTable) {
		resultTable.setWidth(componentConfig.getComponentWidth());
		resultTable.setHeight(componentConfig.getComponentHight());
		resultTable.addStyleName(GtnFrameworkCssConstants.FILTERBAR);
		resultTable.addStyleName(GtnFrameworkCssConstants.V_HAS_WIDTH);
		resultTable.addStyleName(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		resultTable.addStyleName(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
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

	private Map<String, Class<?>> getColumnProperties(GtnUIFrameworkPagedTableConfig tableConfig,
			List<Object> visibleColumnList, List<Integer> dateColumn) {
		Map<String, Class<?>> dataType = new HashMap<>();
		addColumnProperties(visibleColumnList, dateColumn, dataType, tableConfig.getTableColumnDataType());
		generateForExtraColumn(tableConfig, dataType, dateColumn);
		return dataType;
	}

	private void generateForExtraColumn(GtnUIFrameworkPagedTableConfig tableConfig, Map<String, Class<?>> dataType,
			List<Integer> dateColumn) {
		if (tableConfig.getExtraColumn() != null && tableConfig.getExtraColumnDataType() != null) {
			if (tableConfig.getExtraColumn().length != tableConfig.getExtraColumnDataType().length) {
				throw new IllegalArgumentException("Extracolumn and ExtraColumn data types are not equal");
			}
			addColumnProperties(Arrays.asList(tableConfig.getExtraColumn()), dateColumn, dataType,
					tableConfig.getExtraColumnDataType());
		}

	}

	private void addColumnProperties(List<Object> visibleColumnList, List<Integer> dateColumn,
			Map<String, Class<?>> dataType, Class<?>[] dataTypeArray) {
		for (int i = 0; i < dataTypeArray.length; i++) {

			if (Date.class.equals(dataTypeArray[i])) {
				dateColumn.add(i);
			}
			dataType.put(visibleColumnList.get(i).toString(), dataTypeArray[i]);

		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

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
				if (event.isDoubleClick()) {
					if (componentConfig.getGtnPagedTableConfig().getItemDoubleClickActionConfigList() != null) {
						for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnPagedTableConfig()
								.getItemDoubleClickActionConfigList()) {
							final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
							action.configureParams(actionConfig);
							action.doAction(componentConfig.getComponentId(), actionConfig);
						}
					}
					return;
				}
				if (componentConfig.getGtnPagedTableConfig().getItemClickActionConfigList() != null) {
					for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnPagedTableConfig()
							.getItemClickActionConfigList()) {
						final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
						actionConfig.setActionParameter(new GtnUIFrameworkActionParameter());
						actionConfig.getActionParameter().setItemId((GtnWsRecordBean) event.getItemId());
						action.configureParams(actionConfig);
						action.doAction(componentConfig.getComponentId(), actionConfig);
					}
				}
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage());
			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			baseComponent.resetFilter();
			baseComponent.removeAllContainerFilters();
			baseComponent.setTableValue(null);
			baseComponent.removeAllGridItems();
		} catch (GtnFrameworkValidationFailedException ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}
	}

	private void addCustomFilterToTable(GtnUIFrameworkPagedTableConfig tableConfig, ExtFilterTable resultTable) {
		if (tableConfig.getCustomFilterConfigMap() != null) {
			resultTable.setFilterGenerator(new GtnUIFrameworkPagedTableFilterGenerator(tableConfig));
		}
	}

	private void setTableCaption(GtnUIFrameworkPagedTableConfig tableConfig, ExtFilterTable resultTable,
			GtnUIFrameworkComponentConfig componentConfig) {
		if (tableConfig.isCaptionVisible()) {
			resultTable.setCaption(componentConfig.getComponentName());
		}
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkGlobalUI.tablePostCreateAction(componentConfig);
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(e.getMessage());
		}

	}

}
