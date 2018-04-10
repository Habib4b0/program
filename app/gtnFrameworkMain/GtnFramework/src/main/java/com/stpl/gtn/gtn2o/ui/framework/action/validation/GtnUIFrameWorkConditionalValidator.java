package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameWorkConditionalValidator implements GtnUIFrameworkValidator {

	public void isNotNull(Object value) throws GtnFrameworkValidationFailedException {
		if (value != null) {
			return;
		}
		throw new GtnFrameworkValidationFailedException("Not Empty validation Failed");

	}

	public void isNotEmpty(Object value) throws GtnFrameworkValidationFailedException {
		isNotNull(value);
		if (!"".equals(String.valueOf(value))) {
			return;
		}

		throw new GtnFrameworkValidationFailedException("Not Empty validation Failed");

	}

	@Override
	public void validate(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {

		if (gtnUIFrameworkValidationConfig != null && gtnUIFrameworkValidationConfig.getConditionList() != null) {
			GtnUIFrameworkBaseComponent vaadinFieldBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldId, componentId);
			Object currentValue = vaadinFieldBaseComponent.getValueFromComponent();
			for (GtnUIFrameworkConditionalValidationType condition : gtnUIFrameworkValidationConfig
					.getConditionList()) {

				if (GtnUIFrameworkConditionalValidationType.NOT_NULL == condition) {
					isNotNull(currentValue);

				} else if (GtnUIFrameworkConditionalValidationType.NOT_EMPTY == condition) {

					isNotEmpty(currentValue);
				}

			}

		}
	}

	@Override
	public GtnUIFrameworkValidator createInstance() {
		return new GtnUIFrameWorkConditionalValidator();
	}

}