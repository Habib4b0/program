package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameworkRPValidationActionAddMandatoryCheck
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String subMessage = validate();
		if (!"".equals(subMessage)) {
			String msg = subMessage;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

	private String validate() throws GtnFrameworkValidationFailedException {
		String subMessage = "";
		String fromTier;
		String toTier;
		String operator;
		String calculateValue;
		String formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabformulaType")
				.getCaptionFromComboBox();
		if (formulaType.equals("Complex")) {
			fromTier = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX;
			toTier = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX;
			operator = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR_COMPLEX;
			calculateValue = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE_COMPLEX;
		} else {
			fromTier = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM;
			toTier = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO;
			operator = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR;
			calculateValue = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE;
		}
		GtnUIFrameworkBaseComponent tierToComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(toTier);

		String tierFrom = getValueFromField(fromTier);

		String tierTo = getValueFromField(toTier);

		String tierOperator = getValueFromField(operator);

		String tierValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(calculateValue).getCaptionFromComboBox();

		if (!tierToComponent.isEnabled()) {
			subMessage = "No more tiers can be entered";
			return subMessage;
		}
		if (checkConditions(tierFrom)) {
			subMessage = "Please enter From";
			return subMessage;
		} else if (!tierFrom.matches(GtnFrameworkRegexStringConstants.NUMERIC_DECIAL_DOUBLE_PRECISION_FORMAT)) {
			subMessage = "Please enter only numbers with two decimals places in From";
			return subMessage;
		} else if (!checkConditions(tierTo) && Double.valueOf(tierTo) <= Double.valueOf(tierFrom)) {
			subMessage = "To value should not be lesser than From value";
			return subMessage;
		} else if (checkCondition(tierOperator)) {
			subMessage = "Please select Operator";
			return subMessage;
		} else if (checkCondition(tierValue)) {
			subMessage = "Please select value";
			return subMessage;
		}

		return subMessage;
	}

	private String getValueFromField(final String componentId) {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
	}

	private boolean checkCondition(final String value) {
		return "null".equals(value) || "".equals(value);
	}

	private boolean checkConditions(final Object value) {
		return "null".equals(value) || "".equals(value);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
