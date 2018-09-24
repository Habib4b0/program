package com.stpl.gtn.gtn2o.registry.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionRelationshipLoadAction;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.vaadin.ui.TreeGrid;

public class GtnForecastingDataSelectionLoadViewAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass

{
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnForecastingDataSelectionLoadViewAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
			 
			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(actionParamList.get(1).toString(), componentId).getComponentData()
					.getCustomData();
			//String viewData = (String) recordBean.getPropertyValueByIndex(5);
			String nameSpace = actionParamList.get(2).toString();
			ObjectMapper mapper = new ObjectMapper();

			GtnFrameworkForecastDataSelectionBean dataSelectionBean = mapper.convertValue(
					recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1), new TypeReference<GtnFrameworkForecastDataSelectionBean>() {
					});
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "company", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCompany());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "businessUnit", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getBusinessUnit());
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "projectionName", componentId)
			.loadV8FieldValue(dataSelectionBean.getProjectionName());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "projectionDescription", componentId)
			.loadV8FieldValue(dataSelectionBean.getProjectionDescription());
			
			GtnWsRecordBean customerRecordBean = dataSelectionBean.getCustomerHierarchyRecordBean();
			
			GtnWsRecordBean customerGroup= dataSelectionBean.getCustomerGroup();
			
			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							"forecastLandingScreen_customerHierarchy", componentId)
					.getComponentData();
			customerHierarchyData.setCustomData(customerRecordBean);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							"forecastLandingScreen_customerHierarchy", componentId)
					.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));
			
			List<GtnUIFrameWorkActionConfig> customerActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig customerActionConfig = new GtnUIFrameWorkActionConfig();
			customerActionConfigList.add(customerActionConfig);
			customerActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customerActionConfig.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
			customerActionConfig.addActionParameter("forecastLandingScreen_customerHierarchy");
			customerActionConfig.addActionParameter("Commercial_Forecasting_customerSelectionRelationship");
			customerActionConfig.addActionParameter(dataSelectionBean.getCustomerRelationshipBuilderSid());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId,customerActionConfig); 
			
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial_Forecasting_customerRelationshipVersion", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerRelationshipVersionNo());
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial_Forecasting_customerSelectionForecastLevel", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyLevel().toString());
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial_Forecasting_customerSelectionLevel", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyInnerLevel());

			GtnUIFrameworkComponentData customerGroupData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							"Commercial Forecasting_customerGroup", componentId)
					.getComponentData();
			customerGroupData.setCustomData(customerGroup);
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_customerGroup", componentId)
			.setV8PopupFieldValue(customerGroup.getPropertyValueByIndex(0));
			
			String customerTableId ="Commercial Forecasting_customerDualListBox";
			GtnUIFrameworkBaseComponent abstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(customerTableId, componentId);
			GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData
					.getCustomData();
			TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = dualListBoxBean.getTreeBuilder();
			gtnUIFrameworkHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedCustomerHierarchyList());
			gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();
			
			GtnWsRecordBean productRecordBean = dataSelectionBean.getProductHierarchyRecordBean();
			
			GtnWsRecordBean productGroup = dataSelectionBean.getProductGroup();

			GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							"Commercial Forecasting_prodhierarchyName", componentId)
					.getComponentData();
			productHierarchyData.setCustomData(productRecordBean);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							"Commercial Forecasting_prodhierarchyName", componentId)
					.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));
			
			List<GtnUIFrameWorkActionConfig> productActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig productActionConfig = new GtnUIFrameWorkActionConfig();
			productActionConfigList.add(productActionConfig);
			productActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			productActionConfig.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
			productActionConfig.addActionParameter("Commercial Forecasting_prodhierarchyName");
			productActionConfig.addActionParameter("Commercial Forecasting_prodrelationship");
			productActionConfig.addActionParameter(dataSelectionBean.getProductRelationshipBuilderSid());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId,productActionConfig); 
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial_Forecasting_productRelationshipVersion", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getProductRelationshipVersionNo());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_prodforecastLevel", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getProductHierarchyLevel().toString());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_productLevel", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getProductHierarchyInnerLevel());
			
			GtnUIFrameworkComponentData productGroupData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							"Commercial Forecasting_productGroup", componentId)
					.getComponentData();
			productGroupData.setCustomData(productGroup);
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_productGroup", componentId)
			.setV8PopupFieldValue(productGroup.getPropertyValueByIndex(0));
			
			String productTableId ="Commercial Forecasting_productDualListBox";
			GtnUIFrameworkBaseComponent productAbstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(productTableId, componentId);
			GtnUIFrameworkComponentData productDualListBoxData = (GtnUIFrameworkComponentData) productAbstractComponent.getData();

			GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) productDualListBoxData
					.getCustomData();
			TreeGrid<GtnWsRecordBean> productRightTable = productDualListBoxBean.getRightTable();
			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkProductHierarchyTreeBuilder = productDualListBoxBean.getTreeBuilder();
			gtnUIFrameworkProductHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedProductHierarchyList());
			gtnUIFrameworkProductHierarchyTreeBuilder.loadRightTreeTable(productRightTable, 1);
			productRightTable.getDataProvider().refreshAll();
			productRightTable.markAsDirty();
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_Frequency", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getFrequency());
			
			
			

			gtnLogger.info("nameSpace" + nameSpace);
			
//			GtnWsRecordBean rb=new GtnWsRecordBean();
//			
//			List<Object> list=new ArrayList<>();
//			list.add(rb.getPropertyValueByIndex(4));
//			list.add(rb.getPropertyValueByIndex());
//			list.add(rb.getPropertyValueByIndex());
//			list.add(rb.getPropertyValueByIndex(11));
//			list.add(rb.getPropertyValueByIndex(12));
//			list.add(rb.getPropertyValueByIndex(5));
//			list.add(rb.getPropertyValueByIndex(28));
//			list.add(rb.getPropertyValueByIndex(17));
//			
//			rb.addPropertiesAsList(list);
//			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
//					.getVaadinBaseComponentFromParent(
//							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "customerHierarchy", componentId)
//					.getComponentData();
//			customerHierarchyData.setCustomData(rb);
			
//			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
//					.getVaadinBaseComponentFromParent(
//							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "customerHierarchy", componentId)
//					.getComponentData();
//			customerHierarchyData.setCustomData(rb);
			
			/*GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
			try {
				dataSelectionBean = (GtnWsReportDataSelectionBean) convertJsonToObject(
						GtnWsReportDataSelectionBean.class, viewData.replaceAll("\\\\", "'"));
			} catch (IOException e) {
				gtnLogger.error("Error in converting Bean", e);
			}
			GtnWsRecordBean customerHierarchyRecordBean = dataSelectionBean.getCustomerHierarchyRecordBean();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "company", componentId)
					.loadV8ComboBoxComponentValue(dataSelectionBean.getCompanyReport());

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "businessUnit", componentId)
					.loadV8ComboBoxComponentValue(dataSelectionBean.getBusinessUnitReport());

			String dataSourceId = nameSpace.equals("dataSelectionTab")
					? nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "dsTabProjectionName"
					: nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "reportDataSource";
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(dataSourceId, componentId)
					.loadV8ComboBoxComponentValue(dataSelectionBean.getReportDataSource());

			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "customerHierarchy", componentId)
					.getComponentData();
			customerHierarchyData.setCustomData(customerHierarchyRecordBean);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "customerHierarchy", componentId)
					.setV8PopupFieldValue(customerHierarchyRecordBean.getPropertyValueByIndex(0));

			Integer hierarchyDefinitionSid = (Integer.valueOf(String.valueOf(customerHierarchyRecordBean
					.getPropertyValueByIndex(customerHierarchyRecordBean.getProperties().size() - 1))));

			String relationshipId = nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
					+ "customerSelectionRelationship";
			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(relationshipId, componentId).getComponentConfig()
					.getGtnComboboxConfig();
			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);
			GtnUIFrameworkComboBoxComponent customerRelationshipCombobox = new GtnUIFrameworkComboBoxComponent();

			customerRelationshipCombobox.reloadComponentFromParent(relationshipId, componentId,
					Arrays.asList(hierarchyDefinitionSid));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(relationshipId, componentId)
					.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerRelationshipBuilderSid());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "customerSelectionLevel", componentId)
					.loadV8ComboBoxComponentValue(
							Integer.valueOf(dataSelectionBean.getCustomerHierarchyForecastLevel()));

			String dsCustomerTableId = nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "customerDualListBox";
			GtnUIFrameworkBaseComponent abstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(dsCustomerTableId, componentId);
			GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData
					.getCustomData();
			TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = dualListBoxBean.getTreeBuilder();
			gtnUIFrameworkHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedCustomerHierarchyList());
			gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();

			GtnWsRecordBean productRecordBean = dataSelectionBean.getProductHierarchyRecordBean();
			GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "producthierarchy", componentId)
					.getComponentData();
			productHierarchyData.setCustomData(productRecordBean);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "producthierarchy", componentId)
					.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));

			Integer productHierarchyDefinitionSid = (Integer.valueOf(String
					.valueOf(productRecordBean.getPropertyValueByIndex(productRecordBean.getProperties().size() - 1))));

			String productRelationshipId = nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "relationship";
			GtnUIFrameworkComboBoxConfig productRelationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(productRelationshipId, componentId).getComponentConfig()
					.getGtnComboboxConfig();

			productRelationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			productRelationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

			GtnUIFrameworkComboBoxComponent productRelationshipCombobox = new GtnUIFrameworkComboBoxComponent();
			productRelationshipCombobox.reloadComponentFromParent(productRelationshipId, componentId,
					Arrays.asList(productHierarchyDefinitionSid));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(productRelationshipId, componentId)
					.loadV8ComboBoxComponentValue(dataSelectionBean.getProductRelationshipBuilderSid());

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "level", componentId)
					.loadV8ComboBoxComponentValue(
							Integer.valueOf(dataSelectionBean.getProductHierarchyForecastLevel()));

			String productTableComponentId = nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
					+ "productdualListBoxComp";
			GtnUIFrameworkBaseComponent productComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(productTableComponentId, componentId);
			GtnUIFrameworkComponentData productDualListBoxData = (GtnUIFrameworkComponentData) productComponentData
					.getData();

			GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) productDualListBoxData
					.getCustomData();

			TreeGrid<GtnWsRecordBean> dsProductRightTable = productDualListBoxBean.getRightTable();

			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkProductHierarchyTreeBuilder = productDualListBoxBean
					.getTreeBuilder();
			gtnUIFrameworkProductHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedProductHierarchyList());
			gtnUIFrameworkProductHierarchyTreeBuilder.loadRightTreeTable(dsProductRightTable, 1);
			dsProductRightTable.getDataProvider().refreshAll();
			dsProductRightTable.markAsDirty();
                        
                        if(dataSelectionBean.getCustomView()!=null){
                            GtnUIFrameworkGlobalUI
                                            .getVaadinBaseComponentFromParent(
                                                            nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "displaySelectionTabCustomView",
                                                            componentId)
                                            .loadV8ComboBoxComponentValue(String.valueOf(dataSelectionBean.getCustomView()));
                        }
		
			int frequency = dataSelectionBean.getFrequency();
			if (frequency != 0) {
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
								+ "landingScreenVariableBreakdownFrequencyConfig", componentId)
						.loadV8ComboBoxComponentValue(frequency);
			}

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
							+ "reportingDashboardComparisonConfig", componentId)
					.getComponentData().setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
			if (dataSelectionBean.getComparisonProjectionBeanList() != null) {
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
								+ "reportingDashboardComparisonConfig", componentId)
						.setV8PopupFieldValue(getDisplayValue(dataSelectionBean));
			}

			GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
					GtnWsReportVariablesType.values().length - 1);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "displaySelectionTabVariable",
							componentId)
					.addAllItemsToMultiSelect(
							Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()),
							Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()));
			if (dataSelectionBean.getVariablesList() != null) {
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
								+ "displaySelectionTabVariable", componentId)
						.updateSelection(dataSelectionBean.getVariablesList());
			}

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_reportOptionsTabVariableBreakdown")
					.getComponentData().setCustomData(
							Optional.ofNullable(dataSelectionBean.getVariableBreakdownSaveList()).isPresent() == true
									? dataSelectionBean.getVariableBreakdownSaveList()
									: new ArrayList<>());*/
		} catch (Exception ex) {
			gtnLogger.error("Error message", ex);
		}
	}

//	private Object getDisplayValue(GtnWsReportDataSelectionBean dataSelectionBean) {
//		if (dataSelectionBean.getComparisonProjectionBeanList().size() > 1) {
//			return "MULTIPLE";
//		} else {
//			return dataSelectionBean.getComparisonProjectionBeanList().get(0).getProjectionName();
//		}
//	}
//
//	private GtnWsReportDataSelectionBean convertJsonToObject(Class<GtnWsReportDataSelectionBean> dataSelectionBean,
//			String viewData) throws JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.readValue(viewData, dataSelectionBean);
//	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
	

	
