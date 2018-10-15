/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.Date;
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
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
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

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkV8DualListBoxLoadRightTableAction.class);

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
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
		loadRightTable(dualListBoxBean, componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadRightTable(GtnFrameworkV8DualListBoxBean dualListBoxBean, String componentId)
			throws GtnFrameworkGeneralException {
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		if (!leftTable.getSelectedItems().iterator().hasNext()) {
			GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
			gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgList = new ArrayList<>(2);
			alertMsgList.add(GtnFrameworkForecastAlertMsgConstants.DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG_HEADER);
			alertMsgList.add(GtnFrameworkForecastAlertMsgConstants.DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG);
			gtnUIFrameAlertWorkActionConfig.setActionParameterList(alertMsgList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameAlertWorkActionConfig);
			return;
		} else {
			Set<GtnWsRecordBean> recordBean = leftTable.getSelectedItems();
			Map<String, String> levelValueMap = (Map<String, String>) dualListBoxBean.getGtnDualListBoxqueryParameters()
					.get(1);
			boolean isProduct = (boolean) dualListBoxBean.getGtnDualListBoxqueryParameters().get(8);
			List<Object> queryParameters = dualListBoxBean.getGtnDualListBoxqueryParametersList();
			GtnWsRecordBean record = recordBean.iterator().next();
			GtnUIFrameworkWebserviceRequest request = createRightTableRequest(queryParameters, record,
					dualListBoxConfig, isProduct);
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
				treeBuilder.buildTree(beanList);
				treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
				rightTable.getDataProvider().refreshAll();
				rightTable.markAsDirty();
		}
	}

	private GtnUIFrameworkWebserviceRequest createRightTableRequest(final List<Object> queryParameters,
			GtnWsRecordBean recordBean, GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig, boolean isProduct) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsReportRequest(createReportRequest(recordBean, queryParameters, isProduct));
		request.setGtnWsSearchRequest(createSearchRequest(queryParameters, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		return request;
	}

	private GtnWsReportRequest createReportRequest(GtnWsRecordBean recordBean, final List<Object> queryParameters,
			boolean isProduct) {
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setHierarchyInputBean((GtnReportHierarchyLevelBean) queryParameters.get(2));
		reportRequest.setHierarchyLevelList((List<GtnReportHierarchyLevelBean>) queryParameters.get(3));
		if (!isProduct) {
			reportRequest.setForecastEligibleDate((Date) queryParameters.get(7));
		}
		List<GtnWsRecordBean> bean = new ArrayList<>();
		bean.add(recordBean);
		reportRequest.setRecordBean(bean);
		return reportRequest;
	}

	private GtnUIFrameworkWebserviceResponse callWebService(final String webServiceUrl, final String moduleName,
			final GtnUIFrameworkWebserviceRequest request) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		return client.callGtnWebServiceUrl(webServiceUrl,
				moduleName, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private GtnWsSearchRequest createSearchRequest(final List<Object> queryParameters,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnWsSearchRequest v8dualListBoxSearchRequest = new GtnWsSearchRequest();
		v8dualListBoxSearchRequest.setQueryInputList(queryParameters);
		v8dualListBoxSearchRequest.setSearchColumnNameList(new ArrayList<Object>(dualListBoxConfig.getRecordHeader()));
		queryParameters.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		return v8dualListBoxSearchRequest;
	}

	private GtnWsGeneralRequest createGeneralRequest() {
		GtnWsGeneralRequest v8dualListBoxGeneralRequest = new GtnWsGeneralRequest();
		v8dualListBoxGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		return v8dualListBoxGeneralRequest;
	}

}
