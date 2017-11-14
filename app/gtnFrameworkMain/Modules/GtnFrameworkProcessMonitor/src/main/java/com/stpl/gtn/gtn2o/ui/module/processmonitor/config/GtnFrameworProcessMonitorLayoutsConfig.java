package com.stpl.gtn.gtn2o.ui.module.processmonitor.config;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;

public class GtnFrameworProcessMonitorLayoutsConfig {

	private static GtnFrameworProcessMonitorLayoutsConfig commonConfig = null;

	private GtnFrameworProcessMonitorLayoutsConfig() {

	}

	public static synchronized GtnFrameworProcessMonitorLayoutsConfig getInstance() {
		if (commonConfig == null) {
			commonConfig = new GtnFrameworProcessMonitorLayoutsConfig();
		}
		return commonConfig;
	}

	public GtnUIFrameWorkActionConfig getUIFrameworkActionConfig(final GtnUIFrameworkActionType type) {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(type);
		return actionConfig;
	}

}
