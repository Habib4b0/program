package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.businessrole.config.GtnFrameworkBRMMConfig;
import com.stpl.gtn.gtn2o.ui.businessrole.dynamicclasses.GtnFrameworkBRMMDynamicClassObject;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
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
        "com.liferay.portlet.display-category=Security",
        "javax.portlet.name=GtnFrameworkBusinessRoleModuleMaster",
        "javax.portlet.display-name=GtnFrameworkBusinessRoleModuleMaster",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkBRMM extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName("bootstrap");
		addStyleName("bootstrap-bb");
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkBRMMConfig().getBRMMRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "BusinessRoleModuleMaster",
				new GtnFrameworkBRMMDynamicClassObject());

	}

}
