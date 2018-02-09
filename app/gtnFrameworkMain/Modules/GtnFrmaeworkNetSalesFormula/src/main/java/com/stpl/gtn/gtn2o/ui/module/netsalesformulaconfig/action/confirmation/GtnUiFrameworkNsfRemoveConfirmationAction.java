package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

public class GtnUiFrameworkNsfRemoveConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		boolean isSalesBasis = (boolean) actionParemeterList.get(2);
		if (callremoveService(isSalesBasis)) {
			reloadTable(isSalesBasis, viewId);
		}

	}

	public boolean callremoveService(boolean isSalesBasis) {
		GtnUIFrameworkWebserviceRequest updateRequest = GtnFrameworkNSFCommonLogic.getWsRequest(isSalesBasis);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NSF_REMOVE_RECORD_SERVICE,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsGeneralResponse nsfGeneralResponse = gtnResponse.getGtnWsGeneralResponse();
		return nsfGeneralResponse.isSucess();
	}

	public void reloadTable(boolean isSalesBasis, String viewId) throws GtnFrameworkValidationFailedException {
		String componentId = "selectedDeductionsResultTable";
		if (isSalesBasis) {
			componentId = "selectedCustomersResultTable";
		}
		GtnFrameworkNSFCommonLogic.reloadTable(viewId, componentId);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + componentId).setPagedTableHeaderCheckBox(false,
				"checkRecordId");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
