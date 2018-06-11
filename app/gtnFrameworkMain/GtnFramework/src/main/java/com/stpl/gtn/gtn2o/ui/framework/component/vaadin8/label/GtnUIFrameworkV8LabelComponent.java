/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.label;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.label.GtnUIFrameworkLabelConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnUIFrameworkV8LabelComponent implements GtnUIFrameworkComponent {
    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkV8LabelComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		Label labelVaadin8 = new Label(componentConfig.getComponentName());

		loadStyles(labelVaadin8, componentConfig.getComponentStyle());
		labelVaadin8.setVisible(componentConfig.isVisible());
		labelVaadin8.setEnabled(componentConfig.isEnable());

		String componentValue = "";
		if (componentConfig.getComponentValue() != null) {
			componentValue = String.valueOf(componentConfig.getComponentValue());
		}
		labelVaadin8.setValue(componentValue);
		GtnUIFrameworkLabelConfig labelConfigVaadin8 = componentConfig.getGtnLabelConfig();
		if (labelConfigVaadin8 != null) {
			labelVaadin8.setEnabled(labelConfigVaadin8.isEnable());
			
		}
		return labelVaadin8;
	}

	private void loadStyles(final Component component, final List<String> vaadin8Styles) {
		if (vaadin8Styles != null) {
			for (String vaadin8Style : vaadin8Styles) {
				component.addStyleName(vaadin8Style);
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
		Label labelVaadin8 = (Label) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		labelVaadin8.setVisible(componentConfig.isVisible());
		labelVaadin8.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkLabelConfig labelConfig = componentConfig.getGtnLabelConfig();
		if (labelConfig != null) {
			labelVaadin8.setEnabled(labelConfig.isEnable());
		}
		String componentValue=  "";
		if (componentConfig.getComponentValue() != null) {
			componentValue = String.valueOf(componentConfig.getComponentValue());
		}
		labelVaadin8.setValue(componentValue);
	}
}
