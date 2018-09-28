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
import com.vaadin.v7.data.Property;
import java.util.List;

public class GtnFrameworkCustomViewEditAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCustomViewEditAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        //Do nothing
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        try {
            
            
            String nameSpacePrefix="V002_";
            List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
            Object value = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString())
                    .getValueFromComponent();
            if (value == null) {
                GtnUIFrameWorkActionConfig rbEditAlertAction = new GtnUIFrameWorkActionConfig(
                        GtnUIFrameworkActionType.ALERT_ACTION);
                rbEditAlertAction.addActionParameter("View Error");
		rbEditAlertAction.addActionParameter("Please select a record to "+parameters.get(2));
                GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbEditAlertAction);
                return;
            }
            
            GtnUIFrameworkGlobalUI.addSessionProperty("mode", parameters.get(2));
            
            executeNavigateAction(componentId); 
            GtnWsRecordBean customViewBean = (GtnWsRecordBean) value;
            int customSid = (int)customViewBean.getPropertyValue("customViewMasterSId");
            
            setValuesToFields(customViewBean, nameSpacePrefix, customSid, parameters);
            
            GtnUIFrameworkGlobalUI.addSessionProperty("customSid", customSid);
            GtnUIFrameworkGlobalUI.addSessionProperty("customViewBean", customViewBean);
        } catch (Exception e) {
            gtnLogger.error("Exception in GtnUIFrameworkEditButtonAction", e);
        }
    }

	public void executeNavigateAction(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.addActionParameter("V002");
		GtnUIFrameWorkAction navigationAction = GtnUIFrameworkActionType.NAVIGATION_ACTION
		        .getGtnUIFrameWorkAction();
		navigationAction.configureParams(navigationActionConfig);
		navigationAction.doAction(componentId, navigationActionConfig);
	}

    private void setValuesToFields(GtnWsRecordBean customViewBean, String nameSpacePrefix, int customSid, List<Object> parameters) throws GtnFrameworkGeneralException {
        String viewName = String.valueOf(customViewBean.getPropertyValue(GtnFrameworkCommonConstants.TREE_VIEW_NAME));
        String description = (String)customViewBean.getPropertyValue(GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION);
        String viewType =String.valueOf(customViewBean.getPropertyValue(GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));
        int customerRaltionSid = Integer.parseInt((String)customViewBean.getPropertyValue(GtnFrameworkCommonConstants.CUTOMER_RELATION_SID));
        int productRealtionSid = Integer.parseInt((String)customViewBean.getPropertyValue(GtnFrameworkCommonConstants.PRODUCT_RELATION_SID));
        
        
        GtnUIFrameworkBaseComponent baseViewName= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix+GtnFrameworkCommonConstants.TREE_VIEW_NAME);
        GtnUIFrameworkBaseComponent descriptionField= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix+GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION);
        GtnUIFrameworkBaseComponent viewTypeField= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix+GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE);
        GtnUIFrameworkBaseComponent customerRelationSidField= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix+GtnFrameworkCommonConstants.CUTOMER_RELATION);
        GtnUIFrameworkBaseComponent productRelationSidField= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix+GtnFrameworkCommonConstants.PRODUCT_RELATION);
        GtnUIFrameworkBaseComponent saveBtn= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent("customViewAddSaveButton");
        GtnUIFrameworkBaseComponent gtnCustomerGreaterButton= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix + "gtnCustomerGreaterButton");
        GtnUIFrameworkBaseComponent gtnProductGreaterButton= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix + "gtnCustomerLesserButton");
        GtnUIFrameworkBaseComponent gtnCustomerLesserButton= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix + "gtnProductGreaterButton");
        GtnUIFrameworkBaseComponent gtnProductLesserButton= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix + "gtnProductLesserButton");
        GtnUIFrameworkBaseComponent gtnDeductionGreaterButton= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix + "gtnDeductionGreaterButton");
        GtnUIFrameworkBaseComponent gtnDeductionLesserButton= GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(nameSpacePrefix + "gtnDeductionLesserButton");
        
        baseViewName.loadFieldValue(viewName);
        descriptionField.loadFieldValue(description);
        viewTypeField.selectOptionGroupValue(viewType);
        getTreeData(customSid,parameters,nameSpacePrefix,viewType);
        customerRelationSidField.setEnable(false);
        productRelationSidField.setEnable(false);
        customerRelationSidField.loadComboBoxComponentValue(customerRaltionSid);
        productRelationSidField.loadComboBoxComponentValue(productRealtionSid);
        
        boolean isEnable=String.valueOf(parameters.get(2)).equalsIgnoreCase("VIEW");
        
        baseViewName.setEnable(!isEnable);
        descriptionField.setEnable(!isEnable);
        viewTypeField.setEnable(!isEnable);
        saveBtn.setVisible(!isEnable);
        gtnCustomerGreaterButton.setEnable(!isEnable);
        gtnCustomerLesserButton.setEnable(!isEnable);
        gtnProductGreaterButton.setEnable(!isEnable);
        gtnProductLesserButton.setEnable(!isEnable);
        gtnDeductionGreaterButton.setEnable(!isEnable); 
        gtnDeductionLesserButton.setEnable(!isEnable);
    }

    private void getTreeData(int customSid,List<Object> parameters,String nameSpacePrefix,String viewType) throws GtnFrameworkGeneralException {
        final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        final GtnUIFrameworkWebserviceRequest generalRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
        cvRequest.setCustomViewType(viewType);
        cvRequest.setCvSysId(customSid);
        cvRequest.setCustomViewType("");
        generalRequest.setGtnWsCustomViewRequest(cvRequest);

        GtnUIFrameworkWebserviceResponse response = getResponse(wsclient, generalRequest);
        GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
       
         GtnUIFrameworkBaseComponent tableTreeComponent=GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(nameSpacePrefix + String.valueOf(parameters.get(3)));
         ((Property<Object>) tableTreeComponent.getComponentData().getCustomData()).setValue(null);
        GtnWsRecordBean parent=null;
        int i=0;
        for (GtnWsRecordBean object : cvResponse.getCvTreeNodeList()) {
            if (i != cvResponse.getCvTreeNodeList().size() - 1) {
                if (parent == null) {
                    tableTreeComponent.addItemToTreeDataTable(object, true);
                } else {
                    tableTreeComponent.addItemToTreeDataTable(parent, object, true);
                    tableTreeComponent.expandTreeItem(parent);
                }
            } else {
                tableTreeComponent.addItemToTreeDataTable(parent, object, true);
                    tableTreeComponent.expandTreeItem(parent);
            }
            i++;
            parent = object;
        }
       
    
    }

	public GtnUIFrameworkWebserviceResponse getResponse(final GtnUIFrameworkWebServiceClient wsclient,
			final GtnUIFrameworkWebserviceRequest generalRequest) {
		return wsclient.callGtnWebServiceUrl(
                GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
                + GtnWsCustomViewConstants.CUSTOM_VIEW_GET_TREE_DATA,
                generalRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
	}

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
