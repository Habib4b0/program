/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkForecastConfigurationRootConfig {

	public GtnUIFrameworkRootConfig getForecastConfigurationRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkForecastConfigurationConfig().getMainView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
