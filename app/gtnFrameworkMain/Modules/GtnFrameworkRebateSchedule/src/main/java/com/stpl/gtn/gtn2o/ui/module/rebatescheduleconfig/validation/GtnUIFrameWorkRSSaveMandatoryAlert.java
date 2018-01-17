package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.v7.ui.AbstractField;

public class GtnUIFrameWorkRSSaveMandatoryAlert implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String subMessage = validate();

		if (!"".equals(subMessage)) {
			String msg = "Information for the following Mandatory fields need to be provided:" + " <br> " + subMessage
					+ "  ";

			throw new GtnFrameworkValidationFailedException(msg, componentId);

		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	private String validate() {
		String subMessage = "";

		String rebateScheduleId = getValueFromField("rebateScheduleId1");
		String rebateScheduleNo = getValueFromField("rebateScheduleNo1");
		String rebateScheduleName = getValueFromField("rebateScheduleName1");
		String rebateScheduleStatus = getValueFromField("rebateScheduleStatus1");

		String rebateScheduleStartDate = getValueFromField("rebateScheduleStartDate");
		String rsDeductionInclusion = getValueFromField("rsDeductionInclusion");
		String rebateScheduleType = getValueFromField("rebateScheduleType1");
		String rebateProgramType = getValueFromField("rebateProgramType1");
		String rebateScheduleCategory = getValueFromField("rebateScheduleCategory1");
		String rebateFrequency = getValueFromField("rebateFrequency1");
		String paymentFrequency = getValueFromField("paymentFrequency");
		String paymentMethod = getValueFromField("paymentMethod");
		String calculationType = getValueFromField("calculationType1");
		String calculationLevel = getValueFromField("calculationLevel");
		GtnUIFrameworkBaseComponent rSrightResultTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("RSrightResultTable");

		if (checkCondition(rebateScheduleId)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Schedule ID";
		}

		if (checkCondition(rebateScheduleName)) {
			subMessage = subMessage + "Rebate Schedule Name";

		}

		if (checkCondition(rebateScheduleNo)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Schedule No";
		}
		if (checkCondition(rebateScheduleStatus)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Schedule Status";
		}
		if (checkCondition(rebateScheduleStartDate)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Schedule Start Date";
		}
		if (checkCondition(rsDeductionInclusion)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Deduction Inclusion";
		}

		if (checkCondition(rebateScheduleType)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Schedule Type";
		}

		if (checkCondition(rebateProgramType)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Program Type";
		}

		if (checkCondition(rebateScheduleCategory)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Schedule Category";
		}

		if (checkCondition(rebateFrequency)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Rebate Frequency";
		}
		if (checkCondition(paymentFrequency)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Payment Frequency";
		}

		if (checkCondition(paymentMethod)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Payment Method";
		}
		if (checkCondition(calculationType)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Calculation  Type";
		}
		if (checkCondition(calculationLevel)) {
			subMessage = subMessageEmptyCheck(subMessage) + "Calculation Level";
		}
		if (rSrightResultTable.getExtPagedTableSize() == 0) {

			subMessage = subMessageEmptyCheck(subMessage) + "Select atleast one IFP.";
		}

		return subMessage;
	}

	@SuppressWarnings("rawtypes")
	private String getValueFromField(final String propertyId) {

		return String.valueOf(((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent(propertyId)).getValue());

	}

	private boolean checkCondition(final String value) {
		return "null".equals(value) || "".equals(value) || value == null;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public String subMessageEmptyCheck(String subMessage) {
		String message = subMessage;
		if (!message.isEmpty()) {
			message = message + ",";
		}
		return message;
	}

}
