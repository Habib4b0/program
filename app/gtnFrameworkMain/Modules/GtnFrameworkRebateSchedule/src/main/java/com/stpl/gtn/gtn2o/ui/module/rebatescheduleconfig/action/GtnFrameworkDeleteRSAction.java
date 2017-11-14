
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkDeleteAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkDeleteRSAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String url = (String) parameters.get(1);
		onSucess((Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId"), url);

	}

	public void onSucess(int sysId, String uri) throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put("sysId", sysId);
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(uri, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if ("Fail".equals(String.valueOf(response.getOutBountData()[0]))) {
			List<Object> actionParams = new ArrayList<>();
			actionParams.add("Delete Failed");
			actionParams.add("Record Already used in Contract");
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();

			gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

			GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
			alert.configureParams(gtnUIFrameWorkActionConf);
			alert.doAction(" ", gtnUIFrameWorkActionConf);
			throw new GtnFrameworkGeneralException("Delete Failed ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkDeleteAction();
	}
}