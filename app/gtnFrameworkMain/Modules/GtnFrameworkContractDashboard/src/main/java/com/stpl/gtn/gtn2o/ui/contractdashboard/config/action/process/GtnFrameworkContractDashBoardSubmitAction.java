/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkErrorLabelAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractDashBoardSubmitAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
    
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		GtnUIFrameworkBaseComponent errorLabelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(processDataBean.getErrorDisplayId());
		errorLabelBaseComponent.setVisible(false);
		if (processDataBean.getHasPermission() == null) {
			GtnUIFrameWorkActionConfig permissionActionConfig = new GtnUIFrameWorkActionConfig();
			permissionActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			permissionActionConfig.addActionParameter(GtnUIFrameworkPermissionCheckAction.class.getName());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, permissionActionConfig);
		}
		if (!processDataBean.getHasPermission()) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					parameters.get(2).toString(), parameters.get(3).toString());
			return;
		}

		doValidationAndSaveAction(processDataBean, componentId, parameters, errorLabelBaseComponent);

	}

	private void doValidationAndSaveAction(GtnWsContractDashboardSessionBean processDataBean, String componentId,
			List<Object> parameters, GtnUIFrameworkBaseComponent errorLabelBaseComponent)
			throws GtnFrameworkGeneralException {
		if (validateContract(componentId, processDataBean) && validateCfp(componentId, processDataBean, parameters)) {
			doMoreValidationAndSaveAction(processDataBean, componentId, parameters, errorLabelBaseComponent);
		}
	}

	private void doMoreValidationAndSaveAction(GtnWsContractDashboardSessionBean processDataBean, String componentId,
			List<Object> parameters, GtnUIFrameworkBaseComponent errorLabelBaseComponent)
			throws GtnFrameworkGeneralException {
		if (validateIfp(componentId, processDataBean, parameters)
				&& validatePs(componentId, processDataBean, parameters)
				&& validateRs(componentId, processDataBean, parameters)) {
			doSaveAction(processDataBean, componentId, parameters, errorLabelBaseComponent);
		}
	}

	private void doSaveAction(GtnWsContractDashboardSessionBean processDataBean, String componentId,
			List<Object> parameters, GtnUIFrameworkBaseComponent errorLabelBaseComponent)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setContractDashboardBean(processDataBean);

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		cdRequest.setUserId(processDataBean.getProcessBean().getUserId());
		cdRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.VALIDATE_CONTRACT_DASHBOARD_TO_SAVE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdResponse = newResponse.getGtnWsContractDashboardResponse();
		if (cdResponse.isSuccess()) {
			confirmSubmit(componentId, parameters.get(3).toString(), parameters.get(4).toString());
			return;
		}
		errorLabelBaseComponent.setVisible(true);
		errorLabelBaseComponent.setPropertyValue(cdResponse.getMessage());
	}

	private void confirmSubmit(String componentId, String confirmHeader, String confirmMessage)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(confirmHeader);
		confirmActionConfig.addActionParameter(confirmMessage);
		GtnUIFrameWorkActionConfig submitActionConfig = new GtnUIFrameWorkActionConfig();
		submitActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		submitActionConfig.addActionParameter(GtnFrameworkContractDashBoardConfirmSubmitAction.class.getName());
		submitActionConfig.addActionParameter(componentId);
		confirmActionConfig.addActionParameter(Arrays.asList(submitActionConfig));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@SuppressWarnings("unchecked")
	private boolean validateContract(String componentId, GtnWsContractDashboardSessionBean processDataBean)
			throws GtnFrameworkGeneralException {
		List<String> fieldList = new ArrayList<>();
		fieldList.addAll(processDataBean.getContractInfoFieldList().subList(0, 5));
		fieldList.add(processDataBean.getContractInfoFieldList().get(6));
		List<String> contractFieldMessageList = new ArrayList<>();

		contractFieldMessageList.add("Contract ID should be entered on Contract Header tab");
		contractFieldMessageList.add("Contract No should be entered on Contract Header tab");
		contractFieldMessageList.add("Contract Name should be entered on Contract Header tab");
		contractFieldMessageList.add("Contract Type should be entered on Contract Header tab");
		contractFieldMessageList.add("Contract Status should be entered on Contract Header tab");
		contractFieldMessageList.add("Start Date should be entered on Contract Header tab");
		contractFieldMessageList.add("Trading Partner should be entered on Contract Header tab");

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
				getValidateActionConfig(processDataBean, fieldList, contractFieldMessageList));
		if (processDataBean.isSuccess()) {
			collectFieldValues(processDataBean.getContractInfoFieldList(), componentId,
					processDataBean.getContractInfoBean().getProperties());
			processDataBean.getContractInfoBean().getProperties()
					.set(49, GtnUIFrameworkGlobalUI
							.getVaadinComponentData(processDataBean.getContractInfoFieldList().get(9))
							.getCustomDataList().get(0));
			processDataBean.getContractInfoBean().getProperties()
					.set(50, GtnUIFrameworkGlobalUI
							.getVaadinComponentData(processDataBean.getContractInfoFieldList().get(12))
							.getCustomDataList().get(0));

			List<Object> notesValueList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();
			processDataBean.getContractInfoBean().getProperties().set(48, String.valueOf(notesValueList.get(0)));
			processDataBean.setNotesTabRecordList(
					collectNotesTableRecord((List<NotesDTO>) notesValueList.get(1), processDataBean));
			if (processDataBean.isAliasLoaded()) {
				processDataBean.setContractAliasRecordList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(processDataBean.getAliasTableId()).getItemsFromDataTable());
			}
		}
		return processDataBean.isSuccess();
	}

	private List<GtnWsRecordBean> collectNotesTableRecord(List<NotesDTO> dataRowList,
			GtnWsContractDashboardSessionBean processDataBean) {
		List<GtnWsRecordBean> records = new ArrayList<>();
		for (NotesDTO notesDTO : dataRowList) {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			dto.setProperties(new ArrayList<>());
			GtnWsRecordBean.addProperties(0, notesDTO.getDocDetailsId(), dto.getProperties());
			GtnWsRecordBean.addProperties(1, processDataBean.getProcessBean().getContractId(), dto.getProperties());
			GtnWsRecordBean.addProperties(2, "CONTRACT_MASTER", dto.getProperties());
			GtnWsRecordBean.addProperties(3, notesDTO.getDocumentFullPath(), dto.getProperties());
			GtnWsRecordBean.addProperties(4, new Date(), dto.getProperties());
			GtnWsRecordBean.addProperties(5, processDataBean.getProcessBean().getUserId(), dto.getProperties());
			GtnWsRecordBean.addProperties(6, notesDTO.getUserName(), dto.getProperties());
			records.add(dto);
		}
		return records;
	}

	private boolean validateCfp(String componentId, GtnWsContractDashboardSessionBean processDataBean,
			List<Object> parameters) throws GtnFrameworkGeneralException {
		if (processDataBean.getProcessBean().getCfpContractId() == 0) {
			return true;
		}
		if (!processDataBean.isCompaniesLoaded()) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					parameters.get(5).toString(), parameters.get(6).toString());
			return false;
		}
		List<String> cfpFieldList = new ArrayList<>();
		cfpFieldList.addAll(processDataBean.getCompaniesFieldList().subList(0, 5));
		cfpFieldList.add(processDataBean.getCompaniesFieldList().get(12));
		List<String> cfpFieldMessageList = new ArrayList<>();

		cfpFieldMessageList.add("CFP ID should be entered on Companies tab");
		cfpFieldMessageList.add("CFP No should be entered on Companies tab");
		cfpFieldMessageList.add("CFP Name should be entered on Companies tab");
		cfpFieldMessageList.add("CFP Status should be selected on Companies tab");
		cfpFieldMessageList.add("CFP Start Date should be selected on Companies tab");
		cfpFieldMessageList.add("Sales Inclusion should be selected in Companies Tab");

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
				getValidateActionConfig(processDataBean, cfpFieldList, cfpFieldMessageList));
		if (processDataBean.isSuccess()) {
			collectFieldValues(processDataBean.getCompaniesFieldList(), componentId,
					processDataBean.getCompanyInfoBean().getProperties());
			processDataBean.getCompanyInfoBean().getProperties()
					.set(17, GtnUIFrameworkGlobalUI
							.getVaadinComponentData(processDataBean.getCompaniesFieldList().get(10)).getCustomDataList()
							.get(0));
		}
		return processDataBean.isSuccess();
	}

	private boolean validateIfp(String componentId, GtnWsContractDashboardSessionBean processDataBean,
			List<Object> parameters) throws GtnFrameworkGeneralException {
		if (processDataBean.getProcessBean().getIfpContractId() == 0) {
			return true;
		}
		if (!processDataBean.isItemsLoaded()) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					parameters.get(5).toString(), parameters.get(6).toString());
			return false;
		}
		List<String> ifpFieldList = new ArrayList<>();
		ifpFieldList.addAll(processDataBean.getItemsFieldList().subList(0, 5));
		List<String> ifpFieldMessageList = new ArrayList<>();

		ifpFieldMessageList.add("IFP ID should be entered on Items tab");
		ifpFieldMessageList.add("IFP No should be entered on Items tab");
		ifpFieldMessageList.add("IFP Name should be entered on Items tab");
		ifpFieldMessageList.add("IFP Status should be selected on Items tab");
		ifpFieldMessageList.add("IFP Start Date should be selected on Items tab");

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
				getValidateActionConfig(processDataBean, ifpFieldList, ifpFieldMessageList));

		if (processDataBean.isSuccess()) {
			collectFieldValues(processDataBean.getItemsFieldList(), componentId,
					processDataBean.getItemInfoBean().getProperties());
		}
		return processDataBean.isSuccess();
	}

	private boolean validatePs(String componentId, GtnWsContractDashboardSessionBean processDataBean,
			List<Object> parameters) throws GtnFrameworkGeneralException {
		if (processDataBean.getProcessBean().getPsContractId() == 0) {
			return true;
		}
		if (!processDataBean.isPricingLoaded()) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					parameters.get(5).toString(), parameters.get(6).toString());
			return false;
		}
		List<String> psFieldList = new ArrayList<>();
		List<String> psFieldMessageList = new ArrayList<>();
		psFieldList.add(processDataBean.getPricingFieldList().get(0));
		psFieldList.add(processDataBean.getPricingFieldList().get(1));
		psFieldList.add(processDataBean.getPricingFieldList().get(2));
		psFieldList.add(processDataBean.getPricingFieldList().get(3));
		psFieldList.add(processDataBean.getPricingFieldList().get(4));

		psFieldMessageList.add("Price Schedule ID should be entered on Pricing tab");
		psFieldMessageList.add("Price Schedule No should be entered on Pricing tab");
		psFieldMessageList.add("Price Schedule Name should be entered on Pricing tab");
		psFieldMessageList.add("Price Schedule Status should be selected on Pricing tab");
		psFieldMessageList.add("Price Schedule Start Date should be selected on Pricing tab");

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
				getValidateActionConfig(processDataBean, psFieldList, psFieldMessageList));
		if (processDataBean.isSuccess()) {
			collectFieldValues(processDataBean.getPricingFieldList(), componentId,
					processDataBean.getPriceInfoBean().getProperties());
		}
		return processDataBean.isSuccess();
	}

	private boolean validateRs(String componentId, GtnWsContractDashboardSessionBean processDataBean,
			List<Object> parameters) throws GtnFrameworkGeneralException {
		if (processDataBean.getProcessBean().getRsContractId() == 0) {
			return true;
		}
		if (!processDataBean.isRebateLoaded()) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					parameters.get(5).toString(), parameters.get(6).toString());
			return false;
		}
		List<String> rsFieldList = new ArrayList<>();
		rsFieldList.addAll(processDataBean.getRebateFieldList().subList(0, 8));
		List<String> rsFieldMessageList = new ArrayList<>();
		rsFieldList.add(processDataBean.getRebateFieldList().get(16));
		rsFieldList.add(processDataBean.getRebateFieldList().get(24));
		rsFieldList.add(processDataBean.getRebateFieldList().get(26));
		rsFieldList.add(processDataBean.getRebateFieldList().get(29));
		rsFieldList.add(processDataBean.getRebateFieldList().get(36));
		rsFieldList.add(processDataBean.getRebateFieldList().get(38));

		rsFieldMessageList.add("Rebate Schedule ID should be entered on Rebate tab");
		rsFieldMessageList.add("Rebate Schedule No should be entered on Rebate tab");
		rsFieldMessageList.add("Rebate Schedule Name should be entered on Rebate tab");
		rsFieldMessageList.add("Rebate Schedule Status Date should be selected on Rebate tab");
		rsFieldMessageList.add("Rebate Schedule Type should be selected on Rebate tab");
		rsFieldMessageList.add("Rebate Program Type should be selected on Rebate tab");
		rsFieldMessageList.add("Rebate Schedule Category should be selected on Rebate tab");
		rsFieldMessageList.add("RS Start Date should be selected on Rebate tab");
		rsFieldMessageList.add("Deduction Inclusion should be selected on Rebate tab");
		rsFieldMessageList.add("Rebate Frequency should be selected on Rebate tab");
		rsFieldMessageList.add("Payment Frequency should be selected on Rebate tab");
		rsFieldMessageList.add("Payment Method should be selected on Rebate tab");
		rsFieldMessageList.add("Calculation Type should be selected on Rebate tab");
		rsFieldMessageList.add("Calculation Level should be selected on Rebate tab");

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId,
				getValidateActionConfig(processDataBean, rsFieldList, rsFieldMessageList));
		if (processDataBean.isSuccess()) {
			collectFieldValues(processDataBean.getRebateFieldList(), componentId,
					processDataBean.getRebateInfoBean().getProperties());
			processDataBean.getRebateInfoBean().getProperties().set(43, GtnUIFrameworkGlobalUI
					.getVaadinComponentData(processDataBean.getRebateFieldList().get(33)).getCustomDataList().get(0));
			processDataBean.getRebateInfoBean().getProperties().set(44, GtnUIFrameworkGlobalUI
					.getVaadinComponentData(processDataBean.getRebateFieldList().get(37)).getCustomDataList().get(0));
		}
		return processDataBean.isSuccess();
	}

	private void collectFieldValues(List<String> fieldIdList, String componentId, List<Object> valueList)
			throws GtnFrameworkValidationFailedException {
		for (int i = 0; i < fieldIdList.size(); i++) {
			String fieldId = fieldIdList.get(i);
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldId,
					componentId);
			if (isValidValue(baseComponent)) {
				Object value = baseComponent.validateAndGetValue();
				valueList.set(i, value);
			}
		}
	}

	private boolean isValidValue(GtnUIFrameworkBaseComponent baseComponent) {
		boolean ret = true;
		GtnUIFrameworkValidationConfig valConfig = baseComponent.getComponentData().getCurrentComponentConfig()
				.getGtnUIFrameworkValidationConfig();
		if (valConfig != null && (valConfig.isAttachRegxValidatior() || valConfig.isAttachLengthValidatior())) {
			ret = baseComponent.isValidFieldValue();
		}
		return ret;
	}

	public GtnUIFrameWorkActionConfig getValidateActionConfig(GtnWsContractDashboardSessionBean processDataBean,
			List<String> fieldList, List<String> fieldMessageList) {

		processDataBean.setSuccess(true);
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(fieldList);

		GtnUIFrameWorkActionConfig failActionConfig = new GtnUIFrameWorkActionConfig();
		failActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		failActionConfig.addActionParameter(GtnFrameworkErrorLabelAction.class.getName());
		failActionConfig.addActionParameter(processDataBean.getErrorDisplayId());

		failActionConfig.addActionParameter(fieldList);
		failActionConfig.addActionParameter(fieldMessageList);

		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);
		validationActionConfig.addActionParameter(Arrays.asList(failActionConfig));
		return validationActionConfig;
	}
}
