package com.stpl.gtn.gtn2o.ui.framework.engine;

import java.lang.management.ManagementFactory;
import java.lang.ref.WeakReference;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.vaadin.alump.beforeunload.BeforeUnload;

import com.stpl.gtn.gtn2o.ui.framework.authorization.GtnUIFrameworkAuthorization;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.jmx.GtnUIFrameworkJmx;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.portal.kernel.util.WebKeys;
import com.stpl.portal.theme.PortletDisplay;
import com.stpl.portal.theme.ThemeDisplay;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class GtnUIFrameworkEngine {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkEngine.class);
	private GtnUIFrameworkComponentData componentData = null;

	/* Dynamic Object Filler implementation */
	public void buildVaadinScreen(GtnUIFrameworkRootConfig rootConfig, Navigator navigator, VaadinRequest request,
			UI ui, String subModuleName, GtnUIDynamicObjectFiller dynamicObject) {
		registerErrorHandler(ui);
		setPortletNameAsUIId(request, ui);
		gtnLogger.info("Entering into buildvaadinScreen() of GtnUIFrameworkEngine");

		GtnUIFrameworkGlobalUI.initGlobalUI(request.getRemoteUser());
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME, subModuleName);
		GtnUIFrameworkAuthorization.setAuthorization(rootConfig, subModuleName);
		if (dynamicObject != null) {
			dynamicObject.addDynamicObject();
		}

		componentData = GtnUIFrameworkGlobalUI.getGlobalComponentData();
		GtnUIFrameworkConfigMap frameworkConfigMap = componentData.getFrameworkConfigMap();

		for (GtnUIFrameworkViewConfig currentViewconfig : rootConfig.getGtnViewConfigList()) {
			if (currentViewconfig.isReplicable()) {
				frameworkConfigMap.getReplicableViewConfigMap().put(currentViewconfig.getViewId(), currentViewconfig);
			} else {
				GtnUIFrameworkView currentVaddinView = new GtnUIFrameworkView(currentViewconfig);

				if (currentViewconfig.isAddToNavigator()) {
					navigator.addView(currentVaddinView.getVaadinViewName(), currentVaddinView);
				}
			}

		}
		registerJmxBean(subModuleName, ui);
		gtnLogger.info("End into buildvaadinScreen() of GtnUIFrameworkEngine");

	}

	private void registerErrorHandler(UI ui) {
		
		ui.setErrorHandler(new DefaultErrorHandler() {
			private static final long serialVersionUID = 1L;
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				gtnLogger.error("Error Occur BecauseOf UploadFailed ", new Exception(event.getThrowable()));}
		});	
	}

	private void setPortletNameAsUIId(VaadinRequest request, UI ui) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			ui.setId(portletDisplay.getPortletName());
		} catch (NoClassDefFoundError e) {
			gtnLogger.error("No Portlet Modules Found");
		}
	}

	private void registerJmxBean(String screenName, final UI currentUI) {
		try {

			GtnUIFrameworkJmx gtnUIFrameworkJmx = new GtnUIFrameworkJmx();
			ObjectName name = null;
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			// close listerner
			if (!gtnUIFrameworkJmx.isEnableJMX()) {
				gtnUIFrameworkJmx.clear();
				return;
			}
			BeforeUnload ob = BeforeUnload.closeBeforeUnload(currentUI);

			ob.addUnloadListener(new BeforeUnload.UnloadListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void unload(BeforeUnload.UnloadEvent event) {
					componentData = null;
					currentUI.setData(null);
					currentUI.close();
				}
			});

			// register Mbeans

			name = new ObjectName("com.stpl.gtn." + screenName + ":type=" + screenName);
			if (mbs.isRegistered(name)) {
				mbs.unregisterMBean(name);
			}

			mbs.registerMBean(gtnUIFrameworkJmx, name);

			gtnUIFrameworkJmx.addGtnCompnentsObjList(new WeakReference<>(componentData));

		} catch (MalformedObjectNameException | MBeanRegistrationException | NotCompliantMBeanException
				| InstanceAlreadyExistsException | InstanceNotFoundException ex) {
			gtnLogger.error("Exception occured : ", ex);

		}
	}
}
