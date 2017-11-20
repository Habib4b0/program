
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnUIFrameWorkAlertAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String messageHeader = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);
		String messageBody = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

		MessageBox.showPlain(Icon.ERROR, messageHeader, messageBody, new MessageBoxListener() {

			@Override
			public void buttonClicked(final ButtonId buttonId) {
				return;

			}
		}, ButtonId.OK);

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
