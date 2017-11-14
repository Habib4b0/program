
package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;

public class GtnUIFrameworkCompanyMasterDeleteAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCompanyMasterDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnCMasterBean companyMasterBean = new GtnCMasterBean();
			GtnCMasterInformationBean companyInformation = new GtnCMasterInformationBean();
			companyInformation.setCompanyMasterSystemId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("companyInformationTabSystemId").getIntegerFromField());
			companyMasterBean.setGtnCMasterInformationBean(companyInformation);
			GtnCMasterRequest companyMasterRequest = new GtnCMasterRequest();
			companyMasterRequest.setGtnCMasterBean(companyMasterBean);
			request.setGtnCMasterRequest(companyMasterRequest);
			GtnCompanyMasterResponse deleteResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER
							+ GtnWebServiceUrlConstants.GTN_CM_GET_DELETE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken()).getGtnCompanyMasterResponse();
			if (deleteResponse.isSuccess()) {
				List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
				GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.NOTIFICATION_ACTION);
				notificationAction.addActionParameter(
						GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyId")
								.getStringFromField() + " has been Deleted Successfully");
				notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				actionConfigList.add(notificationAction);
				GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
				navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
				navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				actionConfigList.add(navigationActionConfig);
				GtnUIFrameworkActionExecutor.executeActionList(componentId, actionConfigList);

			} else {
				GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.ALERT_ACTION);
				alertAction.addActionParameter("Cannot Delete");
				alertAction.addActionParameter(deleteResponse.getReasonForFailure());
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
			}

		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCompanyMasterDeleteAction();
	}

}
