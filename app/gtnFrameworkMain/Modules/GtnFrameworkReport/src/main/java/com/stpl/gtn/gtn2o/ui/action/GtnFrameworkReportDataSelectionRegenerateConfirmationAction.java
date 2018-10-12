package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkReportDataSelectionRegenerateConfirmationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReportDataSelectionRegenerateConfirmationAction.class);
	private String dataSelectionTabProductHierarchy = "dataSelectionTab_producthierarchy";

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            try
            {

		List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) params.get(1);
                if (isForward((int) params.get(26))) {
		if (params.get(2).equals("OK")) {
			updateCustomer((boolean) params.get(14), dataSelectionBean, (List<GtnWsRecordBean>) params.get(3),
					componentId);
			updateProduct((boolean) params.get(15), dataSelectionBean, (List<GtnWsRecordBean>) params.get(4),
					componentId);
			updateComparisonProjectionInReportingDashboard((boolean) params.get(16), dataSelectionBean,
					(List<GtnReportComparisonProjectionBean>) params.get(5), componentId);
			updateCustomView((boolean) params.get(17), dataSelectionBean, (String) params.get(6), componentId);
			updateFrequency((boolean) params.get(18), dataSelectionBean, (String) params.get(7), componentId);
			updateVariables((boolean) params.get(19), (List<Object>) params.get(8), dataSelectionBean, componentId);
			updateCompany((boolean) params.get(20), (String) params.get(9), dataSelectionBean);
			updateBusinessUnit((boolean) params.get(21), (String) params.get(10), dataSelectionBean);
			updateReportDataSource((boolean) params.get(22), (String) params.get(11), dataSelectionBean);
			updateFromPeriod((boolean) params.get(23), (String) params.get(12), dataSelectionBean, componentId,
					(String) params.get(13));
			callRegenerate(dataSelectionBean);
			updateComparisonProjectionInDataSelection((boolean) params.get(24), dataSelectionBean,
					(List<GtnReportComparisonProjectionBean>) params.get(25), componentId);
			addTabInDataAssumptions(dataSelectionBean, componentId);
		} else {

			loadDataSelectionTab(componentId, dataSelectionBean);

		}
                }
                 else {
            gtnLogger.info("Success");
            if (params.get(2).equals("OK")) {
                updateCustomViewInDataSelection((boolean) params.get(17), dataSelectionBean, componentId);
                updateFrequencyInDataSelection((boolean) params.get(18), dataSelectionBean, componentId);
                updateVariablesInDataSelection((boolean) params.get(19), dataSelectionBean, componentId);
                loadComparisonInReportingDashboard(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_COMPARISON_LOOKUP,
				componentId, dataSelectionBean);
                addTabInDataAssumptions(dataSelectionBean, componentId);
            } else {

                loadDataSelectionTab(componentId, dataSelectionBean);

            }

        }
            }
            catch(Exception exception){
                gtnLogger.info("Exception Occured while Updating Bean"+exception);
            }

	}
         private boolean isForward(int indexValue) {
        return indexValue < 0;
    }


	private void addTabInDataAssumptions(GtnWsReportDataSelectionBean dataSelectionBean, String componentId) {
		try {
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			actionConfig.setActionParameterList(Arrays.asList(GtnReportDataAssumptionsTabLoadAction.class.getName(),
					GtnFrameworkReportStringConstants.TAB_SHEET + "dataAssump",
					GtnFrameworkReportStringConstants.CURRENT_TAB,
					GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD, dataSelectionBean));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(" " + e);
		}
	}

	private void loadDataSelectionTab(String componentId, GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkValidationFailedException {
		GtnWsRecordBean customerRecordBean = dataSelectionBean.getCustomerHierarchyRecordBean();
		GtnWsRecordBean productRecordBean = dataSelectionBean.getProductHierarchyRecordBean();

		if (dataSelectionBean.getPrivateViewName() != null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabPrivateViews", componentId)
					.setV8PopupFieldValue(dataSelectionBean.getPrivateViewName());
		}

		if (dataSelectionBean.getPublicViewName() != null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabPublicViews", componentId)
					.setV8PopupFieldValue(dataSelectionBean.getPublicViewName());
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_company", componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getCompanyReport());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_businessUnit", componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getBusinessUnitReport());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getReportDataSource());

		GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_HIERARCHY,
						componentId)
				.getComponentData();
		customerHierarchyData.setCustomData(customerRecordBean);

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_HIERARCHY,
						componentId)
				.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));

		new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                                    GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_CUSTOM_VIEW, componentId, Arrays.asList(""));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_CUSTOM_VIEW, componentId)
				.loadV8ComboBoxComponentValue(String.valueOf(dataSelectionBean.getCustomViewMasterSid()));
		new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_DISPLAY_SELECTION_TAB_CUSTOM_VIEW,
				componentId, Arrays.asList(""));
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(
						GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_DISPLAY_SELECTION_TAB_CUSTOM_VIEW,
						componentId)
				.loadV8ComboBoxComponentValue(String.valueOf(dataSelectionBean.getCustomViewMasterSid()));

		new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE, componentId, Arrays.asList(""));

		Integer hierarchyDefinitionSid = Integer.valueOf(String
				.valueOf(customerRecordBean.getPropertyValueByIndex(customerRecordBean.getProperties().size() - 1)));

		GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
				.getComponentConfig().getGtnComboboxConfig();

		relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

		GtnUIFrameworkComboBoxComponent customerRelationshipCombobox = new GtnUIFrameworkComboBoxComponent();
		customerRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId,
				Arrays.asList(hierarchyDefinitionSid));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerRelationshipBuilderSid());

		int relationshipValue = Integer.parseInt(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
				.getCaptionFromV8ComboBox());

		GtnUIFrameworkComboBoxConfig customerRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(
						GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION, componentId)
				.getComponentConfig().getGtnComboboxConfig();

		customerRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerRelationshipVersionComboboxConfig
				.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

		GtnUIFrameworkComboBoxComponent customerRelationshipVersionCombobox = new GtnUIFrameworkComboBoxComponent();
		customerRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION, componentId,
				Arrays.asList(relationshipValue));

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_LEVEL,
						componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getCustomerHierarchyForecastLevel());

		String dsCustomerTableId = "dataSelectionTab_customerDualListBox";
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsCustomerTableId, componentId);
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();

		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();

		GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
		gtnUIFrameworkHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedCustomerHierarchyList());
		gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
		rightTable.getDataProvider().refreshAll();
		rightTable.markAsDirty();

		GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(dataSelectionTabProductHierarchy, componentId).getComponentData();
		productHierarchyData.setCustomData(productRecordBean);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(dataSelectionTabProductHierarchy, componentId)
				.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));

		Integer productHierarchyDefinitionSid = Integer.valueOf(String
				.valueOf(productRecordBean.getPropertyValueByIndex(productRecordBean.getProperties().size() - 1)));

		GtnUIFrameworkComboBoxConfig productRelationComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP, componentId)
				.getComponentConfig().getGtnComboboxConfig();

		productRelationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productRelationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

		GtnUIFrameworkComboBoxComponent productRelationshipCombobox = new GtnUIFrameworkComboBoxComponent();
		productRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP, componentId,
				Arrays.asList(productHierarchyDefinitionSid));
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP, componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getProductRelationshipBuilderSid());

		int productrelationshipValue = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP, componentId)
				.getCaptionFromV8ComboBox());

		GtnUIFrameworkComboBoxConfig productRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(
						GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION, componentId)
				.getComponentConfig().getGtnComboboxConfig();

		productRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productRelationshipVersionComboboxConfig
				.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

		GtnUIFrameworkComboBoxComponent productRelationshipVersionCombobox = new GtnUIFrameworkComboBoxComponent();
		productRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION, componentId,
				Arrays.asList(productrelationshipValue));

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_LEVEL, componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getProductHierarchyForecastLevel());

		String dsProductTableId = "dataSelectionTab_productdualListBoxComp";
		AbstractComponent dsProductAbstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsProductTableId,
				componentId);
		GtnUIFrameworkComponentData dsProductDualListBoxData = (GtnUIFrameworkComponentData) dsProductAbstractComponent
				.getData();

		GtnFrameworkV8DualListBoxBean dsProductDualListBoxBean = (GtnFrameworkV8DualListBoxBean) dsProductDualListBoxData
				.getCustomData();

		TreeGrid<GtnWsRecordBean> dsProductRightTable = dsProductDualListBoxBean.getRightTable();

		GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkProductHierarchyTreeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();
		gtnUIFrameworkProductHierarchyTreeBuilder.buildTree(dataSelectionBean.getSelectedProductHierarchyList());
		gtnUIFrameworkProductHierarchyTreeBuilder.loadRightTreeTable(dsProductRightTable, 1);
		dsProductRightTable.getDataProvider().refreshAll();
		dsProductRightTable.markAsDirty();

		loadComparisonInReportingDashboard(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_COMPARISON_LOOKUP,
				componentId, dataSelectionBean);

		GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
				GtnWsReportVariablesType.values().length - 1);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_VARIABLE, componentId)
				.addAllItemsToMultiSelect(
						Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
								.collect(Collectors.toList()),
						Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
								.collect(Collectors.toList()));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_VARIABLE, componentId)
				.updateSelection(dataSelectionBean.getVariablesList());

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG, componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getFrequency());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void callRegenerate(GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnForecastHierarchyInputBean inputBean = createInputBeanForCCPInsert(
				dataSelectionBean.getSelectedCustomerHierarchyList(),
				dataSelectionBean.getSelectedProductHierarchyList(), dataSelectionBean);
		insertToCCp(inputBean, dataSelectionBean);
	}

	private GtnForecastHierarchyInputBean createInputBeanForCCPInsert(
			List<GtnWsRecordBean> selectedCustomerHierarchyList, List<GtnWsRecordBean> selectedProductHierarchyList,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedCustomerList(relationBeanList(selectedCustomerHierarchyList, dataSelectionBean, false));
		inputBean.setSelectedProductList(relationBeanList(selectedProductHierarchyList, dataSelectionBean, true));
		inputBean.setSelectedCustomerRelationShipBuilderVersionNo(dataSelectionBean.getCustomerRelationshipVersionNo());
		inputBean.setSelectedProductRelationShipBuilderVersionNo(dataSelectionBean.getProductRelationshipVersionNo());
		inputBean.setSelectedCustomerHierarcySid((int) dataSelectionBean.getCustomerHierarchySid());
		inputBean.setSelectedProductHierarcySid((int) dataSelectionBean.getProductHierarchySid());
		inputBean.setSelectedProductRelationShipBuilderSid(dataSelectionBean.getProductRelationshipBuilderSid());
		inputBean.setSelectedCustomerHierarchyVersionNo(dataSelectionBean.getCustomerHierarchyVersionNo());
		inputBean.setSelectedProductHierarchyVersionNo(dataSelectionBean.getProductHierarchyVersionNo());
		inputBean.setForecastEligibleDate(dataSelectionBean.getForecastEligibleDate());
		inputBean.setForecastInsert(true);
		return inputBean;
	}

	private List<GtnFrameworkRelationshipLevelDefintionBean> relationBeanList(
			List<GtnWsRecordBean> selectedCustomerHierarchyList, GtnWsReportDataSelectionBean dataSelectionBean,
			boolean isProduct) {
		List<GtnFrameworkRelationshipLevelDefintionBean> relationBeanList = new ArrayList<>(
				selectedCustomerHierarchyList.size());
		for (GtnWsRecordBean recordBean : selectedCustomerHierarchyList) {

			GtnFrameworkRelationshipLevelDefintionBean forecastRelationshipLevelDefintionBean = new GtnFrameworkRelationshipLevelDefintionBean();
			forecastRelationshipLevelDefintionBean.setLevelName(recordBean.getStringProperty("levelName"));
			forecastRelationshipLevelDefintionBean.setLevelNo(recordBean.getIntegerProperty("levelNo"));
			forecastRelationshipLevelDefintionBean
					.setRelationshipLevelSid(recordBean.getIntegerProperty("relationshipLevelSid"));
			forecastRelationshipLevelDefintionBean
					.setRelationShipLevelValue(recordBean.getIntegerProperty("relationshipLevelValues"));
			forecastRelationshipLevelDefintionBean.setTableName(recordBean.getStringProperty("tableName"));
			forecastRelationshipLevelDefintionBean.setFieldName(recordBean.getStringProperty("fieldName"));
			forecastRelationshipLevelDefintionBean.setHierarchyNo(recordBean.getStringProperty("hierarchyNo"));
			forecastRelationshipLevelDefintionBean
					.setHierarchyDefinitionSid(recordBean.getIntegerProperty("hierarchyDefSid"));
			forecastRelationshipLevelDefintionBean
					.setHierarchyLevelDefinitionSid(recordBean.getIntegerProperty("hierarchyLevelDefSid"));
			forecastRelationshipLevelDefintionBean
					.setLevelValueReference(recordBean.getStringProperty("levelValuReference"));
			forecastRelationshipLevelDefintionBean
					.setRelationshipBuilderSid(recordBean.getIntegerProperty("relationshipBuilderSid"));
			forecastRelationshipLevelDefintionBean
					.setRelationshipVersionNo(!isProduct ? dataSelectionBean.getCustomerRelationshipVersionNo()
							: dataSelectionBean.getProductRelationshipVersionNo());
			forecastRelationshipLevelDefintionBean
					.setHierarchyVersionNo(!isProduct ? dataSelectionBean.getCustomerHierarchyVersionNo()
							: dataSelectionBean.getProductHierarchyVersionNo());
			forecastRelationshipLevelDefintionBean
					.setHierarchyCategory(recordBean.getStringProperty("hierarchyCategory"));
			relationBeanList.add(forecastRelationshipLevelDefintionBean);

		}
		return relationBeanList;
	}

	private void insertToCCp(GtnForecastHierarchyInputBean inputBean, GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnWsForecastRequest forecastRegenerateRequest = new GtnWsForecastRequest();
		GtnWsReportRequest reportRegenerateRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRegenerateRequest = new GtnWsGeneralRequest();
		GtnWsReportBean reportBeanRegenerateRequest = new GtnWsReportBean();
		generalRegenerateRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRegenerateRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		reportRegenerateRequest.setReportBean(reportBeanRegenerateRequest);
		reportBeanRegenerateRequest.setDataSelectionBean(dataSelectionBean);
		forecastRegenerateRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient regenerateClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest regenerateRequest = new GtnUIFrameworkWebserviceRequest();
		regenerateRequest.setGtnWsForecastRequest(forecastRegenerateRequest);
		regenerateRequest.setGtnWsReportRequest(reportRegenerateRequest);
		regenerateRequest.setGtnWsGeneralRequest(generalRegenerateRequest);
		GtnUIFrameworkWebserviceResponse responseRegenerate = regenerateClient.callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_WS_DATA_SELECTION_REGENERATE_SERVICE, "report", regenerateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		gtnLogger.info(responseRegenerate.getResponseStatus());
	}

	private void updateCustomer(boolean isCustomerChanged, GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnWsRecordBean> selectedCustomerList, String componentId)
			throws GtnFrameworkValidationFailedException {
		Date forecastEligibleDate = null;
		if (isCustomerChanged) {
			GtnWsRecordBean customerHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_CUSTOMER_HIERARCHY,
							componentId)
					.getComponentData().getCustomData();
			dataSelectionBean.setCustomerHierarchySid(Integer.valueOf(String.valueOf(
					customerHierarchyBean.getPropertyValueByIndex(customerHierarchyBean.getProperties().size() - 1))));
			dataSelectionBean.setCustomerRelationshipBuilderSid(Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionRelationship", componentId)
					.getCaptionFromV8ComboBox())));
			dataSelectionBean.setCustomerHierarchyVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerRelationshipVersion", componentId)
					.getStringCaptionFromV8ComboBox()));
			dataSelectionBean.setCustomerRelationshipVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerRelationshipVersion", componentId)
					.getCaptionFromV8ComboBox()));
			dataSelectionBean.setCustomerHierarchyForecastLevel(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionLevel", componentId)
					.getCaptionFromV8ComboBox()));
			LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionForecastEligibilityDate", componentId)
					.getFieldValue();
			if (date != null) {
				forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
			dataSelectionBean.setForecastEligibleDate(forecastEligibleDate);
			dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyBean);
			dataSelectionBean.setSelectedCustomerHierarchyList(selectedCustomerList);
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboardTab_filterTabCustomerLevel", componentId, Arrays.asList(""));
		}
	}

	private void updateProduct(boolean isProductChanged, GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnWsRecordBean> selectedProductList, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (isProductChanged) {
			GtnWsRecordBean productHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId).getComponentData()
					.getCustomData();
			dataSelectionBean.setProductHierarchySid(Integer.valueOf(String.valueOf(
					productHierarchyBean.getPropertyValueByIndex(productHierarchyBean.getProperties().size() - 1))));
			dataSelectionBean.setProductRelationshipBuilderSid(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_relationship", componentId).getCaptionFromV8ComboBox()));
			dataSelectionBean.setProductHierarchyVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_productRelationshipVersion", componentId)
					.getStringCaptionFromV8ComboBox()));
			dataSelectionBean.setProductRelationshipVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_productRelationshipVersion", componentId)
					.getCaptionFromV8ComboBox()));
			dataSelectionBean.setProductHierarchyForecastLevel(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_level", componentId).getCaptionFromV8ComboBox()));
			dataSelectionBean.setProductHierarchyRecordBean(productHierarchyBean);
			dataSelectionBean.setSelectedProductHierarchyList(selectedProductList);
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboardTab_filterOptionsTabProductLevel", componentId, Arrays.asList(""));
		}
	}

	private void updateComparisonProjectionInReportingDashboard(boolean isComparisonProjectionChanged,
			GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnReportComparisonProjectionBean> comparisonProjectionsList, String componentId) {
		if (isComparisonProjectionChanged) {
			dataSelectionBean.setComparisonProjectionBeanList(comparisonProjectionsList);
			GtnUIFrameworkComponentData comparisonData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
					.getComponentData();
			comparisonData.setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
			if (dataSelectionBean.getComparisonProjectionBeanList() != null) {
				String displayValue = getDisplayValue(dataSelectionBean.getComparisonProjectionBeanList());
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
						.setV8PopupFieldValue(displayValue);
			}
			updateComparisonBaasis(comparisonProjectionsList, componentId);
		}
	}

	private void updateComparisonProjectionInDataSelection(boolean isComparisonProjectionChanged,
			GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnReportComparisonProjectionBean> comparisonProjectionsList, String componentId) {
		if (isComparisonProjectionChanged) {
			dataSelectionBean.setComparisonProjectionBeanList(comparisonProjectionsList);
			GtnUIFrameworkComponentData comparisonData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_COMPARISON_LOOKUP,
							componentId)
					.getComponentData();
			comparisonData.setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
			if (dataSelectionBean.getComparisonProjectionBeanList() != null
					&& !dataSelectionBean.getComparisonProjectionBeanList().isEmpty()) {
				String displayValue = getDisplayValue(dataSelectionBean.getComparisonProjectionBeanList());
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_TAB_COMPARISON_LOOKUP,
								componentId)
						.setV8PopupFieldValue(displayValue);
			}
		}
	}

	private String getDisplayValue(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		if (comparisonProjectionBeanList.size() > 1) {
			return "MULTIPLE";
		}
		return comparisonProjectionBeanList.get(0).getProjectionName();
	}

	private void updateCustomView(boolean isCustomView, GtnWsReportDataSelectionBean dataSelectionBean,
			String customViewName, String componentId) throws GtnFrameworkValidationFailedException {
		if (isCustomView) {
			dataSelectionBean.setCustomViewMasterSid(Integer.valueOf(customViewName));
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_DISPLAY_SELECTION_TAB_CUSTOM_VIEW,
					componentId, Arrays.asList(""));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_DISPLAY_SELECTION_TAB_CUSTOM_VIEW,
					componentId).loadV8ComboBoxComponentValue(customViewName);
		}
	}
        private void updateCustomViewInDataSelection(boolean isCustomView, GtnWsReportDataSelectionBean dataSelectionBean,
            String componentId) throws GtnFrameworkValidationFailedException {
        if (isCustomView) {
            new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
                    GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_CUSTOM_VIEW,
                    componentId, Arrays.asList(""));
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
                    GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_CUSTOM_VIEW,
                    componentId).loadV8ComboBoxComponentValue(String.valueOf(dataSelectionBean.getCustomViewMasterSid()));
        }
    }

	private void updateVariables(boolean isVariablesChanged, List<Object> variableList,
			GtnWsReportDataSelectionBean dataSelectionBean, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (isVariablesChanged) {
			dataSelectionBean.setVariablesList(variableList);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable", componentId)
					.addAllItemsToMultiSelect(
							Arrays.stream(GtnWsReportVariablesType.values()).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()),
							Arrays.stream(GtnWsReportVariablesType.values()).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable", componentId)
					.updateSelection(variableList);
		}
	}

         private void updateVariablesInDataSelection(boolean isVariablesChanged,
            GtnWsReportDataSelectionBean dataSelectionBean, String componentId)
            throws GtnFrameworkValidationFailedException {
        if (isVariablesChanged) {
            GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_DISPLAY_VARIABLE, componentId)
                    .updateSelection(dataSelectionBean.getVariablesList());
        }
        }
	private void updateFrequency(boolean isFrequencyChanged, GtnWsReportDataSelectionBean dataSelectionBean,
			String frequency, String componentId) throws GtnFrameworkValidationFailedException {
		if (isFrequencyChanged) {
			dataSelectionBean.setFrequency(Integer.valueOf(frequency));
			dataSelectionBean.setFrequencyName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG,
							componentId)
					.getStringCaptionFromV8ComboBox());
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequency));
		}
	}
         private void updateFrequencyInDataSelection(boolean isFrequencyChanged, GtnWsReportDataSelectionBean dataSelectionBean,
            String componentId) throws GtnFrameworkValidationFailedException {
        if (isFrequencyChanged) {
            GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkReportStringConstants.DATA_SELECTION_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG, componentId)
                    .loadV8ComboBoxComponentValue(Integer.valueOf(String.valueOf(dataSelectionBean.getFrequency())));
        }
        }

	private void updateCompany(boolean isCompanyChanged, String company,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		if (isCompanyChanged) {
			dataSelectionBean.setCompanyReport(Integer.valueOf(company));
		}
	}

	private void updateBusinessUnit(boolean isBusinessUnitChanged, String businessUnit,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		if (isBusinessUnitChanged) {
			dataSelectionBean.setBusinessUnitReport(Integer.valueOf(businessUnit));
		}
	}

	private void updateReportDataSource(boolean isReportDataSourceChanged, String reportDataSource,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		if (isReportDataSourceChanged) {
			dataSelectionBean.setReportDataSource(Integer.valueOf(reportDataSource));
		}
	}

	private void updateFromPeriod(boolean isFromPeriodChanged, String fromPeriod,
			GtnWsReportDataSelectionBean dataSelectionBean, String componentId, String fromPeriodValue)
			throws GtnFrameworkGeneralException {
		if (isFromPeriodChanged) {
			dataSelectionBean.setFromPeriodReport(Integer.valueOf(fromPeriod));
			loadPeriodRangeFrom(componentId, fromPeriodValue);
			loadPeriodRangeTo(componentId, fromPeriodValue);
		}
	}

	private void loadPeriodRangeTo(String componentId, String fromPeriodValue) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig toPeriodLoadAction = new GtnUIFrameWorkActionConfig();
		toPeriodLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		toPeriodLoadAction.addActionParameter(GtnFrameworkLoadToInDataSelectionAction.class.getName());
		toPeriodLoadAction.addActionParameter(fromPeriodValue);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, toPeriodLoadAction);
	}

	private void loadPeriodRangeFrom(String componentId, String fromPeriodValue) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig fromPeriodLoadAction = new GtnUIFrameWorkActionConfig();
		fromPeriodLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fromPeriodLoadAction.addActionParameter(GtnFrameworkLoadFromInDataSelectionAction.class.getName());
		fromPeriodLoadAction.addActionParameter(fromPeriodValue);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, fromPeriodLoadAction);
	}

	private void updateComparisonBaasis(List<GtnReportComparisonProjectionBean> comparisonProjectionsList,
			String componentId) {
		int initialCapacity = 4 + comparisonProjectionsList.size();
		List<String> inputForComparisonBasisList = new ArrayList<>(initialCapacity);
		inputForComparisonBasisList.add("Actuals");
		inputForComparisonBasisList.add("Accruals");
		inputForComparisonBasisList.add("Current Projection");
		comparisonProjectionsList.stream().forEach(comparisonProjectionBeans -> inputForComparisonBasisList
				.add(comparisonProjectionBeans.getProjectionName()));
		List idList = IntStream.range(1, initialCapacity).boxed().collect(Collectors.toList());
		GtnUIFrameworkComboBoxConfig comparisonBasisComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId)
				.getComponentConfig().getGtnComboboxConfig();
		comparisonBasisComboboxConfig.setItemCaptionValues(inputForComparisonBasisList);
		comparisonBasisComboboxConfig.setItemValues(idList);

		GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
		combobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				"reportingDashboard_displaySelectionTabComparisonBasis", componentId, Arrays.asList(""));
	}

	private void loadComparisonInReportingDashboard(String sourceComponentId, String componentId,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnUIFrameworkComponentData comparisonData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId, componentId).getComponentData();
		comparisonData.setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
		if (dataSelectionBean.getComparisonProjectionBeanList() != null) {
			String displayValue = getDisplayValue(dataSelectionBean.getComparisonProjectionBeanList());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(sourceComponentId, componentId)
					.setV8PopupFieldValue(displayValue);
		}
	}
        }
