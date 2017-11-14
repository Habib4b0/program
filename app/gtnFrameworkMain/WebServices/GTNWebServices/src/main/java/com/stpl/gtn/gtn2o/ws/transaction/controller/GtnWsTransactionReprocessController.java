package com.stpl.gtn.gtn2o.ws.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import com.stpl.gtn.gtn2o.ws.transaction.service.GtnWsTransactionReprocessIOService;

@RestController
@RequestMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE)
public class GtnWsTransactionReprocessController {
    public GtnWsTransactionReprocessController(){
        /**
         * empty constructor
         */
    }

	@Autowired
	private GtnWsTransactionReprocessIOService gtnWsTransactionReprocessIOService;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsTransactionReprocessController.class);

	@PostMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_REPROCESS_SERVICE)
	public GtnUIFrameworkWebserviceResponse reprocessRecords(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsTransactionRequest gtnWsTransactionRequest = gtnWsRequest.getGtnWsTransactionRequest();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnWsTransactionReprocessIOService.getReprocessRecords(gtnWsTransactionRequest);
		} catch (GtnFrameworkGeneralException e) {

			logger.debug("Reprocess Web" + gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().isSucess());
		}
		return gtnUIFrameworkWebserviceResponse;
	}

}