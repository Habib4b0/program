package com.stpl.gtn.gtn2o.ws.forecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsSaveIOService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

@RestController

public class GtnWsProjectionSaveController {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProjectionSaveController.class);

	@Autowired
	private GtnWsReturnsSaveIOService fileService;

	public GtnWsReturnsSaveIOService getFileService() {
		return fileService;
	}

	public void setFileService(GtnWsReturnsSaveIOService fileService) {
		this.fileService = fileService;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_MASTER_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveProjectionMaster(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnFrameworkQueryEngineMainConfig mainConfig = new GtnFrameworkQueryEngineMainConfig();
			mainConfig = fileService.configureDataArray(mainConfig, gtnWsRequest);
			mainConfig = fileService.buildQueryConfigForProjection(mainConfig, gtnWsRequest);
			boolean saveFlag = fileService.executeQuery(mainConfig);
			if (saveFlag) {
				forecastResponse = fileService.getSavedProjectionMasterSid(gtnUIFrameworkWebserviceResponse, mainConfig,
						forecastResponse);
			}
			gtnUIFrameworkWebserviceResponse.setGtnWsForecastResponse(forecastResponse);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveProjectionMaster method", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		} finally {
			logger.info("Exit saveProjectionMaster");
		}

	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_VIEW_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveViewMaster(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnFrameworkQueryEngineMainConfig mainConfig = new GtnFrameworkQueryEngineMainConfig();
			mainConfig = fileService.configureDataArrayForView(mainConfig, gtnWsRequest);
			mainConfig = fileService.buildQueryConfigForView(mainConfig, gtnWsRequest);
			fileService.executeQuery(mainConfig);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveViewMaster method", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		} finally {
			logger.info("Exit saveProjectionMaster");
		}

	}

}
