package com.stpl.gtn.gtn2o.ui;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.stpl.gtn.gtn2o.registry.dynamicclasses.GtnUIFrameworkCommercialForecastingDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.forecasting.config.GtnFrameworkCommercialForecastingConfig;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = { "com.liferay.portlet.display-category=GTN-FORECASTING_NEWARCH",
		"javax.portlet.name=gtnFrameworkCommercialForecasting", "javax.portlet.display-name=Commercial Forecasting",
		"com.vaadin.osgi.liferay.portlet-ui=true" }, scope = ServiceScope.PROTOTYPE)
public class GtnUIFrameworkCommercialForecasting extends UI {

	private static final long serialVersionUID = 1L;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCommercialForecasting.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("Forecasting UI");
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkCommercialForecastingConfig().getForecastingRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Commercial Forecasting",
				new GtnUIFrameworkCommercialForecastingDynamicClassFiller());
	}

}
