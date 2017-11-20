package com.stpl.gtn.gtn2o.ws.module.contractheader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractheader.service.GtnWsContractHeaderValidationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
@RequestMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE)
public class GtnWsContractHeaderValidationCotroller {
    public GtnWsContractHeaderValidationCotroller(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractHeaderValidationCotroller.class);

	@Autowired
	private GtnWsContractHeaderValidationService chWebservice;

	@PostMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_ID_NO_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse contractIdAndNoValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter contractIdAndNoValidation");
			chWebservice.checkContractIdNameExist(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionSearch Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionSearch");
		}
	}

}
