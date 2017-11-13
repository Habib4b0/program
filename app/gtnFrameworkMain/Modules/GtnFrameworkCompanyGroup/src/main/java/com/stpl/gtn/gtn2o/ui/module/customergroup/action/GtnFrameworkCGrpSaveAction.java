package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCGrpSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkCompanyGrpSaveAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnCompanyGroupBean cGrBean = new GtnCompanyGroupBean();
			GtnCompanyGrpInformationBean infoBean = new GtnCompanyGrpInformationBean();
			infoBean.setCompanyGrpName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME)
					.getStringFromField());
			infoBean.setCompanyGrpNo(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpNo").getStringFromField());
			infoBean.setCompanyGrpDesc(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpDesc").getStringFromField());
			cGrBean.setGtnCompanyGrpInformationBean(infoBean);
			GtnCompanyGroupRequest cGrpRequest = new GtnCompanyGroupRequest();
			cGrpRequest.setGtnCompanyGroupBean(cGrBean);
			request.setGtnCompanyGroupRequest(cGrpRequest);
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("companyGrpSid");
			Integer versionId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("versionId");
			String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
			infoBean.setModifiedBy(Integer.valueOf(userId));
			if (systemId != null && systemId > 0) {
				infoBean.setCompanyGrpSid(systemId);
			}
			if (versionId != null && versionId > 0) {
				infoBean.setVersionNo(versionId + 1);
			}
			infoBean.setCompanyGrpSid(systemId);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
							+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SAVE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid",
					response.getGtnWsCompanyGroupResponse().getGtnCompanyGrpInformationBean().getCompanyGrpSid());
			GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);
			GtnUIFrameworkSetter gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
			gtnUIFrameworkSetter.setComponentEnable(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME, false);
			gtnUIFrameworkSetter.setComponentEnable("cGrpInformationCGrpNo", false);
			gtnUIFrameworkSetter.setComponentEnable("cGrpInformationCGrpDesc", false);
			GtnUIFrameworkGlobalUI.getVaadinComponent("cGrpAddSaveButton").setCaption("UPDATE");
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig();
			notificationConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			Object emptyValue = GtnFrameworkCGrpStringContants.EMPTY;
			notificationConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME)
					.getStringFromField() + " has been successfully saved", emptyValue, 2));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);

		} catch (Exception e) {
			logger.error("Error while GtnFrameworkCompanyGrpSaveAction", e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
