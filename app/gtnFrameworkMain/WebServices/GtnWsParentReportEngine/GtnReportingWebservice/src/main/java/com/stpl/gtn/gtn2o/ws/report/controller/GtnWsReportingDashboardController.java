/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.engine.GtnGenerateReportEngine;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportingDashBoardSevice;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;

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
	GtnGenerateReportEngine gtnGeneralReportEngine;

	@Autowired
	GtnWsMongoService gtnWsMongoService;

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
		GtnWsSearchRequest gtnWsSearchRequest = request.getGtnWsSearchRequest();
		GtnWsReportResponse gtnWsReportRespose = new GtnWsReportResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportDashboardBean reportDashboardBean = request.getGtnWsReportRequest().getGtnWsReportDashboardBean();
		GtnWsReportEngineTreeNode inputTree = getSavedCustomTree(reportDashboardBean);

		if (inputTree != null) {
			GtnWsReportEngineTreeNode root = gtnGeneralReportEngine
					.generateReportOutput(getGtnWsReportEngineBean(inputTree));
			saveComputedResults(reportDashboardBean, root);

			resultList = gtnWsReportingDashBoardSevice.getDashboardLeftData(gtnWsSearchRequest);
			gtnWsReportRespose.setRecordBeanResultList(resultList);
			response.setGtnWsReportResponse(gtnWsReportRespose);
		}
		return response;
	}

	private void saveComputedResults(GtnWsReportDashboardBean reportDashboardBean, GtnWsReportEngineTreeNode root) {
		gtnWsMongoService.updateFinalResultsToMongo(
				reportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.COMPUTED_TREE_RESULTS), root);
	}

	private GtnWsReportEngineTreeNode getSavedCustomTree(GtnWsReportDashboardBean reportDashboardBean) {
		return (GtnWsReportEngineTreeNode) gtnWsMongoService.getTreeFromMongo(
				reportDashboardBean.getTableNameWithUniqueId(reportDashboardBean.getCustomViewName()),
				GtnWsReportEngineTreeNode.class, null, null);
	}

	private GtnWsReportEngineBean getGtnWsReportEngineBean(GtnWsReportEngineTreeNode input) {
		GtnWsReportEngineBean engineBean = new GtnWsReportEngineBean();
		engineBean.setSelectedProjectionId(0);
		engineBean.setComparisonBasis("Actuals");
		engineBean.setInput(input);
		engineBean.addComparisonTableName("projection");
		engineBean.addComparisonTableName("projection1");
		engineBean.addComparisonTableName("projection2");
		return engineBean;
	}
}
