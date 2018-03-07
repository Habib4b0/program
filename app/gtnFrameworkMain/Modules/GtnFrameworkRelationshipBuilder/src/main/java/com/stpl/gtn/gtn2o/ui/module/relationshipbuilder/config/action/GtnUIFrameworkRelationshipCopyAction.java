/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.List;

/**
 *
 * @author vinodhini.palanisamy
 */
public class GtnUIFrameworkRelationshipCopyAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{
    private final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkRelationshipCopyAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        LOGGER.info("Inside Relationship Copy Action");
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        Object value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getValueFromComponent();
        GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) value;
        GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
        navigationActionConfig.addActionParameter(parameters.get(18).toString());
        GtnUIFrameWorkAction navigationAction = GtnUIFrameworkActionType.NAVIGATION_ACTION.getGtnUIFrameWorkAction();
        navigationAction.configureParams(navigationActionConfig);
        navigationAction.doAction(componentId, navigationActionConfig);
        
        GtnFrameworkValueChangeManager.setValueChangeAllowed(false);
        int index = (Integer) parameters.get(2);
        int versionNo = gtnWsRecordBean.getIntegerPropertyByIndex(index + 3);
        GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(3).toString(), versionNo);
        GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(4).toString(), gtnWsRecordBean);
        GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Copy");
        
        GtnUIFrameworkViewButtonAction viewAction = new GtnUIFrameworkViewButtonAction();
        viewAction.configureParams(gtnUIFrameWorkActionConfig);
        viewAction.doAction(componentId, gtnUIFrameWorkActionConfig);
        GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return new GtnUIFrameworkRelationshipCopyAction();
    }
    
}
