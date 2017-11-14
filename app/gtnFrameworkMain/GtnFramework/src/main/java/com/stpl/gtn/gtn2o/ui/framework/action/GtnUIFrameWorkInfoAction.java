package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIMsgBoxDummyListener;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

public class GtnUIFrameWorkInfoAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String messageHeader = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);
		String messageBody = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		MessageBox.showPlain(Icon.INFO, messageHeader, messageBody, GtnUIMsgBoxDummyListener.getListener(),
				ButtonId.OK);

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
