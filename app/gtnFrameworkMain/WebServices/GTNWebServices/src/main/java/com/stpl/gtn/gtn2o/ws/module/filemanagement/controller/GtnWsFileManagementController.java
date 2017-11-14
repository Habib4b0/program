/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.filemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.filemanagement.bean.GtnWsFileManagementBean;
import com.stpl.gtn.gtn2o.ws.filemanagement.constants.GtnWsFileManagementConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.filemanagement.service.GtnWsFileManagementService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = "/fileManagement")
public class GtnWsFileManagementController {
	public GtnWsFileManagementController() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsFileManagementService gtnWsFileManagementService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsFileManagementController.class);

	@RequestMapping(value = GtnWsFileManagementConstants.FILE_MANAGEMENT_HISTORY_TABLE_LOAD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse fileManagementHistoryTableload(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		String fileTypeArray = gtnWsRequest.getGtnWsSearchRequest().getSearchQueryName();
		LOGGER.info("fileType------------" + fileTypeArray);
		if (fileTypeArray != null) {

			gtnResponse = historyTableLoad(gtnWsRequest, gtnResponse, fileTypeArray, "");

			LOGGER.info("Exit Method Of fileManagementHistoryTableLoad");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsFileManagementConstants.FILE_MANAGEMENT_RESULT_TABLE_LOAD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse fileManagementResultTableload(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		String fileTypeArray = gtnWsRequest.getGtnWsSearchRequest().getSearchQueryName();
		LOGGER.info("fileType-------------" + fileTypeArray);
		if (fileTypeArray != null) {

			gtnResponse = resultTableLoad(gtnWsRequest, gtnResponse, fileTypeArray);

			LOGGER.info("Exit fileManagementHistoryTableLoad");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsFileManagementConstants.FILE_MANAGEMENT_DETAILS_TABLE_LOAD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse fileManagementDetailsTableload(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		String fileTypeArray = gtnWsRequest.getGtnWsSearchRequest().getSearchQueryName();
		LOGGER.info("fileType----------------------" + fileTypeArray);
		if (fileTypeArray != null) {

			gtnResponse = historyTableLoad(gtnWsRequest, gtnResponse, fileTypeArray, "");

			LOGGER.info("Exit fileManagementHistoryTableLoad");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsFileManagementConstants.FILE_MANAGEMENT_CURRENT_FILE_DATA_POPULATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse setDataPopulate(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsFileManagementBean fileManagementBean = gtnWsRequest.getGtnWsFileManagementRequest()
				.getFileManagementBean();
		String fileTypeDataPopulate = "DataPopulate";
		String fileTypeArray = fileManagementBean.getFiletype();
		LOGGER.info("fileType-----------------------------" + fileTypeArray);

		historyTableLoad(gtnWsRequest, gtnResponse, fileTypeArray, fileTypeDataPopulate);

		LOGGER.info("Exit " + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE);
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse historyTableLoad(GtnUIFrameworkWebserviceRequest wsRequest,
			GtnUIFrameworkWebserviceResponse wsResponse, String fileType, String fileTypeDataLoad) {
		GtnUIFrameworkWebserviceResponse gtnWsResponse;
		gtnWsResponse = gtnWsFileManagementService.historyTableLoad(wsRequest, wsResponse, fileType, fileTypeDataLoad);

		return gtnWsResponse;
	}

	public GtnUIFrameworkWebserviceResponse resultTableLoad(GtnUIFrameworkWebserviceRequest wsRequest,
			GtnUIFrameworkWebserviceResponse wsResponse, String fileType) {
		GtnUIFrameworkWebserviceResponse gtnWsResponse;
		gtnWsResponse = gtnWsFileManagementService.resultTableLoad(wsRequest, wsResponse, fileType);

		return gtnWsResponse;
	}

}
