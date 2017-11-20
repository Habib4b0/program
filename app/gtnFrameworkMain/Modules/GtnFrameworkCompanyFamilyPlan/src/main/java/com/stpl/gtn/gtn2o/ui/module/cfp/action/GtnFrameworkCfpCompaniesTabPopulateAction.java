package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCfpCompaniesTabPopulateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger GTN_LOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkCfpCompaniesTabPopulateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GTN_LOGGER.debug("inside GtnFrameworkCfpCompaniesTabPopulateAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnCFamilyPlanCommonUpdateBean updateBean = new GtnCFamilyPlanCommonUpdateBean();
		updateBean.setPopulateType(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)));
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		try {
			if (!validateCfpFields(componentId, alertActionConfig, updateBean)) {
				return;
			}
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsCfpRequest gtnWsCfpRequest = new GtnWsCfpRequest();

			generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			request.setGtnWsCfpRequest(gtnWsCfpRequest);

			String cfpCompaniesTabMassFieldValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cfpCompaniesTabMassField").getStringFromField();

			boolean isDropDown = "CFP Status".equals(cfpCompaniesTabMassFieldValue);
			updateBean.setColumnName(cfpCompaniesTabMassFieldValue);
			String cfpMassFieldValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			if (isDropDown) {
				cfpMassFieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDropDown")
						.getStringFromField();
			} else {
				Object date = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDateFeild")
						.getObjectFromField();
				if (date != null) {
					cfpMassFieldValue = new Timestamp(((Date) date).getTime()).toString();
				}
			}
			updateBean.setValue(cfpMassFieldValue);
			GtnCFamilyPlan cfpBean = new GtnCFamilyPlan();
			cfpBean.setUpdateBean(updateBean);
			gtnWsCfpRequest.setGtnCFamilyPlan(cfpBean);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
							+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_COLUMN_UPDATE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, Boolean.TRUE);
		} catch (Exception e) {
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, Boolean.TRUE);
			GTN_LOGGER.error(e.getMessage(), e);
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	private void throwAlertMessage(String componentId, String alertMessage) {
		try {
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			actionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			actionConfig.addActionParameter("Info");
			actionConfig.addActionParameter(alertMessage);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
		} catch (GtnFrameworkGeneralException e) {
			GTN_LOGGER.error("Error in CFP Company Tab Papolate Action", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean validateCfpFields(String componentId, GtnUIFrameWorkActionConfig alertActionConfig,
			GtnCFamilyPlanCommonUpdateBean updateBean) throws GtnFrameworkGeneralException {

		String ifpItemsTabMassField = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField")
				.getStringFromField();
		Date ifpItemsTabMassDateFeild = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDateFeild")
				.getDateFromDateField();
		Integer ifpItemsTabMassDropDown = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassDropDown")
				.getIntegerFromField();

		if (ifpItemsTabMassField.isEmpty()) {
			String msg = GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_POPULATE_001;
			throwAlertMessage(componentId, msg);
			return false;
		}

		if ("CFP Status".equals(ifpItemsTabMassField)) {
			if (ifpItemsTabMassDropDown == null || ifpItemsTabMassDropDown == 0) {
				String msg = GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_POPULATE_002;
				Object errorMsg = GtnFrameworkCommonStringConstants.ERROR;
				alertActionConfig.setActionParameterList(Arrays.asList(errorMsg, msg));
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
				throwAlertMessage(componentId, msg);
				return false;
			}
		} else {
			String msg;
			if ("CFP Start Date".equals(ifpItemsTabMassField)) {
				msg = GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_POPULATE_003;
			} else {
				msg = "Please Select a date to populate";
			}
			if (ifpItemsTabMassDateFeild == null) {
				throwAlertMessage(componentId, msg);
				return false;
			}
		}

		if ("populate".equals(updateBean.getPopulateType())) {
			GtnUIFrameworkWebserviceRequest request2 = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
			gtnWsGeneralRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request2.setGtnWsGeneralRequest(gtnWsGeneralRequest);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
							+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMMON_VALIDATION_SERVICE,
					request2, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response.getGtnWsCfpReponse().getGtnCFamilyPlanValidationBean().getCheckedCount() == 0) {
				String msg = GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_POPULATE_004;
				throwAlertMessage(componentId, msg);
				return false;
			}
		}
		return true;
	}
}
