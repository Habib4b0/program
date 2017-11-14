package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.config.GtnFrameworkEmailModuleConfig;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.dynamicclasses.GtnUIFrameworkEmailConfigDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class GtnFrameworkEmailconfig extends UI {

	private static final long serialVersionUID = 1L;
	private static final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkEmailconfig.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("User Id.................." + request.getRemoteUser());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		addStyleName("bootstrap-admin");
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkEmailModuleConfig().getEmailRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Mail Configuration",
				new GtnUIFrameworkEmailConfigDynamicClassFiller());
	}

}
