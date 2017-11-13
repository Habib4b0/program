package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkRebatePlanDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String rpDeleteUrl = (String) parameters.get(1);
		String tableId = (String) parameters.get(2);
		String propertyId = (String) parameters.get(3);
		boolean isAdditionalProperty = false;
		int index = 0;
		if (parameters.size() > 3) {
			isAdditionalProperty = (boolean) parameters.get(4);
			index = (int) parameters.get(5);
		}

		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId)
				.getValueFromPagedDataTable();

		int rpSystemId = 0;
		if (!isAdditionalProperty) {
			rpSystemId = (Integer) gtnWsRecordBean.getPropertyValue(propertyId);
		} else {
			rpSystemId = gtnWsRecordBean != null ? Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(index)))
                                : (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID);
		}

		callGtnWebService(rpSystemId, rpDeleteUrl);

	}

	public void callGtnWebService(int sysId, String url) {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest rpDeleteRequest = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMap = new HashMap<>();

		if ((Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID) != null
				|| (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID) != 0) {
			inputMap.put("sysId", GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID));
		} else {
			inputMap.put("sysId", sysId);

		}
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		rpDeleteRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		wsclient.callGtnWebServiceUrl(url, rpDeleteRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}