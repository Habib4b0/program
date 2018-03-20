package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.service.GtnFrameworkCCPInsertQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl.GtnFrameworkCCPCustomerQueryGenerator;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkCCPInsertService {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	GtnFrameworkCCPCustomerQueryGenerator queryGeneratorService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private ApplicationContext applicationContext;

	public void insertToCPPTable(GtnForecastHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
		try {
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getSelectedCustomerHierarcySid(),
							inputBean.getSelectedCustomerHierarchyVersionNo());
			List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getSelectedProductHierarcySid(),
							inputBean.getSelectedProductHierarchyVersionNo());
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerRelationLevelList = inputBean
					.getSelectedCustomerList();
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductRelationLevelList = inputBean
					.getSelectedProductList();
			String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(inputBean,
					customerHierarchyLevelDefinitionList, selectedCustomerRelationLevelList, false);
			String productHierarchyQuery = getCustomerAndContractHierarchyQuery(inputBean,
					productHierarchyLevelDefinitionList, selectedProductRelationLevelList, true);
			List<String> input = new ArrayList<>();
			input.add(customerHierarchyQuery);
			input.add(productHierarchyQuery);
			String withTableNameQuery = replaceTableNames(gtnWsSqlService.getQuery(input, "ccpInsertQuery"),
					inputBean.getTempTableMap());
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(withTableNameQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertToCPPTableForARP(GtnForecastHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
		try {
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getSelectedCustomerHierarcySid(),
							inputBean.getSelectedCustomerHierarchyVersionNo());
			List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getSelectedProductHierarcySid(),
							inputBean.getSelectedProductHierarchyVersionNo());
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerRelationLevelList = inputBean
					.getSelectedCustomerList();
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductRelationLevelList = inputBean
					.getSelectedProductList();
			String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(inputBean,
					customerHierarchyLevelDefinitionList, selectedCustomerRelationLevelList, false);
			String productHierarchyQuery = getCustomerAndContractHierarchyQuery(inputBean,
					productHierarchyLevelDefinitionList, selectedProductRelationLevelList, true);
			List<Object> input = new ArrayList<>();
			input.add(customerHierarchyQuery);
			input.add(productHierarchyQuery);
			input.add(inputBean.getProjectionId());
			input.add(inputBean.getDeductionLevel());
			input.add(inputBean.getDeductionValue());
			String withTableNameQuery = replaceTableNames(gtnWsSqlService.getQuery(input, "ccpInsertQueryForARP"),
					inputBean.getTempTableMap());
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(withTableNameQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getCustomerAndContractHierarchyQuery(GtnForecastHierarchyInputBean inputBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedRelationLevelList, boolean isProduct) {


		int relationSid = selectedRelationLevelList.get(0).getRelationshipBuilderSid();
		int relationVersionNo = selectedRelationLevelList.get(0).getRelationshipVersionNo();
		List<Object> input = new ArrayList<>();
		StringBuilder finalQuery = getParentHierarchyNo(hierarchyLevelDefinitionList,
				hierarchyLevelDefinitionList.get(hierarchyLevelDefinitionList.size() - 1));

		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyLevelDefinitionList);
		input.add(finalQuery);
		input.add(relationVersionNo);
		input.add(lastLevelDto.getLevelNo());
		input.add(relationSid);
		String beanName = isProduct ? "ProductQueryGenerator" : "CustomerQueryGenerator";
		GtnFrameworkCCPInsertQueryGenerator queryGeneratorService = (GtnFrameworkCCPInsertQueryGenerator) applicationContext
				.getBean(beanName);
		StringBuilder query = queryGeneratorService.getCCPInsertQuery(selectedRelationLevelList,
				hierarchyLevelDefinitionList, relationVersionNo);
		if (!isProduct) {
			List<Object> input1 = new ArrayList<>();
			input1.add(inputBean.getProjectionId());
			query.append(gtnWsSqlService.getQuery(input1, "filtercontractsbasedoneligibledate"));
		}

		return gtnWsSqlService.getReplacedQuery(input, query.toString());
	}

	public static String replaceTableNames(String query, final Map<String, String> tableNameMap) {
		for (String key : tableNameMap.keySet()) {
			query = query.replaceAll("(?i:\\b" + key + "\\b)", tableNameMap.get(key));
		}
		return query;
	}

	public StringBuilder getParentHierarchyNo(List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				query.append(",'%'");
				query.append(",'.'");
				continue;
			}
			query.append(',');
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			query.append(singleColumnRelationBean.getActualTtableName()).append('.')
					.append(singleColumnRelationBean.getWhereClauseColumn());
			query.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(query);
		finalQuery.append(')');
		return finalQuery;
	}

}
