package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkSetDefaultAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkRebatePlanCalculationAddButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkBaseComponent tableBaseComponent = null;
		GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkRebatePlanCalculationAddButtonAction.class);
		try {
			String tierToId;
			String tierFromId;
			String formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabformulaType")
					.getCaptionFromComboBox();
			if (formulaType.equals("Complex")) {
				tierToId = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX;
				tierFromId = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX;
			} else {
				tierToId = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO;
				tierFromId = GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM;
			}
			GtnWsRecordBean record = new GtnWsRecordBean();
			String resultValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(5))
					.getCaptionFromComboBox();
			List<Object> inputList = getTableIdFormulaType(resultValue, gtnUIFrameWorkActionConfig);
			tableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inputList.get(0).toString());
			List<String> properties = (List<String>) inputList.get(1);
			List<String> components = (List<String>) inputList.get(2);

			record.setRecordHeader(tableBaseComponent.getTableRecordHeader());
			setRecordProperties(properties, components, record, formulaType);
			tableBaseComponent.addItemToDataTable(record);

			String tierTo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tierToId).getStringFromField();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tierFromId).getComponent().setEnabled(false);

			if (tierTo != null && tierTo.equals(GtnFrameworkCommonStringConstants.STRING_EMPTY)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tierToId).getComponent().setEnabled(false);
			} else {
				BigDecimal value = BigDecimal.valueOf(Double.valueOf(tierTo));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tierFromId)
						.loadFieldValue(value.add(new BigDecimal("0.01")));
			}
			refreshComponenent(componentId);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	GtnWsRecordBean setRecordProperties(List<String> properties, List<String> components, GtnWsRecordBean record,
			String formulaType) throws GtnFrameworkValidationFailedException {

		for (int i = 0; i < properties.size(); i++) {

			if (i >= 2) {
				GtnUIFrameworkBaseComponent combobox = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(components.get(i));
				record.addProperties(properties.get(i), combobox.getCaptionFromComboBox());
				simpleOrComplex(i, record, combobox, formulaType);
				if (i == 3 && combobox.getIntegerFromField() < 0) {
					// true if value is newly added in value ddlb
					record.addProperties("newItem", true);
					record.addAdditionalProperty("newItem");
				}
			} else {
				Object value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(components.get(i)).getStringFromField();
				Object checkValue = (value.equals("") ? null : toDouble(String.valueOf(value), 0.0d));
				value = i <= 4 ? checkValue : value;
				record.addProperties(properties.get(i), value);
				record.addAdditionalProperty(value);
			}

		}

		return record;
	}

	private void refreshComponenent(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkSetDefaultAction defaultAction = new GtnUIFrameWorkSetDefaultAction();
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO, "");
		resetSelectMap.put(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR, null);
		resetSelectMap.put(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE, null);
		resetSelectMap.put(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX, "");
		resetSelectMap.put(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR_COMPLEX, null);
		resetSelectMap.put(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE_COMPLEX, null);
		resetSelectMap.put("rebatePlanCalculationsOperatorType", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentOperator1", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentValue", null);
		resetSelectMap.put("rebatePlanCalculationsOperatorType2", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentOperator2", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentValue2", null);
		resetSelectMap.put("rebatePlanCalculationsOperatorType3", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentOperator3", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentValue3", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentOperator4", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentValue4", null);
		resetSelectMap.put("rebatePlanCalculationsOperatorType4", null);
		resetSelectMap.put("rebatePlanCalculationsOperatorType5", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentOperator5", null);
		resetSelectMap.put("rebatePlanCalculationsAdjustmentValue5", null);
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		defaultAction.doAction(componentId, resetTableConfig);
	}

	public static double toDouble(String str, double defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return defaultValue;
		}
	}

	@SuppressWarnings("unchecked")
	private List<Object> getTableIdFormulaType(String resultValue,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		String tableId;
		List<Object> tableInputList = new ArrayList<>();
		List<String> tableProperties;
		List<String> tableComponents;
		if (resultValue.equals("Complex")) {
			tableId = "ruleDetailsattachResultTableComplex";
			tableProperties = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);
			tableComponents = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(4);
		} else {
			tableId = "ruleDetailsattachResultTable";
			tableProperties = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
			tableComponents = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		}
		tableInputList.add(tableId);
		tableInputList.add(tableProperties);
		tableInputList.add(tableComponents);
		return tableInputList;
	}

	private void simpleOrComplex(int i, GtnWsRecordBean record, GtnUIFrameworkBaseComponent combobox,
			String formulaType) throws GtnFrameworkValidationFailedException {
		String operatorIdValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperator")
				.getCaptionFromComboBox();
		String comboBoxValue;
		if (operatorIdValue.equals("%") && formulaType.equals("Simple") && combobox.getIntegerFromField() > 0) {
			comboBoxValue = combobox.getIntegerFromField().toString();
		} else {
			comboBoxValue = i == 2 ? combobox.getIntegerFromField().toString() : combobox.getCaptionFromComboBox();
		}
		record.addAdditionalProperty(comboBoxValue);
	}

}
