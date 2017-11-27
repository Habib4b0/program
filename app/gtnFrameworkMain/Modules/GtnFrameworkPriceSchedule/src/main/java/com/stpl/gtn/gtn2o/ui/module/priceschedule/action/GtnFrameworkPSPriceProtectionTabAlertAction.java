/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Deepika.krishnakumar
 */
public class GtnFrameworkPSPriceProtectionTabAlertAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
            // empty method    
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        Map<String, String> mandatoryCheckMsgMap = configureMsgMap();

		String subMessage = validates(mandatoryCheckMsgMap);

		if (!"".equals(subMessage)) {
			throw new GtnFrameworkValidationFailedException(
					"Information for the following Mandatory fields need to be provided: \n" + subMessage, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
    }
            private Map<String, String> configureMsgMap() {
		Map<String, String> validationMsgMap = new HashMap<>();
		validationMsgMap.put(GtnFrameworkCommonConstants.TEMP_COUNT,
				"Add atleast One Item in Item Addition tab for PS");
		validationMsgMap.put(GtnFrameworkCommonConstants.TEMP_CHECKED_COUNT,
				"Select atleast one Item in Price Protection tab for PS");
		validationMsgMap.put("PPStartDateNull", " Price Protection Start Date  required for selected Item");
		validationMsgMap.put("PPStartDateEqual", " Price Protection Start Date and Price Protection End date should not be equal for selected Item");
		validationMsgMap.put("PPStartDateLess", " Price Protection Start Date is less than Price Protection End date for selected Item");
		return validationMsgMap;
	}
            
            private String validates(Map<String, String> mandatoryCheckMsgMap) {
		StringBuilder subMessage = new StringBuilder();

		for (Map.Entry<String, String> entry : mandatoryCheckMsgMap.entrySet()) {
			if (!validate(entry.getKey())) {
				subMessage.append(entry.getValue());
				break;
			}

		}

		return subMessage.toString();
	}
            private boolean validate(String processName) {
		boolean isDuplicated = false;

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		request.setGtnWsGeneralRequest(generalWSRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(processName);
		generalWSRequest.setComboBoxWhereclauseParamList(inputList);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_PP_VALIDATION_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int result = Integer.parseInt(String.valueOf(response.getOutBountData()[0]));

		if (processName.equalsIgnoreCase(GtnFrameworkCommonConstants.TEMP_COUNT)
				|| processName.equalsIgnoreCase(GtnFrameworkCommonConstants.TEMP_CHECKED_COUNT)) {
			isDuplicated = result > 0;
		} else {
			isDuplicated = result == 0;
		}

		return isDuplicated;
	}
    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
