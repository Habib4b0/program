package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkCGrpRemoveAllAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpRemoveAllAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCGrpRemoveAllAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		List<GtnWsRecordBean> selectedItems = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCGrpStringContants.C_GRP_SELECTED_RESULT_TABLE)
				.getItemsFromDataTable();
		if (selectedItems == null || selectedItems.isEmpty()) {
			GtnUIFrameWorkActionConfig alertRemoveAllActionConfig = new GtnUIFrameWorkActionConfig();
			alertRemoveAllActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

			List<Object> alertRemoveAllParamsList = new ArrayList<>();
			alertRemoveAllParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_ADD_MSG);
			alertRemoveAllParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_REMOVE_MSG_BODY);

			alertRemoveAllActionConfig.setActionParameterList(alertRemoveAllParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertRemoveAllActionConfig);
		}
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnUIFrameworkGlobalUI.addSearchCriteriaList(gtnWebServiceSearchCriteriaList,
				GtnFrameworkCGrpStringContants.C_GRP_SELECTED_RESULT_TABLE);
		sRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(sRequest);
		try {
			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
							+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_REMOVEALL_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCGrpStringContants.C_GRP_SELECTED_RESULT_TABLE)
					.getLogicFromPagedDataTable();
			logic.startSearchProcess(null, Boolean.TRUE);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
