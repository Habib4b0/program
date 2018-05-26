package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.Collection;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId).getComponentData().getSharedPopupData();

			GtnWsRecordBean customerRecordBean = reportDataSelectionBean.getCustomerHierarchyRecordBean();
			GtnWsRecordBean productRecordBean = reportDataSelectionBean.getProductHierarchyRecordBean();

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_company", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCompanyReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_businessUnit", componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getBusinessUnitReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getFromPeriodReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getReportDataSource());

			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId).getComponentData();
			customerHierarchyData.setCustomData(customerRecordBean);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId)
					.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));

			String dsCustomerTableId = "dataSelectionTab_customerDualListBox";
			AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsCustomerTableId,
					componentId);
			GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData
					.getCustomData();

			TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();

			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
			gtnUIFrameworkHierarchyTreeBuilder.buildTree(reportDataSelectionBean.getSelectedCustomerHierarchyList());
			gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();

			AbstractComponent abstractComponentLeftTable = GtnUIFrameworkGlobalUI
					.getVaadinComponent("reportLandingScreen_customerDualListBox");
			GtnUIFrameworkComponentData dualListBoxDataLeftTable = (GtnUIFrameworkComponentData) abstractComponentLeftTable
					.getData();

			GtnFrameworkV8DualListBoxBean dualListBoxBeanLeftTable = (GtnFrameworkV8DualListBoxBean) dualListBoxDataLeftTable
					.getCustomData();

			Grid<GtnWsRecordBean> leftTable = dualListBoxBeanLeftTable.getLeftTable();
			ListDataProvider listContainer = (ListDataProvider) leftTable.getDataProvider();
			Collection items = listContainer.getItems();

			Grid<GtnWsRecordBean> dsLeftTable = dualListBoxBean.getLeftTable();

			dsLeftTable.setItems(items);

			GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId).getComponentData();
			productHierarchyData.setCustomData(productRecordBean);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId)
					.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));

			String dsProductTableId = "dataSelectionTab_productdualListBoxComp";
			AbstractComponent dsProductAbstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsProductTableId,
					componentId);
			GtnUIFrameworkComponentData dsProductDualListBoxData = (GtnUIFrameworkComponentData) dsProductAbstractComponent
					.getData();

			GtnFrameworkV8DualListBoxBean dsProductDualListBoxBean = (GtnFrameworkV8DualListBoxBean) dsProductDualListBoxData
					.getCustomData();

			TreeGrid<GtnWsRecordBean> dsProductRightTable = dsProductDualListBoxBean.getRightTable();

			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkProductHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
			gtnUIFrameworkProductHierarchyTreeBuilder
					.buildTree(reportDataSelectionBean.getSelectedProductHierarchyList());
			gtnUIFrameworkProductHierarchyTreeBuilder.loadRightTreeTable(dsProductRightTable, 1);
			dsProductRightTable.getDataProvider().refreshAll();
			dsProductRightTable.markAsDirty();

			AbstractComponent dsProductAbstractComponentLeftTable = GtnUIFrameworkGlobalUI
					.getVaadinComponent("reportLandingScreen_productdualListBoxComp");
			GtnUIFrameworkComponentData reportDualListBoxDataLeftTable = (GtnUIFrameworkComponentData) dsProductAbstractComponentLeftTable
					.getData();

			GtnFrameworkV8DualListBoxBean dsProductDualListBoxBeanLeftTable = (GtnFrameworkV8DualListBoxBean) reportDualListBoxDataLeftTable
					.getCustomData();

			Grid<GtnWsRecordBean> reportLandingScreenProductLeftTable = dsProductDualListBoxBeanLeftTable
					.getLeftTable();
			ListDataProvider dsProductListContainer = (ListDataProvider) reportLandingScreenProductLeftTable
					.getDataProvider();
			Collection dsProductItems = dsProductListContainer.getItems();

			Grid<GtnWsRecordBean> dsProductLeftTable = dsProductDualListBoxBean.getLeftTable();

			dsProductLeftTable.setItems(dsProductItems);

			Integer hierarchyDefinitionSid = (Integer) customerRecordBean
					.getPropertyValueByIndex(customerRecordBean.getProperties().size() - 1);

			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
					.getComponentConfig().getGtnComboboxConfig();

			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

			GtnUIFrameworkComboboxComponent customerRelationshipCombobox = new GtnUIFrameworkComboboxComponent();
			customerRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId,
					Arrays.asList(hierarchyDefinitionSid));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());

			int relationshipValue = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
					.getValueFromComponent();

			GtnUIFrameworkComboBoxConfig customerRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION,
							componentId)
					.getComponentConfig().getGtnComboboxConfig();

			customerRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customerRelationshipVersionComboboxConfig
					.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

			GtnUIFrameworkComboboxComponent customerRelationshipVersionCombobox = new GtnUIFrameworkComboboxComponent();
			customerRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION, componentId,
					Arrays.asList(relationshipValue));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION, componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getCustomerHierarchyVersionNo());

			GtnUIFrameworkComboBoxConfig customerLevelComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_LEVEL, componentId)
					.getComponentConfig().getGtnComboboxConfig();

			customerLevelComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customerLevelComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);

			GtnUIFrameworkComboboxComponent customerLevelCombobox = new GtnUIFrameworkComboboxComponent();
			customerLevelCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_LEVEL, componentId,
					Arrays.asList(hierarchyDefinitionSid));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_SELECTION_LEVEL, componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getCustomerHierarchyForecastLevel());

			Integer productHierarchyDefinitionSid = (Integer) productRecordBean
					.getPropertyValueByIndex(productRecordBean.getProperties().size() - 1);

			GtnUIFrameworkComboBoxConfig productRelationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_RELATIONSHIP,
							componentId)
					.getComponentConfig().getGtnComboboxConfig();

			productRelationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			productRelationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

			GtnUIFrameworkComboboxComponent productRelationshipCombobox = new GtnUIFrameworkComboboxComponent();
			productRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_RELATIONSHIP, componentId,
					Arrays.asList(productHierarchyDefinitionSid));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_RELATIONSHIP,
							componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getProductRelationshipBuilderSid());

			int productrelationshipValue = (int) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_RELATIONSHIP,
							componentId)
					.getValueFromComponent();

			GtnUIFrameworkComboBoxConfig productRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION,
							componentId)
					.getComponentConfig().getGtnComboboxConfig();

			productRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			productRelationshipVersionComboboxConfig
					.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

			GtnUIFrameworkComboboxComponent productRelationshipVersionCombobox = new GtnUIFrameworkComboboxComponent();
			productRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION, componentId,
					Arrays.asList(productrelationshipValue));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION, componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getProductHierarchyVersionNo());

			GtnUIFrameworkComboBoxConfig productLevelComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_LEVEL, componentId)
					.getComponentConfig().getGtnComboboxConfig();

			productLevelComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			productLevelComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);

			GtnUIFrameworkComboboxComponent productLevelCombobox = new GtnUIFrameworkComboboxComponent();
			productLevelCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_LEVEL, componentId,
					Arrays.asList(hierarchyDefinitionSid));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_LEVEL, componentId)
					.loadComboBoxComponentValue(reportDataSelectionBean.getProductHierarchyForecastLevel());

		} catch (Exception exception) {
			logger.error("Error message", exception);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
