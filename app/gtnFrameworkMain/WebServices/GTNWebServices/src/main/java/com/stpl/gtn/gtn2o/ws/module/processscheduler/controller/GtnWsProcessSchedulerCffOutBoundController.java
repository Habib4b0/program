package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsPSCffOutBoundService;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_SCREEN)
public class GtnWsProcessSchedulerCffOutBoundController {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerCffOutBoundController.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	
	@Autowired
	private GtnWsPSCffOutBoundService gtnWsPSCffOutBoundService;

	@RequestMapping(value = GtnWsProcessScedulerConstants.UPDATE_CHECK_RECORD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse updateCheckedRecordData(
			@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerCffRequest) {
		logger.info("entered in update check record controller");

		GtnCffOutBoundBean gtnCffOutBoundBean = processSchedulerCffRequest.getProcessSchedulerRequest()
				.getCffOutBoundBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = processSchedulerCffRequest.getGtnWsGeneralRequest();

		int booleanBit = gtnCffOutBoundBean.isCheckedRecord() ? 1 : 0;

		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnUIFrameworkWebserviceResponse processSchedulerResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			int updatedRecord = gtnSqlQueryEngine.executeInsertOrUpdateQuery(
					"UPDATE ST_CFF_OUTBOUND_MASTER SET CHECK_RECORD = " + booleanBit + " WHERE USER_ID = "
							+ gtnWsGeneralRequest.getUserId() + " AND SESSION_ID= " + gtnWsGeneralRequest.getSessionId()
							+ " AND CFF_DETAILS_SID = " + gtnCffOutBoundBean.getCffDetailsSid() + " AND RS_MODEL_SID = "
							+ gtnCffOutBoundBean.getRsModelSid() + " AND PERIOD_SID = "
							+ gtnCffOutBoundBean.getPeriodSid());
			generalResponse.setSucess(updatedRecord > 0 ? true : false);
		} catch (GtnFrameworkGeneralException gtnFrameworkGeneralException) {
			generalResponse.setGtnGeneralException(gtnFrameworkGeneralException);
			generalResponse.setSucess(false);
			logger.error("Update Query Execution Failed", gtnFrameworkGeneralException);
		}

		processSchedulerResponse.setGtnWsGeneralResponse(generalResponse);
		return processSchedulerResponse;

	}
	
	@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_DATA)
	public GtnUIFrameworkWebserviceResponse generateCffOutBound(@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerCffRequest) {
		boolean isSuccess = gtnWsPSCffOutBoundService.cffOutBoundService(processSchedulerCffRequest);
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnUIFrameworkWebserviceResponse processSchedulerResponse = new GtnUIFrameworkWebserviceResponse();
		generalResponse.setSucess(isSuccess);
		processSchedulerResponse.setGtnWsGeneralResponse(generalResponse);
		return processSchedulerResponse;
	}
	
	@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_UPDATE_CHECKED_ALL)
	public GtnUIFrameworkWebserviceResponse checkAllRecordCffOutBound(@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerCffRequest) {
		logger.info("Enter checkAll");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			boolean updateAllSuccess  = gtnWsPSCffOutBoundService.checkAllItems(processSchedulerCffRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(updateAllSuccess);
			return gtnResponse;
		}catch(Exception exception) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting updating check all record Query", exception);
			return gtnResponse;
		}
	}
}
