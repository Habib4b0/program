package com.stpl.gtn.gtn2o.ui.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;

public class GtnReportingDashboardReportProfileLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportingDashboardReportProfileLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean = new GtnReportingDashboardSaveProfileLookupBean();
			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String viewId = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).getParentViewId();
			GtnUIFrameworkBaseComponent reportBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(1).toString(), viewId);
			GtnWsRecordBean recordBean = (GtnWsRecordBean) reportBaseComponent.getComponentData().getCustomData();
			String viewData = (String) recordBean.getPropertyValueByIndex(5);

			reportProfileSaveLookupBean = (GtnReportingDashboardSaveProfileLookupBean) convertJsonToObject(
					GtnReportingDashboardSaveProfileLookupBean.class, viewData.replaceAll("\\\\", "'"));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(2).toString(), viewId)
					.updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabVariable())
							.isPresent() == true ? reportProfileSaveLookupBean.getDisplaySelectionTabVariable()
									: new ArrayList<>());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabFrequency())
									.isPresent() == true
											? Integer.valueOf(
													reportProfileSaveLookupBean.getDisplaySelectionTabFrequency())
											: 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(6).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabComparisonBasis())
									.isPresent() == true
											? Integer.valueOf(
													reportProfileSaveLookupBean.getDisplaySelectionTabComparisonBasis())
											: 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(7).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabCustomViewCombobox())
									.isPresent() == true
											? reportProfileSaveLookupBean.getDisplaySelectionTabCustomViewCombobox()
											: "-Select one-");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(8).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory())
							.isPresent() == true ? reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory()
									: new ArrayList<>());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(9).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabAnnualTotals())
									.isPresent() == true
											? reportProfileSaveLookupBean.getDisplaySelectionTabAnnualTotals() : "Yes");
			setPeriodRangeFromAndTo(reportProfileSaveLookupBean, actionParamList, viewId);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(10).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabCustomerLevel())
									.isPresent() == true
											? reportProfileSaveLookupBean.getFilterOptionsTabCustomerLevel() : "0");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(11).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabCustomerFilter())
							.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabCustomerFilter()
									: new ArrayList<>());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(12).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabProductLevel())
									.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabProductLevel()
											: "0");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(13).toString(), viewId)
					.updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabProductFilter())
							.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabProductFilter()
									: new ArrayList<>());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(14).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionLevel())
									.isPresent() == true
											? reportProfileSaveLookupBean.getFilterOptionsTabDeductionLevel() : "0");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(15).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionFilter())
							.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabDeductionFilter()
									: new ArrayList<>());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(16).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabSalesInclusion())
							.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabSalesInclusion()
									: new ArrayList<>());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(17).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionInclusion())
							.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabDeductionInclusion()
									: new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString(), viewId)
					.loadV8ComboBoxComponentValue(Optional
							.ofNullable(reportProfileSaveLookupBean.getReportOptionsVariableAndVarianceSequencing())
							.isPresent() == true
									? Integer.valueOf(
											reportProfileSaveLookupBean.getReportOptionsVariableAndVarianceSequencing())
									: 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(19).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsHeaderSequencing())
									.isPresent() == true
											? Integer.valueOf(
													reportProfileSaveLookupBean.getReportOptionsHeaderSequencing())
											: 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(20).toString(), viewId)
					.loadV8ComboBoxComponentValue(Optional
							.ofNullable(reportProfileSaveLookupBean.getReportOptionsViewOptions()).isPresent() == true
									? Integer.valueOf(reportProfileSaveLookupBean.getReportOptionsViewOptions()) : 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(21).toString(), viewId)
					.updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsDisplayFormat())
							.isPresent() == true ? reportProfileSaveLookupBean.getReportOptionsDisplayFormat()
									: new ArrayList<>());
			// GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(22).toString(),viewId).updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsUnitsOfMeasure()).isPresent()
			// == true
			// ?reportProfileSaveLookupBean.getReportOptionsUnitsOfMeasure():new
			// ArrayList<>() );
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(23).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsCurrencyDisplay())
									.isPresent() == true
											? Integer.valueOf(
													reportProfileSaveLookupBean.getReportOptionsCurrencyDisplay())
											: 0);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId).getComponentData()
					.setCustomData(Optional
							.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList())
							.isPresent() == true
									? reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList()
									: new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId)
					.setV8PopupFieldValue(
							Optional.ofNullable(getDisplayValue(reportProfileSaveLookupBean)).isPresent() == true
									? getDisplayValue(reportProfileSaveLookupBean) : "");

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(25).toString(), viewId).getComponentData()
					.setCustomData(
							Optional.ofNullable(reportProfileSaveLookupBean.getVariableBreakdownLookupBeanSaveList())
									.isPresent() == true
											? reportProfileSaveLookupBean.getVariableBreakdownLookupBeanSaveList()
											: new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(26).toString(), viewId).getComponentData()
					.setCustomData(
							Optional.ofNullable(reportProfileSaveLookupBean.getComparisonBreakdownLookupBeanSaveList())
									.isPresent() == true
											? reportProfileSaveLookupBean.getComparisonBreakdownLookupBeanSaveList()
											: new ArrayList<>());

		} catch (Exception ex) {
			gtnLogger.error("Error message", ex);
		}
	}

	private void setPeriodRangeFromAndTo(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean,
			List<Object> actionParamList, String viewId) throws GtnFrameworkValidationFailedException {
		if (reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeFrom() != null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(3).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Integer.valueOf(reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeFrom()));

		}
		if (reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeTo() != null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Integer.valueOf(reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeTo()));

		}
	}

	private GtnReportingDashboardSaveProfileLookupBean convertJsonToObject(
			Class<GtnReportingDashboardSaveProfileLookupBean> reportProfileSaveLookupBean, String viewData)
			throws JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(viewData, reportProfileSaveLookupBean);
	}

	private Object getDisplayValue(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean) {
		if (reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList() == null) {
			return null;
		}

		if (reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList().size() > 1) {
			return "MULTIPLE";
		} else {
			return reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList().get(0).getProjectionName();
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
