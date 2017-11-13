package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;

public class GtnItemMasterRequestBuilder {

	public GtnUIFrameworkWebserviceRequest getItemMasterWebServiceRequest() {
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest itemMasterRequest = new GtnWsItemMasterRequest();
		itemMasterRequest.setGtnWsItemMasterBean(new GtnWsItemMasterBean());
		itemMasterRequest.getGtnWsItemMasterBean().setGtnWsItemMasterInfoBean(new GtnWsItemMasterInfoBean());
		GtnWsGeneralRequest generalRequest = getGeneralRequest();
		gtnRequest.setGtnWsGeneralRequest(generalRequest);
		gtnRequest.setGtnWsItemMasterRequest(itemMasterRequest);
		return gtnRequest;
	}

	private GtnWsGeneralRequest getGeneralRequest() {
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		return generalRequest;
	}
}
