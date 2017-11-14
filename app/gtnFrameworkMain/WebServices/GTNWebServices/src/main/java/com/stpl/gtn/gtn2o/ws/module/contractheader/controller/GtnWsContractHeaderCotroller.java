package com.stpl.gtn.gtn2o.ws.module.contractheader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractheader.service.GtnWsContractHeaderService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
@RequestMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE)
public class GtnWsContractHeaderCotroller {
    public GtnWsContractHeaderCotroller(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractHeaderCotroller.class);

	@Autowired
	private GtnWsContractHeaderService contractHeaderService;

	@PostMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveService(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int contractMasterSid = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean()
				.getContractMasterSid();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter saveService");
			if (contractMasterSid == 0) {
				contractMasterSid = contractHeaderService.saveContractHeader(gtnWsRequest, gtnResponse);
			} else {
				contractHeaderService.updateCompanyGrpQuery(gtnWsRequest, gtnResponse);
			}

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit saveService with sid :" + contractMasterSid + " and update in details :" + count);
		}
	}

	@PostMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_ALIAS_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteContractAliasService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter deleteContractAliasService");
			contractHeaderService.deletContractAlias(gtnWsRequest);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting deleteContractAliasService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit deleteContractAliasService :");
		}
	}

	@PostMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteContractMasterService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter deleteContractMasterService");
			contractHeaderService.deletContractMaster(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting deleteContractMasterService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit deleteContractMasterService :");
		}
	}

	@PostMapping(value = GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_FETCH_INFORMATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchContractHeaderService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter fetchContractHeaderService");
			contractHeaderService.getCompanyHeaderFetchQuery(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting fetchContractHeaderService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit fetchContractHeaderService :");
		}
	}
}
