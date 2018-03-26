package com.stpl.gtn.gtn2o.ui.company.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;

public class GtnUIFrameWorkCompanyMasterLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCompanyMasterAddResetAction.class);

	private GtnCMasterInformationBean companyInformation = null;
	private GtnCMasterCompanyUdcInfoBean udcInfo = null;
	private List<GtnCMasterIdentifierInfoBean> identifierBeanList = null;
	private List<GtnCMasterCompanyTradeClassBean> tradeClassInfoList = null;
	private List<GtnCMasterCompanyParentBean> parentCompanyInfoList = null;
	private List<NotesTabBean> loadNotesTabBeanList = null;
	private int systemId = 0;
	private String propertyId = null;

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		this.propertyId = (String) parameters.get(GtnWsNumericConstants.TWO);

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<String> searchWhereCondition = new ArrayList<>();
			GtnWsRecordBean gtnWsRecordBean = getValueFromTable(gtnUIFrameWorkActionConfig);

			systemId = Integer.parseInt(String.valueOf(gtnWsRecordBean.getPropertyValue(propertyId)));
			GtnUIFrameworkGlobalUI.addSessionProperty("companyMasterSid", systemId);
			configureParams(gtnUIFrameWorkActionConfig);

			loadDataFromService();
			companyInformation = getCompanyInformation();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationcompanyId", componentId)
					.loadFieldValue(companyInformation.getCompanyId());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationcompanyNo", componentId)
					.loadFieldValue(companyInformation.getCompanyNo());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationcompanyName", componentId)
					.loadFieldValue(companyInformation.getCompanyName());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyId", componentId)
					.loadFieldValue(companyInformation.getCompanyId());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyNo", componentId)
					.loadFieldValue(companyInformation.getCompanyNo());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyName", componentId)
					.loadFieldValue(companyInformation.getCompanyName());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyStatus", componentId)
					.loadComboBoxComponentValue(companyInformation.getCompanyStatus());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyStartDate", componentId)
					.loadDateValue(companyInformation.getCompanyStartDate());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyEndDate", componentId)
					.loadDateValue(companyInformation.getCompanyEndDate());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyCategory", componentId)
					.loadComboBoxComponentValue(companyInformation.getCompanyCategory());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyGroup", componentId)
					.loadComboBoxComponentValue(companyInformation.getCompanyGroup());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyType", componentId)
					.loadComboBoxComponentValue(companyInformation.getCompanyType());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabOrganizationKey", componentId)
					.loadComboBoxComponentValue(companyInformation.getOrganizationKey());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSource", componentId)
					.loadFieldValue(companyInformation.getSource());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabFinancialSystem", componentId)
					.loadFieldValue(companyInformation.getFinancialSystem());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSystemId", componentId)
					.loadFieldValue(companyInformation.getCompanyMasterSystemId());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedBy", componentId)
					.loadFieldValue(companyInformation.getCreatedByName());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedByID", componentId)
					.loadFieldValue(companyInformation.getCreatedBy());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCreatedDate", componentId)
					.loadDateValue(companyInformation.getCreatedDate());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabModifiedBy", componentId)
					.loadFieldValue(companyInformation.getModifiedByName());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate", componentId)
					.loadDateValue(companyInformation.getModifiedDate());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAdderss1", componentId)
					.loadFieldValue(companyInformation.getAddress1());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCity", componentId)
					.loadFieldValue(companyInformation.getCity());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabZipCode", componentId)
					.loadFieldValue(companyInformation.getZipCode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCountry", componentId)
					.loadComboBoxComponentValue(companyInformation.getCountry());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAddress2", componentId)
					.loadFieldValue(companyInformation.getAddress2());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabState", componentId)
					.loadComboBoxComponentValue(companyInformation.getState());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabRegionCode", componentId)
					.loadFieldValue(companyInformation.getRegionCode());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedBy", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCreatedDate", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabModifiedBy", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSystemId", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSource", componentId)
					.setComponentEnable(false);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userId", componentId)
					.loadFieldValue(GtnUIFrameworkGlobalUI.getCurrentUser());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("SessionId", componentId)
					.loadFieldValue(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));

			if (getUdcInfo() != null) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc1", componentId)
						.loadComboBoxComponentValue(getUdcInfo().getUdc1() != null ? getUdcInfo().getUdc1() : 0);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc2", componentId)
						.loadComboBoxComponentValue(getUdcInfo().getUdc2() != null ? getUdcInfo().getUdc2() : 0);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc3", componentId)
						.loadComboBoxComponentValue(getUdcInfo().getUdc3() != null ? getUdcInfo().getUdc3() : 0);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc4", componentId)
						.loadComboBoxComponentValue(getUdcInfo().getUdc4() != null ? getUdcInfo().getUdc4() : 0);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc5", componentId)
						.loadComboBoxComponentValue(getUdcInfo().getUdc5() != null ? getUdcInfo().getUdc5() : 0);
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC6, componentId)
						.loadComboBoxComponentValue(getUdcInfo().getUdc6() != null ? getUdcInfo().getUdc6() : 0);
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyMasterAddSaveButton").setCaption("UPDATE");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyMasterAddDeleteButton").setVisible(true);

			loadIdentifierInfo("identifierattachResultTable");
			loadTradeClassInfo("tradeClassattachResultTable");
			loadParentCompanyInfo("parentCompanyattachResultTable");
			loadNotesTab();

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("financialCloseResultTable").getLogicFromPagedDataTable();

			searchWhereCondition.add("userId");
			searchWhereCondition.add("SessionId");
			logic.startSearchProcess(searchWhereCondition, true);

		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
		}

	}

	private void loadDataFromService() {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		GtnCMasterBean companyMasterBean = new GtnCMasterBean();

		GtnCMasterInformationBean companyInformationBean = new GtnCMasterInformationBean();
		GtnCMasterFinancialCloseBean gtnCMasterFinancialCloseBean = new GtnCMasterFinancialCloseBean();
		List<GtnCMasterFinancialCloseBean> financialCLoseBean = new ArrayList<>();
		GtnCMasterRequest companyMasterRequest = new GtnCMasterRequest();
		companyInformationBean.setCompanyMasterSystemId(systemId);
		gtnCMasterFinancialCloseBean
				.setSessionId(Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId"))));
		gtnCMasterFinancialCloseBean.setUserId(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
		companyMasterBean.setGtnCMasterInformationBean(companyInformationBean);
		financialCLoseBean.add(gtnCMasterFinancialCloseBean);
		companyMasterBean.setGtnCMasterFinancialCloseBean(financialCLoseBean);
		companyMasterRequest.setGtnCMasterBean(companyMasterBean);
		request.setGtnCMasterRequest(companyMasterRequest);
		request.setGtnWsGeneralRequest(generalRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER + GtnWebServiceUrlConstants.GTN_CM_GET_DETAILS_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnCompanyMasterResponse companyMasterResponse = response.getGtnCompanyMasterResponse();
		GtnCMasterBean companyMasterResponseBean = companyMasterResponse.getGtnCMasterBean();
		setCompanyInformation(companyMasterResponseBean.getGtnCMasterInformationBean());
		setUdcInfo(companyMasterResponseBean.getGtnCMasterCompanyUdcInfoBean());
		setIdentifierBeanList(companyMasterResponseBean.getGtnCMasterIdentifierInfoBeanList());
		setTradeClassInfoList(companyMasterResponseBean.getGtnCMasterCompanyTradeClassBeanList());
		setParentCompanyInfoList(companyMasterResponseBean.getGtnCMasterCompanyParentBeanList());
		setLoadNotesTabBeanList(companyMasterResponseBean.getGtnCMasterCompanyNotesTabBeanList());
	}

	private void loadIdentifierInfo(String resultTableId) throws GtnFrameworkGeneralException {

		List<GtnWsRecordBean> resultLiist = new ArrayList<>();
		GtnWsRecordBean dto;
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId);
		ExtFilterTable identifiersultTable = component.getExtFilterTable();
		handleIdentifierTableColumn(component);
		@SuppressWarnings("unchecked")
		ExtContainer<GtnWsRecordBean> identifierContainer = (ExtContainer<GtnWsRecordBean>) identifiersultTable
				.getContainerDataSource();
		if (identifierBeanList != null && !identifierBeanList.isEmpty()) {
			for (GtnCMasterIdentifierInfoBean identifier : getIdentifierBeanList()) {
				dto = new GtnWsRecordBean();
				dto.setRecordHeader(identifierContainer.getRecordHeader());
				dto.addProperties("qualifierName", identifier.getCompanyQualifierValue()); // changed
				dto.addProperties("identifier", identifier.getCompanyIdentifierValue());
				dto.addProperties("identifierStatus", identifier.getIdentifierStatus());
				dto.addProperties("startDate", identifier.getCompanyIdentifierStartDate());
				dto.addProperties("endDate", identifier.getCompanyIdentifierEndDate());
				dto.addProperties(GtnFrameworkCompanyStringContants.CREATED_BY, identifier.getCreatedByName());
				dto.addProperties(GtnFrameworkCompanyStringContants.CREATED_DATE, identifier.getCreatedDate());
				dto.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_BY, identifier.getModifiedByName() == null
						? GtnFrameworkCommonStringConstants.STRING_EMPTY : identifier.getModifiedByName());
				dto.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_DATE, identifier.getModifiedDate());
				dto.getProperties().add(identifier.getCompanyQualifierSid());
				dto.getProperties().add(identifier.getIdentifierStatusValue());
				dto.getProperties().add(identifier.getCreatedBy());
				resultLiist.add(dto);
			}
			component.loadContainer(resultTableId, resultLiist);
		}
	}

	private void handleIdentifierTableColumn(GtnUIFrameworkBaseComponent resultTableidentifier) {
		resultTableidentifier.setTableColumns(GtnFrameworkCompanyStringContants.getIdentifierTabTableColumnList());
		resultTableidentifier
				.setTableColumnHeaders(GtnFrameworkCompanyStringContants.getIdentifierTabTableHeaderList());
		resultTableidentifier.resetFilter();
	}

	private void loadTradeClassInfo(String resultTableId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId);
		ExtFilterTable tradeResultTable = component.getExtFilterTable();
		@SuppressWarnings("unchecked")
		ExtContainer<GtnWsRecordBean> tradeContainer = (ExtContainer<GtnWsRecordBean>) tradeResultTable
				.getContainerDataSource();

		List<GtnWsRecordBean> resultLiist = new ArrayList<>();
		GtnWsRecordBean dto;
		if (tradeClassInfoList != null && !tradeClassInfoList.isEmpty()) {
			for (GtnCMasterCompanyTradeClassBean tradeClassInfo : getTradeClassInfoList()) {
				dto = new GtnWsRecordBean();
				dto.setRecordHeader(tradeContainer.getRecordHeader());
				dto.addProperties("tradeTabtradeClass", tradeClassInfo.getCompanyTradeClassValue());
				dto.addProperties("tradeClassStartDate", tradeClassInfo.getCompanyTradeClassStartDate());
				dto.addProperties("tradeClassEndDate", tradeClassInfo.getCompanyTradeClassEndDate());
				dto.addProperties(GtnFrameworkCompanyStringContants.CREATED_BY, tradeClassInfo.getCreatedByName());
				dto.addProperties(GtnFrameworkCompanyStringContants.CREATED_DATE, tradeClassInfo.getCreatedDate());
				dto.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_BY,
						tradeClassInfo.getModifiedByName() == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
								: tradeClassInfo.getModifiedByName());
				dto.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_DATE, tradeClassInfo.getModifiedDate());
				dto.addProperties(tradeClassInfo.getCreatedBy());
				dto.addProperties(tradeClassInfo.getCompanyTradeClassSid());
				resultLiist.add(dto);
			}
			component.loadContainer(resultTableId, resultLiist);
		}

	}

	private void loadParentCompanyInfo(String resultTableId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId);
		ExtFilterTable parentResultTable = component.getExtFilterTable();

		@SuppressWarnings("unchecked")
		ExtContainer<GtnWsRecordBean> parentContainer = (ExtContainer<GtnWsRecordBean>) parentResultTable
				.getContainerDataSource();
		List<GtnWsRecordBean> resultLiist = new ArrayList<>();
		GtnWsRecordBean dto;
		if (parentCompanyInfoList != null && !parentCompanyInfoList.isEmpty()) {
			for (GtnCMasterCompanyParentBean parentCompanyInfo : getParentCompanyInfoList()) {
				dto = new GtnWsRecordBean();
				dto.setRecordHeader(parentContainer.getRecordHeader());
				dto.addProperties("parentCompanyNo", parentCompanyInfo.getCompanyNo());
				dto.addProperties("parentCompanyName", parentCompanyInfo.getCompanyName());
				dto.addProperties("parentCompanyStartDate", parentCompanyInfo.getCompanyParentStartDate());
				dto.addProperties("parentCompanyEndDate", parentCompanyInfo.getCompanyParentEndDate());
				dto.addProperties(GtnFrameworkCompanyStringContants.CREATED_BY,
						parentCompanyInfo.getCreatedByName() == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
								: parentCompanyInfo.getCreatedByName());
				dto.addProperties(GtnFrameworkCompanyStringContants.CREATED_DATE, parentCompanyInfo.getCreatedDate());
				dto.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_BY,
						parentCompanyInfo.getModifiedByName() == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
								: parentCompanyInfo.getModifiedByName());
				dto.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_DATE, parentCompanyInfo.getModifiedDate());
				dto.getProperties().add(parentCompanyInfo.getCreatedBy());
				dto.getProperties().add(parentCompanyInfo.getParentCompanyMasterSystemId());
				resultLiist.add(dto);
			}

			component.loadContainer(resultTableId, resultLiist);
		}

	}

	private void loadNotesTab() {

		List<NotesDTO> notesDTOList = new ArrayList<>();
		List<Object> result = new ArrayList<>();
		NotesDTO companyMasterAttachmentDTO;
		if (loadNotesTabBeanList != null && !loadNotesTabBeanList.isEmpty()) {
			for (NotesTabBean companyMasterNotesTabBean : getLoadNotesTabBeanList()) {
				companyMasterAttachmentDTO = new NotesDTO();
				companyMasterAttachmentDTO.setDocDetailsId(companyMasterNotesTabBean.getMasterTableSystemId());
				String filePath = companyMasterNotesTabBean.getFilePath();
				companyMasterAttachmentDTO.setDocumentFullPath(filePath);
				companyMasterAttachmentDTO.setDocumentName("--");
				String tempfilePath = companyMasterNotesTabBean.getFilePath();
				companyMasterAttachmentDTO.setDocumentFullPath(tempfilePath);
				companyMasterAttachmentDTO.setDocumentName(filePath);
				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				TimeZone central = TimeZone.getTimeZone("CST");
				format.setTimeZone(central);
				companyMasterAttachmentDTO.setDateAdded(format.format(companyMasterNotesTabBean.getCreatedDate()));
				companyMasterAttachmentDTO.setUserId(companyMasterNotesTabBean.getCreatedBy());
				companyMasterAttachmentDTO.setUserName(companyMasterNotesTabBean.getCreatedByName());
				notesDTOList.add(companyMasterAttachmentDTO);

			}
		}
		result.add(getCompanyInformation().getInternalNotes());
		result.add(notesDTOList);

		GtnUIFrameworkBaseComponent notesTab = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab");
		try {
			notesTab.setNotesTabValue(result);
		} catch (GtnFrameworkValidationFailedException ex) {
			Logger.getLogger(GtnUIFrameWorkCompanyMasterLoadAction.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public GtnCMasterInformationBean getCompanyInformation() {
		return companyInformation;
	}

	public void setCompanyInformation(GtnCMasterInformationBean companyInformation) {
		this.companyInformation = companyInformation;
	}

	public GtnCMasterCompanyUdcInfoBean getUdcInfo() {
		return udcInfo;
	}

	public void setUdcInfo(GtnCMasterCompanyUdcInfoBean udcInfo) {
		this.udcInfo = udcInfo;
	}

	public List<GtnCMasterIdentifierInfoBean> getIdentifierBeanList() {
		return identifierBeanList != null ? Collections.unmodifiableList(identifierBeanList) : identifierBeanList;
	}

	public void setIdentifierBeanList(List<GtnCMasterIdentifierInfoBean> identifierBeanList) {
		this.identifierBeanList = identifierBeanList != null ? Collections.unmodifiableList(identifierBeanList)
				: identifierBeanList;
	}

	public List<GtnCMasterCompanyTradeClassBean> getTradeClassInfoList() {
		return tradeClassInfoList != null ? Collections.unmodifiableList(tradeClassInfoList) : tradeClassInfoList;
	}

	public void setTradeClassInfoList(List<GtnCMasterCompanyTradeClassBean> tradeClassInfoList) {
		this.tradeClassInfoList = tradeClassInfoList != null ? Collections.unmodifiableList(tradeClassInfoList)
				: tradeClassInfoList;
	}

	public List<GtnCMasterCompanyParentBean> getParentCompanyInfoList() {
		return parentCompanyInfoList != null ? Collections.unmodifiableList(parentCompanyInfoList)
				: parentCompanyInfoList;
	}

	public void setParentCompanyInfoList(List<GtnCMasterCompanyParentBean> parentCompanyInfoList) {
		this.parentCompanyInfoList = parentCompanyInfoList != null ? Collections.unmodifiableList(parentCompanyInfoList)
				: parentCompanyInfoList;
	}

	public List<NotesTabBean> getLoadNotesTabBeanList() {
		return loadNotesTabBeanList != null ? Collections.unmodifiableList(loadNotesTabBeanList) : loadNotesTabBeanList;
	}

	public void setLoadNotesTabBeanList(List<NotesTabBean> loadNotesTabBeanList) {
		this.loadNotesTabBeanList = loadNotesTabBeanList != null ? new ArrayList<>(loadNotesTabBeanList)
				: loadNotesTabBeanList;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkCompanyMasterLoadAction();
	}

	public GtnWsRecordBean getValueFromTable(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof String) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		}
		GtnWsRecordBean selectedRecord = (GtnWsRecordBean) itemId;
		if (selectedRecord == null) {
			throw new GtnFrameworkGeneralException("Selected Table Record is Null");
		}
		return selectedRecord;
	}

}
