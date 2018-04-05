package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnWsCMasterQualifierBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCompanyMasterIdEditListDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkCompanyMasterIdEditListDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering inside configureParams ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int compQualifierFromList = GtnWsNumericConstants.TWO;
		List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnWsRecordBean selectedId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameter.get(1).toString())
				.getValueFromDataTable();
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnCMasterRequest cmRequest = new GtnCMasterRequest();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCMasterQualifierBean qualifierBean = new GtnWsCMasterQualifierBean();

		qualifierBean.setCompanyQualifierSid((Integer) selectedId
				.getPropertyValueByIndex(selectedId.getProperties().size() - compQualifierFromList));
		cmRequest.setGtnCMasterQualifierBean(qualifierBean);
		request.setGtnCMasterRequest(cmRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
						+ GtnWebServiceUrlConstants.GTN_CM_IDENTIFIER_QUALIFIER_VALIDATION,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if (response != null && response.getGtnCompanyMasterResponse() != null) {
			if (response.getGtnCompanyMasterResponse().isIndentifierQualfierUsed()) {
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
				Object failedMsg = GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_DELETE_FAILED_MSG_HEADER;
				alertActionConfig.setActionParameterList(Arrays.asList(failedMsg,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_VALIDATION_MSG_DELETE_003));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkValidationFailedException(
						GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_VALIDATION_MSG_DELETE_003, componentId);
			} else {
				cmRequest = new GtnCMasterRequest();
				request = new GtnUIFrameworkWebserviceRequest();
				qualifierBean = new GtnWsCMasterQualifierBean();
				qualifierBean.setCompanyQualifierSid((Integer) selectedId
						.getPropertyValueByIndex(selectedId.getProperties().size() - compQualifierFromList));
				qualifierBean.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				cmRequest.setGtnCMasterQualifierBean(qualifierBean);
				request.setGtnCMasterRequest(cmRequest);
				wsclient.callGtnWebServiceUrl(
						GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
								+ GtnWebServiceUrlConstants.GTN_CM_QUALIFIER_DELETE_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
