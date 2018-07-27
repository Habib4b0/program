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
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportProfileDataToPojoAction;
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
		
		gtnLogger.info("Inside GtnReportingDashboardReportProfileLoadAction");
		
		try {
			gtnLogger.info("1");
			
			GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean = new GtnReportingDashboardSaveProfileLookupBean();
			
			gtnLogger.info("2");
			
			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String viewId = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).getParentViewId();
			
			gtnLogger.info("3 view id: " + viewId);
			
			GtnUIFrameworkBaseComponent reportBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(1).toString(), viewId);
			
			gtnLogger.info("4");
			gtnLogger.info("5 reportBaseComponent: " + reportBaseComponent);
			gtnLogger.info("7");
			
			GtnWsRecordBean recordBean = (GtnWsRecordBean) reportBaseComponent.getComponentData().getCustomData();
			
			gtnLogger.info("8");
			gtnLogger.info("9 recordBean" + recordBean);
			gtnLogger.info("10");
			
			String viewData = (String) recordBean.getPropertyValueByIndex(5);
			
			gtnLogger.info("11");
			gtnLogger.info("12 viewData: " + viewData);
			gtnLogger.info("13");

			GtnReportingDashboardSaveProfileLookupBean currentViewData = new GtnReportingDashboardSaveProfileLookupBean();
			
			gtnLogger.info("14");
			gtnLogger.info("15 currentViewdata: " + currentViewData);
			gtnLogger.info("16");

			currentViewData = new GtnReportProfileDataToPojoAction()
					.saveDataSelectionDataToReportProfilePojo(currentViewData, viewId);
			
			gtnLogger.info("17");
			gtnLogger.info("18 currentViewData: " + currentViewData);
			gtnLogger.info("19");
			
			reportProfileSaveLookupBean = (GtnReportingDashboardSaveProfileLookupBean) convertJsonToObject(
					GtnReportingDashboardSaveProfileLookupBean.class, viewData);
					//viewData.replaceAll("\\\\", "'"));
			
			gtnLogger.info("20");
			gtnLogger.info("21 reportProfileSaveLookupBean: " + reportProfileSaveLookupBean);
			gtnLogger.info("22");

			if (validateReportProfile(currentViewData, reportProfileSaveLookupBean, viewId)) {
				gtnLogger.info("23 Inside If");
				loadDataToComponent(reportProfileSaveLookupBean, actionParamList, viewId);
				gtnLogger.info("24 Data Loaded");
			}

		} catch (Exception ex) {
			gtnLogger.info("Catch Block");
			gtnLogger.error("Error message", ex);
		}
	}

	private void loadDataToComponent(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean,
			List<Object> actionParamList, String viewId) throws GtnFrameworkValidationFailedException {
		
		gtnLogger.info("101 Inside loadDataToComponent");
		
		try{
		
//		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(2).toString(), viewId).updateSelection(
//				Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabVariable()).isPresent() == true
//						? reportProfileSaveLookupBean.getDisplaySelectionTabVariable()
//						: new ArrayList<>());
		
		gtnLogger.info("102");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString(), viewId)
				.loadV8ComboBoxComponentValue(Optional
						.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabFrequency()).isPresent() == true
								? Integer.valueOf(reportProfileSaveLookupBean.getDisplaySelectionTabFrequency())
								: 0);
		
		gtnLogger.info("103");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(6).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabComparisonBasis())
								.isPresent() == true
										? Integer.valueOf(
												reportProfileSaveLookupBean.getDisplaySelectionTabComparisonBasis())
										: 0);
		
		gtnLogger.info("104");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(7).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabCustomViewCombobox())
								.isPresent() == true
										? reportProfileSaveLookupBean.getDisplaySelectionTabCustomViewCombobox()
										: "-Select one-");
		
		gtnLogger.info("105");
		
		gtnLogger.info("reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory(): " + reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory());
		
//		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(8).toString(), viewId).updateSelection(
//				Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory())!= null
//						? reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory()
//						: new ArrayList<>());
		gtnLogger.info("106");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(9).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabAnnualTotals())
								.isPresent() == true ? reportProfileSaveLookupBean.getDisplaySelectionTabAnnualTotals()
										: "Yes");
		gtnLogger.info("107");
		setPeriodRangeFromAndTo(reportProfileSaveLookupBean, actionParamList, viewId);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(10).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabCustomerLevel())
								.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabCustomerLevel()
										: "0");
		gtnLogger.info("108");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(11).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabCustomerFilter()).isPresent() == true
						? reportProfileSaveLookupBean.getFilterOptionsTabCustomerFilter()
						: new ArrayList<>());
		gtnLogger.info("109");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(12).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabProductLevel())
								.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabProductLevel()
										: "0");
		gtnLogger.info("110");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(13).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabProductFilter()).isPresent() == true
						? reportProfileSaveLookupBean.getFilterOptionsTabProductFilter()
						: new ArrayList<>());
		gtnLogger.info("111");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(14).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionLevel())
								.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabDeductionLevel()
										: "0");
		gtnLogger.info("112");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(15).toString(), viewId)
				.updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionFilter())
						.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabDeductionFilter()
								: new ArrayList<>());
		gtnLogger.info("113");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(16).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabSalesInclusion()).isPresent() == true
						? reportProfileSaveLookupBean.getFilterOptionsTabSalesInclusion()
						: new ArrayList<>());
		gtnLogger.info("114");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(17).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionInclusion())
						.isPresent() == true ? reportProfileSaveLookupBean.getFilterOptionsTabDeductionInclusion()
								: new ArrayList<>());
		gtnLogger.info("115");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsVariableAndVarianceSequencing())
								.isPresent() == true ? Integer.valueOf(
										reportProfileSaveLookupBean.getReportOptionsVariableAndVarianceSequencing())
										: 0);
		gtnLogger.info("116");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(19).toString(), viewId)
				.loadV8ComboBoxComponentValue(Optional
						.ofNullable(reportProfileSaveLookupBean.getReportOptionsHeaderSequencing()).isPresent() == true
								? Integer.valueOf(reportProfileSaveLookupBean.getReportOptionsHeaderSequencing())
								: 0);
		gtnLogger.info("116");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(20).toString(), viewId)
				.loadV8ComboBoxComponentValue(Optional
						.ofNullable(reportProfileSaveLookupBean.getReportOptionsViewOptions()).isPresent() == true
								? Integer.valueOf(reportProfileSaveLookupBean.getReportOptionsViewOptions())
								: 0);
		gtnLogger.info("117");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(21).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsDisplayFormat()).isPresent() == true
						? reportProfileSaveLookupBean.getReportOptionsDisplayFormat()
						: new ArrayList<>());
		gtnLogger.info("118");
// GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(22).toString(),viewId).updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsUnitsOfMeasure()).isPresent()
// == true
// ?reportProfileSaveLookupBean.getReportOptionsUnitsOfMeasure():new
// ArrayList<>() );
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(23).toString(), viewId)
				.loadV8ComboBoxComponentValue(Optional
						.ofNullable(reportProfileSaveLookupBean.getReportOptionsCurrencyDisplay()).isPresent() == true
								? Integer.valueOf(reportProfileSaveLookupBean.getReportOptionsCurrencyDisplay())
								: 0);
		gtnLogger.info("119");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId).getComponentData()
				.setCustomData(
						Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList())
								.isPresent() == true
										? reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList()
										: new ArrayList<>());
		gtnLogger.info("120");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId).setV8PopupFieldValue(
				Optional.ofNullable(getDisplayValue(reportProfileSaveLookupBean)).isPresent() == true
						? getDisplayValue(reportProfileSaveLookupBean)
						: "");
		gtnLogger.info("121");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(25).toString(), viewId).getComponentData()
				.setCustomData(Optional.ofNullable(reportProfileSaveLookupBean.getVariableBreakdownLookupBeanSaveList())
						.isPresent() == true ? reportProfileSaveLookupBean.getVariableBreakdownLookupBeanSaveList()
								: new ArrayList<>());
		gtnLogger.info("122");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(26).toString(), viewId).getComponentData()
				.setCustomData(
						Optional.ofNullable(reportProfileSaveLookupBean.getComparisonBreakdownLookupBeanSaveList())
								.isPresent() == true
										? reportProfileSaveLookupBean.getComparisonBreakdownLookupBeanSaveList()
										: new ArrayList<>());
		gtnLogger.info("123 Ending loadDataToComponent");
	}catch(Exception e)
		{
			gtnLogger.info("Error: "+ e);
			
		}
	}

	private boolean validateReportProfile(GtnReportingDashboardSaveProfileLookupBean currentViewData,
			GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean, String componentId)
			throws GtnFrameworkGeneralException {
		String message = "";
		if (!currentViewData.getCustomerHierarchy().equals(reportProfileSaveLookupBean.getCustomerHierarchy())
				|| !currentViewData.getProductHierarchy().equals(reportProfileSaveLookupBean.getProductHierarchy())) {
			message = "Different Hierarchy";
		} else if (currentViewData.getCustomerRelationSid() != reportProfileSaveLookupBean.getCustomerRelationSid()
				|| currentViewData.getProductRelationSid() != reportProfileSaveLookupBean.getProductRelationSid()) {
			message = "Different Relationship";
		} else if (currentViewData.getCustomerLevelNo() != reportProfileSaveLookupBean.getCustomerLevelNo()
				|| currentViewData.getProductLevelNo() != reportProfileSaveLookupBean.getProductLevelNo()) {
			message = "Different Level";
		}
		if (!message.isEmpty()) {
			String header = message + " was selected";
			String messageBody = "Due to the changes that were made to the Data Selection screen, the previously created Custom Views cannot be recreated.";
			GtnUIFrameWorkActionConfig config = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
			config.addActionParameter(header);
			config.addActionParameter(messageBody);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, config);

			GtnUIFrameworkBaseComponent popup = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportProfileConfig", componentId);
			popup.setV8PopupFieldValue("");
			popup.getComponentData().setCustomData(null);
		}
		return message.isEmpty();
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
