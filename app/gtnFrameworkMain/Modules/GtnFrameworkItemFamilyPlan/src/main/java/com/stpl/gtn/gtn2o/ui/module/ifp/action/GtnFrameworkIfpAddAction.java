package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkIfpAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		setValueToComponents(componentId);
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(userId);
		generalWSRequest.setSessionId(String
				.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse response = getWSResponse(gtnRequest);
		String userName = response.getGtnWsGeneralResponse().getUserName();
		GtnUIFrameworkGlobalUI.addSessionProperty("userName", userName);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabCreatedBy", componentId)
				.loadFieldValue(userName);
                GtnUIFrameworkGlobalUI.addSessionProperty("ifpModelSid", 0);
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
                new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
							GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
									+ GtnWsIFamilyPlanContants.GTN_WS_IFP_TEMP_DELETE_SERVICE ,
							gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	/**
	 * @param gtnRequest
	 * @return
	 */
	public GtnUIFrameworkWebserviceResponse getWSResponse(GtnUIFrameworkWebserviceRequest gtnRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(String componentId) throws GtnFrameworkGeneralException {
		GtnIFamilyPlanInformationBean info = new GtnIFamilyPlanInformationBean();

		List<Object> ifpFieldValues = Arrays.asList(info.getIfpId(), info.getIfpNo(), info.getIfpName(),
				info.getIfpId(), info.getIfpNo(), info.getIfpName(), info.getIfpStatus(), info.getIfpStartDate(),
				info.getIfpEndDate(), info.getIfpType(), info.getIfpCategory(), info.getIfpDesignation(),
				info.getParentIfpId(), info.getParentIfpName(), new Date(), null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);

		GtnUIFrameWorkActionConfig ifpAddDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		ifpAddDefaultValueActionConfig.addActionParameter(GtnFrameworkIfpStringContants.IFP_FIELDS);
		ifpAddDefaultValueActionConfig.addActionParameter(ifpFieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, ifpAddDefaultValueActionConfig);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpAddDeleteButton").setVisible(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("IFPADDResetButton").setVisible(true);
		GtnUIFrameworkBaseComponent ifpAddSaveBtn = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpAddSaveButton");
		ifpAddSaveBtn.setCaption("SAVE");
		ifpAddSaveBtn.setVisible(true);

		GtnUIFrameworkBaseComponent ifpCompaniesTabRecordType = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ifpItemsTabRecordType");
		ifpCompaniesTabRecordType.setComponentEnable(true);
		ifpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.CURRENT);
		ifpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.HISTORY);
		ifpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.FUTURE);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().removeAllItems();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setFilterBarVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifprightResultTable").getExtPagedTable().removeAllItems();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setEnabled(true);

		setTableEnableDisable();

		GtnUIFrameWorkActionConfig fpAddModeDisableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DISABLE_ACTION);
		fpAddModeDisableAction.setActionParameterList(GtnFrameworkIfpStringContants.DISABLED_IFP_FIELDS);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, fpAddModeDisableAction);
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.refreshNotesTab();
	}

	private void setTableEnableDisable() {
		GtnUIFrameworkBaseComponent ifpItemResultTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE);
		ifpItemResultTable.getExtPagedTable().setEnabled(true);
		ifpItemResultTable.getExtPagedTable().setEditable(true);
		ifpItemResultTable.setTableColumns(GtnFrameworkIfpStringContants.IFP_VISIBLE_COLUMN.toArray());
		ifpItemResultTable.setTableColumnHeaders(GtnFrameworkIfpStringContants.IFP_VISIBLE_HEADER
				.toArray(new String[GtnFrameworkIfpStringContants.IFP_VISIBLE_HEADER.size()]));
	}

}
