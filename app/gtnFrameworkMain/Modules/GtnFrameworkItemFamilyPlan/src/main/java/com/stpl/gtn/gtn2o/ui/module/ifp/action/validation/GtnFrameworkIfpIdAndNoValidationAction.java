package com.stpl.gtn.gtn2o.ui.module.ifp.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkIfpIdAndNoValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpIdAndNoValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpIdAndNoValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkBaseComponent ifpId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPId");
		GtnUIFrameworkBaseComponent ifpNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPNo");
		GtnUIFrameworkBaseComponent ifpName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPName");
		GtnUIFrameworkBaseComponent ifpStatus = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ifpInformationTabIFPStatus");
		GtnUIFrameworkBaseComponent ifpStartDate = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ifpInformationIFPStartDate");

		StringBuilder ifpFieldMsg = new StringBuilder();

		Object[] ifpFieldValues = new Object[] { ifpId.getStringFromField(), ifpNo.getStringFromField(),
				ifpName.getStringFromField(), ifpStatus.getIntegerFromField(), ifpStartDate.getDateFromDateField() };
		String[] ifpFields = new String[] { ifpId.getCaption(), ifpNo.getCaption(), ifpName.getCaption(),
				ifpStatus.getCaption(), ifpStartDate.getCaption() };
		validateFields(ifpFields, ifpFieldValues, ifpFieldMsg);

		if (ifpFieldMsg.length() > 0) {
			String msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_MADATORY_FIELDS
					+ GtnFrameworkCommonStringConstants.HTML_BREAK + ifpFieldMsg.toString();
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest ifpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfpBean = new GtnIFamilyPlanInformationBean();
		ifpInfpBean
				.setIfpId(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPId").getStringFromField());
		ifpInfpBean
				.setIfpNo(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPNo").getStringFromField());
		ifpInfpBean.setIfpSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid"));
		ifpBean.setIfpInfo(ifpInfpBean);
		ifpRequest.setGtnIFamilyPlan(ifpBean);
		request.setGtnWsIfpRequest(ifpRequest);
		GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_IFPID_IFPNO_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		boolean isIfpId = (boolean) reponse.getEditRecord().get("ifpId");
		boolean isIfpNo = (boolean) reponse.getEditRecord().get("ifpNo");

		if (isIfpId || isIfpNo) {
			String msg = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			if (isIfpId && isIfpNo) {
				msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_IFP_IDANDNO;
			} else if (isIfpId) {
				msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_IFP_ID;
			} else {
				msg += GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_IFP_NO;
			}
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public static StringBuilder validateFields(String[] fields, Object[] values, StringBuilder errorMessage) {
		String appender = " ";
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || values[i] instanceof String && String.valueOf(values[i]).isEmpty()
					|| values[i] instanceof Integer && Integer.valueOf(String.valueOf(values[i])) == 0) {
				errorMessage.append(appender).append(fields[i]);
				appender = " , ";
			}
		}
		return errorMessage;
	}
}
