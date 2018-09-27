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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

import com.vaadin.ui.TreeGrid;

public class GtnForecastingDataSelectionLoadViewAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass

{
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnForecastingDataSelectionLoadViewAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
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
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME, componentId)
			.loadV8FieldValue(dataSelectionBean.getProjectionName());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_DESCRIPTION, componentId)
			.loadV8FieldValue(dataSelectionBean.getProjectionDescription());
			
			GtnWsRecordBean customerRecordBean = dataSelectionBean.getCustomerHierarchyRecordBean();
			
			GtnWsRecordBean customerGroup= dataSelectionBean.getCustomerGroup();
			
			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP, componentId)
					.getComponentData();
			customerHierarchyData.setCustomData(customerRecordBean);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP, componentId)
					.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));
			
			GtnUIFrameWorkActionConfig customerActionConfig = new GtnUIFrameWorkActionConfig();
			customerActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customerActionConfig.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
			customerActionConfig.addActionParameter(GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP);
			customerActionConfig.addActionParameter(GtnFrameworkCommonConstants.SCREEN_REGISTRY_CF_CUST_SEL_REL);
			customerActionConfig.addActionParameter(dataSelectionBean.getCustomerRelationshipBuilderSid());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId,customerActionConfig); 
			
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTRELATIONVERSION, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerRelationshipVersionNo());
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTOMERSELECTIONFORECASTLEVEL, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyLevel().toString());
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTSELECTIONLEVEL, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyInnerLevel());

			GtnUIFrameworkComponentData customerGroupData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTGROUP, componentId)
					.getComponentData();
			customerGroupData.setCustomData(customerGroup);
			if(customerGroup != null)
			{
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTGROUP, componentId)
			.setV8PopupFieldValue(customerGroup.getPropertyValueByIndex(0));
			}
			
			String customerTableId =GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTOMERDUALLISTBOX;
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
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME, componentId)
					.getComponentData();
			productHierarchyData.setCustomData(productRecordBean);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME, componentId)
					.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));
			
			List<GtnUIFrameWorkActionConfig> productActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig productActionConfig = new GtnUIFrameWorkActionConfig();
			productActionConfigList.add(productActionConfig);
			productActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			productActionConfig.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
			productActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME);
			productActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONSHIP);
			productActionConfig.addActionParameter(dataSelectionBean.getProductRelationshipBuilderSid());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId,productActionConfig); 
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONVERSION, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getProductRelationshipVersionNo());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODFORECASTLEVEL, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getProductHierarchyLevel().toString());
			
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTLEVEL, componentId)
			.loadV8ComboBoxComponentValue(dataSelectionBean.getProductHierarchyInnerLevel());
			
			GtnUIFrameworkComponentData productGroupData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTGROUP, componentId)
					.getComponentData();
			productGroupData.setCustomData(productGroup);
			
			if(productGroup != null)
			{
			GtnUIFrameworkGlobalUI
			.getVaadinBaseComponentFromParent(
					GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTGROUP, componentId)
			.setV8PopupFieldValue(productGroup.getPropertyValueByIndex(0));
			
			}
			
			String productTableId =GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTDUALLISTBOX;
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
	

	
