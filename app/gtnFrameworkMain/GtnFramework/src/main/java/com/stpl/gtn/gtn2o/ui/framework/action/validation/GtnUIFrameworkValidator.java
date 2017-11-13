package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public interface GtnUIFrameworkValidator {

	public void validate(String componentId, String fieldId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig) throws GtnFrameworkValidationFailedException;

	public GtnUIFrameworkValidator createInstance();
}
