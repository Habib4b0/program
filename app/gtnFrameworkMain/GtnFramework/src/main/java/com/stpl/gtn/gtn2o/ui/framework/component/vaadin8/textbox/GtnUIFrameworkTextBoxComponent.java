package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.textbox;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.listner.GtnUIFrameworkBlurListner;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean.ComponentBinderValidatorBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceTextBoxResponse;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class GtnUIFrameworkTextBoxComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTextBoxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		TextField textField = generateAbstaractTextField(componentConfig);
		Binder<ComponentBinderValidatorBean> textBoxBinder = new Binder<>();

		loadStyles(textField, componentConfig.getComponentStyle());
		textField.setVisible(componentConfig.isVisible());
		textField.setEnabled(componentConfig.isEnable());
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}

		textField.setValue(value);
		textBoxBinder.forField(textField).withNullRepresentation(GtnFrameworkCommonStringConstants.STRING_EMPTY).bind(
				ComponentBinderValidatorBean::getWithNullRepresentation, ComponentBinderValidatorBean::setWithNullRepresentation);

		if (textboxConfig != null) {
			if (textboxConfig.isRequired()) {
				textBoxBinder.forField(textField).asRequired(textboxConfig.getRequiredMessage())
						.bind(ComponentBinderValidatorBean::getRequiredField, ComponentBinderValidatorBean::setRequiredField);
			}

			textField.setEnabled(textboxConfig.isEnable());

			if (textboxConfig.isNullSettingAllowed()) {
				textBoxBinder.forField(textField).withNullRepresentation(textboxConfig.getNullRepresentation()).bind(
						ComponentBinderValidatorBean::getWithNullRepresentation,
						ComponentBinderValidatorBean::setWithNullRepresentation);
			}
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

			if (componentConfig.getGtnUIFrameworkValidationConfig().isAttachRegxValidatior()) {

				textBoxBinder.forField(textField)
						.withValidator(new RegexpValidator(
								componentConfig.getGtnUIFrameworkValidationConfig().getRegxValidationMessage(),
								componentConfig.getGtnUIFrameworkValidationConfig().getFormatString()))
						.bind(ComponentBinderValidatorBean::getRegexpValidator, ComponentBinderValidatorBean::setRegexpValidator);
			}
			if (componentConfig.getGtnUIFrameworkValidationConfig().isAttachLengthValidatior()) {
				textBoxBinder.forField(textField)
						.withValidator(new StringLengthValidator(
								componentConfig.getGtnUIFrameworkValidationConfig().getRegxValidationMessage(),
								componentConfig.getGtnUIFrameworkValidationConfig().getMinSize(),
								componentConfig.getGtnUIFrameworkValidationConfig().getMaxLength()))
						.bind(ComponentBinderValidatorBean::getStringLengthValidator,
								ComponentBinderValidatorBean::setStringLengthValidator);
			}
		}
		setDefaultFocus(textField, componentConfig);
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
		Binder<ComponentBinderValidatorBean> textBoxBinder = new Binder<>();
		if (textboxConfig != null) {
			if (textboxConfig.isRequired()) {
				textBoxBinder.forField(textField).asRequired(textboxConfig.getRequiredMessage())
						.bind(ComponentBinderValidatorBean::getRequiredField, ComponentBinderValidatorBean::setRequiredField);
			}
			textField.setEnabled(textboxConfig.isEnable());
		}

	}

	private void fillTextBox(GtnUIFrameworkTextBoxConfig textBoxConfig, TextField vaadinTextBox) {
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

	private TextField generateAbstaractTextField(GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		if (textboxConfig != null && textboxConfig.isPasswordField()) {
			return new PasswordField(componentConfig.getComponentName());
		}
		return new TextField(componentConfig.getComponentName());
	}

	private void addListeners(TextField textField, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		if (textboxConfig.getValueChangeActionConfigList() != null
				&& !textboxConfig.getValueChangeActionConfigList().isEmpty()) {
			textField.addValueChangeListener(e -> {
				try {
					GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
							componentConfig.getGtnTextBoxConfig().getValueChangeActionConfigList());
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(ex.getMessage(), ex);
				}
			});
		}
		if (textboxConfig.getBlurActionConfigList() != null) {
			textField.addBlurListener(GtnUIFrameworkBlurListner.getListener());
		}
	}

	private void setDefaultFocus(AbstractTextField textField, GtnUIFrameworkComponentConfig componentConfig) {
		if (componentConfig.isDefaultFocus()) {
			textField.focus();
		}
	}
	

}
