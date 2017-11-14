package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;

public class GtnFrameworkWorkFlowPopupTableLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkWorkFlowPopupTableLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String businessProcess = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS)
				.getCaptionFromComboBox();
		String url = businessProcess.equals(GtnFrameworkCommonStringConstants.ARM)
				? "/createdbyarm" + "/getCreatedByArmUsersList"
				: "/workFlowLookup" + "/getWfUsersList";
		String tableComponentId = String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1));

		loadDataFromService(url, tableComponentId);

	}

	private void loadDataFromService(String url, String tableComponentId) {
		try {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
			request.setGtnWsGeneralRequest(generalRequest);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsCommonWorkflowResponse companyMasterResponse = response.getGtnWSCommonWorkflowResponse();
			List<GtnWsRecordBean> identifierBeanList = companyMasterResponse.getResultList();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableComponentId).loadContainer(tableComponentId,
					identifierBeanList);
		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
