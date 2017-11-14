package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkEmailModuleConfig {

	public GtnUIFrameworkRootConfig getEmailRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkEmailModuleLandingScreenConfig()
				.getMainView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
