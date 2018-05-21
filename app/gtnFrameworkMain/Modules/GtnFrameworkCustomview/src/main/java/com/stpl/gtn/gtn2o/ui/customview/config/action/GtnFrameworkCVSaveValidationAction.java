/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCVSaveValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        StringBuilder chErrorMsg = new StringBuilder();
        List<Object> paramList = gtnUIFrameWorkActionConfig.getActionParameterList();
        String[] fields = (String[]) paramList.get(1);
        GtnUIFrameworkGlobalUI.validateFields(fields, chErrorMsg);
        GtnUIFrameworkBaseComponent treeTable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(paramList.get(2).toString());
        if (chErrorMsg.length() > 0) {
            String msg = GtnFrameworkCVConstants.GTN_CUSTOM_VIEW_MANDATORY_FIELDS_VALIDATION
                    + " <br> " + chErrorMsg.toString();

            throw new GtnFrameworkValidationFailedException(msg, componentId);
        }
        GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
        if (treeTable.getAllTreeNodes().isEmpty()) {
            GtnUIFrameworkGlobalUI.showMessageBox("Error", GtnUIFrameworkActionType.ALERT_ACTION,
                    "Error", "Please make a tree to save");
            return;
        }

        saveCustomView(componentId, paramList);
    }

    private void saveCustomView(String componentId, List<Object> paramList) throws GtnFrameworkGeneralException {
        final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
        request.setGtnWsCustomViewRequest(cvRequest);
        String[] fields = (String[]) paramList.get(1);
        cvRequest.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
        String customViewName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[0])
                .getStringFromField();
        String customViewDescription = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[1])
                .getStringFromField();
        int customerRelationSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[1])
                .getIntegerFromField();
        int productRelationSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[1])
                .getIntegerFromField();
        String customViewType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(paramList.get(2).toString())
                .getStringFromField();
        cvRequest.setCustomViewName(customViewName);
        cvRequest.setCustomViewDescription(customViewDescription);
        cvRequest.setCustomerRelationshipSid(customerRelationSid);
        cvRequest.setProductRelationshipSid(productRelationSid);
        cvRequest.setCustomViewType(customViewType);
        GtnUIFrameworkBaseComponent cvTreeBaseComponent = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(paramList.get(3).toString());
        if (cvTreeBaseComponent != null) {
            List<GtnWsRecordBean> treeNodeList = cvTreeBaseComponent.getTreeNodes();
            cvRequest.setCvTreeNodeList(treeNodeList);
        }
        GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
                GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
                + GtnWsCustomViewConstants.CHECK_CUSTOM_VIEW_SAVE,
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
        if (cvResponse.isSuccess()) {
            saveCustomView(componentId, paramList, customViewName, cvRequest);
        }
        GtnUIFrameWorkActionConfig cvSaveAlertAction = new GtnUIFrameWorkActionConfig(
                GtnUIFrameworkActionType.ALERT_ACTION);
        cvSaveAlertAction.addActionParameter(cvResponse.getMessageType());
        cvSaveAlertAction.addActionParameter(cvResponse.getMessage());
        GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cvSaveAlertAction);
    }

    private void saveCustomView(String componentId, List<Object> parameters, String customViewName,
            GtnWsCustomViewRequest cvRequest) throws GtnFrameworkGeneralException {
        GtnUIFrameWorkAction confirmAction = GtnUIFrameworkActionType.CONFIRMATION_ACTION.getGtnUIFrameWorkAction();
        GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
        confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
        confirmActionConfig.addActionParameter("Save record " + customViewName + " ?");
        List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
        saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        saveActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
        saveActionConfig.addActionParameter(parameters.get(1).toString());
        saveActionConfig.addActionParameter(parameters.get(2).toString());
        saveActionConfig.addActionParameter(parameters.get(3).toString());
        saveActionConfig.addActionParameter(cvRequest);
        successActionConfigList.add(saveActionConfig);
        confirmActionConfig.addActionParameter(successActionConfigList);
        confirmAction.configureParams(confirmActionConfig);
        confirmAction.doAction(componentId, confirmActionConfig);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
