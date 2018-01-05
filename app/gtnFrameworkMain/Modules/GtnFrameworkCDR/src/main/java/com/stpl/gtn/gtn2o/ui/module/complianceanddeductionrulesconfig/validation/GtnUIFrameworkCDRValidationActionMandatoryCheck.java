package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.v7.ui.AbstractField;

public class GtnUIFrameworkCDRValidationActionMandatoryCheck implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String subMessage = validate();

		if (!"".equals(subMessage)) {
			List<Object> actionParams = new ArrayList<>();
			actionParams.add("Missing Required Fields");
			actionParams
					.add("Please check that all required fields are populated. \n" + subMessage + " has no value. ");
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();
			gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

			GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
			alert.configureParams(gtnUIFrameWorkActionConf);
			alert.doAction(componentId, gtnUIFrameWorkActionConf);

			throw new GtnFrameworkGeneralException("Custom validation failed");

		}

	}

	private String validate() throws GtnFrameworkValidationFailedException {
		String subMessage = "";
		String ruleDetailsLineType = getValueFromField("ruleDetailsLineType");
		String ruleDetailsKeyWord = getValueFromField("ruleDetailsKeyWord");
		String ruleDetailsOperator = getValueFromField("ruleDetailsOperator");
		String ruleDetailsValue = getValueFromField("ruleDetailsValue");

		if (checkCondition(ruleDetailsLineType)) {
			subMessage = "Line Type";
		} else if (checkCondition(ruleDetailsKeyWord)) {
			subMessage = "Key Word";
		} else if (checkCondition(ruleDetailsOperator)) {
			subMessage = "Operator";
		} else if (checkCondition(ruleDetailsValue)) {
			subMessage = "Value";
		}

		return subMessage;
	}

	@SuppressWarnings("rawtypes")
	private String getValueFromField(final String propertyId) {

		return String.valueOf(((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent(propertyId)).getValue());

	}

	private boolean checkCondition(final String value) {
		return "null".equals(value) || "".equals(value);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRValidationActionMandatoryCheck();
	}

}
