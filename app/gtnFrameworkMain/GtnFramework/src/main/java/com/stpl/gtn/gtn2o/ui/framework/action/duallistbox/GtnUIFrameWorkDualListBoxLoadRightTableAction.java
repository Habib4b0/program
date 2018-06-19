/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.v7.ui.TreeTable;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkDualListBoxLoadRightTableAction implements GtnUIFrameWorkAction {

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
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) dualListBoxData.getCustomData();
		loadRightTable(dualListBoxBean, isMoveAll, componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadRightTable(GtnFrameworkDualListBoxBean dualListBoxBean, boolean isMoveAll, String componentId)
			throws GtnFrameworkGeneralException {
		ExtFilterTable leftTable = dualListBoxBean.getLeftTable();
		GtnUIFrameworkDualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		TreeTable rightTable = dualListBoxBean.getRightTable();
		if (!isMoveAll && leftTable.getValue() == null) {
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
						.add(((GtnWsRecordBean) leftTable.getValue()).getAdditionalProperties().get(1).toString());
			}
			inputPutList.add(hierarchyNoList);

			inputPutList.add(dualListBoxConfig.getFileName());
			inputPutList.add(isMoveAll);
			GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getMoveRightURL(),
					dualListBoxConfig.getModuleType(), createRightTableRequest(inputPutList, dualListBoxConfig));

			List<GtnWsRecordBean> gtnWsRecordBeanList = response.getGtnUIFrameworkWebserviceDualListBoxResponse()
					.getDualListBoxTableDataList();
			if (gtnWsRecordBeanList != null) {
				treeBuilder.buildTree(gtnWsRecordBeanList);
				treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
			}
		}
	}

	private GtnUIFrameworkWebserviceRequest createRightTableRequest(final List<Object> queryParameters,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(createSearchRequest(queryParameters, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		return request;
	}

	private GtnWsSearchRequest createSearchRequest(final List<Object> queryParameters,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
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
		return client.callGtnWebServiceUrl(webServiceUrl, moduleName, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}