package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterBrandValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterBrandValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkItemMasterBrandValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			Integer itemInformationTabitemMasterBrand = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemInformationTabitemMasterBrand").getIntegerFromField();
			GtnWsBrandMasterBean brandBean = new GtnWsBrandMasterBean();
			brandBean.setBrandMasterSid(itemInformationTabitemMasterBrand);
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
			gtnRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
			gtnWsItemMasterRequest.setGtnWsBrandMasterBean(brandBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
							+ GtnWsItemMasterContants.GTN_WS_IM_BRAND_MASTER_FETCH_INFORMATION_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response != null) {
				GtnWsBrandMasterBean responseBean = response.getGtnWsItemMasterResponse().getGtnWsBrandMasterBean();
				if (responseBean != null) {
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabBrandId", componentId)
							.loadFieldValue(responseBean.getBrandId());
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabDisplayBrand", componentId)
							.loadFieldValue(responseBean.getDisplayBrand());
				}
			}
		} catch (Exception e) {
			logger.error("in GtnFrameworkItemMasterEditListPopupCloseAction ", e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
