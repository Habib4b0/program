package com.stpl.gtn.gtn2o.ui.module.udc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.udc.GtnWsUdcRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsBrandMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.constants.GtnWsUdcConstants;

public class GtnFrameworkUdcAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkUdcSearchAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into UdcSaveAction doAction");

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		GtnWsUdcRequest udcRequest = new GtnWsUdcRequest();
		Date date = new Date();
		try {
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", true);
			String udcCategory = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY)
					.getCaptionFromComboBox();
			if (udcCategory.equals("BRAND")) {
				GtnWsBrandMasterBean gtnWsBrandMasterBean = loadBrandValues(date);

				GtnWsBrandMasterInfoBean gtnWsBrandMasterInfoBean = new GtnWsBrandMasterInfoBean();
				gtnWsBrandMasterInfoBean.setGtnWsBrandMasterBean(gtnWsBrandMasterBean);

				udcRequest.setGtnWsBrandMasterInfoBean(gtnWsBrandMasterInfoBean);
				request.setGtnWsUdcRequest(udcRequest);
				GtnUIFrameworkWebserviceResponse response = getResponse1(request);
				displayResultStatus(response, componentId);

			} else if (udcCategory.equals("FILE_TYPE")) {
				String udcValue = null;
				String alias = null;
				udcValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_FILETYPE_VALUE)
						.getStringFromField();
				alias = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_FILETYPE_ALIAS)
						.getStringFromField();
				GtnWsUdcInfoBean udcInfoBean = new GtnWsUdcInfoBean();
				udcInfoBean.setUdcCategory(udcCategory);
				udcInfoBean.setUdcValue(udcValue);
				udcInfoBean.setAliasName(alias);
				udcInfoBean.setRefCount(0);
				udcInfoBean.setCreatedBy(0);
				udcInfoBean.setModifiedBy(0);
				udcInfoBean.setCreatedDate(date);
				udcInfoBean.setModifiedDate(date);
				GtnWsUdcBean udcBean = new GtnWsUdcBean();
				udcBean.setGtnWsUdcInfoBean(udcInfoBean);

				udcRequest.setGtnWsUdcBean(udcBean);
				request.setGtnWsUdcRequest(udcRequest);
				GtnUIFrameworkWebserviceResponse response = getResponse2(request);
				displayResultStatus(response, componentId);
			} else {
				String udcValue = null;
				udcValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_VALUE)
						.getStringFromField();
				GtnWsUdcInfoBean udcInfoBean = new GtnWsUdcInfoBean();
				udcInfoBean.setUdcCategory(udcCategory);
				udcInfoBean.setUdcValue(udcValue);
				udcInfoBean.setRefCount(0);
				udcInfoBean.setCreatedBy(0);
				udcInfoBean.setModifiedBy(0);
				udcInfoBean.setCreatedDate(date);
				udcInfoBean.setModifiedDate(date);
				GtnWsUdcBean udcBean = new GtnWsUdcBean();
				udcBean.setGtnWsUdcInfoBean(udcInfoBean);

				udcRequest.setGtnWsUdcBean(udcBean);
				request.setGtnWsUdcRequest(udcRequest);
				GtnUIFrameworkWebserviceResponse response = getResponse3(request);
				displayResultStatus(response, componentId);

			}

		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error("Error while executing UdcSaveAction");
		} finally {
			gtnLogger.info("Exit UdcSaveAction");
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse3(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsUdcConstants.GTN_UDC_SERVICE + GtnWsUdcConstants.GTN_UDC_SAVE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public GtnUIFrameworkWebserviceResponse getResponse2(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsUdcConstants.GTN_UDC_SERVICE + GtnWsUdcConstants.GTN_UDC_SAVE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public GtnUIFrameworkWebserviceResponse getResponse1(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsUdcConstants.GTN_UDC_SERVICE + GtnWsUdcConstants.GTN_UDC_BRAND_SAVE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	
	}

	private GtnWsBrandMasterBean loadBrandValues(Date date) {
		GtnWsBrandMasterBean gtnWsBrandMasterBean = new GtnWsBrandMasterBean();
		gtnWsBrandMasterBean.setBrandId(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("brandId").getStringFromField());
		gtnWsBrandMasterBean
				.setBrandName(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("brandName").getStringFromField());
		gtnWsBrandMasterBean
				.setDisplayBrand(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("displayBrand").getStringFromField());
		gtnWsBrandMasterBean.setCreatedDate(date);
		gtnWsBrandMasterBean.setModifiedDate(date);
		gtnWsBrandMasterBean.setCreatedBy(0);
		gtnWsBrandMasterBean.setModifiedBy(0);
		gtnWsBrandMasterBean.setInboundStatus('A');

		return gtnWsBrandMasterBean;
	}

	private void displayResultStatus(GtnUIFrameworkWebserviceResponse response, String componentId)
			throws GtnFrameworkGeneralException {

		if (response.getGtnWsGeneralResponse().isSucess()) {
			GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
			notificationActionConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			Object emptyValue = "";
			notificationActionConfig.setActionParameterList(Arrays.asList("Category " + GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY).getCaptionFromComboBox()
					+ " Saved successfully", emptyValue));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationActionConfig);

		} else {
			GtnUIFrameWorkActionConfig failedAlertActionConfig = new GtnUIFrameWorkActionConfig();
			failedAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> failedAlertParamsList = new ArrayList<>();
			failedAlertParamsList.add("Duplicate");
			failedAlertParamsList
					.add(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY)
							.getCaptionFromComboBox() + " already exist");
			failedAlertActionConfig.setActionParameterList(failedAlertParamsList);

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, failedAlertActionConfig);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
