package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCfpEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);
		String propertyId = (String) actionParamList.get(2);
		boolean isEditable = (boolean) actionParamList.get(3);

		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", true);
		GtnWsRecordBean gtnWsRecordBean = getItemFormTable(gtnUIFrameWorkActionConfig, tableId);
		if (gtnWsRecordBean == null) {
			return;
		}
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCFamilyPlan cfpBean = new GtnCFamilyPlan();
		GtnCFamilyPlanInformation cfpInfoBean = new GtnCFamilyPlanInformation();
		cfpBean.setCfpInfo(cfpInfoBean);

		int systemId = (Integer) gtnWsRecordBean.getPropertyValue(propertyId);
		cfpInfoBean.setCfpSid(systemId);
		GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid", systemId);
		GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();
		cfpRequest.setGtnCFamilyPlan(cfpBean);
		gtnRequest.setGtnWsCfpRequest(cfpRequest);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		try {
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
							+ GtnWsCFamilyPlanContants.GTN_WS_CFP_FETCH_INFORMATION_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			setValueToComponents(response.getGtnWsCfpReponse().getGtnCFamilyPlan(), componentId, isEditable);

			loadNotesTab(response.getGtnWsCfpReponse().getGtnCFamilyPlan(), componentId);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable").setTableReadOnly(!isEditable);
			GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("CFPrightResultTable").getLogicFromPagedDataTable();
			cfpCaTabRightTablelogic.startSearchProcess(null, Boolean.TRUE);
			GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogicOnView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cfprightResultTableOnView").getLogicFromPagedDataTable();
			cfpCaTabRightTablelogicOnView.startSearchProcess(null, Boolean.TRUE);
			GtnUIFrameworkPagedTableLogic cfpCompaniesTabTablelogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCfpStringContants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE)
					.getLogicFromPagedDataTable();
			cfpCompaniesTabTablelogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), Boolean.TRUE);
			setCompaniesRecordType();
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	private GtnWsRecordBean getItemFormTable(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String tableId)
			throws GtnFrameworkValidationFailedException {
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof Boolean) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		}
		return (GtnWsRecordBean) itemId;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnCFamilyPlan bean, String componentId, boolean isEditable)
			throws GtnFrameworkGeneralException {
		GtnCFamilyPlanInformation info = bean.getCfpInfo();
		configureInformationTab(componentId, info);
		configreButtonFields(componentId, isEditable, info);
		configureTable(isEditable, info);
		loadAuditDetails(componentId, info);
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		List<String> fieldIds = Arrays.asList("cfpInformationTabCFPId", "cfpInformationTabCFPNo",
				"cfpInformationTabCFPName", "cfpInformationTabCFPStatus", "cfpInformationCFPStartDate",
				"cfpInformationCFPEndDate", "cfpInformationTabCFPType", "cfpInformationTabCFPCategory",
				"cfpInformationTabCFPTradeClass", "cfpInformationTabCFPDesignation",
				"cfpInformationTabCFPSalesInclusion");
		enableOrDisableFields(fieldIds, isEditable, componentId);

		notesTab.setNotesTabEnable(isEditable);

	}

	private void loadAuditDetails(String componentId, GtnCFamilyPlanInformation info)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCreatedBy", componentId)
				.loadFieldValue(info.getCreatedBy());
		GtnUIFrameWorkActionConfig cfpEditDisableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DISABLE_ACTION);
		cfpEditDisableAction.setActionParameterList(GtnFrameworkCfpStringContants.DISABLED_CFP_FIELDS);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cfpEditDisableAction);

	}

	private void configureTable(boolean isEditable, GtnCFamilyPlanInformation info) {
		gtnLogger.debug("is ETL Record :: " + info.isRecordLockStatus());
		GtnUIFrameworkBaseComponent cfpCompaniesTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCfpStringContants.CFP_COMPANIES_TAB_RESULT_DATA_TABLE);
		cfpCompaniesTable.getExtPagedTable().setFilterBarVisible(isEditable);
		cfpCompaniesTable.getExtPagedTable().setEditable(isEditable);
		configureCompaniesTableColumn(cfpCompaniesTable, isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabRecordType").setEnable(true);
	}

	private void configureCompaniesTableColumn(GtnUIFrameworkBaseComponent cfpCompaniesTable, boolean isEditable) {
		if (isEditable) {
			cfpCompaniesTable.setTableColumns(GtnFrameworkCfpStringContants.CFP_COMPANIES_COLUMN_LIST.toArray());
			cfpCompaniesTable.setTableColumnHeaders(GtnFrameworkCfpStringContants.CFP_COMPANIES_HEADERS_LIST
					.toArray(new String[GtnFrameworkCfpStringContants.CFP_COMPANIES_HEADERS_LIST.size()]));

		} else {
			cfpCompaniesTable.setTableColumns(GtnFrameworkCfpStringContants.CFP_COMPANIES_VIEW_COLUMN_LIST.toArray());
			cfpCompaniesTable.setTableColumnHeaders(GtnFrameworkCfpStringContants.CFP_COMPANIES_VIEW_HEADERS_LIST
					.toArray(new String[GtnFrameworkCfpStringContants.CFP_COMPANIES_VIEW_HEADERS_LIST.size()]));

		}

	}

	private void configreButtonFields(String componentId, boolean isEditable, GtnCFamilyPlanInformation info) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpAddDeleteButton", componentId)
				.setComponentVisible(isEditable && !info.isRecordLockStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPADDResetButton", componentId)
				.setComponentVisible(isEditable && !info.isRecordLockStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpAddSaveButton").setCaption("UPDATE");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpAddSaveButton")
				.setVisible(isEditable && !info.isRecordLockStatus());
	}

	private void configureInformationTab(String componentId, GtnCFamilyPlanInformation info)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig cfpEditDefaultActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> values = Arrays.asList(new Object[] { info.getCfpId(), info.getCfpNo(), info.getCfpName(),
				info.getCfpId(), info.getCfpNo(), info.getCfpName(), info.getModifiedBy(), info.getParentCfpId(),
				info.getParentCfpName(), info.getCfpStatus(), info.getCfpType(), info.getCfpCategory(),
				info.getCfpTradeClass(), info.getCfpDesignation(), info.getSalesInclusion(), info.getCfpStartDate(),
				info.getCfpEndDate(), info.getCreatedDate(), info.getModifiedDate() });
		cfpEditDefaultActionConfig.addActionParameter(GtnFrameworkCfpStringContants.CFP_FIELDS);
		cfpEditDefaultActionConfig.addActionParameter(values);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cfpEditDefaultActionConfig);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPId", componentId)
				.setComponentReadOnly(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPName", componentId)
				.setComponentReadOnly(true);
	}

	private void loadNotesTab(GtnCFamilyPlan bean, String componentId) {

		try {
			GtnUIFrameWorkActionConfig cfpEditNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			cfpEditNotesTabLoadAction.addActionParameter(bean.getNotesTabList());
			cfpEditNotesTabLoadAction.addActionParameter(bean.getCfpInfo().getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cfpEditNotesTabLoadAction);
		} catch (Exception e) {
			gtnLogger.error("Exception in loading Notes Tab", e);
		}
	}

	private void enableOrDisableFields(List<String> fieldIds, boolean isEnable, String parentComponentId) {

		for (int i = 0; i < fieldIds.size(); i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldIds.get(i), parentComponentId)
					.setComponentEnable(isEnable);
		}
		if (!isEnable) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPId").setEnable(isEnable);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabParentCFPName").setEnable(isEnable);
		}

	}

	private void setCompaniesRecordType() {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonStringConstants.CFPCOMPANIESTAB_RECORDTYPE)
				.setUnselect(GtnFrameworkCommonStringConstants.CURRENT);
	}

}