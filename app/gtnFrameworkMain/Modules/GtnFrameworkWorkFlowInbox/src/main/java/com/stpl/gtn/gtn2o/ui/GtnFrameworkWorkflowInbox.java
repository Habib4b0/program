package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.GtnFrameworkWorkflowInboxConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.dynamicclasses.GtnUIFrameworkWorkflowInboxDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.annotations.JavaScript;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Theme("mytheme")
@Widgetset("com.stpl.app.v8.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GTN-WORKFLOWINBOX",
        "javax.portlet.name=gtnFrameworkWorkflowInbox",
        "javax.portlet.display-name=Workflow Inbox",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
@JavaScript("js/WorkflowInboxListener.js")
public class GtnFrameworkWorkflowInbox extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842644067750548244L;

	private static final GtnWSLogger gtnWSLogger = GtnWSLogger.getGTNLogger(GtnFrameworkWorkflowInbox.class);

	@Override
	protected void init(VaadinRequest request) {

		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = null;

		try {

			rootConfig = new GtnFrameworkWorkflowInboxConfig().getGtnWorkflowInboxRootConfig();

			GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
			frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Workflow Inbox",
					new GtnUIFrameworkWorkflowInboxDynamicClassFiller());

		} catch (Exception ex) {
			gtnWSLogger.error("Error in init Method GtnFrameworkUIWorkflowPortlet", ex);
		}
	}

}
