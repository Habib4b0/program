package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.Collection;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class GtnUIFrameWorkSearchNoticationAction implements GtnUIFrameWorkAction {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkSearchNoticationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			String tableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);

			Collection<?> items = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId)
					.getItemsFromTable();
			String msg = "";
			if (!items.isEmpty()) {
				msg = "Search Completed";
			} else {
				msg = "No results found";
			}
			final Notification notif = new Notification(msg, Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(getPosition(gtnUIFrameWorkActionConfig.getActionParameterList()));
			notif.setStyleName("mystyle");
			notif.setDelayMsec(3000);
			notif.show(Page.getCurrent());
		} catch (Exception e) {
			logger.error("Error in GtnUIFrameWorkSearchNoticationAction ");
			new GtnFrameworkGeneralException(e);
		}
	}

	private Position getPosition(List<Object> parameters) {
		if (parameters.size() > 1 && parameters.get(1) != null) {
			int value = Integer.parseInt(String.valueOf(parameters.get(1)));
			Position position = value < 0 ? Position.BOTTOM_CENTER : Position.MIDDLE_CENTER;
			return value > 0 ? Position.TOP_CENTER : position;
		}
		return Position.TOP_CENTER;
	}
}