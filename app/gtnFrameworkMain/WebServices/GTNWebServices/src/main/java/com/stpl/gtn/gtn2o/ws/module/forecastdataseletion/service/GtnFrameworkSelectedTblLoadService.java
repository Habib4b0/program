package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkSelectedTblLoadService {
	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine;
	//private static final Logger logger = LogManager.getLogger(GtnFrameworkSelectedTblLoadService.class);

	public GtnFrameworkSelectedTblLoadService() {
		super();
	}

	public String getQueryForSelectedCustomer(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {
		GtnFrameworkRelationshipLevelDefintionBean selecteHierarchyBean = inputBean.getSelectedHierarchyLevelDto();
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService.getHierarchyBuilder(
				selecteHierarchyBean.getHierarchyDefinitionSid(), selecteHierarchyBean.getHierarchyVersionNo());
		HierarchyLevelDefinitionBean lastlinkedLevel = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService
				.getQuerybySituationNameAndLevel(lastlinkedLevel, "LOAD_SELECTED_CUSTOMER", hierarchyDefinitionList);
		queryGeneratorService.getWhereQueryBasedOnHierarchyType(inputBean.getHierarchyType(),
				inputBean.getGroupFilterCompenies(), queryBean);
		return queryBean.generateQuery();
	}

	public List<String> getResultForSelectedCustomer(StringBuilder inputQuery, List<Object> inputValuesList)
			throws GtnFrameworkGeneralException {
		String query = getQuery(inputQuery.toString(), inputValuesList);
		return (List<String>) gtnFrameworkSqlQueryEngine.executeSelectQuery(query);
	}

	public static String getQuery(String query, List input) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(query);
			for (Object temp : input) {
				sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
			}

		} catch (Exception ex) {
			//logger.error(ex.getMessage(), ex);
		}
		return sql.toString();
	}

	public String getChildLevelQueryForProduct(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {

		GtnFrameworkRelationshipLevelDefintionBean selecteHierarchyBean = inputBean.getSelectedHierarchyLevelDto();
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService.getHierarchyBuilder(
				selecteHierarchyBean.getHierarchyDefinitionSid(), selecteHierarchyBean.getHierarchyVersionNo());
		HierarchyLevelDefinitionBean lastlinkedLevel = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		List<Object> input = new ArrayList<>();
		input.add(selecteHierarchyBean.getRelationshipVersionNo());
		input.add(lastlinkedLevel.getLevelNo());
		input.add("'" + selecteHierarchyBean.getHierarchyNo() + "%'");
		input.add(selecteHierarchyBean.getRelationshipBuilderSid());
		input.add(selecteHierarchyBean.getRelationshipVersionNo());
		input.add(inputBean.getLowestLevelNo() + 1);
		input.add("'" + selecteHierarchyBean.getHierarchyNo() + "'");

		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService
				.getQuerybySituationNameAndLevel(lastlinkedLevel, "LOAD_SELECTED_PRODUCT", hierarchyDefinitionList);
		queryGeneratorService.getWhereQueryBasedOnHierarchyType(inputBean.getHierarchyType(),
				inputBean.getGroupFilterCompenies(), queryBean);
		queryBean.addOrderByClauseBean("HIERARCHY_NO_JOIN.LEVEL_NO", "ASC");
		return gtnWsSqlService.getReplacedQuery(input, queryBean.generateQuery());
	}

	public String getChildLevelQueryForReportProduct(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {

		GtnFrameworkRelationshipLevelDefintionBean selecteHierarchyBean = inputBean.getSelectedHierarchyLevelDto();
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService.getHierarchyBuilder(
				selecteHierarchyBean.getHierarchyDefinitionSid(), selecteHierarchyBean.getHierarchyVersionNo());
		HierarchyLevelDefinitionBean lastlinkedLevel = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService
				.getQuerybySituationNameAndLevel(lastlinkedLevel, "LOAD_SELECTED_PRODUCT", hierarchyDefinitionList);
		queryGeneratorService.getWhereQueryBasedOnHierarchyType(inputBean.getHierarchyType(),
				inputBean.getGroupFilterCompenies(), queryBean);
		queryBean.addOrderByClauseBean("HIERARCHY_NO_JOIN.LEVEL_NO", "ASC");
		return queryBean.generateQuery();
	}

	public List<Object[]> getResultForSelectedProduct(StringBuilder inputQuery, List<Object> inputValuesList)
			throws GtnFrameworkGeneralException {
		String query = getQuery(inputQuery.toString(), inputValuesList);
		return (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(query);
	}

	public List<Object[]> getChildLevelQueryForCustomer(List<Object> inputsForRelationQuery)
			throws GtnFrameworkGeneralException {
		String query = gtnWsSqlService.getQuery("childLevelsHierarchyNo");
		String replacedQuery = getQuery(query, inputsForRelationQuery);
		return (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(replacedQuery);
	}
}
