package com.stpl.gtn.gtn2o.ws.module.itemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemaster.service.GtnWsItemMasterAddService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
@RequestMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE)
public class GtnWsItemMasterAddCotroller {
	public GtnWsItemMasterAddCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsItemMasterAddCotroller.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsItemMasterAddService addWebservice;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_IM_BRAND_MASTER_FETCH_INFORMATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchBrandMasterInfo(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter fetchBrandMasterInfo");
			addWebservice.fetchBrandMasterInfo(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting fetchBrandMasterInfo Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit fetchBrandMasterInfo");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_IM_COMPANY_INFO_FETCH_INFORMATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchCompanyInfo(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter fetchCompanyInfo");
			addWebservice.fetchCompanyInfo(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting fetchCompanyInfo Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit fetchCompanyInfo");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_FETCH_INFORMATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchItemMasterInfo(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter fetchItemMaster");
			addWebservice.fetchItemMaster(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting fetchItemMaster Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit fetchItemMaster");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteItemMaster(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info(GtnFrameworkWebserviceConstant.ENTER_DELETE_ITEM_MASTER);
			addWebservice.deleteItemMaster(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info(GtnFrameworkWebserviceConstant.EXIT_DELETE_ITEM_MASTER);
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_INDENTIFIER_QUALIFIER_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteItemQualifier(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info(GtnFrameworkWebserviceConstant.ENTER_DELETE_ITEM_MASTER);
			addWebservice.deleteItemQualifier(gtnWsRequest);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info(GtnFrameworkWebserviceConstant.EXIT_DELETE_ITEM_MASTER);
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_PRICING_QUALIFIER_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deletePricingQualifier(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info(GtnFrameworkWebserviceConstant.ENTER_DELETE_ITEM_MASTER);
			addWebservice.deletePricingQualifier(gtnWsRequest);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info(GtnFrameworkWebserviceConstant.EXIT_DELETE_ITEM_MASTER);
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_PRICING_TABLE_LOAD_SERVICE)
	public GtnUIFrameworkWebserviceResponse loadItemPricing(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			logger.info("Enter Pricing load Data");
			addWebservice.loadPricingTempData(gtnWsRequest, gtnResponse);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit Pricing load Data");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_PRICING_CREATE_TEMP_TABLE)
	public GtnUIFrameworkWebserviceResponse createTempTable(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			logger.info("Creating Temp table");
			addWebservice.createTembTable(gtnWsRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_PRICING_UPDATE_SERVICE)
	public GtnUIFrameworkWebserviceResponse updatePricingData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			logger.info("Updating in table");
			addWebservice.updateData(gtnWsRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_PRICING_INSERT_SERVICE)
	public GtnUIFrameworkWebserviceResponse insertPricingData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			logger.info("Insert in table");
			addWebservice.insertInPricingTembTable(gtnWsRequest, gtnResponse);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception in Insert Pricing Data", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_PRICING_DROP_TEMP_TABLE_SERVICE)
	public GtnUIFrameworkWebserviceResponse dropTempTable(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			logger.info("Droping Temb Table");
			addWebservice.dropPricingTempTable(gtnWsRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception in Droping Temb Table", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_PRICING_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse validatePricingDate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			logger.info("Validating Temb data");
			addWebservice.validatePricingData(gtnWsRequest, gtnResponse);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception in Validating Temb data", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		}
	}

}
