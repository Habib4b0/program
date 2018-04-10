package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
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

		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(
				lastlinkedLevel,
				"LOAD_SELECTED_PRODUCT", hierarchyDefinitionList);
		queryGeneratorService.getWhereQueryBasedOnHierarchyType(inputBean.getHierarchyType(),
				inputBean.getGroupFilterCompenies(), queryBean);
		queryBean.addOrderByClauseBean("HIERARCHY_NO_JOIN.LEVEL_NO", "ASC");
		return gtnWsSqlService.getReplacedQuery(input, queryBean.generateQuery());
	}

}
