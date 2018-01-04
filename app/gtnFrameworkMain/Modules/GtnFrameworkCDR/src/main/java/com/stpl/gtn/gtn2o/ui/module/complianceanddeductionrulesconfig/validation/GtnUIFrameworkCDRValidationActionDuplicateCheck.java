package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.v7.ui.AbstractField;

public class GtnUIFrameworkCDRValidationActionDuplicateCheck implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String subMessage = validate();

		if (!"".equals(subMessage)) {
			List<Object> actionParams = new ArrayList<>();
			actionParams.add("Duplicate Rules");
			actionParams.add("You cannot add two of the same rules. The rule you are trying to add already exists.");
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();

			gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

			GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
			alert.configureParams(gtnUIFrameWorkActionConf);
			alert.doAction("", gtnUIFrameWorkActionConf);
			throw new GtnFrameworkGeneralException("Custom validation failed");

		}
	}

	@SuppressWarnings("rawtypes")
	private String validate() throws GtnFrameworkValidationFailedException {
		String subMessage = "";
		List<GtnWsRecordBean> ruleDetailsList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ruleDetailsattachResultTable").getItemsFromDataTable();
		String[] fieldData = new String[6];
		String[] tableData = new String[6];
		fieldData[0] = String
				.valueOf(((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleDetailsLineType")).getValue());
		fieldData[1] = String
				.valueOf(((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleDetailsKeyWord")).getValue());
		fieldData[2] = String
				.valueOf(((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleDetailsOperator")).getValue());
		fieldData[3] = String
				.valueOf(((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleDetailsValue")).getValue());

		fieldData[4] = String.valueOf(
				((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleDetailsComparison")).getValue());
		fieldData[5] = String.valueOf(
				((AbstractField) GtnUIFrameworkGlobalUI.getVaadinComponent("ruleDetailsOperatorOne")).getValue());

		if (ruleDetailsList != null && !ruleDetailsList.isEmpty()) {
			GtnWsRecordBean dto = ruleDetailsList.get(ruleDetailsList.size() - 1);

			tableData[0] = String.valueOf(dto.getAdditionalProperties().get(0));
			tableData[1] = String.valueOf(dto.getAdditionalProperties().get(2));
			tableData[2] = String.valueOf(dto.getAdditionalProperties().get(3));
			tableData[3] = String.valueOf(dto.getAdditionalProperties().get(4));
			tableData[4] = String.valueOf(dto.getAdditionalProperties().get(5));
			tableData[5] = String.valueOf(dto.getAdditionalProperties().get(6));
			int count = 0;
			for (int i = 0; i < fieldData.length; i++) {
				if (checkEquality(fieldData[i], tableData[i])) {
					count++;
				}

			}
			if (count == fieldData.length) {
				return "duplicate";
			}

		}
		return subMessage;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRValidationActionDuplicateCheck();
	}

	private boolean checkEquality(String paramOne, String paramTwo) {

		return paramOne.equals(paramTwo);

	}

}
