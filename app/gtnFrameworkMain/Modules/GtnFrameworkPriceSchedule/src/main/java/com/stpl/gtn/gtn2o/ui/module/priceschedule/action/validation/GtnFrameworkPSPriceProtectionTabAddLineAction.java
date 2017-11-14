/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnFrameworkPSPriceProtectionTabAddLineAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String queryPS = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		addCopyDeleteActionPS(componentId, queryPS, gtnUIFrameWorkActionConfig);

	}

	private void addCopyDeleteActionPS(String componentId, String queryPS,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		GtnUIFrameWorkPSInfoBean psInfoBean = new GtnUIFrameWorkPSInfoBean();

		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = new GtnWsPriceScheduleGeneralRequest();
		String addCopyIndicator = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);

		List<Object> inputList = new ArrayList<>();
		inputList.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		inputList.add(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		inputList.add(queryPS);
		inputList.add(addCopyIndicator);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		getGtnWsPriceScheduleGeneralRequest.setPsInfoBean(psInfoBean);
		request.setGtnWsPriceScheduleGeneralRequest(getGtnWsPriceScheduleGeneralRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/priceSchedulePriceProtectionTab", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		int responseResult = Integer.parseInt(String.valueOf(response.getOutBountData()[0]));
		String msg = String.valueOf(response.getOutBountData()[1]);
		if ((responseResult == 0) && "checkRecord".equals(msg)) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgParamsList = new ArrayList<>();
			alertMsgParamsList.add("Error");
			alertMsgParamsList.add("Please first check mark a record in order to continue.");
			alertActionConfig.setActionParameterList(alertMsgParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		} else if (responseResult == 0 && "unableToRemove".equals(msg) && addCopyIndicator.equals("D")) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertParameterList = new ArrayList<>();
			alertParameterList.add("Error");
			alertParameterList.add(
					"Only records that have been created using the Add Line functionality, Copy Line functionality, or that was interfaced into the application can be removed using this command button.");
			alertActionConfig.setActionParameterList(alertParameterList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		}

		GtnUIFrameworkComponentData priceProtectiontable = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("psPriceProtectionTabResultDataTable");
		GtnUIFrameworkPagedTableLogic tableLogicPs = priceProtectiontable.getCurrentPageTableLogic();
		tableLogicPs.startSearchProcess(new ArrayList<String>(), true);

	}

	@Override
	public GtnFrameworkPSPriceProtectionTabAddLineAction createInstance() {
		return this;
	}

}
