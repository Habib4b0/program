package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.List;

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
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.V8_LABEL;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GtnReportingVariableBreakdownGridLoadAction
        implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownGridLoadAction.class);

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
            logger.info("------------GtnReportingVariablBreakdownGridLoadAction---------AA-------");
            int i = 0;
            List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
            String variableBreakdownTableId = actionParameterList.get(1).toString();
            List<GtnReportComparisonProjectionBean> comparisonLookupBeanList = new ArrayList<>();
            
            GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponentFromParent(
                            "reportLandingScreen_reportingDashboardComparisonConfig", componentId)
                    .getComponentData();
            
            comparisonLookupBeanList = (List<GtnReportComparisonProjectionBean>) idComponentData.getCustomData();
           
            if(comparisonLookupBeanList == null){
                comparisonLookupBeanList = new ArrayList<>();
            }
            List<String> projectionNameListFromCustomData = new ArrayList<>();
            projectionNameListFromCustomData.clear();
            projectionNameListFromCustomData.add("Ex-Factory Sales");
            projectionNameListFromCustomData.add("Latest Approved");
            
            for(int count=0;count<comparisonLookupBeanList.size();count++){
                projectionNameListFromCustomData.add(comparisonLookupBeanList.get(count).getProjectionName());
            }
            
            
            int comparisonLookupBeanSize = projectionNameListFromCustomData.size();
            
            AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(variableBreakdownTableId,
                    componentId);
            GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
            PagedGrid pagedGrid = (PagedGrid) gridComponent.getCustomData();
            Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();

            
            List projectionList=new ArrayList<>();
            for(int start=0;start<projectionNameListFromCustomData.size();start++){
                projectionList.add(projectionNameListFromCustomData.get(start));
            }     
                   GtnUIFrameworkComboBoxConfig fileOrProjectionComboboxConfig = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent("reportOptionsTab_variableBreakdownValueFileorProjection", componentId).getComponentConfig()
                    .getGtnComboboxConfig();
            fileOrProjectionComboboxConfig.setItemValues(projectionList);
            fileOrProjectionComboboxConfig.setItemCaptionValues(projectionList);

            GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
            combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                    "reportOptionsTab_variableBreakdownValueFileorProjection", componentId,
                    Arrays.asList(""));
                      
            
            String frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig")
                    .getStringCaptionFromV8ComboBox();
            
            String frequencyId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig")
                    .getCaptionFromV8ComboBox();
            
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportOptionsTab_variableBreakdownFrequencyConfig", componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequencyId));
            
            
            
            clearGrid(grid);

            GtnUIFrameworkPagedTableConfig tableConfig = setHeaderFromWs(pagedGrid, componentId, grid);
            configureCheckboxHeaderComponents(tableConfig.getTableColumnMappingId(), tableConfig.getColumnHeaders(), grid, tableConfig);

            setStartAndEndPeriodForVariableBreakdwonLookup(tableConfig, componentId);
            
            String localDate = String.valueOf(LocalDate.now());

            String[] localDateSplit = localDate.split("-");
            String currentYear = localDateSplit[0];
            String currentmonth = localDateSplit[1];
            String currentDateToDisableField = getCurrentDateToDisableField(frequency, currentYear, currentmonth);

            Component vaadinComponent = null;

            Object[] filterColumnIdList = pagedGrid.getTableConfig().getTableColumnMappingId();

            while (comparisonLookupBeanSize > 0) {
                HeaderRow filterRow = grid.appendHeaderRow();
                isDisableColumns = true;
                for (Object column : filterColumnIdList) {
                   
                        vaadinComponent = getCustomFilterComponent(String.valueOf(column), componentId, i, currentDateToDisableField , grid,projectionNameListFromCustomData.get(i));
                        filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent); 
                }
                i++;
                comparisonLookupBeanSize--;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStartAndEndPeriodForVariableBreakdwonLookup(GtnUIFrameworkPagedTableConfig tableConfig, String componentId) {
        List<String> startAndEndPeriodCaptionList = tableConfig.getColumnHeaders();
        ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(Arrays.asList(tableConfig.getTableColumnMappingId()));
        
        startAndEndPeriodCaptionList.remove(0);
        startAndEndPeriodItemIdList.remove(0);
        
        GtnUIFrameworkComboBoxConfig startPeriodComboboxConfig = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent("reportOptionsTab_variableBreakdownStartPeriod", componentId).getComponentConfig()
                .getGtnComboboxConfig();
        startPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
        startPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);
        
        GtnUIFrameworkComboBoxComponent startPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
        startPeriodCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                "reportOptionsTab_variableBreakdownStartPeriod", componentId,
                Arrays.asList(""));
        
        GtnUIFrameworkComboBoxConfig endPeriodComboboxConfig = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent("reportOptionsTab_variableBreakdownEndPeriod", componentId).getComponentConfig()
                .getGtnComboboxConfig();
        endPeriodComboboxConfig.setItemValues(startAndEndPeriodItemIdList);
        endPeriodComboboxConfig.setItemCaptionValues(startAndEndPeriodCaptionList);
        
        GtnUIFrameworkComboBoxComponent endPeriodCombobox = new GtnUIFrameworkComboBoxComponent();
        endPeriodCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                "reportOptionsTab_variableBreakdownEndPeriod", componentId,
                Arrays.asList(""));
        
    }

    private String getCurrentDateToDisableField(String frequency, String currentYear, String currentmonth) {
        String currentDate = "";
        if (frequency.startsWith("Ann")) {

            currentDate = currentYear + "year";
        } else if (frequency.startsWith("Qua")) {

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

    private GtnUIFrameworkPagedTableConfig setHeaderFromWs(PagedGrid pagedGrid, String componentId, Grid<GtnWsRecordBean> grid) throws GtnFrameworkGeneralException {
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
        tableSingleHeaders.add(0,"");
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
                if(!tableColumnMappingId[i].equals("projectionNames")){
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

    private Component getCustomFilterComponent(String property, String componentId, int i, String currentDateField, Grid<GtnWsRecordBean> grid, String projectionName) {
        try {
            List variableBreakdownSaveActionList = new ArrayList<>();
            if (property.equals("projectionNames")) {
                GtnUIFrameworkComponentConfig componentConfig = new GtnUIFrameworkComponentConfig();
                componentConfig.setComponentName(projectionName);
                componentConfig.setComponentId(property+projectionName+i);

                GtnUIFrameworkComponent componentLabel = V8_LABEL.getGtnComponent();
                Component vaadinComponentLabel = null;
                vaadinComponentLabel = componentLabel.buildVaadinComponent(componentConfig);
                Label vaadinLabel = (Label) vaadinComponentLabel;
                vaadinLabel.setValue(projectionName);
                grid.getColumn(property).setWidth(400);
                return vaadinLabel;
            }
            GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(
                    "reportOptionsTab_variableBreakdownValue", componentId);

            GtnUIFrameworkComponent component = COMBOBOX_VAADIN8.getGtnComponent();
            Component vaadinComponent = null;
            vaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
            ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
            vaadinCombobox.setId(property + String.valueOf(i));
            if (property.equalsIgnoreCase(currentDateField)) {

                isDisableColumns = false;
            }
            if (!isDisableColumns) {
                grid.getHeaderRow(0).getCell(property).getComponent().setEnabled(false);
                vaadinCombobox.setSelectedItem(2);
                vaadinCombobox.setReadOnly(true);
            }

            vaadinCombobox.addValueChangeListener(new HasValue.ValueChangeListener() {
                @Override
                public void valueChange(HasValue.ValueChangeEvent event) {
                    
                }
            });
            
            return vaadinCombobox;
        } catch (Exception e) {
            logger.error("Error message" + e);
        }
        return null;
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
        GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponentFromParent(
                        gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(), sourceViewId)
                .getComponentData();
        return resultTableComponentData.getCustomPagedTreeTableRequest();
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
