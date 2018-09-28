package com.stpl.gtn.gtn2o.registry.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkForecastingCCPTableLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass{

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastingCCPTableLoadAction.class);
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();

		List<GtnWsRecordBean> selectedCustomerList = null;
		List<GtnWsRecordBean> selectedProductList = null;
		GtnFrameworkForecastDataSelectionBean dataSelectionDto = null;
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
			logger.error("Error in ", ex);
			return;
		}

		GtnUIFrameWorkActionConfig gtnUIFrameWorkGeneratePopupAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGeneratePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> params = new ArrayList<>();
		params.add("forecastGenerateLookupView");
		params.add("Forecast Generate Lookup View");
		params.add(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		params.add(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		params.add(null);
		params.add(dataSelectionDto);
		gtnUIFrameWorkGeneratePopupAction.setActionParameterList(params);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameWorkGeneratePopupAction);
	}

	private GtnFrameworkForecastDataSelectionBean getDataSelectionBean(List<Object> actionParamList, String componentId)  {
		List<GtnWsRecordBean> selectedCustomerList = null;
		List<GtnWsRecordBean> selectedProductList = null;
		GtnFrameworkForecastDataSelectionBean dataSelectionDto = null;
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
			methodForSingleExcecution(componentId, alertAction);
			logger.error("Error in ", ex);
		}
		return dataSelectionDto;
		
	}

	private void methodForSingleExcecution(String componentId, GtnUIFrameWorkActionConfig alertAction) {
		try {
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
		} catch (GtnFrameworkGeneralException e) {
			logger.info("Exception " + e);
		}
		
	}

	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId)
			throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData tableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) tableComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		rightTable.expand(rightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedValues = rightTable.getTreeData().getRootItems();
		if (selectedValues == null || selectedValues.isEmpty()) {
			throw new GtnFrameworkValidationFailedException("Selected Table is Empty");
		}
		List<GtnWsRecordBean> recordList = new ArrayList<>(10);
		for (GtnWsRecordBean gtnWsRecordBean : selectedValues) {

			recordList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, recordList);
		}
		return recordList;
	}
	
	private GtnFrameworkForecastDataSelectionBean getDataSelectionDto(List<Object> actionParamList,
			List<GtnWsRecordBean> selectedCustomerList, List<GtnWsRecordBean> selectedProductList, String componentId)
			throws GtnFrameworkValidationFailedException {

		GtnFrameworkForecastDataSelectionBean dto = new GtnFrameworkForecastDataSelectionBean();
		Date forecastEligibleDate = null;

		String hierarchyComponentId = actionParamList.get(3).toString();
		String relationshipComponentId = actionParamList.get(4).toString();
		GtnWsRecordBean customerRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(hierarchyComponentId).getComponentData().getCustomData();
		GtnWsRecordBean productRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(8).toString()).getComponentData().getCustomData();
		
		GtnWsRecordBean customerGroupRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(hierarchyComponentId).getComponentData().getCustomData();
		GtnWsRecordBean productGroupRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(8).toString()).getComponentData().getCustomData();

		LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(7).toString())
				.getFieldValue();
		if (date != null) {
			forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}

		generateButtonMandatoryCheck(dto, actionParamList, customerRecordBean, productRecordBean,
				relationshipComponentId);

		dto.setForecastEligibleDate(forecastEligibleDate);

		
		dto.setFrequency(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString())
				.getIntegerFromV8ComboBox());
		dto.setFrequencyName(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString())
				.getStringCaptionFromV8ComboBox());

		String privateView = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(26).toString(), componentId).getV8PopupFieldValue());
		if (!"".equals(privateView)) {
			dto.setPrivateViewName(privateView);
		}
		String publicViewName = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(27).toString(), componentId).getV8PopupFieldValue());
		if (!"".equals(publicViewName)) {
			dto.setPublicViewName(publicViewName);
		}
		dto.setCustomerHierarchyRecordBean(customerRecordBean);
		dto.setProductHierarchyRecordBean(productRecordBean);
		dto.setSelectedCustomerHierarchyList(selectedCustomerList);
		dto.setSelectedProductHierarchyList(selectedProductList);
		dto.setCustomerGroup(customerGroupRecordBean);
		dto.setProductGroup(productGroupRecordBean);
		
		dto.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		String uniqueId = UUID.randomUUID().toString().replaceAll("-", "_").substring(0, 12);
		dto.setSessionId(uniqueId);
		dto.setUniqueId(uniqueId);

		return dto;
	}
	
	private void generateButtonMandatoryCheck(GtnFrameworkForecastDataSelectionBean dto, List<Object> actionParamList,
			GtnWsRecordBean customerRecordBean, GtnWsRecordBean productRecordBean, String relationshipComponentId)
			throws GtnFrameworkValidationFailedException {
		dto.setCompany(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(12).toString()).getCaptionFromV8ComboBox())));
		dto.setBusinessUnit(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(13).toString()).getCaptionFromV8ComboBox())));
		dto.setFromPeriodForecast(checkDDLBValues(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(14).toString()).getIntegerFromV8ComboBox()));
		dto.setToPeriod(checkDDLBValues(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(15).toString()).getIntegerFromV8ComboBox()));
		dto.setCustomerHierarchySid(checkDDLBValuesWrapper(Integer.valueOf(String
				.valueOf(customerRecordBean.getPropertyValueByIndex(7)))));
		dto.setCustomerRelationshipBuilderSid(checkDDLBValues(Integer.parseInt(String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(relationshipComponentId).getCaptionFromV8ComboBox()))));
		dto.setCustomerRelationshipVersionNo(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(28).toString()).getCaptionFromV8ComboBox()))));
		dto.setCustomerHierarchyLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(20).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductHierarchySid(checkDDLBValuesWrapper(Integer.valueOf(String
				.valueOf(productRecordBean.getPropertyValueByIndex(7)))));
		dto.setProductRelationshipBuilderSid(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(9).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductRelationshipVersionNo(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(29).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductHierarchyLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(10).toString()).getCaptionFromV8ComboBox()))));
		dto.setProductHierarchyInnerLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(21).toString()).getCaptionFromV8ComboBox()))));
		dto.setProjectionName(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(16).toString()).getV8StringFromField());
		dto.setProjectionDescription(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(17).toString()).getV8StringFromField());
		dto.setCustomerHierarchyInnerLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(20).toString()).getCaptionFromV8ComboBox()))));
		dto.setDeductionLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(19).toString()).getCaptionFromV8ComboBox()))));
		
	}
	
	private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedvalues,
			List<GtnWsRecordBean> selectedList) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedvalues)) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
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
