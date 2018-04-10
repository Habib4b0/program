package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkIfpDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpDeleteAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest ifpRequst = new GtnWsIfpRequest();
		GtnIFamilyPlanBean bean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean info = new GtnIFamilyPlanInformationBean();
		bean.setIfpInfo(info);
		info.setIfpSid((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid"));
		ifpRequst.setGtnIFamilyPlan(bean);
		gtnRequest.setGtnWsIfpRequest(ifpRequst);

		GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE + GtnWsIFamilyPlanContants.GTN_WS_IFP_DELETE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (!reponse.getGtnWsGeneralResponse().isSucess()) {
			Object msg = GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_DELETE;
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();

			alertActionConfig.setActionParameterList(Arrays.asList("Cannot Delete", msg));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			GtnUIFrameworkGlobalUI.addSessionProperty("ifpModelSid", 0);
			throw new GtnFrameworkSkipActionException("Delete Error :" + msg);
		} else {

			GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
			notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			String message = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPName")
					.getStringFromField() + " has been deleted successfully";
			notificationAction.addActionParameter(message);
			notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
