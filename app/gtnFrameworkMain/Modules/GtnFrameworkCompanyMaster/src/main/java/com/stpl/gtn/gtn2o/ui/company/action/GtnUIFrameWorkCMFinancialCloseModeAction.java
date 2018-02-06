/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnUIFrameWorkCMFinancialCloseModeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkCMFinancialCloseModeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering inside configureParams ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String url = GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER
				+ GtnWebServiceUrlConstants.GTN_WS_CM_FETCH_HELPERSID;
		Calendar mCalendar = Calendar.getInstance();
		String mode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMode", componentId)
				.getCaptionFromComboBox();
		if ("Manual".equals(mode)) {
			resetAndEnableDisableCombobox(GtnFrameworkCompanyStringContants.getGtnCompanyAutoModeComponentsList(),
					componentId, true);
			resetAndEnableDisableCombobox(GtnFrameworkCompanyStringContants.getGtnCompanyManualModeComponentsList(),
					componentId, false);

			getDefaultMonth( url, mCalendar);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseOpenButton").setEnable(true);
		} else if ("Auto".equals(mode)) {
			resetAndEnableDisableCombobox(GtnFrameworkCompanyStringContants.getGtnCompanyAutoModeComponentsList(),
					componentId, false);
			resetAndEnableDisableCombobox(GtnFrameworkCompanyStringContants.getGtnCompanyManualModeComponentsList(),
					componentId, true);
			getDefaultMonth(url, mCalendar);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseOpenButton", componentId).setEnable(false);
		} else {
			resetAndEnableDisableCombobox(GtnFrameworkCompanyStringContants.getGtnCompanyAutoModeComponentsList(),
					componentId, false);
			resetAndEnableDisableCombobox(GtnFrameworkCompanyStringContants.getGtnCompanyManualModeComponentsList(),
					componentId, false);
			getDefaultMonth( url, mCalendar);
		}

	}

	private void getDefaultMonth(String url, Calendar mCalendar) {
		String month = new SimpleDateFormat("MMMMM").format(mCalendar.getTime());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseYear")
				.loadComboBoxComponentValue(mCalendar.get(Calendar.YEAR));

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMonth")
				.loadComboBoxComponentValue(loadDataFromService(url, month));
	}

	void resetAndEnableDisableCombobox(List<String> componentIdList, String sourceComponentId, boolean enable) {
		for (String componentId : componentIdList) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId)
					.loadComboBoxComponentValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).setEnable(enable);
		}
	}

	private int loadDataFromService(String url, String month) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setExtraParameter(month);
		request.setGtnWsGeneralRequest(generalRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnCompanyMasterResponse().getHelperTableSid();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
