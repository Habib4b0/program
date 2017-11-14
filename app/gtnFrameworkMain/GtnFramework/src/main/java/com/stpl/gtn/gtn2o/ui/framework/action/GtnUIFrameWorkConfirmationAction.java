package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIMsgBoxTwoButtonListener;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

public class GtnUIFrameWorkConfirmationAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		final List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
		String messageHeader = (String) params.get(0);
		String messageBody = (String) params.get(1);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = (List<GtnUIFrameWorkActionConfig>) params.get(2);
		List<GtnUIFrameWorkActionConfig> onFailureActionConfigList = params.size() > 3
				? (List<GtnUIFrameWorkActionConfig>) params.get(3) : null;

		MessageBox.showPlain(Icon.QUESTION, messageHeader, messageBody,
				new GtnUIMsgBoxTwoButtonListener(onSucessActionConfigList, onFailureActionConfigList, componentId),
				ButtonId.YES, ButtonId.NO);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}