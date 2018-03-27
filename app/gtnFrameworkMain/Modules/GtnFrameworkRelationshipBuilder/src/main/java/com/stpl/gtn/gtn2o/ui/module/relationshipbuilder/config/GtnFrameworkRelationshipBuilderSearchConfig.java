/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkSearchSecurityAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkAddButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkConfirmedCopyAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkConfirmedDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkEditButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.GtnFrameworkRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRelationshipBuilderSearchConfig {
	private final GtnFrameworkComponentConfigProvider gtnConfigFactory = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = gtnConfigFactory.getViewConfig(
				GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_SEARCH,
				GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_SEARCH, true);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRBSearchMainPanel(componentList, namspacePrefix);

	}

	private void addRBSearchMainPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig panel = gtnConfigFactory.getPanelConfig(namspacePrefix + "mainPanel", false,
				null);
		componentList.add(panel);
		addRBSearchMainLayout(componentList, namspacePrefix);
	}

	private void addRBSearchMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getVerticalLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT, true, namspacePrefix + "mainPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addSearchCriteriaPanel(componentList, namspacePrefix);
		addButtonLayout(componentList, namspacePrefix);
		addResultPanel(componentList, namspacePrefix);
		addActionButtonLayout(componentList, namspacePrefix);
	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig panel = gtnConfigFactory.getPanelConfig(namspacePrefix + "searchCriteriaPanel",
				true, namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
		panel.setComponentName("Search Criteria");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		addFieldLayout(componentList, namspacePrefix);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panelConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "resultPanel", true, namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT,
				GtnUIFrameworkComponentType.PANEL);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panelConfig);
		addResultLayout(componentList, namspacePrefix);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT, true, namspacePrefix + "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList, namspacePrefix);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true,
				namspacePrefix + "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, namspacePrefix);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RELATIONSHIP_SEARCH_BUTTONLAYOUT, true,
				namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList, namspacePrefix);
		addAuditSearchButtonComponent(componentList, namspacePrefix);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		addRelationshipName(componentList, namspacePrefix);
		addHierarchyName(componentList, namspacePrefix);
		addRelationshipDescription(componentList, namspacePrefix);
		addStartDateFrom(componentList, namspacePrefix);
		addStartDateTo(componentList, namspacePrefix);
		addRelationshipType(componentList, namspacePrefix);
		addCreationDateFrom(componentList, namspacePrefix);
		addCreationDateTo(componentList, namspacePrefix);
		addAuditSearchParam(componentList, namspacePrefix);
	}

	private void addRelationshipName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "relationshipNameLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_NAME, true,
				namspacePrefix + "relationshipNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Relationship Name");

		companyIdConfig.setAddToParent(true);

		componentList.add(companyIdConfig);
	}

	private void addHierarchyName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "hierarchyNameLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME, true,
				namspacePrefix + "hierarchyNameLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Hierarchy Name");

		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRelationshipDescription(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "relationshipDescriptionLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_DESCRIPTION, true,
				namspacePrefix + "relationshipDescriptionLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Relationship Description");

		companyNoConfig.setAddToParent(true);

		componentList.add(companyNoConfig);
	}

	private void addStartDateFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "startDateFromLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyEndDate = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.START_DATE_FROM, true,
				namspacePrefix + "startDateFromLayout", GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyEndDate.setAuthorizationIncluded(true);
		companyInformationCompanyEndDate.setComponentName("Start Date From");

		companyInformationCompanyEndDate.setAddToParent(true);

		componentList.add(companyInformationCompanyEndDate);
	}

	private void addStartDateTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "startDateToLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyEndDate = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.START_DATE_TO, true, namspacePrefix + "startDateToLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyEndDate.setAuthorizationIncluded(true);
		companyInformationCompanyEndDate.setComponentName("Start Date To");

		companyInformationCompanyEndDate.setAddToParent(true);

		componentList.add(companyInformationCompanyEndDate);
	}

	private void addRelationshipType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "RelationshipTypeLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_TYPE, true,
				namspacePrefix + "RelationshipTypeLayout", GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setAuthorizationIncluded(true);
		addHierarchyTypeOptionGroup.setComponentName("Relationship Type");

		GtnUIFrameworkOptionGroupConfig relationshipTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		relationshipTypeConfig.setValuesFromService(false);
		relationshipTypeConfig.setItemValues(Arrays.asList("Primary", "Secondary"));

		addHierarchyTypeOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(relationshipTypeConfig);

		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addCreationDateFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "creationDateFromLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyEndDate = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_FROM, true,
				namspacePrefix + "creationDateFromLayout", GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyEndDate.setAuthorizationIncluded(true);
		companyInformationCompanyEndDate.setComponentName("Creation Date From");

		componentList.add(companyInformationCompanyEndDate);
	}

	private void addCreationDateTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "creationDateToLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyEndDate = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_TO, true,
				namspacePrefix + "creationDateToLayout", GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyEndDate.setAuthorizationIncluded(true);
		companyInformationCompanyEndDate.setComponentName("Creation Date To");

		companyInformationCompanyEndDate.setAddToParent(true);

		componentList.add(companyInformationCompanyEndDate);
	}

	private void addAuditSearchParam(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig auditSearchParam = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "auditSearchParam", true,
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		auditSearchParam.setComponentValue("HIST_");
		auditSearchParam.setVisible(false);
		componentList.add(auditSearchParam);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnSearch01Layout", true,
				GtnFrameworkCommonConstants.RELATIONSHIP_SEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch01", true, namspacePrefix + "gtnSearch01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig searchSecurityAction = new GtnUIFrameWorkActionConfig();
		searchSecurityAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchSecurityAction.addActionParameter(GtnFrameworkSearchSecurityAction.class.getName());
		searchSecurityAction.addActionParameter(true);
		searchSecurityAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.GTN_DELETE_BUTTON);
		searchSecurityAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.GTN_EDIT_BUTTON);
		searchButtonConfig.addGtnUIFrameWorkActionConfig(searchSecurityAction);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		List<String> fieldValues = Arrays
				.asList(new String[] { namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_NAME,
						namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME,
						namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_DESCRIPTION,
						namspacePrefix + GtnFrameworkCommonConstants.START_DATE_FROM,
						namspacePrefix + GtnFrameworkCommonConstants.START_DATE_TO,
						namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_TYPE,
						namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_FROM,
						namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_TO });
		loadDataTableActionConfig.setFieldValues(fieldValues);
		searchButtonConfig.addGtnUIFrameWorkActionConfig(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		searchButtonConfig.addGtnUIFrameWorkActionConfig(notificationActionConfig);

	}

	private void addAuditSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnAuditSearch01Layout", true,
				GtnFrameworkCommonConstants.RELATIONSHIP_SEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnAuditSearch", true, namspacePrefix + "gtnAuditSearch01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Audit Search");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig searchSecurityAction = new GtnUIFrameWorkActionConfig();
		searchSecurityAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchSecurityAction.addActionParameter(GtnFrameworkSearchSecurityAction.class.getName());
		searchSecurityAction.addActionParameter(false);
		searchSecurityAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.GTN_DELETE_BUTTON);
		searchSecurityAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.GTN_EDIT_BUTTON);
        searchSecurityAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.GTN_COPY_BUTTON);
		searchButtonConfig.addGtnUIFrameWorkActionConfig(searchSecurityAction);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		List<String> fieldValues = Arrays.asList(new String[] {
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_NAME,
				namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME,
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_DESCRIPTION,
				namspacePrefix + GtnFrameworkCommonConstants.START_DATE_FROM,
				namspacePrefix + GtnFrameworkCommonConstants.START_DATE_TO,
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_TYPE,
				namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_FROM,
				namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_TO, namspacePrefix + "auditSearchParam" });
		loadDataTableActionConfig.setFieldValues(fieldValues);
		searchButtonConfig.addGtnUIFrameWorkActionConfig(loadDataTableActionConfig);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig searchResultConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE, true,
				namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentName("Results");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig rbSearchResults = new GtnUIFrameworkPagedTableConfig();
		rbSearchResults.setEditable(false);
		rbSearchResults.setFilterBar(true);
		rbSearchResults.setSelectable(true);
		rbSearchResults.setDefaultDateFormat(null);
		rbSearchResults.setSinkItemPerPageWithPageLength(false);
		rbSearchResults.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class, String.class,
				Integer.class, Date.class, Date.class, Date.class, String.class });
		rbSearchResults.setTableVisibleHeader(GtnFrameworkRelationshipBuilderConstants.getRelationshipBuilderHeader());
		rbSearchResults
				.setTableColumnMappingId(GtnFrameworkRelationshipBuilderConstants.getRelationshipBuilderColumn());
		rbSearchResults.setCountUrl(GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
				+ GtnWsRelationshipBuilderConstants.GET_RELATIONSHIP_BUILDER_TABLE_DATA);
		rbSearchResults.setResultSetUrl(GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
				+ GtnWsRelationshipBuilderConstants.GET_RELATIONSHIP_BUILDER_TABLE_DATA);
		searchResultConfig.setGtnPagedTableConfig(rbSearchResults);

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, true,
				namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addResetButtonComponent(componentList, namspacePrefix);
		addAddButtonComponent(componentList, namspacePrefix);
		addEditButtonComponent(componentList, namspacePrefix);
		addViewButtonComponent(componentList, namspacePrefix);
		addCopyButtonComponent(componentList, namspacePrefix);
		addDeleteButtonComponent(componentList, namspacePrefix);
		addExcelButtonComponent(componentList, namspacePrefix);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnReset01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig resetButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnResetButton", true, namspacePrefix + "gtnReset01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		List<String> resetFieldList = new ArrayList<>();

		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_DESCRIPTION);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.START_DATE_FROM);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.START_DATE_TO);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_FROM);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.CREATION_DATE_TO);
		resetFieldList.add(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		List<Object> resetValueList = new ArrayList<>();
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(null);
		resetValueList.add("Primary");
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		resetActionConfig.addActionParameter("Are you sure you want to reset the page to default/previous values ?");
		resetActionConfig.addActionParameter(resetFieldList);
		resetActionConfig.addActionParameter(resetValueList);
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnAdd01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnAddButton", true, namspacePrefix + "gtnAdd01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("ADD");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnUIFrameworkAddButtonAction.class.getName());
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.VERSION_NO);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_RELATIONSHIP);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_ID);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.TOTAL_LEVELS);
		addActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD
				+ GtnFrameworkCommonStringConstants.UNDERSCORE);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESULTLAYOUT);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.BUILD_TYPE);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_DESC);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.START_DATE);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.SAVE_BUTTON);
		addActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESET_BUTTON);
		addActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD);
		actionConfigList.add(addActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnEdit01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.GTN_EDIT_BUTTON, true, namspacePrefix + "gtnEdit01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("EDIT");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnUIFrameworkEditButtonAction.class.getName());
		editActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(9);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.VERSION_NO);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_RELATIONSHIP);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_ID);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.TOTAL_LEVELS);
		editActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD
				+ GtnFrameworkCommonStringConstants.UNDERSCORE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESULTLAYOUT);
		editActionConfig.addActionParameter("rbTree");
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.BUILD_TYPE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_DESC);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.START_DATE);
		editActionConfig.addActionParameter("resultTable");
		editActionConfig.addActionParameter("value");
		editActionConfig.addActionParameter("Edit Error");
		editActionConfig.addActionParameter("Please select a record to edit");
		editActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD);
		editActionConfig.addActionParameter(false);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.SAVE_BUTTON);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESET_BUTTON);
		editActionConfig.addActionParameter("removeFromTreeBtn");
		actionConfigList.add(editActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnView01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnViewButton", true, namspacePrefix + "gtnView01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("VIEW");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig viewActionConfig = new GtnUIFrameWorkActionConfig();
		viewActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewActionConfig.addActionParameter(GtnUIFrameworkEditButtonAction.class.getName());
		viewActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		viewActionConfig.addActionParameter(9);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.VERSION_NO);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_RELATIONSHIP);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_ID);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.TOTAL_LEVELS);
		viewActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD
				+ GtnFrameworkCommonStringConstants.UNDERSCORE);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESULTLAYOUT);
		viewActionConfig.addActionParameter("rbTree");
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.BUILD_TYPE);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_DESC);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.START_DATE);
		viewActionConfig.addActionParameter("resultTable");
		viewActionConfig.addActionParameter("value");
		viewActionConfig.addActionParameter("View Error");
		viewActionConfig.addActionParameter("Please select a record to view");
		viewActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD);
		viewActionConfig.addActionParameter(true);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.SAVE_BUTTON);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESET_BUTTON);
		viewActionConfig.addActionParameter("removeFromTreeBtn");
		actionConfigList.add(viewActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnCopyBtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnCopy01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnCopyBtnLayout);

		GtnUIFrameworkComponentConfig copyButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.GTN_COPY_BUTTON, true, namspacePrefix + "gtnCopy01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		copyButtonConfig.setComponentName("COPY");
		copyButtonConfig.setAuthorizationIncluded(true);
		componentList.add(copyButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig copyActionConfig = new GtnUIFrameWorkActionConfig();
		copyActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyActionConfig.addActionParameter(GtnUIFrameworkConfirmedCopyAction.class.getName());
		copyActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		copyActionConfig.addActionParameter(9);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.VERSION_NO);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_RELATIONSHIP);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.SELECTED_ID);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.TOTAL_LEVELS);
		copyActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD
				+ GtnFrameworkCommonStringConstants.UNDERSCORE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESULTLAYOUT);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RB_TREE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.BUILD_TYPE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_DESC);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.START_DATE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESULT_TABLE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_VALUE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		copyActionConfig.addActionParameter("Are you sure you want to copy record ");
		copyActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD);
		copyActionConfig.addActionParameter(Boolean.TRUE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.SAVE_BUTTON);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESET_BUTTON);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.REMOVE_FROM_TREE_BTN);
		actionConfigList.add(copyActionConfig);
		copyButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnDelete01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.GTN_DELETE_BUTTON, true,
				namspacePrefix + "gtnDelete01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("DELETE");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnUIFrameworkConfirmedDeleteButtonAction.class.getName());
		deleteActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		deleteActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		deleteActionConfig.addActionParameter("No Record has been selected.  Please select a Record and try again.");
		deleteActionConfig.addActionParameter("Are you sure you want to delete record ");
		actionConfigList.add(deleteActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "gtnExcel01Layout", true,
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnExcelButton", true, namspacePrefix + "gtnExcel01Layout",
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		componentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = gtnConfigFactory.getExcelBtnconfig(
				"Relationship Builder", true, namspacePrefix + GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE, false);
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}
}
