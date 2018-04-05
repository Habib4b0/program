package com.stpl.gtn.gtn2o.ui.module.forecasting.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkCommercialForecastingConfig {

	public GtnUIFrameworkRootConfig getForecastingRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkForecastingLandingScreenConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
