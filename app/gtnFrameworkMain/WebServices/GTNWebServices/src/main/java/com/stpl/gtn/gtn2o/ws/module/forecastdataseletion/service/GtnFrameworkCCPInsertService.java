package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkCCPInsertService {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationController.class);

	public GtnFrameworkCCPInsertService() {
		super();
	}
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
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
		}
	}

	private String getCustomerAndContractHierarchyQuery(GtnForecastHierarchyInputBean inputBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedRelationLevelList, boolean isProduct) {

		int relationSid = selectedRelationLevelList.get(0).getRelationshipBuilderSid();
		int relationVersionNo = selectedRelationLevelList.get(0).getRelationshipVersionNo();
		List<Object> input = new ArrayList<>();

		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyLevelDefinitionList);
		input.add(relationVersionNo);
		input.add(lastLevelDto.getLevelNo());
		input.add(relationSid);

		String beanName = isProduct ? "CCP_INSERT_PRODUCT" : "CCP_INSERT_CUSTOMER";
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
				beanName, hierarchyLevelDefinitionList);
		queryGeneratorService.getWhereQueryForCustomerAndContract(selectedRelationLevelList, queryBean);
		StringBuilder query = new StringBuilder(queryBean.generateQuery());
		if (!isProduct) {
			List<Object> input1 = new ArrayList<>();
			input1.add(inputBean.getProjectionId());
			query.append(gtnWsSqlService.getQuery(input1, "filtercontractsbasedoneligibledate"));
		}

		return gtnWsSqlService.getReplacedQuery(input, query.toString());
	}


	public static String replaceTableNames(String query, final Map<String, String> tableNameMap) {
		String tempQuery = query;
		for (Map.Entry<String, String> key : tableNameMap.entrySet()) {
			tempQuery = tempQuery.replaceAll("(?i:\\b" + key.getKey() + "\\b)", key.getValue());
		}
		return tempQuery;
	}



}
