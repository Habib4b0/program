package com.stpl.gtn.gtn2o.ui.module.rebateplan.portlets;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.dynamicclasses.GtnUIFrameworkRebatePlanDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.GtnFrameworkRebatePlanConfig;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
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
        "com.liferay.portlet.display-category=GtnFrameworkRebatePlan",
        "javax.portlet.name=RebatePlan",
        "javax.portlet.display-name=RebatePlan",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkRebatePlanPortlet extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkRebatePlanConfig().getRebatePlanRootConfig();
		UI.getCurrent().setData(new GtnUIFrameworkConfigMap());

		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Rebate Plan",
				new GtnUIFrameworkRebatePlanDynamicClassFiller());
	}

}
