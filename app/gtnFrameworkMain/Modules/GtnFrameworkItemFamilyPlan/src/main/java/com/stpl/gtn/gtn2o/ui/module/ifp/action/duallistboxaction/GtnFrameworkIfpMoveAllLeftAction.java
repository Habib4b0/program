package com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkIfpMoveAllLeftAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpMoveAllLeftAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpMoveAllLeftAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		request.setGtnWsSearchRequest(sRequest);
		try {
			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
					.getExtPagedTableSize() == 0) {
				GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
				Object msg = "There are no items to remove";
				actionConfig.setActionParameterList(Arrays.asList("Error", msg));
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
				alertAction.doAction(componentId, actionConfig);
			}

			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
					.getExtPagedTableSize() != 0) {

				generalWSRequest.setUserId(String
						.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
				generalWSRequest.setSessionId(String.valueOf(
						GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
				request.setGtnWsGeneralRequest(generalWSRequest);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
								+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_ALL_LEFT_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable").getExtPagedTable()
						.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
						.getLogicFromPagedDataTable();
				logic.clearFilters();
				logic.startSearchProcess(null, true);
			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}
	}


	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}