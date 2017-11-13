package com.stpl.gtn.gtn2o.ws.forecast.commercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.commercial.service.GtnWsCommercialFileService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnWsCommercialFileController {

	private static final GtnWSLogger GTN_LOGGER = GtnWSLogger.getGTNLogger(GtnWsCommercialFileController.class);

	@Autowired
	private GtnWsCommercialFileService gtnWsCommercialFileService;

	@PostMapping(value = "commercial/writeSalesFiles")
	public GtnUIFrameworkWebserviceResponse writeSalesBaseFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		try {

			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();

			gtnWsCommercialFileService.writeSalesBaseFiles(gtnForecastBean);

		} catch (Exception e) {
			GTN_LOGGER.error("Error in Web Service Commercial file Controller", e);
		}

		return null;
	}

	@PostMapping(value = "commercial/writeDiscountFiles")
	public GtnUIFrameworkWebserviceResponse writeDiscountBaseFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		try {

			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();

			gtnWsCommercialFileService.writeDiscountBaseFiles(gtnForecastBean);

		} catch (Exception e) {
			GTN_LOGGER.error("Error in Web Service Commercial DiscountBase files ", e);
		}

		return null;
	}

	@PostMapping(value = "commercial/writeCustomerTreeFiles")
	public GtnUIFrameworkWebserviceResponse writeCustomerTreeFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		try {

			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();

			gtnWsCommercialFileService.writeCustomerTreeFile(gtnForecastBean);

		} catch (Exception e) {
			GTN_LOGGER.error("Error in Web Service Commercial Customer Tree files ", e);
		}

		return null;
	}

	@PostMapping(value = "commercial/writeProductTreeFiles")
	public GtnUIFrameworkWebserviceResponse writeProductTreeFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		try {

			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();

			gtnWsCommercialFileService.writeProductTreeFile(gtnForecastBean);

		} catch (Exception e) {
			GTN_LOGGER.error("Error in Web Service Commercial Product Tree files ", e);
		}

		return null;
	}

	@PostMapping(value = "commercial/writeSalesGroupedFiles")
	public GtnUIFrameworkWebserviceResponse writeSalesGroupedFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		try {

			GtnForecastBean gtnForecastBean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest()
					.getGtnForecastBean();

			gtnWsCommercialFileService.writeSalesCustomerGroupedFile(gtnForecastBean);

		} catch (Exception e) {
			GTN_LOGGER.error("Error in Web Service Commercial Sales Group files ", e);
		}

		return null;
	}

}
