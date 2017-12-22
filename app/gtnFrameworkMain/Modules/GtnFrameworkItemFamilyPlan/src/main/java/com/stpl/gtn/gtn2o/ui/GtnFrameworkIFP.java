package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.ifp.config.GtnFrameworkIfpConfig;
import com.stpl.gtn.gtn2o.ui.module.ifp.dynamicclasses.GtnUIFrameworkIfpDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
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
        "javax.portlet.name=GtnFrameworkItemFamilyPlan",
        "javax.portlet.display-name=Item Family Plan",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkIFP extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIFP.class);
		gtnLogger.info("User Id.................." + request.getRemoteUser());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkIfpConfig().getIFPRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Item Family Plan",
				new GtnUIFrameworkIfpDynamicClassFiller());
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonStringConstants.USER_ID, request.getRemoteUser());
	}

}
