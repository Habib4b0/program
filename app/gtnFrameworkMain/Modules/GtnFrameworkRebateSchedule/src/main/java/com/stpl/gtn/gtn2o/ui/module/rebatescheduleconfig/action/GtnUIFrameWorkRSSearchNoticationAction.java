/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnUIFrameWorkRSSearchNoticationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkRSSearchNoticationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnUIFrameWorkRSSearchNoticationAction");
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			String tableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

			int itemSize = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId).getTableSize();
			String msg = "";
			if (itemSize != 0) {
				msg = "Search Completed";
			} else {
				msg = "No results found";
			}
			final Notification notif = new Notification(msg, Notification.Type.HUMANIZED_MESSAGE);
			notif.setStyleName("mystyle");
			notif.setDelayMsec(3000);
			notif.show(Page.getCurrent());
		} catch (Exception e) {
			logger.error("Error in GtnUIFrameWorkRSSearchNoticationAction ");
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}