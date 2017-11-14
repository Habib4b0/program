package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;

@RestController
@RequestMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE)
public class GtnWsIfpSaveCotroller {
	public GtnWsIfpSaveCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsIfpSaveCotroller.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnWsIfpSaveService ifpSaveWebservice;

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_COLUMN_UPDATE_FIELD_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemsTabUpdateField(
			@RequestBody GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest) {
		logger.info("Enter itemsTabUpdateField");
		GtnUIFrameworkWebserviceResponse ifpItemsResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			Object[] params = { itemsTabUpdateRequest.getGtnWsGeneralRequest().getUserId(),
					itemsTabUpdateRequest.getGtnWsGeneralRequest().getSessionId() };
			String ifpItemsSelectedQuery = ifpSaveWebservice.updateFieldsQuery(itemsTabUpdateRequest);
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(ifpItemsSelectedQuery, params, types);
			return ifpItemsResponse;
		} catch (Exception e) {
			ifpItemsResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemsTabUpdateField", e);
			ifpItemsResponse.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return ifpItemsResponse;
		} finally {
			logger.info("Exit itemsTabUpdateField and inserted or updated " + count + " rows");
		}

	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveCompanyFamilyPlan(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveCfpService");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			GtnIFamilyPlanBean bean = gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan();
			Integer ifpModelSid = bean.getIfpInfo().getIfpSid();
			if (ifpModelSid == null || ifpModelSid <= 0) {
				ifpModelSid = ifpSaveWebservice.saveCfpQuery(bean);
				bean.getIfpInfo().setIfpSid(ifpModelSid);
			} else {
				ifpSaveWebservice.updateCfpQuery(bean);
			}
			ifpSaveWebservice.saveNotesTabDetails(bean);
			Object[] params = { ifpModelSid, gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			String cfpDetailsquery = ifpSaveWebservice.saveCfpDetailsQuery();
			GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
			bean.getIfpInfo().setIfpSid(ifpModelSid);
			gtnWsIfpReponse.setGtnIFamilyPlan(bean);
			response.setGtnWsIfpReponse(gtnWsIfpReponse);
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(cfpDetailsquery, params, types);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveCompanyFamilyPlan", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit saveCfpService and inserted " + count + "  rows");
		}

	}

}
