/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import java.util.Iterator;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractRebuildAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnWsRecordBean bean = (GtnWsRecordBean) tableBaseComponent.getValueFromComponent();
		String message = parameters.get(3).toString()
				.replace(parameters.get(4).toString(), bean.getStringPropertyByIndex(0))
				.replace(parameters.get(5).toString(), bean.getStringPropertyByIndex(1));
		GtnUIFrameWorkActionConfig failureActionConfig = new GtnUIFrameWorkActionConfig();
		failureActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);
		if (bean.getBooleanPropertyByIndex(14)) {
			failureActionConfig.addActionParameter(parameters.get(2));
			failureActionConfig.addActionParameter(message);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, failureActionConfig);
			return;
		}
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setContractId(bean.getIntegerPropertyByIndex(6));
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setParentBean(bean);
		request.setGtnWsSearchRequest(searchRequest);
		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.GET_CONTRACT_TO_REBUILD,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdResponse = newResponse.getGtnWsContractDashboardResponse();
		GtnUIFrameworkBaseComponent cdTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(6).toString());
		if (isDuplicate(cdTreeBaseComponent.getRootNodes(), cdResponse.getTreeBean())) {
			failureActionConfig.addActionParameter(parameters.get(7));
			failureActionConfig.addActionParameter(parameters.get(8));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, failureActionConfig);
			return;
		}
		recursiveAddToTree(cdTreeBaseComponent, null, cdResponse.getTreeBean());
	}

	private void recursiveAddToTree(GtnUIFrameworkBaseComponent cdTreeBaseComponent, GtnWsRecordBean parentBean,
			GtnWsRecordBean bean) throws GtnFrameworkGeneralException {
		addToTree(cdTreeBaseComponent, parentBean, bean);
		if (bean.getChildList() != null && !bean.getChildList().isEmpty()) {
			for (GtnWsRecordBean childBean : bean.getChildList()) {
				recursiveAddToTree(cdTreeBaseComponent, bean, childBean);
			}
		}
	}

	private boolean isDuplicate(List<GtnWsRecordBean> nodeList, GtnWsRecordBean tableBean) {
		boolean flag = false;
		Iterator<GtnWsRecordBean> itr = nodeList.iterator();
		while (itr.hasNext()) {
			GtnWsRecordBean availableBean = itr.next();
			if (availableBean.getIntegerPropertyByIndex(4) == tableBean.getIntegerPropertyByIndex(4)) {
				flag = true;
			}
		}
		return flag;
	}

	private void addToTree(GtnUIFrameworkBaseComponent cdTreeBaseComponent, GtnWsRecordBean parentBean,
			GtnWsRecordBean bean) throws GtnFrameworkGeneralException {
		int tableLevel = bean.getIntegerPropertyByIndex(7);
		cdTreeBaseComponent.addItemToTreeDataTable(parentBean, bean, tableLevel != 5);
		String tableCategory = bean.getStringPropertyByIndex(8);
		String tableId = bean.getStringPropertyByIndex(0);
		String tableNo = bean.getStringPropertyByIndex(1);
		String tableName = bean.getStringPropertyByIndex(2);
		String caption = tableCategory + "-" + tableId + "-" + tableNo + "-" + tableName;
		cdTreeBaseComponent.setTreeItemCaption(bean, caption);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}