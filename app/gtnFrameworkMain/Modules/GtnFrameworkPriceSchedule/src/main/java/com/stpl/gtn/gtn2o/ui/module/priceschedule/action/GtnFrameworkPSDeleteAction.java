/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Deepika.krishnakumar
 */
public class GtnFrameworkPSDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
      List<Object> parametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String psDeleteUrl = (String) parametersList.get(1);
		String psTableId = (String) parametersList.get(2);
		String psPropertyId = (String) parametersList.get(3);
		boolean isAdditionalProp = false;
		int indexVal = 0;
		if (parametersList.size() > 3) {
			isAdditionalProp = (boolean) parametersList.get(4);
			indexVal = (int) parametersList.get(5);
		}

		GtnWsRecordBean gtnWsRecordBeanData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(psTableId, componentId)
				.getValueFromPagedDataTable();

		int psSystemId = 0;
		if (!isAdditionalProp) {
			psSystemId = (Integer) gtnWsRecordBeanData.getPropertyValue(psPropertyId);
		} else {
			psSystemId = gtnWsRecordBeanData != null ? Integer.valueOf(String.valueOf(gtnWsRecordBeanData.getProperties().get(indexVal)))
                                : (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID);
		}

		callWebService(psSystemId, psDeleteUrl);

	}

	public void callWebService(int psSysId, String delUrl) {

		GtnUIFrameworkWebServiceClient webServiceclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest psDeleteRequest = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMapValues = new HashMap<>();

		if ((Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID) != null
				|| (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID) != 0) {
			inputMapValues.put("sysId", GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID));
		} else {
			inputMapValues.put("sysId", psSysId);

		}
		GtnWsGeneralRequest wsGeneralRequest = new GtnWsGeneralRequest();
		psDeleteRequest.setGtnWsGeneralRequest(wsGeneralRequest);
		List<Object> inputLists = new ArrayList<>();
		inputLists.add(inputMapValues);
		wsGeneralRequest.setComboBoxWhereclauseParamList(inputLists);
		webServiceclient.callGtnWebServiceUrl(delUrl, psDeleteRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

    @Override
    public GtnUIFrameWorkAction createInstance() {
      return this;
    }
    
}
