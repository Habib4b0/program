/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.priceschedule.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.popup.GtnFrameworkDeductionCalendarPopUpConfig;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.popup.GtnFrameworkFormulaPopUpConfig;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.popup.GtnFrameworkNSSearchConfig;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.popup.GtnFrameworkParentPSPopupConfig;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkPSConfig {

	public GtnUIFrameworkRootConfig getPriceScheduleRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkPSSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkPSAddConfig().getAddView());
		viewList.add(new GtnFrameworkFormulaPopUpConfig().getSearchView());
		viewList.add(new GtnFrameworkParentPSPopupConfig().getSearchView());
		viewList.add(new GtnFrameworkNSSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkDeductionCalendarPopUpConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
