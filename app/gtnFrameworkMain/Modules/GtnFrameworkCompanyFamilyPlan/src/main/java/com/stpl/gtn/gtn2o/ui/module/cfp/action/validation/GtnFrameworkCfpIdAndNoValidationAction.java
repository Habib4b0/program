package com.stpl.gtn.gtn2o.ui.module.cfp.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCfpIdAndNoValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpIdAndNoValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpIdAndNoValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		validateCfp(componentId);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();
		GtnCFamilyPlan cfpBean = new GtnCFamilyPlan();
		GtnCFamilyPlanInformation cfpInfpBean = new GtnCFamilyPlanInformation();
		cfpInfpBean
				.setCfpId(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCFPId").getStringFromField());
		cfpInfpBean
				.setCfpNo(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCFPNo").getStringFromField());
		cfpInfpBean.setCfpSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("cfpModelSid"));
		cfpBean.setCfpInfo(cfpInfpBean);
		cfpRequest.setGtnCFamilyPlan(cfpBean);
		request.setGtnWsCfpRequest(cfpRequest);
		GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_CFPID_CFPNO_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		boolean iscfpID = (boolean) reponse.getEditRecord().get("cfpId");
		boolean isCfpNo = (boolean) reponse.getEditRecord().get("cfpNo");

		if (iscfpID || isCfpNo) {
			String msg = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			if (iscfpID && isCfpNo) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_ID_AND_NO_DUPLICATE_VALIDATION_MSG;
			} else if (iscfpID) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_ID_DUPLICATE_VALIDATION_MSG;
			} else {
				msg += GtnFrameworkCfpStringContants.GTN_CFP_NO_DUPLICATE_VALIDATION_MSG;
			}

			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void validateCfp(String componentId) throws GtnFrameworkGeneralException {

		StringBuilder cfpFieldMsg = new StringBuilder();
		GtnUIFrameworkGlobalUI.validateFields(GtnFrameworkCfpStringContants.getCfpValidateFields(), cfpFieldMsg);

		if (cfpFieldMsg.length() > 0) {
			String msg = "Information for the following Mandatory fields need to be provided: "
					+ cfpFieldMsg.toString();
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

}
