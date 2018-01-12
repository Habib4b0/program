/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.DateField;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkFieldFactoryAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkFieldFactoryAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardSessionBean sharedBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);

		try {
			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			GtnUIFrameworkComponentData componentData = baseComponent.getComponentData();
			List<GtnUIFrameWorkActionConfig> focusList = componentData.getCurrentComponentConfig()
					.getGtnUIFrameWorkFocusActionConfigList();
			GtnUIFrameworkActionParameter actionParameter = componentData.getActionParameter();
			if (sharedBean.needOperation()
					&& isComponentNeedToupdate(focusList, componentId, baseComponent, sharedBean)) {
				sharedBean.setFocusedId(null);

				gtnLogger.info("PropertyId=" + actionParameter.getPropertyId() + "\nOldValue="
						+ actionParameter.getOldValue() + "\nCurrentValue=" + actionParameter.getCurrentValue());
				if (isValidValue(baseComponent, componentData)) {
					operateFieldFactoryValue(actionParameter, componentId, parameters, baseComponent);
				}
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkFieldFactoryAction", e);
		}
		sharedBean.setFocusedId(null);
	}

	private boolean isComponentNeedToupdate(List<GtnUIFrameWorkActionConfig> focusList, String componentId,
			GtnUIFrameworkBaseComponent baseComponent, GtnWsContractDashboardSessionBean sharedBean) {
		return focusList == null || focusList.isEmpty() || componentId.equals(sharedBean.getFocusedId())
				|| baseComponent.getComponent() instanceof DateField;
	}

	private void operateFieldFactoryValue(GtnUIFrameworkActionParameter actionParameter, String componentId,

			List<Object> parameters, GtnUIFrameworkBaseComponent baseComponent) throws GtnFrameworkGeneralException {
		String propertyId = actionParameter.getPropertyId();
		int idIndex = Integer.parseInt(String.valueOf(parameters.get(2)));
		GtnUIFrameWorkActionConfig updateActionConfig = new GtnUIFrameWorkActionConfig();
		updateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		updateActionConfig.addActionParameter(GtnFrameworkFieldFactoryValueUpdateAction.class.getName());
		updateActionConfig.addActionParameter(parameters.get(1));
		updateActionConfig.addActionParameter(actionParameter.getItemId().getIntegerPropertyByIndex(idIndex));
		if (actionParameter.getPropertyId().endsWith("popup")) {
			propertyId += "1";
			updateActionConfig.addActionParameter(propertyId);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
					getPopupAction(componentId, propertyId, updateActionConfig));
			return;
		}
		Object value = actionParameter.getCurrentValue();
		actionParameter.getItemId().addProperties(actionParameter.getPropertyId(), value);
		if ("checkRecordId".equals(actionParameter.getPropertyId())) {
			value = "true".equalsIgnoreCase(String.valueOf(actionParameter.getCurrentValue())) ? "1" : "0";
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameter.getTableComponentId())
					.getLogicFromPagedDataTable().handleCheckBoxOnItem(propertyId,
							Boolean.parseBoolean(String.valueOf(actionParameter.getCurrentValue())));
		}
		if (propertyId.equals(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[8])) {
			propertyId = getFieldId(propertyId, actionParameter.getItemId(), value);
		}
		if (!propertyId.isEmpty()) {
			updateValue(propertyId, componentId, value, actionParameter, baseComponent, updateActionConfig);
		}
	}

	private void updateValue(String propertyId, String componentId, Object value,
			GtnUIFrameworkActionParameter actionParameter, GtnUIFrameworkBaseComponent baseComponent,
			GtnUIFrameWorkActionConfig updateActionConfig) throws GtnFrameworkGeneralException {
		updateActionConfig.addActionParameter(propertyId);
		updateActionConfig.addActionParameter(value);
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId,
				getModifiedSuccessAction(updateActionConfig, componentId, propertyId));
		if (propertyId.equals(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[7])) {
			String depandingValue = baseComponent.getCaptionFromComboBox();
			actionParameter.getItemId().getProperties().set(52, depandingValue);
			Object newValue = getFieldValue(actionParameter.getItemId());
			actionParameter.getItemId().addProperties(
					GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[8], newValue);
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameter.getTableComponentId());
			GtnFrameworkSessionManagerAction.getDashboardSessionBean(componentId).setNeedOperation(false);
			tableBaseComponent.setTableColumnValue(actionParameter.getItemId(),
					GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[8], null);
			tableBaseComponent.setTableRefresh(true);
			GtnFrameworkSessionManagerAction.getDashboardSessionBean(componentId).setNeedOperation(true);
		}
		if ((GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[16]).equals(propertyId)
				|| (GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[17]).equals(propertyId)) {
			refreshTable();
		}
		if (propertyId.equals(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[4])) {
			String componentIdPrefix = componentId.substring(0,
					componentId.length() - actionParameter.getPropertyId().length());
			GtnUIFrameworkBaseComponent priceBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					componentIdPrefix + GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[5]);
			if ("Contract Price".equalsIgnoreCase(baseComponent.getCaptionFromComboBox())) {
				actionParameter.getItemId().getProperties().set(18, 0);
				priceBaseComponent.setEnable(true);
			} else {
				actionParameter.getItemId().getProperties().set(18, 1);
				priceBaseComponent.setEnable(false);
			}
		}
	}

	private void refreshTable() throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("PricingTabPricingTable");
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		tableLogic.startSearchProcess(new ArrayList<String>(), true);
		GtnFrameworkSessionManagerAction.getDashboardSessionBean("PricingTabPricingTable").setNeedOperation(true);

	}

	private boolean isValidValue(GtnUIFrameworkBaseComponent baseComponent, GtnUIFrameworkComponentData componentData) {
		boolean ret = true;
		GtnUIFrameworkValidationConfig valConfig = componentData.getCurrentComponentConfig()
				.getGtnUIFrameworkValidationConfig();
		if (valConfig != null && (valConfig.isAttachRegxValidatior() || valConfig.isAttachLengthValidatior())) {
			ret = baseComponent.isValidFieldValue();
		}
		return ret;
	}

	private String getFieldId(String currentPropId, GtnWsRecordBean bean, Object value) {

		String depandingValue = bean.getStringPropertyByIndex(52);
		if (depandingValue.startsWith("P")) {
			bean.getProperties().set(53, value);
			return currentPropId + "Ddlb";
		}
		if (depandingValue.startsWith("D")) {
			bean.getProperties().set(54, value);
			return currentPropId + "Date";
		}
		if (depandingValue.startsWith("M")) {
			bean.getProperties().set(55, value);
			return currentPropId + "Entry";
		}
		return "";
	}

	private Object getFieldValue(GtnWsRecordBean bean) {
		String depandingValue = bean.getStringPropertyByIndex(52);
		if (depandingValue.startsWith("P")) {
			return bean.getPropertyValueByIndex(53);
		}
		if (depandingValue.startsWith("D")) {
			Object value = bean.getPropertyValueByIndex(54);
			if (value != null && Long.class.isAssignableFrom(value.getClass())) {
				value = new Date((Long) value);
			}
			return value;
		}
		if (depandingValue.startsWith("M")) {
			return bean.getStringPropertyByIndex(55).trim();
		}
		return "";
	}

	private GtnUIFrameWorkActionConfig getPopupAction(String componentId, String propertyId,
			GtnUIFrameWorkActionConfig updateActionConfig) {
		GtnUIFrameWorkActionConfig popupActionConfig;
		switch (propertyId) {
		case "rebatePlanNopopup1":
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW,
					"Rebate Plan ID Lookup",
					Arrays.asList(componentId, Arrays.asList("rebatePlanNo", "rebatePlanName"),
							Arrays.asList(componentId,
									getFieldFactoryComponent(componentId, "rebatePlanNopopup", "rebatePlanName")),
							Arrays.asList(updateActionConfig, "14")));
			break;
		case "deductionCalendarNopopup1":
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW,
					"Deduction Calendar Lookup", Arrays.asList(componentId, Arrays.asList("dcNo"),
							Arrays.asList(componentId), Arrays.asList(updateActionConfig, "8")));
			break;
		case "formulaNopopup1":
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW,
					"Formula No Lookup", Arrays.asList(componentId, Arrays.asList("formulaNo"),
							Arrays.asList(componentId), Arrays.asList(updateActionConfig, "0")));
			break;
		case "netSalesRulepopup1":
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW,
					GtnFrameworkContractDashboardContants.NET_SALES_FORMULA_LOOKUP,
					Arrays.asList(componentId, Arrays.asList(GtnFrameworkContractDashboardContants.RULE_NO),
							Arrays.asList(componentId), Arrays.asList(updateActionConfig, "4"), "Net Sales"));

			break;
		case "evaluationRulepopup1":
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW,
					GtnFrameworkContractDashboardContants.NET_SALES_FORMULA_LOOKUP,
					Arrays.asList(componentId, Arrays.asList(GtnFrameworkContractDashboardContants.RULE_NO),
							Arrays.asList(componentId), Arrays.asList(updateActionConfig, "4"), "Evaluation"));
			break;
		case "calculationRulepopup1":
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW,
					GtnFrameworkContractDashboardContants.NET_SALES_FORMULA_LOOKUP,
					Arrays.asList(componentId, Arrays.asList(GtnFrameworkContractDashboardContants.RULE_NO),
							Arrays.asList(componentId), Arrays.asList(updateActionConfig, "4"), "Calculation"));
			break;
		default:
			popupActionConfig = getActionConfigParameter(GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW,
					GtnFrameworkContractDashboardContants.NET_SALES_FORMULA_LOOKUP,
					Arrays.asList(componentId, Arrays.asList("formulaName"), Arrays.asList(componentId),
							Arrays.asList(updateActionConfig, "8")));
		}
		return popupActionConfig;
	}

	private String getFieldFactoryComponent(String componentId, String sourceComponent, String targetComponent) {
		return componentId.replace(sourceComponent, targetComponent);
	}

	private GtnUIFrameWorkActionConfig getActionConfigParameter(String viewId, String viewName,
			List<Object> paramList) {
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(viewId);
		popupActionConfig.addActionParameter(viewName);
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(paramList);
		return popupActionConfig;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private GtnUIFrameWorkActionConfig getModifiedFieldList(String componentId, String propertyId) {
		GtnUIFrameWorkActionConfig dateValidationAction;
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		switch (propertyId) {
		case "cfpStartDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getCompaniesFieldList().get(4),
					processDataBean.getCompaniesFieldList().get(5), GtnFrameworkContractDashboardContants.START);
			break;
		case "cfpEndDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getCompaniesFieldList().get(4),
					processDataBean.getCompaniesFieldList().get(5), "End");
			break;
		case "ifpStartDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getItemsFieldList().get(4),
					processDataBean.getItemsFieldList().get(5), GtnFrameworkContractDashboardContants.START);
			break;
		case "ifpEndDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getItemsFieldList().get(4),
					processDataBean.getItemsFieldList().get(5), "End");
			break;
		case "rebateStartDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getRebateFieldList().get(7),
					processDataBean.getRebateFieldList().get(8), GtnFrameworkContractDashboardContants.START);
			break;
		case "rebateEndDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getRebateFieldList().get(7),
					processDataBean.getRebateFieldList().get(8), "End");
			break;
		default:
			dateValidationAction = getPricingModifiedFieldList(componentId, propertyId);

		}
		return dateValidationAction;
	}

	private GtnUIFrameWorkActionConfig getPricingModifiedFieldList(String componentId, String propertyId) {
		GtnUIFrameWorkActionConfig dateValidationAction;
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		switch (propertyId) {
		case "CPStartDate":
		case "PriceProtectionStartDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getPricingFieldList().get(4),
					processDataBean.getPricingFieldList().get(5), GtnFrameworkContractDashboardContants.START);
			break;
		case "CPEndDate":
		case "PriceProtectionEndDate":
			dateValidationAction = getDateValidationAction(componentId, processDataBean.getPricingFieldList().get(4),
					processDataBean.getPricingFieldList().get(5), "End");
			break;
		default:
			dateValidationAction = null;

		}
		return dateValidationAction;
	}

	private GtnUIFrameWorkActionConfig getDateValidationAction(String componentId, String startField, String endField,
			String message) {
		List<String> startFieldList = new ArrayList<>();
		List<String> endFieldList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dateValidationAction = new GtnUIFrameWorkActionConfig();
		dateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValidationAction.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		dateValidationAction.addActionParameter(componentId);
		dateValidationAction.addActionParameter(startFieldList);
		dateValidationAction.addActionParameter(endFieldList);
		dateValidationAction.addActionParameter("Populate Error");
		startFieldList.add(startField);
		endFieldList.add(endField);
		dateValidationAction.addActionParameter(message);

		return dateValidationAction;
	}

	GtnUIFrameWorkActionConfig getModifiedSuccessAction(GtnUIFrameWorkActionConfig successAction, String componentId,
			String propertyId) {
		GtnUIFrameWorkActionConfig dateValidationAction = getModifiedFieldList(componentId, propertyId);
		if (dateValidationAction == null) {
			return successAction;
		}
		dateValidationAction.addActionParameter(Arrays.asList(successAction));
		return dateValidationAction;
	}

}
