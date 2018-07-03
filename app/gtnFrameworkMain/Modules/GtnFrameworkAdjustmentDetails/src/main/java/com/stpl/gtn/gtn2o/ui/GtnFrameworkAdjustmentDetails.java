/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkAdjustmentDetailsConfig;
import com.stpl.gtn.gtn2o.ui.dynamicFiller.GtnFrameworkAdjustmentDetailsDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=GTN-BUILDINGBLOCKS",
    "javax.portlet.name=GtnFrameworkAdjustmentDetails",
    "javax.portlet.display-name=Adjustment Details",
    "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkAdjustmentDetails extends UI {

    private static final long serialVersionUID = 1L;

    private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAdjustmentDetails.class);

    @Override
    protected void init(VaadinRequest request) {
        gtnLogger.info("In AdjustmentDetails UI");
        addStyleName(GtnFrameworkCssConstants.BOOTSTRAP);
        addStyleName(GtnFrameworkCssConstants.BOOTSTRAP_BB);
        Navigator navigator = new Navigator(this, this);
        GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkAdjustmentDetailsConfig().getItemMasterRootConfig();
        GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
        frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Adjustment Details",
                new GtnFrameworkAdjustmentDetailsDynamicClassFiller());
    }
}
