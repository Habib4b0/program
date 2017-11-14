/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
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
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Mersal Manikanda.Prabu
 */
public class GtnUIFrameworkCompanyMasterResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCompanyMasterResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
			Integer companyMasterSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("companyMasterSid");
			if (companyMasterSid == null || companyMasterSid == 0) {
				GtnCMasterBean bean = new GtnCMasterBean();
				bean.setGtnCMasterInformationBean(new GtnCMasterInformationBean());
				bean.setGtnCMasterCompanyUdcInfoBean(new GtnCMasterCompanyUdcInfoBean());
				setValueToComponents(bean, position, componentId);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedBy", componentId)
						.loadFieldValue(GtnUIFrameworkGlobalUI.getCurrentUserName());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCreatedDate", componentId)
						.loadDateValue(new Date());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSystemId", componentId)
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				switch (position) {
				case GtnWsNumericConstants.TWO:
					ExtFilterTable pricingTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("identifierattachResultTable").getExtFilterTable();
					pricingTable.getContainerDataSource().removeAllItems();
					break;
				case GtnWsNumericConstants.THREE:
					ExtFilterTable tradeclassTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("tradeClassattachResultTable").getExtFilterTable();
					tradeclassTable.getContainerDataSource().removeAllItems();
					break;
				case GtnWsNumericConstants.FOUR:
					ExtFilterTable parentTable = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("parentCompanyattachResultTable").getExtFilterTable();
					parentTable.getContainerDataSource().removeAllItems();
					break;
				case GtnWsNumericConstants.SIX:
					GtnUIFrameworkBaseComponent notesTab = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab");
					notesTab.refreshNotesTab();
					break;
				default:
					break;
				}
			} else {
				GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
				GtnCMasterBean cmasterBean = new GtnCMasterBean();
				GtnCMasterInformationBean infoBean = new GtnCMasterInformationBean();
				cmasterBean.setGtnCMasterInformationBean(infoBean);
				infoBean.setCompanyMasterSystemId(companyMasterSid);
				GtnCMasterRequest cmRequest = new GtnCMasterRequest();
				cmRequest.setGtnCMasterBean(cmasterBean);
				gtnRequest.setGtnCMasterRequest(cmRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER
								+ GtnWebServiceUrlConstants.GTN_CM_GET_DETAILS_SERVICE,
						gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				setValueToComponents(response.getGtnCompanyMasterResponse().getGtnCMasterBean(), position, componentId);
				loadTabAfterSave(response.getGtnCompanyMasterResponse().getGtnCMasterBean(), position);
				if (position == GtnWsNumericConstants.SIX) {
					loadNotesTab(response.getGtnCompanyMasterResponse().getGtnCMasterBean());
				}
			}

		} catch (UnsupportedOperationException | GtnFrameworkGeneralException e) {
			gtnLogger.error("Error while calling doAction in GtnUIFrameworkCompanyMasterResetAction ", e);
		} finally {
			gtnLogger.info("Exit GtnUIFrameworkCompanyMasterResetAction doAction ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCompanyMasterResetAction();
	}

	public void setValueToComponents(GtnCMasterBean bean, int position, String componentId) {
		GtnCMasterInformationBean info = bean.getGtnCMasterInformationBean();
		GtnCMasterCompanyUdcInfoBean udcInfo = bean.getGtnCMasterCompanyUdcInfoBean();

		switch (position) {
		case 0:
			loadCompanyInfoTab(componentId, info, udcInfo);
			break;
		case 1:
			loadAddressTab(componentId, info);
			break;
		case GtnWsNumericConstants.TWO:
			loadIdentifierTab(componentId);
			break;
		case GtnWsNumericConstants.THREE:
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeTabtradeClass", componentId)
					.loadComboBoxComponentValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassStartDate", componentId).loadDateValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassEndDate", componentId).loadDateValue(null);
			break;
		case GtnWsNumericConstants.FOUR:
			loadParentCompanyTab(componentId);
			break;
		default:
			break;
		}
	}

	public void loadParentCompanyTab(String componentId) {
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE, componentId)
				.loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyEndDate", componentId).loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyNo", componentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyName", componentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE)
				.getComponent().removeStyleName(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
	}

	public void loadIdentifierTab(String componentId) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierCompanyQualifierName", componentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierInformationCompanyIdentifier", componentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyIdentifierStartDate", componentId).loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyIdentifierEndDate", componentId).loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierInformationCompanyStatus", componentId)
				.loadComboBoxComponentValue(null);
	}

	public void loadAddressTab(String componentId, GtnCMasterInformationBean info) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAdderss1", componentId)
				.loadFieldValue(info.getAddress1());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCity", componentId).loadFieldValue(info.getCity());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabZipCode", componentId)
				.loadFieldValue(info.getZipCode());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAddress2", componentId)
				.loadFieldValue(info.getAddress2());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabRegionCode", componentId)
				.loadFieldValue(info.getRegionCode());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCountry", componentId)
				.loadComboBoxComponentValue(info.getCountry());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabState", componentId)
				.loadComboBoxComponentValue(info.getState());
	}

	public void loadCompanyInfoTab(String componentId, GtnCMasterInformationBean info,
			GtnCMasterCompanyUdcInfoBean udcInfo) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyId", componentId)
				.loadFieldValue(info.getCompanyId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyNo", componentId)
				.loadFieldValue(info.getCompanyNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyName", componentId)
				.loadFieldValue(info.getCompanyName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyStatus", componentId)
				.loadComboBoxComponentValue(info.getCompanyStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyStartDate", componentId)
				.loadDateValue(info.getCompanyStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyEndDate", componentId)
				.loadDateValue(info.getCompanyEndDate());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyType", componentId)
				.loadComboBoxComponentValue(info.getCompanyType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyCategory", componentId)
				.loadComboBoxComponentValue(info.getCompanyCategory());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyGroup", componentId)
				.loadComboBoxComponentValue(info.getCompanyGroup());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabOrganizationKey", componentId)
				.loadComboBoxComponentValue(info.getOrganizationKey());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSource", componentId)
				.loadFieldValue(info.getSource());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabFinancialSystem", componentId)
				.loadFieldValue(info.getFinancialSystem());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSystemId", componentId)
				.loadFieldValue(info.getCompanyMasterSystemId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc1", componentId)
				.loadComboBoxComponentValue((udcInfo != null && udcInfo.getUdc1() != null) ? udcInfo.getUdc1() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc2", componentId)
				.loadComboBoxComponentValue((udcInfo != null && udcInfo.getUdc2() != null) ? udcInfo.getUdc2() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc3", componentId)
				.loadComboBoxComponentValue((udcInfo != null && udcInfo.getUdc3() != null) ? udcInfo.getUdc3() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc4", componentId)
				.loadComboBoxComponentValue((udcInfo != null && udcInfo.getUdc4() != null) ? udcInfo.getUdc4() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc5", componentId)
				.loadComboBoxComponentValue((udcInfo != null && udcInfo.getUdc5() != null) ? udcInfo.getUdc5() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc6", componentId)
				.loadComboBoxComponentValue((udcInfo != null && udcInfo.getUdc6() != null) ? udcInfo.getUdc6() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedBy", componentId)
				.loadFieldValue(info.getCreatedByName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabModifiedBy", componentId)
				.loadFieldValue(info.getModifiedByName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate", componentId)
				.loadDateValue(info.getModifiedDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCreatedDate", componentId)
				.loadDateValue(info.getCreatedDate());
	}

	@SuppressWarnings("unchecked")
	private void loadTabAfterSave(GtnCMasterBean reponseBean, int position) throws GtnFrameworkGeneralException {
		if (position == GtnWsNumericConstants.TWO) {
			ExtFilterTable identifiersultTable = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("identifierattachResultTable").getExtFilterTable();

			ExtContainer<GtnWsRecordBean> identifierContainer = (ExtContainer<GtnWsRecordBean>) identifiersultTable
					.getContainerDataSource();
			identifierContainer.removeAllItems();

			loadIdentifierTab(reponseBean.getGtnCMasterIdentifierInfoBeanList(), identifierContainer);
		} else if (position == GtnWsNumericConstants.THREE) {
			ExtFilterTable tradeTable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassattachResultTable")
					.getExtFilterTable();

			ExtContainer<GtnWsRecordBean> pricingContainer = (ExtContainer<GtnWsRecordBean>) tradeTable
					.getContainerDataSource();
			pricingContainer.removeAllItems();

			loadTradeClassTab(reponseBean.getGtnCMasterCompanyTradeClassBeanList(), pricingContainer);
		} else if (position == GtnWsNumericConstants.FOUR) {
			ExtFilterTable parentCompanyTable = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("parentCompanyattachResultTable").getExtFilterTable();

			ExtContainer<GtnWsRecordBean> parentContainer = (ExtContainer<GtnWsRecordBean>) parentCompanyTable
					.getContainerDataSource();
			parentContainer.removeAllItems();

			loadParentTab(reponseBean.getGtnCMasterCompanyParentBeanList(), parentContainer);
		}
	}

	private void loadIdentifierTab(List<GtnCMasterIdentifierInfoBean> identifierSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {
		try {

			GtnWsRecordBean idenBean;
			for (GtnCMasterIdentifierInfoBean dto : identifierSaveList) {
				idenBean = new GtnWsRecordBean();
				idenBean.setRecordHeader(container.getRecordHeader());
				idenBean.addProperties("qualifierName", dto.getCompanyQualifierValue());
				idenBean.addProperties("identifier", dto.getCompanyIdentifierValue());
				idenBean.addProperties("identifierStatus", dto.getIdentifierStatus());

				idenBean.addProperties("startDate", dto.getCompanyIdentifierStartDate());
				idenBean.addProperties("endDate", dto.getCompanyIdentifierEndDate());
				idenBean.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_BY,
						dto.getModifiedByName() == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : String.valueOf(dto.getModifiedByName()));
				idenBean.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_DATE, dto.getModifiedDate());
				idenBean.addProperties(GtnFrameworkCompanyStringContants.CREATED_BY,
						String.valueOf(dto.getCreatedByName()));
				idenBean.addProperties(GtnFrameworkCompanyStringContants.CREATED_DATE, dto.getCreatedDate());
				idenBean.getProperties().add(dto.getCreatedBy());
				idenBean.getProperties().add(dto.getCompanyQualifierSid());
				container.addBean(idenBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCompanyStringContants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadTradeClassTab(List<GtnCMasterCompanyTradeClassBean> tradeSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {

		try {
			GtnWsRecordBean tradeBean;
			for (GtnCMasterCompanyTradeClassBean dto : tradeSaveList) {
				tradeBean = new GtnWsRecordBean();
				tradeBean.setRecordHeader(container.getRecordHeader());
				tradeBean.addProperties("tradeTabtradeClass", dto.getCompanyTradeClassValue());
				tradeBean.addProperties("tradeClassStartDate", dto.getCompanyTradeClassStartDate());
				tradeBean.addProperties("tradeClassEndDate", dto.getCompanyTradeClassEndDate());

				tradeBean.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_BY,
						dto.getModifiedByName() == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : String.valueOf(dto.getModifiedByName()));
				tradeBean.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_DATE, dto.getModifiedDate());
				tradeBean.addProperties(GtnFrameworkCompanyStringContants.CREATED_BY, dto.getCreatedByName());
				tradeBean.addProperties(GtnFrameworkCompanyStringContants.CREATED_DATE, dto.getCreatedDate());
				tradeBean.getProperties().add(dto.getCreatedBy());
				tradeBean.getProperties().add(dto.getCompanyTradeClassSid());
				container.addBean(tradeBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCompanyStringContants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadParentTab(List<GtnCMasterCompanyParentBean> parentSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {

		try {
			GtnWsRecordBean parentBean;
			for (GtnCMasterCompanyParentBean dto : parentSaveList) {
				parentBean = new GtnWsRecordBean();
				parentBean.setRecordHeader(container.getRecordHeader());
				parentBean.addProperties("parentCompanyNo", dto.getCompanyNo());
				parentBean.addProperties("parentCompanyName", String.valueOf(dto.getCompanyName()));
				parentBean.addProperties("parentCompanyStartDate", dto.getCompanyParentStartDate());
				parentBean.addProperties("parentCompanyEndDate", dto.getCompanyParentEndDate());

				parentBean.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_BY, dto.getModifiedByName());
				parentBean.addProperties(GtnFrameworkCompanyStringContants.MODIFIED_DATE, dto.getModifiedDate());
				parentBean.addProperties(GtnFrameworkCompanyStringContants.CREATED_BY, dto.getCreatedByName());
				parentBean.addProperties(GtnFrameworkCompanyStringContants.CREATED_DATE, dto.getCreatedDate());
				parentBean.getProperties().add(dto.getCreatedBy());
				parentBean.getProperties().add(dto.getParentCompanyMasterSystemId());
				container.addBean(parentBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCompanyStringContants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadNotesTab(GtnCMasterBean bean) {
		try {
			List<NotesDTO> notesDTOList = new ArrayList<>();
			List<Object> resultList = new ArrayList<>();
			NotesDTO cmAttachmentDTO;
			if (bean.getGtnCMasterCompanyNotesTabBeanList() != null) {
				for (NotesTabBean cmNotesTabBean : bean.getGtnCMasterCompanyNotesTabBeanList()) {
					cmAttachmentDTO = new NotesDTO();
					cmAttachmentDTO.setDocDetailsId(cmNotesTabBean.getMasterTableSystemId());
					String filePath = cmNotesTabBean.getFilePath();
					cmAttachmentDTO.setDocumentFullPath(filePath);
					cmAttachmentDTO.setDocumentName("--");
					String tempfilePath = cmNotesTabBean.getFilePath();
					cmAttachmentDTO.setDocumentFullPath(tempfilePath);
					String fileNameWithId = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('_'))
							+ filePath.substring(filePath.lastIndexOf('.'));
					String fileName = fileNameWithId;
					cmAttachmentDTO.setDocumentName(fileName);
					SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
					TimeZone central = TimeZone.getTimeZone("CST");
					format.setTimeZone(central);
					cmAttachmentDTO.setDateAdded(format.format(cmNotesTabBean.getCreatedDate()));
					cmAttachmentDTO.setUserId(cmNotesTabBean.getCreatedBy());
					cmAttachmentDTO.setUserName(String.valueOf(cmNotesTabBean.getCreatedByName()));
					notesDTOList.add(cmAttachmentDTO);

				}
			}
			resultList.add(bean.getGtnCMasterInformationBean().getInternalNotes());
			resultList.add(notesDTOList);
			GtnUIFrameworkBaseComponent notesTab = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab");
			notesTab.setNotesTabValue(resultList);
			notesTab.setUploaderValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

}
