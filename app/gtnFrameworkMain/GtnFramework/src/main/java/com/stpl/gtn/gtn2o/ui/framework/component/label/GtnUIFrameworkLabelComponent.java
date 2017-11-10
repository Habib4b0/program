/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.label;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.List;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkLabelComponent implements GtnUIFrameworkComponent {
    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkLabelComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		Label label = new Label(componentConfig.getComponentName());

		loadStyles(label, componentConfig.getComponentStyle());
		label.setVisible(componentConfig.isVisible());
		label.setEnabled(componentConfig.isEnable());

		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		label.setValue(value);
		GtnUIFrameworkLabelConfig labelConfig = componentConfig.getGtnLabelConfig();
		if (labelConfig != null) {
			label.setEnabled(labelConfig.isEnable());
			label.setImmediate(labelConfig.isImmediate());
			label.setReadOnly(labelConfig.isReadOnly());
			if (labelConfig.getValueChangeActionConfigList() != null
					&& !labelConfig.getValueChangeActionConfigList().isEmpty()) {
				label.addValueChangeListener(new LabelValueChangeListener(componentConfig));
			}
		}
		return label;
	}

	private void loadStyles(final Component component, final List<String> styles) {
		if (styles != null) {
			for (String style : styles) {
				component.addStyleName(style);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		Label label = (Label) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		label.setVisible(componentConfig.isVisible());
		label.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkLabelConfig labelConfig = componentConfig.getGtnLabelConfig();
		if (labelConfig != null) {
			label.setEnabled(labelConfig.isEnable());
			label.setImmediate(labelConfig.isImmediate());
			label.setReadOnly(labelConfig.isReadOnly());
		}
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		label.setValue(value);
	}

	class LabelValueChangeListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public LabelValueChangeListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event) {
			try {
				GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
						componentConfig.getGtnLabelConfig().getValueChangeActionConfigList());
			} catch (GtnFrameworkGeneralException e) {
                            gtnLogger.error(e.getMessage());
			}
		}

	}

}
