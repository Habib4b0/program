package com.stpl.gtn.gtn2o.ui.module.ifp.action.validation;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.Date;

public class GtnFrameworkIfpCommonValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpCommonValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpCommonValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(
				String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
		gtnWsGeneralRequest.setSessionId(String
				.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		Date ifpStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")
				.getDateFromDateField();
		Date ifpEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")
				.getDateFromDateField();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMMON_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		Object msg;
		GtnIFamilyPlanValidationBean validationBean = response.getGtnWsIfpReponse().getGtnIFamilyPlanValidationBean();
		validateDateEqualOrGreater(ifpStartDate, ifpEndDate, componentId);

		if (validationBean.getCount() == 0) {
			msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_ATLEAST_ONE_RECORD;
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}

		if (validationBean.getCheckedCount() == 0) {
			msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_ATLEAST_ONE_RECORD_TO_SAVE;
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}

		if (validationBean.getStartDateNullCount() > 0) {
			if (validationBean.getStartDateNullCount() > 1) {
				msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_START_DATE_NULL;
			} else {
				msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_START_DATE_NULL_001
						+ validationBean.getStartDateNullItemId() + " in Items tab";
			}
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}
		if (validationBean.getStatusNullCount() > 0) {
			if (validationBean.getStatusNullCount() > 1) {
				msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_STATUS_NULL;
			} else {
				msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_STATUS_NULL_001
						+ validationBean.getStatusNullItemId() + "in Items tab";
			}
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}
		if (validationBean.getStartDateGreaterThanEndCount() > 0) {
			msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_STARTDATE_GREATER;
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}

	}

	public void validateDateEqualOrGreater(Date ifpStartDate, Date ifpEndDate, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (ifpEndDate != null) {
			if (ifpStartDate.equals(ifpEndDate)) {
				throw new GtnFrameworkValidationFailedException(GtnFrameworkIfpStringContants.IFP_DATE_EQUAL_VALIDATION,
						componentId);
			}
			if (ifpStartDate.after(ifpEndDate)) {
				throw new GtnFrameworkValidationFailedException(
						GtnFrameworkIfpStringContants.IFP_DATE_LESS_THAN_VALIDATION, componentId);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
