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
		if(componentConfig.getComponentHight()!=null) {
			textField.setHeight(componentConfig.getComponentHight());
		}
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

	private void loadStyles(final Component component, final List<String> stylesForTextBox) {
		if (stylesForTextBox != null) {
			for (String styleText : stylesForTextBox) {
				component.addStyleName(styleText);
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
		AbstractTextField abstractTextField = (AbstractTextField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		abstractTextField.setVisible(componentConfig.isVisible());
		abstractTextField.setEnabled(componentConfig.isEnable());
		String textValue = "";
		if (componentConfig.getComponentValue() != null) {
			textValue = String.valueOf(componentConfig.getComponentValue());
		}
		if (abstractTextField.isReadOnly()) {
			abstractTextField.setReadOnly(false);
			abstractTextField.setValue(textValue);
			abstractTextField.setReadOnly(true);
		} else {
			abstractTextField.setValue(textValue);
		}

		GtnUIFrameworkTextBoxConfig textBoxComponentConfig = componentConfig.getGtnTextBoxConfig();
		Binder<ComponentBinderValidatorBean> textBoxBinder = new Binder<>();
		if (textBoxComponentConfig != null) {
			if (textBoxComponentConfig.isRequired()) {
				textBoxBinder.forField(abstractTextField).asRequired(textBoxComponentConfig.getRequiredMessage())
						.bind(ComponentBinderValidatorBean::getRequiredField, ComponentBinderValidatorBean::setRequiredField);
			}
			abstractTextField.setEnabled(textBoxComponentConfig.isEnable());
		}
		
		setDefaultFocus(abstractTextField, componentConfig);

	}

	private void fillTextBox(GtnUIFrameworkTextBoxConfig textBoxConfig, TextField vaadin8TextBox) {
		GtnUIFrameworkWebserviceTextBoxResponse textBoxResponse = getResponseFromService(textBoxConfig);
		if (textBoxResponse != null) {
			vaadin8TextBox.setValue(textBoxResponse.getDefaultValue());
		}
	}

	private GtnUIFrameworkWebserviceTextBoxResponse getResponseFromService(GtnUIFrameworkTextBoxConfig textBoxConfig) {
		GtnUIFrameworkWebserviceTextBoxResponse textBoxResponse = new GtnUIFrameworkWebserviceTextBoxResponse();
		GtnUIFrameworkWebServiceClient textBoxWsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest textBoxRequest = new GtnUIFrameworkWebserviceRequest();
		if (textBoxConfig.getLoadingUrl() != null) {
			textBoxResponse = textBoxWsclient
					.callGtnWebServiceUrl(textBoxConfig.getLoadingUrl(), textBoxRequest,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceTextBoxResponse();
		}
		return textBoxResponse;
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
