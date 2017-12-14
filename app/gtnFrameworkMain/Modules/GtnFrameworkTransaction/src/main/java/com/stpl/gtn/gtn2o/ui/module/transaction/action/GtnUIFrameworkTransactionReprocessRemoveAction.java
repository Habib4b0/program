package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;

public class GtnUIFrameworkTransactionReprocessRemoveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkTransactionReprocessRemoveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String tableName = GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME)
				.toString();
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParametersList.get(2).toString()).getComponentData().getSharedPopupData();

		List<String> checkedIdSet = new ArrayList<>(checkBean.getCheckedIdSet());
		List<String> unCheckedIDSet = new ArrayList<>(checkBean.getUnCheckedIdSet());
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
		gtnWsTransactionRequest.setOutBoundTableName(tableName);
		tableName = getTableNameModuleWise(tableName);
		gtnWsTransactionRequest.setTableName(tableName);
		if (GtnTransactionUIConstants.REPROCESS.equals(actionParametersList.get(1).toString())) {
			gtnWsTransactionRequest.setReprocessFlag(true);
		}
		gtnWsTransactionRequest.setStagingTableName(tableName.replace("Ivld", "Stag"));

		if (checkBean.isCheckAll()) {
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParametersList.get(3).toString()).getLogicFromPagedDataTable();
			gtnWsTransactionRequest.setSearchCriteria(logic.getCurrentSearchCriteria());
			gtnWsTransactionRequest.setUncheckedIds(unCheckedIDSet);
		} else {
			gtnWsTransactionRequest.setReprocessIds(checkedIdSet);

		}
		gtnWsTransactionRequest.setOutBoundModule((boolean)actionParametersList.get(5));
		gtnWsTransactionRequest.setStagInsertColumns((Object[])actionParametersList.get(6));
		gtnWsTransactionRequest.setStagUpdateColumns((Object[])actionParametersList.get(7));
		gtnWsTransactionRequest.setStagUpdateColumnsValues((Object[])actionParametersList.get(8));
		request.setGtnWsTransactionRequest(gtnWsTransactionRequest);
		String wsReprocessURL = actionParametersList.get(4).toString();
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(wsReprocessURL, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		gtnLogger.debug("Reprocess----" + response.getGtnWsGeneralResponse().isSucess());
	}

	private String getTableNameModuleWise(String tableName) throws GtnFrameworkValidationFailedException {
		String invalidTableName;
		switch (tableName) {
		case "VwIvldInventoryWdActualProjMas":
			invalidTableName = getInventoryTableName("inventoryType", "inventoryLevel");
			break;

		case "VwIvldAdjDemandForeActual":
			invalidTableName = getDemandTableName("adjustedDemandForecastId", "forecastTypeSid");
			break;

		case "VwIvldDemandForecastActual":
			invalidTableName = getDemandTableName("adjustedDemandForecastId", "forecastTypeSid");
			break;

		case "VwIvldActualsMaster":
			invalidTableName = "IvldActualMaster";
			break;
		case "VwCffOutboundMaster":
			invalidTableName = "StCffOutboundMaster";
			break;
		default:
			invalidTableName = tableName;
			break;
		}
		return invalidTableName;
	}

	private String getDemandTableName(String componentId1, String componentId2)
			throws GtnFrameworkValidationFailedException {
		String invalidTableName = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		String captionOne = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId1).getCaptionFromComboBox();
		String captionTwo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId2).getCaptionFromComboBox();
		if (GtnTransactionUIConstants.ADJUSTED_DEMAND.equals(captionOne)
				&& GtnTransactionUIConstants.ACTUALS.equals(captionTwo)) {
			invalidTableName = "IvldAdjustedDemandActual";
		} else if (GtnTransactionUIConstants.ADJUSTED_DEMAND.equals(captionOne)
				&& GtnTransactionUIConstants.PROJECTIONS.equals(captionTwo)) {
			invalidTableName = "IvldAdjustedDemandForecast";
		} else if (GtnTransactionUIConstants.DEMAND.equals(captionOne)
				&& GtnTransactionUIConstants.ACTUALS.equals(captionTwo)) {
			invalidTableName = "IvldDemandActual";
		} else if (GtnTransactionUIConstants.DEMAND.equals(captionOne)
				&& GtnTransactionUIConstants.PROJECTIONS.equals(captionTwo)) {
			invalidTableName = "IvldDemandForecast";
		}

		return invalidTableName;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

	private String getInventoryTableName(String inventoryType, String inventoryLevel)
			throws GtnFrameworkValidationFailedException {
		String invalidTableName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String captionOne = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inventoryType).getCaptionFromComboBox();
		String captionTwo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inventoryLevel).getCaptionFromComboBox();
		if (GtnTransactionUIConstants.ACTUALS.equals(captionOne)
				&& GtnTransactionUIConstants.DETAILS.equals(captionTwo)) {
			invalidTableName = "IvldInventoryWdActualDt";
		} else if (GtnTransactionUIConstants.ACTUALS.equals(captionOne)
				&& GtnTransactionUIConstants.SUMMARY.equals(captionTwo)) {
			invalidTableName = "IvldInventoryWdActualMas";
		} else if (GtnTransactionUIConstants.PROJECTIONS.equals(captionOne)
				&& GtnTransactionUIConstants.DETAILS.equals(captionTwo)) {
			invalidTableName = "IvldInventoryWdProjDt";
		} else if (GtnTransactionUIConstants.PROJECTIONS.equals(captionOne)
				&& GtnTransactionUIConstants.SUMMARY.equals(captionTwo)) {
			invalidTableName = "IvldInventoryWdProjMas";
		}
		return invalidTableName;
	}

}
