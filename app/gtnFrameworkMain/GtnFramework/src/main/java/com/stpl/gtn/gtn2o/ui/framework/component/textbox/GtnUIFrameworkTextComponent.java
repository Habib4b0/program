package com.stpl.gtn.gtn2o.ui.framework.component.textbox;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.listner.GtnUIFrameworkBlurListner;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceTextBoxResponse;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class GtnUIFrameworkTextComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTextComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		AbstractTextField textField = generateAbstaractTextField(componentConfig);

		loadStyles(textField, componentConfig.getComponentStyle());
		textField.setVisible(componentConfig.isVisible());
		textField.setEnabled(componentConfig.isEnable());
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		textField.setValue(value);
		textField.setNullSettingAllowed(false);
		textField.setNullRepresentation(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		if (textboxConfig != null) {
			textField.setRequired(textboxConfig.isRequired());
			textField.setRequiredError(textboxConfig.getRequiredMessage());
			textField.setEnabled(textboxConfig.isEnable());
			textField.setImmediate(textboxConfig.isImmediate());
			textField.setNullSettingAllowed(textboxConfig.isNullSettingAllowed());
			textField.setNullRepresentation(textboxConfig.getNullRepresentation());
			if (textboxConfig.isValueLoadFromService()) {
				fillTextBox(textboxConfig, textField);
			}
			textField.setReadOnly(textboxConfig.isReadOnly());
			addListeners(textField, componentConfig);
			if (textboxConfig.getMaximumLength() > 0) {
				textField.setMaxLength(textboxConfig.getMaximumLength());
			}
		}
		if (componentConfig.getGtnUIFrameworkValidationConfig() != null) {
			textField.setImmediate(true);
			if (componentConfig.getGtnUIFrameworkValidationConfig().isAttachRegxValidatior()) {
				textField.addValidator(
						new RegexpValidator(componentConfig.getGtnUIFrameworkValidationConfig().getFormatString(),
								componentConfig.getGtnUIFrameworkValidationConfig().getRegxValidationMessage()));
			}
			if (componentConfig.getGtnUIFrameworkValidationConfig().isAttachLengthValidatior()) {
				textField.addValidator(new StringLengthValidator(
						componentConfig.getGtnUIFrameworkValidationConfig().getRegxValidationMessage(),
						componentConfig.getGtnUIFrameworkValidationConfig().getMinSize(),
						componentConfig.getGtnUIFrameworkValidationConfig().getMaxLength(), true));
			}
			textField.setValidationVisible(
					componentConfig.getGtnUIFrameworkValidationConfig().isFieldValidationVisible());
		}

		return textField;

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
		AbstractTextField textField = (AbstractTextField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		textField.setVisible(componentConfig.isVisible());
		textField.setEnabled(componentConfig.isEnable());
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		if (textField.isReadOnly()) {
			textField.setReadOnly(false);
			textField.setValue(value);
			textField.setReadOnly(true);
		} else {
			textField.setValue(value);
		}

		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		if (textboxConfig != null) {
			textField.setRequired(textboxConfig.isRequired());
			textField.setRequiredError(textboxConfig.getRequiredMessage());
			textField.setEnabled(textboxConfig.isEnable());
			textField.setImmediate(textboxConfig.isImmediate());
		}

	}

	private void fillTextBox(GtnUIFrameworkTextBoxConfig textBoxConfig, AbstractTextField vaadinTextBox) {
		GtnUIFrameworkWebserviceTextBoxResponse response = getResponseFromService(textBoxConfig);
		if (response != null) {
			vaadinTextBox.setValue(response.getDefaultValue());
		}
	}

	private GtnUIFrameworkWebserviceTextBoxResponse getResponseFromService(GtnUIFrameworkTextBoxConfig textBoxConfig) {
		GtnUIFrameworkWebserviceTextBoxResponse response = new GtnUIFrameworkWebserviceTextBoxResponse();
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		if (textBoxConfig.getLoadingUrl() != null) {
			response = wsclient
					.callGtnWebServiceUrl(textBoxConfig.getLoadingUrl(), request,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceTextBoxResponse();
		}
		return response;
	}

	private AbstractTextField generateAbstaractTextField(GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		if (textboxConfig != null && textboxConfig.isPasswordField()) {
			return new PasswordField(componentConfig.getComponentName());
		}
		return new TextField(componentConfig.getComponentName());
	}

	class TextFieldValueChangeListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public TextFieldValueChangeListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event) {
			try {
				GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
						componentConfig.getGtnTextBoxConfig().getValueChangeActionConfigList());

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

	private void addListeners(AbstractTextField textField, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		if (textboxConfig.getValueChangeActionConfigList() != null
				&& !textboxConfig.getValueChangeActionConfigList().isEmpty()) {
			textField.addValueChangeListener(new TextFieldValueChangeListener(componentConfig));
		}
		if (textboxConfig.getBlurActionConfigList() != null) {
			textField.addBlurListener(GtnUIFrameworkBlurListner.getListener());
		}
	}

}
