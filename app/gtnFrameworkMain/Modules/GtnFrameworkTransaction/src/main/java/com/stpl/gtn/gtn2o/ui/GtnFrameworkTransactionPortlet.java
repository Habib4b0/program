package com.stpl.gtn.gtn2o.ui;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.transaction.config.GtnFrameworkTransactionComponentConfig;
import com.stpl.gtn.gtn2o.ui.module.transaction.config.GtnFrameworkTransactionInvalidComponentConfig;
import com.stpl.gtn.gtn2o.ui.module.transaction.config.GtnUIFrameworkTransactionConfig;
import com.stpl.gtn.gtn2o.ui.module.transaction.dynamicclasses.GtnUIFrameworkTransactionDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.portal.kernel.util.WebKeys;
import com.stpl.portal.theme.PortletDisplay;
import com.stpl.portal.theme.ThemeDisplay;
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
        "com.liferay.portlet.display-category=Security",
        "javax.portlet.name=LotMaster",
        "javax.portlet.display-name=Lot Master",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkTransactionPortlet extends UI {

	private static final long serialVersionUID = 1L;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkTransactionPortlet.class);

	@Override
	protected void init(VaadinRequest request) {
		try {
			addStyleName("bootstrap");
			addStyleName("bootstrap-bb");
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			GtnUIFrameworkRootConfig rootConfig;
			Navigator navigator = new Navigator(this, this);
			boolean isInvalid = "InvalidIntegration".equals(portletDisplay.getPortletName());

			rootConfig = new GtnUIFrameworkTransactionConfig().getMainTransactionRootConfig(isInvalid);
			List<GtnUIFrameworkViewConfig> gtnViewConfigList = new ArrayList<>(rootConfig.getGtnViewConfigList());
			List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>(
					gtnViewConfigList.get(0).getGtnComponentList());
			List<GtnUIFrameworkComponentConfig> viewComponentList = new ArrayList<>(
					gtnViewConfigList.get(1).getGtnComponentList());
			if (isInvalid) {
				new GtnFrameworkTransactionInvalidComponentConfig().getComponentsForModules(componentList,
						viewComponentList);
			} else {
				new GtnFrameworkTransactionComponentConfig().getComponentsForModules(portletDisplay.getPortletName(),
						isInvalid, componentList, viewComponentList, GtnFrameworkCommonStringConstants.STRING_EMPTY,
						null, GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}

			getComponentList(rootConfig, componentList, viewComponentList, gtnViewConfigList);
			GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
			frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, portletDisplay.getPortletName(),
					new GtnUIFrameworkTransactionDynamicClassFiller());
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Error in init Method GtnFrameworkTransactionPortlet", e);
		}
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void error(com.vaadin.server.ErrorEvent event) {

				StringBuilder cause = new StringBuilder("The Exception occured because of: ");
				for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
					if (t.getCause() == null) // We're at final cause
					{

						cause.append(t.getClass().getName());
					}
				}
				LOGGER.info(cause.toString());
			}
		});

	}

	private void getComponentList(GtnUIFrameworkRootConfig rootConfig,
			List<GtnUIFrameworkComponentConfig> componentList, List<GtnUIFrameworkComponentConfig> viewComponentList,
			List<GtnUIFrameworkViewConfig> gtnViewConfigList) {
		GtnUIFrameworkViewConfig mainComponentConfig = new ArrayList<>(gtnViewConfigList).get(0);
		GtnUIFrameworkViewConfig viewComponentConfig = new ArrayList<>(gtnViewConfigList).get(1);
		mainComponentConfig.setGtnComponentList(componentList);
		viewComponentConfig.setGtnComponentList(viewComponentList);
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(mainComponentConfig);
		viewList.add(viewComponentConfig);
		rootConfig.setGtnViewConfigList(viewList);

	}

}
