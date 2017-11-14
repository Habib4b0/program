package com.stpl.gtn.gtn2o.ws.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnQueryLogger;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
public class GtnWsGeneralController {
	public GtnWsGeneralController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsGeneralController.class);

	@Autowired
	private GtnQueryLogger queryLogger;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public GtnWsAllListConfig getGtnComboBoxUtil() {
		return gtnWebServiceAllListConfig;
	}

	public GtnQueryLogger getQueryLogger() {
		return queryLogger;
	}

	public void setQueryLogger(GtnQueryLogger queryLogger) {
		this.queryLogger = queryLogger;
	}

	public void setGtnWebServiceAllListConfig(GtnWsAllListConfig gtnWebServiceAllListConfig) {
		this.gtnWebServiceAllListConfig = gtnWebServiceAllListConfig;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public void setSysSessionFactory(org.hibernate.SessionFactory sysSessionFactory) {
		this.sysSessionFactory = sysSessionFactory;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
			+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getComboBoxResultSet(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info(GtnFrameworkWebserviceConstant.ENTER_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RES);

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		try {
			Map<String, String> comboBoxTypeResponseMap = gtnWebServiceAllListConfig.getComboBoxQueryMap();
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String comboBoxType = generalWSRequest.getComboBoxType();
			String query = comboBoxTypeResponseMap.get(comboBoxType);

			List<Object[]> resultList = null;

			if (query != null) {
				if (!"loadFromXML".equals(query)) {
					if (query.contains("?")) {
						if (generalWSRequest.getComboBoxWhereclauseParamList() != null) {
							resultList = executeQuery(query, generalWSRequest.getComboBoxWhereclauseParamList());
						}
					} else {
						resultList = executeQuery(query);
					}

				} else if ("loadFromXML".equals(query)) {
					resultList = executeQuery(gtnWsSqlService.getQuery(null, comboBoxType));
				}
				GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
				comboBoxResponse.setComboBoxList(resultList);
				gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
			} else {

				Map<String, GtnUIFrameworkWebserviceComboBoxResponse> comboBoxResponseMap = gtnWebServiceAllListConfig
						.getComboBoxResponseMap();
				gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponseMap.get(comboBoxType));
			}

		} catch (GtnFrameworkGeneralException gtnException) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GET_COMBO_BOX_RESULT_SET, gtnException);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(gtnException.getException());
		} catch (Exception exception) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GET_COMBO_BOX_RESULT_SET, exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		} finally {
			logger.info(GtnFrameworkWebserviceConstant.EXIT_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RESP);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
			+ GtnWebServiceUrlConstants.GTN_COMMON_RELOAD_COMBO_BOX, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse reloadHelperTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		Map<String, GtnUIFrameworkWebserviceComboBoxResponse> comboBoxResponseMap = gtnWebServiceAllListConfig
				.getComboBoxResponseMap();
		comboBoxResponseMap.clear();
		gtnWebServiceAllListConfig.loadAllComboBoxListFromHelperTable();
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> executeQuery(String sqlQuery, List<Object> comboBoxWhereclauseParamList)
			throws GtnFrameworkGeneralException {
		logger.queryLog("Executing query with whereclause: " + sqlQuery);

		List<Object[]> list = null;
		try {
			list = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(sqlQuery, comboBoxWhereclauseParamList);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes" })
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		List queryValueList = null;
		try {
			queryValueList = gtnSqlQueryEngine.executeSelectQuery(sqlQuery, params, type);

		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return queryValueList;
	}

	public int executeUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		int id = 0;
		try {
			id = gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery, params, type);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return id;
	}

	public int executeUpdateQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		int updateOrDeletedRecordCount = 0;
		try {
			updateOrDeletedRecordCount = gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return updateOrDeletedRecordCount;
	}

	public String getDescriptionFromHelperTable(int id) {
		String tableSelectQuery = "select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID = " + id;
		try {
			return (String) executeQuery(tableSelectQuery).get(0);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Error while getting Description.", ex);
		}
		return null;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
			+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_NON_HELPER_DDLB, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getNonHelperComboBoxResultSet(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		logger.info(GtnFrameworkWebserviceConstant.ENTER_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RES);

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String comboBoxType = generalWSRequest.getComboBoxType();
			Map<String, GtnUIFrameworkWebserviceComboBoxResponse> comboBoxResponseMap = gtnWebServiceAllListConfig
					.getNonHelperComboBoxResponseMap();
			gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponseMap.get(comboBoxType));

		} catch (Exception exception) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GET_COMBO_BOX_RESULT_SET, exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		} finally {
			logger.info(GtnFrameworkWebserviceConstant.EXIT_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RESP);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
			+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getUserIdNameBoxResultSet(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info(GtnFrameworkWebserviceConstant.ENTER_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RES);

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		try {

			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = gtnWebServiceAllListConfig
					.getUserIdNameResponse();
			gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);

		} catch (Exception exception) {
			logger.error("Exception in loadUserIdNameComboBox ", exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		} finally {
			logger.info(GtnFrameworkWebserviceConstant.EXIT_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RESP);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;

	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
			+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getUserNameByUserId(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getUserNameByUserId");

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);
		try {
			generalWSResponse.setUserName(gtnWebServiceAllListConfig.getUserIdNameMap()
					.get(Integer.valueOf(gtnWsRequest.getGtnWsGeneralRequest().getUserId())));
		} catch (Exception exception) {
			logger.error("Exception in getUserNameByUserId ", exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		} finally {
			logger.info("Exit getUserNameByUserId");
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;

	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
			+ GtnWebServiceUrlConstants.GTN_TEMP_EXCEL_FILE_DELETE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteTempExcelFile(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter deleteTempExcelFile");
		boolean isdeleted = false;
		GtnUIFrameworkWebserviceResponse generalWSResponse = new GtnUIFrameworkWebserviceResponse();
		generalWSResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		try {

			String filePath = gtnWsRequest.getGtnWsGeneralRequest().getExtraParameter().toString();
			File dir = GtnFileNameUtils.getFile(filePath);
			if (dir.isDirectory()) {
				String[] entries = dir.list();
				if (entries != null)
					for (String child : entries) {
						File file = GtnFileNameUtils.getFile(dir, child);
						isdeleted = file.delete();

					}
			}
			generalWSResponse.getGtnWsGeneralResponse().setSucess(isdeleted);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			generalWSResponse.getGtnWsGeneralResponse().setSucess(false);
			generalWSResponse.getGtnWsGeneralResponse()
					.setGtnGeneralException(new GtnFrameworkGeneralException("Error in deleting Temp File", ex));
			return generalWSResponse;
		}

		logger.info("Exit deleteTempExcelFile");
		return generalWSResponse;

	}

}
