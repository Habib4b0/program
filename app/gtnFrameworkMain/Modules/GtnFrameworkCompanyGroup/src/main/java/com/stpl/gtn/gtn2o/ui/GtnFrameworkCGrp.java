package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.customergroup.config.GtnFrameworkCGrpConfig;
import com.stpl.gtn.gtn2o.ui.module.customergroup.dynamicclasses.GtnUIFrameworkCGrpDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 *
 * @author Harlin.Mani
 */
public class GtnFrameworkCGrp extends UI {

	private static final long serialVersionUID = 1L;

	private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrp.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("User Id.................." + request.getRemoteUser());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		final Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkCGrpConfig().getCGrpRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Customer Group Master",
				new GtnUIFrameworkCGrpDynamicClassFiller());
	}

}
