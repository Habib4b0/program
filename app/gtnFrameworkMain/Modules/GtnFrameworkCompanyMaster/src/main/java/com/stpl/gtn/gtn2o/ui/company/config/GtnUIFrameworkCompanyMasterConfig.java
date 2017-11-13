package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnUIFrameworkCompanyMasterConfig {

	public GtnUIFrameworkRootConfig getCompanyMasterRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkCompanyMasterSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkCompanyMasterAddConfig().getCompanyMasterAddView());
		viewList.add(new GtnUIFrameworkCMIdentifierEditList().getSearchView());
		viewList.add(new GtnUIFrameworkCMParentCompanyPopup().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
