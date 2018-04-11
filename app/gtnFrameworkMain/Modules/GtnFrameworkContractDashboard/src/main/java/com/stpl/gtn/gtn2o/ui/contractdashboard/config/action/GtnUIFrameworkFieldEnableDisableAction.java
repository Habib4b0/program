package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkFieldEnableDisableAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkFieldEnableDisableAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering into the doAction( ) of GtnUIFrameworkFieldEnableDisableAction");
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent fieldBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		String value = fieldBaseComponent.getStringFromField();
		if (Boolean.parseBoolean(String.valueOf(parameters.get(2)))) {
			value = fieldBaseComponent.getCaptionFromComboBox();
		}
		boolean isEnable = String.valueOf(parameters.get(3)).equalsIgnoreCase(value);
		List<String> componentIds = gtnUIFrameWorkActionConfig.getFieldValues();
		for (String componentId1 : componentIds) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId1).setEnable(isEnable);
		}
		logger.debug("End  doAction( ) of GtnUIFrameworkFieldEnableDisableAction");

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
