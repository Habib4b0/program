/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportDataRefreshBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkUIReportDasboardTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkUIReportDasboardTableLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) params.get(1), componentId);
		PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();

		GtnWsReportDashboardBean dashBoardBean = grid.getTableConfig().getGtnWsReportDashboardBean();
		dashBoardBean.setSessionId(dataSelectionBean.getSessionId());
		List<Object> salesInclusion = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(2).toString(), componentId).getSelectedListFromV8MultiSelect();
		dashBoardBean.setSalesInclusion(
				salesInclusion.size() == 1 ? Integer.parseInt(salesInclusion.get(0).toString()) - 1 : -1);
		List<Object> deductionInclusion = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(3).toString(), componentId).getSelectedListFromV8MultiSelect();
		dashBoardBean.setDeductionInclusion(
				deductionInclusion.size() == 1 ? Integer.parseInt(deductionInclusion.get(0).toString()) - 1 : -1);
		String annualTotalValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabAnnualTotals", componentId)
				.getCaptionFromV8ComboBox();
		dashBoardBean.setAnnualTotals(annualTotalValue);

		String comparisonBasis = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId)
				.getCaptionFromV8ComboBox();
		dashBoardBean.setComparisonBasis(comparisonBasis);

		String[] selectedVariable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable", componentId)
				.getStringFromMultiselectComboBox();

		dashBoardBean.setSelectedVariableType(selectedVariable);

		List<Object> displayFormat = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(4).toString(), componentId)
				.getSelectedCaptionListFromV8MultiSelect();
		dashBoardBean.setDisplayFormat(displayFormat.toArray());

		dashBoardBean.setVariablesVariances(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(9).toString(), componentId).getIntegerFromV8ComboBox() == 2);

		dashBoardBean.setCurrencyConversion(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(5).toString(), componentId).getCaptionFromV8ComboBox());

		grid.getTableConfig().setGtnWsReportDashboardBean(dashBoardBean);

		if (!dataSelectionBean.isDataRefreshDone()) {
			grid.getTableConfig().setGtnReportDataRefreshBean(null);
		}
		checkForSelectionChange(dataSelectionBean, componentId, params, grid.getTableConfig());

		dashBoardBean
				.setCustomViewMasterSid(grid.getTableConfig().getGtnReportDataRefreshBean().getCustomViewMasterSid());
		dashBoardBean.setComparisonProjectionBeanList(
				grid.getTableConfig().getGtnReportDataRefreshBean().getComparisonProjectionBeanList());
		dashBoardBean.setSelectFreqString(grid.getTableConfig().getGtnReportDataRefreshBean().getFrequencyName());

		componentData.getCurrentGtnComponent().reloadComponent(null, componentId, (String) params.get(1), null);
	}

	private void checkForSelectionChange(GtnWsReportDataSelectionBean dataSelectionBean, String componentId,
			List<Object> actionParameterList, GtnUIFrameworkPagedTreeTableConfig tableConfig) {
		try {
			if (checkDataRefreshCondition(dataSelectionBean, tableConfig, actionParameterList, componentId)) {
				GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
				GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
				GtnWsReportRequest reportRequest = new GtnWsReportRequest();
				GtnWsReportBean reportBean = new GtnWsReportBean();
				reportBean.setDataSelectionBean(getDataSelectionBeanCopy(dataSelectionBean, tableConfig));
				reportRequest.setReportBean(reportBean);
				serviceRequest.setGtnWsReportRequest(reportRequest);
				wsclient.callGtnWebServiceUrl(
						GtnWsReportConstants.GTN_WS_REPORT_DASHBOARD_CUSTOM_VIEW_AND_DATA_REGENERATION_SERVICE,
						GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, serviceRequest,
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

				dataSelectionBean.setDataRefreshDone(true);
                                dataSelectionBean.setCustomViewMasterSid(reportBean.getDataSelectionBean().getCustomViewMasterSid());
			}
		} catch (GtnFrameworkValidationFailedException e) {
			logger.error(e.getErrorMessage(), e);
		}
	}

	private GtnWsReportDataSelectionBean getDataSelectionBeanCopy(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnUIFrameworkPagedTreeTableConfig tableConfig) {
		GtnWsReportDataSelectionBean reportDataSelectionBeanCopy = dataSelectionBean.reportDataSelectionBeanCopy();
		GtnReportDataRefreshBean refreshBean = tableConfig.getGtnReportDataRefreshBean();
		reportDataSelectionBeanCopy.setCustomViewMasterSid(refreshBean.getCustomViewMasterSid());
		reportDataSelectionBeanCopy.setComparisonProjectionBeanList(refreshBean.getComparisonProjectionBeanList());
		reportDataSelectionBeanCopy.setFrequencyName(refreshBean.getFrequencyName());
		return reportDataSelectionBeanCopy;
	}

	private boolean checkDataRefreshCondition(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnUIFrameworkPagedTreeTableConfig tableConfig, List<Object> actionParameterList, String componentId)
			throws GtnFrameworkValidationFailedException {
		int customViewId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(6).toString(), componentId).getIntegerFromV8ComboBox();
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(7).toString(), componentId)
				.getStringCaptionFromV8ComboBox();
		GtnUIFrameworkComponentData comparisonProjectionData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(8).toString(), componentId);
		List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = (List<GtnReportComparisonProjectionBean>) comparisonProjectionData
				.getCustomData();
		boolean refreshNeeded = false;
		GtnReportDataRefreshBean refreshBean = tableConfig.getGtnReportDataRefreshBean();
		if (refreshBean == null) {
			refreshBean = new GtnReportDataRefreshBean();
			refreshNeeded = (customViewId != dataSelectionBean.getCustomViewMasterSid())
					|| (!frequency.equals(dataSelectionBean.getFrequencyName()))
					|| (checkComparison(dataSelectionBean.getComparisonProjectionBeanList(),
							comparisonProjectionBeanList));
		} else {
			refreshNeeded = (customViewId != refreshBean.getCustomViewMasterSid())
					|| (!frequency.equals(refreshBean.getFrequencyName()))
					|| (checkComparison(refreshBean.getComparisonProjectionBeanList(), comparisonProjectionBeanList));
		}
		refreshBean.setCustomViewMasterSid(customViewId);
		refreshBean.setFrequencyName(frequency);
		refreshBean.setComparisonProjectionBeanList(comparisonProjectionBeanList);
		tableConfig.setGtnReportDataRefreshBean(refreshBean);
		dataSelectionBean.setDataRefreshDone(true);
		logger.info("checkDataRefreshCondition = = = " + refreshNeeded);
		return refreshNeeded;
	}

	private boolean checkComparison(List<GtnReportComparisonProjectionBean> dataselectionComparisonProjectionBeanList,
			List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		boolean dsComparison = Optional.ofNullable(dataselectionComparisonProjectionBeanList).isPresent();
		boolean rdComparison = Optional.ofNullable(comparisonProjectionBeanList).isPresent();
		if (dataselectionComparisonProjectionBeanList != null && comparisonProjectionBeanList != null) {
			return checkBothComparisonList(dataselectionComparisonProjectionBeanList, comparisonProjectionBeanList);
		} else if ((dsComparison && !rdComparison) || (!dsComparison && rdComparison)) {
			return true;
		}
		return false;
	}

	private boolean checkBothComparisonList(
			List<GtnReportComparisonProjectionBean> dataselectionComparisonProjectionBeanList,
			List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		for (GtnReportComparisonProjectionBean dsComparison : dataselectionComparisonProjectionBeanList) {
			for (GtnReportComparisonProjectionBean rdComparison : comparisonProjectionBeanList) {
				if ((dsComparison.getProjectionMasterSid() != rdComparison.getProjectionMasterSid())
						|| (!dsComparison.getProjectionType().equals(rdComparison.getProjectionType()))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
