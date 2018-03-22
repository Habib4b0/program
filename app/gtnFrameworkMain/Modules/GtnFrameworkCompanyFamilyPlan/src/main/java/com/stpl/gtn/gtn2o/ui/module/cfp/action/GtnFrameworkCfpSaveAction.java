package com.stpl.gtn.gtn2o.ui.module.cfp.action;

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
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;

public class GtnFrameworkCfpSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<String> fieldList = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
			List<String> beanFieldList = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnCFamilyPlan cfpBean = setProperties(fieldList, beanFieldList);
			GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();

			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("cfpModelSid");
			if (systemId != null && systemId > 0) {
				cfpBean.getCfpInfo().setCfpSid(systemId);
			}
			cfpRequest.setGtnCFamilyPlan(cfpBean);
			request.setGtnWsCfpRequest(cfpRequest);
			GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE + GtnWsCFamilyPlanContants.GTN_WS_CFP_SAVE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (reponse.getGtnWsGeneralResponse() != null && reponse.getGtnWsGeneralResponse().isSucess()) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpId", componentId).loadFieldValue(
						GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCFPId").getStringFromField());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpNo", componentId).loadFieldValue(
						GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCFPNo").getStringFromField());
				String cfpName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationTabCFPName")
						.getStringFromField();
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpName", componentId).loadFieldValue(cfpName);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpAddSaveButton").setCaption("UPDATE");
				GtnUIFrameworkGlobalUI.addSessionProperty("cfpModelSid",
						reponse.getGtnWsCfpReponse().getGtnCFamilyPlan().getCfpInfo().getCfpSid());
				GtnUIFrameworkPagedTableLogic logic1 = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("CFPrightResultTable").getLogicFromPagedDataTable();

				logic1.startSearchProcess(null, Boolean.TRUE);
				GtnUIFrameworkPagedTableLogic logic2 = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getLogicFromPagedDataTable();

				logic2.startSearchProcess(null, Boolean.TRUE);
				TabSheet sheet = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpTabSheet").getAsTabSheet();
				sheet.setSelectedTab(sheet.getTab(0));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpAddDeleteButton", componentId)
						.setComponentVisible(true);

				final Notification notif = new Notification(cfpName + " has been successfully saved",
						Notification.Type.HUMANIZED_MESSAGE);

				notif.setPosition(Position.TOP_CENTER);
				notif.setStyleName(GtnFrameworkCssConstants.MY_STYLE);
				notif.setDelayMsec(3000);
				notif.show(Page.getCurrent());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		}

	}

	private GtnCFamilyPlan setProperties(List<String> fieldList, List<String> beanFieldList)
			throws GtnFrameworkGeneralException {
		GtnCFamilyPlan cfpBean = new GtnCFamilyPlan();
		GtnCFamilyPlanInformation infoBean = new GtnCFamilyPlanInformation();
		cfpBean.setCfpInfo(infoBean);
		for (int i = 0; i < fieldList.size(); i++) {
			String componentId = fieldList.get(i);
			try {
				Field field = GtnCFamilyPlanInformation.class.getDeclaredField(beanFieldList.get(i));
				field.setAccessible(true);
				field.set(infoBean, GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getObjectFromField());
			} catch (Exception ex) {
				logger.error("Error while Setting the property for the field : " + componentId + " with filed :"
						+ beanFieldList.get(i) + GtnFrameworkCommonStringConstants.STRING_EMPTY, ex);
			}
		}

		List<NotesTabBean> noteBeanList = new ArrayList<>();
		cfpBean.setNotesTabList(noteBeanList);
		List<Object> notesList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

		cfpBean.getCfpInfo().setInternalNotes((String) notesList.get(0));
		@SuppressWarnings("unchecked")
		List<NotesDTO> notesDTOList = (List<NotesDTO>) notesList.get(1);

		loadNotesTab(noteBeanList, notesDTOList);
		return cfpBean;
	}

	private void loadNotesTab(List<NotesTabBean> cfpNotesBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean cfpNotesBean;
			for (NotesDTO note : notesDTOs) {
				cfpNotesBean = new NotesTabBean();
				cfpNotesBean.setMasterTableName("CFP_MODEL");
				cfpNotesBean.setMasterTableSystemId(note.getDocDetailsId());
				cfpNotesBean.setFilePath(note.getDocumentFullPath());
				cfpNotesBean.setFileName(note.getDocumentName());
				cfpNotesBean.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				cfpNotesBean.setCreatedDate(new Date());
				cfpNotesBeanList.add(cfpNotesBean);
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
