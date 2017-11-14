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

public class GtnFrameworkItemsTabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemsTabLoadAction.class);

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
		GtnWsContractDashboardSessionBean itemsTabProcessDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (!itemsTabProcessDataBean.isItemsLoaded()) {
			itemsTabProcessDataBean.setNeedOperation(false);
			itemsTabProcessDataBean.setItemsLoaded(true);
			itemsTabProcessDataBean.setItemsTableLoad(true);
			itemsTabProcessDataBean.setItemsLevelId(parameters.get(1).toString());
			itemsTabProcessDataBean.setItemsTableId(parameters.get(2).toString());
			itemsTabProcessDataBean.setItemsFieldList((List<String>) parameters.get(3));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
					.setVisible(!itemsTabProcessDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
					.setVisible(!itemsTabProcessDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(itemsTabProcessDataBean.getItemsTableId())
					.setVisible(!itemsTabProcessDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(itemsTabProcessDataBean.getItemsTableId() + "View")
					.setVisible(itemsTabProcessDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
					.setVisible(!itemsTabProcessDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString() + "View")
					.setVisible(itemsTabProcessDataBean.isViewMode());
			loadFieldDetails(itemsTabProcessDataBean, componentId);
			if (itemsTabProcessDataBean.isViewMode()) {
				disableFields(itemsTabProcessDataBean.getItemsFieldList());
			}
			itemsTabProcessDataBean.setNeedOperation(true);
		}
		GtnUIFrameworkBaseComponent levelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(itemsTabProcessDataBean.getItemsLevelId());
		String levelValue = levelBaseComponent.getStringFromField();
		if (itemsTabProcessDataBean.isItemsTableLoad() && GtnWsContractDashboardContants.DETAIL.equals(levelValue)) {
			itemsTabProcessDataBean.setItemsTableLoad(false);
			loadTable(componentId, itemsTabProcessDataBean.getItemsTableId());
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(itemsTabProcessDataBean.getItemsTableId());
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
		GtnUIFrameWorkActionConfig itemsTabLoadActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemsTabLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		itemsTabLoadActionConfig.addActionParameter(tableId);
		itemsTabLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT);
		itemsTabLoadActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkContractDashboardContants.CDPROCESSVIEW_ITRECORD,
						GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, itemsTabLoadActionConfig);
	}

	private void loadFieldDetails(GtnWsContractDashboardSessionBean processDataBean, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			processDataBean.setItemInfoBean(new GtnWsRecordBean());
			processDataBean.getItemInfoBean()
					.setRecordHeader(Arrays.asList(processDataBean.getItemsFieldList().toArray()));
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnWsGeneralRequest itemsTabGeneralWSRequest = new GtnWsGeneralRequest();
			GtnWsContractDashboardRequest itemsTabCdRequest = new GtnWsContractDashboardRequest();
			itemsTabGeneralWSRequest.setUserId(processDataBean.getProcessBean().getUserId());
			itemsTabGeneralWSRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsContractDashboardRequest(itemsTabCdRequest);
			request.setGtnWsGeneralRequest(itemsTabGeneralWSRequest);
			itemsTabCdRequest.setContractId(processDataBean.getProcessBean().getContractId());
			itemsTabCdRequest.setSessionDate(processDataBean.getProcessBean().getSessionDate());
			itemsTabCdRequest.setCfpContractId(processDataBean.getProcessBean().getCfpContractId());
			itemsTabCdRequest.setIfpContractId(processDataBean.getProcessBean().getIfpContractId());
			itemsTabCdRequest.setPsContractId(processDataBean.getProcessBean().getPsContractId());
			itemsTabCdRequest.setRsContractId(processDataBean.getProcessBean().getRsContractId());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.GET_ITEM_INFO_FIELD_DATA,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			processDataBean.getItemInfoBean()
					.setProperties(response.getGtnWsContractDashboardResponse().getItemInfoBean().getProperties());

			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(processDataBean.getItemsFieldList());
			resetActionConfig.addActionParameter(processDataBean.getItemInfoBean().getProperties());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkItemsTabLoadAction", e);
		}
	}
}
