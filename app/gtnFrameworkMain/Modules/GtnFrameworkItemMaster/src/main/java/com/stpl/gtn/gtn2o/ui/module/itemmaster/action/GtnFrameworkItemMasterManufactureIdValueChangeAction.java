package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterManufactureIdValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkItemMasterManufactureIdValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkItemMasterManufactureIdValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			Integer itemInformationTabManufacturerID = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemInformationTabManufacturerID").getIntegerFromField();
			GtnWsBrandMasterBean brandBean = new GtnWsBrandMasterBean();
			brandBean.setBrandMasterSid(itemInformationTabManufacturerID);
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
			gtnRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
			GtnWsItemMasterBean bean = new GtnWsItemMasterBean();
			GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
			infoBean.setCompanyMasterSid(itemInformationTabManufacturerID);
			bean.setGtnWsItemMasterInfoBean(infoBean);
			gtnWsItemMasterRequest.setGtnWsItemMasterBean(bean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
							+ GtnWsItemMasterContants.GTN_WS_IM_COMPANY_INFO_FETCH_INFORMATION_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			String companyName = response.getGtnWsItemMasterResponse().getCompanyName();
			if (companyName != null) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabManufacturerName", componentId)
						.loadFieldValue(companyName);
			}
		} catch (Exception e) {
			logger.error("in GtnFrameworkItemMasterManufactureIdValueChangeAction ", e);
			throw new GtnFrameworkGeneralException(e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
