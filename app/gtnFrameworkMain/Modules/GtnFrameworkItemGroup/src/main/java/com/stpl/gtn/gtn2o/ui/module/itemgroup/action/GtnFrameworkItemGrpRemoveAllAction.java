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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkItemGrpRemoveAllAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemGrpRemoveAllAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemGrpRemoveAllAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		List<GtnWsRecordBean> removeSelectedItems = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_RESULT_TABLE)
				.getItemsFromDataTable();

		if (removeSelectedItems == null || removeSelectedItems.isEmpty()) {
			GtnUIFrameWorkActionConfig alertItemRemoveAllActionConfig = new GtnUIFrameWorkActionConfig();
			alertItemRemoveAllActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

			List<Object> alertItemRemoveAllParamsList = new ArrayList<>();
			alertItemRemoveAllParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_ADD_MSG);
			alertItemRemoveAllParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_REMOVE_MSG_BODY);

			alertItemRemoveAllActionConfig.setActionParameterList(alertItemRemoveAllParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertItemRemoveAllActionConfig);
		}

		sRequest.setGtnWebServiceSearchCriteriaList(getSearchCriteriaList());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(sRequest);

		try {
			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE
							+ GtnWsItemGrpContants.GTN_WS_ITEM_GRP_REMOVEALL_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_RESULT_TABLE)
					.getLogicFromPagedDataTable();
			logic.startSearchProcess(null, Boolean.TRUE);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public List<GtnWebServiceSearchCriteria> getSearchCriteriaList() {
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnUIFrameworkGlobalUI.addSearchCriteriaList(gtnWebServiceSearchCriteriaList,
				GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_RESULT_TABLE);
		return gtnWebServiceSearchCriteriaList;
	}
}
