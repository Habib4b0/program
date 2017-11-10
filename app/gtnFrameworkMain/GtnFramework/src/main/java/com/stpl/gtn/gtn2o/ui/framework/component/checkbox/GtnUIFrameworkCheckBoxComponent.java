/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.checkbox;

import java.util.List;

import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIFrameworkValueChangeListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

public class GtnUIFrameworkCheckBoxComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCheckBoxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.debug("Entering into the buildVaadinComponent() of GtnUIFrameworkTextComponent");
		ExtCustomCheckBox checkBox = new ExtCustomCheckBox(componentConfig.getComponentName());

		loadStyles(checkBox, componentConfig.getComponentStyle());
		GtnUIFrameworkCheckBoxComponentConfig checkboxConfig = componentConfig.getGtnCheckBoxConfig();
		checkBox.setVisible(componentConfig.isVisible());
		checkBox.setEnabled(componentConfig.isEnable());
		if (checkboxConfig != null) {
			checkBox.setRequired(checkboxConfig.isRequired());
			checkBox.setEnabled(checkboxConfig.isEnable());
			checkBox.setImmediate(true);
			checkBox.setReadOnly(checkboxConfig.isReadOnly());
		}
		if (componentConfig.getGtnUIFrameWorkActionConfigList() != null
				&& !componentConfig.getGtnUIFrameWorkActionConfigList().isEmpty()) {
			checkBox.addValueChangeListener(GtnUIFrameworkValueChangeListener.getListener());
		}
		gtnLogger.debug("End into the buildVaadinComponent() of GtnUIFrameworkTextComponent");

		return checkBox;

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
		ExtCustomCheckBox checkBox = (ExtCustomCheckBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkCheckBoxComponentConfig checkboxConfig = componentConfig.getGtnCheckBoxConfig();
		checkBox.setVisible(componentConfig.isVisible());
		checkBox.setEnabled(componentConfig.isEnable());
		if (checkboxConfig != null) {
			checkBox.setRequired(checkboxConfig.isRequired());
			checkBox.setEnabled(checkboxConfig.isEnable());
			checkBox.setImmediate(true);
			checkBox.setReadOnly(checkboxConfig.isReadOnly());
		}
		boolean value = false;
		if (componentConfig.getComponentValue() != null) {
			value = (boolean) componentConfig.getComponentValue();
		}
		checkBox.setValue(value);
	}
}
