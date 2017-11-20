package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.company.config.GtnUIFrameworkCompanyMasterConfig;
import com.stpl.gtn.gtn2o.ui.company.dynamicclasses.GtnUIFrameworkCdrDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class GtnFrameworkCM extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName("bootstrap");
		addStyleName("bootstrap-bb");
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkCompanyMasterConfig().getCompanyMasterRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Company Master",
				new GtnUIFrameworkCdrDynamicClassFiller());

	}

}
