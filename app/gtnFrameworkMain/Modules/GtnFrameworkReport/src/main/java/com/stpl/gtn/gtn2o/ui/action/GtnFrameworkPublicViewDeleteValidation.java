/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.List;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnFrameworkPublicViewDeleteValidation implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean wsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(1).toString()).getComponentData().getCustomData();
		if (wsRecordBean == null) {
			wsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(2).toString()).getComponentData().getCustomData();
		}
		Integer getViewId = (Integer) wsRecordBean.getPropertyValueByIndex(4);
		GtnWsReportDataSelectionBean reportDataSelectionBean = new GtnWsReportDataSelectionBean();
		reportDataSelectionBean.setViewId(getViewId);
		GtnWsReportRequest reportRqst = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRqst = new GtnWsGeneralRequest();
		reportRqst.setDataSelectionBean(reportDataSelectionBean);
		generalRqst.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		wsRequest.setGtnWsGeneralRequest(generalRqst);
		wsRequest.setGtnWsReportRequest(reportRqst);
		GtnUIFrameworkWebserviceResponse getresponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORT_DELETE_VALIDATION_SERVICE, "report",
				wsRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if (!getresponse.getGtnWsGeneralResponse().isSucess()) {
                    GtnUIFrameWorkActionConfig alert = new GtnUIFrameWorkActionConfig();
			alert.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			alert.addActionParameter("Delete View Confirmation");
			alert.addActionParameter("You do not have permission to delete this projection.It can only be deleted by the creator");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alert);
			return;
		}
    }
    
}
