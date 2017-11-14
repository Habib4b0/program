/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkDeleteAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String url = (String) parameters.get(0);
		String tableId = (String) parameters.get(1);
		String propertyId = (String) parameters.get(2);
		boolean isAdditionalProperty = false;
		int index = 0;
		if (parameters.size() > 3) {
			isAdditionalProperty = (boolean) parameters.get(3);
			index = (int) parameters.get(4);
		}

		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId)
				.getValueFromPagedDataTable();

		int sysId = 0;
		if (!isAdditionalProperty) {
			sysId = (Integer) gtnWsRecordBean.getPropertyValue(propertyId);
		} else {
			sysId = Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(index)));

		}

		callGtnWebService(sysId, url);

	}

	public void callGtnWebService(int sysId, String url) throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put("sysId", sysId);
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		wsclient.callGtnWebServiceUrl(url, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}