/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.report.controller;

import static com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType.BYTE;
import static com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType.INTEGER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.service.HeaderGeneratorService;
import com.stpl.gtn.gtn2o.ws.report.serviceimpl.GtnWsReportDataSelectionSqlGenerateServiceImpl;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;

/**
 *
 * @author Karthik.Raja
 */
@RestController
public class GtnWsReportingDashboardController {

	public GtnWsReportingDashboardController() {
		super();
	}

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportController.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private HeaderGeneratorService reportHeaderService;

	@Autowired
	private GtnWsReportDataSelectionSqlGenerateServiceImpl dataSelectionServiceImpl;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_DASHBOARD_LEFT_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDashboardLeftData(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		List<GtnWsRecordBean> resultList = null;
		GtnWsReportResponse gtnWsReportRespose = new GtnWsReportResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportDashboardBean reportDashboardBean = request.getGtnWsReportRequest().getGtnWsReportDashboardBean();
		if (request.getGtnWsReportRequest().isLoadTableUsingFile()) {
			resultList = dataSelectionServiceImpl.getDashboardLeftData(reportDashboardBean, request);
		}
		gtnWsReportRespose.setRecordBeanResultList(resultList);
		response.setGtnWsReportResponse(gtnWsReportRespose);
		return response;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getReportConfiguredRightHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsPagedTreeTableResponse rightHeader = reportHeaderService
					.getReportRightTableColumnsDummy(gtnUIFrameworkWebserviceRequest);
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(rightHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
			gtnLogger.error("Error in Right header service, ", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getReportConfigureLeftHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsPagedTreeTableResponse leftHeader = reportHeaderService.getReportLeftTableColumns();
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(leftHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_TABLE_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getVariableBreakdownGridHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
		GtnWsPagedTableResponse leftHeader = reportHeaderService
				.getVariableBreakdownHeaderColumns(gtnUIFrameworkWebserviceRequest);
		gtnUIFrameworkWebserviceResponse.setGtnWsPagedTableResponse(leftHeader);
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_PERIODS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getVariableBreakdownPeriods(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

			String query = reportHeaderService.getVariableBreakdownPeriods(gtnUIFrameworkWebserviceRequest);
			List<Object[]> results = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
			GtnWsReportResponse gtnReportResponse = new GtnWsReportResponse();
			GtnReportVariableBreakdownLookupBean gtnReportVariableBreakdownLookupBean = new GtnReportVariableBreakdownLookupBean();
			gtnReportVariableBreakdownLookupBean.setResultList(results);
			gtnReportResponse.setVariableBreakdownLookupBean(gtnReportVariableBreakdownLookupBean);
			gtnUIFrameworkWebserviceResponse.setGtnReportResponse(gtnReportResponse);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in Variable Breakdown Periods, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getVariableBreakdownSubmit(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

			dataSelectionServiceImpl.callVariableBreakdownInsertService(
					gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean());
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
			gtnLogger.error("Error in Variable Breakdown Submit, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_TABLE_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getComparisonBreakdownGridHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
		GtnWsPagedTableResponse leftHeader = reportHeaderService
				.getVariableBreakdownHeaderColumns(gtnUIFrameworkWebserviceRequest);
		gtnUIFrameworkWebserviceResponse.setGtnWsPagedTableResponse(leftHeader);
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_PERIODS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getComparisonBreakdownPeriods(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			String query = reportHeaderService.getComparisonBreakdownPeriods(gtnUIFrameworkWebserviceRequest);
			List<Object[]> results = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
			GtnWsReportResponse gtnReportResponse = new GtnWsReportResponse();
			GtnReportComparisonBreakdownLookupBean gtnReportComparisonBreakdownLookupBean = new GtnReportComparisonBreakdownLookupBean();
			gtnReportComparisonBreakdownLookupBean.setResultList(results);
			gtnReportResponse.setComparisonBreakdownLookupBean(gtnReportComparisonBreakdownLookupBean);
			gtnUIFrameworkWebserviceResponse.setGtnReportResponse(gtnReportResponse);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in comparison breakdown Periods, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse comparisonBreakdownSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

			GtnWsReportDataSelectionBean dataSelectionBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
					.getDataSelectionBean();
			List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdown = gtnUIFrameworkWebserviceRequest
					.getGtnWsReportRequest().getDataSelectionBean().getComparisonBreakdownSaveList();
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(GtnWsReportDataSelectionSqlGenerateServiceImpl
					.replaceTableNames(GtnWsQueryConstants.COMPARISON_BREAKDOWN_TRUNCATE_QUERY,
							dataSelectionBean.getSessionTableMap()));
			for (int i = 0; i < comparisonBreakdown.size(); i++) {
				Object[] obj = new Object[4];
				obj[0] = comparisonBreakdown.get(i).getMasterSid();
				obj[1] = comparisonBreakdown.get(i).getPeriod();
				obj[2] = comparisonBreakdown.get(i).getYear();
				obj[3] = Integer.valueOf(comparisonBreakdown.get(i).getSelectedVariable());
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(
						GtnWsReportDataSelectionSqlGenerateServiceImpl.replaceTableNames(
								GtnWsQueryConstants.COMPARISON_BREAKDOWN_SAVE_SERVICE_QUERY,
								dataSelectionBean.getSessionTableMap()),
						obj, new GtnFrameworkDataType[] { INTEGER, BYTE, BYTE, BYTE });
			}

			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in variable breakdown controller, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;

		}

	}
}
