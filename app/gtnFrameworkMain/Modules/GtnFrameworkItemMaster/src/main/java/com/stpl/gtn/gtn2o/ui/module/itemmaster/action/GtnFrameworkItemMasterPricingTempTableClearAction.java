package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

public class GtnFrameworkItemMasterPricingTempTableClearAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnItemMasterRequestBuilder().getItemMasterWebServiceRequest();
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_PRICING_DROP_TEMP_TABLE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
