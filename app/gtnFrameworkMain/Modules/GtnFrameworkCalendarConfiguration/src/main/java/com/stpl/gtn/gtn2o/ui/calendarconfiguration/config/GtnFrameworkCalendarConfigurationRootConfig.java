/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCalendarConfigurationRootConfig {

	public GtnUIFrameworkRootConfig getContractDashboardRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkCalendarConfigurationSearchConfig().getMainView());
		viewList.add(new GtnFrameworkCalendarConfigurationCrudConfig().getCalendarConfigView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
