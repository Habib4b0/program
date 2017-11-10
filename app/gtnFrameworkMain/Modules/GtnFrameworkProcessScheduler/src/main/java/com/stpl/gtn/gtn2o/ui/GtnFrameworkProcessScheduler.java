package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.config.GtnFrameworkProcessSchedulerConfig;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.dynamicclasses.GtnUIFrameworkProcessSchedularDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 *
 * @author
 */
public class GtnFrameworkProcessScheduler extends UI {

	private static final long serialVersionUID = 1L;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkProcessScheduler.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("User Id.................." + request.getRemoteUser());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		final Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkProcessSchedulerConfig().getProcessSchedulerRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Process Scheduler",
				new GtnUIFrameworkProcessSchedularDynamicClassFiller());
	}

}
