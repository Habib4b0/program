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
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=ProcessSchedulerNew",
        "javax.portlet.name=ProcessSchedulerNew",
        "javax.portlet.display-name=ProcessSchedulerNew",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkProcessScheduler extends UI {

	private static final long serialVersionUID = 1L;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkProcessScheduler.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("Process Schedular -----new");
		//gtnLogger.info(GtnUIFrameworkGlobalUI.getCurrentUser()+"  User Id.................." + request.getRemoteUser());
		//gtnLogger.info("Session id..............."+GtnUIFrameworkGlobalUI.getSessionProperty(GtnUIFrameworkGlobalUI.getCurrentUser()));
		//gtnLogger.info("Seesion id(GtnFrameworkSession bean)---> "+GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		final Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkProcessSchedulerConfig().getProcessSchedulerRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Process Scheduler",
				new GtnUIFrameworkProcessSchedularDynamicClassFiller());
	}

}
