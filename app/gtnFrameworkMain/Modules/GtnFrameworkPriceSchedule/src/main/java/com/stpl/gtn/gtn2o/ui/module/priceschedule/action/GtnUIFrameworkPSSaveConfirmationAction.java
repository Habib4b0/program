/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkPSSaveConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			boolean var = priceProtectionStartDateAlert(componentId);
			if (!var) {
				GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
				confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
				List<Object> alertParams = new ArrayList<>();
				alertParams.add(" Save Confirmation ");
				alertParams.add(" Save record "
						+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1").getStringFromField()
						+ "?");

				List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

				GtnUIFrameWorkActionConfig custoSavemAction = new GtnUIFrameWorkActionConfig();
				custoSavemAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				custoSavemAction.addActionParameter(GtnUIFrameWorkPSSaveAction.class.getName());

				onSucessActionConfig.add(custoSavemAction);

				GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
				changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);
				changeCaptionActionConfig.addActionParameter(Arrays.asList("cDRAddSaveButton"));
				changeCaptionActionConfig.addActionParameter(Arrays.asList("Update"));
				onSucessActionConfig.add(changeCaptionActionConfig);

				GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
				notificationActionConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
				List<Object> notificationParams = new ArrayList<>();
				notificationParams
						.add(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleId1").getStringFromField()
								+ "," + GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1")
										.getStringFromField()
								+ " has been successfully saved");
				notificationParams.add("");

				notificationActionConfig.setActionParameterList(notificationParams);
				onSucessActionConfig.add(notificationActionConfig);

				GtnUIFrameWorkActionConfig setModeActionConfig = new GtnUIFrameWorkActionConfig();
				setModeActionConfig.setActionType(GtnUIFrameworkActionType.MODE_CHANGE);
				setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.EDIT }));

				onSucessActionConfig.add(setModeActionConfig);

				alertParams.add(onSucessActionConfig);
				confirmationActionConfig.setActionParameterList(alertParams);
				GtnUIFrameWorkAction action = confirmationActionConfig.getActionType().getGtnUIFrameWorkAction();
				action.configureParams(confirmationActionConfig);

				action.doAction(componentId, confirmationActionConfig);
			}

		} catch (Exception ex) {
			throw ex;
		}
	}

	public boolean priceProtectionStartDateAlert(String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		GtnUIFrameWorkPSInfoBean psInfoBean = new GtnUIFrameWorkPSInfoBean();

		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = new GtnWsPriceScheduleGeneralRequest();
		List<Object> inputList = new ArrayList<>();
		inputList.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		inputList.add(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		getGtnWsPriceScheduleGeneralRequest.setPsInfoBean(psInfoBean);
		request.setGtnWsPriceScheduleGeneralRequest(getGtnWsPriceScheduleGeneralRequest);
		GtnUIFrameworkWebserviceResponse responseStartDate = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/priceProtectionStartDateAlert", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int responseResult = Integer.parseInt(String.valueOf(responseStartDate.getOutBountData()[0]));
		if (responseResult > 1) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgParamsList = new ArrayList<>();
			alertMsgParamsList.add("Error");
			alertMsgParamsList.add(
					"For the same Item, the ‘Price Protection Start Date’ field cannot have the same month/year combination across multiple records.");
			alertActionConfig.setActionParameterList(alertMsgParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			return true;
		}
		return false;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
