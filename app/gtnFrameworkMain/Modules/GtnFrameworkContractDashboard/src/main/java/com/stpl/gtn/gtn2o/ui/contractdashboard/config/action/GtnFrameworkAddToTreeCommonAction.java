/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
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
public class GtnFrameworkAddToTreeCommonAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent cdTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnWsRecordBean tableBean1 = (GtnWsRecordBean) parameters.get(2);
		GtnWsRecordBean tableBean = new GtnWsRecordBean();
		tableBean.setProperties(new ArrayList<>(tableBean1.getProperties()));
		Object selectedTreeValue = parameters.get(3);
		if (selectedTreeValue == null) {
			doForNonSelectedTreeValue(cdTreeBaseComponent, componentId, parameters, tableBean);
			return;
		}
		doForSelectedTreeValue(selectedTreeValue, componentId, parameters, tableBean);
	}

	private void confirmAddToTree(String componentId, List<Object> parameters,
			GtnWsContractDashboardResponse cdNewResponse) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(cdNewResponse.getMessageHeader());
		confirmActionConfig.addActionParameter(cdNewResponse.getMessage());
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkAddToTreeMainAction.class.getName());
		addActionConfig.addActionParameter(parameters.get(1));
		addActionConfig.addActionParameter(cdNewResponse.getTableBean());
		addActionConfig.addActionParameter(parameters.get(3));
		addActionConfig.addActionParameter(parameters.get(4));
		addActionConfig.addActionParameter(parameters.get(5));
		addActionConfig.addActionParameter(parameters.get(6));
		successActionConfigList.add(addActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
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

	public static String getReplacedString(String... value) {
		StringBuilder sql = new StringBuilder(value[0]);
		for (int i = 1; i < value.length; i++) {
			sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, value[i]);
		}
		return sql.toString();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void doForSelectedTreeValue(Object selectedTreeValue, String componentId, List<Object> parameters,
			GtnWsRecordBean tableBean) throws GtnFrameworkGeneralException {
		GtnWsRecordBean treeBean = (GtnWsRecordBean) selectedTreeValue;
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setTableBean(tableBean);
		cdRequest.setTreeBean(treeBean);

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.CHECK_ADD_TO_TREE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdNewResponse = newResponse.getGtnWsContractDashboardResponse();
		if (cdNewResponse.isSuccess()) {
			GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
			addActionConfig.addActionParameter(GtnFrameworkAddToTreeMainAction.class.getName());
			addActionConfig.addActionParameter(parameters.get(1));
			addActionConfig.addActionParameter(cdNewResponse.getTableBean());
			addActionConfig.addActionParameter(parameters.get(3));
			addActionConfig.addActionParameter(parameters.get(4));
			addActionConfig.addActionParameter(parameters.get(5));
			addActionConfig.addActionParameter(parameters.get(6));
			addActionConfig.addActionParameter(parameters.get(7));
			GtnUIFrameworkActionExecutor.executeCustomAction(componentId, addActionConfig);
			return;
		}
		if (cdNewResponse.getMessageType().equals(GtnFrameworkCommonStringConstants.WARNING)) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION,
					cdNewResponse.getMessageHeader(), cdNewResponse.getMessage());
			return;
		}
		if (cdNewResponse.getMessageType().equals(GtnFrameworkCommonStringConstants.CONFIRMATION)) {
			cdRequest.setTableBean(cdNewResponse.getTableBean());
			cdRequest.setTreeBean(cdNewResponse.getTreeBean());
			confirmAddToTree(componentId, parameters, cdNewResponse);
		}
	}

	private void doForNonSelectedTreeValue(GtnUIFrameworkBaseComponent cdTreeBaseComponent, String componentId,
			List<Object> parameters, GtnWsRecordBean tableBean) throws GtnFrameworkGeneralException {
		int tableLevel = tableBean.getIntegerPropertyByIndex(7);
		String tableCategory = tableBean.getStringPropertyByIndex(8);
		if (tableLevel != 1) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION,
					parameters.get(4).toString(), getReplacedString(parameters.get(5).toString(), tableCategory));
			return;
		}
		if (isDuplicate(cdTreeBaseComponent.getRootNodes(), tableBean)) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION,
					parameters.get(6).toString(), parameters.get(7).toString());
			return;
		}
		cdTreeBaseComponent.addItemToTreeDataTable(null, tableBean, true);
		String tableId = tableBean.getStringPropertyByIndex(0);
		String tableNo = tableBean.getStringPropertyByIndex(1);
		String tableName = tableBean.getStringPropertyByIndex(2);
		String caption = tableCategory + "-" + tableId + "-" + tableNo + "-" + tableName;
		cdTreeBaseComponent.setTreeItemCaption(tableBean, caption);
	}

}
