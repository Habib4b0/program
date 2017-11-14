package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIMsgBoxTwoButtonListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

public class GtnUIFrameWorkSaveConfirmationAction implements GtnUIFrameWorkAction {

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
		String messageHeader = (String) params.get(0);
		String messageBody = (String) params.get(1);
		String saveMsgComponentName = (String) params.get(2);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = (List<GtnUIFrameWorkActionConfig>) params.get(3);

		configureParams(gtnUIFrameWorkActionConfig);
		String body = messageBody
				+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent(saveMsgComponentName, componentId).getStringFromField()
				+ " ?";
		MessageBox.showPlain(Icon.QUESTION, messageHeader, body,
				new GtnUIMsgBoxTwoButtonListener(onSucessActionConfigList, componentId), ButtonId.YES, ButtonId.NO);

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