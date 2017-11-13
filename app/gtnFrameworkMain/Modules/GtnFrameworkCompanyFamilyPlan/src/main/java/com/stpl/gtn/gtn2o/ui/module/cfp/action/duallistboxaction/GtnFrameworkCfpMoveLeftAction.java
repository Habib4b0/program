package com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction;

import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;

public class GtnFrameworkCfpMoveLeftAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpMoveLeftAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpMoveLeftAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCfpRequest gtnWsCfpRequest = new GtnWsCfpRequest();
		Set<GtnWsRecordBean> cfpRightResultTableDtoList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("CFPrightResultTable").getValueFromMultiSelectPagedDataTable();
		if (cfpRightResultTableDtoList.isEmpty()) {
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_COMPANY_ADDITION_REMOVE, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		try {
			for (GtnWsRecordBean gtnWsRecordBean : cfpRightResultTableDtoList) {
				Object systemId = gtnWsRecordBean.getProperties().get(8);
				int companyMasterSid = systemId == null ? 0 : Integer.valueOf(String.valueOf(systemId));
				GtnCFamilyPlanCommonUpdateBean cfpUpdateBean = new GtnCFamilyPlanCommonUpdateBean();
				GtnCFamilyPlan cfpMoveLeftBean = new GtnCFamilyPlan();
				cfpMoveLeftBean.setUpdateBean(cfpUpdateBean);
				cfpUpdateBean.setCompanyMasterSid(companyMasterSid);
				GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
				generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
				generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
				request.setGtnWsGeneralRequest(generalWSRequest);
				gtnWsCfpRequest.setGtnCFamilyPlan(cfpMoveLeftBean);
				request.setGtnWsCfpRequest(gtnWsCfpRequest);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getExtPagedTable()
						.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
								+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_LEFT_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPrightResultTable")
					.getLogicFromPagedDataTable();

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