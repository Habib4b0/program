/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnFrameworkContractPriceProtectionTabAddLineAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String queryName = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		addCopyDeleteAction(componentId, queryName, gtnUIFrameWorkActionConfig);

	}

	private void addCopyDeleteAction(String componentId, String queryName,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		GtnWsContractDashboardSessionBean psInfoBean = new GtnWsContractDashboardSessionBean();

		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		GtnWsContractDashboardRequest getGtnWsPriceScheduleGeneralRequest = new GtnWsContractDashboardRequest();
		String addCopyIndicatorContract = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);

		List<Object> inputList = new ArrayList<>();
		inputList.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		inputList.add(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		inputList.add(queryName);
		inputList.add(addCopyIndicatorContract);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		getGtnWsPriceScheduleGeneralRequest.setContractDashboardBean(psInfoBean);
		request.setGtnWsContractDashboardRequest(getGtnWsPriceScheduleGeneralRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ "/contractPriceSchedulePriceProtectionTab",
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		int resultContract = Integer.parseInt(String.valueOf(response.getOutBountData()[0]));
		String msg = String.valueOf(response.getOutBountData()[1]);
		if (resultContract == 0 && "checkRecord".equals(msg)) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> paramsList = new ArrayList<>();
			paramsList.add("Error");
			paramsList.add("Please first check mark a record in order to continue.");
			alertActionConfig.setActionParameterList(paramsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		} else if (resultContract == 0 && "unableToRemove".equals(msg) && addCopyIndicatorContract.equals("D")) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertParaList = new ArrayList<>();
			alertParaList.add("Error");
			alertParaList.add(
					"Only records that have been created using the Add Line functionality, Copy Line functionality, or that was interfaced into the application can be removed using this command button.");
			alertActionConfig.setActionParameterList(alertParaList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		}

		GtnUIFrameworkComponentData resultTable = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("PricingTabPricingTable");
		GtnUIFrameworkPagedTableLogic tableLogicContract = resultTable.getCurrentPageTableLogic();
		tableLogicContract.startSearchProcess(new ArrayList<String>(), true);

	}

	@Override
	public GtnFrameworkContractPriceProtectionTabAddLineAction createInstance() {
		return this;
	}

}
