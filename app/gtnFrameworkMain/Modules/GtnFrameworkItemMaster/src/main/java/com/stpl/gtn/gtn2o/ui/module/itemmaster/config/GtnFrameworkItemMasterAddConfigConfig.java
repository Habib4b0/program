package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingTempTableClearAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkItemMasterAddConfigConfig {

	public GtnUIFrameworkViewConfig getItemMasterAddView() {

		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig addView = componentConfig.getViewConfig("Add View", "V002", false);
		addComponentList(addView, componentConfig);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addItemInfoPanel(componentList, componentConfig);
		addTabLayout(componentList, componentConfig);
		addSaveButtonLayout(componentList, componentConfig);
	}

	private void addItemInfoPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig itemMasterMainPanel = componentConfig.getPanelConfig("itemInformationPanel",
				false, null);
		itemMasterMainPanel.setComponentName("Item Information");
		itemMasterMainPanel.setAuthorizationIncluded(true);
		componentList.add(itemMasterMainPanel);
		addItemInfoFieldLayout(componentList, componentConfig, itemMasterMainPanel.getComponentId());
	}

	private void addItemInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String parentId) {

		GtnUIFrameworkComponentConfig itemInfoLayoutConfig = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_INFORMATION_LAYOUT, true, parentId,
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(itemInfoLayoutConfig);
		addItemInfoFieldComponent(componentList, componentConfig);
	}

	private void addItemInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addItemInfoItemId(componentList, componentConfig);
		addItemInfoItemNo(componentList, componentConfig);
		addItemInfoItemName(componentList, componentConfig);
		addItemInfoDesc(componentList, componentConfig);
	}

	private void addItemInfoItemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdLayout = componentConfig.getHorizontalLayoutConfig("itemIdlayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_LAYOUT);
		componentList.add(itemIdLayout);

		GtnUIFrameworkComponentConfig itemId = componentConfig.getUIFrameworkComponentConfig("itemId", true,
				itemIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemId.setAuthorizationIncluded(true);
		itemId.setComponentName("Item ID");

		GtnUIFrameworkTextBoxConfig itemIdConfig = new GtnUIFrameworkTextBoxConfig();
		itemIdConfig.setEnable(false);
		itemId.setGtnTextBoxConfig(itemIdConfig);

		componentList.add(itemId);
	}

	private void addItemInfoItemNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNoLayout = componentConfig.getHorizontalLayoutConfig("itemNolayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_LAYOUT);
		componentList.add(itemNoLayout);

		GtnUIFrameworkComponentConfig itemNo = componentConfig.getUIFrameworkComponentConfig("itemNo", true,
				itemNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemNo.setAuthorizationIncluded(true);
		itemNo.setComponentName("Item NO");

		GtnUIFrameworkTextBoxConfig itemNoConfig = new GtnUIFrameworkTextBoxConfig();
		itemNoConfig.setEnable(false);
		itemNo.setGtnTextBoxConfig(itemNoConfig);

		componentList.add(itemNo);
	}

	private void addItemInfoItemName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNameLayout = componentConfig.getHorizontalLayoutConfig("itemNamelayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_LAYOUT);
		componentList.add(itemNameLayout);

		GtnUIFrameworkComponentConfig itemName = componentConfig.getUIFrameworkComponentConfig("itemName", true,
				itemNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemName.setAuthorizationIncluded(true);
		itemName.setComponentName("Item Name");

		GtnUIFrameworkTextBoxConfig itemNameConfig = new GtnUIFrameworkTextBoxConfig();
		itemNameConfig.setEnable(false);
		itemName.setGtnTextBoxConfig(itemNameConfig);

		componentList.add(itemName);
	}

	private void addItemInfoDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemDescLayout = componentConfig.getHorizontalLayoutConfig("itemDesclayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_LAYOUT);
		componentList.add(itemDescLayout);

		GtnUIFrameworkComponentConfig itemDesc = componentConfig.getUIFrameworkComponentConfig("itemDesc", true,
				itemDescLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemDesc.setAuthorizationIncluded(true);
		itemDesc.setComponentName("Item Description");

		GtnUIFrameworkTextBoxConfig itemDescConfig = new GtnUIFrameworkTextBoxConfig();
		itemDescConfig.setEnable(false);
		itemDesc.setGtnTextBoxConfig(itemDescConfig);
		componentList.add(itemDesc);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig mainLayoutConfig = componentConfig.getHorizontalLayoutConfig("itemTabsheetLayout",
				false, null);
		mainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(mainLayoutConfig);

		addTabSheet(componentList, componentConfig);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterTabSheetConfig = componentConfig.getUIFrameworkComponentConfig(
				"tabSheet", true, "itemTabsheetLayout", GtnUIFrameworkComponentType.TABSHEET);
		itemMasterTabSheetConfig.setAuthorizationIncluded(true);
		itemMasterTabSheetConfig.setComponentName("Tab Sheet");
		itemMasterTabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(itemMasterTabSheetConfig);
		// find the No of tabs
		GtnUIFrameworkTabConfig itemInformationTab = componentConfig.getTabConfig("itemInformationTab",
				"Item Information");
		List<GtnUIFrameworkComponentConfig> itemInformationTabConfig = new ArrayList<>();
		itemInformationTab.setTabLayoutComponentConfigList(itemInformationTabConfig);
		new GtnFrameworkItemMasterInformationTabConfig().addItemInformationTab(itemInformationTabConfig);

		GtnUIFrameworkTabConfig addressTab = componentConfig.getTabConfig("itemMasterAdditionalInformationTab",
				"Additional Information");
		List<GtnUIFrameworkComponentConfig> addressTabConfig = new ArrayList<>();
		addressTab.setTabLayoutComponentConfigList(addressTabConfig);
		new GtnFrameworkItemMasterInfoTabConfig().addAdditionalInfoTab(addressTabConfig);

		GtnUIFrameworkTabConfig identifierTab = componentConfig.getTabConfig("itemMasteridentifierTab", "Identifier");
		List<GtnUIFrameworkComponentConfig> identifierTabConfig = new ArrayList<>();
		identifierTab.setTabLayoutComponentConfigList(identifierTabConfig);
		new GtnFrameworkItemMasterIdentifierTabConfig().addIdentifierTab(identifierTabConfig);

		GtnUIFrameworkTabConfig itemPricingTab = componentConfig.getTabConfig("itemMasterPricingTab", "Pricing");
		List<GtnUIFrameworkComponentConfig> itemPricingTabConfig = new ArrayList<>();
		itemPricingTab.setTabLayoutComponentConfigList(itemPricingTabConfig);
		new GtnFrameworkItemMasterPricingTabConfig().addPricingTab(itemPricingTabConfig);

		GtnUIFrameworkTabConfig notesTab = componentConfig.getTabConfig("notesTab", "Notes");
		List<GtnUIFrameworkComponentConfig> notesTabConfig = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfig);
		addNotesTab(notesTabConfig);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(itemInformationTab);
		tabConfigList.add(addressTab);
		tabConfigList.add(identifierTab);
		tabConfigList.add(itemPricingTab);
		tabConfigList.add(notesTab);
		itemMasterTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("notesTab");
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.NOTES_TAB);
		layoutConfig.setAuthorizationIncluded(true);

		layoutConfig.setNotesTabConfig(new GtnUIFrameworkNotesTabConfig());

		componentList.add(layoutConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		gtnLayout.setAddToParent(false);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList, componentConfig);
		addDeleteButtonComponent(componentList, componentConfig);
		addBackButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig backButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"itemMasterAddBackButtonlayout", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		componentList.add(backButtonLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemMasterAddBackButton", true, backButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("BACK");
		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> backActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.CONFIRMATION);
		alertParamsList.add("Any unsaved information will not be saved. Do you want to proceed?");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(Arrays.asList("itemSystemID", "IMasterSearchItemId", "IMasterSearchItemNo", "IMasterSearchItemName",
				"IMasterSearchItemDesc", "IMasterSearchItemStatus", "IMasterSearchItemType", "therapeuticClass",
				"IMasterSearchNdc9", "form", "IMasterSearchQualifierName", "IMasterSearchItemIdentifier", "brand",
				"IMasterSearchNdc8", "strength", "IMasterSearchBatchId", "itemMastersearchResultTable"));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null));
		resetActionConfig.setActionParameterList(params);
		onSucessActionConfigList.add(resetActionConfig);

		GtnUIFrameWorkActionConfig dropTempTableAction = new GtnUIFrameWorkActionConfig();
		dropTempTableAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dropTempTableAction.addActionParameter(GtnFrameworkItemMasterPricingTempTableClearAction.class.getName());
		onSucessActionConfigList.add(dropTempTableAction);

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		confirmationActionConfig.setActionParameterList(alertParamsList);
		backActionConfigList.add(confirmationActionConfig);
		backButtonConfig.setGtnUIFrameWorkActionConfigList(backActionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterSaveLayout = componentConfig.getHorizontalLayoutConfig(
				"itemMasterAddSaveButtonlayout", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		componentList.add(itemMasterSaveLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemMasterAddSaveButton", true, itemMasterSaveLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("SAVE");
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> saveActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customCommonValidationAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_COMMON_VALIDATION_ACTION);
		saveActionConfigList.add(customCommonValidationAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.CONFIRMATION);
		alertParamsList.add("Save record ");
		alertParamsList.add(GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_ITEM_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		alertParamsList.add(onSucessActionConfigList);

		GtnUIFrameWorkActionConfig customAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_SAVE_ACTION);
		customAction.addActionParameter(Arrays.asList("itemInformationTabItemId", "itemInformationTabItemNo",
				"itemInformationTabItemDesc", GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_ITEM_NAME,
				"itemInformationTabItemStatus", "itemInformationItemStartDate", "itemInformationItemEndDate",
				"itemInformationTabItemType", "itemInformationTabtherapyClass", "itemInformationTabitemMasterBrand",
				"itemInformationTabItemClass", "itemInformationTabItemForm", "itemInformationTabItemStrength",
				"itemInformationTabFirstSaleDate", "itemInformationTabNDC8", "itemInformationTabPrimaryUOM",
				"itemInformationTabSecondaryUOM", "itemInformationTabLabelerCode", "itemInformationTabItemCode",
				"itemInformationTabPackageSize", "itemInformationTabPackageSizeCode",
				"itemInformationTabItemTypeIndication", "itemInformationTabItemCategory",
				"itemInformationTabPackageSizeIntroduction", "itemInformationTabManufacturerID",
				"itemInformationTabUDC1", "itemInformationTabUDC2", "itemInformationTabUDC3", "itemInformationTabUDC4",
				"itemInformationTabUDC5", "itemInformationTabUDC6", "itemInformationTabOrganizationKey",
				"additionalInformationTabDosesPerUnit", "additionalInformationTabShelfLife",
				"additionalInformationTabShelfLifeType", "itemInformationTabLastLotExpirationDate",
				"itemInformationTabAuthorizedGenericStartDate", "itemInformationTabPediatricStartDate",
				"itemInformationTabClottingFactorStartDate", "itemInformationTabDiscountinuationDate",
				"itemInformationTabAuthorizedGenericEndDate", "itemInformationTabPediatricEndDate",
				"itemInformationTabClottingFactorEndDate", "itemInformationTabDivestitureDate",
				"itemInformationTabAcquisitionDate", "itemInformationTabNonFederalExpirationDate",
				"itemInformationTabMarketTerminationDate", "itemInformationTabNewFormStartDate",
				"itemInformationTabBaseCPIPeriod", "itemInformationTabBaseNewFormEndDate",
				"itemInformationTabAuthorizedGenericIndicator", "itemInformationTabPediatricExclusiveIndicator",
				"itemInformationTabClottingFactorIndicator", "itemInformationTabDualPricingIndicator",
				"itemInformationTabNewFormulationIndicator", "additionalInformationTabNewFormulationLookup"));

		customAction.addActionParameter(Arrays.asList("itemId", "itemNo", "itemDesc", "itemName", "itemStatus",
				"itemStartDate", "itemEndDate", "itemType", "therapeuticClass", "brandMasterSid", "itemClass", "form",
				"strength", "firstSaleDate", "ndc8", "primaryUom", "secondaryUom", "labelerCode", "itemCode",
				"packageSize", "packageSizeCode", "itemTypeIndication", "itemCategory", "packageSizeIntroDate",
				"manufacturerId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "companyMasterSid", "dosesPerUnit",
				"shelfLife", "shelfLifeType", "lastLotExpirationDate", "authorizedGenericStartDate",
				"pediatricExclusiveStartDate", "clottingFactorStartDate", "discontinuationDate",
				"authorizedGenericEndDate", "pediatricExclusiveEndDate", "clottingFactorEndDate", "divestitureDate",
				"acquisitionDate", "nonFederalExpirationDate", "marketTerminationDate", "newFormulationStartDate",
				"baseCpiPeriod", "newFormulationEndDate", "authorizedGeneric", "pediatricExclusiveIndicator",
				"clottingFactorIndicator", "dualPricingIndicator", "newFormulationIndicator", "newFormulation"));
		onSucessActionConfigList.add(customAction);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		saveActionConfigList.add(confirmationActionConfig);
		saveButtonConfig.setGtnUIFrameWorkActionConfigList(saveActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig resetButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"itemMasterAddResetButtonlayout", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		componentList.add(resetButtonLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemMasterAddResetButton", true, resetButtonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("RESET");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG_RESET_001);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_RESET_ACTION);
		onSucessActionConfigList.add(customAction);
		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		resetActionConfigList.add(confirmationActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig deleteButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"itemMasterDeleteButtonlayout", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		componentList.add(deleteButtonLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemMasterDeleteButton", true, deleteButtonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setAuthorizationIncluded(true);
		deleteButtonConfig.setComponentName("DELETE");
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> deleteActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.CONFIRMATION);
		alertParamsList.add("Are you sure you want to delete record ");
		alertParamsList.add(GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_ITEM_NAME);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);

		GtnUIFrameWorkActionConfig deleteActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_DELETE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		deleteActionConfigList.add(confirmationActionConfig);

		GtnUIFrameWorkActionConfig loadIndexTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadIndexTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadIndexTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		loadIndexTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				"form", GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER, GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID));
		loadIndexTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.BRAND,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9));
		onSucessActionConfigList.add(loadIndexTableActionConfig);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(deleteActionConfigList);
	}

}
