package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.duallistboxaction;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;

public class GtnFrameworkItemAdditionTableRecordMoveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String moveActionType = parameters.get(2).toString();
		boolean moveAllRight = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_ALL_RIGHT.equals(moveActionType);
		boolean moveRight = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_RIGHT.equals(moveActionType);
		boolean moveAllLeft = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_ALL_LEFT.equals(moveActionType);
		boolean moveLeft = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_LEFT.equals(moveActionType);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		GtnUIFrameworkBaseComponent leftTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		cdRequest.setContractId(processDataBean.getProcessBean().getContractId());
		cdRequest.setCfpContractId(processDataBean.getProcessBean().getCfpContractId());
		cdRequest.setIfpContractId(processDataBean.getProcessBean().getIfpContractId());
		cdRequest.setPsContractId(processDataBean.getProcessBean().getPsContractId());
		cdRequest.setRsContractId(processDataBean.getProcessBean().getRsContractId());
		processDataBean.setItemsTableLoad(true);
		processDataBean.setItemAddTableLoad(true);
		processDataBean.setPriceProtectionTableLoad(true);
		processDataBean.setPricingDetailsTableLoad(true);
		processDataBean.setRebateDetailsTableLoad(true);
		generalWSRequest.setUserId(processDataBean.getProcessBean().getUserId());
		generalWSRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
		request.setGtnWsContractDashboardRequest(cdRequest);
		if (moveRight || moveLeft) {
			Object dto = leftTableBaseComponent.getValueFromComponent();
			cdRequest.setTableBean((GtnWsRecordBean) dto);
		} else {
			GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
			sRequest.setGtnWebServiceSearchCriteriaList(leftTableBaseComponent.getPagedTableSearchCriteriaList());
			request.setGtnWsSearchRequest(sRequest);
		}

		request.setGtnWsGeneralRequest(generalWSRequest);
		if (moveLeft || moveAllLeft
				|| (moveAllRight || moveRight && leftTableBaseComponent.getExtPagedTable().size() != 0)) {
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE + moveActionType, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
