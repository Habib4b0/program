package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkIfpConfig {

	public GtnUIFrameworkRootConfig getIFPRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkIfpLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkIfpAddConfig().getIFPAddView());
		viewList.add(new GtnFrameworkParentIfpPopupConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
