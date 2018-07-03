/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkTransactionDetailsValueChange implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDeductionLevelValueChange.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        String transactionDetails = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getValueFromComponent().toString();
        List<String[]> list = (List<String[]>) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(parameters.size() - 1).toString()).getValueFromComponent();
        for (String[] strings : list) {
            for (String string : strings) {
                gtnLogger.info(string);
            }
        }
        if ("GTN Details".equals(transactionDetails)) {
            enableDisableComponents(parameters, true);
        } else {
            enableDisableComponents(parameters, false);
        }
    }

    private void enableDisableComponents(List<Object> parameters, boolean value) {
        int length = parameters.size();
        for (int i = 2; i < length; i++) {
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(i).toString()).setEnable(value);

        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
