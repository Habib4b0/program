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

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCfpMoveRightAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpMoveRightAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpMoveRightAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCfpRequest gtnWsCfpRequest = new GtnWsCfpRequest();
		Set<GtnWsRecordBean> cfpLeftResultTableDtoList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("CFPleftResultTable").getValueFromMultiSelectPagedDataTable();
		if (cfpLeftResultTableDtoList.isEmpty()) {
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_COMPANY_ADDITION_ADD, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		try {
			for (GtnWsRecordBean gtnWsRecordBean : cfpLeftResultTableDtoList) {
				int companyMasterSid = gtnWsRecordBean.getProperties().get(8) == null ? 0
						: Integer.parseInt(String.valueOf(gtnWsRecordBean.getProperties().get(8)));
				GtnCFamilyPlanCommonUpdateBean cfpUpdateBean = new GtnCFamilyPlanCommonUpdateBean();
				GtnCFamilyPlan cfpMoveRightBean = new GtnCFamilyPlan();
				cfpMoveRightBean.setUpdateBean(cfpUpdateBean);
				cfpUpdateBean.setCompanyMasterSid(companyMasterSid);
				GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
				generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
				generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
				request.setGtnWsGeneralRequest(generalWSRequest);
				gtnWsCfpRequest.setGtnCFamilyPlan(cfpMoveRightBean);
				request.setGtnWsCfpRequest(gtnWsCfpRequest);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
								+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_RIGHT_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPrightResultTable")
					.getLogicFromPagedDataTable();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getExtPagedTable()
					.setData(GtnFrameworkCommonStringConstants.STRING_EMPTY);

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