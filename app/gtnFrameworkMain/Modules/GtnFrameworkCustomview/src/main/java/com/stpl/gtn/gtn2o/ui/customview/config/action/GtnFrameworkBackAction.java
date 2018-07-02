/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIMsgBoxTwoButtonListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;

public class GtnFrameworkBackAction implements GtnUIFrameWorkAction , GtnUIFrameworkDynamicClass{

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;

    }

    @SuppressWarnings("unchecked")
    @Override
    public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {

        if (!String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).equalsIgnoreCase("View")) {
            final List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
            String messageHeader = (String) params.get(1);
            String messageBody = (String) params.get(2);
            List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = (List<GtnUIFrameWorkActionConfig>) params.get(3);
            List<GtnUIFrameWorkActionConfig> onFailureActionConfigList = params.size() > 4
                    ? (List<GtnUIFrameWorkActionConfig>) params.get(4) : null;

            MessageBox.showPlain(Icon.QUESTION, messageHeader, messageBody,
                    new GtnUIMsgBoxTwoButtonListener(onSucessActionConfigList, onFailureActionConfigList, componentId),
                    ButtonId.YES, ButtonId.NO);

        }

    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
}
