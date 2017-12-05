
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateschedule.GtnWsRebateScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameWorkRSLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		loadDataFromService(componentId);
	}

	private void loadDataFromService(String componentId) throws GtnFrameworkGeneralException {
		GtnWsRebateScheduleInfoBean rsInfoBean;
		List<NotesTabBean> beanList;
		int systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId");
		boolean isEditable = true;
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsRebateScheduleInfoBean rSInfoBean = new GtnWsRebateScheduleInfoBean();
		rSInfoBean.setSystemId(systemId);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		GtnWsRebateScheduleGeneralRequest getGtnWsRebateScheduleGeneralRequest = new GtnWsRebateScheduleGeneralRequest();
		getGtnWsRebateScheduleGeneralRequest.setRebateScheduleInfoBean(rSInfoBean);
		request.setGtnWsRebateScheduleGeneralRequest(getGtnWsRebateScheduleGeneralRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.RS_GET_DATA, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		rsInfoBean = response.getRebateScheduleInfoBean();
		beanList = response.getRebateScheduleInfoBean().getNoteBeanList();

		setValuesToFields(componentId, rsInfoBean);
		loadIfpSelectedTable(rsInfoBean);
		loadNotesTab(beanList, rsInfoBean);
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		if (mode == GtnUIFrameworkModeType.VIEW) {
			isEditable = false;
			setTableHeaderAndVisibleColumn(isEditable);
			setTableEnableDisable(isEditable);
		} else if (mode.equals("EDIT")) {
			isEditable = true;
			setTableHeaderAndVisibleColumn(isEditable);
			setTableEnableDisable(isEditable);
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setFilterBarVisible(isEditable);
	}

	private void loadNotesTab(List<NotesTabBean> noteBeanList, GtnWsRebateScheduleInfoBean rebateScheduleInfoBean) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").refreshNotesTab();
		List<NotesDTO> rsNotesDTOList = new ArrayList<>();
		List<Object> finalList = new ArrayList<>();
		NotesDTO notesDTO;
		if (noteBeanList != null) {
			for (NotesTabBean notesTabBean : noteBeanList) {
				notesDTO = new NotesDTO();
				notesDTO.setDocDetailsId(notesTabBean.getMasterTableSystemId());
				String filePath = notesTabBean.getFilePath();
				notesDTO.setDocumentFullPath(filePath);
				notesDTO.setDocumentName("--");
				String tempfilePath = notesTabBean.getFilePath();
				notesDTO.setDocumentFullPath(tempfilePath);
				String fileName = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
				notesDTO.setDocumentName(fileName);
				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				TimeZone central = TimeZone.getTimeZone("CST");
				format.setTimeZone(central);
				notesDTO.setDateAdded(format.format(notesTabBean.getCreatedDate()));
				notesDTO.setUserId(notesTabBean.getCreatedBy());
				notesDTO.setUserName(String.valueOf(notesTabBean.getCreatedByName()));
				rsNotesDTOList.add(notesDTO);

			}
		}
		finalList.add(rebateScheduleInfoBean.getInternalNotes());
		finalList.add(rsNotesDTOList);
		GtnUIFrameworkGlobalUI.loadNotesTab(finalList, "notesTab");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkRSLoadAction();
	}

	@SuppressWarnings("rawtypes")
	private void loadIfpSelectedTable(GtnWsRebateScheduleInfoBean rebateScheduleInfoBean) {

		List<Object> dataList = rebateScheduleInfoBean.getIfpDataList();
		GtnWsRecordBean dto = new GtnWsRecordBean();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData("RSrightResultTable");

		List<Object> colList = new ArrayList<>();
		colList.addAll(dataList);
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		dto.setRecordHeader(tableLogic.getRecordHeader());
		dto.setProperties(colList);

		tableLogic.configureContainer(dto, tableLogic.getContainerDataSource());
		((ExtPagedTable) componentData.getCustomData()).select(dto);

	}

	private void setValuesToFields(String componentId, GtnWsRebateScheduleInfoBean rebateScheduleInfoBean)
			throws GtnFrameworkGeneralException {
		GtnFrameworkRSCommonLogic.setValuesToFields(componentId, rebateScheduleInfoBean);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsUDC1());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC2", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsUDC2());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC3", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsUDC3());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC4", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsUDC4());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC5", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsUDC5());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC6", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsUDC6());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationRule", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getCalculationRule().equals("null") ? ""
						: rebateScheduleInfoBean.getCalculationRule());
		GtnUIFrameWorkCalculationTypeChangeAction changeAction = new GtnUIFrameWorkCalculationTypeChangeAction();
		changeAction.doAction(componentId, null);
	}

	private void setTableHeaderAndVisibleColumn(boolean isEditable) throws GtnFrameworkValidationFailedException {
		if (isEditable) {
			String calculationType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationType1")
					.getCaptionFromComboBox();
			String[] tableHeader;
			Object[] visibleColumns;
			if (calculationType.equals("Rebate Plan")) {
				tableHeader = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeRebatePlanTableHeaders();
				visibleColumns = GtnFrameworkRSConstants.getRsSetUPTabCalculationTypeRebatePlanVisibleColumns();
			} else if (calculationType.equals("Formula")) {
				tableHeader = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeFormulaTableHeaders();
				visibleColumns = GtnFrameworkRSConstants.getRsSetUPTabCalculationTypeFormulaVisibleColumns();
			} else if (calculationType.equals("Deduction Calendar")) {
				tableHeader = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeDeductionCalendarTableHeaders();
				visibleColumns = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypeDeductionCalendarVisibleColumns();
			} else {
				tableHeader = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypePriceProtectionTableHeaders();
				visibleColumns = GtnFrameworkRSConstants.getRsSetUpTabCalculationTypePriceProtectionVisibleColumns();
			}
			GtnUIFrameworkBaseComponent rsRebateSetupTabResultDataTable = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE);
			rsRebateSetupTabResultDataTable.setTableColumns(visibleColumns);
			rsRebateSetupTabResultDataTable.setTableColumnHeaders(tableHeader);
		} else {
			GtnUIFrameworkBaseComponent rsRebateSetupTabResultDataTableForView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE);
			rsRebateSetupTabResultDataTableForView
					.setTableColumns(GtnFrameworkRSConstants.getRsSetupTabVisibleColumnsView());
			rsRebateSetupTabResultDataTableForView
					.setTableColumnHeaders(GtnFrameworkRSConstants.getRsSetupTabVisibleHeadersView());
		}

	}

	private void setTableEnableDisable(boolean isEditable) {

		if (isEditable) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setEnabled(isEditable);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setReadOnly(!isEditable);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE)
					.getExtPagedTable().setEditable(isEditable);
		}

	}

}
