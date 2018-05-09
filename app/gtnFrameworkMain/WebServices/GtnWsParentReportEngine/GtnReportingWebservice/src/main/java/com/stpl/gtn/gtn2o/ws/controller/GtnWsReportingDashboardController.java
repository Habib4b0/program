/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportRespose;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReportingDashBoardSevice;
import com.stpl.gtn.gtn2o.ws.service.HeaderGeneratorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Karthik.Raja
 */
@RestController
public class GtnWsReportingDashboardController {
    	public GtnWsReportingDashboardController() {

	}

	
	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportController.class);

	@Autowired
	private GtnWsReportingDashBoardSevice gtnWsReportingDashBoardSevice;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
        @Autowired
	private HeaderGeneratorService reportHeaderService;


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
	public GtnUIFrameworkWebserviceResponse loadDashboardLeftData(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {
            
                GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
            	try {
		List<GtnWsRecordBean> resultList;
                GtnWsSearchRequest gtnWsSearchRequest= request.getGtnWsSearchRequest();
		GtnWsReportResponse gtnWsReportRespose = new GtnWsReportResponse();
                resultList = gtnWsReportingDashBoardSevice.getDashboardLeftData(gtnWsSearchRequest);
		gtnWsReportRespose.setResultList(resultList);
		response.setGtnWsReportResponse(gtnWsReportRespose);
	
                } catch (Exception ex) {
                    gtnLogger.error(ex.getMessage(),ex);
                }
                	return response;
	}
         @PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getReportConfigureLeftHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse leftHeader = reportHeaderService.getReportLeftTableColumns(request);
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(leftHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getReportConfiguredRightHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse rightHeader = reportHeaderService
					.getReportRightTableColumnsDummy();
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(rightHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

}
