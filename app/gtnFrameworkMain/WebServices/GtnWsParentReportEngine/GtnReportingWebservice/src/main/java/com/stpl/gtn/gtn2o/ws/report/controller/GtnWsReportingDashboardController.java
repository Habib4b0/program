/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.constants.MongoConstants;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import static com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType.BYTE;
import static com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType.INTEGER;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.engine.GtnGenerateReportEngine;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportingDashBoardSevice;
import com.stpl.gtn.gtn2o.ws.report.service.HeaderGeneratorService;
import com.stpl.gtn.gtn2o.ws.report.serviceimpl.GtnWsReportDataSelectionSqlGenerateServiceImpl;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;
import java.io.IOException;

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
	public GtnUIFrameworkWebserviceResponse loadDashboardLeftData(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException, IOException {
		List<GtnWsRecordBean> resultList;
		GtnWsSearchRequest gtnWsSearchRequest = request.getGtnWsSearchRequest();
		GtnWsReportResponse gtnWsReportRespose = new GtnWsReportResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportDashboardBean reportDashboardBean = request.getGtnWsReportRequest().getGtnWsReportDashboardBean();
	        if (request.getGtnWsReportRequest().isLoadTableUsingFile()) {
                resultList = dataSelectionServiceImpl.getDashboardLeftData(reportDashboardBean,request);
                } else {
                resultList = gtnWsReportingDashBoardSevice.getDashboardLeftData(gtnWsSearchRequest, reportDashboardBean);
                }
		gtnWsReportRespose.setRecordBeanResultList(resultList);
		response.setGtnWsReportResponse(gtnWsReportRespose);
		return response;
	}
	
	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_DASHBOARD_GENERATE_REPORT_CALCULATION_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse generateReportCalculationInsert(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportDashboardBean reportDashboardBean = request.getGtnWsReportRequest().getGtnWsReportDashboardBean();
		GtnWsReportEngineTreeNode inputTree = getSavedCustomTree(reportDashboardBean);
		if (inputTree != null) {
			GtnWsReportEngineTreeNode root = gtnGeneralReportEngine
					.generateReportOutput(getGtnWsReportEngineBean(inputTree, reportDashboardBean));
			dropComputedResultsInGenerate(reportDashboardBean);
			saveComputedResults(reportDashboardBean, root);
		}

		return response;
	}

	private void dropComputedResultsInGenerate(GtnWsReportDashboardBean reportDashboardBean) {
		gtnWsMongoService.dropCollection(
				reportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.COMPUTED_TREE_RESULTS));
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

	private GtnWsReportEngineBean getGtnWsReportEngineBean(GtnWsReportEngineTreeNode input,
			GtnWsReportDashboardBean reportDashboardBean) {
		GtnWsReportEngineBean engineBean = new GtnWsReportEngineBean();
		engineBean.setSelectedProjectionId(0);
		engineBean.setComparisonBasis("Actuals");
		engineBean.setInput(input);
		engineBean.addComparisonTableName(
				reportDashboardBean.getTableNameWithUniqueId(MongoConstants.USER_BASED_CCP_COLLECTION));
		return engineBean;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getReportConfiguredRightHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

//			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse rightHeader = reportHeaderService.getReportRightTableColumnsDummy(gtnUIFrameworkWebserviceRequest);
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(rightHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
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
	
	
        @PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_TABLE_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getVariableBreakdownGridHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsPagedTableResponse leftHeader = reportHeaderService.getVariableBreakdownHeaderColumns(gtnUIFrameworkWebserviceRequest);
			gtnUIFrameworkWebserviceResponse.setGtnWsPagedTableResponse(leftHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in variable breakdown controller, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}
        
        
        
        @PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse variableBreakdownSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			
                         List<GtnReportVariableBreakdownLookupBean> variableBreakdown = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean()
                    .getVariableBreakdownSaveList();
                         
                         for(int i=0;i<variableBreakdown.size();i++){
                              Integer[] obj = new Integer[4];
                             obj[0] = variableBreakdown.get(i).getMasterSid();
                             obj[1] = variableBreakdown.get(i).getPeriod();
                             obj[2] = variableBreakdown.get(i).getYear();
                             obj[3] = variableBreakdown.get(i).getSelectedVariable();
                             gtnSqlQueryEngine.executeInsertOrUpdateQuery(GtnWsQueryConstants.VARIABLE_BREAKDOWN_SAVE_SERVICE_QUERY, obj, new GtnFrameworkDataType[]{INTEGER,INTEGER,INTEGER,INTEGER});
                         }
                       
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in variable breakdown controller, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}
        
        @PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_PERIODS_SERVICE)
    	public GtnUIFrameworkWebserviceResponse getVariableBreakdownPeriods(
    			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
    		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
    		try {
    			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
    			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
    			String query= reportHeaderService.getVariableBreakdownPeriods(gtnUIFrameworkWebserviceRequest);
                            List<Object[]> results = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
                            Object[] ob=results.get(0);
                            GtnWsReportResponse gtnReportResponse = new GtnWsReportResponse();
                            GtnReportVariableBreakdownLookupBean gtnReportVariableBreakdownLookupBean= new GtnReportVariableBreakdownLookupBean();
                            gtnReportVariableBreakdownLookupBean.setResultList(results);
                            gtnReportResponse.setVariableBreakdownLookupBean(gtnReportVariableBreakdownLookupBean);
    			gtnUIFrameworkWebserviceResponse.setGtnReportResponse(gtnReportResponse);
    			return gtnUIFrameworkWebserviceResponse;
    		} catch (GtnFrameworkGeneralException ex) {
    			gtnLogger.error("Error in variable breakdown controller, " + ex.getMessage(), ex);
    			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
    			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
    			return gtnUIFrameworkWebserviceResponse;
    		}
    	}
        
	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_TABLE_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getComparisonBreakdownGridHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTableResponse leftHeader = reportHeaderService.getComparisonBreakdownHeaderColumns();
			gtnUIFrameworkWebserviceResponse.setGtnWsPagedTableResponse(leftHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in comparison breakdown controller, " + ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}
	
	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_PERIODS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getComparisonBreakdownPeriods(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			String query= reportHeaderService.getComparisonBreakdownPeriods(gtnUIFrameworkWebserviceRequest);
                        List<Object[]> results = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
                        Object[] ob=results.get(0);
                        GtnWsReportResponse gtnReportResponse = new GtnWsReportResponse();
                        GtnReportComparisonBreakdownLookupBean gtnReportComparisonBreakdownLookupBean= new GtnReportComparisonBreakdownLookupBean();
                        gtnReportComparisonBreakdownLookupBean.setResultList(results);
                        gtnReportResponse.setComparisonBreakdownLookupBean(gtnReportComparisonBreakdownLookupBean);
			gtnUIFrameworkWebserviceResponse.setGtnReportResponse(gtnReportResponse);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error("Error in comparison breakdown controller, " + ex.getMessage(), ex);
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
				
				GtnWsReportDataSelectionBean dataSelectionBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean();
	                         List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdown = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean()
	                    .getComparisonBreakdownSaveList();
	                         
	                         for(int i=0;i<comparisonBreakdown.size();i++){
	                              Object[] obj = new Object[4];
	                             obj[0] = comparisonBreakdown.get(i).getMasterSid();	                             
	                             obj[1] = comparisonBreakdown.get(i).getPeriod();
	                             obj[2] = comparisonBreakdown.get(i).getYear();
	                             obj[3] = Integer.valueOf(comparisonBreakdown.get(i).getSelectedVariable());
	                             gtnSqlQueryEngine.executeInsertOrUpdateQuery(GtnWsQueryConstants.COMPARISON_BREAKDOWN_SAVE_SERVICE_QUERY, obj, new GtnFrameworkDataType[]{INTEGER,BYTE,BYTE,BYTE});
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
