/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnModeOptionValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
        if(!actionParamList.get(2).toString().contains("DataSelection")){
        setEnableAndDisableComponents(componentId, actionParamList);
        }
    }

	private void setEnableAndDisableComponents(String componentId, List<Object> actionParamList) {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(1)),componentId);
        String modeValue = baseComponent.getV8StringFromField();
        boolean add = "Add".equals(modeValue);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(2)),componentId).setEnable(add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(3)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(4)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(5)),componentId).setEnable(add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(6)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(7)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(8)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(9)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(10)),componentId).setEnable(!add);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(11)),componentId).setEnable(!add);
	}
    
    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
