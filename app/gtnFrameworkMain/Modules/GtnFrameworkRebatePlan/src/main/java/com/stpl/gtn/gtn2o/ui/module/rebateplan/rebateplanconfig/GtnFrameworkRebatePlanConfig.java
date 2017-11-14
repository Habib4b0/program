
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.popup.GtnFrameworkCDRRules;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.popup.GtnFrameworkFormulaPopUpConfig;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.popup.GtnFrameworkNSSearchConfig;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.popup.GtnFrameworkRPPopUpSearchConfig;

public class GtnFrameworkRebatePlanConfig {

	public GtnUIFrameworkRootConfig getRebatePlanRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkRebatePlanAddConfig().getAddView());
		viewList.add(new GtnFrameworkCDRRules().getSearchView());
		viewList.add(new GtnFrameworkNSSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkRPLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkRPPopUpSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkFormulaPopUpConfig().getSearchView());

		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
