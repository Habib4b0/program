package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
@RequestMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE)
public class GtnWsIfpAddCotroller {
	public GtnWsIfpAddCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsIfpAddCotroller.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsIfpAddService ifpWebservice;

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

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_SEARCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse ifpSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest ifpSearchRequest) {
		GtnUIFrameworkWebserviceResponse ifpSearchResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			ifpSearchResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			ifpSearchResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionSearch");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String query = ifpWebservice.ifpLeftTableSearchQuery(ifpSearchRequest); 
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query);
			if (ifpSearchRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
			} else {
				GtnUIFrameworkDataTable ifpSearchDataTable = new GtnUIFrameworkDataTable();
				ifpSearchDataTable.addData((List<Object[]>) resultList);
				gtnSerachResponse.setResultSet(ifpSearchDataTable);
			}
			ifpSearchResponse.setGtnSerachResponse(gtnSerachResponse);
			return ifpSearchResponse;
		} catch (GtnFrameworkGeneralException ex) {
			ifpSearchResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionSearch Query", ex);
			ifpSearchResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return ifpSearchResponse;
		} finally {
			logger.info("Exit itemAdditionSearch");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_RIGHT_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveRight(
			@RequestBody GtnUIFrameworkWebserviceRequest ifpItemAdditionRequest) {
		GtnUIFrameworkWebserviceResponse ifpItemAdditionResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			ifpItemAdditionResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			ifpItemAdditionResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionMoveRight");
			String query = gtnWsSqlService.getQuery("getIFPItemAdditionMoveRightQuery");

			GtnWsIfpRequest gtnWsCfpRequest = ifpItemAdditionRequest.getGtnWsIfpRequest();
			GtnIFamilyPlanBean inputs = gtnWsCfpRequest.getGtnIFamilyPlan();
			Object[] params = { inputs.getUpdateBean().getItemMasterSid(),
					ifpItemAdditionRequest.getGtnWsGeneralRequest().getUserId(),
					ifpItemAdditionRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return ifpItemAdditionResponse;
		} catch (GtnFrameworkGeneralException ex) {
			ifpItemAdditionResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionMoveRight Query", ex);
			return ifpItemAdditionResponse;
		} finally {
			logger.info("Exit itemAdditionMoveRight and inserted or updated " + count
					+ GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_LEFT_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest itemAdditionMoveLeftRequest) {
		GtnUIFrameworkWebserviceResponse itemAdditionMoveLeftResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsIfpRequest gtnWsCfpRequest = itemAdditionMoveLeftRequest.getGtnWsIfpRequest();
		int count = 0;
		try {
			itemAdditionMoveLeftResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			itemAdditionMoveLeftResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionMoveLeft");
			String query = gtnWsSqlService.getQuery("getIfpItemAdditionMoveLeftQuery");
			GtnIFamilyPlanBean inputs = gtnWsCfpRequest.getGtnIFamilyPlan();
			Object[] params = { itemAdditionMoveLeftRequest.getGtnWsGeneralRequest().getUserId(),
					itemAdditionMoveLeftRequest.getGtnWsGeneralRequest().getSessionId(), inputs.getUpdateBean().getItemMasterSid() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return itemAdditionMoveLeftResponse;
		} catch (GtnFrameworkGeneralException ex) {
			itemAdditionMoveLeftResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionMoveLeft Query", ex);
			return itemAdditionMoveLeftResponse;
		} finally {
			logger.info("Exit itemAdditionMoveLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_ALL_LEFT_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveAllLeft(
			@RequestBody GtnUIFrameworkWebserviceRequest ifpMoveAllLeftRequest) {
		GtnUIFrameworkWebserviceResponse ifpMoveAllLeftResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			ifpMoveAllLeftResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			ifpMoveAllLeftResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionMoveAllLeft");
			Object[] params = { ifpMoveAllLeftRequest.getGtnWsGeneralRequest().getUserId(),
					ifpMoveAllLeftRequest.getGtnWsGeneralRequest().getSessionId() };
			String query = ifpWebservice.getIfpMoveAllLeftQuery(ifpMoveAllLeftRequest);
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return ifpMoveAllLeftResponse;
		} catch (GtnFrameworkGeneralException ex) {
			ifpMoveAllLeftResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionMoveAllLeft Query", ex);
			return ifpMoveAllLeftResponse;
		} finally {
			logger.info("Exit itemAdditionMoveAllLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAdditionMoveAllRight(
			@RequestBody GtnUIFrameworkWebserviceRequest ifpMoveAllRightRequest) {
		GtnUIFrameworkWebserviceResponse ifpMoveAllRightResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			ifpMoveAllRightResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			ifpMoveAllRightResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionMoveAllRight");
			Object[] params = { ifpMoveAllRightRequest.getGtnWsGeneralRequest().getUserId(),
					ifpMoveAllRightRequest.getGtnWsGeneralRequest().getSessionId() };
			String query = ifpWebservice.getIfpMoveAllRightQuery(ifpMoveAllRightRequest);
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
			return ifpMoveAllRightResponse;
		} catch (GtnFrameworkGeneralException ex) {
			ifpMoveAllRightResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionMoveAllRight Query", ex);
			return ifpMoveAllRightResponse;
		} finally {
			logger.info("Exit itemAdditionMoveAllRight and inserted or updated " + count
					+ GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemAdditionRightTableSearch(  
			@RequestBody GtnUIFrameworkWebserviceRequest itemAdditionRightRequest) {
		GtnUIFrameworkWebserviceResponse itemAdditionRightResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			itemAdditionRightResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			itemAdditionRightResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemAdditionRightTableSearch");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String query = ifpWebservice.ifpRightTableSearchQuery(itemAdditionRightRequest);
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query);
			if (itemAdditionRightRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
			} else {
				GtnUIFrameworkDataTable itemAdditionRightDataTable = new GtnUIFrameworkDataTable();
				itemAdditionRightDataTable.addData((List<Object[]>) resultList);
				gtnSerachResponse.setResultSet(itemAdditionRightDataTable);
			}
			itemAdditionRightResponse.setGtnSerachResponse(gtnSerachResponse);
			return itemAdditionRightResponse;
		} catch (GtnFrameworkGeneralException ex) {
			itemAdditionRightResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemAdditionRightTableSearch Query", ex);
			return itemAdditionRightResponse;
		} finally {
			logger.info("Exit itemAdditionRightTableSearch");
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse companiesResultTableData(  
			@RequestBody GtnUIFrameworkWebserviceRequest ifpItemsTabRequest) {
		GtnUIFrameworkWebserviceResponse ifpItemsTabResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			ifpItemsTabResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			ifpItemsTabResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companiesResultTableData");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			Object[] params = { ifpItemsTabRequest.getGtnWsGeneralRequest().getUserId(),
					ifpItemsTabRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			String query = ifpWebservice.itemsTabResultTable(ifpItemsTabRequest);
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query, params, types);
			if (ifpItemsTabRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
			} else {
				// set user name for user id
				for (int i = 0; i < resultList.size(); i++) {
					final Object[] obj = (Object[]) resultList.get(i);
					String modifiedByString = (String) obj[14];
					if (modifiedByString != null && !modifiedByString.isEmpty()) {
						obj[14] = gtnWebServiceAllListConfig.getUserIdNameMap().get(Integer.valueOf(modifiedByString));
					}
					String createdByString = (String) obj[16];
					if (createdByString != null && !createdByString.trim().isEmpty())
						obj[16] = gtnWebServiceAllListConfig.getUserIdNameMap().get(Integer.valueOf(createdByString));
					GtnUIFrameworkDataTable ifpItemsTabDataTable = new GtnUIFrameworkDataTable();
					ifpItemsTabDataTable.addData((List<Object[]>) resultList);
					gtnSerachResponse.setResultSet(ifpItemsTabDataTable);
				}
			}

			ifpItemsTabResponse.setGtnSerachResponse(gtnSerachResponse);
			return ifpItemsTabResponse;
		} catch (GtnFrameworkGeneralException ex) {
			ifpItemsTabResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companiesResultTableData Query", ex);
			ifpItemsTabResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return ifpItemsTabResponse;
		} finally {
			logger.info("Exit companiesResultTableData");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_COLUMN_UPDATE_SERVICE)
	public GtnUIFrameworkWebserviceResponse itemsColumnUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter itemsColumnUpdate");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			String query = ifpWebservice.companiesUpdateColumnQuery(gtnWsRequest);
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			return gtnResponse;  
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting itemsColumnUpdate Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit itemsColumnUpdate " + count + " rows");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_FETCH_INFORMATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchIfpInformation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		logger.info("Enter fetchIfpInformation");
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			ifpWebservice.getIfpFetchQuery(gtnWsRequest, gtnResponse);
			count = ifpWebservice.updateIfpCompaniesTabQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting fetchIfpInformation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit fetchIfpInformation and inserted " + count + "rows in IFP temp table");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_TEMP_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getIfpItemsTabDelete(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getIfpItemsTabDelete");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			ifpWebservice.getIfpTabDeleteQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getIfpItemsTabDelete Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit getIfpItemsTabDelete and delete the ifp ");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteIfp(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter deleteIfp");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());

			boolean deleteSuccess = ifpWebservice.deleteIfpModel(gtnWsRequest);
			gtnResponse.getGtnWsGeneralResponse().setSucess(deleteSuccess);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting deleteIfp Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit deleteIfp and delete the ifp ");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_CHECK_ALL_SERVICE)
	public GtnUIFrameworkWebserviceResponse checkAll(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter checkAll");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());

			int deleteSuccess = ifpWebservice.checkAllItems(gtnWsRequest);
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

}
