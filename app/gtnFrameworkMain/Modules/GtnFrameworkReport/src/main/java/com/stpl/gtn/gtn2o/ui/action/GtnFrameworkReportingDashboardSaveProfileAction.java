package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportProfileDataToPojoAction;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

public class GtnFrameworkReportingDashboardSaveProfileAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportingDashboardSaveProfileAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info("loading configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean = new GtnReportingDashboardSaveProfileLookupBean();

			List<Object> displaySelectionTabVariable = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(1).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean.setDisplaySelectionTabVariable(displaySelectionTabVariable);

			String displaySelectionTabPeriodRangeFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(2).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setDisplaySelectionTabPeriodRangeFrom(
					displaySelectionTabPeriodRangeFrom.equals("") ? null : displaySelectionTabPeriodRangeFrom);

			String displaySelectionTabPeriodRangeTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(3).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setDisplaySelectionTabPeriodRangeTo(
					displaySelectionTabPeriodRangeTo.equals("") ? null : displaySelectionTabPeriodRangeTo);

			String displaySelectionTabFrequency = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(4).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setDisplaySelectionTabFrequency(displaySelectionTabFrequency);

			String displaySelectionTabComparisonBasis = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(5).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean
					.setDisplaySelectionTabComparisonBasis(displaySelectionTabComparisonBasis);

			String displaySelectionTabCustomViewCombobox = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(6).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setDisplaySelectionTabCustomViewCombobox(
					Optional.ofNullable(displaySelectionTabCustomViewCombobox).isPresent()
							? displaySelectionTabCustomViewCombobox : "0");

			List<Object> displaySelectionTabVariableCategory = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(7).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean
					.setDisplaySelectionTabVariableCategory(displaySelectionTabVariableCategory);

			String displaySelectionTabAnnualTotals = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(8).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setDisplaySelectionTabAnnualTotals(displaySelectionTabAnnualTotals);

			String filterOptionsTabCustomerLevel = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(9).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabCustomerLevel(
					filterOptionsTabCustomerLevel.equals("") ? null : filterOptionsTabCustomerLevel);

			List<Object> filterOptionsTabCustomerFilter = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(10).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabCustomerFilter(filterOptionsTabCustomerFilter);

			String filterOptionsTabProductLevel = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(11).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabProductLevel(
					filterOptionsTabProductLevel.equals("") ? null : filterOptionsTabProductLevel);

			List<Object> filterOptionsTabProductFilter = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(12).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabProductFilter(filterOptionsTabProductFilter);

			String filterOptionsTabDeductionLevel = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(13).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabDeductionLevel(
					filterOptionsTabDeductionLevel.equals("") ? null : filterOptionsTabDeductionLevel);

			List<Object> filterOptionsTabDeductionFilter = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(14).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabDeductionFilter(filterOptionsTabDeductionFilter);

			List<Object> filterOptionsTabSalesInclusion = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(15).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean.setFilterOptionsTabSalesInclusion(filterOptionsTabSalesInclusion);

			List<Object> filterOptionsTabDeductionInclusion = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(16).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean
					.setFilterOptionsTabDeductionInclusion(filterOptionsTabDeductionInclusion);

			String reportOptionsVariableAndVarianceSequencing = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(17).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean
					.setReportOptionsVariableAndVarianceSequencing(reportOptionsVariableAndVarianceSequencing);

			String reportOptionsHeaderSequencing = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(18).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setReportOptionsHeaderSequencing(reportOptionsHeaderSequencing);

			String reportOptionsViewOptions = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(19).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setReportOptionsViewOptions(reportOptionsViewOptions);

			List<Object> reportOptionsDisplayFormat = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(20).toString(), componentId)
					.getSelectedListFromV8MultiSelect();
			reportingDashboardSaveProfileLookupBean.setReportOptionsDisplayFormat(reportOptionsDisplayFormat);

			String reportOptionsUnitsOfMeasure = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(21).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setReportOptionsUnitsOfMeasure(
					reportOptionsUnitsOfMeasure.equals("") ? null : reportOptionsUnitsOfMeasure);

			String reportOptionsCurrencyDisplay = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(22).toString(), componentId).getCaptionFromV8ComboBox();
			reportingDashboardSaveProfileLookupBean.setReportOptionsCurrencyDisplay(reportOptionsCurrencyDisplay);

			String sourceParentComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
			GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(sourceParentComponentId).getComponentData().getSharedPopupData();

			List<GtnReportComparisonProjectionBean> displaySelectionComparisonLookupBeanList = reportDataSelectionBean
					.getComparisonProjectionBeanList();

			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(23).toString(), componentId)
					.getComponentData().getCustomData() != null) {
				displaySelectionComparisonLookupBeanList = (List<GtnReportComparisonProjectionBean>) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(actionParamList.get(23).toString(), componentId).getComponentData()
						.getCustomData();
			}

			reportingDashboardSaveProfileLookupBean = new GtnReportProfileDataToPojoAction()
					.saveDataSelectionDataToReportProfilePojo(reportingDashboardSaveProfileLookupBean, componentId);

			reportingDashboardSaveProfileLookupBean
					.setDisplaySelectionComparisonLookupBeanList(displaySelectionComparisonLookupBeanList);

			List<GtnReportVariableBreakdownLookupBean> variableBreakdownLookupBeanSaveList = reportDataSelectionBean
					.getVariableBreakdownSaveList();

			reportingDashboardSaveProfileLookupBean
					.setVariableBreakdownLookupBeanSaveList(variableBreakdownLookupBeanSaveList);

			List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownLookupBeanSaveList = reportDataSelectionBean
					.getComparisonBreakdownSaveList();

			reportingDashboardSaveProfileLookupBean
					.setComparisonBreakdownLookupBeanSaveList(comparisonBreakdownLookupBeanSaveList);

			GtnUIFrameWorkActionConfig saveProfileAction = new GtnUIFrameWorkActionConfig();
			saveProfileAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
			List<Object> params = new ArrayList<>();
			params.add("saveProfileViewLookUp");
			params.add("Save View Popup");
			params.add("500px");
			params.add("260px");
			params.add(null);
			params.add(reportingDashboardSaveProfileLookupBean);

			saveProfileAction.setActionParameterList(params);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, saveProfileAction);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportDashboardSaveProfile_reportDashboardSaveProfileSaveViewUpdate")
					.setEnable(false);

			GtnWsRecordBean recordBean = Optional.ofNullable(GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent("reportingDashboardTab_reportProfileConfig", componentId)
							.getComponentData().getCustomData()).isPresent()
					
							? (GtnWsRecordBean) GtnUIFrameworkGlobalUI
									.getVaadinBaseComponent("reportingDashboardTab_reportProfileConfig", componentId)
									.getComponentData().getCustomData()
							: null;
			if (recordBean != null) {

				reportingDashboardSaveProfileLookupBean.setRecordBean(recordBean);
				reportingDashboardSaveProfileLookupBean
					.setReportProfileViewId(Integer.valueOf(String.valueOf(recordBean.getPropertyValueByIndex(4))));					
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("reportDashboardSaveProfile_ReportDashboardSaveProfileNameTextField")
						.loadV8FieldValue(recordBean.getPropertyValueByIndex(0));
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("reportDashboardSaveProfile_reportDashboardSaveProfileSaveViewUpdate")
						.setEnable(true);
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("reportDashboardSaveProfile_reportDashboardSaveProfileSaveViewAdd")
						.setEnable(false);
			}

		} catch (Exception ex) {
			logger.error("Error message ", ex);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
