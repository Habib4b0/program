package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.v7.ui.Field;

public class GtnUIFrameworkFormatValidator implements GtnUIFrameworkValidator {

	@Override
	public void validate(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {

		if (gtnUIFrameworkValidationConfig != null && gtnUIFrameworkValidationConfig.getFormatString() != null) {
			validateFormat(componentId, fieldId, gtnUIFrameworkValidationConfig);
		}
	}

	private void validateFormat(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {

		Field<?> vaadinField = (Field<?>) GtnUIFrameworkGlobalUI.getVaadinComponent(fieldId, componentId);
		String currentValue = (String) vaadinField.getValue();
		isValidFormat(currentValue, gtnUIFrameworkValidationConfig.getFormatString());

	}

	private void isValidFormat(String currentValue, String format) throws GtnFrameworkValidationFailedException {
		if (currentValue.matches(format)) {
			return;
		}
		throw new GtnFrameworkValidationFailedException("Format Validation failed");

	}

	@Override
	public GtnUIFrameworkValidator createInstance() {
		return new GtnUIFrameworkFormatValidator();
	}

}