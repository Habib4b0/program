package com.stpl.gtn.gtn2o.ui.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionLoadViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionLoadViewAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(1).toString()).getComponentData().getCustomData();
		String viewData = (String) recordBean.getPropertyValueByIndex(5);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		try {
			dataSelectionBean = (GtnWsReportDataSelectionBean) convertJsonToObject(GtnWsReportDataSelectionBean.class,
					viewData);
		} catch (IOException e) {
			gtnLogger.error("Error in converting Bean", e);
		}
		GtnWsRecordBean customerHierarchyRecordBean = dataSelectionBean.getCustomerHierarchyRecordBean();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_company")
				.loadV8ComboBoxComponentValue(dataSelectionBean.getCompanyReport());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_businessUnit")
				.loadComboBoxComponentValue(dataSelectionBean.getBusinessUnitReport());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_reportDataSource")
				.loadV8ComboBoxComponentValue(dataSelectionBean.getReportDataSource());

		GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerHierarchy").getComponentData();
		customerHierarchyData.setCustomData(customerHierarchyRecordBean);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_customerHierarchy", componentId)
				.setV8PopupFieldValue(customerHierarchyRecordBean.getPropertyValueByIndex(0));

		Integer hierarchyDefinitionSid = (Integer) customerHierarchyRecordBean
				.getPropertyValueByIndex(customerHierarchyRecordBean.getProperties().size() - 1);
		GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIP)
				.getComponentConfig().getGtnComboboxConfig();
		relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);
		GtnUIFrameworkComboboxComponent customerRelationshipCombobox = new GtnUIFrameworkComboboxComponent();
		customerRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIP, componentId,
				Arrays.asList(hierarchyDefinitionSid));
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIP)
				.loadComboBoxComponentValue(dataSelectionBean.getCustomerRelationshipBuilderSid());

		int relationshipValue = (int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIP)
				.getValueFromComponent();
		GtnUIFrameworkComboBoxConfig customerRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIPVERSION)
				.getComponentConfig().getGtnComboboxConfig();
		customerRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerRelationshipVersionComboboxConfig
				.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);
		GtnUIFrameworkComboboxComponent customerRelationshipVersionCombobox = new GtnUIFrameworkComboboxComponent();
		customerRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIPVERSION, componentId,
				Arrays.asList(relationshipValue));

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_RELATIONSHIPVERSION)
				.loadComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyVersionNo());

		GtnUIFrameworkComboBoxConfig customerLevelComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_LEVEL)
				.getComponentConfig().getGtnComboboxConfig();

		customerLevelComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerLevelComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);

		GtnUIFrameworkComboboxComponent customerLevelCombobox = new GtnUIFrameworkComboboxComponent();
		customerLevelCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_CUSTOMERHIERARCHY_LEVEL, componentId,
				Arrays.asList(hierarchyDefinitionSid));

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_customerSelectionLevel")
				.loadComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyForecastLevel());

		String dsCustomerTableId = "reportLandingScreen_customerDualListBox";
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsCustomerTableId);
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
		gtnUIFrameworkHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedCustomerHierarchyList());
		gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
		rightTable.getDataProvider().refreshAll();
		rightTable.markAsDirty();

		GtnWsRecordBean productRecordBean = dataSelectionBean.getProductHierarchyRecordBean();
		GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_producthierarchy").getComponentData();
		productHierarchyData.setCustomData(productRecordBean);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_producthierarchy", componentId)
				.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));

		Integer productHierarchyDefinitionSid = (Integer) productRecordBean
				.getPropertyValueByIndex(productRecordBean.getProperties().size() - 1);

		GtnUIFrameworkComboBoxConfig productRelationComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIP)
				.getComponentConfig().getGtnComboboxConfig();

		productRelationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productRelationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

		GtnUIFrameworkComboboxComponent productRelationshipCombobox = new GtnUIFrameworkComboboxComponent();
		productRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIP, componentId,
				Arrays.asList(productHierarchyDefinitionSid));
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIP)
				.loadComboBoxComponentValue(dataSelectionBean.getProductRelationshipBuilderSid());

		int productrelationshipValue = (int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIP)
				.getValueFromComponent();

		GtnUIFrameworkComboBoxConfig productRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIPVERSION)
				.getComponentConfig().getGtnComboboxConfig();

		productRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productRelationshipVersionComboboxConfig
				.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

		GtnUIFrameworkComboboxComponent productRelationshipVersionCombobox = new GtnUIFrameworkComboboxComponent();
		productRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIPVERSION, componentId,
				Arrays.asList(productrelationshipValue));

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_RELATIONSHIPVERSION)
				.loadComboBoxComponentValue(dataSelectionBean.getProductHierarchyVersionNo());

		GtnUIFrameworkComboBoxConfig productLevelComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_LEVEL)
				.getComponentConfig().getGtnComboboxConfig();

		productLevelComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productLevelComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);

		GtnUIFrameworkComboboxComponent productLevelCombobox = new GtnUIFrameworkComboboxComponent();
		productLevelCombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_LEVEL, componentId,
				Arrays.asList(productHierarchyDefinitionSid));

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_PRODUCTHIERARCHY_LEVEL)
				.loadComboBoxComponentValue(dataSelectionBean.getProductHierarchyForecastLevel());

		String productTableComponentId = "reportLandingScreen_productdualListBoxComp";
		AbstractComponent productComponentData = GtnUIFrameworkGlobalUI.getVaadinComponent(productTableComponentId);
		GtnUIFrameworkComponentData productDualListBoxData = (GtnUIFrameworkComponentData) productComponentData
				.getData();

		GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) productDualListBoxData
				.getCustomData();

		TreeGrid<GtnWsRecordBean> dsProductRightTable = productDualListBoxBean.getRightTable();

		GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkProductHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
		gtnUIFrameworkProductHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedProductHierarchyList());
		gtnUIFrameworkProductHierarchyTreeBuilder.loadRightTreeTable(dsProductRightTable, 1);
		dsProductRightTable.getDataProvider().refreshAll();
		dsProductRightTable.markAsDirty();
	}

	private GtnWsReportDataSelectionBean convertJsonToObject(Class<GtnWsReportDataSelectionBean> dataSelectionBean,
			String viewData) throws JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(viewData, dataSelectionBean);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
