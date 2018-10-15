package com.stpl.gtn.gtn2o.ui.module.cfp.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.Date;

public class GtnFrameworkCfpCommonValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpCommonValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpCommonValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
		gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		Date cfpStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")
				.getDateFromDateField();
		Date cfpEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")
				.getDateFromDateField();
		GtnUIFrameworkWebserviceResponse response = getCommonValidationResponse(request);
		GtnCFamilyPlanValidationBean validationBean = response.getGtnWsCfpReponse().getGtnCFamilyPlanValidationBean();

		validateDateEqualOrGreater(cfpStartDate, cfpEndDate, componentId);
		validateAtleastOneCompanySelected(componentId, validationBean);
		validateStartDate(componentId, validationBean);
		validateStatus(componentId, validationBean);
		validateIsEndBeforeStartDate(componentId, validationBean);
		validateDuplicateCompany(componentId, validationBean);
		validateIsOneChecked(componentId, validationBean);
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	public GtnUIFrameworkWebserviceResponse getCommonValidationResponse(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMMON_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private void validateIsOneChecked(String componentId, GtnCFamilyPlanValidationBean validationBean)
			throws GtnFrameworkValidationFailedException {
		String msg;
		if (validationBean.getCheckedCount() == 0) {
			msg = GtnFrameworkCfpStringContants.GTN_CFP_CHECK_ATLEAST_ONE_VALIDATION_MSG;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

	private void validateAtleastOneCompanySelected(String componentId, GtnCFamilyPlanValidationBean validationBean)
			throws GtnFrameworkValidationFailedException {
		if (validationBean.getCount() == 0) {
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCfpStringContants.GTN_CFP_ATLEAST_ONE_COMPANY_VALIDATION_MSG, componentId);
		}
	}

	private void validateDuplicateCompany(String componentId, GtnCFamilyPlanValidationBean validationBean)
			throws GtnFrameworkValidationFailedException {
		String msg;
		if (validationBean.getDuplicateCompanyCount() > 0) {
			if (validationBean.getDuplicateCompanyCount() > 1) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_005
						+ validationBean.getDuplicateCompanyId() + " and " + validationBean.getDuplicateCompanyCount()
						+ GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_COMPANY_COUNT;
			} else {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_006
						+ validationBean.getDuplicateCompanyId() + " in Companies tab";
			}
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

	private void validateIsEndBeforeStartDate(String componentId, GtnCFamilyPlanValidationBean validationBean)
			throws GtnFrameworkValidationFailedException {
		String msg= "";
	   if (validationBean.getStartDateGreaterThanEndCount() == 0 && validationBean.getStartDateEqualsEndCount() > 0 ) {
			msg = GtnFrameworkCfpStringContants.CFP_DATE_EQUAL_VALIDATION;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	   if (validationBean.getStartDateGreaterThanEndCount() > 0  ) {
			if (validationBean.getStartDateGreaterThanEndCount() > 1) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_001
						+ validationBean.getStartDateGreaterThanEndCount()
						+ GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_002;
			} else {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_003
						+ validationBean.getStartDateGreaterThanEndCompanyId()
						+ GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_004;
			}
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		
	}

	private void validateStatus(String componentId, GtnCFamilyPlanValidationBean validationBean)
			throws GtnFrameworkValidationFailedException {
		String msg;
		if (validationBean.getStatusNullCount() > 0) {
			if (validationBean.getStatusNullCount() > 1) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_STATUS_REQUIRED_VALIDATION_MSG;
			} else {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_STATUS_NULL
						+ validationBean.getStatusNullCompanyId()
						+ GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_004;
			}
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

	private void validateStartDate(String componentId, GtnCFamilyPlanValidationBean validationBean)
			throws GtnFrameworkValidationFailedException {
		String msg;
		if (validationBean.getStartDateNullCount() > 0) {
			if (validationBean.getStartDateNullCount() > 1) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG;
			} else {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_START_DATE_NULL
						+ validationBean.getStartDateNullCompanyId()
						+ GtnFrameworkCfpStringContants.GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_004;
			}
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

	public void validateDateEqualOrGreater(Date cfpStartDate, Date cfpEndDate, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (cfpEndDate != null) {
			if (cfpStartDate.equals(cfpEndDate)) {
				throw new GtnFrameworkValidationFailedException(GtnFrameworkCfpStringContants.CFP_DATE_EQUAL_VALIDATION,
						componentId);
			}
			if (cfpStartDate.after(cfpEndDate)) {
				throw new GtnFrameworkValidationFailedException(
						GtnFrameworkCfpStringContants.CFP_DATE_LESS_THAN_VALIDATION, componentId);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
