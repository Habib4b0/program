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
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Map<String, String> mandatoryCheckMessageMap = configureMessageMap();

		String message = validatesFieldsForPP(mandatoryCheckMessageMap);

		if (!"".equals(message)) {
			throw new GtnFrameworkValidationFailedException(
					"Information for the following Mandatory fields need to be provided: \n" + message, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	private Map<String, String> configureMessageMap() {
		Map<String, String> validationMessageMap = new HashMap<>();
		validationMessageMap.put(GtnFrameworkCommonConstants.TEMP_COUNT,
				GtnFrameworkCommonConstants.PS_ALERT_MSG);
		validationMessageMap.put(GtnFrameworkCommonConstants.TEMP_CHECKED_COUNT,
				"Select atleast one Item in Price Protection tab for PS");
		validationMessageMap.put("PPStartDateEqual",
				" Price Protection Start Date and Price Protection End date should not be equal for selected Item");
		validationMessageMap.put("PPStartDateLess",
				" Price Protection Start Date is less than Price Protection End date for selected Item");
		validationMessageMap.put("PPStartDateNull",
				" Price Protection Start Date is Required");
		return validationMessageMap;
	}

	private String validatesFieldsForPP(Map<String, String> mandatoryCheckMsgMap) {
		StringBuilder subMsg = new StringBuilder();

		for (Map.Entry<String, String> entry : mandatoryCheckMsgMap.entrySet()) {
			if (!validateFields(entry.getKey())) {
				subMsg.append(entry.getValue());
				break;
			}

		}

		return subMsg.toString();
	}

	private boolean validateFields(String processName) {
		boolean isDuplicate = false;

		GtnUIFrameworkWebServiceClient wsClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest requestValidate = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest generalWSRequestValidate = new GtnWsGeneralRequest();

		generalWSRequestValidate.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequestValidate.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		requestValidate.setGtnWsGeneralRequest(generalWSRequestValidate);
		List<Object> inputList = new ArrayList<>();
		inputList.add(processName);
		generalWSRequestValidate.setComboBoxWhereclauseParamList(inputList);

		GtnUIFrameworkWebserviceResponse validationResponse = wsClient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_PP_VALIDATION_SERVICE, requestValidate,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int result = Integer.parseInt(String.valueOf(validationResponse.getOutBountData()[0]));

		if (processName.equalsIgnoreCase(GtnFrameworkCommonConstants.TEMP_COUNT)
				|| processName.equalsIgnoreCase(GtnFrameworkCommonConstants.TEMP_CHECKED_COUNT)) {
			isDuplicate = result > 0;
		} else {
			isDuplicate = result == 0;
		}

		return isDuplicate;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
