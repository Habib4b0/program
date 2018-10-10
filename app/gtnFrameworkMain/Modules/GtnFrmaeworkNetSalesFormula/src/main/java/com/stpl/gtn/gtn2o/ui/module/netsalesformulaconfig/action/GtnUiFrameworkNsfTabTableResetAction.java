package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

public class GtnUiFrameworkNsfTabTableResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
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
		if (callResetService(isSalesBasis)) {
			reloadTable(isSalesBasis, viewId);
		}
		resetComponents(isSalesBasis, viewId);
	}

	private void resetComponents(boolean isSalesBasis, String viewId) {
		String componentId = viewId + "selectedDeductionsmassUpdateOptionGroup";
		if (isSalesBasis) {
			componentId = viewId + "massUpdateOptionGroup";
		}
		GtnUIFrameworkBaseComponent massUpdateOptionGrp = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		massUpdateOptionGrp.selectOptionGroupValue(GtnFrameworkNSFConstants.getDisable());
	}

	public boolean callResetService(boolean isSalesBasis) {
		GtnUIFrameworkWebserviceRequest resetRequest = getWsRequest(isSalesBasis);
		GtnUIFrameworkWebserviceResponse gtnResponse = getResponse(resetRequest);
		GtnWsGeneralResponse nsfGeneralResponse = gtnResponse.getGtnWsGeneralResponse();
		return nsfGeneralResponse.isSucess();
	}

	/**
	 * @param resetRequest
	 * @return
	 */
	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest resetRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NSF_RESET_TABLE_SERVICE,
				resetRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		 
	}

	public void reloadTable(boolean isSalesBasis, String viewId) throws GtnFrameworkValidationFailedException {
		String componentId = "selectedDeductionsResultTable";
		if (isSalesBasis) {
			componentId = "selectedCustomersResultTable";
		}
		GtnFrameworkNSFCommonLogic.reloadTable(viewId, componentId);
	}

	public GtnUIFrameworkWebserviceRequest getWsRequest(boolean isSalesBasis) {
		GtnUIFrameworkWebserviceRequest resetRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfUpdateRequest = new GtnWsNetSalesFormulaGeneralRequest();
		resetRequest.setGtnWsNetSalesGeneralRequest(gtnWsNsfUpdateRequest);
		GtnWsNsfUpdateBean nsfResetBean = new GtnWsNsfUpdateBean();
		nsfResetBean.setSalesBasis(isSalesBasis);
		nsfResetBean.setMasterSid(Integer.parseInt(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkNSFConstants.getSystemid()).toString()));
		gtnWsNsfUpdateRequest.setNsfUpdateBean(nsfResetBean);
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		resetRequest.setGtnWsGeneralRequest(generalWSRequest);
		return resetRequest;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
