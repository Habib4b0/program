package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkIfpItemsTabPopulateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpItemsTabPopulateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpItemsTabPopulateAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		try {
			GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
			updateBean.setPopulateType(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)));
			String ifpItemsTabMassField = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")
					.getStringFromField();
			Date ifpItemsTabMassDateFeild = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")
					.getDateFromDateField();
			Integer ifpItemsTabMassDropDown = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")
					.getIntegerFromField();

			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			if (ifpItemsTabMassField.isEmpty()) {
				validateEmptyField(componentId, alertActionConfig);
			}

			if ("IFP Status".equals(ifpItemsTabMassField)) {
				validateIfpStatus(componentId, ifpItemsTabMassDropDown, alertActionConfig);
			} else {
				validateEmptyDateMassUpdate(componentId, ifpItemsTabMassField, ifpItemsTabMassDateFeild,
						alertActionConfig);
			}
			if ("populate".equals(updateBean.getPopulateType())) {
				GtnUIFrameworkWebserviceRequest request2 = new GtnUIFrameworkWebserviceRequest();
				GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
				gtnWsGeneralRequest.setUserId(String
						.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
				gtnWsGeneralRequest.setSessionId(String.valueOf(
						GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
				request2.setGtnWsGeneralRequest(gtnWsGeneralRequest);
				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
								+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMMON_VALIDATION_SERVICE,
						request2, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				if (response.getGtnWsIfpReponse().getGtnIFamilyPlanValidationBean().getCheckedCount() == 0) {
					Object msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_POPULATE_005;
					alertActionConfig
							.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
					throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
				}
			}
			// Select a row to populate
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsIfpRequest gtnWsCfpRequest = new GtnWsIfpRequest();

			generalWSRequest.setUserId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
			generalWSRequest.setSessionId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
			request.setGtnWsGeneralRequest(generalWSRequest);
			request.setGtnWsIfpRequest(gtnWsCfpRequest);

			String massFieldvalue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")
					.getStringFromField();
			boolean isDropDown = "IFP Status".equals(massFieldvalue);
			updateBean.setColumnName(massFieldvalue);
			String value = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			if (isDropDown) {
				value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown").getStringFromField();
			} else {
				Object date = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")
						.getObjectFromField();
				if (date != null) {
					value = new Timestamp(((Date) date).getTime()).toString();
				}
			}
			updateBean.setValue(value);
			GtnIFamilyPlanBean ifp = new GtnIFamilyPlanBean();
			ifp.setUpdateBean(updateBean);
			gtnWsCfpRequest.setGtnIFamilyPlan(ifp);
			request.setGtnWsIfpRequest(gtnWsCfpRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
							+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_COLUMN_UPDATE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("ifpItemsTabResultDataTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, true);
		} catch (GtnFrameworkValidationFailedException e) {
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("ifpItemsTabResultDataTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, true);
			gtnLogger.error(e.getMessage(), e);
			throw new GtnFrameworkSkipActionException(e.getMessage(), e);

		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	private void validateEmptyField(String componentId, GtnUIFrameWorkActionConfig alertActionConfig)
			throws GtnFrameworkGeneralException {
		Object msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_POPULATE_001;
		alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
	}

	private void validateEmptyDateMassUpdate(String componentId, String ifpItemsTabMassField,
			Date ifpItemsTabMassDateFeild, GtnUIFrameWorkActionConfig alertActionConfig)
			throws GtnFrameworkGeneralException {
		Object msg;
		if ("IFP Start Date".equals(ifpItemsTabMassField)) {
			msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_POPULATE_003;
		} else {
			msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_POPULATE_004;
		}
		if (ifpItemsTabMassDateFeild == null) {
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}
	}

	private void validateIfpStatus(String componentId, Integer ifpItemsTabMassDropDown,
			GtnUIFrameWorkActionConfig alertActionConfig) throws GtnFrameworkGeneralException {
		if (ifpItemsTabMassDropDown == null || ifpItemsTabMassDropDown == 0) {
			Object msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_POPULATE_002;
			alertActionConfig.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
