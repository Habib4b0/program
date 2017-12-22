/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.GtnFrameworkRelationShipBuilderConfig;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.dynamicclasses.GtnUIFrameworkRSBuilderDynamicClassFiller;
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
@Theme("mytheme")
@Widgetset("com.stpl.app.v8.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GtnFrameworkRelationshipBuilder",
        "javax.portlet.name=RelationshipBuilder",
        "javax.portlet.display-name=Relationship Builder",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class GtnFrameworkRelationshipBuilder extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName("bootstrap");
		addStyleName("bootstrap-bb");
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkRelationShipBuilderConfig()
				.getRelationShipBuilderRootConfig();
		UI.getCurrent().setData(new GtnUIFrameworkConfigMap());
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "Relationship Builder",new GtnUIFrameworkRSBuilderDynamicClassFiller());
		GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
	}

}
