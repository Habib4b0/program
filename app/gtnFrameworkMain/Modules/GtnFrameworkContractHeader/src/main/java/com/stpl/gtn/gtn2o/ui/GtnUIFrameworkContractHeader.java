package com.stpl.gtn.gtn2o.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.contractheader.config.GtnUIFrameworkContractHeaderConfig;
import com.stpl.gtn.gtn2o.ui.module.contractheader.dynamicclasses.GtnUIFrameworkContractHeaderDynamicClassFiller;
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

@Theme("stpl")
@Widgetset("com.stpl.gtn.gtn20.widgetset.gtnvaadinwidgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GTN-BUILDINGBLOCKS",
        "javax.portlet.name=GtnFrameworkContractHeader",
        "javax.portlet.display-name=Contract Header",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnUIFrameworkContractHeader extends UI {

	private static final long serialVersionUID = 1L;

	private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkContractHeader.class);

	@Override
	protected void init(VaadinRequest request) {
		gtnLogger.info("User Id.................." + request.getRemoteUser());
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
		addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkContractHeaderConfig().getContractHeaderrRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Contract Header",
				new GtnUIFrameworkContractHeaderDynamicClassFiller());
		GtnUIFrameworkGlobalUI.addSessionProperty("userId", request.getRemoteUser());
		final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
		GtnUIFrameworkGlobalUI.addSessionProperty("sessionId", fmtID.format(new Date()));

		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {

			private static final long serialVersionUID = 1L;

			@Override
			public void error(com.vaadin.server.ErrorEvent event) {

				gtnLogger.info("The Exception occured because of: " + event.getThrowable().getCause().getMessage());

			}
		});

	}

}
