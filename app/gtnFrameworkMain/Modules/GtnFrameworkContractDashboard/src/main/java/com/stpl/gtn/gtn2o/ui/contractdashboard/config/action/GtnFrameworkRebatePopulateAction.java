/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkRebateTabLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRebatePopulateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> parameters = (List<String>) mainParameters.get(1);
		List<String> fieldList = new ArrayList<>();
		String fieldId = getFieldId(parameters, mainParameters.get(7).toString());
		fieldList.add(fieldId);
		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldId).getStringFromField();
		if (componentValue.isEmpty()) {
			componentValue = componentId;
		}
		componentValue = componentValue.replace(" ", "");
		int start = Integer.parseInt(String.valueOf(mainParameters.get(2)));
		int end = Integer.parseInt(String.valueOf(mainParameters.get(3)));
		String valueId = fieldId;

		for (int i = start; i < parameters.size() - end; i++) {
			String parameter = parameters.get(i);
			if (parameter.contains("_" + componentValue + "_")) {
				valueId = parameter;
				break;
			}
		}
		fieldList.add(valueId);
		String servicePath = componentId.equals(parameters.get(parameters.size() - 1))
				? mainParameters.get(6).toString()
				: mainParameters.get(5).toString();
		GtnUIFrameWorkActionConfig rebatePopulateValidationActionConfig = new GtnUIFrameWorkActionConfig();
		rebatePopulateValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		rebatePopulateValidationActionConfig.setFieldValues(fieldList);

		GtnUIFrameWorkActionConfig rebatePopulateFailActionConfig = new GtnUIFrameWorkActionConfig();
		rebatePopulateFailActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		rebatePopulateFailActionConfig.addActionParameter("populate");
		rebatePopulateFailActionConfig.addActionParameter("Please Enter Value");
		List<GtnUIFrameWorkActionConfig> successActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig rebatePopulateActionConfig = new GtnUIFrameWorkActionConfig();
		rebatePopulateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rebatePopulateActionConfig.addActionParameter(GtnFrameworkCompanyConfirmPopulateAction.class.getName());
		rebatePopulateActionConfig.setFieldValues(Arrays.asList(fieldList.get(0), fieldList.get(1), servicePath));
		successActionList.add(rebatePopulateActionConfig);

		GtnUIFrameWorkActionConfig rebateTableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		rebateTableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rebateTableLoadActionConfig.addActionParameter(GtnFrameworkRebateTableLoadAction.class.getName());
		successActionList.add(rebateTableLoadActionConfig);

		GtnUIFrameWorkActionConfig tabLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tabLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadActionConfig.addActionParameter(GtnFrameworkRebateTabLoadAction.class.getName());
		successActionList.add(tabLoadActionConfig);
		rebatePopulateValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);
		rebatePopulateValidationActionConfig.addActionParameter(Arrays.asList(rebatePopulateFailActionConfig));
		rebatePopulateValidationActionConfig.addActionParameter(successActionList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rebatePopulateValidationActionConfig);
	}

	private String getFieldId(List<String> parameters, String calculationTypeId) throws GtnFrameworkGeneralException {
		String fieldId;
		String fieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(calculationTypeId).getCaptionFromComboBox();
		fieldValue = fieldValue.replace(" ", "").toLowerCase(Locale.ENGLISH).trim();
		switch (fieldValue) {
		case "formula":
			fieldId = parameters.get(1);
			break;
		case "rebateplan":
			fieldId = parameters.get(2);
			break;
		case "deductioncalendar":
			fieldId = parameters.get(3);
			break;
		default:
			fieldId = parameters.get(0);
		}
		return fieldId;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
