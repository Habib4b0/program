package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

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
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkItemGrpAddAllAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		List<GtnWebServiceSearchCriteria> currentSearchCriteriaList = GtnUIFrameworkGlobalUI
				.addCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues());
		List<GtnWebServiceSearchCriteria> searchCriteriaList = getSearchCriteriaList(currentSearchCriteriaList);
		if (searchCriteriaList.isEmpty()) {
			GtnUIFrameWorkActionConfig alertItemAddAllActionConfig = new GtnUIFrameWorkActionConfig();
			alertItemAddAllActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

			List<Object> alertItemAddAllParamsList = new ArrayList<>();
			alertItemAddAllParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_ADD_MSG);
			alertItemAddAllParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_ADD_MSG_BODY);

			alertItemAddAllActionConfig.setActionParameterList(alertItemAddAllParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertItemAddAllActionConfig);
		} else {
			sRequest.setGtnWebServiceSearchCriteriaList(searchCriteriaList);
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsSearchRequest(sRequest);
			try {
				generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
				generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
				request.setGtnWsGeneralRequest(generalWSRequest);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE
								+ GtnWsItemGrpContants.GTN_WS_ITEM_GRP_ADDALL_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

				GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("itemGrpSelectedResultTable").getLogicFromPagedDataTable();
				logic.startSearchProcess(null, true);
			} catch (Exception e) {
				throw new GtnFrameworkGeneralException(e);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public List<GtnWebServiceSearchCriteria> getSearchCriteriaList(
			List<GtnWebServiceSearchCriteria> currentSearchCriteriaList) {
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

		gtnWebServiceSearchCriteriaList.addAll(currentSearchCriteriaList);

		GtnUIFrameworkGlobalUI.addSearchCriteriaList(gtnWebServiceSearchCriteriaList,
				"itemGrpAvailablesearchResultTable");

		return gtnWebServiceSearchCriteriaList;
	}

}
