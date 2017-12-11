package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.service.GtnWsCfpSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.cfpresponse.GtnWsCfpReponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
@RequestMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE)
public class GtnWsCfpSaveCotroller {
	public GtnWsCfpSaveCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCfpSaveCotroller.class);
	@Autowired
	private GtnWsCfpSaveService cfpSaveWebservice;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_COLUMN_UPDATE_FIELD_SERVICE)
	public GtnUIFrameworkWebserviceResponse companiesTabUpdateField(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companiesTabUpdateField");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		int count = 0;

		try {

			String query = cfpSaveWebservice.updateFieldsQuery(gtnWsRequest);
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getCfpDelete Query", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit companiesTabUpdateField and inserted or updated " + count + " rows");
		}

	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveCompanyFamilyPlan(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveCfpService");
		int countUpdate = 0;
		if (gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().getCfpSid() == null || gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().getCfpSid() == 0) {
			countUpdate = performUpdateForCfpIdWithStatusD(
					gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo());
		}
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalReponse = new GtnWsGeneralResponse();
		response.setGtnWsGeneralResponse(generalReponse);
		int count = 0;

		try {
			if (countUpdate == 1) {
				int automatedSystemId = getSysIdForCfpIdWithStatusD(
						gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo());
				gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().setCfpSid(automatedSystemId);

			}
			GtnCFamilyPlan bean = gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan();
			Integer cfpModelSid = bean.getCfpInfo().getCfpSid();
			if (cfpModelSid == null || cfpModelSid == 0) {
				cfpModelSid = cfpSaveWebservice.saveCfpQuery(bean, gtnWsRequest);
				bean.getCfpInfo().setCfpSid(cfpModelSid);
			} else {
				cfpSaveWebservice.updateCfpQuery(gtnWsRequest);

			}
			cfpSaveWebservice.saveNotesTabDetails(bean);

			String cfpDetailsquery = cfpSaveWebservice.saveCfpDetailsQuery();
			Object[] params = { cfpModelSid, gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(cfpDetailsquery, params, types);
			GtnWsCfpReponse cfpResponse = new GtnWsCfpReponse();
			cfpResponse.setGtnCFamilyPlan(bean);
			response.setGtnWsCfpReponse(cfpResponse);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception saveCompanyFamilyPlan", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit saveCfpService and inserted " + count + "  rows");
		}

	}

	@SuppressWarnings("unchecked")
	private int getSysIdForCfpIdWithStatusD(GtnCFamilyPlanInformation cfpInfo) {
		List<String> cfpCriteria = new ArrayList<>();
		cfpCriteria.add(cfpInfo.getCfpId());
		cfpCriteria.add(cfpInfo.getCfpNo());
		List<Integer> sysId = new ArrayList<>();
		try {
			sysId = (List<Integer>) (gtnSqlQueryEngine.executeSelectQuery(gtnWsSqlService.getQuery(cfpCriteria, "getSysIdCfpIdAndCfpNoWithStatusD")));

		} catch (GtnFrameworkGeneralException e) {
			logger.info("Error in Updating");
		}

		return sysId.get(0);
	}

	private int performUpdateForCfpIdWithStatusD(GtnCFamilyPlanInformation cfpInfo) {
		boolean isCompanyExist = false;
		List<Long> resultsDb4 = checkIfCfpIdExistsWithStatusD(cfpInfo);
		if (resultsDb4 != null) {
			isCompanyExist = (long) resultsDb4.size() == 1;
		}
		List<String> cfpCriteria = new ArrayList<>();
		cfpCriteria.add(cfpInfo.getCfpId());
		cfpCriteria.add(cfpInfo.getCfpNo());
		int countUpdate = 0;
		if (isCompanyExist) {
		try {
				countUpdate = gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery(cfpCriteria, "UpdateCfpIdAndCfpNoWithStatusA"));
			} catch (GtnFrameworkGeneralException e) {
				logger.info("Error in Updating");
			}
		}
		return countUpdate;

	}

	@SuppressWarnings("unchecked")
	private List<Long> checkIfCfpIdExistsWithStatusD(GtnCFamilyPlanInformation cfpInfo) {

		List<String> cfpCriteria = new ArrayList<>();
		cfpCriteria.add(cfpInfo.getCfpId());
		cfpCriteria.add(cfpInfo.getCfpNo());
		List<Long> resultsDb3 = new ArrayList<>();
		try {
			resultsDb3 = (List<Long>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(cfpCriteria, "getCfpIdWithStatusD"));

		} catch (GtnFrameworkGeneralException e) {

			logger.error("Exception Occured while Checking Whetehr CFP Exists");
		}
		return resultsDb3;
	}

}
