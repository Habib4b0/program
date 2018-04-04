package com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "gtnReturnsForecasting/DataSelection")
public class GtnWsReturnsSearchController {
	public GtnWsReturnsSearchController() {
		/**
		 * empty constructor
		 */
	}

	private Map<String, String> filterAndSortingProjectionCriteriaMap = new HashMap<>();
	private Map<String, String> searchCriteriaMap = new HashMap<>();

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsSearchController.class);
	@Autowired
	private GtnWsGeneralController gtnGeneralServiceController;
        @Autowired
	private GtnWsSqlService gtnWsSqlService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getDataSelectionSearch", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDataSelectionSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCountForecastingReturnsSearch"
					: "getDataForecastingReturnsSearch";

			List<Object[]> result = gtnGeneralServiceController.executeQuery(
					gtnWsSqlService.getQuery(getSearchInput(gtnWsRequest), queryName));
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException | SQLException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;
	}

	private List<Object> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException, SQLException {
		List<Object> list = new ArrayList<>();

		Connection connection = null;
		StringBuilder inputWhereConditions = new StringBuilder();

		try {
			connection = gtnGeneralServiceController.getSysSessionFactory().getSessionFactoryOptions()
					.getServiceRegistry().getService(ConnectionProvider.class).getConnection();

			for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {

				if (searchCriteria.isFilter()) {
					frameFilterConditionQuery(searchCriteria, inputWhereConditions);
				} else {
					frameSelectConditionQuery(searchCriteria, inputWhereConditions);
				}
			}

			list.add(connection.getCatalog());
			list.add(inputWhereConditions);

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				list.addAll(getSortedInputProjectionColumn(
						gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return list;
	}

	void frameFilterConditionQuery(GtnWebServiceSearchCriteria searchCriteria, StringBuilder inputWhereConditions)
			throws ParseException {
		if (searchCriteria.getFieldId().contains("Date")) {
			searchCriteria.setFilterValue1(getDate(searchCriteria.getFilterValue1()));
			if (searchCriteria.getFilterValue2() != null) {
				searchCriteria.setFilterValue2(getDate(searchCriteria.getFilterValue2()));
				inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
						.append(filterAndSortingProjectionCriteriaMap().get(searchCriteria.getFieldId())).append(' ')
						.append(searchCriteria.getExpression()).append(" '").append(searchCriteria.getFilterValue1())
						.append("' AND '").append(searchCriteria.getFilterValue2()).append("' ");
			}
		} else {
			inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
					.append(filterAndSortingProjectionCriteriaMap().get(searchCriteria.getFieldId())).append(' ')
					.append(searchCriteria.getExpression()).append(" '").append(searchCriteria.getFilterValue1())
					.append("' ");
		}
	}

	void frameSelectConditionQuery(GtnWebServiceSearchCriteria searchCriteria, StringBuilder inputWhereConditions) {
		if ("returnsForecastMainScreenDataSelection_dualListBoxComp".equals(searchCriteria.getFieldId())) {
			inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
					.append(searchCriteriaMap().get(searchCriteria.getFieldId())).append(searchCriteria.getExpression())
					.append(searchCriteria.getFilterValue1());
		} else if (searchCriteria.getFieldId().contains("Period")) {

			inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
					.append(searchCriteriaMap().get(searchCriteria.getFieldId())).append(' ')
					.append(searchCriteria.getExpression()).append(" '")
					.append(GtnCommonUtil.getDateFromQuarter(searchCriteria.getFilterValue1())).append("' ");
		} else {

			inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
					.append(searchCriteriaMap().get(searchCriteria.getFieldId())).append(' ')
					.append(searchCriteria.getExpression()).append(" '")
					.append(searchCriteria.getFilterValue1().replace('*', '%')).append("' ");
		}
	}

	private List<Object> getSortedInputProjectionColumn(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(filterAndSortingProjectionCriteriaMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(filterAndSortingProjectionCriteriaMap().get("createdDate") + "DESC");
		}
		return list;
	}

	private Map<String, String> filterAndSortingProjectionCriteriaMap() {
		if (filterAndSortingProjectionCriteriaMap.isEmpty()) {
			filterAndSortingProjectionCriteriaMap.put("projectionName", "PM.PROJECTION_NAME");
			filterAndSortingProjectionCriteriaMap.put("projectionDescription", "PM.PROJECTION_DESCRIPTION");
			filterAndSortingProjectionCriteriaMap.put("productHierarchy", "HDP.HIERARCHY_NAME");
			filterAndSortingProjectionCriteriaMap.put("productLevel", "PM.PRODUCT_HIERARCHY_LEVEL");
			filterAndSortingProjectionCriteriaMap.put("createdBy", "CONCAT(usr.LastName ,', ',  usr.firstName) ");
			filterAndSortingProjectionCriteriaMap.put("lastModifiedDate", "PM.MODIFIED_DATE");
			filterAndSortingProjectionCriteriaMap.put("company", "CM.COMPANY_NAME");
			filterAndSortingProjectionCriteriaMap.put("businessUnit", "CM1.COMPANY_NAME");
			filterAndSortingProjectionCriteriaMap.put("createdDate", "PM.CREATED_DATE ");

		}
		return filterAndSortingProjectionCriteriaMap;
	}

	private Map<String, String> searchCriteriaMap() {
		if (searchCriteriaMap.isEmpty()) {
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_projectionName", "PM.PROJECTION_NAME");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_projectionDescription",
					"PM.PROJECTION_DESCRIPTION");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_productHierarchy",
					"PM.PRODUCT_HIERARCHY_SID");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_company", "PM.COMPANY_MASTER_SID");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_businessUnit", "PM.BUSINESS_UNIT");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_productGroup", "PM.ITEM_GROUP_SID");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_relationShipCombobox",
					"PM.PROD_RELATIONSHIP_BUILDER_SID");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_forecastLevel", "PM.PRODUCT_HIERARCHY_LEVEL");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_dualListBoxComp", "RLD.HIERARCHY_NO");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_fromPeriod", "PM.FROM_DATE");
			searchCriteriaMap.put("returnsForecastMainScreenDataSelection_toPeriod", "PM.TO_DATE");
		}
		return searchCriteriaMap;
	}

	public String getDate(String input) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = formatter.parse(input);
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		return commonDate.format(date);
	}

}
