/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.portlets;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.GtnFrameworkRebateScheduleConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.dynamicclasses.GtnUIFrameworkRSDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 *
 * @author Mahesh.James
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = { "com.liferay.portlet.display-category=GtnFrameworkRebateSchedule",
		"javax.portlet.name=RebateSchedule", "javax.portlet.display-name=Rebate Schedule",
		"com.vaadin.osgi.liferay.portlet-ui=true" }, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkRebateSchedulePortlet extends UI {

	private static final long serialVersionUID = 1L;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkRebateSchedulePortlet.class);

	@Override
	protected void init(VaadinRequest request) {
            try{
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkRebateScheduleConfig().getRebateScheduleRootConfig();
		UI.getCurrent().setData(new GtnUIFrameworkConfigMap());
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Rebate Schedule",
				new GtnUIFrameworkRSDynamicClassFiller());
//		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void error(com.vaadin.server.ErrorEvent event) {
//
//				LOGGER.info("The Exception occured because of: " + event.getThrowable().getCause().getMessage());
//			}
//		});
	} catch(Exception e){
        LOGGER.info("-------------"+e);
        }
}
}
