package com.stpl.gtn.gtn2o.ui.module.forecasting.config;

import com.stpl.gtn.gtn2o.registry.config.GtnUIFrameworkDataSelectionScreenConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkForecastingLandingScreenConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkDataSelectionScreenConfig dataSelection = new GtnUIFrameworkDataSelectionScreenConfig();
		return dataSelection.getDataSelectionView("Commercial Forecasting");
	}
}
