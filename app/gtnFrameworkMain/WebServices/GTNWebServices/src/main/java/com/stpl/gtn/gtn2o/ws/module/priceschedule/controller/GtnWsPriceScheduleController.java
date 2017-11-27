/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.priceschedule.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.PsModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.priceschedule.service.GtnWsPriceScheduleService;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.priceschedule.GtnWsPriceScheduleGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Mahesh.James
 */
@RestController
@RequestMapping(value = GtnWsCDRContants.PS_SERVICE)
public class GtnWsPriceScheduleController {
	public GtnWsPriceScheduleController() {
		/**
		 * empty constructor
		 */
	}

	private static final String TEMP_COUNT = "tempCount";
	private static final String STATUS = "Status";
	private static final String TEMP_CHECKED_COUNT = "tempCheckedCount";
	private static final String PRICE_TYPE = "PriceType";
	private static final String CP_START_DATE_NULL = "CPStartDateNull";
	private static final String CP_START_DATE_EQUAL = "CPStartDateEqual";
	private static final String CP_START_DATE_LESS = "CPStartDateLess";
	private static final String PP_START_DATE_NULL = "PPStartDateNull";
	private static final String PP_START_DATE_EQUAL = "PPStartDateEqual";
	private static final String PP_START_DATE_LESS = "PPStartDateLess";

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsPriceScheduleController.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine queryEngine;

	@Autowired
	private GtnWsSearchServiceController gTNSearchServiceController;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	@Autowired
	private GtnWsPriceScheduleService gtnWsPriceScheduleService;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_SEARCH_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceScheduleSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList();
		GtnWebServiceSearchCriteria searchFieldCriteria = gtnWebServiceSearchCriteriaList.get(0);
		GtnWebServiceSearchCriteria searchValueCriteria = gtnWebServiceSearchCriteriaList.get(1);
		searchValueCriteria.setFieldId(searchFieldCriteria.getFilterValue1());
		gtnWsRequest.getGtnWsSearchRequest().removeGtnWebServiceSearchCriteriaList(0);
		return gTNSearchServiceController.getSearchResultForAllModule(gtnWsRequest);
	}

	@RequestMapping(value = GtnWsCDRContants.PS_QUERY_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceQuery(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = gtnWsRequest
					.getGtnWsPriceScheduleGeneralRequest();

			GtnUIFrameWorkPSInfoBean psionfoBean = getGtnWsPriceScheduleGeneralRequest.getPsInfoBean();
			String createdBy = psionfoBean.getCreatedBy();
			String modifiedBy = psionfoBean.getModifiedBy();
			if (modifiedBy.equals("update")) {

				gtnResponse.setOutBountData(new Object[] { executUpdateQuery(createdBy) });

			} else {
				gtnResponse.setOutBountData(new Object[] { executeQuery(createdBy) });

			}

		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Error in loadPriceSchedule", ex);
		}

		return gtnResponse;
	}

	@RequestMapping(value = "/getGTNPriceScheduleService", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadPriceSchedule(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getGTNPriceScheduleService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = gtnWsRequest
				.getGtnWsPriceScheduleGeneralRequest();

		int sysId = getGtnWsPriceScheduleGeneralRequest.getPsInfoBean().getSystemId();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
		try {
			GtnUIFrameWorkPSInfoBean psInfoBean = gtnWsPriceScheduleService.getPsInfoBean(sysId);
			psInfoBean.setIfpDataList(gtnWsPriceScheduleService.getIfpInfoBean(sysId));
			GtnWsPriceScheduleGeneralResponse gtnWsPriceScheduleGeneralResponse = new GtnWsPriceScheduleGeneralResponse();
			gtnWsPriceScheduleGeneralResponse.setPriceScheduleInfoBean(psInfoBean);

			gtnResponse.setGtnWsPriceScheduleGeneralResponse(gtnWsPriceScheduleGeneralResponse);
			priceScheduleEditInsert(gtnWsRequest);
		} catch (Exception ex) {
			gtnGenWsesponse.setSucess(false);
			logger.error("Error in loadPriceSchedule", ex);
		}

		logger.info("Exit getGTNPriceScheduleService");
		return gtnResponse;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return queryEngine.executeSelectQuery(sqlQuery);
	}

	public int executUpdateQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog("Executing query : " + sqlQuery);
		Session session = queryEngine.getSessionFactory().openSession();
		int cout = 0;
		try {
			cout = queryEngine.executeInsertOrUpdateQuery(sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return cout;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceScheduleSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
		GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();

		GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = gtnWsRequest
				.getGtnWsPriceScheduleGeneralRequest();
		GtnUIFrameWorkPSInfoBean psInfoBean = getGtnWsPriceScheduleGeneralRequest.getPsInfoBean();

		try {
			gtnWsPriceScheduleService.priceScheduleSave(psInfoBean, gtnWsGeneralRequest.getUserId(),
					gtnWsGeneralRequest.getSessionId());
			GtnWsPriceScheduleGeneralResponse gtnWsPriceScheduleGeneralResponse = new GtnWsPriceScheduleGeneralResponse();
			gtnWsPriceScheduleGeneralResponse.setPriceScheduleInfoBean(psInfoBean);
			gtnResponse.setGtnWsPriceScheduleGeneralResponse(gtnWsPriceScheduleGeneralResponse);

		} catch (GtnFrameworkGeneralException ex) {
			gtnGenWsesponse.setSucess(false);
			logger.error("Error in priceScheduleSaveService", ex);
		}

		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_EDIT_INSERT_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceScheduleEditInsert(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGenResponse = new GtnWsGeneralResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsPriceScheduleGeneralRequest getGtnWsPriceScheduleGeneralRequest = gtnWsRequest
					.getGtnWsPriceScheduleGeneralRequest();
			GtnUIFrameWorkPSInfoBean psInfoBean = getGtnWsPriceScheduleGeneralRequest.getPsInfoBean();

			gtnWsPriceScheduleService.priceScheduleEditInsert(psInfoBean.getSystemId(), gtnWsGeneralRequest.getUserId(),
					gtnWsGeneralRequest.getSessionId());
			gtnWsGenResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGenResponse.setSucess(false);
			gtnWsGenResponse.setGtnGeneralException(e);
		}
		gtnResponse.setGtnWsGeneralResponse(gtnWsGenResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.PS_ITEM_ADDITION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceScheduleItemAddition(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
		List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
		Map<String, String> inputValueMap = (Map<String, String>) inputList.get(0);
		try {
			gtnWsPriceScheduleService.priceScheduleInsert(inputValueMap);
			gtnWsGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(e);
		}
		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_PRICETAB_UPDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceSchedulePriceTabUpdateService(
			@RequestBody GtnUIFrameworkWebserviceRequest psPriceTabWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnPsDetailsUpdateResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnPsDetailsUpdateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		GtnWsCheckAllUpdateRequest checkAllUpdateRequest = psPriceTabWsRequest.getGtnWsCheckAllUpdateRequest();
		GtnWsCheckAllUpdateBean psUpdateBean = checkAllUpdateRequest.getUpdateBean();

		GtnWsGeneralRequest generalRequest = psPriceTabWsRequest.getGtnWsGeneralRequest();
		GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE
				.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
				.get("priceSchedulePrice");

		Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap();
		try {
			gtnWsPriceScheduleService.updateImtdPsDetailsColumns(componetMap, psUpdateBean, generalRequest);
			gtnWsGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(e);
		}

		return gtnPsDetailsUpdateResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_PRICE_PROTECTION_TAB_UPDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceSchedulePriceProtectionTabUpdateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnPsDetailsProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnPsDetailsProtectionUpdateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = gtnWsRequest.getGtnWsCheckAllUpdateRequest();
		GtnWsCheckAllUpdateBean psUpdateBean = gtnWsPSUpdateRequest.getUpdateBean();
		GtnWsGeneralRequest psPriceProtectionWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
		GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE
				.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
				.get("updatePriceSchedulePriceProtection");
		Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap();
		try {
			gtnWsPriceScheduleService.updateImtdPsDetailsColumns(componetMap, psUpdateBean, psPriceProtectionWSRequest);
			gtnWsGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(e);
		}

		return gtnPsDetailsProtectionUpdateResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_PRICETAB_POPULATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse psPriceTabSchedulePopulateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = gtnWsRequest.getGtnWsCheckAllUpdateRequest();
			GtnWsCheckAllUpdateBean psPricePopulateUpdateBean = gtnWsPSUpdateRequest.getUpdateBean();
                   			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

			GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE
					.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());

			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
					.get("priceSchedulePrice");

			Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();

			StringBuilder psPricePopulateUpdateQuery = new StringBuilder(
					GtnFrameworkWebserviceConstant.UPDATE_IMTD_PS_DETAILS_SET);

			if (psPricePopulateUpdateBean.getPropertyValueMap() != null) {

				gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
						.get(psPricePopulateUpdateBean.getPropertyId());
				componetMap = gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap();
				boolean isAdded = false;

				for (String key : psPricePopulateUpdateBean.getPropertyValueMap().keySet()) {
					if (isAdded) {
						psPricePopulateUpdateQuery.append(",").append(componetMap.get(key).getDbColumnName())
								.append("='").append(psPricePopulateUpdateBean.getPropertyValueMap().get(key))
								.append("'");

					} else {
						psPricePopulateUpdateQuery.append(componetMap.get(key).getDbColumnName()).append("='")
								.append(psPricePopulateUpdateBean.getPropertyValueMap().get(key)).append("'");
						isAdded = true;
					}

				}

			} else {
				psPricePopulateUpdateQuery.append("")
						.append(componetMap.get(psPricePopulateUpdateBean.getPropertyId()).getDbColumnName())
						.append("=");

				psPricePopulateUpdateQuery.append("'").append(psPricePopulateUpdateBean.getValue()).append("'");

			}

			psPricePopulateUpdateQuery.append(" WHERE  USERS_SID='").append(generalWSRequest.getUserId())
					.append(GtnFrameworkWebserviceConstant.AND_SESSION_ID).append(generalWSRequest.getSessionId())
					.append("'");
			if (!psPricePopulateUpdateBean.isCheckAll()) {
				psPricePopulateUpdateQuery.append(" AND CHECK_RECORD='1' ");
			}

			priceTabUpdate(psPricePopulateUpdateQuery.toString());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = "/"
			+ GtnWsCDRContants.PS_PRICE_PROTECTION_TAB_POPULATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse psPriceProtectionTabSchedulePopulateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = gtnWsRequest.getGtnWsCheckAllUpdateRequest();
			GtnWsCheckAllUpdateBean psPPUpdateBean = gtnWsPSUpdateRequest.getUpdateBean();
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

			GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE
					.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());

			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
					.get("priceSchedulePriceProtection");

			Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();
			StringBuilder psPPUpdateQuery = gtnWsPriceScheduleService.getPopulateQuery(psPPUpdateBean, generalWSRequest,
					componetMap, psPPUpdateBean.getPropertyId(), psPPUpdateBean.getValue());
			priceTabUpdate(psPPUpdateQuery.toString());

			if (psPPUpdateBean.getPropertyId1() != null && psPPUpdateBean.getValue1() != null) {
				psPPUpdateQuery = gtnWsPriceScheduleService.getPopulateQuery(psPPUpdateBean, generalWSRequest,
						componetMap, psPPUpdateBean.getPropertyId1(), psPPUpdateBean.getValue1());
				priceTabUpdate(psPPUpdateQuery.toString());
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return new GtnUIFrameworkWebserviceResponse();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.PS_DELECTE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse removePsRecord(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter removePsRecord");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			Map<String, Object> inputMap = (Map<String, Object>) inputList.get(0);

			int sysId = Integer.parseInt(String.valueOf(inputMap.get("sysId")));
			gtnWsPriceScheduleService.deletePriceScheduleRecord(sysId);
			gtnResponse.setOutBountData(new Object[] { "Sucess" });
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(ex.getMessage());
			gtnResponse.setOutBountData(new Object[] { "Fail" });
		}

		logger.info("Exit removePsRecord");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.PS_VALIDATION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse validationService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validationService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			String processName = String.valueOf(inputList.get(0));
			Object resulList = validateTempPSDeatils(gtnWsGeneralRequest.getUserId(),
					gtnWsGeneralRequest.getSessionId(), processName);
			gtnResponse.setOutBountData(new Object[] { 0 });
			if (resulList != null) {

				List<Integer> list = (List<Integer>) resulList;
				gtnResponse.setOutBountData(new Object[] { list.get(0) });
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Exit validationService");
		return gtnResponse;
	}

	private void priceTabUpdate(String updateQuery) throws GtnFrameworkGeneralException {
		queryEngine.executeInsertOrUpdateQuery(updateQuery);

	}

	public Object validateTempPSDeatils(String userId, String sessionId, String process)
			throws GtnFrameworkGeneralException {

		StringBuilder psValidateSql = new StringBuilder("");
		boolean checkRecord = false;

		if (TEMP_COUNT.equalsIgnoreCase(process)) {
			psValidateSql.append("select count(item_No) from dbo.Imtd_Ps_Details where ");

		}
		if (STATUS.equalsIgnoreCase(process)) {
			psValidateSql.append(
					"select  count(item_No) from dbo.Imtd_Ps_Details where (status is null OR status = '0') and ");
			checkRecord = true;
		}
		if (TEMP_CHECKED_COUNT.equalsIgnoreCase(process)) {
			psValidateSql.append("select count(item_No) from dbo.Imtd_Ps_Details where ");
			checkRecord = true;
		}
		if (PRICE_TYPE.equalsIgnoreCase(process)) {
			psValidateSql.append(
					"select count(item_No) from dbo.Imtd_Ps_Details where ( ps_Details_Pricetype is null OR ps_Details_Pricetype = '0') and");
			checkRecord = true;
		}
		if (CP_START_DATE_NULL.equalsIgnoreCase(process)) {
			psValidateSql.append(
					"select count(item_No) from dbo.Imtd_Ps_Details where ps_Dtls_Cont_Price_Startdate is  null and");
			checkRecord = true;
		}
		if (CP_START_DATE_EQUAL.equalsIgnoreCase(process)) {
			psValidateSql.append(
					"select  count(item_No) from dbo.Imtd_Ps_Details where ps_Dtls_Cont_Price_Startdate = ps_Dtls_Cont_Price_Enddate and");
			checkRecord = true;
		}
		if (CP_START_DATE_LESS.equalsIgnoreCase(process)) {
			psValidateSql.append(
					"select  count(item_No) from dbo.Imtd_Ps_Details where ps_Dtls_Cont_Price_Startdate > ps_Dtls_Cont_Price_Enddate and");
			checkRecord = true;
		}

		if (userId != null) {
			psValidateSql.append("  users_Sid='").append(userId).append("'");
		}
		if (sessionId != null) {
			psValidateSql.append(" and session_Id='").append(sessionId).append("'");
		}

		if ("Price".equalsIgnoreCase(process) || "PPStartDateEqual".equalsIgnoreCase(process)
				|| "PPStartDateLess".equalsIgnoreCase(process) || checkRecord) {
			psValidateSql.append(" and check_record = 1");
		}

		if ("itemDuplicationCheck".equalsIgnoreCase(process)) {
			psValidateSql.append(" group by item_Id,ps_Dtls_Cont_Price_Startdate) a where a.countA >1;");
		}

		return queryEngine.executeSelectQuery(psValidateSql.toString());

	}

	@RequestMapping(value = "/priceSchedulePriceProtectionTab", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceSchedulePriceProtectionTabService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnPsProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponsePs = new GtnWsGeneralResponse();
		gtnPsProtectionUpdateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponsePs);

		GtnWsGeneralRequest psPriceProtectionWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

		List<Object> inputList = psPriceProtectionWSRequest.getComboBoxWhereclauseParamList();

		try {
			gtnWsPriceScheduleService.priceScheduleInsertTemp(inputList, gtnPsProtectionUpdateResponse);
			gtnWsGeneralResponsePs.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponsePs.setSucess(false);
			gtnWsGeneralResponsePs.setGtnGeneralException(e);
		}

		return gtnPsProtectionUpdateResponse;
	}

	@RequestMapping(value = "/priceProtectionStartDateAlert", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceProtectionStartDateAlertTabService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnPsProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponsePs = new GtnWsGeneralResponse();
		gtnPsProtectionUpdateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponsePs);

		GtnWsGeneralRequest psPriceProtectionWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

		List<Object> inputList = psPriceProtectionWSRequest.getComboBoxWhereclauseParamList();

		try {
			gtnWsPriceScheduleService.priceProtectionStartDateAlert(inputList, gtnPsProtectionUpdateResponse);
			gtnWsGeneralResponsePs.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponsePs.setSucess(false);
			gtnWsGeneralResponsePs.setGtnGeneralException(e);
		}

		return gtnPsProtectionUpdateResponse;
	}

	@SuppressWarnings("rawtypes")
	public String getQuery(List input, String queryName) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(gtnWsSqlService.getQuery(queryName));
			if (input != null) {
				for (Object temp : input) {
					sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}

	@PostMapping(value = GtnWsCDRContants.GTN_WS_PS_ID_NO_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse contractIdAndNoValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter psIdAndNoValidation");
			checkPsIdNameExist(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting psIdAndNoValidation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit psIdAndNoValidation");
		}
	}

	@SuppressWarnings("unchecked")
	public void checkPsIdNameExist(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			boolean isPsIdExist = false;
			boolean isPsNoExist = false;

			GtnUIFrameWorkPSInfoBean psMasterBean = gtnWsRequest.getGtnWsPriceScheduleGeneralRequest().getPsInfoBean();
			int systemId = psMasterBean.getSystemId();
			Criteria cr = session.createCriteria(PsModel.class).add(Restrictions.eq("psId", psMasterBean.getPsId()))
					.add(Restrictions.ne("inboundStatus", 'D'));
			if (systemId > 0) {
				cr.add(Restrictions.ne("psModelSid", systemId));
			}
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.countDistinct("psId"));
			cr.setProjection(proj);
			List<Long> results = cr.list();
			if (results != null && !results.isEmpty()) {
				isPsIdExist = results.get(0) > 0;
			}
			Criteria cr2 = session.createCriteria(PsModel.class).add(Restrictions.eq("psNo", psMasterBean.getPsNo()))
					.add(Restrictions.ne("inboundStatus", 'D'));
			if (systemId > 0) {
				cr2.add(Restrictions.ne("psModelSid", systemId));
			}
			ProjectionList proj2 = Projections.projectionList();
			proj2.add(Projections.countDistinct("psNo"));
			cr2.setProjection(proj2);

			List<Long> results2 = cr2.list();
			if (results2 != null && !results2.isEmpty()) {
				isPsNoExist = results2.get(0) > 0;
			}
			tx.commit();
			GtnWsPriceScheduleGeneralResponse imResponse = new GtnWsPriceScheduleGeneralResponse();
			imResponse.setPsIdDuplicate(isPsIdExist);
			imResponse.setPsNoDuplicate(isPsNoExist);
			gtnResponse.setGtnWsPriceScheduleGeneralResponse(imResponse);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in PsIdNameExist ", e);
		} finally {
			session.close();
		}

	}

        @SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.PS_PP_VALIDATION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceProtectionValidationService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validationService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			String processName = String.valueOf(inputList.get(0));
			Object resulList = validateTempPriceProtectionDeatils(gtnWsGeneralRequest.getUserId(),
					gtnWsGeneralRequest.getSessionId(), processName);
			gtnResponse.setOutBountData(new Object[] { 0 });
			if (resulList != null) {

				List<Integer> list = (List<Integer>) resulList;
				gtnResponse.setOutBountData(new Object[] { list.get(0) });
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Exit validationService");
		return gtnResponse;
	}
        
        public Object validateTempPriceProtectionDeatils(String userId, String sessionId, String process)
			throws GtnFrameworkGeneralException {

		StringBuilder ppValidateSql = new StringBuilder("");
		boolean checkRecord = false;

		if (TEMP_COUNT.equalsIgnoreCase(process)) {
			ppValidateSql.append("select count(item_No) from dbo.Imtd_Ps_Details where ");

		}
//		if (STATUS.equalsIgnoreCase(process)) {
//			psValidateSql.append(
//					"select  count(item_No) from dbo.Imtd_Ps_Details where (status is null OR status = '0') and ");
//			checkRecord = true;
//		}
		if (TEMP_CHECKED_COUNT.equalsIgnoreCase(process)) {
			ppValidateSql.append("select count(item_No) from dbo.Imtd_Ps_Details where ");
			checkRecord = true;
		}
//		if (PRICE_TYPE.equalsIgnoreCase(process)) {
//			psValidateSql.append(
//					"select count(item_No) from dbo.Imtd_Ps_Details where ( ps_Details_Pricetype is null OR ps_Details_Pricetype = '0') and");
//			checkRecord = true;
//		}
		if (PP_START_DATE_NULL.equalsIgnoreCase(process)) {
			ppValidateSql.append(
					"select count(item_No) from dbo.Imtd_Ps_Details where ps_Details_Pric_Prtcn_Stdate is  null and");
			checkRecord = true;
		}
		if (PP_START_DATE_EQUAL.equalsIgnoreCase(process)) {
			ppValidateSql.append(
					"select  count(item_No) from dbo.Imtd_Ps_Details where ps_Details_Pric_Prtcn_Stdate = ps_Details_Pric_Prtcn_Eddate and");
			checkRecord = true;
		}
		if (PP_START_DATE_LESS.equalsIgnoreCase(process)) {
			ppValidateSql.append(
					"select  count(item_No) from dbo.Imtd_Ps_Details where ps_Details_Pric_Prtcn_Stdate > ps_Details_Pric_Prtcn_Eddate and");
			checkRecord = true;
		}

		if (userId != null) {
			ppValidateSql.append("  users_Sid='").append(userId).append("'");
		}
		if (sessionId != null) {
			ppValidateSql.append(" and session_Id='").append(sessionId).append("'");
		}

		if ("Price".equalsIgnoreCase(process) || "PPStartDateEqual".equalsIgnoreCase(process)
				|| "PPStartDateLess".equalsIgnoreCase(process) || checkRecord) {
			ppValidateSql.append(" and check_record = 1");
		}

		if ("itemDuplicationCheck".equalsIgnoreCase(process)) {
			ppValidateSql.append(" group by item_Id,ps_Dtls_Cont_Price_Startdate) a where a.countA >1;");
		}

		return queryEngine.executeSelectQuery(ppValidateSql.toString());

	}
}
