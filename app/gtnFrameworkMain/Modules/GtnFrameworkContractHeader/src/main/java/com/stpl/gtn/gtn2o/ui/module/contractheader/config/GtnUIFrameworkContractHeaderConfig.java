package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.contractheader.config.popup.GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig;
import com.stpl.gtn.gtn2o.ui.module.contractheader.config.popup.GtnUIFrameworkContractHeaderCompanyNamerPoupupConfig;

public class GtnUIFrameworkContractHeaderConfig {

	public GtnUIFrameworkRootConfig getContractHeaderrRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnUIFrameworkContractHeaderLandingScreenConfig().getSearchView());
		viewList.add(new GtnUIFrameworkContractHeaderAddConfig().getContractHeaderAddView());
		viewList.add(new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig().getSearchView("aliasTab"));
		viewList.add(new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig().getSearchView("landingScreen"));
		viewList.add(
				new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig().getSearchView("contractHeaderTab"));
		viewList.add(new GtnUIFrameworkContractHeaderCompanyNamerPoupupConfig().getSearchView());

		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
