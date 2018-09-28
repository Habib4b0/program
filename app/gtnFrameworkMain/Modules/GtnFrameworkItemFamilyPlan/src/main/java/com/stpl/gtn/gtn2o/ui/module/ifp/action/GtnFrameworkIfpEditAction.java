package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Component;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkIfpEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String tableId = (String) actionParamList.get(1); 
			String propertyId = (String) actionParamList.get(2);
			boolean isEditable = (boolean) actionParamList.get(4);
			String tableComponentId = (String) actionParamList.get(3);
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
			GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
			GtnIFamilyPlanInformationBean ifpInfoBean = new GtnIFamilyPlanInformationBean();
			ifpBean.setIfpInfo(ifpInfoBean);

			GtnWsRecordBean gtnWsRecordBean = getItemFormTable(gtnUIFrameWorkActionConfig, tableId);
			if (gtnWsRecordBean == null) {
				return;
			}
			boolean isCopyModeId = "gtnCopyButton".equalsIgnoreCase(componentId);
			GtnUIFrameworkGlobalUI.addSessionProperty("mode", isCopyModeId ? "Copy" : "Edit");
			String copyModeVal = (String) GtnUIFrameworkGlobalUI.getSessionProperty("mode");
			if ("Copy".equals(copyModeVal)) {
				Component delComponent = GtnUIFrameworkGlobalUI.getVaadinComponent("ifpAddDeleteButton");
				delComponent.setEnabled(false);
			}
			int systemId = (Integer) gtnWsRecordBean.getPropertyValue(propertyId);
			ifpInfoBean.setIfpSid(systemId);
			GtnUIFrameworkGlobalUI.addSessionProperty("ifpModelSid", systemId);
			GtnWsIfpRequest ifpRequest = new GtnWsIfpRequest();
			ifpRequest.setGtnIFamilyPlan(ifpBean);
			gtnRequest.setGtnWsIfpRequest(ifpRequest);
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
			generalWSRequest.setSessionId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
			gtnRequest.setGtnWsGeneralRequest(generalWSRequest);

			GtnUIFrameworkWebserviceResponse response = getEditResponse(gtnRequest);
			if (response != null)   
			{
				setValueToComponents(response.getGtnWsIfpReponse().getGtnIFamilyPlan(), componentId, isEditable);
				GtnUIFrameWorkActionConfig ifpEditNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.LOAD_NOTES_TAB);
				ifpEditNotesTabLoadAction
						.addActionParameter(response.getGtnWsIfpReponse().getGtnIFamilyPlan().getNotesTabList());
				ifpEditNotesTabLoadAction.addActionParameter(
						response.getGtnWsIfpReponse().getGtnIFamilyPlan().getIfpInfo().getInternalNotes());
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, ifpEditNotesTabLoadAction);
			}
			GtnUIFrameworkPagedTableLogic ifpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(tableComponentId).getLogicFromPagedDataTable();
			ifpCaTabRightTablelogic.startSearchProcess(null, true);
			setTableHeaderAndVisibleColumn(isEditable);

			GtnUIFrameworkPagedTableLogic ifpItemsTabTablelogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.getLogicFromPagedDataTable();
			ifpItemsTabTablelogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.setFilterFieldVisible(GtnFrameworkCommonConstants.CHECK_RECORD_ID, false);
			setCompaniesRecordType();
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	public GtnUIFrameworkWebserviceResponse getEditResponse(GtnUIFrameworkWebserviceRequest gtnRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_FETCH_INFORMATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private void setTableHeaderAndVisibleColumn(boolean isEditable) {
		if (!isEditable) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.setTableColumns(GtnFrameworkIfpStringContants.geIfpVisibleColumnForView());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.setTableColumnHeaders(GtnFrameworkIfpStringContants.getIfpVisibleHeaderForView());
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.setTableColumns(GtnFrameworkIfpStringContants.IFP_VISIBLE_COLUMN.toArray().clone());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.setTableColumnHeaders(
							(String[]) GtnFrameworkIfpStringContants.IFP_VISIBLE_HEADER.toArray().clone());

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnIFamilyPlanBean bean, String componentId, boolean isEditable)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Laoding values in IFP - EDit Action " + bean.getIfpInfo());
		GtnIFamilyPlanInformationBean ifpInfoBean = bean.getIfpInfo();
		List<Object> ifpFieldValues = Arrays.asList(ifpInfoBean.getIfpId(), ifpInfoBean.getIfpNo(),
				ifpInfoBean.getIfpName(), ifpInfoBean.getIfpId(), ifpInfoBean.getIfpNo(), ifpInfoBean.getIfpName(),
				ifpInfoBean.getIfpStatus(), ifpInfoBean.getIfpStartDate(), ifpInfoBean.getIfpEndDate(),
				ifpInfoBean.getIfpType(), ifpInfoBean.getIfpCategory(), ifpInfoBean.getIfpDesignation(),
				ifpInfoBean.getParentIfpId(), ifpInfoBean.getParentIfpName(), ifpInfoBean.getCreatedDate(),
				ifpInfoBean.getModifiedDate(), ifpInfoBean.getModifiedBy());

		GtnUIFrameWorkActionConfig ifpEditdDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		ifpEditdDefaultValueActionConfig.addActionParameter(GtnFrameworkIfpStringContants.IFP_FIELDS);
		ifpEditdDefaultValueActionConfig.addActionParameter(ifpFieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, ifpEditdDefaultValueActionConfig);
		boolean isButtonVisible = isEditable && !ifpInfoBean.isRecordLockStatus();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpAddDeleteButton").setVisible(isButtonVisible);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("IFPADDResetButton").setVisible(isButtonVisible);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabCreatedBy", componentId)
				.loadFieldValue(ifpInfoBean.getCreatedBy());

		setTableEnableDisable(isEditable, ifpInfoBean);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setFilterBarVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabCreatedBy", componentId)
				.setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationCreatedDate", componentId)
				.setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabModifiedBy", componentId)
				.setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationModifiedDate", componentId)
				.setComponentEnable(false);

		List<String> fieldIds = Arrays.asList("ifpInformationTabIFPId", "ifpInformationTabIFPNo",
				"ifpInformationTabIFPName", "ifpInformationTabIFPStatus", "ifpInformationIFPStartDate",
				"ifpInformationIFPEndDate", "ifpInformationTabIFPType", "ifpInformationTabIFPCategory",
				"ifpInformationTabIFPDesignation");
		enableOrDisableFields(fieldIds, isEditable, componentId);

		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.setNotesTabEnable(isEditable);

	}

	private void setCompaniesRecordType() {
		GtnUIFrameworkBaseComponent ifpCompaniesTabRecordType = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonStringConstants.IFPITEMSTAB_RECORDTYPE);
		ifpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.CURRENT);
		ifpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.HISTORY);
		ifpCompaniesTabRecordType.setUnselect(GtnFrameworkCommonStringConstants.FUTURE);
	}

	private void setTableEnableDisable(boolean isEditable, GtnIFamilyPlanInformationBean info) {
		gtnLogger.debug("is ETL Record :: " + info.isRecordLockStatus());
		if (isEditable) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setEnabled(isEditable);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setEditable(isEditable);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setReadOnly(isEditable);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setEditable(isEditable);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setReadOnly(!isEditable);
		}

	}

	private void enableOrDisableFields(List<String> fieldIds, boolean isEnable, String parentComponentId) {

		for (int i = 0; i < fieldIds.size(); i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldIds.get(i), parentComponentId)
					.setComponentEnable(isEnable);
		}
		if (!isEnable) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabParentIFPId").setEnable(isEnable);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabParentIFPName").setEnable(isEnable);
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

}
