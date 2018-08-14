/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractComponent;
import java.util.List;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnFrameworkReportSearchNotification implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkReportSearchNotification.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        try {
            String getTableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

            AbstractComponent abstractComponentData = GtnUIFrameworkGlobalUI.getVaadinComponent(getTableId, componentId);
            GtnUIFrameworkComponentData compData = (GtnUIFrameworkComponentData) abstractComponentData.getData();
            List<GtnWsRecordBean> getRows = compData.getPagedGrid().getDataSet().getRows();
            if (getRows.isEmpty()) {
                GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
                alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
                alertAction.addActionParameter("No Results Found");
                alertAction.addActionParameter("No Report Profiles match your search criteria.");
                GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
            }
        } catch (Exception e) {
            gtnLogger.error("Error in GtnFrameworkReportSearchNotification ");
            new GtnFrameworkGeneralException(e);
        }
    }

    private Position getNotifPosition(List<Object> params) {
        if (params.size() > 2 && params.get(2) != null) {
            int value = Integer.parseInt(String.valueOf(params.get(2)));
            Position position = value < 0 ? Position.BOTTOM_CENTER : Position.MIDDLE_CENTER;
            return value > 0 ? Position.TOP_CENTER : position;
        }
        return Position.TOP_CENTER;
    }
}
