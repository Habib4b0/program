/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TabSheet;

public class GtnReportDataAssumptionsTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataAssumptionsTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info("Inside GtnReportDataAssumptionsTabLoadAction");
		try {
			final List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = (GtnWsReportDataSelectionBean) actionParameterList
					.get(4);
			Integer reportDataSourceValue = gtnWsReportDataSelectionBean.getReportDataSource();
			addTab(gtnWsReportDataSelectionBean.getComparisonProjectionBeanList(), reportDataSourceValue, componentId);

		} catch (Exception exception) {
			logger.error("Error message", exception);
		}
	}

	private void addTab(List<GtnReportComparisonProjectionBean> gtnReportComparisonProjectionBeanList, Integer reportDataSourceValue,
			String sourceComponentId) {
		try {
			int projectionNameCount = 0;
			List<Integer> projectionMasterSidList = new ArrayList<>();

			if (gtnReportComparisonProjectionBeanList != null) {
				for (GtnReportComparisonProjectionBean gtnReportComparisonProjectionBean : gtnReportComparisonProjectionBeanList) {
					projectionMasterSidList.add(gtnReportComparisonProjectionBean.getProjectionMasterSid());
				}
			}

			setTabSheetVisible(projectionMasterSidList, sourceComponentId, gtnReportComparisonProjectionBeanList,
					projectionNameCount);

			setDataAssumptionsGridDataLoad(projectionMasterSidList, reportDataSourceValue, sourceComponentId);
		} catch (Exception ex) {
			logger.error("Error message ", ex);
		}
	}

	private void setDataAssumptionsGridDataLoad(List<Integer> projectionMasterSidList, Integer reportDataSourceValue, String sourceComponentId) {
		for (int i = 0; i < projectionMasterSidList.size(); i++) {

			Grid<GtnWsRecordBean> dataAssumptionsCurrentTabComponent = getDataAssumptionsGridComponent(
					GtnFrameworkReportStringConstants.getReportDataAssumptionsTabId().get(i), sourceComponentId);

			List<GtnWsRecordBean> dsLoadResults = getDataAssumptionGridLoadValues(projectionMasterSidList.get(i), reportDataSourceValue, "/gtnWsReportLoadDataAssumptionsMultipleTabs");

			dataAssumptionsCurrentTabComponent.setItems(dsLoadResults);
		}
		// Current Tab must load by default
		Grid<GtnWsRecordBean> dataAssumptionsCurrentTabComponent1 = getDataAssumptionsGridComponent(
				"dataAssumptionsPagedTableComponentcurrentTabdataAssumptionsTab", sourceComponentId);
		List<GtnWsRecordBean> dsLoadResults1 = getDataAssumptionGridLoadValues(0, reportDataSourceValue, "/gtnWsReportLoadDataAssumptions");
		dataAssumptionsCurrentTabComponent1.setItems(dsLoadResults1);
	}

	private void setTabSheetVisible(List<Integer> projectionMasterSidList, String sourceComponentId,
			List<GtnReportComparisonProjectionBean> gtnReportComparisonProjectionBeanList, int projectionNameCount) {
		for (int i = 1; i < 6; i++) {
			if (i > projectionMasterSidList.size()) {
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkReportStringConstants.TAB_SHEET + "dataAssump",
								sourceComponentId)
						.setTabComponentVisible(i, false);
			} else {
				final TabSheet tabSheet = (TabSheet) getAbstractComponent(
						GtnFrameworkReportStringConstants.TAB_SHEET + "dataAssump", sourceComponentId);
				tabSheet.getTab(i)
						.setCaption(gtnReportComparisonProjectionBeanList.get(projectionNameCount).getProjectionName());
				projectionNameCount++;
			}
		}
	}

	private List<GtnWsRecordBean> getDataAssumptionGridLoadValues(int projectionmasterSid, Integer reportDataSourceValue, String url) {


		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		
		if(url.equals("/gtnWsReportLoadDataAssumptions"))
		{
			GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
			gtnWsSearchRequest.setCount(true);
			request.setGtnWsSearchRequest(gtnWsSearchRequest);
		}
		
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		
		dataSelectionBean.setReportDataSource(reportDataSourceValue);
		reportRequest.setDataSelectionBean(dataSelectionBean);

		reportRequest.setProjectionMasterSid(projectionmasterSid);
		request.setGtnWsReportRequest(reportRequest);
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				"/gtnReport" + url, "report", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
		List<GtnWsRecordBean> records = new ArrayList<>(
				response.getGtnSerachResponse().getResultSet().getDataTable().size());
		for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			dto.setProperties(record.getColList());
			dto.setRecordHeader(Arrays.asList(new Object[] { "file", "company", "businessUnit", "type", "version",
					"activeFrom", "fromPeriod", "toPeriod" }));
			records.add(dto);
		}
		return records;
	}

	private Grid<GtnWsRecordBean> getDataAssumptionsGridComponent(String gridId, String sourceComponentId) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(gridId,
				sourceComponentId);
		PagedGrid pagedGrid = (PagedGrid) componentData.getCustomData();
		return pagedGrid.getGrid();
	}

	private AbstractComponent getAbstractComponent(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
