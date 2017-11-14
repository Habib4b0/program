/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkDeductionCalendarConfig {

	public GtnUIFrameworkRootConfig getDeductionCalendarRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkDCLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkDCAddConfig().getAddView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
