/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */

public class GtnFrameworkRemoveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        GtnUIFrameworkBaseComponent treeBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString());
        GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString());
        String hierarchyIndicator = parameters.get(3).toString();
        Object cutomerValueToRemove = treeBaseComponent.getValueFromComponent();
        GtnWsRecordBean selectedTreeBean = (GtnWsRecordBean) cutomerValueToRemove;
        if(cutomerValueToRemove == null) {
            GtnUIFrameworkGlobalUI.showMessageBox("No Level Selected", GtnUIFrameworkActionType.ALERT_ACTION,
                    "No Level Selected", "Please select a level to remove from tree structure");
            return;
        } 
        if(!hierarchyIndicator.equals(selectedTreeBean.getAdditionalPropertyByIndex(2))) {
            String hierarchyName = "C".equals(selectedTreeBean.getAdditionalPropertyByIndex(2)) ? "Customer Hierarchy" : 
                    "P".equals(selectedTreeBean.getAdditionalPropertyByIndex(2)) ? "Product Hierarchy" : "Deduction Hierarchy";
            GtnUIFrameworkGlobalUI.showMessageBox("Illegal Level", GtnUIFrameworkActionType.ALERT_ACTION,
                    "Illegal Level", "Level which is selected belongs to "+hierarchyName);
            return;
        }
        if(treeBaseComponent.getChildNodes(selectedTreeBean).isEmpty()) {
            treeBaseComponent.removeSelectedTreeItems(parameters.get(1).toString(), false);
            table.removeItemFromDataTable(cutomerValueToRemove);
//            GtnWsRecordBean addBean = (GtnWsRecordBean) cutomerValueToRemove;
            List<Object> dataList = selectedTreeBean.getProperties();
            dataList.addAll(selectedTreeBean.getAdditionalProperties());
            selectedTreeBean.setProperties(dataList);
            selectedTreeBean.setRecordHeader(Arrays.asList("levelName","levelNo","treeLevelNo","hierarchyIndicator","hierarchyLevelDefinitionSid"));
            table.addItemToDataTable(selectedTreeBean);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
