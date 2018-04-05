package com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkCfpMoveAllRightAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpMoveAllRightAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkPagedTableLogic rightPagedTableLogic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("CFPrightResultTable").getLogicFromPagedDataTable();
		GtnUIFrameworkBaseComponent leftTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE);

		rightPagedTableLogic.addCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues(), null);
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>(
				rightPagedTableLogic.getCurrentSearchCriteria());
		searchCriteriaList.addAll(
				rightPagedTableLogic.getSearchCriteriaList(leftTable.getLogicFromPagedDataTable().getFilters()));
		rightPagedTableLogic.resetSearchCriteriaList();

		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		List<GtnWsRecordBean> leftTableItemList = leftTable.getItemsFromDataTable();
		if (leftTableItemList == null || leftTableItemList.isEmpty()) {
			return;
		}
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		request.setGtnWsSearchRequest(sRequest);
		try {
			generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			sRequest.setGtnWebServiceSearchCriteriaList(searchCriteriaList);
			request.setGtnWsGeneralRequest(generalWSRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
							+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getExtPagedTable()
					.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPrightResultTable")
					.getLogicFromPagedDataTable();
			logic.clearFilters();
			logic.startSearchProcess(null, true);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}