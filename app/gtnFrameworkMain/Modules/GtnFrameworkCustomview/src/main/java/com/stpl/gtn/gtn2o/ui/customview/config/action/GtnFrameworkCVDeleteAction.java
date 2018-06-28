/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import java.util.List;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnFrameworkCVDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkCVDeleteAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        LOGGER.info("configure params in GtnFrameworkCVDeleteValidationAction");
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
       try{
        final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        Object value = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(parameters.get(1).toString())
                .getValueFromComponent();
        GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
        GtnWsRecordBean customViewBean = (GtnWsRecordBean) value;
        int customSid = (int) customViewBean.getPropertyValue("customViewMasterSId");
        String custViewName = String.valueOf(customViewBean.getPropertyValue(GtnFrameworkCommonConstants.TREE_VIEW_NAME));
        String custViewDesc = (String) customViewBean.getPropertyValue(GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION);
        String custViewType = String.valueOf(customViewBean.getPropertyValue(GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));
        
        cvRequest.setCvSysId(customSid);
        cvRequest.setCustomViewName(custViewName);
        cvRequest.setCustomViewDescription(custViewDesc);
        cvRequest.setCustomViewType(custViewType);
        request.setGtnWsCustomViewRequest(cvRequest);
        GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
                GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
                + GtnWsCustomViewConstants.CUSTOM_VIEW_DELETE,
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
         GtnWsCustomViewResponse rbNewResponse = newResponse.getGtnWsCustomViewResponse();
		if (rbNewResponse.isSuccess()) {
			GtnUIFrameworkBaseComponent removeItem = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(2).toString());
			removeItem.removeItemFromDataTable(parameters.get(1));
			removeItem.setTableValue(null);
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.NOTIFICATION_ACTION,
					rbNewResponse.getMessage(), null);
			return;
		}
		GtnUIFrameWorkActionConfig rbDeleteSuccessAlertAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		rbDeleteSuccessAlertAction.addActionParameter(rbNewResponse.getMessageType());
		rbDeleteSuccessAlertAction.addActionParameter(rbNewResponse.getMessage());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbDeleteSuccessAlertAction);
    }catch(Exception ex)
    {
        LOGGER.error("message",ex);
    }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
