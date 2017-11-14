package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class GtnUIFrameWorkNotificationAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(final String componetId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String message = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);
		String caption = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		Notification notif = new Notification(message, caption, Notification.Type.HUMANIZED_MESSAGE);
		notif.setPosition(getPosition(gtnUIFrameWorkActionConfig.getActionParameterList()));
		notif.setDelayMsec(GtnWsNumericConstants.THREE_THOUSAND);
		notif.show(Page.getCurrent());

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	private Position getPosition(List<Object> parameters) {
		if (parameters.size() > 2 && parameters.get(2) != null) {
			int value = Integer.parseInt(String.valueOf(parameters.get(2)));
			Position position = value < 0 ? Position.BOTTOM_CENTER : Position.MIDDLE_CENTER;
			return value > 0 ? Position.TOP_CENTER : position;
		}
		return Position.MIDDLE_CENTER;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}