
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic.GtnWsRelationshipBuilderHelperLogic;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic.GtnWsRelationshipBuilderLogic;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Mahesh.James
 */
@RestController
@RequestMapping(value = GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE)
public class GtnWsRelationshipBuilderController {
	public GtnWsRelationshipBuilderController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRelationshipBuilderController.class);

	private GtnWsRelationshipBuilderLogic logic = new GtnWsRelationshipBuilderLogic(this);
	private GtnWsRelationshipBuilderHelperLogic helperLogic = new GtnWsRelationshipBuilderHelperLogic(this);

	private Map<String, String> filterAndSortingRBCriteriaMap = new HashMap<>();
	private Map<String, String> searchCriteriaMap = new HashMap<>();

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	public GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean() {
		return gtnFrameworkEntityMasterBean;
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public GtnFrameworkHierarchyService getHierarchyService() {
		return hierarchyService;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, params, type);
	}

	public int executeUpdateQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery);
	}

	public int executeUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery, params, type);
	}

	public String getQuery(String sqlId) {
		return gtnWsSqlService.getQuery(sqlId);
	}

	public Object[] createParams(Object... params) {
		return params;
	}

	public GtnFrameworkDataType[] createDataTypes(GtnFrameworkDataType... dataTypes) {
		return dataTypes;
	}

	public GtnWsRelationshipBuilderHelperLogic getHelperLogic() {
		return helperLogic;
	}

	public void setHelperLogic(GtnWsRelationshipBuilderHelperLogic helperLogic) {
		this.helperLogic = helperLogic;
	}

	public GtnWsRelationshipBuilderLogic getLogic() {
		return logic;
	}

	public void setLogic(GtnWsRelationshipBuilderLogic logic) {
		this.logic = logic;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsRelationshipBuilderConstants.GET_RELATIONSHIP_BUILDER_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRelationshipBuilderTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse relationshipBuilderResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse relationshipBuilderSerachResponse = new GtnSerachResponse();
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getRelationshipBuilderCount"
					: "getRelationshipBuilderResults";
			List<String> inputlist = getSearchInput(gtnWsRequest);
			List<Object[]> result = executeQuery(getQuery(inputlist, queryName));
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				relationshipBuilderSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				relationshipBuilderSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			relationshipBuilderResponse.setGtnSerachResponse(relationshipBuilderSerachResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex // | SQLException ex
		) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		relationshipBuilderResponse.setGtnWsGeneralResponse(generalResponse);
		return relationshipBuilderResponse;
	}

	@RequestMapping(value = GtnWsRelationshipBuilderConstants.GET_RELATIONSHIP_BUILDER_AVAILABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getRelationshipBuilderAvailableTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse relationshipBuilderResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse relationshipBuilderSerachResponse = logic.getHistAllLevelValues(gtnWsRequest);
			relationshipBuilderResponse.setGtnSerachResponse(relationshipBuilderSerachResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		relationshipBuilderResponse.setGtnWsGeneralResponse(generalResponse);
		return relationshipBuilderResponse;
	}

	private List<String> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<String> list = new ArrayList<>();

		StringBuilder inputWhereConditions = new StringBuilder();
		String and = " AND ";
		String where = " WHERE (RB.DEDUCTION_RELATION is null OR RB.DEDUCTION_RELATION=0)";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			String audit = "";
			for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {

				if (searchCriteria.isFilter()) {
					String value = searchCriteria.getFilterValue1();
					if ("LIKE".equalsIgnoreCase(searchCriteria.getExpression())) {
						value = "%" + searchCriteria.getFilterValue1() + "%";
					}
					inputWhereConditions.append(where).append(and)
							.append(GtnCommonUtil.getWhereClauseForAColumn(searchCriteria.getExpression(),
									filterAndSortingRBCriteriaMap().get(searchCriteria.getFieldId()), value,
									searchCriteria.getFilterValue2()));
					and = GtnFrameworkWebserviceConstant.AND_COLUMN;
					where = "";
				} else if ("RBSearch_auditSearchParam".equals(searchCriteria.getFieldId())) {
					audit = "HIST_";
				} else if (searchCriteria.getFieldId().contains("DateFrom")) {

					inputWhereConditions.append(where).append(and)
							.append(searchCriteriaMap().get(searchCriteria.getFieldId()))
							.append(GtnFrameworkWebserviceConstant.GREATER_VAR).append(searchCriteria.getFilterValue1())
							.append("'");
					and = GtnFrameworkWebserviceConstant.AND_COLUMN;
					where = "";
				} else if (searchCriteria.getFieldId().contains("DateTo")) {

					inputWhereConditions.append(where).append(and)
							.append(searchCriteriaMap().get(searchCriteria.getFieldId()))
							.append(GtnFrameworkWebserviceConstant.LESSER).append(searchCriteria.getFilterValue1())
							.append("'");
					and = GtnFrameworkWebserviceConstant.AND_COLUMN;
					where = "";
				} else {
					inputWhereConditions.append(where).append(and)
							.append(searchCriteriaMap().get(searchCriteria.getFieldId())).append(" ")
							.append(searchCriteria.getExpression().replace("EQUALS", "=")).append(" '")
							.append(searchCriteria.getFilterValue1().replace("*", "%")).append("'");
					and = GtnFrameworkWebserviceConstant.AND_COLUMN;
					where = "";
				}
			}
			list.add(audit);
			list.add(connection.getCatalog());
			list.add(inputWhereConditions.toString());

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				list.addAll(
						getSortedInputs(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				list.add(String.valueOf(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart()));
				list.add(String.valueOf(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset()));
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	private List<String> getSortedInputs(List<GtnWebServiceOrderByCriteria> relationshipBuilderOrderByCriteriaList) {
		List<String> list = new ArrayList<>();
		if (!relationshipBuilderOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = relationshipBuilderOrderByCriteriaList.get(0);
			list.add(filterAndSortingRBCriteriaMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(filterAndSortingRBCriteriaMap().get("creationDate") + " ASC");
		}
		return list;
	}

	private Map<String, String> filterAndSortingRBCriteriaMap() {
		if (filterAndSortingRBCriteriaMap.isEmpty()) {
			filterAndSortingRBCriteriaMap.put("relationshipName", "RB.RELATIONSHIP_NAME");
			filterAndSortingRBCriteriaMap.put("relationshipDescription", "RB.RELATIONSHIP_DESCRIPTION");
			filterAndSortingRBCriteriaMap.put("relationshipType", "HT.DESCRIPTION");
			filterAndSortingRBCriteriaMap.put("hierarchyName", "HD.HIERARCHY_NAME");
			filterAndSortingRBCriteriaMap.put("versionNo", "RB.VERSION_NO");
			filterAndSortingRBCriteriaMap.put("startDate", GtnFrameworkWebserviceConstant.RBSTART_DATE);
			filterAndSortingRBCriteriaMap.put("creationDate", GtnFrameworkWebserviceConstant.RBCREATED_DATE);
			filterAndSortingRBCriteriaMap.put("modifiedDate", "RB.MODIFIED_DATE");
			filterAndSortingRBCriteriaMap.put("createdBy", "usr.lastName + ' ' + usr.middleName + ' ' + usr.firstName");

		}
		return filterAndSortingRBCriteriaMap;
	}

	private Map<String, String> searchCriteriaMap() {
		if (searchCriteriaMap.isEmpty()) {
			searchCriteriaMap.put("RBSearch_relationshipName", "RB.RELATIONSHIP_NAME");
			searchCriteriaMap.put("RBSearch_hierarchyName", "RB.HIERARCHY_DEFINITION_SID");
			searchCriteriaMap.put("RBSearch_relationshipDescription", "RB.RELATIONSHIP_DESCRIPTION");
			searchCriteriaMap.put("RBSearch_startDateFrom", GtnFrameworkWebserviceConstant.RBSTART_DATE);
			searchCriteriaMap.put("RBSearch_startDateTo", GtnFrameworkWebserviceConstant.RBSTART_DATE);
			searchCriteriaMap.put("RBSearch_relationshipType", "HT.DESCRIPTION");
			searchCriteriaMap.put("RBSearch_creationDateFrom", GtnFrameworkWebserviceConstant.RBCREATED_DATE);
			searchCriteriaMap.put("RBSearch_creationDateTo", GtnFrameworkWebserviceConstant.RBCREATED_DATE);
		}
		return searchCriteriaMap;
	}

	@RequestMapping(value = GtnWsRelationshipBuilderConstants.GET_VERSION_NO, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getVersionNo(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getVersionNo");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
			gtnResponse.setGtnWsRelationshipBuilderResponse(
					logic.getHierarchyVersionNo(gtnWsRequest.getRelationshipBuilderRequest(), rbResponse));
			int versionNo = gtnResponse.getGtnWsRelationshipBuilderResponse().getHierarchyVersionNo();
			logic.updateQueryInHierarchy(gtnWsRequest.getRelationshipBuilderRequest().getHierarchyDefSId(), versionNo);
		} catch (Exception ex) {
			logger.error("Exception in getVersionNo", ex);
		}
		logger.info("Exit getVersionNo");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsRelationshipBuilderConstants.CHECK_SAVE_RELATIONSHIP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkSaveRelationship(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters checkSaveRelationship");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
			gtnResponse.setGtnWsRelationshipBuilderResponse(rbResponse);
			logic.checkSaveRelationship(gtnWsRequest.getRelationshipBuilderRequest(), rbResponse);
		} catch (Exception ex) {
			logger.error("Exception in checkSaveRelationship", ex);
		}

		logger.info("Exit checkSaveRelationship");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsRelationshipBuilderConstants.SAVE_RELATIONSHIP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveRelationship(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters saveRelationship");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
			gtnResponse.setGtnWsRelationshipBuilderResponse(rbResponse);
			logic.saveRelationship(gtnWsRequest.getRelationshipBuilderRequest(), rbResponse);
		} catch (Exception ex) {
			logger.error("Exception in saveRelationship", ex);
		}

		logger.info("Exit saveRelationship");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsRelationshipBuilderConstants.DELETE_RELATIONSHIP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteRelationship(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters deleteRelationship");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
			gtnResponse.setGtnWsRelationshipBuilderResponse(rbResponse);
			logic.deleteRelationship(gtnWsRequest.getRelationshipBuilderRequest(), rbResponse);
		} catch (Exception ex) {
			logger.error("Exception in deleteRelationship", ex);
		}

		logger.info("Exit deleteRelationship");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsRelationshipBuilderConstants.LOAD_RELATIONSHIP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadRelationship(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters loadRelationship");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsRelationshipBuilderResponse rbResponse = new GtnWsRelationshipBuilderResponse();
			gtnResponse.setGtnWsRelationshipBuilderResponse(rbResponse);
			logic.loadRelationship(gtnWsRequest.getRelationshipBuilderRequest(), rbResponse);
		} catch (Exception ex) {
			logger.error("Exception in loadRelationship", ex);
		}

		logger.info("Exit loadRelationship");
		return gtnResponse;
	}

	public String getQuery(List<String> input, String queryName) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(getQuery(queryName));
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

}
