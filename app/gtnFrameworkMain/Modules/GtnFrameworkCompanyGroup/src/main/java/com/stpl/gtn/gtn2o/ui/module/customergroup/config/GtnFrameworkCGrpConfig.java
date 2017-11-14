package com.stpl.gtn.gtn2o.ui.module.customergroup.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkCGrpConfig {

	public GtnUIFrameworkRootConfig getCGrpRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkCGrpLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkCGrpAddComapanyConfig().getCGrpAddView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
