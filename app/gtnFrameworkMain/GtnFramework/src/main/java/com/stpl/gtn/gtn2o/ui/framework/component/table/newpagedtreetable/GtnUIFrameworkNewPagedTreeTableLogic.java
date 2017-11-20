/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Field;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkNewPagedTreeTableLogic extends PageTreeTableLogic {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkNewPagedTreeTableLogic.class);
	private List<Object> recordHeader = null;
	private boolean reset = false;
	private GtnUIFrameworkComponentConfig componentConfig = null;
	private List<GtnWebServiceSearchCriteria> currentSearchCriteria = null;
	private List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = null;
	private Object extraParameter = null;

	@Override
	public Map<Integer, Object> loadData(int start, int offset) {
		Map<Integer, Object> returnMap = new HashMap<>();
		if (isReset()) {
			GtnUIFrameworkNewTableConfig tableConfig = getComponentConfig().getGtnNewTableConfig();
			GtnWsSearchRequest request = new GtnWsSearchRequest();
			request.setTableRecordOffset(offset);
			request.setTableRecordStart(start);
			GtnSerachResponse searchResponse = getSerachResponse(request, tableConfig.getResultSetUrl());
			List<GtnUIFrameworkDataRow> dataRowList = searchResponse.getResultSet().getDataTable();
			int i = start;
			List<GtnWsRecordBean> records = new ArrayList<>();
			for (GtnUIFrameworkDataRow record : dataRowList) {
				GtnWsRecordBean dto = new GtnWsRecordBean();
				dto.setRecordHeader(getRecordHeader());
				dto.setProperties(record.getColList());
				detectChildrenAllowed(dto);
				records.add(dto);
				returnMap.put(i, dto);
				i++;
			}
			try {
				GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
				tableBaseComponent.getComponentData().setDataTableRecordList(records);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentConfig.getComponentId(),
						tableConfig.getRecordTypeManageActionConfig());
			} catch (GtnFrameworkGeneralException ex) {
				gtnLogger.info("Error While executing RecordTypeManageAction for " + componentConfig.getComponentId());
			}

		}
		return returnMap;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (isReset()) {
			GtnUIFrameworkNewPagedTableConfig pagedTableConfig = getComponentConfig().getGtnNewPagedTableConfig();
			GtnWsSearchRequest request = new GtnWsSearchRequest();
			request.setCount(true);
			GtnSerachResponse searchResponse = getSerachResponse(request, pagedTableConfig.getCountUrl());
			count = searchResponse.getCount();
		}
		return count;
	}

	private GtnSerachResponse getSerachResponse(GtnWsSearchRequest request, String serviceUrl) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(getExtraParameter());
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		serviceRequest.setGtnWsGeneralRequest(generalWSRequest);
		request.setSearchColumnNameList(getRecordHeader());
		request.setGtnWebServiceSearchCriteriaList(getSearchCriteriaList(getFilters()));
		request.setGtnWebServiceOrderByCriteriaList(getOrderByCriteria(getSortByColumns()));
		if (getLastParent() != null && getLastParent() instanceof GtnWsRecordBean) {
			request.setParentBean((GtnWsRecordBean) getLastParent());
		}
		serviceRequest.setGtnWsSearchRequest(request);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(serviceUrl, serviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnSerachResponse();
	}

	@Override
	public Object configureContainer(Object object, Container datasource) {
		GtnWsRecordBean dto = (GtnWsRecordBean) object;
		((Container.Hierarchical) getContainerDataSource()).addItem(dto);
		((Container.Hierarchical) getContainerDataSource()).setChildrenAllowed(dto, dto.getParentFlag());
		return dto;
	}

	@Override
	protected void createCurrentPageStart() {
		resetValues();
		setCurrentPageProgress(true);
		setRefresh(Boolean.FALSE);
	}

	@Override
	protected void expandCollapseEnd(boolean isExpand) {
		setExpandCollapseProgress(false);
	}

	@Override
	protected void expandCollapseStart(boolean isExpand) {
		setExpandCollapseProgress(true);
	}

	@Override
	protected void createCurrentPageEnd() {
		setCurrentPageProgress(false);
		setRefresh(Boolean.TRUE);
	}

	private void detectChildrenAllowed(GtnWsRecordBean recordBean) {
		GtnUIFrameworkNewTreeTableConfig treeTableConfig = getComponentConfig().getGtnNewTreeTableConfig();
		if (treeTableConfig != null && treeTableConfig.getChildDetectActionConfig() != null) {
			try {
				GtnUIFrameWorkActionConfig actionConfig = treeTableConfig.getChildDetectActionConfig();
				GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
				actionConfig.setEventParameter(recordBean);
				action.configureParams(actionConfig);
				action.doAction(getComponentConfig().getComponentId(), actionConfig);
			} catch (GtnFrameworkGeneralException ex) {
				gtnLogger.error(ex.getMessage());
			}
		}
	}

	public void startGenerateProcess(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkValidationFailedException {
		setReset(true);
		clearAll();
		addCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues(),
				gtnUIFrameWorkActionConfig.getFieldDescription());
		resetFilter();
		removeAllContainerFilters();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentConfig.getComponentId(), componentConfig.getSourceViewId());
		tableBaseComponent.getComponentData().setDataTableRecordList(null);
		setCurrentPage(1);
	}

	public List<Object> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<Object> recordHeader) {
		this.recordHeader = new ArrayList<>(recordHeader);
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public void resetFilter() {
		for (int i = 0; i < getTables().size(); i++) {
			getTables().get(i).resetFilters();
		}
	}

	public void resetValues() {
		for (int i = 0; i < getTables().size(); i++) {
			getTables().get(i).setValue(null);
		}
	}

	public GtnUIFrameworkComponentConfig getComponentConfig() {
		return componentConfig;
	}

	public void setComponentConfig(GtnUIFrameworkComponentConfig componentConfig) {
		this.componentConfig = componentConfig;
	}

	private void addCurrentSearchCriteria(List<String> vaadinFieldValues, List<String> vaadinFieldDescriptionList)
			throws GtnFrameworkValidationFailedException {
		currentSearchCriteria = new ArrayList<>();
		getFieldValues(vaadinFieldValues);

		if (vaadinFieldDescriptionList != null) {
			for (String description : vaadinFieldDescriptionList) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(description);
				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = baseComponent.getComponentConfig();

				String currentValue = baseComponent.getCaptionFromComboBox();
				if (currentValue != null && !"".equals(currentValue)) {

					if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
						searchCriteria.setFieldId(description);
					} else {
						searchCriteria.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
					}

					searchCriteria.setFilterValue1(currentValue);
					searchCriteria.setExpression(getExpression(currentValue));
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

	public List<GtnWebServiceSearchCriteria> getSearchCriteriaList(Set<Container.Filter> filterSet) {
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria newPagedTableSearchCriteria = null;

		if (currentSearchCriteria != null) {
			for (GtnWebServiceSearchCriteria current : currentSearchCriteria) {
				gtnWebServiceSearchCriteriaList.add(current);
			}
		}

		if (filterSet != null && !filterSet.isEmpty()) {
			for (Container.Filter filter : filterSet) {
				newPagedTableSearchCriteria = new GtnWebServiceSearchCriteria();
				newPagedTableSearchCriteria.setFilter(true);
				if (filter instanceof SimpleStringFilter) {
					newPagedTableSearchCriteria.setFieldId(((SimpleStringFilter) filter).getPropertyId().toString());
					newPagedTableSearchCriteria.setFilterValue1(((SimpleStringFilter) filter).getFilterString());
					newPagedTableSearchCriteria.setExpression("LIKE");
				}
				setBetweenFilter(filter, newPagedTableSearchCriteria);
				setCompareFilter(filter, newPagedTableSearchCriteria);
				setAddFilter(filter, newPagedTableSearchCriteria);
				gtnWebServiceSearchCriteriaList.add(newPagedTableSearchCriteria);
			}
		}

		return gtnWebServiceSearchCriteriaList;
	}

	public List<GtnWebServiceOrderByCriteria> getOrderByCriteria(List<SortByColumn> sortByColumns) {
		List<GtnWebServiceOrderByCriteria> newPagedTableOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria gtnWebServiceOrderByCriteria;
		if (sortByColumns != null && !sortByColumns.isEmpty()) {
			for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
				gtnWebServiceOrderByCriteria = new GtnWebServiceOrderByCriteria();
				SortByColumn sortByColumn = iterator.next();
				gtnWebServiceOrderByCriteria.setPropertyId(sortByColumn.getName());
				gtnWebServiceOrderByCriteria.setOrderByCriteria(sortByColumn.getType().toString());
				newPagedTableOrderByCriteriaList.add(gtnWebServiceOrderByCriteria);
			}
		}
		return newPagedTableOrderByCriteriaList;
	}

	private boolean isNull(Object value) {
		return value == null;
	}

	private String getString(Object value) {
		if (isNull(value)) {
			return "";
		}
		return String.valueOf(value);
	}

	public List<GtnWebServiceSearchCriteria> getCurrentSearchCriteria() {
		return currentSearchCriteria == null ? currentSearchCriteria
				: Collections.unmodifiableList(currentSearchCriteria);
	}

	public void setCurrentSearchCriteria(List<GtnWebServiceSearchCriteria> currentSearchCriteria) {
		this.currentSearchCriteria = new ArrayList<>(currentSearchCriteria);
	}

	public List<GtnWebServiceSearchCriteria> getAdditioanlSearchCriteriaList() {
		return additioanlSearchCriteriaList == null ? additioanlSearchCriteriaList
				: Collections.unmodifiableList(additioanlSearchCriteriaList);
	}

	public void setAdditioanlSearchCriteriaList(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
		this.additioanlSearchCriteriaList = new ArrayList<>(additioanlSearchCriteriaList);
	}

	public void resetSearchCriteriaList() {
		currentSearchCriteria = new ArrayList<>();
		additioanlSearchCriteriaList = new ArrayList<>();

	}

	@Override
	public void clearAll() {
		removeAllContainerFilters();
		super.clearAll();
	}

	public Object getExtraParameter() {
		return extraParameter;
	}

	public void setExtraParameter(Object extraParameter) {
		this.extraParameter = extraParameter;
	}

	private void getFieldValues(List<String> vaadinFieldValues) {
		if (vaadinFieldValues != null) {
			for (String componentId : vaadinFieldValues) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				Field<?> vaadinField = (Field<?>) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
				AbstractComponent vaadinAbstractComponent = (AbstractComponent) vaadinField;
				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = ((GtnUIFrameworkComponentData) vaadinAbstractComponent
						.getData()).getCurrentComponentConfig();

				String currentValue = getString(vaadinField.getValue());
				if (currentValue != null && !"".equals(currentValue)) {

					if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
						searchCriteria.setFieldId(componentId);
					} else {
						searchCriteria.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
					}

					searchCriteria.setFilterValue1(currentValue);
					searchCriteria.setExpression(getExpression(currentValue));
					currentSearchCriteria.add(searchCriteria);

				}
			}
		}
	}

	private String getExpression(String currentValue) {
		return currentValue.contains("*") ? "LIKE" : "EQUALS";
	}

	private void setAddFilter(Container.Filter filter, GtnWebServiceSearchCriteria newPagedTableSearchCriteria) {
		if (filter instanceof And) {
			And stringFilter = (And) filter;
			Collection<Container.Filter> value = stringFilter.getFilters();
			for (Container.Filter filter1 : value) {
				if (filter1 instanceof Compare.Less) {
					Compare.Less less = (Compare.Less) filter1;
					newPagedTableSearchCriteria.setFieldId(less.getPropertyId().toString());
					newPagedTableSearchCriteria.setFilterValue1(less.getValue().toString());
				}
				if (filter1 instanceof Compare.Greater) {
					Compare.Greater greater = (Compare.Greater) filter1;
					newPagedTableSearchCriteria.setFieldId(greater.getPropertyId().toString());
					newPagedTableSearchCriteria.setFilterValue2(greater.getValue().toString());
				}
				newPagedTableSearchCriteria.setExpression("AND");
			}
		}
	}

	private void setCompareFilter(Container.Filter filter, GtnWebServiceSearchCriteria newPagedTableSearchCriteria) {
		if (filter instanceof Compare) {
			Compare stringFilter = (Compare) filter;
			Compare.Operation operation = stringFilter.getOperation();
			newPagedTableSearchCriteria.setFieldId(stringFilter.getPropertyId().toString());
			newPagedTableSearchCriteria.setFilterValue1(stringFilter.getValue().toString());
			newPagedTableSearchCriteria.setExpression(operation.name());
		}
	}

	private void setBetweenFilter(Container.Filter filter, GtnWebServiceSearchCriteria newPagedTableSearchCriteria) {
		if (filter instanceof Between) {
			newPagedTableSearchCriteria.setFieldId(((Between) filter).getPropertyId().toString());
			newPagedTableSearchCriteria.setFilterValue1(((Between) filter).getStartValue().toString());
			newPagedTableSearchCriteria.setFilterValue2(((Between) filter).getEndValue().toString());
			newPagedTableSearchCriteria.setExpression("BETWEEN");
		}
	}

}
