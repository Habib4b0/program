package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkFrequencyValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkFrequencyValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnUIFrameworkFrequencyValueChangeAction");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		if (!value.isEmpty()) {
			boolean visibility = "Interval".equals(value);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
					.setComponentVisible(!visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.setComponentVisible(!visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
					.setComponentVisible(!visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
					.setComponentVisible(!visibility);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return null;
	}

}
