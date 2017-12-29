package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GntWsItemPricingBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;

public class GtnFrameworkItemMasterPricingDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE)
				.getValueFromPagedDataTable();

		GtnUIFrameworkWebserviceRequest gtnUIFrameworkDeleteWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnUIFrameworkDeleteWebserviceRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		Integer itemPricingQualifierSid = (Integer) gtnWsRecordBean
				.getPropertyValueByIndex(gtnWsRecordBean.getProperties().size() - 2);

		GntWsItemPricingBean gtnWsItemPricingBean = new GntWsItemPricingBean();
		gtnWsItemPricingBean.setItemPricingSid(itemPricingQualifierSid);
		GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
		gtnWsItemMasterBean.setGntWsItemPricingBean(gtnWsItemPricingBean);

		GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
		gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);
		gtnUIFrameworkDeleteWebserviceRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_PRICING_DELETE_SERVICE,
				gtnUIFrameworkDeleteWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
