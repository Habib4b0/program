package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameWorkSaveMandatoryAlert implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<GtnWsRecordBean> ruleDetailsList;

		StringBuilder fieldMsg = new StringBuilder();
		Object[] fieldValues = new Object[] { getString("rebatePlanInformationTabRebatePlanName"),
				getString("rebatePlanInformationTabRebatePlanType"), getString("rebatePlanInformationTabRebatePlanID"),
				getString("rebatePlanInformationTabformulaType"), getString("rebatePlanInformationTabRebatePlanNo"),
				getInt("rebatePlanInformationTabRebateStatus"), getInt("rebatePlanCalculationsRebateStructure"),
				getInt("rebatePlanCalculationsRangeBasedOn"), getInt("rebatePlanCalculationsRebateBasedOn") };
		String[] fields = GtnFrameworkStringConstants.getDO_ACTION_FIELDS();

		String resultValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabformulaType")
				.getCaptionFromComboBox();
		if (resultValue.equals("Complex")) {
			ruleDetailsList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleDetailsattachResultTableComplex")
					.getItemsFromDataTable();

		} else {
			ruleDetailsList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleDetailsattachResultTable")
					.getItemsFromDataTable();

		}
		validateFields(fields, fieldValues, fieldMsg);
		if (ruleDetailsList.isEmpty()) {
			if (fieldMsg.length() != 0) {
				fieldMsg.append(",\n");
			}
			fieldMsg.append("Attach atleast One Tier");
		}
		if (fieldMsg.length() != 0) {
			String msg = "Information for the following Mandatory fields need to be provided " + "<br>"
					+ fieldMsg.toString();
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
	}

	private String getString(final String componentId) {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
	}

	private Integer getInt(final String componentId) throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getIntegerFromField();
	}

	public static StringBuilder validateFields(String[] fields, Object[] values, StringBuilder message) {
		String appender = " ";
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || (values[i] instanceof String && (String.valueOf(values[i])).isEmpty())
					|| (values[i] instanceof Integer && (Integer) values[i] == 0)) {
				message.append(appender).append(fields[i]);
				appender = " , ";
			}
		}

		return message;
	}

	public StringBuilder validateFieldsComplex(String[] fields, Object[] values, StringBuilder message) {
		String appender = " ";
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || (values[i] instanceof String && (String.valueOf(values[i])).isEmpty())
					|| (values[i] instanceof Integer && (Integer) values[i] == 0)) {
				message.append(appender).append(fields[i]);
				appender = " , ";
			}

		}
		return message;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
