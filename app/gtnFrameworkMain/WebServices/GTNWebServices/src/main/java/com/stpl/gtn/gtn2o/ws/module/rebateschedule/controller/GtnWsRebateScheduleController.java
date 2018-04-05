/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.rebateschedule.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
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
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.rebateschedule.RsModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.rebateschedule.service.GtnWsRebateScheduleCrudService;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateschedule.GtnWsRebateScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mahesh.James
 */
@RestController
@RequestMapping(value = GtnWsCDRContants.RS_SERVICE)
public class GtnWsRebateScheduleController {
	public GtnWsRebateScheduleController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRebateScheduleController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsRebateScheduleCrudService rsWebservice;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.RS_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse rebateScheduleSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering rebateScheduleSaveService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
                GtnWsRebateScheduleGeneralRequest gtnRequest = gtnWsRequest.getGtnWsRebateScheduleGeneralRequest();
		try {

			gtnGenWsesponse.setSucess(true);
			gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsRebateScheduleInfoBean rsInfoBean = gtnWsRequest.getGtnWsRebateScheduleGeneralRequest()
					.getRebateScheduleInfoBean();
                        boolean flag = checkRebateScheduleMaster(
					gtnRequest.getRebateScheduleInfoBean().getRebateScheduleId(),gtnRequest.getRebateScheduleInfoBean().getSystemId());
                        rsInfoBean.setRsIdAlreadyExist(flag);
                        if(!flag){
                            rsWebservice.rebateScheduleSave(rsInfoBean, gtnWsGeneralRequest.getUserId(),
					gtnWsGeneralRequest.getSessionId());
                        }
			gtnResponse.setRebateScheduleInfoBean(rsInfoBean);
		} catch (GtnFrameworkGeneralException ex) {
			gtnGenWsesponse.setSucess(false);
			gtnGenWsesponse.setGtnGeneralException(ex);
			logger.error("Error in RebateScheduleSaveService", ex);
		}
		logger.info("Ending rebateScheduleSaveService");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.RS_ITEM_ADDITION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse rebateScheduleItemAddition(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering rebateScheduleItemAddition");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			Map<String, String> inputValueMap = (Map<String, String>) inputList.get(0);

			rsWebservice.rebateScheduleInsert(inputValueMap);
		} catch (GtnFrameworkGeneralException ex) {
			gtnGenWsesponse.setSucess(false);
			gtnGenWsesponse.setGtnGeneralException(ex);
		}
		logger.info("Ending rebateScheduleItemAddition");
		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.RS_UPDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse rebateScheduleUpdateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering rebateScheduleUpdateService");
		try {
			GtnWsCheckAllUpdateRequest gtnWsRsUpdateRequest = gtnWsRequest.getGtnWsCheckAllUpdateRequest();
			GtnWsCheckAllUpdateBean rsUpdateBean = gtnWsRsUpdateRequest.getUpdateBean();

			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

			GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) GtnWsSearchQueryConfigLoaderType.REBATE_SCHEDULE
					.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());

			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
					.get("rebateScheduleRebateSetup");

			Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();
			rsWebservice.updateColumn(componetMap, rsUpdateBean, generalWSRequest);

		} catch (Exception ex) {
			logger.error("Error in rebateSchedulePopulateService", ex);
		}
		logger.info("Ending rebateScheduleUpdateService");
		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.RS_POPULATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse rebateSchedulePopulateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering rebateSchedulePopulateService");
		try {
			GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = gtnWsRequest.getGtnWsCheckAllUpdateRequest();
			GtnWsCheckAllUpdateBean psUpdateBean = gtnWsPSUpdateRequest.getUpdateBean();

			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

			GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) GtnWsSearchQueryConfigLoaderType.REBATE_SCHEDULE
					.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());

			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
					.get("rebateScheduleRebateSetup");

			Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();

			StringBuilder updateQuery = new StringBuilder(" UPDATE IMTD_RS_DETAILS  SET ");

			if (psUpdateBean.getPropertyValueMap() != null) {

				gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
						.get(psUpdateBean.getPropertyId());
				componetMap = gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap();
				boolean isAdded = false;

				for (String key : psUpdateBean.getPropertyValueMap().keySet()) {
					if (isAdded) {
						updateQuery.append(',').append(componetMap.get(key).getDbColumnName()).append("='")
								.append(psUpdateBean.getPropertyValueMap().get(key)).append("' ");

					} else {
						updateQuery.append(componetMap.get(key).getDbColumnName()).append("='")
								.append(psUpdateBean.getPropertyValueMap().get(key)).append("' ");
						isAdded = true;
					}

				}

			} else {

				updateQuery.append("").append(componetMap.get(psUpdateBean.getPropertyId()).getDbColumnName())
						.append('=');

				updateQuery.append(" '").append(psUpdateBean.getValue()).append("' ");

			}

			updateQuery.append(" WHERE  USERS_SID='").append(generalWSRequest.getUserId()).append("' AND SESSION_ID='")
					.append(generalWSRequest.getSessionId()).append("' ");
			if (!psUpdateBean.isCheckAll()) {
				updateQuery.append(" AND CHECK_RECORD='1' ");
			}

			rebateTabUpdate(updateQuery.toString());
		} catch (Exception ex) {
			logger.error("Error in rebateSchedulePopulateService", ex);
		}
		logger.info("Ending rebateSchedulePopulateService");
		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.RS_GET_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadRebateSchedule(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter loadRebateSchedule");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int sysId = gtnWsRequest.getGtnWsRebateScheduleGeneralRequest().getRebateScheduleInfoBean().getSystemId();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
		try {
			GtnWsRebateScheduleInfoBean psInfoBean = rsWebservice.getRsInfoBean(sysId);
			psInfoBean.setIfpDataList(rsWebservice.getIFPInfoBean(sysId));
			gtnResponse.setRebateScheduleInfoBean(psInfoBean);
			rsWebservice.rebateScheduleEditInsert(sysId, gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId());
		} catch (GtnFrameworkGeneralException ex) {

			gtnGenWsesponse.setSucess(false);
			logger.error("Error in loadPriceSchedule", ex);
		}

		logger.info("Exit  loadRebateSchedule");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.RS_VALIDATION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse validationService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validationService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			String processName = String.valueOf(inputList.get(0));
			Object resulList = validateTempRSDeatils(gtnWsGeneralRequest.getUserId(),
					gtnWsGeneralRequest.getSessionId(), processName);
			gtnResponse.setOutBountData(new Object[] { 0 });
			if (resulList != null) {

				List<Integer> list = (List<Integer>) resulList;
				gtnResponse.setOutBountData(new Object[] { list.get(0) });
			}

		} catch (Exception ex) {
			logger.error("Error in validationService", ex);

		}

		logger.info("Exit validationService");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.RS_DELECTE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse removeRsRecord(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter removeRsRecord");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			Map<String, Object> inputMap = (Map<String, Object>) inputList.get(0);

			int sysId = Integer.parseInt(String.valueOf(inputMap.get("sysId")));

			gtnResponse.setOutBountData(new Object[] { rsWebservice.deleteRebateScheduleRecord(sysId) });
		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Error in removeRsRecord", ex);
			gtnResponse.setOutBountData(new Object[] { "Fail" });
		}
		logger.info("Exit removeRsRecord");
		return gtnResponse;
	}

	private boolean rebateTabUpdate(String updateQuery) throws GtnFrameworkGeneralException {
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(updateQuery);
		return true;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		gtnSqlQueryEngine.setSessionFactory(sessionFactory);
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public Object validateTempRSDeatils(String userId, String sessionId, String process)
			throws GtnFrameworkGeneralException {

		StringBuilder sql = new StringBuilder("");
		try {

			if (process.equalsIgnoreCase("tempCount")) {
				sql.append("select count(1) from dbo.Imtd_Rs_Details where ");
			}
			if (process.equalsIgnoreCase("Status")) {
				sql.append(
						"select  count(1) from dbo.Imtd_Rs_Details where (RS_DETAILS_ATTACHED_STATUS is null OR RS_DETAILS_ATTACHED_STATUS = '0') and ");
			}
			if (process.equalsIgnoreCase("tempCheckedCount")) {
				sql.append("select count(1) from dbo.Imtd_Rs_Details where ");
			}
			if (process.equalsIgnoreCase("StartDateNull")) {
				sql.append("select count(1) from dbo.Imtd_Rs_Details where ITEM_REBATE_START_DATE is  null and");
			}

			if (userId != null) {
				sql.append("  users_Sid='").append(userId).append("' ");
			}
			if (sessionId != null) {
				sql.append(" and session_Id='").append(sessionId).append("' ");
			}

			if (process.equalsIgnoreCase("tempCheckedCount") || process.equalsIgnoreCase("Status")
					|| process.equalsIgnoreCase("Price") || process.equalsIgnoreCase("PriceType")
					|| process.equalsIgnoreCase("StartDateNull")) {
				sql.append(" and check_record = 1");
			}

			return gtnSqlQueryEngine.executeSelectQuery(sql.toString());
		} catch (HibernateException e) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, e);
			return null;
		}
	}

	@PostMapping(value = GtnWsCDRContants.GTN_WS_RS_CHECK_ALL_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkAll(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter checkAll");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());

			int deleteSuccess = rsWebservice.checkAllItems(gtnWsRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(deleteSuccess > 0);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting deleteIfp Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit deleteIfp and delete the ifp ");
		}
	}
    @SuppressWarnings("unchecked")
    public boolean checkRebateScheduleMaster(String rebateScheduleId,int rsModelSid) throws GtnFrameworkGeneralException {
        List<RsModel> results = null;
        Criteria criteria;
		try (Session session = sessionFactory.openSession()) {
                    if(rsModelSid==0){
			 criteria = session.createCriteria(RsModel.class)
					.add(Restrictions.eq("rsId", rebateScheduleId));
                    }else{
                         criteria = session.createCriteria(RsModel.class)
					.add(Restrictions.eq("rsId", rebateScheduleId))
					.add(Restrictions.ne("rsModelSid", rsModelSid));
                    }
			results = criteria.list();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(ex);
		}
		return results != null && !results.isEmpty(); 
    }

}
