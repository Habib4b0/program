package com.stpl.gtn.gtn2o.ui.framework.component.textarea;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;

public class GtnUIFrameworkTextAreaComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTextAreaComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkTextAreaComponent");

		TextArea textArea = new TextArea(componentConfig.getComponentName());

		loadStyles(textArea, componentConfig.getComponentStyle());
		GtnUIFrameworkTextAreaConfig textAreaConfig = componentConfig.getGtnTextAreaConfig();
		textArea.addStyleName("addInfoText");
		textArea.setSizeFull();
		textArea.setRows(textAreaConfig != null ? textAreaConfig.getRows() : 5);
		textArea.setImmediate(true);
		textArea.setMaxLength(1000);
		if (textAreaConfig != null) {
			textArea.setRequired(textAreaConfig.isRequired());
			textArea.setEnabled(textAreaConfig.isEnable());
			textArea.setImmediate(true);
			textArea.setInputPrompt(textAreaConfig.getInputPrompt());
		}

		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkTextAreaComponent");

		return textArea;
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
		TextArea textArea = (TextArea) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		textArea.setValue(value);
	}
}
