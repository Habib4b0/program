/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import java.util.Date;
import java.util.List;

public class GtnFrameworkConfirmSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkCVSaveValidationAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
       try{
        final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        List<Object> paramList = gtnUIFrameWorkActionConfig.getActionParameterList();
        
        GtnWsCustomViewRequest cvRequest = (GtnWsCustomViewRequest) paramList.get(4);
        cvRequest.setCreatedBy(GtnUIFrameworkGlobalUI.getCurrentUser());
        cvRequest.setModifiedBy(GtnUIFrameworkGlobalUI.getCurrentUser());
        cvRequest.setModifiedDate(new Date());
        cvRequest.setCreatedDate(new Date());
        if(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).equalsIgnoreCase("Edit")){
        cvRequest.setCvSysId(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("customSid"))));
        }
        request.setGtnWsCustomViewRequest(cvRequest);
        GtnUIFrameworkWebserviceResponse response = getResponse(wsclient, request);
        GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
        if (cvResponse.isSuccess()) {
            final Notification notif = new Notification(cvRequest.getCustomViewName() + " has been successfully saved",
                    Notification.Type.HUMANIZED_MESSAGE);

            notif.setPosition(Position.TOP_CENTER);
            notif.setStyleName(GtnFrameworkCssConstants.MY_STYLE);
            notif.setDelayMsec(3000);
            notif.show(Page.getCurrent());
        }
    }catch(Exception ex)
    {
        LOGGER.error("message",ex);
    }
    }

	public GtnUIFrameworkWebserviceResponse getResponse(final GtnUIFrameworkWebServiceClient wsclient,
			final GtnUIFrameworkWebserviceRequest request) {
		return wsclient.callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CUSTOM_VIEW_SAVE_LOGIC,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
