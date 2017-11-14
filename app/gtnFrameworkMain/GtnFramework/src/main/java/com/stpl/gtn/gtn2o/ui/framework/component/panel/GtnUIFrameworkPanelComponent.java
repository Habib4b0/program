package com.stpl.gtn.gtn2o.ui.framework.component.panel;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

public class GtnUIFrameworkPanelComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPanelComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkPanelComponent ");
		Panel panel = new Panel(componentConfig.getComponentName());
		loadStyles(panel, componentConfig.getComponentStyle());
		panel.setWidth(componentConfig.getComponentWidth());
		panel.setHeight(componentConfig.getComponentHight());
		panel.setVisible(componentConfig.isVisible());
		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkPanelComponent ");

		return panel;
	}

	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				component.addStyleName(style);

			}
		}

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		AbstractComponent component = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		component.setVisible(componentConfig.isVisible());
	}

}
