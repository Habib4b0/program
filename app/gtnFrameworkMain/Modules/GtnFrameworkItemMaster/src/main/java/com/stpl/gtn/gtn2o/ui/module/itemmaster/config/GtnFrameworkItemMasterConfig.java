package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup.GtnUIFrameworkItemMasterIdentifierEditListConfig;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup.GtnUIFrameworkItemMasterNewFormulationPopupConfig;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup.GtnUIFrameworkItemMasterParentCompanyPopupConfig;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup.GtnUIFrameworkItemMasterPrcingEditListConfig;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup.GtnUIFrameworkItemMasterPricingEntityPopupConfig;

public class GtnFrameworkItemMasterConfig {

	public GtnUIFrameworkRootConfig getItemMasterRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkItemMasterLandingScreenConfig().getSearchView());
		viewList.add(new GtnFrameworkItemMasterAddConfigConfig().getItemMasterAddView());
		viewList.add(new GtnUIFrameworkItemMasterIdentifierEditListConfig().getSearchView());
		viewList.add(new GtnUIFrameworkItemMasterPrcingEditListConfig().getSearchView());
		viewList.add(new GtnUIFrameworkItemMasterParentCompanyPopupConfig().getSearchView());
		viewList.add(new GtnUIFrameworkItemMasterPricingEntityPopupConfig().getSearchView());
		viewList.add(new GtnUIFrameworkItemMasterNewFormulationPopupConfig().getNfPopupSearchView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}
}
