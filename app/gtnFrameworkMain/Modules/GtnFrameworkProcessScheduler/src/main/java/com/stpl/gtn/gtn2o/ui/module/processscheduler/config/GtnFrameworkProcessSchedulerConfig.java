package com.stpl.gtn.gtn2o.ui.module.processscheduler.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkProcessSchedulerConfig {

	public GtnUIFrameworkRootConfig getProcessSchedulerRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkProcessSchedulerLandingScreenConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
