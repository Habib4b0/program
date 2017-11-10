package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.asi.container.ExtContainer;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
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

public class GtnFrameworkItemMasterResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering GtnFrameworkItemMasterAddAction doAction ");
		try {
			int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
			Integer itemMasterSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemMasterSid");
			if (itemMasterSid == null || itemMasterSid == 0) {
				GtnWsItemMasterBean bean = new GtnWsItemMasterBean();
				bean.setGtnWsItemMasterInfoBean(new GtnWsItemMasterInfoBean());
				setValueToComponents(componentId, bean, position);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabCreatedBy")
						.loadFieldValue(GtnUIFrameworkGlobalUI.getCurrentUser());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationCreatedDate").loadDateValue(new Date());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabSystemId")
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabBatchID")
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				switch (position) {
				case 2:
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterIdentifierattachResultTable")
							.getExtFilterTable().getContainerDataSource().removeAllItems();
					break;
				case 3:
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterPricingattachResultTable")
							.getExtFilterTable().getContainerDataSource().removeAllItems();
					break;
				case 4:
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").refreshNotesTab();
					break;
				default:
					break;
				}
			} else {
				GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
				GtnWsItemMasterBean imBean = new GtnWsItemMasterBean();
				GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
				imBean.setGtnWsItemMasterInfoBean(infoBean);
				infoBean.setItemMasterSid(itemMasterSid);
				GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
				imRequest.setGtnWsItemMasterBean(imBean);
				gtnRequest.setGtnWsItemMasterRequest(imRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
								+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_FETCH_INFORMATION_SERVICE,
						gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				setValueToComponents(componentId, response.getGtnWsItemMasterResponse().getGtnWsItemMasterBean(),
						position);
				loadTabAfterSave(response.getGtnWsItemMasterResponse().getGtnWsItemMasterBean(), position);
				if (position == 4) {
					loadNotesTab(componentId, response.getGtnWsItemMasterResponse().getGtnWsItemMasterBean());
				}
			}

		} catch (UnsupportedOperationException | GtnFrameworkGeneralException e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkItemMasterAddAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkItemMasterAddAction doAction ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterResetAction();
	}

	public void setValueToComponents(String sourceComponentId, GtnWsItemMasterBean bean, int position)
			throws GtnFrameworkGeneralException {
		GtnWsItemMasterInfoBean info = bean.getGtnWsItemMasterInfoBean();
		GtnUIFrameWorkActionConfig imInfoRestActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemId").loadFieldValue(info.getItemId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemNo").loadFieldValue(info.getItemNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemDesc").loadFieldValue(info.getItemDesc());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemName").loadFieldValue(info.getItemName());
		List<Object> imInfoTabFielsValues = null;
		switch (position) {
		case 0:

			imInfoTabFielsValues = Arrays.asList(new Object[] { info.getItemId(), info.getItemNo(), info.getItemDesc(),
					info.getItemName(), info.getItemStatus(), info.getItemStartDate(), info.getItemEndDate(),
					info.getItemType(), info.getTherapeuticClass(), info.getBrandMasterSid(), info.getItemClass(),
					info.getForm(), info.getStrength(), info.getFirstSaleDate(), info.getNdc9(), info.getNdc8(),
					info.getPrimaryUom(), info.getSecondaryUom(), info.getLabelerCode(), info.getItemCode(),
					info.getPackageSize(), info.getPackageSizeCode(), info.getItemTypeIndication(),
					info.getItemCategory(), info.getPackageSizeIntroDate(), info.getManufacturerId(), info.getUdc1(),
					info.getUdc2(), info.getUdc3(), info.getUdc4(), info.getUdc5(), info.getUdc6(),
					info.getCompanyMasterSid(), info.getCreatedDate(), info.getModifiedDate(), info.getItemMasterSid(),
					info.getBatchId(), info.getUpps() });
			imInfoRestActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.IM_INFO_TAB_FIELDS);
			imInfoRestActionConfig.addActionParameter(imInfoTabFielsValues);
			GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imInfoRestActionConfig);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabCreatedBy")
					.loadFieldValue(getUserName(info.getCreatedBy()));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabModifiedBy")
					.loadFieldValue(getUserName(info.getModifiedBy()));

			break;
		case 1:
			imInfoTabFielsValues = Arrays.asList(new Object[] { info.getDosesPerUnit(), info.getShelfLife(),
					info.getShelfLifeType(), info.getLastLotExpirationDate(), info.getAuthorizedGenericStartDate(),
					info.getPediatricExclusiveStartDate(), info.getClottingFactorStartDate(),
					info.getDiscontinuationDate(), info.getAuthorizedGenericEndDate(),
					info.getPediatricExclusiveEndDate(), info.getClottingFactorEndDate(), info.getDivestitureDate(),
					info.getAcquisitionDate(), info.getNonFederalExpirationDate(), info.getMarketTerminationDate(),
					info.getBaseCpiPeriod(), info.getAuthorizedGeneric(), info.getPediatricExclusiveIndicator(),
					info.getClottingFactorIndicator(), info.getDualPricingIndicator(),
					info.getNewFormulationIndicator(), info.getBaselineAmp(), info.getBaseCpi(), info.getAcquiredAmp(),
					info.getAcquiredBamp(), info.getDra(), info.getObraBamp(), info.getNewFormulationStartDate(),
					info.getNewFormulationEndDate(), info.getNewFormulation() });

			imInfoRestActionConfig
					.addActionParameter(GtnFrameworkItemMasterStringContants.IM_ADDITIONALINFO_TAB_FIELDS);
			imInfoRestActionConfig.addActionParameter(imInfoTabFielsValues);
			GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imInfoRestActionConfig);
			break;
		case 2:
			imInfoTabFielsValues = Arrays.asList(new Object[] { null, null,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					null, GtnFrameworkCommonStringConstants.STRING_EMPTY, null });

			imInfoRestActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.IM_IDENTIFIER_TAB_FIELDS);
			imInfoRestActionConfig.addActionParameter(imInfoTabFielsValues);
			GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imInfoRestActionConfig);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.IDENTIFIER_INFORMATION_ENTITY_CODE_NO)
					.setCustomData(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemIdentifierEntityCodeName").setCustomData(null);
			break;
		case 3:
			imInfoTabFielsValues = Arrays.asList(new Object[] { null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					null, null, null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, null });

			imInfoRestActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.IM_PRICING_TAB_FIELDS);
			imInfoRestActionConfig.addActionParameter(imInfoTabFielsValues);
			GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imInfoRestActionConfig);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingEntityCodeNo").setCustomData(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingEntityCodeName").setCustomData(null);
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void loadTabAfterSave(GtnWsItemMasterBean reponseBean, int position) throws GtnFrameworkGeneralException {
		if (position == 2) {

			ExtContainer<GtnWsRecordBean> identifierContainer = (ExtContainer<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemMasterIdentifierattachResultTable").getExtFilterTable()
					.getContainerDataSource();
			identifierContainer.removeAllItems();

			loadIdentifierTab(reponseBean.getGtnWsItemIdentifierBeanList(), identifierContainer);
		} else if (position == 3) {
			ExtContainer<GtnWsRecordBean> pricingContainer = (ExtContainer<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("itemMasterPricingattachResultTable").getExtFilterTable()
					.getContainerDataSource();
			pricingContainer.removeAllItems();

			loadPricingTab();
		}
	}

	private void loadIdentifierTab(List<GtnWsItemIdentifierBean> identifierSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean identifierBean;
			for (GtnWsItemIdentifierBean dto : identifierSaveList) {
				identifierBean = new GtnWsRecordBean();
				identifierBean.setRecordHeader(container.getRecordHeader());
				identifierBean.addProperties("itemIrtQualifierName", dto.getItemQualifierName());
				identifierBean.addProperties("itemIdentifier", dto.getItemIdentifierValue());
				identifierBean.addProperties("identifierStatus", dto.getItemIdentifierStatus());

				identifierBean.addProperties("startDate", dto.getStartDate());
				identifierBean.addProperties("endDate", dto.getEndDate());
				identifierBean.addProperties("entityCode", dto.getEntityCode());
				identifierBean.addProperties("entityCodeName", dto.getEntityCode());
				identifierBean.addProperties("modifiedBy", String.valueOf(dto.getModifiedBy()));
				identifierBean.addProperties("modifiedDate", dto.getModifiedDate());
				identifierBean.addProperties("createdBy", String.valueOf(dto.getCreatedBy()));
				identifierBean.addProperties("createdDate", dto.getCreatedDate());
				identifierBean.getProperties().add(dto.getItemQualifierMasterSid());
				identifierBean.getProperties().add(dto.getItemIdentifierSid());
				container.addBean(identifierBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException("Save Error", systemExcption);
		}

	}

	private String getUserName(int userId) {
		GtnUIFrameworkWebserviceRequest imResetGtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest imResetGeneralWSRequest = new GtnWsGeneralRequest();
		imResetGeneralWSRequest.setUserId(String.valueOf(userId));
		imResetGeneralWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		imResetGtnRequest.setGtnWsGeneralRequest(imResetGeneralWSRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				imResetGtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		return response.getGtnWsGeneralResponse().getUserName();
	}

	private void loadPricingTab() throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE)
				.getLogicFromPagedDataTable().startSearchProcess(new ArrayList<String>(), true);
	}

	private void loadNotesTab(String componentId, GtnWsItemMasterBean bean) {
		try {
			GtnUIFrameWorkActionConfig imResetNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			imResetNotesTabLoadAction.addActionParameter(bean.getNoteBeanList());
			imResetNotesTabLoadAction.addActionParameter(bean.getGtnWsItemMasterInfoBean().getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, imResetNotesTabLoadAction);
		} catch (Exception e) {
			gtnLogger.error("Exception in loading Notes Tab", e);
		}

	}
}
