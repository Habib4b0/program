package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.crud;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkInfoAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;

public class GtnFrameworkWorkflowPublicPrivateViewSaveAction

		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkWorkflowPublicPrivateViewSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCommonWorkflowRequest forecastRequest = new GtnWsCommonWorkflowRequest();
		GtnWsWorkflowInboxBean projMasterBean = new GtnWsWorkflowInboxBean();
		String combocomponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS)
				.getCaptionFromComboBox();
		try {
			projMasterBean.setWorkflowId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId)
					.getStringFromField());
			projMasterBean.setWorkflowName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId)
					.getStringFromField());
			projMasterBean.setWorkflowDesc(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId)
					.getStringFromField());
			projMasterBean.setCreatedFrom(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(3), componentId)
					.getDateFromDateField());

			projMasterBean.setCreatedTo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(4), componentId)
					.getDateFromDateField());
			projMasterBean.setApprovedFrom(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(5), componentId)
					.getDateFromDateField());
			projMasterBean.setApprovedTo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(6), componentId)
					.getDateFromDateField());

			projMasterBean.setContractId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(7), componentId)
					.getStringFromField());

			projMasterBean.setContractNo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(8), componentId)
					.getStringFromField());

			projMasterBean.setCompanyNo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(9), componentId)
					.getStringFromField());

			projMasterBean.setCompanyName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(10), componentId)
					.getStringFromField());

			projMasterBean.setBusinessUnitId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(11), componentId)
					.getStringFromField());

			projMasterBean.setBusinessUnitNo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(12), componentId)
					.getStringFromField());

			projMasterBean.setBusinessUnitName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(13), componentId)
					.getStringFromField());

			getComponentsOne(componentId, gtnUIFrameWorkActionConfig, projMasterBean);

			getComponentsTwo(componentId, gtnUIFrameWorkActionConfig, projMasterBean);

			if (componentId.contains(GtnFrameworkWorkflowInboxClassConstants.UPDATE)) {

				projMasterBean.setViewType(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(47), componentId)
						.getStringFromField());
				String tablecomponentid = "private" + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE;
				if (projMasterBean.getViewType().equals(GtnFrameworkWorkflowInboxClassConstants.PUBLIC)) {
					tablecomponentid = "public" + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE;
				}

				GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tablecomponentid)
						.getValueFromPagedDataTable();

				int workflowViewId;

				if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM)) {
					workflowViewId = Integer.parseInt((String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(6))));
				} else {
					workflowViewId = Integer.parseInt((String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(11))));
				}

				projMasterBean.setWorkflowSid(workflowViewId);

			}

			gtnLogger.info("Configuration Completed");
			request.setGtnWSCommonWorkflowRequest(forecastRequest);
			request.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsWorkFlowConstants.GTN_WS_WORKFLOW_VIEW_SAVE_SERVICE
							+ GtnFrameworkCommonStringConstants.WORKFLOW_MODULE_NAME,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			boolean saveFalg = response.getGtnWsGeneralResponse().isSucess();
			String msg = "";

			if (saveFalg) {
				msg += GtnFrameworkWorkflowInboxClassConstants.YOUHAVESUCESSFULLY;

				if (GtnFrameworkWorkflowInboxClassConstants.UPDATE_LOWERCASE
						.equals(actionParametersList.get(1).toString())) {
					msg += GtnFrameworkWorkflowInboxClassConstants.UPDATED;
				} else {
					msg += GtnFrameworkWorkflowInboxClassConstants.ADDED;
				}
				msg += projMasterBean.getViewType().toLowerCase() + " view ( " + projMasterBean.getViewName() + " )";

				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkInfoAction infoAction = new GtnUIFrameWorkInfoAction();

				Object viewaddsucess = GtnFrameworkWorkflowInboxClassConstants.VIEW_ADDED_SUCESSFULLY;
				alertActionConfig.setActionParameterList(Arrays.asList(viewaddsucess, msg));
				infoAction.doAction(componentId, alertActionConfig);
			}

		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			throw new GtnFrameworkGeneralException(
					"Error in GtnFrameworkWorkflowPublicPrivateViewSaveAction class doAction method", ex);
		}
	}

	private void getComponentsOne(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnWsWorkflowInboxBean projMasterBean) throws GtnFrameworkValidationFailedException {
		projMasterBean.setContractName(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(14), componentId)
				.getStringFromField());

		projMasterBean.setItemNo(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(15), componentId)
				.getStringFromField());

		projMasterBean.setItemName(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(16), componentId)
				.getStringFromField());

		projMasterBean.setForecastdeductionValue(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(17), componentId)
				.getIntegerFromField());

		projMasterBean.setForecastdeductionLevel(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(18), componentId)
				.getIntegerFromField());

		projMasterBean.setContractType(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(19), componentId)
				.getIntegerFromField());

		projMasterBean.setCompanyID(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(20), componentId)
				.getStringFromField());

		projMasterBean.setItemId(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(21), componentId)
				.getStringFromField());

		projMasterBean.setBusinessUnitIdReturns(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(22), componentId)
				.getStringFromField());

		projMasterBean.setBusinessUnitNoReturns(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(23), componentId)
				.getStringFromField());

		projMasterBean.setBusinessUnitNameReturns(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(24), componentId)
				.getStringFromField());

		projMasterBean.setItemNoReturns(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(25), componentId)
				.getStringFromField());

		projMasterBean.setItemNameReturns(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(26), componentId)
				.getStringFromField());
	}

	private void getComponentsTwo(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnWsWorkflowInboxBean projMasterBean) throws GtnFrameworkValidationFailedException {
		projMasterBean.setItemIdReturns(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(27), componentId)
				.getStringFromField());

		projMasterBean.setCompanyARM(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(28), componentId)
				.getIntegerFromField());

		projMasterBean.setBusinessUnitARM(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(29), componentId)
				.getIntegerFromField());

		projMasterBean.setWorkflowStatusArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(30), componentId)
				.getIntegerFromField());

		projMasterBean.setAdjustmentType(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(31), componentId)
				.getIntegerFromField());

		projMasterBean.setContractIdArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(32), componentId)
				.getStringFromField());

		projMasterBean.setContractNoArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(33), componentId)
				.getStringFromField());

		projMasterBean.setCustomerNoArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(34), componentId)
				.getStringFromField());

		projMasterBean.setCustomerNameArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(35), componentId)
				.getStringFromField());

		projMasterBean.setBrandIdArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(36), componentId)
				.getStringFromField());

		projMasterBean.setContractNameArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(37), componentId)
				.getStringFromField());

		projMasterBean.setItemNoArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(38), componentId)
				.getStringFromField());

		projMasterBean.setItemNameArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(39), componentId)
				.getStringFromField());

		projMasterBean.setBrandNameArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(40), componentId)
				.getStringFromField());

		projMasterBean.setGlDateArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(41), componentId)
				.getDateFromDateField());

		projMasterBean.setDeductionLevelArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(42), componentId)
				.getIntegerFromField());

		projMasterBean.setDeductionValueArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(43), componentId)
				.getIntegerFromField());

		projMasterBean.setDeductionNoArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(44), componentId)
				.getStringFromField());

		projMasterBean.setDeductionNameArm(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(45), componentId)
				.getStringFromField());

		projMasterBean.setViewName(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(46), componentId)
				.getStringFromField());
		projMasterBean.setViewType(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(47), componentId)
				.getStringFromField());

		projMasterBean.setBusinessProcess(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(48), componentId)
				.getStringFromField());
		projMasterBean.setCreatedByPrivate(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(49), componentId)
				.getStringFromField());
		projMasterBean.setApprovedBy(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(50), componentId)
				.getStringFromField());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
