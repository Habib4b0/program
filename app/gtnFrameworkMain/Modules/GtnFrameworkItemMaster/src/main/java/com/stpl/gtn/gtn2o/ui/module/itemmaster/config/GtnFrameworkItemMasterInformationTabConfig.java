package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterItemTypeAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkItemMasterInformationTabConfig {

	public void addItemInformationTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

		GtnUIFrameworkComponentConfig itemInformationTabMainLayout = componentConfig
				.getRootLayoutConfig("itemInformationTab", GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, true);
		componentList.add(itemInformationTabMainLayout);

		GtnUIFrameworkComponentConfig itemInformationTabMainCssLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT, true,
				itemInformationTabMainLayout.getComponentId(), GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(itemInformationTabMainCssLayout);

		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addItemId(componentList, componentConfig);
		addItemNo(componentList, componentConfig);
		addItemName(componentList, componentConfig);
		addItemDesc(componentList, componentConfig);
		addItemStatus(componentList, componentConfig);
		addItemStartDate(componentList, componentConfig);
		addItemEndDate(componentList, componentConfig);
		addItemType(componentList, componentConfig);
		addTherapyClass(componentList, componentConfig);
		addBrand(componentList, componentConfig);
		addBrandId(componentList, componentConfig);
		addDisplayBrand(componentList, componentConfig);
		addItemClass(componentList, componentConfig);
		addForm(componentList, componentConfig);
		addItemStrength(componentList, componentConfig);
		addFirstSaleDate(componentList, componentConfig);
		addNDC9(componentList, componentConfig);
		addNDC9DropDown(componentList, componentConfig);
		addNDC8(componentList, componentConfig);
		addPrimaryUOM(componentList, componentConfig);
		addSecondaryUOM(componentList, componentConfig);
		addLabelerCode(componentList, componentConfig);
		addItemCode(componentList, componentConfig);
		addPackageSize(componentList, componentConfig);
		addPackageSizeCode(componentList, componentConfig);
		addItemTypeIndication(componentList, componentConfig);
		addItemCategory(componentList, componentConfig);
		addUPPS(componentList, componentConfig);
		addPackageSizeIntroduction(componentList, componentConfig);
		addManufacturerID(componentList, componentConfig);
		addUDC1(componentList, componentConfig);
		addUDC2(componentList, componentConfig);
		addUDC3(componentList, componentConfig);
		addUDC4(componentList, componentConfig);
		addUDC5(componentList, componentConfig);
		addUDC6(componentList, componentConfig);
		addManufacturerName(componentList, componentConfig);
		addCreatedBy(componentList, componentConfig);
		addCreatedDate(componentList, componentConfig);
		addModifiedBy(componentList, componentConfig);
		addModifiedDate(componentList, componentConfig);
		addSystemId(componentList, componentConfig);
		addBatchID(componentList, componentConfig);
		addOrganizationKey(componentList, componentConfig);
	}

	private void addItemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemIdlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemIdLayout);

		GtnUIFrameworkComponentConfig itemIdConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemId", true, itemIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemIdConfig.setAuthorizationIncluded(true);
		itemIdConfig.setComponentName("Item ID");
		itemIdConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig itemIdMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemIdMaxLengthConfig.setMaximumLength(38);
		itemIdConfig.setGtnTextBoxConfig(itemIdMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemIdValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), false,
				itemIdConfig.getComponentName() + GtnFrameworkRegexStringConstants.ALPHANUMERIC_ERROR_MSG,
				GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		itemIdValidationConfig.setMaxLength(itemIdMaxLengthConfig.getMaximumLength());
		itemIdConfig.setGtnUIFrameworkValidationConfig(itemIdValidationConfig);

		componentList.add(itemIdConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNoLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemNolayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemNoLayout);

		GtnUIFrameworkComponentConfig itemNoConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemNo", true, itemNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemNoConfig.setAuthorizationIncluded(true);
		itemNoConfig.setComponentName("Item No");
		itemNoConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig itemNoMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemNoMaxLengthConfig.setMaximumLength(50);
		itemNoConfig.setGtnTextBoxConfig(itemNoMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemNoValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), false,
				itemNoConfig.getComponentName() + GtnFrameworkRegexStringConstants.ALPHANUMERIC_ERROR_MSG,
				GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		itemNoValidationConfig.setMaxLength(itemNoMaxLengthConfig.getMaximumLength());
		itemNoConfig.setGtnUIFrameworkValidationConfig(itemNoValidationConfig);

		componentList.add(itemNoConfig);
	}

	private void addItemDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemDescLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemDesclayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemDescLayout);

		GtnUIFrameworkComponentConfig itemDescConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemDesc", true, itemDescLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemDescConfig.setAuthorizationIncluded(true);
		itemDescConfig.setComponentName("Item Description");

		GtnUIFrameworkTextBoxConfig itemDescMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemDescMaxLengthConfig.setMaximumLength(250);
		itemDescConfig.setGtnTextBoxConfig(itemDescMaxLengthConfig);

		componentList.add(itemDescConfig);
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNameLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemNamelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemNameLayout);

		GtnUIFrameworkComponentConfig itemNameConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemName", true, itemNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNameConfig.setAuthorizationIncluded(true);
		itemNameConfig.setComponentName("Item Name");
		itemNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig itemNameMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemNameMaxLengthConfig.setMaximumLength(100);
		itemNameConfig.setGtnTextBoxConfig(itemNameMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemNameValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), false,
				itemNameConfig.getComponentName() + GtnFrameworkRegexStringConstants.ALPHANUMERIC_ERROR_MSG,
				GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		itemNameValidationConfig.setMaxLength(itemNameMaxLengthConfig.getMaximumLength());
		itemNameConfig.setGtnUIFrameworkValidationConfig(itemNameValidationConfig);

		componentList.add(itemNameConfig);
	}

	private void addItemStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemStatusLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemStatuslayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemStatusLayout);

		GtnUIFrameworkComponentConfig itemStatus = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemStatus", true, itemStatusLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemStatus.setAuthorizationIncluded(true);
		itemStatus.setComponentName("Item Status");
		itemStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemStatus);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemStatus.setGtnComboboxConfig(itemStatusConfig);

		GtnUIFrameworkValidationConfig itemStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		itemStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemStatus.setGtnUIFrameworkValidationConfig(itemStatusValidationConfig);

	}

	private void addItemStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationItemStartDatelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemStartDateLayout);

		GtnUIFrameworkComponentConfig itemStartDate = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationItemStartDate", true, itemStartDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		itemStartDate.setAuthorizationIncluded(true);
		itemStartDate.setComponentName("Item Start Date");
		itemStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkDateFieldConfig itemStartDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		itemStartDateFieldConfig.setImmediate(true);
		itemStartDate.setGtnDateFieldConfig(itemStartDateFieldConfig);

		componentList.add(itemStartDate);

		GtnUIFrameworkValidationConfig itemStartDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		itemStartDate.setGtnUIFrameworkValidationConfig(itemStartDateValidationConfig);
	}

	private void addItemEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationItemEndDatelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemEndDateLayout);

		GtnUIFrameworkComponentConfig itemEndDate = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationItemEndDate", true, itemEndDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		itemEndDate.setAuthorizationIncluded(true);
		itemEndDate.setComponentName("Item End Date");

		GtnUIFrameworkDateFieldConfig itemEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		itemEndDateFieldConfig.setImmediate(true);
		itemEndDate.setGtnDateFieldConfig(itemEndDateFieldConfig);

		GtnUIFrameworkValidationConfig itemEndDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		itemEndDate.setGtnUIFrameworkValidationConfig(itemEndDateValidationConfig);

		componentList.add(itemEndDate);
	}

	private void addItemType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemTypeLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemTypelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemTypeLayout);

		GtnUIFrameworkComponentConfig itemType = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemType", true, itemTypeLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemType.setAuthorizationIncluded(true);
		itemType.setComponentName("Item Type");
		componentList.add(itemType);

		GtnUIFrameworkComboBoxConfig itemTypeConfig = componentConfig.getComboBoxConfig("ITEM_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemMasterItemTypeAction.class.getName());
		actionConfigList.add(customAction);
		itemType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		itemType.setGtnComboboxConfig(itemTypeConfig);
		itemType.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addTherapyClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig therapyClassLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabTherapyClasslayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(therapyClassLayout);

		GtnUIFrameworkComponentConfig therapyClassConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabtherapyClass", true, therapyClassLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		therapyClassConfig.setAuthorizationIncluded(true);
		therapyClassConfig.setComponentName("Therapeutic Class");

		GtnUIFrameworkComboBoxConfig tradeClassConfig = componentConfig.getComboBoxConfig("THERAPEUTIC_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		therapyClassConfig.setGtnComboboxConfig(tradeClassConfig);

		componentList.add(therapyClassConfig);
	}

	private void addBrand(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig brandLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabitemMasterBrandlayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(brandLayout);

		GtnUIFrameworkComponentConfig brand = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabitemMasterBrand", true, brandLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		brand.setAuthorizationIncluded(true);
		brand.setComponentName("Brand");
		brand.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(brand);

		GtnUIFrameworkComboBoxConfig brandConfig = componentConfig.getComboBoxConfig("Brands",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		brand.setGtnComboboxConfig(brandConfig);

		GtnUIFrameworkValidationConfig brandValidationConfig = new GtnUIFrameworkValidationConfig();
		brandValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		brand.setGtnUIFrameworkValidationConfig(brandValidationConfig);

		List<GtnUIFrameWorkActionConfig> brandActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig brandCustomAction = new GtnUIFrameWorkActionConfig();
		brandCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		brandCustomAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_BRAND_VALUECHANGE_ACTION);
		brandActionConfigList.add(brandCustomAction);
		brand.setGtnUIFrameWorkActionConfigList(brandActionConfigList);

	}

	private void addBrandId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig brandIdLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabBrandIdlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(brandIdLayout);

		GtnUIFrameworkComponentConfig brandId = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabBrandId", true, brandIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		brandId.setAuthorizationIncluded(true);
		brandId.setComponentName("Brand ID");
		brandId.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(brandId);

		GtnUIFrameworkTextBoxConfig brandIdConfig = new GtnUIFrameworkTextBoxConfig();
		brandIdConfig.setEnable(false);
		brandId.setGtnTextBoxConfig(brandIdConfig);

		GtnUIFrameworkValidationConfig brandIdValidationConfig = new GtnUIFrameworkValidationConfig();
		brandIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		brandId.setGtnUIFrameworkValidationConfig(brandIdValidationConfig);

	}

	private void addDisplayBrand(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig displayBrandLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabDisplayBrandlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(displayBrandLayout);

		GtnUIFrameworkComponentConfig itemName = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabDisplayBrand", true, displayBrandLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemName.setAuthorizationIncluded(true);
		itemName.setComponentName("Display Brand");

		GtnUIFrameworkTextBoxConfig itemNameConfig = new GtnUIFrameworkTextBoxConfig();
		itemNameConfig.setEnable(false);
		itemName.setGtnTextBoxConfig(itemNameConfig);

		componentList.add(itemName);
	}

	private void addItemClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemClassLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemClasslayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemClassLayout);

		GtnUIFrameworkComponentConfig itemClass = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemClass", true, itemClassLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemClass.setAuthorizationIncluded(true);
		itemClass.setComponentName("Item Class");
		itemClass.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemClass);

		GtnUIFrameworkComboBoxConfig itemClassConfig = componentConfig.getComboBoxConfig("ITEM_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemClass.setGtnComboboxConfig(itemClassConfig);

	}

	private void addForm(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig formLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemFormlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(formLayout);

		GtnUIFrameworkComponentConfig form = componentConfig.getUIFrameworkComponentConfig("itemInformationTabItemForm",
				true, formLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		form.setAuthorizationIncluded(true);
		form.setComponentName("Form");
		form.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(form);

		GtnUIFrameworkComboBoxConfig formConfig = componentConfig.getComboBoxConfig("FORM",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		form.setGtnComboboxConfig(formConfig);

		GtnUIFrameworkValidationConfig formValidationConfig = new GtnUIFrameworkValidationConfig();
		formValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		form.setGtnUIFrameworkValidationConfig(formValidationConfig);

	}

	private void addItemStrength(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig strengthLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemStrengthlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(strengthLayout);

		GtnUIFrameworkComponentConfig strength = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemStrength", true, strengthLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		strength.setAuthorizationIncluded(true);
		strength.setComponentName("Strength");
		strength.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(strength);

		GtnUIFrameworkComboBoxConfig strengthConfig = componentConfig.getComboBoxConfig("STRENGTH",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		strength.setGtnComboboxConfig(strengthConfig);

		GtnUIFrameworkValidationConfig strengthValidationConfig = new GtnUIFrameworkValidationConfig();
		strengthValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		strength.setGtnUIFrameworkValidationConfig(strengthValidationConfig);

	}

	private void addFirstSaleDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig firstSaleDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabFirstSaleDatelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(firstSaleDateLayout);

		GtnUIFrameworkComponentConfig firstSaleDate = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabFirstSaleDate", true, firstSaleDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		firstSaleDate.setAuthorizationIncluded(true);
		firstSaleDate.setComponentName("First Sale Date");

		GtnUIFrameworkDateFieldConfig firstSaleDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		firstSaleDateFieldConfig.setImmediate(true);
		firstSaleDate.setGtnDateFieldConfig(firstSaleDateFieldConfig);

		GtnUIFrameworkValidationConfig firstSaleDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		firstSaleDate.setGtnUIFrameworkValidationConfig(firstSaleDateValidationConfig);

		componentList.add(firstSaleDate);

	}

	private void addNDC9(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc9Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabNDC9layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(ndc9Layout);

		GtnUIFrameworkComponentConfig ndc9Config = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabNDC9", true, ndc9Layout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		ndc9Config.setComponentName("NDC 9");
		ndc9Config.setAuthorizationIncluded(true);
		
		ndc9Config.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig ndc9MaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		ndc9MaxLengthConfig.setImmediate(true);
                ndc9MaxLengthConfig.setEnable(false);
		ndc9Config.setGtnTextBoxConfig(ndc9MaxLengthConfig);
		componentList.add(ndc9Config);

	}

	private void addNDC9DropDown(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc9dropLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabNDC9Droplayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(ndc9dropLayout);
		ndc9dropLayout.setVisible(false);
		GtnUIFrameworkComponentConfig valueNdc9Ddlb = componentConfig.getUIFrameworkComponentConfig(
				"ifpItemInformationTabValueDropDown", true, "itemInformationTabNDC9Droplayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		valueNdc9Ddlb.setAuthorizationIncluded(true);
		valueNdc9Ddlb.setComponentName("NDC 9");
		componentList.add(valueNdc9Ddlb);
		valueNdc9Ddlb.setVisible(false);
		valueNdc9Ddlb.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		GtnUIFrameworkComboBoxConfig companyTypeConfig = componentConfig.getComboBoxConfig("Ndc9ItemId",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		valueNdc9Ddlb.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addNDC8(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc8Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabNDC8layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(ndc8Layout);

		GtnUIFrameworkComponentConfig ndc8Config = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabNDC8", true, ndc8Layout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		ndc8Config.setAuthorizationIncluded(true);
		ndc8Config.setComponentName("NDC 8");
		ndc8Config.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig ndc8MaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		ndc8MaxLengthConfig.setImmediate(true);
		ndc8Config.setGtnTextBoxConfig(ndc8MaxLengthConfig);
		componentList.add(ndc8Config);
	}

	private void addPrimaryUOM(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig primaryUOMLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPrimaryUOMlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(primaryUOMLayout);

		GtnUIFrameworkComponentConfig primaryUOM = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPrimaryUOM", true, primaryUOMLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		primaryUOM.setAuthorizationIncluded(true);
		primaryUOM.setComponentName("Primary UOM");
		componentList.add(primaryUOM);

		GtnUIFrameworkComboBoxConfig primaryUOMConfig = componentConfig.getComboBoxConfig("UOM",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		primaryUOM.setGtnComboboxConfig(primaryUOMConfig);

	}

	private void addSecondaryUOM(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig secondaryUOMLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabSecondaryUOMlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(secondaryUOMLayout);

		GtnUIFrameworkComponentConfig secondaryUOM = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabSecondaryUOM", true, secondaryUOMLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		secondaryUOM.setAuthorizationIncluded(true);
		secondaryUOM.setComponentName("Secondary UOM");
		componentList.add(secondaryUOM);

		GtnUIFrameworkComboBoxConfig itemCategoryConfig = new GtnUIFrameworkComboBoxConfig();
		itemCategoryConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemCategoryConfig.setComboBoxType("UOM");
		secondaryUOM.setGtnComboboxConfig(itemCategoryConfig);

	}

	private void addLabelerCode(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig labelerCodeLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabLabelerCodelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(labelerCodeLayout);

		GtnUIFrameworkComponentConfig labelerCode = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabLabelerCode", true, labelerCodeLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		labelerCode.setAuthorizationIncluded(true);
		labelerCode.setComponentName("Labeler Code");

		GtnUIFrameworkDateFieldConfig labelerCodeConfig = new GtnUIFrameworkDateFieldConfig();
		labelerCodeConfig.setImmediate(true);
		labelerCode.setGtnDateFieldConfig(labelerCodeConfig);

		GtnUIFrameworkValidationConfig labelerCodeValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Labeler Code can only be Alphanumeric", GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		labelerCode.setGtnUIFrameworkValidationConfig(labelerCodeValidationConfig);

		componentList.add(labelerCode);
	}

	private void addItemCode(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemCodeLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemCodelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemCodeLayout);

		GtnUIFrameworkComponentConfig itemCodeConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemCode", true, itemCodeLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemCodeConfig.setAuthorizationIncluded(true);
		itemCodeConfig.setComponentName("Item Code");
		itemCodeConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig itemCodeMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemCodeMaxLengthConfig.setMaximumLength(25);
		itemCodeConfig.setGtnTextBoxConfig(itemCodeMaxLengthConfig);

		componentList.add(itemCodeConfig);

		GtnUIFrameworkValidationConfig itemCodeValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Item Code can only be Alphanumeric", GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);

		itemCodeConfig.setGtnUIFrameworkValidationConfig(itemCodeValidationConfig);
	}

	private void addPackageSize(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig packageSizeLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPackageSizelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(packageSizeLayout);

		GtnUIFrameworkComponentConfig packageSize = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPackageSize", true, packageSizeLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		packageSize.setAuthorizationIncluded(true);
		packageSize.setComponentName("Package Size");

		componentList.add(packageSize);

		GtnUIFrameworkComboBoxConfig packageSizeConfig = componentConfig.getComboBoxConfig("PACKAGE_SIZE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		packageSize.setGtnComboboxConfig(packageSizeConfig);

	}

	private void addPackageSizeCode(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig packageSizeCodeLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPackageSizeCodelayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(packageSizeCodeLayout);

		GtnUIFrameworkComponentConfig packageSizeCode = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPackageSizeCode", true, packageSizeCodeLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		packageSizeCode.setAuthorizationIncluded(true);
		packageSizeCode.setComponentName("Package Size Code");
		componentList.add(packageSizeCode);

	}

	private void addItemTypeIndication(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemTypeIndicationLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemTypeIndicationlayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemTypeIndicationLayout);

		GtnUIFrameworkComponentConfig itemTypeIndication = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemTypeIndication", true, itemTypeIndicationLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemTypeIndication.setAuthorizationIncluded(true);
		itemTypeIndication.setComponentName("Item Type Indication");
		componentList.add(itemTypeIndication);

		GtnUIFrameworkComboBoxConfig itemTypeIndicationConfig = componentConfig
				.getComboBoxConfig("ITEM_TYPE_INDICATION", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemTypeIndication.setGtnComboboxConfig(itemTypeIndicationConfig);

	}

	private void addItemCategory(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemCategoryLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabItemCategorylayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(itemCategoryLayout);

		GtnUIFrameworkComponentConfig itemCategory = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabItemCategory", true, itemCategoryLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemCategory.setAuthorizationIncluded(true);
		itemCategory.setComponentName("Item Category");
		componentList.add(itemCategory);

		GtnUIFrameworkComboBoxConfig itemCategoryConfig = componentConfig.getComboBoxConfig("ITEM_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemCategory.setGtnComboboxConfig(itemCategoryConfig);

	}

	private void addUPPS(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig uppsLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUPPSlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(uppsLayout);

		GtnUIFrameworkComponentConfig upps = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUPPS",
				true, uppsLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		upps.setAuthorizationIncluded(true);
		upps.setComponentName("UPPS");
		componentList.add(upps);
	}

	private void addPackageSizeIntroduction(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig packageSizeIntroductionLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPackageSizeIntroductionlayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(packageSizeIntroductionLayout);

		GtnUIFrameworkComponentConfig packageSizeIntroduction = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPackageSizeIntroduction", true, packageSizeIntroductionLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		packageSizeIntroduction.setAuthorizationIncluded(true);
		packageSizeIntroduction.setComponentName("Package Size Introduction Date");

		GtnUIFrameworkDateFieldConfig packageSizeIntroductionConfig = new GtnUIFrameworkDateFieldConfig();
		packageSizeIntroductionConfig.setImmediate(true);
		packageSizeIntroduction.setGtnDateFieldConfig(packageSizeIntroductionConfig);

		GtnUIFrameworkValidationConfig packageSizeIntroductionValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		packageSizeIntroduction.setGtnUIFrameworkValidationConfig(packageSizeIntroductionValidationConfig);

		componentList.add(packageSizeIntroduction);

	}

	private void addManufacturerID(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig manufacturerIDLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabManufacturerIDlayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(manufacturerIDLayout);

		GtnUIFrameworkComponentConfig manufacturerID = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabManufacturerID", true, manufacturerIDLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		manufacturerID.setAuthorizationIncluded(true);
		manufacturerID.setComponentName("Manufacturer ID");
		componentList.add(manufacturerID);

		GtnUIFrameworkComboBoxConfig manufacturerIDConfig = componentConfig.getComboBoxConfig("CompanyManufacture",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		manufacturerID.setGtnComboboxConfig(manufacturerIDConfig);

		List<GtnUIFrameWorkActionConfig> manufacturerIDActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig manufacturerIDCustomAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		manufacturerIDCustomAction
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_MANUFACURE_ID_VALUECHANGE_ACTION);
		manufacturerIDActionConfigList.add(manufacturerIDCustomAction);
		manufacturerID.setGtnUIFrameWorkActionConfigList(manufacturerIDActionConfigList);

	}

	private void addUDC1(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig udc1Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUDC1layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(udc1Layout);

		GtnUIFrameworkComponentConfig udc1 = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUDC1",
				true, udc1Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		udc1.setComponentName("UDC 1");
		udc1.setAuthorizationIncluded(true);
		componentList.add(udc1);

		GtnUIFrameworkComboBoxConfig itemGroupConfig = componentConfig.getComboBoxConfig("ITEM_UDC1",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		udc1.setGtnComboboxConfig(itemGroupConfig);

	}

	private void addUDC2(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig udc2Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUDC2layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(udc2Layout);

		GtnUIFrameworkComponentConfig udc2 = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUDC2",
				true, udc2Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		udc2.setComponentName("UDC 2");
		udc2.setAuthorizationIncluded(true);
		componentList.add(udc2);

		GtnUIFrameworkComboBoxConfig udc2Config = componentConfig.getComboBoxConfig("ITEM_UDC2",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		udc2.setGtnComboboxConfig(udc2Config);

	}

	private void addUDC3(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig udc3Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUDC3layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(udc3Layout);

		GtnUIFrameworkComponentConfig udc3 = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUDC3",
				true, udc3Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		udc3.setComponentName("UDC 3");
		udc3.setAuthorizationIncluded(true);
		componentList.add(udc3);

		GtnUIFrameworkComboBoxConfig udc3Config = componentConfig.getComboBoxConfig("ITEM_UDC3",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		udc3.setGtnComboboxConfig(udc3Config);

	}

	private void addUDC4(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig udc4Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUDC4layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(udc4Layout);

		GtnUIFrameworkComponentConfig udc4 = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUDC4",
				true, udc4Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		udc4.setComponentName("UDC 4");
		udc4.setAuthorizationIncluded(true);
		componentList.add(udc4);

		GtnUIFrameworkComboBoxConfig udc4Config = componentConfig.getComboBoxConfig("ITEM_UDC4",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		udc4.setGtnComboboxConfig(udc4Config);

	}

	private void addUDC5(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig udc5Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUDC5layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(udc5Layout);

		GtnUIFrameworkComponentConfig udc5 = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUDC5",
				true, udc5Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		udc5.setComponentName("UDC 5");
		udc5.setAuthorizationIncluded(true);
		componentList.add(udc5);

		GtnUIFrameworkComboBoxConfig udc5Config = componentConfig.getComboBoxConfig("ITEM_UDC5",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		udc5.setGtnComboboxConfig(udc5Config);

	}

	private void addUDC6(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig udc6Layout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabUDC6layout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(udc6Layout);

		GtnUIFrameworkComponentConfig udc6 = componentConfig.getUIFrameworkComponentConfig("itemInformationTabUDC6",
				true, udc6Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		udc6.setComponentName("UDC 6");
		udc6.setAuthorizationIncluded(true);
		componentList.add(udc6);

		GtnUIFrameworkComboBoxConfig udc6Config = componentConfig.getComboBoxConfig("ITEM_UDC6",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		udc6.setGtnComboboxConfig(udc6Config);

	}

	private void addManufacturerName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig manufacturerNameLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabManufacturerNamelayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(manufacturerNameLayout);

		GtnUIFrameworkComponentConfig manufacturerName = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabManufacturerName", true, manufacturerNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		manufacturerName.setAuthorizationIncluded(true);
		manufacturerName.setComponentName("Manufacturer Name");

		GtnUIFrameworkTextBoxConfig manufacturerNameConfig = new GtnUIFrameworkTextBoxConfig();
		manufacturerNameConfig.setEnable(false);
		manufacturerName.setGtnTextBoxConfig(manufacturerNameConfig);

		componentList.add(manufacturerName);
	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig createdByLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabCreatedBylayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(createdByLayout);

		GtnUIFrameworkComponentConfig createdBy = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabCreatedBy", true, createdByLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		createdBy.setAuthorizationIncluded(true);
		createdBy.setComponentName("Created By");

		GtnUIFrameworkTextBoxConfig createdByConfig = new GtnUIFrameworkTextBoxConfig();
		createdByConfig.setEnable(false);
		createdBy.setGtnTextBoxConfig(createdByConfig);

		componentList.add(createdBy);
	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig createdDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationCreatedDatelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(createdDateLayout);

		GtnUIFrameworkComponentConfig createdDate = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationCreatedDate", true, createdDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		createdDate.setAuthorizationIncluded(true);
		createdDate.setComponentName("Created Date");
		createdDate.setEnable(false);
		componentList.add(createdDate);

		GtnUIFrameworkDateFieldConfig createdDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		createdDateFieldConfig.setImmediate(true);
		createdDate.setGtnDateFieldConfig(createdDateFieldConfig);

		GtnUIFrameworkValidationConfig createdDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		createdDate.setGtnUIFrameworkValidationConfig(createdDateValidationConfig);
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig modifiedByLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabModifiedBylayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(modifiedByLayout);

		GtnUIFrameworkComponentConfig modifiedBy = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabModifiedBy", true, modifiedByLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		modifiedBy.setAuthorizationIncluded(true);
		modifiedBy.setComponentName("Modified By");

		GtnUIFrameworkTextBoxConfig modifiedByConfig = new GtnUIFrameworkTextBoxConfig();
		modifiedByConfig.setEnable(false);
		modifiedBy.setGtnTextBoxConfig(modifiedByConfig);

		componentList.add(modifiedBy);
	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig modifiedDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationModifiedDatelayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(modifiedDateLayout);

		GtnUIFrameworkComponentConfig modifiedDate = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationModifiedDate", true, modifiedDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		modifiedDate.setAuthorizationIncluded(true);
		modifiedDate.setComponentName("Modified Date");
		modifiedDate.setEnable(false);
		componentList.add(modifiedDate);

		GtnUIFrameworkDateFieldConfig modifiedDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		modifiedDateFieldConfig.setImmediate(true);
		modifiedDate.setGtnDateFieldConfig(modifiedDateFieldConfig);

		GtnUIFrameworkValidationConfig modifiedDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		modifiedDate.setGtnUIFrameworkValidationConfig(modifiedDateValidationConfig);
	}

	private void addSystemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig systemIdLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabSystemIdlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(systemIdLayout);

		GtnUIFrameworkComponentConfig systemId = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabSystemId", true, systemIdLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		systemId.setAuthorizationIncluded(true);
		systemId.setComponentName("System ID");

		GtnUIFrameworkTextBoxConfig systemIdConfig = new GtnUIFrameworkTextBoxConfig();
		systemIdConfig.setEnable(false);
		systemId.setGtnTextBoxConfig(systemIdConfig);

		componentList.add(systemId);
	}

	private void addBatchID(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig batchIDLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabBatchIDlayout", true, GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(batchIDLayout);

		GtnUIFrameworkComponentConfig batchID = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabBatchID", true, batchIDLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		batchID.setAuthorizationIncluded(true);
		batchID.setComponentName("Batch ID");

		GtnUIFrameworkTextBoxConfig batchIDConfig = new GtnUIFrameworkTextBoxConfig();
		batchIDConfig.setEnable(false);
		batchID.setGtnTextBoxConfig(batchIDConfig);

		componentList.add(batchID);
	}

	private void addOrganizationKey(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig organizationKeyLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabOrganizationKeylayout", true,
				GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB_LAYOUT);
		componentList.add(organizationKeyLayout);

		GtnUIFrameworkComponentConfig organizationKey = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabOrganizationKey", true, organizationKeyLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		organizationKey.setAuthorizationIncluded(true);
		organizationKey.setComponentName("Organization Key");
		organizationKey.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(organizationKey);

		GtnUIFrameworkComboBoxConfig organizationKeyConfig = componentConfig.getComboBoxConfig("CompanyOrganizationKey",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		organizationKey.setGtnComboboxConfig(organizationKeyConfig);

		GtnUIFrameworkValidationConfig organizationKeyValidationConfig = new GtnUIFrameworkValidationConfig();
		organizationKeyValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		organizationKey.setGtnUIFrameworkValidationConfig(organizationKeyValidationConfig);
	}

}
