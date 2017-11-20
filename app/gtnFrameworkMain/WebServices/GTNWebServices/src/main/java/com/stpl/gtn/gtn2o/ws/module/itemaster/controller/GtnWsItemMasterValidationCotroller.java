package com.stpl.gtn.gtn2o.ws.module.itemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemaster.service.GtnWsItemMasterValidationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
@RequestMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE)
public class GtnWsItemMasterValidationCotroller {
    public GtnWsItemMasterValidationCotroller(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsItemMasterValidationCotroller.class);

	@Autowired
	private GtnWsItemMasterValidationService imWebservice;

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_IM_IDENTIFIER_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkItemIdentifierNameExist(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.info(" inside checkItemIdentifierNameExist method");
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter checkItemIdentifierNameExist");
			imWebservice.checkIdentifierExist(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkItemIdentifierNameExist Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit checkItemIdentifierNameExist");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_IDENTIFIER_QUALIFIER_NAMEANDVALUE_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkItemIdentifierQualifierValueExist(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.info(" inside checkItemIdentifierQualifierValueExist method");
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter checkItemIdentifierQualifierValueExist");
			imWebservice.checkItemIdentifierQualfierValueExist(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkItemIdentifierQualifierValueExist Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit checkItemIdentifierQualifierValueExist");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_PRICING_QUALIFIER_NAMEANDVALUE_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkItemPricingQualifierValueExist(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.info(" inside checkItemPricingQualifierValueExist method");
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter checkItemPricingQualifierValueExist");
			imWebservice.checkItemPricingQualifierValueExist(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkItemPricingQualifierValueExist Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit checkItemPricingQualifierValueExist");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_ID_NAME_NDC8_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkItemMasterIdNameNdc8Exist(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.info(" inside checkItemMasterIdNameNdc8Exist method");
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter checkItemMasterIdNameNdc8Exist");
			imWebservice.checkItemMasterIdNameNdc8Exist(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkItemMasterIdNameNdc8Exist Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit checkItemMasterIdNameNdc8Exist");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_INDENTIFIER_QUALIFER_DELETE_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkItemIdentifierQualifierValueUsed(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.info(" inside checkItemIdentifierQualifierValueUsed method");
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter checkItemIdentifierQualifierValueUsed");
			imWebservice.checkItemIdentifierQualfierValueUsed(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkItemIdentifierQualifierValueUsed Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit checkItemIdentifierQualifierValueUsed");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_PRICING_QUALIFER_DELETE_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkPricingIdentifierQualifierValueUsed(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.info(" inside checkItemIdentifierQualifierValueUsed method");
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter checkItemIdentifierQualifierValueUsed");
			imWebservice.checkPricingIdentifierQualifierValueUsed(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkItemIdentifierQualifierValueUsed Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit checkItemIdentifierQualifierValueUsed");
		}
	}

}
