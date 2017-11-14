package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkWrapLoadTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkRebateTabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkRebateTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (!processDataBean.isRebateLoaded()) {
			processDataBean.setNeedOperation(false);
			processDataBean.setRebateLoaded(true);
			processDataBean.setRebateDetailsTableLoad(true);
			processDataBean.setRebateDetailsColumnChange(!processDataBean.isViewMode());
			processDataBean.setRebateLevelId(parameters.get(1).toString());
			processDataBean.setRebateDetailsTableId(parameters.get(3).toString());
			processDataBean.setRebateSelectedRuleTableId(parameters.get(4).toString());
			processDataBean.setRebateCalculationTypeId(parameters.get(5).toString());
			processDataBean.setRebateFieldList((List<String>) parameters.get(6));
			processDataBean.setRebatePopulateFieldList((List<String>) parameters.get(7));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(9).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getRebateDetailsTableId())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getRebateDetailsTableId() + "View")
					.setVisible(processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(10).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(10).toString() + "View")
					.setVisible(processDataBean.isViewMode());
			loadFieldDetails(processDataBean, componentId);
			if (processDataBean.isViewMode()) {
				disableFields(processDataBean.getRebateFieldList());
			}
			processDataBean.setNeedOperation(true);
		}
		GtnUIFrameworkBaseComponent levelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(processDataBean.getRebateLevelId());
		String levelValue = levelBaseComponent.getStringFromField();

		if (GtnWsContractDashboardContants.DETAIL.equals(levelValue)) {
			if (processDataBean.isRebateDetailsColumnChange()) {
				GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(processDataBean.getRebateDetailsTableId());
				tableBaseComponent.setTableColumns(GtnFrameworkContractDashboardContants.getRebateDetailsColumn());
				tableBaseComponent
						.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getRebateDetailsColumnHeader());
			}
			if (processDataBean.isRebateDetailsTableLoad()) {
				processDataBean.setRebateDetailsTableLoad(false);
				loadTable(componentId, processDataBean.getRebateDetailsTableId());
				GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(processDataBean.getRebateDetailsTableId());
				Object[] columns = tableBaseComponent.getTableColumns();
				tableBaseComponent.setFilterFieldVisible(columns[0], false);
			}
			if (processDataBean.isRebateDetailsColumnChange()) {
				processDataBean.setRebateDetailsColumnChange(false);
				modifyTableColumns(processDataBean);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void disableFields(List<String> fieldList) {
		for (String field : fieldList) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(field).setEnable(false);
		}
	}

	private void loadTable(String componentId, String tableId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(tableId);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(GtnFrameworkContractDashboardContants.CDPROCESSVIEW_RTRECORD,
				GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, tableLoadActionConfig);
	}

	private void modifyTableColumns(GtnWsContractDashboardSessionBean processDataBean)
			throws GtnFrameworkGeneralException {
		String fieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getRebateCalculationTypeId())
				.getCaptionFromComboBox();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(processDataBean.getRebateDetailsTableId());
		fieldValue = fieldValue.replace(" ", "").toLowerCase(Locale.ENGLISH).trim();
		String newField;
		switch (fieldValue) {
		case "formula":
			newField = processDataBean.getRebatePopulateFieldList().get(1);
			tableBaseComponent.setTableColumns(GtnFrameworkContractDashboardContants.getRebateDetailsColumnFormula());
			break;
		case "rebateplan":
			newField = processDataBean.getRebatePopulateFieldList().get(2);
			tableBaseComponent
					.setTableColumns(GtnFrameworkContractDashboardContants.getRebateDetailsColumnRebatePlan());
			break;
		case "deductioncalendar":
			newField = processDataBean.getRebatePopulateFieldList().get(3);
			tableBaseComponent
					.setTableColumns(GtnFrameworkContractDashboardContants.getRebateDetailsColumnDeductionCalendar());
			break;
		default:
			newField = processDataBean.getRebatePopulateFieldList().get(0);
			tableBaseComponent.setTableColumns(GtnFrameworkContractDashboardContants.getRebateDetailsColumnDefault());
		}
		setPopulateFieldInVisible(processDataBean.getRebatePopulateFieldList(), newField);
		GtnUIFrameworkActionExecutor.executeActionList(newField, GtnUIFrameworkGlobalUI.getVaadinComponentData(newField)
				.getCurrentComponentConfig().getGtnUIFrameWorkActionConfigList());
	}

	private void setPopulateFieldInVisible(List<String> fields, String newField) {
		for (String field : fields) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(field).setVisible(field.equals(newField));
		}
	}

	private void loadFieldDetails(GtnWsContractDashboardSessionBean processDataBean, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			processDataBean.setRebateInfoBean(new GtnWsRecordBean());
			processDataBean.getRebateInfoBean()
					.setRecordHeader(Arrays.asList(processDataBean.getRebateFieldList().toArray()));
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsContractDashboardRequest rebateTabCdRequest = new GtnWsContractDashboardRequest();
			generalWSRequest.setUserId(processDataBean.getProcessBean().getUserId());
			generalWSRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsContractDashboardRequest(rebateTabCdRequest);
			request.setGtnWsGeneralRequest(generalWSRequest);
			rebateTabCdRequest.setContractId(processDataBean.getProcessBean().getContractId());
			rebateTabCdRequest.setSessionDate(processDataBean.getProcessBean().getSessionDate());
			rebateTabCdRequest.setCfpContractId(processDataBean.getProcessBean().getCfpContractId());
			rebateTabCdRequest.setIfpContractId(processDataBean.getProcessBean().getIfpContractId());
			rebateTabCdRequest.setPsContractId(processDataBean.getProcessBean().getPsContractId());
			rebateTabCdRequest.setRsContractId(processDataBean.getProcessBean().getRsContractId());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.GET_REBATE_INFO_FIELD_DATA,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			processDataBean.getRebateInfoBean()
					.setProperties(response.getGtnWsContractDashboardResponse().getRebateInfoBean().getProperties());

			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(processDataBean.getRebateFieldList());
			resetActionConfig.addActionParameter(processDataBean.getRebateInfoBean().getProperties());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
			GtnUIFrameworkGlobalUI.getVaadinComponentData(processDataBean.getRebateFieldList().get(33))
					.setCustomDataList(Arrays.asList(processDataBean.getRebateInfoBean().getProperties().get(43)));
			GtnUIFrameworkGlobalUI.getVaadinComponentData(processDataBean.getRebateFieldList().get(37))
					.setCustomDataList(Arrays.asList(processDataBean.getRebateInfoBean().getProperties().get(44)));
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkRebateTabLoadAction", e);
		}
	}
}
