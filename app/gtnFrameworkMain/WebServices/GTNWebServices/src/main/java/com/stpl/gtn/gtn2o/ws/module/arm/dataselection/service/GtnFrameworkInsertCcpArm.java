/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Sathya.Seelan
 */
@Service
@Scope(value = "singleton")
public class GtnFrameworkInsertCcpArm {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationController.class);

	public GtnFrameworkInsertCcpArm() {
		super();
	}

	public void insertToCPPTable(GtnARMHierarchyInputBean armInputBean) {
		try {
			List<HierarchyLevelDefinitionBean> armCustomerHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(armInputBean.getSelectedCustomerHierarcySid(),
							armInputBean.getSelectedCustomerHierarchyVersionNo());
			List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(armInputBean.getSelectedProductHierarcySid(),
							armInputBean.getSelectedProductHierarchyVersionNo());
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerRelationLevelList = armInputBean
					.getSelectedCustomerList();
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductRelationLevelList = armInputBean
					.getSelectedProductList();
			String armCustomerHierarchyQuery = getCustomerAndContractHierarchyQuery(
					armCustomerHierarchyLevelDefinitionList, selectedCustomerRelationLevelList, false);
			String armProductHierarchyQuery = getCustomerAndContractHierarchyQuery(productHierarchyLevelDefinitionList,
					selectedProductRelationLevelList, true);
			List<String> input = new ArrayList<>();
			input.add(armCustomerHierarchyQuery);
			input.add(armProductHierarchyQuery);
			String withTableNameQuery = replaceTableNames(gtnWsSqlService.getQuery(input, "ccpInsertQuery"),
					armInputBean.getTempTableMap());
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(withTableNameQuery);
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private String getCustomerAndContractHierarchyQuery(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
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
		return gtnWsSqlService.getReplacedQuery(input, queryBean.generateQuery());
	}

	public static String replaceTableNames(String query, final Map<String, String> tableNameMap) {
		String tempQuery = query;
		for (Map.Entry<String, String> key : tableNameMap.entrySet()) {
			tempQuery = tempQuery.replaceAll("(?i:\\b" + key.getKey() + "\\b)", key.getValue());
		}
		return tempQuery;
	}

}
