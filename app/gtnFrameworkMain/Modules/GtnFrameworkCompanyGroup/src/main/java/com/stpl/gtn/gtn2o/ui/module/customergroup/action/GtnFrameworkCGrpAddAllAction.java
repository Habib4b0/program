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
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkCGrpAddAllAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpAddAllAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsSearchRequest cGrpAvailableTableFilterSearchRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest cGrpAvailableTableFilterSearchGeneralWSRequest = new GtnWsGeneralRequest();
		List<GtnWebServiceSearchCriteria> cGrpAvailableTableFilterSearchCriteriaList = GtnUIFrameworkGlobalUI
				.addCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues());
		List<GtnWebServiceSearchCriteria> searchCriteriaList = getSearchCriteriaList(
				cGrpAvailableTableFilterSearchCriteriaList);
		if (searchCriteriaList.isEmpty()) {
			GtnUIFrameWorkActionConfig alertAddAllActionConfig = new GtnUIFrameWorkActionConfig();
			alertAddAllActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

			List<Object> alertAddAllParamsList = new ArrayList<>();
			alertAddAllParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_ADD_MSG);
			alertAddAllParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_ADD_ALL_MSG_BODY);

			alertAddAllActionConfig.setActionParameterList(alertAddAllParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAddAllActionConfig);
		} else {
			cGrpAvailableTableFilterSearchRequest.setGtnWebServiceSearchCriteriaList(searchCriteriaList);
			GtnUIFrameworkWebserviceRequest cGrpAvailableTableFilterSearchWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
			cGrpAvailableTableFilterSearchWebserviceRequest
					.setGtnWsSearchRequest(cGrpAvailableTableFilterSearchRequest);
			try {
				cGrpAvailableTableFilterSearchGeneralWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
				cGrpAvailableTableFilterSearchGeneralWSRequest
						.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
				cGrpAvailableTableFilterSearchWebserviceRequest
						.setGtnWsGeneralRequest(cGrpAvailableTableFilterSearchGeneralWSRequest);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
								+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_ADDALL_SERVICE,
						cGrpAvailableTableFilterSearchWebserviceRequest,
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

				GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("cGrpSelectedResultTable").getLogicFromPagedDataTable();
				logic.startSearchProcess(null, Boolean.TRUE);
			} catch (Exception e) {
				gtnLogger.error(e.getMessage(), e);
				throw new GtnFrameworkGeneralException(e);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public static List<GtnWebServiceSearchCriteria> getSearchCriteriaList(
			List<GtnWebServiceSearchCriteria> currentSearchCriteriaList) {
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		if (currentSearchCriteriaList != null) {
			gtnWebServiceSearchCriteriaList.addAll(currentSearchCriteriaList);
		}

		GtnUIFrameworkGlobalUI.addSearchCriteriaList(gtnWebServiceSearchCriteriaList, "cGrpAvailablesearchResultTable");

		return gtnWebServiceSearchCriteriaList;
	}

}
