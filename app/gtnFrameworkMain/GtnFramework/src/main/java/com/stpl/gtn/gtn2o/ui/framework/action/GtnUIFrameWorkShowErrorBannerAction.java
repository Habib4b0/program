package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.server.ErrorMessage.ErrorLevel;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class GtnUIFrameWorkShowErrorBannerAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String errorBannercomponentId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);
		String errrorMessage = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		Label errorBanner = (Label) GtnUIFrameworkGlobalUI.getVaadinComponent(errorBannercomponentId, componentId);
		String errorMessage = String.valueOf(errrorMessage);
		errorBanner.setContentMode(ContentMode.HTML);
		errorBanner.setVisible(!isStringNull(errorMessage));
		errorBanner.setValue(errorMessage);
		errorBanner.setComponentError(
				new UserError(errorMessage, com.vaadin.server.AbstractErrorMessage.ContentMode.HTML, ErrorLevel.ERROR));
	}

	private boolean isStringNull(String errorMessage) {
		return "null".equals(errorMessage) || errorMessage.isEmpty();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
