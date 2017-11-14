package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkCfpConfig {

	public GtnUIFrameworkRootConfig getCFPRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkCfpLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkCfpAddConfig().getCFPAddView());
		viewList.add(new GtnFrameworkParentCfpPopupConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
