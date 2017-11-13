package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.asi.container.ExtContainer;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("In GtnFrameworkItemMasterSaveAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			GtnUIFrameworkWebserviceResponse reponse = saveItemMaster(gtnUIFrameWorkActionConfig);
			changeAsLikeItemUpdate(componentId, reponse);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void cpDetailsInsert(int itemMasterSid) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(itemMasterSid);
		request.setGtnWsGeneralRequest(generalWSRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_CP_DETAILS_INSERT_FOR_ITEMS_INSERT_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private void changeAsLikeItemUpdate(String compoenentId, GtnUIFrameworkWebserviceResponse reponse)
			throws GtnFrameworkGeneralException {
		int itemMasterSid = reponse.getGtnWsItemMasterResponse().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
				.getItemMasterSid();
		int systemId = GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.ITEM_MASTER_SID) == null
				? 0
				: Integer.parseInt(GtnUIFrameworkGlobalUI
						.getSessionProperty(GtnFrameworkCommonConstants.ITEM_MASTER_SID).toString());
		if (systemId == 0) {
			cpDetailsInsert(itemMasterSid);
		}
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.ITEM_MASTER_SID, itemMasterSid);
		GtnWsItemMasterBean reponseBean = reponse.getGtnWsItemMasterResponse().getGtnWsItemMasterBean();
		GtnWsItemMasterInfoBean info = reponse.getGtnWsItemMasterResponse().getGtnWsItemMasterBean()
				.getGtnWsItemMasterInfoBean();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemId").loadFieldValue(info.getItemId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemNo").loadFieldValue(info.getItemNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemDesc").loadFieldValue(info.getItemDesc());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemName").loadFieldValue(info.getItemName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterAddSaveButton").setCaption("UPDATE");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterDeleteButton").setComponentVisible(true);
		loadTabAfterSave(reponseBean);

		GtnUIFrameWorkActionConfig imSaveNotificationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		imSaveNotificationActionConfig.addActionParameter(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabItemId").getStringFromField()
						+ " has been successfully saved");
		imSaveNotificationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkActionExecutor.executeSingleAction(compoenentId, imSaveNotificationActionConfig);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").setSelectedTabByPostion(0);

	}

	@SuppressWarnings("unchecked")
	private GtnUIFrameworkWebserviceResponse saveItemMaster(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> fields = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		List<String> beanFields = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterBean cfpBean = setProperties(fields, beanFields);

		GtnWsItemMasterRequest cfpRequest = new GtnWsItemMasterRequest();
		cfpRequest.setGtnWsItemMasterBean(cfpBean);
		request.setGtnWsItemMasterRequest(cfpRequest);
		cfpRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemMasterSid");
		if (systemId != null && systemId > 0) {
			cfpBean.getGtnWsItemMasterInfoBean().setItemMasterSid(systemId);
		}

		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SAVE_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private GtnWsItemMasterBean setProperties(List<String> fields, List<String> beanFields)
			throws GtnFrameworkGeneralException {
		GtnWsItemMasterBean itemMasterBean = new GtnWsItemMasterBean();
		GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
		itemMasterBean.setGtnWsItemMasterInfoBean(infoBean);
		String componentId = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String beanFeild = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		try {

			for (int i = 0; i < fields.size(); i++) {
				componentId = fields.get(i);
				beanFeild = beanFields.get(i);
				Field field = GtnWsItemMasterInfoBean.class.getDeclaredField(beanFeild);
				field.setAccessible(true);
				GtnUIFrameworkBaseComponent comp = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
				if (comp != null) {
					Object value = comp.getObjectFromField();
					field.set(infoBean, value);
				}
			}
		} catch (Exception ex) {
			logger.error("Error while Setting the property for the field : " + componentId + " with field :" + beanFeild
					+ GtnFrameworkCommonStringConstants.STRING_EMPTY, ex);
		}

		Double upps = toDouble(String
				.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabUPPS").getObjectFromField()));
		if (upps != null) {
			infoBean.setUpps(upps);
		}
             String itemType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabItemType").getCaptionFromComboBox();
            Object ndc9Value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemInformationTabValueDropDown").getObjectFromField();
            boolean isNDC11 = "NDC-11".equals(itemType);
            if (!isNDC11) {
                infoBean.setNdc9("0");
            } else {
                infoBean.setNdc9(String.valueOf(ndc9Value));
            }
		Double baselineAmp = toDouble(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("additionalInformationTabBaselineAMP").getObjectFromField()));
		if (baselineAmp != null) {
			infoBean.setBaselineAmp(baselineAmp);
		}
		Double baseCpi = toDouble(String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("additionalInformationTabBaseCPI").getObjectFromField()));
		if (baseCpi != null) {
			infoBean.setBaseCpi(baseCpi);
		}
		Double acquiredAmp = toDouble(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("additionalInformationTabAcquiredAMP").getObjectFromField()));
		if (acquiredAmp != null) {
			infoBean.setAcquiredAmp(acquiredAmp);
		}
		Double acquiredBamp = toDouble(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("additionalInformationTabAcquiredBAMP").getObjectFromField()));
		if (acquiredBamp != null) {
			infoBean.setAcquiredBamp(acquiredBamp);
		}
		Double dra = toDouble(String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("additionalInformationTabDRA").getObjectFromField()));
		if (dra != null) {
			infoBean.setDra(dra);
		}
		Double obraBamp = toDouble(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("additionalInformationTabOBRABAMP").getObjectFromField()));
		if (obraBamp != null) {
			infoBean.setObraBamp(obraBamp);
		}
		/*
		 * save Notes Tab
		 */
		List<NotesTabBean> noteBeanList = new ArrayList<>();
		List<Object> notesList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

		itemMasterBean.getGtnWsItemMasterInfoBean().setInternalNotes((String) notesList.get(0));
		@SuppressWarnings("unchecked")
		List<NotesDTO> notesDTOList = (List<NotesDTO>) notesList.get(1);

		loadNotesTab(noteBeanList, notesDTOList);
		itemMasterBean.setNoteBeanList(noteBeanList);

		/**
		 * Identifier List save
		 *
		 */
		List<GtnWsRecordBean> identifierList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterIdentifierattachResultTable").getItemsFromDataTable();
		List<GtnWsItemIdentifierBean> identifierSaveList = new ArrayList<>();
		if (identifierList != null && !identifierList.isEmpty()) {
			getIdentifierTabList(identifierList, identifierSaveList);
		}

		itemMasterBean.setGtnWsItemIdentifierBeanList(identifierSaveList);

		return itemMasterBean;
	}

	private void loadNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean imNotesBean;
			for (NotesDTO note : notesDTOs) {
				imNotesBean = new NotesTabBean();
				imNotesBean.setMasterTableName("ITEM_MASTER");
				imNotesBean.setFilePath(note.getDocumentFullPath());
				imNotesBean.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				imNotesBean.setCreatedDate(new Date());
				noteBeanList.add(imNotesBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void getIdentifierTabList(List<GtnWsRecordBean> identifierList,
			List<GtnWsItemIdentifierBean> identifierSaveList) throws GtnFrameworkGeneralException {
		try {

			GtnWsItemIdentifierBean idenBean;
			for (GtnWsRecordBean dto : identifierList) {
				idenBean = new GtnWsItemIdentifierBean();
				Object itemQualifierSid = dto.getPropertyValueByIndex(12);
				idenBean.setItemQualifierMasterSid(itemQualifierSid == null ? 0 : (int) itemQualifierSid);
				Object itemIdentifierSid = dto.getPropertyValueByIndex(13);
				idenBean.setItemIdentifierSid(itemIdentifierSid == null ? 0 : (int) itemIdentifierSid);
				idenBean.setItemIdentifierValue((String) dto.getPropertyValue("itemIdentifier"));
				idenBean.setItemIdentifierStatus((Integer) dto.getPropertyValue("identifierStatus"));
				idenBean.setStartDate((Date) dto.getPropertyValue(GtnFrameworkCommonConstants.START_DATE));
				idenBean.setEndDate((Date) dto.getPropertyValue(GtnFrameworkCommonConstants.END_DATE));
				idenBean.setEntityCode((String) dto.getPropertyValue(GtnFrameworkCommonConstants.ENTITY_CODE));
				idenBean.setModifiedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				idenBean.setModifiedDate(new Date());
				identifierSaveList.add(idenBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	@SuppressWarnings("unchecked")
	private void loadTabAfterSave(GtnWsItemMasterBean reponseBean) throws GtnFrameworkGeneralException {

		ExtContainer<GtnWsRecordBean> imIdentifierContainer = (ExtContainer<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterIdentifierattachResultTable").getExtFilterTable()
				.getContainerDataSource();
		imIdentifierContainer.removeAllItems();

		loadIdentifierTab(reponseBean.getGtnWsItemIdentifierBeanList(), imIdentifierContainer);

		ExtContainer<GtnWsRecordBean> imPricingContainer = (ExtContainer<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterPricingattachResultTable").getExtFilterTable()
				.getContainerDataSource();
		imPricingContainer.removeAllItems();

		loadPricingTab();
	}

	private void loadIdentifierTab(List<GtnWsItemIdentifierBean> identifierSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean imIdentifierBean;
			for (GtnWsItemIdentifierBean dto : identifierSaveList) {
				imIdentifierBean = new GtnWsRecordBean();
				imIdentifierBean.setRecordHeader(container.getRecordHeader());
				imIdentifierBean.addProperties("itemIrtQualifierName", dto.getItemQualifierName());
				imIdentifierBean.addProperties("itemIdentifier", dto.getItemIdentifierValue());
				imIdentifierBean.addProperties("identifierStatus", dto.getItemIdentifierStatus());

				imIdentifierBean.addProperties(GtnFrameworkCommonConstants.START_DATE, dto.getStartDate());
				imIdentifierBean.addProperties(GtnFrameworkCommonConstants.END_DATE, dto.getEndDate());
				imIdentifierBean.addProperties(GtnFrameworkCommonConstants.ENTITY_CODE, dto.getEntityCode());
				imIdentifierBean.addProperties("entityCodeName", dto.getEntityCode());
				imIdentifierBean.addProperties("modifiedBy", String.valueOf(dto.getModifiedBy()));
				imIdentifierBean.addProperties("modifiedDate", dto.getModifiedDate());
				imIdentifierBean.addProperties("createdBy", String.valueOf(dto.getCreatedBy()));
				imIdentifierBean.addProperties("createdDate", dto.getCreatedDate());
				imIdentifierBean.addProperties("identifierStatusDes", "");
				imIdentifierBean.getProperties().add(dto.getItemQualifierMasterSid());
				imIdentifierBean.getProperties().add(dto.getItemIdentifierSid());
				container.addBean(imIdentifierBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadPricingTab() throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE)
				.getLogicFromPagedDataTable().startSearchProcess(new ArrayList<String>(), true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	Double toDouble(String str) {
		Double deafultValue = null;
		try {
			deafultValue = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		return deafultValue;
	}

}
