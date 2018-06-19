/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkReportCustomViewDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        //empty method
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        GtnWsCustomViewRequest reportCvRequest = new GtnWsCustomViewRequest();
        String selectedItem = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_displaySelectionTabCustomView").getV8StringFromField();
        if ("".equals(selectedItem) && "0".equals(selectedItem)) {
            return;
        }
        reportCvRequest.setCvSysId(Integer.parseInt(selectedItem));
        GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
                GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
                + "/deleteCustomViewReport",
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();

        if (cvResponse.isSuccess()) {
            GtnUIFrameWorkActionConfig notification = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
            notification.addActionParameter(reportCvRequest.getCustomViewName() + " has been successfully Deleted");
            notification.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
            GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notification);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
