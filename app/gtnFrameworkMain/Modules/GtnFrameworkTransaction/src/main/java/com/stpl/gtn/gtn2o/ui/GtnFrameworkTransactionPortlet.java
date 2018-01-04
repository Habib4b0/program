package com.stpl.gtn.gtn2o.ui;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
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
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.portlet.PortletMode;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Theme("stpl")
@Widgetset("com.stpl.gtn.gtn20.widgetset.gtnvaadinwidgetset.AppWidgetSet")
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=Security",
    "javax.portlet.name=LotMaster",
    "javax.portlet.display-name=Lot Master",
    "com.liferay.portlet.instanceable=false",
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

            GtnUIFrameworkRootConfig rootConfig;
            Navigator navigator = new Navigator(this, this);
            String portletName = getPorletName(themeDisplay.getURLCurrent());
            LOGGER.info("portletName = " + portletName);
            boolean isInvalid = "InvalidIntegration".equals(portletName);

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
                new GtnFrameworkTransactionComponentConfig().getComponentsForModules(portletName,
                        isInvalid, componentList, viewComponentList, GtnFrameworkCommonStringConstants.STRING_EMPTY,
                        null, GtnFrameworkCommonStringConstants.STRING_EMPTY);
            }

            getComponentList(rootConfig, componentList, viewComponentList, gtnViewConfigList);
            GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
            frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, portletName,
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

    public static String getPorletName(String portletUrl) {
        String portletName=GtnFrameworkCommonStringConstants.STRING_EMPTY;
       try{
         portletName = portletUrl.split("\\?")[0].split("/")[3];
        LOGGER.info("portletName"+portletName);
        ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.Portlet");
        portletName=listNameBundle.getString(portletName);
          
        LOGGER.info("prop.getProperty(portletName)"+portletName);
       }catch(Exception e){
           LOGGER.error(portletUrl+" failed "+e.getMessage());
                   
       }
        return portletName.trim();
    }

}
