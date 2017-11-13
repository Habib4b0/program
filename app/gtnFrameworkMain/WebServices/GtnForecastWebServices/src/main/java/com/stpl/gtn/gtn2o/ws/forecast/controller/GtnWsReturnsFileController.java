/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 *
 * @author STPL
 */
@RestController
public class GtnWsReturnsFileController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsFileController.class);

	@Autowired
	private GtnWsReturnsFileIOService fileService;

	public GtnWsReturnsFileIOService getFileService() {
		return fileService;
	}

	public void setFileService(GtnWsReturnsFileIOService fileService) {
		this.fileService = fileService;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_SERVICE)
	public GtnUIFrameworkWebserviceResponse writeFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = request.getGtnForecastBean();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			fileService.createFile(request.getGtnForecastBean());

			String basePath = fileService.getFilePath(fileService.getModuleName(gtnForecastBean),

					gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(),
					gtnForecastBean.getTestFilePath());
			@SuppressWarnings("unchecked")
			List<List<Object>> masterFileData = fileService.readJSONDataFromFile(
					basePath + "RETURNS_FORECAST_MASTER" + GtnFrameworkCommonStringConstants.STPL_EXTENSION,
					List.class);
			fileService.createTreeFile(request.getGtnForecastBean(), masterFileData, "RETURNS_FORECAST_MASTER");
			fileService.createGroupedDataFile(request.getGtnForecastBean(), masterFileData);

			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting writeFile", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		} finally {
			LOGGER.info("Exit writeFile");
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_DATE_CONFIGURATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse configureForecastDateDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		LOGGER.info("configureForecastDateDetails");
		GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
				.getGtnForecastBean();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			fileService.getForecastDateDetials(gtnForecastBean);
			GtnWsForecastResponse gtnWsForecastResponse = new GtnWsForecastResponse();
			gtnWsForecastResponse.setGtnForecastBean(gtnForecastBean);
			gtnUIFrameworkWebserviceResponse.setGtnWsForecastResponse(gtnWsForecastResponse);
		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting configureForecastDateDetails method", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_DELETE_FILE_ON_CLOSE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getFileDelete(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			fileService.deleteDir(request.getGtnForecastBean());
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_WRITE_DATA_SELECTION_FILE)
	public GtnUIFrameworkWebserviceResponse writeDataSelectionFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();
			DataSelectionBean dataSelectionBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getDataSelectionBean();
			fileService.createDataSelectionFile(gtnForecastBean, dataSelectionBean);
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
		} catch (Exception ex) {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
			LOGGER.error("Exception in Returns Write Data Selection file", ex);
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_READ_DATA_SELECTION_FILE)
	public GtnUIFrameworkWebserviceResponse readDataSelectionFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();
			DataSelectionBean dataSelectionBean = fileService.readDataSelectionFile(gtnForecastBean);
			GtnWsForecastResponse gtnWsForecastResponse = new GtnWsForecastResponse();
			gtnWsForecastResponse.setDataSelectionBean(dataSelectionBean);
			gtnUIFrameworkWebserviceResponse.setGtnWsForecastResponse(gtnWsForecastResponse);
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(true));
		} catch (Exception ex) {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(generateGeneralWsResopnse(false));
			LOGGER.error("Exception in Returns Read Data Selection file", ex);
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	private GtnWsGeneralResponse generateGeneralWsResopnse(boolean isSuccess) {
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(isSuccess);
		return generalWSResponse;
	}
}
