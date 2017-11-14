package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkCompanyMasterSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCompanyMasterSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		try {

			GtnCMasterBean companyMasterBean = new GtnCMasterBean();
			Integer systemId = (Integer) GtnUIFrameworkGlobalUI
					.getSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID);

			GtnCMasterInformationBean companyInformation = new GtnCMasterInformationBean();
			GtnCMasterCompanyUdcInfoBean udcInfo = new GtnCMasterCompanyUdcInfoBean();
			GtnCMasterRequest companyMasterRequest = new GtnCMasterRequest();
			GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();

			List<GtnCMasterIdentifierInfoBean> identifierResultList = new ArrayList<>();
			List<GtnCMasterCompanyParentBean> parentCompanyResult = new ArrayList<>();
			List<GtnCMasterCompanyTradeClassBean> tradeClassResultList = new ArrayList<>();
			List<NotesTabBean> noteBeanList = new ArrayList<>();
			List<GtnCMasterFinancialCloseBean> financialCloseBeanList = new ArrayList<>();

			setInformationTabValues(companyInformation, systemId);

			setAddressInfoValues(companyInformation);

			setUdcValues(udcInfo);

			setIdentifierTabValues(identifierResultList);

			setTradeClassValues(tradeClassResultList);

			setParentCompanyValues(parentCompanyResult);

			setNotesTabValues(companyInformation, noteBeanList);

			companyMasterBean.setGtnCMasterInformationBean(companyInformation);
			companyMasterBean.setGtnCMasterIdentifierInfoBeanList(identifierResultList);
			companyMasterBean.setGtnCMasterCompanyTradeClassBeanList(tradeClassResultList);
			companyMasterBean.setGtnCMasterCompanyParentBeanList(parentCompanyResult);
			companyMasterBean.setGtnCMasterCompanyUdcInfoBean(udcInfo);
			companyMasterBean.setGtnCMasterCompanyNotesTabBeanList(noteBeanList);

			if (systemId != null && systemId > 0) {
				companyMasterBean.getGtnCMasterInformationBean().setCompanyMasterSystemId(systemId);
				companyMasterBean.getGtnCMasterInformationBean()
						.setModifiedBy(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
				for (GtnCMasterIdentifierInfoBean gtnCMasterIdentifierInfoBean : companyMasterBean
						.getGtnCMasterIdentifierInfoBeanList()) {
					gtnCMasterIdentifierInfoBean
							.setModifiedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
					gtnCMasterIdentifierInfoBean.setModifiedDate(new Date());
				}
				for (GtnCMasterCompanyTradeClassBean gtnCMasterCompanyTradeClassBean : companyMasterBean
						.getGtnCMasterCompanyTradeClassBeanList()) {
					gtnCMasterCompanyTradeClassBean
							.setModifiedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
					gtnCMasterCompanyTradeClassBean.setModifiedDate(new Date());
				}
				for (GtnCMasterCompanyParentBean gtnCMasterCompanyParentBean : companyMasterBean
						.getGtnCMasterCompanyParentBeanList()) {
					gtnCMasterCompanyParentBean
							.setModifiedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
					gtnCMasterCompanyParentBean.setModifiedDate(new Date());

				}

			}

			companyMasterBean.setGtnCMasterFinancialCloseBean(financialCloseBeanList);

			companyMasterRequest.setGtnCMasterBean(companyMasterBean);
			request.setGtnCMasterRequest(companyMasterRequest);
			request.setGtnWsGeneralRequest(generalRequest);

			setFinancialCloseTabValues(financialCloseBeanList, request, companyMasterBean, companyMasterRequest);
			companyMasterBean.setGtnCMasterFinancialCloseBean(financialCloseBeanList);

			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER + GtnWebServiceUrlConstants.GTN_WS_CM_SAVE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID,
					response.getGtnCompanyMasterResponse().getGtnCMasterBean().getGtnCMasterInformationBean()
							.getCompanyMasterSystemId());

			if (systemId == null || systemId == 0) {
				insertInCPDeatils(Integer.parseInt(GtnUIFrameworkGlobalUI
						.getSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID).toString()));
			}
			loadAfterSave(companyInformation, componentId, response.getGtnCompanyMasterResponse().getGtnCMasterBean());

		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
			throw new GtnFrameworkGeneralException("Save Error", exception);
		}

	}

	private void insertInCPDeatils(int companyMasterSid) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(companyMasterSid);
		request.setGtnWsGeneralRequest(generalWSRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER
						+ GtnWebServiceUrlConstants.GTN_WS_CP_DETAILS_FOR_COMPANY_INSERT_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	public void setFinancialCloseTabValues(List<GtnCMasterFinancialCloseBean> financialCloseBeanList,
			GtnUIFrameworkWebserviceRequest request, GtnCMasterBean companyMasterBean,
			GtnCMasterRequest companyMasterRequest) throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> totalData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseResultTable")
				.getItemsFromDataTable();
		@SuppressWarnings("rawtypes")
		List automaticList = (List) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseButton")
				.getComponentData().getCustomData();
		if (totalData != null && !totalData.isEmpty()) {
			GtnCMasterFinancialCloseBean financialcloseCompany = new GtnCMasterFinancialCloseBean();
			financialcloseCompany.setCompanyMasterSid((Integer) GtnUIFrameworkGlobalUI
					.getSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID));
			financialcloseCompany.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
			financialcloseCompany.setSessionId(
					Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId"))));
			financialCloseBeanList.add(financialcloseCompany);
			companyMasterBean.setGtnCMasterFinancialCloseBean(financialCloseBeanList);
		}
		if (request.getGtnCMasterRequest().getGtnCMasterBean().getGtnCMasterFinancialCloseBean() != null
				&& !request.getGtnCMasterRequest().getGtnCMasterBean().getGtnCMasterFinancialCloseBean().isEmpty()) {

			if (GtnFrameworkCompanyStringContants.UPDATE_STRING.equalsIgnoreCase(GtnUIFrameworkGlobalUI
					.getVaadinComponent(GtnFrameworkCompanyStringContants.COMPANY_ADD_SAVE_BUTTON).getCaption())) {
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
								+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_TEMP_EDIT_SELECT,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			} else {
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
								+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_MANUAL_CLOSE_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}
		}
		if (automaticList != null && !automaticList.isEmpty()) {
			financialCloseBeanList.clear();
			if (automaticList.get(0) != null) {
				GtnCMasterFinancialCloseBean masterFinancialCloseBean = (GtnCMasterFinancialCloseBean) automaticList
						.get(0);
				masterFinancialCloseBean.setCompanyMasterSid((Integer) GtnUIFrameworkGlobalUI
						.getSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID));
				financialCloseBeanList.add(masterFinancialCloseBean);
				companyMasterBean.setGtnCMasterFinancialCloseBean(financialCloseBeanList);
				companyMasterRequest.setGtnCMasterBean(companyMasterBean);
				request.setGtnCMasterRequest(companyMasterRequest);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
								+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_AUTOMATIC_CLOSE_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			}
		}
	}

	public void setNotesTabValues(GtnCMasterInformationBean companyInformation,
			List<NotesTabBean> noteBeanList) throws GtnFrameworkGeneralException {
		List<Object> notes = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();
		companyInformation.setInternalNotes((String) notes.get(0));
		@SuppressWarnings("unchecked")
		List<NotesDTO> notesDTOs = (List<NotesDTO>) notes.get(1);

		for (NotesDTO note : notesDTOs) {
			NotesTabBean notesBean = new NotesTabBean();
			notesBean.setMasterTableName("COMPANY_MASTER");
			notesBean.setFilePath(note.getDocumentFullPath());
			notesBean.setCreatedBy(note.getUserId());
			notesBean.setCreatedDate(new Date());
			noteBeanList.add(notesBean);
		}
	}

	public void setParentCompanyValues(List<GtnCMasterCompanyParentBean> parentCompanyResult)
			throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> parentCompanyList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("parentCompanyattachResultTable").getItemsFromDataTable();
		for (GtnWsRecordBean parentCompany1 : parentCompanyList) {
			GtnCMasterCompanyParentBean parentCompany = new GtnCMasterCompanyParentBean();
			parentCompany.setParentCompanyMasterSystemId(Integer.parseInt(
					String.valueOf(parentCompany1.getPropertyValueByIndex(parentCompany1.getProperties().size() - 1))));
			parentCompany.setCompanyParentStartDate(parentCompany1.getDateProperty("parentCompanyStartDate"));
			parentCompany.setCompanyParentEndDate(parentCompany1.getDateProperty("parentCompanyEndDate"));
			parentCompany.setCreatedBy(Integer.parseInt(String.valueOf(parentCompany1
					.getPropertyValueByIndex(parentCompany1.getProperties().size() - GtnWsNumericConstants.TWO))));
			parentCompany.setCreatedDate(parentCompany1.getDateProperty("createdDate"));
			parentCompany.setModifiedBy(0);
			parentCompany.setModifiedDate(parentCompany1.getDateProperty("modifiedDate"));
			parentCompanyResult.add(parentCompany);
		}
	}

	public void setTradeClassValues(List<GtnCMasterCompanyTradeClassBean> tradeClassResultList)
			throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> tradeClassList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("tradeClassattachResultTable").getItemsFromDataTable();

		for (GtnWsRecordBean tradeClass1 : tradeClassList) {
			GtnCMasterCompanyTradeClassBean tradeClass = new GtnCMasterCompanyTradeClassBean();
			Object tradeClassSid = tradeClass1.getPropertyValueByIndex(tradeClass1.getProperties().size() - 1);
			tradeClass.setCompanyTradeClassSid(tradeClassSid == null ? 0 : (int) tradeClassSid);
			tradeClass.setCompanyTradeClassValue(tradeClass1.getStringProperty("tradeClass"));
			tradeClass.setCompanyTradeClassStartDate(tradeClass1.getDateProperty("tradeClassStartDate"));
			tradeClass.setCompanyTradeClassEndDate(tradeClass1.getDateProperty("tradeClassEndDate"));
			tradeClass.setCreatedBy(Integer.parseInt(String.valueOf(tradeClass1
					.getPropertyValueByIndex(tradeClass1.getProperties().size() - GtnWsNumericConstants.TWO))));
			tradeClass.setCreatedDate(tradeClass1.getDateProperty("createdDate"));
			tradeClass.setModifiedBy(0);
			tradeClass.setModifiedDate(tradeClass1.getDateProperty("modifiedDate"));
			tradeClassResultList.add(tradeClass);
		}
	}

	public void setIdentifierTabValues(List<GtnCMasterIdentifierInfoBean> identifierResultList)
			throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> identifierList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("identifierattachResultTable").getItemsFromDataTable();

		for (GtnWsRecordBean identifierList1 : identifierList) {
			GtnCMasterIdentifierInfoBean identifier = new GtnCMasterIdentifierInfoBean();
			Object qualifierSid = identifierList1.getPropertyValueByIndex(identifierList1.getProperties().size() - 3);
			identifier.setCompanyQualifierSid(qualifierSid == null ? 0 : (int) qualifierSid);
			identifier.setCompanyQualifierValue(identifierList1.getStringProperty("qualifierName"));
			identifier.setCompanyIdentifierValue(identifierList1.getStringProperty("identifier"));
			identifier.setIdentifierStatus(identifierList1.getIntegerProperty("identifierStatus"));
			identifier.setCompanyIdentifierStartDate(identifierList1.getDateProperty("startDate"));
			identifier.setCompanyIdentifierEndDate(identifierList1.getDateProperty("endDate"));
			String userCreated = String.valueOf(identifierList1
					.getPropertyValueByIndex(identifierList1.getProperties().size() - GtnWsNumericConstants.ONE));
			identifier.setCreatedBy(Integer.valueOf(userCreated));
			identifier.setCreatedDate(identifierList1.getDateProperty(GtnFrameworkCompanyStringContants.CREATED_DATE));
			identifier.setModifiedBy(0);
			identifier
					.setModifiedDate(identifierList1.getDateProperty(GtnFrameworkCompanyStringContants.MODIFIED_DATE));
			identifierResultList.add(identifier);
		}
	}

	public void setUdcValues(GtnCMasterCompanyUdcInfoBean udcInfo) throws GtnFrameworkGeneralException {
		udcInfo.setUdc1(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc1").getIntegerFromField());
		udcInfo.setUdc2(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc2").getIntegerFromField());
		udcInfo.setUdc3(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc3").getIntegerFromField());
		udcInfo.setUdc4(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc4").getIntegerFromField());
		udcInfo.setUdc5(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc5").getIntegerFromField());
		udcInfo.setUdc6(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc6").getIntegerFromField());
	}

	public void setAddressInfoValues(GtnCMasterInformationBean companyInformation) throws GtnFrameworkGeneralException {
		companyInformation
				.setAddress1(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAdderss1").getStringFromField());
		companyInformation
				.setCity(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCity").getStringFromField());
		companyInformation
				.setZipCode(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabZipCode").getStringFromField());
		companyInformation
				.setCountry(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCountry").getIntegerFromField());
		companyInformation
				.setAddress2(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAddress2").getStringFromField());
		companyInformation
				.setState(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabState").getIntegerFromField());
		companyInformation.setRegionCode(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabRegionCode").getStringFromField());
	}

	public void setInformationTabValues(GtnCMasterInformationBean companyInformation, Integer systemId)
			throws GtnFrameworkGeneralException {
		companyInformation.setCompanyId(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyId").getStringFromField());
		companyInformation.setCompanyNo(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyNo").getStringFromField());
		companyInformation.setCompanyName(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyName").getStringFromField());
		companyInformation.setCompanyStatus(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationTabCompanyStatus").getIntegerFromField());
		companyInformation.setCompanyStartDate(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationCompanyStartDate").getDateFromDateField());
		companyInformation.setCompanyEndDate(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationCompanyEndDate").getDateFromDateField());
		companyInformation.setCompanyCategory(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationTabCompanyCategory").getIntegerFromField());
		companyInformation.setCompanyGroup(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationTabCompanyGroup").getIntegerFromField());
		companyInformation.setCompanyType(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationTabCompanyType").getIntegerFromField());
		companyInformation.setOrganizationKey(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationTabOrganizationKey").getIntegerFromField());
		companyInformation.setSource(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSource").getStringFromField());
		companyInformation.setFinancialSystem(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("companyInformationTabFinancialSystem").getStringFromField());
		companyInformation.setCompanyMasterSystemId(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSystemId").getIntegerFromField());
		String userCreatedBy = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedByID")
				.getStringFromField();

		String userId = (systemId != null && systemId > 0) ? userCreatedBy : GtnUIFrameworkGlobalUI.getCurrentUser();
		companyInformation.setCreatedBy(Integer.valueOf(userId));

		companyInformation.setCreatedDate(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCreatedDate").getDateFromDateField());
		companyInformation.setModifiedBy(
				(systemId != null && systemId > 0) ? Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()) : 0);
		companyInformation.setModifiedDate(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate").getDateFromDateField());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCompanyMasterSaveAction();
	}

	private void loadAfterSave(GtnCMasterInformationBean companyInformation, String componentId,
			GtnCMasterBean gtnCMasterBean) throws GtnFrameworkGeneralException {
		if (GtnFrameworkCompanyStringContants.UPDATE_STRING.equalsIgnoreCase(GtnUIFrameworkGlobalUI
				.getVaadinComponent(GtnFrameworkCompanyStringContants.COMPANY_ADD_SAVE_BUTTON).getCaption())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate").loadDateValue(new Date());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabModifiedBy").loadFieldValue(
					getCurrentUserName(String.valueOf(gtnCMasterBean.getGtnCMasterInformationBean().getModifiedBy())));
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").setSelectedTabByPostion(0);
		GtnUIFrameworkGlobalUI.getVaadinComponent(GtnFrameworkCompanyStringContants.COMPANY_ADD_SAVE_BUTTON)
				.setCaption(GtnFrameworkCompanyStringContants.UPDATE_STRING);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationcompanyId")
				.loadFieldValue(companyInformation.getCompanyId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationcompanyNo")
				.loadFieldValue(companyInformation.getCompanyNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationcompanyName")
				.loadFieldValue(companyInformation.getCompanyName());

		GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		notificationAction.addActionParameter(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyId").getStringFromField()
						+ " has been successfully saved");
		notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyMasterAddDeleteButton").setVisible(true);

	}

	private String getCurrentUserName(String userId) {
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
		return String.valueOf(userName);
	}

}
