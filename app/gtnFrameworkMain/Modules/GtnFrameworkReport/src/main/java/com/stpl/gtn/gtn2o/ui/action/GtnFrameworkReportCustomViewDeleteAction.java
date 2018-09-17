/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkReportCustomViewDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportCustomViewDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String selectedItem;
		String customViewName;
		String customViewComponentId;
		GtnUIFrameworkBaseComponent baseComponent;
		boolean isCustomViewGenerated = false;
		String screenName = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();
		if (screenName.contains(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_DS)) {
			customViewComponentId = "dataSelectionTab_displaySelectionTabCustomView";
			baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(customViewComponentId, componentId);
			isCustomViewGenerated = true;
		} else if (screenName.contains(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_RD)) {
			customViewComponentId = "reportingDashboardTab_displaySelectionTabCustomView";
			baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(customViewComponentId, componentId);
			isCustomViewGenerated = true;
		} else {
			customViewComponentId = GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW;
			baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(customViewComponentId);
		}
		selectedItem = baseComponent.getCaptionFromV8ComboBox();
		customViewName = baseComponent.getStringCaptionFromV8ComboBox();

		if ("".equals(selectedItem) && "0".equals(selectedItem)) {
			return;
		}

		deleteValidation(Arrays.asList(selectedItem, customViewName, componentId,
				gtnUIFrameWorkActionConfig.getActionParameterList(), isCustomViewGenerated, customViewComponentId));

	}

	private void deleteValidation(List<Object> inputList) {
		try {
			if (!deleteUserValidation(inputList.get(0).toString())) {
				GtnUIFrameWorkActionConfig userNotification = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.ALERT_ACTION);
				userNotification.addActionParameter("Delete Halt");
				userNotification.addActionParameter("Only the user who created the Custom Tree View can delete it.");
				GtnUIFrameworkActionExecutor.executeSingleAction(inputList.get(2).toString(), userNotification);
				return;
			}

			if ((boolean) inputList.get(4) && isCustomViewGenerated(inputList.get(0).toString(),
					inputList.get(2).toString(), (boolean) inputList.get(4))) {
				GtnUIFrameWorkActionConfig customViewAlreadygeneratedNotification = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.ALERT_ACTION);
				customViewAlreadygeneratedNotification.addActionParameter("Delete Check");
				customViewAlreadygeneratedNotification.addActionParameter(
						"The selected view cannot be deleted as it is currently in use on the Reporting Dashboard tab, Results list view.  Please select a different view on the Reporting Dashboard tab in order to delete this selected view.");
				GtnUIFrameworkActionExecutor.executeSingleAction(inputList.get(2).toString(),
						customViewAlreadygeneratedNotification);
				return;
			}

			deleteCustomView(inputList);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getErrorMessage(), e);
		}
	}

	private boolean deleteUserValidation(String selectedItem) {
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest reportCvRequest = new GtnWsCustomViewRequest();
		reportCvRequest.setCvSysId(Integer.parseInt(selectedItem));
		reportCvRequest.setCreatedBy(GtnUIFrameworkGlobalUI.getCurrentUser());
		request.setGtnWsCustomViewRequest(reportCvRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + "/deleteCustomViewUserValidationReport", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
		return cvResponse.isSuccess();
	}

	private boolean isCustomViewGenerated(String selectedItem, String componentId, boolean isCustomViewGenerate) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				"reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE, componentId).getComponentData();
		PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();
		GtnWsReportDashboardBean dashBoardBean = grid.getTableConfig().getGtnWsReportDashboardBean();
		return reportDashboardCustomViewValidation(dashBoardBean, isCustomViewGenerate, selectedItem)
				|| (dashBoardBean != null && dashBoardBean.getCustomViewMasterSid() == Integer.parseInt(selectedItem));
	}

	private boolean reportDashboardCustomViewValidation(GtnWsReportDashboardBean dashBoardBean,
			boolean isCustomViewGenerate, String selectedItem) {
		if (dashBoardBean == null && isCustomViewGenerate) {
			try {
				String dsSelectedItem = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW)
						.getCaptionFromV8ComboBox();
				return dsSelectedItem.equals(selectedItem);

			} catch (GtnFrameworkValidationFailedException e) {
				logger.error(e.getErrorMessage(), e);
				return false;
			}
		}
		return false;
	}

	private void deleteCustomView(List<Object> inputList) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Delete record " + inputList.get(1) + " ?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkReportCustomViewConfirmDeleteAction.class.getName());
		saveActionConfig.addActionParameter(inputList.get(0).toString());
		saveActionConfig.addActionParameter(inputList.get(5).toString());
		saveActionConfig.addActionParameter(inputList.get(4).toString());
		saveActionConfig.addActionParameter(inputList.get(1).toString());
		successActionConfigList.add(saveActionConfig);
		List<Object> actionParameter = (List<Object>) inputList.get(3);
		successActionConfigList.add((GtnUIFrameWorkActionConfig) actionParameter.get(2));
		confirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(inputList.get(2).toString(), confirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
