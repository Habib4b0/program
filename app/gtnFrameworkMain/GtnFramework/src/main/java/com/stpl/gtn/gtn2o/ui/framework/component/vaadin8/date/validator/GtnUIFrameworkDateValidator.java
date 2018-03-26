package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.date.validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class GtnUIFrameworkDateValidator extends AbstractValidator<LocalDate> implements Validator<LocalDate> {

	private static final long serialVersionUID = 1L;
	private String componentId;
	private List<GtnUIFrameWorkActionConfig> validationActionConfigList;

	public GtnUIFrameworkDateValidator(String componentId,
			List<GtnUIFrameWorkActionConfig> validationActionConfigList) {
		super("");
		this.componentId = componentId;
		this.validationActionConfigList = validationActionConfigList == null ? validationActionConfigList
				: new ArrayList<>(validationActionConfigList);

	}

	@Override
	public ValidationResult apply(LocalDate value, ValueContext context) {
		try {
			GtnUIFrameworkActionExecutor.executeActionList(componentId, validationActionConfigList);
			GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		} catch (GtnFrameworkGeneralException e) {
			e.getErrorMessage();
		}
		return null;
	}

}
