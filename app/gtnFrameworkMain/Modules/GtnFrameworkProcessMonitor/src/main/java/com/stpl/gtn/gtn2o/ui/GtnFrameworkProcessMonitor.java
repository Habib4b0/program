package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.config.GtnFrameworkProcessMonitorConfig;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.dynamicclasses.GtnUIFrameworkProcessMonitorDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Theme("stpl")
@Widgetset("com.stpl.gtn.gtn20.widgetset.gtnvaadinwidgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GTN-BUILDINGBLOCKS",
        "javax.portlet.name=ProcessMonitor",
        "javax.portlet.display-name=Process Monitoring",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkProcessMonitor extends UI {

	private static final long serialVersionUID = 1L;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkProcessMonitor.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("User Id.................." + request.getRemoteUser());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		final Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkProcessMonitorConfig().getProcessMonitorRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Process Monitor",
				new GtnUIFrameworkProcessMonitorDynamicClassFiller());
	}

}
