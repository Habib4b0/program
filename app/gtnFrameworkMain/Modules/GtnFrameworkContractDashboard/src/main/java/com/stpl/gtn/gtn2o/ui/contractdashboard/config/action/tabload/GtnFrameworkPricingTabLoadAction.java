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

public class GtnFrameworkPricingTabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkPricingTabLoadAction.class);

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
		if (!processDataBean.isPricingLoaded()) {
			processDataBean.setNeedOperation(false);
			processDataBean.setPricingLoaded(true);
			processDataBean.setPricingDetailsTableLoad(true);
			processDataBean.setPriceProtectionTableLoad(true);
			processDataBean.setPricingLevelId(parameters.get(1).toString());
			processDataBean.setPricingDetailsTableId(parameters.get(2).toString());
			processDataBean.setPriceProtectionTableId(parameters.get(3).toString());
			processDataBean.setPricingFieldList((List<String>) parameters.get(4));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getPricingDetailsTableId())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getPricingDetailsTableId() + "View")
					.setVisible(processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getPriceProtectionTableId())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(processDataBean.getPriceProtectionTableId() + "View")
					.setVisible(processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString() + "View")
					.setVisible(processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString() + "View")
					.setVisible(processDataBean.isViewMode());
			loadFieldDetails(processDataBean, componentId);
			if (processDataBean.isViewMode()) {
				disableFields(processDataBean.getPricingFieldList());
			}
			processDataBean.setNeedOperation(true);
		}
		GtnUIFrameworkBaseComponent levelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(processDataBean.getPricingLevelId());
		String levelValue = levelBaseComponent.getStringFromField();
		if (processDataBean.isPricingDetailsTableLoad() && GtnWsContractDashboardContants.DETAIL.equals(levelValue)) {
			processDataBean.setPricingDetailsTableLoad(false);
			loadTable(componentId, processDataBean.getPricingDetailsTableId());
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(processDataBean.getPricingDetailsTableId());
			Object[] columns = tableBaseComponent.getTableColumns();
			tableBaseComponent.setFilterFieldVisible(columns[0], false);
			return;
		}
		if (processDataBean.isPriceProtectionTableLoad()
				&& GtnWsContractDashboardContants.PRICE_PROTECTION.equals(levelValue)) {
			processDataBean.setPriceProtectionTableLoad(false);
			loadTable(componentId, processDataBean.getPriceProtectionTableId());
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(processDataBean.getPriceProtectionTableId());
			Object[] columns = tableBaseComponent.getTableColumns();
			tableBaseComponent.setFilterFieldVisible(columns[0], false);
			tableBaseComponent.setFilterFieldVisible(columns[13], false);
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
		GtnUIFrameWorkActionConfig pricingTableLoadActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingTableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		pricingTableLoadActionConfig.addActionParameter(tableId);
		pricingTableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT);
		pricingTableLoadActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkContractDashboardContants.CDPROCESSVIEW_PTRECORD,
						GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, pricingTableLoadActionConfig);
	}

	private void loadFieldDetails(GtnWsContractDashboardSessionBean processDataBean, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			processDataBean.setPriceInfoBean(new GtnWsRecordBean());
			processDataBean.getPriceInfoBean()
					.setRecordHeader(Arrays.asList(processDataBean.getPricingFieldList().toArray()));
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsContractDashboardRequest pricingTabCdRequest = new GtnWsContractDashboardRequest();
			generalWSRequest.setUserId(processDataBean.getProcessBean().getUserId());
			generalWSRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsContractDashboardRequest(pricingTabCdRequest);
			request.setGtnWsGeneralRequest(generalWSRequest);
			pricingTabCdRequest.setContractId(processDataBean.getProcessBean().getContractId());
			pricingTabCdRequest.setSessionDate(processDataBean.getProcessBean().getSessionDate());
			pricingTabCdRequest.setCfpContractId(processDataBean.getProcessBean().getCfpContractId());
			pricingTabCdRequest.setIfpContractId(processDataBean.getProcessBean().getIfpContractId());
			pricingTabCdRequest.setPsContractId(processDataBean.getProcessBean().getPsContractId());
			pricingTabCdRequest.setRsContractId(processDataBean.getProcessBean().getRsContractId());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.GET_PRICING_INFO_FIELD_DATA,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			processDataBean.getPriceInfoBean()
					.setProperties(response.getGtnWsContractDashboardResponse().getPriceInfoBean().getProperties());

			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(processDataBean.getPricingFieldList());
			resetActionConfig.addActionParameter(processDataBean.getPriceInfoBean().getProperties());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkPricingTabLoadAction", e);
		}
	}
}
