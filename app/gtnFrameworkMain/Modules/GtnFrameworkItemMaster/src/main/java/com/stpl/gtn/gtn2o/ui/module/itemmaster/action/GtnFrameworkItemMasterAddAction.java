package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Component;

public class GtnFrameworkItemMasterAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterAddAction.class);

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
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
			GtnWsItemMasterInfoBean info = new GtnWsItemMasterInfoBean();
			configureInformationTabComponents(componentId, info);
			configureIdentifierTable();
			configurePricingTable();
			configureButton(componentId);
			loadNotesTab();
			setEditViewProperties(componentId, true);
			GtnUIFrameworkGlobalUI.addSessionProperty("itemMasterSid", 0);
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkItemMasterAddAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkItemMasterAddAction doAction ");
		}

	}

	private void loadNotesTab() {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.resetAddMode();
		notesTab.removeAndDisablingComponents(false);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void configureInformationTabComponents(String sourceComponentId, GtnWsItemMasterInfoBean info)
			throws GtnFrameworkGeneralException {

		List<Object> imFielsValues = Arrays.asList(new Object[] { info.getItemId(), info.getItemNo(),
				info.getItemDesc(), info.getItemName(), info.getItemId(), info.getItemNo(), info.getItemDesc(),
				info.getItemName(), info.getItemStatus(), info.getItemStartDate(), info.getItemEndDate(),
				info.getItemType(), info.getTherapeuticClass(), info.getBrandMasterSid(), info.getItemClass(),
				info.getForm(), info.getStrength(), info.getFirstSaleDate(), info.getNdc9(), info.getNdc8(),
				info.getPrimaryUom(), info.getSecondaryUom(), info.getLabelerCode(), info.getItemCode(),
				info.getPackageSize(), info.getPackageSizeCode(), info.getItemTypeIndication(), info.getItemCategory(),
				info.getPackageSizeIntroDate(), info.getManufacturerId(), info.getUdc1(), info.getUdc2(),
				info.getUdc3(), info.getUdc4(), info.getUdc5(), info.getUdc6(), info.getCompanyMasterSid(),
				info.getDosesPerUnit(), info.getShelfLife(), info.getShelfLifeType(), info.getLastLotExpirationDate(),
				info.getAuthorizedGenericStartDate(), info.getPediatricExclusiveStartDate(),
				info.getClottingFactorStartDate(), info.getDiscontinuationDate(), info.getAuthorizedGenericEndDate(),
				info.getPediatricExclusiveEndDate(), info.getClottingFactorEndDate(), info.getDivestitureDate(),
				info.getAcquisitionDate(), info.getNonFederalExpirationDate(), info.getMarketTerminationDate(),
				info.getBaseCpiPeriod(), info.getAuthorizedGeneric(), info.getPediatricExclusiveIndicator(),
				info.getClottingFactorIndicator(), info.getDualPricingIndicator(), info.getNewFormulationIndicator(),
				info.getUpps(), info.getBaselineAmp(), info.getBaseCpi(), info.getAcquiredAmp(), info.getAcquiredBamp(),
				info.getDra(), info.getObraBamp(), info.getNewFormulationStartDate(), info.getNewFormulationEndDate(),
				info.getNewFormulation(), null, GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,null });

		GtnUIFrameWorkActionConfig imAddDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		imAddDefaultValueActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.IM_FIELDS);
		imAddDefaultValueActionConfig.addActionParameter(imFielsValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imAddDefaultValueActionConfig);

		List<Object> disabledFields = Arrays.asList(new Object[] { "itemInformationTabCreatedBy",
				"itemInformationCreatedDate", "itemInformationTabModifiedBy", "itemInformationModifiedDate",
				"itemInformationTabSystemId", "itemInformationTabBatchID" });
		List<Object> disabledFieldValues = Arrays.asList(new Object[] { getUser(), new Date(),
				GtnFrameworkCommonStringConstants.STRING_EMPTY, info.getModifiedDate(),
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY });
		GtnUIFrameWorkActionConfig imAddDisableFieldsPopulateeActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		imAddDisableFieldsPopulateeActionConfig.addActionParameter(disabledFields);
		imAddDisableFieldsPopulateeActionConfig.addActionParameter(disabledFieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imAddDisableFieldsPopulateeActionConfig);

		GtnUIFrameWorkActionConfig imDisableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DISABLE_ACTION);
		imDisableAction.setActionParameterList(disabledFields);
		GtnUIFrameworkActionExecutor.executeSingleAction(sourceComponentId, imDisableAction);
                
      boolean isNDC9 = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_ITEM_TYPE)
                .getCaptionFromComboBox().equals("NDC 9");
        GtnUIFrameworkBaseComponent itemTypeEditCheck = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNewFormulationIndicator", sourceComponentId);
        if (isNDC9) {
            itemTypeEditCheck.selectOptionGroupValue("Yes");
        } else {
            itemTypeEditCheck.selectOptionGroupValue("No");
        }
        itemTypeEditCheck.selectOptionGroupValue("Yes", isNDC9);
   
	}

	private String getUser() {
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		String userName = response.getGtnWsGeneralResponse().getUserName();
		GtnUIFrameworkGlobalUI.addSessionProperty("userName", userName);
		return userName;
	}

	private void configureIdentifierTable() throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent imIdentifierTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		imIdentifierTableBaseComponent.getExtFilterTable().setEnabled(true);
		imIdentifierTableBaseComponent.getExtFilterTable().getContainerDataSource().removeAllItems();
		imIdentifierTableBaseComponent
				.setTableColumns(GtnFrameworkItemMasterStringContants.getIdentifierVisibleColumnForEdit());
		imIdentifierTableBaseComponent
				.setTableColumnHeaders(GtnFrameworkItemMasterStringContants.getIdentifierVisibleHeader());
		imIdentifierTableBaseComponent.resetTableFiter();
		imIdentifierTableBaseComponent.setTableFieldEditable(true);
		imIdentifierTableBaseComponent.setTableReadOnly(false);
	}

	private void configurePricingTable() throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent imPricingTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE);
		imPricingTableBaseComponent.getExtFilterTable().setEnabled(true);

		imPricingTableBaseComponent.getExtPagedTable().getContainerDataSource().removeAllItems();
		imPricingTableBaseComponent.getLogicFromPagedDataTable().startSearchProcess(new ArrayList<String>(), true);
		imPricingTableBaseComponent
				.setTableColumns(GtnFrameworkItemMasterStringContants.getPricingVisibleColumnForEdit());
		imPricingTableBaseComponent
				.setTableColumnHeaders(GtnFrameworkItemMasterStringContants.getPricingVisibleHeader());
		imPricingTableBaseComponent.resetTableFiter();
		imPricingTableBaseComponent.setTableFieldEditable(true);
		imPricingTableBaseComponent.setTableReadOnly(false);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnItemMasterRequestBuilder().getItemMasterWebServiceRequest();
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_PRICING_CREATE_TEMP_TABLE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private void configureButton(String sourceComponentId) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterDeleteButton", sourceComponentId)
				.setComponentVisible(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterAddResetButton", sourceComponentId)
				.setComponentVisible(true);
		Component component = GtnUIFrameworkGlobalUI.getVaadinComponent("itemMasterAddSaveButton");
		component.setCaption("SAVE");
		component.setVisible(true);
	}

	private void setEditViewProperties(String sourceComponentId, boolean value) {

		for (int i = 0; i < GtnFrameworkItemMasterStringContants.DISABLED_FIELDS.size(); i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.DISABLED_FIELDS.get(i),
					sourceComponentId).setComponentEnable(value);
		}

		String[] visibleFields = new String[] { "itemMasterIdentifierInformationPanel", "identifierAttachButton",
				"identifierRemoveButton", "itemMasterPricingPanel", "pricingAttachButton", "pricingRemoveButton" };

		for (int i = 0; i < visibleFields.length; i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(visibleFields[i], sourceComponentId)
					.setComponentVisible(value);
		}

	}

}
