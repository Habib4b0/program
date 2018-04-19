/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.pagedgrid;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.QueryBean;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.config.PagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkPagedGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedGridComponent.class);
    @Override
    public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) throws GtnFrameworkGeneralException {
 
        VerticalLayout resultLayout = new VerticalLayout();
        GtnUIFrameworkPagedTableConfig tableConfig = componentConfig.getGtnPagedTableConfig();
//        PagedTableConfig pagedTableConfig = new PagedTableConfig();

//        String columns[] = Arrays.stream(tableConfig.getTableColumnMappingId()).map(Object::toString).toArray(String[]::new);
//        pagedTableConfig.setVisibleColumns(Arrays.asList(columns));
        
        
//        pagedTableConfig.setColumnHeaders(Arrays.asList(tableConfig.getTableVisibleHeader()));
//        pagedTableConfig.setQueryBean(queryBean);
//       	try{
//       		if(tableConfig.getDataQueryInputs()==null && tableConfig.getCountQueryInputs()==null){
//        tableConfig.setDataQueryInputs(GtnUIFrameworkGlobalUI.fetchInput(tableConfig.getDataQueryInputs()));
//        tableConfig.setCountQueryInputs(GtnUIFrameworkGlobalUI.fetchInput(tableConfig.getCountQueryInputs()));
//       		}
//    	}
//    	catch(Exception e){
//    		gtnLogger.info("Exception in grid"+e);
//    	}
        PagedGrid pagedGrid = new PagedGrid(tableConfig,componentConfig);
        pagedGrid.getGrid().setId(componentConfig.getComponentId());
        resultLayout.setSizeFull();
        resultLayout.addComponent(pagedGrid.getGrid());
        pagedGrid.getGrid().setWidth(componentConfig.getComponentWidth());
        pagedGrid.getGrid().setHeight(componentConfig.getComponentHight());
        resultLayout.setComponentAlignment(pagedGrid.getGrid(), Alignment.MIDDLE_CENTER);
        GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
        componentData.setTableConfig(tableConfig);
        componentData.setPagedGrid(pagedGrid);
        componentData.setCustomData(pagedGrid);

        VerticalLayout controls = new VerticalLayout();
        controls.addComponents(pagedGrid.getControlLayout());
        controls.setWidth("100%");
        controls.setHeightUndefined();
        controls.setComponentAlignment(pagedGrid.getControlLayout(), Alignment.MIDDLE_CENTER);
        resultLayout.addComponent(controls);

        pagedGrid.getGrid().getEditor().setEnabled(true);
//
//            GtnUIFrameworkPagedTableLogic tableLogic = getPagedTableLogicClass(componentConfig);
//
//            if (tableConfig.getTableColumnDataTypeURL() != null) {
//                loadColumnDataTypeFromWS(tableConfig);
//            }
//
//            tableLogic.setComponentConfig(componentConfig);
//            final ExtContainer container = new ExtContainer(GtnWsRecordBean.class, ExtContainer.DataStructureMode.LIST);
//            final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
//
//            if (tableConfig.isCaptionVisible()) {
//                resultTable.setCaption(componentConfig.getComponentName());
//            }
//            for (String style : componentConfig.getComponentStyle()) {
//                resultTable.addStyleName(style);
//            }
//            GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
//            componentData.setCurrentPageTableLogic(tableLogic);
//            componentData.setCustomData(resultTable);
//
//            resultTable.setWidth(componentConfig.getComponentWidth());
//            if (componentConfig.getComponentHight() != null) {
//                resultTable.setHeight(componentConfig.getComponentHight());
//            }
//            resultTable.setSortEnabled(tableConfig.isSortingEnable());
//            resultTable.setDragMode(tableConfig.getDragMode().getDragMode());
//            resultTable.setPageLength(tableConfig.getPageLength());
//            resultTable.setItemsPerPage(tableConfig.getItemPerPage());
//            tableLogic.sinkItemPerPageWithPageLength(tableConfig.isSinkItemPerPageWithPageLength());
//            tableLogic.setTempPageLength(tableConfig.getPageLength());
//            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
//            resultTable.setFilterGenerator(new GtnUIFrameworkPagedTableFilterGenerator(tableConfig));
//            resultTable.setEditable(tableConfig.isEditable());
//            resultTable.setMultiSelect(tableConfig.isMultiSelect());
//            setFieldFactoryProperties(tableConfig, resultTable, componentData);
//            resultTable.setImmediate(true);
//            resultTable.addStyleName(GtnFrameworkCssConstants.FILTERBAR);
//            resultTable.addStyleName(GtnFrameworkCssConstants.V_HAS_WIDTH);
//            resultTable.addStyleName(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
//            resultTable.addStyleName(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
//            resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
//
//            List<Object> visibleColumnList = Arrays.asList(tableConfig.getTableColumnMappingId());
//            if (tableConfig.getRecordTypeManageActionConfig() == null) {
//                GtnUIFrameWorkActionConfig recordTypeManageActionConfig = new GtnUIFrameWorkActionConfig();
//                recordTypeManageActionConfig.setActionType(GtnUIFrameworkActionType.MANAGE_TABLE_RECORD_TYPE_ACTION);
//                recordTypeManageActionConfig.addActionParameter(componentConfig.getComponentId());
//                tableConfig.setRecordTypeManageActionConfig(recordTypeManageActionConfig);
//            }
//            List<Integer> dateColumn = new ArrayList<>();
//            Map<String, Class<?>> dataType = getColumnProperties(tableConfig, visibleColumnList, dateColumn);
//            container.setColumnProperties(dataType);
//            container.setRecordHeader(getRecordHeader(tableConfig, visibleColumnList));
//            tableLogic.setRecordHeader((List<Object>) getRecordHeader(tableConfig, visibleColumnList));
//            tableLogic.setContainerDataSource(container);
//            tableLogic.setDateColumn(dateColumn);
//            resultTable.setVisibleColumns(visibleColumnList.toArray());
//            resultTable.setColumnHeaders(tableConfig.getTableVisibleHeader());
//            resultTable.setFilterBarVisible(tableConfig.isFilterBar());
//            resultTable.setSelectable(tableConfig.isSelectable());
//            for (Object propertyId : resultTable.getVisibleColumns()) {
//                resultTable.setColumnWidth(propertyId, -1);
//            }
//            setInvisibleFilterPropertyIds(tableConfig, resultTable);
//            setTableColumnAlignment(tableConfig, resultTable);
//            setDoubleHeaders(tableConfig, resultTable);
//            resultLayout.setSizeFull();
//            CssLayout tableCssLayout = new CssLayout();
//            tableCssLayout.addComponent(resultTable);
//            tableCssLayout.setSizeFull();
//            tableLogic.setPageLength(tableConfig.getItemPerPage());
//            tableLogic.setItemsPerPage(tableConfig.getPageLength());
//            tableLogic.sinkItemPerPageWithPageLength(tableConfig.isSinkItemPerPageWithPageLength());
//            resultLayout.addComponent(tableCssLayout);
//            HorizontalLayout pageHorizontalLayout = tableLogic.createControls();
//            pageHorizontalLayout.addStyleName("responsivePagedTable");
//            pageHorizontalLayout.setWidthUndefined();
//            resultLayout.addComponent(pageHorizontalLayout);
//            resultLayout.setVisible(componentConfig.isVisible());
//            tableLogic.setCountUrl(tableConfig.getCountUrl());
//            tableLogic.setResultSetUrl(tableConfig.getResultSetUrl());
//            if (!tableConfig.getColumnCheckBoxId().isEmpty()) {
//                resultTable.setColumnCheckBox(tableConfig.getColumnCheckBoxId(), true, false);
//            }
//            if (tableConfig.getColumnCheckActionConfigList() != null
//                    && !tableConfig.getColumnCheckActionConfigList().isEmpty()) {
//                resultTable.addColumnCheckListener(
//                        new GtnUIFrameworkTableColumnCheckListener(componentConfig.getComponentId()));
//            }
//            addTableClickListener(tableConfig, resultTable, componentConfig);
//            resultTable.addValueChangeListener(new GtnUIFrameworkPagedTableComponent.TableValueChangeListener(componentConfig));
//            setDefaultDateFormat(tableConfig, resultTable, visibleColumnList);
//            setCustomDateFormat(tableConfig, resultTable, visibleColumnList);
//            if (tableConfig.getIntegerFormatPropertyList() != null
//                    && !tableConfig.getIntegerFormatPropertyList().isEmpty()) {
//                for (String propertyId : tableConfig.getIntegerFormatPropertyList()) {
//                    resultTable.setConverter(propertyId, IntegerFormatConverter.getConverter());
//                }
//            }
//
            resultLayout.setData(componentData);
    	
        return resultLayout;
    }

  

    @Override
    public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId, Object reloadInput) {
        return;
    }

    @Override
    public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
        return;
    }

    @Override
    public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
        return;
    }

}
