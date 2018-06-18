package com.stpl.gtn.gtn2o.ui.framework.component.button;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIGeneralButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;

public class GtnUIFrameworkButtonComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	@Override
	public AbstractComponent buildVaadinComponent(final GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkButtonConfig gtnUIFrameworkButtonConfig = componentConfig.getButtonConfig();
		String componentName = componentConfig.getComponentName();
		if (componentConfig.getButtonConfig() == null
				|| componentConfig.getButtonConfig().isButtonCaptionInUpperCase()) {
			componentName = componentName.toUpperCase(Locale.ENGLISH);

		}
		Button vaadinButton = new Button(componentName);
		vaadinButton.setWidth(componentConfig.getComponentWidth());
		vaadinButton.setVisible(componentConfig.isVisible());
		vaadinButton.addStyleName("buttonCustomStyle");
		if (gtnUIFrameworkButtonConfig != null
				&& GtnUiFrameworkButtonType.LINK_BUTTON == gtnUIFrameworkButtonConfig.getButtonType()) {
			vaadinButton.setStyleName(ValoTheme.BUTTON_LINK);
		}
		setButtonIcon(vaadinButton, gtnUIFrameworkButtonConfig);
		loadStyles(vaadinButton, componentConfig.getComponentStyle());
		vaadinButton.setEnabled(componentConfig.isEnable());
		return vaadinButton;
	}

	private void setButtonIcon(Button vaadinButton, GtnUIFrameworkButtonConfig gtnUIFrameworkButtonConfig) {
		Optional<GtnUIFrameworkButtonConfig> buttonConfigOptional = Optional.ofNullable(gtnUIFrameworkButtonConfig);
		if (buttonConfigOptional.isPresent()) {
			Optional<String> excelUrlOptional = Optional.ofNullable(buttonConfigOptional.get().getIconUrl());
			excelUrlOptional.ifPresent(excelIconUrl -> vaadinButton.setIcon(new ThemeResource(excelIconUrl)));
		}
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		Button vaadinButton = (Button) component;
		vaadinButton.addClickListener(GtnUIGeneralButtonClickListener.getListener());

	}

	private void loadStyles(final Component buttonComponent, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {
				buttonComponent.addStyleName(style);
			}
		}

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String buttonComponentId, GtnUIFrameworkComponentConfig buttonComponentConfig) {
		AbstractComponent component = GtnUIFrameworkGlobalUI.getVaadinComponent(buttonComponentId);
		component.setVisible(buttonComponentConfig.isVisible());
		component.setEnabled(buttonComponentConfig.isEnable());
	}
}