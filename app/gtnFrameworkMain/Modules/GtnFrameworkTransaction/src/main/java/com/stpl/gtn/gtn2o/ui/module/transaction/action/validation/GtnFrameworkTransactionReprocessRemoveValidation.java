package com.stpl.gtn.gtn2o.ui.module.transaction.action.validation;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GtnFrameworkTransactionReprocessRemoveValidation
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(actionParametersList.get(2))).getComponentData()
				.getSharedPopupData();

		if (!checkBean.isCheckAll() && checkBean.getCheckedIdSet().isEmpty()) {
			Object header = String.valueOf(actionParametersList.get(1));
			String messageBody = "Please check mark at least one row from the Results list view";
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			alertActionConfig.setActionParameterList(Arrays.asList(header, messageBody));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Skip Action");
		}

		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Already Running ");
		alertParamsList.add("Already Running Process");
		alertAction.setActionParameterList(alertParamsList);

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
		if ((boolean) actionParametersList.get(3)) {
			gtnWsTransactionRequest
					.setTableName(getProcessName().get(String.valueOf(String.valueOf(actionParametersList.get(4)))));
		} else {
			String valueFromDdlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("intefaceName")
					.getCaptionFromComboBox();
			gtnWsTransactionRequest.setTableName(String.valueOf(getProcessName().get(valueFromDdlb)));
		}

		request.setGtnWsTransactionRequest(gtnWsTransactionRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
						+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if ("Y".equals(response.getOutBountData()[0])) {
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
			throw new GtnFrameworkSkipActionException("Skip Action");
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private Map<String, String> getProcessName() {
		Map<String, String> processNameMap = new HashMap<>();
		processNameMap.put("Accrual Details", "ACCRUAL_INBOUND_INTERFACE");
		processNameMap.put("Average Shelf Life", "AVERAGE_SHELF_LIFE_INTERFACE");
		processNameMap.put("Contract Header", "CONTRACT_HEADER_INTERFACE");
		processNameMap.put("CPI", "CPI_INTERFACE");
		processNameMap.put("Demand", "DEMAND_ACTUAL_INTERFACE");
		processNameMap.put("GL Balance", "GL_BALANCE_INTERFACE");
		processNameMap.put("GL Cost Center", "GL_COST_CENTER_INTERFACE");
		processNameMap.put("Global Files Company Family Plan", "COMPANY_FAMILY_PLAN_INTERFACE");
		processNameMap.put("Global Files Company Identifier", "COMPANY_IDENTIFIER_INTERFACE");
		processNameMap.put("Global Files Company Master", "COMPANY_MASTER_INTERFACE");
		processNameMap.put("Global Files Company Parent", "COMPANY_PARENT_DETAILS_INTERFACE");
		processNameMap.put("Global Files Company Trade Class", "COMPANY_TRADE_CLASS_INTERFACE");
		processNameMap.put("Global Files Item Family Plan", "ITEM_FAMILY_PLAN_INTERFACE");
		processNameMap.put("Global Files Item Identifier", "ITEM_IDENTIFIER_INTERFACE");
		processNameMap.put("Global Files Item Master", "ITEM_MASTER_INTERFACE");
		processNameMap.put("Global Files Item Pricing", "ITEM_PRICING_INTERFACE");
		processNameMap.put("Global Files Price Schedule", "PRICE_SCHEDULE_INTERFACE");
		processNameMap.put("Global Files Rebate Plan", "REBATE_PLAN_INTERFACE");
		processNameMap.put("Global Files Rebate Schedule", "REBATE_SCHEDULE_INTERFACE");
		processNameMap.put("GTS Actual", "CUSTOMER_GTS_ACTUAL_INTERFACE");
		processNameMap.put("GTS Forecast", "CUSTOMER_GTS_FORECAST_INTERFACE");
		processNameMap.put("Inventory Withdrawal", "INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE");
		processNameMap.put("Lot Master", "LOT_MASTER_INTERFACE");
		processNameMap.put("Payments", "ACTUAL_MASTER_INTERFACE");
		processNameMap.put("Returns", "RETURNS_INTERFACE");
		processNameMap.put("Sales Actual", "SALES_MASTER_INTERFACE");
		processNameMap.put("Sales Forecast", "FORECAST_SALES_INTERFACE");
		processNameMap.put("Return Rate Forecast", "RETURN_RATE_FORECAST_INTERFACE");
		processNameMap.put("Item Uom", "ITEM_UOM_INTERFACE");
		processNameMap.put("VwCffOutboundMaster", "CFF_OUTBOUND_INTERFACE");
		return processNameMap;
	}
}