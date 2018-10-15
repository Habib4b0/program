/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.util;

import com.stpl.gtn.gtn2o.registry.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnFrameworkAlertUtil {

    public GtnUIFrameWorkActionConfig throwAlertUtilAction(String url) {
        GtnUIFrameWorkActionConfig alert = new GtnUIFrameWorkActionConfig();
        alert.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
        alert.addActionParameter("Error");
        alert.addActionParameter("Requested Url " + url + " not found");
        try {
            GtnUIFrameworkActionExecutor.executeSingleAction("", alert);

        } catch (GtnFrameworkGeneralException ex) {
            Logger.getLogger(GtnCustomerAvailableTableLoadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alert;

    }
    public GtnUIFrameWorkActionConfig throwAlertUtil(String url) {
        GtnUIFrameWorkActionConfig alert = new GtnUIFrameWorkActionConfig();
        alert.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
        alert.addActionParameter("Error");
        alert.addActionParameter("Requested Url " + url + " not found");
        return alert;

    }

}
