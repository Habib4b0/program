/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;

/**
 *
 * @author Karthik.Raja
 */
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordTypeBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractComponentContainer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GtnUIFrameworkPagedGridLogic {

    private List<Object> recordHeader = null;
    private List<Integer> dateColumnPagedGrid = null;
    private boolean activePagedGrid = false;
    private String countUrlPagedGrid = null;
    private String resultSetUrlPagedGrid = null;
    private GtnUIFrameworkComponentConfig componentConfigPagedGrid = null;
    private List<GtnWebServiceSearchCriteria> currentSearchCriteriaPaged = null;
    private List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaListPaged = null;
    private GtnWsRecordTypeBean gtnWsRecordTypeBean = null;
    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedGridLogic.class);
    private Object extraParameter = null;
    private GtnUIFrameworkPagedTableConfig tableConfig;

    GtnUIFrameworkPagedGridLogic(GtnUIFrameworkPagedTableConfig tableConfig,
            GtnUIFrameworkComponentConfig gridComponentConfig) {
        recordHeader = Arrays.asList(tableConfig.getTableColumnMappingId());
        countUrlPagedGrid = tableConfig.getCountUrl();
        resultSetUrlPagedGrid = tableConfig.getResultSetUrl();
        componentConfigPagedGrid = gridComponentConfig;
        this.tableConfig = tableConfig;
    }

    public int getCount() {
        gtnLogger.debug("Get count for Table " + componentConfigPagedGrid.getComponentId());
        activePagedGrid = true;
        GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
        serviceRequest.getGtnWsSearchRequest().setCount(true);
        addServiceRegistryWsRequest(serviceRequest);
        gtnLogger.info("Count Query Module Name -------->" + componentConfigPagedGrid.getModuleName());
        GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(countUrlPagedGrid,
                componentConfigPagedGrid.getModuleName(), serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        if (response != null && response.getGtnSerachResponse() != null) {

            getCheckedRecordCount(serviceRequest, response.getGtnSerachResponse().getCount() > 0);
            return response.getGtnSerachResponse().getCount();
        }
        return 0;
    }

    private void addServiceRegistryWsRequest(GtnUIFrameworkWebserviceRequest serviceRequest) {
        if (tableConfig.getPagedTableWsUrl() != null) {
            GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
            GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
            serviceRegistryBean.setRegisteredWebContext(tableConfig.getRegisteredWebContext());
            serviceRegistryBean.setUrl(tableConfig.getPagedTableWsUrl());
            serviceRegistryBean.setModuleName(tableConfig.getModuleName());
            serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);
            serviceRequest.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
        }
    }

    private void getCheckedRecordCount(GtnUIFrameworkWebserviceRequest serviceRequest, boolean isTogetCount) {
        if (isTogetCount && !componentConfigPagedGrid.getGtnPagedTableConfig().getColumnCheckBoxId().trim().isEmpty()) {
            List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>();
            if (serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList() != null) {
                searchCriteriaList.addAll(serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
            }
            GtnWebServiceSearchCriteria searchCriteria = generateSearchCriteriaListForColumnCheckBoxId(componentConfigPagedGrid.getGtnPagedTableConfig().getColumnCheckBoxId());
            searchCriteriaList.add(searchCriteria);
            serviceRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(searchCriteriaList);
        }

    }

    public void getCheckedRecordCount() {

        GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
        serviceRequest.getGtnWsSearchRequest().setCount(true);
        getCheckedRecordCount(serviceRequest, true);
    }

    private GtnWebServiceSearchCriteria generateSearchCriteriaListForColumnCheckBoxId(String columnCheckBoxId) {
        GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
        searchCriteria.setFieldId(columnCheckBoxId);
        searchCriteria.setFilterValue1(String.valueOf(1));
        searchCriteria.setFilter(true);
        searchCriteria.setExpression(GtnFrameworkCommonConstants.PROPERTY_EQUALS);
        return searchCriteria;

    }

    public void handleCheckBoxOnItem(Object propertyIdHandleCheck, boolean valueHandleCheck) {
        // This method hasn't been used
    }

    // @Override
    public List<GtnWsRecordBean> loadData(int startLoadData, int offsetLoadData) {
        gtnLogger.debug("Get Data for Table " + componentConfigPagedGrid.getComponentId());
        activePagedGrid = true;
        List<GtnWsRecordBean> recordsLoadData = new ArrayList<>();
        GtnUIFrameworkWebServiceClient wsclientLoadData = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest serviceRequestLoadData = getWSRequest();
        serviceRequestLoadData.getGtnWsSearchRequest().setTableRecordOffset(offsetLoadData);
        serviceRequestLoadData.getGtnWsSearchRequest().setTableRecordStart(startLoadData);
        addServiceRegistryWsRequest(serviceRequestLoadData);
        gtnLogger.info("Module Name is------->" + componentConfigPagedGrid.getModuleName());
        GtnUIFrameworkWebserviceResponse responseLoadData = wsclientLoadData.callGtnWebServiceUrl(resultSetUrlPagedGrid,
                componentConfigPagedGrid.getModuleName(), serviceRequestLoadData, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        addDataAndConfig(responseLoadData, recordsLoadData);

        return recordsLoadData;
    }

    private void addDataAndConfig(GtnUIFrameworkWebserviceResponse responseAddData, List<GtnWsRecordBean> recordsAddData) {
        if (responseAddData != null) {
            for (GtnUIFrameworkDataRow recordAddData : responseAddData.getGtnSerachResponse().getResultSet().getDataTable()) {
                GtnWsRecordBean dtoAddData = new GtnWsRecordBean();
                dtoAddData.setRecordHeader(recordHeader);
                dtoAddData.setProperties(recordAddData.getColList());
                recordsAddData.add(dtoAddData);
            }
        }
        if (responseAddData != null && responseAddData.getGtnSerachResponse().getResultSet().getDataTable().size() == 0
                && componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeManageActionConfig() != null) {
            try {
                boolean isFilter = false;
                List<GtnWebServiceSearchCriteria> searchCriteriaList = getSearchCriteriaList();
                for (GtnWebServiceSearchCriteria searchCriteria : searchCriteriaList) {
                    if (searchCriteria.isFilter()) {
                        isFilter = true;
                    }
                }
                if (!isFilter) {
                    GtnUIFrameworkActionExecutor.executeSingleAction(componentConfigPagedGrid.getComponentId(),
                            componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeManageActionConfig());
                }
            } catch (GtnFrameworkGeneralException e) {
                gtnLogger.error(e.getMessage());
            }
        } else if (responseAddData == null && componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeManageActionConfig() != null) {
            try {
                GtnUIFrameworkActionExecutor.executeSingleAction(componentConfigPagedGrid.getComponentId(),
                        componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeManageActionConfig());
            } catch (GtnFrameworkGeneralException ex) {
                Logger.getLogger(GtnUIFrameworkPagedGridLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Object> getRecordHeader() {
        return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
    }

    public void setRecordHeader(List<Object> recordHeader) {
        this.recordHeader = new ArrayList<>(recordHeader);
    }

    public void startSearchProcess(final List<String> vaadinFieldValues, boolean isActive)
            throws GtnFrameworkValidationFailedException {

        gtnLogger.info("*************Inside startSearchProcess");
        this.activePagedGrid = isActive;
        addCurrentSearchCriteria(vaadinFieldValues, null);
        GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(componentConfigPagedGrid.getComponentId(), componentConfigPagedGrid.getSourceViewId());
        tableBaseComponent.getComponentData().setDataTableRecordList(null);
        tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
    }

    public void startSearchProcess(boolean isActive) {
        this.activePagedGrid = isActive;
        GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(componentConfigPagedGrid.getComponentId(), componentConfigPagedGrid.getSourceViewId());
        tableBaseComponent.getComponentData().setDataTableRecordList(null);
        tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
    }

    public void startSearchProcess(final List<String> vaadinFieldValues, final List<String> vaadinFieldDescriptionList,
            boolean isActive) throws GtnFrameworkValidationFailedException {
        this.activePagedGrid = isActive;
        addCurrentSearchCriteria(vaadinFieldValues, vaadinFieldDescriptionList);
        GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(componentConfigPagedGrid.getComponentId(), componentConfigPagedGrid.getSourceViewId());
        tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
    }

    public void addCurrentSearchCriteria(List<String> vaadinFieldValuesCurrentSearch, List<String> vaadinFieldDescriptionListCurrentSearch)
            throws GtnFrameworkValidationFailedException {

        gtnLogger.info("*************Inside startSearchProcess");

        currentSearchCriteriaPaged = new ArrayList<>();
        getFieldValues(vaadinFieldValuesCurrentSearch);
        if (vaadinFieldDescriptionListCurrentSearch != null) {
            for (String description : vaadinFieldDescriptionListCurrentSearch) {
                GtnWebServiceSearchCriteria searchCriteriaCurrentSearch = new GtnWebServiceSearchCriteria();
                GtnUIFrameworkBaseComponent baseComponentCurrentSearch = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(description,
                        componentConfigPagedGrid.getSourceViewId());
                GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = baseComponentCurrentSearch.getComponentConfig();

                String currentValueCurrentSearch = baseComponentCurrentSearch.getCaptionFromComboBox();
                if (currentValueCurrentSearch != null && !"".equals(currentValueCurrentSearch)) {

                    if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
                        searchCriteriaCurrentSearch.setFieldId(description);
                    } else {
                        searchCriteriaCurrentSearch.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
                    }

                    searchCriteriaCurrentSearch.setFilterValue1(currentValueCurrentSearch);
                    setExpressionType(gtnUIFrameworkComponentConfig, searchCriteriaCurrentSearch, currentValueCurrentSearch);
                    currentSearchCriteriaPaged.add(searchCriteriaCurrentSearch);
                }
            }
        }
        addAdditionalSearchCriteriaListFromConfig();
        addAdditionalSearchCriteriaPagedGrid();

    }

    private void addAdditionalSearchCriteriaListFromConfig() {
        if (tableConfig.getAdditionalSearchCriteriaListValues() != null && !tableConfig.getAdditionalSearchCriteriaListValues().isEmpty()) {
            List<String> additionalSearchCriteriaListValues = tableConfig.getAdditionalSearchCriteriaListValues();
            List<GtnWebServiceSearchCriteria> webSearchCriteriaList = new ArrayList<>();
            for (String searchCriteria : additionalSearchCriteriaListValues) {
                GtnWebServiceSearchCriteria gtnWebSearchCriteria = new GtnWebServiceSearchCriteria();
                gtnWebSearchCriteria.setFieldId(searchCriteria);
                gtnWebSearchCriteria.setFilterValue1(searchCriteria);
                webSearchCriteriaList.add(gtnWebSearchCriteria);
            }
            setAdditioanlSearchCriteriaList(webSearchCriteriaList);
        }
    }

    private void addAdditionalSearchCriteriaPagedGrid() {
        if (additioanlSearchCriteriaListPaged != null) {
            for (GtnWebServiceSearchCriteria searchCriteriaPagedGrid : additioanlSearchCriteriaListPaged) {
                currentSearchCriteriaPaged.add(searchCriteriaPagedGrid);
            }
        }
    }

    public boolean isActive() {
        return activePagedGrid;
    }

    public void setActive(boolean active) {
        this.activePagedGrid = active;
    }

    public String getCountUrl() {
        return countUrlPagedGrid;
    }

    public void setCountUrl(String countUrl) {
        this.countUrlPagedGrid = countUrl;
    }

    public String getResultSetUrl() {
        return resultSetUrlPagedGrid;
    }

    public void setResultSetUrl(String resultSetUrl) {
        this.resultSetUrlPagedGrid = resultSetUrl;
    }

    public List<Integer> getDateColumn() {
        return dateColumnPagedGrid == null ? dateColumnPagedGrid : Collections.unmodifiableList(dateColumnPagedGrid);
    }

    public void setDateColumn(List<Integer> dateColumn) {
        this.dateColumnPagedGrid = new ArrayList<>(dateColumn);
    }

    public GtnUIFrameworkComponentConfig getComponentConfig() {
        return componentConfigPagedGrid;
    }

    public void setComponentConfig(GtnUIFrameworkComponentConfig componentConfig) {
        this.componentConfigPagedGrid = componentConfig;
    }

    public List<GtnWebServiceSearchCriteria> getSearchCriteriaList() {
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria searchCriteria = null;

        if (currentSearchCriteriaPaged != null) {
            for (GtnWebServiceSearchCriteria current : currentSearchCriteriaPaged) {
                gtnWebServiceSearchCriteriaList.add(current);
            }
        }
        gtnLogger.info("---------getSearchCriteriaList--------");
        if (tableConfig.getFilterValueMap() != null && !tableConfig.getFilterValueMap().isEmpty()) {
            for (String property : tableConfig.getFilterValueMap().keySet()) {
                gtnLogger.info("---------tableConfig.getFilterValueMap() --------" + property);
                searchCriteria = new GtnWebServiceSearchCriteria();
                searchCriteria.setFilter(true);
                searchCriteria.setFieldId(property);
                searchCriteria.setFilterValue1(String.valueOf(tableConfig.getFilterValueMap().get(property)));
                searchCriteria.setExpression("LIKE");

                gtnWebServiceSearchCriteriaList.add(searchCriteria);
            }
        }

        return gtnWebServiceSearchCriteriaList;
    }

    private boolean isNull(Object valueIsNull) {
        return valueIsNull == null;
    }

    private String getString(Object valueGetString) {
        if (isNull(valueGetString)) {
            return "";
        } else if (valueGetString instanceof Date) {
            java.sql.Date date = new java.sql.Date(((Date) valueGetString).getTime());
            return String.valueOf(date);
        }
        return String.valueOf(valueGetString);
    }

    public List<GtnWebServiceSearchCriteria> getCurrentSearchCriteria() {
        return currentSearchCriteriaPaged == null ? currentSearchCriteriaPaged
                : Collections.unmodifiableList(currentSearchCriteriaPaged);
    }

    public void addCurrentSearchCriteria(GtnWebServiceSearchCriteria searchCriteria) {
        currentSearchCriteriaPaged.add(searchCriteria);
    }

    public void setCurrentSearchCriteria(List<GtnWebServiceSearchCriteria> currentSearchCriteria) {
        this.currentSearchCriteriaPaged = currentSearchCriteria == null ? currentSearchCriteria
                : new ArrayList<>(currentSearchCriteria);
    }

    public List<GtnWebServiceSearchCriteria> getAdditioanlSearchCriteriaList() {
        return additioanlSearchCriteriaListPaged == null ? additioanlSearchCriteriaListPaged
                : Collections.unmodifiableList(additioanlSearchCriteriaListPaged);
    }

    public void setAdditioanlSearchCriteriaList(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
        this.additioanlSearchCriteriaListPaged = additioanlSearchCriteriaList == null ? additioanlSearchCriteriaList
                : new ArrayList<>(additioanlSearchCriteriaList);
    }

    public GtnWsRecordTypeBean getGtnWsRecordTypeBean() {
        return gtnWsRecordTypeBean;
    }

    public void setGtnWsRecordTypeBean(GtnWsRecordTypeBean gtnWsRecordTypeBean) {
        this.gtnWsRecordTypeBean = gtnWsRecordTypeBean;
    }

    public void resetSearchCriteriaList() {

        currentSearchCriteriaPaged = new ArrayList<>();
        additioanlSearchCriteriaListPaged = new ArrayList<>();

    }

    public Object getExtraParameter() {
        return extraParameter;
    }

    public void setExtraParameter(Object extraParameter) {
        this.extraParameter = extraParameter;
    }

    public String loadDataForExcel(int start, int offset, List<String> headers, List<String> tableColumnFormatList) {

        GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
        serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
        serviceRequest.getGtnWsSearchRequest().setTableRecordStart(start);
        serviceRequest.getGtnWsGeneralRequest().setExcel(true);
        serviceRequest.getGtnWsGeneralRequest().setExtraParameter(headers);
        serviceRequest.getGtnWsGeneralRequest().setTableColumnFormatList(tableColumnFormatList);
        GtnUIFrameworkWebserviceResponse responseExcel = wsclient.callGtnWebServiceUrl(resultSetUrlPagedGrid,
                componentConfigPagedGrid.getModuleName(), serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

        return responseExcel.getExportFilePath();
    }

    private GtnUIFrameworkWebserviceRequest getWSRequest() {

        GtnUIFrameworkWebserviceRequest serviceRequestService = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest serviceRequest = new GtnWsSearchRequest();
        GtnWsGeneralRequest generalWSRequestService = new GtnWsGeneralRequest();
        generalWSRequestService.setExtraParameter(getExtraParameter());

        serviceRequest.setSearchModuleName(componentConfigPagedGrid.getGtnPagedTableConfig().getModuleName());
        serviceRequest.setSearchQueryName(componentConfigPagedGrid.getGtnPagedTableConfig().getQueryName());

        serviceRequest.setSearchConfigLodaderType(componentConfigPagedGrid.getGtnPagedTableConfig().getSearchQueryConfigLoaderType());

        List<Object> requestRecordHeader = new ArrayList<>(recordHeader);
        if (componentConfigPagedGrid.getGtnPagedTableConfig().getExtraColumn() != null
                && componentConfigPagedGrid.getGtnPagedTableConfig().getExtraColumnDataType() == null) {
            requestRecordHeader.addAll(Arrays.asList(componentConfigPagedGrid.getGtnPagedTableConfig().getExtraColumn()));
        }

        generalWSRequestService.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
        generalWSRequestService.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
        serviceRequestService.setGtnWsGeneralRequest(generalWSRequestService);
        serviceRequest.setSearchColumnNameList(requestRecordHeader);
        serviceRequest.setGtnWebServiceSearchCriteriaList(getSearchCriteriaList());
        serviceRequest.setQueryInputList(componentConfigPagedGrid.getQueryInputs());
        setRecordTypeBean();
        serviceRequest.setGtnWsRecordTypeBean(gtnWsRecordTypeBean);
        serviceRequestService.setGtnWsSearchRequest(serviceRequest);

        return serviceRequestService;
    }

    private String getExpression(String currentValue) {
        return currentValue.contains("*") ? "LIKE" : GtnFrameworkCommonConstants.PROPERTY_EQUALS;
    }

    private void getFieldValues(List<String> vaadinFieldValues) {
        if (vaadinFieldValues != null) {
            for (String componentId : vaadinFieldValues) {
                GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
                AbstractComponent vaadinAbstractComponent = (AbstractComponent) GtnUIFrameworkGlobalUI
                        .getVaadinBaseComponent(componentId, componentConfigPagedGrid.getSourceViewId()).getComponent();
                GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = ((GtnUIFrameworkComponentData) vaadinAbstractComponent
                        .getData()).getCurrentComponentConfig();

                HasValue vaadinField = getVaadinField(vaadinAbstractComponent);
                if (!vaadinField.isReadOnly()) {
                    String currentValueField = getString(vaadinField.getValue());
                    if (isNullOrEmpty(currentValueField)) {
                        setFieldIdToCriteria(gtnUIFrameworkComponentConfig, searchCriteria, componentId);
                        searchCriteria.setFilterValue1(getFilterValue(currentValueField));
                        setExpressionType(gtnUIFrameworkComponentConfig, searchCriteria, currentValueField);
                        currentSearchCriteriaPaged.add(searchCriteria);

                    }
                }
            }
        }
    }

    private HasValue getVaadinField(AbstractComponent vaadinAbstractComponent) {
        if (vaadinAbstractComponent instanceof AbstractComponentContainer) {
            return (HasValue) ((AbstractComponentContainer) vaadinAbstractComponent).iterator().next();
        } else if(vaadinAbstractComponent instanceof HasValue){
            return (HasValue) vaadinAbstractComponent;
        } 
        throw new IllegalArgumentException("Unable to find hasvalue based on "+ vaadinAbstractComponent);
    }

    private void setExpressionType(GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfigExpression,
            GtnWebServiceSearchCriteria searchCriteriaExpression, String currentValueExpression) {
        if (gtnUIFrameworkComponentConfigExpression.getExpressionType() == null) {
            searchCriteriaExpression.setExpression(getExpression(currentValueExpression));
        } else {
            searchCriteriaExpression.setExpression(gtnUIFrameworkComponentConfigExpression.getExpressionType());
        }
    }

    private String getFilterValue(String currentValueExpression) {
        return currentValueExpression.contains("'") ? currentValueExpression.replaceAll("'", "''") : currentValueExpression;
    }

    private boolean isNullOrEmpty(String currentValueNull) {
        return currentValueNull != null && !"".equals(currentValueNull);
    }

    private void setFieldIdToCriteria(GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfigCriteria,
            GtnWebServiceSearchCriteria searchCriteria, String componentId) {
        if (gtnUIFrameworkComponentConfigCriteria.getComponentWsFieldId() == null) {
            searchCriteria.setFieldId(componentId);
        } else {
            searchCriteria.setFieldId(gtnUIFrameworkComponentConfigCriteria.getComponentWsFieldId());
        }
    }

    private void setRecordTypeBean() {
        if (componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeComponentId() != null) {
            String recordTypeValueBean = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeComponentId())
                    .getStringFromField();
            gtnWsRecordTypeBean = new GtnWsRecordTypeBean();
            gtnWsRecordTypeBean.setCurrent(recordTypeValueBean.contains("Current"));
            gtnWsRecordTypeBean.setHistory(recordTypeValueBean.contains("History"));
            gtnWsRecordTypeBean.setFuture(recordTypeValueBean.contains("Future"));
            gtnWsRecordTypeBean.setStartDateColumn(componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeStartDate());
            gtnWsRecordTypeBean.setEndDateColumn(componentConfigPagedGrid.getGtnPagedTableConfig().getRecordTypeEndDate());
        }
    }

}
