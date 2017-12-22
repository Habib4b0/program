/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.periodconfiguration;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.config.GtnFrameworkPeriodConfigurationRootConfig;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.dynamicclasses.GtnUIFrameworkPeriodConfigDynamicClassFiller;
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
        "com.liferay.portlet.display-category=GtnFrameworkPeriodConfiguration",
        "javax.portlet.name=PeriodConfiguration",
        "javax.portlet.display-name=PeriodConfiguration",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkPeriodConfiguration extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkPeriodConfigurationRootConfig()
				.getPeriodConfigurationRootConfig();
		UI.getCurrent().setData(new GtnUIFrameworkConfigMap());
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Period Configuration",
				new GtnUIFrameworkPeriodConfigDynamicClassFiller());
	}

}
