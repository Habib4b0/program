package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkContractHeaderSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkContractHeaderSaveAction.class);

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
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

			GtnWsContractHeaderRequest chRequest = new GtnWsContractHeaderRequest();
			setProperties(chRequest, fieldList, beanFieldList);

			request.setGtnWsContractHeaderRequest(chRequest);
			chRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid");
			if (systemId != null && systemId > 0) {
				chRequest.getGtnWsContractMasterBean().setContractMasterSid(systemId);
			}
			GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE
							+ GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SAVE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			Integer contractMasterSid = reponse.getGtnWsContractHeaderResponse().getGtnWsContractMasterBean()
					.getContractMasterSid();
			GtnUIFrameworkGlobalUI.addSessionProperty("contractMasterSid", contractMasterSid);
			GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();
			setter.loadFieldValue("contractId",
					reponse.getGtnWsContractHeaderResponse().getGtnWsContractMasterBean().getContractId());
			setter.loadFieldValue("contractNo",
					reponse.getGtnWsContractHeaderResponse().getGtnWsContractMasterBean().getContractNo());
			setter.loadFieldValue("contractName",
					reponse.getGtnWsContractHeaderResponse().getGtnWsContractMasterBean().getContractName());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderAddSaveButton").setCaption("UPDATE");
			setter.setComponentVisible("contractHeaderDeleteButton", true);

			loadTabAfterSave(reponse.getGtnWsContractHeaderResponse().getGtnwsContractAliasMasterBeanList());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabSheet").setSelectedTabByPostion(0);

			GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notificationActionConfig.addActionParameter(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractId").getStringFromField()
							+ ","+GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractName").getStringFromField()+" has been successfully saved");
			notificationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationActionConfig);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void setProperties(GtnWsContractHeaderRequest chReques, List<String> fieldList, List<String> beanFieldList)
			throws GtnFrameworkGeneralException {
		GtnWsContractMasterBean contractMasterBean = new GtnWsContractMasterBean();
		chReques.setGtnWsContractMasterBean(contractMasterBean);
		String componentId = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String beanFeild = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		try {
			for (int i = 0; i < fieldList.size(); i++) {
				componentId = fieldList.get(i);
				beanFeild = beanFieldList.get(i);
				Field field = GtnWsContractMasterBean.class.getDeclaredField(beanFeild);
				field.setAccessible(true);
				GtnUIFrameworkBaseComponent comp = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
				if (comp != null) {
					Object value = comp.getObjectFromField();
					field.set(contractMasterBean, value);
				}
			}
		} catch (Exception ex) {
			logger.error("Error while Setting the property for the field : " + componentId + " with field :" + beanFeild
					+ GtnFrameworkCommonStringConstants.STRING_EMPTY, ex);
		}
		GtnUIFrameworkComponentData idComponentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractHeaderTabCompanyName").getComponent().getData();
		Integer companyMaterId = null;

		if (idComponentData != null && idComponentData.getCustomData() != null) {
			GtnWsRecordBean tpDto = (GtnWsRecordBean) idComponentData.getCustomData();
			if (tpDto.getPropertyValueByIndex(5) != null)
				companyMaterId = Integer.parseInt(String.valueOf(tpDto.getPropertyValueByIndex(5)));
		}
		contractMasterBean.setCompanyMasterByBunitCompanyMasterSid(companyMaterId);

		GtnUIFrameworkComponentData tpComponentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractHeaderTabTradingPartner").getComponent().getData();
		Integer tpSid = null;

		if (tpComponentData != null && tpComponentData.getCustomData() != null) {
			GtnWsRecordBean tpDto = (GtnWsRecordBean) tpComponentData.getCustomData();
			if (tpDto.getPropertyValueByIndex(5) != null)
				tpSid = Integer.parseInt(String.valueOf(tpDto.getPropertyValueByIndex(5)));
		}
		contractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(tpSid);

		setNumericFieldValues(contractMasterBean);

		List<NotesTabBean> noteBeanList = new ArrayList<>();
		chReques.setNoteBeanList(noteBeanList);
		List<Object> notesList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

		chReques.getGtnWsContractMasterBean().setInternalNotes((String) notesList.get(0));
		@SuppressWarnings("unchecked")
		List<NotesDTO> notesDTOList = (List<NotesDTO>) notesList.get(1);

		loadNotesTab(noteBeanList, notesDTOList);

		List<GtnWsRecordBean> identifierList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractAliasattachResultTable").getItemsFromDataTable();
		List<GtnwsContractAliasMasterBean> aliasSaveList = new ArrayList<>();
		if (identifierList != null && !identifierList.isEmpty()) {
			getAliasTabList(identifierList, aliasSaveList);
		}

		chReques.setGtnwsContractAliasMasterBeanList(aliasSaveList);

	}

	private void loadNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean notesBean;
			for (NotesDTO note : notesDTOs) {
				notesBean = new NotesTabBean();
				notesBean.setMasterTableName("CONTRACT_MASTER");
				notesBean.setFilePath(note.getDocumentFullPath());
				notesBean.setFileName(note.getDocumentName());
				notesBean.setMasterTableSystemId(note.getDocDetailsId());
				notesBean.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				notesBean.setCreatedDate(new Date());
				noteBeanList.add(notesBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnUIFrameworkContractHeaderStringContants.SAVE_ERROR,
					systemExcption);
		}

	}

	private void getAliasTabList(List<GtnWsRecordBean> identifierList, List<GtnwsContractAliasMasterBean> aliasBeanList)
			throws GtnFrameworkGeneralException {
		try {

			GtnwsContractAliasMasterBean aliasBean;
			for (GtnWsRecordBean dto : identifierList) {
				aliasBean = new GtnwsContractAliasMasterBean();
				Object tpSid = dto.getPropertyValueByIndex(6);
				aliasBean.setTpCompanyMasterSid(tpSid == null ? 0 : (int) tpSid);
				Object aliasMasterSid = dto.getPropertyValueByIndex(7);
				aliasBean.setContractAliasMasterSid(aliasMasterSid == null ? 0 : (int) aliasMasterSid);
				aliasBean.setContractAliasNo((String) dto.getPropertyValue("contractAliasNo"));
				aliasBean.setContractAliasName((String) dto.getPropertyValue("contractAliasName"));
				aliasBean.setStartDate((Date) dto.getPropertyValue("aliasStartDate"));
				aliasBean.setEndDate((Date) dto.getPropertyValue("aliasEndDate"));
				aliasBean.setContractTypeDesc((String) dto.getPropertyValue("contractAliasType"));
				Object contractType = dto.getPropertyValueByIndex(5);
				aliasBean.setContractType(contractType == null ? 0 : (int) contractType);
				aliasBeanList.add(aliasBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnUIFrameworkContractHeaderStringContants.SAVE_ERROR,
					systemExcption);
		}

	}

	@SuppressWarnings("unchecked")
	private void loadTabAfterSave(List<GtnwsContractAliasMasterBean> reponseBean) throws GtnFrameworkGeneralException {
		ExtFilterTable identifiersultTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractAliasattachResultTable").getExtFilterTable();

		ExtContainer<GtnWsRecordBean> identifierContainer = (ExtContainer<GtnWsRecordBean>) identifiersultTable
				.getContainerDataSource();
		identifierContainer.removeAllItems();

		loadIdentifierTab(reponseBean, identifierContainer);

	}

	private void loadIdentifierTab(List<GtnwsContractAliasMasterBean> aliasSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean idenBean;
			for (GtnwsContractAliasMasterBean dto : aliasSaveList) {
				idenBean = new GtnWsRecordBean();
				idenBean.setRecordHeader(container.getRecordHeader());
				idenBean.addProperties("contractAliasNo", dto.getContractAliasNo());
				idenBean.addProperties("contractAliasName", dto.getContractAliasName());
				idenBean.addProperties("contractAliasType", dto.getContractTypeDesc());

				idenBean.addProperties("aliasStartDate", dto.getStartDate());
				idenBean.addProperties("aliasEndDate", dto.getEndDate());

				idenBean.getProperties().add(dto.getContractType());
				idenBean.getProperties().add(dto.getTpCompanyMasterSid());
				idenBean.getProperties().add(dto.getContractAliasMasterSid());
				container.addBean(idenBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnUIFrameworkContractHeaderStringContants.SAVE_ERROR,
					systemExcption);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setNumericFieldValues(GtnWsContractMasterBean contractMasterBean)
			throws GtnFrameworkValidationFailedException {
		String term = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabTerm").getStringFromField();
		contractMasterBean.setTerm(NumberUtils.toInt(term));
		String paymentTerms = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabPaymentTerms")
				.getStringFromField();

		contractMasterBean.setPaymentTerms(NumberUtils.toInt(paymentTerms));
		String advanceNoticeDays = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractInformationTabAdvanceNoticeDays").getStringFromField();

		contractMasterBean.setAdvanceNoticeDays(NumberUtils.toDouble(advanceNoticeDays));
		Object priceResetIndicator = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractInformationTabPriceResetIndicator").getObjectFromField();

		contractMasterBean.setPriceResetIndicator(NumberUtils.toInt(String.valueOf(priceResetIndicator)));
	}

}
