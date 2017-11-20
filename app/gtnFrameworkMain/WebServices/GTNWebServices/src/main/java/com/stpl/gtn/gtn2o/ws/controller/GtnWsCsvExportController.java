package com.stpl.gtn.gtn2o.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCsvExportResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnCsvExportService;

@RestController
@RequestMapping()
public class GtnWsCsvExportController {

	public GtnWsCsvExportController() {
		super();
	}

	@Autowired
	private GtnCsvExportService gtnCsvExportService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCsvExportController.class);

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CSV_EXPORT_FILE_SERVICE, method = RequestMethod.POST, produces = "application/json")
	public GtnUIFrameworkWebserviceResponse getCsvExportFile(
			@RequestBody GtnUIFrameworkWebserviceRequest csvExportRequest) {

		GtnUIFrameworkWebserviceResponse csvExportFileResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCsvExportResponse gtnWsCsvExportResponse = new GtnWsCsvExportResponse();
		csvExportFileResponse.setGtnWsCsvExportResponse(gtnWsCsvExportResponse);
		try {
			gtnWsCsvExportResponse.setFileName(gtnCsvExportService.getCsvExportFile(csvExportRequest));
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error("Exception in GTN_CSV_EXPORT_FILE_SERVICE", ex);
		}

		return csvExportFileResponse;
	}
}
