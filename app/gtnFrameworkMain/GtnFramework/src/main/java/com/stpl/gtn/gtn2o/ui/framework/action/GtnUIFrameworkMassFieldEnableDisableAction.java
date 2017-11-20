package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnUIFrameworkMassFieldEnableDisableAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String sourceComponentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(sourceComponentId).getStringFromField();
		boolean isEnable = "Enable".equals(value);
		List<String> componentIds = gtnUIFrameWorkActionConfig.getFieldValues();
		for (String componentId : componentIds) {
			Component component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId)
					.getComponent();
			component.setEnabled(isEnable);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}