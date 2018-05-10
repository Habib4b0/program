package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			logger.info("-------------------------GtnReportDataSelectionTabLoadAction------------------------------"
					+ componentId);

			GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId).getComponentData().getSharedPopupData();

			logger.info("-------popupData-----------" + reportDataSelectionBean.getCompanyReport());

			GtnWsRecordBean customerRecordBean = reportDataSelectionBean.getCustomerHierarchyRecordBean();
			for(int i=0;i<7;i++){
				logger.info("---------customerRecordBean-------"+customerRecordBean.getPropertyValueByIndex(i));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_company", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCompanyReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_businessUnit", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getBusinessUnitReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getFromPeriodReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getReportDataSource());
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId)
					.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));

			
			
			String rightTableId="dataSelectionTab_customerDualListBox";
			AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(rightTableId, componentId);
			GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		
			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
			
			TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
			
			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
			gtnUIFrameworkHierarchyTreeBuilder.buildTree(reportDataSelectionBean.getSelectedCustomerHierarchyList());
			gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();

			
			AbstractComponent abstractComponentLeftTable = GtnUIFrameworkGlobalUI.getVaadinComponent("reportLandingScreen_customerDualListBox");
			GtnUIFrameworkComponentData dualListBoxDataLeftTable = (GtnUIFrameworkComponentData) abstractComponentLeftTable.getData();
		
			GtnFrameworkV8DualListBoxBean dualListBoxBeanLeftTable = (GtnFrameworkV8DualListBoxBean) dualListBoxDataLeftTable.getCustomData();
			
			Grid<GtnWsRecordBean> leftTable = dualListBoxBeanLeftTable.getLeftTable();
			ListDataProvider listContainer = (ListDataProvider)leftTable.getDataProvider();
		    Collection items = listContainer.getItems(); 
		    
		    
		    Grid<GtnWsRecordBean> dsLeftTable = dualListBoxBean.getLeftTable();
		    
		    dsLeftTable.setItems(items);
			
			
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerSelectionRelationship", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerSelectionLevel", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerHierarchyForecastLevel());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerRelationshipVersion", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_relationship", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_level", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_productRelationshipVersion", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
		} catch (Exception exception) {
			logger.error("Error message", exception);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
