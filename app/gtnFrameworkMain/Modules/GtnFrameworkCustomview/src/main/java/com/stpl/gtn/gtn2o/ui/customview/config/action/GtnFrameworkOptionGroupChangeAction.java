/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkOptionGroupChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
        String optionValue = component.getStringFromField();
        GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(parameters.get(2).toString());
        GtnUIFrameworkBaseComponent tableBaseLayout = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(parameters.get(1).toString());
        if (GtnFrameworkCVConstants.CUSTOM_VIEW_OPTION_DISCOUNT.equals(optionValue)) {
            tableBaseComponent.setVisible(true);
            tableBaseLayout.setVisible(true);
        } else {
            tableBaseComponent.setVisible(false);
            tableBaseLayout.setVisible(false);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
