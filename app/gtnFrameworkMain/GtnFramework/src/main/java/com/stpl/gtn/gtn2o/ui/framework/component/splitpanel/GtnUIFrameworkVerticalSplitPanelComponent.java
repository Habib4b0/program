package com.stpl.gtn.gtn2o.ui.framework.component.splitpanel;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalSplitPanel;

public class GtnUIFrameworkVerticalSplitPanelComponent implements GtnUIFrameworkComponent {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkVerticalSplitPanelComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkVerticalSplitPanelComponent ");
		VerticalSplitPanel panel = new VerticalSplitPanel();
		loadStyles(panel, componentConfig.getComponentStyle());
		panel.setWidth(componentConfig.getComponentWidth());
		panel.setHeight(componentConfig.getComponentHight());
		GtnUIFrameworkSplitPanelConfig verticalSplitPanelConfig = componentConfig.getGtnSplitPanelConfig();
		if (verticalSplitPanelConfig != null) {
			if (verticalSplitPanelConfig.getSplitPosition() != null) {
				panel.setSplitPosition(getPosition(verticalSplitPanelConfig.getSplitPosition()),
						getUnit(verticalSplitPanelConfig.getSplitPosition()));
			}
			if (verticalSplitPanelConfig.getMinSplitPosition() != null) {
				panel.setMinSplitPosition(getPosition(verticalSplitPanelConfig.getMinSplitPosition()),
						getUnit(verticalSplitPanelConfig.getMinSplitPosition()));
			}
			if (verticalSplitPanelConfig.getMaxSplitPosition() != null) {
				panel.setMaxSplitPosition(getPosition(verticalSplitPanelConfig.getMaxSplitPosition()),
						getUnit(verticalSplitPanelConfig.getMaxSplitPosition()));
			}
		}

		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkVerticalSplitPanelComponent ");

		return panel;
	}

	private float getPosition(String position) {
		return Float.valueOf(position.replace("%", "").trim());
	}

	private Unit getUnit(String position) {
		return position.endsWith("%") ? Unit.PERCENTAGE : Unit.PIXELS;
	}

	private void loadStyles(final Component component, final List<String> verticalSplitPanelStyles) {
		if (verticalSplitPanelStyles != null) {
			for (String style : verticalSplitPanelStyles) {
				component.addStyleName(style);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId,
			String verticalSplitPanelComponentId, Object reloadInput) {
		return;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		return;
	}

}