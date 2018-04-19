/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

/**
 *
 * @author Karthik.Raja
 */

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
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
//import com.vaadin.v7.ui.Field;

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
        GtnUIFrameworkPagedGridLogic(GtnUIFrameworkPagedTableConfig tableConfig){
            recordHeader=Arrays.asList(tableConfig.getTableColumnMappingId());
            countUrl=tableConfig.getCountUrl();
            resultSetUrl=tableConfig.getResultSetUrl();
            
        }

	public int getCount() {
		gtnLogger.debug("Get count for Table " + componentConfig.getComponentId());
		if (active) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
			serviceRequest.getGtnWsSearchRequest().setCount(true);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(countUrl, serviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response != null && response.getGtnSerachResponse() != null) {
				if (response.getGtnSerachResponse().getCount() == 0) {
					GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId())
							.getGrid().setItems();
					return 0;
				}
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
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceResponse checkedRecordCountresponse = wsclient.callGtnWebServiceUrl(countUrl,
					serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
//			clearCheckedRecordCount();
//			incrementCheckedRecordCount(checkedRecordCountresponse.getGtnSerachResponse().getCount());
		}

	}

	public void getCheckedRecordCount() {
//		Set<Container.Filter> filterSet = new HashSet<>(getFilters());
//		clearFilters();
		GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		getCheckedRecordCount(serviceRequest, true);
//		setFilters(filterSet);
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
//		if (value) {
//			incrementCheckedRecordCount(1);
//		} else {
//			decrementCheckedRecordCount(1);
//		}
//		perFormCheckAll(propertyId);
	}

//	@Override
	public List<GtnWsRecordBean> loadData(int start, int offset) {
		gtnLogger.debug("Get Data for Table " + componentConfig.getComponentId());
		List<GtnWsRecordBean> records = new ArrayList<>();
		if (active) {
//			perFormCheckAll(componentConfig.getGtnPagedTableConfig().getColumnCheckBoxId());
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(start);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(resultSetUrl, serviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
				GtnWsRecordBean dto = new GtnWsRecordBean();
				dto.setRecordHeader(recordHeader);
				dto.setProperties(record.getColList());
				records.add(dto);
			}
			try {
				GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
				tableBaseComponent.setTableValue(null);
				tableBaseComponent.getComponentData().setDataTableRecordList(records);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentConfig.getComponentId(),
						componentConfig.getGtnPagedTableConfig().getRecordTypeManageActionConfig());
			} catch (GtnFrameworkGeneralException ex) {
				gtnLogger.info("Error While executing RecordTypeManageAction for " + componentConfig.getComponentId());
			}
			return records;
		}

		return records;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Object configureContainer(Object object, Container container) {
//		GtnWsRecordBean dto = (GtnWsRecordBean) object;
//		((ExtContainer<GtnWsRecordBean>) container).addBean(dto);
//		return dto;
//	}

	public List<Object> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<Object> recordHeader) {
		this.recordHeader = new ArrayList<>(recordHeader);
	}

	public void startSearchProcess(final List<String> vaadinFieldValues, boolean isActive)
			throws GtnFrameworkValidationFailedException {
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

//	public List<GtnWsRecordBean> generateExcel() {
//		Set<Container.Filter> addedFilters = new HashSet<>();
//		addedFilters.addAll(getFilters());
//		super.getFilters().clear();
//		int totalRecords = (int) getRecordCount();
//		if (!addedFilters.isEmpty()) {
//			totalRecords = getCount();
//		}
//		List<GtnWsRecordBean> excelData = loadData(0, totalRecords);
//		super.setFilters(addedFilters);
//		return excelData;
//
//	}

	public void startSearchProcess(final List<String> vaadinFieldValues, final List<String> vaadinFieldDescriptionList,
			boolean isActive) throws GtnFrameworkValidationFailedException {
		this.active = isActive;
		addCurrentSearchCriteria(vaadinFieldValues, vaadinFieldDescriptionList);
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
		tableBaseComponent.getComponentData().getPagedGrid().refreshGrid();
//		resetAndInit();
	}

//	private void resetAndInit() {
//            
//            
////		resetFilter();
////		super.clearAll();
////		super.getFilters().clear();
////		super.removeAllContainerFilters();
////		super.setRequiredCount(true);
////		super.setCurrentPage(1);
//	}

	public void addCurrentSearchCriteria(List<String> vaadinFieldValues, List<String> vaadinFieldDescriptionList)
			throws GtnFrameworkValidationFailedException {
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

//		if (filterSet != null && !filterSet.isEmpty()) {
//			for (Container.Filter filter : filterSet) {
//				searchCriteria = new GtnWebServiceSearchCriteria();
//				searchCriteria.setFilter(true);
//				if (filter instanceof SimpleStringFilter) {
//					searchCriteria.setFieldId(((SimpleStringFilter) filter).getPropertyId().toString());
//					searchCriteria.setFilterValue1(((SimpleStringFilter) filter).getFilterString());
//					searchCriteria.setExpression("LIKE");
//				}
//				setBetweenFilter(filter, searchCriteria);
//				setCompareFilter(filter, searchCriteria);
//				setAddFilter(filter, searchCriteria);
//				gtnWebServiceSearchCriteriaList.add(searchCriteria);
//			}
//		}

		return gtnWebServiceSearchCriteriaList;
	}

//	public List<GtnWebServiceOrderByCriteria> getOrderByCriteria(List<SortByColumn> sortByColumns) {
//		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
//		GtnWebServiceOrderByCriteria gtnWebServiceOrderByCriteria;
//		if (sortByColumns != null && !sortByColumns.isEmpty()) {
//			for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
//				gtnWebServiceOrderByCriteria = new GtnWebServiceOrderByCriteria();
//				SortByColumn sortByColumn = iterator.next();
//				gtnWebServiceOrderByCriteria.setPropertyId(sortByColumn.getName());
//				gtnWebServiceOrderByCriteria.setOrderByCriteria(sortByColumn.getType().toString());
//				gtnWebServiceOrderByCriteriaList.add(gtnWebServiceOrderByCriteria);
//			}
//		}
//		return gtnWebServiceOrderByCriteriaList;
//	}

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
//
//	public void resetFilter() {
//		for (int i = 0; i < getTables().size(); i++) {
//			getTables().get(i).resetFilters();
//		}
//	}
//
//	public void resetValues() {
//		for (int i = 0; i < getTables().size(); i++) {
//			getTables().get(i).setValue(null);
//		}
//	}

	public Object getExtraParameter() {
		return extraParameter;
	}

	public void setExtraParameter(Object extraParameter) {
		this.extraParameter = extraParameter;
	}

//	@Override
//	protected void createCurrentPageStart() {
//		resetValues();
//		setRefresh(false);
//	}
//
//	@Override
//	protected void createCurrentPageEnd() {
//		setRefresh(true);
//	}

	public String loadDataForExcel(int start, int offset, List<String> headers, List<String> tableColumnFormatList) {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest();
		serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
		serviceRequest.getGtnWsSearchRequest().setTableRecordStart(start);
		serviceRequest.getGtnWsGeneralRequest().setExcel(true);
		serviceRequest.getGtnWsGeneralRequest().setExtraParameter(headers);
		serviceRequest.getGtnWsGeneralRequest().setTableColumnFormatList(tableColumnFormatList);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(resultSetUrl, serviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

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
//		request.setGtnWebServiceOrderByCriteriaList(getOrderByCriteria(getSortByColumns()));
		request.setQueryInputList(componentConfig.getQueryInputs());
		setRecordTypeBean();
		request.setGtnWsRecordTypeBean(gtnWsRecordTypeBean);
		serviceRequest.setGtnWsSearchRequest(request);

		return serviceRequest;
	}

	private String getExpression(String currentValue) {
		return currentValue.contains("*") ? "LIKE" : GtnFrameworkCommonConstants.PROPERTY_EQUALS;
	}

//	private void setAddFilter(Container.Filter filter, GtnWebServiceSearchCriteria searchCriteria) {
//		if (filter instanceof And) {
//			And stringFilter = (And) filter;
//			Collection<Container.Filter> value = stringFilter.getFilters();
//			for (Container.Filter filter1 : value) {
//				if (filter1 instanceof Compare.Less) {
//					Compare.Less less = (Compare.Less) filter1;
//					searchCriteria.setFieldId(less.getPropertyId().toString());
//					searchCriteria.setFilterValue1(less.getValue().toString());
//				}
//				if (filter1 instanceof Compare.Greater) {
//					Compare.Greater greater = (Compare.Greater) filter1;
//					searchCriteria.setFieldId(greater.getPropertyId().toString());
//					searchCriteria.setFilterValue2(greater.getValue().toString());
//				}
//				searchCriteria.setExpression("AND");
//			}
//		}
//	}
//
//	private void setCompareFilter(Container.Filter filter, GtnWebServiceSearchCriteria searchCriteria) {
//		if (filter instanceof Compare) {
//			Compare stringFilter = (Compare) filter;
//			Compare.Operation operation = stringFilter.getOperation();
//			searchCriteria.setFieldId(stringFilter.getPropertyId().toString());
//			searchCriteria.setFilterValue1(stringFilter.getValue().toString());
//			searchCriteria.setExpression(operation.name());
//		}
//	}
//
//	private void setBetweenFilter(Container.Filter filter, GtnWebServiceSearchCriteria searchCriteria) {
//		if (filter instanceof Between) {
//			searchCriteria.setFieldId(((Between) filter).getPropertyId().toString());
//			searchCriteria.setFilterValue1(((Between) filter).getStartValue().toString());
//			searchCriteria.setFilterValue2(((Between) filter).getEndValue().toString());
//			searchCriteria.setExpression("BETWEEN");
//		}
//	}

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

