package com.stpl.gtn.gtn2o.ws.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.transaction.GtnWsTransactionResponse;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import com.stpl.gtn.gtn2o.ws.transaction.service.GtnWsTransactionService;

/**
 *
 * @author STPL
 */
@RestController
@RequestMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE)
public class GtnWsTransactionSearchController {
    public GtnWsTransactionSearchController(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsTransactionSearchController.class);
	@Autowired
	private GtnWsTransactionService gtnWsTransactionService;
	

	@SuppressWarnings({ "unchecked" })
	@PostMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETSEARCHRESULTS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getSearchResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.debug("-------getColumnDataTypegetColumnDataTypegetColumnDataTypegetColumnDataType-------");
		Object resultSet = null;
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnWsGeneralRequest generalRequest = gtnWsRequest.getGtnWsGeneralRequest();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsSearchRequest gtnWsSearchRequest = gtnWsRequest.getGtnWsSearchRequest();

			GtnWsTransactionResponse gtnWsTransactionResponse = new GtnWsTransactionResponse();

			if (!generalRequest.isExcel()) {
				resultSet = gtnWsTransactionService.getSearchDetails(gtnWsRequest.getGtnWsSearchRequest(),
						gtnWsRequest.getGtnWsSearchRequest().isCount(), false);

				if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
					logger.info("Size of listt" + (int) resultSet);
					gtnSerachResponse.setCount((int) resultSet);
				} else {

					GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();

					gtnUIFrameworkDataTable.addData((List<Object[]>) resultSet);
					gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);

				}

			} else {
				gtnUIFrameworkWebserviceResponse = gtnWsTransactionService.getExcelFile(
						gtnUIFrameworkWebserviceResponse, generalRequest, gtnWsSearchRequest, gtnWsRequest);
			}
			gtnUIFrameworkWebserviceResponse.setGtnWsTransactionResponse(gtnWsTransactionResponse);

			gtnUIFrameworkWebserviceResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (GtnFrameworkGeneralException e) {
			generalResponse.setGtnGeneralException(e);
		}
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(generalResponse);

		return gtnUIFrameworkWebserviceResponse;

	}

	@PostMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETVIEWRESULTS_SERVICE)
	public GtnUIFrameworkWebserviceResponse getViewResults(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.debug("-------getColumnDataTypegetColumnDataTypegetColumnDataTypegetColumnDataType-------");
		GtnWsTransactionRequest gtnWsTransactionRequest = gtnWsRequest.getGtnWsTransactionRequest();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {

			Object[] result = gtnWsTransactionService.getViewDetails(gtnWsTransactionRequest);

			GtnWsTransactionResponse gtnWsTransactionResponse = new GtnWsTransactionResponse();
			gtnWsTransactionResponse.setViewResults(result);
			gtnUIFrameworkWebserviceResponse.setGtnWsTransactionResponse(gtnWsTransactionResponse);

		} catch (GtnFrameworkGeneralException e) {
			generalResponse.setGtnGeneralException(e);
		}
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnUIFrameworkWebserviceResponse;

	}
        
	@PostMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse getValidationResults(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsTransactionRequest gtnWsTransactionRequest = gtnWsRequest.getGtnWsTransactionRequest();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		String result = gtnWsTransactionService.getValidationForRunning(gtnWsTransactionRequest.getTableName());
		gtnUIFrameworkWebserviceResponse.setOutBountData(new Object[]{result});
		return gtnUIFrameworkWebserviceResponse;

	}
}
