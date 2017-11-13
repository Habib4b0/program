
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnUIFrameWorkWarningAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String messageHeader = (String) paramsList.get(0);
		String messageBody = (String) paramsList.get(1);

		configureParams(gtnUIFrameWorkActionConfig);

		MessageBox.showPlain(Icon.WARN, messageHeader, messageBody, new MessageBoxListener() {

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
		return new GtnUIFrameWorkWarningAction();
	}

}
