package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

public class GtnFrameworkCGrpTempTableClearAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
         return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest customerGrpGtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest customerGrpGeneralWSRequest = new GtnWsGeneralRequest();
		customerGrpGeneralWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		customerGrpGeneralWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		customerGrpGtnRequest.setGtnWsGeneralRequest(customerGrpGeneralWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
						+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_TEMP_TABLE_CLEAR_SERVICE,
				customerGrpGtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
