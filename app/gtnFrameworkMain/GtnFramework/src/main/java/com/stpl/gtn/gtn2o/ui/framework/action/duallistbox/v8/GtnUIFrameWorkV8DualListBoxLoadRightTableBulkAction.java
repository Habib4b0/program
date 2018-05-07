package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameWorkV8DualListBoxLoadRightTableBulkAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) actionParametersList.get(0);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
		loadRightTableBulk(dualListBoxBean);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadRightTableBulk(GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		Map<String, String> levelValueMap = (Map<String, String>) dualListBoxBean.getGtnDualListBoxqueryParameters()
				.get(1);
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getMoveAllDataURL(),
				createRightTableRequest(dualListBoxBean.getGtnDualListBoxqueryParameters(), dualListBoxConfig),
				dualListBoxConfig);

		List<GtnWsRecordBean> gtnWsRecordBeanList = new ArrayList<>();
		for (GtnUIFrameworkDataRow data : response.getGtnSerachResponse().getResultSet().getDataTable()) {
			GtnWsRecordBean selectedRecordBean = new GtnWsRecordBean();
			selectedRecordBean.setProperties(data.getColList());
			selectedRecordBean.addProperties(levelValueMap.get(data.getColumnVAlue(8)));
			selectedRecordBean.setRecordHeader(dualListBoxConfig.getRightRecordHeader());
			gtnWsRecordBeanList.add(selectedRecordBean);
		}

		if (gtnWsRecordBeanList != null) {
			treeBuilder.buildTree(gtnWsRecordBeanList);
			treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();
		}
	}

	private GtnWsReportRequest createReportRequest(final List<Object> queryParameters) {
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setHierarchyInputBean((GtnReportHierarchyLevelBean) queryParameters.get(2));
		reportRequest.setHierarchyLevelList((List<GtnReportHierarchyLevelBean>) queryParameters.get(3));
		return reportRequest;
	}

	private GtnWsSearchRequest createSearchRequest(final List<Object> queryParameters,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setQueryInputList(queryParameters);
		searchRequest.setSearchColumnNameList(new ArrayList<>(dualListBoxConfig.getRecordHeader()));
		return searchRequest;
	}

	private GtnWsGeneralRequest createGeneralRequest() {
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		return generalRequest;
	}

	private GtnUIFrameworkWebserviceRequest createRightTableRequest(final List<Object> queryParameters,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(createSearchRequest(queryParameters, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		request.setGtnReportRequest(createReportRequest(queryParameters));
		return request;
	}

	private GtnUIFrameworkWebserviceResponse callWebService(final String webServiceUrl,
			final GtnUIFrameworkWebserviceRequest request, GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		return client.callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE + webServiceUrl,
				dualListBoxConfig.getModuleType(), request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
