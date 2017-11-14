package com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup;

import com.stpl.gtn.gtn2o.config.commonpopup.GtnUIFrameworkParentCompanyPopupConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnUIFrameworkItemMasterParentCompanyPopupConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkParentCompanyPopupConfig companyParentPopup = new GtnUIFrameworkParentCompanyPopupConfig();
		return companyParentPopup.getSearchView("itemIdentifier");
	}
}
