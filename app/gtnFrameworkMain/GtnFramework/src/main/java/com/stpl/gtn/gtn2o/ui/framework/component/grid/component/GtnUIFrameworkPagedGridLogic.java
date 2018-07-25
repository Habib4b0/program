/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

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
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GtnUIFrameworkPagedGridLogic {

	private List<Object> recordHeader = null;
	private List<Integer> dateColumn = null;
	private boolean active = false;
	private String countUrl = null;
	private String resultSetUrl = null;
	private GtnUIFrameworkComponentConfig componentConfig = null;
	private List<GtnWebServiceSearchCriteria> currentSearchCriteria = null;
	private List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = null;
	private GtnWsRecordTypeBean gtnWsRecordTypeBean = null;
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedGridLogic.class);
	private Object extraParameter = null;
	private GtnUIFrameworkPagedTableConfig tableConfig;

	GtnUIFrameworkPagedGridLogic(GtnUIFrameworkPagedTableConfig tableConfig,
			GtnUIFrameworkComponentConfig gridComponentConfig) {
		recordHeader = Arrays.asList(tableConfig.getTableColumnMappingId());
		countUrl = tableConfig.getCountUrl();
		resultSetUrl = tableConfig.getResultSetUrl();
		componentConfig = gridComponentConfig;
		this.tableConfig = tableConfig;
	}

	public int getCount() {
		gtnLogger.debug("Get count for Table " + componentConfig.getComponentId());
		active = true;
		if (active) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
			serviceRequest.getGtnWsSearchRequest().setCount(true);
			gtnLogger.info("Count Query Module Name -------->" +componentConfig.getModuleName());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(countUrl,
					componentConfig.getModuleName(), serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response != null && response.getGtnSerachResponse() != null) {
				
				getCheckedRecordCount(serviceRequest, response.getGtnSerachResponse().getCount() > 0);
				return response.getGtnSerachResponse().getCount();
			}
		}
		return 0;
	}

	private void getCheckedRecordCount(GtnUIFrameworkWebserviceRequest serviceRequest, boolean isTogetCount) {
		if (isTogetCount && !componentConfig.getGtnPagedTableConfig().getColumnCheckBoxId().trim().isEmpty()) {
			List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>();
			if (serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList() != null) {
				searchCriteriaList.addAll(serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
			}
			GtnWebServiceSearchCriteria searchCriteria = generateSearchCriteriaListForColumnCheckBoxId(
					componentConfig.getGtnPagedTableConfig().getColumnCheckBoxId());
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

	public void handleCheckBoxOnItem(Object propertyId, boolean value) {
		//This method hasn't been used
	}

	// @Override
	public List<GtnWsRecordBean> loadData(int start, int offset) {
		gtnLogger.debug("Get Data for Table " + componentConfig.getComponentId());
		active = true;
		List<GtnWsRecordBean> records = new ArrayList<>();
		if (active) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(start);
			gtnLogger.info("Module Name is------->" +componentConfig.getModuleName());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(resultSetUrl,
					componentConfig.getModuleName(), serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response != null) {
				for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
					GtnWsRecordBean dto = new GtnWsRecordBean();
					dto.setRecordHeader(recordHeader);
					dto.setProperties(record.getColList());
					records.add(dto);
				}
			} if(response != null && response.getGtnSerachResponse().getResultSet().getDataTable().size() == 0) {
				if (componentConfig.getGtnPagedTableConfig().getRecordTypeManageActionConfig() != null) {
					try{
					GtnUIFrameworkActionExecutor.executeSingleAction(componentConfig.getComponentId(),
							componentConfig.getGtnPagedTableConfig().getRecordTypeManageActionConfig());
					}catch(GtnFrameworkGeneralException e){
						gtnLogger.error(e.getMessage());
					}
				}
			}

		}

		return records;
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
		this.active = isActive;
		addCurrentSearchCriteria(vaadinFieldValues, null);
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
		tableBaseComponent.getComponentData().setDataTableRecordList(null);
		tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
	}

	public void startSearchProcess(boolean isActive) {
		this.active = isActive;
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
		tableBaseComponent.getComponentData().setDataTableRecordList(null);
		tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
	}



	public void startSearchProcess(final List<String> vaadinFieldValues, final List<String> vaadinFieldDescriptionList,
			boolean isActive) throws GtnFrameworkValidationFailedException {
		this.active = isActive;
		addCurrentSearchCriteria(vaadinFieldValues, vaadinFieldDescriptionList);
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
		tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
	}


	public void addCurrentSearchCriteria(List<String> vaadinFieldValues, List<String> vaadinFieldDescriptionList)
			throws GtnFrameworkValidationFailedException {
		
		gtnLogger.info("*************Inside startSearchProcess");
		
		currentSearchCriteria = new ArrayList<>();
		getFieldValues(vaadinFieldValues);
		if (vaadinFieldDescriptionList != null) {
			for (String description : vaadinFieldDescriptionList) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(description,
						componentConfig.getSourceViewId());
				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = baseComponent.getComponentConfig();

				String currentValue = baseComponent.getCaptionFromComboBox();
				if (currentValue != null && !"".equals(currentValue)) {

					if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
						searchCriteria.setFieldId(description);
					} else {
						searchCriteria.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
					}

					searchCriteria.setFilterValue1(currentValue);
					setExpressionType(gtnUIFrameworkComponentConfig, searchCriteria, currentValue);
					currentSearchCriteria.add(searchCriteria);
				}
			}
		}
		addAdditionalSearchCriteria();

	}

	private void addAdditionalSearchCriteria() {
		if (additioanlSearchCriteriaList != null) {
			for (GtnWebServiceSearchCriteria searchCriteria : additioanlSearchCriteriaList) {
				currentSearchCriteria.add(searchCriteria);
			}
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCountUrl() {
		return countUrl;
	}

	public void setCountUrl(String countUrl) {
		this.countUrl = countUrl;
	}

	public String getResultSetUrl() {
		return resultSetUrl;
	}

	public void setResultSetUrl(String resultSetUrl) {
		this.resultSetUrl = resultSetUrl;
	}

	public List<Integer> getDateColumn() {
		return dateColumn == null ? dateColumn : Collections.unmodifiableList(dateColumn);
	}

	public void setDateColumn(List<Integer> dateColumn) {
		this.dateColumn = new ArrayList<>(dateColumn);
	}

	public GtnUIFrameworkComponentConfig getComponentConfig() {
		return componentConfig;
	}

	public void setComponentConfig(GtnUIFrameworkComponentConfig componentConfig) {
		this.componentConfig = componentConfig;
	}

	public List<GtnWebServiceSearchCriteria> getSearchCriteriaList() {
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria searchCriteria = null;

		if (currentSearchCriteria != null) {
			for (GtnWebServiceSearchCriteria current : currentSearchCriteria) {
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


	private boolean isNull(Object value) {
		return value == null;
	}

	private String getString(Object value) {
		if (isNull(value)) {
			return "";
		} else if (value instanceof Date) {
			java.sql.Date date = new java.sql.Date(((Date) value).getTime());
			return String.valueOf(date);
		}
		return String.valueOf(value);
	}

	public List<GtnWebServiceSearchCriteria> getCurrentSearchCriteria() {
		return currentSearchCriteria == null ? currentSearchCriteria
				: Collections.unmodifiableList(currentSearchCriteria);
	}

	public void addCurrentSearchCriteria(GtnWebServiceSearchCriteria searchCriteria) {
		currentSearchCriteria.add(searchCriteria);
	}

	public void setCurrentSearchCriteria(List<GtnWebServiceSearchCriteria> currentSearchCriteria) {
		this.currentSearchCriteria = currentSearchCriteria == null ? currentSearchCriteria
				: new ArrayList<>(currentSearchCriteria);
	}

	public List<GtnWebServiceSearchCriteria> getAdditioanlSearchCriteriaList() {
		return additioanlSearchCriteriaList == null ? additioanlSearchCriteriaList
				: Collections.unmodifiableList(additioanlSearchCriteriaList);
	}

	public void setAdditioanlSearchCriteriaList(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
		this.additioanlSearchCriteriaList = additioanlSearchCriteriaList == null ? additioanlSearchCriteriaList
				: new ArrayList<>(additioanlSearchCriteriaList);
	}

	public GtnWsRecordTypeBean getGtnWsRecordTypeBean() {
		return gtnWsRecordTypeBean;
	}

	public void setGtnWsRecordTypeBean(GtnWsRecordTypeBean gtnWsRecordTypeBean) {
		this.gtnWsRecordTypeBean = gtnWsRecordTypeBean;
	}

	public void resetSearchCriteriaList() {

		currentSearchCriteria = new ArrayList<>();
		additioanlSearchCriteriaList = new ArrayList<>();

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
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(resultSetUrl,
				componentConfig.getModuleName(), serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		return response.getExportFilePath();
	}

	private GtnUIFrameworkWebserviceRequest getWSRequest() {

		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(getExtraParameter());

		request.setSearchModuleName(componentConfig.getGtnPagedTableConfig().getModuleName());
		request.setSearchQueryName(componentConfig.getGtnPagedTableConfig().getQueryName());

		request.setSearchConfigLodaderType(componentConfig.getGtnPagedTableConfig().getSearchQueryConfigLoaderType());

		List<Object> requestRecordHeader = new ArrayList<>(recordHeader);
		if (componentConfig.getGtnPagedTableConfig().getExtraColumn() != null
				&& componentConfig.getGtnPagedTableConfig().getExtraColumnDataType() == null) {
			requestRecordHeader.addAll(Arrays.asList(componentConfig.getGtnPagedTableConfig().getExtraColumn()));
		}

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		serviceRequest.setGtnWsGeneralRequest(generalWSRequest);
		request.setSearchColumnNameList(requestRecordHeader);
		request.setGtnWebServiceSearchCriteriaList(getSearchCriteriaList());
		request.setQueryInputList(componentConfig.getQueryInputs());
		setRecordTypeBean();
		request.setGtnWsRecordTypeBean(gtnWsRecordTypeBean);
		serviceRequest.setGtnWsSearchRequest(request);

		return serviceRequest;
	}

	private String getExpression(String currentValue) {
		return currentValue.contains("*") ? "LIKE" : GtnFrameworkCommonConstants.PROPERTY_EQUALS;
	}



	private void getFieldValues(List<String> vaadinFieldValues) {
		if (vaadinFieldValues != null) {
			for (String componentId : vaadinFieldValues) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				HasValue<?> vaadinField = (HasValue<?>) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentId, componentConfig.getSourceViewId()).getComponent();
				AbstractComponent vaadinAbstractComponent = (AbstractComponent) vaadinField;
				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = ((GtnUIFrameworkComponentData) vaadinAbstractComponent
						.getData()).getCurrentComponentConfig();
				if (!vaadinField.isReadOnly()) {
					String currentValue = getString(vaadinField.getValue());
					if (isNullOrEmpty(currentValue)) {
						setFieldIdToCriteria(gtnUIFrameworkComponentConfig, searchCriteria, componentId);
						searchCriteria.setFilterValue1(getFilterValue(currentValue));
						setExpressionType(gtnUIFrameworkComponentConfig, searchCriteria, currentValue);
						currentSearchCriteria.add(searchCriteria);

					}
				}
			}
		}
	}

	private void setExpressionType(GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig,
			GtnWebServiceSearchCriteria searchCriteria, String currentValue) {
		if (gtnUIFrameworkComponentConfig.getExpressionType() == null) {
			searchCriteria.setExpression(getExpression(currentValue));
		} else {
			searchCriteria.setExpression(gtnUIFrameworkComponentConfig.getExpressionType());
		}
	}

	private String getFilterValue(String currentValue) {
		return currentValue.contains("'") ? currentValue.replaceAll("'", "''") : currentValue;
	}

	private boolean isNullOrEmpty(String currentValue) {
		return currentValue != null && !"".equals(currentValue);
	}

	private void setFieldIdToCriteria(GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig,
			GtnWebServiceSearchCriteria searchCriteria, String componentId) {
		if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
			searchCriteria.setFieldId(componentId);
		} else {
			searchCriteria.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
		}
	}

	private void setRecordTypeBean() {
		if (componentConfig.getGtnPagedTableConfig().getRecordTypeComponentId() != null) {
			String recordTypeValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentConfig.getGtnPagedTableConfig().getRecordTypeComponentId())
					.getStringFromField();
			gtnWsRecordTypeBean = new GtnWsRecordTypeBean();
			gtnWsRecordTypeBean.setCurrent(recordTypeValue.contains("Current"));
			gtnWsRecordTypeBean.setHistory(recordTypeValue.contains("History"));
			gtnWsRecordTypeBean.setFuture(recordTypeValue.contains("Future"));
			gtnWsRecordTypeBean.setStartDateColumn(componentConfig.getGtnPagedTableConfig().getRecordTypeStartDate());
			gtnWsRecordTypeBean.setEndDateColumn(componentConfig.getGtnPagedTableConfig().getRecordTypeEndDate());
		}
	}
}
