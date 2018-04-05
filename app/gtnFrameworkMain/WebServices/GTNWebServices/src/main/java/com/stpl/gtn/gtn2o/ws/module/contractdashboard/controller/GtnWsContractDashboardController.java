/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardCompanyLogic;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardItemLogic;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardLogic;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardLookupLogic;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardProcessLogic;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardSubmitLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

/**
 *
 * @author Abhiram.Giri
 */
@RestController
@RequestMapping(value = GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE)
public class GtnWsContractDashboardController {
	public GtnWsContractDashboardController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardController.class);

	private final GtnWsContractDashboardLogic logic = new GtnWsContractDashboardLogic(this);
	private final GtnWsContractDashboardCompanyLogic companyLogic = new GtnWsContractDashboardCompanyLogic(this);
	private final GtnWsContractDashboardProcessLogic processLogic = new GtnWsContractDashboardProcessLogic(this);
	private final GtnWsContractDashboardSubmitLogic submitLogic = new GtnWsContractDashboardSubmitLogic(this);
	private final GtnWsContractDashboardItemLogic itemLogic = new GtnWsContractDashboardItemLogic(this);
	private final GtnWsContractDashboardLookupLogic lookupLogic = new GtnWsContractDashboardLookupLogic(this);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkAutomaticService automaticRelationService;

	public GtnFrameworkAutomaticService getAutomaticRelationService() {
		return automaticRelationService;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public GtnWsContractDashboardLogic getLogic() {
		return logic;
	}

	public GtnWsContractDashboardCompanyLogic getCompanyLogic() {
		return companyLogic;
	}

	public GtnWsContractDashboardProcessLogic getProcessLogic() {
		return processLogic;
	}

	public GtnWsContractDashboardSubmitLogic getSubmitLogic() {
		return submitLogic;
	}

	public GtnWsContractDashboardItemLogic getItemLogic() {
		return itemLogic;
	}

	public GtnWsContractDashboardLookupLogic getLookupLogic() {
		return lookupLogic;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return getGtnSqlQueryEngine().executeSelectQuery(sqlQuery);
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return getGtnSqlQueryEngine().executeSelectQuery(sqlQuery, params, type);
	}

	public int executeUpdateQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return getGtnSqlQueryEngine().executeInsertOrUpdateQuery(sqlQuery);
	}

	public int executeUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return getGtnSqlQueryEngine().executeInsertOrUpdateQuery(sqlQuery, params, type);
	}

	public String getQuery(String sqlId) {
		return getGtnWsSqlService().getQuery(sqlId);
	}

	public Object[] createParams(Object... params) {
		return params;
	}

	public GtnFrameworkDataType[] createDataTypes(GtnFrameworkDataType... dataTypes) {
		return dataTypes;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getContractDashboardTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLogic().getContractDashboardTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_DETAILS_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getContractDashboardDetailsTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLogic().getContractDashboardDetailsTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.CHECK_ADD_TO_TREE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkAddToTree(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter checkAddToTree");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getLogic().checkAddToTree(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in checkAddToTree", ex);
		}

		logger.info("Exit checkAddToTree");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.ADD_TO_TREE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse addToTree(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter addToTree");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getLogic().addToTree(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in addToTree", ex);
		}

		logger.info("Exit addToTree");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.SAVE_CONTRACT_TREE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveContractTree(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveContractTree");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getLogic().saveContractTree(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in saveContractTree", ex);
		}

		logger.info("Exit saveContractTree");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_PROCESS_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getContractDashboardProcessTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getContractDashboardProcessTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getContractDashboardProcessTreeData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getContractDashboardProcessTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_LEFT_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDCompanyAdditionLeftTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDCompanyAdditionLeftTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getCompanyLogic().getCDCompanyAdditionLeftTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getCDCompanyAdditionLeftTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_RIGHT)
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveRight(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyAdditionMoveRight");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			getCompanyLogic().companyAdditionMoveRight(gtnWsRequest, gtnResponse);
		} catch (Exception ex) {
			logger.error("Exception while Excuting companyAdditionMoveRight Query", ex);
		} finally {
			logger.info("Exit companyAdditionMoveRight ");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_LEFT)
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyAdditionMoveLeft");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			String query = getGtnWsSqlService().getQuery("getCDCompanyAdditionMoveLeftQuery");
			GtnWsGeneralRequest inputs = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsRecordBean bean = gtnWsRequest.getGtnWsContractDashboardRequest().getTableBean();
			int systemId = bean.getProperties().get(11) == null ? 0
					: Integer.parseInt(String.valueOf(bean.getProperties().get(11)));
			Object[] params = { inputs.getUserId(), inputs.getSessionId(), systemId };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER };
			count = executeUpdateQuery(query, params, types);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionMoveLeft Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionMoveLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_ALL_LEFT)
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveAllLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyAdditionMoveAllLeft");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			Object[] params = createParams(gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId());
			GtnFrameworkDataType[] types = createDataTypes(GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING);
			String query = getCompanyLogic().getCompanyMoveAllLeftQuery(gtnWsRequest);
			count = executeUpdateQuery(query, params, types);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionMoveAllLeft Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionMoveAllLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.COMPANY_ADDITION_MOVE_ALL_RIGHT)
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveAllRight(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyAdditionMoveAllRight");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			String query = getCompanyLogic().getCompanyMoveAllRightQuery(gtnWsRequest);
			count = executeUpdateQuery(query);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionMoveAllRight Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionMoveAllRight and inserted or updated " + count
					+ GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDCompanyAdditionRightTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDCompanyAdditionRightTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getCompanyLogic().getCDCompanyAdditionViewTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getCDCompanyAdditionRightTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_RIGHT_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDItemAdditionRightTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDItemAdditionRightTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().getCDItemAdditionViewTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getCDItemAdditionRightTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_LEFT_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDItemAdditionLeftTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDItemAdditionLeftTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdItemAdditionLeftTableResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().getCDItemAdditionLeftTableData(gtnWsRequest, gtnResponse);
			cdItemAdditionLeftTableResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			cdItemAdditionLeftTableResponse.setSucess(false);
			cdItemAdditionLeftTableResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(cdItemAdditionLeftTableResponse);
		logger.info("Exit getCDItemAdditionLeftTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_RIGHT)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveRight(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters itemAdditionMoveRight ");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			getItemLogic().itemAdditionMoveRight(gtnWsRequest, gtnResponse);
		} catch (Exception ex) {
			logger.error("Exception while Excuting itemAdditionMoveRight Query", ex);
		} finally {
			logger.info("Exit itemAdditionMoveRight ");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_LEFT)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter itemAdditionMoveLeft");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			getItemLogic().itemAdditionMoveLeft(gtnWsRequest, gtnResponse);
		} catch (Exception ex) {
			logger.error("Exception while Excuting itemAdditionMoveLeft Query", ex);
		} finally {
			logger.info("Exit itemAdditionMoveLeft ");
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_ALL_LEFT)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveAllLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter itemAdditionMoveAllLeft");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			getItemLogic().itemAdditionMoveAllLeft(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionMoveAllLeft Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit itemAdditionMoveAllLeft");
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_ALL_RIGHT)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveAllRight(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter itemAdditionMoveAllRight");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			getItemLogic().itemAdditionMoveAllRight(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionMoveAllRight Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit itemAdditionMoveAllRight");
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_COMPANIES_DETAIL_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDCompniesDetailTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDCompniesDetailTableData");
		GtnUIFrameworkWebserviceResponse getCDCompniesDetailwsResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse getCDCompniesDetailResponse = new GtnWsGeneralResponse();
		try {

			String recordType = GtnWsConstants.EMPTY;

			if (!gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria getCDCompniesDetailSearchCriteria = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!getCDCompniesDetailSearchCriteria.isFilter()) {
					recordType = getCDCompniesDetailSearchCriteria.getFilterValue1().replace("[", GtnWsConstants.EMPTY)
							.replace("]", GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(recordType)) {

				getCompanyLogic().getCDCompniessDetailTableData(gtnWsRequest, getCDCompniesDetailwsResponse);
			} else {
				getCompanyLogic().getCDCompniesDetailTableData(gtnWsRequest, getCDCompniesDetailwsResponse);
			}
			getCDCompniesDetailResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			getCDCompniesDetailResponse.setSucess(false);
			getCDCompniesDetailResponse.setGtnGeneralException(ex);
		}
		getCDCompniesDetailwsResponse.setGtnWsGeneralResponse(getCDCompniesDetailResponse);
		logger.info("Exit getCDCompniesDetailTableData");
		return getCDCompniesDetailwsResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_COMPANIES_DETAIL_VIEW_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDCompniesDetailViewTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest companiesgtnWsRequest) {
		logger.info("Enter getCDCompniesDetailViewTableData");
		GtnUIFrameworkWebserviceResponse companiesviewgtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse companiesviewgeneralResponse = new GtnWsGeneralResponse();
		try {

			String recordTypecompaniesView = GtnWsConstants.EMPTY;

			if (!companiesgtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria getCDCompniesViewDetailSearchCriteria = companiesgtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				if (!getCDCompniesViewDetailSearchCriteria.isFilter()) {
					recordTypecompaniesView = getCDCompniesViewDetailSearchCriteria.getFilterValue1()
							.replace("[", GtnWsConstants.EMPTY).replace("]", GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(recordTypecompaniesView)) {

				getCompanyLogic().getCDCompniessViewDetailTableData(companiesgtnWsRequest, companiesviewgtnResponse);
			} else {
				getCompanyLogic().getCDCompniesDetailViewTableData(companiesgtnWsRequest, companiesviewgtnResponse);
			}
			companiesviewgeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			companiesviewgeneralResponse.setSucess(false);
			companiesviewgeneralResponse.setGtnGeneralException(ex);
		}
		companiesviewgtnResponse.setGtnWsGeneralResponse(companiesviewgeneralResponse);
		logger.info("Exit getCDCompniesDetailViewTableData");
		return companiesviewgtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_COMPANIES_HISTORY_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDCompniesHistoryTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDCompniesHistoryTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdCompniesHistoryResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().getCDItemAdditionLeftTableData(gtnWsRequest, gtnResponse);
			cdCompniesHistoryResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			cdCompniesHistoryResponse.setSucess(false);
			cdCompniesHistoryResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(cdCompniesHistoryResponse);
		logger.info("Exit getCDCompniesHistoryTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.POPULATE_ALL_COMPANY, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse populateAllCompanies(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter populateAllCompanies");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getCompanyLogic().populateAllCompanies(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit populateAllCompanies");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.POPULATE_COMPANY, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse populateCompany(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter populateCompany");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getCompanyLogic().populateCompany(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit populateCompany");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.POPULATE_COMPANY_FIELD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse populateCompanyField(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter populateCompanyField");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getCompanyLogic().populateCompanyField(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit populateCompanyField");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_ITEMS_DETAIL_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDItemsDetailTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest cdItemsDetailRequest) {
		logger.info("Enter getCDItemsDetailTableData");
		GtnUIFrameworkWebserviceResponse cdItemsDetailResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdItemsDetailGeneralResponse = new GtnWsGeneralResponse();
		boolean isStartDateEndDate = false;
		try {

			String recordType = GtnWsConstants.EMPTY;

			if (!cdItemsDetailRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {

				GtnWebServiceSearchCriteria cdItemsDetailSearchCriteria = cdItemsDetailRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!cdItemsDetailSearchCriteria.isFilter()) {
					recordType = cdItemsDetailSearchCriteria.getFilterValue1().replace("[", GtnWsConstants.EMPTY)
							.replace("]", GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(recordType)) {
				getItemLogic().getCDItemsDetailPendingTableData(cdItemsDetailRequest, cdItemsDetailResponse);
			} else {
				getItemLogic().getCDItemsDetailTableData(cdItemsDetailRequest, cdItemsDetailResponse,
						isStartDateEndDate);
			}
			cdItemsDetailGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			cdItemsDetailGeneralResponse.setSucess(false);
			cdItemsDetailGeneralResponse.setGtnGeneralException(ex);
		}
		cdItemsDetailResponse.setGtnWsGeneralResponse(cdItemsDetailGeneralResponse);
		logger.info("Exit getCDItemsDetailTableData");
		return cdItemsDetailResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_ITEMS_DETAIL_VIEW_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDItemsDetailViewTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest itemsdetailgtnWsRequest) {
		logger.info("Enter getCDItemsDetailViewTableData");
		GtnUIFrameworkWebserviceResponse itemsdetailgtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse itemsdetailgeneralResponse = new GtnWsGeneralResponse();
		boolean isStartDateEndDate = false;
		try {

			String itemsdetailrecordType = GtnWsConstants.EMPTY;

			if (!itemsdetailgtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {

				GtnWebServiceSearchCriteria cdItemsDetailSearchCriteria = itemsdetailgtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				if (!cdItemsDetailSearchCriteria.isFilter()) {
					itemsdetailrecordType = cdItemsDetailSearchCriteria.getFilterValue1()
							.replace("[", GtnWsConstants.EMPTY).replace("]", GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(itemsdetailrecordType)) {
				getItemLogic().getCDItemsViewDetailPendingTableData(itemsdetailgtnWsRequest, itemsdetailgtnResponse);
			} else {

				getItemLogic().getCDItemsDetailViewTableData(itemsdetailgtnWsRequest, itemsdetailgtnResponse,
						isStartDateEndDate);
			}
			itemsdetailgeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			itemsdetailgeneralResponse.setSucess(false);
			itemsdetailgeneralResponse.setGtnGeneralException(ex);
		}
		itemsdetailgtnResponse.setGtnWsGeneralResponse(itemsdetailgeneralResponse);
		logger.info("Exit getCDItemsDetailViewTableData");
		return itemsdetailgtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_ITEMS_HISTORY_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDItemsHistoryTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDItemsHistoryTableData");
		GtnUIFrameworkWebserviceResponse cdItemAdditionResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdItemAdditionGeneralResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().getCDItemAdditionLeftTableData(gtnWsRequest, cdItemAdditionResponse);
			cdItemAdditionGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			cdItemAdditionGeneralResponse.setSucess(false);
			cdItemAdditionGeneralResponse.setGtnGeneralException(ex);
		}
		cdItemAdditionResponse.setGtnWsGeneralResponse(cdItemAdditionGeneralResponse);
		logger.info("Exit getCDItemsHistoryTableData");
		return cdItemAdditionResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.POPULATE_ALL_ITEM, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse populateAllItems(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter populateAllItems");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().populateAllItems(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit populateAllItems");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.POPULATE_ITEM, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse populateItem(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter populateCompany");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().populateItem(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit populateItem");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.POPULATE_ITEM_FIELD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse populateItemField(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter populateItemField");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().populateItemField(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit populateItemField");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_PRICING_DETAIL_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDPricingDetailTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest cdPricingDetailRequest) {
		logger.info("Enter getCDPricingDetailTableData");
		GtnUIFrameworkWebserviceResponse cdPricingDetailResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdPricingDetailGeneralResponse = new GtnWsGeneralResponse();
		try {

			String recordType = GtnWsConstants.EMPTY;

			if (!cdPricingDetailRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = cdPricingDetailRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", GtnWsConstants.EMPTY).replace("]",
							GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(recordType)) {
				getItemLogic().getCDPricingDetailPendingTableData(cdPricingDetailRequest, cdPricingDetailResponse);
			} else {
				getItemLogic().getCDPricingDetailTableData(cdPricingDetailRequest, cdPricingDetailResponse);
			}

			cdPricingDetailGeneralResponse.setSucess(true);

		} catch (GtnFrameworkGeneralException ex) {
			cdPricingDetailGeneralResponse.setSucess(false);
			cdPricingDetailGeneralResponse.setGtnGeneralException(ex);
		}
		cdPricingDetailResponse.setGtnWsGeneralResponse(cdPricingDetailGeneralResponse);
		logger.info("Exit getCDPricingDetailTableData");
		return cdPricingDetailResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_PRICING_DETAIL_VIEW_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDPricingDetailViewTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest pricingdetailgtnWsRequest) {
		logger.info("Enter getCDPricingDetailViewTableData");
		GtnUIFrameworkWebserviceResponse pricingdetailgtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse pricingdetailgeneralResponse = new GtnWsGeneralResponse();
		try {

			String recordType = GtnWsConstants.EMPTY;

			if (!pricingdetailgtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria pricingdetailsearchCriteria = pricingdetailgtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				if (!pricingdetailsearchCriteria.isFilter()) {
					recordType = pricingdetailsearchCriteria.getFilterValue1().replace("[", GtnWsConstants.EMPTY)
							.replace("]", GtnWsConstants.EMPTY);
				}

			}
			if (GtnWsContractDashboardContants.PENDING.equals(recordType)) {
				getItemLogic().getCDPricingDetailViewPendingTableData(pricingdetailgtnWsRequest,
						pricingdetailgtnResponse);
			} else {
				getItemLogic().getCDPricingDetailViewTableData(pricingdetailgtnWsRequest, pricingdetailgtnResponse);
			}
			pricingdetailgeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			pricingdetailgeneralResponse.setSucess(false);
			pricingdetailgeneralResponse.setGtnGeneralException(ex);
		}
		pricingdetailgtnResponse.setGtnWsGeneralResponse(pricingdetailgeneralResponse);
		logger.info("Exit getCDPricingDetailViewTableData");
		return pricingdetailgtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest cdPricingProtectionRequest) {
		logger.info("Enter getCDPricingProtectionTableData");
		GtnUIFrameworkWebserviceResponse cdPricingProtectionResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdPricingProtectionGeneralResponse = new GtnWsGeneralResponse();
		try {

			String recordType = GtnWsConstants.EMPTY;

			if (!cdPricingProtectionRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {

				GtnWebServiceSearchCriteria searchCriteria = cdPricingProtectionRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", GtnWsConstants.EMPTY).replace("]",
							GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(recordType)) {
				getItemLogic().getCDPricingProtectionPendingTableData(cdPricingProtectionRequest,
						cdPricingProtectionResponse);
			} else {
				getItemLogic().getCDPricingProtectionTableData(cdPricingProtectionRequest, cdPricingProtectionResponse);
			}

			cdPricingProtectionGeneralResponse.setSucess(true);

		} catch (GtnFrameworkGeneralException ex) {
			cdPricingProtectionGeneralResponse.setSucess(false);
			cdPricingProtectionGeneralResponse.setGtnGeneralException(ex);
		}
		cdPricingProtectionResponse.setGtnWsGeneralResponse(cdPricingProtectionGeneralResponse);
		logger.info("Exit getCDPricingProtectionTableData");
		return cdPricingProtectionResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_EXCEL_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionExcelData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCDPricingProtectionTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getItemLogic().getCDPricingProtectionExcelData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getCDPricingProtectionTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_VIEW_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDPricingProtectionViewTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest pricingprotectiongtnWsRequest) {
		logger.info("Enter getCDPricingProtectionViewTableData");
		GtnUIFrameworkWebserviceResponse pricingprotectiongtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse pricingprotectiongeneralResponse = new GtnWsGeneralResponse();
		try {

			String pricingprotectionrecordType = GtnWsConstants.EMPTY;

			if (!pricingprotectiongtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {

				GtnWebServiceSearchCriteria pricingprotectionsearchCriteria = pricingprotectiongtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				if (!pricingprotectionsearchCriteria.isFilter()) {
					pricingprotectionrecordType = pricingprotectionsearchCriteria.getFilterValue1()
							.replace("[", GtnWsConstants.EMPTY).replace("]", GtnWsConstants.EMPTY);
				}

			}
			if (GtnWsContractDashboardContants.PENDING.equals(pricingprotectionrecordType)) {
				getItemLogic().getCDPricingProtectionViewPendingTableData(pricingprotectiongtnWsRequest,
						pricingprotectiongtnResponse);
			} else {

				getItemLogic().getCDPricingProtectionViewTableData(pricingprotectiongtnWsRequest,
						pricingprotectiongtnResponse);
			}
			pricingprotectiongeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			pricingprotectiongeneralResponse.setSucess(false);
			pricingprotectiongeneralResponse.setGtnGeneralException(ex);
		}
		pricingprotectiongtnResponse.setGtnWsGeneralResponse(pricingprotectiongeneralResponse);
		logger.info("Exit getCDPricingProtectionViewTableData");
		return pricingprotectiongtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_REBATE_DETAIL_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDRebateDetailTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest cdRebateDetailRequest) {
		logger.info("Enter getCDRebateDetailTableData");
		GtnUIFrameworkWebserviceResponse cdRebateDetailResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse cdRebateDetailGeneralResponse = new GtnWsGeneralResponse();
		try {
			String recordType = GtnWsConstants.EMPTY;

			if (!cdRebateDetailRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = cdRebateDetailRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", GtnWsConstants.EMPTY).replace("]",
							GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(recordType)) {
				getItemLogic().getCDRebateDetailPendingTableData(cdRebateDetailRequest, cdRebateDetailResponse);
			} else {
				getItemLogic().getCDRebateDetailTableData(cdRebateDetailRequest, cdRebateDetailResponse);
			}

			cdRebateDetailGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			cdRebateDetailGeneralResponse.setSucess(false);
			cdRebateDetailGeneralResponse.setGtnGeneralException(ex);
		}
		cdRebateDetailResponse.setGtnWsGeneralResponse(cdRebateDetailGeneralResponse);
		logger.info("Exit getCDRebateDetailTableData");
		return cdRebateDetailResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CD_REBATE_DETAIL_VIEW_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCDRebateDetailViewTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest rebatedetailgtnWsRequest) {
		logger.info("Enter getCDRebateDetailViewTableData");
		GtnUIFrameworkWebserviceResponse rebatedetailgtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse rebatedetailgeneralResponse = new GtnWsGeneralResponse();
		try {

			String rebatedetailrecordType = GtnWsConstants.EMPTY;

			if (!rebatedetailgtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {

				GtnWebServiceSearchCriteria rebatedetailsearchCriteria = rebatedetailgtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0);
				if (!rebatedetailsearchCriteria.isFilter()) {
					rebatedetailrecordType = rebatedetailsearchCriteria.getFilterValue1()
							.replace("[", GtnWsConstants.EMPTY).replace("]", GtnWsConstants.EMPTY);
				}

			}

			if (GtnWsContractDashboardContants.PENDING.equals(rebatedetailrecordType)) {
				getItemLogic().getCDRebateDetailViewPendingTableData(rebatedetailgtnWsRequest, rebatedetailgtnResponse);
			} else {
				getItemLogic().getCDRebateDetailViewTableData(rebatedetailgtnWsRequest, rebatedetailgtnResponse);
			}
			rebatedetailgeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			rebatedetailgeneralResponse.setSucess(false);
			rebatedetailgeneralResponse.setGtnGeneralException(ex);
		}
		rebatedetailgtnResponse.setGtnWsGeneralResponse(rebatedetailgeneralResponse);
		logger.info("Exit getCDRebateDetailViewTableData");
		return rebatedetailgtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_NSF_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getNSFLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getNSFLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getNSFLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getNSFLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CFP_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCFPLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCFPLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getCFPLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getCFPLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_IFP_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getIFPLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getIFPLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getIFPLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getIFPLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_TP_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getTPLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getTPLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getTPLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getTPLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_PS_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getPSLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getPSLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getPSLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getPSLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_RS_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRSLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getRSLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getRSLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getRSLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_RULES_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRulesLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getRulesLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getRulesLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getRulesLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRuleDetailsLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getRuleDetailsLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getRuleDetailsLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getRuleDetailsLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.VALIDATE_CONTRACT_TO_PROCESS, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse validateContractToProcess(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validateContractToProcess");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getProcessLogic().validateContractToProcess(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in validateContractToProcess", ex);
		}
		logger.info("Exit validateContractToProcess");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.PROCESS_CONTRACT_INFO_TO_SESSION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse processContractInfoToSession(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter processContractInfoToSession");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().processContractInfoToSession(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit processContractInfoToSession");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.DELETE_CONTRACT_INFO_ON_BACK_PROCESS, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteContractInfoOnBackProcess(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter deleteContractInfoOnBackProcess");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().deleteContractInfoOnBackProcess(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit deleteContractInfoOnBackProcess");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CONTRACT_INFO_FIELD_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getContractInfoFieldData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getContractInfoFieldData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getContractInfoFieldData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getContractInfoFieldData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_COMPANY_INFO_FIELD_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCompanyInfoFieldData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCompanyInfoFieldData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getCompanyInfoFieldData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getCompanyInfoFieldData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_ITEM_INFO_FIELD_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getItemInfoFieldData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getItemInfoFieldData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getItemInfoFieldData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getItemInfoFieldData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_PRICING_INFO_FIELD_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getPricingInfoFieldData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getPricingInfoFieldData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getPricingInfoFieldData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getPricingInfoFieldData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_REBATE_INFO_FIELD_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRebateInfoFieldData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getRebateInfoFieldData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getRebateInfoFieldData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getRebateInfoFieldData");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsContractDashboardContants.CHECK_SELECTED_COMPANIES)
	public GtnUIFrameworkWebserviceResponse checkSelectedCompanies(
			@RequestBody GtnUIFrameworkWebserviceRequest selectedCompaniesRequest) {
		logger.info("Enter checkSelectedCompanies");
		GtnUIFrameworkWebserviceResponse selectedCompaniesResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			selectedCompaniesResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			selectedCompaniesResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			selectedCompaniesResponse.setGtnWsContractDashboardResponse(cdResponse);
			String query = getGtnWsSqlService().getQuery("checkSelectedCDCompanies");
			GtnWsGeneralRequest inputs = selectedCompaniesRequest.getGtnWsGeneralRequest();
			Object[] params = { inputs.getUserId(), inputs.getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			List<Object[]> result = executeQuery(query, params, types);
			cdResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			return selectedCompaniesResponse;
		} catch (Exception ex) {
			selectedCompaniesResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkSelectedCompanies Query", ex);
			return selectedCompaniesResponse;
		} finally {
			logger.info("Exit checkSelectedCompanies");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsContractDashboardContants.CHECK_SELECTED_ITEMS)
	public GtnUIFrameworkWebserviceResponse checkSelectedItems(
			@RequestBody GtnUIFrameworkWebserviceRequest selectedItemsRequest) {
		logger.info("Enter checkSelectedItems");
		GtnUIFrameworkWebserviceResponse selectedItemsResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			selectedItemsResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			selectedItemsResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			selectedItemsResponse.setGtnWsContractDashboardResponse(cdResponse);
			String query = getGtnWsSqlService().getQuery("checkSelectedCDItems");
			GtnWsGeneralRequest inputs = selectedItemsRequest.getGtnWsGeneralRequest();
			Object[] params = { inputs.getUserId(), inputs.getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			List<Object[]> result = executeQuery(query, params, types);
			cdResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			return selectedItemsResponse;
		} catch (Exception ex) {
			selectedItemsResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting checkSelectedItems Query", ex);
			return selectedItemsResponse;
		} finally {
			logger.info("Exit checkSelectedItems");
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.REMOVE_COMPANIES)
	public GtnUIFrameworkWebserviceResponse removeCompanies(
			@RequestBody GtnUIFrameworkWebserviceRequest removeCompaniesRequest) {
		logger.info("Enter removeCompanies");
		GtnUIFrameworkWebserviceResponse removeCompaniesResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			removeCompaniesResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			removeCompaniesResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			removeCompaniesResponse.setGtnWsContractDashboardResponse(cdResponse);
			String query = getGtnWsSqlService().getQuery("deleteCDCompanies");
			GtnWsGeneralRequest inputs = removeCompaniesRequest.getGtnWsGeneralRequest();
			Object[] params = { inputs.getUserId(), inputs.getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = executeUpdateQuery(query, params, types);
			cdResponse.setCount(count);
			return removeCompaniesResponse;
		} catch (Exception ex) {
			removeCompaniesResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting removeCompanies Query", ex);
			return removeCompaniesResponse;
		} finally {
			logger.info("Exit removeCompanies and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.REMOVE_ITEMS)
	public GtnUIFrameworkWebserviceResponse removeItems(
			@RequestBody GtnUIFrameworkWebserviceRequest removeItemsRequest) {
		logger.info("Enter removeItems");
		GtnUIFrameworkWebserviceResponse removeItemsResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			removeItemsResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			removeItemsResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			removeItemsResponse.setGtnWsContractDashboardResponse(cdResponse);
			String query = getGtnWsSqlService().getQuery("deleteCDItems");
			GtnWsGeneralRequest inputs = removeItemsRequest.getGtnWsGeneralRequest();
			Object[] params = { inputs.getUserId(), inputs.getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = executeUpdateQuery(query, params, types);
			cdResponse.setCount(count);
			return removeItemsResponse;
		} catch (Exception ex) {
			removeItemsResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting removeItems Query", ex);
			return removeItemsResponse;
		} finally {
			logger.info("Exit removeItems and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_RP_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRPLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getRPLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getRPLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getRPLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_DC_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDCLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getDCLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getDCLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getDCLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_FORMULA_LOOKUP_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getFormulaLookupTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getFormulaLookupTableData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLookupLogic().getFormulaLookupTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getFormulaLookupTableData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.VALIDATE_CONTRACT_DASHBOARD_TO_SAVE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse validateContractDashboardToSave(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validateContractDashboardToSave");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getSubmitLogic().validateContractDashboardToSave(gtnWsRequest.getGtnWsContractDashboardRequest(),
					cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in validateContractDashboardToSave", ex);
		}

		logger.info("Exit validateContractDashboardToSave");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.SUBMIT_CONTRACT_DASHBOARD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse submitContractDashboard(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter submitContractDashboard");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getSubmitLogic().saveContractDashboard(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse,
					getRequest);
		} catch (Exception ex) {
			logger.error("Exception in submitContractDashboard", ex);
		}

		logger.info("Exit submitContractDashboard");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.VALIDATE_CONTRACT_TO_REBUILD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse validateContractToRebuild(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validateContractToRebuild");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			getProcessLogic().validateContractToRebuild(gtnWsRequest, gtnResponse);
		} catch (Exception ex) {
			logger.error("Exception in validateContractToRebuild", ex);
		}

		logger.info("Exit validateContractToRebuild");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CONTRACT_TO_REBUILD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getContractDashboardRebuildTreeData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getContractDashboardRebuildTreeData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			getProcessLogic().getContractDashboardRebuildTreeData(gtnWsRequest, gtnResponse);
		} catch (Exception ex) {
			logger.error("Exception in getContractDashboardRebuildTreeData", ex);
		}

		logger.info("Exit getContractDashboardRebuildTreeData");
		return gtnResponse;
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		try {
			StringBuilder cdWhereClauseSql = new StringBuilder();
			switch (expersion) {
			case "BETWEEN":
				cdWhereClauseSql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) >= '").append(parseDate(value1)).append("' AND ");
				cdWhereClauseSql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) <= '").append(parseDate(value2)).append("' ");
				break;
			case "AND":
				cdWhereClauseSql.append(field).append(" < '").append(value1).append("' AND ");
				cdWhereClauseSql.append(field).append(" > '").append(value2).append("' ");
				break;
			case "GREATER_OR_EQUAL":
				cdWhereClauseSql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) >= '").append(parseDate(value1)).append("' ");
				break;
			case "LESS_OR_EQUAL":
				cdWhereClauseSql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) <= '").append(parseDate(value1)).append("' ");
				break;
			case "LIKE":
				cdWhereClauseSql.append(field).append(' ').append(expersion).append(" '")
						.append(value1.replace('*', '%').replaceAll("\\s+", "%")).append("' ");
				break;
			case "EQUAL":
			case "EQUALS":
				cdWhereClauseSql.append(field).append(" = '").append(value1).append("' ");
				break;
			case "GREATER":
				cdWhereClauseSql.append(field).append(" > '").append(value1).append("' ");
				break;
			case "LESS":
				cdWhereClauseSql.append(field).append(" < '").append(value1).append("' ");
				break;
			default:
				cdWhereClauseSql.append(field).append(' ').append(expersion).append(" '").append(value1).append("' ");
				break;
			}
			return cdWhereClauseSql.toString();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getWhereClauseForAColumn", ex);
		}
	}

	private String parseDate(String value1) {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = null;
		try {
			date = inputFormat.parse(value1);
		} catch (ParseException e) {
			
			logger.error("Date Parse Exception:"+e);
		}
		DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return outputFormat.format(date);
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			logger.error("Exception in getSysSchemaCatalog", ex);
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}

	public String getPopulateValue(String field, Object populateValue) {
		String value = "NULL";
		SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
		if (populateValue != null) {
			value = "'";
			String val = (field.toLowerCase(Locale.ENGLISH).contains("date"))
					? dbDate.format(new Date((Long) populateValue)) : String.valueOf(populateValue);
			value += val;
			value += "'";
		}
		return value;
	}

	@SuppressWarnings("rawtypes")
	public String getQuery(List input, String queryName) {
		return gtnWsSqlService.getQuery(input, queryName);
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_PROCESS_TABLE_EXCEL_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getContractDashboardProcessTableExcelData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getContractDashboardProcessTableExcelData");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getProcessLogic().getContractDashboardProcessTreeExcelData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		logger.info("Exit getContractDashboardProcessTableExcelData");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.APPROVE_CONTRACT_DASHBOARD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse approveContractDashboard(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter submitContractDashboard");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getSubmitLogic().approveContractDashboard(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse);
			checkAndUpdateAllrelationShip();
		} catch (Exception ex) {
			logger.error("Exception in submitContractDashboard", ex);
		}

		logger.info("Exit submitContractDashboard");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.REJECT_CANCEL_CONTRACT_DASHBOARD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse rejectContractDashboard(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter rejectcancelContractDashboard");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			getSubmitLogic().rejectContractDashboard(gtnWsRequest.getGtnWsContractDashboardRequest(), cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in rejectcancelContractDashboard", ex);
		}

		logger.info("Exit rejectcancelContractDashboard");
		return gtnResponse;
	}

	@RequestMapping(value = "/contractPriceSchedulePriceProtectionTab", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceSchedulePriceProtectionTabService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnContractProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponseContract = new GtnWsGeneralResponse();
		gtnContractProtectionUpdateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponseContract);

		GtnWsGeneralRequest contractPriceProtectionWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

		List<Object> inputParamList = contractPriceProtectionWSRequest.getComboBoxWhereclauseParamList();

		try {
			getItemLogic().contractPriceProtectionInsertTemp(inputParamList, gtnContractProtectionUpdateResponse);
			gtnWsGeneralResponseContract.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponseContract.setSucess(false);
			gtnWsGeneralResponseContract.setGtnGeneralException(e);
		}

		return gtnContractProtectionUpdateResponse;
	}
        @RequestMapping(value = "/contractPriceProtectionStartDateAlert", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse contractPriceProtectionStartDateAlertTabService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnContractProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponseContract = new GtnWsGeneralResponse();
		gtnContractProtectionUpdateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponseContract);

		GtnWsGeneralRequest contractPriceProtectionWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

		List<Object> inputList = contractPriceProtectionWSRequest.getComboBoxWhereclauseParamList();

		try {
			getItemLogic().contractPriceProtectionStartDateAlert(inputList, gtnContractProtectionUpdateResponse);
			gtnWsGeneralResponseContract.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			gtnWsGeneralResponseContract.setSucess(false);
			gtnWsGeneralResponseContract.setGtnGeneralException(e);
		}

		return gtnContractProtectionUpdateResponse;
	}

	public void checkAndUpdateAllrelationShip() {
		try {
			automaticRelationService.checkAndUpdateAllRelationShip("");
		} catch (Exception e) {
			logger.error("Error checkAndUpdateAllrelationShip" + e.getMessage());
		}

	}
        
}
