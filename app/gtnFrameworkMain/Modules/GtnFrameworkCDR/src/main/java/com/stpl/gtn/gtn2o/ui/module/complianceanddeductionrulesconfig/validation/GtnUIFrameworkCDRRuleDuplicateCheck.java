package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractField;

public class GtnUIFrameworkCDRRuleDuplicateCheck implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode").toString();

		if (!mode.equalsIgnoreCase("Edit")) {

			if (validate()) {
				List<Object> actionParams = new ArrayList<>();
				actionParams.add("Cannot Save Rule");
				actionParams.add("Same Rule No or Rule Name already exists.");
				GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();

				gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

				GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
				alert.configureParams(gtnUIFrameWorkActionConf);
				alert.doAction(componentId, gtnUIFrameWorkActionConf);

				throw new GtnFrameworkGeneralException("Custom validation failed");

			}
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean validate() throws GtnFrameworkValidationFailedException {
		boolean isDuplicated = false;

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMap = new HashMap<>();

		inputMap.put("ruleName", String.valueOf(
				((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleInformationRuleName")).getValue()));
		inputMap.put("ruleNo", String.valueOf(
				((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleInformationRuleNo")).getValue()));
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.CDR_SERVICE + "/" + GtnWsCDRContants.IS_RULE_EXIST_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int result = Integer.parseInt(String.valueOf(response.getOutBountData()[0]));

		if (result > 0) {
			isDuplicated = true;

		}

		return isDuplicated;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRRuleDuplicateCheck();
	}

}
