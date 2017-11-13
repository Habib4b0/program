package com.stpl.gtn.gtn2o.ui.module.itemgroup.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkItemGrpConfig {

	public GtnUIFrameworkRootConfig getIGrpRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkItemGrpLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkItemGrpAddConfig().getItemGrpAddView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
