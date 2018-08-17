package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkDateFromToValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDateFromToValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("executing GtnFrameworkDateFromToValidationAction:-");
		if (GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_FROM_ID)
				.getDateFromDateField() != null
				&& GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_TO_ID)
						.getDateFromDateField() != null) {
			Date cffCreationDatefrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_FROM_ID)
					.getDateFromDateField();
			Date cffCreationDateTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_TO_ID)
					.getDateFromDateField();
			if (cffCreationDatefrom.compareTo(cffCreationDateTo) > 0) {
				gtnLogger.info("cffCreationDatefrom is after cffCreationDateTo ");
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

				List<Object> alertParamsList = new ArrayList<>();
				alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);
				alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_VALIDATION_MESSAGE);
				alertActionConfig.setActionParameterList(alertParamsList);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			} else {
				gtnLogger.info("cffCreationDatefrom is before cffCreationDateTo ");
				if (GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_FROM_ID)
						.getDateFromDateField() != null
						&& GtnUIFrameworkGlobalUI
								.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_TO_ID)
								.getDateFromDateField() != null) {
					cffApprovalDateValidation(componentId);
				} else {
					callSearchCriteria(componentId);
				}
			}
		}
		else if (GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_FROM_ID)
				.getDateFromDateField() != null
				&& GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_TO_ID)
						.getDateFromDateField() != null) {
			cffApprovalDateValidation(componentId);
		} else {
			callSearchCriteria(componentId);
		}
	}

	private void cffApprovalDateValidation(String componentId)
			throws  GtnFrameworkGeneralException {
		Date cffApprovalDatefrom = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_FROM_ID)
				.getDateFromDateField();
		Date cffApprovalDateTo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_TO_ID)
				.getDateFromDateField();
		if (cffApprovalDatefrom.compareTo(cffApprovalDateTo) > 0) {
			gtnLogger.info("cffApprovalDatefrom is after cffApprovalDateTo ");
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

			List<Object> alertParamsList = new ArrayList<>();
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_VALIDATION_MESSAGE);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		} else {
			gtnLogger.info("cffApprovalDatefrom is before cffApprovalDateTo ");
			callSearchCriteria(componentId);
		}
	}

	private void callSearchCriteria(String componentId) throws GtnFrameworkGeneralException {
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnFrameworkAdditionalSearchCriteriaAction.class.getName());

		actionConfigList.add(actionConfig);
		addValidationForSearchButon(actionConfigList);
		GtnUIFrameworkActionExecutor.executeActionList(componentId, actionConfigList);
	}

	private void addValidationSuccessActionConfigForSearchButton(
			List<GtnUIFrameWorkActionConfig> validationActionSuccessConfigList) {

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig
				.addActionParameter(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.CFF_ID,
				GtnFrameworkProcessSchedulerStringContants.PROJECTION_ID,
				GtnFrameworkProcessSchedulerStringContants.CUSTOMER_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.COMPANY_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.PROJECTION_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.CUSTOMER_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.COMPANY_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.TYPE_ID,
				GtnFrameworkProcessSchedulerStringContants.CONTRACT_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.ITEM_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.BUSINESS_UNIT_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.CONTRACT_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.ITEM_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.BUSINESS_UNIT_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_FROM_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_TO_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_FROM_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_TO_ID));

		validationActionSuccessConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig
				.addActionParameter(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE);
		
		validationActionSuccessConfigList.add(notificationActionConfig);
	}

	private void addValidationFailureActionConfigForSearchButton(
			List<GtnUIFrameWorkActionConfig> validationActionFailureConfigList) {

		GtnUIFrameWorkActionConfig onFailureAlertActionConfig = new GtnUIFrameWorkActionConfig();
		onFailureAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");
		onFailureAlertActionConfig.setActionParameterList(alertParamsList);

		validationActionFailureConfigList.add(onFailureAlertActionConfig);
	}

	private void addValidationForSearchButon(List<GtnUIFrameWorkActionConfig> searchButtonActionConfigList) {

		List<GtnUIFrameWorkActionConfig> validationActionSuccessConfigList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> validationActionFailureConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.CFF_ID,
				GtnFrameworkProcessSchedulerStringContants.PROJECTION_ID,
				GtnFrameworkProcessSchedulerStringContants.CUSTOMER_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.COMPANY_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.PROJECTION_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.CUSTOMER_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.COMPANY_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.TYPE_ID,
				GtnFrameworkProcessSchedulerStringContants.CONTRACT_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.ITEM_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.BUSINESS_UNIT_NO_ID,
				GtnFrameworkProcessSchedulerStringContants.CONTRACT_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.ITEM_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.BUSINESS_UNIT_NAME_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_FROM_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_TO_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_FROM_ID,
				GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_TO_ID));

		addValidationFailureActionConfigForSearchButton(validationActionFailureConfigList);
		addValidationSuccessActionConfigForSearchButton(validationActionSuccessConfigList);
		validationActionConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkValidationType.OR,
				validationActionFailureConfigList, validationActionSuccessConfigList));

		searchButtonActionConfigList.add(validationActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
