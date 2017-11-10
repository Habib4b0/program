/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCompanyPopulateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final String START = "Start";

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
		String fieldId = parameters.get(0);
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
			start = i;
			String parameter = parameters.get(i);
			if (parameter.contains("_" + componentValue + "_")) {
				valueId = parameter;
				break;
			}
		}
		fieldList.add(valueId);
		if (GtnFrameworkContractDashboardContants.BASE_PRICE.replace(" ", "").equals(componentValue)) {
			String valueId1 = valueId;
			String newComponentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(valueId).getCaptionFromComboBox();
			if (newComponentValue.isEmpty()) {
				newComponentValue = valueId1;
			}
			newComponentValue = newComponentValue.replace(" ", "");
			for (int i = start + 1; i < start + 4; i++) {
				String parameter = parameters.get(i);
				if (parameter.contains("_" + newComponentValue + "_")) {
					valueId1 = parameter;
					break;
				}
			}
			fieldList.add(valueId1);
		}
		String servicePath = componentId.equals(parameters.get(parameters.size() - 1))
				? mainParameters.get(6).toString()
				: mainParameters.get(5).toString();
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(fieldList);

		GtnUIFrameWorkActionConfig companyPopulateFailActionConfig = new GtnUIFrameWorkActionConfig();
		companyPopulateFailActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		companyPopulateFailActionConfig.addActionParameter("Populate");
		companyPopulateFailActionConfig.addActionParameter("Please Enter Value");
		List<GtnUIFrameWorkActionConfig> successActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig populateActionConfig = new GtnUIFrameWorkActionConfig();
		populateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		populateActionConfig.addActionParameter(GtnFrameworkCompanyConfirmPopulateAction.class.getName());
		populateActionConfig.addActionParameter(mainParameters.get(4));
		populateActionConfig.setFieldValues(Arrays.asList(fieldList.get(0), fieldList.get(1), servicePath));
		successActionList.add(populateActionConfig);
		if (fieldList.size() > 2) {
			GtnUIFrameWorkActionConfig companyPopulateBasepriceActionConfig = new GtnUIFrameWorkActionConfig();
			companyPopulateBasepriceActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			companyPopulateBasepriceActionConfig
					.addActionParameter(GtnFrameworkCompanyConfirmPopulateAction.class.getName());
			companyPopulateBasepriceActionConfig.addActionParameter(mainParameters.get(4));
			companyPopulateBasepriceActionConfig
					.setFieldValues(Arrays.asList(fieldList.get(1), fieldList.get(2), servicePath, "true"));
			successActionList.add(companyPopulateBasepriceActionConfig);
		}

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(mainParameters.get(4));
		successActionList.add(tableLoadActionConfig);
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);
		validationActionConfig.addActionParameter(Arrays.asList(companyPopulateFailActionConfig));

		validationActionConfig.addActionParameter(getModifiedSuccessActionList(successActionList, fieldList.get(1)));

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, validationActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private GtnUIFrameWorkActionConfig getModifiedFieldList(String componentId) {
		List<String> startFieldList = new ArrayList<>();
		List<String> endFieldList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dateValidationAction = new GtnUIFrameWorkActionConfig();
		dateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValidationAction.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		dateValidationAction.addActionParameter(componentId);
		dateValidationAction.addActionParameter(startFieldList);
		dateValidationAction.addActionParameter(endFieldList);
		dateValidationAction.addActionParameter("Populate Error");
		return getModifiedFieldValidation(dateValidationAction, componentId, startFieldList, endFieldList);
	}

	private List<GtnUIFrameWorkActionConfig> getModifiedSuccessActionList(
			List<GtnUIFrameWorkActionConfig> successActionList, String componentId) {
		GtnUIFrameWorkActionConfig dateValidationAction = getModifiedFieldList(componentId);
		if (dateValidationAction == null) {
			return successActionList;
		}
		dateValidationAction.addActionParameter(successActionList);
		return Arrays.asList(dateValidationAction);
	}

	private GtnUIFrameWorkActionConfig getModifiedFieldValidation(GtnUIFrameWorkActionConfig dateValidationAction,
			String componentId, List<String> startFieldList, List<String> endFieldList) {
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (componentId.contains("_CFPStartDate_")) {
			startFieldList.add(processDataBean.getCompaniesFieldList().get(4));
			endFieldList.add(processDataBean.getCompaniesFieldList().get(5));
			dateValidationAction.addActionParameter(START);
			return dateValidationAction;
		}
		if (componentId.contains("_CFPEndDate_")) {
			startFieldList.add(processDataBean.getCompaniesFieldList().get(4));
			endFieldList.add(processDataBean.getCompaniesFieldList().get(5));
			dateValidationAction.addActionParameter("End");
			return dateValidationAction;
		}
		if (componentId.contains("_IFPStartDate_")) {
			startFieldList.add(processDataBean.getItemsFieldList().get(4));
			endFieldList.add(processDataBean.getItemsFieldList().get(5));
			dateValidationAction.addActionParameter(START);
			return dateValidationAction;
		}
		if (componentId.contains("_IFPEndDate_")) {
			startFieldList.add(processDataBean.getItemsFieldList().get(4));
			endFieldList.add(processDataBean.getItemsFieldList().get(5));
			dateValidationAction.addActionParameter("End");
			return dateValidationAction;
		}
		return getPricingFieldValidation(dateValidationAction, componentId, startFieldList, endFieldList,
				processDataBean);
	}

	private GtnUIFrameWorkActionConfig getPricingFieldValidation(GtnUIFrameWorkActionConfig dateValidationAction,
			String componentId, List<String> startFieldList, List<String> endFieldList,
			GtnWsContractDashboardSessionBean processDataBean) {
		if (componentId.contains("_CPStartDate_") || componentId.contains("_PriceProtectionStartDate_")) {
			startFieldList.add(processDataBean.getPricingFieldList().get(4));
			endFieldList.add(processDataBean.getPricingFieldList().get(5));
			dateValidationAction.addActionParameter("Price Protection Start");
			return dateValidationAction;
		}
		if (componentId.contains("_CPEndDate_") || componentId.contains("_PriceProtectionEndDate_")) {
			startFieldList.add(processDataBean.getPricingFieldList().get(4));
			endFieldList.add(processDataBean.getPricingFieldList().get(5));
			dateValidationAction.addActionParameter("Price Protection End");
			return dateValidationAction;
		}
		return getRebateFieldValidation(dateValidationAction, componentId, startFieldList, endFieldList,
				processDataBean);
	}

	private GtnUIFrameWorkActionConfig getRebateFieldValidation(GtnUIFrameWorkActionConfig dateValidationAction,
			String componentId, List<String> startFieldList, List<String> endFieldList,
			GtnWsContractDashboardSessionBean processDataBean) {
		if (componentId.contains("RT") && componentId.contains("_StartDate_")) {
			startFieldList.add(processDataBean.getRebateFieldList().get(7));
			endFieldList.add(processDataBean.getRebateFieldList().get(8));
			dateValidationAction.addActionParameter(START);
			return dateValidationAction;
		}
		if (componentId.contains("RT") && componentId.contains("_EndDate_")) {
			startFieldList.add(processDataBean.getRebateFieldList().get(7));
			endFieldList.add(processDataBean.getRebateFieldList().get(8));
			dateValidationAction.addActionParameter("End");
			return dateValidationAction;
		}
		return null;
	}

}
