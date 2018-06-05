package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.List;

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
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

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
			int countUpdate = 0;
			if (bean.getIfpInfo().getIfpSid() == null || bean.getIfpInfo().getIfpSid() == 0) {
				countUpdate = doUpdateForDeletedPsId(bean);
			}
			if (countUpdate == 1) {
				int automatedSystemId = getSysIdForPsIdWithStatusD(bean);
				bean.getIfpInfo().setIfpSid(automatedSystemId);
				ifpModelSid = automatedSystemId;

			}
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

	private int doUpdateForDeletedPsId(GtnIFamilyPlanBean bean) throws GtnFrameworkGeneralException {
		boolean isCompanyExist = false;
		List<Long> resultsDb4 = checkIfPsIdExistsWithStatusD(bean);
		if (resultsDb4 != null) {
			isCompanyExist = (long) resultsDb4.size() == 1;
		}
		int countUpdate = 0;
		if (isCompanyExist) {
			try {
				countUpdate = gtnSqlQueryEngine.executeInsertOrUpdateQuery(
						gtnWsSqlService.getQuery(psIdAndNo(bean), "UpdateIfpIdAndPsNoWithStatusA"));
			} catch (GtnFrameworkGeneralException e) {
				throw new GtnFrameworkGeneralException("Error in Updating", e);
			}
		}
		return countUpdate;

	}

	private List<Long> checkIfPsIdExistsWithStatusD(GtnIFamilyPlanBean bean) throws GtnFrameworkGeneralException {

		List<Long> resultsList = new ArrayList<>();
		try {
			resultsList = (List<Long>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(psIdAndNo(bean), "getIfpIdWithStatusD"));

		} catch (GtnFrameworkGeneralException e) {
			throw new GtnFrameworkGeneralException("Exception Occured while Checking Whetehr IFP Exists", e);
		}
		return resultsList;
	}

	@SuppressWarnings("unchecked")
	private int getSysIdForPsIdWithStatusD(GtnIFamilyPlanBean bean) throws GtnFrameworkGeneralException {
		List<Integer> sysId = new ArrayList<>();
		try {
			sysId = (List<Integer>) (gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(psIdAndNo(bean), "getSysIdForIfpIdWithStatusD")));

		} catch (GtnFrameworkGeneralException e) {
			throw new GtnFrameworkGeneralException("Error in Updating ", e);
		}

		return sysId.get(0);
	}

	private List<String> psIdAndNo(GtnIFamilyPlanBean bean) {
		List<String> cfpCriteria = new ArrayList<>();
		cfpCriteria.add(bean.getIfpInfo().getIfpId());
		cfpCriteria.add(bean.getIfpInfo().getIfpNo());
		return cfpCriteria;
	}
}
