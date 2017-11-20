package com.stpl.gtn.gtn2o.ws.module.itemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemaster.service.GtnWsItemMasterSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
@RequestMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE)
public class GtnWsItemMasterSaveCotroller {
    public GtnWsItemMasterSaveCotroller(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsItemMasterSaveCotroller.class);

	@Autowired
	private GtnWsItemMasterSaveService imSaveWebservice;

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_INDENTIFIER_QUALIFER_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemMasterIdentifierQualifierSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter itemMasterIdentifierQualifierSaveService");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		try {
			imSaveWebservice.saveItemMasterIndetifierQualifier(gtnWsRequest);

			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemMasterIdentifierQualifierSaveService", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit itemMasterIdentifierQualifierSaveService ");
		}

	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_PRICING_QUALIFER_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemMasterPricingQualifierSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter itemMasterPricingQualifierSaveService");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		try {
			imSaveWebservice.saveItemMasterPricingQualifier(gtnWsRequest);

			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemMasterPricingQualifierSaveService", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit itemMasterPricingQualifierSaveService ");
		}

	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveItemMaster(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveItemMaster");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			GtnWsItemMasterBean bean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean();
			Integer itemMasterSid = bean.getGtnWsItemMasterInfoBean().getItemMasterSid();
			if (itemMasterSid == null || itemMasterSid == 0) {
				itemMasterSid = imSaveWebservice.saveItemMaster(gtnWsRequest, response);
				bean.getGtnWsItemMasterInfoBean().setItemMasterSid(itemMasterSid);
			} else {
				imSaveWebservice.updateItemMaster(gtnWsRequest, response);
			}
			imSaveWebservice.saveNotesTabDetails(bean);

			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveItemMaster", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit saveItemMaster and inserted " + count + "  rows");
		}
	}

	@PostMapping(value = GtnWsItemMasterContants.GTN_WS_CP_DETAILS_INSERT_FOR_ITEMS_INSERT_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveCPDetailsForItems(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveCPDetailsForItems");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		try {
			int itemMasterSid = Integer
					.parseInt(gtnWsRequest.getGtnWsGeneralRequest().getExtraParameter().toString());
			imSaveWebservice.insertInCPDetailsForItems(itemMasterSid);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		}
		logger.info("Exit saveCPDetailsForItems");
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;

	}
}
