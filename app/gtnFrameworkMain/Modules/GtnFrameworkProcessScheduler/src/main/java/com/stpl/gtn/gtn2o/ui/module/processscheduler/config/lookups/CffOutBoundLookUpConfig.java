package com.stpl.gtn.gtn2o.ui.module.processscheduler.config.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;

public class CffOutBoundLookUpConfig {
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(CffOutBoundLookUpConfig.class);
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	public GtnUIFrameworkViewConfig getCffOutBoundLookUpView() {
		gtnLogger.info("enetred in CffOutBoundLookUp config");
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("CffOutBound Search View");
		view.setViewId("V002");
		view.setDefaultView(false);  
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		gtnLogger.info("enetred in CffOutBoundLookUp config component list");
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCffOutBoundRootLayout(componentList);
		
	}
	
	private void addCffOutBoundRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		gtnLogger.info("Started the execution of addCffOutBoundRootLayout()");
		GtnUIFrameworkComponentConfig cffOutBoundRootLayout = new GtnUIFrameworkComponentConfig();
		cffOutBoundRootLayout.setComponentId("CffOutBound_rootLayout");
		cffOutBoundRootLayout.setComponentWidth("100%");
		cffOutBoundRootLayout.setAddToParent(false);
		cffOutBoundRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cffOutBoundRootLayout.setSpacing(true);
		cffOutBoundRootLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		cffOutBoundRootLayout.setGtnLayoutConfig(layout);
		componentList.add(cffOutBoundRootLayout);
		
		addCffOutBoundRootPanel(componentList,cffOutBoundRootLayout.getComponentId());		
	}
	
	private void addCffOutBoundRootPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffOutBoundRootPanel()");
		GtnUIFrameworkComponentConfig cffOutBoundRootRootPanel = new GtnUIFrameworkComponentConfig();
		cffOutBoundRootRootPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		cffOutBoundRootRootPanel.setComponentId("CffOutBound_rootPanel");
		cffOutBoundRootRootPanel.setAddToParent(true);
		//cffOutBoundRootRootPanel.setComponentName("Root PAnnel");
		cffOutBoundRootRootPanel.setParentComponentId(parentComponentId);
		cffOutBoundRootRootPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cffOutBoundRootRootPanel);
		
		addCffOutBoundMainLayout(componentList,cffOutBoundRootRootPanel.getComponentId());
	}

	private void addCffOutBoundMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffOutBoundMainLayout()");
		GtnUIFrameworkComponentConfig cffOutBoundMainLayout = new GtnUIFrameworkComponentConfig();
		cffOutBoundMainLayout.setComponentId("CffOutBound_mainLayout");
		cffOutBoundMainLayout.setComponentWidth("100%");
		cffOutBoundMainLayout.setAddToParent(true);
		cffOutBoundMainLayout.setParentComponentId(parentComponentId);
		cffOutBoundMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cffOutBoundMainLayout.setSpacing(true);
		cffOutBoundMainLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		cffOutBoundMainLayout.setGtnLayoutConfig(layout);
		componentList.add(cffOutBoundMainLayout);
		
		addCffOutBoundSearchPanel(componentList,cffOutBoundMainLayout.getComponentId());
		addCffOutBoundResultsPanel(componentList,cffOutBoundMainLayout.getComponentId());
		addGenerateOutBoundButtonLayout(componentList,cffOutBoundMainLayout.getComponentId());
	}

	private void addCffOutBoundSearchPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffOutBoundSearchPanel()");
		GtnUIFrameworkComponentConfig cffOutBoundSearchPanel = new GtnUIFrameworkComponentConfig();
		cffOutBoundSearchPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		cffOutBoundSearchPanel.setComponentId("CffOutBound_searchPanel");
		cffOutBoundSearchPanel.setAddToParent(true);
		cffOutBoundSearchPanel.setComponentName("Search Criteria");
		cffOutBoundSearchPanel.setParentComponentId(parentComponentId);
		cffOutBoundSearchPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cffOutBoundSearchPanel);
		
		addCffOutBoundSearchPanelLayout(componentList,cffOutBoundSearchPanel.getComponentId());
	}

	private void addCffOutBoundSearchPanelLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffOutBoundSearchPanelLayout()");
		GtnUIFrameworkComponentConfig cffOutBoundSearchPanelLayout = configProvider.getVerticalLayoutConfig("CffOutBound_searchPanelLayout", true, parentComponentId);
		cffOutBoundSearchPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cffOutBoundSearchPanelLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(cffOutBoundSearchPanelLayout);
		
		addCffProjIdCustCompanyNoLayout(componentList,cffOutBoundSearchPanelLayout.getComponentId());
		addCffProjCustCompNameLayout(componentList,cffOutBoundSearchPanelLayout.getComponentId());
		addTypeContractItemBussUnitNoLayout(componentList,cffOutBoundSearchPanelLayout.getComponentId());
		addCffCreationDateFromToContratItemNameLayout(componentList,cffOutBoundSearchPanelLayout.getComponentId());
		addBussUnitNameCffApprovalDateFromToLayout(componentList,cffOutBoundSearchPanelLayout.getComponentId());
		addSearchResetButtonLayout(componentList,cffOutBoundSearchPanelLayout.getComponentId());
	}

	private void addCffProjIdCustCompanyNoLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffProjIdCustCompanyNoLayout()");
		GtnUIFrameworkComponentConfig cffProjIdCustCompanyNoLayout=new GtnUIFrameworkComponentConfig();
		cffProjIdCustCompanyNoLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cffProjIdCustCompanyNoLayout.setComponentId("CffOutBound_cffProjIdCustCompanyNoLayout");
		cffProjIdCustCompanyNoLayout.setComponentWidth("100%");
		cffProjIdCustCompanyNoLayout.setAddToParent(true);
		cffProjIdCustCompanyNoLayout.setSpacing(true);
		cffProjIdCustCompanyNoLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig cffProjIdCustCompanyNoLayoutConfig=new GtnUIFrameworkLayoutConfig();
		cffProjIdCustCompanyNoLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cffProjIdCustCompanyNoLayout.setGtnLayoutConfig(cffProjIdCustCompanyNoLayoutConfig);
		componentList.add(cffProjIdCustCompanyNoLayout);
		
		addCffId(componentList,cffProjIdCustCompanyNoLayout.getComponentId());
		addProjectionId(componentList,cffProjIdCustCompanyNoLayout.getComponentId());
		addCustomerNo(componentList,cffProjIdCustCompanyNoLayout.getComponentId());
		addCompanyNo(componentList,cffProjIdCustCompanyNoLayout.getComponentId());
		
	}

	private void addCffId(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffId()");
		GtnUIFrameworkComponentConfig cffIdLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_CffIdLayout", true, parentComponentId);
		cffIdLayout.addComponentStyle("v-ps-cffidname-margin-left");
		cffIdLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(cffIdLayout);
		
		GtnUIFrameworkComponentConfig cffIdConfig = new GtnUIFrameworkComponentConfig();
		cffIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		cffIdConfig.setComponentName("CFF ID ");
		cffIdConfig.setComponentId("CffOutBound_CffId");
		cffIdConfig.setComponentWidth("100%");
		cffIdConfig.setParentComponentId(cffIdLayout.getComponentId());
		cffIdConfig.setAddToParent(true);
		componentList.add(cffIdConfig);
		
	}

	private void addProjectionId(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addProjectionId()");
		GtnUIFrameworkComponentConfig projectionIdLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_ProjectionIdLayout", true, parentComponentId);
		projectionIdLayout.addComponentStyle("v-ps-projnameid-margin-left");
		projectionIdLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionIdLayout);
		
		GtnUIFrameworkComponentConfig projectionIdConfig = new GtnUIFrameworkComponentConfig();
		projectionIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionIdConfig.setComponentName("Projection ID ");
		projectionIdConfig.setComponentId("CffOutBound_ProjectionId");
		projectionIdConfig.setComponentWidth("100%");
		projectionIdConfig.setParentComponentId(projectionIdLayout.getComponentId());
		projectionIdConfig.setAddToParent(true);
		componentList.add(projectionIdConfig);
		
	}

	private void addCustomerNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCustomerNo()");
		GtnUIFrameworkComponentConfig customerNoLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_customerNoLayout", true, parentComponentId);
		customerNoLayout.addComponentStyle("v-ps-customernameno-contractname-margin-left");
		customerNoLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerNoLayout);
		
		GtnUIFrameworkComponentConfig customerNoConfig = new GtnUIFrameworkComponentConfig();
		customerNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		customerNoConfig.setComponentName("Customer No ");
		customerNoConfig.setComponentId("CffOutBound_CustomerNo");
		customerNoConfig.setComponentWidth("100%");
		customerNoConfig.setParentComponentId(customerNoLayout.getComponentId());
		customerNoConfig.setAddToParent(true);
		componentList.add(customerNoConfig);
		
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCompanyNo()");
		GtnUIFrameworkComponentConfig companyNoLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_companyNoLayout", true, parentComponentId);
		companyNoLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(companyNoLayout);
		
		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		companyNoConfig.setComponentName("Company No ");
		companyNoConfig.setComponentId("CffOutBound_CompanyNo");
		companyNoConfig.setComponentWidth("100%");
		companyNoConfig.setParentComponentId(companyNoLayout.getComponentId());
		companyNoConfig.setAddToParent(true);
		componentList.add(companyNoConfig);
		
	}

	private void addCffProjCustCompNameLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffProjCustCompNameLayout()");
		GtnUIFrameworkComponentConfig cffProjCustCompNameLayout=new GtnUIFrameworkComponentConfig();
		cffProjCustCompNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cffProjCustCompNameLayout.setComponentId("CffOutBound_cffProjCustCompNameLayout");
		cffProjCustCompNameLayout.setComponentWidth("100%");
		cffProjCustCompNameLayout.setAddToParent(true);
		cffProjCustCompNameLayout.setSpacing(true);
		cffProjCustCompNameLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig cffProjCustCompNameLayoutConfig=new GtnUIFrameworkLayoutConfig();
		cffProjCustCompNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cffProjCustCompNameLayout.setGtnLayoutConfig(cffProjCustCompNameLayoutConfig);
		componentList.add(cffProjCustCompNameLayout);
		
		addCffName(componentList,cffProjCustCompNameLayout.getComponentId());
		addProjectionName(componentList,cffProjCustCompNameLayout.getComponentId());
		addCustomerName(componentList,cffProjCustCompNameLayout.getComponentId());
		addCompanyName(componentList,cffProjCustCompNameLayout.getComponentId());		
	}

	private void addCffName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffName()");
		GtnUIFrameworkComponentConfig cffNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_CffNameLayout", true, parentComponentId);
		cffNameLayout.addComponentStyle("v-ps-cffidname-margin-left");
		cffNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(cffNameLayout);
		
		GtnUIFrameworkComponentConfig cffNameConfig = new GtnUIFrameworkComponentConfig();
		cffNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		cffNameConfig.setComponentName("CFF Name ");
		cffNameConfig.setComponentId("CffOutBound_CffName");
		cffNameConfig.setComponentWidth("100%");
		cffNameConfig.setParentComponentId(cffNameLayout.getComponentId());
		cffNameConfig.setAddToParent(true);
		componentList.add(cffNameConfig);
		
	}

	private void addProjectionName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addProjectionName()");
		GtnUIFrameworkComponentConfig projectionNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_ProjectionNameLayout", true, parentComponentId);
		projectionNameLayout.addComponentStyle("v-ps-projnameid-margin-left");
		projectionNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionNameLayout);
		
		GtnUIFrameworkComponentConfig projectionNameConfig = new GtnUIFrameworkComponentConfig();
		projectionNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionNameConfig.setComponentName("Projection Name ");
		projectionNameConfig.setComponentId("CffOutBound_ProjectionName");
		projectionNameConfig.setComponentWidth("100%");
		projectionNameConfig.setParentComponentId(projectionNameLayout.getComponentId());
		projectionNameConfig.setAddToParent(true);
		componentList.add(projectionNameConfig);
		
	}

	private void addCustomerName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCustomerName()");
		GtnUIFrameworkComponentConfig customerNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_customerNameLayout", true, parentComponentId);
		customerNameLayout.addComponentStyle("v-ps-customernameno-contractname-margin-left");
		customerNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerNameLayout);
		
		GtnUIFrameworkComponentConfig customerNameConfig = new GtnUIFrameworkComponentConfig();
		customerNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		customerNameConfig.setComponentName("Customer Name ");
		customerNameConfig.setComponentId("CffOutBound_CustomerName");
		customerNameConfig.setComponentWidth("100%");
		customerNameConfig.setParentComponentId(customerNameLayout.getComponentId());
		customerNameConfig.setAddToParent(true);
		componentList.add(customerNameConfig);
		
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCompanyName()");
		GtnUIFrameworkComponentConfig companyNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_companyNameLayout", true, parentComponentId);
		companyNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(companyNameLayout);
		
		GtnUIFrameworkComponentConfig companyNameConfig = new GtnUIFrameworkComponentConfig();
		companyNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		companyNameConfig.setComponentName("Company Name ");
		companyNameConfig.setComponentId("CffOutBound_CompanyName");
		companyNameConfig.setComponentWidth("100%");
		companyNameConfig.setParentComponentId(companyNameLayout.getComponentId());
		companyNameConfig.setAddToParent(true);
		componentList.add(companyNameConfig);
		
	}
	
	private void addTypeContractItemBussUnitNoLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addTypeContractItemBussUnitNo()");
		GtnUIFrameworkComponentConfig typeContractItemBussUnitNoLayout=new GtnUIFrameworkComponentConfig();
		typeContractItemBussUnitNoLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		typeContractItemBussUnitNoLayout.setComponentId("CffOutBound_typeContractItemBussUnitNoLayout");
		typeContractItemBussUnitNoLayout.setComponentWidth("100%");
		typeContractItemBussUnitNoLayout.setAddToParent(true);
		typeContractItemBussUnitNoLayout.setSpacing(true);
		
		typeContractItemBussUnitNoLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig typeContractItemBussUnitNoLayoutConfig=new GtnUIFrameworkLayoutConfig();
		typeContractItemBussUnitNoLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		typeContractItemBussUnitNoLayout.setGtnLayoutConfig(typeContractItemBussUnitNoLayoutConfig);
		componentList.add(typeContractItemBussUnitNoLayout);		

		addType(componentList,typeContractItemBussUnitNoLayout.getComponentId());
		addContractNo(componentList,typeContractItemBussUnitNoLayout.getComponentId());
		addItemNo(componentList,typeContractItemBussUnitNoLayout.getComponentId());
		addBusinessUnitNo(componentList,typeContractItemBussUnitNoLayout.getComponentId());
	}

	private void addType(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addType()");
		GtnUIFrameworkComponentConfig typeLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_typeLayout", true, parentComponentId);
		typeLayout.addComponentStyle("v-ps-businessname-type-left-margin");
		typeLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(typeLayout);
		
		GtnUIFrameworkComponentConfig typeConfig = new GtnUIFrameworkComponentConfig();
		typeConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		typeConfig.setComponentName("Type ");
		typeConfig.setComponentId("CffOutBound_type");
		typeConfig.setComponentWidth("100%");
		typeConfig.setParentComponentId(typeLayout.getComponentId());
		typeConfig.setAddToParent(true);
		componentList.add(typeConfig);
		
		GtnUIFrameworkComboBoxConfig typeComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		typeComboBoxConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.TYPE));
		typeConfig.setGtnComboboxConfig(typeComboBoxConfig);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		typeConfig.setGtnUIFrameworkValidationConfig(valConfig);
		typeConfig.setGtnComboboxConfig(typeComboBoxConfig);
		
		typeConfig.setGtnComboboxConfig(typeComboBoxConfig);
		
	}

	private void addContractNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addContractNo()");
		GtnUIFrameworkComponentConfig contractNoLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_contractNoLayout", true, parentComponentId);
		contractNoLayout.addComponentStyle("v-ps-contractno-margin-left");
		contractNoLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(contractNoLayout);
		
		GtnUIFrameworkComponentConfig contractNoConfig = new GtnUIFrameworkComponentConfig();
		contractNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		contractNoConfig.setComponentName("Contract No ");
		contractNoConfig.setComponentId("CffOutBound_contractNo");
		contractNoConfig.setComponentWidth("100%");
		contractNoConfig.setParentComponentId(contractNoLayout.getComponentId());
		contractNoConfig.setAddToParent(true);
		componentList.add(contractNoConfig);
		
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addItemNo()");
		GtnUIFrameworkComponentConfig itemNoLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_itemNoLayout", true, parentComponentId);
		itemNoLayout.addComponentStyle("v-ps-itemno-margin-left");
		itemNoLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(itemNoLayout);
		
		GtnUIFrameworkComponentConfig itemNoConfig = new GtnUIFrameworkComponentConfig();
		itemNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		itemNoConfig.setComponentName("Item No ");
		itemNoConfig.setComponentId("CffOutBound_itemNo");
		itemNoConfig.setComponentWidth("100%");
		itemNoConfig.setParentComponentId(itemNoLayout.getComponentId());
		itemNoConfig.setAddToParent(true);
		componentList.add(itemNoConfig);
		
	}

	private void addBusinessUnitNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addBusinessUnitNo()");
		GtnUIFrameworkComponentConfig businessUnitNoLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_businessUnitNoLayout", true, parentComponentId);
		businessUnitNoLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(businessUnitNoLayout);
		
		GtnUIFrameworkComponentConfig businessUnitNoConfig = new GtnUIFrameworkComponentConfig();
		businessUnitNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		businessUnitNoConfig.setComponentName("Business Unit No ");
		businessUnitNoConfig.setComponentId("CffOutBound_businessUnitNo");
		businessUnitNoConfig.setComponentWidth("100%");
		businessUnitNoConfig.setParentComponentId(businessUnitNoLayout.getComponentId());
		businessUnitNoConfig.setAddToParent(true);
		componentList.add(businessUnitNoConfig);
	}

	private void addCffCreationDateFromToContratItemNameLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffCreationDateFromToContratItemName()");
		GtnUIFrameworkComponentConfig cffCreationDateFromToContratItemNameLayout=new GtnUIFrameworkComponentConfig();
		cffCreationDateFromToContratItemNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cffCreationDateFromToContratItemNameLayout.setComponentId("CffOutBound_cffCreationDateFromToContratItemNameLayout");
		cffCreationDateFromToContratItemNameLayout.setComponentWidth("100%");
		cffCreationDateFromToContratItemNameLayout.setAddToParent(true);
		cffCreationDateFromToContratItemNameLayout.setSpacing(true);
		cffCreationDateFromToContratItemNameLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig cffCreationDateFromToContratItemNameLayoutConfig=new GtnUIFrameworkLayoutConfig();
		cffCreationDateFromToContratItemNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cffCreationDateFromToContratItemNameLayout.setGtnLayoutConfig(cffCreationDateFromToContratItemNameLayoutConfig);
		componentList.add(cffCreationDateFromToContratItemNameLayout);
		
		addCffCreationDateFrom(componentList,cffCreationDateFromToContratItemNameLayout.getComponentId());
		addCffCreationDateTo(componentList,cffCreationDateFromToContratItemNameLayout.getComponentId());
		addContractName(componentList,cffCreationDateFromToContratItemNameLayout.getComponentId());
		addItemName(componentList,cffCreationDateFromToContratItemNameLayout.getComponentId());
	}

	private void addCffCreationDateFrom(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffCreationDateFrom()");
		GtnUIFrameworkComponentConfig cffCreationDateFromLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_cffCreationDateFromLayout", true, parentComponentId);
		cffCreationDateFromLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(cffCreationDateFromLayout);
		
		GtnUIFrameworkComponentConfig cffCreationDateFromConfig = new GtnUIFrameworkComponentConfig();
		cffCreationDateFromConfig.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		cffCreationDateFromConfig.setComponentName("CFF Creation Date From ");
		cffCreationDateFromConfig.setComponentId("CffOutBound_cffCreationDateFrom");
		cffCreationDateFromConfig.setComponentWidth("100%");
		cffCreationDateFromConfig.setParentComponentId(cffCreationDateFromLayout.getComponentId());
		cffCreationDateFromConfig.setAddToParent(true);
		componentList.add(cffCreationDateFromConfig);
		
	}

	private void addCffCreationDateTo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffCreationDateTo()");
		GtnUIFrameworkComponentConfig cffCreationDateToLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_cffCreationDateToLayout", true, parentComponentId);
		cffCreationDateToLayout.addComponentStyle("v-ps-cffcdt-margin-left");
		cffCreationDateToLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(cffCreationDateToLayout);
		
		GtnUIFrameworkComponentConfig cffCreationDateToConfig = new GtnUIFrameworkComponentConfig();
		cffCreationDateToConfig.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		cffCreationDateToConfig.setComponentName("CFF Creation Date To ");
		cffCreationDateToConfig.setComponentId("CffOutBound_cffCreationDateTo");
		cffCreationDateToConfig.setComponentWidth("100%");
		cffCreationDateToConfig.setParentComponentId(cffCreationDateToLayout.getComponentId());
		cffCreationDateToConfig.setAddToParent(true);
		componentList.add(cffCreationDateToConfig);
		
	}

	private void addContractName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addContractName()");
		GtnUIFrameworkComponentConfig contractNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_contractNameLayout", true, parentComponentId);
		contractNameLayout.addComponentStyle("v-ps-customernameno-contractname-margin-left");
		contractNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(contractNameLayout);
		
		GtnUIFrameworkComponentConfig contractNameConfig = new GtnUIFrameworkComponentConfig();
		contractNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		contractNameConfig.setComponentName("Contract Name ");
		contractNameConfig.setComponentId("CffOutBound_contractName");
		contractNameConfig.setComponentWidth("100%");
		contractNameConfig.setParentComponentId(contractNameLayout.getComponentId());
		contractNameConfig.setAddToParent(true);
		componentList.add(contractNameConfig);
		
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addItemName()");
		GtnUIFrameworkComponentConfig itemNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_itemNameLayout", true, parentComponentId);
		itemNameLayout.addComponentStyle("v-ps-itemname-margin-left");
		itemNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(itemNameLayout);
		
		GtnUIFrameworkComponentConfig itemNameConfig = new GtnUIFrameworkComponentConfig();
		itemNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		itemNameConfig.setComponentName("Item Name ");
		itemNameConfig.setComponentId("CffOutBound_itemName");
		itemNameConfig.setComponentWidth("100%");
		itemNameConfig.setParentComponentId(itemNameLayout.getComponentId());
		itemNameConfig.setAddToParent(true);
		componentList.add(itemNameConfig);
		
	}

	private void addBussUnitNameCffApprovalDateFromToLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addBussUnitNameCffApprovalDateFromTo()");
		GtnUIFrameworkComponentConfig bussUnitNameCffApprovalDateFromToLayout=new GtnUIFrameworkComponentConfig();
		bussUnitNameCffApprovalDateFromToLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		bussUnitNameCffApprovalDateFromToLayout.setComponentId("CffOutBound_bussUnitNameCffApprovalDateFromToLayout");
		bussUnitNameCffApprovalDateFromToLayout.setComponentWidth("100%");
		bussUnitNameCffApprovalDateFromToLayout.setAddToParent(true);
		bussUnitNameCffApprovalDateFromToLayout.setSpacing(true);
		bussUnitNameCffApprovalDateFromToLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig bussUnitNameCffApprovalDateFromToLayoutConfig=new GtnUIFrameworkLayoutConfig();
		bussUnitNameCffApprovalDateFromToLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		bussUnitNameCffApprovalDateFromToLayout.setGtnLayoutConfig(bussUnitNameCffApprovalDateFromToLayoutConfig);
		componentList.add(bussUnitNameCffApprovalDateFromToLayout);
		
		addBusinessUnitName(componentList,bussUnitNameCffApprovalDateFromToLayout.getComponentId());
		addCffApprovalDateFrom(componentList,bussUnitNameCffApprovalDateFromToLayout.getComponentId());
		addCffApprovalDateTo(componentList,bussUnitNameCffApprovalDateFromToLayout.getComponentId());
	}

	private void addBusinessUnitName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addBuisnessUnitName()");
		GtnUIFrameworkComponentConfig businessUnitNameLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_businessUnitNameLayout", true, parentComponentId);
		businessUnitNameLayout.addComponentStyle("v-ps-businessname-type-left-margin");
		businessUnitNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(businessUnitNameLayout);
		
		GtnUIFrameworkComponentConfig businessUnitNameConfig = new GtnUIFrameworkComponentConfig();
		businessUnitNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		businessUnitNameConfig.setComponentName("Business Name ");
		businessUnitNameConfig.setComponentId("CffOutBound_businessUnitName");
		businessUnitNameConfig.setComponentWidth("100%");
		businessUnitNameConfig.setParentComponentId(businessUnitNameLayout.getComponentId());
		businessUnitNameConfig.setAddToParent(true);
		componentList.add(businessUnitNameConfig);
		
	}

	private void addCffApprovalDateFrom(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffApprovalDateFrom()");
		GtnUIFrameworkComponentConfig cffApprovalDateFromLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_cffApprovalDateFromLayout", true, parentComponentId);
		cffApprovalDateFromLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(cffApprovalDateFromLayout);
		
		GtnUIFrameworkComponentConfig cffApprovalDateFromConfig = new GtnUIFrameworkComponentConfig();
		cffApprovalDateFromConfig.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		cffApprovalDateFromConfig.setComponentName("CFF Approval Date From ");
		cffApprovalDateFromConfig.setComponentId("CffOutBound_cffApprovalDateFrom");
		cffApprovalDateFromConfig.setComponentWidth("100%");
		cffApprovalDateFromConfig.setParentComponentId(cffApprovalDateFromLayout.getComponentId());
		cffApprovalDateFromConfig.setAddToParent(true);
		componentList.add(cffApprovalDateFromConfig);
		
	}

	private void addCffApprovalDateTo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffApprovalDateTo()");
		GtnUIFrameworkComponentConfig cffApprovalDateToLayout = configProvider
				.getHorizontalLayoutConfig("CffOutBound_cffApprovalDateToLayout", true, parentComponentId);
		cffApprovalDateToLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(cffApprovalDateToLayout);
		
		GtnUIFrameworkComponentConfig cffApprovalDateToConfig = new GtnUIFrameworkComponentConfig();
		cffApprovalDateToConfig.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		cffApprovalDateToConfig.setComponentName("CFF Approval Date To ");
		cffApprovalDateToConfig.setComponentId("CffOutBound_cffCreationDateTo");
		cffApprovalDateToConfig.setComponentWidth("100%");
		cffApprovalDateToConfig.setParentComponentId(cffApprovalDateToLayout.getComponentId());
		cffApprovalDateToConfig.setAddToParent(true);
		componentList.add(cffApprovalDateToConfig);		
	}

	private void addSearchResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addSearchResetButtonLayout()");
		GtnUIFrameworkComponentConfig searchResetButtonLayout=new GtnUIFrameworkComponentConfig();
		searchResetButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchResetButtonLayout.setComponentId("CffOutBound_searchResetButtonLayout");
		searchResetButtonLayout.setComponentWidth("100%");
		searchResetButtonLayout.setAddToParent(true);
		searchResetButtonLayout.setSpacing(true);
		searchResetButtonLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig searchResetButtonLayoutConfig=new GtnUIFrameworkLayoutConfig();
		searchResetButtonLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		searchResetButtonLayout.setGtnLayoutConfig(searchResetButtonLayoutConfig);
		componentList.add(searchResetButtonLayout);
		
		addSearchButton(componentList,searchResetButtonLayout.getComponentId());
		addResetButton(componentList,searchResetButtonLayout.getComponentId());
	}

	private void addSearchButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addSearchButton()");
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("CffOutBound_searchButton");
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setParentComponentId(parentComponentId);
		searchButtonConfig.setAddToParent(true);
		componentList.add(searchButtonConfig);
		
	}

	private void addResetButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig resetButtonConfig = new GtnUIFrameworkComponentConfig();
		resetButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentId("CffOutBound_resetButton");
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setParentComponentId(parentComponentId);
		resetButtonConfig.setAddToParent(true);
		componentList.add(resetButtonConfig);
		
	}

	private void addCffOutBoundResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addCffOutBoundResultsPanel()");
		GtnUIFrameworkComponentConfig cffOutBoundResultsPanel = new GtnUIFrameworkComponentConfig();
		cffOutBoundResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		cffOutBoundResultsPanel.setComponentId("CffOutBound_resultsPanel");
		cffOutBoundResultsPanel.setAddToParent(true);
		cffOutBoundResultsPanel.setComponentName("Results");
		cffOutBoundResultsPanel.setParentComponentId(parentComponentId);
		cffOutBoundResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cffOutBoundResultsPanel);
		
		addCffOutBoundResultsLayout(componentList,cffOutBoundResultsPanel.getComponentId());
	}

	private void addCffOutBoundResultsLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addCffOutBoundSearchResultsLayout()");
		GtnUIFrameworkComponentConfig cffOutBoundSearchPanelLayout = configProvider.getVerticalLayoutConfig("CffOutBound_resultsPanelLayout", true, parentComponentId);
		cffOutBoundSearchPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cffOutBoundSearchPanelLayout);
		addResultsTable(componentList,cffOutBoundSearchPanelLayout.getComponentId());
	}

	private void addResultsTable(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addResultsTable()");
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentId(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId(parentComponentId);
		searchResultConfig.setAddToParent(true);
		searchResultConfig.setResetToDefaultAllowed(false);
		
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setSelectable(true);
		searchResults.setPageLength(5);
		searchResults.setItemPerPage(5);
		searchResults.setSinkItemPerPageWithPageLength(true);
		searchResults.setFilterBar(true);
		searchResults.setEditable(false);
		//searchResults.set
	

		

		searchResults.setTableColumnDataType(new Class[] { Boolean.class,String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class });
		searchResults.setTableVisibleHeader(
				new String[] { "", "Financial Forecast ID", "Financial Forecast Name", "Type",
				        "Project ID", "Projection Name", "Year", "Month", "Contract ID", "Contract No", "Contract Name", "Contract Type", "Contract Holder ID", "Contract Holder No",
				        "Contract Holder Name", "Customer ID", "Customer No", "Customer Name", "Item ID", "Item No", "Item Name", "Sales Dollars", "Sales Units", "Sales Inclusion",
				        "Deduction ID", "Deduction No", "Deduction Name", "Deduction Category", "Deduction Type", "Deduction Program", "Deduction Inclusion", "Deduction Category 1",
				        "Deduction Category 2", "Deduction Category 3", "Deduction Category 4", "Deduction Category 5", "Deduction Category 6", "Deduction Dollars", "Deduction Rate",
				        "Deduction Per Unit", "Net Sales Dollar", "COGS Amount", "COGS Per Unit", "Net Profit Dollars", "Net Profit Per Unit", "Company ID", "Company No", "Company Name",
				        "Business Unit ID", "Business Unit No", "Business Unit Name", "Financial Forecast Creation Date", "Financial Forecast Approval Date" 
				        , "Outbound Status", "Original Batch ID" });
		searchResults.setTableColumnMappingId(
				new Object[] {GtnFrameworkCommonConstants.CHECK_RECORD_ID, "financialForecastId", "financialForecastName", "typeDesc",
				        "projectionId", "projectionName", "year", "month", "contractId", "contractNo", "contractName", "contractType", "contractHolderId", "contractHolderNo",
				        "contractHolderName", "customerId", "customerNo", "customerName", "itemId", "itemNo", "itemName", "salesDollars", "salesUnits", "salesInclusion",
				        "deductionId", "deductionNo", "deductionName", "deductionCategory", "deductionType", "deductionProgram", "deductionInclusion", "deductionCategoryOne",
				        "deductionCategoryTwo", "deductionCategoryThree", "deductionCategoryFour", "deductionCategoryFive", "deductionCategorySix", "deductionDollars", "deductionRate",
				        "deductionPerUnit", "netSalesDollar", "cogsAmount", "cogsPerUnit", "netProfitDollars", "netProfitPerUnit", "companyId", "companyNo", "companyName",
				        "businessUnitId", "businessUnitNo", "businessUnitName", "financialForecastCreationDate", "financialForecastApprovalDate" 
				        , "outboundStatus", "originalBatchId" });
		searchResults.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		//searchResults.setExtraColumn(new Object[] { "scriptname" });
		searchResultConfig.setGtnPagedTableConfig(searchResults);
		
		
		/*GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(searchResultConfig.getComponentId());
		searchResults.addPostCreationActionConfig(loadDataTableActionConfig);
		searchResults.setDoubleClickEnable(true);*/
	}

	private void addGenerateOutBoundButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addGenerateOutBoundButtonLayout()");
		GtnUIFrameworkComponentConfig generateOutBoundButtonLayout=new GtnUIFrameworkComponentConfig();
		generateOutBoundButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		generateOutBoundButtonLayout.setComponentId("CffOutBound_generateOutBoundButtonLayout");
		generateOutBoundButtonLayout.setComponentWidth("100%");
		generateOutBoundButtonLayout.setAddToParent(true);
		generateOutBoundButtonLayout.setSpacing(true);
		generateOutBoundButtonLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig generateOutBoundButtonLayoutConfig=new GtnUIFrameworkLayoutConfig();
		generateOutBoundButtonLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		generateOutBoundButtonLayout.setGtnLayoutConfig(generateOutBoundButtonLayoutConfig);
		componentList.add(generateOutBoundButtonLayout);
		
		addGenerateOutBoundButton(componentList,generateOutBoundButtonLayout.getComponentId());
	}

	private void addGenerateOutBoundButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addGenerateOutBoundButton()");
		GtnUIFrameworkComponentConfig generateOutBoundButtonConfig = new GtnUIFrameworkComponentConfig();
		generateOutBoundButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateOutBoundButtonConfig.setComponentId("CffOutBound_generateOutBoundButton");
		generateOutBoundButtonConfig.setComponentName("GENERATE OUTBOUND");
		generateOutBoundButtonConfig.setParentComponentId(parentComponentId);
		generateOutBoundButtonConfig.setAddToParent(true);
		componentList.add(generateOutBoundButtonConfig);
		
	}
}
