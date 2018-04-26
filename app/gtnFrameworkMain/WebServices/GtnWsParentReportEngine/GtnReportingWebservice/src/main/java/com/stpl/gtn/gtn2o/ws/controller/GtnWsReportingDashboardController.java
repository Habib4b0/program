/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportRespose;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReportingDashBoardSevice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<GtnWsRecordBean> resultList;
                GtnWsSearchRequest gtnWsSearchRequest= request.getGtnWsSearchRequest();
		GtnWsReportRespose gtnWsReportRespose = new GtnWsReportRespose();

                GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
			resultList = gtnWsReportingDashBoardSevice.getDashboardLeftData(gtnWsSearchRequest);
		gtnWsReportRespose.setResultList(resultList);
		response.setGtnWsReportRespose(gtnWsReportRespose);
		return response;
	}
}
