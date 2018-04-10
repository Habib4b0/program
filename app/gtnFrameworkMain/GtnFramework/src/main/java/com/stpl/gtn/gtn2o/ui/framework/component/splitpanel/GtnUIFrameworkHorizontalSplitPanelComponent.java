package com.stpl.gtn.gtn2o.ui.framework.component.splitpanel;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;

public class GtnUIFrameworkHorizontalSplitPanelComponent implements GtnUIFrameworkComponent {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkHorizontalSplitPanelComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkHorizontalSplitPanelComponent ");
		HorizontalSplitPanel panel = new HorizontalSplitPanel();
		loadStyles(panel, componentConfig.getComponentStyle());
		panel.setWidth(componentConfig.getComponentWidth());
		panel.setHeight(componentConfig.getComponentHight());
		GtnUIFrameworkSplitPanelConfig horizontalSplitPanelConfig = componentConfig.getGtnSplitPanelConfig();
		if (horizontalSplitPanelConfig != null) {
			if (horizontalSplitPanelConfig.getSplitPosition() != null) {
				panel.setSplitPosition(getPosition(horizontalSplitPanelConfig.getSplitPosition()),
						getUnit(horizontalSplitPanelConfig.getSplitPosition()));
			}
			if (horizontalSplitPanelConfig.getMinSplitPosition() != null) {
				panel.setMinSplitPosition(getPosition(horizontalSplitPanelConfig.getMinSplitPosition()),
						getUnit(horizontalSplitPanelConfig.getMinSplitPosition()));
			}
			if (horizontalSplitPanelConfig.getMaxSplitPosition() != null) {
				panel.setMaxSplitPosition(getPosition(horizontalSplitPanelConfig.getMaxSplitPosition()),
						getUnit(horizontalSplitPanelConfig.getMaxSplitPosition()));
			}
		}
		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkHorizontalSplitPanelComponent ");

		return panel;
	}

	private float getPosition(String position) {
		return Float.parseFloat(position.replace("%", "").trim());
	}

	private Sizeable.Unit getUnit(String position) {
		return position.endsWith("%") ? Sizeable.Unit.PERCENTAGE : Sizeable.Unit.PIXELS;
	}

	private void loadStyles(final Component component, final List<String> horizontalSplitPanelStyles) {
		if (horizontalSplitPanelStyles != null) {
			for (String style : horizontalSplitPanelStyles) {
				component.addStyleName(style);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId,
			String horizontalSplitPanelComponentId, Object reloadInput) {
		return;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		return;
	}

}