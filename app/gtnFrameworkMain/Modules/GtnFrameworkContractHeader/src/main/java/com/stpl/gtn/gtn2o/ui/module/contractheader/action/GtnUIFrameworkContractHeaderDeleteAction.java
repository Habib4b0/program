package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnUIFrameworkContractHeaderDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsContractMasterBean imBean = new GtnWsContractMasterBean();

		imBean.setContractMasterSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid"));
		GtnWsContractHeaderRequest imRequest = new GtnWsContractHeaderRequest();
		imRequest.setGtnWsContractMasterBean(imBean);
		gtnRequest.setGtnWsContractHeaderRequest(imRequest);

		GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE
						+ GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_DELETE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
		if (reponse.getGtnWsContractHeaderResponse().isContractMasterUsed()) {
			String msg = GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONTRACT_HEADER_DELETE_VALIDATION;
			alertActionConfig.setActionParameterList(Arrays.asList(new Object[] { "Cannot Delete", msg }));
			alertAction.doAction(componentId, alertActionConfig);
			GtnUIFrameworkGlobalUI.addSessionProperty("contractMasterSid", 0);
			throw new GtnFrameworkValidationFailedException("Delete Error :" + msg);
		}
		GtnUIFrameWorkActionConfig notifActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		notifActionConfig.addActionParameter("Delete Success");
		notifActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notifActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
