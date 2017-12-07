package com.stpl.gtn.gtn2o.ui.module.udc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.udc.GtnWsUdcRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.constants.GtnWsUdcConstants;

public class GtnFrameworkUdcDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}


	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String categoryValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY)
				.getCaptionFromComboBox();
		List<Object> beanValue = isBrand(categoryValue);
		String tableComponentId = (String) beanValue.get(0);
		boolean isBrand = (boolean) beanValue.get(1);
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(tableComponentId)
				.getValueFromPagedDataTable();

		GtnUIFrameworkWebserviceRequest gtnUIFrameworkDeleteWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnUIFrameworkDeleteWebserviceRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		if (gtnWsRecordBean == null) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			Object emptyValue = "";
			alertActionConfig.setActionParameterList(Arrays.asList("Deleted Failed", emptyValue));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		} else {
			Integer systemId = (Integer) gtnWsRecordBean
					.getPropertyValueByIndex(gtnWsRecordBean.getProperties().size() - 1);
			if (systemId != null) {
				GtnWsUdcBean udcBean = new GtnWsUdcBean();
				GtnWsUdcInfoBean udcInfoBean = new GtnWsUdcInfoBean();
				udcInfoBean.setHelperTableSid(systemId);
				udcInfoBean.setBrand(isBrand);
				udcBean.setGtnWsUdcInfoBean(udcInfoBean);
				GtnWsUdcRequest udcRequest = new GtnWsUdcRequest();
				udcRequest.setGtnWsUdcBean(udcBean);
				gtnUIFrameworkDeleteWebserviceRequest.setGtnWsUdcRequest(udcRequest);
				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsUdcConstants.GTN_UDC_SERVICE + GtnWsUdcConstants.GTN_UDC_DELETE_SERVICE,
						gtnUIFrameworkDeleteWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				displayResponseStatus(response, componentId);

				GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
				loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
				loadDataTableActionConfig.addActionParameter(tableComponentId);
				if (isBrand) {
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
				} else {
					loadDataTableActionConfig
							.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.UDC_CATEGORY));
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
				}
			}
		}
	}

	private List<Object> isBrand(String categoryValue) {
		List<Object> beanValues = new ArrayList<>();
		GtnWsUdcInfoBean udcInfoBean = new GtnWsUdcInfoBean();
		if (categoryValue.equals("BRAND")) {
			beanValues.add(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE);
			udcInfoBean.setBrand(true);
			beanValues.add(udcInfoBean.isBrand());
		} else {
			beanValues.add(GtnFrameworkCommonConstants.UDC_RESULT_TABLE);
			udcInfoBean.setBrand(false);
			beanValues.add(udcInfoBean.isBrand());
		}

		return beanValues;
	}

	private void displayResponseStatus(GtnUIFrameworkWebserviceResponse response, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		Object emptyValue = "";
		if (response.getGtnWsGeneralResponse().isSucess()) {
			notificationActionConfig.setActionParameterList(Arrays.asList("Category Value " + GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY).getCaptionFromComboBox()
					+ " deleted successfully", emptyValue));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationActionConfig);

		}
		else {
			notificationActionConfig
					.setActionParameterList(Arrays.asList(
							"Deleted Failed : The Category Value " + GtnUIFrameworkGlobalUI
									.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY)
									.getCaptionFromComboBox() + " is currently used in Master Records",
							emptyValue));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationActionConfig);
		}
	}


	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
