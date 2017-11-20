package com.stpl.gtn.gtn2o.ws.forecast.controller;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsEditIOService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 *
 * @author stpl
 */

@RestController
public class GtnWsReturnsEditController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsEditController.class);

	@Autowired
	private GtnWsReturnsEditIOService gtnWsReturnsEditIOService;

	public GtnWsReturnsEditIOService getGtnWsReturnsEditIOService() {
		return gtnWsReturnsEditIOService;
	}

	public void setGtnWsReturnsEditIOService(GtnWsReturnsEditIOService gtnWsReturnsEditIOService) {
		this.gtnWsReturnsEditIOService = gtnWsReturnsEditIOService;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_ON_EDIT_SERVICE)
	public GtnUIFrameworkWebserviceResponse createFileForEditMode(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
		GtnForecastBean gtnForecastBean = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();
		Map<String, List<String>> queryParameters = gtnForecastBean.getQueryParameters();

		String projectionMasterSid = String.valueOf(gtnForecastBean.getProjectionMasterSid());

		queryParameters.put("RETURNS_FORECAST_PROJECTION_" + gtnForecastBean.getMode(),
				Arrays.asList(queryParameters
						.get(GtnFrameworkWebserviceConstant.RETURNS_FORECAST_ACTUAL + gtnForecastBean.getMode()).get(0),
						projectionMasterSid));
		queryParameters.put("RETURNS_FORECAST_MASTER_" + gtnForecastBean.getMode(),
				Arrays.asList(queryParameters
						.get(GtnFrameworkWebserviceConstant.RETURNS_FORECAST_ACTUAL + gtnForecastBean.getMode()).get(0),
						projectionMasterSid));
		queryParameters.put("RETURNS_FORECAST_MASTER", Arrays.asList(queryParameters
				.get(GtnFrameworkWebserviceConstant.RETURNS_FORECAST_ACTUAL + gtnForecastBean.getMode()).get(0)));
		gtnForecastBean.setQueryParameters(queryParameters);
		gtnForecastBean.setHierarchyType("Product");
		try {
			gtnWsReturnsEditIOService.getCreatedFiles(gtnForecastBean, false);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting itemAdditionSearch Query", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		} finally {
			LOGGER.info("Exit createFileForEditMode");
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_PROJECTION_SELECTION_DETAILS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getProjectionSelectionDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		LOGGER.info("Enter getProjectionSelectionDetails");
		GtnWsForecastRequest forecastRequest = gtnWsRequest.getGtnWsForecastRequest();

		GtnForecastBean forecastBean = forecastRequest.getGtnForecastBean();

		LOGGER.info("Exit getProjectionSelectionDetails");
		return gtnWsReturnsEditIOService
				.configureResponseForProjectionSelection(String.valueOf(forecastBean.getProjectionMasterSid()));
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_PROJECTION_VIEW_DETAILS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getProjectionViewDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter getProjectionViewDetails");
		GtnWsForecastRequest forecastRequest = gtnWsRequest.getGtnWsForecastRequest();

		GtnForecastBean forecastBean = forecastRequest.getGtnForecastBean();

		LOGGER.info("Exit getProjectionViewDetails");
		return gtnWsReturnsEditIOService
				.configureResponseForProjectionView(String.valueOf(forecastBean.getProjectionMasterSid()));
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_EXISTING_VIEW_DETAILS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getExistingView(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		return gtnWsReturnsEditIOService.configureResponseForExistingViewName();
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_DS_TAB_CHANGE_SERVICE)
	public GtnUIFrameworkWebserviceResponse createFileOnDSReload(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnForecastBean gtnForecastBean = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();
		try {
			gtnWsReturnsEditIOService.getCreatedFiles(gtnForecastBean, true);
			return null;
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error("Exception while Excuting itemAdditionSearch Query", ex);
			return null;
		}
	}

}
