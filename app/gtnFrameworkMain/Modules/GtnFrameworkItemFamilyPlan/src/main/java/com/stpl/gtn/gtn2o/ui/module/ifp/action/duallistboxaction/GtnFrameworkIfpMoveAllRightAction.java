package com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnFrameworkIfpMoveAllRightAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpMoveAllRightAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkPagedTableLogic ifpRightPagedTableLogic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE).getLogicFromPagedDataTable();
		GtnUIFrameworkBaseComponent ifpLeftTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPLEFT_RESULT_TABLE);

		ifpRightPagedTableLogic.addCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues(), null);
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>(
				ifpRightPagedTableLogic.getCurrentSearchCriteria());
		searchCriteriaList.addAll(
				ifpRightPagedTableLogic.getSearchCriteriaList(ifpLeftTable.getLogicFromPagedDataTable().getFilters()));
		ifpRightPagedTableLogic.resetSearchCriteriaList();

		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		request.setGtnWsSearchRequest(sRequest);
		try {
			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPLEFT_RESULT_TABLE)
					.getExtPagedTableSize() == 0) {
				GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
				Object msg = "There are no items to move";
				actionConfig.setActionParameterList(Arrays.asList("Error", msg));
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
				alertAction.doAction(componentId, actionConfig);
			}

			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPLEFT_RESULT_TABLE)
					.getExtPagedTableSize() != 0) {
				generalWSRequest.setUserId(String
						.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
				generalWSRequest.setSessionId(String.valueOf(
						GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
				request.setGtnWsGeneralRequest(generalWSRequest);
				sRequest.setGtnWebServiceSearchCriteriaList(searchCriteriaList);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
								+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable").getExtPagedTable()
						.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				reloadRightTable();
			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	private void reloadRightTable() throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
				.getLogicFromPagedDataTable().startSearchProcess(null, true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}