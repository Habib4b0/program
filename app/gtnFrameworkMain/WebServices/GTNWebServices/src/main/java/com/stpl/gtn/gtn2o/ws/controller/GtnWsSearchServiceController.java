package com.stpl.gtn.gtn2o.ws.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

@RestController
public class GtnWsSearchServiceController {
	public GtnWsSearchServiceController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSearchServiceController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnWsAllListConfig getGtnComboBoxUtil() {
		return gtnWebServiceAllListConfig;
	}

	public void setGtnWebServiceAllListConfig(GtnWsAllListConfig gtnWebServiceAllListConfig) {
		this.gtnWebServiceAllListConfig = gtnWebServiceAllListConfig;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE
			+ GtnWebServiceUrlConstants.GTN_COMMON_SEARCH, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getSearchResultForAllModule(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		try {

			generalWSResponse.setSucess(true);
			String queryName = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
			boolean isCount = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount();

			GtnWsSearchQueryConfigLoaderType searchQueryconfigLoaderType = gtnUIFrameworkWebserviceRequest
					.getGtnWsSearchRequest().getSearchConfigLodaderType();
			GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) searchQueryconfigLoaderType
					.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
					.get(queryName);

			GtnWsSearchQueryGenerationLogic searchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					gtnWebServiceSearchQueryConfig, gtnUIFrameworkWebserviceRequest);

			String generatedQuery = searchQueryGenerationLogic.generateSearchQuery();

			List<Object[]> resultList = executeQuery(generatedQuery);

			if (!isCount && gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap() != null) {
				getCustomizedSearchFormFromObject(resultList, gtnWebServiceSearchQueryConfig,
						gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchColumnNameList());

			}
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			if (isCount) {

				gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(resultList.get(0))));

			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(resultList);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception exception) {
			logger.error("Exception in getSearchResultForAllModule ", exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		} finally {
			logger.info("Exit getSearchResultForAllModule");
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	public void getCustomizedSearchFormFromObject(final List<Object[]> list,
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext, List<Object> columnName) {
		List<String> typeList = findTypeList(gtnWebServiceSearchQueryContext, columnName);

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {
				final Object[] obj = list.get(i);
                                
				for (int index = 0; index < typeList.size(); index++) {
					switch (typeList.get(index)) {
					case "String":
						obj[index] = getString(obj[index]);
						break;
					case "Integer":
						obj[index] = getInteger(obj[index]);

						break;
					case "Helper":
						obj[index] = getHelperValue(obj[index]);
						break;
					case "Boolean":
						obj[index] = Boolean.parseBoolean(getString(obj[index]));
						break;
					case "Date":
						Date startDate = (Date) obj[index];
						obj[index] = startDate;
						break;
					case "User":

						obj[index] = gtnWebServiceAllListConfig.getUserIdNameMap().get(obj[index]);
						break;
					case "BigDecimal":
						BigDecimal numericColumn = (BigDecimal) obj[index];
						obj[index] = numericColumn == null ? numericColumn : numericColumn.doubleValue();
						break;
					default:
						obj[index] = (obj[index]);
						break;
					}
				}
			}
		}
	}

	List<String> findTypeList(GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext, List<Object> columnName) {
		Map<String, GtnWsColumnDetailsConfig> rearrangedOrder = gtnWebServiceSearchQueryContext
				.getFieldToColumnDetailsMap();

		List<String> typeList = new ArrayList<>();
		for (Object ob : columnName) {
			if (rearrangedOrder.get(ob) != null) {
				typeList.add(rearrangedOrder.get(ob).getDataType());
			}

		}
		return typeList;
	}

	Object getHelperValue(Object object) {
		Object defaultValue = "";
		Map<Integer, String> helperMap = gtnWebServiceAllListConfig.getIdDescMap();

		if (object != null && StringUtils.isNotBlank(object.toString())
				&& (Integer.valueOf(String.valueOf(object)) != 0)) {
			defaultValue = helperMap.get(Integer.valueOf(String.valueOf(object)));
		}
		return defaultValue;
	}

	public String getString(final Object str) {
		String stringOut = "";
		stringOut = StringUtils.trimToEmpty(String.valueOf(str));
		if (GtnWsConstants.NULL.equals(stringOut) || GtnWsConstants.SELECT_ONE.equals(stringOut)) {
			stringOut = StringUtils.EMPTY;
		}
		return stringOut;
	}

	public Integer getInteger(final Object obj) {
		int integerOut = 0;
		try {
			integerOut = Integer.parseInt(String.valueOf(obj));
		} catch (NumberFormatException e) {
			// Expecting a integer If not No action needed
		}
		return integerOut;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.debug("Final Search Qurryyyyy:: " + sqlQuery);
		return getGtnSqlQueryEngine().executeSelectQuery(sqlQuery);
	}
}
