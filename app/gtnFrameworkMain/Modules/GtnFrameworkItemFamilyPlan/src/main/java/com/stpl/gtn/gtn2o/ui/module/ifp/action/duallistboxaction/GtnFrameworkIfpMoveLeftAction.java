package com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction;

import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;

public class GtnFrameworkIfpMoveLeftAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpMoveLeftAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpMoveLeftAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		Set<GtnWsRecordBean> dtoSet = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
				.getValueFromMultiSelectPagedDataTable();
		if (dtoSet == null || dtoSet.isEmpty()) {
			throw new GtnFrameworkValidationFailedException("Please select an item to remove", componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		try {
                    GtnUIFrameworkWebServiceClient frameWorkWebServiceClientnew = new GtnUIFrameworkWebServiceClient();
				for (GtnWsRecordBean gtnWsRecordBean : dtoSet) {
					String companyMasterSid = String.valueOf(gtnWsRecordBean.getProperties().get(8));
					GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
					updateBean
							.setItemMasterSid(companyMasterSid.equals(GtnFrameworkCommonStringConstants.STRING_NULL) ? 0
									: Integer.parseInt(companyMasterSid));
					GtnIFamilyPlanBean ifp = new GtnIFamilyPlanBean();
					ifp.setUpdateBean(updateBean);
					GtnWsIfpRequest ifpRequest = new GtnWsIfpRequest();
					ifpRequest.setGtnIFamilyPlan(ifp);
					request.setGtnWsIfpRequest(ifpRequest);
					GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
					generalWSRequest.setUserId(String.valueOf(
							GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
					generalWSRequest.setSessionId(String.valueOf(
							GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
					request.setGtnWsGeneralRequest(generalWSRequest);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable").getExtPagedTable()
							.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);
					frameWorkWebServiceClientnew.callGtnWebServiceUrl(
							GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
									+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_LEFT_SERVICE,
							request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				}
				GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("ifprightResultTable").getLogicFromPagedDataTable();

				logic.startSearchProcess(null, true);
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