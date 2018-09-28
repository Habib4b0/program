package com.stpl.gtn.gtn2o.registry.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkSaveViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkSaveViewAction.class);

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
		Date date = null;
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnFrameworkForecastDataSelectionBean dataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		
		String company = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setCompany(company == "0" ? 0 : Integer.parseInt(company));
		
		String businessUnit = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString()).getCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setBusinessUnit(businessUnit == "0" ? 0 : Integer.parseInt(businessUnit));
		String projectionName = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(3).toString()).getV8StringFromField())
				.orElseGet(String::new);
		String companyName = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getStringCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setCompanyName(companyName);
		String businessUnitName = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString()).getStringCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setBusinessUnitName(businessUnitName);
		String fromPeriodForecast = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(5).toString()).getStringCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setFromPeriodForecastName(fromPeriodForecast);
		String toPeriodForecast = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(6).toString()).getStringCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setToPeriodForecastName(toPeriodForecast);
		dataSelectionBean.setProjectionName(projectionName == null ? "" : projectionName);
		String projectionDescription = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(4).toString()).getV8StringFromField())
				.orElseGet(String::new);
		dataSelectionBean.setProjectionDescription(projectionDescription == null ? "" : projectionDescription);
		
		dataSelectionBean.setFromPeriodForecast(checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(5).toString()).getCaptionFromV8ComboBox())));
		dataSelectionBean.setToPeriod(checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(6).toString()).getCaptionFromV8ComboBox())));
		GtnWsRecordBean customerHierarchyBean = null;
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(7).toString()).getComponentData()
				.getCustomData() != null)
			customerHierarchyBean = ((GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(7).toString()).getComponentData().getCustomData());
		dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyBean);
		dataSelectionBean.setCustomerRelationshipBuilderSid((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(8).toString()).getCaptionFromV8ComboBox()))));

		dataSelectionBean.setCustomerHierarchyLevel((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(9).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setCustomerHierarchyInnerLevel((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(10).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setCustomerRelationshipVersionNo((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(11).toString()).getCaptionFromV8ComboBox()))));
		LocalDate forecastEligibleDate = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(12).toString()).getFieldValue();
		if (forecastEligibleDate != null) {
			date = Date.from(forecastEligibleDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		dataSelectionBean.setForecastEligibleDate(date);
		
		
		List<GtnWsRecordBean> selectedCustomerList = getSelectedList(actionParamsList.get(13).toString(), componentId);
		

		dataSelectionBean.setSelectedCustomerHierarchyList(selectedCustomerList);
		
		

		GtnWsRecordBean productHierarchyBean = null;
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(14).toString()).getComponentData()
				.getCustomData() != null)
			productHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(14).toString()).getComponentData().getCustomData();
		dataSelectionBean.setProductHierarchyRecordBean(productHierarchyBean);
		dataSelectionBean.setProductRelationshipBuilderSid((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(15).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setProductRelationshipVersionNo((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(16).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setProductHierarchyLevel((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(17).toString()).getCaptionFromV8ComboBox()))));
        dataSelectionBean.setProductHierarchyInnerLevel((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(18).toString()).getCaptionFromV8ComboBox()))));
		List<GtnWsRecordBean> selectedProductList = getSelectedList(actionParamsList.get(19).toString(), componentId);
		dataSelectionBean.setSelectedProductHierarchyList(selectedProductList);
		String frequency = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(20).toString()).getCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setFrequency(frequency == "0" ? 0 : Integer.parseInt(frequency));
		GtnWsRecordBean customerGroup = ((GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(21).toString()).getComponentData().getCustomData());
	dataSelectionBean.setCustomerGroup(customerGroup);
	GtnWsRecordBean productGroup = ((GtnWsRecordBean) GtnUIFrameworkGlobalUI
			.getVaadinBaseComponent(actionParamsList.get(22).toString()).getComponentData().getCustomData());
    dataSelectionBean.setProductGroup(productGroup);
		String privateViewName = Optional
				.ofNullable(String.valueOf(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("Commercial Forecasting_privateViewLookup").getV8PopupFieldValue()))
				.orElseGet(String::new);
		dataSelectionBean.setPrivateViewName(StringUtils.isBlank(privateViewName) ? null : privateViewName);
		String publicViewName = Optional
				.ofNullable(String.valueOf(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("Commercial Forecasting_publicView").getV8PopupFieldValue()))
				.orElseGet(String::new);
		dataSelectionBean.setPublicViewName(StringUtils.isBlank(publicViewName) ? null : publicViewName);

		
		GtnUIFrameWorkActionConfig popupAction = new GtnUIFrameWorkActionConfig();
		popupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> params = new ArrayList<>();
	
		params.add("forecastDsSaveViewLookUp");
		params.add("Save View");
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(dataSelectionBean);
		popupAction.setActionParameterList(params);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, popupAction);
		gtnLogger.info("privateViewName--------->" + privateViewName);
		gtnLogger.info("publicViewName----------->" + publicViewName);
		}
		catch(Exception e)
		{
			gtnLogger.error("Exception", e);
		}

	}

	@SuppressWarnings("rawtypes")
	private Integer checkIfNotNull(Optional input) {
		return input.isPresent() && !"".equals(input.get().toString()) ? Integer.valueOf(input.get().toString()) : null;
	}


	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId) {
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		if (gtnUIFrameworkComponentData.getCustomData() == null)
			return Collections.emptyList();
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		rightTable.expand(rightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedvalues = rightTable.getTreeData().getRootItems();

		List<GtnWsRecordBean> selectedList = new ArrayList<>(10);
		for (GtnWsRecordBean gtnWsRecordBean : selectedvalues) {

			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
		return selectedList;
	}

	private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedVales,
			List<GtnWsRecordBean> selectedList) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedVales)) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
