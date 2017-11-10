package com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkCfpMoveAllLeftAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpMoveAllLeftAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpMoveAllLeftAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE)
				.getFiltersFromPagedDataTable();
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		request.setGtnWsSearchRequest(sRequest);
		try {
			generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
							+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getExtPagedTable()
					.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE)
					.getLogicFromPagedDataTable();
			logic.clearFilters();
			logic.startSearchProcess(null, Boolean.TRUE);

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
