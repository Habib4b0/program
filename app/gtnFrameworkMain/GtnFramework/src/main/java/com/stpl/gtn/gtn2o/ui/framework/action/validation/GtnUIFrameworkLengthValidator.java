package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.v7.ui.Field;

public class GtnUIFrameworkLengthValidator implements GtnUIFrameworkValidator {

	@Override
	public void validate(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {
		if (gtnUIFrameworkValidationConfig != null && gtnUIFrameworkValidationConfig.getMaxLength() != 0) {
			validateLength(componentId, fieldId, gtnUIFrameworkValidationConfig);
		}
	}

	private void validateLength(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {

		Field<?> vaadinField = (Field<?>) GtnUIFrameworkGlobalUI.getVaadinComponent(fieldId, componentId);
		String currentValue = (String) vaadinField.getValue();
		isValidLength(currentValue, gtnUIFrameworkValidationConfig);

	}

	private void isValidLength(String currentValue, GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {
		if (currentValue.length() <= gtnUIFrameworkValidationConfig.getMaxLength()) {
			return;
		}
		throw new GtnFrameworkValidationFailedException("Length Validation failed");

	}

	@Override
	public GtnUIFrameworkValidator createInstance() {
		return new GtnUIFrameworkLengthValidator();
	}

}