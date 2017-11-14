/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.deductioncalendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkQueryEngineMain;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.deductioncalendar.bean.GtnWsDeductionCalendarBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.deductioncalendar.GtnWsDeductionCalendarRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 *
 * @author Mahesh.James
 */
@RestController
@RequestMapping(value = GtnWsCDRContants.DC_SERVICE)
public class GtnWsDeductionCallendarController {
	public GtnWsDeductionCallendarController() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsDeductionCallendarController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkQueryEngineMain queryEngineMain;
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkQueryEngineMain getQueryEngineMain() {
		return queryEngineMain;
	}

	public void setQueryEngineMain(GtnFrameworkQueryEngineMain queryEngineMain) {
		this.queryEngineMain = queryEngineMain;
	}

	@RequestMapping(value = GtnWsCDRContants.DC_ITEM_ADD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse itemAddService(@RequestBody GtnUIFrameworkWebserviceRequest dcAddItemRequest) {
		LOGGER.info("Enter itemAddService");
		GtnUIFrameworkWebserviceResponse dcAddItemResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsDeductionCalendarRequest dedRequest = dcAddItemRequest.getDeductionCalendarRequest();
		GtnWsDeductionCalendarBean deductionCalendarBean = dedRequest.getDeductionCalendarBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = dcAddItemRequest.getGtnWsGeneralRequest();

		GtnWsGeneralResponse dcAddItemWsResponse = new GtnWsGeneralResponse();

		dcAddItemWsResponse.setSucess(true);

		dcAddItemResponse.setGtnWsGeneralResponse(dcAddItemWsResponse);

		try {
			if (deductionCalendarBean.isIsAddAll()) {
				GtnWsSearchRequest dcAddItemWsSearchRequest = new GtnWsSearchRequest();
				dcAddItemRequest.setGtnWsSearchRequest(dcAddItemWsSearchRequest);
				dcAddItemWsSearchRequest.setSearchQueryName("dcAvailableItem");

				getAllItemData(dcAddItemRequest, gtnWsGeneralRequest.getUserId(), gtnWsGeneralRequest.getSessionId());
			} else {
				buildItemInsertQuery(deductionCalendarBean.getIdList(), gtnWsGeneralRequest.getUserId(),
						gtnWsGeneralRequest.getSessionId(), "");
			}
		} catch (Exception ex) {
			dcAddItemWsResponse.setSucess(false);
			LOGGER.error("Error in itemAddService", ex);
		}

		LOGGER.info("Exit itemAddService");
		return dcAddItemResponse;
	}

	@RequestMapping(value = GtnWsCDRContants.DC_COMPANY_ADD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCompanyData(@RequestBody GtnUIFrameworkWebserviceRequest dcCompanyRequest) {
		LOGGER.info("Enter itemAddService");
		GtnUIFrameworkWebserviceResponse dcCompanyResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsDeductionCalendarRequest dedRequest = dcCompanyRequest.getDeductionCalendarRequest();
		GtnWsDeductionCalendarBean deductionCalendarBean = dedRequest.getDeductionCalendarBean();
		GtnWsGeneralRequest dcCompanyGeneralRequest = dcCompanyRequest.getGtnWsGeneralRequest();

		GtnWsGeneralResponse dccompanyWsResponse = new GtnWsGeneralResponse();

		dccompanyWsResponse.setSucess(true);

		dcCompanyResponse.setGtnWsGeneralResponse(dccompanyWsResponse);

		try {
			if (deductionCalendarBean.isIsAddAll()) {
				GtnWsSearchRequest dcCompanySearchRequest = new GtnWsSearchRequest();
				dcCompanyRequest.setGtnWsSearchRequest(dcCompanySearchRequest);
				dcCompanySearchRequest.setSearchQueryName("dcAvailableCustomer");
				dcCompanySearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);

				getAllCompanyData(dcCompanyRequest, dcCompanyGeneralRequest.getUserId(), dcCompanyGeneralRequest.getSessionId());
			} else {
				buildCustomerInsertQuery(deductionCalendarBean.getIdList(), dcCompanyGeneralRequest.getUserId(),
						dcCompanyGeneralRequest.getSessionId(), "");
			}

		} catch (Exception ex) {
			dccompanyWsResponse.setSucess(false);
			LOGGER.error("Error in itemAddService", ex);
		}

		LOGGER.info("Exit itemAddService");
		return dcCompanyResponse;
	}

	@SuppressWarnings({ "rawtypes" })
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {

		GtnFrameworkSqlQueryEngine queryEngine = new GtnFrameworkSqlQueryEngine();
		queryEngine.setSessionFactory(sessionFactory);

		return queryEngine.executeSelectQuery(sqlQuery);
	}

	private boolean getAllCompanyData(GtnUIFrameworkWebserviceRequest gtnWsRequest, String userId, String sessionId)
			throws GtnFrameworkGeneralException {
		try {
			String query = "SELECT DISTINCT TOP 900 CM.COMPANY_MASTER_SID FROM   COMPANY_MASTER CM\n"
					+ "                    LEFT JOIN COMPANY_TRADE_CLASS CTS\n"
					+ "                      ON CTS.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
					+ "                    LEFT JOIN HELPER_TABLE HT_CTS\n"
					+ "                           ON HT_CTS.HELPER_TABLE_SID = CTS.COMPANY_TRADE_CLASS\n"
					+ "                    LEFT JOIN HELPER_TABLE HT_CT\n"
					+ "                           ON HT_CT.HELPER_TABLE_SID = CM.COMPANY_TYPE\n"
					+ "                    LEFT JOIN HELPER_TABLE HT_CS\n"
					+ "                           ON HT_CS.HELPER_TABLE_SID = CM.COMPANY_STATUS\n"
					+ "                    LEFT JOIN HELPER_TABLE HT_ORG\n"
					+ "                           ON HT_ORG.HELPER_TABLE_SID = CM.ORGANIZATION_KEY\n"
					+ "                    LEFT JOIN HELPER_TABLE HT_CG\n"
					+ "                           ON HT_CG.HELPER_TABLE_SID = CM.COMPANY_GROUP\n"
					+ "                    LEFT JOIN UDCS UDC\n"
					+ "                           ON UDC.MASTER_SID = CM.COMPANY_MASTER_SID\n"
					+ "                              AND UDC.MASTER_TYPE LIKE 'COMPANY_MASTER'\n"
					+ "                    LEFT JOIN COMPANY_PARENT_DETAILS CPD\n"
					+ "                      ON CPD.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
					+ "                    LEFT JOIN COMPANY_MASTER CM1\n"
					+ "                      ON CPD.PARENT_COMPANY_MASTER_SID = CM1.COMPANY_MASTER_SID\n"
					+ "                    LEFT JOIN COMPANY_MASTER CM2\n"
					+ "                           ON CPD.PRIOR_PARENT_CMPY_MASTER_SID = CM2.COMPANY_MASTER_SID ";

			query = query + buildWhereClause(gtnWsRequest);
			@SuppressWarnings("unchecked")
			List<Integer> idList = executeQuery(query);
			query = "DELETE FROM ST_DEDUCTION_CALENDAR_COMPANY WHERE SESSION_ID='" + sessionId + "';";
			buildCustomerInsertQuery(idList, userId, sessionId, query);
		} catch (Exception ex) {
			LOGGER.error("Error in CDR Company Data", ex);
		}
		return true;
	}

	private boolean getAllItemData(GtnUIFrameworkWebserviceRequest gtnWsRequest, String userId, String sessionId)
			throws GtnFrameworkGeneralException {

		String query = " SELECT DISTINCT TOP 900 IM.ITEM_MASTER_SID FROM ITEM_MASTER IM \n"
				+ "LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n"
				+ "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER'  \n"
				+ "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID ";

		query = query + buildWhereClause(gtnWsRequest);

		@SuppressWarnings("unchecked")
		List<Integer> idList = executeQuery(query);

		query = "DELETE FROM ST_DEDUCTION_CALENDAR_ITEM WHERE SESSION_ID='" + sessionId + "';";
		buildItemInsertQuery(idList, userId, sessionId, query);
		return true;
	}

	private String buildWhereClause(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		String whereClause = "";

		String queryName = gtnWsRequest.getGtnWsSearchRequest().getSearchQueryName();

		GtnWsSearchQueryConfigLoaderType searchQueryconfigLoaderType = gtnWsRequest.getGtnWsSearchRequest()
				.getSearchConfigLodaderType();
		GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) searchQueryconfigLoaderType
				.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
				.get(queryName);

		GtnWsSearchQueryGenerationLogic searchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
				gtnWebServiceSearchQueryConfig, gtnWsRequest);

		whereClause = searchQueryGenerationLogic.generateSearchQueryWhereClause();

		return whereClause;

	}

	private boolean buildItemInsertQuery(List<Integer> idList, String userId, String sessionId, String deleteQuery) {
		StringBuilder query = new StringBuilder(deleteQuery);
		query.append(" INSERT INTO ST_DEDUCTION_CALENDAR_ITEM (ITEM_MASTER_SID, USER_ID, SESSION_ID) VALUES");
		boolean isAdded = false;

		for (Integer id : idList) {

			if (isAdded) {
				query.append(",");
			} else {

				isAdded = true;
			}

			query.append("( ").append("'").append(id).append("','").append(userId + "',")
					.append("'" + sessionId + "')");

		}

		return executeUpdate(query.toString());
	}

	private boolean buildCustomerInsertQuery(List<Integer> idList, String userId, String sessionId,
			String deteteQuery) {
		StringBuilder query = new StringBuilder(deteteQuery);

		query.append(" INSERT INTO ST_DEDUCTION_CALENDAR_COMPANY (COMPANY_MASTER_SID, USER_ID, SESSION_ID) VALUES");
		boolean isAdded = false;

		for (Integer id : idList) {

			if (isAdded) {
				query.append(",");
			} else {

				isAdded = true;
			}

			query.append("( '").append(id).append("','").append(userId).append("','").append(sessionId).append("')");

		}

		return executeUpdate(query.toString());
	}

	private boolean executeUpdate(String updateQuery) {
		try {
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(updateQuery);
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Error while executing the query.", e);
		}
		return true;
	}

}
