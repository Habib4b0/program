package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkFrequencyValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkFrequencyValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnUIFrameworkFrequencyValueChangeAction");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnUIFrameworkFrequencyValueChangeAction");
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		if (!value.isEmpty()) {
			boolean visibility = "Time".equals(value);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).setComponentVisible(visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString()).setComponentVisible(visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).setComponentVisible(visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString()).setComponentVisible(visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString()).setComponentVisible(visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString()).setComponentVisible(!visibility);
			
		}
		if("Time".equals(value)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN1_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN2_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN3_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES1_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES3_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		}else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN_EVERY_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID).loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
