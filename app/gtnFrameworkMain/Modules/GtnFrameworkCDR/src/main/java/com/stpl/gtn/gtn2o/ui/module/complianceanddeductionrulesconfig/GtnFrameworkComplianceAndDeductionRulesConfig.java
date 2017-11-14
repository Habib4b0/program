package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkComplianceAndDeductionRulesConfig {

	public GtnUIFrameworkRootConfig getComplianceAndDeductionRulesRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkComplianceAndDeductionRulesLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkComplianceAndDeductionRulesAddConfig().getAddView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
