package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;

public class GtnUIFrameworkModeValueChangeAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		if (!value.isEmpty()) {
			boolean visibility = GtnWsForecastConfigurationConstants.MODE_VALUE_INTERVAL.equals(value);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
					.setComponentVisible(!visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.setComponentVisible(!visibility);
			for (int pameterindex = 3; pameterindex < parameters.size() - 3; pameterindex++) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(pameterindex).toString())
						.setComponentVisible(visibility);
			}
			for (int pameterindex = parameters.size() - 3; pameterindex < parameters.size(); pameterindex++) {
				GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(parameters.get(pameterindex).toString());
				boolean required = baseComponent.isEnabled() && baseComponent.isVisible();
				baseComponent.setFieldRequired(required);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
