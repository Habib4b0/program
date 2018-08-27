/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsProcessSchedulerRunValidationService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
*
* @author Deepak.Kumar
*/
@RestController
@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN)
public class GtnWsProcessSchedulerController {
	public GtnWsProcessSchedulerController() {
		super();
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerController.class);

	@Autowired
	private GtnWsProcessSchedulerRunValidationService processSchedularRunServiceValidator;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	
	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsPSServiceUtil;

	@RequestMapping(value = GtnWsProcessScedulerConstants.GET_PROCESS_SCHEDULER_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getProcesMonitorTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerRequest) throws GtnFrameworkGeneralException {
		logger.info("Enter ProcessScheduler");
		GtnUIFrameworkWebserviceResponse processSchedulerResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		String queryName = processSchedulerRequest.getGtnWsSearchRequest().isCount() ? "getProcessSchedulerCount"
				: "getProcessSchedulerManualResults";
		logger.info("------------->startRecord="+  processSchedulerRequest.getGtnWsSearchRequest().getTableRecordStart());
		int startRecord = processSchedulerRequest.getGtnWsSearchRequest().getTableRecordStart();
		
		GtnWsSearchQueryGenerationLogic searchQueryLogic = new GtnWsSearchQueryGenerationLogic();
		
		int endRecord = processSchedulerRequest.getGtnWsSearchRequest().getTableRecordOffset();
		logger.info("-------------> endRecord: "+ processSchedulerRequest.getGtnWsSearchRequest().getTableRecordOffset());
		
		List<Object> inputlist = gtnWsPSServiceUtil.getSearchInput(processSchedulerRequest);

		if (processSchedulerRequest.getGtnWsSearchRequest().isCount()) {
			@SuppressWarnings("unchecked")
			List<Object[]> result = gtnWsPSServiceUtil.executeQuery(gtnWsSqlService.getQuery(inputlist, queryName));
			logger.info("result size: "+result.size());
			gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
		} else {
			
			StringBuilder updatedOffsetQuery = new StringBuilder(gtnWsSqlService.getQuery(inputlist, queryName));
			logger.info("before appending offset logic---------->"+updatedOffsetQuery);
			searchQueryLogic.appendOffset(updatedOffsetQuery, startRecord, endRecord);
			logger.info("after appending offset logic---------->"+updatedOffsetQuery);
			
			@SuppressWarnings("unchecked")		
			List<Object[]> result = gtnWsPSServiceUtil.executeQuery(updatedOffsetQuery.toString());
			GtnUIFrameworkDataTable processSchedulerDataTable = new GtnUIFrameworkDataTable();
			processSchedulerDataTable.addData(result);
			gtnSerachResponse.setResultSet(processSchedulerDataTable);
		}

		processSchedulerResponse.setGtnSerachResponse(gtnSerachResponse);
		generalResponse.setSucess(true);
		processSchedulerResponse.setGtnWsGeneralResponse(generalResponse);
		return processSchedulerResponse;
	}

	@RequestMapping(value = GtnWsProcessScedulerConstants.GET_SCHEDULED_PROCESSING_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getScheduledProcessingTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerRequest) throws GtnFrameworkGeneralException {
		logger.info("Enter Scheduled Processing; ");
		
		GtnUIFrameworkWebserviceResponse processSchedulerResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		String queryName = processSchedulerRequest.getGtnWsSearchRequest().isCount() ? "getProcessSchedulerCount"
				: "getProcessSchedulerSchedulerResults";
		logger.info("------------->"+gtnWsPSServiceUtil.getSysSchemaCatalog()+"-----> "+queryName);
		logger.info("------------->startRecord="+  processSchedulerRequest.getGtnWsSearchRequest().getTableRecordStart());
		int startRecord = processSchedulerRequest.getGtnWsSearchRequest().getTableRecordStart();
		
		GtnWsSearchQueryGenerationLogic searchQueryLogic = new GtnWsSearchQueryGenerationLogic();
		
		int endRecord = processSchedulerRequest.getGtnWsSearchRequest().getTableRecordOffset();
		logger.info("-------------> endRecord: "+ processSchedulerRequest.getGtnWsSearchRequest().getTableRecordOffset());
		
		List<Object> inputlist = gtnWsPSServiceUtil.getSearchInput(processSchedulerRequest);
		
		if (processSchedulerRequest.getGtnWsSearchRequest().isCount()) {
			@SuppressWarnings("unchecked")		
			List<Object[]> result = gtnWsPSServiceUtil.executeQuery(gtnWsSqlService.getQuery(inputlist, queryName));
			gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
		} else {
			String replacedQuery= gtnWsSqlService.getQuery(inputlist, queryName).replace("?", gtnWsPSServiceUtil.getSysSchemaCatalog());
			StringBuilder updatedOffsetQuery = new StringBuilder(replacedQuery);
			logger.info("before appending offset logic---------->"+updatedOffsetQuery);
			searchQueryLogic.appendOffset(updatedOffsetQuery, startRecord, endRecord);
			logger.info("after appending offset logic---------->"+updatedOffsetQuery);
			
			@SuppressWarnings("unchecked")		
			List<Object[]> result = gtnWsPSServiceUtil.executeQuery(updatedOffsetQuery.toString());
			logger.info("result size: "+result.size());
			GtnUIFrameworkDataTable processSchedulerDataTable = new GtnUIFrameworkDataTable();
			processSchedulerDataTable.addData(result);
			gtnSerachResponse.setResultSet(processSchedulerDataTable);
		}

		processSchedulerResponse.setGtnSerachResponse(gtnSerachResponse);
		generalResponse.setSucess(true);
		processSchedulerResponse.setGtnWsGeneralResponse(generalResponse);
		return processSchedulerResponse;
	}
	
	@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_RUN_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRunningProcess(
			@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerRequest) throws GtnFrameworkGeneralException {
		logger.info("Enter RunningProcess WebServices; ");
		
		GtnUIFrameworkWebserviceResponse processRunResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		
		List<String> validationCriteria = new ArrayList<>();
		String schemaName=processSchedulerRequest.getProcessSchedulerRequest().getProcessSchedulerBean().getPsSchemaName();
		String processName = processSchedulerRequest.getProcessSchedulerRequest().getProcessSchedulerBean().getPsProcessName();
		logger.info("------------> schemaName in webservices: "+schemaName+"  processName: "+processName);
		
		validationCriteria.add(schemaName);
		validationCriteria.add(processName);
		
		List<Object> inputList = new ArrayList<>();
		inputList.add(gtnWsPSServiceUtil.getSysSchemaCatalog());
		if(! processSchedularRunServiceValidator.validateProcessRun(validationCriteria, inputList)) {
			generalResponse.setSucess(false);
			
		}else {
			generalResponse.setSucess(true);
		}
		processRunResponse.setGtnWsGeneralResponse(generalResponse);
		return processRunResponse;
	}
	
}
