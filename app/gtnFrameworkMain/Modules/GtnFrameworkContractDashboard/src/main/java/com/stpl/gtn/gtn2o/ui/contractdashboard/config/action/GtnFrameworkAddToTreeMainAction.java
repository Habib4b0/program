/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkAddToTreeMainAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();

		GtnWsRecordBean tableBean = (GtnWsRecordBean) parameters.get(2);
		GtnWsRecordBean treeBean = (GtnWsRecordBean) parameters.get(3);

		GtnUIFrameworkBaseComponent cdTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setTableBean(tableBean);
		cdRequest.setTreeBean(treeBean);
		cdRequest.setRecordBeanList(cdTreeBaseComponent.getTreeNodes());

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.ADD_TO_TREE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdNewResponse = newResponse.getGtnWsContractDashboardResponse();
		if (cdNewResponse.isSuccess()) {
			int tableLevel = cdNewResponse.getTableBean().getIntegerPropertyByIndex(7);
			cdTreeBaseComponent.addItemToTreeDataTable(parameters.get(3), cdNewResponse.getTableBean(),
					tableLevel != 5);
			String tableCategory = cdNewResponse.getTableBean().getStringPropertyByIndex(8);
			String tableId = cdNewResponse.getTableBean().getStringPropertyByIndex(0);
			String tableNo = cdNewResponse.getTableBean().getStringPropertyByIndex(1);
			String tableName = cdNewResponse.getTableBean().getStringPropertyByIndex(2);
			String caption = tableCategory + "-" + tableId + "-" + tableNo + "-" + tableName;
			cdTreeBaseComponent.setTreeItemCaption(cdNewResponse.getTableBean(), caption);
			cdTreeBaseComponent.expandTreeItem(parameters.get(3));
			return;
		}
		if (cdNewResponse.getMessageType().equals(GtnFrameworkCommonStringConstants.WARNING)) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION,
					cdNewResponse.getMessageHeader(), cdNewResponse.getMessage());
			return;
		}
		if (cdNewResponse.getMessageType().equals(GtnFrameworkCommonStringConstants.ERROR)) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION, cdNewResponse.getMessageHeader(),
					cdNewResponse.getMessage());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkAddToTreeMainAction();
	}

}
