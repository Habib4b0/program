package com.stpl.gtn.gtn2o.ws.module.itemaster.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemaster.service.GtnWsItemMasterSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

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
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

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
		int countUpdate=performUpdateForItemIdWithStatusD(gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean());
		if(countUpdate==1)
		{
		int automatedSystemId=getSysIdForItemIdWithStatusD(gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean());
		gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean().setItemMasterSid(automatedSystemId);
		
		}
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			GtnWsItemMasterBean bean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean();
			Integer itemMasterSid = bean.getGtnWsItemMasterInfoBean().getItemMasterSid();
			if (itemMasterSid == null || itemMasterSid == 0) {
				itemMasterSid = imSaveWebservice.saveItemMaster(gtnWsRequest, response);
				bean.getGtnWsItemMasterInfoBean().setItemMasterSid(itemMasterSid);
			} else {
				logger.info("inside Update");
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
	private int performUpdateForItemIdWithStatusD(GtnWsItemMasterBean gtnWsItemMasterBean) {
		boolean isItemIdExist = false;
		List<Long> resultsDb4 = checkIfItemIdExistsWithStatusD(gtnWsItemMasterBean);
		if (resultsDb4 != null) {
			isItemIdExist = (long) resultsDb4.size() == 1;
		}
		
		int countUpdate = 0;
		if (isItemIdExist) {
			  List<Integer> sysId=new ArrayList<>();
			    List<String> itemIdCriteria = new ArrayList<>();
				itemIdCriteria.add(gtnWsItemMasterBean.getGtnWsItemMasterInfoBean().getItemId());
				sysId.add(gtnWsItemMasterBean.getGtnWsItemMasterInfoBean().getItemMasterSid());
			try {
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery(sysId, "updateIdentifierWithStatusD"));
				countUpdate = gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery(itemIdCriteria, "updateItemIdWithStatusA"));
			} catch (GtnFrameworkGeneralException e) {
				logger.info("Error in Updating");
			}
		}
		return countUpdate;
	}
	@SuppressWarnings("unchecked")
	private int getSysIdForItemIdWithStatusD(GtnWsItemMasterBean gtnWsItemMasterBean) {
		    List<Integer> sysId=new ArrayList<>();
		    List<String> itemIdCriteria = new ArrayList<>();
			itemIdCriteria.add(gtnWsItemMasterBean.getGtnWsItemMasterInfoBean().getItemId());
			try {
				sysId=(List<Integer>) (gtnSqlQueryEngine.executeSelectQuery(gtnWsSqlService.getQuery(itemIdCriteria, "selectItemMasterSysId")));
				
			} catch (GtnFrameworkGeneralException e) {
				logger.info("Error in Updating");
			}
		
		return sysId.get(0);
	}
	

	@SuppressWarnings("unchecked")
	private List<Long> checkIfItemIdExistsWithStatusD(GtnWsItemMasterBean gtnWsItemMasterBean) {
		List<String> itemIdCriteria = new ArrayList<>();
		itemIdCriteria.add(gtnWsItemMasterBean.getGtnWsItemMasterInfoBean().getItemId());
		
		List<Long> resultsDb3 = new ArrayList<>();
		try {
			resultsDb3 = (List<Long>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(itemIdCriteria, "getItemIdWithStatusD"));
			
		} catch (GtnFrameworkGeneralException e) {

			logger.error("Exception Occured while Checking Whetehr Company Exists");
		}
		return resultsDb3;
	}
	
	
}
