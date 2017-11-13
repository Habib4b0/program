package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkRPRemoveConfirmationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String resultValueComboBox = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1))
				.getCaptionFromComboBox();
		List<String> input = new ArrayList<>();
		String resultTableId = getResultTableId(resultValueComboBox);
		GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId);
		getInputForResetComponent(input, resultValueComboBox);
		int size = (table.getItemsFromDataTable().size());
		int lastIndex = (table.getItemsFromDataTable().size() - 1);

		if (size == 0) {
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			actionConfig.setActionParameterList(Arrays.asList(
					new Object[] { " No Items available", "There is no Rebate Plan Level available to remove." }));
			alertAction.doAction(componentId, actionConfig);
		} else if ((table.getValueFromDataTable()) == null) {
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			actionConfig.setActionParameterList(Arrays
					.asList(new Object[] { " Remove", "Please select the last tier from the list view to remove." }));
			alertAction.doAction(componentId, actionConfig);
		} else if ((table.getValueFromDataTable()) != null
				&& (size == 1 || (table.getItemsFromDataTable()).indexOf(table.getValueFromDataTable()) == lastIndex)) {
			String data = size == 1 ? "" : String.valueOf(table.getValueFromDataTable().getPropertyValueByIndex(0));
			GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
			confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			List<Object> alertParams = new ArrayList<>();
			alertParams.add(" Confirmation ");
			alertParams.add(" Are you sure you want to remove the last tier ?");

			List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

			GtnUIFrameWorkActionConfig removeActionConfig = new GtnUIFrameWorkActionConfig();
			removeActionConfig.setActionType(GtnUIFrameworkActionType.REMOVE_ACTION);
			removeActionConfig.setActionParameterList(Arrays.asList(new Object[] { resultTableId }));
			onSucessActionConfigList.add(removeActionConfig);

			GtnUIFrameWorkActionConfig removeActionConfigs = new GtnUIFrameWorkActionConfig();
			removeActionConfigs.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			removeActionConfigs.setActionParameterList(Arrays
					.asList(new Object[] { GtnUIFrameWorkFromToTierValueAction.class.getName(), data, size, input }));
			onSucessActionConfigList.add(removeActionConfigs);
			alertParams.add(onSucessActionConfigList);
			confirmationActionConfig.setActionParameterList(alertParams);
			GtnUIFrameWorkAction action = confirmationActionConfig.getActionType().getGtnUIFrameWorkAction();
			action.configureParams(confirmationActionConfig);

			action.doAction(componentId, confirmationActionConfig);

		}

	}

	private void getInputForResetComponent(List<String> input, String resultValueComboBox) {

		if (resultValueComboBox.equals("Complex")) {
			input.add(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX);
			input.add(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX);
		} else {
			input.add(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM);
			input.add(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO);

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private String getResultTableId(String resultValueComboBox) {
		String resultTableId;
		if (resultValueComboBox.equals("Complex")) {
			resultTableId = "ruleDetailsattachResultTableComplex";
		} else {
			resultTableId = "ruleDetailsattachResultTable";

		}
		return resultTableId;
	}

}
