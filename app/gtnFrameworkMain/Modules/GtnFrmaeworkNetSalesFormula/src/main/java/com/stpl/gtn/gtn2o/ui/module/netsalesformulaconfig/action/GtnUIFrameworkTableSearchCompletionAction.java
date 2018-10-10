package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.Collection;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

public class GtnUIFrameworkTableSearchCompletionAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTableSearchCompletionAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkTableSearchCompletionAction();
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			String tableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

			Collection<?> items = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId)
					.getItemsFromTable(); 
			String msg = "";
			if (!items.isEmpty()) {
				msg = "Search Completed";
			} else {
				List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
				String messageHeader = (String) paramsList.get(2);
				String messageBody = (String) paramsList.get(3);
				MessageBox.showPlain(Icon.ERROR, messageHeader, messageBody, buttonId -> {
                            //nothing 
                                }, ButtonId.OK);
				throw new GtnFrameworkSkipActionException("Skip Action");
			}
			notificationIfTableNotEmpty(items, msg);  
		} catch (Exception e) {
			logger.error("Error in GtnUIFrameWorkTableSearchCompletionAction ");
		}
	}

	private void notificationIfTableNotEmpty(Collection<?> items, String msg) {
		if (!items.isEmpty()) {
		final Notification notif = new Notification(msg, Notification.Type.HUMANIZED_MESSAGE);
		notif.setPosition(Position.TOP_CENTER);
		notif.setStyleName("mystyle");
		notif.setDelayMsec(3000);
		notif.show(Page.getCurrent());
		}
	}

	
}