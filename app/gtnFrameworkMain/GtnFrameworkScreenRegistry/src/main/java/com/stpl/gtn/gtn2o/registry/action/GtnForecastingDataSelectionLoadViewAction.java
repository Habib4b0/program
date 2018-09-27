package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

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
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCompany());
			
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					 nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
					+GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID, componentId)
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
			if(customerGroup != null)
			{
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_customerGroup", componentId)
			.setV8PopupFieldValue(customerGroup.getPropertyValueByIndex(0));
			}
			
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
			
			if(productGroup != null)
			{
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					"Commercial Forecasting_productGroup", componentId)
			.setV8PopupFieldValue(productGroup.getPropertyValueByIndex(0));
			
			}
			
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
					"Commercial Forecasting_frequency", componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getFrequency());
			
			
			

			gtnLogger.info("nameSpace" + nameSpace);
			

		} catch (Exception ex) {
			gtnLogger.error("Error message", ex);
		}
	}


	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
	

	
