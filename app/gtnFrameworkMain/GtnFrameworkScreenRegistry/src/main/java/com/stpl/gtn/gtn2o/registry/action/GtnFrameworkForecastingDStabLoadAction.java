package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;

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
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkForecastingDStabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try{
		
		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		
		GtnFrameworkForecastDataSelectionBean forecastDsBean = (GtnFrameworkForecastDataSelectionBean)GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData().getSharedPopupData();
		
		GtnWsRecordBean customerRecordBean = forecastDsBean.getCustomerHierarchyRecordBean();
		
		GtnWsRecordBean productRecordBean = forecastDsBean.getProductHierarchyRecordBean();
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_profileMode", componentId).setEnable(false);
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_company", componentId).loadV8ComboBoxComponentValue(forecastDsBean.getCompany());
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_businessUnit", componentId).loadV8ComboBoxComponentValue(forecastDsBean.getBusinessUnit());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_projectionName", componentId).loadV8FieldValue(forecastDsBean.getProjectionName());
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_projectionDescription", componentId).loadV8FieldValue(forecastDsBean.getProjectionDescription());
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_customerHierarchy", componentId).setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));
		
		GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_customerHierarchy", componentId).getComponentData();
		customerHierarchyData.setCustomData(customerRecordBean);

		GtnUIFrameWorkActionConfig relationshiploadAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		relationshiploadAction.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
		relationshiploadAction.addActionParameter("CFDataSelection_customerHierarchy");
		relationshiploadAction.addActionParameter("CFDataSelection_customerSelectionRelationship");
		relationshiploadAction.addActionParameter(forecastDsBean.getCustomerRelationshipBuilderSid());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, relationshiploadAction);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("CFDataSelection_customerSelectionForecastLevel",
							componentId)
					.loadV8ComboBoxComponentValue(forecastDsBean.getCustomerHierarchyLevel().toString());
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("CFDataSelection_customerSelectionLevel", componentId)
					.loadV8ComboBoxComponentValue(forecastDsBean.getCustomerHierarchyInnerLevel());
			
			
			String customerTableId ="CFDataSelection_customerDualListBox";
			GtnUIFrameworkBaseComponent abstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(customerTableId, componentId);
			GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData
					.getCustomData();
			TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = dualListBoxBean.getTreeBuilder();
			gtnUIFrameworkHierarchyTreeBuilder.buildTree(forecastDsBean.getSelectedCustomerHierarchyList());
			gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_prodhierarchyName", componentId).setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));
			
			GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_prodhierarchyName", componentId).getComponentData();
			productHierarchyData.setCustomData(productRecordBean);

			GtnUIFrameWorkActionConfig productRelationshiploadAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			productRelationshiploadAction.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
			productRelationshiploadAction.addActionParameter("CFDataSelection_prodhierarchyName");
			productRelationshiploadAction.addActionParameter("CFDataSelection_prodrelationship");
			productRelationshiploadAction.addActionParameter(forecastDsBean.getProductRelationshipBuilderSid());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, productRelationshiploadAction);

				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("CFDataSelection_prodforecastLevel",
								componentId)
						.loadV8ComboBoxComponentValue(forecastDsBean.getProductHierarchyLevel().toString());
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("CFDataSelection_productLevel", componentId)
						.loadV8ComboBoxComponentValue(forecastDsBean.getProductHierarchyInnerLevel());
				
				
				String productTableId ="CFDataSelection_productDualListBox";
				GtnUIFrameworkBaseComponent productSelectionAbstractComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(productTableId, componentId);
				GtnUIFrameworkComponentData productTableIdDualListBoxData = (GtnUIFrameworkComponentData) productSelectionAbstractComponent.getData();

				GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) productTableIdDualListBoxData
						.getCustomData();
				TreeGrid<GtnWsRecordBean> productRightTable = productDualListBoxBean.getRightTable();
				GtnUIFrameworkHierarchyTreeBuilder productGtnUIFrameworkHierarchyTreeBuilder = productDualListBoxBean.getTreeBuilder();
				productGtnUIFrameworkHierarchyTreeBuilder.buildTree(forecastDsBean.getSelectedProductHierarchyList());
				productGtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(productRightTable, 1);
				productRightTable.getDataProvider().refreshAll();
				productRightTable.markAsDirty();
			
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_frequency", componentId).loadV8ComboBoxComponentValue(forecastDsBean.getFrequency());
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_deductionLevel", componentId).loadV8ComboBoxComponentValue(forecastDsBean.getDeductionLevel());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_from", componentId).loadV8ComboBoxComponentValue(forecastDsBean.getFromPeriodForecast());
		
		if(forecastDsBean.getCustomerGroup()!=null){
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_customerGroup", componentId).setV8PopupFieldValue(forecastDsBean.getCustomerGroup().getPropertyValueByIndex(0));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_customerGroup", componentId).getComponentData().setCustomData(forecastDsBean.getCustomerGroup());
		}
		
		if(forecastDsBean.getProductGroup()!=null){
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_productGroup", componentId).setV8PopupFieldValue(forecastDsBean.getProductGroup().getPropertyValueByIndex(0));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFDataSelection_productGroup", componentId).getComponentData().setCustomData(forecastDsBean.getProductGroup());

		}

		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
