package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.udc.config.GtnFrameworkUDCConfigurationConfig;
import com.stpl.gtn.gtn2o.ui.module.udc.dynamicclasses.GtnUIFrameworkUDCConfigurationDynamicFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
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
        "com.liferay.portlet.display-category=GTN-BUILDINGBLOCKS",
        "javax.portlet.name=GtnFrameworkUDCConfiguration",
        "javax.portlet.display-name=UDC Configuration",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkUDCConfiguration extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkUDCConfiguration.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("In UDC Configuration UI");
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkUDCConfigurationConfig().getUDCConfigurationRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "UDC Configuration",
				new GtnUIFrameworkUDCConfigurationDynamicFiller());
	}

}
