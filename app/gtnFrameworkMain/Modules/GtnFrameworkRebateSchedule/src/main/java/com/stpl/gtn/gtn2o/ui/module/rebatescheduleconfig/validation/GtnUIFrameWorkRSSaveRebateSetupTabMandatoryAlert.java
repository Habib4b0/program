
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String subMessage;
		if (gtnUIFrameWorkActionConfig.getActionParameterList().size() > 1
				&& gtnUIFrameWorkActionConfig.getActionParameterList().get(1).equals("populate")) {
			subMessage = validateTempTableCount();
		} else {
			subMessage = validateFields();
		}

		if (!"".equals(subMessage)) {
			throw new GtnFrameworkValidationFailedException(subMessage, componentId);

		}

	}

	private String validateTempTableCount() {
		String subMessage = "";

		if (!validate(GtnFrameworkRSConstants.TEMP_CHECKED_COUNT)) {
			subMessage = subMessage + "No items have been selected.  Please select at least one item.";
		}

		return subMessage;
	}

	private String validateFields() {
		String subMessage = "";

		if (!validate("tempCount")) {
			subMessage = subMessage + "Select atleast one IFP";
		} else if (!validate(GtnFrameworkRSConstants.TEMP_CHECKED_COUNT)) {
			subMessage = subMessage + "No items have been selected.  Please select at least one item.";
		} else if (!validate("Status")) {
			subMessage = subMessage + "RS Status is Mandatory";
		} else if (!validate("StartDateNull")) {
			subMessage = subMessage + " Start Date is Mandatory";
		}

		return subMessage;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert();
	}

	private boolean validate(String processName) {
		boolean isDuplicated = false;

		GtnUIFrameworkWebServiceClient rsWebServiceClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest rsWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest rsGeneralWSRequest = new GtnWsGeneralRequest();

		rsGeneralWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		rsGeneralWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		rsWebserviceRequest.setGtnWsGeneralRequest(rsGeneralWSRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(processName);
		rsGeneralWSRequest.setComboBoxWhereclauseParamList(inputList);

		GtnUIFrameworkWebserviceResponse response = rsWebServiceClient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.RS_VALIDATION_SERVICE, rsWebserviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int result = Integer.parseInt(String.valueOf(response.getOutBountData()[0]));

		if (processName.equalsIgnoreCase("tempCount")
				|| processName.equalsIgnoreCase(GtnFrameworkRSConstants.TEMP_CHECKED_COUNT)) {
			isDuplicated = result > 0;

		}
		if (processName.equalsIgnoreCase("Status") || processName.equalsIgnoreCase("StartDateNull")) {
			isDuplicated = result == 0;

		}

		return isDuplicated;
	}

}
