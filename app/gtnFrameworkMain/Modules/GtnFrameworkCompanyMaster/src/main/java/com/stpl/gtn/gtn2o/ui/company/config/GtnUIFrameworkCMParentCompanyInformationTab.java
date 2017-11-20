package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterAttachAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCompanyMasterTradeClassParentTabValidation;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;

public class GtnUIFrameworkCMParentCompanyInformationTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addParentCompanyTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_TAB, true, "companyTabsheetPanel");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setTabComponent(true);
		componentList.add(layoutConfig);
		parentCompanyInformationPanel(componentList);
		parentCompanyResultPanel(componentList);
		parentCompanyInformationRemoveButtonLayout(componentList);
		addExcelButtonComponent(componentList);

	}

	private void parentCompanyInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("parentCompanyInformationPanel", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_TAB);
		panel.setComponentName("Parent Company Information");
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_PANEL_LAYOUT, true,
				"parentCompanyInformationPanel");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAuthorizationIncluded(true);
		componentList.add(layoutConfig);

		parentCompanyInformationLayout(componentList);
		parentCompanyInformationAttachButtonLayout(componentList);
	}

	private void parentCompanyInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_LAYOUT, true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_PANEL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		parentCompanyInformationFields(componentList);
	}

	private void parentCompanyInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addParentCompanyNo(componentList);
		addParentCompanyName(componentList);
		addParentCompanyStartDate(componentList);
		addParentCompanyEndDate(componentList);
		addParentCompanyMasterSid(componentList);
	}

	private void addParentCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentCompanyNolayout",
				true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentCompanyNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO, true, "parentCompanyNolayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentCompanyNo.setComponentName(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO_DISPLAY_NAME);

		componentList.add(parentCompanyNo);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("parentCompanyView");
		popupActionParam.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO_DISPLAY_NAME);
		popupActionParam.add("70%");
		popupActionParam.add("70%");

		popupActionConfig.setActionParameterList(popupActionParam);
		GtnUIFrameWorkActionConfig selectActionAconfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectActionAconfig
				.addActionParameter(GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction.class.getName());
		selectActionAconfig.addActionParameter(GtnFrameworkCompanyStringContants.FROM_MAIN_POPUP);
		actionConfigList.add(selectActionAconfig);
		parentCompanyNo.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addParentCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentCompanyNamelayout",
				true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_NAME, true, "parentCompanyNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyQualifierName.setComponentName("Parent Company Name");
		companyQualifierName.setEnable(false);
		companyQualifierName.setAddToParent(true);

		componentList.add(companyQualifierName);

	}

	private void addParentCompanyStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanyStartDatelayout", true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentCompanyStartDate = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE, true, "parentCompanyStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		parentCompanyStartDate.setComponentName("Parent Company Start Date");

		componentList.add(parentCompanyStartDate);
	}

	private void addParentCompanyEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentCompanyEndDatelayout",
				true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentCompanyEndDate = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_END_DATE, true, "parentCompanyEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		parentCompanyEndDate.setComponentName("Parent Company End Date");

		componentList.add(parentCompanyEndDate);

	}

	private void parentCompanyInformationAttachButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"parentCompanyAttachButtonLayout", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_PANEL_LAYOUT);

		componentList.add(layoutConfig);
		parentCompanyInformationAttachButton(componentList);
	}

	private void parentCompanyInformationAttachButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentCompanyInformationAttachButtonConfig = configProvider
				.getUIFrameworkComponentConfig("parentCompanyAttachButton", true, "parentCompanyAttachButtonLayout",
						GtnUIFrameworkComponentType.BUTTON);
		parentCompanyInformationAttachButtonConfig.setComponentName("Attach");

		componentList.add(parentCompanyInformationAttachButtonConfig);

		List<GtnUIFrameWorkActionConfig> parentCompanyInformationAttachActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		validAction.addActionParameter(GtnFrameworkCompanyMasterTradeClassParentTabValidation.class.getName());
		parentCompanyInformationAttachActionConfigList.add(validAction);

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnUIFrameworkCompanyMasterAttachAction.class.getName());
		actionParameter.add(GtnFrameworkCompanyStringContants.PARENT_ATTACH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO);
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_NAME,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_END_DATE));
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_NAME,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_END_DATE));
		actionParameter.add(Arrays.asList(String.class, String.class, Date.class, Date.class));
		actionParameter.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_SID);
		actionParameter.add("ParentCompanyAttach");
		selectAction.setActionParameterList(actionParameter);
		parentCompanyInformationAttachActionConfigList.add(selectAction);
		parentCompanyInformationAttachButtonConfig
				.setGtnUIFrameWorkActionConfigList(parentCompanyInformationAttachActionConfigList);
	}

	private void parentCompanyResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("parentCompanyResultPanel", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_TAB);
		panel.setComponentName("Results");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		parentCompanyResultLayout(componentList);
	}

	private void parentCompanyResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig("parentCompanyResultLayout", true, "parentCompanyResultPanel");
		layoutConfig.setComponentWidth("100%");
		componentList.add(layoutConfig);

		parentCompanyResultDataTable(componentList);
	}

	private void parentCompanyResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentCompanyInformationAttachResultConfig = configProvider
				.getUIFrameworkComponentConfig(GtnFrameworkCompanyStringContants.PARENT_ATTACH_RESULT_TABLE, true,
						"parentCompanyResultLayout", GtnUIFrameworkComponentType.DATATABLE);
		parentCompanyInformationAttachResultConfig.setComponentName("Results");
		parentCompanyInformationAttachResultConfig.setComponentHight("300px");
		parentCompanyInformationAttachResultConfig.setComponentWidth("100%");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		parentCompanyInformationAttachResultConfig.setComponentStyle(tableStyle);

		componentList.add(parentCompanyInformationAttachResultConfig);

		GtnUIFrameworkPagedTableConfig parentCompanyInformationAttachResults = new GtnUIFrameworkPagedTableConfig();
		parentCompanyInformationAttachResultConfig.setGtnPagedTableConfig(parentCompanyInformationAttachResults);
		parentCompanyInformationAttachResults.setEditable(true);
		parentCompanyInformationAttachResults.setFilterBar(true);
		parentCompanyInformationAttachResults.setSelectable(true);
		parentCompanyInformationAttachResults.setTableColumnDataType(new Class[] { String.class, String.class,
				Date.class, Date.class, String.class, Date.class, String.class, Date.class });
		parentCompanyInformationAttachResults
				.setTableVisibleHeader(new String[] { GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO_DISPLAY_NAME,
						"Parent Company Name", "Parent Company Start Date", "Parent Company End Date", "Created By",
						"Created Date", "Modified By", "Modified Date" });
		parentCompanyInformationAttachResults
				.setTableColumnMappingId(new Object[] { GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_NAME,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_END_DATE, "createdBy", "createdDate",
						"modifiedBy", "modifiedDate" });

		parentCompanyInformationAttachResults
				.setEditableColumnList(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_NAME,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_START_DATE,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_END_DATE, "createdBy", "createdDate",
						"modifiedBy", "modifiedDate"));
		parentCompanyInformationAttachResults
				.setExtraColumn(new Object[] { GtnFrameworkCompanyStringContants.PARENT_COMPANY_SID });
		parentCompanyInformationAttachResults.setEditableField(
				createTableFieldFactoryComponents(parentCompanyInformationAttachResults.getEditableColumnList()));

	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			if (GtnFrameworkCompanyStringContants.getCmParentTextfieldPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
			} else if (GtnFrameworkCompanyStringContants.getCmParentDatefieldPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
			} else if (GtnFrameworkCompanyStringContants.getCmParentLookupPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.POPUPTEXTFIELD;
			}

			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			boolean isReadOnly = !GtnFrameworkCompanyStringContants.getCmParentEditablefieldList().contains(propertyId);
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.POPUPTEXTFIELD)) {

				GtnUIFrameWorkActionConfig popupActionConfig = configProvider
						.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
				List<Object> popupActionParam = new ArrayList<>();
				popupActionParam.add("parentCompanyView");
				popupActionParam.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO_DISPLAY_NAME);
				popupActionParam.add("70%");
				popupActionParam.add("70%");
				popupActionConfig.setActionParameterList(popupActionParam);
				fieldConfig.addComponentStyle("searchicon");
				fieldConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
				GtnUIFrameWorkActionConfig selectSction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.CUSTOM_ACTION);
				selectSction
						.addActionParameter(GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction.class.getName());
				selectSction.addActionParameter(GtnFrameworkCompanyStringContants.FROM_FIELD_POPUP);
				fieldConfig.addGtnUIFrameWorkActionConfig(selectSction);
			} else if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.TEXTBOX)) {
				GtnUIFrameworkTextBoxConfig textConfigPCInformationTab = new GtnUIFrameworkTextBoxConfig();
				textConfigPCInformationTab.setReadOnly(isReadOnly);
				fieldConfig.setGtnTextBoxConfig(textConfigPCInformationTab);
			}

			else if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.DATEFIELD)) {
				fieldConfig.setEnable(!isReadOnly);
			}

			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	private void parentCompanyInformationRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"parentCompanyRemoveButtonLayout", true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_TAB);

		componentList.add(layoutConfig);
		parentCompanyInformationRemoveButton(componentList);
	}

	private void parentCompanyInformationRemoveButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanyRemoveButton", true, "parentCompanyRemoveButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Remove");
		attachButtonConfig.setAuthorizationIncluded(true);

		componentList.add(attachButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object removeValidationCustom = GtnFrameworkCompanyClassContants.IDENTIFIER_REMOVE_VALIDATION_CUSTOM;
		alertActionConfig.setActionParameterList(
				Arrays.asList(removeValidationCustom, GtnFrameworkCompanyStringContants.PARENT_ATTACH_RESULT_TABLE,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_PARENT_REMOVE,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_PARENT_REMOVE));
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_MSG_PARENT_REMOVE_CONFIRMATION);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.REMOVE_ACTION);

		selectAction.addActionParameter(GtnFrameworkCompanyStringContants.PARENT_ATTACH_RESULT_TABLE);
		onSucessActionConfigList.add(selectAction);
		actionConfigList.add(confirmationActionConfig);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_TAB + "excelButton", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_TAB, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		componentList.add(excelButtonConfig);
		excelButtonConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"ParentCompany", true, GtnFrameworkCompanyStringContants.PARENT_ATTACH_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setTitleNeeded(true);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private void addParentCompanyMasterSid(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentCompanySidlayout",
				true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentCompanySid = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SID, true, "parentCompanySidlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCompanySid.setComponentName("Sid");
		parentCompanySid.setVisible(false);

		componentList.add(parentCompanySid);
	}
}
