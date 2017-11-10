package com.stpl.gtn.gtn2o.ui.framework.component.prouptextfield;

import java.util.List;

import org.asi.ui.customtextfield.CustomTextField;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

public class GtnUIFrameworkPopupTextField implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPopupTextField.class);

	@Override
	public AbstractComponent buildVaadinComponent(final GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent () of GtnUIFrameworkPopupTextField ");

		CustomTextField popupTextField = new CustomTextField();
		if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
			popupTextField.setCaption(componentConfig.getComponentName());
		}
		popupTextField.setNullRepresentation(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		popupTextField.setVisible(componentConfig.isVisible());
		if (textboxConfig != null) {
			popupTextField.setRequired(textboxConfig.isRequired());
			popupTextField.setEnabled(textboxConfig.isEnable());
			popupTextField.setImmediate(true);
			popupTextField.setReadOnly(textboxConfig.isReadOnly());
			popupTextField.setNullRepresentation(textboxConfig.getNullRepresentation());
		}

		popupTextField.addStyleName(GtnFrameworkCssConstants.SEARCHICON);
		loadStyles(popupTextField, componentConfig.getComponentStyle());
		gtnLogger.info("End into the buildVaadinComponent () of GtnUIFrameworkPopupTextField ");

		return popupTextField;
	}

	@Override
	public void postCreateComponent(AbstractComponent component, final GtnUIFrameworkComponentConfig componentConfig) {
		CustomTextField popupTextField = (CustomTextField) component;
		popupTextField.setVisible(componentConfig.isVisible());
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		final String componentId = componentData.getComponentIdInMap();
		popupTextField.addClickListener(new CustomTextField.ClickListener() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void click(CustomTextField.ClickEvent event) {
				try {

					for (GtnUIFrameWorkActionConfig actionConfig : componentConfig
							.getGtnUIFrameWorkActionConfigList()) {
						final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
						action.configureParams(actionConfig);
						action.doAction(componentId, actionConfig);
					}

				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			}
		});
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				component.addStyleName(style);

			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		CustomTextField popupTextField = (CustomTextField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		popupTextField.setVisible(componentConfig.isVisible());
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		boolean isReadOnly = popupTextField.isReadOnly();
		popupTextField.setReadOnly(false);
		popupTextField.setValue(value);
		popupTextField.setReadOnly(isReadOnly);
	}

}
