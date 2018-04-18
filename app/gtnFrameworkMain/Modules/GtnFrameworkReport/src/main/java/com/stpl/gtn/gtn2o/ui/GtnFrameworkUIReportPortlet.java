package com.stpl.gtn.gtn2o.ui;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportConfig;
import com.stpl.gtn.gtn2o.ui.dynamicclasses.GtnUIFrameworkReportDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = { "com.liferay.portlet.display-category=GTN-REPORT",
		"javax.portlet.name=gtnFrameworkReport", "javax.portlet.display-name=Report",
		"com.vaadin.osgi.liferay.portlet-ui=true" }, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkUIReportPortlet extends UI {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkUIReportPortlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = null;
		try {

			rootConfig = new GtnFrameworkReportConfig().getGtnReportRootConfig();

		} catch (Exception ex) {
			LOGGER.error("Error in init Method GtnFrameworkUIReportPortlet", ex);
		}
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Report",
				new GtnUIFrameworkReportDynamicClassFiller());

	}

}
