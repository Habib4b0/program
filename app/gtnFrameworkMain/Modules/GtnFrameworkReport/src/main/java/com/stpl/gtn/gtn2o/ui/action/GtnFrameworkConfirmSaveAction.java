/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import java.util.Arrays;

public class GtnFrameworkConfirmSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkConfirmSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		try {
                    //reportCustomViewLookupcustomViewSave
			final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			List<Object> paramList = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnWsCustomViewRequest reportCvRequest = buildWsRequest(paramList, request);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CUSTOM_VIEW_SAVE_LOGIC,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();

			if (cvResponse.isSuccess()) {
				GtnUIFrameWorkActionConfig notification = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.NOTIFICATION_ACTION);
				notification.addActionParameter(reportCvRequest.getCustomViewName() + " has been successfully saved");
				notification.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notification);
                                String[] idsToReload=new String[]{GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW
                                ,"reportingDashboardTab_displaySelectionTabCustomView","dataSelectionTab_displaySelectionTabCustomView"};
                              for (String component : idsToReload) {
                                  GtnUIFrameworkBaseComponent customView=GtnUIFrameworkGlobalUI.getVaadinBaseComponent(component);
                                  if(customView!=null && customView.getComponent()!=null){
                                      new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                                              component, componentId,
                                              Arrays.asList(""));

                                      customView.loadV8ComboBoxComponentValue(String.valueOf(cvResponse.getCvSysId()));
                                  }
                            }

			}
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(ex.getErrorMessage(), ex);
		}
	}

	public GtnWsCustomViewRequest buildWsRequest(List<Object> paramList, final GtnUIFrameworkWebserviceRequest request)
			throws NumberFormatException {
		GtnWsCustomViewRequest reportCvRequest = (GtnWsCustomViewRequest) paramList.get(1);
		reportCvRequest.setCreatedBy(GtnUIFrameworkGlobalUI.getCurrentUser());
		reportCvRequest.setModifiedBy(GtnUIFrameworkGlobalUI.getCurrentUser());
		reportCvRequest.setModifiedDate(new Date());
		reportCvRequest.setCreatedDate(new Date());
		String selectedItem = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW).getV8StringFromField();
		if (!"".equals(selectedItem) && !"0".equals(selectedItem)) {
			reportCvRequest.setCvSysId(Integer.parseInt(selectedItem));
		}
		request.setGtnWsCustomViewRequest(reportCvRequest);
		return reportCvRequest;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
