
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.Container;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnUIFrameworkPagedTreeTableLogic extends PageTreeTableLogic {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedTreeTableLogic.class);

	private List<Object> recordHeader;
	private boolean isCountAvailable = false;
	private boolean levelFilter = false;
	private Map<String, Object> loadDataMap = new HashMap<>();
	private GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig;
	private GtnUIFrameworkPagedTreeTableConfig gtnUIFrameworkPagedTreeTableConfig;
	private final GtnUIFrameworkComponentData treeTableComponentData;

	public GtnUIFrameworkPagedTreeTableLogic(GtnUIFrameworkComponentData treeTableComponentData) {
		this.treeTableComponentData = treeTableComponentData;
	}

	@Override
	public int getCount() {

		int count = 0;
		try {

			if (isCountAvailable) {
				String classPath = gtnUIFrameworkPagedTreeTableConfig.getCountUrl();
				loadAndBuildCustomClassGetCount(classPath, getLastParent());
				GtnUIFrameworkWebserviceRequest serviceRequest = getCustomPagedTreeTableRequest(
						gtnUIFrameWorkActionConfig);
				GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
				GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
						gtnUIFrameworkPagedTreeTableConfig.getCountWsUrl(),
						gtnUIFrameworkPagedTreeTableConfig.getModuleName(), serviceRequest,
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

				count = response.getGtnWSPagedTreeTableResponse().getTableCount();
			}
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error("Error in getCount method.");
		}
		return count;
	}

	public void startGenerateProcess(GtnUIFrameWorkActionConfig currentGtnUIFrameWorkActionConfig,
			GtnUIFrameworkPagedTreeTableConfig tableConfig) {

		setBulkDataLoadAllowed(true);
		setFullBulkDataLoadAllowed(true);

		if (tableConfig != null) {
			this.gtnUIFrameworkPagedTreeTableConfig = tableConfig;
		}

		if (currentGtnUIFrameWorkActionConfig != null) {
			this.gtnUIFrameWorkActionConfig = currentGtnUIFrameWorkActionConfig;
		}

		if (levelFilter) {
			getLevelMapList().clear();
		}

		isCountAvailable = true;
		clearAll();
		setCurrentPage(1);
	}

	@Override
	public Map<String, Object> loadBulkData(Map<String, Object> bulkDataMap) {

		try {
			String classPath = gtnUIFrameworkPagedTreeTableConfig.getBulkDataUrl();
			loadAndBuildCustomClassGetBulkData(classPath, bulkDataMap);

			GtnUIFrameworkWebserviceRequest serviceRequest = getCustomPagedTreeTableRequest(gtnUIFrameWorkActionConfig);
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					gtnUIFrameworkPagedTreeTableConfig.getBulkDataWsUrl(),
					gtnUIFrameworkPagedTreeTableConfig.getModuleName(), serviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			List<GtnWsRecordBean> list = response.getGtnWSPagedTreeTableResponse().getGtnWsRecordBeanList();
			int i = 0;
			for (String hierarchyTable : bulkDataMap.keySet()) {
				GtnWsRecordBean currentWsRecordBean = list.get(i++);
				currentWsRecordBean.setRecordHeader(recordHeader);
				loadDataMap.put(hierarchyTable, currentWsRecordBean);
			}
			createCurrentPageStart();
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error("Error in loadBulkData method.");
		}
		return new HashMap<>();
	}

	@Override
	protected void createCurrentPageStart() {
		setCurrentPageProgress(true);
		setRefresh(Boolean.FALSE);
		for (Map.Entry<String, Object> loadData : loadDataMap.entrySet()) {
			removeExpandedTreeDataFetchable(loadData.getKey());
			addCurrentPageData(loadData.getKey(), loadData.getValue());
		}
		loadDataMap = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object configureContainer(Object object, Container container) {
		GtnWsRecordBean dto = (GtnWsRecordBean) object;
		((ExtTreeContainer<GtnWsRecordBean>) container).addBean(dto);
		((ExtTreeContainer<GtnWsRecordBean>) container).setChildrenAllowed(dto, dto.getParentFlag());
		return dto;
	}

	@Override
	protected void createCurrentPageEnd() {
		setCurrentPageProgress(false);
		setRefresh(Boolean.TRUE);
	}

	@Override
	protected void expandCollapseStart(boolean isExpand) {
		setExpandCollapseProgress(true);
	}

	@Override
	protected void expandCollapseEnd(boolean isExpand) {
		setExpandCollapseProgress(false);
	}

	@Override
	public Map<Integer, Object> loadData(int start, int offset) {
		return null;
	}

	public void forRefresh(Set<String> hierachyNos) {
		addExpandedTreeDataFetchable(hierachyNos);
	}

	public void expandCollapse(int levelNo) {
		fillCountForLevel(levelNo);
	}

	private void fillCountForLevel(int levelNo) {
		try {
			getLevelMapList().clear();
			getExpandedTreeList().clear();

			String classPath = gtnUIFrameworkPagedTreeTableConfig.getFillCountUrl();
			loadAndBuildCustomClassFillCountData(classPath, levelNo);

			GtnUIFrameworkWebserviceRequest serviceRequest = getCustomPagedTreeTableRequest(gtnUIFrameWorkActionConfig);

			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					gtnUIFrameworkPagedTreeTableConfig.getFillCountWsUrl(),
					gtnUIFrameworkPagedTreeTableConfig.getModuleName(), serviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			Map<String, Integer> countMap = response.getGtnWSPagedTreeTableResponse().getCountMap();
			addExpandedTreeDataFetchable(countMap.keySet());
			LevelMap levelCount = new LevelMap(countMap.remove(""), getColumnIdToFilterMap());
			addlevelMap("", levelCount);
			addExpandedTreeDataFetchable(countMap.keySet());
			for (Map.Entry<String, Integer> treeLevelNo : countMap.entrySet()) {
				levelCount = new LevelMap(treeLevelNo.getValue(), getColumnIdToFilterMap());
				addlevelMap(treeLevelNo.getKey(), levelCount);
				addExpandedTreeList(treeLevelNo.getKey(), new GtnWsRecordBean());
			}
			setRecordCount(getCalculatedTotalRecordCount());
			setCurrentPage(getTotalAmountOfPages());
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error("Error in fillCountForLevel method.");
		}
	}

	private void loadAndBuildCustomClassGetCount(String className, Object data) throws GtnFrameworkGeneralException {
		GtnUIFrameworkActionParameter params = new GtnUIFrameworkActionParameter();
		params.setLastParent(data);
		params.setRecordHeader(recordHeader);
		gtnUIFrameWorkActionConfig.setActionParameter(params);
		execteAction(className);
	}

	private void loadAndBuildCustomClassGetBulkData(String className, Map<String, Object> bulkDataMap)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkActionParameter params = new GtnUIFrameworkActionParameter();
		params.setLoadBulkMap(bulkDataMap);
		gtnUIFrameWorkActionConfig.setActionParameter(params);
		params.setRecordHeader(recordHeader);
		execteAction(className);
	}

	private void loadAndBuildCustomClassFillCountData(String classPath, int levelNo)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkActionParameter params = new GtnUIFrameworkActionParameter();
		params.setLevelNo(levelNo);
		gtnUIFrameWorkActionConfig.setActionParameter(params);
		params.setRecordHeader(recordHeader);
		execteAction(classPath);
	}

	private void execteAction(String classPath) throws GtnFrameworkGeneralException {
		try {
			gtnUIFrameWorkActionConfig.getActionParameter().setCurrentValue(levelFilter);
			GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
			GtnUIFrameWorkAction loader = (GtnUIFrameWorkAction) classLoader.loadDynamicClass(classPath);
			loader.configureParams(gtnUIFrameWorkActionConfig);
			loader.doAction(treeTableComponentData.getComponentIdInMap(), gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(" Error in class Loader ", e);
		}
	}

	private GtnUIFrameworkWebserviceRequest getCustomPagedTreeTableRequest(
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(),
				treeTableComponentData.getComponentIdInMap());
		return resultTableComponentData.getCustomPagedTreeTableRequest();
	}

	public List<Object> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<Object> recordHeader) {
		this.recordHeader = new ArrayList<>(recordHeader);
	}

	public boolean isLevelFilter() {
		return levelFilter;
	}

	public void setLevelFilter(boolean levelFilter) {
		this.levelFilter = levelFilter;
	}

}
