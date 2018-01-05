package com.stpl.gtn.gtn2o.ui.framework.component.date.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.data.validator.AbstractValidator;

public class GtnFrameworkDateValidator extends AbstractValidator<Date> {

	private String componentId;
	private static final long serialVersionUID = 1L;
	private List<GtnUIFrameWorkActionConfig> actionConfigList;

	public GtnFrameworkDateValidator(String componentId, List<GtnUIFrameWorkActionConfig> actionConfigList) {
		super("");
		this.componentId = componentId;
		this.actionConfigList = actionConfigList == null ? actionConfigList : new ArrayList<>(actionConfigList);
	}

	@Override
	protected boolean isValidValue(Date value) {
		try {
			GtnUIFrameworkActionExecutor.executeActionList(componentId, actionConfigList);
			GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
			return true;
		} catch (GtnFrameworkGeneralException e) {
			setErrorMessage(e.getErrorMessage());
		}
		return false;
	}

	@Override
	public Class<Date> getType() {
		return Date.class;
	}

}
