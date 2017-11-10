
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.UI;

public class GtnUIFrameWorkNavigationAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		UI.getCurrent().getNavigator().navigateTo((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0));

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