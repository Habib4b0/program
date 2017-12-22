/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable;

import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkDateFormatConverter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CssLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkNewPagedTreeTableComponent implements GtnUIFrameworkComponent {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkNewPagedTreeTableComponent.class);

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkNewPagedTreeTableComponent");
		VerticalLayout resultLayout = new VerticalLayout();
		try {
			GtnUIFrameworkNewTableConfig tableConfig = componentConfig.getGtnNewTableConfig();
			GtnUIFrameworkNewPagedTableConfig pagedTableConfig = componentConfig.getGtnNewPagedTableConfig();
			GtnUIFrameworkNewPagedTreeTableLogic tableLogic = new GtnUIFrameworkNewPagedTreeTableLogic();

			tableLogic.setComponentConfig(componentConfig);
			ExtTreeContainer<GtnWsRecordBean> container = new ExtTreeContainer<>(GtnWsRecordBean.class,
					ExtContainer.DataStructureMode.LIST);
			ExtPagedTreeTable newPagedTreeTable = new ExtPagedTreeTable(tableLogic);
			tableLogic.setControlTable(newPagedTreeTable);
			for (String style : componentConfig.getComponentStyle()) {
				newPagedTreeTable.addStyleName(style);
			}
			GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
			componentData.setNewPageTableLogic(tableLogic);
			componentData.setCustomData(newPagedTreeTable);

			newPagedTreeTable.setWidth(componentConfig.getComponentWidth());
			if (componentConfig.getComponentHight() != null) {
				newPagedTreeTable.setHeight(componentConfig.getComponentHight());
			}
			newPagedTreeTable.setPageLength(tableConfig.getPageLength());
			newPagedTreeTable.setItemsPerPage(pagedTableConfig.getItemPerPage());
			tableLogic.setTempPageLength(tableConfig.getPageLength());
			newPagedTreeTable.setEditable(tableConfig.isEditable());
			newPagedTreeTable.setMultiSelect(tableConfig.isMultiSelect());
			newPagedTreeTable.setImmediate(true);
			newPagedTreeTable.addStyleName(GtnFrameworkCssConstants.FILTERBAR);
			newPagedTreeTable.addStyleName(GtnFrameworkCssConstants.V_HAS_WIDTH);
			newPagedTreeTable.addStyleName(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
			newPagedTreeTable.addStyleName(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
			newPagedTreeTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

			List<Object> visibleColumnList = Arrays.asList(tableConfig.getTableColumnMappingId());
			if (tableConfig.getRecordTypeManageActionConfig() == null) {
				GtnUIFrameWorkActionConfig recordTypeManageActionConfig = new GtnUIFrameWorkActionConfig();
				recordTypeManageActionConfig.setActionType(GtnUIFrameworkActionType.MANAGE_TABLE_RECORD_TYPE_ACTION);
				recordTypeManageActionConfig.addActionParameter(componentConfig.getComponentId());
				tableConfig.setRecordTypeManageActionConfig(recordTypeManageActionConfig);
			}
			Map<Object, Class> dataType = new HashMap<>();
			for (int i = 0; i < tableConfig.getTableColumnDataType().length; i++) {
				dataType.put(visibleColumnList.get(i).toString(), tableConfig.getTableColumnDataType()[i]);
			}

			container.setColumnProperties(dataType);
			container.setRecordHeader(visibleColumnList);
			tableLogic.setRecordHeader(visibleColumnList);
			tableLogic.setContainerDataSource(container);
			newPagedTreeTable.setVisibleColumns(visibleColumnList.toArray());
			newPagedTreeTable.setColumnHeaders(tableConfig.getTableVisibleHeader());
			newPagedTreeTable.setFilterBarVisible(tableConfig.isFilterBar());
			newPagedTreeTable.setSelectable(tableConfig.isSelectable());
			for (Object propertyId : newPagedTreeTable.getVisibleColumns()) {
				newPagedTreeTable.setColumnWidth(propertyId, -1);
			}

			resultLayout.setSizeFull();
			CssLayout tableCssLayout = new CssLayout();
			tableCssLayout.addComponent(newPagedTreeTable);
			tableCssLayout.setSizeFull();
			tableLogic.setPageLength(pagedTableConfig.getItemPerPage());
			tableLogic.setItemsPerPage(tableConfig.getPageLength());
			tableLogic.sinkItemPerPageWithPageLength(pagedTableConfig.isSinkItemPerPageWithPageLength());
			resultLayout.addComponent(tableCssLayout);
			HorizontalLayout pageHorizontalLayout = tableLogic.createControls();
			pageHorizontalLayout.addStyleName("responsivePagedTable");
			resultLayout.addComponent(pageHorizontalLayout);

			GtnUIFrameworkDateFormatConverter dataFormatConverter = new GtnUIFrameworkDateFormatConverter("MM/dd/yyyy");
			for (Object propertyId : newPagedTreeTable.getContainerPropertyIds()) {
				if (String.valueOf(propertyId).contains("Date")) {
					newPagedTreeTable.setConverter(propertyId, dataFormatConverter);
				}
			}

			resultLayout.setData(componentData);
			gtnLogger.info("End the buildVaadinComponent() of GtnUIFrameworkNewPagedTreeTableComponent");
		} catch (Exception exception) {
			gtnLogger.error("Exception while building GtnUIFrameworkNewPagedTreeTableComponent", exception);
			throw new GtnFrameworkGeneralException("Exception while building GtnUIFrameworkNewPagedTreeTableComponent",
					exception);
		}
		return resultLayout;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);
		GtnUIFrameworkNewPagedTreeTableLogic tableLogic = componentData.getNewPageTableLogic();
		tableLogic.setExtraParameter(null);
		tableLogic.resetValues();
		tableLogic.resetFilter();
		tableLogic.setReset(false);
		tableLogic.clearAll();
		tableLogic.resetSearchCriteriaList();
	}

	public List<GtnWebServiceSearchCriteria> getPagedTableSearchCriteriaList(
			GtnUIFrameworkComponentData componentData) {
		GtnUIFrameworkNewPagedTreeTableLogic tableLogic = componentData.getNewPageTableLogic();
		return tableLogic.getSearchCriteriaList(tableLogic.getFilters());
	}

}
