package com.stpl.gtn.gtn2o.ui.module.udc.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkUDCConfigurationConfig {

	public GtnUIFrameworkRootConfig getUDCConfigurationRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkUDCConfigurationLandingScreenConfig().getUDCView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
