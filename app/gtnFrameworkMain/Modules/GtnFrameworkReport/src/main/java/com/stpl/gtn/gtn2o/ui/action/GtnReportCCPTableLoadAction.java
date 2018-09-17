package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TreeGrid;

public class GtnReportCCPTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();

		List<GtnWsRecordBean> selectedCustomerList = null;
		List<GtnWsRecordBean> selectedProductList = null;
		GtnWsReportDataSelectionBean dataSelectionDto = null;
		try {
			selectedCustomerList = getSelectedList(actionParamList.get(1).toString(), componentId);
			selectedProductList = getSelectedList(actionParamList.get(2).toString(), componentId);
			dataSelectionDto = getDataSelectionDto(actionParamList, selectedCustomerList, selectedProductList,
					componentId);
		} catch (Exception ex) {
			GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
			alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			alertAction.addActionParameter("Error");
			alertAction.addActionParameter("Not all required fields have been populated. Please try again.");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
			return;
		}
		ccpHierarchyInsert(selectedCustomerList, selectedProductList, dataSelectionDto);

		GtnUIFrameWorkActionConfig gtnUIFrameWorkGeneratePopupAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGeneratePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> params = new ArrayList<>(6);
		params.add(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		params.add(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD);
		params.add(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		params.add(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		params.add(null);
		params.add(dataSelectionDto);
		params.add("v-position-fixed");
		gtnUIFrameWorkGeneratePopupAction.setActionParameterList(params);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameWorkGeneratePopupAction);

		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_reportingDashboardComparisonConfig", componentId)
				.getComponentData();
		List<GtnReportComparisonProjectionBean> comparisonLookupBeanList = (List<GtnReportComparisonProjectionBean>) idComponentData
				.getCustomData();

		int initialCapacity = 4 + (comparisonLookupBeanList == null ? 0 : comparisonLookupBeanList.size());
		List<String> inputForComparisonBasisList = new ArrayList<>(initialCapacity);

		inputForComparisonBasisList.add("Actuals");
		inputForComparisonBasisList.add("Accruals");
		inputForComparisonBasisList.add("Current Projection");
		Optional.ofNullable(comparisonLookupBeanList).ifPresent(e -> {
			for (GtnReportComparisonProjectionBean comparisonProjectionBeans : e) {
				inputForComparisonBasisList.add(comparisonProjectionBeans.getProjectionName());
			}
		});
		List idList = IntStream.range(1, initialCapacity).boxed().collect(Collectors.toList());

		GtnUIFrameworkComboBoxConfig comparisonBasisComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromChild("reportingDashboard_displaySelectionTabComparisonBasis", componentId)
				.getComponentConfig().getGtnComboboxConfig();
		comparisonBasisComboboxConfig.setItemCaptionValues(inputForComparisonBasisList);
		comparisonBasisComboboxConfig.setItemValues(idList);

		GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
		combobox.reloadComponentFromChild("reportingDashboard_displaySelectionTabComparisonBasis", componentId,
				Arrays.asList(""));
	}

	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId)
			throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData selectedTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		GtnFrameworkV8DualListBoxBean selectedDualListBoxBean = (GtnFrameworkV8DualListBoxBean) selectedTableComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> selectedRightTable = selectedDualListBoxBean.getRightTable();
		selectedRightTable.expand(selectedRightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedValues = selectedRightTable.getTreeData().getRootItems();
		if (selectedValues == null || selectedValues.isEmpty()) {
			throw new GtnFrameworkValidationFailedException("Selected Table is Empty");
		}
		List<GtnWsRecordBean> selectedRecordList = new ArrayList<>(10);
		for (GtnWsRecordBean gtnWsRecordBean : selectedValues) {

			selectedRecordList.add(gtnWsRecordBean);
			addSelectedValues(selectedRightTable, gtnWsRecordBean, selectedRecordList);
		}
		return selectedRecordList;
	}

	private GtnWsReportDataSelectionBean getDataSelectionDto(List<Object> actionParamList,
			List<GtnWsRecordBean> selectedCustomerList, List<GtnWsRecordBean> selectedProductList, String componentId)
			throws GtnFrameworkValidationFailedException {

		GtnWsReportDataSelectionBean dto = new GtnWsReportDataSelectionBean();
		Date forecastEligibleDate = null;

		String hierarchyComponentId = actionParamList.get(3).toString();
		String relationshipComponentId = actionParamList.get(4).toString();
		GtnWsRecordBean customerRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(hierarchyComponentId).getComponentData().getCustomData();
		GtnWsRecordBean productRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(8).toString()).getComponentData().getCustomData();

		LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(7).toString())
				.getFieldValue();
		if (date != null) {
			forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}

		generateButtonMandatoryCheck(dto, actionParamList, customerRecordBean, productRecordBean,
				relationshipComponentId);

		dto.setForecastEligibleDate(forecastEligibleDate);

		GtnUIFrameworkComponentData comparisonProjectionData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParamList.get(16).toString());
		List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = (List<GtnReportComparisonProjectionBean>) comparisonProjectionData
				.getCustomData();
		dto.setComparisonProjectionBeanList(comparisonProjectionBeanList);
		dto.setCustomViewMasterSid(Integer.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(17).toString()).getCaptionFromV8ComboBox()));
		dto.setFrequency(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString())
				.getIntegerFromV8ComboBox());
		dto.setFrequencyName(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString())
				.getStringCaptionFromV8ComboBox());
		dto.setVariablesList(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(22).toString())
				.getSelectedListFromV8MultiSelect());
		String privateView = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(23).toString(), componentId).getV8PopupFieldValue());
		if (privateView != "") {
			dto.setPrivateViewName(privateView);
		}
		String publicViewName = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(24).toString(), componentId).getV8PopupFieldValue());
		if (publicViewName != "") {
			dto.setPublicViewName(publicViewName);
		}
		dto.setCustomerHierarchyRecordBean(customerRecordBean);
		dto.setProductHierarchyRecordBean(productRecordBean);
		dto.setSelectedCustomerHierarchyList(selectedCustomerList);
		dto.setSelectedProductHierarchyList(selectedProductList);

		dto.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		String uniqueId = UUID.randomUUID().toString().replaceAll("-", "_").substring(0, 12);
		dto.setSessionId(uniqueId);
		dto.setUniqueId(uniqueId);

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponent(actionParamList.get(21).toString(), componentId);
		if (abstractComponent != null && abstractComponent.getData() != null) {
			GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
			if (gridComponent.getCustomData() instanceof List) {
				List<GtnReportVariableBreakdownLookupBean> gtnReportVariableBreakdownLookupBeanList = (List<GtnReportVariableBreakdownLookupBean>) gridComponent
						.getCustomData();
				dto.setVariableBreakdownSaveList(gtnReportVariableBreakdownLookupBeanList);
				dto.setCustomDataList(gridComponent.getCustomDataList());
			}
		}

		return dto;
	}

	private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedvalues,
			List<GtnWsRecordBean> selectedList) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedvalues)) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
	}

	public void ccpHierarchyInsert(List<GtnWsRecordBean> selectedCustomerContractList,
			List<GtnWsRecordBean> selectedProductList, GtnWsReportDataSelectionBean dataSelectionDto) {
		GtnForecastHierarchyInputBean inputBean = createInputBeanForCCPInsert(selectedCustomerContractList,
				selectedProductList, dataSelectionDto);
		insertToCCp(inputBean, dataSelectionDto);
	}

	private GtnForecastHierarchyInputBean createInputBeanForCCPInsert(
			final List<GtnWsRecordBean> selectedCustomerContractList, final List<GtnWsRecordBean> selectedProductList,
			GtnWsReportDataSelectionBean dataSelectionDto) {

		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedCustomerList(relationBeanList(selectedCustomerContractList, dataSelectionDto, false));
		inputBean.setSelectedProductList(relationBeanList(selectedProductList, dataSelectionDto, true));
		inputBean.setSelectedCustomerRelationShipBuilderVersionNo(dataSelectionDto.getCustomerRelationshipVersionNo());
		inputBean.setSelectedProductRelationShipBuilderVersionNo(dataSelectionDto.getProductRelationshipVersionNo());
		inputBean.setSelectedCustomerHierarcySid((int) dataSelectionDto.getCustomerHierarchySid());
		inputBean.setSelectedProductHierarcySid((int) dataSelectionDto.getProductHierarchySid());
		inputBean.setSelectedProductRelationShipBuilderSid(dataSelectionDto.getProductRelationshipBuilderSid());
		inputBean.setSelectedCustomerHierarchyVersionNo(dataSelectionDto.getCustomerHierarchyVersionNo());
		inputBean.setSelectedProductHierarchyVersionNo(dataSelectionDto.getProductHierarchyVersionNo());
		inputBean.setForecastEligibleDate(dataSelectionDto.getForecastEligibleDate());
		inputBean.setForecastInsert(true);
		return inputBean;
	}

	private void insertToCCp(GtnForecastHierarchyInputBean inputBean, GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		GtnWsReportBean reportBeanRequest = new GtnWsReportBean();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		reportRequest.setReportBean(reportBeanRequest);
		reportBeanRequest.setDataSelectionBean(dataSelectionBean);
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		request.setGtnWsReportRequest(reportRequest);
		request.setGtnWsGeneralRequest(generalRequest);
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_WS_DATA_SELECTION_GENERATE_SERVICE, "report", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		dataSelectionBean.setSessionTableMap(
				response.getGtnWsReportResponse().getReportBean().getDataSelectionBean().getSessionTableMap());

	}

	private List<GtnFrameworkRelationshipLevelDefintionBean> relationBeanList(
			List<GtnWsRecordBean> selectedCustomerContractList, GtnWsReportDataSelectionBean dataSelectionDto,
			boolean isProduct) {
		List<GtnFrameworkRelationshipLevelDefintionBean> relationBeanList = new ArrayList<>(
				selectedCustomerContractList.size());
		for (GtnWsRecordBean recordBean : selectedCustomerContractList) {

			GtnFrameworkRelationshipLevelDefintionBean forecast = new GtnFrameworkRelationshipLevelDefintionBean();
			forecast.setLevelName(recordBean.getStringProperty("levelName"));
			forecast.setLevelNo(recordBean.getIntegerProperty("levelNo"));
			forecast.setRelationshipLevelSid(recordBean.getIntegerProperty("relationshipLevelSid"));
			forecast.setRelationShipLevelValue(recordBean.getIntegerProperty("relationshipLevelValues"));
			forecast.setTableName(recordBean.getStringProperty("tableName"));
			forecast.setFieldName(recordBean.getStringProperty("fieldName"));
			forecast.setHierarchyNo(recordBean.getStringProperty("hierarchyNo"));
			forecast.setHierarchyDefinitionSid(recordBean.getIntegerProperty("hierarchyDefSid"));
			forecast.setHierarchyLevelDefinitionSid(recordBean.getIntegerProperty("hierarchyLevelDefSid"));
			forecast.setLevelValueReference(recordBean.getStringProperty("levelValuReference"));
			forecast.setRelationshipBuilderSid(recordBean.getIntegerProperty("relationshipBuilderSid"));
			forecast.setRelationshipVersionNo(!isProduct ? dataSelectionDto.getCustomerRelationshipVersionNo()
					: dataSelectionDto.getProductRelationshipVersionNo());
			forecast.setHierarchyVersionNo(!isProduct ? dataSelectionDto.getCustomerHierarchyVersionNo()
					: dataSelectionDto.getProductHierarchyVersionNo());
			forecast.setHierarchyCategory(recordBean.getStringProperty("hierarchyCategory"));
			relationBeanList.add(forecast);

		}
		return relationBeanList;
	}

	private void generateButtonMandatoryCheck(GtnWsReportDataSelectionBean dto, List<Object> actionParamList,
			GtnWsRecordBean customerRecordBean, GtnWsRecordBean productRecordBean, String relationshipComponentId)
			throws GtnFrameworkValidationFailedException {
		dto.setCompanyReport(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(13).toString()).getCaptionFromV8ComboBox())));
		dto.setBusinessUnitReport(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(14).toString()).getCaptionFromV8ComboBox())));
		dto.setReportDataSource(checkDDLBValues(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(12).toString()).getIntegerFromV8ComboBox()));
		dto.setFromPeriodReport(checkDDLBValues(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(15).toString()).getIntegerFromV8ComboBox()));
		dto.setToPeriod(checkDDLBValues(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(20).toString()).getIntegerFromV8ComboBox()));
		dto.setCustomerHierarchySid(checkDDLBValuesWrapper(Integer.valueOf(String
				.valueOf(customerRecordBean.getPropertyValueByIndex(customerRecordBean.getProperties().size() - 1)))));
		dto.setCustomerHierarchyVersionNo(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(5).toString()).getStringCaptionFromV8ComboBox())));
		dto.setCustomerRelationshipBuilderSid(checkDDLBValues(Integer.parseInt(String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(relationshipComponentId).getCaptionFromV8ComboBox()))));
		dto.setCustomerRelationshipVersionNo(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(5).toString()).getCaptionFromV8ComboBox()))));
		dto.setCustomerHierarchyForecastLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(6).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductHierarchySid(checkDDLBValuesWrapper(Integer.valueOf(String
				.valueOf(productRecordBean.getPropertyValueByIndex(productRecordBean.getProperties().size() - 1)))));
		dto.setProductHierarchyVersionNo(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(11).toString()).getStringCaptionFromV8ComboBox())));
		dto.setProductRelationshipBuilderSid(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(9).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductRelationshipVersionNo(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(11).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductHierarchyForecastLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(10).toString()).getCaptionFromV8ComboBox()))));
	}

	private int checkDDLBValues(int value) throws GtnFrameworkValidationFailedException {
		if (value == 0) {
			throw new GtnFrameworkValidationFailedException("Generate Validation Exception");
		}
		return value;
	}

	private Integer checkDDLBValuesWrapper(Integer value) throws GtnFrameworkValidationFailedException {
		if (value == 0) {
			throw new GtnFrameworkValidationFailedException("Generate Validation Exception");
		}
		return value;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
