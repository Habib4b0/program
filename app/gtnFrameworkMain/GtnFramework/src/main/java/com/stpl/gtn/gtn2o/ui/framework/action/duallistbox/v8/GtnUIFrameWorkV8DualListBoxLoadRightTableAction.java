/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
//import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkV8DualListBoxLoadRightTableAction implements GtnUIFrameWorkAction {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkV8DualListBoxLoadRightTableAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) actionParametersList.get(0);
		boolean isMoveAll = (boolean) actionParametersList.get(1);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
		loadRightTable(dualListBoxBean, isMoveAll, componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadRightTable(GtnFrameworkV8DualListBoxBean dualListBoxBean, boolean isMoveAll, String componentId)
			throws GtnFrameworkGeneralException {
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		Set<GtnWsRecordBean> recordBean = leftTable.getSelectedItems();
		Map<String, String> levelValueMap = (Map<String, String>) dualListBoxBean.getGtnDualListBoxqueryParameters()
				.get(1);
		GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		if (!isMoveAll && leftTable.getSelectedItems() == null) {
			GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
			gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgList = new ArrayList<>(2);
			alertMsgList.add(GtnFrameworkForecastAlertMsgConstants.DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG_HEADER);
			alertMsgList.add(GtnFrameworkForecastAlertMsgConstants.DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG);
			gtnUIFrameAlertWorkActionConfig.setActionParameterList(alertMsgList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameAlertWorkActionConfig);
			return;
		} else {
			List<Object> inputPutList = new ArrayList<>(4);
			inputPutList.add("loaddata");

			List<String> hierarchyNoList = new ArrayList<>();
			if (isMoveAll) {
				hierarchyNoList.add("");
			} else {
				hierarchyNoList
						.add(leftTable.getSelectedItems().iterator().next().getPropertyValueByIndex(4).toString());
			}
			inputPutList.add(hierarchyNoList);

			inputPutList.add(dualListBoxConfig.getFileName());
			inputPutList.add(isMoveAll);
			List<Object> queryParameters = dualListBoxBean.getGtnDualListBoxqueryParametersList();
			GtnWsRecordBean record = recordBean.iterator().next();
			GtnUIFrameworkWebserviceRequest request = createRightTableRequest(queryParameters, record,
					dualListBoxConfig);
			GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getMoveRightURL(),
					dualListBoxConfig.getModuleType(), request);
			List<GtnWsRecordBean> beanList = new ArrayList<>();
			for (GtnUIFrameworkDataRow data : response.getGtnSerachResponse().getResultSet().getDataTable()) {
				GtnWsRecordBean selectedRecordBean = new GtnWsRecordBean();
				selectedRecordBean.setProperties(data.getColList());
				selectedRecordBean.addProperties(levelValueMap.get(data.getColumnVAlue(8)));
				selectedRecordBean.setRecordHeader(dualListBoxConfig.getRightRecordHeader());
				beanList.add(selectedRecordBean);
			}
			if (beanList != null) {
				treeBuilder.buildTree(beanList);
				treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
				rightTable.getDataProvider().refreshAll();
				rightTable.markAsDirty();
			}
		}
	}

	private GtnUIFrameworkWebserviceRequest createRightTableRequest(final List<Object> queryParameters,
			GtnWsRecordBean recordBean, GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnReportRequest(createReportRequest(recordBean, queryParameters));
		request.setGtnWsSearchRequest(createSearchRequest(queryParameters, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		return request;
	}

	private GtnWsReportRequest createReportRequest(GtnWsRecordBean recordBean, final List<Object> queryParameters) {
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setHierarchyInputBean((GtnReportHierarchyLevelBean) queryParameters.get(2));
		reportRequest.setHierarchyLevelList((List<GtnReportHierarchyLevelBean>) queryParameters.get(3));
		List<GtnWsRecordBean> bean = new ArrayList<>();
		bean.add(recordBean);
		reportRequest.setRecordBean(bean);
		return reportRequest;
	}

	private GtnWsSearchRequest createSearchRequest(final List<Object> queryParameters,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnWsSearchRequest dualListBoxSearchRequest = new GtnWsSearchRequest();
		dualListBoxSearchRequest.setQueryInputList(queryParameters);
		dualListBoxSearchRequest.setSearchColumnNameList(new ArrayList<Object>(dualListBoxConfig.getRecordHeader()));
		queryParameters.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		return dualListBoxSearchRequest;
	}

	private GtnWsGeneralRequest createGeneralRequest() {
		GtnWsGeneralRequest dualListBoxGeneralRequest = new GtnWsGeneralRequest();
		dualListBoxGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		return dualListBoxGeneralRequest;
	}

	private GtnUIFrameworkWebserviceResponse callWebService(final String webServiceUrl, final String moduleName,
			final GtnUIFrameworkWebserviceRequest request) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		return client.callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE + webServiceUrl,
				moduleName, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
