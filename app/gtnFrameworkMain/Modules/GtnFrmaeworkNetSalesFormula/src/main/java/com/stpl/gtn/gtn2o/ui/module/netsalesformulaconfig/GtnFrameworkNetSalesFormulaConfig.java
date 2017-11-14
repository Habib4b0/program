/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.popup.GtnFrameworkCDRRulesConfig;

/**
 *
 * @author STPL
 */
public class GtnFrameworkNetSalesFormulaConfig {

	public GtnUIFrameworkRootConfig getNetSalesFormulaRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkNSFLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkNetSalesFormulaAddConfig().getAddView());
		viewList.add(new GtnFrameworkCDRRulesConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
