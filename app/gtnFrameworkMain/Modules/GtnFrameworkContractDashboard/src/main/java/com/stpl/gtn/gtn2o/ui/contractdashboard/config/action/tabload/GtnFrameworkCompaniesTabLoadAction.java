package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload;

import java.util.Arrays;
import java.util.List;

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

public class GtnFrameworkCompaniesTabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCompaniesTabLoadAction.class);

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
		if (!processDataBean.isCompaniesLoaded()) {
			processDataBean.setNeedOperation(false);
			processDataBean.setCompaniesLoaded(true);
			processDataBean.setCompaniesTableLoad(true);
			processDataBean.setCompaniesLevelId(parameters.get(1).toString());
			processDataBean.setCompaniesTableId(parameters.get(2).toString());
			processDataBean.setCompaniesFieldList((List<String>) parameters.get(3));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getCompaniesTableId())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getCompaniesTableId() + "View")
					.setVisible(processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString() + "View")
					.setVisible(processDataBean.isViewMode());
			loadFieldDetails(processDataBean, componentId);
			if (processDataBean.isViewMode()) {
				disableFields(processDataBean.getCompaniesFieldList());
			}
			processDataBean.setNeedOperation(true);
		}

		GtnUIFrameworkBaseComponent levelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(processDataBean.getCompaniesLevelId());
		String levelValue = levelBaseComponent.getStringFromField();
		if (processDataBean.isCompaniesTableLoad() && GtnWsContractDashboardContants.DETAIL.equals(levelValue)) {
			processDataBean.setCompaniesTableLoad(false);
			loadTable(componentId, processDataBean.getCompaniesTableId());
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(processDataBean.getCompaniesTableId());
			Object[] columns = tableBaseComponent.getTableColumns();
			tableBaseComponent.setFilterFieldVisible(columns[0], false);
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

	private void loadTable(String componentId, Object tableId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig companiesTabTableLoadConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		companiesTabTableLoadConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		companiesTabTableLoadConfig.addActionParameter(tableId);
		companiesTabTableLoadConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT);
		companiesTabTableLoadConfig
				.setFieldValues(Arrays.asList(GtnFrameworkContractDashboardContants.CDPROCESSVIEW_CTRECORD,
						GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, companiesTabTableLoadConfig);
	}

	private void loadFieldDetails(GtnWsContractDashboardSessionBean processDataBean, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			processDataBean.setCompanyInfoBean(new GtnWsRecordBean());
			processDataBean.getCompanyInfoBean()
					.setRecordHeader(Arrays.asList(processDataBean.getCompaniesFieldList().toArray()));
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsContractDashboardRequest companiesTabCdRequest = new GtnWsContractDashboardRequest();
			generalWSRequest.setUserId(processDataBean.getProcessBean().getUserId());
			generalWSRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsContractDashboardRequest(companiesTabCdRequest);
			request.setGtnWsGeneralRequest(generalWSRequest);
			companiesTabCdRequest.setContractId(processDataBean.getProcessBean().getContractId());
			companiesTabCdRequest.setSessionDate(processDataBean.getProcessBean().getSessionDate());
			companiesTabCdRequest.setCfpContractId(processDataBean.getProcessBean().getCfpContractId());
			companiesTabCdRequest.setIfpContractId(processDataBean.getProcessBean().getIfpContractId());
			companiesTabCdRequest.setPsContractId(processDataBean.getProcessBean().getPsContractId());
			companiesTabCdRequest.setRsContractId(processDataBean.getProcessBean().getRsContractId());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.GET_COMPANY_INFO_FIELD_DATA,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			processDataBean.getCompanyInfoBean()
					.setProperties(response.getGtnWsContractDashboardResponse().getCompanyInfoBean().getProperties());
			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(processDataBean.getCompaniesFieldList());
			resetActionConfig.addActionParameter(processDataBean.getCompanyInfoBean().getProperties());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
			GtnUIFrameworkGlobalUI.getVaadinComponentData(processDataBean.getCompaniesFieldList().get(10))
					.setCustomDataList(Arrays.asList(processDataBean.getCompanyInfoBean().getProperties().get(17)));
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkCompaniesTabLoadAction", e);
		}
	}
}
