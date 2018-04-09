package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.service.GtnWsCfpAddService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
@RequestMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE)
public class GtnWsCfpAddCotroller {
	public GtnWsCfpAddCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCfpAddCotroller.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsCfpAddService cFPWebservice;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnWsAllListConfig getGtnWebServiceAllListConfig() {
		return gtnWebServiceAllListConfig;
	}

	public void setGtnWebServiceAllListConfig(GtnWsAllListConfig gtnWebServiceAllListConfig) {
		this.gtnWebServiceAllListConfig = gtnWebServiceAllListConfig;
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_SEARCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse cfpSearch(@RequestBody GtnUIFrameworkWebserviceRequest cfpSearchRequest) {
		GtnUIFrameworkWebserviceResponse cfpSearchResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			cfpSearchResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			cfpSearchResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter cfpSearch");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String query = cFPWebservice.leftTableSearchQuery(cfpSearchRequest);
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query);
			if (cfpSearchRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
			} else {
				GtnUIFrameworkDataTable cfpSearchDataTable = new GtnUIFrameworkDataTable();
				cfpSearchDataTable.addData((List<Object[]>) resultList);
				gtnSerachResponse.setResultSet(cfpSearchDataTable);
			}
			cfpSearchResponse.setGtnSerachResponse(gtnSerachResponse);
			return cfpSearchResponse;
		} catch (GtnFrameworkGeneralException ex) {
			cfpSearchResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting cfpSearch Query", ex);
			cfpSearchResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return cfpSearchResponse;
		} finally {
			logger.info("Exit cfpSearch");
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_RIGHT_SERVICE)
	public GtnUIFrameworkWebserviceResponse cfoCompanyAdditionMoveRight(
			@RequestBody GtnUIFrameworkWebserviceRequest cfoCompanyAdditionRequest) {
		GtnUIFrameworkWebserviceResponse cfoCompanyAdditionResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			cfoCompanyAdditionResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			GtnWsCfpRequest gtnWsCfpRequest = cfoCompanyAdditionRequest.getGtnWsCfpRequest();
			cfoCompanyAdditionResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter cfpCompanyAdditionMoveRight");
			String query = gtnWsSqlService.getQuery("getCFPCompanyAdditionMoveRightQuery");
			GtnWsGeneralRequest inputs = cfoCompanyAdditionRequest.getGtnWsGeneralRequest();
			Object[] params = { inputs.getUserId(), inputs.getSessionId(),
					gtnWsCfpRequest.getGtnCFamilyPlan().getUpdateBean().getCompanyMasterSid() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return cfoCompanyAdditionResponse;
		} catch (Exception ex) {
			cfoCompanyAdditionResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTCOMPANY_NAMEING_COMPANY_ADDITION_M, ex);
			return cfoCompanyAdditionResponse;
		} finally {
			logger.info("Exit cfpCompanyAdditionMoveRight and inserted or updated " + count
					+ GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_LEFT_SERVICE)
	public GtnUIFrameworkWebserviceResponse cfpCompanyAdditionMoveLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest cfpCompanyAdditionRequest) {
		GtnUIFrameworkWebserviceResponse cfpCompanyAdditionResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			cfpCompanyAdditionResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			GtnWsCfpRequest gtnWsCfpRequest = cfpCompanyAdditionRequest.getGtnWsCfpRequest();
			cfpCompanyAdditionResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter cfpCompanyAdditionMoveLeft");
			String query = gtnWsSqlService.getQuery("getCFPCompanyAdditionMoveLeftQuery");
			GtnWsGeneralRequest inputs = cfpCompanyAdditionRequest.getGtnWsGeneralRequest();
			Object[] params = { inputs.getUserId(), inputs.getSessionId(),
					gtnWsCfpRequest.getGtnCFamilyPlan().getUpdateBean().getCompanyMasterSid() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return cfpCompanyAdditionResponse;
		} catch (Exception ex) {
			cfpCompanyAdditionResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTCOMPANY_NAMEING_COMPANY_ADDITION_M, ex);
			return cfpCompanyAdditionResponse;
		} finally {
			logger.info("Exit cfpCompanyAdditionMoveLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveAllLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyAdditionMoveAllLeft");
			String query = cFPWebservice.getMoveAllLeftQuery(gtnWsRequest);
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTCOMPANY_NAMEING_COMPANY_ADDITION_M, ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionMoveAllLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveAllRight(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyAdditionMoveAllRight");
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			String query = cFPWebservice.getMoveAllRightQuery(gtnWsRequest);
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTCOMPANY_NAMEING_COMPANY_ADDITION_M, ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionMoveAllRight and inserted or updated " + count
					+ GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyAdditionRightTableSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyAdditionRightTableSearch");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String query = cFPWebservice.rightTableSearchQuery(gtnWsRequest);
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query);
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData((List<Object[]>) resultList);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionRightTableSearch Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionRightTableSearch");
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse cfpCompaniesResultTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest cfpCompaniesResultRequest) {
		GtnUIFrameworkWebserviceResponse cfpCompaniesResultResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			cfpCompaniesResultResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			cfpCompaniesResultResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info(GtnFrameworkWebserviceConstant.ENTER_COMPANIES_RESULT_TABLE_DATA);
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			Object[] params = { cfpCompaniesResultRequest.getGtnWsGeneralRequest().getUserId(),
					cfpCompaniesResultRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			String query = cFPWebservice.companiesTabResultTable(cfpCompaniesResultRequest);
			List<?> cfpCompaniesResultList = gtnSqlQueryEngine.executeSelectQuery(query, params, types);
			if (cfpCompaniesResultRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(cfpCompaniesResultList.get(0))));
			} else {
				// set user name for user id
				for (int i = 0; i < cfpCompaniesResultList.size(); i++) {
					final Object[] obj = (Object[]) cfpCompaniesResultList.get(i);
					String modifiedByString = (String) obj[14];
					if (modifiedByString != null && !modifiedByString.isEmpty()) {
						obj[14] = gtnWebServiceAllListConfig.getUserIdNameMap().get(Integer.valueOf(modifiedByString));
					}
					String createdByString = (String) obj[16];
					if (createdByString != null && !createdByString.trim().isEmpty())
						obj[16] = gtnWebServiceAllListConfig.getUserIdNameMap().get(Integer.valueOf(createdByString));
					GtnUIFrameworkDataTable cfpCompaniesResultDataTable = new GtnUIFrameworkDataTable();
					cfpCompaniesResultDataTable.addData((List<Object[]>) cfpCompaniesResultList);
					gtnSerachResponse.setResultSet(cfpCompaniesResultDataTable);
				}
			}
			cfpCompaniesResultResponse.setGtnSerachResponse(gtnSerachResponse);
			return cfpCompaniesResultResponse;
		} catch (Exception ex) {
			cfpCompaniesResultResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_COMPANIES_RESULT_T, ex);
			cfpCompaniesResultResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return cfpCompaniesResultResponse;
		} finally {
			logger.info("Exit companiesResultTableData");
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_COLUMN_UPDATE_SERVICE)
	public GtnUIFrameworkWebserviceResponse companiesColumnUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info(GtnFrameworkWebserviceConstant.ENTER_COMPANIES_RESULT_TABLE_DATA);
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			String query = cFPWebservice.companiesUpdateColumnQuery(gtnWsRequest);
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_COMPANIES_RESULT_T, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companiesColumnUpdate " + count + " rows");
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_FETCH_INFORMATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchCfpInformation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		try {
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info(GtnFrameworkWebserviceConstant.ENTER_COMPANIES_RESULT_TABLE_DATA);
			cFPWebservice.getCfpFetchQuery(gtnWsRequest, gtnResponse);
			cFPWebservice.updateCfpCompaniesTabQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_WHILE_EXCUTING_COMPANIES_RESULT_T, ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companiesColumnUpdate ");
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_TEMP_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCfpCompaniesTabDelete(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCfpCompaniesTabDelete");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			cFPWebservice.getCfpCompaniesTabDeleteQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getCfpCompaniesTabDelete Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit getCfpCompaniesTabDelete ");
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCfpDelete(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCfpDelete");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());

			boolean deleteSuccess = cFPWebservice.deleteCfp(gtnWsRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(deleteSuccess);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getCfpDelete Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit getCfpDelete ");
		}
	}

}
