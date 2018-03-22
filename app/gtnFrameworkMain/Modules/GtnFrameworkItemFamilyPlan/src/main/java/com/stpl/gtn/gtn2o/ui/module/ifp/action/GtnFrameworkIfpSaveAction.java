package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;

public class GtnFrameworkIfpSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private List<String> fields = null;
	private List<String> beanFields = null;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpSaveAction.class);

	@SuppressWarnings("unchecked")
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		this.fields = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		this.beanFields = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnIFamilyPlanBean cfpBean = setProperties();
			cfpBean.getIfpInfo().setCreatedBy(
					(String) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID));
			cfpBean.getIfpInfo().setModifiedBy(
					(String) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID));
			GtnWsIfpRequest cfpRequest = new GtnWsIfpRequest();

			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
			generalWSRequest.setSessionId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
			request.setGtnWsGeneralRequest(generalWSRequest);
			Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid");
			if (systemId != null && systemId > 0) {
				cfpBean.getIfpInfo().setIfpSid(systemId);
			}
			cfpRequest.setGtnIFamilyPlan(cfpBean);
			request.setGtnWsIfpRequest(cfpRequest);
			GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE + GtnWsIFamilyPlanContants.GTN_WS_IFP_SAVE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("ifpItemsTabResultDataTable").getLogicFromPagedDataTable();

			logic.startSearchProcess(null, Boolean.TRUE);

			GtnUIFrameworkPagedTableLogic logic2 = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE)
					.getLogicFromPagedDataTable();

			logic2.startSearchProcess(null, Boolean.TRUE);
			TabSheet sheet = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifptabSheet").getAsTabSheet();
			sheet.setSelectedTab(sheet.getTab(0));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInfoifpId", componentId).loadFieldValue(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPId").getStringFromField());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInfoifpNo", componentId).loadFieldValue(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPNo").getStringFromField());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInfoifpName", componentId).loadFieldValue(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPName").getStringFromField());

			Component component = GtnUIFrameworkGlobalUI.getVaadinComponent("ifpAddSaveButton");
			component.setCaption("UPDATE");
			component = GtnUIFrameworkGlobalUI.getVaadinComponent("ifpAddDeleteButton");
			component.setVisible(true);
			GtnUIFrameworkGlobalUI.addSessionProperty("ifpModelSid",
					reponse.getGtnWsIfpReponse().getGtnIFamilyPlan().getIfpInfo().getIfpSid());
			final Notification notif = new Notification(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationTabIFPName").getStringFromField()
							+ " has been successfully saved",
					Notification.Type.HUMANIZED_MESSAGE);

			notif.setPosition(Position.TOP_CENTER);
			notif.setStyleName(GtnFrameworkCssConstants.MY_STYLE);
			notif.setDelayMsec(3000);
			notif.show(Page.getCurrent());

		} catch (GtnFrameworkValidationFailedException e) {
			logger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}

	}

	private GtnIFamilyPlanBean setProperties() throws GtnFrameworkGeneralException {
		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean infoBean = new GtnIFamilyPlanInformationBean();
		ifpBean.setIfpInfo(infoBean);
		for (int i = 0; i < fields.size(); i++) {
			String string = fields.get(i);
			try {
				Field field = GtnIFamilyPlanInformationBean.class.getDeclaredField(beanFields.get(i));
				field.setAccessible(true);
				field.set(infoBean, GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).getObjectFromField());
			} catch (Exception ex) {
				logger.error("Error while Setting the property for the field : " + string + " with filed :"
						+ beanFields.get(i) + GtnFrameworkCommonStringConstants.STRING_EMPTY, ex);
			}
		}
		List<NotesTabBean> noteBeanList = new ArrayList<>();
		ifpBean.setNotesTabList(noteBeanList);
		List<Object> notesList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

		ifpBean.getIfpInfo().setInternalNotes((String) notesList.get(0));
		@SuppressWarnings("unchecked")
		List<NotesDTO> notesDTOList = (List<NotesDTO>) notesList.get(1);

		loadNotesTab(noteBeanList, notesDTOList);
		return ifpBean;
	}

	private void loadNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean ifpNotesBean;
			for (NotesDTO note : notesDTOs) {
				ifpNotesBean = new NotesTabBean();
				ifpNotesBean.setMasterTableName("IFP_MODEL");
				ifpNotesBean.setMasterTableSystemId(note.getDocDetailsId());
				ifpNotesBean.setFilePath(note.getDocumentFullPath());
				ifpNotesBean.setFileName(note.getDocumentName());
				ifpNotesBean.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				ifpNotesBean.setCreatedDate(new Date());
				noteBeanList.add(ifpNotesBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkSkipActionException("Save Error", systemExcption);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
