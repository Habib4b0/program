package com.stpl.gtn.gtn2o.ui;

import java.util.HashMap;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkForecastReturnConfig;
import com.stpl.gtn.gtn2o.ui.config.workflowconfig.GtnUIFrameworkReturnsWorkflowViewConfig;
import com.stpl.gtn.gtn2o.ui.dynamicclasses.GtnUIFrameworkForecastReturnDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Theme("stpl")
@Widgetset("com.stpl.gtn.gtn20.widgetset.gtnvaadinwidgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GTN-FORECASTRETURN",
        "javax.portlet.name=gtnFrameworkForecastReturn",
        "javax.portlet.display-name=Forecast Returns",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkUIForecastReturnsPortlet extends UI {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkUIForecastReturnsPortlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = null;
		try {
			Map<String, String> infoMap = getWorkflowInfoMap();
			if (infoMap != null && !infoMap.isEmpty()) {
				rootConfig = new GtnUIFrameworkReturnsWorkflowViewConfig().getWorkfolwView(infoMap);
			} else {
				rootConfig = new GtnFrameworkForecastReturnConfig().getGtnForecastReturnRootConfig();
			}
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error("Error in init Method GtnFrameworkUIForecastReturnsPortlet", ex);
		}
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "ReturnForecasting",
				new GtnUIFrameworkForecastReturnDynamicClassFiller());

	}

	public Map<String, String> getWorkflowInfoMap() {

		String pageParameters = null;

		pageParameters = Page.getCurrent().getLocation().getQuery();

		if (pageParameters != null) {
			String[] parameters = pageParameters.split("&");

			Map<String, String> projectionDetails = new HashMap<>();

			for (String para : parameters) {
				String[] paraStr = para.split("=");
				projectionDetails.put(paraStr[0], paraStr[1]);
			}
			return projectionDetails;
		}
		return null;
	}

}
