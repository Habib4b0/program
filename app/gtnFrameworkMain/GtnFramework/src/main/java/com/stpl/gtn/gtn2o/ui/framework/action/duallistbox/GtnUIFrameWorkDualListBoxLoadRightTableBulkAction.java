package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.TreeTable;

public class GtnUIFrameWorkDualListBoxLoadRightTableBulkAction implements GtnUIFrameWorkAction {

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
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) dualListBoxData.getCustomData();
		List<String> inputList = (List<String>) actionParametersList.get(1);
		loadRightTableBulk(inputList, dualListBoxBean);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadRightTableBulk(List<String> inputList, GtnFrameworkDualListBoxBean dualListBoxBean) {
		GtnUIFrameworkDualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		TreeTable rightTable = dualListBoxBean.getRightTable();
		List<Object> parameterList = new ArrayList<>(3);
		parameterList.add("loadbulkdata");
		parameterList.add(inputList);
		parameterList.add(dualListBoxConfig.getFileName());
		GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getMoveRightURL(),
				createRightTableRequest(parameterList, dualListBoxConfig), dualListBoxConfig);
		List<GtnWsRecordBean> gtnWsRecordBeanList = response.getGtnUIFrameworkWebserviceDualListBoxResponse()
				.getDualListBoxTableDataList();
		if (gtnWsRecordBeanList != null) {
			treeBuilder.buildTree(gtnWsRecordBeanList);
			treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
		}
	}

	private GtnWsSearchRequest createSearchRequest(final List<Object> queryParameters,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setQueryInputList(queryParameters);
		searchRequest.setSearchColumnNameList(new ArrayList<Object>(dualListBoxConfig.getRecordHeader()));
		queryParameters.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		return searchRequest;
	}

	private GtnWsGeneralRequest createGeneralRequest() {
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		return generalRequest;
	}

	private GtnUIFrameworkWebserviceRequest createRightTableRequest(final List<Object> queryParameters,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(createSearchRequest(queryParameters, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		return request;
	}

	private GtnUIFrameworkWebserviceResponse callWebService(final String webServiceUrl,
			final GtnUIFrameworkWebserviceRequest request, GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		return client.callGtnWebServiceUrl(webServiceUrl, dualListBoxConfig.getModuleType(), request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
