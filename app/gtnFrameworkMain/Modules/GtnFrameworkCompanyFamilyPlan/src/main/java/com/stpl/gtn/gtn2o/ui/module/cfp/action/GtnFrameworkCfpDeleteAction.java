package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCfpDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.debug("inside GtnFrameworkCfpDeleteAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnCFamilyPlan cfpBean = new GtnCFamilyPlan();
		GtnCFamilyPlanInformation infoBean = new GtnCFamilyPlanInformation();
		cfpBean.setCfpInfo(infoBean);
		infoBean.setCfpSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("cfpModelSid"));
		GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();
		cfpRequest.setGtnCFamilyPlan(cfpBean);
		gtnRequest.setGtnWsCfpRequest(cfpRequest);
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
		gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE + GtnWsCFamilyPlanContants.GTN_WS_CFP_DELETE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (!reponse.getGtnWsGeneralResponse().isSucess()) {
			String msg = GtnFrameworkCfpStringContants.GTN_CFP_DELETE_VALIDATION_MSG;
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			Object deleteMsg = "Cannot Delete";
			alertActionConfig.setActionParameterList(Arrays.asList(deleteMsg, msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid", 0);
			throw new GtnFrameworkSkipActionException("Delete Error :" + msg);
		} else {
			String cfpName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCFPName")
					.getStringFromField();
			GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
			notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notificationAction.addActionParameter(cfpName + " has been deleted successfully");
			notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
