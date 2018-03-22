/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkPSSaveAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		saveToDb();
	}

	private int getSystemId() {
		String mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode").toString();
		int sysId = 0;
		if (mode.equalsIgnoreCase("Edit")) {
			sysId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID);
		}
		return sysId;

	}

	@SuppressWarnings("unchecked")
	private void saveToDb() throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		try {

			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
			request.setGtnWsGeneralRequest(generalWSRequest);

			int sysId = getSystemId();

			GtnUIFrameWorkPSInfoBean priceScheduleInfoBean = new GtnUIFrameWorkPSInfoBean();
			priceScheduleInfoBean.setSystemId(sysId);
			List<NotesTabBean> noteBeanList = new ArrayList<>();

			loadPriceScheduleInfo(priceScheduleInfoBean);

			
			List<Object> notes = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

			priceScheduleInfoBean.setInternalNotes((String) notes.get(0));
			List<NotesDTO> notesDTOs = (List<NotesDTO>) notes.get(1);

			loadNotesTab(noteBeanList, notesDTOs);
			priceScheduleInfoBean.setNoteBeanList(noteBeanList);
			loadNonEditableField(priceScheduleInfoBean);

			GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = new GtnWsPriceScheduleGeneralRequest();

			getGtnWsPriceScheduleGeneralRequest.setPsInfoBean(priceScheduleInfoBean);
			request.setGtnWsPriceScheduleGeneralRequest(getGtnWsPriceScheduleGeneralRequest);

			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					"/" + GtnWsCDRContants.PS_SERVICE + GtnWsCDRContants.PS_SAVE_SERVICE, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID,
					response.getGtnWsPriceScheduleGeneralResponse().getPriceScheduleInfoBean().getSystemId());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").setSelectedTab("priceScheduleInfoTab");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleAddViewAAddDeleteButton").setVisible(true);

		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadPriceScheduleInfo(final GtnUIFrameWorkPSInfoBean psInfoBean) throws GtnFrameworkGeneralException {
		try {

			psInfoBean.setPsId(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleId1").getStringFromField());
			psInfoBean.setPsNo(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNo1").getStringFromField());
			psInfoBean.setPsName(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1").getStringFromField());
			psInfoBean.setParentpSNo(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleName").getStringFromField());
			psInfoBean.setParentPsId(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentPriceScheduleID").getStringFromField());

			psInfoBean.setParentPsSid(getIdFromComponent("parentPriceScheduleID"));

			psInfoBean.setPsStatus(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStatus1").getIntegerFromField());
			psInfoBean.setPsDesignation(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleDesignation").getIntegerFromField());
			psInfoBean.setPsType(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleType1").getIntegerFromField());
			psInfoBean.setPsCategory(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleCategory").getIntegerFromField());
			psInfoBean.setPsTradeClass(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleTradeClass").getIntegerFromField());

			psInfoBean.setPsStartDate(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStartDate").getDateFromDateField());
			psInfoBean.setPsEndDate(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleEndDate").getDateFromDateField());

		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean notesBean;
			for (NotesDTO note : notesDTOs) {
				notesBean = new NotesTabBean();
				notesBean.setMasterTableName("PS_MODEL");
				notesBean.setFilePath(note.getDocumentFullPath());
				notesBean.setMasterTableSystemId(note.getDocDetailsId());
				notesBean.setFileName(note.getDocumentName());
				notesBean.setCreatedBy(note.getUserId());
				notesBean.setCreatedDate(new Date());
				noteBeanList.add(notesBean);
			}
		} catch (Exception systemExcption) {

			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private String getIdFromComponent(String componentId) throws GtnFrameworkGeneralException {

		if (componentId != null) {
			return "0";
		}
		AbstractComponent vaadinField = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		Object value = vaadinField.getData();
		if (value == null) {
			return "0";
		}
		try {
			if (value instanceof GtnWsRecordBean) {
				GtnWsRecordBean dto = (GtnWsRecordBean) value;
				return dto.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID).toString();

			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, ex);
		}
		return "0";
	}

	private void loadNonEditableField(GtnUIFrameWorkPSInfoBean priceScheduleInfoBean) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleIdTop")
				.setPropertyValue(priceScheduleInfoBean.getPsId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNoTop")
				.setPropertyValue(priceScheduleInfoBean.getPsNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNameTop")
				.setPropertyValue(priceScheduleInfoBean.getPsName());

	}

}
