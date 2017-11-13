/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnFrameWorkCMEditCustomAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

    private final GtnWSLogger logger = GtnWSLogger
            .getGTNLogger(GtnFrameWorkCMEditCustomAction.class);

    @Override

    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        logger.debug("Inside Configure Parameters");
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        /* For making Table fieldfactory visible */
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyattachResultTable", componentId)
                .setTableFieldEditable(true);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierattachResultTable", componentId)
                .setTableFieldEditable(true);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassattachResultTable", componentId)
                .setTableFieldEditable(true);
        GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
                .getVaadinComponent("notesTab");
        notesTab.removeAndDisablingComponents(false);

    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
}
