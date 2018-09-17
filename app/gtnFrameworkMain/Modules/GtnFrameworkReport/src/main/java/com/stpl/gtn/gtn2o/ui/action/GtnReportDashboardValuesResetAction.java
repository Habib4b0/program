package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.List;

public class GtnReportDashboardValuesResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		String nameSpace = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileConfig", componentId)
				.setV8PopupFieldValue(" ");

		GtnUIFrameworkComponentData comparisonData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportingDashboardComparisonConfig",
				componentId);
		comparisonData.setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
		String displayValue = dataSelectionBean.getComparisonProjectionBeanList() != null
				? getComparisonValue(dataSelectionBean.getComparisonProjectionBeanList()) : " ";
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportingDashboardComparisonConfig",
				componentId).setV8PopupFieldValue(displayValue);

		resetVariableInDashboard(componentId);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabCustomView", componentId)
				.loadV8ComboBoxComponentValue(String.valueOf(dataSelectionBean.getCustomViewMasterSid()));

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				"reportingDashboard_displaySelectionTabFrequency", componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getFrequency());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportOptionsTabVariableAndVarianceSequencing", componentId).loadV8ComboBoxComponentValue(0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptionsTabViewOptions", componentId)
				.loadV8ComboBoxComponentValue(0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptionsTabHeaderSequencing",
				componentId).loadV8ComboBoxComponentValue(0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptionsTabCurrencyDisplay",
				componentId).loadV8ComboBoxComponentValue(0);

		 GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis",
				componentId).loadV8ComboBoxComponentValue(0);
		 
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabAnnualTotals", componentId)
				.loadV8ComboBoxComponentValue("Yes");
		
                
                GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_reportProfileConfig", componentId).getComponentData()
				.setCustomData("");
		GtnUIFrameWorkActionConfig resetAction = new GtnUIFrameWorkActionConfig();
		resetAction.setActionType(GtnUIFrameworkActionType.V8_CONFIRMED_RESET_ACTION);
		resetAction.addActionParameter(Arrays
				.asList("reportingDashboard_displaySelectionTabPeriodRangeFrom",
						"reportingDashboard_displaySelectionTabPeriodRangeTo",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ "displaySelectionTabVariableCategory",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterTabCustomerLevel",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabProductLevel",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabDeductionLevel",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabCustomerFilter",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabProductFilter",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabDeductionFilter",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabSalesInclusion",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "filterOptionsTabDeductionInclusion",
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptionsTabDisplayFormat"));
		resetAction.addActionParameter(
				Arrays.asList(new Object[] { "0", "0", "", "0", "0", "0", "", "", "", "", "", "" }));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetAction);
              
                VerticalLayout treeGridLayout = (VerticalLayout) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DASHBOARD+GtnFrameworkCommonConstants.RESULT_TABLE,componentId).getComponent();
                TreeGrid<GtnWsRecordBean> treeGrid = (TreeGrid<GtnWsRecordBean>) treeGridLayout.getComponent(0);
                treeGrid.setDataProvider(new TreeDataProvider<>(new TreeData<GtnWsRecordBean>()));
                treeGrid.removeAllColumns();
        }

	private String getComparisonValue(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		if (comparisonProjectionBeanList.size() > 1) {
			return "MULTIPLE";
		}
		return comparisonProjectionBeanList.get(0).getProjectionName();
	}

	private void resetVariableInDashboard(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnReportVariableReloadInReportingDashboardAction.class.getName());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
