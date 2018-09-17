package com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;

public class GtnUIFrameworkContractHeaderCommonValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		StringBuilder chErrorMsg = new StringBuilder();
		String[] fields = GtnUIFrameworkContractHeaderStringContants.getDoActionFields();
		GtnUIFrameworkGlobalUI.validateFields(fields, chErrorMsg);

		if (chErrorMsg.length() > 0) {
			String msg = GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONTRACT_MANDATORY_FIELDS_VALIDATION
					+ " <br> " + chErrorMsg.toString();

			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsContractHeaderRequest imRequest = new GtnWsContractHeaderRequest();
		GtnWsContractMasterBean infoBean = new GtnWsContractMasterBean();
		infoBean.setContractId(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_ID)
				.getStringFromField());
		infoBean.setContractNo(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_NO)
				.getStringFromField());
		Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid");
		infoBean.setContractMasterSid(systemId == null ? 0 : systemId);

		imRequest.setGtnWsContractMasterBean(infoBean);

		gtnRequest.setGtnWsContractHeaderRequest(imRequest);

		GtnUIFrameworkWebserviceResponse response = callContractHeaderValidationService(gtnRequest);
		GtnWsContractHeaderResponse reponseBean = response.getGtnWsContractHeaderResponse();
		if (reponseBean.isContractIdDuplicate()) {
			throw new GtnFrameworkValidationFailedException(
					GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONTRACT_ID_VALIDATION,
					GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_ID);
		}
		if (reponseBean.isContractNoDuplicate()) {
			throw new GtnFrameworkValidationFailedException(
					GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONTRACT_NO_VALIDATION,
					GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_NO);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

    public GtnUIFrameworkWebserviceResponse callContractHeaderValidationService(GtnUIFrameworkWebserviceRequest gtnRequest) {
        return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE
                        + GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_ID_NO_VALIDATION_SERVICE,
                gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());  
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
