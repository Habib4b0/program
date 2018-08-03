/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnFrameworkCVSearchNotification implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkCVSearchNotification.class);

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        try {
            String resultTableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

            int checkSize = ((ExtCustomTable) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId, componentId)
                    .getComponentData().getCustomData()).getContainerDataSource().size();
            String message = "";
            if (checkSize != 0) {
                message = "Search Completed";
            } else {
                message = "No results found";
            }
            showNotification(message, gtnUIFrameWorkActionConfig);
        } catch (Exception e) {
            logger.error("Error in Custom View SearchNoticationAction ");
            new GtnFrameworkGeneralException(e);
        }
    }

    private Position getNotifPosition(List<Object> parameters) {
        if (parameters.size() > 2 && parameters.get(2) != null) {
            int value = Integer.parseInt(String.valueOf(parameters.get(1)));
            Position position = value < 0 ? Position.BOTTOM_CENTER : Position.MIDDLE_CENTER;
            return value > 0 ? Position.TOP_CENTER : position;
        }
        return Position.TOP_CENTER;
    }

    private void showNotification(String message, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
        final Notification createNotif = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
        createNotif.setPosition(getNotifPosition(gtnUIFrameWorkActionConfig.getActionParameterList()));
        createNotif.setStyleName("mystyle");
        createNotif.setDelayMsec(3000);
        createNotif.show(Page.getCurrent());
    }

}
