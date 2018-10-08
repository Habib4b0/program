package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkNsfAddAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkNsfAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkNSFConstants.getSystemid(), null);
		GtnUIFrameworkGlobalUI.addSessionProperty("mode", "add");
		LOGGER.info("In GtnFrameworkNsfAddAction");
		GtnUIFrameworkWebserviceRequest refreshLoadRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest refreshLoadWSRequest = new GtnWsGeneralRequest();

		refreshLoadWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		refreshLoadWSRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());
		refreshLoadRequest.setGtnWsGeneralRequest(refreshLoadWSRequest);
		refreshLoadRequest.setGtnWsGeneralRequest(refreshLoadWSRequest);
		GtnUIFrameworkWebserviceResponse gtnWsresponse = getResponse(refreshLoadRequest);
		if (gtnWsresponse.getGtnWsGeneralResponse().isSucess()) {
			LOGGER.debug("In GtnFrameworkNsfAddAction");
		}

	}

	/**
	 * @param refreshLoadRequest
	 * @return
	 */
	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest refreshLoadRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NSF_SALES_DEDUCT_REFRESH,
				refreshLoadRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}