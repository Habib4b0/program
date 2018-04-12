package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asi.container.ExtContainer;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemMasterEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	public GtnWsRecordBean getValueFromTable(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String imTableId = (String) actionParamList.get(1);
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof Boolean) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(imTableId).getValueFromPagedDataTable();
		}
		GtnWsRecordBean selectedRecord = (GtnWsRecordBean) itemId;
		if (selectedRecord == null) {
			throw new GtnFrameworkGeneralException("Selected Table Record is Null");
		}
		return selectedRecord;
	}

	private GtnUIFrameworkWebserviceResponse loadItemMasterData(GtnWsRecordBean selectedItem, String propertyId) {
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterBean imBean = new GtnWsItemMasterBean();
		GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
		imBean.setGtnWsItemMasterInfoBean(infoBean);

		int systemId = (Integer) selectedItem.getPropertyValue(propertyId);
		infoBean.setItemMasterSid(systemId);
		GtnUIFrameworkGlobalUI.addSessionProperty("itemMasterSid", systemId);
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		imRequest.setGtnWsItemMasterBean(imBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);

		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_FETCH_INFORMATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String propertyId = (String) actionParamList.get(2);
		boolean isEditable = (boolean) actionParamList.get(3);

		GtnWsRecordBean selectedItem = getValueFromTable(gtnUIFrameWorkActionConfig);
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
		GtnUIFrameworkWebserviceResponse response = loadItemMasterData(selectedItem, propertyId);

		if (!response.getGtnWsGeneralResponse().isSucess()) {
			throw new GtnFrameworkGeneralException("Webservice Failed");
		}

		setValueToComponents(response.getGtnWsItemMasterResponse().getGtnWsItemMasterBean(), componentId, isEditable);
		loadTabAfterSave(response.getGtnWsItemMasterResponse().getGtnWsItemMasterBean(), isEditable);
		loadNotesTab(componentId, response.getGtnWsItemMasterResponse().getGtnWsItemMasterBean());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void configureInformationTabComponents(String sourceComponentId, GtnWsItemMasterInfoBean imEditBean)
			throws GtnFrameworkGeneralException {
		List<Object> imFielsValues = Arrays.asList(new Object[] { imEditBean.getItemId(), imEditBean.getItemNo(),
				imEditBean.getItemDesc(), imEditBean.getItemName(), imEditBean.getItemId(), imEditBean.getItemNo(),
				imEditBean.getItemDesc(), imEditBean.getItemName(), imEditBean.getItemStatus(),
				imEditBean.getItemStartDate(), imEditBean.getItemEndDate(), imEditBean.getItemType(),
				imEditBean.getTherapeuticClass(), imEditBean.getBrandMasterSid(), imEditBean.getItemClass(),
				imEditBean.getForm(), imEditBean.getStrength(), imEditBean.getFirstSaleDate(), imEditBean.getNdc9(),
				imEditBean.getNdc8(), imEditBean.getPrimaryUom(), imEditBean.getSecondaryUom(),
				imEditBean.getLabelerCode(), imEditBean.getItemCode(), imEditBean.getPackageSize(),
				imEditBean.getPackageSizeCode(), imEditBean.getItemTypeIndication(), imEditBean.getItemCategory(),
				imEditBean.getPackageSizeIntroDate(), imEditBean.getManufacturerId(), imEditBean.getUdc1(),
				imEditBean.getUdc2(), imEditBean.getUdc3(), imEditBean.getUdc4(), imEditBean.getUdc5(),
				imEditBean.getUdc6(), imEditBean.getCompanyMasterSid(), imEditBean.getDosesPerUnit(),
				imEditBean.getShelfLife(), imEditBean.getShelfLifeType(), imEditBean.getLastLotExpirationDate(),
				imEditBean.getAuthorizedGenericStartDate(), imEditBean.getPediatricExclusiveStartDate(),
				imEditBean.getClottingFactorStartDate(), imEditBean.getDiscontinuationDate(),
				imEditBean.getAuthorizedGenericEndDate(), imEditBean.getPediatricExclusiveEndDate(),
				imEditBean.getClottingFactorEndDate(), imEditBean.getDivestitureDate(), imEditBean.getAcquisitionDate(),
				imEditBean.getNonFederalExpirationDate(), imEditBean.getMarketTerminationDate(),
				imEditBean.getBaseCpiPeriod(), imEditBean.getAuthorizedGeneric(),
				imEditBean.getPediatricExclusiveIndicator(), imEditBean.getClottingFactorIndicator(),
				imEditBean.getDualPricingIndicator(), imEditBean.getNewFormulationIndicator(), imEditBean.getUpps(),
				imEditBean.getBaselineAmp(), imEditBean.getBaseCpi(), imEditBean.getAcquiredAmp(),
				imEditBean.getAcquiredBamp(), imEditBean.getDra(), imEditBean.getObraBamp(),
				imEditBean.getNewFormulationStartDate(), imEditBean.getNewFormulationEndDate(),
				imEditBean.getNewFormulation(), null, GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				GtnFrameworkCommonStringConstants.STRING_NULL.equals(String.valueOf(imEditBean.getNdc9())) ? null
						: Integer.valueOf(imEditBean.getNdc9()) });

		GtnUIFrameWorkActionConfig imEditDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		imEditDefaultValueActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.IM_FIELDS);
		imEditDefaultValueActionConfig.addActionParameter(imFielsValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imEditDefaultValueActionConfig);

		List<Object> disabledFields = Arrays.asList(new Object[] { "itemInformationTabCreatedBy",
				"itemInformationCreatedDate", "itemInformationTabModifiedBy", "itemInformationModifiedDate",
				"itemInformationTabSystemId", "itemInformationTabBatchID" });
		List<Object> disabledFieldValues = Arrays.asList(new Object[] { imEditBean.getCreatedByUserName(),
				imEditBean.getCreatedDate(), imEditBean.getModifiedUserName(), imEditBean.getModifiedDate(),
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY });
		GtnUIFrameWorkActionConfig imEditDisableFieldsPopulateeActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		imEditDisableFieldsPopulateeActionConfig.addActionParameter(disabledFields);
		imEditDisableFieldsPopulateeActionConfig.addActionParameter(disabledFieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imEditDisableFieldsPopulateeActionConfig);

		GtnUIFrameWorkActionConfig imEditDisableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DISABLE_ACTION);
		imEditDisableAction.setActionParameterList(disabledFields);
		GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imEditDisableAction);
	}

	private void configureIdentifierTab(boolean isEditable) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkBaseComponent imIdentifierTableComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		imIdentifierTableComponent.getExtFilterTable().setEnabled(true);
		imIdentifierTableComponent.getExtFilterTable().getContainerDataSource().removeAllItems();
		imIdentifierTableComponent.setTableFieldEditable(isEditable);
		imIdentifierTableComponent.setTableReadOnly(!isEditable);
	}

	private void configurePricingTab(boolean isEditable) throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent imPricingTableComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE);
		imPricingTableComponent.getExtFilterTable().setEnabled(true);
		imPricingTableComponent.getExtFilterTable().getContainerDataSource().removeAllItems();
		imPricingTableComponent.setTableFieldEditable(isEditable);
		imPricingTableComponent.setTableReadOnly(!isEditable);
	}

	private void configureButton(String sourceComponentId, boolean isEditable) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterDeleteButton", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterAddResetButton", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterAddSaveButton");
		component.setCaption("UPDATE");
		component.setVisible(isEditable);
	}

	public void setValueToComponents(GtnWsItemMasterBean bean, String sourceComponentId, boolean isEditable) {
		try {
			GtnWsItemMasterInfoBean info = bean.getGtnWsItemMasterInfoBean();
			configureInformationTabComponents(sourceComponentId, info);
			configureIdentifierTab(isEditable);
			configurePricingTab(isEditable);
			configureButton(sourceComponentId, isEditable);
			setEditViewProperties(sourceComponentId, isEditable);
			loadNotesTab(isEditable);
		} catch (Exception systemExcption) {
			gtnLogger.error(systemExcption.getMessage(), systemExcption);
		}
	}

	private void loadNotesTab(boolean isEditable) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.resetAddMode();
		notesTab.removeAndDisablingComponents(false);

		notesTab.setNotesTabEnable(isEditable);
	}

	private void setEditViewProperties(String sourceComponentId, boolean value) {

		for (int i = 0; i < GtnFrameworkItemMasterStringContants.DISABLED_FIELDS.size(); i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.DISABLED_FIELDS.get(i),
					sourceComponentId).setComponentEnable(value);
		}

		String[] editVisibleFields = GtnFrameworkItemMasterStringContants.getEditVisibleFields();

		for (int i = 0; i < editVisibleFields.length; i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(editVisibleFields[i], sourceComponentId)
					.setComponentVisible(value);
		}

	}

	@SuppressWarnings("unchecked")
	private void loadTabAfterSave(GtnWsItemMasterBean reponseBean, boolean isEditable)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent imIdentifierTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		ExtContainer<GtnWsRecordBean> identifierContainer = (ExtContainer<GtnWsRecordBean>) imIdentifierTable
				.getExtFilterTable().getContainerDataSource();
		identifierContainer.removeAllItems();
		setTableHeaderAndVisibleColumnForIdentifier(isEditable);
		imIdentifierTable.getExtFilterTable().resetFilters();
		loadIdentifierTab(reponseBean.getGtnWsItemIdentifierBeanList(), identifierContainer);

		GtnUIFrameworkBaseComponent imPricingTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE);

		ExtContainer<GtnWsRecordBean> pricingContainer = (ExtContainer<GtnWsRecordBean>) imPricingTable
				.getExtFilterTable().getContainerDataSource();
		pricingContainer.removeAllItems();
		setTableHeaderAndVisibleColumnForPricing(isEditable);
		imPricingTable.getExtFilterTable().resetFilters();
		loadPricingTab();
	}

	private void loadIdentifierTab(List<GtnWsItemIdentifierBean> identifierSaveList,
			ExtContainer<GtnWsRecordBean> container) throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean idenBean;
			for (GtnWsItemIdentifierBean dto : identifierSaveList) {
				idenBean = new GtnWsRecordBean();
				idenBean.setRecordHeader(container.getRecordHeader());
				idenBean.addProperties("itemIrtQualifierName", dto.getItemQualifierName());
				idenBean.addProperties("itemIdentifier", dto.getItemIdentifierValue());
				idenBean.addProperties("identifierStatus", dto.getItemIdentifierStatus());
				idenBean.addProperties("startDate", dto.getStartDate());
				idenBean.addProperties("endDate", dto.getEndDate());
				idenBean.addProperties("entityCode", dto.getEntityCode());
				idenBean.addProperties("entityCodeName", dto.getEntityCode());
				idenBean.addProperties("modifiedBy", String.valueOf(dto.getModifiedByAsName()));
				idenBean.addProperties("modifiedDate", dto.getModifiedDate());
				idenBean.addProperties("createdBy", String.valueOf(dto.getCreatedByAsName()));
				idenBean.addProperties("createdDate", dto.getCreatedDate());
				idenBean.addProperties("identifierStatusDes", dto.getItemIdentifierStatusDes());
				idenBean.getProperties().add(dto.getItemQualifierMasterSid());
				idenBean.getProperties().add(dto.getItemIdentifierSid());
				container.addBean(idenBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException("Save Error", systemExcption);
		}

	}

	private void loadPricingTab() throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE)
				.getLogicFromPagedDataTable().startSearchProcess(new ArrayList<String>(), true);
	}

	private void loadNotesTab(String componentId, GtnWsItemMasterBean bean) {
		try {
			GtnUIFrameWorkActionConfig imEditNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			imEditNotesTabLoadAction.addActionParameter(bean.getNoteBeanList());
			imEditNotesTabLoadAction.addActionParameter(bean.getGtnWsItemMasterInfoBean().getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, imEditNotesTabLoadAction);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private void setTableHeaderAndVisibleColumnForIdentifier(boolean isEditable) {
		GtnUIFrameworkBaseComponent imIdentifierTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		imIdentifierTable
				.setTableColumns(isEditable ? GtnFrameworkItemMasterStringContants.getIdentifierVisibleColumnForEdit()
						: GtnFrameworkItemMasterStringContants.getIdentifierVisibleColumnForView());

		imIdentifierTable.setTableColumnHeaders(GtnFrameworkItemMasterStringContants.getIdentifierVisibleHeader());
	}

	private void setTableHeaderAndVisibleColumnForPricing(boolean isEditable) {
		GtnUIFrameworkBaseComponent imPricingTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE);
		imPricingTable
				.setTableColumns(isEditable ? GtnFrameworkItemMasterStringContants.getPricingVisibleColumnForEdit()
						: GtnFrameworkItemMasterStringContants.getPricingVisibleColumnForView());

		imPricingTable.setTableColumnHeaders(GtnFrameworkItemMasterStringContants.getPricingVisibleHeader());

	}

}
