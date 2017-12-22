/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.contractdashboard;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardRootConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.dynamicclasses.GtnUIFrameworkContractDashboardClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
/**
 *
 * @author Abhiram.Giri
 */
@Theme("stpl")
@Widgetset("com.stpl.gtn.gtn20.widgetset.gtnvaadinwidgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GtnFrameworkContractDashboard",
        "javax.portlet.name=ContractDashboard",
        "javax.portlet.display-name=Contract Dashboard",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkContractDashboard extends UI {

	private static final long serialVersionUID = -4480297535751996732L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkContractDashboardRootConfig()
				.getContractDashboardRootConfig();
		UI.getCurrent().setData(new GtnUIFrameworkConfigMap());
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Contract Dashboard",new GtnUIFrameworkContractDashboardClassFiller());
	}
}