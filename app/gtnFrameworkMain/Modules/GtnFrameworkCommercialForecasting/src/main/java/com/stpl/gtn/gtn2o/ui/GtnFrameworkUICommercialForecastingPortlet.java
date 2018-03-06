package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.dynamicclasses.GtnUIFrameworkCommercialForecastingDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
/**
 * @author Kalpana.Ramanana
 *
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GTN-COMMERCIALFORECASTING",
        "javax.portlet.name=gtnFrameworkCommercialForecasting",
        "javax.portlet.display-name=Commercial Forecasting",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkUICommercialForecastingPortlet extends UI {

	private static final long serialVersionUID = -5842644067750548244L;

	private static final GtnWSLogger gtnWSLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkUICommercialForecastingPortlet.class);

	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {

		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = null;

		try {

			rootConfig = new GtnFrameworkCommercialForecastingConfig().getGtnCommercialForecastingRootConfig();

			GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
			frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "CommercialForecasting",new GtnUIFrameworkCommercialForecastingDynamicClassFiller());

		} catch (GtnFrameworkGeneralException ex) {
			gtnWSLogger.error("Error in init Method GtnFrameworkUICommercialForecastPortlet", ex);
		}
	}
}
