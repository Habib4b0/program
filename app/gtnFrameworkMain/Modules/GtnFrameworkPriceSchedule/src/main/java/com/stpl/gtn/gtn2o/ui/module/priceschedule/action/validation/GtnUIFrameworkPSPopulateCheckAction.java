/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkPSPopulateCheckAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		if (!validateDuplication()) {
			List<Object> actionParams = new ArrayList<>();
			actionParams.add("Populate Error");
			actionParams.add("Please Select a field to Populate");
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();

			gtnUIFrameWorkActionConf.setActionParameterList(actionParams);
			GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
			alert.configureParams(gtnUIFrameWorkActionConf);
			alert.doAction(componentId, gtnUIFrameWorkActionConf);

			throw new GtnFrameworkGeneralException("Custom validation failed");

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean validateDuplication() {
		String checkedCount = "tempCheckedCount";
		boolean isDuplicated = false;

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest frameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		frameworkWebserviceRequest.setGtnWsGeneralRequest(generalRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(checkedCount);
		generalRequest.setComboBoxWhereclauseParamList(inputList);

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = client.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_VALIDATION_SERVICE,
				frameworkWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int result = Integer.parseInt(String.valueOf(gtnUIFrameworkWebserviceResponse.getOutBountData()[0]));

		if (checkedCount.equalsIgnoreCase("tempCheckedCount") && result > 0) {
			isDuplicated = true;
		}

		return isDuplicated;
	}
}
