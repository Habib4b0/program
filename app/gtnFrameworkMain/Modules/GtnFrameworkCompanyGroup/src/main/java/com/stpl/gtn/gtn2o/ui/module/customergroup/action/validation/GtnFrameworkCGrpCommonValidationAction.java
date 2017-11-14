package com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCGrpCommonValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpCommonValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCGrpCommonValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));

		GtnCompanyGrpValidationBean valBean = new GtnCompanyGrpValidationBean();
		valBean.setCompanyGrpName(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpName").getStringFromField());
		valBean.setCompanyGrpNo(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpNo").getStringFromField());
		valBean.setCompanyGrpMasterSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("companyGrpSid"));
		GtnCompanyGroupRequest cmRequest = new GtnCompanyGroupRequest();
		cmRequest.setGtnCompanyGrpValidationBean(valBean);
		request.setGtnCompanyGroupRequest(cmRequest);
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
						+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_COMMON_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		GtnCompanyGrpValidationBean validationBean = response.getGtnWsCompanyGroupResponse()
				.getGtnCompanyGrpValidationBean();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		if (validationBean.isCompanyGrpNameExist()) {
			String msg = GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_DUPLICATION_NAME;
			Object errorMsg = GtnFrameworkCommonStringConstants.ERROR;
			alertActionConfig.setActionParameterList(Arrays.asList(errorMsg, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCGrpStringContants.VALIDATAION_ERROR + msg);
		}
		if (validationBean.isCompanyGrpNoExist()) {
			String msg = GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_DUPLICATION_NO;
			Object errorMsg = GtnFrameworkCommonStringConstants.ERROR;
			alertActionConfig.setActionParameterList(Arrays.asList(errorMsg, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCGrpStringContants.VALIDATAION_ERROR + msg);
		}
		if (validationBean.getImtdCount() == 0) {
			Object errorMsg = GtnFrameworkCommonStringConstants.ERROR;
			alertActionConfig.setActionParameterList(Arrays.asList(errorMsg,
					GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_SELECT_ONE_CUSTOMER));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCGrpStringContants.VALIDATAION_ERROR
					+ GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_SELECT_ONE_CUSTOMER);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
