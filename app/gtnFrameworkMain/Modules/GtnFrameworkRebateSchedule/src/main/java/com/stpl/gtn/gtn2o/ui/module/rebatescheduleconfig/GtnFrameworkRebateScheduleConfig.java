/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkDeductionCalendarPopUpConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkFormulaPopUpConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkNSSearchConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkNetSaleRulePopupConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkRPPopUpSearchConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkRSPopupConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup.GtnFrameworkRebateSchedulePopUpConfig;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRebateScheduleConfig {

	public GtnUIFrameworkRootConfig getRebateScheduleRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkRebateScheduleLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkRebateScheduleAddConfig().getAddView());
		viewList.add(new GtnFrameworkDeductionCalendarPopUpConfig().getSearchView());
		viewList.add(new GtnFrameworkFormulaPopUpConfig().getSearchView());
		viewList.add(new GtnFrameworkNSSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkRPPopUpSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkRebateSchedulePopUpConfig().getSearchView());
		viewList.add(new GtnFrameworkRSPopupConfig().getView());
		viewList.add(new GtnFrameworkNetSaleRulePopupConfig().getView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
