/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnReturnsExcelLoadService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsExcelHeaderService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsHeaderService;
import com.stpl.gtn.gtn2o.ws.forecast.service.HeaderGeneratorService;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsCalculationService;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnTreeTableLoadService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnwsExcelRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;
import net.sourceforge.jeval.EvaluationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author STPL
 */
@RestController
public class GtnWsReturnsProjectionController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsProjectionController.class);

	@Autowired
	private GtnWsReturnsHeaderService headerService;

	@Autowired
	private GtnWsCalculationService gtnWsCalculationService;

	@Autowired
	GtnTreeTableLoadService gtnTreeTableLoadService;

	@Autowired
	GtnWsReturnsExcelHeaderService excelHeaderService;

	@Autowired
	GtnReturnsExcelLoadService returnsExcelLoadService;
        @Autowired
	private HeaderGeneratorService reportHeaderService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCount(@RequestBody GtnUIFrameworkWebserviceRequest requester) {
		LOGGER.info("Count Call");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			response.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			response.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = requester.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse returnPagedTreeTableBean = new GtnWsPagedTreeTableResponse();
			returnPagedTreeTableBean.setTableCount(gtnTreeTableLoadService.getCount(request.getGtnForecastBean()));
			response.setGtnWSPagedTreeTableResponse(returnPagedTreeTableBean);
			return response;
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.getGtnWsGeneralResponse().setSucess(false);
			response.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return response;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE)
	public GtnUIFrameworkWebserviceResponse loadBulkData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		LOGGER.info("Bulk loadData");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			response.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			response.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse returnPagedTreeTableBean = new GtnWsPagedTreeTableResponse();
			returnPagedTreeTableBean
					.setGtnWsRecordBeanList(gtnTreeTableLoadService.loadBulkData(request.getGtnForecastBean()));
			response.setGtnWSPagedTreeTableResponse(returnPagedTreeTableBean);
			return response;
		} catch (GtnFrameworkGeneralException | EvaluationException ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.getGtnWsGeneralResponse().setSucess(false);
			response.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return response;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE)
	public GtnUIFrameworkWebserviceResponse loadBulkCount(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		LOGGER.info("Bulk loadData");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			response.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			response.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse returnPagedTreeTableBean = new GtnWsPagedTreeTableResponse();
			returnPagedTreeTableBean.setCountMap(gtnTreeTableLoadService.loadBulkCount(request.getGtnForecastBean()));
			response.setGtnWSPagedTreeTableResponse(returnPagedTreeTableBean);
			return response;
		} catch (GtnFrameworkGeneralException | EvaluationException ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.getGtnWsGeneralResponse().setSucess(false);
			response.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return response;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getConfigureLeftHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse leftHeader = headerService.getReturnsLeftTableColumns(request);
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(leftHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getConfiguredRightHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

			GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
			GtnWsPagedTreeTableResponse rightHeader = headerService
					.getReturnsRightTableColumns(request.getGtnForecastBean());
			gtnUIFrameworkWebserviceResponse.setGtnWSPagedTreeTableResponse(rightHeader);
			return gtnUIFrameworkWebserviceResponse;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
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
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage(), ex);
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
			LOGGER.error(ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_CALCULATE_SERVICE)
	public GtnUIFrameworkWebserviceResponse calculation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnWsCalculationService.methodologyCalculation(
					gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest().getGtnForecastBean());
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
		return gtnUIFrameworkWebserviceResponse;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_HEADERS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getConfiguredExcelRightHeaders(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);

			GtnwsExcelRequest request = gtnUIFrameworkWebserviceRequest.getGtnwsExcelRequest();
			//
			GtnForecastBean gtnForecastBean = objectMapper.convertValue(request.getInputs()[0], GtnForecastBean.class);
			GtnWsForecastResponse rightHeader = excelHeaderService.getReturnsTableColumns(gtnForecastBean);
			gtnUIFrameworkWebserviceResponse
					.setGtnWsExcelResponse(returnsExcelLoadService.convertForecastResponsetoExcelResponse(rightHeader));
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage(), ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		}
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_DATA_SERVICE)
	public GtnUIFrameworkWebserviceResponse getExcelData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		LOGGER.info(" get Data for Excel");
		try {
			GtnwsExcelRequest request = gtnUIFrameworkWebserviceRequest.getGtnwsExcelRequest();
			GtnWsForecastResponse responseDto = new GtnWsForecastResponse();
			GtnForecastBean gtnForecastBean = objectMapper.convertValue(request.getInputs()[0], GtnForecastBean.class);
			responseDto.setForecastPagedTableBeanList(returnsExcelLoadService.loadBulkData(gtnForecastBean));
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
			response.setGtnWsExcelResponse(returnsExcelLoadService.convertForecastResponsetoExcelResponse(responseDto));
			return response;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	}

}
