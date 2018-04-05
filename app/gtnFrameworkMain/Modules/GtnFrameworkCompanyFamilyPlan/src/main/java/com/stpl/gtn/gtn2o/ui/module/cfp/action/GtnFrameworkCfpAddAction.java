package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCfpAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering GtnFrameworkCfpAddAction doAction ");
		try {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable").setTableReadOnly(false);
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
			String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(userId);
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			String userName = response.getGtnWsGeneralResponse().getUserName();
			GtnUIFrameworkGlobalUI.addSessionProperty("userName", userName);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCreatedBy", componentId)
					.loadFieldValue(userName);
			setValueToComponents(componentId);
			GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid", 0);
		} catch (Exception e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkCfpAddAction ", e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
			gtnLogger.info("Exit GtnFrameworkCfpAddAction doAction ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(String componentId) {
		GtnCFamilyPlanInformation info = new GtnCFamilyPlanInformation();

		try {
			List<Object> values = Arrays.<Object>asList(info.getCfpId(), info.getCfpNo(), info.getCfpName(),
					info.getCfpId(), info.getCfpNo(), info.getCfpName(), GtnFrameworkCommonStringConstants.STRING_EMPTY,
					info.getParentCfpId(), info.getParentCfpName(), info.getCfpStatus(), info.getCfpType(),
					info.getCfpCategory(), info.getCfpTradeClass(), info.getCfpDesignation(), info.getSalesInclusion(),
					info.getCfpStartDate(), info.getCfpEndDate(), new Date(), null);

			GtnUIFrameWorkActionConfig cfpAddDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
			cfpAddDefaultValueActionConfig.addActionParameter(GtnFrameworkCfpStringContants.CFP_FIELDS);
			cfpAddDefaultValueActionConfig.addActionParameter(values);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cfpAddDefaultValueActionConfig);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpAddDeleteButton").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPADDResetButton").setVisible(true);
			GtnUIFrameworkBaseComponent cfpAddSaveBtn = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cfpAddSaveButton");
			cfpAddSaveBtn.setCaption("SAVE");
			cfpAddSaveBtn.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPId", componentId)
					.setComponentReadOnly(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPName", componentId)
					.setComponentReadOnly(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setEditable(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setFilterBarVisible(true);

			GtnUIFrameworkBaseComponent cfpCompaniesTabRecordType = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cfpCompaniesTabRecordType");
			cfpCompaniesTabRecordType.setComponentEnable(true);
			cfpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.CURRENT);
			cfpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.HISTORY);
			cfpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.FUTURE);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().removeAllItems();

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPrightResultTable").getExtPagedTable().removeAllItems();

			GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
					.getVaadinComponent("notesTab");

			GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.DISABLE_ACTION);
			disableAction.setActionParameterList(GtnFrameworkCfpStringContants.DISABLED_CFP_FIELDS);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, disableAction);
			notesTab.refreshNotesTab();
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}

	}

}
